FHIR Path
==========

FHIRPath is a path based extraction language, somewhat like XPath. It is optimized to work on FHIR resources. Operations are expressed in terms of the logical content of the resources, rather than their XML or JSON reprsentation. The expressions can (in theory) be converted to XPath, JSON or OCL equivalents

All FHIRPath operations result in a collection of Elements of various
types. When the expression begins evaluating, there is a collection with one element in focus. 

1. Usage
----------

FHIR Path is used in 5 places within the FHIR specifications
- search parameter paths - used to define what contents the parameter refers to 
- slicing discriminator - used to indicate what element(s) define uniqueness
- invariants in ElementDefinition, used to apply co-occurance and other rules to the contents 
- error message locations in OperationOutcome
- URL templates in Smart on FHIR's cds-hooks

Implementations may find other uses for this as well

Some features are only allowed in invariants - either because they are hard to implement, or because they make constraints on the contexts in which the path statements can be used.

2. Path selection
-----------------

The first fundamental operation is to select a set of elements by their path:

	path.subPath.subPath - select all the elements on the path
  
e.g. To select all the phone numbers for a patient

	telecom.value

when the focus is "Patient". 

Paths and polymorphic items:

	path.value[x].subPath - all kinds of value	
	path.valueQuantity.subPath - only values that are quantity
 
### Special paths

	\* - any child
	\*\* - any descendent
	name* - recursive uses of the element 
	$context - the original context (see below for usage)
	$resource - the original container resource (e.g. skip contained resources, but do not go past a root resource into a bundle, if it is contained in a bundle)
	$parent - the element that contains $contex

Note: $resource and $parent are only allowed in invariants
 
There is a special case around the entry point, where the type of the entry point can be represented, but is optional. To illustrate this point, take the path 

	telecom(use = 'phone').value

This can be evaluated as an expression on a Patient resource, or other kind of resources. However, for natural human use, expressions are often prefixed with the name of the context in which they are used:

	Patient.telecom(use = 'phone').value
  
These 2 expressions have the same outcome, but when evaluating the second, the evaluation will only produce results when used on a Patient resource.

3. FHIR Types
-------------

FHIR Paths are always run against an Element or a Resource, and always produce collections of Elements or Resources. All elements in a collection will always have a FHIR type. The evaluation engine will automatically elevate constants to the right FHIR type. 

To illustrate this point, take the following path expression: 

	Patient.name.text

This produces a collection of elements with the type "string", 
as defined by the FHIR data types. Literal values such as

```
  "test string"
  0
  0.1
  true
  false
```

are automatically converted to the appropriate FHIR types (string, integer, decimal, and boolean respectively). 

Note: string constants are surrounded by either " or ', and use json type escaping internally. String constants are the only place that non-ascii characters are allowed in the expression. Unicode characters may, but do not need to be, escaped using the \u syntax.

4. Boolean evaluations
----------------------

Collections can be evaluated.ofType(boolean)s in logical tests in criteria. When a collection is implicited converted to a boolean then:

* if it has a single item that is a boolean:
  - it has the value of the boolean
* if it is empty
  - it is false
* else 
  - it is true
 
Note that collections never contain null objects 

This same principle applies when using the path statement in invariants.

5. Functions
-------------------------

In addition to selecting subelements, functions can be performed on the list. Functions are names that are followed by a () with zero or more parameters, separated by ','

As an example:
  
	telecom.where(use = 'home').value.empty()

This returns a collection of a single boolean element that contains true if there is no home telephone numbers. 

The parameters are expressions that are evaluated with respect to the collection that the operation applies to, which can be modified by the special paths given in section 2.

The following operations are defined:

### .empty()
true if the collection is empty

### .not()
Returns the opposite of the boolean evaluation of a collection

### .where(criteria)
Filter the collection to only those elements that meet the stated criteria expression. Expressions are evaluated with respect to the elements in the context. If the criteria are true, they are included in the result collection.
  
