package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.utils.EOperationOutcome;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class ValueSetRenderer extends BaseRenderer {

  private ValueSet vs;

  public ValueSetRenderer(IWorkerContext context, String prefix, ValueSet vs, IGKnowledgeProvider igp) {
    super(context, prefix, igp);
    this.vs = vs;
  }

  public String summary(boolean xml, boolean json, boolean ttl) {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tbody><tr><td>Defining URL:</td><td>"+Utilities.escapeXml(vs.getUrl())+"</td></tr>\r\n");
    b.append(" <tr><td>Name:</td><td>"+Utilities.escapeXml(vs.getName())+"</td></tr>\r\n");
    b.append(" <tr><td>Definition:</td><td>"+Utilities.escapeXml(vs.getDescription())+"</td></tr>\r\n");
    if (vs.hasPublisher())
      b.append(" <tr><td>Publisher:</td><td>"+Utilities.escapeXml(vs.getPublisher())+"</td></tr>\r\n");
    if (ToolingExtensions.hasOID(vs))
      b.append(" <tr><td>OID:</td><td>"+ToolingExtensions.getOID(vs)+"(for OID based terminology systems)</td></tr>\r\n");
    if (vs.hasCopyright())
      b.append(" <tr><td>Copyright:</td><td>"+Utilities.escapeXml(vs.getCopyright())+"</td></tr>\r\n");
    if (xml || json || ttl) {
      b.append(" <tr><td>Source Resource</td><td>");
      boolean first = true;
      if (xml) {
        first = false;
        b.append("<a href=\"ValueSet-"+vs.getId()+".xml.html\">XML</a>");
      }
      if (json) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"ValueSet-"+vs.getId()+".json.html\">JSON</a>");
      }
      if (ttl) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"ValueSet-"+vs.getId()+".ttl.html\">Turtle</a>");
      }
      b.append("</td></tr>\r\n");
    }
    b.append("</tbody></table>\r\n");

    return b.toString();
  }

  public String cld() throws EOperationOutcome, FHIRException, IOException  {
    if (vs.hasText() && vs.getText().hasDiv())
      return new XhtmlComposer().compose(vs.getText().getDiv());
    ValueSet vsc = vs.copy();
    vsc.setText(null);
    new NarrativeGenerator("", "", context).generate(vsc);

    return new XhtmlComposer().compose(vsc.getText().getDiv());
  }

  public String xref() throws FHIRException {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    b.append("\r\n");
    List<String> sdurls = new ArrayList<String>();
    List<String> vsurls = new ArrayList<String>();
    for (BaseConformance sd : context.allConformanceResources()) {
      if (sd instanceof StructureDefinition)
        sdurls.add(sd.getUrl());
      if (sd instanceof ValueSet)
        vsurls.add(sd.getUrl());
    }
    Collections.sort(sdurls);
    Collections.sort(vsurls);
    
    for (String url : vsurls) {
      ValueSet vc = context.fetchResource(ValueSet.class, url);
      for (UriType ed : vc.getCompose().getImport()) {
        if (ed.getValueAsString().equals(vs.getUrl())) {
          if (first) {
            first = false;
            b.append("<ul>\r\n");
          }
          b.append(" <li><a href=\""+vc.getUserString("path")+"\">"+Utilities.escapeXml(vc.getName())+"</a></li>\r\n");
          break;
        }
      }
    }
    for (String url : sdurls) {
      StructureDefinition sd = context.fetchResource(StructureDefinition.class, url);
      for (ElementDefinition ed : sd.getSnapshot().getElement()) {
        if (ed.hasBinding() && ed.getBinding().hasValueSet()) {
          if ((ed.getBinding().hasValueSetUriType() && ed.getBinding().getValueSetUriType().getValueAsString().equals(vs.getUrl())) ||
              (ed.getBinding().hasValueSetReference() && ed.getBinding().getValueSetReference().getReference().equals(vs.getUrl()))) {
            if (first) {
              first = false;
              b.append("<ul>\r\n");
            }
            b.append(" <li><a href=\""+sd.getUserString("path")+"\">"+Utilities.escapeXml(sd.getName())+"</a></li>\r\n");
            break;
          }
        }
      }
    }
    
    if (first)
      b.append("<p>This value set is not used</p>\r\n");
    else
      b.append("</ul>\r\n");
    return b.toString();
  }

  
}
