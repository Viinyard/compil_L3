// Generated from Calculette.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'"
	};
	public static final String[] ruleNames = {
		"T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", 
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "BOOL", 
		"RETURN", "IF", "ELSE", "WHILE", "TYPE", "IDENTIFIANT", "FLOAT", "INT", 
		"OPE_REL", "EQUAL", "DIFF", "INF", "SUP", "INFEQ", "SUPEQ", "PO", "PF", 
		"EQ", "P1OPE", "P2OPE", "SEMICOLON", "NEWLINE", "COM", "WS", "UNMATCH"
	};


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


	public CalculetteLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0121\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\5\22\u009e\n\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u00c1\n\27\3\30\6\30\u00c4\n\30\r\30\16\30\u00c5\3\30\7\30\u00c9\n\30"+
		"\f\30\16\30\u00cc\13\30\3\31\6\31\u00cf\n\31\r\31\16\31\u00d0\3\31\3\31"+
		"\7\31\u00d5\n\31\f\31\16\31\u00d8\13\31\3\32\6\32\u00db\n\32\r\32\16\32"+
		"\u00dc\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00e5\n\33\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\5\35\u00ee\n\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!"+
		"\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\5(\u0107\n(\3(\3(\3)\3"+
		")\7)\u010d\n)\f)\16)\u0110\13)\3)\5)\u0113\n)\3)\3)\3*\6*\u0118\n*\r*"+
		"\16*\u0119\3*\3*\3+\3+\3+\3+\2\2,\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\29\2;\2=\2?\2A\2C\35E\36G\37I K!M\"O#Q$S%U&\3\2"+
		"\b\5\2C\\aac|\6\2\62;C\\aac|\4\2,,\61\61\4\2--//\4\2\f\f\17\17\4\2\13"+
		"\13\"\"\u012c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2C\3\2\2\2\2E\3\2"+
		"\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2"+
		"\2S\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5[\3\2\2\2\7`\3\2\2\2\tb\3\2\2\2\13"+
		"e\3\2\2\2\rh\3\2\2\2\17j\3\2\2\2\21n\3\2\2\2\23t\3\2\2\2\25w\3\2\2\2\27"+
		"z\3\2\2\2\31~\3\2\2\2\33\u0085\3\2\2\2\35\u0088\3\2\2\2\37\u008c\3\2\2"+
		"\2!\u008e\3\2\2\2#\u009d\3\2\2\2%\u009f\3\2\2\2\'\u00a6\3\2\2\2)\u00a9"+
		"\3\2\2\2+\u00ae\3\2\2\2-\u00c0\3\2\2\2/\u00c3\3\2\2\2\61\u00ce\3\2\2\2"+
		"\63\u00da\3\2\2\2\65\u00e4\3\2\2\2\67\u00e6\3\2\2\29\u00ed\3\2\2\2;\u00ef"+
		"\3\2\2\2=\u00f1\3\2\2\2?\u00f3\3\2\2\2A\u00f6\3\2\2\2C\u00f9\3\2\2\2E"+
		"\u00fb\3\2\2\2G\u00fd\3\2\2\2I\u00ff\3\2\2\2K\u0101\3\2\2\2M\u0103\3\2"+
		"\2\2O\u0106\3\2\2\2Q\u010a\3\2\2\2S\u0117\3\2\2\2U\u011d\3\2\2\2WX\7p"+
		"\2\2XY\7q\2\2YZ\7p\2\2Z\4\3\2\2\2[\\\7v\2\2\\]\7t\2\2]^\7w\2\2^_\7g\2"+
		"\2_\6\3\2\2\2`a\7}\2\2a\b\3\2\2\2bc\7@\2\2cd\7@\2\2d\n\3\2\2\2ef\7>\2"+
		"\2fg\7>\2\2g\f\3\2\2\2hi\7\177\2\2i\16\3\2\2\2jk\7h\2\2kl\7q\2\2lm\7t"+
		"\2\2m\20\3\2\2\2no\7w\2\2op\7p\2\2pq\7v\2\2qr\7k\2\2rs\7n\2\2s\22\3\2"+
		"\2\2tu\7q\2\2uv\7t\2\2v\24\3\2\2\2wx\7q\2\2xy\7w\2\2y\26\3\2\2\2z{\7c"+
		"\2\2{|\7p\2\2|}\7f\2\2}\30\3\2\2\2~\177\7t\2\2\177\u0080\7g\2\2\u0080"+
		"\u0081\7r\2\2\u0081\u0082\7g\2\2\u0082\u0083\7c\2\2\u0083\u0084\7v\2\2"+
		"\u0084\32\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7v\2\2\u0087\34\3\2\2"+
		"\2\u0088\u0089\7p\2\2\u0089\u008a\7q\2\2\u008a\u008b\7v\2\2\u008b\36\3"+
		"\2\2\2\u008c\u008d\7.\2\2\u008d \3\2\2\2\u008e\u008f\7h\2\2\u008f\u0090"+
		"\7c\2\2\u0090\u0091\7n\2\2\u0091\u0092\7u\2\2\u0092\u0093\7g\2\2\u0093"+
		"\"\3\2\2\2\u0094\u0095\7v\2\2\u0095\u0096\7t\2\2\u0096\u0097\7w\2\2\u0097"+
		"\u009e\7g\2\2\u0098\u0099\7h\2\2\u0099\u009a\7c\2\2\u009a\u009b\7n\2\2"+
		"\u009b\u009c\7u\2\2\u009c\u009e\7g\2\2\u009d\u0094\3\2\2\2\u009d\u0098"+
		"\3\2\2\2\u009e$\3\2\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2"+
		"\7v\2\2\u00a2\u00a3\7w\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7p\2\2\u00a5"+
		"&\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7h\2\2\u00a8(\3\2\2\2\u00a9\u00aa"+
		"\7g\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7g\2\2\u00ad"+
		"*\3\2\2\2\u00ae\u00af\7y\2\2\u00af\u00b0\7j\2\2\u00b0\u00b1\7k\2\2\u00b1"+
		"\u00b2\7n\2\2\u00b2\u00b3\7g\2\2\u00b3,\3\2\2\2\u00b4\u00b5\7k\2\2\u00b5"+
		"\u00b6\7p\2\2\u00b6\u00c1\7v\2\2\u00b7\u00b8\7h\2\2\u00b8\u00b9\7n\2\2"+
		"\u00b9\u00ba\7q\2\2\u00ba\u00bb\7c\2\2\u00bb\u00c1\7v\2\2\u00bc\u00bd"+
		"\7d\2\2\u00bd\u00be\7q\2\2\u00be\u00bf\7q\2\2\u00bf\u00c1\7n\2\2\u00c0"+
		"\u00b4\3\2\2\2\u00c0\u00b7\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c1.\3\2\2\2"+
		"\u00c2\u00c4\t\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00ca\3\2\2\2\u00c7\u00c9\t\3\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\60\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00cf\4\62;\2\u00ce\u00cd"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d6\7\60\2\2\u00d3\u00d5\4\62;\2\u00d4\u00d3\3"+
		"\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\62\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00db\4\62;\2\u00da\u00d9\3\2\2"+
		"\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\64"+
		"\3\2\2\2\u00de\u00e5\5\67\34\2\u00df\u00e5\59\35\2\u00e0\u00e5\5;\36\2"+
		"\u00e1\u00e5\5=\37\2\u00e2\u00e5\5? \2\u00e3\u00e5\5A!\2\u00e4\u00de\3"+
		"\2\2\2\u00e4\u00df\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\66\3\2\2\2\u00e6\u00e7\7?\2\2"+
		"\u00e7\u00e8\7?\2\2\u00e88\3\2\2\2\u00e9\u00ea\7#\2\2\u00ea\u00ee\7?\2"+
		"\2\u00eb\u00ec\7>\2\2\u00ec\u00ee\7@\2\2\u00ed\u00e9\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ee:\3\2\2\2\u00ef\u00f0\7>\2\2\u00f0<\3\2\2\2\u00f1\u00f2"+
		"\7@\2\2\u00f2>\3\2\2\2\u00f3\u00f4\7>\2\2\u00f4\u00f5\7?\2\2\u00f5@\3"+
		"\2\2\2\u00f6\u00f7\7@\2\2\u00f7\u00f8\7?\2\2\u00f8B\3\2\2\2\u00f9\u00fa"+
		"\7*\2\2\u00faD\3\2\2\2\u00fb\u00fc\7+\2\2\u00fcF\3\2\2\2\u00fd\u00fe\7"+
		"?\2\2\u00feH\3\2\2\2\u00ff\u0100\t\4\2\2\u0100J\3\2\2\2\u0101\u0102\t"+
		"\5\2\2\u0102L\3\2\2\2\u0103\u0104\7=\2\2\u0104N\3\2\2\2\u0105\u0107\7"+
		"\17\2\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u0109\7\f\2\2\u0109P\3\2\2\2\u010a\u010e\7%\2\2\u010b\u010d\n\6\2\2\u010c"+
		"\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0113\5O(\2\u0112\u0111"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\b)\2\2\u0115"+
		"R\3\2\2\2\u0116\u0118\t\7\2\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2"+
		"\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c"+
		"\b*\2\2\u011cT\3\2\2\2\u011d\u011e\13\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0120\b+\2\2\u0120V\3\2\2\2\20\2\u009d\u00c0\u00c5\u00ca\u00d0\u00d6"+
		"\u00dc\u00e4\u00ed\u0106\u010e\u0112\u0119\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}