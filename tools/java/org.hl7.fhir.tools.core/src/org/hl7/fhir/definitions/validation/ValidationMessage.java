package org.hl7.fhir.definitions.validation;

public class ValidationMessage 
{
  public enum Level {
    Hint,
    Warning,
    Error
  }
  
  private String message;
  private Level level;
  public ValidationMessage(String message, Level level) {
    super();
    this.message = message;
    this.level = level;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Level getLevel() {
    return level;
  }
  public void setLevel(Level level) {
    this.level = level;
  }
  
}