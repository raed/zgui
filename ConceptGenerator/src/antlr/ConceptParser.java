// Generated from Concept.g4 by ANTLR 4.7.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConceptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, LIST=11, TRANSITIVE=12, SYMMETRIC=13, REFLEXIVE=14, INVERS=15, 
		ABSTRACT=16, ASSERT=17, BOOLEAN=18, BREAK=19, BYTE=20, CASE=21, CATCH=22, 
		CHAR=23, CLASS=24, CONST=25, CONTINUE=26, DEFAULT=27, DO=28, DOUBLE=29, 
		ELSE=30, ENUM=31, EXTENDS=32, FINAL=33, FINALLY=34, FLOAT=35, FOR=36, 
		IF=37, GOTO=38, IMPLEMENTS=39, IMPORT=40, INSTANCEOF=41, INT=42, INTERFACE=43, 
		LONG=44, NATIVE=45, NEW=46, PACKAGE=47, PRIVATE=48, PROTECTED=49, PUBLIC=50, 
		RETURN=51, SHORT=52, STATIC=53, STRICTFP=54, SUPER=55, SWITCH=56, SYNCHRONIZED=57, 
		THIS=58, THROW=59, THROWS=60, TRANSIENT=61, TRY=62, VOID=63, VOLATILE=64, 
		WHILE=65, IntegerLiteral=66, FloatingPointLiteral=67, BooleanLiteral=68, 
		CharacterLiteral=69, StringLiteral=70, NullLiteral=71, LPAREN=72, RPAREN=73, 
		LBRACE=74, RBRACE=75, LBRACK=76, RBRACK=77, SEMI=78, COMMA=79, DOT=80, 
		ASSIGN=81, GT=82, LT=83, BANG=84, TILDE=85, QUESTION=86, COLON=87, EQUAL=88, 
		LE=89, GE=90, NOTEQUAL=91, AND=92, OR=93, INC=94, DEC=95, ADD=96, SUB=97, 
		MUL=98, DIV=99, BITAND=100, BITOR=101, CARET=102, MOD=103, ARROW=104, 
		COLONCOLON=105, ADD_ASSIGN=106, SUB_ASSIGN=107, MUL_ASSIGN=108, DIV_ASSIGN=109, 
		AND_ASSIGN=110, OR_ASSIGN=111, XOR_ASSIGN=112, MOD_ASSIGN=113, LSHIFT_ASSIGN=114, 
		RSHIFT_ASSIGN=115, URSHIFT_ASSIGN=116, Identifier=117, AT=118, ELLIPSIS=119, 
		WS=120, COMMENT=121, LINE_COMMENT=122;
	public static final int
		RULE_compilationUnit = 0, RULE_packageDeclaration = 1, RULE_packageName = 2, 
		RULE_importDeclaration = 3, RULE_conceptDeclaration = 4, RULE_conceptModifier = 5, 
		RULE_superConcept = 6, RULE_includeConcept = 7, RULE_conceptBody = 8, 
		RULE_attribute = 9, RULE_simpleAttribute = 10, RULE_complexAttribute = 11, 
		RULE_dataAttribute = 12, RULE_conceptAttribute = 13, RULE_simpleType = 14, 
		RULE_chainAttribute = 15, RULE_functionAttribute = 16, RULE_expr = 17, 
		RULE_aggregatingAttribute = 18, RULE_range = 19, RULE_propertyList = 20, 
		RULE_property = 21, RULE_relation = 22, RULE_referenceType = 23, RULE_primitiveType = 24, 
		RULE_qualifiedName = 25;
	public static final String[] ruleNames = {
		"compilationUnit", "packageDeclaration", "packageName", "importDeclaration", 
		"conceptDeclaration", "conceptModifier", "superConcept", "includeConcept", 
		"conceptBody", "attribute", "simpleAttribute", "complexAttribute", "dataAttribute", 
		"conceptAttribute", "simpleType", "chainAttribute", "functionAttribute", 
		"expr", "aggregatingAttribute", "range", "propertyList", "property", "relation", 
		"referenceType", "primitiveType", "qualifiedName"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'concept'", "'base'", "'includes'", "'concat'", "'func'", "'start'", 
		"'ag'", "'fin'", "'range'", "'string'", "'list'", "'transitive'", "'symmetric'", 
		"'reflexive'", "'inverse'", "'abstract'", "'assert'", "'boolean'", "'break'", 
		"'byte'", "'case'", "'catch'", "'char'", "'class'", "'const'", "'continue'", 
		"'default'", "'do'", "'double'", "'else'", "'enum'", "'extends'", "'final'", 
		"'finally'", "'float'", "'for'", "'if'", "'goto'", "'implements'", "'import'", 
		"'instanceof'", "'int'", "'interface'", "'long'", "'native'", "'new'", 
		"'package'", "'private'", "'protected'", "'public'", "'return'", "'short'", 
		"'static'", "'strictfp'", "'super'", "'switch'", "'synchronized'", "'this'", 
		"'throw'", "'throws'", "'transient'", "'try'", "'void'", "'volatile'", 
		"'while'", null, null, null, null, null, "'null'", "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "';'", "','", "'.'", "'='", "'>'", "'<'", "'!'", 
		"'~'", "'?'", "':'", "'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'++'", 
		"'--'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'", "'^'", "'%'", "'->'", 
		"'::'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", 
		"'<<='", "'>>='", "'>>>='", null, "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "LIST", 
		"TRANSITIVE", "SYMMETRIC", "REFLEXIVE", "INVERS", "ABSTRACT", "ASSERT", 
		"BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", "CHAR", "CLASS", "CONST", 
		"CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", "ENUM", "EXTENDS", "FINAL", 
		"FINALLY", "FLOAT", "FOR", "IF", "GOTO", "IMPLEMENTS", "IMPORT", "INSTANCEOF", 
		"INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", "PRIVATE", "PROTECTED", 
		"PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH", 
		"SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", "TRY", "VOID", 
		"VOLATILE", "WHILE", "IntegerLiteral", "FloatingPointLiteral", "BooleanLiteral", 
		"CharacterLiteral", "StringLiteral", "NullLiteral", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "ASSIGN", 
		"GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", "LE", "GE", 
		"NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", "DIV", "BITAND", 
		"BITOR", "CARET", "MOD", "ARROW", "COLONCOLON", "ADD_ASSIGN", "SUB_ASSIGN", 
		"MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", 
		"LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "Identifier", "AT", 
		"ELLIPSIS", "WS", "COMMENT", "LINE_COMMENT"
	};
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
	public String getGrammarFileName() { return "Concept.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ConceptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ConceptParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<ConceptDeclarationContext> conceptDeclaration() {
			return getRuleContexts(ConceptDeclarationContext.class);
		}
		public ConceptDeclarationContext conceptDeclaration(int i) {
			return getRuleContext(ConceptDeclarationContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PACKAGE) {
				{
				setState(52);
				packageDeclaration();
				}
			}

			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(55);
				importDeclaration();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC))) != 0)) {
				{
				{
				setState(61);
				conceptDeclaration();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(EOF);
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

	public static class PackageDeclarationContext extends ParserRuleContext {
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitPackageDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(PACKAGE);
			setState(70);
			packageName(0);
			setState(71);
			match(SEMI);
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

	public static class PackageNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitPackageName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		return packageName(0);
	}

	private PackageNameContext packageName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PackageNameContext _localctx = new PackageNameContext(_ctx, _parentState);
		PackageNameContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_packageName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(74);
			match(Identifier);
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PackageNameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_packageName);
					setState(76);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(77);
					match(DOT);
					setState(78);
					match(Identifier);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ImportDeclarationContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(IMPORT);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(85);
				match(STATIC);
				}
			}

			setState(88);
			qualifiedName();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(89);
				match(DOT);
				setState(90);
				match(MUL);
				}
			}

			setState(93);
			match(SEMI);
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

	public static class ConceptDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class,0);
		}
		public List<ConceptModifierContext> conceptModifier() {
			return getRuleContexts(ConceptModifierContext.class);
		}
		public ConceptModifierContext conceptModifier(int i) {
			return getRuleContext(ConceptModifierContext.class,i);
		}
		public SuperConceptContext superConcept() {
			return getRuleContext(SuperConceptContext.class,0);
		}
		public IncludeConceptContext includeConcept() {
			return getRuleContext(IncludeConceptContext.class,0);
		}
		public ConceptDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitConceptDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptDeclarationContext conceptDeclaration() throws RecognitionException {
		ConceptDeclarationContext _localctx = new ConceptDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conceptDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC))) != 0)) {
				{
				{
				setState(95);
				conceptModifier();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(T__0);
			setState(102);
			match(Identifier);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(103);
				match(T__1);
				}
			}

			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(106);
				superConcept();
				}
			}

			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(109);
				includeConcept();
				}
			}

			setState(112);
			conceptBody();
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

	public static class ConceptModifierContext extends ParserRuleContext {
		public ConceptModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptModifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitConceptModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptModifierContext conceptModifier() throws RecognitionException {
		ConceptModifierContext _localctx = new ConceptModifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_conceptModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class SuperConceptContext extends ParserRuleContext {
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public SuperConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superConcept; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitSuperConcept(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperConceptContext superConcept() throws RecognitionException {
		SuperConceptContext _localctx = new SuperConceptContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_superConcept);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(EXTENDS);
			setState(117);
			referenceType();
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

	public static class IncludeConceptContext extends ParserRuleContext {
		public List<ReferenceTypeContext> referenceType() {
			return getRuleContexts(ReferenceTypeContext.class);
		}
		public ReferenceTypeContext referenceType(int i) {
			return getRuleContext(ReferenceTypeContext.class,i);
		}
		public IncludeConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_includeConcept; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitIncludeConcept(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncludeConceptContext includeConcept() throws RecognitionException {
		IncludeConceptContext _localctx = new IncludeConceptContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_includeConcept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__2);
			setState(120);
			referenceType();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(121);
				match(COMMA);
				setState(122);
				referenceType();
				}
				}
				setState(127);
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

	public static class ConceptBodyContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public ConceptBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitConceptBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptBodyContext conceptBody() throws RecognitionException {
		ConceptBodyContext _localctx = new ConceptBodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_conceptBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(LBRACE);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(129);
				attribute();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			match(RBRACE);
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

	public static class AttributeContext extends ParserRuleContext {
		public SimpleAttributeContext simpleAttribute() {
			return getRuleContext(SimpleAttributeContext.class,0);
		}
		public ComplexAttributeContext complexAttribute() {
			return getRuleContext(ComplexAttributeContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_attribute);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				simpleAttribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				complexAttribute();
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

	public static class SimpleAttributeContext extends ParserRuleContext {
		public DataAttributeContext dataAttribute() {
			return getRuleContext(DataAttributeContext.class,0);
		}
		public ConceptAttributeContext conceptAttribute() {
			return getRuleContext(ConceptAttributeContext.class,0);
		}
		public SimpleAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitSimpleAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleAttributeContext simpleAttribute() throws RecognitionException {
		SimpleAttributeContext _localctx = new SimpleAttributeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_simpleAttribute);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				dataAttribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				conceptAttribute();
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

	public static class ComplexAttributeContext extends ParserRuleContext {
		public ChainAttributeContext chainAttribute() {
			return getRuleContext(ChainAttributeContext.class,0);
		}
		public FunctionAttributeContext functionAttribute() {
			return getRuleContext(FunctionAttributeContext.class,0);
		}
		public AggregatingAttributeContext aggregatingAttribute() {
			return getRuleContext(AggregatingAttributeContext.class,0);
		}
		public ComplexAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitComplexAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexAttributeContext complexAttribute() throws RecognitionException {
		ComplexAttributeContext _localctx = new ComplexAttributeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_complexAttribute);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				chainAttribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				functionAttribute();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				aggregatingAttribute();
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

	public static class DataAttributeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TerminalNode LIST() { return getToken(ConceptParser.LIST, 0); }
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public PropertyListContext propertyList() {
			return getRuleContext(PropertyListContext.class,0);
		}
		public DataAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitDataAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataAttributeContext dataAttribute() throws RecognitionException {
		DataAttributeContext _localctx = new DataAttributeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dataAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(Identifier);
			setState(151);
			match(COLON);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(152);
				match(LIST);
				}
			}

			setState(155);
			primitiveType();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYMMETRIC || _la==INVERS) {
				{
				setState(156);
				relation();
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(159);
				propertyList();
				}
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

	public static class ConceptAttributeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TerminalNode LIST() { return getToken(ConceptParser.LIST, 0); }
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public PropertyListContext propertyList() {
			return getRuleContext(PropertyListContext.class,0);
		}
		public ConceptAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitConceptAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptAttributeContext conceptAttribute() throws RecognitionException {
		ConceptAttributeContext _localctx = new ConceptAttributeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conceptAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(Identifier);
			setState(163);
			match(COLON);
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(164);
				match(LIST);
				}
			}

			setState(167);
			referenceType();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYMMETRIC || _la==INVERS) {
				{
				setState(168);
				relation();
				}
			}

			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(171);
				propertyList();
				}
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

	public static class SimpleTypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_simpleType);
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				primitiveType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				referenceType();
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

	public static class ChainAttributeContext extends ParserRuleContext {
		public List<ConceptAttributeContext> conceptAttribute() {
			return getRuleContexts(ConceptAttributeContext.class);
		}
		public ConceptAttributeContext conceptAttribute(int i) {
			return getRuleContext(ConceptAttributeContext.class,i);
		}
		public DataAttributeContext dataAttribute() {
			return getRuleContext(DataAttributeContext.class,0);
		}
		public ChainAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chainAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitChainAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChainAttributeContext chainAttribute() throws RecognitionException {
		ChainAttributeContext _localctx = new ChainAttributeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_chainAttribute);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			conceptAttribute();
			setState(181); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(179);
					match(T__3);
					setState(180);
					conceptAttribute();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(183); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(185);
			match(T__3);
			setState(186);
			dataAttribute();
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

	public static class FunctionAttributeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctionAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitFunctionAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionAttributeContext functionAttribute() throws RecognitionException {
		FunctionAttributeContext _localctx = new FunctionAttributeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_functionAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(Identifier);
			setState(189);
			match(ASSIGN);
			setState(190);
			match(T__4);
			setState(191);
			simpleType();
			setState(192);
			match(LPAREN);
			setState(193);
			expr();
			setState(194);
			match(RPAREN);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(196);
					matchWildcard();
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class AggregatingAttributeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AggregatingAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregatingAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitAggregatingAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregatingAttributeContext aggregatingAttribute() throws RecognitionException {
		AggregatingAttributeContext _localctx = new AggregatingAttributeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_aggregatingAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(Identifier);
			setState(203);
			match(ASSIGN);
			setState(204);
			simpleType();
			setState(205);
			match(LPAREN);
			setState(206);
			match(T__5);
			setState(207);
			match(ASSIGN);
			setState(208);
			expr();
			setState(209);
			match(COMMA);
			setState(210);
			match(T__6);
			setState(211);
			match(ASSIGN);
			setState(212);
			expr();
			setState(213);
			match(COMMA);
			setState(214);
			match(T__7);
			setState(215);
			match(ASSIGN);
			setState(216);
			expr();
			setState(217);
			match(RPAREN);
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

	public static class RangeContext extends ParserRuleContext {
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__8);
			setState(220);
			referenceType();
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

	public static class PropertyListContext extends ParserRuleContext {
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public PropertyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitPropertyList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyListContext propertyList() throws RecognitionException {
		PropertyListContext _localctx = new PropertyListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_propertyList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(LPAREN);
			setState(223);
			property();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(224);
				match(COMMA);
				setState(225);
				property();
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			match(RPAREN);
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

	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode TRANSITIVE() { return getToken(ConceptParser.TRANSITIVE, 0); }
		public TerminalNode REFLEXIVE() { return getToken(ConceptParser.REFLEXIVE, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !(_la==TRANSITIVE || _la==REFLEXIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public TerminalNode SYMMETRIC() { return getToken(ConceptParser.SYMMETRIC, 0); }
		public TerminalNode INVERS() { return getToken(ConceptParser.INVERS, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_la = _input.LA(1);
			if ( !(_la==SYMMETRIC || _la==INVERS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(236);
			match(Identifier);
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

	public static class ReferenceTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ConceptParser.Identifier, 0); }
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitReferenceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_referenceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(Identifier);
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

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(ConceptParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(ConceptParser.Identifier, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConceptVisitor ) return ((ConceptVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(Identifier);
			setState(247);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(243);
					match(DOT);
					setState(244);
					match(Identifier);
					}
					} 
				}
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return packageName_sempred((PackageNameContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean packageName_sempred(PackageNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3|\u00fd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\5\28\n\2\3\2\7\2;\n\2\f\2\16\2>\13\2\3\2\7\2"+
		"A\n\2\f\2\16\2D\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4R\n\4\f\4\16\4U\13\4\3\5\3\5\5\5Y\n\5\3\5\3\5\3\5\5\5^\n\5\3\5\3\5\3"+
		"\6\7\6c\n\6\f\6\16\6f\13\6\3\6\3\6\3\6\5\6k\n\6\3\6\5\6n\n\6\3\6\5\6q"+
		"\n\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\7\t~\n\t\f\t\16\t\u0081"+
		"\13\t\3\n\3\n\7\n\u0085\n\n\f\n\16\n\u0088\13\n\3\n\3\n\3\13\3\13\5\13"+
		"\u008e\n\13\3\f\3\f\5\f\u0092\n\f\3\r\3\r\3\r\5\r\u0097\n\r\3\16\3\16"+
		"\3\16\5\16\u009c\n\16\3\16\3\16\5\16\u00a0\n\16\3\16\5\16\u00a3\n\16\3"+
		"\17\3\17\3\17\5\17\u00a8\n\17\3\17\3\17\5\17\u00ac\n\17\3\17\5\17\u00af"+
		"\n\17\3\20\3\20\5\20\u00b3\n\20\3\21\3\21\3\21\6\21\u00b8\n\21\r\21\16"+
		"\21\u00b9\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\7\23\u00c8\n\23\f\23\16\23\u00cb\13\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\7\26\u00e5\n\26\f\26\16\26\u00e8\13\26\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\7\33\u00f8"+
		"\n\33\f\33\16\33\u00fb\13\33\3\33\3\u00c9\3\6\34\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\2\6\5\2##\62\64\67\67\4\2\16\16\20"+
		"\20\4\2\17\17\21\21\13\2\f\f\24\24\26\26\31\31\37\37%%,,..\66\66\2\u00fd"+
		"\2\67\3\2\2\2\4G\3\2\2\2\6K\3\2\2\2\bV\3\2\2\2\nd\3\2\2\2\ft\3\2\2\2\16"+
		"v\3\2\2\2\20y\3\2\2\2\22\u0082\3\2\2\2\24\u008d\3\2\2\2\26\u0091\3\2\2"+
		"\2\30\u0096\3\2\2\2\32\u0098\3\2\2\2\34\u00a4\3\2\2\2\36\u00b2\3\2\2\2"+
		" \u00b4\3\2\2\2\"\u00be\3\2\2\2$\u00c9\3\2\2\2&\u00cc\3\2\2\2(\u00dd\3"+
		"\2\2\2*\u00e0\3\2\2\2,\u00eb\3\2\2\2.\u00ed\3\2\2\2\60\u00f0\3\2\2\2\62"+
		"\u00f2\3\2\2\2\64\u00f4\3\2\2\2\668\5\4\3\2\67\66\3\2\2\2\678\3\2\2\2"+
		"8<\3\2\2\29;\5\b\5\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=B\3\2\2\2"+
		"><\3\2\2\2?A\5\n\6\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2"+
		"DB\3\2\2\2EF\7\2\2\3F\3\3\2\2\2GH\7\61\2\2HI\5\6\4\2IJ\7P\2\2J\5\3\2\2"+
		"\2KL\b\4\1\2LM\7w\2\2MS\3\2\2\2NO\f\3\2\2OP\7R\2\2PR\7w\2\2QN\3\2\2\2"+
		"RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\7\3\2\2\2US\3\2\2\2VX\7*\2\2WY\7\67\2"+
		"\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z]\5\64\33\2[\\\7R\2\2\\^\7d\2\2][\3\2"+
		"\2\2]^\3\2\2\2^_\3\2\2\2_`\7P\2\2`\t\3\2\2\2ac\5\f\7\2ba\3\2\2\2cf\3\2"+
		"\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\3\2\2hj\7w\2\2ik\7\4"+
		"\2\2ji\3\2\2\2jk\3\2\2\2km\3\2\2\2ln\5\16\b\2ml\3\2\2\2mn\3\2\2\2np\3"+
		"\2\2\2oq\5\20\t\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\5\22\n\2s\13\3\2\2\2"+
		"tu\t\2\2\2u\r\3\2\2\2vw\7\"\2\2wx\5\60\31\2x\17\3\2\2\2yz\7\5\2\2z\177"+
		"\5\60\31\2{|\7Q\2\2|~\5\60\31\2}{\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2"+
		"\177\u0080\3\2\2\2\u0080\21\3\2\2\2\u0081\177\3\2\2\2\u0082\u0086\7L\2"+
		"\2\u0083\u0085\5\24\13\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0089\u008a\7M\2\2\u008a\23\3\2\2\2\u008b\u008e\5\26\f\2\u008c\u008e"+
		"\5\30\r\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\25\3\2\2\2\u008f"+
		"\u0092\5\32\16\2\u0090\u0092\5\34\17\2\u0091\u008f\3\2\2\2\u0091\u0090"+
		"\3\2\2\2\u0092\27\3\2\2\2\u0093\u0097\5 \21\2\u0094\u0097\5\"\22\2\u0095"+
		"\u0097\5&\24\2\u0096\u0093\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2"+
		"\2\2\u0097\31\3\2\2\2\u0098\u0099\7w\2\2\u0099\u009b\7Y\2\2\u009a\u009c"+
		"\7\r\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009f\5\62\32\2\u009e\u00a0\5.\30\2\u009f\u009e\3\2\2\2\u009f\u00a0\3"+
		"\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\5*\26\2\u00a2\u00a1\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\33\3\2\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a7\7Y\2\2"+
		"\u00a6\u00a8\7\r\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00ab\5\60\31\2\u00aa\u00ac\5.\30\2\u00ab\u00aa\3\2\2\2"+
		"\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00af\5*\26\2\u00ae\u00ad"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\35\3\2\2\2\u00b0\u00b3\5\62\32\2\u00b1"+
		"\u00b3\5\60\31\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\37\3\2"+
		"\2\2\u00b4\u00b7\5\34\17\2\u00b5\u00b6\7\6\2\2\u00b6\u00b8\5\34\17\2\u00b7"+
		"\u00b5\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\6\2\2\u00bc\u00bd\5\32\16\2\u00bd"+
		"!\3\2\2\2\u00be\u00bf\7w\2\2\u00bf\u00c0\7S\2\2\u00c0\u00c1\7\7\2\2\u00c1"+
		"\u00c2\5\36\20\2\u00c2\u00c3\7J\2\2\u00c3\u00c4\5$\23\2\u00c4\u00c5\7"+
		"K\2\2\u00c5#\3\2\2\2\u00c6\u00c8\13\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca%\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\7w\2\2\u00cd\u00ce\7S\2\2\u00ce\u00cf\5\36"+
		"\20\2\u00cf\u00d0\7J\2\2\u00d0\u00d1\7\b\2\2\u00d1\u00d2\7S\2\2\u00d2"+
		"\u00d3\5$\23\2\u00d3\u00d4\7Q\2\2\u00d4\u00d5\7\t\2\2\u00d5\u00d6\7S\2"+
		"\2\u00d6\u00d7\5$\23\2\u00d7\u00d8\7Q\2\2\u00d8\u00d9\7\n\2\2\u00d9\u00da"+
		"\7S\2\2\u00da\u00db\5$\23\2\u00db\u00dc\7K\2\2\u00dc\'\3\2\2\2\u00dd\u00de"+
		"\7\13\2\2\u00de\u00df\5\60\31\2\u00df)\3\2\2\2\u00e0\u00e1\7J\2\2\u00e1"+
		"\u00e6\5,\27\2\u00e2\u00e3\7Q\2\2\u00e3\u00e5\5,\27\2\u00e4\u00e2\3\2"+
		"\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7K\2\2\u00ea+\3\2\2\2\u00eb"+
		"\u00ec\t\3\2\2\u00ec-\3\2\2\2\u00ed\u00ee\t\4\2\2\u00ee\u00ef\7w\2\2\u00ef"+
		"/\3\2\2\2\u00f0\u00f1\7w\2\2\u00f1\61\3\2\2\2\u00f2\u00f3\t\5\2\2\u00f3"+
		"\63\3\2\2\2\u00f4\u00f9\7w\2\2\u00f5\u00f6\7R\2\2\u00f6\u00f8\7w\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\65\3\2\2\2\u00fb\u00f9\3\2\2\2\34\67<BSX]djmp\177\u0086\u008d"+
		"\u0091\u0096\u009b\u009f\u00a2\u00a7\u00ab\u00ae\u00b2\u00b9\u00c9\u00e6"+
		"\u00f9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}