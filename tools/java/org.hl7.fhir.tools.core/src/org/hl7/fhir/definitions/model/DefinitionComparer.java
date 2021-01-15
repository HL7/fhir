package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Operation.OperationExample;
import org.hl7.fhir.definitions.model.Profile.ConformancePackageSourceType;
import org.hl7.fhir.definitions.model.ResourceDefn.InheritedMapping;
import org.hl7.fhir.definitions.model.ResourceDefn.PointSpec;
import org.hl7.fhir.definitions.model.SearchParameterDefn.CompositeDefinition;
import org.hl7.fhir.r5.model.Base;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.utilities.Utilities;

public class DefinitionComparer {

  public static boolean compareResourceDefinitions(ResourceDefn left, ResourceDefn right) {
    boolean res = true;

    if (!left.getName().equals(right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": status = "+left.getName()+" vs "+right.getName());
    }
    
    if (!stringsMatch(left.getEnteredInErrorStatus(), right.getEnteredInErrorStatus())) {
      if (left.getEnteredInErrorStatus() != null) {
        res = false;
        System.out.println("Definitions differ @"+left.getName()+": enteredInErrorStatus = "+left.getEnteredInErrorStatus()+" vs "+right.getEnteredInErrorStatus());
      }
    }
    
    if (!stringsMatch(left.getFmmLevel(), right.getFmmLevel())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": fmmLevel = "+left.getFmmLevel()+" vs "+right.getFmmLevel());
    }
    
