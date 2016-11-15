package org.hl7.fhir.tools.converters;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.dstu3.conformance.ProfileUtilities;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;

public class MarkDownPreProcessor {

  public static String process(Definitions definitions, BuildWorkerContext workerContext, List<ValidationMessage> validationErrors, String text, String location, String prefix) throws Exception {
    if (Utilities.noString(text))
      return "";
    
    text = text.replace("||", "\r\n\r\n");
    while (text.contains("[[[")) {
      String left = text.substring(0, text.indexOf("[[["));
      if (text.indexOf("]]]") < 0)
        throw new Error(location + ": Missing closing ]]] in markdown text: " + text);
      String linkText = text.substring(text.indexOf("[[[")+3, text.indexOf("]]]"));
      String right = text.substring(text.indexOf("]]]")+3);
      String url = "";
      String[] parts = linkText.split("\\#");
      if (parts[0].contains("/StructureDefinition/")) {
        StructureDefinition ed = workerContext.getExtensionStructure(null, parts[0]);
        if (ed == null)
          throw new Error(location + ": Unable to find extension "+parts[0]);
        url = ed.getUserData("filename")+".html";
      } 
      if (Utilities.noString(url)) {
        String[] paths = parts[0].split("\\.");
        StructureDefinition p = new ProfileUtilities(workerContext, null, null).getProfile(null, paths[0]);
        if (p != null) {
          String suffix = (paths.length > 1) ? "-definitions.html#"+parts[0] : ".html";
          if (p.getUserData("filename") == null)
            url = paths[0].toLowerCase()+suffix;
          else
            url = p.getUserData("filename")+suffix;
        } else if (definitions.hasResource(linkText)) {
          url = linkText.toLowerCase()+".html#";
        } else if (definitions.hasElementDefn(linkText)) {
          url = definitions.getSrcFile(linkText)+".html#"+linkText;
        } else if (definitions.hasPrimitiveType(linkText)) {
          url = "datatypes.html#"+linkText;
        } else if (definitions.getPageTitles().containsKey(linkText)) {
          url = definitions.getPageTitles().get(linkText);
        } else if (definitions.getLogicalModel(linkText.toLowerCase()) != null) {
          url = definitions.getLogicalModel(linkText.toLowerCase()).getId()+".html";
        } else if (validationErrors != null) {
          validationErrors.add(
              new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, location, "Unresolved logical URL '"+linkText+"'", IssueSeverity.WARNING));
          //        throw new Exception("Unresolved logical URL "+url);
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
    
    return text;
  }

}
