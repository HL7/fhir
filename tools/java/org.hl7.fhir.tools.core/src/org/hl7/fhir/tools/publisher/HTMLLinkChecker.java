package org.hl7.fhir.tools.publisher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.FileNotifier;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

/**
 * This class started out in life as a pbulisher for epubs, but 
 * we could never get a valid epub. Zip issues? 
 * 
 * Anyway it lives on as the html link checker. 
 * 
 * @author Grahame Grieve
 *
 */
public class HTMLLinkChecker implements FileNotifier {
  public static final String XHTML_TYPE = "application/xhtml+xml";
  public static final String CSS_TYPE = "application/css";
  public static final String PNG_TYPE = "image/png";
  public static final String JPEG_TYPE = "image/jpeg";
  public static final String JS_TYPE = "application/javascript";
  public static final String EOT_TYPE = "application/vnd.ms-fontobject";
  public static final String BIN_TYPE = "application/octet-stream";
  public static final String SVG_TYPE = "application/avg";
  public static final boolean WANT_CHECK = true;

  private class Entry {
    private String filename;
    private String title;
    private String type;
    private boolean include;
    private List<String> anchors = new ArrayList<String>();
    private boolean checked = false;
    public byte[] bytes;

    public Entry(String filename, String title, String type, boolean include) {
      super();
      this.filename = filename;
      this.title = title;
      this.type = type;
      this.include = include;
    }
  }

  private PageProcessor page;
  private List<Entry> entries = new ArrayList<HTMLLinkChecker.Entry>();
  private List<String> externals = new ArrayList<String>();
  private List<ValidationMessage> issues;
  private String webPath;

  
  public HTMLLinkChecker(PageProcessor page, List<ValidationMessage> issues, String webPath) {
    super();
    this.page = page;
    this.issues = issues;
    this.webPath = webPath;
  }

  public void registerExternal(String filename) {
    if (filename.startsWith("\\"))
      throw new Error("wrong path?");
//    if (filename.startsWith(page.getFolders().dstDir))
//      filename = filename.substring(page.getFolders().dstDir.length());
//    externals.add(filename);
    if (filename.startsWith(page.getFolders().dstDir))
      filename = filename.substring(page.getFolders().dstDir.length());
//    if (type == null)
//      type = BIN_TYPE;
    if (getEntryForFile(filename, "registerExternal") != null)
      throw new Error("File "+filename+" already registered");
    else
      entries.add(new Entry(filename, "--title--", BIN_TYPE, false));
  }
  
  public void registerFile(String filename, String title, String type, boolean include) {
    if (filename.startsWith(page.getFolders().dstDir))
      filename = filename.substring(page.getFolders().dstDir.length()+1);
    if (filename.startsWith("\\"))
      throw new Error("wrong path?");
    if (type == null)
      type = BIN_TYPE;
    if (getEntryForFile(filename, "registerFile") != null)
      throw new Error("File "+filename+" already registered");
    else
      entries.add(new Entry(filename, title, type, include));
  }

  public void produce() throws FileNotFoundException, Exception {
    if (WANT_CHECK) {
      build();
    }
  }



  private void build() throws FileNotFoundException, Exception {
    for (Entry e : entries) {
      if (XHTML_TYPE.equals(e.type)) {
        if (!e.checked)
          check(e);
        if (e.bytes == null)
          System.out.println("no content in "+e.filename);
        else 
        e.bytes = null;        
      } else {
      }
    }
  }

  private void check(Entry e) throws Exception {
    if (new File(Utilities.path(page.getFolders().dstDir, e.filename)).exists()) { 
      e.checked = true;
      XhtmlDocument doc;
      try {
        doc = new XhtmlParser().parse(new FileInputStream(Utilities.path(page.getFolders().dstDir, e.filename)), "html");
        checkAnchors(doc, e);
        checkLinks(doc, e);
        stripDivs(doc);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new XhtmlComposer().compose(stream, doc);
        e.bytes = stream.toByteArray();
        if (e.bytes == null || e.bytes.length == 0)
          throw new Exception("File is empty");
      } catch (Exception e1) {
        throw new Exception("Error parsing "+Utilities.path(page.getFolders().dstDir, e.filename), e1);
      }
    } else {
      reportError(e.filename, "Unable to find file "+e.filename);
    }
  }

