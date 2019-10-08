package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.SemanticError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AST {
    public static final String VAR_ASSIGNMENTS = "varAssignments";
    public static final String STYLE_RULES = "styleRules";
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

    public List<ASTNode> getVariableAssignments() {
        return extractGivenChildGroup(VAR_ASSIGNMENTS);
    }

    public List<ASTNode> getDeclarations() {
        return extractGivenChildGroup(STYLE_RULES);
    }

    private List<ASTNode> extractGivenChildGroup(String childGroup) {
        List<ASTNode> nodes = new ArrayList<>();
        for (ASTNode node : root.body) {
            if (childGroup.equals(VAR_ASSIGNMENTS)) {
                if (node instanceof VariableAssignment) {
                    nodes.add(node);
                }
            } else if (childGroup.equals(STYLE_RULES)) {
                if (node instanceof Stylerule) {
                    nodes.addAll(((Stylerule) node).body);
                }
            }

        }
        return nodes;
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
