package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

public class EPubManager {
  public static final String XHTML_TYPE = "text/html";
  public static final String CSS_TYPE = "application/css";
  public static final String PNG_TYPE = "image/png";
  public static final String JPEG_TYPE = "image/jpeg";

  private class Entry {
    private String filename;
    private String title;
    private String type;
    private List<String> anchors = new ArrayList<String>();
    private boolean checked = false;

    public Entry(String filename, String title, String type) {
      super();
      this.filename = filename;
      this.title = title;
      this.type = type;
    }
  }

  private String pubdir;
  private QaTracker qa;
  private Logger log;
  private List<Entry> entries = new ArrayList<EPubManager.Entry>();

  
  public EPubManager(String pubdir, QaTracker qa, Logger log) {
    super();
    this.pubdir = pubdir;
    this.qa = qa;
    this.log = log;
  }

  public void registerFile(String filename, String title, String type) {
    if (filename.startsWith(pubdir))
      filename = filename.substring(pubdir.length());
    if (getEntryForFile(filename) == null)
      entries.add(new Entry(filename, title, type));
  }

  public void produce() throws FileNotFoundException, Exception {
    checkLinks();    
  }

  private void checkLinks() throws FileNotFoundException, Exception {
    for (Entry e : entries) {
      if (XHTML_TYPE.equals(e.type) && !e.checked) {
        check(e);
      }
    }
  }

  private void check(Entry e) throws FileNotFoundException, Exception {
    if (new File(Utilities.path(pubdir, e.filename)).exists()) { 
      e.checked = true;
      XhtmlDocument doc = new XhtmlParser().parse(new FileInputStream(Utilities.path(pubdir, e.filename)), "html");
      checkAnchors(doc, e);
      checkLinks(doc, e); 
    } else {
      reportError("Unable to find file "+e.filename);
    }
  }

  private void checkAnchors(XhtmlNode node, Entry e) throws FileNotFoundException, Exception {
    if ("a".equals(node.getName())) {
      if (node.getAttributes().containsKey("name")) {
        e.anchors.add(node.getAttribute("name"));
      }
      else if (node.getAttributes().containsKey("href") || node.getAttributes().containsKey("xlink:href") ) {
      }
      else if (!"true".equals(node.getAttribute("ok"))) {
        String msg = "Invalid \"a\" link in "+e.filename+" - no href or name ("+node.allText()+")";
        reportError(msg);      
      }
    }
    for (XhtmlNode child : node.getChildNodes())
      checkAnchors(child, e);    
  }

  private void reportError(String msg) {
    log.log(msg);
    qa.brokenlink(msg);
  }

  private void checkLinks(XhtmlNode node, Entry e) throws FileNotFoundException, Exception {
    if ("a".equals(node.getName())) {
      if (node.getAttributes().containsKey("href") || node.getAttributes().containsKey("xlink:href") ) {
        String href = node.getAttribute("href");
        if (Utilities.noString(href))
          href = node.getAttribute("xlink:href");
        check(node, href, e.filename);
      }
    }
    for (XhtmlNode child : node.getChildNodes())
      checkLinks(child, e);    
  }

  private void check(XhtmlNode node, String href, String base) throws FileNotFoundException, Exception {
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
      if (href.endsWith("qa.html")) 
        return;
      String target = collapse(base, path);
      if (target.endsWith(".xml") || target.endsWith(".json") || target.endsWith(".xsd") || target.endsWith(".zip") || target.endsWith(".sch") || target.endsWith(".pdf")) {
        node.setAttribute("href", "http://hl7.org/fhir/"+target.replace(File.separatorChar, '/'));
        e = null;
      } else {
        e = getEntryForFile(target);
        if (e == null) {
          reportError("Broken Link in "+base+": '"+href+"' not found ("+node.allText()+")");
          return;
        }
      }
    } else 
      e = getEntryForFile(base);
    if (!Utilities.noString(anchor)) {
      if (!e.checked)
        check(e);
      if (!e.anchors.contains(anchor))
        reportError("Broken Link in "+base+": '"+href+"' anchor not found ("+node.allText()+")");
    }
  }

  private Entry getEntryForFile(String target) {
    for (Entry e : entries) {
      if (e.filename.equals(target))
        return e;
    }
    return null;
  }

  private String collapse(String base, String path) throws Exception {
    if (base.contains(File.separator))
      base = base.substring(0, base.lastIndexOf(File.separator));
    else
      base = "";
    while (path.startsWith("../")) {
      path = path.substring(3);
      if (Utilities.noString(base)) 
        throw new Exception("error in path - ../ out of zone");
      else if (!base.contains(File.separator))
        base = null;
      else
        base = base.substring(0, base.lastIndexOf(File.separator));
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
    if ("pdf".equals(ext))
      return null;
    if ("xml".equals(ext))
      return null;
    if ("json".equals(ext))
      return null;
    return null;
  }


}
