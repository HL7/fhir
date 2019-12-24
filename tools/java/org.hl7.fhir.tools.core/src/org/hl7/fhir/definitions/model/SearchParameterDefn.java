package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r5.model.ExpressionNode;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;

/*
Copyright (c) 2011+, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/

public class SearchParameterDefn {
  public static class CompositeDefinition {
    private String definition;
    private String expression;
    public CompositeDefinition(String definition, String expression) {
      super();
      this.definition = definition;
      this.expression = expression;
    }
    public String getDefinition() {
      return definition;
    }
    public String getExpression() {
      return expression;
    }
    
  }

  public enum SearchType {
    composite, // search parameter is a composite of others
    number,  // search parameter must be a simple name 
    string,   // search parameter is a simple string, like a name part
    date,     // search parameter is onto a date
    quantity, // search parameter is onto a quantity (= token + -lower & -upper, and canonical)
    reference,// search parameter refers to a resource reference
    token,   // search parameter is onto a pair of fixed value strings, space and value. Space is optional
    uri,     // search onto a URI
    special;  // special case - not one of the others
  }
  
  
  private String code;
  private String description;
  private SearchType type;
  private SearchParameter.XPathUsageType xPathUsage;
  private List<String> paths = new ArrayList<String>();
  private String expression;
  private List<CompositeDefinition> composites = new ArrayList<CompositeDefinition>();
  private Set<String> targets = new HashSet<String>();
  private Set<String> manualTargets = new HashSet<String>();
  private SearchParameter resource;
  private ExpressionNode expressionNode;
  private boolean XPathDone;
  private List<String> otherResources = new ArrayList<String>();
  private String commonId;
  private boolean hierarchy;
  private StandardsStatus standardsStatus;
  private String url;
  
  // operational tracking
  private String xPath;
  private boolean works; // marked by the testing routines if this search parameter yields results for any of the examples
  private String normativeVersion;
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getDescription() {
    return description;
  }
  
  public SearchType getType() {
    return type;
  }
  
  public SearchParameterDefn(String code, String description, SearchType type, SearchParameter.XPathUsageType xPathUsage, StandardsStatus status) {
    super();
    this.code = code;
    this.description = description;
    this.type = type;
    this.xPathUsage = xPathUsage; 
    this.standardsStatus = status;
  }
    
  public SearchParameterDefn(SearchParameterDefn source, String oldName, String newName, String title, String name, StandardsStatus status) {
    super();
    code = source.code;
    description = source.description.replace("{{title}}", title).replace("{{titles}}", Utilities.pluralize(title, 2));
    type = source.type;
    xPathUsage = source.xPathUsage;
    for (String s : source.paths)
      paths.add(s.replace(oldName+'.', newName+'.')); 
    if (source.expression != null)
      expression = source.expression.replace("{{name}}", name);
    composites.addAll(source.composites);
    targets.addAll(source.targets);
    manualTargets.addAll(source.manualTargets);
    otherResources.addAll(source.otherResources);
    if (type == SearchType.composite && source.getStandardsStatus() != null)
      this.standardsStatus = source.getStandardsStatus();
    else
      this.standardsStatus = status;
  }

  public List<String> getPaths() {
    return paths;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public List<CompositeDefinition> getComposites() {
    return composites;
  }

  public String getPathSummary() {
    StringBuilder b = new StringBuilder();
    for (String s : paths) {
      b.append(", "+s);
    }
    return b.length() == 0 ? "" : b.toString().substring(2);
  }

  public boolean isWorks() {
    return works;
  }

  public void setWorks(boolean works) {
    this.works = works;
  }

  public String getXPath() {
    return xPath;
  }

  public void setXPath(String xPath) {
    this.xPath = xPath;
  }

  public String getTargetTypesAsText() {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    Set<String> t = getWorkingTargets();
    for (String rn : t) {
      if (first) {
        first = false;
        b.append("<br/>(");
      } else
        b.append(", ");
      if (rn.equals("Any")) 
        b.append("Any");
      else if (rn.equals("Binary")) 
        b.append("<a href=\"extras.html#Binary\">"+rn+"</a>");
      else
        b.append("<a href=\""+rn.toLowerCase()+".html\">"+rn+"</a>");
    }
    if (!first)
      b.append(")");
    return b.toString();
  }

  public Set<String> getWorkingTargets() {
    return manualTargets.size() > 0 ? manualTargets : targets;
  }

  public Set<String> getTargets() {
    return targets;
  }

  public static boolean isType(String type) {
    if ("composite".equals(type)) return true;
    if ("number".equals(type)) return true; 
    if ("string".equals(type)) return true;
    if ("date".equals(type)) return true;
    if ("quantity".equals(type)) return true;
    if ("reference".equals(type)) return true;
    if ("token".equals(type)) return true;
    return false;
  }

  public void setManualTypes(String[] list) {
    for (String s : list)
      manualTargets.add(s.trim());
  }

  public SearchParameter.XPathUsageType getxPathUsage() {
    return xPathUsage;
  }

  public SearchParameter getResource() {
    return resource;
  }

  public void setResource(SearchParameter resource) {
    this.resource = resource;
  }

  public ExpressionNode getExpressionNode() {
    return expressionNode;
  }

  public void setExpressionNode(ExpressionNode expressionNode) {
    this.expressionNode = expressionNode;
  }

  public boolean isXPathDone() {
    return XPathDone;
  }

  public void setXPathDone(boolean xPathDone) {
    XPathDone = xPathDone;
  }

  public List<String> getOtherResources() {
    return otherResources;
  }

  public String getCommonId() {
    return commonId;
  }

  public void setCommonId(String commonId) {
    this.commonId = commonId;
  }

  public boolean isHierarchy() {
    return hierarchy;
  }

  public void setHierarchy(boolean hierarchy) {
    this.hierarchy = hierarchy;
  }

  public StandardsStatus getStandardsStatus() {
    return standardsStatus;
  }

  public void setStandardsStatus(StandardsStatus standardsStatus) {
    this.standardsStatus = standardsStatus;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getNormativeVersion() {
    return normativeVersion;
  }

  public void setNormativeVersion(String normativeVersion) {
    this.normativeVersion = normativeVersion;
  }

  public void setStandardsStatus(StandardsStatus standardsStatus, String normativeVersion) {
    this.standardsStatus = standardsStatus;
    this.normativeVersion = normativeVersion;    
  }
}
