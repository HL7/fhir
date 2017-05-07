package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hl7.fhir.r4.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r4.context.IWorkerContext.ILoggingService.LogCategory;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.igtools.publisher.HTLMLInspector.NodeChangeType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlNode.Location;
import org.omg.CosNaming.NamingContextExtOperations;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

//import org.owasp.html.Handler;
//import org.owasp.html.HtmlChangeListener;
//import org.owasp.html.HtmlPolicyBuilder;
//import org.owasp.html.HtmlSanitizer;
//import org.owasp.html.HtmlStreamEventReceiver;
//import org.owasp.html.HtmlStreamRenderer;
//import org.owasp.html.PolicyFactory;
//import org.owasp.html.Sanitizers;

public class HTLMLInspector {

  
  public enum NodeChangeType {
    NONE, SELF, CHILD
  }

  public class HtmlChangeListenerContext {

    private List<ValidationMessage> messages;
    private String source;

    public HtmlChangeListenerContext(List<ValidationMessage> messages, String source) {
      this.messages = messages;
      this.source = source;
    }
  }

//  public class HtmlSanitizerObserver implements HtmlChangeListener<HtmlChangeListenerContext> {
//
//    @Override
//    public void discardedAttributes(HtmlChangeListenerContext ctxt, String elementName, String... attributeNames) {
//      ctxt.messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, ctxt.source, "the element "+elementName+" attributes failed security testing", IssueSeverity.ERROR));
//    }
//
//    @Override
//    public void discardedTag(HtmlChangeListenerContext ctxt, String elementName) {
//      ctxt.messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, ctxt.source, "the element "+elementName+" failed security testing", IssueSeverity.ERROR));
//    }
//  }

  public class StringPair {
    private String source;
    private String link;
    public StringPair(String source, String link) {
      super();
      this.source = source;
      this.link = link;
    }
  }

  public class LoadedFile {
    private String filename;
    private long lastModified;
    private XhtmlNode xhtml;
    private int iteration;
    private Set<String> targets = new HashSet<String>();

    public LoadedFile(String filename, long lastModified, XhtmlNode xhtml, int iteration) {
      this.filename = filename;
      this.lastModified = lastModified;
      this.xhtml = xhtml;
      this.iteration = iteration;
    }

    public long getLastModified() {
      return lastModified;
    }

    public int getIteration() {
      return iteration;
    }

    public void setIteration(int iteration) {
      this.iteration = iteration;
    }

    public XhtmlNode getXhtml() {
      return xhtml;
    }

    public Set<String> getTargets() {
      return targets;
    }

    public String getFilename() {
      return filename;
    }
    
  }

  private boolean strict;
  private String rootFolder;
  private List<SpecMapManager> specs;
  private Map<String, LoadedFile> cache = new HashMap<String, LoadedFile>();
  private int iteration = 0;
  private List<StringPair> otherlinks = new ArrayList<StringPair>();
  private int links;
  private List<String> manual = new ArrayList<String>(); // pages that will be provided manually when published, so allowed to be broken links
  private ILoggingService log;

  public HTLMLInspector(String rootFolder, List<SpecMapManager> specs, ILoggingService log) {
    this.rootFolder = rootFolder.replace("/", File.separator);
    this.specs = specs;
    this.log = log;
  }

  public List<ValidationMessage> check() throws IOException {
    iteration ++;

    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

    log.logDebugMessage(LogCategory.HTML, "CheckHTML: List files");
    // list new or updated files
    List<String> loadList = new ArrayList<>();
    listFiles(rootFolder, loadList);
    log.logMessage("found "+Integer.toString(loadList.size())+" files");

    checkGoneFiles();

    log.logDebugMessage(LogCategory.HTML, "Loading Files");
    // load files
    for (String s : loadList)
      loadFile(s, messages);


    log.logDebugMessage(LogCategory.HTML, "Checking Files");
    links = 0;
    // check links
    for (String s : cache.keySet()) {
      LoadedFile lf = cache.get(s);
      if (lf.getXhtml() != null)
        if (checkLinks(s, "", lf.getXhtml(), null, messages) != NodeChangeType.NONE) // returns true if changed
          saveFile(lf);
    }
 
    log.logDebugMessage(LogCategory.HTML, "Checking Other Links");
    // check other links:
    for (StringPair sp : otherlinks) {
      sp = sp;
      checkResolveLink(sp.source, null, null, sp.link, messages, null);
    }
    
    log.logDebugMessage(LogCategory.HTML, "Done checking");
    
    return messages;
  }


