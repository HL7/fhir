package org.hl7.fhir.definitions.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.parsers.IgParser;
import org.hl7.fhir.definitions.parsers.IgParser.GuidePageKind;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.ImplementationGuide;
import org.hl7.fhir.r4.model.ImplementationGuide.ImplementationGuideDefinitionPageComponent;
import org.hl7.fhir.r4.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.TableModel;

public class ImplementationGuideDefn {



  private class LinkTriple {
    private String url;
    private String display;
    private String title;
    public LinkTriple(String url, String display, String title) {
      super();
      this.url = url;
      this.display = display;
      this.title = title;
    }
    public String getUrl() {
      return url;
    }
    public String getDisplay() {
      return display;
    }
    public String getTitle() {
      return title;
    }
    
  }

  private String committee;
  private String code;
  private boolean core; 
  private String name;
  private String brief;
  private boolean review;
  private String source;
  private String ballot;
  private String fmm;
  private String sectionId;
  private List<String> pageList = new ArrayList<String>();
  private List<String> imageList = new ArrayList<String>();
  private List<Example> examples = new ArrayList<Example>();
  private List<ValueSet> valueSets = new ArrayList<ValueSet>();
  private List<Profile> profiles = new ArrayList<Profile>();
  private List<Dictionary> dictionaries = new ArrayList<Dictionary>();
  private Map<String, String> tlas = new HashMap<String, String>();
  private Map<String, StructureDefinition> extensions = new HashMap<String, StructureDefinition>();
  private List<BindingSpecification> unresolvedBindings = new ArrayList<BindingSpecification>();
  private List<LogicalModel> logicalModels = new ArrayList<LogicalModel>();
  private ImplementationGuide ig;
  private List<ValidationMessage> issues;
  
  public ImplementationGuideDefn(String committee, String code, String name, String brief, String source, boolean review, String ballot, String fmm, String sectionId, boolean core, List<ValidationMessage> issues) {
    super();
    this.code = code;
    this.name = name;
    this.source = source;
    this.brief = brief;
    this.review = review;
    this.committee = committee;
    this.fmm = fmm;
    this.ballot = ballot;
    this.core = core;
    this.issues = issues;
    this.sectionId = sectionId;
  }
  
  public String getCode() {
    return code;
  }
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public boolean isReview() {
    return review;
  }
  public void setReview(boolean review) {
    this.review = review;
  }

  public String getSource() {
    return source;
  }

  public List<String> getPageList() {
    return pageList;
  }
  
  public List<String> getImageList() {
    return imageList;
  }

  public List<Example> getExamples() {
    return examples;
  }

  public List<ValueSet> getValueSets() {
    return valueSets;
  }

  public Map<String, String> getTlas() {
    return tlas;
  }

  public List<Profile> getProfiles() {
    return profiles;
  }

  public List<Dictionary> getDictionaries() {
    return dictionaries;
  }

  public Map<String, StructureDefinition> getExtensions() {
    return extensions ;
  }

  public ValueSet getValueSet(String url) {
    for (ValueSet vs : valueSets)
      if (vs.getUrl().equals(url))
        return vs;
    return null;
  }

  public List<BindingSpecification> getUnresolvedBindings() {
    return unresolvedBindings ;
  }

  public List<LogicalModel> getLogicalModels() {
    return logicalModels ;
  }

  public LogicalModel getLogicalModel(String id) {
    for (LogicalModel lm : logicalModels) 
      if (lm.getId().equals(id))
        return lm;
    return null;
  }

  public String getCommittee() {
    return committee;
  }

  public String getBallot() {
    return ballot;
  }

  public void setBallot(String ballot) {
    this.ballot = ballot;
  }

  public String getFmm() {
    return fmm;
  }

  public void setFmm(String fmm) {
    this.fmm = fmm;
  }

  public boolean isCore() {
    return core;
  }

  
  public ImplementationGuide getIg() {
    return ig;
  }

  public void setIg(ImplementationGuide ig) {
    this.ig = ig;
  }

