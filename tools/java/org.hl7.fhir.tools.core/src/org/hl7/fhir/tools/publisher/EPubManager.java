package org.hl7.fhir.tools.publisher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.tools.publisher.BreadCrumbManager.Page;
import org.hl7.fhir.utilities.FileNotifier;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLWriter;

public class EPubManager implements FileNotifier {
  public static final String XHTML_TYPE = "application/xhtml+xml";
  public static final String CSS_TYPE = "application/css";
  public static final String PNG_TYPE = "image/png";
  public static final String JPEG_TYPE = "image/jpeg";
  public static final String JS_TYPE = "application/javascript";
  public static final String EOT_TYPE = "application/vnd.ms-fontobject";
  public static final String BIN_TYPE = "application/octet-stream";
  public static final String SVG_TYPE = "application/avg";

  private class Entry {
    private String filename;
    private String title;
    private String type;
    private List<String> anchors = new ArrayList<String>();
    private boolean checked = false;
    public byte[] bytes;

    public Entry(String filename, String title, String type) {
      super();
      this.filename = filename;
      this.title = title;
      this.type = type;
    }
  }

  private PageProcessor page;
  private List<Entry> entries = new ArrayList<EPubManager.Entry>();
  private List<String> externals = new ArrayList<String>();
  private String uuid;

  
  public EPubManager(PageProcessor page) {
    super();
    this.page = page;
  }

  public void registerExternal(String filename) {
    if (filename.startsWith(page.getFolders().dstDir))
      filename = filename.substring(page.getFolders().dstDir.length());
    externals.add(filename);
  }
  
  public void registerFile(String filename, String title, String type) {
    if (filename.startsWith(page.getFolders().dstDir))
      filename = filename.substring(page.getFolders().dstDir.length());
    if (type != null && getEntryForFile(filename) == null)
      entries.add(new Entry(filename, title, type));
  }

  public void produce() throws FileNotFoundException, Exception {
    ZipGenerator zip = new ZipGenerator(Utilities.path(page.getFolders().dstDir, "fhir-v"+page.getVersion()+".epub"));
    zip.addMimeTypeFile("mimetype", Utilities.path(page.getFolders().rootDir, "tools", "epub", "mimetype"));
    zip.addFileName("META-INF/container.xml", Utilities.path(page.getFolders().rootDir, "tools", "epub", "container.xml"), false);
    zip.addBytes("OEBPS/content.opf", generateContentFile(), false);
    zip.addBytes("OEBPS/toc.ncx", generateIndexFile(), false);
    build(zip);
    zip.close();
    
//    zip = new ZipGenerator(Utilities.path(page.getFolders().dstDir, "fhir-v"+page.getVersion()+".epub.zip"));
//    zip.addFileName("fhir-v"+page.getVersion()+".epub", Utilities.path(page.getFolders().dstDir, "fhir-v"+page.getVersion()+".epub"), false);
//    zip.close();
//    // new File(Utilities.path(page.getFolders().dstDir, "fhir-v"+page.getVersion()+".epub")).delete();
  }


  private byte[] generateContentFile() throws Exception {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    XMLWriter xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(true);
    xml.start();
    xml.namespace("http://www.idpf.org/2007/opf", "");
    xml.attribute("unique-identifier", "BookID");
    xml.attribute("version", "2.0");
    xml.open("package");
    
    xml.namespace("http://purl.org/dc/elements/1.1/", "dc");
    xml.namespace("http://www.idpf.org/2007/opf", "opf");    
    xml.open("metadata");    
    xml.element("http://purl.org/dc/elements/1.1/", "title", "FHIR Book");
    xml.attribute("http://www.idpf.org/2007/opf", "role", "aut");
    xml.element("http://purl.org/dc/elements/1.1/", "creator", "FHIR Project Team");
    xml.element("http://purl.org/dc/elements/1.1/", "language", "en-US");
    xml.element("http://purl.org/dc/elements/1.1/", "rights", "http://hl7.org/fhir/license.html");
    xml.element("http://purl.org/dc/elements/1.1/", "publisher", "http://hl7.org/fhir");
    xml.attribute("id", "BookID");
    xml.attribute("http://www.idpf.org/2007/opf", "scheme", "UUID");
    uuid = UUID.randomUUID().toString();
    xml.element("http://purl.org/dc/elements/1.1/", "identifier", uuid);
    xml.close("metadata");
    
    xml.open("manifest");
    xml.attribute("id", "ncx");
    xml.attribute("href", "toc.ncx");
    xml.attribute("media-type", "application/x-dtbncx+xml");   
    xml.element("item", null);
    for (int i = 0; i < entries.size(); i++) {
      Entry e = entries.get(i);
      xml.attribute("id", "n"+Integer.toString(i));
      xml.attribute("href", e.filename);
      xml.attribute("media-type", e.type);
      xml.element("item", null);
    }
    xml.close("manifest");
    
    xml.attribute("toc", "ncx");
    xml.open("spine");
    List<String> spineOrder = page.getBreadCrumbManager().getSpineOrder();
    for (String n : spineOrder) 
      addToSpine(xml, n);
    List<String> others = new ArrayList<String>();
    for (Entry e : entries) 
      if (!spineOrder.contains(e.filename))
        others.add(e.filename);
    Collections.sort(others);
    for (String n : others) 
      addToSpine(xml, n);
    xml.close("spine");
    xml.close("package");
    xml.close();
    return stream.toByteArray();
  }

