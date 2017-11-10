grammar Calculette;

@members {
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
}

start
    : calcul EOF;

calcul returns [ String code ] 
        @init {
                $code = new String();
            }
        @after {
                System.out.println($code);
            }
    :
        (declaration { $code += $declaration.code; })*        
        { int entry = nextLabel(); $code += "JUMP " + entry + "\n"; }
        NEWLINE*
        
        (fonction { $code += $fonction.code; })* 
        NEWLINE*
        
        { $code += "LABEL " + entry + "\n"; }
        (instruction { $code += $instruction.code; })* { 
                $code += pop(tablesSymboles.getTaille()) + N
                        + "HALT";
            }
    ;

priority0Expression returns [String code, String type] :
    PO expression PF {
        $code = $expression.code;
        $type = $expression.type;
    }
;

fonction returns [ String code ]
    : (TYPE IDENTIFIANT '('  params ? ')' 
        { 
            int lab = nextLabel();
            tablesSymboles.nouvelleFonction($IDENTIFIANT.text, lab, $TYPE.text);
            $code = "LABEL " + lab + N;
        }
        bloc 
        {
            $code += $bloc.code;
        }) {
            $code += "RETURN" + N;
        }
    ;

bloc returns [String code] @init { $code = new String(); } :
    '{'  (instruction { $code += $instruction.code; })* '}' finInstruction {

        tablesSymboles.dropTableLocale();
        
    }
;

params
    : TYPE IDENTIFIANT 
        { 
            tablesSymboles.newTableLocale();
            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        }  
        ( ',' TYPE IDENTIFIANT 
            { 
                tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
            } 
        )*
    ;

 // init nécessaire à cause du ? final et donc args peut être vide (mais $args sera non null) 
args returns [ String code, int size] @init{ $code = new String(); $size = 0; }
    : ( expression 
    { 
        $code += $expression.code;
        $size += AdresseType.getSize($expression.type);

    }
    ( ',' expression 
    { 
        $code += $expression.code;
        $size += AdresseType.getSize($expression.type);
    } 
    )* 
      )? 
    ;

atome returns [String code, String type] :
        INT {
            $code = "PUSHI " + $INT.text + N;
            $type = "int";
        }
    |   FLOAT {
            $code = "PUSHF " + $FLOAT.text + N;
            $type = "float";
        }
    |
        IDENTIFIANT {
            $code = pusher(tablesSymboles.getAdresseType($IDENTIFIANT.text));
            $type = tablesSymboles.getAdresseType($IDENTIFIANT.text).type;
        }
;

signed returns [String code] :
    (P2OPE {
        $code = getSignedCode($P2OPE.text);
    })?
;

condition returns [String code, String type] :
        IDENTIFIANT {
            $code = pusher(tablesSymboles.getAdresseType($IDENTIFIANT.text));
            $type = tablesSymboles.getAdresseType($IDENTIFIANT.text).type;
            if(!$type.equals("bool")) {
                throw new IllegalArgumentException("Type mismatch: cannot convert from "+$type+" to bool");
            }
        }
    |
        'true'  { 
            $code = "PUSHI 1" + N;
            $type = "bool";
        }
    | 
        'false' { 
            $code = "PUSHI 0" + N;
            $type = "bool";
        }
    |
        ('not' | 'non') c=condition {
            $code = $c.code + $c.code + "NEQ" + N;
            $type = "bool";
        }
    |
        c1=condition ('and' | 'et') c2=condition {
            $code = $c1.code + $c2.code + "MUL" + N;
            $type = "bool";
        }
    |
        c1=condition ('or' | 'ou') c2=condition {
            $code = $c1.code + $c2.code + "ADD" + N;
            $type = "bool";
        }
    | exp1=expression OPE_REL exp2=expression {
            $code = $exp1.code + $exp2.code + opeLog($OPE_REL.text, getType($exp1.type, $exp2.type)) + N;
            $type = "bool";
        }
    ;


