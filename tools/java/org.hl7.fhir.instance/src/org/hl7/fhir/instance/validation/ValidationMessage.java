package org.hl7.fhir.instance.validation;

import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.utils.ToolingExtensions;

public class ValidationMessage 
{
  public enum Source {
    ExampleValidator,
    ProfileValidator,
    ResourceValidator, 
    InstanceValidator,
    Schema,
    Schematron
  }
  
  private Source source;
  private String location;
  private String message;
  private String type;
  private IssueSeverity level;
  
  
  public ValidationMessage(Source source, String type, String path, String message, IssueSeverity level) {
    super();
    this.location = path;
    this.message = message;
    this.level = level;
    this.source = source;
    this.type = type;
  }
  
  public ValidationMessage() {
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public IssueSeverity getLevel() {
    return level;
  }
  public void setLevel(IssueSeverity level) {
    this.level = level;
  }
  
  public Source getSource() {
    return source;
  }
  public void setSource(Source source) {
    this.source = source;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String summary() {
    return level.toString()+" @ "+location+": "+message +(source != null ? " (src = "+source+")" : "");
  }

  public OperationOutcomeIssueComponent asIssue(OperationOutcome op) throws Exception {
    OperationOutcomeIssueComponent issue = new OperationOutcome.OperationOutcomeIssueComponent();
    if (type != null) {
      issue.setType(new Coding());
      issue.getType().setSystemSimple("http://hl7.org/fhir/issue-type");
      issue.getType().setCodeSimple(type);
    }
    if (location != null) {
      StringType s = new StringType();
      s.setValue(location);
      issue.getLocation().add(s);
    }
    issue.setSeveritySimple(level);
    issue.setDetailsSimple(message);
    if (source != null) {
      issue.getExtensions().add(ToolingExtensions.makeIssueSource(source));      
    }
    return issue;
  }
  
}