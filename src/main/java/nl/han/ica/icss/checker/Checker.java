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
    private int currentStyleRule = 1;
    private Map<ExpressionType, List<String>> properties = new HashMap<>();


    public Checker() {
        List<String> PERCENTAGE_PIXEL_PROPERTIES = new ArrayList<>(List.of("width", "height"));
        List<String> COLOR_PROPERTIES = new ArrayList<>(List.of("color", "background-color"));

        properties.put(ExpressionType.PERCENTAGE, PERCENTAGE_PIXEL_PROPERTIES);
        properties.put(ExpressionType.PIXEL, PERCENTAGE_PIXEL_PROPERTIES);
        properties.put(ExpressionType.COLOR, COLOR_PROPERTIES);

    }

    public void check(AST ast) {
        setVariableTypes(ast);
        int scopeLevel = 0;
        for (ASTNode styleRule : ast.getStyleRules()) {
            scopeLevel++;
            currentStyleRule = scopeLevel;
            for (ASTNode node : ((Stylerule) styleRule).body) {
                scopeLevel = validateBody(node, scopeLevel);
            }
        }
    }

    private int validateBody(ASTNode node, int scopeLevel) {
        if (node instanceof IfClause) {
            return validateIfClauseConditions(((IfClause) node).conditionalExpression, (IfClause) node, scopeLevel);
        }
        if (node instanceof Declaration) {
            Expression expression = ((Declaration) node).expression;
            validateVariables(expression, scopeLevel, currentStyleRule);
            validatePropertyValueTypes(((Declaration) node).property, expression, scopeLevel);
            validateOperands(expression, scopeLevel);
        }
        return scopeLevel;
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

    private int validateIfClauseConditions(Expression expression, IfClause ifClause, int scopeLevel) {
        if (getExpressionType(expression, scopeLevel) != ExpressionType.BOOL) {
            ifClause.conditionalExpression.setError("The condition must be the type boolean.");
        }

        scopeLevel++;

        for (ASTNode node : ifClause.body) {
            if (!(node instanceof VariableAssignment)) {
                scopeLevel = validateBody(node, scopeLevel);
            }
        }
        return scopeLevel;
    }

    private void validateVariables(Expression expression, int scopeLevel, int currentStyleRule) {
        if (expression instanceof Operation) {
            validateVariables(((Operation) expression).rhs, scopeLevel, currentStyleRule);
            validateVariables(((Operation) expression).lhs, scopeLevel, currentStyleRule);
        }
        if (expression instanceof VariableReference) {
            if (scopeLevel < variableTypes.size()) {
                for (int i = scopeLevel; i >= currentStyleRule; i--) {
                    if (variableTypes.get(i).containsKey(((VariableReference) expression).name)) {
                        return;
                    }
                }

                if (variableTypes.getFirst().containsKey(((VariableReference) expression).name)) {
                    return;
                }
            }
            expression.setError("Variable " + ((VariableReference) expression).name + " is not defined.");
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
        return properties.get(expressionType) != null && properties.get(expressionType).contains(property.name);
    }

    private ExpressionType getExpressionType(Expression expression, int scopeLevel) {
        if (expression instanceof VariableReference) {
            if (scopeLevel < variableTypes.size()) {
                for (int i = scopeLevel; i >= currentStyleRule; i--) {
                    if (variableTypes.get(i).containsKey(((VariableReference) expression).name)) {
                        return variableTypes.get(i).get(((VariableReference) expression).name);
                    }
                }
                if (variableTypes.getFirst().containsKey(((VariableReference) expression).name)) {
                    return variableTypes.getFirst().get(((VariableReference) expression).name);
                }
                return ExpressionType.UNDEFINED;
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
