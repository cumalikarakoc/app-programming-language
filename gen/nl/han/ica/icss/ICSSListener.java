// Generated from C:/Users/cumal/dev/asd/icss/startcode/src/main/java/nl/han/ica/icss\ICSS.g4 by ANTLR 4.7.2
package nl.han.ica.icss;
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
	 * Enter a parse tree produced by {@link ICSSParser#children}.
	 * @param ctx the parse tree
	 */
	void enterChildren(ICSSParser.ChildrenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#children}.
	 * @param ctx the parse tree
	 */
	void exitChildren(ICSSParser.ChildrenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#child}.
	 * @param ctx the parse tree
	 */
	void enterChild(ICSSParser.ChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#child}.
	 * @param ctx the parse tree
	 */
	void exitChild(ICSSParser.ChildContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(ICSSParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(ICSSParser.PropertiesContext ctx);
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
}