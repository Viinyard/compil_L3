grammar JavaLikeToTemplate;

options { 
    output = template;
}

@members { 
    private java.util.ArrayList<String> globals = new java.util.ArrayList<String>();

}

compilationUnit : class_def EOF 
                    -> compilationUnit(classDef={$class_def.st}, globals={globals});
class_def       : CLASS ID LCUR class_body RCUR
                    -> class(name={$ID.text}, body={$class_body.st});
class_body      : (t+=class_element)+
                    -> append(parts={$t});
class_element   : class_field
                    -> {$class_field.st}
                | class_method
                    -> {$class_method.st};
class_field     : VAR ID SEMI {globals.add($ID.text);}
                    -> classField(name={$ID.text});
class_method    : VAR ID LPAR paramlist? RPAR LCUR method_body RCUR
                    -> classMethod(name={$ID.text}, params={$paramlist.st}, body={$method_body.st});
method_body     : (t+=method_element)+
                    -> append(parts={$t});
method_element  : method_field
                    -> {$method_field.st};
method_field    : VAR ID SEMI {globals.add($ID.text);}
                    -> methodField(name={$ID.text});
paramlist       : VAR t+=ID (COMMA VAR t+=ID)*
                    -> paramList(params={$t});

CLASS   : 'class';
VAR     : 'var';
ID      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
INT     : ('0'..'9')+;
COMMA   : ',';
SEMI    : ';';
LCUR    : '{';
RCUR    : '}';
LPAR    : '(';
RPAR    : ')';
EQ      : '=';
WS      : (' '|'\t'|'\f'|'\r'|'\n'){skip();};