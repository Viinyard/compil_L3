// Generated from Calculette.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__15=1, T__14=2, T__13=3, T__12=4, T__11=5, T__10=6, T__9=7, T__8=8, 
		T__7=9, T__6=10, T__5=11, T__4=12, T__3=13, T__2=14, T__1=15, T__0=16, 
		BOOL=17, RETURN=18, IF=19, ELSE=20, WHILE=21, TYPE=22, IDENTIFIANT=23, 
		FLOAT=24, INT=25, OPE_REL=26, PO=27, PF=28, EQ=29, P1OPE=30, P2OPE=31, 
		SEMICOLON=32, NEWLINE=33, COM=34, WS=35, UNMATCH=36;
	public static final String[] tokenNames = {
		"<INVALID>", "'non'", "'true'", "'{'", "'>>'", "'<<'", "'}'", "'for'", 
		"'until'", "'or'", "'ou'", "'and'", "'repeat'", "'et'", "'not'", "','", 
		"'false'", "BOOL", "'return'", "'if'", "'else'", "'while'", "TYPE", "IDENTIFIANT", 
		"FLOAT", "INT", "OPE_REL", "'('", "')'", "'='", "P1OPE", "P2OPE", "';'", 
		"NEWLINE", "COM", "WS", "UNMATCH"
	};
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_priority0Expression = 2, RULE_fonction = 3, 
		RULE_bloc = 4, RULE_params = 5, RULE_args = 6, RULE_atome = 7, RULE_signed = 8, 
		RULE_condition = 9, RULE_boucle = 10, RULE_boucleElse = 11, RULE_expression = 12, 
		RULE_instruction = 13, RULE_declaration = 14, RULE_finInstruction = 15, 
		RULE_assignation = 16;
	public static final String[] ruleNames = {
		"start", "calcul", "priority0Expression", "fonction", "bloc", "params", 
		"args", "atome", "signed", "condition", "boucle", "boucleElse", "expression", 
		"instruction", "declaration", "finInstruction", "assignation"
	};

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private int _label = 0;
	    /** générateur de nom d'étiquettes */
	    private int nextLabel() { return _label++; }


	    private TablesSymboles tablesSymboles = new TablesSymboles();
	    private final String N = "\n";

	    private String push(String type) {
	        switch(type) {
	            case "int" :
	                return "PUSHI 0" + N;
	            case "float" :
	                return "PUSHF 0." + N;
	            case "bool" :
	                return "PUSHI 0" + N;
	            default :
	                throw new IllegalArgumentException("Type inconnu : '"+type+"'");
	        }
	    }

	    private String ope(String op, String at1Type, String at2Type) {
	        String t = "";
	        if(at1Type.equals("float") || at2Type.equals("float")) {
	            t = "F";
	        }

	        switch(op) {
	            case "*" :
	                return t + "MUL" + N;
	            case "/" :
	                return t + "DIV" + N;
	            case "+" :
	                return t + "ADD" + N;
	            case "-" :
	                return t + "SUB" + N;
	            default:
	                System.err.println("Opérateur arithmétique inconnu : '"+op+"'");
	                throw new IllegalArgumentException("Opérateur arithmétique inconnu : '"+op+"'");
	        }
	    }

	    private String getSignedCode(String ope) {
	        switch(ope) {
	            case "+" :
	                return "";
	            case "-" :
	                return "PUSHI -1" + N;
	            default:
	               throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+ope+"'");
	        }
	    }

	    private String signer(String sign, String type) {
	        if(sign != null) {
	            if(sign.equals("-")) {
	                if(type.equals("int")) {
	                    return "PUSHI -1" + N + "MUL" + N;
	                } else if(type.equals("float")) {
	                    return "PUSHI -1" + N + "ITOF" + N + "FMUL" + N;
	                }
	            }
	        }

	        return "";
	    }

	    private String storerL(String type) {
	        System.err.println("TAILLE LOCALE : "+tablesSymboles.getLocaleTaille());
	        String code = "";
	        for(int i = (-3 - tablesSymboles.getLocaleTaille()) ; i > (-3 - tablesSymboles.getLocaleTaille() - AdresseType.getSize(type)); i--) {
	            code += "STOREL "+ i + N;
	        }
	        return code;
	    }

	    private String pusher(AdresseType table) {
	        String ret = "";
	        for(int i = table.adresse; i < table.adresse + table.getSize(table.type); i++) {
	            ret += (i < 0 ? "PUSHL " : "PUSHG ") + i + N;
	        }
	        return ret;
	    }

	    private String reader(AdresseType table) {
	         switch(table.type) {
	            case "float" :
	                return "READF " + N;
	            case "int" :
	                return "READ " + N;
	            default :
	                throw new IllegalArgumentException("Writer type incorrect : '"+table.type+"'");
	        }
	    }

	    private String writer(String type) {
	        switch(type) {
	            case "float" :
	                return "WRITEF " + N;
	            case "bool" :
	            case "int" :
	                return "WRITE " + N;
	            default :
	                throw new IllegalArgumentException("Writer type incorrect : '"+type+"'");
	        }
	    }

	    private String assigner(String id, String typeExp) {
	        AdresseType at = tablesSymboles.getAdresseType(id);
	        if(at.type.equals(typeExp)) {
	            return storer(at);
	        } else {
	            throw new IllegalArgumentException("Type mismatch: cannot convert from "+typeExp+" to "+at.type);
	        }
	    }

	    private String getType(String exp1Type, String exp2Type) {
	        if(exp1Type.equals("float") || exp2Type.equals("float")) {
	            return "float";
	        } else {
	            return "int";
	        }
	    }

	    private String storer(AdresseType table) {
	        String ret = "";
	        for(int i = table.adresse + table.getSize(table.type) - 1; i >= table.adresse; i--) {
	            ret += (i < 0 ? "STOREL " : "STOREG ") + i + N;
	        }
	        return ret;
	    }

	    private String poper(AdresseType table) {
	        return pop(table.getSize(table.type));
	    }

	    private String pop(int size) {
	        String pop = "";
	        for(int i = 0; i < size; i++) {
	            pop += "POP" + N;
	        }
	        return pop;
	    }

	    private String converter(String at1Type, String at2Type) {
	        if(at1Type.equals(at2Type)) {
	            return "";
	        } else if(at1Type.equals("int")) {
	            return "ITOF" + N;
	        } else {
	            return "";
	        }
	    }

	    private String reterner(String type) {
	        switch(type) {
	            case "int" :
	                return "PUSHI 0" + N;
	            case "float" :
	                return "PUSHF 0." +N;
	            default :
	                throw new IllegalArgumentException("Reterner type incorrect : '"+type+"'");
	        }
	    }

	    private String opeLog(String ope, String type) {
	        String t = "";
	        if(type.equals("float")) t = "F";
	        switch(ope) {
	            case "<" :
	                return t + "INF";
	            case "<=" :
	                return t + "INFEQ";
	            case ">" :
	                return t + "SUP";
	            case ">=" :
	                return t + "SUPEQ";
	            case "==" :
	                return t + "EQUAL";
	            case "!=" :
	            case "<>" :
	                return t + "NEQ";
	                default :
	                throw new IllegalArgumentException("Operateur logique inconnu : '"+ope+"'");
	        }
	    }

	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); calcul();
			setState(35); match(EOF);
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

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclarationContext declaration;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);

		                ((CalculContext)_localctx).code =  new String();
		            
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(37); ((CalculContext)_localctx).declaration = declaration();
					 _localctx.code += ((CalculContext)_localctx).declaration.code; 
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			 int entry = nextLabel(); _localctx.code += "JUMP " + entry + "\n"; 
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46); match(NEWLINE);
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(52); ((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60); match(NEWLINE);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL " + entry + "\n"; 
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(67); ((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 
			                _localctx.code += pop(tablesSymboles.getTaille()) + N
			                        + "HALT";
			            
			}

			                System.out.println(_localctx.code);
			            
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

	public static class Priority0ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext expression;
		public TerminalNode PF() { return getToken(CalculetteParser.PF, 0); }
		public TerminalNode PO() { return getToken(CalculetteParser.PO, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Priority0ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_priority0Expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterPriority0Expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitPriority0Expression(this);
		}
	}

	public final Priority0ExpressionContext priority0Expression() throws RecognitionException {
		Priority0ExpressionContext _localctx = new Priority0ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_priority0Expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); match(PO);
			setState(78); ((Priority0ExpressionContext)_localctx).expression = expression(0);
			setState(79); match(PF);

			        ((Priority0ExpressionContext)_localctx).code =  ((Priority0ExpressionContext)_localctx).expression.code;
			        ((Priority0ExpressionContext)_localctx).type =  ((Priority0ExpressionContext)_localctx).expression.type;
			    
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

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public BlocContext bloc;
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fonction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(82); ((FonctionContext)_localctx).TYPE = match(TYPE);
			setState(83); ((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(84); match(PO);
			setState(86);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(85); params();
				}
			}

			setState(88); match(PF);
			 
			            int lab = nextLabel();
			            tablesSymboles.nouvelleFonction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), lab, (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			            ((FonctionContext)_localctx).code =  "LABEL " + lab + N;
			        
			setState(90); ((FonctionContext)_localctx).bloc = bloc();

			            _localctx.code += ((FonctionContext)_localctx).bloc.code;
			        
			}

			            _localctx.code += "RETURN" + N;
			        
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

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloc);
		 ((BlocContext)_localctx).code =  new String(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(T__13);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(96); ((BlocContext)_localctx).instruction = instruction();
				 _localctx.code += ((BlocContext)_localctx).instruction.code; 
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104); match(T__10);
			setState(105); finInstruction();


			        tablesSymboles.dropTableLocale();
			        
			    
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

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public List<TerminalNode> TYPE() { return getTokens(CalculetteParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CalculetteParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); ((ParamsContext)_localctx).TYPE = match(TYPE);
			setState(109); ((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			 
			            tablesSymboles.newTableLocale();
			            tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
			        
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(111); match(T__1);
				setState(112); ((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(113); ((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				 
				                tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				            
				}
				}
				setState(119);
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

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE))) != 0)) {
				{
				setState(120); ((ArgsContext)_localctx).expression = expression(0);
				 
				        _localctx.code += ((ArgsContext)_localctx).expression.code;
				        _localctx.size += AdresseType.getSize(((ArgsContext)_localctx).expression.type);

				    
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(122); match(T__1);
					setState(123); ((ArgsContext)_localctx).expression = expression(0);
					 
					        _localctx.code += ((ArgsContext)_localctx).expression.code;
					        _localctx.size += AdresseType.getSize(((ArgsContext)_localctx).expression.type);
					    
					}
					}
					setState(130);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class AtomeContext extends ParserRuleContext {
		public String code;
		public String type;
		public Token INT;
		public Token FLOAT;
		public Token IDENTIFIANT;
		public TerminalNode INT() { return getToken(CalculetteParser.INT, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode FLOAT() { return getToken(CalculetteParser.FLOAT, 0); }
		public AtomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAtome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAtome(this);
		}
	}

	public final AtomeContext atome() throws RecognitionException {
		AtomeContext _localctx = new AtomeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atome);
		try {
			setState(139);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(133); ((AtomeContext)_localctx).INT = match(INT);

				            ((AtomeContext)_localctx).code =  "PUSHI " + (((AtomeContext)_localctx).INT!=null?((AtomeContext)_localctx).INT.getText():null) + N;
				            ((AtomeContext)_localctx).type =  "int";
				        
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(135); ((AtomeContext)_localctx).FLOAT = match(FLOAT);

				            ((AtomeContext)_localctx).code =  "PUSHF " + (((AtomeContext)_localctx).FLOAT!=null?((AtomeContext)_localctx).FLOAT.getText():null) + N;
				            ((AtomeContext)_localctx).type =  "float";
				        
				}
				break;
			case IDENTIFIANT:
				enterOuterAlt(_localctx, 3);
				{
				setState(137); ((AtomeContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            ((AtomeContext)_localctx).code =  pusher(tablesSymboles.getAdresseType((((AtomeContext)_localctx).IDENTIFIANT!=null?((AtomeContext)_localctx).IDENTIFIANT.getText():null)));
				            ((AtomeContext)_localctx).type =  tablesSymboles.getAdresseType((((AtomeContext)_localctx).IDENTIFIANT!=null?((AtomeContext)_localctx).IDENTIFIANT.getText():null)).type;
				        
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

	public static class SignedContext extends ParserRuleContext {
		public String code;
		public Token P2OPE;
		public TerminalNode P2OPE() { return getToken(CalculetteParser.P2OPE, 0); }
		public SignedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterSigned(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitSigned(this);
		}
	}

	public final SignedContext signed() throws RecognitionException {
		SignedContext _localctx = new SignedContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_signed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if (_la==P2OPE) {
				{
				setState(141); ((SignedContext)_localctx).P2OPE = match(P2OPE);

				        ((SignedContext)_localctx).code =  getSignedCode((((SignedContext)_localctx).P2OPE!=null?((SignedContext)_localctx).P2OPE.getText():null));
				    
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

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ConditionContext c1;
		public ConditionContext c;
		public Token IDENTIFIANT;
		public ExpressionContext exp1;
		public Token OPE_REL;
		public ExpressionContext exp2;
		public ConditionContext c2;
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode OPE_REL() { return getToken(CalculetteParser.OPE_REL, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(146);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(147); ((ConditionContext)_localctx).c = condition(4);

				            ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).c.code + "NEQ" + N;
				            ((ConditionContext)_localctx).type =  "bool";
				        
				}
				break;
			case 2:
				{
				setState(150); ((ConditionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            ((ConditionContext)_localctx).code =  pusher(tablesSymboles.getAdresseType((((ConditionContext)_localctx).IDENTIFIANT!=null?((ConditionContext)_localctx).IDENTIFIANT.getText():null)));
				            ((ConditionContext)_localctx).type =  tablesSymboles.getAdresseType((((ConditionContext)_localctx).IDENTIFIANT!=null?((ConditionContext)_localctx).IDENTIFIANT.getText():null)).type;
				            if(!_localctx.type.equals("bool")) {
				                throw new IllegalArgumentException("Type mismatch: cannot convert from "+_localctx.type+" to bool");
				            }
				        
				}
				break;
			case 3:
				{
				setState(152); match(T__14);
				 
				            ((ConditionContext)_localctx).code =  "PUSHI 1" + N;
				            ((ConditionContext)_localctx).type =  "bool";
				        
				}
				break;
			case 4:
				{
				setState(154); match(T__0);
				 
				            ((ConditionContext)_localctx).code =  "PUSHI 0" + N;
				            ((ConditionContext)_localctx).type =  "bool";
				        
				}
				break;
			case 5:
				{
				setState(156); ((ConditionContext)_localctx).exp1 = expression(0);
				setState(157); ((ConditionContext)_localctx).OPE_REL = match(OPE_REL);
				setState(158); ((ConditionContext)_localctx).exp2 = expression(0);

				            ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).exp1.code + ((ConditionContext)_localctx).exp2.code + opeLog((((ConditionContext)_localctx).OPE_REL!=null?((ConditionContext)_localctx).OPE_REL.getText():null), getType(((ConditionContext)_localctx).exp1.type, ((ConditionContext)_localctx).exp2.type)) + N;
				            ((ConditionContext)_localctx).type =  "bool";
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(173);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(163);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(164);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__3) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(165); ((ConditionContext)_localctx).c2 = condition(4);

						                      ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c1.code + ((ConditionContext)_localctx).c2.code + "MUL" + N;
						                      ((ConditionContext)_localctx).type =  "bool";
						                  
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(168);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(169);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__6) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(170); ((ConditionContext)_localctx).c2 = condition(3);

						                      ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c1.code + ((ConditionContext)_localctx).c2.code + "ADD" + N;
						                      ((ConditionContext)_localctx).type =  "bool";
						                  
						}
						break;
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	public static class BoucleContext extends ParserRuleContext {
		public String code;
		public String codeInstru;
		public String elseCode;
		public String codeDec;
		public String codeCond;
		public String codeAssign;
		public ConditionContext condition;
		public InstructionContext instruction;
		public BoucleElseContext boucleElse;
		public DeclarationContext declaration;
		public AssignationContext assignation;
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode PF() { return getToken(CalculetteParser.PF, 0); }
		public TerminalNode IF() { return getToken(CalculetteParser.IF, 0); }
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public TerminalNode PO() { return getToken(CalculetteParser.PO, 0); }
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public TerminalNode WHILE() { return getToken(CalculetteParser.WHILE, 0); }
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public BoucleElseContext boucleElse() {
			return getRuleContext(BoucleElseContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public BoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucle(this);
		}
	}

	public final BoucleContext boucle() throws RecognitionException {
		BoucleContext _localctx = new BoucleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_boucle);
		 ((BoucleContext)_localctx).codeAssign =  new String(); ((BoucleContext)_localctx).codeInstru =  new String(); ((BoucleContext)_localctx).codeCond =  new String(); ((BoucleContext)_localctx).elseCode =  new String(); 
		int _la;
		try {
			setState(296);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(178); match(WHILE);
				setState(179); match(PO);
				setState(180); ((BoucleContext)_localctx).condition = condition(0);
				setState(181); match(PF);
				setState(195);
				switch (_input.LA(1)) {
				case T__9:
				case T__4:
				case RETURN:
				case IF:
				case WHILE:
				case IDENTIFIANT:
				case FLOAT:
				case INT:
				case PO:
				case P2OPE:
				case SEMICOLON:
				case NEWLINE:
					{
					setState(182); ((BoucleContext)_localctx).instruction = instruction();
					 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
					}
					break;
				case T__13:
					{
					setState(185); match(T__13);
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
						{
						{
						setState(186); ((BoucleContext)_localctx).instruction = instruction();
						 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
						}
						}
						setState(193);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(194); match(T__10);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

				       // tablesSymboles.newTableLocale();
				        ((BoucleContext)_localctx).code =  "LABEL " + nextLabel() + N;
				        _localctx.code += ((BoucleContext)_localctx).condition.code;
				        _localctx.code += "JUMPF " + _label + N;
				        _localctx.code += _localctx.codeInstru;
				        _localctx.code += "JUMP " + (_label - 1) + N;
				        _localctx.code += "LABEL " + nextLabel() + N;
				       // tablesSymboles.dropTableLocale();
				    
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(199); match(T__4);
				setState(200); match(T__13);
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
					{
					{
					setState(201); ((BoucleContext)_localctx).instruction = instruction();
					 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
					}
					}
					setState(208);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(209); match(T__10);
				setState(210); finInstruction();
				setState(211); match(T__8);
				setState(212); match(PO);
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__14) | (1L << T__2) | (1L << T__0) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE))) != 0)) {
					{
					{
					setState(213); ((BoucleContext)_localctx).condition = condition(0);
					 _localctx.codeCond += ((BoucleContext)_localctx).condition.code; 
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221); match(PF);

				       // tablesSymboles.newTableLocale();
				        int labelDebutWhile = nextLabel();
				        int labelFinWhile = nextLabel();
				        ((BoucleContext)_localctx).code =  "LABEL " + labelDebutWhile + N;
				        _localctx.code += _localctx.codeInstru;
				        _localctx.code += ((BoucleContext)_localctx).condition.code;
				        _localctx.code += "JUMPF " + labelFinWhile + N;
				         _localctx.code += "JUMP " + labelDebutWhile + N;
				        _localctx.code += "LABEL " + labelFinWhile + N;
				       // tablesSymboles.dropTableLocale();
				    
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(224); match(IF);
				setState(225); match(PO);
				setState(226); ((BoucleContext)_localctx).condition = condition(0);
				setState(227); match(PF);
				setState(241);
				switch (_input.LA(1)) {
				case T__9:
				case T__4:
				case RETURN:
				case IF:
				case WHILE:
				case IDENTIFIANT:
				case FLOAT:
				case INT:
				case PO:
				case P2OPE:
				case SEMICOLON:
				case NEWLINE:
					{
					setState(228); ((BoucleContext)_localctx).instruction = instruction();
					 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
					}
					break;
				case T__13:
					{
					setState(231); match(T__13);
					setState(237);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
						{
						{
						setState(232); ((BoucleContext)_localctx).instruction = instruction();
						 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
						}
						}
						setState(239);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(240); match(T__10);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(246);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(243); ((BoucleContext)_localctx).boucleElse = boucleElse();
					 ((BoucleContext)_localctx).elseCode =  ((BoucleContext)_localctx).boucleElse.code; 
					}
					break;
				}

				       // tablesSymboles.newTableLocale();

				        int labelElse = nextLabel();
				        int labelIf = nextLabel();

				        ((BoucleContext)_localctx).code =  ((BoucleContext)_localctx).condition.code;
				        _localctx.code += "JUMPF " + labelElse + N;
				        _localctx.code += _localctx.codeInstru;
				        _localctx.code += "JUMP " + labelIf + N;
				        _localctx.code += "LABEL " + labelElse + N;
				        _localctx.code += _localctx.elseCode;
				        _localctx.code += "LABEL " + labelIf + N;

				        //tablesSymboles.dropTableLocale();
				    
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(250); match(T__9);
				setState(251); match(PO);
				setState(258);
				switch (_input.LA(1)) {
				case TYPE:
					{
					setState(252); ((BoucleContext)_localctx).declaration = declaration();
					 ((BoucleContext)_localctx).codeDec =  ((BoucleContext)_localctx).declaration.code; 
					}
					break;
				case IDENTIFIANT:
					{
					setState(255); ((BoucleContext)_localctx).assignation = assignation();
					 ((BoucleContext)_localctx).codeDec =  ((BoucleContext)_localctx).assignation.code; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(260); match(SEMICOLON);
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__14) | (1L << T__2) | (1L << T__0) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE))) != 0)) {
					{
					{
					setState(261); ((BoucleContext)_localctx).condition = condition(0);
					 _localctx.codeCond += ((BoucleContext)_localctx).condition.code; 
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(269); match(SEMICOLON);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IDENTIFIANT) {
					{
					{
					setState(270); ((BoucleContext)_localctx).assignation = assignation();
					 _localctx.codeAssign += ((BoucleContext)_localctx).assignation.code; 
					}
					}
					setState(277);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(278); match(PF);
				setState(292);
				switch (_input.LA(1)) {
				case T__9:
				case T__4:
				case RETURN:
				case IF:
				case WHILE:
				case IDENTIFIANT:
				case FLOAT:
				case INT:
				case PO:
				case P2OPE:
				case SEMICOLON:
				case NEWLINE:
					{
					setState(279); ((BoucleContext)_localctx).instruction = instruction();
					 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
					}
					break;
				case T__13:
					{
					setState(282); match(T__13);
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
						{
						{
						setState(283); ((BoucleContext)_localctx).instruction = instruction();
						 _localctx.codeInstru += ((BoucleContext)_localctx).instruction.code; 
						}
						}
						setState(290);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(291); match(T__10);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

				           // tablesSymboles.newTableLocale();
				            ((BoucleContext)_localctx).code =  _localctx.codeDec;
				            int labelDebutFor = nextLabel();
				            int labelFinFor = nextLabel();
				            _localctx.code += "LABEL " + labelDebutFor + N;
				            _localctx.code += _localctx.codeCond;
				            _localctx.code += "JUMPF " + labelFinFor + N;
				            _localctx.code += _localctx.codeInstru;
				            _localctx.code += _localctx.codeAssign;
				            _localctx.code += "JUMP " + labelDebutFor + N;
				            _localctx.code += "LABEL " + labelFinFor + N;

				           // tablesSymboles.dropTableLocale();
				        
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

	public static class BoucleElseContext extends ParserRuleContext {
		public String code;
		public String codeInstru;
		public InstructionContext instruction;
		public TerminalNode ELSE() { return getToken(CalculetteParser.ELSE, 0); }
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public BoucleElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucleElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucleElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucleElse(this);
		}
	}

	public final BoucleElseContext boucleElse() throws RecognitionException {
		BoucleElseContext _localctx = new BoucleElseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_boucleElse);
		 ((BoucleElseContext)_localctx).codeInstru =  new String(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298); match(ELSE);
			setState(312);
			switch (_input.LA(1)) {
			case T__9:
			case T__4:
			case RETURN:
			case IF:
			case WHILE:
			case IDENTIFIANT:
			case FLOAT:
			case INT:
			case PO:
			case P2OPE:
			case SEMICOLON:
			case NEWLINE:
				{
				setState(299); ((BoucleElseContext)_localctx).instruction = instruction();
				 _localctx.codeInstru += ((BoucleElseContext)_localctx).instruction.code; 
				}
				break;
			case T__13:
				{
				setState(302); match(T__13);
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__4) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << IDENTIFIANT) | (1L << FLOAT) | (1L << INT) | (1L << PO) | (1L << P2OPE) | (1L << SEMICOLON) | (1L << NEWLINE))) != 0)) {
					{
					{
					setState(303); ((BoucleElseContext)_localctx).instruction = instruction();
					 _localctx.codeInstru += ((BoucleElseContext)_localctx).instruction.code; 
					}
					}
					setState(310);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(311); match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			        ((BoucleElseContext)_localctx).code =  _localctx.codeInstru;
			    
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
		public String code;
		public String type;
		public ExpressionContext exp1;
		public Priority0ExpressionContext priority0Expression;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token TYPE;
		public Token P2OPE;
		public AtomeContext atome;
		public Token P1OPE;
		public ExpressionContext exp2;
		public TerminalNode P1OPE() { return getToken(CalculetteParser.P1OPE, 0); }
		public TerminalNode PF() { return getToken(CalculetteParser.PF, 0); }
		public AtomeContext atome() {
			return getRuleContext(AtomeContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public Priority0ExpressionContext priority0Expression() {
			return getRuleContext(Priority0ExpressionContext.class,0);
		}
		public TerminalNode PO() { return getToken(CalculetteParser.PO, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode P2OPE() { return getToken(CalculetteParser.P2OPE, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(317); ((ExpressionContext)_localctx).priority0Expression = priority0Expression();
				 
				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).priority0Expression.code;
				            ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).priority0Expression.type;
				        
				}
				break;
			case 2:
				{
				setState(320); ((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(321); match(PO);
				setState(322); ((ExpressionContext)_localctx).args = args();
				setState(323); match(PF);
				  
				            
				            ((ExpressionContext)_localctx).code =  reterner(tablesSymboles.getFonction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)).type);
				            _localctx.code += ((ExpressionContext)_localctx).args.code;
				            _localctx.code += "CALL " + tablesSymboles.getFonction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)).adresse + N;
				            ((ExpressionContext)_localctx).type =  tablesSymboles.getFonction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)).type;
				            _localctx.code += pop(((ExpressionContext)_localctx).args.size) + N;
				        
				}
				break;
			case 3:
				{
				{
				setState(326); match(PO);
				setState(327); ((ExpressionContext)_localctx).TYPE = match(TYPE);
				setState(328); match(PF);
				setState(329); ((ExpressionContext)_localctx).exp1 = expression(0);
				}

				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).exp1.code;
				            if("float".equals((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null)) && ("int".equals(((ExpressionContext)_localctx).exp1.type) || "bool".equals(((ExpressionContext)_localctx).exp1.type))) {
				                _localctx.code += "ITOF" + N;
				            } else if("int".equals((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null)) && "float".equals(((ExpressionContext)_localctx).exp1.type)) {
				                _localctx.code += "FTOI" + N;
				            } else if("bool".equals((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null)) && "float".equals(((ExpressionContext)_localctx).exp1.type)) {
				                _localctx.code += "PUSHF 0." + N + "FNEQ" + N;
				            } else if("bool".equals((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null)) && "int".equals(((ExpressionContext)_localctx).exp1.type)) {
				                _localctx.code += "PUSHI 0." + N + "NEQ" + N;
				            }
				            ((ExpressionContext)_localctx).type =  (((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null);
				        
				}
				break;
			case 4:
				{
				setState(334);
				_la = _input.LA(1);
				if (_la==P2OPE) {
					{
					setState(333); ((ExpressionContext)_localctx).P2OPE = match(P2OPE);
					}
				}

				setState(336); ((ExpressionContext)_localctx).atome = atome();

				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).atome.code + signer((((ExpressionContext)_localctx).P2OPE!=null?((ExpressionContext)_localctx).P2OPE.getText():null), ((ExpressionContext)_localctx).atome.type) ;
				            ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).atome.type;
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(351);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(341);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(342); ((ExpressionContext)_localctx).P1OPE = match(P1OPE);
						setState(343); ((ExpressionContext)_localctx).exp2 = expression(6);

						                      ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).exp1.code + converter(((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type) + ((ExpressionContext)_localctx).exp2.code + converter(((ExpressionContext)_localctx).exp2.type, ((ExpressionContext)_localctx).exp1.type) + ope((((ExpressionContext)_localctx).P1OPE!=null?((ExpressionContext)_localctx).P1OPE.getText():null), ((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type);
						                      ((ExpressionContext)_localctx).type =  getType(((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type);
						                  
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(346);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(347); ((ExpressionContext)_localctx).P2OPE = match(P2OPE);
						setState(348); ((ExpressionContext)_localctx).exp2 = expression(5);

						                      ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).exp1.code + converter(((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type) + ((ExpressionContext)_localctx).exp2.code + converter(((ExpressionContext)_localctx).exp2.type, ((ExpressionContext)_localctx).exp1.type) + ope((((ExpressionContext)_localctx).P2OPE!=null?((ExpressionContext)_localctx).P2OPE.getText():null), ((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type);
						                      ((ExpressionContext)_localctx).type =  getType(((ExpressionContext)_localctx).exp1.type, ((ExpressionContext)_localctx).exp2.type);
						                  
						}
						break;
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public BoucleContext boucle;
		public Token IDENTIFIANT;
		public AssignationContext assignation;
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BoucleContext boucle() {
			return getRuleContext(BoucleContext.class,0);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_instruction);
		try {
			setState(390);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(356); ((InstructionContext)_localctx).expression = expression(0);
				setState(357); finInstruction();
				 
				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360); ((InstructionContext)_localctx).boucle = boucle();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucle.code;
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(363); match(RETURN);
				setState(364); ((InstructionContext)_localctx).expression = expression(0);
				setState(365); finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				            _localctx.code += storerL(((InstructionContext)_localctx).expression.type);
				            _localctx.code += "RETURN " + N;
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(368); ((InstructionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(369); match(T__11);
				setState(370); finInstruction();

				            ((InstructionContext)_localctx).code =  reader(tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null))) + storer(tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null)));
				            tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null));
				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(373); ((InstructionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(374); match(T__12);
				setState(375); finInstruction();

				            ((InstructionContext)_localctx).code =  pusher(tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null))) + writer(tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null)).type) + poper(tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null)));
				        
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(378); ((InstructionContext)_localctx).expression = expression(0);
				setState(379); match(T__12);
				setState(380); finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code + writer(((InstructionContext)_localctx).expression.type) + pop(AdresseType.getSize(((InstructionContext)_localctx).expression.type));
				        
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(383); ((InstructionContext)_localctx).assignation = assignation();
				setState(384); finInstruction();
				 
				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
				        
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(387); finInstruction();

				            ((InstructionContext)_localctx).code = "";
				        
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

	public static class DeclarationContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public ConditionContext condition;
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode EQ() { return getToken(CalculetteParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_declaration);
		 ((DeclarationContext)_localctx).code =  new String(); 
		try {
			setState(417);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(392); ((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(393); ((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(394); finInstruction();

				            ((DeclarationContext)_localctx).code =  push((((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));
				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(397); ((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(398); ((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(399); match(EQ);
				setState(406);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(400); ((DeclarationContext)_localctx).expression = expression(0);
					 ((DeclarationContext)_localctx).code =  ((DeclarationContext)_localctx).expression.code; 
					}
					break;
				case 2:
					{
					setState(403); ((DeclarationContext)_localctx).condition = condition(0);
					 ((DeclarationContext)_localctx).code =  ((DeclarationContext)_localctx).condition.code; 
					}
					break;
				}
				setState(408); finInstruction();

				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(411); ((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(412); ((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(413); match(T__11);
				setState(414); finInstruction();

				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));
				            ((DeclarationContext)_localctx).code =  reader(tablesSymboles.getAdresseType((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null)));
				        
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

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(419); match(SEMICOLON);
				}
				}
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(426); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(425); match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(428); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public ConditionContext condition;
		public TerminalNode EQ() { return getToken(CalculetteParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_assignation);
		try {
			setState(440);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(430); ((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(431); match(EQ);
				setState(432); ((AssignationContext)_localctx).expression = expression(0);
				  
				            ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + assigner((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null), ((AssignationContext)_localctx).expression.type);
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(435); ((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(436); match(EQ);
				setState(437); ((AssignationContext)_localctx).condition = condition(0);

				            ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).condition.code + assigner((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null), ((AssignationContext)_localctx).condition.type);
				        
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9: return condition_sempred((ConditionContext)_localctx, predIndex);
		case 12: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 3);
		case 1: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 5);
		case 3: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u01bd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\7\3\62\n\3\f"+
		"\3\16\3\65\13\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\7\3@\n\3\f\3\16"+
		"\3C\13\3\3\3\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\5\5Y\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\7\6f\n\6\f\6\16\6i\13\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\7\7v\n\7\f\7\16\7y\13\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0081\n\b\f\b"+
		"\16\b\u0084\13\b\5\b\u0086\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u008e\n\t\3"+
		"\n\3\n\5\n\u0092\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a4\n\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00b0\n\13\f\13\16\13\u00b3\13\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00c0\n\f\f\f\16\f\u00c3\13"+
		"\f\3\f\5\f\u00c6\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00cf\n\f\f\f\16"+
		"\f\u00d2\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00db\n\f\f\f\16\f\u00de"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00ee"+
		"\n\f\f\f\16\f\u00f1\13\f\3\f\5\f\u00f4\n\f\3\f\3\f\3\f\5\f\u00f9\n\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0105\n\f\3\f\3\f\3\f\3\f\7"+
		"\f\u010b\n\f\f\f\16\f\u010e\13\f\3\f\3\f\3\f\3\f\7\f\u0114\n\f\f\f\16"+
		"\f\u0117\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0121\n\f\f\f\16\f\u0124"+
		"\13\f\3\f\5\f\u0127\n\f\3\f\3\f\5\f\u012b\n\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\7\r\u0135\n\r\f\r\16\r\u0138\13\r\3\r\5\r\u013b\n\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\5\16\u0151\n\16\3\16\3\16\3\16\5\16\u0156\n\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0162\n\16\f\16\16"+
		"\16\u0165\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0189\n\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0199"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u01a4\n\20\3\21"+
		"\7\21\u01a7\n\21\f\21\16\21\u01aa\13\21\3\21\6\21\u01ad\n\21\r\21\16\21"+
		"\u01ae\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01bb\n"+
		"\22\3\22\2\4\24\32\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\5\4"+
		"\2\3\3\20\20\4\2\r\r\17\17\3\2\13\f\u01e2\2$\3\2\2\2\4,\3\2\2\2\6O\3\2"+
		"\2\2\bT\3\2\2\2\na\3\2\2\2\fn\3\2\2\2\16\u0085\3\2\2\2\20\u008d\3\2\2"+
		"\2\22\u0091\3\2\2\2\24\u00a3\3\2\2\2\26\u012a\3\2\2\2\30\u012c\3\2\2\2"+
		"\32\u0155\3\2\2\2\34\u0188\3\2\2\2\36\u01a3\3\2\2\2 \u01a8\3\2\2\2\"\u01ba"+
		"\3\2\2\2$%\5\4\3\2%&\7\2\2\3&\3\3\2\2\2\'(\5\36\20\2()\b\3\1\2)+\3\2\2"+
		"\2*\'\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\63\b"+
		"\3\1\2\60\62\7#\2\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3"+
		"\2\2\2\64;\3\2\2\2\65\63\3\2\2\2\66\67\5\b\5\2\678\b\3\1\28:\3\2\2\29"+
		"\66\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<A\3\2\2\2=;\3\2\2\2>@\7#\2\2"+
		"?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DJ\b\3\1\2"+
		"EF\5\34\17\2FG\b\3\1\2GI\3\2\2\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2"+
		"\2KM\3\2\2\2LJ\3\2\2\2MN\b\3\1\2N\5\3\2\2\2OP\7\35\2\2PQ\5\32\16\2QR\7"+
		"\36\2\2RS\b\4\1\2S\7\3\2\2\2TU\7\30\2\2UV\7\31\2\2VX\7\35\2\2WY\5\f\7"+
		"\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7\36\2\2[\\\b\5\1\2\\]\5\n\6\2]^\b"+
		"\5\1\2^_\3\2\2\2_`\b\5\1\2`\t\3\2\2\2ag\7\5\2\2bc\5\34\17\2cd\b\6\1\2"+
		"df\3\2\2\2eb\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2"+
		"jk\7\b\2\2kl\5 \21\2lm\b\6\1\2m\13\3\2\2\2no\7\30\2\2op\7\31\2\2pw\b\7"+
		"\1\2qr\7\21\2\2rs\7\30\2\2st\7\31\2\2tv\b\7\1\2uq\3\2\2\2vy\3\2\2\2wu"+
		"\3\2\2\2wx\3\2\2\2x\r\3\2\2\2yw\3\2\2\2z{\5\32\16\2{\u0082\b\b\1\2|}\7"+
		"\21\2\2}~\5\32\16\2~\177\b\b\1\2\177\u0081\3\2\2\2\u0080|\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0086\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0085z\3\2\2\2\u0085\u0086\3\2\2\2\u0086\17\3"+
		"\2\2\2\u0087\u0088\7\33\2\2\u0088\u008e\b\t\1\2\u0089\u008a\7\32\2\2\u008a"+
		"\u008e\b\t\1\2\u008b\u008c\7\31\2\2\u008c\u008e\b\t\1\2\u008d\u0087\3"+
		"\2\2\2\u008d\u0089\3\2\2\2\u008d\u008b\3\2\2\2\u008e\21\3\2\2\2\u008f"+
		"\u0090\7!\2\2\u0090\u0092\b\n\1\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\23\3\2\2\2\u0093\u0094\b\13\1\2\u0094\u0095\t\2\2\2\u0095\u0096"+
		"\5\24\13\6\u0096\u0097\b\13\1\2\u0097\u00a4\3\2\2\2\u0098\u0099\7\31\2"+
		"\2\u0099\u00a4\b\13\1\2\u009a\u009b\7\4\2\2\u009b\u00a4\b\13\1\2\u009c"+
		"\u009d\7\22\2\2\u009d\u00a4\b\13\1\2\u009e\u009f\5\32\16\2\u009f\u00a0"+
		"\7\34\2\2\u00a0\u00a1\5\32\16\2\u00a1\u00a2\b\13\1\2\u00a2\u00a4\3\2\2"+
		"\2\u00a3\u0093\3\2\2\2\u00a3\u0098\3\2\2\2\u00a3\u009a\3\2\2\2\u00a3\u009c"+
		"\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\u00b1\3\2\2\2\u00a5\u00a6\f\5\2\2\u00a6"+
		"\u00a7\t\3\2\2\u00a7\u00a8\5\24\13\6\u00a8\u00a9\b\13\1\2\u00a9\u00b0"+
		"\3\2\2\2\u00aa\u00ab\f\4\2\2\u00ab\u00ac\t\4\2\2\u00ac\u00ad\5\24\13\5"+
		"\u00ad\u00ae\b\13\1\2\u00ae\u00b0\3\2\2\2\u00af\u00a5\3\2\2\2\u00af\u00aa"+
		"\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\25\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7\27\2\2\u00b5\u00b6\7\35"+
		"\2\2\u00b6\u00b7\5\24\13\2\u00b7\u00c5\7\36\2\2\u00b8\u00b9\5\34\17\2"+
		"\u00b9\u00ba\b\f\1\2\u00ba\u00c6\3\2\2\2\u00bb\u00c1\7\5\2\2\u00bc\u00bd"+
		"\5\34\17\2\u00bd\u00be\b\f\1\2\u00be\u00c0\3\2\2\2\u00bf\u00bc\3\2\2\2"+
		"\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c6\7\b\2\2\u00c5\u00b8\3\2\2\2\u00c5"+
		"\u00bb\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\f\1\2\u00c8\u012b\3\2"+
		"\2\2\u00c9\u00ca\7\16\2\2\u00ca\u00d0\7\5\2\2\u00cb\u00cc\5\34\17\2\u00cc"+
		"\u00cd\b\f\1\2\u00cd\u00cf\3\2\2\2\u00ce\u00cb\3\2\2\2\u00cf\u00d2\3\2"+
		"\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\u00d4\7\b\2\2\u00d4\u00d5\5 \21\2\u00d5\u00d6\7\n"+
		"\2\2\u00d6\u00dc\7\35\2\2\u00d7\u00d8\5\24\13\2\u00d8\u00d9\b\f\1\2\u00d9"+
		"\u00db\3\2\2\2\u00da\u00d7\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2"+
		"\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df"+
		"\u00e0\7\36\2\2\u00e0\u00e1\b\f\1\2\u00e1\u012b\3\2\2\2\u00e2\u00e3\7"+
		"\25\2\2\u00e3\u00e4\7\35\2\2\u00e4\u00e5\5\24\13\2\u00e5\u00f3\7\36\2"+
		"\2\u00e6\u00e7\5\34\17\2\u00e7\u00e8\b\f\1\2\u00e8\u00f4\3\2\2\2\u00e9"+
		"\u00ef\7\5\2\2\u00ea\u00eb\5\34\17\2\u00eb\u00ec\b\f\1\2\u00ec\u00ee\3"+
		"\2\2\2\u00ed\u00ea\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f4\7\b"+
		"\2\2\u00f3\u00e6\3\2\2\2\u00f3\u00e9\3\2\2\2\u00f4\u00f8\3\2\2\2\u00f5"+
		"\u00f6\5\30\r\2\u00f6\u00f7\b\f\1\2\u00f7\u00f9\3\2\2\2\u00f8\u00f5\3"+
		"\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b\f\1\2\u00fb"+
		"\u012b\3\2\2\2\u00fc\u00fd\7\t\2\2\u00fd\u0104\7\35\2\2\u00fe\u00ff\5"+
		"\36\20\2\u00ff\u0100\b\f\1\2\u0100\u0105\3\2\2\2\u0101\u0102\5\"\22\2"+
		"\u0102\u0103\b\f\1\2\u0103\u0105\3\2\2\2\u0104\u00fe\3\2\2\2\u0104\u0101"+
		"\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u010c\7\"\2\2\u0107\u0108\5\24\13\2"+
		"\u0108\u0109\b\f\1\2\u0109\u010b\3\2\2\2\u010a\u0107\3\2\2\2\u010b\u010e"+
		"\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010f\u0115\7\"\2\2\u0110\u0111\5\"\22\2\u0111\u0112\b"+
		"\f\1\2\u0112\u0114\3\2\2\2\u0113\u0110\3\2\2\2\u0114\u0117\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0118\u0126\7\36\2\2\u0119\u011a\5\34\17\2\u011a\u011b\b\f\1\2\u011b"+
		"\u0127\3\2\2\2\u011c\u0122\7\5\2\2\u011d\u011e\5\34\17\2\u011e\u011f\b"+
		"\f\1\2\u011f\u0121\3\2\2\2\u0120\u011d\3\2\2\2\u0121\u0124\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125\3\2\2\2\u0124\u0122\3\2"+
		"\2\2\u0125\u0127\7\b\2\2\u0126\u0119\3\2\2\2\u0126\u011c\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0129\b\f\1\2\u0129\u012b\3\2\2\2\u012a\u00b4\3\2"+
		"\2\2\u012a\u00c9\3\2\2\2\u012a\u00e2\3\2\2\2\u012a\u00fc\3\2\2\2\u012b"+
		"\27\3\2\2\2\u012c\u013a\7\26\2\2\u012d\u012e\5\34\17\2\u012e\u012f\b\r"+
		"\1\2\u012f\u013b\3\2\2\2\u0130\u0136\7\5\2\2\u0131\u0132\5\34\17\2\u0132"+
		"\u0133\b\r\1\2\u0133\u0135\3\2\2\2\u0134\u0131\3\2\2\2\u0135\u0138\3\2"+
		"\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0139\u013b\7\b\2\2\u013a\u012d\3\2\2\2\u013a\u0130\3\2"+
		"\2\2\u013b\u013c\3\2\2\2\u013c\u013d\b\r\1\2\u013d\31\3\2\2\2\u013e\u013f"+
		"\b\16\1\2\u013f\u0140\5\6\4\2\u0140\u0141\b\16\1\2\u0141\u0156\3\2\2\2"+
		"\u0142\u0143\7\31\2\2\u0143\u0144\7\35\2\2\u0144\u0145\5\16\b\2\u0145"+
		"\u0146\7\36\2\2\u0146\u0147\b\16\1\2\u0147\u0156\3\2\2\2\u0148\u0149\7"+
		"\35\2\2\u0149\u014a\7\30\2\2\u014a\u014b\7\36\2\2\u014b\u014c\5\32\16"+
		"\2\u014c\u014d\3\2\2\2\u014d\u014e\b\16\1\2\u014e\u0156\3\2\2\2\u014f"+
		"\u0151\7!\2\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0152\u0153\5\20\t\2\u0153\u0154\b\16\1\2\u0154\u0156\3\2\2\2\u0155"+
		"\u013e\3\2\2\2\u0155\u0142\3\2\2\2\u0155\u0148\3\2\2\2\u0155\u0150\3\2"+
		"\2\2\u0156\u0163\3\2\2\2\u0157\u0158\f\7\2\2\u0158\u0159\7 \2\2\u0159"+
		"\u015a\5\32\16\b\u015a\u015b\b\16\1\2\u015b\u0162\3\2\2\2\u015c\u015d"+
		"\f\6\2\2\u015d\u015e\7!\2\2\u015e\u015f\5\32\16\7\u015f\u0160\b\16\1\2"+
		"\u0160\u0162\3\2\2\2\u0161\u0157\3\2\2\2\u0161\u015c\3\2\2\2\u0162\u0165"+
		"\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\33\3\2\2\2\u0165"+
		"\u0163\3\2\2\2\u0166\u0167\5\32\16\2\u0167\u0168\5 \21\2\u0168\u0169\b"+
		"\17\1\2\u0169\u0189\3\2\2\2\u016a\u016b\5\26\f\2\u016b\u016c\b\17\1\2"+
		"\u016c\u0189\3\2\2\2\u016d\u016e\7\24\2\2\u016e\u016f\5\32\16\2\u016f"+
		"\u0170\5 \21\2\u0170\u0171\b\17\1\2\u0171\u0189\3\2\2\2\u0172\u0173\7"+
		"\31\2\2\u0173\u0174\7\7\2\2\u0174\u0175\5 \21\2\u0175\u0176\b\17\1\2\u0176"+
		"\u0189\3\2\2\2\u0177\u0178\7\31\2\2\u0178\u0179\7\6\2\2\u0179\u017a\5"+
		" \21\2\u017a\u017b\b\17\1\2\u017b\u0189\3\2\2\2\u017c\u017d\5\32\16\2"+
		"\u017d\u017e\7\6\2\2\u017e\u017f\5 \21\2\u017f\u0180\b\17\1\2\u0180\u0189"+
		"\3\2\2\2\u0181\u0182\5\"\22\2\u0182\u0183\5 \21\2\u0183\u0184\b\17\1\2"+
		"\u0184\u0189\3\2\2\2\u0185\u0186\5 \21\2\u0186\u0187\b\17\1\2\u0187\u0189"+
		"\3\2\2\2\u0188\u0166\3\2\2\2\u0188\u016a\3\2\2\2\u0188\u016d\3\2\2\2\u0188"+
		"\u0172\3\2\2\2\u0188\u0177\3\2\2\2\u0188\u017c\3\2\2\2\u0188\u0181\3\2"+
		"\2\2\u0188\u0185\3\2\2\2\u0189\35\3\2\2\2\u018a\u018b\7\30\2\2\u018b\u018c"+
		"\7\31\2\2\u018c\u018d\5 \21\2\u018d\u018e\b\20\1\2\u018e\u01a4\3\2\2\2"+
		"\u018f\u0190\7\30\2\2\u0190\u0191\7\31\2\2\u0191\u0198\7\37\2\2\u0192"+
		"\u0193\5\32\16\2\u0193\u0194\b\20\1\2\u0194\u0199\3\2\2\2\u0195\u0196"+
		"\5\24\13\2\u0196\u0197\b\20\1\2\u0197\u0199\3\2\2\2\u0198\u0192\3\2\2"+
		"\2\u0198\u0195\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b\5 \21\2\u019b\u019c"+
		"\b\20\1\2\u019c\u01a4\3\2\2\2\u019d\u019e\7\30\2\2\u019e\u019f\7\31\2"+
		"\2\u019f\u01a0\7\7\2\2\u01a0\u01a1\5 \21\2\u01a1\u01a2\b\20\1\2\u01a2"+
		"\u01a4\3\2\2\2\u01a3\u018a\3\2\2\2\u01a3\u018f\3\2\2\2\u01a3\u019d\3\2"+
		"\2\2\u01a4\37\3\2\2\2\u01a5\u01a7\7\"\2\2\u01a6\u01a5\3\2\2\2\u01a7\u01aa"+
		"\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa"+
		"\u01a8\3\2\2\2\u01ab\u01ad\7#\2\2\u01ac\u01ab\3\2\2\2\u01ad\u01ae\3\2"+
		"\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af!\3\2\2\2\u01b0\u01b1"+
		"\7\31\2\2\u01b1\u01b2\7\37\2\2\u01b2\u01b3\5\32\16\2\u01b3\u01b4\b\22"+
		"\1\2\u01b4\u01bb\3\2\2\2\u01b5\u01b6\7\31\2\2\u01b6\u01b7\7\37\2\2\u01b7"+
		"\u01b8\5\24\13\2\u01b8\u01b9\b\22\1\2\u01b9\u01bb\3\2\2\2\u01ba\u01b0"+
		"\3\2\2\2\u01ba\u01b5\3\2\2\2\u01bb#\3\2\2\2*,\63;AJXgw\u0082\u0085\u008d"+
		"\u0091\u00a3\u00af\u00b1\u00c1\u00c5\u00d0\u00dc\u00ef\u00f3\u00f8\u0104"+
		"\u010c\u0115\u0122\u0126\u012a\u0136\u013a\u0150\u0155\u0161\u0163\u0188"+
		"\u0198\u01a3\u01a8\u01ae\u01ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}