  private byte[] generateIndexFile() throws Exception {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    XMLWriter xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(true);
    xml.start();
    xml.namespace("http://www.daisy.org/z3986/2005/ncx/", "");
    xml.attribute("version", "2005-1");
    xml.open("ncx");
    
    xml.open("head");    
    
    xml.attribute("name", "dtb:uid");
    xml.attribute("content", uuid);
    xml.element("meta", null);
    
    xml.attribute("name", "dtb:depth");
    xml.attribute("content", "1");
    xml.element("meta", null);
    
    xml.attribute("name", "dtb:totalPageCount");
    xml.attribute("content", "0");
    xml.element("meta", null);
    
    xml.attribute("name", "dtb:maxPageNumber");
    xml.attribute("content", "0");
    xml.element("meta", null);
    
    xml.close("head");
    xml.open("docTitle");
    xml.element("text", "FHIR Specification v"+page.getVersion());
    xml.close("docTitle");
    
    xml.open("navMap");
    int i = 1;
    addNavPoint(xml, page.getBreadCrumbManager().getPage(), i);
    for (org.hl7.fhir.tools.publisher.BreadCrumbManager.Node p : page.getBreadCrumbManager().getPage().getChildren()) {
      if (p instanceof Page) {
        i++;
        addNavPoint(xml, (Page) p, i);
      }
    }
    xml.close("navMap");
    xml.close("ncx");
    xml.close();
    return stream.toByteArray();
  }

  private void addNavPoint(XMLWriter xml, Page page, int i) throws Exception {
    xml.attribute("id", "id"+page.getId());
    xml.attribute("playOrder", Integer.toString(i));
    xml.open("navPoint");
    xml.open("navLabel");
    xml.element("text", page.getTitle());
    xml.close("navLabel");
    xml.attribute("src", page.getFilename());
    xml.element("content", null);
    xml.close("navPoint");
    
  }

  private void addToSpine(XMLWriter xml, String n) throws IOException {
    int i = getEntryIndex(n);
    xml.comment(n, false);
    xml.attribute("idref", "n"+Integer.toString(i));
    xml.element("itemref", null);
  }

  private void build(ZipGenerator zip) throws FileNotFoundException, Exception {
    for (Entry e : entries) {
      if (XHTML_TYPE.equals(e.type)) {
        if (!e.checked)
          check(e);
        zip.addBytes("OEBPS/"+e.filename, e.bytes, false);
        e.bytes = null;        
      } else {
        zip.addFileName("OEBPS/"+e.filename, Utilities.path(page.getFolders().dstDir, e.filename), true);
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
      } catch (Exception e1) {
        throw new Exception("Error parsing "+Utilities.path(page.getFolders().dstDir, e.filename), e1);
      }
    } else {
      reportError("Unable to find file "+e.filename);
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
      }
      else if (node.getAttributes().containsKey("href") || node.getAttributes().containsKey("xlink:href") ) {
      }
      else if (!"true".equals(node.getAttribute("ok"))) {
        String msg = "Invalid \"a\" link in "+e.filename+" - no href or name ("+node.allText()+")";
        reportError(msg);      
      }
    }
    if (node.getAttributes().containsKey("id"))
      e.anchors.add(node.getAttribute("id"));
    for (XhtmlNode child : node.getChildNodes())
      checkAnchors(child, e);    
  }

  private void reportError(String msg) {
    page.log(msg, LogMessageType.Error);
    page.getQa().brokenlink(msg);
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
      if (href.endsWith("qa.html") || href.endsWith(".epub.zip")) 
        return;
      String target = collapse(base, path);
      if (target.endsWith(".xml") || target.endsWith(".json") || target.endsWith(".xsd") || target.endsWith(".zip") || target.endsWith(".xls") || target.endsWith(".txt") || target.endsWith(".sch") || target.endsWith(".pdf") || target.endsWith(".epub")) {
        if (!(new File(Utilities.path(page.getFolders().dstDir, target)).exists()))
          reportError("Broken Link in "+base+": '"+href+"' not found at \""+Utilities.path(page.getFolders().dstDir, target)+"\" ("+node.allText()+")");
        node.setAttribute("href", "http://hl7.org/fhir/"+target.replace(File.separatorChar, '/'));
        e = null;
      } else if (externals.contains(target)) {
        node.setAttribute("href", "http://hl7.org/fhir/"+target.replace(File.separatorChar, '/'));
        e = null;
      } else {
        e = getEntryForFile(target);
        if (e == null) {
          if (href.startsWith("v2/") || href.startsWith("v3/")) // we can't check those links
            return;
          reportError("Broken Link in "+base+": '"+href+"' not found at \""+target+"\"("+node.allText()+")");
          return;
        }
      }
    } else 
      e = getEntryForFile(base);
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

  private Entry getEntryForFile(String target) {
    for (Entry e : entries) {
      if (e.filename.equals(target))
        return e;
    }
    return null;
  }

  private int getEntryIndex(String target) {
    for (int i = 0; i < entries.size(); i++) {
      Entry e = entries.get(i);
      if (e.filename.equals(target))
        return i;
    }
    return -1;
  }

  private String collapse(String base, String path) throws Exception {
    String mBase = base;
    String mPath = path;
    if (base.contains(File.separator))
      base = base.substring(0, base.lastIndexOf(File.separator));
    else
      base = "";
    while (path.startsWith("../")) {
      path = path.substring(3);
      if (Utilities.noString(base)) 
        throw new Exception("error in path - ../ out of zone in link "+mPath+" in base "+mBase);
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
    registerFile(dst.substring(page.getFolders().dstDir.length()+1), "Support File", determineType(dst));
    
  }


}