    if (!stringsMatch(left.getProposedOrder(), right.getProposedOrder())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": proposedOrder = "+left.getProposedOrder()+" vs "+right.getProposedOrder());
    }

    if (!stringsMatch(left.getRequirements(), right.getRequirements())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": requirements = "+left.getRequirements()+" vs "+right.getRequirements());
    }
    
    if (!stringsMatch(left.getNormativePackage(), right.getNormativePackage())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": normativePackage = "+left.getNormativePackage()+" vs "+right.getNormativePackage());
    }
    
    if (!stringsMatch(left.getNormativeVersion(), right.getNormativeVersion())) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": normativeVersion = "+left.getNormativeVersion()+" vs "+right.getNormativeVersion());
    }
    
    if (left.getStatus() != right.getStatus()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": status = "+left.getStatus().toCode()+" vs "+right.getStatus().toCode());
    }
    
    if (left.getSecurityCategorization() != right.getSecurityCategorization()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": securityCategorization = "+left.getSecurityCategorization().toCode()+" vs "+right.getSecurityCategorization().toCode());
    }
    if (left.getApproval() != right.getApproval()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": FMGApproval = "+left.getApproval()+" vs "+right.getApproval());
    }
    
    if (left.isAbstract() != right.isAbstract()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": abstract = "+left.isAbstract()+" vs "+right.isAbstract());
    }
    
    if (left.isInterface() != right.isInterface()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": interface = "+left.isInterface()+" vs "+right.isInterface());
    }
    
    if (left.getWg() != right.getWg()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": wg = "+left.getWg().getCode()+" vs "+right.getWg().getCode());
    }
    
    if (!compareElementDefinitions(left.getName(), left.getRoot(), right.getRoot())) {
      res = false;
    }

    if ((left.getTemplate() != null) != (right.getTemplate() != null)) {
      res = false;      
      System.out.println("Definitions differ @"+left.getName()+": template = "+left.getTemplate() +" vs "+right.getTemplate());
    } else if (left.getTemplate() != null && !compareElementDefinitions(left.getName()+"#template", left.getTemplate(), right.getTemplate())) {
      res = false;
    }

    if (left.getLayout().size() != right.getLayout().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": layout.count = "+left.getLayout().size()+" vs "+right.getLayout().size());      
    } else {
      for (String s : left.getLayout().keySet()) {
        if (!compareLayout(left.getLayout()+".#layout."+s, left.getLayout().get(s), right.getLayout().get(s))) {
          res = false; 
        }
      }
    }

    if (left.getInheritedMappings().size() != right.getInheritedMappings().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": mappings.count = "+left.getInheritedMappings().size()+" vs "+right.getInheritedMappings().size());      
    } else {
      for (int i = 0; i < left.getInheritedMappings().size(); i++) {
        if (!compareMappings(left.getName()+".#mappings["+i+"]", left.getInheritedMappings().get(i), right.getInheritedMappings().get(i))) {
          res = false; 
        }
      }
    }
    
    if ((left.getConformancePack() != null) != (right.getConformancePack() != null)) {
      res = false;      
      System.out.println("Definitions differ @"+left.getName()+": conformancePack = "+left.getConformancePack() +" vs "+right.getConformancePack());
    } else if (left.getConformancePack() != null && !compareProfiles(left.getName()+"#conformancePack", left.getConformancePack(), right.getConformancePack())) {
      res = false;
    }

    if (left.getConformancePackages().size() != right.getConformancePackages().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": conformancePackages.count = "+left.getConformancePackages().size()+" vs "+right.getConformancePackages().size());      
    } else {
      for (int i = 0; i < left.getConformancePackages().size(); i++) {
        if (!compareProfiles(left.getName()+".#conformancePackages["+i+"]", left.getConformancePackages().get(i), right.getConformancePackages().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.getSearchParams().size() != right.getSearchParams().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": searchParams.count = "+left.getSearchParams().size()+" vs "+right.getSearchParams().size());      
    } else {
      for (String s : left.getSearchParams().keySet()) {
        if (!compareSearchParams(left.getName()+".search."+s, left.getSearchParams().get(s), right.getSearchParams().get(s))) {
          res = false; 
        }
      }
    }
    
    if (left.getExamples().size() != right.getExamples().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": examples.count = "+left.getExamples().size()+" vs "+right.getExamples().size());      
    } else {
      for (int i = 0; i < left.getExamples().size(); i++) {
        if (!compareExamples(left.getName()+".#examples["+i+"]", left.getExamples().get(i), right.getExamples().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.getOperations().size() != right.getOperations().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": operations.count = "+left.getOperations().size()+" vs "+right.getOperations().size());      
    } else {
      for (int i = 0; i < left.getOperations().size(); i++) {
        if (!compareOperations(left.getName()+".#op["+left.getOperations().get(i).getName()+"]", left.getOperations().get(i), right.getOperations().get(i))) {
          res = false; 
        }
      }
    }
    
    return res;
  }

  private static boolean compareProfiles(String path, Profile left, Profile right) {
    boolean res = true;
//    if (!stringsMatch(left.getTitle(), right.getTitle())) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": title = "+left.getTitle()+" vs "+right.getTitle());
//    }   
//    if (!stringsMatch(left.getSource(), right.getSource())) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": source = "+left.getSource()+" vs "+right.getSource());
//    }   
    if (!stringsMatch(left.getIntroduction(), right.getIntroduction())) {
      res = false;
      System.out.println("Definitions differ @"+path+": introduction = "+left.getIntroduction()+" vs "+right.getIntroduction());
    }   
    if (!stringsMatch(left.getNotes(), right.getNotes())) {
      res = false;
      System.out.println("Definitions differ @"+path+": notes = "+left.getNotes()+" vs "+right.getNotes());
    }   
    if (!stringsMatch(left.getCategory(), right.getCategory())) {
      res = false;
      System.out.println("Definitions differ @"+path+": category = "+left.getCategory()+" vs "+right.getCategory());
    }   

//    if (left.getSourceType() != right.getSourceType()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": sourceType = "+left.getSourceType()+" vs "+right.getSourceType());
//    }   

    if (left.getExamples().size() != right.getExamples().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": examples.count = "+left.getExamples().size()+" vs "+right.getExamples().size());      
    } else {
      for (int i = 0; i < left.getExamples().size(); i++) {
        if (!compareExamples(path+".#examples["+i+"]", left.getExamples().get(i), right.getExamples().get(i))) {
          res = false; 
        }
      }
    }
    
//    if (left.getMetadata().size() != right.getMetadata().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": metadata.count = "+left.getMetadata().size()+" vs "+right.getMetadata().size());      
//    } else {
//      for (int i = 0; i < left.getExamples().size(); i++) {
//        if (!stringsMatch(left.getMetadata().get(i).toString(), right.getMetadata().get(i).toString())) {
//          res = false;
//          System.out.println("Definitions differ @"+path+".#metadata["+i+"]: metadata = "+left.getMetadata().toString()+" vs "+right.getMetadata().toString());      
//        }
//      }
//    }
//    
//    if (left.getProfiles().size() != right.getProfiles().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": profiles.count = "+left.getProfiles().size()+" vs "+right.getProfiles().size());      
//    } 
//
//    if (left.getExtensions().size() != right.getExtensions().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": extensions.count = "+left.getExtensions().size()+" vs "+right.getExtensions().size());      
//    } 
//
//    if (left.getValuesets().size() != right.getValuesets().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": valuesets.count = "+left.getValuesets().size()+" vs "+right.getValuesets().size());      
//    } 
//
//    if (left.getSearchParameters().size() != right.getSearchParameters().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": searchParameters.count = "+left.getSearchParameters().size()+" vs "+right.getSearchParameters().size());      
//    } 
//
//    if (left.getMappingSpaces().size() != right.getMappingSpaces().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": searchParameters.count = "+left.getMappingSpaces().size()+" vs "+right.getMappingSpaces().size());      
//    } 
//
//    if (left.getOperations().size() != right.getOperations().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": operations.count = "+left.getOperations().size()+" vs "+right.getOperations().size());      
//    } 

    return res;
  }

  private static boolean compareMappings(String path, InheritedMapping left, InheritedMapping right) {
    boolean res = true;
    if (!stringsMatch(left.getPath(), right.getPath())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getPath()+" vs "+right.getPath());
    }      

    if (left.getMappings().size() != right.getMappings().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": mappings.count = "+left.getMappings().size()+" vs "+right.getMappings().size());      
    } else {
      for (String s : left.getMappings().keySet()) {
        if (!stringsMatch(left.getMappings().get(s), right.getMappings().get(s))) {
          res = false;
          System.out.println("Definitions differ @"+path+".$"+s+": "+left.getMappings().get(s)+" vs "+right.getMappings().get(s));
        }      
      }
    }
    
    return res;
  }

  private static boolean compareLayout(String path, PointSpec left, PointSpec right) {
    boolean res = true;
    if (left.getX() != right.getX()) {
      res = false;
      System.out.println("Definitions differ @"+path+": x = "+left.getX()+" vs "+right.getX());
    }  
    if (left.getY() != right.getY()) {
      res = false;
      System.out.println("Definitions differ @"+path+": y = "+left.getY()+" vs "+right.getY());
    }  
    return res;
  }

  private static boolean compareOperations(String path, Operation left, Operation right) {
    boolean res = true;

    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }      

    if (!stringsMatch(left.getKind(), right.getKind())) {
      res = false;
      System.out.println("Definitions differ @"+path+": kind = "+left.getKind()+" vs "+right.getKind());
    }  

    if (!markdownMatches(left.getDoco(), right.getDoco())) {
      res = false;
      System.out.println("Definitions differ @"+path+": doco = ");
      System.out.println("  "+left.getDoco());
      System.out.println("  vs");
      System.out.println("  "+right.getDoco());
    }  

    if (!stringsMatch(left.getTitle(), right.getTitle())) {
      res = false;
      System.out.println("Definitions differ @"+path+": title = "+left.getTitle()+" vs "+right.getTitle());
    }  

    if (!stringsMatch(left.getFooter(), right.getFooter())) {
      res = false;
      System.out.println("Definitions differ @"+path+": footer = "+left.getFooter()+" vs "+right.getFooter());
    }  

    if (!stringsMatch(left.getFooter2(), right.getFooter2())) {
      res = false;
      System.out.println("Definitions differ @"+path+": footer2 = "+left.getFooter2()+" vs "+right.getFooter2());
    }  
    
    if (!stringsMatch(left.getFmm(), right.getFmm()) && !Utilities.noString(left.getFmm())) {
      res = false;
      System.out.println("Definitions differ @"+path+": fmm = "+left.getFmm()+" vs "+right.getFmm());
    }  

    if (left.isSystem() != right.isSystem()) {
      res = false;
      System.out.println("Definitions differ @"+path+": idempotent = "+left.isSystem()+" vs "+right.isSystem());
    }  
    
    if (left.isType() != right.isType()) {
      res = false;
      System.out.println("Definitions differ @"+path+": type = "+left.isType()+" vs "+right.isType());
    }  
    
    if (left.isInstance() != right.isInstance()) {
      res = false;
      System.out.println("Definitions differ @"+path+": instance = "+left.isInstance()+" vs "+right.isInstance());
    }  
    
    if (!booleansMatch(left.getIdempotent(), right.getIdempotent())) {
      res = false;
      System.out.println("Definitions differ @"+path+": idempotent = "+left.getIdempotent()+" vs "+right.getIdempotent());
    }  

    if (left.getStandardsStatus() != right.getStandardsStatus() && left.getStandardsStatus() != null) {
      res = false;
      System.out.println("Definitions differ @"+path+": standardsStatus = "+left.getStandardsStatus()+" vs "+right.getStandardsStatus());
    }
    
    if (left.getParameters().size() != right.getParameters().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": parameters.count = "+left.getParameters().size()+" vs "+right.getParameters().size());      
    } else {
      for (int i = 0; i < left.getParameters().size(); i++) {
        if (!compareOperationParameters(path+"."+left.getParameters().get(i).getName(), left.getParameters().get(i), right.getParameters().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.getExamples().size() != right.getExamples().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": examples.count = "+left.getExamples().size()+" vs "+right.getExamples().size());      
    } else {
      for (int i = 0; i < left.getExamples().size(); i++) {
        if (!compareOperationExamples(path+".#examples["+i+"]", left.getExamples().get(i), right.getExamples().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.getExamples2().size() != right.getExamples2().size()) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": examples2.count = "+left.getExamples2().size()+" vs "+right.getExamples2().size());      
    } else {
      for (int i = 0; i < left.getExamples2().size(); i++) {
        if (!compareOperationExamples(path+".#examples2["+i+"]", left.getExamples2().get(i), right.getExamples2().get(i))) {
          res = false; 
        }
      }
    }

    return res;
  }

  private static boolean compareOperationExamples(String path, OperationExample left, OperationExample right) {
    boolean res = true;
    if (!stringsMatch(left.getContent(), right.getContent())) {
      res = false;
      System.out.println("Definitions differ @"+path+": content = "+left.getContent()+" vs "+right.getContent());
    }  
    if (!stringsMatch(left.getComment(), right.getComment())) {
      res = false;
      System.out.println("Definitions differ @"+path+": getComment = "+left.getComment()+" vs "+right.getComment());
    }  
    if (left.isResponse() != right.isResponse()) {
      res = false;
      System.out.println("Definitions differ @"+path+": response = "+left.isResponse()+" vs "+right.isResponse());
    }  
    
    return res;
  }

  private static boolean compareOperationParameters(String path, OperationParameter left, OperationParameter right) {
    boolean res = true;
    
    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }      
    
    if (!stringsMatch(left.getUse(), right.getUse())) {
      res = false;
      System.out.println("Definitions differ @"+path+": use = "+left.getUse()+" vs "+right.getUse());
    }      
    
    if (!markdownMatches(left.getDoc(), right.getDoc())) {
      res = false;
      System.out.println("Definitions differ @"+path+": doc = "+left.getDoc()+" vs "+right.getDoc());
    }      
    
    if (left.getMin() != right.getMin()) {
      res = false;
      System.out.println("Definitions differ @"+path+": doc = "+left.getMin()+" vs "+right.getMin());
    }      
    
    if (!stringsMatch(left.getMax(), right.getMax())) {
      res = false;
      System.out.println("Definitions differ @"+path+": max = "+left.getMax()+" vs "+right.getMax());
    }      

    if (!stringsMatch(left.getFhirType(), right.getFhirType())) {
      res = false;
      System.out.println("Definitions differ @"+path+": fhirType = "+left.getFhirType()+" vs "+right.getFhirType());
    }      

    if (!stringsMatch(left.getSearchType(), right.getSearchType())) {
      res = false;
      System.out.println("Definitions differ @"+path+": searchType = "+left.getSearchType()+" vs "+right.getSearchType());
    }      

    if (!stringsMatch(left.getProfile(), right.getProfile())) {
      res = false;
      System.out.println("Definitions differ @"+path+": profile = "+left.getProfile()+" vs "+right.getProfile());
    }      

    if (left.hasBinding() != right.hasBinding()) {
      res = false;
      System.out.println("Definitions differ @"+path+": hasbinding = "+left.hasBinding()+" vs "+right.hasBinding());      
    } else if (left.hasBinding()) {
      if (!compareBindings(path+"#binding", left.getBinding(), right.getBinding())) {
        res = false;
      }
    }
    
    if (left.getParts().size() != right.getParts().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": parts.count = "+left.getParts().size()+" vs "+right.getParts().size());      
    } else {
      for (int i = 0; i < left.getParts().size(); i++) {
        if (!compareOperationParameters(path+"."+left.getParts().get(i).getName(), left.getParts().get(i), right.getParts().get(i))) {
          res = false; 
        }
      }
    }
    
    return false;
  }

  private static boolean compareExamples(String path, Example left, Example right) {
    boolean res = true;
    
    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }  
    
    if (!stringsMatch(left.getId(), right.getId())) {
      res = false;
      System.out.println("Definitions differ @"+path+": id = "+left.getId()+" vs "+right.getId());
    }  
    
    if (!stringsMatch(left.getDescription(), right.getDescription())) {
      res = false;
      System.out.println("Definitions differ @"+path+": description = "+left.getDescription()+" vs "+right.getDescription());
    }  
    
    if (!stringsMatch(left.getTitle(), right.getTitle())) {
      res = false;
      System.out.println("Definitions differ @"+path+": title = "+left.getTitle()+" vs "+right.getTitle());
    }  
    
    if (!stringsMatch(left.getXhtm(), right.getXhtm())) {
      res = false;
      System.out.println("Definitions differ @"+path+": xhtm = "+left.getXhtm()+" vs "+right.getXhtm());
    }  
    
    if (left.getType() != right.getType()) {
      res = false;
      System.out.println("Definitions differ @"+path+": type = "+left.getType()+" vs "+right.getType());
    }  
    
    if (!booleansMatch(left.isRegistered(), right.isRegistered())) {
      res = false;
      System.out.println("Definitions differ @"+path+": registered = "+left.isRegistered()+" vs "+right.isRegistered());
    }  
        
    if (!stringsMatch(left.getResourceName(), right.getResourceName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": resourceName = "+left.getDescription()+" vs "+right.getDescription());
    }  
        
    if (!stringsMatch(left.getIg(), right.getIg())) {
      res = false;
      System.out.println("Definitions differ @"+path+": ig = "+left.getIg()+" vs "+right.getIg());
    }  
    
    if (!stringsMatch(left.getExampleFor(), right.getExampleFor())) {
      res = false;
      System.out.println("Definitions differ @"+path+": exampleFor = "+left.getExampleFor()+" vs "+right.getExampleFor());
    }  
    
    return res;
  }

  private static boolean compareSearchParams(String path, SearchParameterDefn left, SearchParameterDefn right) {
    boolean res = true;

    if (right == null) {
      res = false;
      System.out.println("Definitions differ @"+path+": no match");
    }
    
    if (!stringsMatch(left.getDescription(), right.getDescription())) {
      res = false;
      System.out.println("Definitions differ @"+path+": description = "+left.getDescription()+" vs "+right.getDescription());
    }   
    if (!stringsMatch(left.getExpression(), right.getExpression()) && (left.getExpression() != null)) {
      res = false;
      System.out.println("Definitions differ @"+path+": expression = "+left.getExpression()+" vs "+right.getExpression());
    }
    if (!stringsMatch(left.getCommonId(), right.getCommonId())) {
      res = false;
      System.out.println("Definitions differ @"+path+": commonId = "+left.getCommonId()+" vs "+right.getCommonId());
    }
    if (!stringsMatch(left.getUrl(), right.getUrl())) {
      res = false;
      System.out.println("Definitions differ @"+path+": url = "+left.getUrl()+" vs "+right.getUrl());
    }
    if (!stringsMatch(left.getXPath(), right.getXPath()) && (left.getXPath() != null)) {
      res = false;
      System.out.println("Definitions differ @"+path+": xPath = "+left.getXPath()+" vs "+right.getXPath());
    }
    if (!stringsMatch(left.getNormativeVersion(), right.getNormativeVersion())) {
      res = false;
      System.out.println("Definitions differ @"+path+": normativeVersion = "+left.getNormativeVersion()+" vs "+right.getNormativeVersion());
    }

    if (left.getType() != right.getType()) {
      res = false;
      System.out.println("Definitions differ @"+path+": type = "+left.getType()+" vs "+right.getType());
    }
    
    if (left.getxPathUsage() != right.getxPathUsage()) {
      res = false;
      System.out.println("Definitions differ @"+path+": xPathUsage = "+left.getxPathUsage()+" vs "+right.getxPathUsage());
    }
// often wrong in spreadsheet model    
//    if (left.getStandardsStatus() != right.getStandardsStatus() && left.getStandardsStatus() != null) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": standardsStatus = "+left.getStandardsStatus()+" vs "+right.getStandardsStatus());
//    }

    if (left.getManualTypes().size() != right.getManualTypes().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": manualTypes.count = "+left.getManualTypes().size()+" vs "+right.getManualTypes().size());      
    } else {
      for (String s : left.getManualTypes()) {
        if (!right.getManualTypes().contains(s)) {
          System.out.println("Definitions differ @"+path+": manualType "+s+" not found");      
        }
      }
    }
