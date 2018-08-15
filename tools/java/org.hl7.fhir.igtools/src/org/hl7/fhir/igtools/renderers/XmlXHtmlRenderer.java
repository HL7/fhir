package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.ElementDecoration;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.IXMLWriter;
import org.hl7.fhir.utilities.xml.XMLNamespace;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XMLWriterState;
import org.hl7.fhir.utilities.xml.XMLWriterStateStack;

public class XmlXHtmlRenderer implements IXMLWriter {
  private StringBuilder b;
  

  private String charset;
  private boolean pendingClose;
  private boolean pendingOpen;
  private String pendingComment;
  private OutputStream stream;
  private boolean started = false;
  private String[] specialAttributeNames = new String[] {"id", "name" };
  private int attributeLineWrap = 80;
  private String href;
  private List<ElementDecoration> decorations1 = new ArrayList<ElementDecoration>();
  private List<ElementDecoration> decorations2 = new ArrayList<ElementDecoration>();
  
  protected boolean condition(boolean bTest, String message) throws IOException {
    if (!bTest)
      throw new IOException(message);
    return bTest;
  }

  // -- writing context ------------------------------------------------

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#start()
   */
  @Override
  public void start() throws IOException {
    condition(!started, "attempt to start after starting");
    levels.clear();
    attributes = null;
    started = true;
    b = new StringBuilder();
    b.append("<pre class=\"xml\">\r\n");
  }

  private void checkStarted () throws IOException {
    condition(started, "not started");
  }

  private void checkInElement() throws IOException {
    condition(levels.size() > 0, "not in an element");
  }

  // -- link ----------------------------------------------------------
  
  @Override
  public void link(String href) {
    this.href = href;
  }

  public String useHref() {
    String result = this.href;
    this.href = null;
    return result;
  }

  // -- attributes ----------------------------------------------------

  private class Attribute {
    private String name;
    private String value;
    private String link;
    public Attribute(String name, String value, String link) {
      super();
      this.name = name;
      this.value = value;
      this.link = link;
    }
  }
  private List<Attribute> attributes;
  
  private void addAttribute(String name, String value) throws IOException {
    addAttribute(name, value, false);
  }

  private void addAttribute(String name, String value, boolean noLines) throws IOException {
    if (!XMLUtil.isNMToken(name))
      throw new IOException("XML name "+name+" is not valid for value '"+value+"'");

    newLevelIfRequired();
    value = XMLUtil.escapeXML(value, charset, noLines);

    if (attributes == null) 
      attributes = new ArrayList<Attribute>();
    attributes.add(new Attribute(name, value, useHref()));
  }
  
  private void writeAttributes(int col) throws IOException {
    sortAttributes(); 
    int c = col;
    writeAttributeSet(c, col);
    attributes = null;
  }


  private void sortAttributes() {
    if (attributes == null)
      return;
    // bubble sort - look, it's easy
    for (int i = 0; i < attributes.size() - 1; i++) {
      for (int j = 0; j < attributes.size() - 1; j++) {
        if (String.CASE_INSENSITIVE_ORDER.compare(attributes.get(j).name, attributes.get(j+1).name) < 0) {
          Attribute t = attributes.get(j);
          attributes.set(j, attributes.get(j+1));
          attributes.set(j+1, t);
        }
      }
    }
  }

  private int writeAttributeSet(int col, int wrap) throws IOException {
    // first pass: name, id
    if (attributes != null) {
      for (Attribute a : attributes) {
        col = col + a.name.length()+a.value.length() + 4;
        if (col > attributeLineWrap && col > wrap) {
          b.append("\r\n");
          for (int j = 0; j < wrap; j++)
            b.append(" ");
          col = wrap;
        }
        b.append(' ');
        if (a.link != null) {
          b.append("<a href=\""+a.link+"\">");
          b.append(a.name);
          b.append("</a>");
        } else
          b.append(a.name);
        b.append("=&quot;");
        if (a.value != null)
          b.append(Utilities.escapeXml(a.value));
        b.append("&quot;");
      }
    }
    return col;
  }

  @Override
  public void attribute(String namespace, String name, String value, boolean onlyIfNotEmpty) throws IOException {
    if (!onlyIfNotEmpty || value != null && !value.equals(""))
      attribute(namespace, name, value);
  }
  
  @Override
  public void attribute(String namespace, String name, String value) throws IOException {
    checkStarted();
    if (namespace == null || namespace.equals("")) 
      addAttribute(name, value);
    else
      addAttribute(getNSAbbreviation(namespace)+name, value);
  }

  @Override
  public void attribute(String name, String value, boolean onlyIfNotEmpty) throws IOException {
    if (!onlyIfNotEmpty || value != null && !value.equals(""))
      attribute(name, value);
  }

