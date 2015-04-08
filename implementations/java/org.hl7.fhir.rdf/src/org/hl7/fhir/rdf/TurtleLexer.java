package org.hl7.fhir.rdf;

public class TurtleLexer {

  public enum TurtleTokenType {
    NULL, 
    TOKEN, SPECIAL
  }

  private String source;
  private int cursor; 
  private String token;
  private TurtleTokenType type;
  
  public TurtleLexer(String source) throws Exception {
    this.source = source;
    cursor = 0;
    readNext();
  }

  private void readNext() throws Exception {    
    if (cursor >= source.length()) {
      token = null;
      type = TurtleTokenType.NULL;
    } else if (source.charAt(cursor) == '"')
      throw new Exception("not supported yet");
    else if (source.charAt(cursor) == '[')
      throw new Exception("not supported yet");
    else if (source.charAt(cursor) == '(')
      throw new Exception("not supported yet");
    else if (source.charAt(cursor) == ';')
      readDelimiter();
    else if (Character.isAlphabetic(source.charAt(cursor)))
      readToken();
    
  }

  private void readDelimiter() {
    StringBuilder b = new StringBuilder();
    b.append(source.charAt(cursor));
    cursor++;
    token = b.toString();
    type = TurtleTokenType.SPECIAL;
    while (cursor < source.length() && Character.isWhitespace(source.charAt(cursor))) 
      cursor++;
  }

  private void readToken() {
    StringBuilder b = new StringBuilder();
    while (cursor < source.length() && isValidTokenChar(source.charAt(cursor))) {
      if (source.charAt(cursor) == '\\') {
        b.append(source.charAt(cursor));
        cursor++;        
      } 
      b.append(source.charAt(cursor));
      cursor++;
    }
    token = b.toString();
    type = TurtleTokenType.TOKEN;
    while (cursor < source.length() && Character.isWhitespace(source.charAt(cursor))) 
      cursor++;
  }

  private boolean isValidTokenChar(char c) {
    return Character.isAlphabetic(c) || Character.isDigit(c) || c == ':' || c == '\\' || c == '.';
  }

  public boolean done() {
    return type == TurtleTokenType.NULL;
  }

  public String next() throws Exception {
    String res = token;
    readNext();
    return res;
  }

  public TurtleTokenType peekType() {
    return type;
  }
  
  
}
