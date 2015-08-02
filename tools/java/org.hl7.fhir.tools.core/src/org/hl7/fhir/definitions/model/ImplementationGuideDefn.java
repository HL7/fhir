package org.hl7.fhir.definitions.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private String page;
  private boolean review;
  private String source;
  private String ballot;
  private String fmm;
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
  
  public ImplementationGuideDefn(String committee, String code, String name, String page, String source, boolean review, String ballot, String fmm, boolean core, List<ValidationMessage> issues) {
    super();
    this.code = code;
    this.name = name;
    this.source = source;
    this.page = page;
    this.review = review;
    this.committee = committee;
    this.fmm = fmm;
    this.ballot = ballot;
    this.core = core;
    this.issues = issues;
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

  public String getPage() {
    return page;
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

  public void setPage(String page) {
    this.page = page;
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
    
    List<LinkTriple> path = determinePath(n);
    
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

  private List<LinkTriple> determinePath(String n) {
    List<LinkTriple> res = new ArrayList<ImplementationGuideDefn.LinkTriple>();
    res.add(new LinkTriple(ig.getPage().getSource(), ig.getId().toUpperCase(), ig.getName()));
    if (!n.equals(ig.getPage().getSource())) {
      if (!findPage(n, res, ig.getPage().getPage())) {
        issues.add(new ValidationMessage(Source.Publisher, IssueType.PROCESSING, code+"/"+n, "The page "+n+" is not assigned a bread crumb yet", IssueSeverity.WARNING));
        res.add(new LinkTriple(null, "unsorted", "Work in Progress yet"));
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

  public ImplementationGuidePageComponent getVSRegistry() {
    return getVSRegistryPage(ig.getPage().getPage());
  }

  private ImplementationGuidePageComponent getVSRegistryPage(List<ImplementationGuidePageComponent> pages) {
    for (ImplementationGuidePageComponent page : pages) {
      if ((page.getKind().equals(GuidePageKind.LIST) || page.getKind().equals(GuidePageKind.DIRECTORY)) && hasType(page, "ValueSet")) 
          return page;
      ImplementationGuidePageComponent p = getVSRegistryPage(page.getPage());
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
}
