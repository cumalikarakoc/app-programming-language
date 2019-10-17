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
    private int currentStyleRule = 0;
    private int scopeLevel = 0;

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        for (ASTNode node : ast.root.body) {
            if (node instanceof VariableAssignment) {
                transformExpression(node);
                continue;
            }
            if (node instanceof Stylerule) {
                scopeLevel++;
                currentStyleRule = scopeLevel;
                for (ASTNode styleRule : ((Stylerule) node).body) {
                    transformExpression(styleRule);
                }
            }
        }
    }

    private void transformExpression(ASTNode node) {
        if (node instanceof VariableAssignment) {
            setVariableValues((VariableAssignment) node);
        }

        if (node instanceof Declaration) {
            ((Declaration) node).expression = convertExpressionToLiteral(((Declaration) node).expression);
            return;
        }

        if (node instanceof IfClause) {
            ((IfClause) node).conditionalExpression = convertExpressionToLiteral(((IfClause) node).conditionalExpression);

            scopeLevel++;

            for (ASTNode ifClauseDeclaration : ((IfClause) node).body) {
                transformExpression(ifClauseDeclaration);
            }
        }
    }

    private void setVariableValues(VariableAssignment assignment) {
        Map<String, Literal> variables;
        if (scopeLevel < variableValues.size()) {
            variables = variableValues.get(scopeLevel);
        } else {
            variables = new HashMap<>();
            variableValues.add(variables);
        }
        variables.put(assignment.name.name, convertExpressionToLiteral(assignment.expression));
    }

    private Literal convertExpressionToLiteral(Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }

        if (expression instanceof VariableReference) {
            int currentScope = scopeLevel < variableValues.size() ? scopeLevel : variableValues.size() - 1;
            for (int i = currentScope; i >= currentStyleRule; i--) {
                if (variableValues.get(i).containsKey(((VariableReference) expression).name)) {
                    return variableValues.get(i).get(((VariableReference) expression).name);
                }
            }

            //global
            return variableValues.getFirst().get(((VariableReference) expression).name);
        }

        if (expression instanceof Operation) {
            return getLiteralOfOperation(expression);
        }
        return null;
    }

    private Literal getLiteralOfOperation(Expression expression) {
        Calculatable lhs = (Calculatable) convertExpressionToLiteral(((Operation) expression).lhs);
        Calculatable rhs = (Calculatable) convertExpressionToLiteral(((Operation) expression).rhs);

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

    private Literal getResultOfMultiplyOperation(Calculatable nonScalarOperand, int result) {
        if (nonScalarOperand instanceof PixelLiteral) {
            return new PixelLiteral(result);
        }
        if (nonScalarOperand instanceof PercentageLiteral) {
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