  @Override
  public void attribute(String name, String value) throws IOException {
    checkStarted();
    addAttribute(name, value);
  }

  @Override
  public void attributeNoLines(String name, String value) throws IOException {
    checkStarted();
    addAttribute(name, value, true);
  }

  // -- levels -------------------------------------------------

  private XMLWriterStateStack levels = new XMLWriterStateStack();

  private void newLevelIfRequired() throws IOException {
    if (!pendingOpen) {
      if (!levels.empty())
        levels.current().seeChild();
      XMLWriterState level = new XMLWriterState();
      level.setPretty(isPretty());
      levels.push(level);
      pendingOpen = true;
    }
  }

  // -- namespaces ---------------------------------------------

  private void defineNamespace(String namespace, String abbrev) throws IOException {
    checkStarted();
    if (namespace != null && !namespace.equals("")) {
      if ("".equals(abbrev))
        abbrev = null;

      newLevelIfRequired();

      levels.current().addNamespaceDefn(namespace, abbrev);
      if (abbrev == null)
        addAttribute("xmlns", namespace);
      else
        addAttribute("xmlns:"+abbrev, namespace);
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#findByNamespace(java.lang.String)
   */
  public XMLNamespace findByNamespace(String namespace) {
    for (int i = levels.size() - 1; i >= 0; i--) {
      XMLNamespace ns = levels.item(i).getDefnByNamespace(namespace);
      if (ns != null)
        return ns;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#namespaceDefined(java.lang.String)
   */
  @Override
  public boolean namespaceDefined(String namespace) {
    return namespace == null || namespace.equals("") || findByNamespace(namespace) != null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#findByAbbreviation(java.lang.String)
   */
  public XMLNamespace findByAbbreviation(String abbreviation) {
    for (int i = levels.size() - 1; i >= 0; i--) {
      XMLNamespace ns = levels.item(i).getDefnByAbbreviation(abbreviation);
      if (ns != null)
        return ns;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#abbreviationDefined(java.lang.String)
   */
  @Override
  public boolean abbreviationDefined(String abbreviation) {
    return findByAbbreviation(abbreviation) != null;
  }

  protected XMLNamespace findDefaultNamespace() {
    for (int i = levels.size() - 1; i >= 0; i--) {
      XMLNamespace ns = levels.item(i).getDefaultNamespace();
      if (ns != null)
        return ns;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#getDefaultNamespace()
   */
  @Override
  public String getDefaultNamespace() {
    XMLNamespace ns = findDefaultNamespace();
    if (ns == null)
      return null;
    else
      return ns.getNamespace();
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#namespace(java.lang.String)
   */
  @Override
  public void namespace(String namespace) throws IOException {
    if (!namespaceDefined(namespace)) {
      int index = 0;
      while (abbreviationDefined("ns"+Integer.toString(index))) 
        index++;
      defineNamespace(namespace, "ns"+Integer.toString(index));
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#defaultNamespace(java.lang.String)
   * 
   * Replace defaultNamespace()
   */
  @Override
  public void setDefaultNamespace(String namespace) throws IOException {
    if ((namespace == null && getDefaultNamespace() != null) ||
        (namespace != null && !namespace.equals(getDefaultNamespace())))
      defineNamespace(namespace, "");     
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#namespace(java.lang.String, java.lang.String)
   */
  @Override
  public void namespace(String namespace, String abbreviation) throws IOException {
    XMLNamespace ns = findByAbbreviation(abbreviation);
    if (ns == null || !ns.getNamespace().equals(namespace))
      defineNamespace(namespace, abbreviation);
  }


  private String getNSAbbreviation(String namespace) throws IOException {
    if ("http://www.w3.org/XML/1998/namespace".equals(namespace))
      return "xml:";
    
    if (namespace == null || "".equals(namespace))
      return "";
    
    XMLNamespace ns = findByNamespace(namespace);
    if (ns == null)
      throw new IOException("Namespace "+namespace+" is not defined");
    else if (ns.getAbbreviation() == null)
      return "";
    else
      return ns.getAbbreviation()+":";
  }

  // -- public API -----------------------------------------------------------

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#comment(java.lang.String, boolean)
   */
  @Override
  public void comment(String comment, boolean doPretty) throws IOException {
    checkStarted();
    if (pendingClose) { 
      b.append("&gt;");
      writePendingComment();
      pendingClose = false;
    }
    if (doPretty) {
      writePretty();
    }
    if (levels.inComment())
      b.append("&lt;!-- "+Utilities.escapeXml(comment)+" -- &gt;");
    else
      b.append("&lt;!-- "+Utilities.escapeXml(comment)+" --&gt;");
    if (doPretty && !isPretty())
      writePretty();
  }

  
  private void writeDecorations() throws IOException {
    if (decorations2.size() > 0) {
      for (ElementDecoration d : decorations2) {
        if (d.hasLink()) 
          b.append(" <a href=\"\"><img title=\""+Utilities.escapeXml(d.getText())+"\" src=\""+d.getIcon()+"\"/></a>");
        else
          b.append(" <img title=\""+Utilities.escapeXml(d.getText())+"\" src=\""+d.getIcon()+"\"/>");
      }
      decorations2.clear();
    }    
  }
  private void writePendingComment() throws IOException {
    if (pendingComment != null) {
      if (isPretty())
        b.append("   ");
      if (levels.inComment())
        b.append("&lt;!-- "+pendingComment+" -- &gt;");
      else
        b.append("&lt;!-- "+pendingComment+" --&gt;");
    }
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#open(java.lang.String, java.lang.String)
   */
  @Override
  public void enter(String namespace, String name) throws IOException {
    enter(namespace, name, null);
  }


  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#open(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void enter(String namespace, String name, String comment) throws IOException {
    if (name == null)
      throw new Error("name == null");
    if (!XMLUtil.isNMToken(name))
      throw new IOException("XML name "+name+" is not valid");
    checkStarted();
    if (pendingClose) { 
      b.append("&gt;");
      writeDecorations();
      writePendingComment();
      pendingClose = false;
    }
    decorations2.addAll(decorations1);
    decorations1.clear();
    if (name == null) {
      throw new IOException("name is null");
    }
    newLevelIfRequired();
    levels.current().setName(name);
    levels.current().setNamespace(namespace);
    int col = writePretty();
    b.append("&lt;");
    String href= useHref();
    if (namespace == null) {
      if (href != null)
        b.append("<a href=\""+href+"\">");
      b.append(name);
      if (href != null)
        b.append("</a>");
      col = col + name.length()+1;
    } else {
      if (href != null)
        b.append("<a href=\""+href+"\">");
      String n = getNSAbbreviation(namespace)+name;
      b.append(n);
      if (href != null)
        b.append("</a>");
      col = col + n.length()+1;
    }
    writeAttributes(col);
    pendingOpen = false;
    pendingClose = true;
    pendingComment = comment;
  }


  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#close(java.lang.String)
   */
  @Override
  public void exit(String name) throws IOException {
    checkStarted();
    if (levels.empty())
      throw new IOException("Unable to close null|"+name+", nothing to close");
    if (levels.current().getNamespace() != null || !levels.current().getName().equals(name))
      throw new IOException("Unable to close null|"+name+", found "+levels.current().getNamespace()+"|"+levels.current().getName());
    exit();
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#close(java.lang.String, java.lang.String)
   */
  @Override
  public void exit(String namespace, String name) throws IOException {
    checkStarted();
    if (levels.empty())
      throw new IOException("Unable to close "+namespace+"|"+name+", nothing to close");
    if (levels == null)
      throw new Error("levels = null");
    if (levels.current() == null)
      throw new Error("levels.current() = null");
    if (levels.current().getName() == null)
      throw new Error("levels.current().getName() = null");
    if (levels.current().getNamespace() == null)
      throw new Error("levels.current().getNamespace() = null");
    if (name == null)
      throw new Error("name = null");
    if (namespace == null)
      throw new Error("namespace = null");
    if (!levels.current().getNamespace().equals(namespace) || !levels.current().getName().equals(name))
      throw new IOException("Unable to close "+namespace+"|"+name+", found "+levels.current().getNamespace()+"|"+levels.current().getName());
    exit();
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#closeToLevel(int)
   */
  @Override
  public void exitToLevel(int count) throws IOException {
    checkStarted();
    while (levels.size() > count)
      exit();   
  }


    
  @Override
  public void end() throws IOException {
    checkStarted();
    if (!levels.empty()) 
      throw new IOException("Called end() before exiting all opened elements");
    b.append("</pre>\r\n");
  }
  
  @Override
  public void exit() throws IOException {
    checkStarted();
    if (levels.empty()) {
      throw new IOException("Called exit one too many times");
    } else {
      if (pendingClose) { 
        b.append("/&gt;");
        writeDecorations();
        writePendingComment();
        pendingClose = false;
      } else {
        if (levels.current().hasChildren())
          writePretty();
        b.append("&lt;/");
        if (levels.current().getNamespace() == null)
          b.append(levels.current().getName());
        else
          b.append(getNSAbbreviation(levels.current().getNamespace())+levels.current().getName());
        b.append("&gt;");
      }
      levels.pop();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#open(java.lang.String)
   */
  @Override
  public void enter(String name) throws IOException {
    enter(null, name);
  }


  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#element(java.lang.String, java.lang.String, java.lang.String, boolean)
   */
  @Override
  public void element(String namespace, String name, String content, boolean onlyIfNotEmpty) throws IOException {
    if (!onlyIfNotEmpty || content != null && !content.equals(""))
      element(namespace, name, content);
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#element(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void element(String namespace, String name, String content, String comment) throws IOException {
    if (!XMLUtil.isNMToken(name))
      throw new IOException("XML name "+name+" is not valid");
    enter(namespace, name, comment);
    text(content);
    exit();
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#element(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void element(String namespace, String name, String content) throws IOException {
    if (!XMLUtil.isNMToken(name))
      throw new IOException("XML name "+name+" is not valid");
    enter(namespace, name);
    text(content);
    exit();
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#element(java.lang.String, java.lang.String, boolean)
   */
  @Override
  public void element(String name, String content, boolean onlyIfNotEmpty) throws IOException {
    if (!onlyIfNotEmpty || content != null && !content.equals(""))
      element(null, name, content);
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#element(java.lang.String, java.lang.String)
   */
  @Override
  public void element(String name, String content) throws IOException {
    element(null, name, content);
  }

  @Override
  public void element(String name) throws IOException {
    element(null, name, null);
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#text(java.lang.String)
   */
  @Override
  public void text(String content) throws IOException {
    text(content, false);
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#text(java.lang.String, boolean)
   * 
   * Replace escapeText()
   */
  @Override
  public void text(String content, boolean dontEscape) throws IOException {
    checkInElement();
    if (content != null) {
      if (pendingClose) { 
        b.append("&gt;");
        writeDecorations();
        writePendingComment();
        pendingClose = false;
      }
      if (dontEscape)
        b.append(content);
      else
        b.append(XMLUtil.escapeXML(content, charset, false));
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#cData(java.lang.String)
   */
  @Override
  public void cData(String text) throws IOException {
    text("&lt;![CDATA["+text+"]]&gt;");   
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#writeBytes(byte[])
   */
  @Override
  public void writeBytes(byte[] bytes) throws IOException {
    checkInElement();
    if (pendingClose) { 
      b.append("&gt;");
      writeDecorations();
      writePendingComment();
      pendingClose = false;
    }
    b.append(bytes);
  }


  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#startCommentBlock()
   */
  @Override
  public void startCommentBlock() throws IOException {
    if (levels.inComment())
      throw new IOException("cannot nest comments");
    levels.current().setInComment(true);
    if (isPretty())
      writePretty();
    b.append("&lt;!--");
    if (isPretty())
      writePretty();    
  }

  /* (non-Javadoc)
   * @see org.eclipse.ohf.utilities.xml.IXMLWriter#endCommentBlock()
   */
  @Override
  public void endCommentBlock() throws IOException {
    if (!levels.inComment())
      throw new IOException("cannot close a comment block when it is open");
    if (!levels.current().isInComment())
      throw new IOException("cannot close a comment block when it is open");
    if (isPretty())
      writePretty();
    b.append("--&gt;");
    if (isPretty())
      writePretty();    
    levels.current().setInComment(false);
  }


  public int writePretty() throws IOException {
    return writePretty(true);
  }
  
  public int writePretty(boolean eoln) throws IOException {
    if (isPretty()) {
      if (eoln)
        b.append("\r\n");
      for (int i = 0; i < levels.size() - 1; i++)
        b.append("  ");
      return (levels.size() - 1) * 2;
    } else
      return 0;
  }

  public String[] getSpecialAttributeNames() {
    return specialAttributeNames;
  }

  public void setSpecialAttributeNames(String[] specialAttributeNames) {
    this.specialAttributeNames = specialAttributeNames;
  }

  public int getAttributeLineWrap() {
    return attributeLineWrap;
  }

  public void setAttributeLineWrap(int attributeLineWrap) {
    this.attributeLineWrap = attributeLineWrap;
  }

  @Override
  public void escapedText(String content) throws IOException {
    if (decorations2.isEmpty())
      b.append("\r\n");
    else
      writeDecorations();
    for (int i = 0; i < levels.size(); i++)
      b.append("  ");
    int i = content.length();
    while (i > 0 && (content.charAt(i-1) == '\r' || content.charAt(i-1) == '\n'))
     i--;
    b.append(Utilities.escapeXml(content.substring(0, i)));
  }

  public void processingInstruction(String value) throws IOException {
    b.append("&lt;?"+value+"?&gt;");
    b.append("\r\n");
  }

  @Override
  public boolean isPretty() throws IOException {
    return true;
  }

  @Override
  public void setPretty(boolean pretty) throws IOException {
  }

  @Override
  public String toString() {
    return b.toString();
  }

  @Override
  public void decorate(ElementDecoration decoration) throws IOException {
    decorations1.add(decoration);
  }


  
}
