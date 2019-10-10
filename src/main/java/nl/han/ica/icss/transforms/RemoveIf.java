package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;

import java.util.List;

public class RemoveIf implements Transform {

    @Override
    public void apply(AST ast) {
        for (ASTNode node : ast.root.body) {
            if (node instanceof Stylerule) {
                List<ASTNode> nodes = ((Stylerule) node).body;
                for (int i = 0; i < nodes.size(); i++) {
                    evaluateIf(node, node, nodes.get(i));
                }
            }
        }
    }

    private void evaluateIf(ASTNode styleRule, ASTNode parent, ASTNode child) {
        if (child instanceof IfClause) {
            IfClause ifClause = (IfClause) child;
            if (!(((BoolLiteral) ifClause.conditionalExpression).value)) {
                parent.removeChild(ifClause);
            } else {
                evaluateBody(styleRule, ifClause);
            }
        }
    }

    private void evaluateBody(ASTNode styleRule, IfClause ifClause) {
        List<ASTNode> nodes = ifClause.body;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) instanceof Declaration) {
                ((Stylerule) styleRule).body.add(nodes.get(i));
            } else {
                evaluateIf(styleRule, ifClause, nodes.get(i));
            }
        }
    }
}