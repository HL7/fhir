package org.hl7.fhir.definitions.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class MarkDownResourceDefinition {

  public static class MarkDownResourceDefinitionElement {
    private String path;
    private String profileName;
    private String discriminator;
    private String gForge;
    private String card;
    private String slice; 
    private String aliases;
    private boolean isModifier;
    private boolean mustSupport;
    private boolean summary;
    private String regex;
    private String uml;
    private String inv;
    private String type;
    private String binding;
    private String shortLabel;
    private String definition;
    private String maxLength;
    private String requirements;
    private String comments;
    private Map<String, String> mappings = new HashMap<String, String>();
    private String toDo;
    private String example;
    private String committeeNotes;
    private String displayHint;
    private String value;
    private String pattern;
    private String defaultValue;
    private String missingMeaning;
    private String w5;
    public String getPath() {
      return path;
    }
    public void setPath(String path) {
      this.path = path;
    }
    public String getProfileName() {
      return profileName;
    }
    public void setProfileName(String profileName) {
      this.profileName = profileName;
    }
    public String getDiscriminator() {
      return discriminator;
    }
    public void setDiscriminator(String discriminator) {
      this.discriminator = discriminator;
    }
    public String getGForge() {
      return gForge;
    }
    public void setGForge(String gForge) {
      this.gForge = gForge;
    }
    public String getCard() {
      return card;
    }
    public void setCard(String card) {
      this.card = card;
    }
    public String getSlice() {
      return slice;
    }
    public void setSlice(String slice) {
      this.slice = slice;
    }
    public String getAliases() {
      return aliases;
    }
    public void setAliases(String aliases) {
      this.aliases = aliases;
    }
    public boolean  getIsModifier() {
      return isModifier;
    }
    public void setIsModifier(Boolean isModifier) {
      this.isModifier = isModifier == null ? false : isModifier;
    }
    public boolean  getMustSupport() {
      return mustSupport;
    }
    public void setMustSupport(Boolean mustSupport) {
      this.mustSupport = mustSupport == null ? false : mustSupport;
    }
    public boolean  getSummary() {
      return summary;
    }
    public void setSummary(Boolean summary) {
      this.summary = summary == null ? false : summary;
    }
    public String getRegex() {
      return regex;
    }
    public void setRegex(String regex) {
      this.regex = regex;
    }
    public String getUml() {
      return uml;
    }
    public void setUml(String uml) {
      this.uml = uml;
    }
    public String getInv() {
      return inv;
    }
    public void setInv(String inv) {
      this.inv = inv;
    }
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
    public String getBinding() {
      return binding;
    }
    public void setBinding(String binding) {
      this.binding = binding;
    }
    public String getShortLabel() {
      return shortLabel;
    }
    public void setShortLabel(String shortLabel) {
      this.shortLabel = shortLabel;
    }
    public String getDefinition() {
      return definition;
    }
    public void setDefinition(String definition) {
      this.definition = definition;
    }
    public String getMaxLength() {
      return maxLength;
    }
    public void setMaxLength(String maxLength) {
      this.maxLength = maxLength;
    }
    public String getRequirements() {
      return requirements;
    }
    public void setRequirements(String requirements) {
      this.requirements = requirements;
    }
    public String getComments() {
      return comments;
    }
    public void setComments(String comments) {
      this.comments = comments;
    }
    public String getToDo() {
      return toDo;
    }
    public void setToDo(String toDo) {
      this.toDo = toDo;
    }
    public String getExample() {
      return example;
    }
    public void setExample(String example) {
      this.example = example;
    }
    public String getCommitteeNotes() {
      return committeeNotes;
    }
    public void setCommitteeNotes(String committeeNotes) {
      this.committeeNotes = committeeNotes;
    }
    public String getDisplayHint() {
      return displayHint;
    }
    public void setDisplayHint(String displayHint) {
      this.displayHint = displayHint;
    }
    public String getValue() {
      return value;
    }
    public void setValue(String value) {
      this.value = value;
    }
    public String getPattern() {
      return pattern;
    }
    public void setPattern(String pattern) {
      this.pattern = pattern;
    }
    public String getDefaultValue() {
      return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
    }
    public String getMissingMeaning() {
      return missingMeaning;
    }
    public void setMissingMeaning(String missingMeaning) {
      this.missingMeaning = missingMeaning;
    }
    public String getW5() {
      return w5;
    }
    public void setW5(String w5) {
      this.w5 = w5;
    }
    public Map<String, String> getMappings() {
      return mappings;
    }
    

  }
  public static class MarkDownResourceDefinitionStructure {
    private String name;
    private List<MarkDownResourceDefinitionElement> elements = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionElement>();

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<MarkDownResourceDefinitionElement> getElements() {
      return elements;
    }
    
    
  }
  public static class MarkDownResourceDefinitionCode {
    private String system;
    private String id;
    private String code;
    private String display;
    private String definition;
    private String comment;
    private String v2;
    private String v3;
    private String parent;
    private Map<String, String> langs = new HashMap<String, String>();
    public String getSystem() {
      return system;
    }
    public void setSystem(String system) {
      this.system = system;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getCode() {
      return code;
    }
    public void setCode(String code) {
      this.code = code;
    }
    public String getDisplay() {
      return display;
    }
    public void setDisplay(String display) {
      this.display = display;
    }
    public String getDefinition() {
      return definition;
    }
    public void setDefinition(String definition) {
      this.definition = definition;
    }
    public String getComment() {
      return comment;
    }
    public void setComment(String comment) {
      this.comment = comment;
    }
    public String getV2() {
      return v2;
    }
    public void setV2(String v2) {
      this.v2 = v2;
    }
    public String getV3() {
      return v3;
    }
    public void setV3(String v3) {
      this.v3 = v3;
    }
    public String getParent() {
      return parent;
    }
    public void setParent(String parent) {
      this.parent = parent;
    }
    public Map<String, String> getLangs() {
      return langs;
    }
  
    
  }
  
  
  public static class MarkDownResourceDefinitionCodeList {
    private String name;
    private List<MarkDownResourceDefinitionCode> codes = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionCode>();

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<MarkDownResourceDefinitionCode> getCodes() {
      return codes;
    }
    
    
  }
  public static class MarkDownResourceDefinitionInvariant {
    private String id;
    private String requirements;
    private String context;
    private String english;
    private String xpath;
    private String severity;
    private String turtle;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getRequirements() {
      return requirements;
    }
    public void setRequirements(String requirements) {
      this.requirements = requirements;
    }
    public String getContext() {
      return context;
    }
    public void setContext(String context) {
      this.context = context;
    }
    public String getEnglish() {
      return english;
    }
    public void setEnglish(String english) {
      this.english = english;
    }
    public String getXpath() {
      return xpath;
    }
    public void setXpath(String xpath) {
      this.xpath = xpath;
    }
    public String getSeverity() {
      return severity;
    }
    public void setSeverity(String severity) {
      this.severity = severity;
    }
    public String getTurtle() {
      return turtle;
    }
    public void setTurtle(String turtle) {
      this.turtle = turtle;
    }
    
  }
  public static class MarkDownResourceDefinitionBinding {
    private String name;
    private String strength;
    private String binding;
    private String reference;
    private String definition;
    private String description;
    private String uri;
    private String oid;
    private String status;
    private String webSite;
    private String email;
    private String copyright;
    private String v2Map;
    private String v3Map;
    
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getStrength() {
      return strength;
    }

    public void setStrength(String strength) {
      this.strength = strength;
    }

    public String getBinding() {
      return binding;
    }

    public void setBinding(String binding) {
      this.binding = binding;
    }

    public String getReference() {
      return reference;
    }

    public void setReference(String reference) {
      this.reference = reference;
    }

    public String getDefinition() {
      return definition;
    }

    public void setDefinition(String definition) {
      this.definition = definition;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }

    public String getOid() {
      return oid;
    }

    public void setOid(String oid) {
      this.oid = oid;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getWebSite() {
      return webSite;
    }

    public void setWebSite(String webSite) {
      this.webSite = webSite;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getCopyright() {
      return copyright;
    }

    public void setCopyright(String copyright) {
      this.copyright = copyright;
    }

    public String getV2Map() {
      return v2Map;
    }

    public void setV2Map(String v2Map) {
      this.v2Map = v2Map;
    }

    public String getV3Map() {
      return v3Map;
    }

    public void setV3Map(String v3Map) {
      this.v3Map = v3Map;
    }
    
  }

  private List<MarkDownResourceDefinitionBinding> bindings = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionBinding>();
  private List<MarkDownResourceDefinitionInvariant> invariants = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionInvariant>();
  private List<MarkDownResourceDefinitionStructure> structures = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionStructure>();
  private List<MarkDownResourceDefinitionCodeList> codelists = new ArrayList<MarkDownResourceDefinition.MarkDownResourceDefinitionCodeList>();
  private String filename;

  public List<MarkDownResourceDefinitionBinding> getBindings() {
    return bindings;
  }

  public List<MarkDownResourceDefinitionInvariant> getInvariants() {
    return invariants;
  }

  public List<MarkDownResourceDefinitionStructure> getStructures() {
    return structures;
  }

  public List<MarkDownResourceDefinitionCodeList> getCodelists() {
    return codelists;
  }

  public String getFilename() {
    return filename;
  }

  public void setFileName(String value) {
    filename = value;
  }

  public void write() throws Exception {
    StringBuilder b = new StringBuilder();

    writeStructures(b);
    writeInvariants(b);
    writeBindings(b);
    writeCodeLists(b);
//    TextFile.stringToFile(b.toString(), Utilities.path("c:\\temp\\rmd", filename.substring(filename.lastIndexOf("\\")+1)));
  }

  private void writeCodeLists(StringBuilder b) {
    for (MarkDownResourceDefinitionCodeList s : codelists) {
      b.append("CodeList "+s.getName()+"\r\n");
      for (MarkDownResourceDefinitionCode c : s.codes) {
        b.append("  ");
        if (Utilities.noString(c.system))
          b.append(c.id);
        else
          b.append(c.system);
        b.append(" ");
        b.append(c.code);
        b.append(" ");
        if (!Utilities.noString(c.parent))
          b.append("[parent = "+c.parent+"] ");
        if (!Utilities.noString(c.v2))
          b.append("[v2 = "+c.v2+"]");
        if (!Utilities.noString(c.v3))
          b.append("[v3 = "+c.v3+"]");
        b.append("(\""+Utilities.escapeJson(c.display)+"\")");  
        b.append(" : ");
        b.append("\""+Utilities.escapeJson(c.definition)+"\"");  
        b.append("\r\n");
        property(b, 4, "comment", c.comment);        
      }
      b.append("\r\n");
    }
    
  }

  private void writeStructures(StringBuilder b) {
    for (MarkDownResourceDefinitionStructure s : structures) {
      b.append("Structure "+s.getName()+"\r\n");
      for (MarkDownResourceDefinitionElement e : s.elements) {
        b.append("  "+e.path);
        if (!Utilities.noString(e.profileName)) 
          b.append(" ("+e.profileName+")");
        b.append(" [");
        b.append(e.card);
        b.append("] : ");
        b.append(e.type);
        if (e.isModifier)
          b.append("<<modifier>>");
        if (e.mustSupport)
          b.append("<<mustSupport>>");
        if (e.summary)
          b.append("<<summary>>");
        if (!Utilities.noString(e.shortLabel) || !Utilities.noString(e.definition)) {
          b.append("; \"");
          b.append(Utilities.escapeJson(e.shortLabel));
          if (!Utilities.noString(e.definition)) {
            b.append("\" : \"");
            b.append(Utilities.escapeJson(e.definition));
            b.append("\"");
          }
        }
        b.append("\r\n");

        property(b, 4, "discriminator", e.discriminator);
        property(b, 4, "slice", e.slice);
        property(b, 4, "aliases", e.aliases);
        property(b, 4, "regex", e.regex);
        property(b, 4, "uml", e.uml);
        property(b, 4, "inv", e.inv);
        property(b, 4, "binding", e.binding);
        property(b, 4, "maxLength", e.maxLength);
        property(b, 4, "requirements", e.requirements);
        property(b, 4, "comments", e.comments);
        property(b, 4, "toDo", e.toDo);
        property(b, 4, "example", e.example);
        property(b, 4, "committeeNotes", e.committeeNotes);
        property(b, 4, "displayHint", e.displayHint);
        property(b, 4, "pattern", e.pattern);
        property(b, 4, "defaultValue", e.defaultValue);
        property(b, 4, "missingMeaning", e.missingMeaning);
        property(b, 4, "w5", e.w5);
        property(b, 4, "value", e.value);
        property(b, 4, "gForge", e.gForge);
        
        for (String n : e.mappings.keySet())
          property(b, 4, "map:"+n, e.mappings.get(n));
      }
      b.append("\r\n");
    }
  }
  

  private void writeInvariants(StringBuilder b) {
    b.append("Invariants\r\n");
    for (MarkDownResourceDefinitionInvariant inv : invariants) {
      b.append("  "+inv.id+" : "+cn(inv.severity, "strength")+" on "+inv.context+" (\""+Utilities.escapeJson(inv.english)+"\"): \""+Utilities.escapeJson(inv.xpath)+"\"\r\n");
      property(b, 4, "requirements", inv.requirements);
      property(b, 4, "turtle", inv.turtle);
    }
    b.append("\r\n");
  }

  private void writeBindings(StringBuilder b) {
    b.append("Bindings\r\n");
    for (MarkDownResourceDefinitionBinding binding : bindings) {
      b.append("  "+binding.name+" : "+binding.binding+" from \""+binding.reference+"\" strength "+cn(binding.strength, "conformance"));
      if (Utilities.noString(binding.definition))
        b.append("; \""+Utilities.escapeJson(binding.definition)+"\"");
      b.append("\r\n");
      property(b, 4, "description", binding.description);
      property(b, 4, "uri", binding.uri);
      property(b, 4, "oid", binding.oid);
      property(b, 4, "status", binding.status);
      property(b, 4, "webSite", binding.webSite);
      property(b, 4, "email", binding.email);
      property(b, 4, "copyright", binding.copyright);
      property(b, 4, "v2Map", binding.v2Map);
      property(b, 4, "v3Map", binding.v3Map);
    }
    b.append("\r\n");
  }

  private String cn(String s, String type) {
    if (Utilities.noString(s))
      return "?"+type+"?";
    return s;
  }

  private void property(StringBuilder b, int indent, String name, String value) {
    if (Utilities.noString(value))
      return;
          
    b.append(Utilities.padLeft("", ' ', indent));
    b.append(".");
    b.append(name);
    b.append(" : ");
    b.append(Utilities.escapeJson(value));
    b.append("\r\n");
  }

}
