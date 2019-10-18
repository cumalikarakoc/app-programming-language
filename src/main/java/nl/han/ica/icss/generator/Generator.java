package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> declarations = overWriteDuplicateDeclarations(stylerule);

        StringBuilder sb = new StringBuilder();
        for (String propertyName : declarations.keySet()) {
            sb.append("\t")
                    .append(propertyName)
                    .append(": ")
                    .append(declarations.get(propertyName))
                    .append(";").append("\n");
        }
        return sb.toString();
    }

    private Map<String, String> overWriteDuplicateDeclarations(Stylerule stylerule) {
        Map<String, String> declarations = new HashMap<>();
        for (ASTNode declaration : stylerule.body) {
            if (declaration instanceof Declaration) {
                String propertyName = ((Declaration) declaration).property.name;
                String propertyValue = getLiteralValue(((Declaration) declaration).expression);

                declarations.remove(propertyName);
                declarations.put(propertyName, propertyValue);
            }
        }
        return declarations;
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