boucle returns [String code, String codeInstru, String elseCode, String codeDec, String codeCond, String codeAssign] @init { $codeAssign = new String(); $codeInstru = new String(); $codeCond = new String(); $elseCode = new String(); }:
    WHILE PO condition PF (instruction { $codeInstru += $instruction.code; } | '{' (instruction { $codeInstru += $instruction.code; })* '}') {
       // tablesSymboles.newTableLocale();
        $code = "LABEL " + nextLabel() + N;
        $code += $condition.code;
        $code += "JUMPF " + _label + N;
        $code += $codeInstru;
        $code += "JUMP " + (_label - 1) + N;
        $code += "LABEL " + nextLabel() + N;
       // tablesSymboles.dropTableLocale();
    }
    |

    'repeat' '{' (instruction { $codeInstru += $instruction.code; })* '}' finInstruction 'until' PO (condition { $codeCond += $condition.code; })* PF {
       // tablesSymboles.newTableLocale();
        int labelDebutWhile = nextLabel();
        int labelFinWhile = nextLabel();
        $code = "LABEL " + labelDebutWhile + N;
        $code += $codeInstru;
        $code += $condition.code;
        $code += "JUMPF " + labelFinWhile + N;
         $code += "JUMP " + labelDebutWhile + N;
        $code += "LABEL " + labelFinWhile + N;
       // tablesSymboles.dropTableLocale();
    }

    |

    IF PO condition PF (instruction { $codeInstru += $instruction.code; } | '{' (instruction { $codeInstru += $instruction.code; })* '}') (boucleElse { $elseCode = $boucleElse.code; })? {
       // tablesSymboles.newTableLocale();

        int labelElse = nextLabel();
        int labelIf = nextLabel();

        $code = $condition.code;
        $code += "JUMPF " + labelElse + N;
        $code += $codeInstru;
        $code += "JUMP " + labelIf + N;
        $code += "LABEL " + labelElse + N;
        $code += $elseCode;
        $code += "LABEL " + labelIf + N;

        //tablesSymboles.dropTableLocale();
    }
    |
        'for' PO (declaration { $codeDec = $declaration.code; } | assignation { $codeDec = $assignation.code; }) ';' (condition { $codeCond += $condition.code; })* ';' (assignation { $codeAssign += $assignation.code; })* PF (instruction { $codeInstru += $instruction.code; } | '{' (instruction { $codeInstru += $instruction.code; })* '}') {
           // tablesSymboles.newTableLocale();
            $code = $codeDec;
            int labelDebutFor = nextLabel();
            int labelFinFor = nextLabel();
            $code += "LABEL " + labelDebutFor + N;
            $code += $codeCond;
            $code += "JUMPF " + labelFinFor + N;
            $code += $codeInstru;
            $code += $codeAssign;
            $code += "JUMP " + labelDebutFor + N;
            $code += "LABEL " + labelFinFor + N;

           // tablesSymboles.dropTableLocale();
        }
;

boucleElse returns [String code, String codeInstru] @init { $codeInstru = new String(); } :
    ELSE (instruction { $codeInstru += $instruction.code; } | '{' (instruction { $codeInstru += $instruction.code; })* '}') {
        $code = $codeInstru;
    }
;

expression returns [String code, String type] : 

    
    priority0Expression { 
            $code = $priority0Expression.code;
            $type = $priority0Expression.type;
        }
    | exp1=expression P1OPE exp2=expression {
            $code = $exp1.code + converter($exp1.type, $exp2.type) + $exp2.code + converter($exp2.type, $exp1.type) + ope($P1OPE.text, $exp1.type, $exp2.type);
            $type= getType($exp1.type, $exp2.type);
        }
    |  exp1=expression P2OPE exp2=expression {
            $code = $exp1.code + converter($exp1.type, $exp2.type) + $exp2.code + converter($exp2.type, $exp1.type) + ope($P2OPE.text, $exp1.type, $exp2.type);
            $type = getType($exp1.type, $exp2.type);
        }
    |
        IDENTIFIANT '(' args ')'                  // appel de fonction  
        {  
            
            $code = reterner(tablesSymboles.getFonction($IDENTIFIANT.text).type);
            $code += $args.code;
            $code += "CALL " + tablesSymboles.getFonction($IDENTIFIANT.text).adresse + N;
            $type = tablesSymboles.getFonction($IDENTIFIANT.text).type;
            $code += pop($args.size) + N;
        }
    |
    (PO TYPE PF exp1=expression) {
            $code = $exp1.code;
            if("float".equals($TYPE.text) && ("int".equals($exp1.type) || "bool".equals($exp1.type))) {
                $code += "ITOF" + N;
            } else if("int".equals($TYPE.text) && "float".equals($exp1.type)) {
                $code += "FTOI" + N;
            } else if("bool".equals($TYPE.text) && "float".equals($exp1.type)) {
                $code += "PUSHF 0." + N + "FNEQ" + N;
            } else if("bool".equals($TYPE.text) && "int".equals($exp1.type)) {
                $code += "PUSHI 0." + N + "NEQ" + N;
            }
            $type = $TYPE.text;
        }
    | P2OPE? atome {
            $code = $atome.code + signer($P2OPE.text, $atome.type) ;
            $type = $atome.type;
        }
   ;

