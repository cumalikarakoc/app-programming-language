// Generated from C:/Users/cumal/dev/asd/app-programming-language/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variables}.
	 * @param ctx the parse tree
	 */
	void enterVariables(ICSSParser.VariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variables}.
	 * @param ctx the parse tree
	 */
	void exitVariables(ICSSParser.VariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(ICSSParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#varAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(ICSSParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(ICSSParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(ICSSParser.VarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ICSSParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ICSSParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(ICSSParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(ICSSParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ICSSParser.LiteralContext ctx);
}