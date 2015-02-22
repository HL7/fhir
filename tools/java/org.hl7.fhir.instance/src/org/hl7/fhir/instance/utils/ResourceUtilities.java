package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Bundle.BundleLinkComponent;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DataElement.DataElementBindingComponent;
import org.hl7.fhir.instance.model.DataElement.DataElementContactComponent;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
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

  public static Meta meta(Resource resource) {
    if (!resource.hasMeta())
      resource.setMeta(new Meta());
    return resource.getMeta();
  }

  public static String representDataElementCollection(WorkerContext context, Bundle bundle, boolean profileLink, String linkBase) {
    StringBuilder b = new StringBuilder();
    DataElement common = showDECHeader(b, bundle);
    b.append("<table class=\"grid\">\r\n"); 
    List<String> cols = chooseColumns(bundle, common, b, profileLink);
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      renderDE(de, cols, b, profileLink, linkBase);
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  
  private static void renderDE(DataElement de, List<String> cols, StringBuilder b, boolean profileLink, String linkBase) {
    b.append("<tr>");
    for (String col : cols) {
      String v;
      if (col.equals("DataElement.name")) {
        v = de.hasName() ? Utilities.escapeXml(de.getName()) : "";
      } else if (col.equals("DataElement.status")) {
        v = de.hasStatusElement() ? de.getStatusElement().asStringValue() : "";
      } else if (col.equals("DataElement.code")) {
        v = renderCoding(de.getCode());
      } else if (col.equals("DataElement.type")) {
        v = de.hasType() ? Utilities.escapeXml(de.getType()) : "";
      } else if (col.equals("DataElement.units")) {
        v = renderDEUnits(de.getUnits());
      } else if (col.equals("DataElement.binding")) {
        v = renderBinding(de.getBinding());
      } else if (col.equals("DataElement.minValue")) {
        v = ToolingExtensions.hasExtension(de, "http://hl7.org/fhir/ExtensionDefinition/minValue") ? Utilities.escapeXml(ToolingExtensions.readPrimitiveExtension(de, "http://hl7.org/fhir/ExtensionDefinition/minValue").asStringValue()) : "";
      } else if (col.equals("DataElement.maxValue")) {
        v = ToolingExtensions.hasExtension(de, "http://hl7.org/fhir/ExtensionDefinition/maxValue") ? Utilities.escapeXml(ToolingExtensions.readPrimitiveExtension(de, "http://hl7.org/fhir/ExtensionDefinition/maxValue").asStringValue()) : "";
      } else if (col.equals("DataElement.maxLength")) {
        v = ToolingExtensions.hasExtension(de, "http://hl7.org/fhir/ExtensionDefinition/maxLength") ? Utilities.escapeXml(ToolingExtensions.readPrimitiveExtension(de, "http://hl7.org/fhir/ExtensionDefinition/maxLength").asStringValue()) : "";
      } else if (col.equals("DataElement.mask")) {
        v = ToolingExtensions.hasExtension(de, "http://hl7.org/fhir/ExtensionDefinition/mask") ? Utilities.escapeXml(ToolingExtensions.readPrimitiveExtension(de, "http://hl7.org/fhir/ExtensionDefinition/mask").asStringValue()) : "";
      } else 
        throw new Error("Unknown column name: "+col);

      b.append("<td>"+v+"</td>");
    }
    if (profileLink)
      b.append("<td><a href=\""+linkBase+"-"+de.getId()+".html\">Profile</a>, <a href=\"http://www.clinicalelement.com/#/20130723/Intermountain/"+de.getId()+"\">CEM</a></td>");
    b.append("</tr>\r\n");
  }

  
  private static String renderBinding(DataElementBindingComponent binding) {
    return "todo";  
  }

  private static String renderDEUnits(Type units) {
    if (units == null || units.isEmpty())
      return "";
    if (units instanceof CodeableConcept)
      return renderCodeable((CodeableConcept) units);
    else
      return "<a href=\""+Utilities.escapeXml(((Reference) units).getReference())+"\">"+Utilities.escapeXml(((Reference) units).getReference())+"</a>";
      
  }

  private static String renderCodeable(CodeableConcept units) {
    if (units == null || units.isEmpty())
      return "";
    String v = renderCoding(units.getCoding());
    if (units.hasText())
      v = v + " " +Utilities.escapeXml(units.getText());
    return v;
  }

  private static String renderCoding(List<Coding> codes) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (Coding c : codes)
      b.append(renderCoding(c));
    return b.toString();
  }

  private static String renderCoding(Coding code) {
    if (code == null || code.isEmpty())
      return "";
    else
      return "<span title=\""+Utilities.escapeXml(code.getSystem())+"\">"+Utilities.escapeXml(code.getCode())+"</span>";
  }

  private static List<String> chooseColumns(Bundle bundle, DataElement common, StringBuilder b, boolean profileLink) {
    b.append("<tr>");
    List<String> results = new ArrayList<String>();
    results.add("DataElement.name");
    b.append("<td><b></b></td>");
    if (!common.hasStatus()) {
      results.add("DataElement.status");
      b.append("<td><b>Status</b></td>");
    }
    if (hasCode(bundle)) {
      results.add("DataElement.code");
      b.append("<td><b>Code</b></td>");
    }
    if (!common.hasType() && hasType(bundle)) {
      results.add("DataElement.type");
      b.append("<td><b>Type</b></td>");
    }
    if (hasUnits(bundle)) {
      results.add("DataElement.units");
      b.append("<td><b>Units</b></td>");
    }
    if (hasBinding(bundle)) {
      results.add("DataElement.binding");
      b.append("<td><b>Binding</b></td>");
    }
    if (hasExtension(bundle, "http://hl7.org/fhir/ExtensionDefinition/minValue")) {
      results.add("DataElement.minValue");
      b.append("<td><b>Min Value</b></td>");
    }
    if (hasExtension(bundle, "http://hl7.org/fhir/ExtensionDefinition/maxValue")) {
      results.add("DataElement.maxValue");
      b.append("<td><b>Max Value</b></td>");
    }
    if (hasExtension(bundle, "http://hl7.org/fhir/ExtensionDefinition/maxLength")) {
      results.add("DataElement.maxLength");
      b.append("<td><b>Max Length</b></td>");
    }
    if (hasExtension(bundle, "http://hl7.org/fhir/ExtensionDefinition/mask")) {
      results.add("DataElement.mask");
      b.append("<td><b>Mask</b></td>");
    }
    if (profileLink)
      b.append("<td><b>Links</b></td>");
    b.append("</tr>\r\n");
    return results;
  }

  private static boolean hasExtension(Bundle bundle, String url) {
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (ToolingExtensions.hasExtension(de, url))
        return true;
    }
    return false;
  }

  private static boolean hasBinding(Bundle bundle) {
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (de.hasBinding())
        return true;
    }
    return false;
  }

  private static boolean hasCode(Bundle bundle) {
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (de.hasCode())
        return true;
    }
    return false;
  }

  private static boolean hasType(Bundle bundle) {
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (de.hasType())
        return true;
    }
    return false;
  }

  private static boolean hasUnits(Bundle bundle) {
    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (de.hasUnits())
        return true;
    }
    return false;
  }

  private static DataElement showDECHeader(StringBuilder b, Bundle bundle) {
    DataElement meta = new DataElement();
    DataElement prototype = (DataElement) bundle.getEntry().get(0).getResource();
    meta.setPublisher(prototype.getPublisher());
    meta.getContact().addAll(prototype.getContact());
    meta.setStatus(prototype.getStatus());
    meta.setDate(prototype.getDate());
    meta.setType(prototype.getType());

    for (BundleEntryComponent e : bundle.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      if (!Base.compareDeep(de.getPublisherElement(), meta.getPublisherElement(), false))
        meta.setPublisherElement(null);
      if (!Base.compareDeep(de.getContact(), meta.getContact(), false))
        meta.getContact().clear();
      if (!Base.compareDeep(de.getStatusElement(), meta.getStatusElement(), false))
        meta.setStatusElement(null);
      if (!Base.compareDeep(de.getDateElement(), meta.getDateElement(), false))
        meta.setDateElement(null);
      if (!Base.compareDeep(de.getTypeElement(), meta.getTypeElement(), false))
        meta.setTypeElement(null);
    }
    if (meta.hasPublisher() || meta.hasContact() || meta.hasStatus() || meta.hasDate() || meta.hasType()) {
      b.append("<table class=\"grid\">\r\n"); 
      if (meta.hasPublisher())
        b.append("<tr><td>Publisher:</td><td>"+meta.getPublisher()+"</td></tr>\r\n");
      if (meta.hasContact()) {
        b.append("<tr><td>Contacts:</td><td>");
        boolean firsti = true;
        for (DataElementContactComponent c : meta.getContact()) {
          if (firsti)
            firsti = false;
          else
            b.append("<br/>");
          if (c.hasName())
            b.append(Utilities.escapeXml(c.getName())+": ");
          boolean first = true;
          for (ContactPoint cp : c.getTelecom()) {
            if (first)
              first = false;
            else
              b.append(", ");
            renderContactPoint(b, cp);
          }
        }
        b.append("</td></tr>\r\n");
      }
      if (meta.hasStatus())
        b.append("<tr><td>Status:</td><td>"+meta.getStatus().toString()+"</td></tr>\r\n");
      if (meta.hasDate())
        b.append("<tr><td>Date:</td><td>"+meta.getDateElement().asStringValue()+"</td></tr>\r\n");
      if (meta.hasType())
        b.append("<tr><td>Type:</td><td>"+meta.getType()+"</td></tr>\r\n");
      b.append("</table>\r\n"); 
    }  
    return meta;
  }

  public static void renderContactPoint(StringBuilder b, ContactPoint cp) {
    if (cp != null && !cp.isEmpty()) {
      if (cp.getSystem() == ContactPointSystem.EMAIL)
        b.append("<a href=\"mailto:"+cp.getValue()+"\">"+cp.getValue()+"</a>");
      else if (cp.getSystem() == ContactPointSystem.FAX) 
        b.append("Fax: "+cp.getValue());
      else if (cp.getSystem() == ContactPointSystem.URL) 
        b.append("<a href=\""+cp.getValue()+"\">"+cp.getValue()+"</a>");
      else
        b.append(cp.getValue());
    }
  }
}