### .all(criteria)
true if all items in the collection meet the criteria (also true if the collection is empty). The criteria are evaluated for each item in the collection.

### .any(criteria)
true if any items in the collection meet the criteria (and false if the collection is empty). The criteria is evaluated for each item in the collection.

### .item(index)
Returns the indexth item in the collection (0 based index)

### .first()
Returns a collection containing the first item in the list. Equivalent to .item(0)

### .last()
Returns a collection containing the last item in the list 

### .tail()
Returns a collection containing all but the first item in the list 

### .skip(num)
Returns a collection containing all but the first num items in the list

### .take(num)
Returns a collection containing the first num items in the list

### .substring(start[,length])
Returns a part of the string.

### .count()
Returns a collection with a single value which is the integer count of the collection

### .asInteger()
Converts a string to an integer (empty collection if it's not a proper integer)

### .startsWith()
Filters the list to only include elements with a string value that starts with the specified content. Note that only primitive elements have a string representation

### .length()
Returns the length of characters used to represent the value (primitive types only) (does not include syntactical escapes). Returns the longest item in the collection
Note that only primitive elements have a string representations

### .matches(regex)
Returns a boolean for whether all the items in the collection match the given regex (which is a string - usually a constant)

Note that only primitive elements have a string representation

### .contains(string)
A simpler variation of matches that returns a boolean for a matching character sequence

### .distinct(path,path)
Returns true if all the elements in the list are distinct when using the relative paths (simple paths only with no functions). If the elements in the list are primitives, this can be used with no paths (e.g. .distinct())

### .resolve()
for each item in the collection, if it is a Reference, locate the target of the reference, and add it to the resulting collection. Else, ignore it 

Note: distinct() and resolve() are only allowed in the context of invariants, and need only be implemented by path evaluators that are used for testing invariants


6. Operations
-------------

The following operators are allowed to be used between path expressions (e.g. expr op expr):

### = (Equals)
True if the left collection is equal to the right collection

* equality is determined by comparing all the properties of the children
* todo: does order matter in sub-collections? 
* typically, this operator is used with a single fixed values. This means that Patient.telecom.system = 'phone' will return an empty collection if there is more than one telecom with a use typically, you'd want Patient.telecom.where(system = 'phone')</td></tr>


### ~ (Equivalent)
true if the collections are the same

* string evaluation is not case sensitive 
* order doesn't matter

###  != (Not Equals)
the inverse of the equals reaction
 
### !~ (Not Equivalent)
the inverse of the equivalent reaction
 
### > (Greater Than)
* this and the other 3 order related operations can only be used for strings, codes, integers, and decimals
* unless there is only one item in each collection (left and right) this returns an empty collection
* code evaluation is strictly lexical, not based on any defined meaning of order
* comparisons involving other types always return an empty collection
     
### < (Less Than)

### <= (Less or Equal)

### >= (Greater or Equal)

### | (union collections)
merge the two collections into a single list, eliminating any duplicate values (e.g. equal)
 
### in
test whether all the itesm in the left collection are in the right collection.

* order does not matter
* if the right collection is a URI, and it refers to a value set, value set membership testing will be performed

### and     
left and right are converted to booleans (see #3 above) and a boolean true or false is the outcome following normal and rules

### or
left and right are converted to booleans (see #3 above) and a boolean true or false is the outcome following normal or rules

### xor
left and right are converted to booleans (see #3 above) and a boolean true or false is the outcome following normal xor rules

### +
if left and right are collections with a single element, and the element type is the same, add them together (integers, decimals, strings only)

### -
if left and right are collections with a single element, and the element type is the same, subtract right from left (integers, decimals)

### &
if left and right are collections with a single element, and the element type is the same, concatenate them (strings only)
 
Note that operations may be grouped in the classical sense by surrounding them with ()

Operator precedence: 
    #1 . (path/function invocation)
    #2: *, /
    #3: +, -, &, |
    #4: =, ~, !=, !~, >, <, >=, <=, in
    #5: and, xor, or
  
7. Fixed constants
-------------------

A token introduced by a % defines a fixed constant that is automatically expanded into its agreed value by the parser. Tokens consist of an introducing %, and then a sequence of characters that conforms to the id data type (1-64, a..z, A..Z, 0..9, -, .)

The following fixed values are set for all contexts:

```
%sct        - url for snomed ct
%loinc      - url for loinc
%ucum       - url for ucum
%vs-[name]  - full url for the provided HL7 value set with id [name]
%ext-[name] - full url for the provided HL7 extension with id [name]
```

Implementers should note that defining additional fixed constants is a formal extension point for the langauge. Implementation Guides are allowed to define their own fixed constants, and implementers should provide some appropriate configuration framework to allow these constants to be provided to the evaluation engine at run time.

	e.g. %us-zip = "[0-9]{5}(-[0-9]{4}){0,1}"

8. Formal grammar
-----------------------------------

```
expression  = term (righthand)*
righthand   = op term | "." function
term        = "(" expression ")" | primitive | predicate
primitive   = number | "true" | "false" | constant | string
predicate   = item ('.' item)*
item        = element "*"? | function | axis_spec
axis_spec   = "*" | "**" | "$context" | "$parent" | "$resource"
element     = ("a".."z" | "A".."Z") ("a".."z" | "A".."Z" | "0".."9")* "[x]"?
function    = funcname "(" param_list? ")"
param_list  = expression ("," expression)*
funcname    = "empty" | "not" | "where" | "all" | "any" | "first" | "last" | "tail" | "count" | "asInteger" | "startsWith" | "length" | "matches" | "distinct" | "resolve" | "contains" | "substring" | "skip" | "take"
op          = "*" | "/" | "+" | "-" | "|" | "&" | "=" | "~" | "!=" | "!~" | ">" | "<" | "<=" | ">=" | "in" | "and" | "or" | "xor" 
number      = number as used in json
string      = sequence of Unicode characters, surrounded by either ' or ", using json escaping.
constant    = "%" ("a"-"z" | "A-Z" | "0-9") ("a-z" | "A-Z" | "0-9" | "-" | ".")* 
```

Note:
* Unicode whitespace is not explicitly shown in the grammar, but may be present around all terms and tokens in the grammar.
* Non ascii (e.g. Unicode) characters only allowed in constants (they do not need to be escaped in constants, but can be)


9. Mapping Extensions
--------------------------------------

** This section is speculative **

```
There is an additional mode for this language, where it is used for mapping from one content model to another. 

When performing mapping:

a. path definition
  The primary statement is used to define a path that will be instantiated
  when the mapping is performed. The elements referred to in the mspping
  statement are created rather then selected. 

  example: 
    Composition.section.title
  will cause a Composition to be created, then a section to be added to 
  it, and then a title will be created in that (though no value is yet 
  assigned to the title)
  
  When creating items, if an item has cardinality ..1, and it already 
  exist, the existing one will be reused. For items that have 
  cardinality > 1, new items are created. 
  No functions or operators are allowed in the defining path.
  The first name in a defining expression may be either a variable name
  (see below) or a resource or datat type type name - in which case a 
  new resource or datatype is created

b. value being mapped: a new special value is defined that refers to the 
  value being mapped:
  
   $value - this is an object that is an instance of a fhir data type. 
   
   The type of $value is only known at run-time. When mapping from 
   XML or json, only primitives types can be mapped. When mapping from 
   CDA or v2, see the mapping table below for data type mapping
   
c. conditional mappings
  Conditional mappings are encountered faily commonly. There is a special
  mapping criteria:
  
  if (expression) defining-expression

  The expression has no context. Typically, the expression references $value
  
  example: 
    if ($value = "23446-4") Composition.section.title
  A section will be added only if the value (a CDA code in this case) 
  has the given value
    
d. crossing resource boundaries
  mapping statements are allowed to cross resource boundaries, and
  create new resources, using -> 

  The first name after a -> is always a resource type name that 
  defines what type of resource is being referenced (this is always 
  true, even if only one type of resource is valid)

  example: 
    Composition.section.entry->AllergyIntolerance
  When the section is added, an entry will be created. Also, an
  AllergyIntolerance resource will be created, and the entry will
  be updated to point to the newly created resource
  
  Mapping statements do not assign ids to the resource - this is 
  done by the run time infrastructure  
  
e. variables
  mapping paths can be assigned to variables for later reuse 
  by prefixing the defining path with 
    [var-name] := 

  This retains the item for later re-use in other mappings. 
  A variable name can be any combination of letters and digits
  starting with a lowercase letter
  
  example: 
    cmp := Composition
  create a Composition, and remember it as "cmp"
    ai1 := cmp.section.entry->AllergyIntolerance
  take the composition already created, add a section to it,
  then create an AllergyIntolerance resource - and remember that
  as ai1
  
  It is an error to re-use variable names in a set of mappings
  (is it? - be conservative for now, make it an error, and see)
    
f. properties
  properties of items in a defined path can have additional
  properties assigned to them as they are created. This is done by:
  
   owner[name1 := value; name2 := value].child
  
  rules:
    names refer to elements defined on the owner 
    values must be constants, expressions that start with $value, or constants
    when executing, the following type conversions are implicit:
       string -> integer (if it is an integer)
       
  example: 
    lc := Coding[system = %loinc; code = "12345-6"]
    cmp := Composition[code := lc]
    
g, Formal Grammar:  

grammar:

mapping      = (condition) (assignment) map (";" mapping)
condition    = if "(" (expression) ")"
assignment   = varname ":="
varname      = ("a".."z") ("a".."z" | "A".."Z" | "0".."9")* 
map          = (step | type) ( ("." step) | ( "->" type) )*
step         = element ("[" properties "]")?
type         = typename ("[" properties "]")?
properties   = property (";" property)*
property     = element ":=" term
typename     = ("A".."Z") ("a".."z" | "A".."Z" | "0".."9")* 

inside term, one change: 
  - add axis_spec : $value
  
h. data type mapping table

  v2:

  v3:
    ANY -> e := Element
      ANY.nullFlavor -> e.extension[url = http://hl7.org/fhir/StructureDefinition/iso21090-nullFlavor; code = $value]
    BL -> boolean
    BN -> boolean
    ED -> a := Attachment
      mediaType -> ct = a.contentType[$value]
      charset -> ct[ct + $value] 
      language -> a[language := $value]
      compression -> n/a // have to uncompress
      reference -> a[url := $value]
      integrityCheck -> a[hash := $value] 
      integrityCheckAlgorithm -> // have to check this and recalcuate as SHA1
      thumbnail -> n/a
      
    ST -> string
    CD -> CodeableConcept
    CS -> code
    CO -> ??
    CV -> Coding
    CE -> CodeableConcept
    SC -> CodeableConcept
    II -> Identifier
    TEL -> Telecom
    AD -> Address
    EN -> HumanName
    TN -> HumanName
    PN -> HumanName
    ON -> HumanName
    INT -> integer
    REAL -> decimal
    RTO -> Ratio
    PQ -> PhysicalQuantity
    MO -> Quantity
    TS -> dateTime
    SET -> n/a
    LIST -> n/a 
    BAG -> n/a
    IVL<TS> -> Period 
    IVL<PQ> -> Range
    HIST -> n/a
    UVP -> n/a
    PIVL -> Timing
    EIVL -> Timing
    GTS -> Timing
    PPD -> n/a


i. known problems with mappings:
  * when 2 different elements contribute to the same primitive value
  * when 2 different paths use the same variable, and the variable cross a resource boundary, and either is optional

```

  