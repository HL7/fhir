package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

// a named set of profiles and extensions
// most resource defitions have one or two of these, and there are some others as well (e.g. CDA)
public class Profile {

  public enum ConformancePackageSourceType {
    Spreadsheet, Bundle;
  }

  // settings
  private String title; // what it's called to humans
  private String source; // the file to parse
  private List<Example> examples = new ArrayList<Example>(); // a file that is the example  
  private ConformancePackageSourceType sourceType;
  private Map<String, ArrayList<String>> metadata = new HashMap<String, ArrayList<String>>();
  private String introduction;
  private String notes;
  private String category;
  
  // content
  private List<ConstraintStructure> profiles = new ArrayList<ConstraintStructure>();
  private List<StructureDefinition> extensions = new ArrayList<StructureDefinition>();
  private List<ValueSet> valuesets = new ArrayList<ValueSet>();
  private List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
  private Map<String, MappingSpace> mappingSpaces = new HashMap<String, MappingSpace>();
  
  public Profile(String category) {
    super();
    this.category = category;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public ConformancePackageSourceType getSourceType() {
    return sourceType;
  }
  public void setSourceType(ConformancePackageSourceType sourceType) {
    this.sourceType = sourceType;
  }
  public List<Example> getExamples() {
    return examples;
  }

  public Map<String, ArrayList<String>> getMetadata() {
    return metadata;
  }

  public String metadata(String name) {
    if (!metadata.containsKey(name))
      return "";
    ArrayList<String> a = metadata.get(name);
    if (a.size() == 1) 
      return a.get(0);
    else
      return "";
  }

  public boolean hasMetadata(String name) {
    String s = metadata(name);
    return (s != null && !s.equals(""));
  }

  public void putMetadata(String name, String value) {
    ArrayList<String> a;
    if (metadata.containsKey(name))
      a = metadata.get(name);
    else {
      a = new ArrayList<String>();
      metadata.put(name, a);
    }
    a.add(value);
  }

  public void forceMetadata(String name, String value) {
    if (metadata.containsKey(name))
      metadata.remove(name);
    ArrayList<String> a = new ArrayList<String>();
    metadata.put(name, a);
    a.add(value);
  }
  public List<ConstraintStructure> getProfiles() {
    return profiles;
  }
  public List<StructureDefinition> getExtensions() {
    return extensions;
  }
  public List<ValueSet> getValuesets() {
    return valuesets;
  }
  public void loadFromComposition(Composition c, String source) throws Exception {
    putMetadata("id", c.getId());
    putMetadata("date", c.getDateElement().asStringValue());
    putMetadata("title", c.getTitle());
    putMetadata("status", c.getStatus().toCode());
    putMetadata("description", new XhtmlComposer().compose(c.getText().getDiv()));
    title = c.getTitle();
    this.source = source;
  }
  public String getId() {
    return metadata("id");
  }


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
  public String getDescription() {
    return metadata("description");
  }
  public List<SearchParameter> getSearchParameters() {
    return searchParameters;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public Map<String, MappingSpace> getMappingSpaces() {
    return mappingSpaces;
  }
  public StructureDefinition getExtension(String url) {
    for (StructureDefinition st : extensions) {
      if (st.getUrl().equals(url))
        return st;
    }
    return null;
  }
  public boolean coversResource(ResourceDefn resource) {
    for (ConstraintStructure item : profiles)
      if (item.getDefn() != null && item.getDefn().getName().equals(resource.getName()))
        return true;
    return false;
  }

  
  
}
