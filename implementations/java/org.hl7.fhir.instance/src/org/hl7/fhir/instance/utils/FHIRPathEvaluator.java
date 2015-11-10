package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.utilities.Utilities;

public abstract class FHIRPathEvaluator {

	abstract protected void getChildrenByName(Base item, String name, List<Base> result);
	
	/**
	 * syntax check and determine if the paths referred to in the path are valid
	 * 
	 * @param context - the logical type against which this path is applied
	 * @param path - the FHIR Path statement to check
	 */
  public void check(String context, String path) {
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
    return execute(list, exp, true);
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
  @SuppressWarnings("rawtypes")
	public String convertToString(List<Base> items) {
  	StringBuilder b = new StringBuilder();
  	boolean first = true;
  	for (Base item : items) {
  		if (first) 
  			first = false;
  		else
  			b.append(',');
  		if (item instanceof PrimitiveType)
  			b.append(((PrimitiveType) item).asStringValue());
  		else 
  			b.append(item.getClass().getName());
  	}
  	return b.toString();
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
			return current.charAt(1) == '%' || (current.charAt(1) >= '0' && current.charAt(1) <= '9') || current.equals("true") || current.equals("false");
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

	private List<Base> execute(List<Base> context, Expression exp, boolean atEntry) {
		List<Base> work = new ArrayList<Base>();
		// functions are evaluated on the collection
		if (exp.getFunction() != null) {
			work.addAll(evaluateFunction(context, exp));
		} else 
			for (Base item : context) {
				work.addAll(execute(item, exp, atEntry));
			}
		if (exp.getNext() == null)
			return work;
		else
			return execute(work, exp.getNext(), false);
	}

	private List<Base> execute(Base item, Expression exp, boolean atEntry) {
		List<Base> result = new ArrayList<Base>(); 
   if (atEntry && Character.isUpperCase(exp.getName().charAt(0))) {// special case for start up
	   if (item instanceof Resource && ((Resource) item).getResourceType().toString().equals(exp.getName()))  
	     result.add(item);
   } else
  	 getChildrenByName(item, exp.name, result);
   return result;
	}

	
	private List<Base> evaluateFunction(List<Base> context, Expression exp) {
		switch (exp.getFunction()) {
		case Empty : return funcEmpty(context, exp);
		case Item : return funcItem(context, exp);
		case Where : return funcWhere(context, exp);
		case All : return funcAll(context, exp);
		case Any : return funcAny(context, exp);
		case First : return funcFirst(context, exp);
		case Last : return funcLast(context, exp);
		case Tail : return funcTail(context, exp);
		case Count : return funcCount(context, exp);
		case AsInteger : return funcAsInteger(context, exp);
		case StartsWith : return funcStartsWith(context, exp);
		case Length : return funcLength(context, exp);
		case Matches : return funcMatches(context, exp);
		case Distinct : return funcDistinct(context, exp);
		}
		throw new Error("not Implemented yet");
	}

	private List<Base> funcDistinct(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcMatches(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcLength(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcStartsWith(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcAsInteger(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcCount(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcTail(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		for (int i = 1; i < context.size(); i++)
   		result.add(context.get(i));
	  return result;
	}

	private List<Base> funcLast(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		if (context.size() > 0)
   		result.add(context.get(context.size()-1));
	  return result;
	}

	private List<Base> funcFirst(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		if (context.size() > 0)
   		result.add(context.get(0));
	  return result;
	}

	private List<Base> funcAny(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcAll(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcWhere(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		throw new Error("not Implemented yet");
	}

	private List<Base> funcItem(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		String s = convertToString(context);
		if (Utilities.isInteger(s))
   		result.add(context.get(Integer.parseInt(s)));
	  return result;
	}

	private List<Base> funcEmpty(List<Base> context, Expression exp) {
		List<Base> result = new ArrayList<Base>();
		result.add(new BooleanType(context.isEmpty()));
	  return result;
	}
}