grammar fhirpath;

// Grammar rules

expr:
        expr '.' method_invocation |
        '-' expr |
        '!' expr |
        expr ('*' | '/') expr |
        expr ('+' | '-') expr |
        expr '|' expr |
        expr '&' expr |
        expr COMP expr |
        expr LOGIC expr |
        '(' expr ')' |
        path |
        const;

path : part ('.' part)* ;
part: ID CHOICE? recurse? | method_invocation | axis_spec;

method_invocation: ID '(' param_list? ')';
param_list: param (',' param)*;
param: expr;


const: STRING |
       NUMBER |
       BOOL |
       CONST;

recurse: '*';

axis_spec: '*' | '**' | '$context' | '$resource' | '$parent' ;


// Lexical rules

LOGIC: 'and' | 'or' | 'xor';
COMP: '=' | '==' | '!=' | '!==' | '>' | '<' | '<=' | '>=' | 'in';
BOOL: 'true' | 'false';

CONST: '%'[a-zA-Z0-9\-.]+;

STRING: '"' (ESC | ~["\\])* '"' |           // " delineated string
        '\'' (ESC | ~[\'\\])* '\'';         // ' delineated string

fragment ESC: '\\' (["'\\/bfnrt] | UNICODE);    // allow \", \', \\, \/, \b, etc. and \uXXX
fragment UNICODE: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

NUMBER: '-'? INT '.' [0-9]+ EXP? |
        '-'? INT EXP |
        '-'? INT;

fragment INT: '0' | [1-9][0-9]*;
fragment EXP: [Ee] [+\-]? INT;

CHOICE: '[x]';
ID: [a-zA-Z]+ ;

WS: [ \r\n\t]+ -> skip;