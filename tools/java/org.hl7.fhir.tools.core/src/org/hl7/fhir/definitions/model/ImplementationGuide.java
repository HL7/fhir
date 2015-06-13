package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;

public class ImplementationGuide {

  private String code;
  private String name;
  private String page;
  private boolean review;
  private String source;
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
  
  public ImplementationGuide(String code, String name, String page, String source, boolean review) {
    super();
    this.code = code;
    this.name = name;
    this.source = source;
    this.page = page;
    this.review = review;
  }
  
  public String getCode() {
    return code;
  }
  public String getName() {
    return name;
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
  
}
