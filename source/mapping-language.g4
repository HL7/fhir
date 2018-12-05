/**
 * Define a grammar called FhirMapper
 */
 grammar MappingLanguage;

// starting point for parsing a mapping file
// in case we need nested ConceptMaps, we need to have this rule:
// structureMap : mapId conceptMap* structure* imports* group+

structureMap
    : mapId structure* imports* group+
    ;

mapId
	: 'map' url '=' QUOTEDSTRING
	;

url
    : QUOTEDIDENTIFIER
    ;

identifier
    : IDENTIFIER
    ;

structure
	: 'uses' url structureAlias? 'as'  modelMode
	;

structureAlias
    : 'alias' identifier
    ;

imports
	: 'imports' url
	;

group
	: 'group' identifier parameters extends? typeMode? rules
	;

rules
    : '{' rule+ '}'
    ;

typeMode
    : '<<' groupTypeMode '>>'
    ;

extends
    : 'extends' identifier
    ;

parameters
    : '(' parameter (',' parameter)+ ')'
    ;

parameter
    : inputMode identifier (':' type)?
	;

type
    : ':' identifier;

rule
 	: (ruleName ':')? ruleSources ruleTargets? dependent?
 	;

ruleSources
    : 'for' ruleSource (',' ruleSource)*
    ;

ruleSource
    :  ruleContext sourceType? sourceDefault? sourceListMode? alias? whereClause? checkClause? log?
    ;

sourceType
    : ':' identifier (INTEGER? '..' INTEGER?)?
    ;

ruleContext
	: identifier ('.' identifier)?
	;

sourceDefault
    : 'default' literal       // Spec says nothing about what's allowed - what about complex types?
    ;

literal
    : INTEGER
    | NUMBER
    | STRING
    | DATETIME
    | TIME
    | BOOL
    ;

alias
	: 'as' identifier
	;

whereClause
    : 'where' fhirPath
    ;

checkClause
    : 'check' fhirPath
    ;

log
    : 'log' fhirPath
    ;

dependent
    : 'then' (invocation | dependentRule)
    ;

dependentRule
    : '{' rule+ '}'
    ;

ruleTargets
    : 'make' ruleTarget (',' ruleTarget)* targetListSpec?
    ;

targetListSpec
    : '{' targetListMode '}'        // seen in examples, but unnecessary & inconsistent with sourceListMode
    | targetListMode
    ;



ruleTarget
    : ruleContext ('=' transform)? alias?
    | invocation alias
    ;

transform
    : literal           // trivial constant transform
    | ruleContext       // 'copy' transform
    | invocation        // other named transforms
    ;

invocation
    : identifier '(' paramList? ')'
    ;

paramList
    : param (',' param)*
    ;

param
    : literal
    | identifier
    ;


ruleName
	: identifier
	;

fhirPath
    : IDENTIFIER
    ;

groupTypeMode
    : 'types' | 'type+'
    ;

sourceListMode
    : 'first' | 'not_first' | 'last' | 'not_last' | 'only_one'
    ;

targetListMode
   : 'first' | 'share' | 'last' | 'collate'
   ;

inputMode
   : 'source' | 'target'
   ;

modelMode           // StructureMapModelMode binding
    : 'source' | 'queried' | 'target' | 'produced'
    ;



    /*
     * Syntax for embedded ConceptMaps excluded for now
     *
    conceptMap
        : 'conceptMap' '"#'	IDENTIFIER '{' (prefix)+ conceptMapping '}'
        ;

    prefix
    	: 'prefix' conceptMappingVar '=' URL
    	;

    conceptMappingVar
    	:  IDENTIFIER
    	;
    conceptMapping
    	:  conceptMappingVar ':' field
    	   (('<=' | '=' | '==' | '!=' '>=' '>-' | '<-' | '~') conceptMappingVar ':' field) | '--'
    	;
    */




/****************************************************************
    Lexical rules from FhirPath
*****************************************************************/

BOOL
        : 'true'
        | 'false'
        ;

DATETIME
        : '@'
            [0-9][0-9][0-9][0-9] // year
            (
                '-'[0-9][0-9] // month
                (
                    '-'[0-9][0-9] // day
                    (
                        'T' TIMEFORMAT
                    )?
                 )?
             )?
             'Z'? // UTC specifier
        ;

TIME
        : '@' 'T' TIMEFORMAT
        ;

fragment TIMEFORMAT
        :
            [0-9][0-9] (':'[0-9][0-9] (':'[0-9][0-9] ('.'[0-9]+)?)?)?
            ('Z' | ('+' | '-') [0-9][0-9]':'[0-9][0-9])? // timezone
        ;

IDENTIFIER
        : ([A-Za-z])([A-Za-z0-9] | '_' | '-')*
        ;

QUOTEDIDENTIFIER
        : '"' (ESC | .)*? '"'
        ;

STRING
        : '\'' (ESC | .)*? '\''
        ;

QUOTEDSTRING
    : QUOTEDIDENTIFIER
    ;

URL
    : QUOTEDIDENTIFIER
    ;

INTEGER
    : [0-9]+
    ;
// Also allows leading zeroes now (just like CQL and XSD)
NUMBER
    : INTEGER ('.' [0-9]+)?
    ;

// Pipe whitespace to the HIDDEN channel to support retrieving source text through the parser.
WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

COMMENT
        : '/*' .*? '*/' -> channel(HIDDEN)
        ;

LINE_COMMENT
        : '//' ~[\r\n]* -> channel(HIDDEN)
        ;

fragment ESC
        : '\\' (["'\\/fnrt] | UNICODE)    // allow \", \', \\, \/, \f, etc. and \uXXX
        ;

fragment UNICODE
        : 'u' HEX HEX HEX HEX
        ;

fragment HEX
        : [0-9a-fA-F]
        ;
