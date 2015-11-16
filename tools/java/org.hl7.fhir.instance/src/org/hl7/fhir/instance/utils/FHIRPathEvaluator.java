package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.utilities.Utilities;

/**
 * 
 * @author Grahame Grieve
 *
 */
public class FHIRPathEvaluator {

  private IWorkerContext worker;
  private IConstantResolver constantResolver;
  protected boolean mappingExtensions;

  // if the fhir path expressions are allowed to use constants beyond those defined in the specification
  // the application can implement them by providing a constant resolver 
  public interface IConstantResolver {
    public Type resolveConstant(Object appContext, String name) throws Exception;
    public String resolveConstantType(Object appContext, String name) throws Exception;
  }
  

  /**
   * @param worker - used when validating paths (@check), and used doing value set membership when executing tests (once that's defined)
   */
  public FHIRPathEvaluator(IWorkerContext worker) {
    super();
    this.worker = worker;
  }

  
  // --- 3 methods to override in children -------------------------------------------------------
  // if you don't override, it falls through to the using the base reference implementation 
  // HAPI overrides to these to support extensing the base model
  
  public IConstantResolver getConstantResolver() {
    return constantResolver;
  }


  public void setConstantResolver(IConstantResolver constantResolver) {
    this.constantResolver = constantResolver;
  }


