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
	 * Enter a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 */
	void enterStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 */
	void exitStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 */
	void enterTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 */
	void exitTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 */
	void enterIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 */
	void exitIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 */
	void enterClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 */
	void exitClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#elementSelector}.
	 * @param ctx the parse tree
	 */
	void enterElementSelector(ICSSParser.ElementSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#elementSelector}.
	 * @param ctx the parse tree
	 */
	void exitElementSelector(ICSSParser.ElementSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selectorBody}.
	 * @param ctx the parse tree
	 */
	void enterSelectorBody(ICSSParser.SelectorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selectorBody}.
	 * @param ctx the parse tree
	 */
	void exitSelectorBody(ICSSParser.SelectorBodyContext ctx);
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
	 * Enter a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 */
	void enterIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 */
	void exitIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propVal}.
	 * @param ctx the parse tree
	 */
	void enterPropVal(ICSSParser.PropValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propVal}.
	 * @param ctx the parse tree
	 */
	void exitPropVal(ICSSParser.PropValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplyOperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyOperation(ICSSParser.MultiplyOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplyOperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubtractOperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterAddSubtractOperation(ICSSParser.AddSubtractOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubtractOperation}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitAddSubtractOperation(ICSSParser.AddSubtractOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(ICSSParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(ICSSParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propName}.
	 * @param ctx the parse tree
	 */
	void enterPropName(ICSSParser.PropNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propName}.
	 * @param ctx the parse tree
	 */
	void exitPropName(ICSSParser.PropNameContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link ICSSParser#colorLiteral}.
	 * @param ctx the parse tree
	 */
	void enterColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#colorLiteral}.
	 * @param ctx the parse tree
	 */
	void exitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#pixelLiteral}.
	 * @param ctx the parse tree
	 */
	void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#pixelLiteral}.
	 * @param ctx the parse tree
	 */
	void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#percentageLiteral}.
	 * @param ctx the parse tree
	 */
	void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#percentageLiteral}.
	 * @param ctx the parse tree
	 */
	void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolLiteralTrue}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteralTrue(ICSSParser.BoolLiteralTrueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolLiteralTrue}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteralTrue(ICSSParser.BoolLiteralTrueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolLiteralFalse}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteralFalse(ICSSParser.BoolLiteralFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolLiteralFalse}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteralFalse(ICSSParser.BoolLiteralFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#scalarLiteral}.
	 * @param ctx the parse tree
	 */
	void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#scalarLiteral}.
	 * @param ctx the parse tree
	 */
	void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
}