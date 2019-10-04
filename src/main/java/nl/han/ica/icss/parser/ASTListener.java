package nl.han.ica.icss.parser;

import java.util.Iterator;
import java.util.Stack;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

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
    public void enterProperty(ICSSParser.PropertyContext ctx) {
        Declaration declaration = new Declaration();
        currentContainer.peek().addChild(declaration);
        currentContainer.push(declaration);

        currentContainer.peek().addChild(new PropertyName(ctx.LOWER_IDENT().toString()));
        if (ctx.COLOR() != null) {
            currentContainer.peek().addChild(new ColorLiteral(ctx.COLOR().toString()));
        }
        if (ctx.PERCENTAGE() != null) {
            currentContainer.peek().addChild(new PercentageLiteral(ctx.PERCENTAGE().toString()));
        }
        if (ctx.PIXELSIZE() != null) {
            currentContainer.peek().addChild(new PixelLiteral(ctx.PIXELSIZE().toString()));
        }
    }

    @Override
    public void exitProperty(ICSSParser.PropertyContext ctx) {
        currentContainer.pop();
    }
}