//    
//    if (left.getManualTargets().size() != right.getManualTargets().size()) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": manualTargets.count = "+left.getManualTargets().size()+" vs "+right.getManualTargets().size());      
//    } else {
//      for (String s : left.getManualTargets()) {
//        if (!right.getManualTargets().contains(s)) {
//          System.out.println("Definitions differ @"+path+": manualTarget "+s+" not found");      
//        }
//      }
//    }
    
    if (left.getComposites().size() != right.getComposites().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": composites.count = "+left.getComposites().size()+" vs "+right.getComposites().size());      
    } else {
      for (int i = 0; i < left.getComposites().size(); i++) {
        if (!compareSearchParamComposites(path+".#composites["+i+"]", left.getComposites().get(i), right.getComposites().get(i))) {
          res = false; 
        }
      }
    }     
    
    return res;
  }

  private static boolean compareSearchParamComposites(String path, CompositeDefinition left, CompositeDefinition right) {
    boolean res = true;
    if (!stringsMatch(left.getDefinition(), right.getDefinition())) {
      res = false;
      System.out.println("Definitions differ @"+path+": definition = "+left.getDefinition()+" vs "+right.getDefinition());
    }
    
    if (!stringsMatch(left.getExpression(), right.getExpression())) {
      res = false;
      System.out.println("Definitions differ @"+path+": expression = "+left.getExpression()+" vs "+right.getExpression());
    }
    return res;
  }

  public static boolean compareElementDefinitions(String path, ElementDefn left, ElementDefn right) {
    boolean res = true;

    if (!stringsMatch(left.getModifierReason(), right.getModifierReason())) {
      res = false;
      System.out.println("Definitions differ @"+path+": modifierReason = "+left.getModifierReason()+" vs "+right.getModifierReason());
    }
    
    if (!stringsMatch(left.getRegex(), right.getRegex())) {
      res = false;
      System.out.println("Definitions differ @"+path+": regex = "+left.getRegex()+" vs "+right.getRegex());
    }
    
    if (!stringsMatch(left.getUmlDir(), right.getUmlDir())) {
      res = false;
      System.out.println("Definitions differ @"+path+": umlDir = "+left.getUmlDir()+" vs "+right.getUmlDir());
    }
    
    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }
    
    if (!stringsMatch(left.getShortDefn(), right.getShortDefn())) {
      res = false;
      System.out.println("Definitions differ @"+path+": shortDefn = "+left.getShortDefn()+" vs "+right.getShortDefn());
    }
    
    if (!markdownMatches(left.getDefinition(), right.getDefinition())) {
      res = false;
      System.out.println("Definitions differ @"+path+": definition = ");
      System.out.println("  "+Utilities.normalize(left.getDefinition().strip()));
      System.out.println("  "+Utilities.normalize(right.getDefinition().strip()));
    }
    
    if (!markdownMatches(left.getRequirements(), right.getRequirements())) {
      res = false;
      System.out.println("Definitions differ @"+path+": requirements = "+left.getRequirements()+" vs "+right.getRequirements());
    }
    
    if (!markdownMatches(left.getComments(), right.getComments())) {
      res = false;
      System.out.println("Definitions differ @"+path+": comments = ");
      System.out.println("  "+Utilities.normalize(left.getComments().strip()));
      System.out.println("  "+Utilities.normalize(right.getComments().strip()));
    }
    
    if (!stringsMatch(left.getTodo(), right.getTodo())) {
      res = false;
      System.out.println("Definitions differ @"+path+": todo = "+left.getTodo()+" vs "+right.getTodo());
    }
    
    if (!stringsMatch(left.getCommitteeNotes(), right.getCommitteeNotes())) {
      res = false;
      System.out.println("Definitions differ @"+path+": committeeNotes = "+left.getCommitteeNotes()+" vs "+right.getCommitteeNotes());
    }
    
    if (!stringsMatch(left.getMaxLength(), right.getMaxLength())) {
      res = false;
      System.out.println("Definitions differ @"+path+": maxLength = "+left.getMaxLength()+" vs "+right.getMaxLength());
    }
    
    if (!stringsMatch(left.getProfileName(), right.getProfileName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": profileName = "+left.getProfileName()+" vs "+right.getProfileName());
    }
    
    if (!stringsMatch(left.getMeaningWhenMissing(), right.getMeaningWhenMissing())) {
      res = false;
      System.out.println("Definitions differ @"+path+": meaningWhenMissing = "+left.getMeaningWhenMissing()+" vs "+right.getMeaningWhenMissing());
    }
    
    if (!stringsMatch(left.getStatedType(), right.getStatedType())) {
      res = false;
      System.out.println("Definitions differ @"+path+": statedType = "+left.getStatedType()+" vs "+right.getStatedType());
    }
    
    if (!stringsMatch(left.getDisplayHint(), right.getDisplayHint())) {
      res = false;
      System.out.println("Definitions differ @"+path+": displayHint = "+left.getDisplayHint()+" vs "+right.getDisplayHint());
    }
    
    if (!stringsMatch(left.getW5(), right.getW5())) {
      res = false;
      System.out.println("Definitions differ @"+path+": w5 = "+left.getW5()+" vs "+right.getW5());
    }
    
    if (!stringsMatch(left.getOrderMeaning(), right.getOrderMeaning())) {
      res = false;
      System.out.println("Definitions differ @"+path+": orderMeaning = "+left.getOrderMeaning()+" vs "+right.getOrderMeaning());
    }
    
    if (left.isNoBindingAllowed() != right.isNoBindingAllowed()) {
      res = false;
      System.out.println("Definitions differ @"+path+": noBindingAllowed = "+left.isNoBindingAllowed()+" vs "+right.isNoBindingAllowed());
    }
    
    if (left.isTranslatable() != right.isTranslatable()) {
      res = false;
      System.out.println("Definitions differ @"+path+": translatable = "+left.isTranslatable()+" vs "+right.isTranslatable());
    }
    
    if (left.isXmlAttribute() != right.isXmlAttribute()) {
      res = false;
      System.out.println("Definitions differ @"+path+": xmlAttribute = "+left.isXmlAttribute()+" vs "+right.isXmlAttribute());
    }
    
    if (left.isUmlBreak() != right.isUmlBreak()) {
      res = false;
      System.out.println("Definitions differ @"+path+": umlBreak = "+left.isUmlBreak()+" vs "+right.isUmlBreak());
    }
    
    if (left.isAbstractType() != right.isAbstractType()) {
      res = false;
      System.out.println("Definitions differ @"+path+": abstractType = "+left.isAbstractType()+" vs "+right.isAbstractType());
    }
    
    if (left.getHierarchy() != right.getHierarchy()) {
      res = false;
      System.out.println("Definitions differ @"+path+": hierarchy = "+left.getHierarchy()+" vs "+right.getHierarchy());
    }
    
    if (left.isModifier() != right.isModifier()) {
      res = false;
      System.out.println("Definitions differ @"+path+": modifier = "+left.isModifier()+" vs "+right.isModifier());
    }
    
    if (!booleansMatch(left.isMustSupport(), right.isMustSupport())) {
      res = false;
      System.out.println("Definitions differ @"+path+": mustSupport = "+left.isMustSupport()+" vs "+right.isMustSupport());
    }
    
