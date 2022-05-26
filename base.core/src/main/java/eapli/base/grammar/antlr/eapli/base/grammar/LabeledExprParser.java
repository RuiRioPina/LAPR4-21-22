// Generated from C:/Users/Asus/Documents/lei21_22_s4_2dh_05/base.core/src/main/java/eapli/base/grammar\LabeledExpr.g4 by ANTLR 4.10.1
package eapli.base.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LabeledExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ALPHANUMERIC=1, SENTENCE=2, NUMBER=3, STRING=4, OBLIGATORINESS_ENUM=5, 
		CHOOSE=6, FREE_TEXT=7, MULTIPLE_CHOICE=8, SINGLE_CHOICE=9, NUMERIC=10, 
		ID=11, TITLE=12, WELCOME_MESSAGE=13, LIST_OF_SECTIONS=14, CONTENT=15, 
		SECTION_ID=16, SECTION_TITLE=17, SECTION_DESCRIPTION=18, OBLIGATORINESS=19, 
		QUESTION_ID=20, Q=21, EXTRA_INFO=22, WS=23;
	public static final int
		RULE_prog = 0, RULE_info = 1, RULE_section = 2, RULE_question = 3, RULE_questionType = 4, 
		RULE_free_text = 5, RULE_multipleChoice = 6, RULE_singleChoice = 7, RULE_numeric = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "info", "section", "question", "questionType", "free_text", "multipleChoice", 
			"singleChoice", "numeric"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'TYPE: FREE-TEXT'", "'TYPE: MULTIPLE-CHOICE'", 
			"'TYPE: SINGLE-CHOICE'", "'TYPE: NUMERIC'", null, null, null, "'LIST OF SECTIONS:'", 
			"'CONTENT:'", null, null, null, null, null, null, "'EXTRA INFO: '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ALPHANUMERIC", "SENTENCE", "NUMBER", "STRING", "OBLIGATORINESS_ENUM", 
			"CHOOSE", "FREE_TEXT", "MULTIPLE_CHOICE", "SINGLE_CHOICE", "NUMERIC", 
			"ID", "TITLE", "WELCOME_MESSAGE", "LIST_OF_SECTIONS", "CONTENT", "SECTION_ID", 
			"SECTION_TITLE", "SECTION_DESCRIPTION", "OBLIGATORINESS", "QUESTION_ID", 
			"Q", "EXTRA_INFO", "WS"
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
	public String getGrammarFileName() { return "LabeledExpr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LabeledExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<InfoContext> info() {
			return getRuleContexts(InfoContext.class);
		}
		public InfoContext info(int i) {
			return getRuleContext(InfoContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				info();
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	public static class InfoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LabeledExprParser.ID, 0); }
		public TerminalNode TITLE() { return getToken(LabeledExprParser.TITLE, 0); }
		public TerminalNode LIST_OF_SECTIONS() { return getToken(LabeledExprParser.LIST_OF_SECTIONS, 0); }
		public List<TerminalNode> WELCOME_MESSAGE() { return getTokens(LabeledExprParser.WELCOME_MESSAGE); }
		public TerminalNode WELCOME_MESSAGE(int i) {
			return getToken(LabeledExprParser.WELCOME_MESSAGE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public InfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_info; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitInfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitInfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoContext info() throws RecognitionException {
		InfoContext _localctx = new InfoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_info);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(ID);
			setState(24);
			match(TITLE);
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WELCOME_MESSAGE) {
				{
				{
				setState(25);
				match(WELCOME_MESSAGE);
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
			match(LIST_OF_SECTIONS);
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				section();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SECTION_ID );
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

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION_ID() { return getToken(LabeledExprParser.SECTION_ID, 0); }
		public TerminalNode SECTION_TITLE() { return getToken(LabeledExprParser.SECTION_TITLE, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(LabeledExprParser.OBLIGATORINESS, 0); }
		public TerminalNode CONTENT() { return getToken(LabeledExprParser.CONTENT, 0); }
		public List<TerminalNode> SECTION_DESCRIPTION() { return getTokens(LabeledExprParser.SECTION_DESCRIPTION); }
		public TerminalNode SECTION_DESCRIPTION(int i) {
			return getToken(LabeledExprParser.SECTION_DESCRIPTION, i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(SECTION_ID);
			setState(38);
			match(SECTION_TITLE);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SECTION_DESCRIPTION) {
				{
				{
				setState(39);
				match(SECTION_DESCRIPTION);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			match(OBLIGATORINESS);
			setState(46);
			match(CONTENT);
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				question();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION_ID );
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode QUESTION_ID() { return getToken(LabeledExprParser.QUESTION_ID, 0); }
		public TerminalNode Q() { return getToken(LabeledExprParser.Q, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(LabeledExprParser.OBLIGATORINESS, 0); }
		public List<QuestionTypeContext> questionType() {
			return getRuleContexts(QuestionTypeContext.class);
		}
		public QuestionTypeContext questionType(int i) {
			return getRuleContext(QuestionTypeContext.class,i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(QUESTION_ID);
			setState(53);
			match(Q);
			setState(54);
			match(OBLIGATORINESS);
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				questionType();
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FREE_TEXT) | (1L << MULTIPLE_CHOICE) | (1L << SINGLE_CHOICE) | (1L << NUMERIC))) != 0) );
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

	public static class QuestionTypeContext extends ParserRuleContext {
		public Free_textContext free_text() {
			return getRuleContext(Free_textContext.class,0);
		}
		public MultipleChoiceContext multipleChoice() {
			return getRuleContext(MultipleChoiceContext.class,0);
		}
		public SingleChoiceContext singleChoice() {
			return getRuleContext(SingleChoiceContext.class,0);
		}
		public NumericContext numeric() {
			return getRuleContext(NumericContext.class,0);
		}
		public QuestionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterQuestionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitQuestionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitQuestionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTypeContext questionType() throws RecognitionException {
		QuestionTypeContext _localctx = new QuestionTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionType);
		try {
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FREE_TEXT:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				free_text();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				multipleChoice();
				}
				break;
			case SINGLE_CHOICE:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				singleChoice();
				}
				break;
			case NUMERIC:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				numeric();
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

	public static class Free_textContext extends ParserRuleContext {
		public TerminalNode FREE_TEXT() { return getToken(LabeledExprParser.FREE_TEXT, 0); }
		public TerminalNode EXTRA_INFO() { return getToken(LabeledExprParser.EXTRA_INFO, 0); }
		public List<TerminalNode> SENTENCE() { return getTokens(LabeledExprParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(LabeledExprParser.SENTENCE, i);
		}
		public Free_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_free_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterFree_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitFree_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitFree_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Free_textContext free_text() throws RecognitionException {
		Free_textContext _localctx = new Free_textContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_free_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(FREE_TEXT);
			setState(67);
			match(EXTRA_INFO);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				match(SENTENCE);
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SENTENCE );
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

	public static class MultipleChoiceContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE() { return getToken(LabeledExprParser.MULTIPLE_CHOICE, 0); }
		public TerminalNode EXTRA_INFO() { return getToken(LabeledExprParser.EXTRA_INFO, 0); }
		public List<TerminalNode> CHOOSE() { return getTokens(LabeledExprParser.CHOOSE); }
		public TerminalNode CHOOSE(int i) {
			return getToken(LabeledExprParser.CHOOSE, i);
		}
		public MultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_multipleChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(MULTIPLE_CHOICE);
			setState(74);
			match(EXTRA_INFO);
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				match(CHOOSE);
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHOOSE );
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

	public static class SingleChoiceContext extends ParserRuleContext {
		public TerminalNode SINGLE_CHOICE() { return getToken(LabeledExprParser.SINGLE_CHOICE, 0); }
		public TerminalNode EXTRA_INFO() { return getToken(LabeledExprParser.EXTRA_INFO, 0); }
		public List<TerminalNode> CHOOSE() { return getTokens(LabeledExprParser.CHOOSE); }
		public TerminalNode CHOOSE(int i) {
			return getToken(LabeledExprParser.CHOOSE, i);
		}
		public SingleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterSingleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitSingleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitSingleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleChoiceContext singleChoice() throws RecognitionException {
		SingleChoiceContext _localctx = new SingleChoiceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_singleChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(SINGLE_CHOICE);
			setState(81);
			match(EXTRA_INFO);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				match(CHOOSE);
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHOOSE );
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

	public static class NumericContext extends ParserRuleContext {
		public TerminalNode NUMERIC() { return getToken(LabeledExprParser.NUMERIC, 0); }
		public TerminalNode EXTRA_INFO() { return getToken(LabeledExprParser.EXTRA_INFO, 0); }
		public List<TerminalNode> CHOOSE() { return getTokens(LabeledExprParser.CHOOSE); }
		public TerminalNode CHOOSE(int i) {
			return getToken(LabeledExprParser.CHOOSE, i);
		}
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LabeledExprListener ) ((LabeledExprListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LabeledExprVisitor ) return ((LabeledExprVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_numeric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(NUMERIC);
			setState(88);
			match(EXTRA_INFO);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				match(CHOOSE);
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHOOSE );
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
		"\u0004\u0001\u0017_\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0004\u0000\u0014\b\u0000\u000b\u0000\f\u0000\u0015"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u001b\b\u0001\n\u0001"+
		"\f\u0001\u001e\t\u0001\u0001\u0001\u0001\u0001\u0004\u0001\"\b\u0001\u000b"+
		"\u0001\f\u0001#\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002)\b\u0002"+
		"\n\u0002\f\u0002,\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002"+
		"1\b\u0002\u000b\u0002\f\u00022\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0004\u00039\b\u0003\u000b\u0003\f\u0003:\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004A\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0004\u0005F\b\u0005\u000b\u0005\f\u0005G\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0004\u0006M\b\u0006\u000b\u0006\f\u0006N\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0004\u0007T\b\u0007\u000b\u0007\f\u0007U\u0001"+
		"\b\u0001\b\u0001\b\u0004\b[\b\b\u000b\b\f\b\\\u0001\b\u0000\u0000\t\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0000b\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0002\u0017\u0001\u0000\u0000\u0000\u0004%\u0001\u0000\u0000"+
		"\u0000\u00064\u0001\u0000\u0000\u0000\b@\u0001\u0000\u0000\u0000\nB\u0001"+
		"\u0000\u0000\u0000\fI\u0001\u0000\u0000\u0000\u000eP\u0001\u0000\u0000"+
		"\u0000\u0010W\u0001\u0000\u0000\u0000\u0012\u0014\u0003\u0002\u0001\u0000"+
		"\u0013\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000\u0000"+
		"\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000"+
		"\u0016\u0001\u0001\u0000\u0000\u0000\u0017\u0018\u0005\u000b\u0000\u0000"+
		"\u0018\u001c\u0005\f\u0000\u0000\u0019\u001b\u0005\r\u0000\u0000\u001a"+
		"\u0019\u0001\u0000\u0000\u0000\u001b\u001e\u0001\u0000\u0000\u0000\u001c"+
		"\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d"+
		"\u001f\u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000\u0000\u0000\u001f"+
		"!\u0005\u000e\u0000\u0000 \"\u0003\u0004\u0002\u0000! \u0001\u0000\u0000"+
		"\u0000\"#\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000"+
		"\u0000\u0000$\u0003\u0001\u0000\u0000\u0000%&\u0005\u0010\u0000\u0000"+
		"&*\u0005\u0011\u0000\u0000\')\u0005\u0012\u0000\u0000(\'\u0001\u0000\u0000"+
		"\u0000),\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000"+
		"\u0000\u0000+-\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000-.\u0005"+
		"\u0013\u0000\u0000.0\u0005\u000f\u0000\u0000/1\u0003\u0006\u0003\u0000"+
		"0/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000020\u0001\u0000\u0000"+
		"\u000023\u0001\u0000\u0000\u00003\u0005\u0001\u0000\u0000\u000045\u0005"+
		"\u0014\u0000\u000056\u0005\u0015\u0000\u000068\u0005\u0013\u0000\u0000"+
		"79\u0003\b\u0004\u000087\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000"+
		":8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;\u0007\u0001\u0000"+
		"\u0000\u0000<A\u0003\n\u0005\u0000=A\u0003\f\u0006\u0000>A\u0003\u000e"+
		"\u0007\u0000?A\u0003\u0010\b\u0000@<\u0001\u0000\u0000\u0000@=\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000@?\u0001\u0000\u0000\u0000A\t\u0001"+
		"\u0000\u0000\u0000BC\u0005\u0007\u0000\u0000CE\u0005\u0016\u0000\u0000"+
		"DF\u0005\u0002\u0000\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000"+
		"\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000H\u000b\u0001"+
		"\u0000\u0000\u0000IJ\u0005\b\u0000\u0000JL\u0005\u0016\u0000\u0000KM\u0005"+
		"\u0006\u0000\u0000LK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000O\r\u0001\u0000\u0000"+
		"\u0000PQ\u0005\t\u0000\u0000QS\u0005\u0016\u0000\u0000RT\u0005\u0006\u0000"+
		"\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000US\u0001\u0000"+
		"\u0000\u0000UV\u0001\u0000\u0000\u0000V\u000f\u0001\u0000\u0000\u0000"+
		"WX\u0005\n\u0000\u0000XZ\u0005\u0016\u0000\u0000Y[\u0005\u0006\u0000\u0000"+
		"ZY\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000]\u0011\u0001\u0000\u0000\u0000\u000b"+
		"\u0015\u001c#*2:@GNU\\";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}