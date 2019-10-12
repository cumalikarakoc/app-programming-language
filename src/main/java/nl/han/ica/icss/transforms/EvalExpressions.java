package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
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

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        setVariableValues(ast);
        int scopeLevel = 1;
        for (ASTNode styleRule : ast.getStyleRules()) {
            for (ASTNode node : ((Stylerule) styleRule).body) {
                scopeLevel = transformExpression(node, scopeLevel);
            }
            scopeLevel++;
            currentStyleRule += scopeLevel;
        }
    }

    private int transformExpression(ASTNode node, int scopeLevel) {
        if (node instanceof Declaration) {
            convertExpressionToLiteral(node, ((Declaration) node).expression, scopeLevel);
            return scopeLevel;
        }

        if (node instanceof IfClause) {
            convertExpressionToLiteral(node, ((IfClause) node).conditionalExpression, scopeLevel);

            boolean hasVarAssignment = false;
            for (ASTNode child : ((IfClause) node).body) {
                if (child instanceof VariableAssignment) {
                    hasVarAssignment = true;
                    break;
                }
            }
            if (hasVarAssignment) {
                scopeLevel++;
            }

            for (ASTNode ifClauseDeclaration : ((IfClause) node).body){
                transformExpression(ifClauseDeclaration, scopeLevel);
            }
        }
        return scopeLevel;
    }

    private Literal convertExpressionToLiteral(ASTNode node, Expression expression, int scopeLevel) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }
        if (expression instanceof VariableReference) {
            Literal literal = null;
            if (scopeLevel < variableValues.size()) {
                for (int i = variableValues.size() - 1; i >= currentStyleRule; i--) {
                    if (variableValues.get(i).containsKey(((VariableReference) expression).name)) {
                        literal = variableValues.get(i).get(((VariableReference) expression).name);
                        break;
                    }
                }
                if(literal == null){
                    literal = variableValues.getFirst().get(((VariableReference) expression).name);
                }
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
            Literal literal = getLiteralOfOperation(node, expression, scopeLevel);
            if (node instanceof Declaration) {
                ((Declaration) node).expression = literal;
            }
            return literal;
        }
        return null;
    }

    private Literal getLiteralOfOperation(ASTNode node, Expression expression, int scopeLevel) {
        Literal lhs = convertExpressionToLiteral(node, ((Operation) expression).lhs, scopeLevel);
        Literal rhs = convertExpressionToLiteral(node, ((Operation) expression).rhs, scopeLevel);
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
        LinkedList<Map<String, Expression>> assignments = ast.getVariableAssignments();
        int scopeLevel = 0;
        for (Map<String, Expression> scope : assignments) {
            Map<String, Literal> scopeVariableValues = new HashMap<>();
            for (String var : scope.keySet()) {
                scopeVariableValues.put(var, convertExpressionToLiteral(null, scope.get(var), scopeLevel));
            }
            variableValues.add(scopeVariableValues);
            scopeLevel++;
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
