package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvalExpressions implements Transform {

    private HashMap<String, Literal> variableValues = new HashMap<>();

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        setVariableValues(ast);
        for (ASTNode node : ast.getStyleRules()) {
            transformExpression(node);
        }
    }

    private void transformExpression(ASTNode node) {
        if (node instanceof Declaration) {
            covertExpressionToLiteral(node, ((Declaration) node).expression);
            return;
        }

        if (node instanceof IfClause) {
            covertExpressionToLiteral(node, ((IfClause) node).conditionalExpression);
            for (ASTNode ifClauseDeclaration : ((IfClause) node).body)
                transformExpression(ifClauseDeclaration);
        }
    }

    private Literal covertExpressionToLiteral(ASTNode node, Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }
        if (expression instanceof VariableReference) {
            Literal literal = variableValues.get(((VariableReference) expression).name);

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
        Literal lhs = covertExpressionToLiteral(node, ((Operation) expression).lhs);
        Literal rhs = covertExpressionToLiteral(node, ((Operation) expression).rhs);
        Literal res = null;
        if (expression instanceof AddOperation) {
            res = add(lhs, rhs);
        } else if (expression instanceof SubtractOperation) {
            res = subtract(lhs, rhs);
        } else if (expression instanceof MultiplyOperation) {
            res = multiply(lhs, rhs);
        }
        return res;
    }

    private void setVariableValues(AST ast) {
        List<ASTNode> assignments = new ArrayList<>();//ast.getVariableAssignments();
        for (ASTNode node : assignments) {
            if (node instanceof VariableAssignment) {
                variableValues.put(((VariableAssignment) node).name.name, covertExpressionToLiteral(node, ((VariableAssignment) node).expression));
            }
        }
    }

    private Literal add(Literal lhs, Literal rhs) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) lhs).value + ((PixelLiteral) rhs).value);
        }
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) lhs).value + ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }

    private Literal subtract(Literal lhs, Literal rhs) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) lhs).value - ((PixelLiteral) rhs).value);
        }
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) lhs).value - ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }


    private Literal multiply(Literal lhs, Literal rhs) {
        if (lhs instanceof ScalarLiteral) {
            return getResultOfMultiplication((ScalarLiteral) lhs, rhs);
        }
        if (rhs instanceof ScalarLiteral) {
            return getResultOfMultiplication((ScalarLiteral) rhs, lhs);
        }

        throw new UnsupportedOperationException();
    }

    private Literal getResultOfMultiplication(ScalarLiteral lhs, Literal rhs) {
        if (rhs instanceof PixelLiteral) {
            return new PixelLiteral(lhs.value * ((PixelLiteral) rhs).value);
        }
        if (rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(lhs.value * ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }
}
