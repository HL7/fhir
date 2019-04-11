package org.hl7.fhir.igtools.renderers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.igtools.publisher.FetchedFile;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.OperationOutcome;
import org.hl7.fhir.r5.utils.OperationOutcomeUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.r5.utils.TranslatingUtilities;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.stringtemplate.v4.ST;

public class ValidationPresenter extends TranslatingUtilities implements Comparator<FetchedFile> {

  private static final String INTERNAL_LINK = "internal";
  private String statedVersion;
  private IGKnowledgeProvider provider;
  private IGKnowledgeProvider altProvider;
  int err = 0;
  int warn = 0;
  int info = 0;
  private String root;
  private String packageId;
  private String altPackageId;
  private String igVersion;
  private String ballotCheck;

  public ValidationPresenter(String statedVersion, String igVersion, IGKnowledgeProvider provider, IGKnowledgeProvider altProvider, String root, String packageId, String altPackageId, String ballotCheck) {
    super();
    this.statedVersion = statedVersion;
    this.igVersion = igVersion;
    this.provider = provider;
    this.altProvider = altProvider;
    this.root = root;
    this.packageId = packageId;
    this.altPackageId = altPackageId;
    this.ballotCheck = ballotCheck;
  }

  private List<FetchedFile> sorted(List<FetchedFile> files) {
    List<FetchedFile> list = new ArrayList<FetchedFile>();
    list.addAll(files);
    Collections.sort(list, this);
    return list;
  }
  
  public String generate(String title, List<ValidationMessage> allErrors, List<FetchedFile> files, String path, List<String> filteredMessages) throws IOException {
    
    for (FetchedFile f : files) {
      for (ValidationMessage vm : removeDupMessages(f.getErrors())) {
        if (vm.getLevel().equals(ValidationMessage.IssueSeverity.FATAL)||vm.getLevel().equals(ValidationMessage.IssueSeverity.ERROR))
          err++;
        else if (vm.getLevel().equals(ValidationMessage.IssueSeverity.WARNING))
          warn++;
        else
          info++;
      }
    }
    
    List<ValidationMessage> linkErrors = removeDupMessages(allErrors); 
    StringBuilder b = new StringBuilder();
    b.append(genHeader(title, err, warn, info, linkErrors.size(), filteredMessages.size()));
    b.append(genSummaryRowInteral(linkErrors));
    files = sorted(files);
    for (FetchedFile f : files) 
      b.append(genSummaryRow(f));
    b.append(genEnd());
    b.append(genStartInternal());
    for (ValidationMessage vm : linkErrors)
      b.append(genDetails(vm));
    b.append(genEnd());
    for (FetchedFile f : files) {
      b.append(genStart(f));
      if (f.getErrors().size() > 0)
        b.append(startTemplateErrors);
      else
        b.append(startTemplateNoErrors);
      for (ValidationMessage vm : removeDupMessages(f.getErrors())) {
        b.append(genDetails(vm));
        if (vm.getLevel().equals(ValidationMessage.IssueSeverity.FATAL)||vm.getLevel().equals(ValidationMessage.IssueSeverity.ERROR))
          err++;
        else if (vm.getLevel().equals(ValidationMessage.IssueSeverity.WARNING))
          warn++;
        else
          info++;
      }
      b.append(genEnd());
    }    
    b.append(genSuppressedMessages(filteredMessages));
    b.append(genFooter(title));
    TextFile.stringToFile(b.toString(), path);

    Bundle validationBundle = new Bundle().setType(Bundle.BundleType.COLLECTION);
    OperationOutcome oo = new OperationOutcome();
    validationBundle.addEntry(new BundleEntryComponent().setResource(oo));
    for (ValidationMessage vm : linkErrors) {
      oo.getIssue().add(OperationOutcomeUtilities.convertToIssue(vm, oo));
    }
    for (FetchedFile f : files) {
      if (!f.getErrors().isEmpty()) {
        oo = new OperationOutcome();
        validationBundle.addEntry(new BundleEntryComponent().setResource(oo));
        ToolingExtensions.addStringExtension(oo, ToolingExtensions.EXT_OO_FILE, f.getName());
        for (ValidationMessage vm : removeDupMessages(f.getErrors())) {
          oo.getIssue().add(OperationOutcomeUtilities.convertToIssue(vm, oo));
        }
      }
    }
    FileOutputStream s = new FileOutputStream(Utilities.changeFileExt(path, ".xml"));
    new XmlParser().compose(s, validationBundle, true);
    s.close();

    b = new StringBuilder();
    b.append(genHeaderTxt(title, err, warn, info));
    b.append(genSummaryRowTxtInternal(linkErrors));
    files = sorted(files);
    for (FetchedFile f : files) 
      b.append(genSummaryRowTxt(f));
    b.append(genEnd());
    b.append(genStartTxtInternal());
    for (ValidationMessage vm : linkErrors)
      b.append(vm.getDisplay() + "\r\n");
    b.append(genEndTxt());
    for (FetchedFile f : files) {
      b.append(genStartTxt(f));
      for (ValidationMessage vm : removeDupMessages(f.getErrors()))
        b.append(vm.getDisplay() + "\r\n");
      b.append(genEndTxt());
    }    
    b.append(genFooterTxt(title));
    TextFile.stringToFile(b.toString(), Utilities.changeFileExt(path, ".txt"));
    
    String summary = "Errors: " + err + "  Warnings: " + warn + "  Info: " + info;
    return path + "\r\n" + summary;
  }

