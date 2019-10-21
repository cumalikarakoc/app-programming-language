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

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        for (ASTNode node : ast.root.body) {

            //global
            variableValues.add(new HashMap<>());

            if (node instanceof VariableAssignment) {
                transformExpression(node);
                continue;
            }
            if (node instanceof Stylerule) {
                variableValues.add(new HashMap<>());
                for (ASTNode styleRule : ((Stylerule) node).body) {
                    transformExpression(styleRule);
                }
                variableValues.removeLast();
            }
        }
    }

    private void transformExpression(ASTNode node) {
        if (node instanceof VariableAssignment) {
            variableValues.getLast().put(((VariableAssignment) node).name.name, convertExpressionToLiteral(((VariableAssignment) node).expression));
        }

        if (node instanceof Declaration) {
            ((Declaration) node).expression = convertExpressionToLiteral(((Declaration) node).expression);
            return;
        }

        if (node instanceof IfClause) {
            ((IfClause) node).conditionalExpression = convertExpressionToLiteral(((IfClause) node).conditionalExpression);

            variableValues.add(new HashMap<>());

            for (ASTNode ifClauseDeclaration : ((IfClause) node).body) {
                transformExpression(ifClauseDeclaration);
            }

            variableValues.removeLast();
        }
    }

    private Literal convertExpressionToLiteral(Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }

        if (expression instanceof VariableReference) {
            return getLiteralOfVariable(expression);
        }

        if (expression instanceof Operation) {
            return getLiteralOfOperation(expression);
        }
        return null;
    }

    private Literal getLiteralOfVariable(Expression expression) {
        for (int i = variableValues.size() - 1; i >= 0; i--) {
            if (variableValues.get(i).containsKey(((VariableReference) expression).name)) {
                return variableValues.get(i).get(((VariableReference) expression).name);
            }
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
        return getResultOfMultiplyOperation(lhs, rhs, lhs.getValue() * rhs.getValue());
    }

    private Literal getResultOfMultiplyOperation(Calculatable lhs, Calculatable rhs, int result) {
        if (lhs instanceof PixelLiteral || rhs instanceof PixelLiteral) {
            return new PixelLiteral(result);
        }
        if (lhs instanceof PercentageLiteral || rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(result);
        }
        if (lhs instanceof ScalarLiteral && rhs instanceof ScalarLiteral) {
            return new ScalarLiteral(result);
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
        if (lhs instanceof ScalarLiteral && rhs instanceof ScalarLiteral) {
            return new ScalarLiteral(result);
        }
        throw new UnsupportedOperationException();
    }
}
