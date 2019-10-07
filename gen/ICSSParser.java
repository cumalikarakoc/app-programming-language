// Generated from C:/Users/cumal/dev/asd/app-programming-language/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ICSSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, BOX_BRACKET_OPEN=2, BOX_BRACKET_CLOSE=3, TRUE=4, FALSE=5, PIXELSIZE=6, 
		PERCENTAGE=7, SCALAR=8, COLOR=9, ID_IDENT=10, CLASS_IDENT=11, LOWER_IDENT=12, 
		CAPITAL_IDENT=13, WS=14, OPEN_BRACE=15, CLOSE_BRACE=16, SEMICOLON=17, 
		COLON=18, PLUS=19, MIN=20, MUL=21, ASSIGNMENT_OPERATOR=22;
	public static final int
		RULE_stylesheet = 0, RULE_varAssignments = 1, RULE_varAssignment = 2, 
		RULE_varName = 3, RULE_styleRules = 4, RULE_styleRule = 5, RULE_tagSelector = 6, 
		RULE_idSelector = 7, RULE_classSelector = 8, RULE_elementSelector = 9, 
		RULE_declarations = 10, RULE_declaration = 11, RULE_ifClause = 12, RULE_propVal = 13, 
		RULE_operation = 14, RULE_addOperation = 15, RULE_multiplyOperation = 16, 
		RULE_subtractOperation = 17, RULE_expression = 18, RULE_propName = 19, 
		RULE_literal = 20, RULE_colorLiteral = 21, RULE_pixelLiteral = 22, RULE_percentageLiteral = 23, 
		RULE_boolLiteral = 24, RULE_boolLiteralTrue = 25, RULE_boolLiteralFalse = 26, 
		RULE_scalarLiteral = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"stylesheet", "varAssignments", "varAssignment", "varName", "styleRules", 
			"styleRule", "tagSelector", "idSelector", "classSelector", "elementSelector", 
			"declarations", "declaration", "ifClause", "propVal", "operation", "addOperation", 
			"multiplyOperation", "subtractOperation", "expression", "propName", "literal", 
			"colorLiteral", "pixelLiteral", "percentageLiteral", "boolLiteral", "boolLiteralTrue", 
			"boolLiteralFalse", "scalarLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, null, null, 
			null, null, null, null, null, "'{'", "'}'", "';'", "':'", "'+'", "'-'", 
			"'*'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", "FALSE", 
			"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ICSSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StylesheetContext extends ParserRuleContext {
		public StyleRulesContext styleRules() {
			return getRuleContext(StyleRulesContext.class,0);
		}
		public VarAssignmentsContext varAssignments() {
			return getRuleContext(VarAssignmentsContext.class,0);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterStylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitStylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitStylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CAPITAL_IDENT:
				{
				setState(56);
				varAssignments();
				}
				break;
			case ID_IDENT:
			case CLASS_IDENT:
			case LOWER_IDENT:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(60);
			styleRules();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignmentsContext extends ParserRuleContext {
		public List<VarAssignmentContext> varAssignment() {
			return getRuleContexts(VarAssignmentContext.class);
		}
		public VarAssignmentContext varAssignment(int i) {
			return getRuleContext(VarAssignmentContext.class,i);
		}
		public VarAssignmentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssignments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterVarAssignments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitVarAssignments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitVarAssignments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignmentsContext varAssignments() throws RecognitionException {
		VarAssignmentsContext _localctx = new VarAssignmentsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varAssignments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				varAssignment();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CAPITAL_IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignmentContext extends ParserRuleContext {
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public TerminalNode ASSIGNMENT_OPERATOR() { return getToken(ICSSParser.ASSIGNMENT_OPERATOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ICSSParser.SEMICOLON, 0); }
		public VarAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterVarAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitVarAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitVarAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignmentContext varAssignment() throws RecognitionException {
		VarAssignmentContext _localctx = new VarAssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			varName();
			setState(68);
			match(ASSIGNMENT_OPERATOR);
			setState(69);
			expression();
			setState(70);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarNameContext extends ParserRuleContext {
		public TerminalNode CAPITAL_IDENT() { return getToken(ICSSParser.CAPITAL_IDENT, 0); }
		public VarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitVarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitVarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(CAPITAL_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StyleRulesContext extends ParserRuleContext {
		public List<StyleRuleContext> styleRule() {
			return getRuleContexts(StyleRuleContext.class);
		}
		public StyleRuleContext styleRule(int i) {
			return getRuleContext(StyleRuleContext.class,i);
		}
		public StyleRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterStyleRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitStyleRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitStyleRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleRulesContext styleRules() throws RecognitionException {
		StyleRulesContext _localctx = new StyleRulesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_styleRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				styleRule();
				}
				}
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID_IDENT) | (1L << CLASS_IDENT) | (1L << LOWER_IDENT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StyleRuleContext extends ParserRuleContext {
		public TagSelectorContext tagSelector() {
			return getRuleContext(TagSelectorContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(ICSSParser.OPEN_BRACE, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(ICSSParser.CLOSE_BRACE, 0); }
		public StyleRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_styleRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterStyleRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitStyleRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitStyleRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleRuleContext styleRule() throws RecognitionException {
		StyleRuleContext _localctx = new StyleRuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_styleRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			tagSelector();
			setState(80);
			match(OPEN_BRACE);
			setState(81);
			declarations();
			setState(82);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagSelectorContext extends ParserRuleContext {
		public IdSelectorContext idSelector() {
			return getRuleContext(IdSelectorContext.class,0);
		}
		public ClassSelectorContext classSelector() {
			return getRuleContext(ClassSelectorContext.class,0);
		}
		public ElementSelectorContext elementSelector() {
			return getRuleContext(ElementSelectorContext.class,0);
		}
		public TagSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterTagSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitTagSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitTagSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagSelectorContext tagSelector() throws RecognitionException {
		TagSelectorContext _localctx = new TagSelectorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tagSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID_IDENT:
				{
				setState(84);
				idSelector();
				}
				break;
			case CLASS_IDENT:
				{
				setState(85);
				classSelector();
				}
				break;
			case LOWER_IDENT:
				{
				setState(86);
				elementSelector();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdSelectorContext extends ParserRuleContext {
		public TerminalNode ID_IDENT() { return getToken(ICSSParser.ID_IDENT, 0); }
		public IdSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterIdSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitIdSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitIdSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdSelectorContext idSelector() throws RecognitionException {
		IdSelectorContext _localctx = new IdSelectorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_idSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(ID_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassSelectorContext extends ParserRuleContext {
		public TerminalNode CLASS_IDENT() { return getToken(ICSSParser.CLASS_IDENT, 0); }
		public ClassSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterClassSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitClassSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitClassSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassSelectorContext classSelector() throws RecognitionException {
		ClassSelectorContext _localctx = new ClassSelectorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(CLASS_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementSelectorContext extends ParserRuleContext {
		public TerminalNode LOWER_IDENT() { return getToken(ICSSParser.LOWER_IDENT, 0); }
		public ElementSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterElementSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitElementSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitElementSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementSelectorContext elementSelector() throws RecognitionException {
		ElementSelectorContext _localctx = new ElementSelectorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_elementSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(LOWER_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationsContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<IfClauseContext> ifClause() {
			return getRuleContexts(IfClauseContext.class);
		}
		public IfClauseContext ifClause(int i) {
			return getRuleContext(IfClauseContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitDeclarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(95);
				declaration();
				}
				}
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LOWER_IDENT );
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF) {
				{
				{
				setState(100);
				ifClause();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public PropNameContext propName() {
			return getRuleContext(PropNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ICSSParser.COLON, 0); }
		public PropValContext propVal() {
			return getRuleContext(PropValContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ICSSParser.SEMICOLON, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			propName();
			setState(107);
			match(COLON);
			setState(108);
			propVal();
			setState(109);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfClauseContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(ICSSParser.IF, 0); }
		public TerminalNode BOX_BRACKET_OPEN() { return getToken(ICSSParser.BOX_BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BOX_BRACKET_CLOSE() { return getToken(ICSSParser.BOX_BRACKET_CLOSE, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(ICSSParser.OPEN_BRACE, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(ICSSParser.CLOSE_BRACE, 0); }
		public IfClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterIfClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitIfClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitIfClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfClauseContext ifClause() throws RecognitionException {
		IfClauseContext _localctx = new IfClauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(IF);
			setState(112);
			match(BOX_BRACKET_OPEN);
			setState(113);
			expression();
			setState(114);
			match(BOX_BRACKET_CLOSE);
			setState(115);
			match(OPEN_BRACE);
			setState(116);
			declarations();
			setState(117);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropValContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public PropValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterPropVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitPropVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitPropVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropValContext propVal() throws RecognitionException {
		PropValContext _localctx = new PropValContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_propVal);
		int _la;
		try {
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << PIXELSIZE) | (1L << PERCENTAGE) | (1L << SCALAR) | (1L << COLOR) | (1L << CAPITAL_IDENT))) != 0)) {
					{
					{
					setState(120);
					operation();
					}
					}
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public MultiplyOperationContext multiplyOperation() {
			return getRuleContext(MultiplyOperationContext.class,0);
		}
		public AddOperationContext addOperation() {
			return getRuleContext(AddOperationContext.class,0);
		}
		public SubtractOperationContext subtractOperation() {
			return getRuleContext(SubtractOperationContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_operation);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				multiplyOperation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				addOperation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				subtractOperation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddOperationContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ICSSParser.PLUS, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public AddOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterAddOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitAddOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitAddOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddOperationContext addOperation() throws RecognitionException {
		AddOperationContext _localctx = new AddOperationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_addOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			expression();
			setState(134);
			match(PLUS);
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(135);
				expression();
				}
				break;
			case 2:
				{
				setState(136);
				operation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplyOperationContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(ICSSParser.MUL, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public MultiplyOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterMultiplyOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitMultiplyOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitMultiplyOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplyOperationContext multiplyOperation() throws RecognitionException {
		MultiplyOperationContext _localctx = new MultiplyOperationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multiplyOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			expression();
			setState(140);
			match(MUL);
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(141);
				expression();
				}
				break;
			case 2:
				{
				setState(142);
				operation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubtractOperationContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MIN() { return getToken(ICSSParser.MIN, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public SubtractOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtractOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterSubtractOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitSubtractOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitSubtractOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtractOperationContext subtractOperation() throws RecognitionException {
		SubtractOperationContext _localctx = new SubtractOperationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_subtractOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			expression();
			setState(146);
			match(MIN);
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(147);
				expression();
				}
				break;
			case 2:
				{
				setState(148);
				operation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expression);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case PIXELSIZE:
			case PERCENTAGE:
			case SCALAR:
			case COLOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				literal();
				}
				break;
			case CAPITAL_IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				varName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropNameContext extends ParserRuleContext {
		public TerminalNode LOWER_IDENT() { return getToken(ICSSParser.LOWER_IDENT, 0); }
		public PropNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterPropName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitPropName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitPropName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropNameContext propName() throws RecognitionException {
		PropNameContext _localctx = new PropNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_propName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(LOWER_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public ColorLiteralContext colorLiteral() {
			return getRuleContext(ColorLiteralContext.class,0);
		}
		public PixelLiteralContext pixelLiteral() {
			return getRuleContext(PixelLiteralContext.class,0);
		}
		public PercentageLiteralContext percentageLiteral() {
			return getRuleContext(PercentageLiteralContext.class,0);
		}
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public ScalarLiteralContext scalarLiteral() {
			return getRuleContext(ScalarLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		try {
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				colorLiteral();
				}
				break;
			case PIXELSIZE:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				pixelLiteral();
				}
				break;
			case PERCENTAGE:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				percentageLiteral();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				boolLiteral();
				}
				break;
			case SCALAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(161);
				scalarLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColorLiteralContext extends ParserRuleContext {
		public TerminalNode COLOR() { return getToken(ICSSParser.COLOR, 0); }
		public ColorLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterColorLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitColorLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitColorLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorLiteralContext colorLiteral() throws RecognitionException {
		ColorLiteralContext _localctx = new ColorLiteralContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_colorLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(COLOR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PixelLiteralContext extends ParserRuleContext {
		public TerminalNode PIXELSIZE() { return getToken(ICSSParser.PIXELSIZE, 0); }
		public PixelLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pixelLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterPixelLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitPixelLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitPixelLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PixelLiteralContext pixelLiteral() throws RecognitionException {
		PixelLiteralContext _localctx = new PixelLiteralContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_pixelLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(PIXELSIZE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PercentageLiteralContext extends ParserRuleContext {
		public TerminalNode PERCENTAGE() { return getToken(ICSSParser.PERCENTAGE, 0); }
		public PercentageLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percentageLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterPercentageLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitPercentageLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitPercentageLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PercentageLiteralContext percentageLiteral() throws RecognitionException {
		PercentageLiteralContext _localctx = new PercentageLiteralContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_percentageLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(PERCENTAGE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralContext extends ParserRuleContext {
		public BoolLiteralTrueContext boolLiteralTrue() {
			return getRuleContext(BoolLiteralTrueContext.class,0);
		}
		public BoolLiteralFalseContext boolLiteralFalse() {
			return getRuleContext(BoolLiteralFalseContext.class,0);
		}
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_boolLiteral);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				boolLiteralTrue();
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				boolLiteralFalse();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralTrueContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(ICSSParser.TRUE, 0); }
		public BoolLiteralTrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteralTrue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterBoolLiteralTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitBoolLiteralTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitBoolLiteralTrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralTrueContext boolLiteralTrue() throws RecognitionException {
		BoolLiteralTrueContext _localctx = new BoolLiteralTrueContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_boolLiteralTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(TRUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralFalseContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(ICSSParser.FALSE, 0); }
		public BoolLiteralFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteralFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterBoolLiteralFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitBoolLiteralFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitBoolLiteralFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralFalseContext boolLiteralFalse() throws RecognitionException {
		BoolLiteralFalseContext _localctx = new BoolLiteralFalseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_boolLiteralFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(FALSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarLiteralContext extends ParserRuleContext {
		public TerminalNode SCALAR() { return getToken(ICSSParser.SCALAR, 0); }
		public ScalarLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).enterScalarLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ICSSListener ) ((ICSSListener)listener).exitScalarLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ICSSVisitor ) return ((ICSSVisitor<? extends T>)visitor).visitScalarLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarLiteralContext scalarLiteral() throws RecognitionException {
		ScalarLiteralContext _localctx = new ScalarLiteralContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_scalarLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(SCALAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30\u00b7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\5\2=\n\2\3\2\3\2\3\3"+
		"\6\3B\n\3\r\3\16\3C\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\6\6N\n\6\r\6\16\6"+
		"O\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\6\fc\n\f\r\f\16\fd\3\f\7\fh\n\f\f\f\16\fk\13\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17|\n\17\f\17\16"+
		"\17\177\13\17\5\17\u0081\n\17\3\20\3\20\3\20\5\20\u0086\n\20\3\21\3\21"+
		"\3\21\3\21\5\21\u008c\n\21\3\22\3\22\3\22\3\22\5\22\u0092\n\22\3\23\3"+
		"\23\3\23\3\23\5\23\u0098\n\23\3\24\3\24\5\24\u009c\n\24\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u00a5\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\5\32\u00af\n\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\2\2\36\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\2\2\u00ae\2"+
		"<\3\2\2\2\4A\3\2\2\2\6E\3\2\2\2\bJ\3\2\2\2\nM\3\2\2\2\fQ\3\2\2\2\16Y\3"+
		"\2\2\2\20[\3\2\2\2\22]\3\2\2\2\24_\3\2\2\2\26b\3\2\2\2\30l\3\2\2\2\32"+
		"q\3\2\2\2\34\u0080\3\2\2\2\36\u0085\3\2\2\2 \u0087\3\2\2\2\"\u008d\3\2"+
		"\2\2$\u0093\3\2\2\2&\u009b\3\2\2\2(\u009d\3\2\2\2*\u00a4\3\2\2\2,\u00a6"+
		"\3\2\2\2.\u00a8\3\2\2\2\60\u00aa\3\2\2\2\62\u00ae\3\2\2\2\64\u00b0\3\2"+
		"\2\2\66\u00b2\3\2\2\28\u00b4\3\2\2\2:=\5\4\3\2;=\3\2\2\2<:\3\2\2\2<;\3"+
		"\2\2\2=>\3\2\2\2>?\5\n\6\2?\3\3\2\2\2@B\5\6\4\2A@\3\2\2\2BC\3\2\2\2CA"+
		"\3\2\2\2CD\3\2\2\2D\5\3\2\2\2EF\5\b\5\2FG\7\30\2\2GH\5&\24\2HI\7\23\2"+
		"\2I\7\3\2\2\2JK\7\17\2\2K\t\3\2\2\2LN\5\f\7\2ML\3\2\2\2NO\3\2\2\2OM\3"+
		"\2\2\2OP\3\2\2\2P\13\3\2\2\2QR\5\16\b\2RS\7\21\2\2ST\5\26\f\2TU\7\22\2"+
		"\2U\r\3\2\2\2VZ\5\20\t\2WZ\5\22\n\2XZ\5\24\13\2YV\3\2\2\2YW\3\2\2\2YX"+
		"\3\2\2\2Z\17\3\2\2\2[\\\7\f\2\2\\\21\3\2\2\2]^\7\r\2\2^\23\3\2\2\2_`\7"+
		"\16\2\2`\25\3\2\2\2ac\5\30\r\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"ei\3\2\2\2fh\5\32\16\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\27\3\2"+
		"\2\2ki\3\2\2\2lm\5(\25\2mn\7\24\2\2no\5\34\17\2op\7\23\2\2p\31\3\2\2\2"+
		"qr\7\3\2\2rs\7\4\2\2st\5&\24\2tu\7\5\2\2uv\7\21\2\2vw\5\26\f\2wx\7\22"+
		"\2\2x\33\3\2\2\2y\u0081\5&\24\2z|\5\36\20\2{z\3\2\2\2|\177\3\2\2\2}{\3"+
		"\2\2\2}~\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\u0080y\3\2\2\2\u0080}\3\2"+
		"\2\2\u0081\35\3\2\2\2\u0082\u0086\5\"\22\2\u0083\u0086\5 \21\2\u0084\u0086"+
		"\5$\23\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086"+
		"\37\3\2\2\2\u0087\u0088\5&\24\2\u0088\u008b\7\25\2\2\u0089\u008c\5&\24"+
		"\2\u008a\u008c\5\36\20\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c"+
		"!\3\2\2\2\u008d\u008e\5&\24\2\u008e\u0091\7\27\2\2\u008f\u0092\5&\24\2"+
		"\u0090\u0092\5\36\20\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092#"+
		"\3\2\2\2\u0093\u0094\5&\24\2\u0094\u0097\7\26\2\2\u0095\u0098\5&\24\2"+
		"\u0096\u0098\5\36\20\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098%"+
		"\3\2\2\2\u0099\u009c\5*\26\2\u009a\u009c\5\b\5\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\'\3\2\2\2\u009d\u009e\7\16\2\2\u009e)\3\2\2\2\u009f"+
		"\u00a5\5,\27\2\u00a0\u00a5\5.\30\2\u00a1\u00a5\5\60\31\2\u00a2\u00a5\5"+
		"\62\32\2\u00a3\u00a5\58\35\2\u00a4\u009f\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4"+
		"\u00a1\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5+\3\2\2\2"+
		"\u00a6\u00a7\7\13\2\2\u00a7-\3\2\2\2\u00a8\u00a9\7\b\2\2\u00a9/\3\2\2"+
		"\2\u00aa\u00ab\7\t\2\2\u00ab\61\3\2\2\2\u00ac\u00af\5\64\33\2\u00ad\u00af"+
		"\5\66\34\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af\63\3\2\2\2\u00b0"+
		"\u00b1\7\6\2\2\u00b1\65\3\2\2\2\u00b2\u00b3\7\7\2\2\u00b3\67\3\2\2\2\u00b4"+
		"\u00b5\7\n\2\2\u00b59\3\2\2\2\21<COYdi}\u0080\u0085\u008b\u0091\u0097"+
		"\u009b\u00a4\u00ae";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}