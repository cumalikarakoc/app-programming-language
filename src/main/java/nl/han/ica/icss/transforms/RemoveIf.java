package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;

import java.util.ArrayList;


public class RemoveIf implements Transform {

    @Override
    public void apply(AST ast) {
        for (ASTNode node : ast.root.body) {
            if (node instanceof Stylerule) {
                ArrayList<ASTNode> nodes = new ArrayList<>(((Stylerule) node).body);
                for (ASTNode child : nodes) {
                    evaluateIf(node, child);
                }
            }
        }
    }

    private void evaluateIf(ASTNode parent, ASTNode child) {
        if (child instanceof IfClause) {
            IfClause ifClause = (IfClause) child;
            if (!(((BoolLiteral) ifClause.conditionalExpression).value)) {
                parent.removeChild(ifClause);
            } else {
                evaluateBody(parent, ifClause);
            }
        }
    }

    private void evaluateBody(ASTNode styleRule, IfClause ifClause) {
        ArrayList<ASTNode> nodes = new ArrayList<>(ifClause.body);
        for (ASTNode node : nodes) {
            if (node instanceof Declaration) {
                ((Stylerule) styleRule).body.add(node);
            }
            if (node instanceof IfClause) {
                evaluateIf(styleRule, node);
            }
        }
    }
}