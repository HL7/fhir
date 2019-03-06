package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r5.model.MetadataResource;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.utils.EOperationOutcome;
import org.hl7.fhir.r5.utils.NarrativeGenerator;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.MarkDownProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class CodeSystemRenderer extends BaseRenderer {

  private CodeSystem cs;

  public CodeSystemRenderer(IWorkerContext context, String prefix, CodeSystem cs, IGKnowledgeProvider igp, List<SpecMapManager> maps, MarkDownProcessor markdownEngine, NpmPackage packge) {
    super(context, prefix, igp, maps, markdownEngine, packge);
    this.cs = cs;
  }

  public String summary(boolean xml, boolean json, boolean ttl) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tbody><tr><td>"+translate("cs.summary", "Defining URL")+":</td><td>"+Utilities.escapeXml(cs.getUrl())+"</td></tr>\r\n");
    if (cs.hasVersion())
      b.append(" <tr><td>"+translate("cs.summary", "Version")+":</td><td>"+Utilities.escapeXml(cs.getVersion())+"</td></tr>\r\n");
    b.append(" <tr><td>"+translate("cs.summary", "Name")+":</td><td>"+Utilities.escapeXml(gt(cs.getNameElement()))+"</td></tr>\r\n");
    b.append(" <tr><td>"+translate("cs.summary", "Status")+":</td><td>"+describeContent(cs.getContent())+"</td></tr>\r\n");
    b.append(" <tr><td>"+translate("cs.summary", "Definition")+":</td><td>"+processMarkdown("description", cs.getDescriptionElement())+"</td></tr>\r\n");
    if (cs.hasPublisher())
      b.append(" <tr><td>"+translate("cs.summary", "Publisher")+":</td><td>"+Utilities.escapeXml(gt(cs.getPublisherElement()))+"</td></tr>\r\n");
    if (CodeSystemUtilities.hasOID(cs))
      b.append(" <tr><td>"+translate("cs.summary", "OID")+":</td><td>"+CodeSystemUtilities.getOID(cs)+"("+translate("cs.summary", "for OID based terminology systems")+")</td></tr>\r\n");
    if (cs.hasCopyright())
      b.append(" <tr><td>"+translate("cs.summary", "Copyright")+":</td><td>"+Utilities.escapeXml(gt(cs.getCopyrightElement()))+"</td></tr>\r\n");
    if (ToolingExtensions.hasExtension(cs, ToolingExtensions.EXT_FMM_LEVEL))
      b.append(" <tr><td><a class=\"fmm\" href=\"versions.html#maturity\" title=\"Maturity Level\">"+translate("cs.summary", "Maturity")+"</a>:</td><td>"+ToolingExtensions.readStringExtension(cs, ToolingExtensions.EXT_FMM_LEVEL)+"</td></tr>\r\n");
    if (xml || json || ttl) {
      b.append(" <tr><td>"+translate("cs.summary", "Source Resource")+":</td><td>");
      boolean first = true;
      if (xml) {
        first = false;
        b.append("<a href=\"CodeSystem-"+cs.getId()+".xml.html\">"+translate("cs.summary", "XML")+"</a>");
      }
      if (json) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"CodeSystem-"+cs.getId()+".json.html\">"+translate("cs.summary", "JSON")+"</a>");
      }
      if (ttl) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\"CodeSystem-"+cs.getId()+".ttl.html\">"+translate("cs.summary", "Turtle")+"</a>");
      }
      b.append("</td></tr>\r\n");
    }
    b.append("</tbody></table>\r\n");

    return b.toString();
  }


  private String describeContent(CodeSystemContentMode content) {
    switch (content) {
    case COMPLETE: return translate("cs.summary", "All the concepts defined by the code system are included in the code system resource");
    case NOTPRESENT: return translate("cs.summary", "None of the concepts defined by the code system are included in the code system resource");
    case EXAMPLE: return translate("cs.summary", "A few representative concepts are included in the code system resource");
    case FRAGMENT: return translate("cs.summary", "A subset of the code system concepts are included in the code system resource");
    }
    return "?? illegal status";
  }

  public String content(Set<String> outputTracker) throws EOperationOutcome, FHIRException, IOException, org.hl7.fhir.exceptions.FHIRException  {
//    if (cs.hasText() && cs.getText().hasDiv())
//      return new XhtmlComposer().compose(cs.getText().getDiv());
//    else {
      CodeSystem csc = cs.copy();
      csc.setId(cs.getId()); // because that's not copied
      csc.setText(null);
      new NarrativeGenerator(prefix, prefix, context).generate(csc, outputTracker);
      return new XhtmlComposer(XhtmlComposer.HTML).compose(csc.getText().getDiv());
//    }
  }

  public String xref() throws FHIRException {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    b.append("\r\n");
    List<String> vsurls = new ArrayList<String>();
    for (MetadataResource sd : context.allConformanceResources()) {
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
      b.append("<p>"+translate("cs.xref", "This CodeSystem is not used")+"</p>\r\n");
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
        b.append(" <li><a href=\""+vc.getUserString("path")+"\">"+Utilities.escapeXml(gt(vc.getNameElement()))+"</a></li>\r\n");
        processed.add(vc.getUserString("path"));
      }
    }
    return first;
  }


}