  private void stripDivs(XhtmlNode node) {
    for (int i = node.getChildNodes().size() - 1; i >= 0; i--) {
      XhtmlNode child = node.getChildNodes().get(i);
      if ("div".equals(child.getName()) && wantEliminate(child.getAttribute("id")))
        node.getChildNodes().remove(i);
      else 
        stripDivs(child);
    }
  }

  private boolean wantEliminate(String id) {
    if ("segment-header".equals(id))
      return true;
    if ("segment-navbar".equals(id))
      return true;
    if ("segment-breadcrumb".equals(id))
      return true;
    if ("segment-footer".equals(id))
      return true;
    if ("segment-post-footer".equals(id))
      return true;
    return false;
  }

  private void checkAnchors(XhtmlNode node, Entry e) throws FileNotFoundException, Exception {
    if ("a".equals(node.getName())) {
      if (node.getAttributes().containsKey("name")) {
        e.anchors.add(node.getAttribute("name"));
        if (Utilities.noString(node.allText())) { 
          String msg = "Invalid \"a\" link in "+e.filename+" - name "+node.getAttribute("name")+" has no text)";
          reportError(e.filename, msg);      
        }
      }
      else if (node.getAttributes().containsKey("href") || node.getAttributes().containsKey("xlink:href") ) {
      }
      else if (!"true".equals(node.getAttribute("ok"))) {
        String msg = "Invalid \"a\" link in "+e.filename+" - no href or name ("+node.allText()+")";
        reportError(e.filename, msg);      
      }
    }
    if (node.getAttributes().containsKey("id"))
      e.anchors.add(node.getAttribute("id"));
    for (XhtmlNode child : node.getChildNodes())
      checkAnchors(child, e);    
  }

  private void reportError(String path, String msg) {
    if (!ok(msg)) {
      issues.add(new ValidationMessage(Source.Publisher, IssueType.INFORMATIONAL, -1, -1, path, msg, IssueSeverity.ERROR));
    }
  }

  private boolean ok(String msg) {
    if (msg.contains("hspc-specimen"))
      return true;
    if (msg.contains("hspc-performinglaboratory"))
      return true;
    if (msg.contains("hspc-responsibleobserver"))
      return true;
//    if (msg.contains("cda"))
//      return true;
//    if (msg.contains("daf-cqi"))
//      return true;
//    if (msg.contains("quick\\"))
//      return true;
//    if (msg.contains("-examples.html"))
//      return true;
    if (msg.contains("'??"))
      return true;
    return false;
  }

  private void checkLinks(XhtmlNode node, Entry e) throws FileNotFoundException, Exception {
    if ("a".equals(node.getName())) {
      if (node.getAttributes().containsKey("href") || node.getAttributes().containsKey("xlink:href") ) {
        String href = node.getAttribute("href");
        if (Utilities.noString(href))
          href = node.getAttribute("xlink:href");
        check(node, href, e.filename, e.filename);
      }
    }
    for (XhtmlNode child : node.getChildNodes())
      checkLinks(child, e);    
  }

