package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

public class Generator {

    public String generate(AST ast) {
        StringBuilder sb = new StringBuilder();
        for (ASTNode node : ast.root.body) {
            if (node instanceof Stylerule) {
                sb.append(((Stylerule) node).selectors.get(0))
                        .append(" {").append("\n")
                        .append(decorateWithDeclarations((Stylerule) node))
                        .append("}").append("\n\n");
            }
        }
        return sb.toString();
    }

    private String decorateWithDeclarations(Stylerule stylerule) {
        StringBuilder sb = new StringBuilder();
        for (ASTNode declaration : stylerule.body) {
            if (declaration instanceof Declaration) {
                sb.append("\t")
                        .append(((Declaration) declaration).property.name)
                        .append(": ")
                        .append(getLiteralValue(((Declaration) declaration).expression))
                        .append(";").append("\n");
            }
        }
        return sb.toString();
    }

    private String getLiteralValue(Expression expression) {
        if (expression instanceof ColorLiteral) {
            return ((ColorLiteral) expression).value;
        }
        if (expression instanceof PixelLiteral) {
            return ((PixelLiteral) expression).value + "px";
        }
        if (expression instanceof PercentageLiteral) {
            return ((PercentageLiteral) expression).value + "%";
        }
        if (expression instanceof ScalarLiteral) {
            return ((ScalarLiteral) expression).value + "";
        }
        return "";
    }
}