package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService.LogCategory;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.hl7.fhir.utilities.cache.PackageCacheManager.VersionHistory;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlNode.Location;
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
    private String text;
    public StringPair(String source, String link, String text) {
      super();
      this.source = source;
      this.link = link;
      this.text = text;
    }
  }

  public class LoadedFile {
    private String filename;
    private long lastModified;
    private XhtmlNode xhtml;
    private int iteration;
    private Set<String> targets = new HashSet<String>();
    private Boolean hl7State;
    private String path;

    public LoadedFile(String filename, String path, long lastModified, XhtmlNode xhtml, int iteration, Boolean hl7State) {
      this.filename = filename;
      this.lastModified = lastModified;
      this.xhtml = xhtml;
      this.iteration = iteration;
      this.hl7State = hl7State;
      this.path = path;
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

    public Boolean getHl7State() {
      return hl7State;
    }
    
  }

  private static final String RELEASE_HTML_MARKER = "<!--ReleaseHeader--><p id=\"publish-box\">Publish Box goes here</p><!--EndReleaseHeader-->";
  private static final String START_HTML_MARKER = "<!--ReleaseHeader--><p id=\"publish-box\">";
  private static final String END_HTML_MARKER = "</p><!--EndReleaseHeader-->";

  private boolean strict;
  private String rootFolder;
  private String altRootFolder;
  private List<SpecMapManager> specs;
  private Map<String, LoadedFile> cache = new HashMap<String, LoadedFile>();
  private int iteration = 0;
  private List<StringPair> otherlinks = new ArrayList<StringPair>();
  private int links;
  private List<String> manual = new ArrayList<String>(); // pages that will be provided manually when published, so allowed to be broken links
  private ILoggingService log;
  private boolean forHL7;
  private List<String> igs;
  private PackageCacheManager pcm;
  private Map<String, SpecMapManager> otherSpecs = new HashMap<String, SpecMapManager>();
  private List<String> errorPackages = new ArrayList<>();
  private String canonical;
  private boolean hl7Checks;

  private String statusText;
  private List<String> exemptHtmlPatterns = new ArrayList<>();

  public HTLMLInspector(String rootFolder, List<SpecMapManager> specs, ILoggingService log, String canonical, boolean hl7Checks) {
    this.rootFolder = rootFolder.replace("/", File.separator);
    this.specs = specs;
    this.log = log;
    this.canonical = canonical;
    this.forHL7 = canonical.contains("hl7.org/fhir");
    this.hl7Checks = hl7Checks;
  }

  public void setAltRootFolder(String altRootFolder) throws IOException {
    this.altRootFolder = Utilities.path(rootFolder, altRootFolder.replace("/", File.separator));
  }
  
  public List<ValidationMessage> check(String statusText) throws IOException {  
    this.statusText = statusText;
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
      loadFile(s, rootFolder, messages);


    log.logDebugMessage(LogCategory.HTML, "Checking Files");
    links = 0;
    // check links
    boolean first = true;
    for (String s : sorted(cache.keySet())) {
      LoadedFile lf = cache.get(s);
      if (lf.getHl7State() != null && !lf.getHl7State()) {
        boolean check = true;
        for (String pattern : exemptHtmlPatterns ) {
          if (lf.path.matches(pattern)) {
            check = false;
            break;
          }
        }
        if (check) {
          messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, s, "The html source does not contain the header marker" 
            + (first ? " "+RELEASE_HTML_MARKER+" (see note at http://wiki.hl7.org/index.php?title=FHIR_Implementation_Guide_Publishing_Requirements#HL7_HTML_Standards_considerations)" : ""), IssueSeverity.ERROR));
        }
        first = false;
      }
      if (lf.getXhtml() != null)
        if (checkLinks(s, "", lf.getXhtml(), null, messages, false) != NodeChangeType.NONE) // returns true if changed
          saveFile(lf);
    }
 
    log.logDebugMessage(LogCategory.HTML, "Checking Other Links");
    // check other links:
    for (StringPair sp : otherlinks) {
      checkResolveLink(sp.source, null, null, sp.link, sp.text, messages, null);
    }
    
    log.logDebugMessage(LogCategory.HTML, "Done checking");
    
    return messages;
  }


  private List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }

  private void saveFile(LoadedFile lf) throws IOException {
    new File(lf.getFilename()).delete();
    FileOutputStream f = new FileOutputStream(lf.getFilename());
    new XhtmlComposer(XhtmlComposer.HTML).composeDocument(f, lf.getXhtml());
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

  private void loadFile(String s, String base, List<ValidationMessage> messages) {
    File f = new File(s);
    Boolean hl7State = null;
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
    if (hl7Checks && x != null) {
      String src;
      try {
        src = TextFile.fileToString(f);
        hl7State = src.contains(RELEASE_HTML_MARKER);
        if (hl7State) {
          src = src.replace(RELEASE_HTML_MARKER, START_HTML_MARKER + statusText+END_HTML_MARKER);
          TextFile.stringToFile(src, f, false);
        }
          
      } catch (Exception e1) {
        hl7State = false;
      }
    }
    LoadedFile lf = new LoadedFile(s, getPath(s, base), f.lastModified(), x, iteration, hl7State);
    cache.put(s, lf);
    if (x != null) {
      checkHtmlStructure(s, x, messages);
      listTargets(x, lf.getTargets());
      if (forHL7 & !isRedirect(x)) {
        checkTemplatePoints(x, messages, s);
      }
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

  private String getPath(String s, String base) {
    String t = s.substring(base.length()+1);
    return t.replace("\\", "/");
  }

  private boolean isRedirect(XhtmlNode x) {
    return !hasHTTPRedirect(x);
  }

  private boolean hasHTTPRedirect(XhtmlNode x) {
    if ("meta".equals(x.getName()) && x.hasAttribute("http-equiv"))
      return true;
    for (XhtmlNode c : x.getChildNodes())
      if (hasHTTPRedirect(c))
        return true;
    return false;
  }

  private void checkTemplatePoints(XhtmlNode x, List<ValidationMessage> messages, String s) {
    // look for a footer: a div tag with igtool=footer on it 
    XhtmlNode footer = findFooterDiv(x);
    if (footer == null) 
      messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, s, "The html must include a div with an attribute igtool=\"footer\" that marks the footer in the template", IssueSeverity.ERROR));
    else {
      // look in the footer for: .. nothing yet... 
    }
  }

  private XhtmlNode findFooterDiv(XhtmlNode x) {
    if (x.getNodeType() == NodeType.Element && "footer".equals(x.getAttribute("igtool")))
      return x;
    for (XhtmlNode c : x.getChildNodes()) {
      XhtmlNode n = findFooterDiv(c);
      if (n != null)
        return n;
    }
    return null;
  }

  private boolean findStatusBarComment(XhtmlNode x) {
    if (x.getNodeType() == NodeType.Comment && "status-bar".equals(x.getContent().trim()))
      return true;
    for (XhtmlNode c : x.getChildNodes()) {
      if (findStatusBarComment(c))
        return true;
    }
    return false;
  }

  private void checkHtmlStructure(String s, XhtmlNode x, List<ValidationMessage> messages) {
    if (x.getNodeType() == NodeType.Document)
      x = x.getFirstElement();
    if (!"html".equals(x.getName()) && !"div".equals(x.getName()))
      messages.add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, s, "Root node must be 'html' or 'div', but is "+x.getName(), IssueSeverity.ERROR));
    // We support div as well because with HTML 5, referenced files might just start with <div>
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

  private NodeChangeType checkLinks(String s, String path, XhtmlNode x, String uuid, List<ValidationMessage> messages, boolean inPre) throws IOException {
    boolean changed = false;
    if (x.getName() != null)
      path = path + "/"+ x.getName();
    if ("title".equals(x.getName()) && Utilities.noString(x.allText()))
      x.addText("??");
    if ("a".equals(x.getName()) && x.hasAttribute("href"))
      changed = checkResolveLink(s, x.getLocation(), path, x.getAttribute("href"), x.allText(), messages, uuid);
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
      NodeChangeType ct = checkLinks(s, path, c, nuid, messages, inPre || "pre".equals(x.getName()));
      if (ct == NodeChangeType.SELF) {
        nSelfChanged = true;
        nchanged = true;
      } else if (ct == NodeChangeType.CHILD) {
        nchanged = true;
      }      
    }
    if (nSelfChanged) {
      XhtmlNode a = new XhtmlNode(NodeType.Element);
      a.setName("a").setAttribute("name", nuid).addText("\u200B");
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
    if (Utilities.isAbsoluteUrl(href) && !href.startsWith("http://hl7.org/") && !href.startsWith("http://cql.hl7.org/")) {
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(loc == null ? "" : " at "+loc.toString()), "The <link> href '"+href+"' is llegal", IssueSeverity.FATAL).setLocationLink(uuid == null ? null : filename+"#"+uuid));
      return true;        
    } else
      return false;
  }

  private boolean checkResolveLink(String filename, Location loc, String path, String ref, String text, List<ValidationMessage> messages, String uuid) throws IOException {
    links++;
    String rref = ref;
    if ((rref.startsWith("http:") || rref.startsWith("https:") ) && (rref.endsWith(".sch") || rref.endsWith(".xsd") || rref.endsWith(".shex"))) { // work around for the fact that spec.internals does not track all these minor things 
      rref = Utilities.changeFileExt(ref, ".html");
    }
    String tgtList = "";
    boolean resolved = Utilities.existsInList(ref, "qa.html", "http://hl7.org/fhir", "http://hl7.org", "http://www.hl7.org", "http://hl7.org/fhir/search.cfm") || ref.startsWith("http://gforge.hl7.org/gf/project/fhir/tracker/") || ref.startsWith("mailto:") || ref.startsWith("javascript:");
    if (!resolved && forHL7)
      resolved = Utilities.pathURL(canonical, "history.html").equals(ref) || Utilities.pathURL(canonical, "history.cfml").equals(ref); 
    if (!resolved)
      resolved = manual.contains(rref);
    if (!resolved && specs != null){
      for (SpecMapManager spec : specs) {
        resolved = resolved || spec.getBase().equals(rref) || (spec.getBase()).equals(rref+"/") || spec.hasTarget(rref); 
      }
    }
    
    if (!resolved) {
      String fref = Utilities.path(Utilities.getDirectoryForFile(filename), ref);
      if (fref.equals(Utilities.path(rootFolder, "qa.html")))
        resolved = true;
    }
    // special case end-points that are always valid:
     if (!resolved)
      resolved = Utilities.existsInList(ref, "http://hl7.org/fhir/fhir-spec-r4.zip", "http://hl7.org/fhir/R4/fhir-spec-r4.zip", "http://hl7.org/fhir/STU3/fhir-spec.zip", "http://hl7.org/fhir/DSTU2/fhir-spec.zip") || 
          matchesTarget(ref, "http://hl7.org", "http://hl7.org/fhir/DSTU2", "http://hl7.org/fhir/STU3", "http://hl7.org/fhir/R4", "http://hl7.org/fhir/smart-app-launch", "http://hl7.org/fhir/validator");
    
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
          if (altRootFolder != null && filename.startsWith(altRootFolder))
            page = Utilities.path(altRootFolder, page.substring(0, page.indexOf("#")).replace("/", File.separator));
          else
            page = Utilities.path(rootFolder, page.substring(0, page.indexOf("#")).replace("/", File.separator));
        } else {
          String folder = Utilities.getDirectoryForFile(filename);
          page = Utilities.path(folder == null ? (altRootFolder != null && filename.startsWith(altRootFolder) ? altRootFolder : rootFolder) : folder, page.replace("/", File.separator));
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
    if (!resolved)
      for (String url : pcm.getUrls()) {
        if (!"http://hl7.org/fhir".equals(url)) {
          if (matchesTarget(ref, url))
            resolved = true;
          if (ref.startsWith(url) && !errorPackages.contains(url)) {
            // now, iterate the package list to see if we have a version match
            VersionHistory vers = null; 
            try {
              vers = pcm.listVersions(url);
            } catch (Exception e) {
              errorPackages.add(url);
              System.out.println("Error checking links for "+url+": "+e.getMessage());
            }
            if (vers != null) {
              for (String ver : vers.getVersions().keySet()) {
                String v = vers.getId()+"#"+ver;
                if (!errorPackages.contains(v)) {
                  try {
                    if (ref.startsWith(vers.getVersions().get(ver))) {
                      SpecMapManager sm = otherSpecs.get(v);
                      if (sm == null) {
                        sm = loadSpecMap(vers.getId(), ver, vers.getVersions().get(ver));
                        otherSpecs.put(v, sm);
                      }
                      if (sm.getBase().equals(rref) || (sm.getBase()).equals(rref+"/") || sm.hasTarget(rref)) {
                        resolved = true;
                        break;
                      }
                    }
                  } catch (Exception e) {
                    errorPackages.add(v);
                    System.out.println("Error checking links for "+v+": "+e.getMessage());
                  }
                }
              }
            }
          }
        }
      }

    if (resolved) {
      return false;
    } else {
      if (text == null)
        text = "";
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(path == null ? "" : "#"+path+(loc == null ? "" : " at "+loc.toString())), "The link '"+ref+"' for \""+text.replaceAll("[\\s\\n]+", " ").trim()+"\" cannot be resolved"+tgtList, IssueSeverity.ERROR).setLocationLink(uuid == null ? null : makeLocal(filename)+"#"+uuid));
      return true;
    } 
  }

  private boolean matchesTarget(String ref, String... url) {
    for (String s : url) {
      if (ref.equals(s))
        return true;
      if (ref.equals(s+"/"))
        return true;
      if (ref.equals(s+"/index.html"))
        return true;
    }
    return false;
  }

  private SpecMapManager loadSpecMap(String id, String ver, String url) throws IOException {
    NpmPackage pi = pcm.loadPackageFromCacheOnly(id, ver);
    if (pi == null) {
      System.out.println("Fetch "+id+" package from "+url);
      URL url1 = new URL(Utilities.pathURL(url, "package.tgz")+"?nocache=" + System.currentTimeMillis());
      URLConnection c = url1.openConnection();
      InputStream src = c.getInputStream();
      pi = pcm.addPackageToCache(id, ver, src);
    }    
    SpecMapManager sm = new SpecMapManager(TextFile.streamToBytes(pi.load("other", "spec.internals")), pi.getNpm().getAsJsonObject("dependencies").get("hl7.fhir.core").getAsString());
    sm.setBase(url);
    return sm;
  }

  private String makeLocal(String filename) {
    if (filename.startsWith(rootFolder))
      return filename.substring(rootFolder.length()+1);
    return filename;
  }

  private boolean checkResolveImageLink(String filename, Location loc, String path, String ref, List<ValidationMessage> messages, String uuid) throws IOException {
    links++;
    String tgtList = "";
    boolean resolved = Utilities.existsInList(ref);
    if (ref.startsWith("data:"))
      resolved = true;
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

  public void addLinkToCheck(String source, String link, String text) {
    otherlinks.add(new StringPair(source, link, text));
    
  }

  public int total() {
    return cache.size();
  }

  public int links() {
    return links;
  }

  public static void main(String[] args) throws Exception {
    HTLMLInspector inspector = new HTLMLInspector(args[0], null, null, "http://hl7.org/fhir/us/core", true);
    inspector.setStrict(false);
    List<ValidationMessage> linkmsgs = inspector.check("test text");
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

  public  List<SpecMapManager> getSpecMaps() {
    return specs;
  }

  public List<String> getIgs() {
    return igs;
  }

  public void setPcm(PackageCacheManager pcm) {
    this.pcm = pcm; 
  }

  public List<String> getExemptHtmlPatterns() {
    return exemptHtmlPatterns;
  }

  
}