  public String makeList(String pagename, String type, String genlevel, String crumbTitle) throws Exception {
    String n = pagename.replace("/", "\\");
    if (n.startsWith(code+File.separator))
      n = n.substring(code.length()+1);
    if (!n.endsWith(".html")) // todo: do we need this? 
      n = n + ".html";
    
    List<LinkTriple> path = determinePath(n, type, crumbTitle);
    
    StringBuilder b = new StringBuilder();
    b.append("        <!-- "+pagename+" / "+type+" / "+crumbTitle+" -->\r\n");
    int i = 0;
    for (LinkTriple lt : path) {
      i++;
      String t = lt.getTitle() == null ? "" : " title=\""+lt.getTitle()+"\"";
      if (i == path.size() || lt.getUrl() == null)
        b.append("        <li"+t+"><b>"+lt.getDisplay()+"</b></li>\r\n");
      else
        b.append("        <li"+t+"><a href=\""+lt.getUrl()+"\"><b>"+lt.getDisplay()+"</b></a></li>\r\n");
    }
    return b.toString();
  }

  private List<LinkTriple> determinePath(String n, String type, String crumbTitle) throws Exception {
    List<LinkTriple> res = new ArrayList<ImplementationGuideDefn.LinkTriple>();
    res.add(new LinkTriple(ig.getDefinition().getPage().getNameUrlType().getValue(), ig.getId().toUpperCase(), ig.getName()));

    if (type.equals("valueSet") && hasVSRegistry()) {
      findPage(getVSRegistry().getNameUrlType().getValue(), res, ig.getDefinition().getPage().getPage());
      res.add(new LinkTriple(null, crumbTitle, null));
    } else if (type.startsWith("extension:")) {
      if (findRegistryPage("StructureDefinition", res, ig.getDefinition().getPage().getPage())) 
        res.add(new LinkTriple(null, "Extension", null));
      else
        res.add(new LinkTriple(null, "Extension Stuff", "Work in Progress yet"));
    } else if (type.startsWith("search-parameter:")) {
      String[] p = type.split("\\/");
      if (p.length >= 2 && findPage(p[1]+".html", res, ig.getDefinition().getPage().getPage())) {
        res.add(new LinkTriple(null, "Search Parameter", null));
      } else
        res.add(new LinkTriple(null, "Search Parameter Stuff", "Work in Progress yet"));        
    } else if (n.equals("terminologies-valuesets.html")) {
      res.add(new LinkTriple(null, "Value Sets", "Value set List"));
    } else {
      if (n.endsWith(".xml.html"))
        n = n.substring(0, n.length()-9)+".html";
      else if (n.endsWith(".json.html"))
        n = n.substring(0, n.length()-10)+".html";
      
      if (!n.equals(ig.getDefinition().getPage().getNameUrlType().getValue())) {
        if (!findPage(n, res, ig.getDefinition().getPage().getPage()) && !findLogicalPage(n, type, res, ig.getDefinition().getPage().getPage())) {
          // we didn't find it as a simple page. Figure out what 
         
          issues.add(new ValidationMessage(Source.Publisher, IssueType.PROCESSING, code+"/"+n, "The page "+n+" is not assigned a bread crumb yet", IssueSeverity.WARNING));
          res.add(new LinkTriple(null, "unsorted", "Work in Progress yet"));
        }
      }
    }
    return res;
  }

  private boolean findLogicalPage(String n, String type, List<LinkTriple> res, List<ImplementationGuideDefinitionPageComponent> page) throws Exception {
    // see if we can find it as an example of an existing profile
    String src = Utilities.fileTitle(n)+ ".html";
    for (ImplementationGuideDefinitionResourceComponent r : ig.getDefinition().getResource()) {
      if (src.equals(r.getReference().getReference())) {
        if (r.hasExampleCanonicalType()) {
          String psrc = r.getExampleCanonicalType().getValueAsString().substring(r.getExampleCanonicalType().getValueAsString().lastIndexOf("/")+1)+".html";
          if (findPage(psrc, res, page)) {
            res.add(new LinkTriple(null, r.getName(), null));
            return true;
          }
        }
      }
    }
    // now, see if we can find a registry to make it a child of 
    String id = Utilities.fileTitle(n);
    for (Example e : examples) {
      if (e.getId().equals(id))
        if (findRegistryPage(e.getResourceName(), res, page)) {
          res.add(new LinkTriple(null, e.getName(), null));
          return true;
        }
    }
    if (type.startsWith("profile:"))
      if (findRegistryPage("StructureDefinition", res, page)) {
        res.add(new LinkTriple(null, "Profile", null));
        return true;
      }
      
    if (findModified(n, "-operations.html", "Operations", res, page))
      return true;
    if (findModified(n, "-questionnaire.html", "Questionnaire", res, page))
      return true;
    if (findModified(n, ".profile.html", "StructureDefinition", res, page))
      return true;
    return false;
  }

