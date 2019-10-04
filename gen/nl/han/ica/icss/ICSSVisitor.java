// Generated from C:/Users/cumal/dev/asd/icss/startcode/src/main/java/nl/han/ica/icss\ICSS.g4 by ANTLR 4.7.2
package nl.han.ica.icss;
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
	 * Visit a parse tree produced by {@link ICSSParser#children}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildren(ICSSParser.ChildrenContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#child}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChild(ICSSParser.ChildContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(ICSSParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
}