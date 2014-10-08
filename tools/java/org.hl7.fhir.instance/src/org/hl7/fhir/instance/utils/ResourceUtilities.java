package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.OperationOutcome;
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
			if (t.getSeverity() == IssueSeverity.ERROR)
				return true;
			else if (t.getSeverity() == IssueSeverity.FATAL)
				return true;
		return false;
	}
	
	public static String getErrorDescription(OperationOutcome error) {  
		if (error.getText() != null && error.getText().getDiv() != null)
			return new XhtmlComposer().setXmlOnly(true).composePlainText(error.getText().getDiv());
		
		StringBuilder b = new StringBuilder();
		for (OperationOutcomeIssueComponent t : error.getIssue())
			if (t.getSeverity() == IssueSeverity.ERROR)
				b.append("Error:" +t.getDetails()+"\r\n");
			else if (t.getSeverity() == IssueSeverity.FATAL)
				b.append("Fatal:" +t.getDetails()+"\r\n");
			else if (t.getSeverity() == IssueSeverity.WARNING)
				b.append("Warning:" +t.getDetails()+"\r\n");
			else if (t.getSeverity() == IssueSeverity.INFORMATION)
				b.append("Information:" +t.getDetails()+"\r\n");
		return b.toString();
  }

}