  private void saveFile(LoadedFile lf) throws IOException {
    new File(lf.getFilename()).delete();
    FileOutputStream f = new FileOutputStream(lf.getFilename());
    new XhtmlComposer().composeDocument(f, lf.getXhtml());
    f.close();
  }

  private void checkGoneFiles() {
    List<String> td = new ArrayList<String>();
    for (String s : cache.keySet()) {
      LoadedFile lf = cache.get(s);
      if (lf.getIteration() != iteration)
        td.add(s);
    }
    for (String s : td)
      cache.remove(s);
  }

  private void listFiles(String folder, List<String> loadList) {
    for (File f : new File(folder).listFiles()) {
      if (f.isDirectory()) {
        listFiles(f.getAbsolutePath() ,loadList);
      } else {
        LoadedFile lf = cache.get(f.getAbsolutePath());
        if (lf == null || lf.getLastModified() != f.lastModified())
          loadList.add(f.getAbsolutePath());
        else
          lf.setIteration(iteration);
      }
    }
  }

  private void loadFile(String s, List<ValidationMessage> messages) {
    File f = new File(s);
    XhtmlNode x = null;
    boolean htmlName = f.getName().endsWith(".html") || f.getName().endsWith(".xhtml");
    try {
      x = new XhtmlParser().setMustBeWellFormed(strict).parse(new FileInputStream(f), null);
      if (x.getElement("html")==null && !htmlName) {
        // We don't want resources being treated as HTML.  We'll check the HTML of the narrative in the page representation
        x = null;
      }
    } catch (FHIRFormatError | IOException e) {
      x = null;
      if (htmlName || !(e.getMessage().startsWith("Unable to Parse HTML - does not start with tag.") || e.getMessage().startsWith("Malformed XHTML")))
    	messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, s, e.getMessage(), IssueSeverity.ERROR));    	
    }
    LoadedFile lf = new LoadedFile(s, f.lastModified(), x, iteration);
    cache.put(s, lf);
    if (x != null) {
      checkHtmlStructure(s, x, messages);
      listTargets(x, lf.getTargets());
    }
    
    // ok, now check for XSS safety:
    // this is presently disabled; it's not clear whether oWasp is worth trying out for the purpose we are seeking (XSS safety)
    
