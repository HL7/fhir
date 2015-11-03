grammar fhirpath;

path : part ('.' part)* ;
part: element recurse? | axis_child  | axis_reset | function;

element: ID CHOICE?;
function: ID '(' params? ')';
params: param (',' param)*;
param: expr;

expr: atom (comp atom)*;
comp: '=' | '!=' | '>' | '<' | '<=' | '>=' | 'in';
atom: path | NUMBER;

recurse: '*';

axis_child: '*' | '**';
axis_reset: '$';

CHOICE: '[x]';
ID: [a-zA-Z]+;
DIGIT: ('0'..'9');
NUMBER: '-'? DIGIT+ ('.' DIGIT+)?;

WS: [ \r\n\t]+ -> skip;