//    if (!booleansMatch(left.isSummaryItem(), right.isSummaryItem()) && !(left.isSummaryItem() == null && right.isSummaryItem() == true)) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": summaryItem = "+left.isSummaryItem()+" vs "+right.isSummaryItem());
//    }
    
    if (left.getSvgLeft() != right.getSvgLeft()) {
      res = false;
      System.out.println("Definitions differ @"+path+": svgLeft = "+left.getSvgLeft()+" vs "+right.getSvgLeft());
    }

    if (left.getSvgTop() != right.getSvgTop()) {
      res = false;
      System.out.println("Definitions differ @"+path+": svgTop = "+left.getSvgTop()+" vs "+right.getSvgTop());
    }

    if (left.getSvgWidth() != right.getSvgWidth()) {
      res = false;
      System.out.println("Definitions differ @"+path+": svgWidth = "+left.getSvgWidth()+" vs "+right.getSvgWidth());
    }
    
    if (left.getStandardsStatus() != right.getStandardsStatus() && left.getStandardsStatus() != null) {
      res = false;
      System.out.println("Definitions differ @"+path+": standardsStatus = "+left.getStandardsStatus()+" vs "+right.getStandardsStatus());
    }
    
    if (!integersMatch(left.getMinCardinality(), right.getMinCardinality())) {
      res = false;
      System.out.println("Definitions differ @"+path+": minCardinality = "+left.getMinCardinality()+" vs "+right.getMinCardinality());
    }
    if (!integersMatch(left.getMaxCardinality(), right.getMaxCardinality())) {
      res = false;
      System.out.println("Definitions differ @"+path+": maxCardinality = "+left.getMaxCardinality()+" vs "+right.getMaxCardinality());
    }

    if (left.getElements().size() != right.getElements().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": elements.count = "+left.getElements().size()+" vs "+right.getElements().size());      
    } else {
      for (int i = 0; i < left.getElements().size(); i++) {
        if (!compareElementDefinitions(path+"."+left.getElements().get(i).getName(), left.getElements().get(i), right.getElements().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.hasBinding() != right.hasBinding()) {
      res = false;
      System.out.println("Definitions differ @"+path+": hasbinding = "+left.hasBinding()+" vs "+right.hasBinding());      
    } else if (left.hasBinding()) {
      if (!compareBindings(path+"#binding", left.getBinding(), right.getBinding())) {
        res = false;
      }
    }

    if (left.getTypes().size() != right.getTypes().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": types.count = "+left.getTypes().size()+" vs "+right.getTypes().size());      
    } else {
      for (int i = 0; i < left.getTypes().size(); i++) {
        if (!compareTypeRefs(path+".#type["+i+"]", left.getTypes().get(i), right.getTypes().get(i))) {
          res = false; 
        }
      }
    }
    
    if (left.getInvariants().size() != right.getInvariants().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": invariants.count = "+left.getInvariants().size()+" vs "+right.getInvariants().size());      
    } else {
      for (String s : left.getInvariants().keySet()) {
        if (!compareInvariants(path+".#invariant["+s+"]", left.getInvariants().get(s), right.getInvariants().get(s))) {
          res = false; 
        }
      }
    }

    if (left.getStatedInvariants().size() != right.getStatedInvariants().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": conditions.count = "+left.getStatedInvariants().size()+" vs "+right.getStatedInvariants().size());      
    } else {
      for (int i = 0; i < left.getStatedInvariants().size(); i++) {
        if (!compareInvariants(path+".#condition["+i+"]", left.getStatedInvariants().get(i), right.getStatedInvariants().get(i))) {
          res = false; 
        }
      }
    }

    if (left.getMappings().size() != right.getMappings().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": mappings.count = "+left.getMappings().size()+" vs "+right.getMappings().size());      
    } else {
      for (String s : left.getMappings().keySet()) {
        if (!stringsMatch(left.getMappings().get(s), right.getMappings().get(s))) {
          res = false;
          System.out.println("Definitions differ @"+path+".$"+s+": "+left.getMappings().get(s)+" vs "+right.getMappings().get(s));
        }      
      }
    }

    if (left.getAliases().size() != right.getAliases().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": aliases.count = "+left.getAliases().size()+" vs "+right.getAliases().size());      
    } else {
      for (String s : left.getAliases()) {
        if (!right.getAliases().contains(s)) {
          System.out.println("Definitions differ @"+path+": alias "+s+" not found");      
        }
      }
    }
    
    if ((left.getDerivation() != null) != (right.getDerivation() != null)) {
      res = false;      
      System.out.println("Definitions differ @"+left.getName()+": derivation = "+left.getDerivation() +" vs "+right.getDerivation());
    }

    if ((left.getExample() != null) != (right.getExample() != null)) {
      res = false;      
      System.out.println("Definitions differ @"+left.getName()+": example = "+left.getExample() +" vs "+right.getExample());
    } else if (left.getExample() != null && !Base.compareDeep(left.getExample(), right.getExample(), false)) {
      res = false;
      System.out.println("Definitions differ @"+left.getName()+": example = "+left.getExample() +" vs "+right.getExample());
    }

    if (left.getOtherExamples().size() != right.getOtherExamples().size()) {
      res = false;
      System.out.println("Definitions differ @"+path+": otherExamples.count = "+left.getOtherExamples().size()+" vs "+right.getOtherExamples().size());      
    } else {
      for (Integer i : left.getOtherExamples().keySet()) {
        if (!Base.compareDeep(left.getOtherExamples().get(i), right.getOtherExamples().get(i), false)) {
          res = false;
          System.out.println("Definitions differ @"+path+": otherExamples["+i+"] = "+left.getOtherExamples().get(i)+" vs "+right.getOtherExamples().get(i));      
        }
      }
    }
    
    return res;
  }

  private static boolean compareInvariants(String path, Invariant left, Invariant right) {
    boolean res = true;
    if (!stringsMatch(left.getContext(), right.getContext()) && !isKnownAcceptableContexts(left.getContext(), right.getContext())) {
      res = false;
      System.out.println("Definitions differ @"+path+": context = "+left.getContext()+" vs "+right.getContext());
    }
    if (!stringsMatch(left.getEnglish(), right.getEnglish())) {
      res = false;
      System.out.println("Definitions differ @"+path+": english = "+left.getEnglish()+" vs "+right.getEnglish());
    }
    if (!stringsMatch(left.getOcl(), right.getOcl())) {
      res = false;
      System.out.println("Definitions differ @"+path+": ocl = "+left.getOcl()+" vs "+right.getOcl());
    }
    if (!stringsMatch(left.getXpath(), right.getXpath())) {
      res = false;
      System.out.println("Definitions differ @"+path+": xpath = "+left.getXpath()+" vs "+right.getXpath());
    }
    if (!stringsMatch(left.getId(), right.getId())) {
      res = false;
      System.out.println("Definitions differ @"+path+": id = "+left.getId()+" vs "+right.getId());
    }
    if (!stringsMatch(left.getFixedName(), right.getFixedName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": fixedName = "+left.getFixedName()+" vs "+right.getFixedName());
    }
    if (!stringsMatch(left.getSeverity(), right.getSeverity())) {
      res = false;
      System.out.println("Definitions differ @"+path+": severity = "+left.getSeverity()+" vs "+right.getSeverity());
    }
    if (!stringsMatch(left.getTurtle(), right.getTurtle())) {
      res = false;
      System.out.println("Definitions differ @"+path+": turtle = "+left.getTurtle()+" vs "+right.getTurtle());
    }
    if (!stringsMatch(left.getRequirements(), right.getRequirements())) {
      res = false;
      System.out.println("Definitions differ @"+path+": requirements = "+left.getRequirements()+" vs "+right.getRequirements());
    }
    if (!stringsMatch(left.getExpression(), right.getExpression())) {
      res = false;
      System.out.println("Definitions differ @"+path+": expression = "+left.getExpression()+" vs "+right.getExpression());
    }
    if (!stringsMatch(left.getExplanation(), right.getExplanation())) {
      res = false;
      System.out.println("Definitions differ @"+path+": explanation = "+left.getExplanation()+" vs "+right.getExplanation());
    }     
    
    return res;
  }

  private static boolean isKnownAcceptableContexts(String left, String right) {
    return "CanonicalResource".equals(left) && !right.contains(".");
  }

  private static boolean compareTypeRefs(String path, TypeRef left, TypeRef right) {
    boolean res = true;
    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }
    if (!stringsMatch(left.getProfile(), right.getProfile())) {
      res = false;
      System.out.println("Definitions differ @"+path+": profile = "+left.getProfile()+" vs "+right.getProfile());
    }
    if (!stringsMatch(left.getParams().toString(), right.getParams().toString())) {
      res = false;
      System.out.println("Definitions differ @"+path+": params = "+left.getParams()+" vs "+right.getParams());
    }
    return res;
  }

  private static boolean compareBindings(String path, BindingSpecification left, BindingSpecification right) {
    boolean res = true;
    if (!stringsMatch(left.getName(), right.getName())) {
      res = false;
      System.out.println("Definitions differ @"+path+": name = "+left.getName()+" vs "+right.getName());
    }
    
    if (!stringsMatch(left.getDescription(), right.getDescription())) {
      res = false;
      System.out.println("Definitions differ @"+path+": description = "+left.getDescription()+" vs "+right.getDescription());
    }
//    
//    if (!stringsMatch(left.getReference(), right.getReference())) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": reference = "+left.getReference()+" vs "+right.getReference());
//    }
//    
//    if (!stringsMatch(left.getMaxReference(), right.getMaxReference())) {
//      res = false;
//      System.out.println("Definitions differ @"+path+": definition = "+left.getMaxReference()+" vs "+right.getMaxReference());
//    }

    if (!stringsMatch(left.getUri(), right.getUri())) {
      res = false;
      System.out.println("Definitions differ @"+path+": uri = "+left.getUri()+" vs "+right.getUri());
    }
    
    if (!stringsMatch(left.getWebSite(), right.getWebSite())) {
      res = false;
      System.out.println("Definitions differ @"+path+": webSite = "+left.getWebSite()+" vs "+right.getWebSite());
    }
    
    if (!stringsMatch(left.getEmail(), right.getEmail())) {
      res = false;
      System.out.println("Definitions differ @"+path+": email = "+left.getEmail()+" vs "+right.getEmail());
    }
    
    if (!stringsMatch(left.getCopyright(), right.getCopyright())) {
      res = false;
      System.out.println("Definitions differ @"+path+": copyright = "+left.getCopyright()+" vs "+right.getCopyright());
    }
    
    if (!stringsMatch(left.getCsOid(), right.getCsOid())) {
      res = false;
      System.out.println("Definitions differ @"+path+": csOid = "+left.getCsOid()+" vs "+right.getCsOid());
    }

    if (!stringsMatch(left.getVsOid(), right.getVsOid())) {
      res = false;
      System.out.println("Definitions differ @"+path+": vsOid = "+left.getVsOid()+" vs "+right.getVsOid());
    }

    
    if (left.getStrength() != right.getStrength()) {
      res = false;
      System.out.println("Definitions differ @"+path+": strength = "+left.getStrength().toCode()+" vs "+right.getStrength().toCode());
    }
       
    
    return res;
  }

  private static boolean integersMatch(Integer left, Integer right) {
    if ((left == null || left == 0) && (right == null || right == 0)) {
      return true;
    } else {
      return left.intValue() == right.intValue();
    }
  }

  private static boolean booleansMatch(Boolean left, Boolean right) {
    if ((left == null || !left) && (right == null || !right)) {
      return true;
    } else if (left == null || right == null) {
      return false;
    } else {
      return left && right;
    }
  }

  private static boolean stringsMatch(String left, String right) {
    if (left == null) {
      return right == null;
    } else {
      return (Utilities.noString(left) && Utilities.noString(right)) ||  left.equals(right);
    }
  }
  
  private static boolean markdownMatches(String left, String right) {
    if (Utilities.noString(left)) {
      return Utilities.noString(right);
    } else if (Utilities.noString(right)) {
      return false;
    } else {
      String l = left.contains("[") ? left.substring(0, left.indexOf("[")) : left;
      String r = right.contains("[") ? right.substring(0, right.indexOf("[")) : right;
      l = l.contains("|") ? l.substring(0, l.indexOf("|")) : l;
      r = r.contains("|") ? r.substring(0, r.indexOf("|")) : r;
      l = l.contains("\n") ? l.substring(0, l.indexOf("\n")) : l;
      r = r.contains("\n") ? r.substring(0, r.indexOf("\n")) : r;
      return l.trim().equals(r.trim());
    }
  }
  
}
