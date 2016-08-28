package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
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
    private long lastModified;
    private XhtmlNode xhtml;
    private int iteration;
    private Set<String> targets = new HashSet<String>();

    public LoadedFile(long lastModified, XhtmlNode xhtml, int iteration) {
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
  }

  private boolean strict;
  private String rootFolder;
  private List<SpecMapManager> specs;
  private Map<String, LoadedFile> cache = new HashMap<String, LoadedFile>();
  private int iteration = 0;
  private List<StringPair> otherlinks = new ArrayList<StringPair>();
  private int links;
  private List<String> manual = new ArrayList<String>(); // pages that will be provided manually when published, so allowed to be broken links

  public HTLMLInspector(String rootFolder, List<SpecMapManager> specs) {
    this.rootFolder = rootFolder.replace("/", File.separator);
    this.specs = specs;
  }

  public List<ValidationMessage> check() throws IOException {
    iteration ++;

    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

    // list new or updated files
    List<String> loadList = new ArrayList<>();
    listFiles(rootFolder, loadList);

    checkGoneFiles();

    // load files
    for (String s : loadList)
      loadFile(s, messages);


    links = 0;
    // check links
    for (String s : cache.keySet()) {
      LoadedFile lf = cache.get(s);
      if (lf.getXhtml() != null)
        checkLinks(s, "", lf.getXhtml(), messages);
    }

 
    // check other links:
    for (StringPair sp : otherlinks) {
      sp = sp;
      checkResolveLink(sp.source, null, null, sp.link, messages);
    }
    
    return messages;
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
    LoadedFile lf = new LoadedFile(f.lastModified(), x, iteration);
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

  private void checkLinks(String s, String path, XhtmlNode x, List<ValidationMessage> messages) throws IOException {
    if (x.getName() != null)
      path = path + "/"+ x.getName();
    if ("a".equals(x.getName()) && x.hasAttribute("href"))
      checkResolveLink(s, x.getLocation(), path, x.getAttribute("href"), messages);
    if ("img".equals(x.getName()) && x.hasAttribute("src"))
      checkResolveImageLink(s, x.getLocation(), path, x.getAttribute("src"), messages);
    if ("link".equals(x.getName()))
      checkLinkElement(s, x.getLocation(), path, x.getAttribute("href"), messages);
    if ("script".equals(x.getName()))
      checkScriptElement(s, x.getLocation(), path, x, messages);
    for (XhtmlNode c : x.getChildNodes())
      checkLinks(s, path, c, messages);
  }

  private void checkScriptElement(String filename, Location loc, String path, XhtmlNode x, List<ValidationMessage> messages) {
    String src = x.getAttribute("src");
    if (!Utilities.noString(src) && Utilities.isAbsoluteUrl(src))
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(loc == null ? "" : " at "+loc.toString()), "The <script> src '"+src+"' is llegal", IssueSeverity.FATAL));    
  }

  private void checkLinkElement(String filename, Location loc, String path, String href, List<ValidationMessage> messages) {
    if (Utilities.isAbsoluteUrl(href) && !href.startsWith("http://hl7.org/"))
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(loc == null ? "" : " at "+loc.toString()), "The <link> href '"+href+"' is llegal", IssueSeverity.FATAL));    
  }

  private void checkResolveLink(String filename, Location loc, String path, String ref, List<ValidationMessage> messages) throws IOException {
    links++;
    String tgtList = "";
    boolean resolved = Utilities.existsInList(ref, "qa.html", "http://hl7.org/fhir", "http://hl7.org", "http://www.hl7.org", "http://hl7.org/fhir/search.cfm") || ref.startsWith("http://gforge.hl7.org/gf/project/fhir/tracker/");
    if (!resolved)
      resolved = manual.contains(ref);
    if (!resolved && specs != null){
      for (SpecMapManager spec : specs) {
        resolved = resolved || spec.getBase().equals(ref) || (spec.getBase()).equals(ref+"/") || spec.hasTarget(ref); 
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
      } else { 
        String page = ref;
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
      
    if (!resolved)
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(path == null ? "" : "#"+path+(loc == null ? "" : " at "+loc.toString())), "The link '"+ref+"' cannot be resolved"+tgtList, IssueSeverity.ERROR));
  }

  private void checkResolveImageLink(String filename, Location loc, String path, String ref, List<ValidationMessage> messages) throws IOException {
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
      
    if (!resolved)
      messages.add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, filename+(path == null ? "" : "#"+path+(loc == null ? "" : " at "+loc.toString())), "The image source '"+ref+"' cannot be resolved"+tgtList, IssueSeverity.ERROR));
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
    HTLMLInspector inspector = new HTLMLInspector(args[0], null);
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
