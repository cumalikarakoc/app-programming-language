package nl.han.ica.icss.checker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private HashMap<String, ExpressionType> variableTypes = new HashMap<>();

    public void check(AST ast) {
        setVariableTypes(ast);
        List<ASTNode> declarations = ast.getDeclarations();
        for (ASTNode declaration : declarations) {
            validateDeclaration(declaration);
        }
    }

    private void validateDeclaration(ASTNode node) {
        if (node instanceof IfClause) {
            validateIfClauseConditions(((IfClause) node).conditionalExpression, (IfClause) node);
        }
        if (node instanceof Declaration) {
            Expression expression = ((Declaration) node).expression;
            validateVariables(expression);
            validatePropertyValueTypes(((Declaration) node).property, expression);
            validateOperands(expression);
        }
    }

    private void validateOperands(Expression expression) {
        if (!(expression instanceof Operation)) return;

        Operation operation = (Operation) expression;

        // check the lhs and rhs recursively because they could be operations as well
        validateOperands(operation.lhs);
        validateOperands(operation.rhs);

        ExpressionType lhsType = getExpressionType(operation.lhs);
        ExpressionType rhsType = getExpressionType(operation.rhs);

        if (lhsType == ExpressionType.COLOR || rhsType == ExpressionType.COLOR) {
            operation.setError("An operand may not be type COLOR.");
        }

        if (operation instanceof AddOperation || operation instanceof SubtractOperation) {
            if (lhsType != rhsType) {
                operation.setError("The operands should be the same type.");
            }
        }
        if (operation instanceof MultiplyOperation) {
            if (!(lhsType == ExpressionType.SCALAR || rhsType == ExpressionType.SCALAR)) {
                operation.setError("One of the operands must be type SCALAR.");
            }
        }

    }

    private void validateIfClauseConditions(Expression expression, IfClause ifClause) {
        if (getExpressionType(expression) != ExpressionType.BOOL) {
            ifClause.conditionalExpression.setError("The condition should be type boolean.");
        }

        for (ASTNode node : ifClause.body) {
            validateDeclaration(node);
        }
    }

    private void validateVariables(Expression expression) {
        if (expression instanceof Operation) {
            validateVariables(((Operation) expression).rhs);
            validateVariables(((Operation) expression).lhs);
        }
        if (expression instanceof VariableReference) {
            if (!variableTypes.containsKey(((VariableReference) expression).name)) {
                expression.setError("Variable " + ((VariableReference) expression).name + " is not defined.");
            }
        }
    }

    private void setVariableTypes(AST ast) {
        for (ASTNode node : ast.getVariableAssignments()) {
            variableTypes.put(((VariableAssignment) node).name.name, mapLiteralToExpressionType(((VariableAssignment) node).expression));
        }
    }

    private void validatePropertyValueTypes(PropertyName property, Expression expression) {
        ExpressionType expressionType = getExpressionType(expression);
        boolean match;
        switch (property.name) {
            case "width":
            case "height":
                match = expressionType == ExpressionType.PERCENTAGE || expressionType == ExpressionType.PIXEL;
                break;
            case "color":
            case "background-color":
                match = expressionType == ExpressionType.COLOR;
                break;
            default:
                match = false;
        }

        if (!match) {
            StringBuilder sb = new StringBuilder();
            sb.append("Property ").append(property.name);
            if (expressionType == null) {
                sb.append(" may not be null");
            } else {
                sb.append(" is incompatible with type ").append(expressionType);
            }
            property.setError(sb.toString());
        }
    }

    private ExpressionType getExpressionType(Expression expression) {
        if (expression instanceof VariableReference) {
            return variableTypes.get(((VariableReference) expression).name);
        }
        if (expression instanceof Operation) {
            ExpressionType lhsType = getExpressionType(((Operation) expression).lhs);
            ExpressionType rhsType = getExpressionType(((Operation) expression).rhs);

            if (expression instanceof MultiplyOperation) {
                return lhsType == ExpressionType.SCALAR
                        ? rhsType
                        : lhsType;
            } else {
                return lhsType;
            }
        }
        return mapLiteralToExpressionType(expression);
    }

    private ExpressionType mapLiteralToExpressionType(ASTNode node) {
        if (node instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        }
        if (node instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        }
        if (node instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        }
        if (node instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        }
        if (node instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        }
        return ExpressionType.UNDEFINED;
    }
}
