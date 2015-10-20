package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

/**
 * This class takes 3 parameters:
 *   - the directory of the an existing version of the specification
 *   - the directory of the current version of the specification
 *   - the stated location of the current version
 *   
 * It goes through and marks files as belonging to a past version of the 
 * specification
 * 
 * @author Grahame Grieve
 *
 */
public class OldVersionRedirector {

  public static void main(String[] args) throws Exception {
    String currentVersion = "DSTU2, 1.0.2";
//    new OldVersionRedirector().process("E:\\fhir\\web\\2012May", "E:\\fhir\\web", "Oldest archived version, 0.01", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\2012Sep", "E:\\fhir\\web", "DSTU1 draft #1, 0.05", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\2013Jan", "E:\\fhir\\web", "DSTU1 draft #2, 0.06", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\2013Sep", "E:\\fhir\\web", "DSTU1 ballot, 0.11", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\DSTU1",   "E:\\fhir\\web", "DSTU1 Publication, 0.0.82", currentVersion);
    new OldVersionRedirector().process("E:\\fhir\\web\\2015Jan", "E:\\fhir\\web", "DSTU2 draft, 0.4.0", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\2015May", "E:\\fhir\\web", "DSTU2 ballot, 0.5.0", currentVersion);
//    new OldVersionRedirector().process("E:\\fhir\\web\\2015Sep", "E:\\fhir\\web", "DSTU2 QA Preview, 1.0.0", currentVersion);
  }

  private String upd_exists = "<!--old-s--><p style=\"background-color: #ffaaaa; border:1px solid black; padding: 5px;\">\r\n"+
      "This is an old version of the FHIR specification [ov]. This page has been <a href=\"[p]../[x]\">replaced by a more recent page</a> [nv] (see the <a href=\"[p]../directory.html\">Directory of published versions</a>).\r\n"+
      "</p><!--old-e-->\r\n";
  
  private String upd_no_exists = "<!--old-s--><p style=\"background-color: #ffaaaa; border:1px solid black; padding: 5px;\">\r\n"+
      "This is an old version of the FHIR specification [ov]. This page no longer exists in the <a href=\"[p]../[x]\">current version</a> [nv] (see the <a href=\"[p]../directory.html\">Directory of published versions</a>).\r\n"+
      "</p><!--old-e-->\r\n";
  
  
  
  
  private void process(String existing, String current, String oldVersion, String newVersion) throws Exception {
     List<String> filenames = new ArrayList<String>();
     listAllFiles(filenames, new File(existing));
     for (String filename : filenames) {
       System.out.println(filename);
       String content = TextFile.fileToString(filename);
       if (content.contains("<div class=\"col-9\">")) {
         String message = fileIsInCurrent(filename, existing, current) ? upd_exists.replace("[x]", newUrl(filename, existing)) : upd_no_exists.replace("[x]", "index.html");
         message = message.replace("[ov]", "("+oldVersion+")").replace("[nv]", "("+newVersion+")").replace("[p]", prefix(filename, existing));
         int i = content.indexOf("<!--old-s-->");
         if (i > -1) {
           int j = content.indexOf("<!--old-e-->");
           content = content.substring(0, i)+content.substring(j+14);
         }
         i = content.indexOf("<div class=\"col-9\">");
         content = content.substring(0, i+22) + message + content.substring(i+22);
         TextFile.stringToFile(content, filename);
       } else if (content.contains("<div class=\"content\"")){
         String message = fileIsInCurrent(filename, existing, current) ? upd_exists.replace("[x]", newUrl(filename, existing)) : upd_no_exists.replace("[x]", "index.html");
         message = message.replace("[ov]", "("+oldVersion+")").replace("[nv]", "("+newVersion+")").replace("[p]", prefix(filename, existing));
         int i = content.indexOf("<!--old-s-->");
         if (i > -1) {
           int j = content.indexOf("<!--old-e-->");
           content = content.substring(0, i)+content.substring(j+14);
         }
         i = content.indexOf("<div class=\"content\">");
         content = content.substring(0, i+22) + message + content.substring(i+22);
         TextFile.stringToFile(content, filename);
       }
     }
  }

  private CharSequence prefix(String filename, String existing) {
    String tail = filename.substring(existing.length());
    int t = 0;
    for (char c : tail.toCharArray()) 
      if (c == File.separatorChar)
        t++;
    String s = "";
    for (int i = 1; i < t; i++)
      s = s+"../";
    return s;
  }

  private CharSequence newUrl(String filename, String existing) {
    String tail = filename.substring(existing.length()+1);
    if (tail.endsWith(".htm"))
      tail = tail + "l";
    return tail.replace(File.separator, "/");
  }

  private boolean fileIsInCurrent(String filename, String existing, String current) {
    String tail = filename.substring(existing.length());
    if (tail.endsWith(".htm"))
      tail = tail + "l";
    String target = current+tail;
    return new File(target).exists();
  }

  private void listAllFiles(List<String> filenames, File dir) throws IOException {
    String[] files = dir.list();
    for (String f : files) {
      
      File ff = new CSFile(Utilities.path(dir.getAbsolutePath(), f)); 
      if (ff.isDirectory()) {
        if (!f.startsWith(".")) // ignore .svn...
          listAllFiles(filenames, ff);
      } else if (f.endsWith(".html") || f.endsWith(".htm")) {
        filenames.add(ff.getAbsolutePath());
      }
    }
  }

}
