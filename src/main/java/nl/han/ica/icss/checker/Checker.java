package nl.han.ica.icss.checker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new LinkedList<>();
        variableTypes.add((HashMap<String, ExpressionType>) variables(ast));
        for (ASTNode node : ast.root.body) {
            if (node instanceof VariableReference){
                if(!variableTypes.getLast().containsKey(((VariableReference) node).name)){
                    node.setError(new SemanticError("Variable is not defined").toString());
                }
            }
        }
    }

    private Map<String, ExpressionType> variables(AST ast) {
        Map<String, ExpressionType> map = new HashMap<>();
        for (ASTNode node : ast.root.body) {
            if (node instanceof VariableAssignment) {
                map.put(((VariableAssignment) node).name.name, getExpressionType(node));
            }
        }
        return map;
    }


    private ExpressionType getExpressionType(ASTNode expression) {
        if (expression instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        }
        if (expression instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        }
        if (expression instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        }
        if (expression instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        }
        if (expression instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        }
        return ExpressionType.UNDEFINED;
    }
}
