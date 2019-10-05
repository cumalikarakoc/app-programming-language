// Generated from C:/Users/cumal/dev/asd/app-programming-language/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariables(ICSSParser.VariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignment(ICSSParser.VarAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#varName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarName(ICSSParser.VarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ICSSParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(ICSSParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(ICSSParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
}