package org.hl7.fhir.r4.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.Utilities;

public class GraphQL {


  public static class Value {

  }

  public static class VariableValue extends Value {
    public VariableValue(String value) {
      this.value = value;
    }

    private String value;

  }

  public static class NumberValue extends Value {
    public NumberValue(String value) {
      this.value = value;
    }

    private String value;
  }

  public static class NameValue extends Value {
    public NameValue(String value) {
      this.value = value;
    }

    private String value;
  }

  public static class StringValue extends Value {
    public StringValue(String value) {
      this.value = value;
    }

    private String value;
  }

  public static class ObjectValue extends Value {
    private List<Argument> fields = new ArrayList<Argument>();
  }

  public static class Argument {
    private String name;
    private List<Value> values = new ArrayList<Value>();
  }

  public static class Directive {
    private String name;
    private List<Argument> arguments = new ArrayList<Argument>();
  }

  public static class Field {
    private String alias;
    private String name;
    private List<Selection> selectionSet = new ArrayList<Selection>();
    private List<Argument> arguments = new ArrayList<Argument>();
    private List<Directive> directives = new ArrayList<Directive>();
  }

  public static class FragmentSpread {
    private String name;
    private List<Directive> directives = new ArrayList<Directive>();
  }

  public static class Selection {
    private Field field;
    private Fragment inlineFragment;
    private FragmentSpread fragmentSpread;
  }

  public static class Variable {
    private String name;
    private String type;
    private Value defaultValue;
  }

  public enum  OperationType { Query, Mutation }
  public static class Operation {
    private String name;
    private OperationType operationType;
    private List<Selection> selectionSet = new ArrayList<Selection>();
    private List<Variable> variables = new ArrayList<Variable>();
    private List<Directive> directives = new ArrayList<Directive>();
  }

  public static class Fragment {
    private String name;
    private String typeCondition;
    private List<Selection> selectionSet = new ArrayList<Selection>();
    private List<Directive> directives = new ArrayList<Directive>();
  }

  public static class Document {
    private List<Operation> operations = new ArrayList<Operation>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
  }

  static Document parse(String source) throws IOException {
    Parser p = new Parser();
    p.reader = new StringReader(source);
    Document result = new Document();
    p.parseDocument(result);
    return result;
  }


  public enum LexType { Null, Name, Punctuation, String, Number}
  private static class Parser {

    // lexer
    private StringBuilder token = new StringBuilder();
    private char peek = 0;
    private LexType lexType;
    private Reader reader;

    private char getNextChar() throws IOException {
      if (peek != 0) {
        char res = peek;
        peek = 0;
        return res;
      } else {
        return (char) reader.read();
      }
    }

    private boolean hasName() {
      return (lexType == LexType.Name) && !Utilities.noString(token.toString());
    }

    private boolean hasName(String name) { 
      return (lexType == LexType.Name) && (token.toString().equals(name));
    }

    private boolean hasPunctuation(String punc) {
      return (lexType == LexType.Punctuation) && (token.toString().equals(punc));
    }


    private String consumeName() throws IOException {
      if (lexType != LexType.Name)
        throw new IOException("Found '"+token.toString()+"' expecting a name");
      String result = token.toString();
      next();
      return result;
    }

    private void consumeName(String name) throws IOException{
      if (lexType != LexType.Name)
        throw new IOException("Found '"+token.toString()+"' expecting a name");
      if (token.toString() != name)
        throw new IOException("Found '"+token.toString()+"' expecting '"+name+"'");
      next();
    }

    private void consumePunctuation(String punc) throws IOException {
      if (lexType != LexType.Punctuation)
        throw new IOException("Found '"+token.toString()+"' expecting '"+punc+"'");
      if (token.toString() != punc)
        throw new IOException("Found '"+token.toString()+"' expecting '"+punc+"'");
      next();
    }

    private void pushChar(char ch) {
      peek = ch;
    }