//    
//    HtmlPolicyBuilder pp = new HtmlPolicyBuilder();
//    pp
//      .allowStandardUrlProtocols().allowAttributes("title").globally() 
//      .allowElements("html", "head", "meta", "title", "body", "span", "link", "nav", "button")
//      .allowAttributes("xmlns", "xml:lang", "lang", "charset", "name", "content", "id", "class", "href", "rel", "sizes", "no-external", "target", "data-target", "data-toggle", "type", "colspan").globally();
//    
//    PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS).and(Sanitizers.BLOCKS).and(Sanitizers.IMAGES).and(Sanitizers.STYLES).and(Sanitizers.TABLES).and(pp.toFactory());
//    
//    String source;
//    try {
//      source = TextFile.fileToString(s);
//      HtmlChangeListenerContext ctxt = new HtmlChangeListenerContext(messages, s);
//      String sanitized = policy.sanitize(source, new HtmlSanitizerObserver(), ctxt);
//    } catch (IOException e) {
//      messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, s, "failed security testing: "+e.getMessage(), IssueSeverity.ERROR));
//    } 
  }

  private void checkHtmlStructure(String s, XhtmlNode x, List<ValidationMessage> messages) {
    if (x.getNodeType() == NodeType.Document)
      x = x.getFirstElement();
    if (!"html".equals(x.getName()))
      messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, s, "Root node must be 'html', but is "+x.getName(), IssueSeverity.ERROR));
    // todo: check secure?
  }

  private void listTargets(XhtmlNode x, Set<String> targets) {
    if ("a".equals(x.getName()) && x.hasAttribute("name"))
      targets.add(x.getAttribute("name"));
    if (x.hasAttribute("id"))
      targets.add(x.getAttribute("id"));
    for (XhtmlNode c : x.getChildNodes())
      listTargets(c, targets);
  }

  private NodeChangeType checkLinks(String s, String path, XhtmlNode x, String uuid, List<ValidationMessage> messages) throws IOException {
    boolean changed = false;
    if (x.getName() != null)
      path = path + "/"+ x.getName();
    if ("title".equals(x.getName()) && Utilities.noString(x.allText()))
      x.addText("??");
    if ("a".equals(x.getName()) && x.hasAttribute("href"))
      changed = checkResolveLink(s, x.getLocation(), path, x.getAttribute("href"), messages, uuid);
    if ("img".equals(x.getName()) && x.hasAttribute("src"))
      changed = checkResolveImageLink(s, x.getLocation(), path, x.getAttribute("src"), messages, uuid) || changed;
    if ("link".equals(x.getName()))
      changed = checkLinkElement(s, x.getLocation(), path, x.getAttribute("href"), messages, uuid) || changed;
    if ("script".equals(x.getName()))
      checkScriptElement(s, x.getLocation(), path, x, messages);
    String nuid = UUID.randomUUID().toString().toLowerCase();
    boolean nchanged = false;
    boolean nSelfChanged = false;
    for (XhtmlNode c : x.getChildNodes()) { 
      NodeChangeType ct = checkLinks(s, path, c, nuid, messages);
      if (ct == NodeChangeType.SELF) {
        nSelfChanged = true;
        nchanged = true;
      } else if (ct == NodeChangeType.CHILD) {
        nchanged = true;
      }      
    }
    if (nSelfChanged) {
      XhtmlNode a = new XhtmlNode(NodeType.Element);
      a.setName("a").setAttribute("name", nuid).addText(" ");
      x.getChildNodes().add(0, a);
    } 
    if (changed)
      return NodeChangeType.SELF;
    else if (nchanged)
      return NodeChangeType.CHILD;
    else
      return NodeChangeType.NONE;
  }

  private void checkScriptElement(String filename, Location loc, String path, XhtmlNode x, List<ValidationMessage> messages) {
    String src = x.getAttribute("src");
    if (!Utilities.noString(src) && Utilities.isAbsoluteUrl(src))
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(loc == null ? "" : " at "+loc.toString()), "The <script> src '"+src+"' is llegal", IssueSeverity.FATAL));    
  }

  private boolean checkLinkElement(String filename, Location loc, String path, String href, List<ValidationMessage> messages, String uuid) {
    if (Utilities.isAbsoluteUrl(href) && !href.startsWith("http://hl7.org/")) {
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(loc == null ? "" : " at "+loc.toString()), "The <link> href '"+href+"' is llegal", IssueSeverity.FATAL).setLocationLink(uuid == null ? null : filename+"#"+uuid));
      return true;        
    } else
      return false;
  }

  private boolean checkResolveLink(String filename, Location loc, String path, String ref, List<ValidationMessage> messages, String uuid) throws IOException {
    links++;
    String rref = ref;
    if ((rref.startsWith("http:") || rref.startsWith("https:") ) && (rref.endsWith(".sch") || rref.endsWith(".xsd") || rref.endsWith(".shex"))) { // work around for the fact that spec.internals does not track all these minor things 
      rref = Utilities.changeFileExt(ref, ".html");
    }
    String tgtList = "";
    boolean resolved = Utilities.existsInList(ref, "qa.html", "http://hl7.org/fhir", "http://hl7.org", "http://www.hl7.org", "http://hl7.org/fhir/search.cfm") || ref.startsWith("http://gforge.hl7.org/gf/project/fhir/tracker/") || ref.startsWith("mailto:") || ref.startsWith("javascript:");
    if (!resolved)
      resolved = manual.contains(rref);
    if (!resolved && specs != null){
      for (SpecMapManager spec : specs) {
        resolved = resolved || spec.getBase().equals(rref) || (spec.getBase()).equals(rref+"/") || spec.hasTarget(rref); 
      }
    }
      
    if (!resolved) {
      if (rref.startsWith("http://") || rref.startsWith("https://")) {
        resolved = true;
        if (specs != null) {
          for (SpecMapManager spec : specs) {
            if (rref.startsWith(spec.getBase()))
              resolved = false;
          }
        }
      } else { 
        String page = rref;
        String name = null;
        if (page.startsWith("#")) {
          name = page.substring(1);
          page = filename;
        } else if (page.contains("#")) {
          name = page.substring(page.indexOf("#")+1);
          page = Utilities.path(rootFolder, page.substring(0, page.indexOf("#")).replace("/", File.separator));
        } else {
          String folder = Utilities.getDirectoryForFile(filename);
          page = Utilities.path(folder == null ? rootFolder : folder, page.replace("/", File.separator));
        }
        LoadedFile f = cache.get(page);
        if (f != null) {
          if (Utilities.noString(name))
            resolved = true;
          else { 
            resolved = f.targets.contains(name);
            tgtList = " (valid targets: "+f.targets.toString()+")";
          }
        }
      }
    }
      
    if (resolved) {
      return false;
    } else {
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(path == null ? "" : "#"+path+(loc == null ? "" : " at "+loc.toString())), "The link '"+ref+"' cannot be resolved"+tgtList, IssueSeverity.ERROR).setLocationLink(uuid == null ? null : filename+"#"+uuid));
      return true;
    } 
  }

  private boolean checkResolveImageLink(String filename, Location loc, String path, String ref, List<ValidationMessage> messages, String uuid) throws IOException {
    links++;
    String tgtList = "";
    boolean resolved = Utilities.existsInList(ref);
    if (!resolved)
      resolved = manual.contains(ref);
    if (!resolved && specs != null){
      for (SpecMapManager spec : specs) {
        resolved = resolved || spec.hasImage(ref); 
      }
    }
    if (!resolved) {
      if (ref.startsWith("http://") || ref.startsWith("https://")) {
        resolved = true;
        if (specs != null) {
          for (SpecMapManager spec : specs) {
            if (ref.startsWith(spec.getBase()))
              resolved = false;
          }
        }
      } else if (!ref.contains("#")) { 
        String page = Utilities.path(Utilities.getDirectoryForFile(filename), ref.replace("/", File.separator));
        LoadedFile f = cache.get(page);
        resolved = f != null;
      }
    }
      
    if (resolved)
      return false;
    else {
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(path == null ? "" : "#"+path+(loc == null ? "" : " at "+loc.toString())), "The image source '"+ref+"' cannot be resolved"+tgtList, IssueSeverity.ERROR).setLocationLink(uuid == null ? null : filename+"#"+uuid));
      return true;
    } 
  }

  public void addLinkToCheck(String source, String link) {
    otherlinks.add(new StringPair(source, link));
    
  }

  public int total() {
    return cache.size();
  }

  public int links() {
    return links;
  }

  public static void main(String[] args) throws Exception {
    HTLMLInspector inspector = new HTLMLInspector(args[0], null, null);
    inspector.setStrict(false);
    List<ValidationMessage> linkmsgs = inspector.check();
    int bl = 0;
    int lf = 0;
    for (ValidationMessage m : linkmsgs) {
      if ((m.getLevel() == IssueSeverity.ERROR) || (m.getLevel() == IssueSeverity.FATAL)) {
        if (m.getType() == IssueType.NOTFOUND)
          bl++;
        else
          lf++;
      } 
    }
    System.out.println("  ... "+Integer.toString(inspector.total())+" html "+checkPlural("file", inspector.total())+", "+Integer.toString(lf)+" "+checkPlural("page", lf)+" invalid xhtml ("+(inspector.total() == 0 ? "" : Integer.toString((lf*100)/inspector.total())+"%)"));
    System.out.println("  ... "+Integer.toString(inspector.links())+" "+checkPlural("link", inspector.links())+", "+Integer.toString(bl)+" broken "+checkPlural("link", lf)+" ("+(inspector.links() == 0 ? "" : Integer.toString((bl*100)/inspector.links())+"%)"));
    
    System.out.println("");
    
    for (ValidationMessage m : linkmsgs) 
      if ((m.getLevel() == IssueSeverity.ERROR) || (m.getLevel() == IssueSeverity.FATAL)) 
        System.out.println(m.summary());
  }

  private static String checkPlural(String word, int c) {
    return c == 1 ? word : Utilities.pluralizeMe(word);
  }

  public List<String> getManual() {
    return manual;
  }

  public void setManual(List<String> manual) {
    this.manual = manual;
  }

  public boolean isStrict() {
    return strict;
  }

  public void setStrict(boolean strict) {
    this.strict = strict;
  }

}
