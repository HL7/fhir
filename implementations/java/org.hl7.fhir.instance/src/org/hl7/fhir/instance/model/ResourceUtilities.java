package org.hl7.fhir.instance.model;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

/**
 * Decoration utilities for various resource types
 * @author Grahame
 *
 */
public class ResourceUtilities {

	public static boolean isAnError(OperationOutcome error) {
		for (OperationOutcomeIssueComponent t : error.getIssue())
			if (t.getSeveritySimple() == IssueSeverity.error)
				return true;
			else if (t.getSeveritySimple() == IssueSeverity.fatal)
				return true;
		return false;
	}
	
	public static String getErrorDescription(OperationOutcome error) {  
		if (error.getText() != null && error.getText().getDiv() != null)
			return new XhtmlComposer().composePlainText(error.getText().getDiv());
		
		StringBuilder b = new StringBuilder();
		for (OperationOutcomeIssueComponent t : error.getIssue())
			if (t.getSeveritySimple() == IssueSeverity.error)
				b.append("Error:" +t.getDetailsSimple()+"\r\n");
			else if (t.getSeveritySimple() == IssueSeverity.fatal)
				b.append("Fatal:" +t.getDetailsSimple()+"\r\n");
			else if (t.getSeveritySimple() == IssueSeverity.warning)
				b.append("Warning:" +t.getDetailsSimple()+"\r\n");
			else if (t.getSeveritySimple() == IssueSeverity.information)
				b.append("Information:" +t.getDetailsSimple()+"\r\n");
		return b.toString();
  }

}
