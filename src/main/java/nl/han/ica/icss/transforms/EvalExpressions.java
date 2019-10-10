package nl.han.ica.icss.transforms;

import com.google.errorprone.annotations.Var;
import nl.han.ica.icss.ast.*;

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
            transformVariables(declaration);
        }
    }

    private void transformVariables(ASTNode declaration) {
        if (declaration instanceof Declaration) {
            covertVariableReferenceToLiteral(declaration, ((Declaration) declaration).expression);
            return;
        }
        if (declaration instanceof IfClause) {
            covertVariableReferenceToLiteral(declaration, ((IfClause) declaration).conditionalExpression);
            for (ASTNode ifClauseDeclaration : ((IfClause) declaration).body)
                transformVariables(ifClauseDeclaration);
        }
    }

    private void covertVariableReferenceToLiteral(ASTNode declaration, Expression expression) {
        if (expression instanceof VariableReference) {
            Literal literal = variableValues.get(((VariableReference) expression).name);

            if (declaration instanceof Declaration) {
                ((Declaration) declaration).expression = literal;
                return;
            }

            if (declaration instanceof IfClause) {
                ((IfClause) declaration).conditionalExpression = literal;
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
