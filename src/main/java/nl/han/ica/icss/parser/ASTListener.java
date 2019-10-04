package nl.han.ica.icss.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

    //Accumulator attributes:
    private AST ast;

    //Use this to keep track of the parent nodes when recursively traversing the ast
    private Stack<ASTNode> currentContainer;

    public ASTListener() {
        ast = new AST();
        currentContainer = new Stack<>();
    }

    public AST getAST() {
        return ast;
    }

    @Override
    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        currentContainer.push(new Stylesheet());
    }

    @Override
    public void enterVarAssignment(ICSSParser.VarAssignmentContext ctx) {
        VariableAssignment assignment = new VariableAssignment();
        assignment.addChild(new VariableReference(ctx.varName().CAPITAL_IDENT().toString()));
        assignment.addChild(getCorrespondingLiteral(ctx.literal()));
        ast.root.addChild(assignment);
        currentContainer.peek().addChild(assignment);
    }


    @Override
    public void enterSelector(ICSSParser.SelectorContext ctx) {
        Selector selector;
        if (ctx.CLASS_IDENT() != null) {
            selector = new ClassSelector(ctx.CLASS_IDENT().toString());
        } else if (ctx.ID_IDENT() != null) {
            selector = new IdSelector(ctx.ID_IDENT().toString());
        } else {
            selector = new TagSelector(ctx.LOWER_IDENT().toString());
        }
        Stylerule stylerule = new Stylerule();
        stylerule.selectors.add(selector);
        ast.root.addChild(stylerule);
        currentContainer.push(stylerule);
    }

    @Override
    public void exitSelector(ICSSParser.SelectorContext ctx) {
        currentContainer.pop();
    }

    @Override
    public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
        Declaration declaration = new Declaration();
        currentContainer.peek().addChild(declaration);
        currentContainer.push(declaration);
        currentContainer.peek().addChild(new PropertyName(ctx.property().LOWER_IDENT().toString()));
        if(ctx.varName() != null){
            currentContainer.peek().addChild(new VariableReference(ctx.varName().CAPITAL_IDENT().toString()));

        }else{
            currentContainer.peek().addChild(getCorrespondingLiteral(ctx.literal()));

        }
    }

    private Literal getCorrespondingLiteral(ICSSParser.LiteralContext literal) {
        if (literal == null) {
            return null;
        }
        if (literal.COLOR() != null) {
            return new ColorLiteral(literal.COLOR().toString());
        }
        if (literal.PERCENTAGE() != null) {
            return new PercentageLiteral(literal.PERCENTAGE().toString());
        }
        if (literal.PIXELSIZE() != null) {
            return new PixelLiteral(literal.PIXELSIZE().toString());
        }
        if (literal.TRUE() != null) {
            return new BoolLiteral(literal.TRUE().toString());
        }
        if (literal.FALSE() != null) {
            return new BoolLiteral(literal.FALSE().toString());
        }
        return null;
    }

    @Override
    public void exitProperty(ICSSParser.PropertyContext ctx) {
        currentContainer.pop();
    }

}
