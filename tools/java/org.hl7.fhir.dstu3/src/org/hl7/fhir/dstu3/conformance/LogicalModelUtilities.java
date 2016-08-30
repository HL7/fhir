package org.hl7.fhir.dstu3.conformance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.dstu3.model.ExpressionNode.CollectionStatus;
import org.hl7.fhir.dstu3.model.Extension;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.TypeDetails;
import org.hl7.fhir.dstu3.utils.FluentPathEngine;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.dstu3.utils.FluentPathEngine.IEvaluationContext;
import org.hl7.fhir.dstu3.utils.FluentPathEngine.IEvaluationContext.FunctionDetails;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;

public class LogicalModelUtilities implements IEvaluationContext {

  private IWorkerContext context;
  

  public LogicalModelUtilities(IWorkerContext context) {
    super();
    this.context = context;
  }

  /**
   * Given a logical model, with a Logical Mapping column, 
   * generate profiles consistent with it 
   * 
   * single threaded only
   * 
   * @param logical
   * @return
   */
  public List<StructureDefinition> generateProfiles(StructureDefinition logical) {
    return null;
  }
  
  private Map<String, String> codings = new HashMap<String, String>();
  
  // start at 0, and keep calling this with index++ until it returns null
  public Bundle generateExample(StructureDefinition logical, int index) throws Exception {
    processMappings(logical);
    
    // 1. process the data
    LogicalModelNode data = readExampleData(logical.getSnapshot().getElement(), index);
    if (data.isEmpty())
      return null;
    
    // 2. parse the expressions
    String key = getMappingKey(logical);

    Bundle bnd = new Bundle();
    bnd.setType(BundleType.COLLECTION);
    bnd.setId(UUID.randomUUID().toString().toLowerCase());
    bnd.getMeta().setLastUpdated(new Date());
    
    FluentPathEngine fp = new FluentPathEngine(context);
    fp.setHostServices(this);
    
    // first, look for data
    parseExpressions(fp, key, data);
//    executeExpressions(fp, mc, data);
    return bnd; 
  }

  private void processMappings(StructureDefinition logical) {
    for (StructureDefinitionMappingComponent m : logical.getMapping()) {
      if (isCodingSystem(m.getUri()))
        codings.put(m.getIdentity(), m.getUri());
    }
  }

  private boolean isCodingSystem(String uri) {
    return uri.equals("http://snomed.info/sct") || uri.equals("http://loinc.org") || uri.equals("http://cap.org/ecc");
  }

  private LogicalModelNode readExampleData(List<ElementDefinition> element, int index) throws Exception {
    Map<String, LogicalModelNode> map = new HashMap<String, LogicalModelNode>();
    LogicalModelNode root = null;
    for (ElementDefinition ed : element) {
      LogicalModelNode focus;
      if (root == null) { // first
        focus = new LogicalModelNode(ed);
        root = focus;
      } else {
        focus = new LogicalModelNode(ed);
        LogicalModelNode parent = map.get(ed.getPath().substring(0, ed.getPath().lastIndexOf(".")));
        if (parent.hasData())
          throw new DefinitionException("Cannot provide example data for elements that have children on "+parent.getDefinition().getPath());
        parent.getChildren().add(focus);
      }
      map.put(ed.getPath(), focus);
      Type data = getExample(ed, index);
      if (data != null)
        focus.setData(data);
    }
    return root;
  }

  private Type getExample(ElementDefinition ed, int index) {
    if (index == 0)
      return ed.getExample();
    else 
      for (Extension ex : ed.getExtension()) {
        if (ex.getUrl().equals("http://hl7.org/fhir/StructureDefinition/structuredefinition-example")) {
          String i = ((StringType) ToolingExtensions.getExtension(ex, "index").getValue()).getValue();
          if (Integer.parseInt(i) == index) {
            return ToolingExtensions.getExtension(ex, "exValue").getValue();
          }
        }
      }
    return null;
  }

  private void parseExpressions(FluentPathEngine fp, String key, LogicalModelNode node) throws Exception {
    node.setMapping(getLogicalMapping(node.getDefinition(), key));
    if (node.hasChildren())
      for (LogicalModelNode child : node.getChildren()) 
        parseExpressions(fp, key, child);
  }

  private String getMappingKey(StructureDefinition logical) throws Exception {
    for (StructureDefinitionMappingComponent m : logical.getMapping()) {
      if (m.getUri().equals("http://hl7.org/fhir/logical"))
        return m.getIdentity();
    }
    throw new DefinitionException("unable to find logical mappings");
  }

  private String getLogicalMapping(ElementDefinition ed, String key) {
    for (ElementDefinitionMappingComponent m : ed.getMapping()) {
      if (m.getIdentity().equals(key))
        return m.getMap();
    }
    return null;
  }

  private void executeExpressions(FluentPathEngine fp, LogicalModelNode node) throws Exception {
    if (node.isEmpty())
      return; // don't execute expressions if there's no data
    if (node.hasChildren())
      for (LogicalModelNode child : node.getChildren()) 
        executeExpressions(fp, child);
  }        

  @Override
  public Type resolveConstant(Object appContext, String name) {
    ElementDefinition ed = (ElementDefinition) appContext;
    if (name.equals("%map-codes")) {
      CodeableConcept cc = new CodeableConcept();
      cc.setText(ed.getShort());
      for (ElementDefinitionMappingComponent m : ed.getMapping()) {
        if (codings.containsKey(m.getIdentity())) {
          cc.addCoding().setSystem(codings.get(m.getIdentity())).setCode(m.getMap());
        }
      }
      return cc;
    }
    else
      throw new NotImplementedException("Not done yet ("+name+")");
  }

  @Override
  public TypeDetails resolveConstantType(Object appContext, String name) {
    if (name.equals("%map-codes"))
      return new TypeDetails(CollectionStatus.SINGLETON, "http://hl7.org/fhir/StructureDefinition/CodeableConcept");
    else
      throw new NotImplementedException("Not done yet ("+name+")");
  }

  @Override
  public boolean Log(String argument, List<Base> focus) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public FunctionDetails resolveFunction(String functionName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TypeDetails checkFunction(Object appInfo, String functionName, List<TypeDetails> parameters) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Base> executeFunction(Object appInfo, String functionName, List<List<Base>> parameters) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