  private boolean findModified(String n, String suffix, String title, List<LinkTriple> res, List<ImplementationGuideDefinitionPageComponent> page) throws FHIRException {
    // now, see if we can drop -operations...
    if (n.endsWith(suffix)) {
      if (findPage(n.substring(0, n.length()-suffix.length())+".html", res, page)) {
        res.add(new LinkTriple(null, title, null));
        return true;
      }
    }
    return false;

    
  }

  private boolean findRegistryPage(String n, List<LinkTriple> res, List<ImplementationGuideDefinitionPageComponent> list) throws FHIRException {
    for (ImplementationGuideDefinitionPageComponent page : list) {
      if (IgParser.getKind(page) == GuidePageKind.LIST && isListFor(page, n)) {
        res.add(new LinkTriple(page.getNameUrlType().getValue(), page.getTitle(), null));
        return true;
      }
      if (page.hasPage()) {
        res.add(new LinkTriple(page.getNameUrlType().getValue(), page.getTitle(), null));
        if (findPage(n, res, page.getPage()))
          return true;
        else {
          res.remove(res.size()-1);
        }
      }
    }
    return false;
  }


  private boolean isListFor(ImplementationGuideDefinitionPageComponent page, String n) {
    for (CodeType s : IgParser.getType(page)) {
      if (s.getValue().equals(n))
        return true;
    }
    return false;
  }

  private boolean findPage(String n, List<LinkTriple> res, List<ImplementationGuideDefinitionPageComponent> list) throws FHIRException {
    for (ImplementationGuideDefinitionPageComponent page : list) {
      if (n.equals(page.getNameUrlType().getValue())) {
        res.add(new LinkTriple(page.getNameUrlType().getValue(), page.getTitle(), null));
        return true;
      }
      if (page.hasPage()) {
        res.add(new LinkTriple(page.getNameUrlType().getValue(), page.getTitle(), null));
        if (findPage(n, res, page.getPage()))
          return true;
        else {
          res.remove(res.size()-1);
        }
      }
    }
    return false;
  }

  public String getPath() {
    return isCore() ? "" : code+File.separator;
  }

  public boolean hasVSRegistry() {
    return getRegistryPage("ValueSet") != null;
  }
  public ImplementationGuideDefinitionPageComponent getVSRegistry() {
    return getRegistryPage("ValueSet");
  }
  public ImplementationGuideDefinitionPageComponent getRegistryPage(String type) {
    return getRegistryPage(ig.getDefinition().getPage().getPage(), type);
  }

  private ImplementationGuideDefinitionPageComponent getRegistryPage(List<ImplementationGuideDefinitionPageComponent> pages, String type) {
    for (ImplementationGuideDefinitionPageComponent page : pages) {
      if ((IgParser.getKind(page).equals(GuidePageKind.LIST) || IgParser.getKind(page).equals(GuidePageKind.DIRECTORY)) && hasType(page, type)) 
          return page;
      ImplementationGuideDefinitionPageComponent p = getRegistryPage(page.getPage(), type);
      if (p != null)
        return p;
    }
    return null;
  }

  private boolean hasType(ImplementationGuideDefinitionPageComponent page, String value) {
    for (CodeType t : IgParser.getType(page)) {
      if (t.getValue().equals(value))
        return true;
    }
    return false;
  }