instruction returns [ String code ] 
    : expression finInstruction 
        { 
            $code = $expression.code;
        }
    |
        boucle {
            $code = $boucle.code;
        }
    |
     RETURN expression finInstruction    
        {
            $code = $expression.code;
            $code += storerL($expression.type);
            $code += "RETURN " + N;
        }
    | IDENTIFIANT '<<' finInstruction {
            $code = reader(tablesSymboles.getAdresseType($IDENTIFIANT.text)) + storer(tablesSymboles.getAdresseType($IDENTIFIANT.text));
            tablesSymboles.getAdresseType($IDENTIFIANT.text);
        }
    | IDENTIFIANT '>>' finInstruction {
            $code = pusher(tablesSymboles.getAdresseType($IDENTIFIANT.text)) + writer(tablesSymboles.getAdresseType($IDENTIFIANT.text).type) + poper(tablesSymboles.getAdresseType($IDENTIFIANT.text));
        }
    | expression '>>' finInstruction {
            $code = $expression.code + writer($expression.type) + pop(AdresseType.getSize($expression.type));
        }
    | assignation finInstruction
        { 
            $code = $assignation.code;
        }
    | finInstruction
        {
            $code="";
        }
    ;

declaration returns [ String code ] @init { $code = new String(); }
    :
        TYPE IDENTIFIANT finInstruction
        {
            $code = push($TYPE.text);
            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        }
    |
        TYPE IDENTIFIANT EQ (expression { $code = $expression.code; } | condition { $code = $condition.code; }) finInstruction{
            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        }
    |
        TYPE IDENTIFIANT '<<' finInstruction {
            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
            $code = reader(tablesSymboles.getAdresseType($IDENTIFIANT.text));
        }

    ;

finInstruction :
    ';'* NEWLINE+ 
;



assignation returns [ String code ] 
    : IDENTIFIANT EQ expression
        {  
            $code = $expression.code + assigner($IDENTIFIANT.text, $expression.type);
        }
    |
        IDENTIFIANT EQ condition {
            $code = $condition.code + assigner($IDENTIFIANT.text, $condition.type);
        }
    ;

// LEXER
BOOL : 'true' | 'false';
RETURN: 'return';
IF : 'if';
ELSE : 'else';
WHILE : 'while';

TYPE : 'int' | 'float' | 'bool';

IDENTIFIANT : ('a'..'z' | 'A'..'Z' | '_')+ ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')* ;

FLOAT : (('0'..'9')+ '.' ('0'..'9')*);

INT : ('0'..'9')+ ;

OPE_REL : EQUAL | DIFF | INF | SUP | INFEQ | SUPEQ;

fragment EQUAL : '==';
fragment DIFF : '!=' | '<>';
fragment INF : '<';
fragment SUP : '>';
fragment INFEQ : '<=';
fragment SUPEQ : '>=';

PO : '(';

PF : ')';

EQ : '=';

P1OPE : '*' | '/';

P2OPE : '+' | '-';

SEMICOLON: ';';

NEWLINE : '\r'? '\n';

COM : '#'  ~([\r\n])* NEWLINE? -> skip;

WS :   (' '|'\t')+ -> skip  ;

UNMATCH : . -> skip ;