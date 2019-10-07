package nl.han.ica.icss.checker;

import java.util.HashMap;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private HashMap<String, ExpressionType> variableTypes = new HashMap<>();

    public void check(AST ast) {
        setVariableTypes(ast);
        for (ASTNode declaration : ast.getDeclarations()) {
            Expression expression = ((Declaration) declaration).expression;
            if (expression instanceof VariableReference) {
                if (!variableTypes.containsKey(((VariableReference) expression).name)) {
                    expression.setError("Variable " + ((VariableReference) expression).name + " is not defined.");
                }
            }
        }
    }

    private void setVariableTypes(AST ast) {
        for (ASTNode node : ast.getVariableAssignments()) {
            variableTypes.put(((VariableAssignment) node).name.name, getExpressionType(((VariableAssignment) node).expression));
        }
    }


    private ExpressionType getExpressionType(ASTNode node) {
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
