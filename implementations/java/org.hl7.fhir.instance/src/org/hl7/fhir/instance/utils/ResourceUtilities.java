package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Bundle.BundleLinkComponent;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;
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
		if (error.hasText() && error.getText().hasDiv())
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

	public static Resource getById(Bundle feed, ResourceType type, String reference) {
	  for (BundleEntryComponent item : feed.getEntry()) {
	    if (item.getResource().getId().equals(reference) && item.getResource().getResourceType() == type)
	      return item.getResource();
	  }
	  return null;
  }

  public static BundleEntryComponent getEntryById(Bundle feed, ResourceType type, String reference) {
    for (BundleEntryComponent item : feed.getEntry()) {
      if (item.getResource().getId().equals(reference) && item.getResource().getResourceType() == type)
        return item;
    }
    return null;
  }

	public static String getLink(Bundle feed, String rel) {
		for (BundleLinkComponent link : feed.getLink()) {
			if (link.getRelation().equals(rel))
				return link.getUrl();
		}
	  return null;
  }

  public static Extension getExtension(DomainResource c, String name) {
    if (name == null)
      return null;
    for (Extension e : c.getExtension()) {
      if (name.equals(e.getUrl()))
        return e;
    }
    return null;  }

  public static Meta meta(Resource resource) {
    if (!resource.hasMeta())
      resource.setMeta(new Meta());
    return resource.getMeta();
  }

}