  private void check(XhtmlNode node, String href, String base, String source) throws FileNotFoundException, Exception {
    if (href == null)
      throw new Exception("no ref at "+node.allText());
    if (href.startsWith("http:") || href.startsWith("https:") || href.startsWith("ftp:") || href.startsWith("mailto:"))
      return;
    String path = href;
    String anchor = null;
    if (href.contains("#")) {
      path = href.substring(0, href.indexOf("#"));
      anchor = href.substring(href.indexOf("#") + 1);
    }
    Entry e;
    if (!Utilities.noString(path)) {
      if (href.endsWith("qa.html") || href.endsWith(".epub.zip")) 
        return;
      if ("self-link".equals(node.getAttribute("class")))
        return; 
      String target = collapse(base, path, source);
      if (target.endsWith(".xml") || target.endsWith(".json") || target.endsWith(".jsonld") || target.endsWith(".xsd") || target.endsWith(".shex") || target.endsWith(".txt") || target.endsWith(".sch") || target.endsWith(".pdf") || target.endsWith(".epub")) {
        if (!(new File(Utilities.path(page.getFolders().dstDir, target)).exists()))
          reportError(base, "Broken Link (1) in "+base+": '"+href+"' not found at \""+Utilities.path(page.getFolders().dstDir, target)+"\" ("+node.allText()+")");
        node.setAttribute("href", webPath+"/"+target.replace(File.separatorChar, '/'));
        e = null;
      } else if (externals.contains(target)) {
        node.setAttribute("href", webPath+"/"+target.replace(File.separatorChar, '/'));
        e = null;
      } else {
        e = getEntryForFile(target, source);
        if (e == null) {
          if (href.startsWith("v2/") || href.startsWith("v3/")) // we can't check those links
            return;
          if (target.endsWith(".zip") || target.endsWith(".ttl") || target.endsWith(".jar") || target.endsWith(".cfm"))
            return;
          reportError(base, "Broken Link (2) in "+base+": '"+href+"' not found at \""+target+"\"("+node.allText()+")");
          return;
        } else if (!e.include) {
          node.setAttribute("href", webPath+"/"+node.getAttribute("href"));          
        }
      }
    } else 
      e = getEntryForFile(base, source);
    if (Utilities.noString(anchor)) {
//      if (e == null) - need to enable this an fix everything it finds
//        reportError("Broken Link in "+base+": '"+href+"' anchor not found ("+node.allText()+")");
    } else {
      if (e!= null) {
        if (!e.checked)
          check(e);
//td        if (!e.anchors.contains(anchor))
//td          reportError("Broken Link in "+base+": '"+href+"' anchor not found ("+node.allText()+")");
      }
    }
  }

  private Entry getEntryForFile(String target, String source) {
    for (Entry e : entries) {
      if (e.filename.equalsIgnoreCase(target)) {
        if (!e.filename.equals(target))
          System.out.println("Case Error: found "+e.filename+" looking for "+target+" in "+source);
        return e;
      }
    }
    return null;
  }

  private String collapse(String base, String path, String source) throws Exception {
    if (path.contains("?"))
      path = path.substring(0, path.indexOf("?"));
    String mBase = base;
    String mPath = path;
    if (base.contains(File.separator))
      base = base.substring(0, base.lastIndexOf(File.separator));
    else
      base = "";
    while (path.startsWith("../") || path.startsWith("./")) {
      if (path.startsWith("./"))
        path = path.substring(2);
      else {
        path = path.substring(3);
        if (Utilities.noString(base)) 
          System.out.println("error in path - ../ out of zone in link "+mPath+" in base "+mBase+" in "+source);
        else if (!base.contains(File.separator))
          base = null;
        else
          base = base.substring(0, base.lastIndexOf(File.separator));
      }
    }
    return Utilities.noString(base) ? path.replace('/', File.separatorChar) : base+File.separator+path.replace('/', File.separatorChar);
  }

  public static String determineType(String filename) {
    String ext = filename.substring(filename.lastIndexOf(".")+1);
    if ("jpg".equals(ext))
      return JPEG_TYPE;
    if ("css".equals(ext))
      return CSS_TYPE;
    if ("html".equals(ext))
      return XHTML_TYPE;
    if ("png".equals(ext))
      return PNG_TYPE;
    if ("svg".equals(ext))
      return SVG_TYPE;
    if ("eot".equals(ext))
      return EOT_TYPE;
    if ("js".equals(ext))
      return JS_TYPE;
    if ("ttf".equals(ext) || "otf".equals(ext) || "woff".equals(ext) || "ico".equals(ext))
      return BIN_TYPE;
    
    if ("pdf".equals(ext))
      return null;
    if ("xml".equals(ext) || "json".equals(ext) || "bak".equals(ext) || "json".equals(ext))
      return null;
    return null;
  }

  @Override
  public void copyFile(String src, String dst) {
    if (dst.startsWith(page.getFolders().dstDir))
      registerFile(dst.substring(page.getFolders().dstDir.length()+1), "Support File", determineType(dst), true);
    
  }


}