  /**
   * Given an item, return all the children that conform to the pattern described in name
   * 
   * Possible patterns:
   *  - a simple name
   *  - a name with [] e.g. value[x]
   *  - a name with a type replacement e.g. valueCodeableConcept
   *  - * which means all children
   *  - ** which means all descendents
   *  
   * @param item
   * @param name
   * @param result
   */
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    for (Base v : item.listChildrenByName(name))
      if (v != null)
        result.add(v);
  }
  
  /**
   * ensure that a property of the given name exists, and return the value
   * if the property is a collection, add an instance to the collection and return it
   * 
   * @param focus - the object being affected
   * @param name - the name of the property to set
   * @return - the value of the created object 
   * @throws Exception if the property doesn't exist, or it has a primitive type 
   *  
   */
  protected Base addChildProperty(Base focus, String name) throws Exception {
    return focus.addChild(name);
  }
  
  /**
   * given a value, assign it to a child property
   * if the property is a collection, add it to the collection
   * 
   * @param focus - the object being affected
   * @param name - the name of the property to set
   * @param value - the value of the property 
   * @throws Exception -  if the property doesn't exist, or the value is the wrong type
   */
  protected void setChildProperty(Base focus, String name, Type value) throws Exception {
    focus.setProperty(name, value);  
  }

  // --- public API -------------------------------------------------------
  /**
   * Parse a path for later use using execute
   * 
   * @param path
   * @return
   * @throws Exception
   */
  public Expression parse(String path) throws Exception {
    Lexer lexer = new Lexer(path, false);
    if (lexer.done())
      throw lexer.error("Path cannot be empty");
    Expression result = parseExpression(lexer, true);
    if (!lexer.done())
      throw lexer.error("Premature expression termination at unexpected token \""+lexer.current+"\"");
    return result;    
  }

  /**
   * check that paths referred to in the expression are valid
   * 
   * xPathStartsWithValueRef is a hack work around for the fact that FHIR Path sometimes needs a different starting point than the xpath
   * 
   * returns a list of the possible types that might be returned by executing the expression against a particular context
   * 
   * @param context - the logical type against which this path is applied
   * @param path - the FHIR Path statement to check
   * @throws Exception if the path is not valid
   */
  public Set<String> check(Object appContext, String resourceType, String context, String path, boolean xPathStartsWithValueRef) throws Exception {
    Expression expr = parse(path);
    Set<String> types = new HashSet<String>();
    if (xPathStartsWithValueRef && context.contains(".") && path.startsWith(context.substring(context.lastIndexOf(".")+1)))
      types.add(context.substring(0, context.lastIndexOf(".")));
    else 
      types.add(context);
    return executeType(new ExecutionTypeContext(appContext, resourceType, context), types, expr, true);
  }

  /**
   * evaluate a path and return the matching elements
   * 
   * @param base - the object against which the path is being evaluated
   * @param expression - the parsed expression statement to use
   * @return
   * @throws Exception 
   */
  public List<Base> evaluate(Base base, Expression expression) throws Exception {
    List<Base> list = new ArrayList<Base>();
    if (base != null)
      list.add(base);
    return execute(new ExecutionContext(null, null, base), list, expression, true);
  }

  /**
   * evaluate a path and return the matching elements
   * 
   * @param base - the object against which the path is being evaluated
   * @param path - the FHIR Path statement to use
   * @return
   * @throws Exception 
   */
  public List<Base> evaluate(Base base, String path) throws Exception {
    Expression exp = parse(path);
    List<Base> list = new ArrayList<Base>();
    if (base != null)
      list.add(base);
    return execute(new ExecutionContext(null, null, base), list, exp, true);
  }

  /**
   * evaluate a path and return the matching elements
   * 
   * @param base - the object against which the path is being evaluated
   * @param expression - the parsed expression statement to use
   * @return
   * @throws Exception 
   */
  public List<Base> evaluate(Object appContext, Resource resource, Base base, Expression expression) throws Exception {
    List<Base> list = new ArrayList<Base>();
    if (base != null)
      list.add(base);
    return execute(new ExecutionContext(appContext, resource, base), list, expression, true);
  }

  /**
   * evaluate a path and return the matching elements
   * 
   * @param base - the object against which the path is being evaluated
   * @param path - the FHIR Path statement to use
   * @return
   * @throws Exception 
   */
  public List<Base> evaluate(Object appContext, Resource resource, Base base, String path) throws Exception {
    Expression exp = parse(path);
    List<Base> list = new ArrayList<Base>();
    if (base != null)
      list.add(base);
    return execute(new ExecutionContext(appContext, resource, base), list, exp, true);
  }

  /**
   * evaluate a path and return true or false (e.g. for an invariant)
   * 
   * @param base - the object against which the path is being evaluated
   * @param path - the FHIR Path statement to use
   * @return
   * @throws Exception 
   */
  public boolean evaluateToBoolean(Resource resource, Base base, String path) throws Exception {
    return convertToBoolean(evaluate(null, resource, base, path));
  }

  /**
   * evaluate a path and a string containing the outcome (for display)
   * 
   * @param base - the object against which the path is being evaluated
   * @param path - the FHIR Path statement to use
   * @return
   * @throws Exception 
   */
  public String evaluateToString(Base base, String path) throws Exception {
    return convertToString(evaluate(base, path));
  }

  /**
   * worker routine for converting a set of objects to a string representation
   * 
   * @param items - result from @evaluate
   * @return
   */
  public String convertToString(List<Base> items) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (Base item : items) {
      if (first) 
        first = false;
      else
        b.append(',');

      b.append(convertToString(item));
    }
    return b.toString();
  }

  @SuppressWarnings("rawtypes")
  private String convertToString(Base item) {
    if (item instanceof PrimitiveType)
      return ((PrimitiveType) item).asStringValue();
    else 
      return item.getClass().getName();
  }

  /**
   * worker routine for converting a set of objects to a boolean representation (for invariants)
   * 
   * @param items - result from @evaluate
   * @return
   */
  public boolean convertToBoolean(List<Base> items) {
    if (items == null)
      return false;
    else if (items.size() == 1 && items.get(0) instanceof BooleanType)
      return ((BooleanType) items.get(0)).getValue();
    else 
      return items.size() > 0;
  }

  //the expression will have one of either name or constant
  public enum Function {
    Empty, Item, Where, All, Any, First, Last, Tail, Count, AsInteger, StartsWith, Length, Matches, Substring, Contains, Distinct, Not, Resolve;

    public static Function fromCode(String name) {
      if (name.equals("empty"))
        return Function.Empty;
      if (name.equals("item"))
        return Function.Item;
      if (name.equals("where"))
        return Function.Where;
      if (name.equals("all"))
        return Function.All;
      if (name.equals("any"))
        return Function.Any;
      if (name.equals("first"))
        return Function.First;
      if (name.equals("last"))
        return Function.Last;
      if (name.equals("tail"))
        return Function.Tail;
      if (name.equals("count"))
        return Function.Count;
      if (name.equals("asInteger"))
        return Function.AsInteger;
      if (name.equals("startsWith"))
        return Function.StartsWith;
      if (name.equals("length"))
        return Function.Length;
      if (name.equals("matches"))
        return Function.Matches;
      if (name.equals("contains"))
        return Function.Contains;
      if (name.equals("substring"))
        return Function.Substring;
      if (name.equals("distinct"))
        return Function.Distinct;
      if (name.equals("not"))
        return Function.Not;
      if (name.equals("resolve"))
        return Function.Resolve;
      return null;
    }
  }

  public enum Operation {
    Equals, Equivalent, NotEquals, NotEquivalent, LessThen, Greater, LessOrEqual, GreaterOrEqual, Union, In, Or, And, Xor, Plus, Minus, Concatenate;

    public static Operation fromCode(String name) {
      if (Utilities.noString(name))
        return null;
      if (name.equals("="))
        return Operation.Equals;
      if (name.equals("~"))
        return Operation.Equivalent;
      if (name.equals("!="))
        return Operation.NotEquals;
      if (name.equals("!~"))
        return Operation.NotEquivalent;
      if (name.equals(">"))
        return Operation.Greater;
      if (name.equals("<"))
        return Operation.LessThen;
      if (name.equals(">="))
        return Operation.GreaterOrEqual;
      if (name.equals("<="))
        return Operation.LessOrEqual;
      if (name.equals("|"))
        return Operation.Union;
      if (name.equals("in"))
        return Operation.In;
      if (name.equals("or"))
        return Operation.Or;
      if (name.equals("and"))
        return Operation.And;
      if (name.equals("xor"))
        return Operation.Xor;
      if (name.equals("+"))
        return Operation.Plus;
      if (name.equals("-"))
        return Operation.Minus;
      if (name.equals("&"))
        return Operation.Concatenate;
      return null;

    }
  }

  private class Expression {

    private String name;
    private String constant;
    private Function function;
    private List<Expression> parameters; // will be created if there is a function
    private Expression inner;
    private Operation operation;
    private boolean proximal; // a proximal operation is the first in the sequence of operations. This is significant when evaluating the outcomes
    private Expression opNext;

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getConstant() {
      return constant;
    }
    public void setConstant(String constant) {
      this.constant = constant;
    }
    public Function getFunction() {
      return function;
    }
    public void setFunction(Function function) {
      this.function = function;
      if (parameters == null)
        parameters = new ArrayList<Expression>();
    }

    public boolean isProximal() {
      return proximal;
    }
    public void setProximal(boolean proximal) {
      this.proximal = proximal;
    }
    public Operation getOperation() {
      return operation;
    }
    public void setOperation(Operation operation) {
      this.operation = operation;
    }
    public Expression getInner() {
      return inner;
    }
    public void setInner(Expression value) {
      this.inner = value;
    }
    public Expression getOpNext() {
      return opNext;
    }
    public void setOpNext(Expression value) {
      this.opNext = value;
    }
    public List<Expression> getParameters() {
      return parameters;
    }
    public boolean checkName() {
      if (!name.startsWith("$"))
        return true;
      else if (mappingExtensions && name.equals("$value"))
        return true;
      else
        return name.equals("$context") || name.equals("$resource") || name.equals("$parent");  
    }
  }

  private class Lexer {
    private String source;
    private int cursor;
    private String current;
    private int currentStart;

    public Lexer(String source, boolean forMap) throws Exception {
      this.source = source;
      next(forMap);
    }
    public String getSource() {
      return source;
    }
    public int getCursor() {
      return cursor;
    }
    public String getCurrent() {
      return current;
    }
    public int getCurrentStart() {
      return currentStart;
    }

    public boolean isConstant() {
      return current.charAt(0) == '"' || current.charAt(0) == '%' || (current.charAt(0) >= '0' && current.charAt(0) <= '9') || current.equals("true") || current.equals("false");
    }

    public String take(boolean forMap) throws Exception {
      String s = current;
      next(forMap);
      return s;
    }

    public boolean isToken() {
      if (Utilities.noString(current))
        return false;

      if (current.startsWith("$"))
        return true;

      if (current.equals("$") || current.equals("*") || current.equals("**"))
        return true;

      if ((current.charAt(0) >= 'A' && current.charAt(0) <= 'Z') || (current.charAt(0) >= 'a' && current.charAt(0) <= 'z')) {
        for (int i = 1; i < current.length(); i++) 
          if (!( (current.charAt(1) >= 'A' && current.charAt(1) <= 'Z') || (current.charAt(1) >= 'a' && current.charAt(1) <= 'z') ||
              (current.charAt(1) >= '0' && current.charAt(1) <= '9')) || current.charAt(1) == '[' || current.charAt(1) == ']' || (current.charAt(1) == '*') && (i == current.length()-1))
            return false;
        return true;
      }
      return false;
    }

    public Exception error(String msg) {
      return error(msg, currentStart);
    }

    private Exception error(String msg, int offset) {
      return new Exception("Error in "+source+" at "+Integer.toString(offset+1)+": "+msg);
    }

    public void next(boolean forMapping) throws Exception {
      //	  procedure Grab(length : Integer);
      //	  begin
      //	    FCurrent := copy(path, FCurrentStart, length);
      //	    inc(cursor, length);
      //	  end;
      current = null;
      while (cursor < source.length() && Character.isWhitespace(source.charAt(cursor)))
        cursor++;
      currentStart = cursor;
      if (cursor < source.length()) {
        char ch = source.charAt(cursor);
        if (ch == '!' || ch == '>' || ch == '<' || ch == ':')  {
          cursor++;
          if (cursor < source.length() && source.charAt(cursor) == '=') 
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch == '*') {
          cursor++;
          if (cursor < source.length() && source.charAt(cursor) == '*') 
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch == '-') {
          cursor++;
          if (cursor < source.length() && source.charAt(cursor) == '>') 
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch >= '0' && ch <= '9') {
          while (cursor < source.length() && ((source.charAt(cursor) >= '0' && source.charAt(cursor) <= '9') || source.charAt(cursor) == '.')) 
            cursor++;
          current = source.substring(currentStart, cursor);
        }  else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
          while (cursor < source.length() && ((source.charAt(cursor) >= 'A' && source.charAt(cursor) <= 'Z') || (source.charAt(cursor) >= 'a' && source.charAt(cursor) <= 'z') || 
              (source.charAt(cursor) >= '0' && source.charAt(cursor) <= '9') || (!forMapping && (source.charAt(cursor) == '[' || source.charAt(cursor) == ']')) || source.charAt(cursor) == '*')) 
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch == '%') {
          cursor++;
          while (cursor < source.length() && ((source.charAt(cursor) >= 'A' && source.charAt(cursor) <= 'Z') || (source.charAt(cursor) >= 'a' && source.charAt(cursor) <= 'z') || 
              (source.charAt(cursor) >= '0' && source.charAt(cursor) <= '9') || source.charAt(cursor) == ':' || source.charAt(cursor) == '-'))
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch == '$') {
          cursor++;
          while (cursor < source.length() && (source.charAt(cursor) >= 'a' && source.charAt(cursor) <= 'z'))
            cursor++;
          current = source.substring(currentStart, cursor);
        } else if (ch == '"' || ch == '\''){
          cursor++;
          char ech = ch;
          boolean escape = false;
          while (cursor < source.length() && (escape || source.charAt(cursor) != ech)) {
            if (escape)
              escape = false;
            else 
              escape = (source.charAt(cursor) == '\\');
            cursor++;
          }
          if (cursor == source.length())
            throw error("Unterminated string");
          cursor++;
          current = source.substring(currentStart, cursor);
          if (ech == '\'')
            current = "\""+current.substring(1, current.length() - 1)+"\"";
        } else { // if CharInSet(ch, ['.', ',', '(', ')', '=', '$']) then
          cursor++;
          current = source.substring(currentStart, cursor);
        }
      }
    }


    public boolean isOp() {
      return Operation.fromCode(current) != null;
    }
    public boolean done() {
      return currentStart >= source.length();
    }

  }

  private class ExecutionContext {
    private Object appContext;
    private Resource resource;
    private Base original;
    private Base value; // for mapping
    public ExecutionContext(Object appContext, Resource resource, Base original) {
      this.appContext = appContext;
      this.resource = resource; 
      this.original = original;
    }
    public Resource getResource() {
      return resource;
    }
    public void setResource(Resource resource) {
      this.resource = resource;
    }
    public Base getOriginal() {
      return original;
    }
    public void setOriginal(Base original) {
      this.original = original;
    }
    public Base getValue() {
      return value;
    }
    public void setValue(Base value) {
      this.value = value;
    }
  
  }

  private class ExecutionTypeContext {
    private Object appContext; 
    private String resource;
    private String original;
    private String value; // for mapping
    
    
    public ExecutionTypeContext(Object appContext, String resource, String original) {
      super();
      this.appContext = appContext;
      this.resource = resource;
      this.original = original;
    }
    public String getResource() {
      return resource;
    }
    public void setResource(String resource) {
      this.resource = resource;
    }
    public String getOriginal() {
      return original;
    }
    public void setOriginal(String original) {
      this.original = original;
    }
    public String getValue() {
      return value;
    }
    public void setValue(String value) {
      this.value = value;
    }
    
    
  }
  private Expression parseExpression(Lexer lexer, boolean proximal) throws Exception {
    Expression result = new Expression();
    int c = lexer.getCurrentStart();
    if (lexer.isConstant()) {
      result.setConstant(cleanConstant(lexer.take(false), lexer));
    } else {
      if ("(".equals(lexer.getCurrent())) {
        lexer.next(false);
        Expression group = parseExpression(lexer, true);
        if (!")".equals(lexer.getCurrent())) 
          throw lexer.error("Found "+lexer.getCurrent()+" expecting a \")\"");
        lexer.next(false);
        result = group;
      } else {
        if (!lexer.isToken()) 
          throw lexer.error("Found "+lexer.getCurrent()+" expecting a token name");
        result.setName(lexer.take(false));
        if (!result.checkName())
          throw lexer.error("Found "+result.getName()+" expecting a valid token name");
        if ("(".equals(lexer.getCurrent())) {
          Function f = Function.fromCode(result.getName());  
          if (f == null)
            throw lexer.error("The name "+result.getName()+" is not a valid function name");
          result.setFunction(f);
          lexer.next(false);
          while (!")".equals(lexer.getCurrent())) { 
            result.getParameters().add(parseExpression(lexer, true));
            if (",".equals(lexer.getCurrent()))
              lexer.next(false);
            else if (!")".equals(lexer.getCurrent()))
              throw lexer.error("The token "+lexer.getCurrent()+" is not expected here - either a \",\" or a \")\" expected");
          }
          lexer.next(false);
          checkParameters(lexer, c, result);
        }
      }
      if (".".equals(lexer.current)) {
        lexer.next(false);
        result.setInner(parseExpression(lexer, false));
        assert(!result.getInner().isProximal());
      }
    }
    if (proximal) {
      while (lexer.isOp()) {
        result.setOperation(Operation.fromCode(lexer.getCurrent()));
        lexer.next(false);
        result.setOpNext(parseExpression(lexer, false));
      }
    }
    result.setProximal(proximal);
    return result;
  }

  private String cleanConstant(String s, Lexer lexer) throws Exception {
    if (s.startsWith("\"") && s.endsWith("\"")) {
    	StringBuilder b = new StringBuilder();
    	boolean inEscape = false;
    	for (int i = 1; i < s.length()-1; i++) {
    		char ch = s.charAt(i);
    		if (inEscape) { 
    			switch (ch) {
    			case 't': b.append('\t'); break;
    			case 'r': b.append('\r'); break;
    			case 'n': b.append('\n'); break;
    			case '\'': b.append('\''); break;
    			case '"': b.append('"'); break;
    			case '\\': b.append('\\'); break;
    			default: throw lexer.error("Unknown character escape \\"+ch);
    			}
    			inEscape = false;
    		} else if (ch == '\\')
    			inEscape = true;
    		else
    			b.append(ch);
    	}
      s = s.substring(1, s.length()-1);
      return s.replace("\\t", "\t").replace("\\r", "\r").replace("\\n", "\n").replace("\\\"", "\"").replace("\\'", "'").replace("\\\\", "\\");
    } else
      return s;
  }

  //  procedure CheckParamCount(c : integer);
  //  begin
  //    if exp.Parameters.Count <> c then
  //      raise lexer.error('The function "'+exp.name+'" requires '+inttostr(c)+' parameters', offset);
  //  end;

  private boolean checkNoParameters(Lexer lexer, int offset, Expression exp) throws Exception {
    if (exp.getParameters().size() > 0)
      throw lexer.error("The function \""+exp.name+"\" can not have any parameters", offset);
    return true;
  }

  private boolean checkParamCount(Lexer lexer, int offset, Expression exp, int count) throws Exception {
    if (exp.getParameters().size() != count)
      throw lexer.error("The function \""+exp.name+"\" requires "+Integer.toString(count)+" parameters", offset);
    return true;
  }

  private boolean checkParamCountRange(Lexer lexer, int offset, Expression exp, int countMin, int countMax) throws Exception {
    if (exp.getParameters().size() < countMin || exp.getParameters().size() > countMax)
      throw lexer.error("The function \""+exp.name+"\" requires between "+Integer.toString(countMin)+" and "+Integer.toString(countMax)+" parameters", offset);
    return true;
  }

  private boolean checkParameters(Lexer lexer, int offset, Expression exp) throws Exception {
    switch (exp.getFunction()) {
    case Empty: return checkNoParameters(lexer, offset, exp);
    case Item: return checkParamCount(lexer, offset, exp, 1);
    case Where: return checkParamCount(lexer, offset, exp, 1);
    case All: return checkParamCount(lexer, offset, exp, 1);
    case Any: return checkParamCount(lexer, offset, exp, 1);
    case First: return checkNoParameters(lexer, offset, exp);
    case Last: return checkNoParameters(lexer, offset, exp);
    case Tail: return checkNoParameters(lexer, offset, exp);
    case Count: return checkNoParameters(lexer, offset, exp);
    case AsInteger: return checkNoParameters(lexer, offset, exp);
    case StartsWith: return checkParamCount(lexer, offset, exp, 1);
    case Length: return checkNoParameters(lexer, offset, exp);
    case Matches: return checkParamCount(lexer, offset, exp, 1);
    case Contains: return checkParamCount(lexer, offset, exp, 1);
    case Substring: return checkParamCountRange(lexer, offset, exp, 1, 2);
    case Not: return checkNoParameters(lexer, offset, exp);
    case Distinct: return true; // no chECK
    }
    return false;
  }

  private List<Base> execute(ExecutionContext context, List<Base> focus, Expression exp, boolean atEntry) throws Exception {
    List<Base> work = new ArrayList<Base>();
    if (exp.getFunction() != null) {
      work.addAll(evaluateFunction(context, focus, exp));
    } else if (exp.getConstant() != null) 
      work.add(readConstant(context.appContext, exp.getConstant()));
    else if (exp.getName().equals("$resource"))
      work.add(context.getResource());
    else if (exp.getName().equals("$context"))
      work.add(context.getOriginal());
    else if (exp.getName().equals("$value")) {
      if (context.getValue() != null)
        work.add(context.getValue());
    } else 
      for (Base item : focus) 
        work.addAll(execute(context, item, exp, atEntry));

    if (exp.getInner() != null)
      work = execute(context, work, exp.getInner(), false);
    
    if (exp.proximal && exp.getOperation() != null) {
      Expression next = exp.getOpNext();
      Expression last = exp;
      while (next != null) {
        List<Base> work2 = execute(context, focus, next, false);
        work = operate(work, last.getOperation(), work2);
        last = next;
        next = next.getOpNext();
      }
    }
    return work;
  }

  private Set<String> executeType(ExecutionTypeContext context, Set<String> focus, Expression exp, boolean atEntry) throws Exception {
    Set<String> work = new HashSet<String>();
    if (exp.getFunction() != null) {
      work.addAll(evaluateFunctionType(context, focus, exp));
    } else if (exp.getConstant() != null) 
      work.add(readConstantType(context.appContext, exp.getConstant()));
    else if (exp.getName().equals("$context"))
      work.add(context.getOriginal());
    else if (exp.getName().equals("$resource")) {
      if (context.getResource() != null)
        work.add(context.getResource());
      else
        work.add("DomainResource");
    }     
    else {
      for (String s : focus) 
        work.addAll(executeType(s, exp, atEntry));
      if (work.isEmpty()) 
        throw new Exception("The name "+exp.getName()+" was not valid for any of the possible types: "+focus.toString());
    }

    if (exp.getInner() != null)
      work = executeType(context, work, exp.getInner(), false);

    if (exp.proximal && exp.getOperation() != null) {
      Expression next = exp.getOpNext();
      Expression last = exp;
      while (next != null) {
        Set<String> work2 = executeType(context, focus, next, false);
        work = operateTypes(work, last.getOperation(), work2);
        last = next;
        next = next.getOpNext();
      }
    }
    return work;
  }

  private List<Base> operate(List<Base> left, Operation operation, List<Base> right) {
    switch (operation) {
    case Equals: return opEquals(left, right);
    case NotEquals: return opNotEquals(left, right);
    case LessThen: return opLessThen(left, right);
    case Greater: return opGreater(left, right);
    case LessOrEqual: return opLessOrEqual(left, right);
    case GreaterOrEqual: return opGreaterOrEqual(left, right);
    case In: return opIn(left, right);
    case Plus: return opPlus(left, right);
    case Minus: return opMinus(left, right);
    default: 
      return null;
    }
  }

  private Set<String> operateTypes(Set<String> left, Operation operation, Set<String> right) {
    switch (operation) {
    case Equals: return typeSet("boolean");
    case NotEquals: return typeSet("boolean");
    case LessThen: return typeSet("boolean");
    case Greater: return typeSet("boolean");
    case LessOrEqual: return typeSet("boolean");
    case GreaterOrEqual: return typeSet("boolean");
    case In: return typeSet("boolean");
    case Plus: return typeSet("string");
    case Minus: return typeSet("string");
    case Or: return typeSet("boolean");
    case And: return typeSet("boolean");
    case Xor: return typeSet("boolean");
    case Union: return union(left, right);
    default: 
      return null;
    }
  }

  private Set<String> union(Set<String> left, Set<String> right) {
    Set<String> result = new HashSet<String>();
    result.addAll(left);
    result.addAll(right);
    return result;
  }

  private Set<String> typeSet(String string) {
    Set<String> result = new HashSet<String>();
    result.add(string);
    return result;
  }

  private List<Base> opEquals(List<Base> left, List<Base> right) {
    boolean found = false;
    String sr = convertToString(right);
    for (Base item : left) {
      String sl = convertToString(item);
      found = found || (sl.equals(sr));
    }
    List<Base> result = new ArrayList<Base>();
    result.add(new BooleanType(found));
    return result;
  }

  private List<Base> opNotEquals(List<Base> left, List<Base> right) {
    boolean found = false;
    String sr = convertToString(right);
    for (Base item : left) {
      String sl = convertToString(item);
      found = found || (sl.equals(sr));
    }
    List<Base> result = new ArrayList<Base>();
    result.add(new BooleanType(!found));
    return result;
  }

  private List<Base> opLessThen(List<Base> left, List<Base> right) {
    throw new Error("The operation LessThen is not done yet");
  }

  private List<Base> opGreater(List<Base> left, List<Base> right) {
    throw new Error("The operation Greater is not done yet");
  }

  private List<Base> opLessOrEqual(List<Base> left, List<Base> right) {
    throw new Error("The operation LessOrEqual is not done yet");
  }

  private List<Base> opGreaterOrEqual(List<Base> left, List<Base> right) {
    throw new Error("The operation GreaterOrEqual is not done yet");
  }

  private List<Base> opIn(List<Base> left, List<Base> right) {
    throw new Error("The operation In is not done yet");
  }

  private List<Base> opPlus(List<Base> left, List<Base> right) {
    throw new Error("The operation Plus is not done yet");
  }

  private List<Base> opMinus(List<Base> left, List<Base> right) {
    throw new Error("The operation Minus is not done yet");
  }


  private Type readConstant(Object appContext, String constant) throws Exception {
    if (constant.equals("true")) 
      return new BooleanType(true);
    else if (constant.equals("false")) 
      return new BooleanType(false);
    else if (Utilities.isInteger(constant))
      return new IntegerType(constant);
    else if (Utilities.isDecimal(constant))
      return new DecimalType(constant);
    else if (constant.startsWith("%"))
      return resolveConstant(appContext, constant);
    else
      return new StringType(constant);
  }

  private Type resolveConstant(Object appContext, String s) throws Exception {
    if (s.equals("%sct"))
      return new StringType("\"http://snomed.info/sct\"");
    else if (s.equals("%loinc"))
      return new StringType("\"http://loinc.org\"");
    else if (s.equals("%ucum"))
      return new StringType("\"http://unitsofmeasure.org\"");
    else if (s.equals("%us-zip"))
      return new StringType("\"[0-9]{5}(-[0-9]{4}){0,1}\"");
    else if (s.startsWith("%vs-"))
      return new StringType("\"http://hl7.org/fhir/ValueSet/"+s.substring(4)+"\"");
    else if (s.startsWith("%ext-"))
      return new StringType("\"http://hl7.org/fhir/StructureDefinition/"+s.substring(5)+"\"");
    else if (constantResolver == null)
      throw new Exception("Unknown fixed constant '"+s+"'");
    else
      return constantResolver.resolveConstant(appContext, s);
  }

  private String readConstantType(Object appContext, String constant) throws Exception {
    if (constant.equals("true")) 
      return "boolean";
    else if (constant.equals("false")) 
      return "boolean";
    else if (Utilities.isInteger(constant))
      return "integer";
    else if (Utilities.isDecimal(constant))
      return "decimal";
    else if (constant.startsWith("%"))
      return resolveConstantType(appContext, constant);
    else
      return "string";
  }

  private String resolveConstantType(Object appContext, String s) throws Exception {
    if (s.equals("%sct"))
      return "string";
    else if (s.equals("%loinc"))
      return "string";
    else if (s.equals("%ucum"))
      return "string";
    else if (s.equals("%map-codes"))
      return "string";
    else if (s.equals("%us-zip"))
      return "string";
    else if (s.startsWith("%vs-"))
      return "string";
    else if (s.startsWith("%ext-"))
      return "string";
    else if (constantResolver == null)
      throw new Exception("Unknown fixed constant type for '"+s+"'");
    else
      return constantResolver.resolveConstantType(appContext, s);
  }

  private List<Base> execute(ExecutionContext context, Base item, Expression exp, boolean atEntry) {
    List<Base> result = new ArrayList<Base>(); 
    if (atEntry && Character.isUpperCase(exp.getName().charAt(0))) {// special case for start up
      if (item instanceof Resource && ((Resource) item).getResourceType().toString().equals(exp.getName()))  
        result.add(item);
    } else
      getChildrenByName(item, exp.name, result);
    return result;
  }

  private Set<String> executeType(String type, Expression exp, boolean atEntry) throws Exception {
    Set<String> result = new HashSet<String>(); 
    if (atEntry && Character.isUpperCase(exp.getName().charAt(0))) {// special case for start up
      if (type.equals(exp.getName()))  
        result.add(type);
    } else
      getChildTypesByName(type, exp.name, result);
    return result;
  }


  private Set<String> evaluateFunctionType(ExecutionTypeContext context, Set<String> focus, Expression exp) throws Exception {
    for (Expression expr : exp.getParameters()) {
      executeType(context, focus, expr, false);
    }
    switch (exp.getFunction()) {
    case Empty : return typeSet("boolean");
    case Item : return focus;
    case Where : return focus;
    case All : return typeSet("boolean");
    case Any : return typeSet("boolean");
    case First : return focus;
    case Last : return focus;
    case Tail : return focus;
    case Count : return typeSet("integer");
    case AsInteger : return typeSet("integer");
    case StartsWith : return primitives(focus);
    case Length : return typeSet("integer");
    case Matches : return primitives(focus);
    case Contains : return primitives(focus);
    case Substring : return typeSet("integer");
    case Distinct : return typeSet("boolean");
    case Not : return typeSet("boolean");
    }
    throw new Error("not Implemented yet");
  }

  private Set<String> primitives(Set<String> context) {
    Set<String> result = new HashSet<String>();
    for (String s : context)
      if (isPrimitiveType(s))
        result.add(s);
    return result;
  }

  private boolean isPrimitiveType(String s) {
    return s.equals("boolean") || s.equals("integer") || s.equals("decimal") || s.equals("base64Binary") || s.equals("instant") || s.equals("string") || s.equals("uri") || s.equals("date") || s.equals("dateTime") || s.equals("time") || s.equals("code") || s.equals("oid") || s.equals("id") || s.equals("unsignedInt") || s.equals("positiveInt") || s.equals("markdown");
  }

  private List<Base> evaluateFunction(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    switch (exp.getFunction()) {
    case Empty : return funcEmpty(context, focus, exp);
    case Item : return funcItem(context, focus, exp);
    case Where : return funcWhere(context, focus, exp);
    case All : return funcAll(context, focus, exp);
    case Any : return funcAny(context, focus, exp);
    case First : return funcFirst(context, focus, exp);
    case Last : return funcLast(context, focus, exp);
    case Tail : return funcTail(context, focus, exp);
    case Count : return funcCount(context, focus, exp);
    case AsInteger : return funcAsInteger(context, focus, exp);
    case StartsWith : return funcStartsWith(context, focus, exp);
    case Length : return funcLength(context, focus, exp);
    case Matches : return funcMatches(context, focus, exp);
    case Contains : return funcContains(context, focus, exp);
    case Substring : return funcSubString(context, focus, exp);
    case Distinct : return funcDistinct(context, focus, exp);
    case Not : return funcNot(context, focus, exp);
    }
    throw new Error("not Implemented yet");
  }

  private List<Base> funcDistinct(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    throw new Error("not Implemented yet");
  }

  private List<Base> funcMatches(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    String p = convertToString(execute(context, focus, exp.getParameters().get(0), false));

    for (Base item : focus) {
      String s = convertToString(item);
      if (s.matches(p)) 
        result.add(item);
    }
    return result;
  }

  private List<Base> funcContains(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    String p = convertToString(execute(context, focus, exp.getParameters().get(0), false));

    for (Base item : focus) {
      String s = convertToString(item);
      if (s.contains(p)) 
        result.add(item);
    }
    return result;
  }

  private List<Base> funcLength(ExecutionContext context, List<Base> focus, Expression exp) {
    int l = 0;
    for (Base item : focus) {
      String s = convertToString(item);
      l = Math.max(l, s.length());
    }
    List<Base> result = new ArrayList<Base>();
    result.add(new IntegerType(l));
    return result;
  }

  private List<Base> funcStartsWith(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    String sw = convertToString(execute(context, focus, exp.getParameters().get(0), false));

    for (Base item : focus) {
      String s = convertToString(item);
      if (s.startsWith(sw)) 
        result.add(item);
    }
    return result;
  }

  private List<Base> funcSubString(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    throw new Error("not done yet");
  }

  private List<Base> funcAsInteger(ExecutionContext context, List<Base> focus, Expression exp) {
    String s = convertToString(focus);
    List<Base> result = new ArrayList<Base>();
    if (Utilities.isInteger(s))
      result.add(new IntegerType(s));
    return result;
  }

  private List<Base> funcCount(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    result.add(new IntegerType(focus.size()));
    return result;
  }

  private List<Base> funcTail(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    for (int i = 1; i < focus.size(); i++)
      result.add(focus.get(i));
    return result;
  }

  private List<Base> funcLast(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    if (focus.size() > 0)
      result.add(focus.get(focus.size()-1));
    return result;
  }

  private List<Base> funcFirst(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    if (focus.size() > 0)
      result.add(focus.get(0));
    return result;
  }

  private List<Base> funcAny(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    List<Base> pc = new ArrayList<Base>();
    boolean any = false;
    for (Base item : focus) {
      pc.clear();
      pc.add(item);
      if (convertToBoolean(execute(context, pc, exp.getParameters().get(0), false))) {
        any = true;
        break;
      }
    }
    result.add(new BooleanType(any));
    return result;
  }

  private List<Base> funcAll(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    List<Base> pc = new ArrayList<Base>();
    boolean all = true;
    for (Base item : focus) {
      pc.clear();
      pc.add(item);
      if (!convertToBoolean(execute(context, pc, exp.getParameters().get(0), false))) {
        all = false;
        break;
      }
    }
    result.add(new BooleanType(all));
    return result;
  }

  private List<Base> funcWhere(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    List<Base> pc = new ArrayList<Base>();
    for (Base item : focus) {
      pc.clear();
      pc.add(item);
      if (convertToBoolean(execute(context, pc, exp.getParameters().get(0), false)))
        result.add(item);
    }
    return result;
  }

  private List<Base> funcItem(ExecutionContext context, List<Base> focus, Expression exp) throws Exception {
    List<Base> result = new ArrayList<Base>();
    String s = convertToString(execute(context, focus, exp.getParameters().get(0), false));
    if (Utilities.isInteger(s) && Integer.parseInt(s) < focus.size())
      result.add(focus.get(Integer.parseInt(s)));
    return result;
  }

  private List<Base> funcEmpty(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    result.add(new BooleanType(focus.isEmpty()));
    return result;
  }

  private List<Base> funcNot(ExecutionContext context, List<Base> focus, Expression exp) {
    List<Base> result = new ArrayList<Base>();
    result.add(new BooleanType(!convertToBoolean(focus)));
    return result;
  }

  public class ElementDefinitionMatch {
    private ElementDefinition definition;
    private String fixedType;
    public ElementDefinitionMatch(ElementDefinition definition, String fixedType) {
      super();
      this.definition = definition;
      this.fixedType = fixedType;
    }
    public ElementDefinition getDefinition() {
      return definition;
    }
    public String getFixedType() {
      return fixedType;
    }

  }

  private void getChildTypesByName(String type, String name, Set<String> result) throws Exception {
    if (Utilities.noString(type))
      throw new Exception("No type provided in BuildToolPathEvaluator.getChildTypesByName");
    if (type.equals("xhtml"))
      return;
    String url = null;
    if (type.contains(".")) {
      url = "http://hl7.org/fhir/StructureDefinition/"+type.substring(0, type.indexOf("."));
    } else {
      url = "http://hl7.org/fhir/StructureDefinition/"+type;
    }
    String tail = "";
    StructureDefinition sd = worker.fetchResource(StructureDefinition.class, url);
    if (sd == null)
      throw new Exception("Unknown type "+type); // this really is an error, because we can only get to here if the internal infrastrucgture is wrong
    List<StructureDefinition> sdl = new ArrayList<StructureDefinition>();
    ElementDefinitionMatch m = null;
    if (type.contains("."))
      m = getElementDefinition(sd, type);
    if (m != null && hasDataType(m.definition)) {
    	if (m.fixedType != null)
    	 {
        StructureDefinition dt = worker.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+m.fixedType);
        if (dt == null)
          throw new Exception("unknown data type "+m.fixedType);
        sdl.add(dt);
      } else
	      for (TypeRefComponent t : m.definition.getType()) {
	        StructureDefinition dt = worker.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+t.getCode());
	        if (dt == null)
	          throw new Exception("unknown data type "+t.getCode());
	        sdl.add(dt);
	      }
    } else {
      sdl.add(sd);
      if (type.contains("."))
        tail = type.substring(type.indexOf("."));
    }

    for (StructureDefinition sdi : sdl) {
      String path = sdi.getSnapshot().getElement().get(0).getPath()+tail+".";
      if (name.equals("**")) {
        for (ElementDefinition ed : sdi.getSnapshot().getElement()) {
          if (ed.getPath().startsWith(path))
            for (TypeRefComponent t : ed.getType()) {
              if (t.hasCode() && t.getCodeElement().hasValue()) {
                String tn = null;
                if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                  tn = ed.getPath();
                else
                  tn = t.getCode();
                if (!result.contains(tn)) {
                  result.add(tn);
                  getChildTypesByName(tn, "**", result);
                }
              }
            }
        }      
      } else if (name.equals("*")) {
        for (ElementDefinition ed : sdi.getSnapshot().getElement()) {
          if (ed.getPath().startsWith(path) && !ed.getPath().substring(path.length()).contains("."))
            for (TypeRefComponent t : ed.getType()) {
              if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                result.add(ed.getPath());
              else if (t.getCode().equals("Resource"))
                result.addAll(worker.getResourceNames());
              else
                result.add(t.getCode());
            }
        }
      } else {
        if (name.endsWith("*")) 
          path = sdi.getSnapshot().getElement().get(0).getPath()+tail+"."+name.substring(0, name.length()-1);
        else
          path = sdi.getSnapshot().getElement().get(0).getPath()+tail+"."+name;

        ElementDefinitionMatch ed = getElementDefinition(sdi, path);
        if (ed != null) {
          if (ed.getFixedType() != null)
            result.add(ed.getFixedType());
          else
            for (TypeRefComponent t : ed.getDefinition().getType()) {
              if (Utilities.noString(t.getCode()))
                throw new Exception("Illegal reference to primative value attribute @ "+path);

              if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                result.add(path);
              else if (t.getCode().equals("Resource"))
                result.addAll(worker.getResourceNames());
              else
                result.add(t.getCode());
            }
        }
      }
    }
  }

  private ElementDefinitionMatch getElementDefinition(StructureDefinition sd, String path) {
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.getPath().equals(path)) {
        if (ed.hasNameReference()) {
          return getElementDefinitionByName(sd, ed.getNameReference());
        } else
          return new ElementDefinitionMatch(ed, null);
      }
      if (ed.getPath().endsWith("[x]") && path.startsWith(ed.getPath().substring(0, ed.getPath().length()-3)) && hasType(ed, path.substring(ed.getPath().length()-3)))
        return new ElementDefinitionMatch(ed, path.substring(ed.getPath().length()-3));
      if (ed.hasNameReference() && path.startsWith(ed.getPath()+".")) {
        ElementDefinitionMatch m = getElementDefinitionByName(sd, ed.getNameReference());
        return getElementDefinition(sd, m.definition.getPath()+path.substring(ed.getPath().length()));
      }
    }
    return null;
  }

  private boolean hasType(ElementDefinition ed, String s) {
  	for (TypeRefComponent t : ed.getType()) 
  		if (s.equalsIgnoreCase(t.getCode()))
  			return true;
  	return false;
	}

	private boolean hasDataType(ElementDefinition ed) {
    return ed.hasType() && !(ed.getType().get(0).getCode().equals("Element") || ed.getType().get(0).getCode().equals("BackboneElement"));
  }

  private ElementDefinitionMatch getElementDefinitionByName(StructureDefinition sd, String name) {
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (name.equals(ed.getName())) 
        return new ElementDefinitionMatch(ed, null);
    }
    return null;
  }

  // ----- Mapping Extensions -----------------------------------------------------------------------------------
  
  /*
   * This assumes that you have some source material in some kind of table that
   * defines a heirarchy of content, with a FHIR type for each, and a mapping statement
   * for each
   * 
   * To use this:
   *   - construct a Mapping context
   *   - work through the set of mapping statements, parsing them.
   *   - work through the source, constructing appropriate FHIR types (usually primitives)
   *     - for each, call all the maps for it   
   */
  public class PropertyAssignment {
    private String element;
    private Expression value;
    public PropertyAssignment(String element, Expression value) {
      super();
      this.element = element;
      this.value = value;
    }
    public String getElement() {
      return element;
    }
    public Expression getValue() {
      return value;
    }
    
  }
  
  public class MapTerm {
    private String elementName;
    private List<PropertyAssignment> properties;
    private boolean linkToResource; // source used -> instead '.'
    private MapTerm next;
    public String getElementName() {
      return elementName;
    }
    public void setElementName(String elementName) {
      this.elementName = elementName;
    }
    public List<PropertyAssignment> getProperties() {
      if (properties == null)
        properties = new ArrayList<PropertyAssignment>();
      return properties;
    }
    public void setProperties(List<PropertyAssignment> properties) {
      this.properties = properties;
    }
    public boolean isLinkToResource() {
      return linkToResource;
    }
    public void setLinkToResource(boolean linkToResource) {
      this.linkToResource = linkToResource;
    }
    public MapTerm getNext() {
      return next;
    }
    public void setNext(MapTerm next) {
      this.next = next;
    }
    
  }
  
  public class MapExpression extends MapTerm {
    private Expression condition;
    private String varName; // assignment
    public Expression getCondition() {
      return condition;
    }
    public void setCondition(Expression condition) {
      this.condition = condition;
    }
    public String getVarName() {
      return varName;
    }
    public void setVarName(String varName) {
      this.varName = varName;
    }
    
  }
  
  public interface ResourceFactory {
    /**
     * 
     * @param context
     * @param parentId
     * @param resourceType
     * @return
     * @throws Exception
     */
    public Resource createResource(MappingContext context, String parentId, String resourceType) throws Exception;

    /**
     * when an existing mapping is encountered
     * 
     * @param context
     * @param reference
     * @return
     */
    public Resource fetchResource(MappingContext context, String reference);
  }
  
  public static class Variable {
    Base value;
    String parentId;
    public Variable(Base value, String parentId) {
      super();
      this.value = value;
      this.parentId = parentId;
    }
    
  }
  
  public static class MappingContext {
    private List<MapExpression> allMaps = new ArrayList<MapExpression>();
    private Map<String, Variable> variables = new HashMap<String, Variable>();
    private ResourceFactory factory; // provided by the host
    protected IWorkerContext worker;
    
    public ResourceFactory getFactory() {
      return factory;
    }

    public void setFactory(ResourceFactory factory) {
      this.factory = factory;
    }

    private void reset(IWorkerContext worker) throws Exception {
      this.worker = worker;
      if (factory == null)
        throw new Exception("No Factory method provided");
      for (String n : variables.keySet()) {
        variables.put(n, null);
      }
    }
  }
  
  public static class BundleMappingContext extends MappingContext implements ResourceFactory {
    private Bundle bundle;
    private Map<String, Integer> ids = new HashMap<String, Integer>();
    private Map<String, Resource> resources = new HashMap<String, Resource>();
    
    public BundleMappingContext(Bundle bundle) {
      super();
      this.bundle = bundle;
      setFactory(this);
    }

    @Override
    public Resource createResource(MappingContext context, String parentId, String resourceType) throws Exception {
      Resource resource = org.hl7.fhir.instance.model.ResourceFactory.createResource(resourceType);

      String abbrev = worker.getAbbreviation(resourceType);
      if (!ids.containsKey(abbrev))
        ids.put(abbrev, 0);
      Integer i = ids.get(abbrev)+1;
      ids.put(abbrev, i);
      resource.setId((parentId == null ? "" : parentId+"-")+ abbrev+"."+i.toString());
      bundle.addEntry().setResource(resource);
      resources.put(resourceType+"/"+resource.getId(), resource);
      return resource;
    }

    @Override
    public Resource fetchResource(MappingContext context, String reference) {
      return resources.get(reference);
    }
    
  }
  public void initMapping(MappingContext context) throws Exception {
    context.reset(worker);
  }
  
  public List<MapExpression> parseMap(MappingContext context, String mapping) throws Exception{
    List<MapExpression> results = new ArrayList<MapExpression>();
    Lexer lexer = new Lexer(mapping, true);
    while (!lexer.done()) {
      results.add(parseMapExpression(context, lexer));
      if (!lexer.done()) {
        if (";".equals(lexer.current))
          lexer.next(true);
        else
          throw lexer.error("Unexpected token \""+lexer.current+"\" expecting \";\"");
      }
    }
    return results;    
  }
  
  public void performMapping(MappingContext context, Object appContext, Element value, List<MapExpression> maps) throws Exception {
    for (MapExpression map : maps) {
      performMapping(context, appContext, value, map);
    }
  }
  
  public void closeMapping(MappingContext context) {
    
  }
  
  private MapExpression parseMapExpression(MappingContext context, Lexer lexer) throws Exception {
    mappingExtensions = true;
    MapExpression result = new MapExpression();
    String token = getElementOrTypeName(lexer, true);
    if (token.equals("if")) {
      if (!"(".equals(lexer.current))
        throw lexer.error("Unexpected token \""+lexer.current+"\" expecting \"(\"");
      lexer.next(true);
      result.setCondition(parseExpression(lexer, true));
      if (!")".equals(lexer.current))
        throw lexer.error("Unexpected token \""+lexer.current+"\" expecting \")\"");
      lexer.next(true);
      token = lexer.take(true);
    }
    if (":=".equals(lexer.current)) {
      result.setVarName(checkVarName(token, lexer));
      if (context.variables.containsKey(result.getVarName()))
        throw lexer.error("Duplicate variable name "+result.getVarName());
      else
        context.variables.put(result.getVarName(), null);
      lexer.next(true);
      result.setElementName(getElementOrTypeName(lexer, true));
    } else
      result.setElementName(token);
    parseMapTerm(lexer, result);
    return result;
  }

  private String checkVarName(String s, Lexer lexer) throws Exception {
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (i == 0) {
        if (ch < 'a' || ch > 'z')
          throw lexer.error("Illegal character \""+ch+"\" in variable name '|"+s+"\"");
      } else 
        if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9'))
          throw lexer.error("Illegal character \""+ch+"\" in variable name '|"+s+"\"");
    }
    return s;
  }

  private void parseMapTerm(Lexer lexer, MapTerm result) throws Exception {
    if ("[".equals(lexer.current)) {
      lexer.next(false);
      while (!lexer.done() && !"]".equals(lexer.current)) {
        result.getProperties().add(parseAssignment(lexer));
        if (";".equals(lexer.current))
          lexer.next(false);
      }
      if (!"]".equals(lexer.current)) 
        throw lexer.error("Unexpected end of mapping statement expecting \"]\"");
      else
        lexer.next(true);
    }
    if ("->".equals(lexer.current)) {
      result.setLinkToResource(true);
      lexer.next(true);
      result.setNext(new MapTerm());
      result.getNext().setElementName(getResourceName(lexer, true));
    } else if (".".equals(lexer.current)) {
      result.setLinkToResource(false);
      lexer.next(true);
      result.setNext(new MapTerm());
      result.getNext().setElementName(getElementName(lexer, true));
    }  
    if (result.getNext() != null)
      parseMapTerm(lexer, result.getNext());
  }

  private PropertyAssignment parseAssignment(Lexer lexer) throws Exception {
    String element = getElementName(lexer, false);
    if (!":=".equals(lexer.current))
      throw lexer.error("Unexpected token \""+lexer.current+"\" expecting \":=\"");
    lexer.next(false);
    return new PropertyAssignment(element, parseExpression(lexer, true));
  }

  private String getElementName(Lexer lexer, boolean forMap) throws Exception {
    String s = lexer.current;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (i == 0) {
        if (ch < 'a' || ch > 'z')
          throw lexer.error("Illegal character \""+ch+"\" in element name '|"+lexer.current+"\"");
      } else 
        if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9') && ch != '[' && ch != ']')
          throw lexer.error("Illegal character \""+ch+"\" in element name '|"+lexer.current+"\"");
    }
    return lexer.take(forMap);
  }

  private String getElementOrTypeName(Lexer lexer, boolean forMap) throws Exception {
    String s = lexer.current;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (i == 0) {
        if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z'))
          throw lexer.error("Illegal character \""+ch+"\" in element name '|"+lexer.current+"\"");
      } else 
        if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9') && ch != '[' && ch != ']')
          throw lexer.error("Illegal character \""+ch+"\" in element name '|"+lexer.current+"\"");
    }
    return lexer.take(forMap);
  }

  private String getResourceName(Lexer lexer, boolean forMap) throws Exception {
    if (!worker.getResourceNames().contains(lexer.current))
      throw lexer.error("Unknown resource name \""+lexer.current+"\"");
    return lexer.take(forMap);
  }

  private void performMapping(MappingContext context, Object appContext, Element value, MapExpression map) throws Exception {
    // at this entry point we don't have any context. 
    if (map.getCondition() != null) {
      if (!convertToBoolean(evaluate(null, map.getCondition())))
        return;
    }
    
    // Our first business is to sort out the context
    String n = map.getElementName();
    Base focus = null;
    String parentId = null;
    
    if (Character.isUpperCase(n.charAt(0))) {
      // type name
      if (worker.getResourceNames().contains(n)) {
        Resource res = context.factory.createResource(context, null, n);
        parentId = res.getId();
        focus = res;
      } else 
        focus = new Factory().create(n);
    } else {
      // var name
      if (!context.variables.containsKey(n))
        throw new Exception("Unknown Variable name "+n);
      else {
        Variable var = context.variables.get(n);
        if (var == null)
          throw new Exception("Uninitialised Variable name "+n);
        focus = var.value;
        parentId = var.parentId;
      }
    }
    // ok, so we got here, we have ourselves a focus
    Variable var = processMappingDetails(context, appContext, parentId, focus, value, map);
    if (map.getVarName() != null) {
      context.variables.put(map.getVarName(), var);
    }    
  }

  private Variable processMappingDetails(MappingContext context, Object appContext, String parentId, Base focus, Element value, MapTerm map) throws Exception {
    // ok, handle the properties on the focus object first
    for (PropertyAssignment t : map.getProperties()) {
      List<Base> list = new ArrayList<Base>();
      ExecutionContext ec = new ExecutionContext(appContext, null, null);
      ec.value = value;
      List<Base> v = execute(ec, list, t.value, true);
      if (v.size() > 1)
        throw new Exception("Error: Assignment property value had more than one item in the outcome");
      if (v.size() > 0)
        focus.setProperty(t.element, v.get(0));
    }
    
    // now, see if there's nore to do 
    if (map.getNext() == null)
      return new Variable(focus, parentId);
    else if (map.isLinkToResource()) {
      Reference ref = (Reference) focus;
      if (ref.hasReference()) {
        if (!ref.getReference().startsWith(map.getNext().elementName+"/"))
          throw new Exception("Mismatch on existing reference - expected "+map.getNext().elementName+", found "+ref.getReference());
        Resource r = context.factory.fetchResource(context, ref.getReference());
        if (r == null)
          throw new Exception("Mismatch on existing reference - unable to resolve "+ref.getReference());
        return processMappingDetails(context, appContext, r.getId(), r, value, map.getNext());
      }
      else {      
        Resource r = context.factory.createResource(context, parentId, map.getNext().elementName);
        setChildProperty(focus, "reference", new StringType(map.getNext().elementName+"/"+r.getId()));
        return processMappingDetails(context, appContext, r.getId(), r, value, map.getNext());
      }
    } else {
      Base b = addChildProperty(focus, map.getNext().elementName);
      return processMappingDetails(context, appContext, parentId, b, value, map.getNext());
    }
  }

}