package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.Calculatable;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.*;

public class EvalExpressions implements Transform {

    private LinkedList<Map<String, Literal>> variableValues = new LinkedList<>();
    private int currentStyleRule = 1;
    private int scopeLevel = 0;

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        setVariableValues(ast);
        for (ASTNode styleRule : ast.getStyleRules()) {
            scopeLevel++;
            currentStyleRule = scopeLevel;
            for (ASTNode node : ((Stylerule) styleRule).body) {
                transformExpression(node);
            }
        }
    }

    private void transformExpression(ASTNode node) {
        if (node instanceof Declaration) {
            convertExpressionToLiteral(node, ((Declaration) node).expression);
            return;
        }

        if (node instanceof IfClause) {
            convertExpressionToLiteral(node, ((IfClause) node).conditionalExpression);

            scopeLevel++;

            for (ASTNode ifClauseDeclaration : ((IfClause) node).body) {
                transformExpression(ifClauseDeclaration);
            }
        }
    }

    private Literal convertExpressionToLiteral(ASTNode node, Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }
        if (expression instanceof VariableReference) {
            Literal literal = null;
            if (scopeLevel < variableValues.size()) {
                for (int i = scopeLevel; i >= currentStyleRule; i--) {
                    if (variableValues.get(i).containsKey(((VariableReference) expression).name)) {
                        literal = variableValues.get(i).get(((VariableReference) expression).name);
                        break;
                    }
                }
            }

            if (literal == null) {
                literal = variableValues.getFirst().get(((VariableReference) expression).name);
            }

            if (node instanceof Declaration) {
                ((Declaration) node).expression = literal;
            }

            if (node instanceof IfClause) {
                ((IfClause) node).conditionalExpression = literal;
            }
            return literal;
        }
        if (expression instanceof Operation) {
            Literal literal = getLiteralOfOperation(node, expression);
            if (node instanceof Declaration) {
                ((Declaration) node).expression = literal;
            }
            return literal;
        }
        return null;
    }

    private Literal getLiteralOfOperation(ASTNode node, Expression expression) {
        Calculatable lhs = (Calculatable) convertExpressionToLiteral(node, ((Operation) expression).lhs);
        Calculatable rhs = (Calculatable) convertExpressionToLiteral(node, ((Operation) expression).rhs);

        if (expression instanceof AddOperation) {
            return add(lhs, rhs);
        }
        if (expression instanceof SubtractOperation) {
            return subtract(lhs, rhs);
        }
        if (expression instanceof MultiplyOperation) {
            return multiply(lhs, rhs);
        }

        return null;
    }

    private void setVariableValues(AST ast) {
        LinkedList<Map<String, Expression>> assignments = ast.getVariableAssignments();
        int scopeLevel = 0;
        for (Map<String, Expression> scope : assignments) {
            if (scope.isEmpty()) {
                variableValues.add(new HashMap<>());
            } else {
                Map<String, Literal> scopeVariableValues = new HashMap<>();
                for (String var : scope.keySet()) {
                    scopeVariableValues.put(var, convertExpressionToLiteral(null, scope.get(var)));
                    if (scopeLevel < variableValues.size()) {
                        variableValues.get(scopeLevel).putAll(scopeVariableValues);
                    } else {
                        variableValues.add(scopeVariableValues);
                    }
                }
            }
            scopeLevel++;
        }
    }

    private Literal add(Calculatable lhs, Calculatable rhs) {
        return getResultOfAddSubtractOperation(lhs, rhs, lhs.getValue() + rhs.getValue());
    }

    private Literal subtract(Calculatable lhs, Calculatable rhs) {
        return getResultOfAddSubtractOperation(lhs, rhs, lhs.getValue() - rhs.getValue());
    }

    private Literal multiply(Calculatable lhs, Calculatable rhs) {
        if (lhs instanceof ScalarLiteral) {
            return getResultOfMultiplyOperation(rhs, lhs.getValue() * rhs.getValue());
        }
        if (rhs instanceof ScalarLiteral) {
            return getResultOfMultiplyOperation(lhs, lhs.getValue() * rhs.getValue());
        }
        throw new UnsupportedOperationException();
    }

    private Literal getResultOfMultiplyOperation(Calculatable operand, int result) {
        if (operand instanceof PixelLiteral) {
            return new PixelLiteral(result);
        }
        if (operand instanceof PercentageLiteral) {
            return new PercentageLiteral(result);
        }
        throw new UnsupportedOperationException();
    }

    private Literal getResultOfAddSubtractOperation(Calculatable lhs, Calculatable rhs, int result) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral) {
            return new PixelLiteral(result);
        }
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(result);
        }
        throw new UnsupportedOperationException();
    }
}
