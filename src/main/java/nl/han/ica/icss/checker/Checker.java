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
    private int scopeLevel = 0;
    private Map<ExpressionType, List<String>> properties = new HashMap<>();


    public Checker() {
        List<String> percentagePixelProperties = new ArrayList<>(List.of("width", "height"));
        List<String> colorProperties = new ArrayList<>(List.of("color", "background-color"));

        properties.put(ExpressionType.PERCENTAGE, percentagePixelProperties);
        properties.put(ExpressionType.PIXEL, percentagePixelProperties);
        properties.put(ExpressionType.COLOR, colorProperties);
    }

    public void check(AST ast) {
        setVariableTypes(ast);
        for (ASTNode styleRule : ast.getStyleRules()) {
            scopeLevel++;
            currentStyleRule = scopeLevel;
            for (ASTNode node : ((Stylerule) styleRule).body) {
                validateBody(node);
            }
        }
    }

    private void validateBody(ASTNode node) {
        if (node instanceof IfClause) {
            validateIfClauseConditions(((IfClause) node).conditionalExpression, (IfClause) node);
            return;
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

    private void validateIfClauseConditions(Expression expression, IfClause ifClause) {
        if (getExpressionType(expression) != ExpressionType.BOOL) {
            ifClause.conditionalExpression.setError("The condition must be the type boolean.");
        }

        scopeLevel++;

        for (ASTNode node : ifClause.body) {
            if (!(node instanceof VariableAssignment)) {
                validateBody(node);
            }
        }
    }

    private void validateVariables(Expression expression) {
        if (expression instanceof Operation) {
            validateVariables(((Operation) expression).rhs);
            validateVariables(((Operation) expression).lhs);
        }
        if (expression instanceof VariableReference) {
            if (scopeLevel < variableTypes.size()) {
                for (int i = scopeLevel; i >= currentStyleRule; i--) {
                    if (variableTypes.get(i).containsKey(((VariableReference) expression).name)) {
                        return;
                    }
                }
            }
            if (variableTypes.getFirst().containsKey(((VariableReference) expression).name)) {
                return;
            }
            expression.setError("Variable " + ((VariableReference) expression).name + " is not defined.");
        }
    }

    private void setVariableTypes(AST ast) {
        LinkedList<Map<String, Expression>> variableAssignments = ast.getVariableAssignments();
        int scopeLevel = 0;
        for (Map<String, Expression> scope : variableAssignments) {
            if (scope.isEmpty()) {
                variableTypes.add(new HashMap<>());
            } else {
                Map<String, ExpressionType> scopeVariableTypes = new HashMap<>();
                for (String var : scope.keySet()) {
                    scopeVariableTypes.put(var, getExpressionType(scope.get(var)));
                    if (scopeLevel < variableTypes.size()) {
                        variableTypes.get(scopeLevel).putAll(scopeVariableTypes);
                    } else {
                        variableTypes.add(scopeVariableTypes);
                    }
                    validateOperands(scope.get(var));
                    validateVariables(scope.get(var));
                }
            }
            scopeLevel++;
        }
    }

    private void validatePropertyValueTypes(PropertyName property, Expression expression) {
        ExpressionType expressionType = getExpressionType(expression);

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

    private ExpressionType getExpressionType(Expression expression) {
        if (expression instanceof VariableReference) {
            if (scopeLevel < variableTypes.size()) {
                for (int i = scopeLevel; i >= currentStyleRule; i--) {
                    if (variableTypes.get(i).containsKey(((VariableReference) expression).name)) {
                        return variableTypes.get(i).get(((VariableReference) expression).name);
                    }
                }
            }
            if (variableTypes.getFirst().containsKey(((VariableReference) expression).name)) {
                return variableTypes.getFirst().get(((VariableReference) expression).name);
            }
        }
        if (expression instanceof Operation) {
            ExpressionType lhsType = getExpressionType(((Operation) expression).lhs);
            ExpressionType rhsType = getExpressionType(((Operation) expression).rhs);

            if (expression instanceof MultiplyOperation) {
                if (lhsType != ExpressionType.SCALAR && rhsType != ExpressionType.SCALAR) {
                    return ExpressionType.UNDEFINED;
                }
                return lhsType == ExpressionType.SCALAR
                        ? rhsType
                        : lhsType;
            } else {
                if (lhsType != rhsType) {
                    return ExpressionType.UNDEFINED;
                }
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