  private ImplementationGuideDefinitionPageComponent getPage(String n, ImplementationGuideDefinitionPageComponent node) throws FHIRException {
    if (n.equals(node.getNameUrlType().getValue()))
      return node;
    for (ImplementationGuideDefinitionPageComponent page : node.getPage()) {
      ImplementationGuideDefinitionPageComponent p = getPage(n, page);
      if (p != null)
        return p;
    }
    return null;
  }

  
  public String getIndexPrefixForFile(String page, String logicalName) throws FHIRException {
    if (page.startsWith(code+"\\") || page.startsWith(code+"/") )
      page = page.substring(code.length()+1);
    ImplementationGuideDefinitionPageComponent p = getPage(page, ig.getDefinition().getPage());
    if (p == null)
      return sectionId+".??";
    else
      return p.getUserString(ToolResourceUtilities.NAME_PAGE_INDEX);
  }

  public String getPrefix() {
    if (isCore() || Utilities.noString(code))
      return "";
    else
      return code+"/";
  }

  public List<ImplementationGuideDefinitionPageComponent> getSpecialPages() {
    List<ImplementationGuideDefinitionPageComponent> res = new ArrayList<ImplementationGuide.ImplementationGuideDefinitionPageComponent>();
    if (ig != null)
      listSpecialPages(res, ig.getDefinition().getPage().getPage());
    return res;
  }

  private void listSpecialPages(List<ImplementationGuideDefinitionPageComponent> res, List<ImplementationGuideDefinitionPageComponent> pages) {
    for (ImplementationGuideDefinitionPageComponent page : pages) {
      if (IgParser.getKind(page) == GuidePageKind.TOC)
        res.add(page);
    }
  }
  
  public TableModel genToc(HierarchicalTableGenerator gen) throws FHIRException {
    TableModel model = gen.new TableModel();
    
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Table Of Contents", null, null, 0));
    addPage(gen, model.getRows(), ig.getDefinition().getPage());    
    return model;

   }

  private void addPage(HierarchicalTableGenerator gen, List<Row> rows, ImplementationGuideDefinitionPageComponent page) throws FHIRException {
    Row row = gen.new Row();
    rows.add(row);
    row.setIcon(getIcon(IgParser.getKind(page)), IgParser.getKind(page).getDisplay());
    
    String ndx = page.getUserString(ToolResourceUtilities.NAME_PAGE_INDEX);
    if (ndx == null)
      ndx = "";
    else
      ndx = ndx + " ";
    row.getCells().add(gen.new Cell("", page.getNameUrlType().getValue(), ndx + page.getTitle(), null, null));
    for (ImplementationGuideDefinitionPageComponent p : page.getPage()) {
      addPage(gen, row.getSubRows(), p);
    }
  }

  private String getIcon(GuidePageKind kind) {
    switch (kind) {
    case PAGE: return "icon-page.png";  
    case EXAMPLE: return "icon-example.png"; 
    case LIST: return "icon-list.gif";
    case INCLUDE: return "icon-include.png";
    case DIRECTORY: return "icon-directory.gif";
    case DICTIONARY: return "icon-dictionary.png";
    case TOC: return "icon-toc.png";
    case RESOURCE: return "icon-resource.png";
    default: return "icon-page.png";
    }
  }
  
  public void numberPages() {
    ig.getDefinition().getPage().setUserData(ToolResourceUtilities.NAME_PAGE_INDEX, sectionId+".0");
    numberPages(ig.getDefinition().getPage().getPage(), sectionId+".");
  }

  private void numberPages(List<ImplementationGuideDefinitionPageComponent> list, String prefix) {
    for (int i = 0; i < list.size(); i++) {
      ImplementationGuideDefinitionPageComponent page = list.get(i);
      page.setUserData(ToolResourceUtilities.NAME_PAGE_INDEX, prefix+Integer.toString(i+1)+(page.hasPage() ? ".0" : ""));
      numberPages(page.getPage(), prefix+Integer.toString(i+1)+".");
    }
  }

  public String getBrief() {
    return brief;
  }

  public String getHomePage() throws FHIRException {
    return ig == null ? null : code+"/"+ig.getDefinition().getPage().getNameUrlType().getValue();
  }

  public Example getExample(String rn, String id) {
    for (Example e : examples) {
      if (e.getResourceName().equals(rn) && e.getId().equals(id))
        return e;
    }
    return null;
  }

  public int getLevel() {
    if (isCore())
      return 0;
    else
      return 1;
  }

}
