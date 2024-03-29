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
    private Map<ExpressionType, List<String>> propertyTypes = new HashMap<>();
    private List<String> supportedProperties = new ArrayList<>();


    public Checker() {
        variableTypes.add(new HashMap<>());

        List<String> percentagePixelProperties = new ArrayList<>(List.of("width", "height"));
        List<String> colorProperties = new ArrayList<>(List.of("color", "background-color"));

        supportedProperties.addAll(percentagePixelProperties);
        supportedProperties.addAll(colorProperties);

        propertyTypes.put(ExpressionType.PERCENTAGE, percentagePixelProperties);
        propertyTypes.put(ExpressionType.PIXEL, percentagePixelProperties);
        propertyTypes.put(ExpressionType.COLOR, colorProperties);
    }

    public void check(AST ast) {

        // first handle all global variables since they may appear between the style rules
        for (ASTNode node : ast.root.body) {
            if (node instanceof VariableAssignment) {
                validateElement(node);
            }
        }

        // style rules
        for (ASTNode node : ast.root.body) {
            if (node instanceof Stylerule) {
                variableTypes.add(new HashMap<>());

                validateBody(((Stylerule) node).body);

                variableTypes.removeLast();
            }
        }
    }

    private void validateBody(List<ASTNode> nodes) {
        for (ASTNode node : nodes) {
            validateElement(node);
        }
    }

    private void validateElement(ASTNode node) {
        if (node instanceof VariableAssignment) {
            variableTypes.getLast().put(((VariableAssignment) node).name.name, getExpressionType(((VariableAssignment) node).expression));
            validateVariables(((VariableAssignment) node).expression);
            validateOperands(((VariableAssignment) node).expression);
            return;
        }

        if (node instanceof IfClause) {
            validateIfClauseConditions(((IfClause) node).conditionalExpression, (IfClause) node);
            variableTypes.removeLast();
            return;
        }

        if (node instanceof Declaration) {
            Expression expression = ((Declaration) node).expression;
            validateVariables(expression);
            validateProperties(((Declaration) node).property, expression);
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
                operation.setError("The operands of add/subtract operation must be the same type.");
            }
        }
        if (operation instanceof MultiplyOperation) {
            if (!(lhsType == ExpressionType.SCALAR || rhsType == ExpressionType.SCALAR)) {
                operation.setError("At least one of the operands of multiply operation must be the type " + ExpressionType.SCALAR + ".");
            }
        }

    }

    private void validateIfClauseConditions(Expression expression, IfClause ifClause) {
        if (getExpressionType(expression) != ExpressionType.BOOL) {
            expression.setError("The condition must be the type " + ExpressionType.BOOL + ".");
        }

        // overwrite the error if expression is an undefined variable
        validateVariables(expression);

        variableTypes.add(new HashMap<>());

        validateBody(ifClause.body);
    }

    private void validateVariables(Expression expression) {
        if (expression instanceof Operation) {
            validateVariables(((Operation) expression).rhs);
            validateVariables(((Operation) expression).lhs);
        }

        if (expression instanceof VariableReference) {
            if (getExpressionTypeOfVariable((VariableReference) expression) == null) {
                expression.setError("Variable \"" + ((VariableReference) expression).name + "\" is not defined.");
            }
        }
    }

    private void validateProperties(PropertyName property, Expression expression) {
        if (!supportedProperties.contains(property.name)) {
            property.setError("Property \"" + property.name + "\" is not supported.");
            return;
        }

        ExpressionType expressionType = getExpressionType(expression);
        if (!isExpressionCompatibleWithProperty(property, expressionType)) {
            property.setError("Property \"" + property.name + "\" is incompatible with the type " + expressionType + ".");
        }
    }

    private boolean isExpressionCompatibleWithProperty(PropertyName property, ExpressionType expressionType) {
        return propertyTypes.get(expressionType) != null && propertyTypes.get(expressionType).contains(property.name);
    }

    private ExpressionType getExpressionType(Expression expression) {
        if (expression instanceof VariableReference) {
            return getExpressionTypeOfVariable((VariableReference) expression);
        }
        if (expression instanceof Operation) {
            return getExpressionTypeOfOperation((Operation) expression);
        }
        return mapToExpressionType(expression);
    }

    private ExpressionType getExpressionTypeOfOperation(Operation operation) {
        ExpressionType lhsType = getExpressionType(operation.lhs);
        ExpressionType rhsType = getExpressionType(operation.rhs);
        if (operation instanceof MultiplyOperation) {
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

    private ExpressionType getExpressionTypeOfVariable(VariableReference reference) {
        for (int i = variableTypes.size() - 1; i >= 0; i--) {
            if (variableTypes.get(i).containsKey(reference.name)) {
                return variableTypes.get(i).get(reference.name);
            }
        }
        return null;
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