  private String genSuppressedMessages(List<String> filteredMessages) {
    if (filteredMessages.size() == 0)
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<a name=\"suppressed\"> </a>\r\n<p><b>Suppressed Error Messages</b></p><ul>\r\n");
    for (String s : filteredMessages) {
      b.append(" <li>"+Utilities.escapeXml(s)+"</li>\r\n");
    }
    b.append("</ul>\r\n");
    return b.toString();
  }

  public static void filterMessages(List<ValidationMessage> messages, List<String> suppressedMessages, boolean suppressErrors) {
    List<ValidationMessage> filteredMessages = new ArrayList<ValidationMessage>();
    for (ValidationMessage message : removeDupMessages(messages)) {
      if ((!suppressedMessages.contains(message.getDisplay()) && !suppressedMessages.contains(message.getMessage()))
      || (!suppressErrors && (message.getLevel().equals(ValidationMessage.IssueSeverity.FATAL) || message.getLevel().equals(ValidationMessage.IssueSeverity.ERROR))))
        filteredMessages.add(message);
    }
    while(!messages.isEmpty())
      messages.remove(0);
    messages.addAll(filteredMessages);
  }
  
  private static List<ValidationMessage> removeDupMessages(List<ValidationMessage> errors) {
    List<ValidationMessage> filteredErrors = new ArrayList<ValidationMessage>();
    for (ValidationMessage error : errors) {
      if (!filteredErrors.contains(error))
        filteredErrors.add(error);
    }
    return filteredErrors;
  }
  
  // HTML templating
  private final String headerTemplate = 
      "<!DOCTYPE HTML>\r\n"+
      "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n"+
      "<!-- broken links = $links$, errors = $err$, warn = $warn$, info = $info$ -->\r\n"+
      "<head>\r\n"+
      "  <title>$title$ : Validation Results</title>\r\n"+
      "  <link href=\"fhir.css\" rel=\"stylesheet\"/>\r\n"+
      "</head>\r\n"+
      "<body style=\"margin: 20px; background-color: #ffffff\">\r\n"+
      " <h1>Validation Results for $title$</h1>\r\n"+
      " <p>Generated $time$. FHIR version $version$ for $packageId$#$igversion$ (canonical = <a href=\"$canonical$\">$canonical$</a> (<a href=\"$canonical$/history.html\">history</a>))</p>\r\n"+
      "$suppressedmsgssummary$"+
      " <p>HL7 Publication check:</p> $ballotCheck$\r\n"+
      " <table class=\"grid\">\r\n"+
      "   <tr>\r\n"+
      "     <td><b>Filename</b></td><td><b>Errors</b></td><td><b>Information messages &amp; Warnings</b></td>\r\n"+
      "   </tr>\r\n";
  
  private final String summaryTemplate = 
      "   <tr style=\"background-color: $color$\">\r\n"+
      "     <td><a href=\"#$link$\"><b>$filename$</b></a></td><td><b>$errcount$</b></td><td><b>$other$</b></td>\r\n"+
      "   </tr>\r\n";
  
  private final String endTemplate = 
      "</table>\r\n";

  private final String startTemplate = 
      "<hr/>\r\n"+
      "<a name=\"$link$\"> </a>\r\n"+
      "<h2><a href=\"$xlink$\">$path$</a></h2>\r\n"+
      " <table class=\"grid\">\r\n";
  
  private final String startTemplateErrors = 
      "   <tr>\r\n"+
      "     <td><b>Path</b></td><td><b>Severity</b></td><td><b>Message</b></td>\r\n"+
      "   </tr>\r\n";

  private final String startTemplateNoErrors = 
      "   <tr>\r\n"+
      "     <td>&check;</td>\r\n"+
      "   </tr>\r\n";

  private final String detailsTemplate = 
      "   <tr style=\"background-color: $color$\">\r\n"+
      "     <td><b>$path$</b></td><td><b>$level$</b></td><td><b>$msg$</b></td>\r\n"+
      "   </tr>\r\n";
  
  private final String detailsTemplateTx = 
      "   <tr style=\"background-color: $color$\">\r\n"+
      "     <td><b>$path$</b></td><td><b>$level$</b></td><td><b>$msg$</b> (<a href=\"$tx$\">see Tx log</a>)</td>\r\n"+
      "   </tr>\r\n";
  
  private final String detailsTemplateWithLink = 
      "   <tr style=\"background-color: $color$\">\r\n"+
      "     <td><b><a href=\"$pathlink$\">$path$</a></b></td><td><b>$level$</b></td><td><b>$msg$</b></td>\r\n"+
      "   </tr>\r\n";
  
  private final String footerTemplate = 
      "</body>\r\n"+
      "</html>\r\n";

  // Text templates
  private final String headerTemplateText = 
      "$title$ : Validation Results\r\n"+
      "=========================================\r\n\r\n"+
      "err = $err$, warn = $warn$, info = $info$\r\n"+
      "Generated $time$. FHIR version $version$ for $packageId$#$igversion$ (canonical = $canonical$)\r\n\r\n";
  
  private final String summaryTemplateText = 
      " $filename$ : $errcount$ / $other$\r\n";
  
  private final String endTemplateText = 
      "\r\n";

  private final String startTemplateText = 
      "\r\n== $path$ ==\r\n";

  private final String detailsTemplateText = 
      " * $level$ : $path$ ==> $msg$\r\n";
  
  private final String footerTemplateText = 
      "\r\n";
  
  private ST template(String t) {
    return new ST(t, '$', '$');
  }

  private String genHeader(String title, int err, int warn, int info, int links, int msgCount) {
    ST t = template(headerTemplate);
    t.add("version", statedVersion);
    t.add("igversion", igVersion);
    t.add("title", title);
    t.add("time", new Date().toString());
    t.add("err", Integer.toString(err));
    t.add("warn", Integer.toString(warn));
    t.add("info", Integer.toString(info));
    t.add("links", Integer.toString(links));
    t.add("packageId", packageId);
    t.add("canonical", provider.getCanonical());
    t.add("ballotCheck", ballotCheck);
    if (msgCount == 0)
      t.add("suppressedmsgssummary", "<p>No Suppressed Errors</p>\r\n");
    else
      t.add("suppressedmsgssummary", "<p><a href=\"#suppressed\">"+msgCount+" Suppressed "+Utilities.pluralize("Error", msgCount)+"</a></p>\r\n");
    return t.render();
  }

  private String genHeaderTxt(String title, int err, int warn, int info) {
    ST t = template(headerTemplateText);
    t.add("version", statedVersion);
    t.add("igversion", igVersion);
    t.add("title", title);
    t.add("time", new Date().toString());
    t.add("err",  Integer.toString(err));
    t.add("warn",  Integer.toString(warn));
    t.add("info",  Integer.toString(info));
    t.add("packageId", packageId);
    t.add("canonical", provider.getCanonical());
    t.add("ballotCheck", ballotCheck);
    return t.render();
  }

  private String genEnd() {
    ST t = template(endTemplate);
    t.add("version", Constants.VERSION);
    t.add("igversion", statedVersion);
    t.add("time", new Date().toString());
    return t.render();
  }

  private String genEndTxt() {
    ST t = template(endTemplateText);
    t.add("version", Constants.VERSION);
    t.add("igversion", statedVersion);
    t.add("time", new Date().toString());
    return t.render();
  }

  private String genFooter(String title) {
    ST t = template(footerTemplate);
    t.add("version", Constants.VERSION);
    t.add("igversion", statedVersion);
    t.add("title", title);
    t.add("time", new Date().toString());
    return t.render();
  }

  private String genFooterTxt(String title) {
    ST t = template(footerTemplateText);
    t.add("version", Constants.VERSION);
    t.add("igversion", statedVersion);
    t.add("title", title);
    t.add("time", new Date().toString());
    return t.render();
  }

  private String genSummaryRowInteral(List<ValidationMessage> list) {
    ST t = template(summaryTemplate);
    t.add("link", INTERNAL_LINK);
    
    t.add("filename", "Build Errors");
    String ec = errCount(list);
    t.add("errcount", ec);
    t.add("other", otherCount(list));
    if ("0".equals(ec))
      t.add("color", "#EFFFEF");
    else
      t.add("color", colorForLevel(IssueSeverity.ERROR));
      
    return t.render();
  }

  private String genSummaryRow(FetchedFile f) {
    ST t = template(summaryTemplate);
    t.add("link", makelink(f));
    List<ValidationMessage> uniqueErrors = removeDupMessages(f.getErrors());
    
    t.add("filename", f.getName());
    String ec = errCount(uniqueErrors);
    t.add("errcount", ec);
    t.add("other", otherCount(uniqueErrors));
    if ("0".equals(ec))
      t.add("color", "#EFFFEF");
    else
      t.add("color", colorForLevel(IssueSeverity.ERROR));
      
    return t.render();
  }

  private String genSummaryRowTxt(FetchedFile f) {
    ST t = template(summaryTemplateText);
    t.add("filename", f.getName());
    String ec = errCount(f.getErrors());
    t.add("errcount", ec);
    t.add("other", otherCount(f.getErrors()));
      
    return t.render();
  }

  private String genSummaryRowTxtInternal(List<ValidationMessage> linkErrors) {
    ST t = template(summaryTemplateText);
    t.add("filename", "Build Errors");
    String ec = errCount(linkErrors);
    t.add("errcount", ec);
    t.add("other", otherCount(linkErrors));
      
    return t.render();
  }

  
  private String makelink(FetchedFile f) {
    String fn = f.getName().replace("/", "_").replace("\\", "_").replace(":", "_").replace("#", "_");
    return fn;
  }

  private String errCount(List<ValidationMessage> list) {
    int c = 0;
    for (ValidationMessage vm : list) {
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL)
        c++;
    }
    return Integer.toString(c);
  }

  private Object otherCount(List<ValidationMessage> list) {
    int c = 0;
    for (ValidationMessage vm : list) {
      if (vm.getLevel() == IssueSeverity.INFORMATION || vm.getLevel() == IssueSeverity.WARNING)
        c++;
    }
    return Integer.toString(c);
  }

  private String genStart(FetchedFile f) {
    ST t = template(startTemplate);
    t.add("link", makelink(f));
    t.add("filename", f.getName());
    t.add("path", makeLocal(f.getPath()));
    String link = provider.getLinkFor(f.getResources().get(0));
    if (link==null) {
      link = altProvider.getLinkFor(f.getResources().get(0));
    }
    if (link != null) { 
      link = link.replace("{{[id]}}", f.getResources().get(0).getId());
      link = link.replace("{{[type]}}", f.getResources().get(0).getElement().fhirType());
    }
    
    t.add("xlink", link);
    return t.render();
  }
  
  private String makeLocal(String path) {
    if (path.startsWith(root))
      return path.substring(root.length()+1);
    return path;
  }

  private String genStartInternal() {
    ST t = template(startTemplate);
    t.add("link", INTERNAL_LINK);
    t.add("filename", "Build Errors");
    t.add("path", "n/a");
    t.add("xlink", "");
    return t.render();
  }

  

  private String genStartTxtInternal() {
    ST t = template(startTemplateText);
    t.add("link", INTERNAL_LINK);
    t.add("filename", "Build Errors");
    t.add("path", "n/a");
    return t.render();
  }

  private String genStartTxt(FetchedFile f) {
    ST t = template(startTemplateText);
    t.add("link", makelink(f));
    t.add("filename", f.getName());
    t.add("path", makeLocal(f.getPath()));
    return t.render();
  }
  private String genDetails(ValidationMessage vm) {
    ST t = template(vm.getLocationLink() != null ? detailsTemplateWithLink : vm.getTxLink() != null ? detailsTemplateTx : detailsTemplate);
    t.add("path", makeLocal(vm.getLocation()));
    t.add("pathlink", vm.getLocationLink());
    t.add("level", vm.getLevel().toCode());
    t.add("color", colorForLevel(vm.getLevel()));
    t.add("msg", vm.getHtml());
    t.add("tx", "qa-tx.html#l"+vm.getTxLink());
    return t.render();
  }

  private String genDetailsTxt(ValidationMessage vm) {
    ST t = template(detailsTemplateText);
    t.add("path", vm.getLocation());
    t.add("level", vm.getLevel().toCode());
    t.add("color", colorForLevel(vm.getLevel()));
    t.add("msg", vm.getHtml());
    return t.render();
  }

  private String colorForLevel(IssueSeverity level) {
    switch (level) {
    case ERROR:
      return "#ffcccc";
    case FATAL:
      return "#ff9999";
    case WARNING:
      return "#ffebcc";
    default: // INFORMATION:
      return "#ffffe6";
    }
  }

  @Override
  public int compare(FetchedFile f1, FetchedFile f2) {
    return f1.getName().compareTo(f2.getName());
  }

  public int getErr() {
    return err;
  }

  public int getWarn() {
    return warn;
  }

  public int getInfo() {
    return info;
  }  
  
  
}
