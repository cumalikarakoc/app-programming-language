package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.SemanticError;

import java.util.*;

public class AST {
    private static final String VAR_ASSIGNMENTS = "VAR_ASSIGNMENTS";
    private static final String STYLE_RULES = "STYLE_RULES";

    //The root of the tree
    public Stylesheet root;

    public AST() {
        root = new Stylesheet();
    }

    public AST(Stylesheet stylesheet) {
        root = stylesheet;
    }

    public void setRoot(Stylesheet stylesheet) {
        root = stylesheet;
    }

    public ArrayList<SemanticError> getErrors() {
        ArrayList<SemanticError> errors = new ArrayList<>();
        collectErrors(errors, root);
        return errors;
    }

    private void collectErrors(ArrayList<SemanticError> errors, ASTNode node) {
        if (node.hasError()) {
            errors.add(node.getError());
        }
        for (ASTNode child : node.getChildren()) {
            collectErrors(errors, child);
        }
    }

    public LinkedList<Map<String, Expression>> getVariableAssignments() {
        LinkedList<Map<String, Expression>> varAssignments = new LinkedList<>();
        extractVariableAssignments(root.body, varAssignments);
        return varAssignments;
    }

    private void extractVariableAssignments(List<ASTNode> nodes, LinkedList<Map<String, Expression>> varAssignments) {
        Map<String, Expression> scope = new HashMap<>();
        for (ASTNode node : nodes) {
            if (node instanceof VariableAssignment) {
                scope.put(((VariableAssignment) node).name.name, ((VariableAssignment) node).expression);
                continue;
            }
            if (node instanceof Stylerule) {
                if (!scope.isEmpty()) {
                    varAssignments.add(scope);
                }
                extractVariableAssignments(((Stylerule) node).body, varAssignments);
                return;
            }
            if (node instanceof IfClause) {
                if (!scope.isEmpty()) {
                    varAssignments.add(scope);
                }
                extractVariableAssignments(((IfClause) node).body, varAssignments);
                return;
            }
        }
        if (!scope.isEmpty()) {
            varAssignments.add(scope);
        }
    }

    public List<ASTNode> getStyleRules() {
        List<ASTNode> styleRules = new ArrayList<>();

        for(ASTNode node : root.body){
            if(node instanceof Stylerule){
                styleRules.addAll(((Stylerule) node).body);
            }
        }
        return styleRules;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AST ast = (AST) o;
        return Objects.equals(root, ast.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
