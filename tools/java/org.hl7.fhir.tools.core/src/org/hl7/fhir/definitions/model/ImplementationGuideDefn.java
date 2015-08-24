package org.hl7.fhir.definitions.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.ImplementationGuide;
import org.hl7.fhir.instance.model.ImplementationGuide.GuidePageKind;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePageComponent;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.OperationOutcome.IssueType;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Title;

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

  public String makeList(String pagename, String type, String genlevel, String crumbTitle) {
    String n = pagename;
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

  private List<LinkTriple> determinePath(String n, String type, String crumbTitle) {
    List<LinkTriple> res = new ArrayList<ImplementationGuideDefn.LinkTriple>();
    if (type.equals("valueSet") && hasVSRegistry()) {
      res.add(new LinkTriple(ig.getPage().getSource(), ig.getId().toUpperCase(), ig.getName()));
      findPage(getVSRegistry().getSource(), res, ig.getPage().getPage());
      res.add(new LinkTriple(null, crumbTitle, null));
    } else if (type.startsWith("extension:")) {
      res.add(new LinkTriple(ig.getPage().getSource(), ig.getId().toUpperCase(), ig.getName()));
      res.add(new LinkTriple(null, "Extension Stuff", "Work in Progress yet"));
    } else {
      res.add(new LinkTriple(ig.getPage().getSource(), ig.getId().toUpperCase(), ig.getName()));
      if (!n.equals(ig.getPage().getSource())) {
        if (!findPage(n, res, ig.getPage().getPage())) {
          issues.add(new ValidationMessage(Source.Publisher, IssueType.PROCESSING, code+"/"+n, "The page "+n+" is not assigned a bread crumb yet", IssueSeverity.WARNING));
          res.add(new LinkTriple(null, "unsorted", "Work in Progress yet"));
        }
      }
    }
    return res;
  }

  private boolean findPage(String n, List<LinkTriple> res, List<ImplementationGuidePageComponent> list) {
    for (ImplementationGuidePageComponent page : list) {
      if (n.equals(page.getSource())) {
        res.add(new LinkTriple(page.getSource(), page.getName(), null));
        return true;
      }
      if (page.hasPage()) {
        res.add(new LinkTriple(page.getSource(), page.getName(), null));
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
  public ImplementationGuidePageComponent getVSRegistry() {
    return getRegistryPage("ValueSet");
  }
  public ImplementationGuidePageComponent getRegistryPage(String type) {
    return getRegistryPage(ig.getPage().getPage(), type);
  }

  private ImplementationGuidePageComponent getRegistryPage(List<ImplementationGuidePageComponent> pages, String type) {
    for (ImplementationGuidePageComponent page : pages) {
      if ((page.getKind().equals(GuidePageKind.LIST) || page.getKind().equals(GuidePageKind.DIRECTORY)) && hasType(page, type)) 
          return page;
      ImplementationGuidePageComponent p = getRegistryPage(page.getPage(), type);
      if (p != null)
        return p;
    }
    return null;
  }

  private boolean hasType(ImplementationGuidePageComponent page, String value) {
    for (CodeType t : page.getType()) {
      if (t.getValue().equals(value))
        return true;
    }
    return false;
  }

  private ImplementationGuidePageComponent getPage(String n, ImplementationGuidePageComponent node) {
    if (n.equals(node.getSource()))
      return node;
    for (ImplementationGuidePageComponent page : node.getPage()) {
      ImplementationGuidePageComponent p = getPage(n, page);
      if (p != null)
        return p;
    }
    return null;
  }

  
  public String getIndexPrefixForFile(String page, String logicalName) {
    if (page.startsWith(code+"\\"))
      page = page.substring(code.length()+1);
    ImplementationGuidePageComponent p = getPage(page, ig.getPage());
    if (p == null)
      return sectionId+".??";
    else
      return p.getUserString(ToolResourceUtilities.NAME_PAGE_INDEX);
  }

  public String getPrefix() {
    if (isCore())
      return "";
    else
      return code+"/";
  }

  public List<ImplementationGuidePageComponent> getSpecialPages() {
    List<ImplementationGuidePageComponent> res = new ArrayList<ImplementationGuide.ImplementationGuidePageComponent>();
    if (ig != null)
      listSpecialPages(res, ig.getPage().getPage());
    return res;
  }

  private void listSpecialPages(List<ImplementationGuidePageComponent> res, List<ImplementationGuidePageComponent> pages) {
    for (ImplementationGuidePageComponent page : pages) {
      if (page.getKind() == GuidePageKind.TOC)
        res.add(page);
    }
  }
  
  public TableModel genToc(HeirarchicalTableGenerator gen) {
    TableModel model = gen.new TableModel();
    
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Table Of Contents", null, null, 0));
    addPage(gen, model.getRows(), ig.getPage());    
    return model;

   }

  private void addPage(HeirarchicalTableGenerator gen, List<Row> rows, ImplementationGuidePageComponent page) {
    Row row = gen.new Row();
    rows.add(row);
    row.setIcon(getIcon(page.getKind()), page.getKind().getDisplay());
    
    String ndx = page.getUserString(ToolResourceUtilities.NAME_PAGE_INDEX);
    if (ndx == null)
      ndx = "";
    else
      ndx = ndx + " ";
    row.getCells().add(gen.new Cell("", page.getSource(), ndx + page.getName(), null, null));
    for (ImplementationGuidePageComponent p : page.getPage()) {
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
    ig.getPage().setUserData(ToolResourceUtilities.NAME_PAGE_INDEX, sectionId+".0");
    numberPages(ig.getPage().getPage(), sectionId+".");
  }

  private void numberPages(List<ImplementationGuidePageComponent> list, String prefix) {
    for (int i = 0; i < list.size(); i++) {
      ImplementationGuidePageComponent page = list.get(i);
      page.setUserData(ToolResourceUtilities.NAME_PAGE_INDEX, prefix+Integer.toString(i+1)+(page.hasPage() ? ".0" : ""));
      numberPages(page.getPage(), prefix+Integer.toString(i+1)+".");
    }
  }

  public String getBrief() {
    return brief;
  }

  public String getHomePage() {
    return ig == null ? null : code+"/"+ig.getPage().getSource();
  }

  public Example getExample(String rn, String id) {
    for (Example e : examples) {
      if (e.getResourceName().equals(rn) && e.getId().equals(id))
        return e;
    }
    return null;
  }

}
