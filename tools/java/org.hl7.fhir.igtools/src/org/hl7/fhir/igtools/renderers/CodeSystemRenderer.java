package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.utils.EOperationOutcome;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class CodeSystemRenderer extends BaseRenderer {

  private CodeSystem cs;

  public CodeSystemRenderer(IWorkerContext context, String prefix, CodeSystem cs, IGKnowledgeProvider igp, List<SpecMapManager> maps) {
    super(context, prefix, igp, maps);
    this.cs = cs;
  }

  public String summary(boolean xml, boolean json, boolean ttl) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tbody><tr><td>Defining URL:</td><td>"+Utilities.escapeXml(cs.getUrl())+"</td></tr>\r\n");
    b.append(" <tr><td>Name:</td><td>"+Utilities.escapeXml(cs.getName())+"</td></tr>\r\n");
    b.append(" <tr><td>Status:</td><td>"+describeContent(cs.getContent())+"</td></tr>\r\n");
    b.append(" <tr><td>Definition:</td><td>"+processMarkdown("description", cs.getDescription())+"</td></tr>\r\n");
    if (cs.hasPublisher())
      b.append(" <tr><td>Publisher:</td><td>"+Utilities.escapeXml(cs.getPublisher())+"</td></tr>\r\n");
    if (ToolingExtensions.hasOID(cs))
      b.append(" <tr><td>OID:</td><td>"+ToolingExtensions.getOID(cs)+"(for OID based terminology systems)</td></tr>\r\n");
    if (cs.hasCopyright())
      b.append(" <tr><td>Copyright:</td><td>"+Utilities.escapeXml(cs.getCopyright())+"</td></tr>\r\n");
    if (xml || json || ttl) {
      b.append(" <tr><td>Source Resource</td><td>");
      boolean first = true;
      if (xml) {
        first = false;
        b.append("<a href=\"CodeSystem-"+cs.getId()+".xml.html\">XML</a>");
      }
      if (json) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"CodeSystem-"+cs.getId()+".json.html\">JSON</a>");
      }
      if (ttl) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"CodeSystem-"+cs.getId()+".ttl.html\">Turtle</a>");
      }
      b.append("</td></tr>\r\n");
    }
    b.append("</tbody></table>\r\n");

    return b.toString();
  }

  private String describeContent(CodeSystemContentMode content) {
    switch (content) {
    case COMPLETE: return "All the concepts defined by the code system are included in the code system resource";
    case NOTPRESENT: return "None of the concepts defined by the code system are included in the code system resource";
    case EXAMPLAR: return "A few representative concepts are included in the code system resource";
    case FRAGMENT: return "A subset of the code system concepts are included in the code system resource";
    }
    return "?? illegal status";
  }

  public String content() throws EOperationOutcome, FHIRException, IOException, org.hl7.fhir.exceptions.FHIRException  {
//    if (cs.hasText() && cs.getText().hasDiv()) 
//      return new XhtmlComposer().compose(cs.getText().getDiv());
//    else {
      CodeSystem csc = cs.copy();
      csc.setId(cs.getId()); // because that's not copied
      new NarrativeGenerator(prefix, prefix, context).generate(csc);
      return new XhtmlComposer().compose(csc.getText().getDiv());
//    }
  }

  public String xref() throws FHIRException {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    b.append("\r\n");
    List<String> vsurls = new ArrayList<String>();
    for (BaseConformance sd : context.allConformanceResources()) {
      if (sd instanceof ValueSet)
        vsurls.add(sd.getUrl());
    }
    Collections.sort(vsurls);
    
    Set<String> processed = new HashSet<String>();
    for (String url : vsurls) {
      ValueSet vc = context.fetchResource(ValueSet.class, url);
      for (ConceptSetComponent ed : vc.getCompose().getInclude()) 
        first = addLink(b, first, vc, ed, processed);
      for (ConceptSetComponent ed : vc.getCompose().getExclude()) 
        first = addLink(b, first, vc, ed, processed);
    }
    if (first)
      b.append("<p>This codeSystem is not used</p>\r\n");
    else
      b.append("</ul>\r\n");
    return b.toString();
  }

  private boolean addLink(StringBuilder b, boolean first, ValueSet vc, ConceptSetComponent ed, Set<String> processed) {
    if (ed.hasSystem() && ed.getSystem().equals(cs.getUrl())) {
      if (first) {
        first = false;
        b.append("<ul>\r\n");
      } else if (!processed.contains(vc.getUserString("path"))) {
        b.append(" <li><a href=\""+vc.getUserString("path")+"\">"+Utilities.escapeXml(vc.getName())+"</a></li>\r\n");
        processed.add(vc.getUserString("path"));
      }
    }
    return first;
  }


}
