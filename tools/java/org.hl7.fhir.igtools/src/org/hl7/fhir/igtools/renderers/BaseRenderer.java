package org.hl7.fhir.igtools.renderers;

import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.utilities.Utilities;

import com.github.rjeschke.txtmark.Processor;

public class BaseRenderer {
  protected IWorkerContext context;
  protected String prefix;
  protected IGKnowledgeProvider igp;


  public BaseRenderer(IWorkerContext context, String prefix, IGKnowledgeProvider igp) {
    super();
    this.context = context;
    this.prefix = prefix;
    this.igp = igp;
  }

  public String processMarkdown(String location, String text) throws Exception {
    if (text == null)
      return "";
    // 1. custom FHIR extensions
    text = text.replace("||", "\r\n\r\n");
    while (text.contains("[[[")) {
      String left = text.substring(0, text.indexOf("[[["));
      String linkText = text.substring(text.indexOf("[[[")+3, text.indexOf("]]]"));
      String right = text.substring(text.indexOf("]]]")+3);
      String url = "";
      String[] parts = linkText.split("\\#");
      if (parts[0].contains("/StructureDefinition/")) {
        StructureDefinition ed = context.fetchResource(StructureDefinition.class, parts[0]);
        if (ed == null)
          throw new Error("Unable to find extension "+parts[0]);
        url = ed.getUserData("filename")+".html";
      } 
      if (Utilities.noString(url)) {
        String[] paths = parts[0].split("\\.");
        StructureDefinition p = new ProfileUtilities(context, null, null).getProfile(null, paths[0]);
        if (p != null) {
          String suffix = (paths.length > 1) ? "-definitions.html#"+parts[0] : ".html";
          if (p.getUserData("filename") == null)
            url = paths[0].toLowerCase()+suffix;
          else
            url = p.getUserData("filename")+suffix;
        } else {
          throw new Exception("Unresolved logical URL "+linkText+" in markdown");
        }
      }
      text = left+"["+linkText+"]("+url+")"+right;
    }
    // 1. if prefix <> "", then check whether we need to insert the prefix
    if (!Utilities.noString(prefix)) {
      int i = text.length() - 3;
      while (i > 0) {
        if (text.substring(i, i+2).equals("](")) {
          if (!text.substring(i, i+7).equals("](http:")) { //  && !text.substring(i, i+8).equals("](https:"));
            text = text.substring(0, i)+"]("+prefix+text.substring(i+2);
          }
        }
        i--;
      }
    }
    
    
    // 3. markdown
    String s = Processor.process(checkEscape(text));
    return s;
  }

  private String checkEscape(String text) {
    if (text.startsWith("```"))
      return text.substring(3);
    else
      return Utilities.escapeXml(text);
  }

}
