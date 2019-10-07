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
	 * Visit a parse tree produced by {@link ICSSParser#varAssignments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignments(ICSSParser.VarAssignmentsContext ctx);
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
	 * Visit a parse tree produced by {@link ICSSParser#styleRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleRules(ICSSParser.StyleRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#elementSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementSelector(ICSSParser.ElementSelectorContext ctx);
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
	 * Visit a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropVal(ICSSParser.PropValContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#addOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOperation(ICSSParser.AddOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#multiplyOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#subtractOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractOperation(ICSSParser.SubtractOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropName(ICSSParser.PropNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixelLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentageLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolLiteralTrue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteralTrue(ICSSParser.BoolLiteralTrueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolLiteralFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteralFalse(ICSSParser.BoolLiteralFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalarLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
}