    private void skipIgnore() throws IOException {
      char ch = getNextChar();
      while (Utilities.existsInList(ch, ' ', '\t', '\r', '\t', ',', '\ufeff'))
        ch = getNextChar();
      if ((ch == '#')) {
        while (!Utilities.existsInList(ch, '\r', '\t'))
          ch = getNextChar();
        pushChar(ch);
        skipIgnore();
      } else
        pushChar(ch);
    }

    private void next() throws IOException {
      skipIgnore();
      token = new StringBuilder();
      if (!reader.ready() && (peek == 0)) 
        lexType = LexType.Null;
      else {
        char ch = getNextChar();
        if (Utilities.existsInList(ch, '!', '$', '(', ')', '.', ':', '=', '@', '[', ']', '{', '|', '}')) {
          lexType = LexType.Punctuation;
          token.append(ch);
        } else if (ch == '.') {
          do {
            token.append(ch);
            ch = getNextChar();
          } while (ch == '.');
          pushChar(ch);
          if (token.length() != 3) 
            throw new IOException("Found '"+token.toString()+"' expecting '...'");
        } else if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == '_') {
          lexType = LexType.Name;
          do {
            token.append(ch);
            ch = getNextChar();
          } while (((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_') );
          pushChar(ch);
        } else if ((ch >= '0' && ch <= '9') || ch == '-') {
          lexType = LexType.Number;
          do {
            token.append(ch);
            ch = getNextChar();
          } while ((ch >= '0' && ch <= '9') || ((ch == '.') && !token.toString().contains(".")) ||  ((ch == 'e') && !token.toString().contains("e")));
          pushChar(ch);
        } else if (ch == '"') {
          lexType = LexType.String;
          do {
            ch = getNextChar();
            if (ch == '\\')  {
              if (!reader.ready())
                throw new IOException("premature termination of GraphQL during a string constant");
              ch = getNextChar();
              switch (ch) {
              case '"':token.append('"'); break;
              case '\\':token.append('\\'); break;
              case '/':token.append('/'); break;
              case 'n':token.append('\n'); break;
              case 'r':token.append('\r'); break;
              case 't':token.append('\t'); break;
              case 'u':
              {
                throw new Error("Not done yet");
                //                setLength(hex, 4);
                //                hex[1] = getNextChar;
                //                hex[2] = getNextChar;
                //                hex[3] = getNextChar;
                //                hex[4] = getNextChar;
                //                token.append(chr(StrToInt("$"+hex)));
              }
              default:
                throw new IOException("not supported in GraphQL: \\"+ch);
              }
              ch = 0;
            } else if (ch != '\'') 
              token.append(ch);
          } while (reader.ready() && ch != '\'');
          if (!reader.ready())
            throw new IOException("premature termination of GraphQL during a string constant");
          }
        else
          throw new IOException("Not done yet: "+ch); // syntax error?
      }
    }


    private Argument parseArgument() throws IOException {
      Argument result = new Argument();
      result.name = consumeName();
      consumePunctuation(":");
      if (hasPunctuation("[")) {
        consumePunctuation("[");
        while (!hasPunctuation("]"))
          result.values.add(parseValue());
        consumePunctuation("]");
      } else
        result.values.add(parseValue());
      return result;
    }

    private Directive parseDirective() throws IOException {
      Directive result = new Directive();
      consumePunctuation("@");
      result.name = consumeName();
      if (hasPunctuation("(")) {
        consumePunctuation("(");
        do
          result.arguments.add(parseArgument());
        while (!hasPunctuation(")"));
        consumePunctuation(")");
      }
      return result;
    }

    private void parseDocument(Document doc) throws IOException, IOException, Error {
      if (!hasName()) {
        Operation op = new Operation();
        parseOperationInner(op);
        doc.operations.add(op);
      } else {
        while (reader.ready() || peek != 0) {
          String s = consumeName();
          if (s.equals("mutation") || s.equals("query"))
            doc.operations.add(parseOperation(s));
          else if (s.equals("fragment"))
            doc.fragments.add(parseFragment());
          else
            throw new Error("Not done yet"); // doc.Operations.add(parseOperation(s))?
        }
      }
    }

    private Field parseField() throws IOException {
      Field result = new Field();
      result.name = consumeName();
      if (hasPunctuation(":")) {
        result.alias = result.name;
        consumePunctuation(":");
        result.name = consumeName();
      }
      if (hasPunctuation("(")) {
        consumePunctuation("(");
        do
          result.arguments.add(parseArgument());
        while (!hasPunctuation(")"));
        consumePunctuation(")");
      }
      while (hasPunctuation("@")) 
        result.directives.add(parseDirective());
      if (hasPunctuation("{"))
      {
        consumePunctuation("{");
        do
          result.selectionSet.add(parseSelection());
        while (!hasPunctuation("}"));
        consumePunctuation("}");
      }
      return result;
    }

    private void parseFragmentInner(Fragment fragment) throws IOException {

      while (hasPunctuation("@"))
        fragment.directives.add(parseDirective());
      consumePunctuation("{");
      do
        fragment.selectionSet.add(parseSelection());
      while (!hasPunctuation("}"));
      consumePunctuation("}");
    }

    private Fragment parseFragment() throws IOException {
      Fragment result = new Fragment();
      result.name = consumeName();
      consumeName("on");
      result.typeCondition = consumeName();
      parseFragmentInner(result);
      return result;
    }

    private FragmentSpread parseFragmentSpread() throws IOException {
      FragmentSpread result = new FragmentSpread();
      result.name = consumeName();
      while (hasPunctuation("@")) 
        result.directives.add(parseDirective());
      return result;
    }

    private Fragment parseInlineFragment() throws IOException {
      Fragment result = new Fragment();
      if (hasName("on")) {
        consumeName("on");
        result.typeCondition = consumeName();
      }
      parseFragmentInner(result);
      return result;
    }

    private Operation parseOperation(String name) throws IOException {
      Operation result = new Operation();
      if (name.equals("mutation")) {
        result.operationType = OperationType.Mutation;
        if (hasName())
          result.name = consumeName();
      } else if (name.equals("query")) {
        result.operationType = OperationType.Query;
        if (hasName())
          result.name = consumeName();
      } else
        result.name = name;
      parseOperationInner(result);
      return result;
    }

    private void parseOperationInner(Operation op) throws IOException {
      if (hasPunctuation("("))
      {
        consumePunctuation("(");
        do
          op.variables.add(parseVariable());
        while (!hasPunctuation(")"));
        consumePunctuation(")");
      }
      while (hasPunctuation("@"))
        op.directives.add(parseDirective());
      if (hasPunctuation("{"))
      {
        consumePunctuation("{");
        do
          op.selectionSet.add(parseSelection());
        while (!hasPunctuation("}"));
        consumePunctuation("}");
      }
    }

    private Selection parseSelection() throws IOException {
      Selection result = new Selection();
      if (hasPunctuation("...")) {
        consumePunctuation("...");
        if (hasName() && (!token.toString().equals("on")))
          result.fragmentSpread = parseFragmentSpread();
        else
          result.inlineFragment = parseInlineFragment();
      } else
        result.field = parseField();
      return result;
    }

    private Value parseValue() throws IOException {
      Value result = null;
      switch (lexType) {
      case Null: throw new IOException("Attempt to read a value after reading off the end of the GraphQL statement");
      case Name: result = new NameValue(token.toString()); break;
      case Punctuation:
        if (hasPunctuation("$")) {
          consumePunctuation("$");
          result = new VariableValue(token.toString());
        } else if (hasPunctuation("{")) {
          consumePunctuation("{");
          result = new ObjectValue();
          while (!hasPunctuation("}"))
            ((ObjectValue) result).fields.add(parseArgument());
        } else
          throw new IOException("Attempt to read a value at '"+token.toString()+"'");
        break;
      case String: result = new StringValue(token.toString()); break;
      case Number: result = new NumberValue(token.toString());
      }

      next();
      return result;
    }

    private Variable parseVariable() throws IOException {
      Variable result = new Variable();
      consumePunctuation("$");
      result.name = consumeName();
      consumePunctuation(":");
      result.type = consumeName();
      if (hasPunctuation("==")) {
        consumePunctuation("==");
        result.defaultValue = parseValue();
      }
      return result;
    }


  }
}
