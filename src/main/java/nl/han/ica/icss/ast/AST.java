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
        Map<String, Expression> global = new HashMap<>();
        //global
        for (ASTNode node : root.body) {
            if (node instanceof VariableAssignment) {
                global.put(((VariableAssignment) node).name.name, ((VariableAssignment) node).expression);
            }
        }
        varAssignments.add(global);

        //local
        for (ASTNode styleRule : getStyleRules()) {
            extractLocalVariableAssignments(styleRule, varAssignments);
        }

        return varAssignments;
    }

    private void extractLocalVariableAssignments(ASTNode block, LinkedList<Map<String, Expression>> varAssignments) {
        Map<String, Expression> scope = new HashMap<>();
        List<ASTNode> nodes = new ArrayList<>();

        if(block instanceof Stylerule){
            nodes = ((Stylerule) block).body;
        }else if(block instanceof IfClause){
            nodes = ((IfClause) block).body;
        }

        for(ASTNode node: nodes){
            if (node instanceof VariableAssignment) {
                scope.put(((VariableAssignment) node).name.name, ((VariableAssignment) node).expression);
            } else if (node instanceof IfClause) {
                 extractLocalVariableAssignments(node, varAssignments);
            }
        }
        varAssignments.add(scope);
    }

    public List<ASTNode> getStyleRules() {
        List<ASTNode> styleRules = new ArrayList<>();

        for (ASTNode node : root.body) {
            if (node instanceof Stylerule) {
                styleRules.add(node);
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
