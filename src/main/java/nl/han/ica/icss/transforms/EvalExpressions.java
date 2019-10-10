package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.checker.Checker;

import java.util.HashMap;
import java.util.List;

public class EvalExpressions implements Transform {

    private HashMap<String, Literal> variableValues = new HashMap<>();

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        setVariableValues(ast);
        for (ASTNode declaration : ast.getDeclarations()) {
            if (declaration instanceof Declaration) {
                Expression expression = ((Declaration) declaration).expression;
                if(expression instanceof VariableReference){
                    ((Declaration) declaration).expression = variableValues.get(((VariableReference) expression).name);
                }
            }
        }
    }

    private void setVariableValues(AST ast) {
        List<ASTNode> assignments = ast.getVariableAssignments();
        for (ASTNode node : assignments) {
            if (node instanceof VariableAssignment) {
                variableValues.put(((VariableAssignment) node).name.name, (Literal) ((VariableAssignment) node).expression);
            }
        }
    }
}
