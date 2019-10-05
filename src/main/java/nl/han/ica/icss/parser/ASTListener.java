package nl.han.ica.icss.parser;

import java.util.ArrayList;
import java.util.Stack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
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

    private Stack<Expression> expressions = new Stack<>();

    public ASTListener() {
        ast = new AST();
        currentContainer = new Stack<>();
    }

    public AST getAST() {
        return ast;
    }


    @Override
    public void enterVarAssignment(ICSSParser.VarAssignmentContext ctx) {
        VariableAssignment assignment = new VariableAssignment();
        assignment.addChild(new VariableReference(ctx.varName().CAPITAL_IDENT().toString()));
        assignment.addChild(getCorrespondingLiteral(ctx.literal()));
        ast.root.addChild(assignment);
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
        if (ctx.ifClause() != null) {
            IfClause ifClause = new IfClause(new VariableReference(ctx.ifClause().varName().CAPITAL_IDENT().toString()), new ArrayList<>());
            currentContainer.peek().addChild(ifClause);
            currentContainer.push(ifClause);
        }else{
            Declaration declaration = new Declaration();
            currentContainer.peek().addChild(declaration);
            currentContainer.push(declaration);
        }
    }

    @Override
    public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
        currentContainer.pop();
        expressions.clear();
    }

    @Override
    public void enterProperty(ICSSParser.PropertyContext ctx) {
        currentContainer.peek().addChild(new PropertyName(ctx.LOWER_IDENT().toString()));
    }

    @Override
    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.varName() != null) {
            VariableReference var = new VariableReference(ctx.varName().CAPITAL_IDENT().toString());
            currentContainer.peek().addChild(var);
            expressions.push(var);
        }
        if (getCorrespondingLiteral(ctx.literal()) != null) {
            Literal literal = getCorrespondingLiteral(ctx.literal());
            currentContainer.peek().addChild(literal);
            expressions.push(literal);
        }
    }

    @Override
    public void enterOperation(ICSSParser.OperationContext ctx) {
        Operation operation = getCorrespondingOperation(ctx);
        if (expressions.peek() != null) {
            operation.addChild(expressions.pop());
        }
        if (currentContainer.peek() instanceof Operation) {
            ((Operation) currentContainer.peek()).rhs = null;
        }
        currentContainer.peek().addChild(operation);
        currentContainer.push(operation);
    }

    private Operation getCorrespondingOperation(ICSSParser.OperationContext operation) {
        if (operation == null) {
            return null;
        }
        if (operation.MUL() != null) {
            return new MultiplyOperation();
        }
        if (operation.MIN() != null) {
            return new SubtractOperation();
        }
        if (operation.PLUS() != null) {
            return new AddOperation();
        }
        return null;
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
        if (literal.SCALAR() != null) {
            return new ScalarLiteral(literal.SCALAR().toString());
        }
        return null;
    }
}
