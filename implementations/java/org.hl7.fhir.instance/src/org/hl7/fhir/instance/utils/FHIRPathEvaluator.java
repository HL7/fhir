package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;

public abstract class FHIRPathEvaluator {

	abstract protected void getChildrenByName(Base item, String name, List<Base> result);
	
	/**
	 * syntax check and determine if the paths referred to in the path are valid
	 * 
	 * @param context - the logical type against which this path is applied
	 * @param path - the FHIR Path statement to check
	 */
  public Expression check(String context, String path) {
    throw new Error("Not Done Yet");
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
    list.add(base);
    return execute(list, list, exp, true);
  }

  /**
   * evaluate a path and return true or false (e.g. for an invariant)
   * 
   * @param base - the object against which the path is being evaluated
	 * @param path - the FHIR Path statement to use
   * @return
   * @throws Exception 
   */
  public boolean evaluateToBoolean(Base base, String path) throws Exception {
  	return convertToBoolean(evaluate(base, path));
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
		Empty, Item, Where, All, Any, First, Last, Tail, Count, AsInteger, StartsWith, Length, Matches, Distinct;

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
			if (name.equals("distinct"))
				return Function.Distinct;
			return null;
		}
	}

	public enum Operation {
		Equals, NotEquals, LessThen, Greater, LessOrEqual, GreaterOrEqual, In, Plus, Minus, Divide, Multiply;

		public static Operation fromCode(String name) {
			if (name.equals("="))
				return Operation.Equals;
			if (name.equals("!="))
				return Operation.NotEquals;
			if (name.equals(">"))
				return Operation.Greater;
			if (name.equals("<"))
				return Operation.LessThen;
			if (name.equals(">="))
				return Operation.GreaterOrEqual;
			if (name.equals("<="))
				return Operation.LessOrEqual;
			if (name.equals("in"))
				return Operation.In;
			if (name.equals("+"))
				return Operation.Plus;
			if (name.equals("-"))
				return Operation.Minus;
			if (name.equals("/"))
				return Operation.Divide;
			if (name.equals("*"))
				return Operation.Multiply;
			return null;
			
		}
	}

	private class Expression {

		private String name;
		private String constant;
		private Function function;
		private List<Expression> parameters; // will be created if there is a function
		private Operation operation;
		private Expression next;

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
		}
		public Operation getOperation() {
			return operation;
		}
		public void setOperation(Operation operation) {
			this.operation = operation;
		}
		public Expression getNext() {
			return next;
		}
		public void setNext(Expression next) {
			this.next = next;
		}
		public List<Expression> getParameters() {
			return parameters;
		}
	}

	private class Lexer {
		private String path;
		private int cursor;
		private String current;
		private int currentStart;

		public Lexer(String path) {
			this.path = path;
			next();
		}
		public String getPath() {
			return path;
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
			return current.charAt(1) == '"' || (current.charAt(1) >= '0' && current.charAt(1) <= '9') || current.equals("true") || current.equals("false");
		}

		public String take() {
			String s = current;
			next();
			return s;
		}

		public boolean isToken() {
			if (Utilities.noString(current))
				return false;

			if (current.equals("$") || current.equals("*") || current.equals("**"))
				return true;

			if ((current.charAt(1) >= 'A' && current.charAt(1) <= 'Z') || (current.charAt(1) >= 'a' && current.charAt(1) <= 'z')) {
				for (int i = 0; i < current.length(); i++) 
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
			return new Exception("Error in "+path+" at "+Integer.toString(offset+1)+": "+msg);
		}

		public void next() {
			//	  procedure Grab(length : Integer);
			//	  begin
			//	    FCurrent := copy(path, FCurrentStart, length);
			//	    inc(cursor, length);
			//	  end;
			current = null;
			while (cursor < path.length() && Character.isWhitespace(path.charAt(cursor)))
				cursor++;
			currentStart = cursor;
			if (cursor < path.length()) {
				char ch = path.charAt(cursor);
				if (ch == '!' || ch == '>' || ch == '<')  {
					cursor++;
					if (cursor < path.length() && path.charAt(cursor) == '=') 
						cursor++;
					current = path.substring(currentStart, cursor);
				} else if (ch == '*') {
					cursor++;
					if (cursor < path.length() && path.charAt(cursor) == '*') 
						cursor++;
					current = path.substring(currentStart, cursor);
				} else if (ch >= '0' && ch <= '9') {
					while (cursor < path.length() && ((path.charAt(cursor) >= '0' && path.charAt(cursor) <= '9') || path.charAt(cursor) == '.')) 
						cursor++;
					current = path.substring(currentStart, cursor);
				}  else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
					while (cursor < path.length() && ((path.charAt(cursor) >= 'A' && path.charAt(cursor) <= 'Z') || (path.charAt(cursor) >= 'a' && path.charAt(cursor) <= 'z') || 
							(path.charAt(cursor) >= '0' && path.charAt(cursor) <= '9') || path.charAt(cursor) == '[' || path.charAt(cursor) == ']' || path.charAt(cursor) == '*')) 
						cursor++;
					current = path.substring(currentStart, cursor);
				} else if (ch == '%') {
					cursor++;
					while (cursor < path.length() && ((path.charAt(cursor) >= 'A' && path.charAt(cursor) <= 'Z') || (path.charAt(cursor) >= 'a' && path.charAt(cursor) <= 'z') || 
							(path.charAt(cursor) >= '0' && path.charAt(cursor) <= '9') || path.charAt(cursor) == ':' || path.charAt(cursor) == '-'))
						cursor++;
					current = path.substring(currentStart, cursor);
				} else { // if CharInSet(ch, ['.', ',', '(', ')', '=', '$']) then
					cursor++;
					current = path.substring(currentStart, cursor);
				}
			}
		}

		public boolean isOp() {
			return Operation.fromCode(current) != null;
		}
		public boolean done() {
			return currentStart >= path.length();
		}

	}

	private Expression parseExpression(Lexer lexer) throws Exception {
		Expression result = new Expression();
		int c = lexer.getCurrentStart();
		if (lexer.isConstant()) 
			result.setConstant(lexer.take());
		else {
			if (!lexer.isToken()) 
				throw lexer.error("Found "+lexer.getCurrent()+" expecting a token name");
			result.setName(lexer.take());
			if (lexer.getCurrent().equals("(")) {
				Function f = Function.fromCode(result.getName());  
				if (f == null)
					throw lexer.error("The name "+result.getName()+" is not a valid function name");
				result.setFunction(f);
				lexer.next();
				while (!lexer.getCurrent().equals(")")) 
					result.getParameters().add(parseExpression(lexer));
				lexer.next();
				checkParameters(lexer, c, result);
			}
			if (lexer.current.equals(".")) {
				lexer.next();
				result.setNext(parseExpression(lexer));
			}
		}
		if (lexer.isOp()) {
			result.setOperation(Operation.fromCode(lexer.getCurrent()));
			lexer.next();
			result.setNext(parseExpression(lexer));
		}
		return result;
	}

	private Expression parse(String path) throws Exception {
		Lexer lexer = new Lexer(path);
		if (lexer.done())
			throw lexer.error("Path cannot be empty");
		Expression result = parseExpression(lexer);
		if (!lexer.done())
			throw lexer.error("Premature expression termination at unexpected token");
		return result;
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
		case Distinct: ; // no chECK
		return true;
		}
		return false;
	}

	private List<Base> execute(List<Base> originalContext, List<Base> context, Expression exp, boolean atEntry) {
		List<Base> work = new ArrayList<Base>();
		// functions are evaluated on the collection
		if (exp.getFunction() != null) {
			work.addAll(evaluateFunction(originalContext, context, exp));
		} else if (exp.getConstant() != null) 
      work.add(readConstant(exp.getConstant()));
    else 
			for (Base item : context) 
				work.addAll(execute(originalContext, item, exp, atEntry));
			
	
		if (exp.getNext() == null)
			return work;
		else if (exp.getOperation() != null) {
			List<Base> work2 = execute(originalContext, context, exp.next, false);
      return operate(work, exp.getOperation(), work2);
		} else
			return execute(originalContext, work, exp.getNext(), false);
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
		case Divide: return opDivide(left, right);
		case Multiply: return opMultiply(left, right);
		default: 
			return null;
		}
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

	private List<Base> opDivide(List<Base> left, List<Base> right) {
	  throw new Error("The operation Divide is not done yet");
	}

	private List<Base> opMultiply(List<Base> left, List<Base> right) {
	  throw new Error("The operation Multiply is not done yet");
	}

	private Type readConstant(String constant) {
		if (constant.equals("true")) 
			return new BooleanType(true);
		else if (constant.equals("false")) 
			return new BooleanType(false);
		else if (Utilities.isInteger(constant))
			return new IntegerType(constant);
		else if (Utilities.isDecimal(constant))
			return new DecimalType(constant);
		else
			return new StringType(constant);
	}

	private List<Base> execute(List<Base> originalContext, Base item, Expression exp, boolean atEntry) {
		List<Base> result = new ArrayList<Base>(); 
   if (atEntry && Character.isUpperCase(exp.getName().charAt(0))) {// special case for start up
	   if (item instanceof Resource && ((Resource) item).getResourceType().toString().equals(exp.getName()))  
	     result.add(item);
   } else if (exp.getName().equals("$"))
     result.addAll(originalContext);
   else
  	 getChildrenByName(item, exp.name, result);
   return result;
	}

	
	private List<Base> evaluateFunction(List<Base> originalContext, List<Base> context, Expression exp) {
		switch (exp.getFunction()) {
		case Empty : return funcEmpty(originalContext, context, exp);
		case Item : return funcItem(originalContext, context, exp);
		case Where : return funcWhere(originalContext, context, exp);
		case All : return funcAll(originalContext, context, exp);
		case Any : return funcAny(originalContext, context, exp);
		case First : return funcFirst(originalContext, context, exp);
		case Last : return funcLast(originalContext, context, exp);
		case Tail : return funcTail(originalContext, context, exp);
		case Count : return funcCount(originalContext, context, exp);
		case AsInteger : return funcAsInteger(originalContext, context, exp);
		case StartsWith : return funcStartsWith(originalContext, context, exp);
		case Length : return funcLength(originalContext, context, exp);
		case Matches : return funcMatches(originalContext, context, exp);
		case Distinct : return funcDistinct(originalContext, context, exp);
		}
		throw new Error("not Implemented yet");
	}

	private List<Base> funcDistinct(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcMatches(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
	  String p = convertToString(execute(originalContext, context, exp.getParameters().get(0), false));

	  for (Base item : context) {
	    String s = convertToString(item);
	    if (s.matches(p)) 
	    	result.add(item);
	  }
	  return result;
	}

	private List<Base> funcLength(List<Base> originalContext, List<Base> context, Expression exp) {
	  int l = 0;
	  for (Base item : context) {
	    String s = convertToString(item);
	    l = Math.max(l, s.length());
	  }
		List<Base> result = new ArrayList<Base>();
    result.add(new IntegerType(l));
	  return result;
	}

	private List<Base> funcStartsWith(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
	  String sw = convertToString(execute(originalContext, context, exp.getParameters().get(0), false));

	  for (Base item : context) {
	    String s = convertToString(item);
	    if (s.startsWith(sw)) 
	        result.add(item);
	  }
	  return result;
	}

	private List<Base> funcAsInteger(List<Base> originalContext, List<Base> context, Expression exp) {
	  String s = convertToString(context);
	  List<Base> result = new ArrayList<Base>();
	  if (Utilities.isInteger(s))
      result.add(new IntegerType(s));
	  return result;
	}

	private List<Base> funcCount(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
	  result.add(new IntegerType(context.size()));
	  return result;
	}

	private List<Base> funcTail(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		for (int i = 1; i < context.size(); i++)
   		result.add(context.get(i));
	  return result;
	}

	private List<Base> funcLast(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		if (context.size() > 0)
   		result.add(context.get(context.size()-1));
	  return result;
	}

	private List<Base> funcFirst(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		if (context.size() > 0)
   		result.add(context.get(0));
	  return result;
	}

	private List<Base> funcAny(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		List<Base> pc = new ArrayList<Base>();
		boolean any = false;
		for (Base item : context) {
			pc.clear();
			pc.add(item);
			if (convertToBoolean(execute(originalContext, pc, exp.getParameters().get(0), false))) {
				any = true;
				break;
			}
		}
		result.add(new BooleanType(any));
		return result;
	}

	private List<Base> funcAll(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		List<Base> pc = new ArrayList<Base>();
		boolean all = true;
		for (Base item : context) {
			pc.clear();
			pc.add(item);
			if (!convertToBoolean(execute(originalContext, pc, exp.getParameters().get(0), false))) {
				all = false;
				break;
			}
		}
		result.add(new BooleanType(all));
		return result;
	}

	private List<Base> funcWhere(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		List<Base> pc = new ArrayList<Base>();
	  for (Base item : context) {
	  	pc.clear();
	  	pc.add(item);
	  	if (convertToBoolean(execute(originalContext, pc, exp.getParameters().get(0), false)))
	  			result.add(item);
	  }
	  return result;
	}

	private List<Base> funcItem(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		String s = convertToString(execute(originalContext, context, exp.getParameters().get(0), false));
		if (Utilities.isInteger(s) && Integer.parseInt(s) < context.size())
   		result.add(context.get(Integer.parseInt(s)));
	  return result;
	}

	private List<Base> funcEmpty(List<Base> originalContext, List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		result.add(new BooleanType(context.isEmpty()));
	  return result;
	}
}