/**
 * Define a grammar called FhirMapper
 */
 grammar FhirMapper;

 @header {
 package com.cognitive.utilities;
 }
// starting point for parsing a mapping file

 mappingUnit
 :
 	keyMap conceptMap* keyUses+ (keyImports)* group 
 ;

 keyMap
	:
	 	'map' structureMap '=' quotedString
	; 

 keyUses
	:
	 	'uses' structureDefinition 'as' ( 'source' | 'target' | 'queried' | 'produced' ) (';')?
	;

 keyImports
	:
	 	'imports' structureMap (';')?
	;

 group
	:
	 	'group' groupName ('extends' groupName)* (ruleInput)+ (ruleGroup)+ 'endgroup'
	;

 groupName
	:
	 	IDENTIFIER
	;

 ruleInput
	:  
	   'input' sourceVar ':' inputType 'as' ('source' | 'target' ) (';')?
	;
conceptMap 
    : 'conceptMap' '"#'	IDENTIFIER '{' (prefix)+ conceptMapping '}'
    ;
prefix
	:
	 	'prefix' conceptMappingVar '=' quotedUrl
	; 
conceptMappingVar
	:  IDENTIFIER
	;
conceptMapping
	:  conceptMappingVar ':' field
	   (('<=' | '=' | '==' | '!=' '>=' '>-' | '<-' | '~') conceptMappingVar ':' field) | '--'
	;
inputType
	: IDENTIFIER
	;

ruleGroup 
 	: ruleName ':' 'for' optional? ruleSource listOption?  
 	  ('as' sourceVarAlias)? (whereClause)? (checkClause)? 
 	  'make' ruleAssign  (',' ruleAssign)* 
 	;
ruleSource 
    :  ruleSourceVar | ruleType
    ;
ruleSourceVar 
	: sourceVar ('.' field)*
	;
ruleType 
    : '@' IDENTIFIER '.' field
    ;
optional 
	: '(' 'optional' ')'
	;
listOption 
    : '{' ('first' | 'last' | 'only_one') '}'
    ;

sourceVar 
	:  IDENTIFIER 
	;
inputVar 
	:  IDENTIFIER
	;
sourceVarAlias
	:
	IDENTIFIER
	;
transformVar
	:
	IDENTIFIER
	;
ruleAssign 
	:  targetVar  '=' tranformWithThen
	|  targetVar  '=' (text | sourceVarAlias | ('(' '"' sourceVar '"' ')')) 
	|  tranformWithThen
	|  (targetVar 'as' sourceVar)
	;
targetVar
	:  IDENTIFIER ('.' IDENTIFIER)* 
	;
field
	: IDENTIFIER
	;
sourceParam 
    : '"' IDENTIFIER '"'
    ;
targetParam 
    : '"' IDENTIFIER '"'
    ;
tranformWithThen
    : tranform ('as' transformVar)? thenClause?
    ;
tranform 
    : create | copy | truncate | escape | cast | append 
    | translate | reference | codeableConcept | coding 
    | quantity | text | quotedUrl
    ; 
create
    :  'create' '(' sourceParam ')'
    ;
copy
	:  'copy' '(' sourceParam ')' 
	;
truncate
	:  'truncate' '(' sourceParam ',' DIGITS ')' 
	;
escape
	:  'escape' '(' sourceParam (',' targetParam)* ')' 
	;
cast 
    : 'cast' '(' sourceParam (',' targetParam)? ')'
    ;
append
	:  'append' '(' sourceParam+ ')' 
	;
translate
	:  'translate' '(' sourceParam ',' structureMap ')' 
	;
reference
	:  'reference' '(' IDENTIFIER ')' 
	;
codeableConcept	
    : ('cc' || 'CC') '(' ( (system ',' (code (',' display )? | IDENTIFIER )) | text)  ')'
    ;
coding	
    : ('c' || 'C') '(' system ',' code (',' display )? ')'
    ;
quantity	
    : ('qty' || 'QTY') '(' ( ( value ',' unit (',' system ',' code)?) | text) ')'
    ;
ruleName 
	: IDENTIFIER ('.' IDENTIFIER)*
	;
system 
    : quotedUrl
    ;
code 
    : '"' IDENTIFIER ('.' IDENTIFIER )* '"'
    ;
display 
    : '"' IDENTIFIER '"'
    ;
text 
    : '"' IDENTIFIER '"'
    ;  
value 
    : '"' IDENTIFIER '"'
    ; 
unit 
    : '"' IDENTIFIER '"'
    ;  
structureMap 
	:	'"' url '"'
	;
checkClause 
    : 'check' fluentPath
    ;
whereClause 
    : 'where' fluentPath
    ;
fluentPath 
    : IDENTIFIER
    ;
thenClause 
    : 'then' '{' (ruleGroup)* '}'
    ;
 quotedUrl 
    : '"' url '"'
	;
 structureDefinition
	:  '"' url '"'
	;

url
   : authority '://' login? host (':' port)? ( path)? ('?' search)?
   ;

authority
   : IDENTIFIER
   ;

host
   : hostname
   | hostnumber
   ;

cellname
   : hostname
   ;

hostname
   : IDENTIFIER 
   ;

hostnumber
   : DIGITS '.' DIGITS '.' DIGITS '.' DIGITS
   ;

port
   : DIGITS
   ;

path
   : path_component+
   ;
path_component
   : '/' IDENTIFIER 
   ;
search
   : searchparameter ('&' searchparameter)*
   ;

searchparameter
    : STRING ('=' (STRING |DIGITS | HEX))?;

user
   : STRING
   ;

login
    : user ':' password '@'
    ;

password
   : STRING
   ;

IDENTIFIER 
   : 'source' | 'target' | ( LETTER | DIGIT | '_' | '-' | '.')+  // adding the key words to IDENTIFIER
   ;
fragmentid
   : STRING
   ;
ALPHA_NUMERIC
   : (LETTER | DIGIT)+
   ;
HEX
    : ('%' [a-fA-F0-9] [a-fA-F0-9])+
    ;

STRING
   : (ALPHA_NUMERIC |HEX) ([a-zA-Z0-9] | HEX)*
   ;
LETTER 
    : [a-zA-Z]
    ;

DIGITS
   : [0-9] +
   ;
DIGIT
   : [0-9]
   ;
quotedString
	:  '"' .*? '"'
	; // Match double quoted strings
WS
	: [ \t\n\r]+ -> channel(HIDDEN)
	;

LINE_COMMENT
	: '//' ~[\r\n]* -> skip
	;