package nl.han.ica.icss.checker;

import java.util.*;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private LinkedList<Map<String, ExpressionType>> variableTypes = new LinkedList<>();

    public void check(AST ast) {
        setVariableTypes(ast);
        for (ASTNode styleRule : ast.getStyleRules()) {
            validateDeclaration(styleRule, 0);
        }
    }

    private void validateDeclaration(ASTNode node, int scopeLevel) {
        if (node instanceof IfClause) {
            validateIfClauseConditions(((IfClause) node).conditionalExpression, (IfClause) node, scopeLevel);
        }
        if (node instanceof Declaration) {
            Expression expression = ((Declaration) node).expression;
            validateVariables(expression, scopeLevel);
            validatePropertyValueTypes(((Declaration) node).property, expression, scopeLevel);
            validateOperands(expression, scopeLevel);
        }
    }

    private void validateOperands(Expression expression, int scopeLevel) {
        if (!(expression instanceof Operation)) return;

        Operation operation = (Operation) expression;

        // check the lhs and rhs recursively because they could be operations as well
        validateOperands(operation.lhs, scopeLevel);
        validateOperands(operation.rhs, scopeLevel);

        ExpressionType lhsType = getExpressionType(operation.lhs, scopeLevel);
        ExpressionType rhsType = getExpressionType(operation.rhs, scopeLevel);

        if (lhsType == ExpressionType.COLOR || rhsType == ExpressionType.COLOR) {
            operation.setError("An operand may not be the type COLOR.");
        }

        if (operation instanceof AddOperation || operation instanceof SubtractOperation) {
            if (lhsType != rhsType) {
                operation.setError("The operands must be the same type.");
            }
        }
        if (operation instanceof MultiplyOperation) {
            if (!(lhsType == ExpressionType.SCALAR || rhsType == ExpressionType.SCALAR)) {
                operation.setError("One of the operands must be the type SCALAR.");
            }
        }

    }

    private void validateIfClauseConditions(Expression expression, IfClause ifClause, int scopeLevel) {
        if (getExpressionType(expression, scopeLevel) != ExpressionType.BOOL) {
            ifClause.conditionalExpression.setError("The condition must be the type boolean.");
        }

        scopeLevel++;

        for (ASTNode node : ifClause.body) {
            validateDeclaration(node, scopeLevel);
        }
    }

    private void validateVariables(Expression expression, int scopeLevel) {
        if (expression instanceof Operation) {
            validateVariables(((Operation) expression).rhs, scopeLevel);
            validateVariables(((Operation) expression).lhs, scopeLevel);
        }
        if (expression instanceof VariableReference) {
            if ((variableTypes.get(scopeLevel) == null || !(variableTypes.get(scopeLevel).containsKey(((VariableReference) expression).name)))
                    && !variableTypes.getFirst().containsKey(((VariableReference) expression).name)) {
                expression.setError("Variable " + ((VariableReference) expression).name + " is not defined.");
            }
        }
    }

    private void setVariableTypes(AST ast) {
        LinkedList<Map<String, Expression>> variableAssignments = ast.getVariableAssignments();
        for (Map<String, Expression> scope : variableAssignments) {
            Map<String, ExpressionType> scopeVariableTypes = new HashMap<>();
            for (String var : scope.keySet()) {
                scopeVariableTypes.put(var, mapToExpressionType(scope.get(var)));
            }
            variableTypes.add(scopeVariableTypes);
        }
    }

    private void validatePropertyValueTypes(PropertyName property, Expression expression, int scopeLevel) {
        ExpressionType expressionType = getExpressionType(expression, scopeLevel);

        boolean isCompatible = isExpressionCompatibleWithProperty(property, expressionType);

        if (!isCompatible) {
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

    private boolean isExpressionCompatibleWithProperty(PropertyName property, ExpressionType expressionType) {
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
        return match;
    }

    private ExpressionType getExpressionType(Expression expression, int scopeLevel) {
        if (expression instanceof VariableReference) {
            if (variableTypes.get(scopeLevel) != null && variableTypes.get(scopeLevel).containsKey(((VariableReference) expression).name)) {
                return variableTypes.get(scopeLevel).get(((VariableReference) expression).name);
            } else {
                return variableTypes.getFirst().get(((VariableReference) expression).name);
            }
        }
        if (expression instanceof Operation) {
            ExpressionType lhsType = getExpressionType(((Operation) expression).lhs, scopeLevel);
            ExpressionType rhsType = getExpressionType(((Operation) expression).rhs, scopeLevel);

            if (expression instanceof MultiplyOperation) {
                return lhsType == ExpressionType.SCALAR
                        ? rhsType
                        : lhsType;
            } else {
                return lhsType;
            }
        }
        return mapToExpressionType(expression);
    }

    private ExpressionType mapToExpressionType(ASTNode node) {
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
