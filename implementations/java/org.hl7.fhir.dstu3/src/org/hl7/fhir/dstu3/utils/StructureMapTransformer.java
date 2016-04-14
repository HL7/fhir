package org.hl7.fhir.dstu3.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.PathEngineException;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ExpressionNode;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.PrimitiveType;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleDependentComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleSourceComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleTargetComponent;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.ITransformerServices;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.TransformContext;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.Variable;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.VariableMode;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.Variables;

public class StructureMapTransformer {

  public enum VariableMode {
    INPUT, OUTPUT
  }

  public class Variable {
    private VariableMode mode;
    private String name;
    private Base object;
    public Variable(VariableMode mode, String name, Base object) {
      super();
      this.mode = mode;
      this.name = name;
      this.object = object;
    }
    public VariableMode getMode() {
      return mode;
    }
    public String getName() {
      return name;
    }
    public Base getObject() {
      return object;
    }
    
  }

  public class Variables {
    private List<Variable> list = new ArrayList<Variable>();

    public void add(VariableMode mode, String name, Base object) {
      Variable vv = null;
      for (Variable v : list) 
        if ((v.mode == mode) && v.getName().equals(name))
          vv = v;
      if (vv != null)
        list.remove(vv);
      list.add(new Variable(mode, name, object));
    }

    public Variables copy() {
      Variables result = new Variables();
      result.list.addAll(list);
      return result;
    }

    public Base get(VariableMode mode, String name) {
      for (Variable v : list) 
        if ((v.mode == mode) && v.getName().equals(name))
          return v.getObject();
      return null;
    }
  }

  public class TransformContext {
    private Object appInfo;

    public TransformContext(Object appInfo) {
      super();
      this.appInfo = appInfo;
    }

    public Object getAppInfo() {
      return appInfo;
    }
    
  }

  public interface ITransformerServices {
    public boolean validateByValueSet(Coding code, String valuesetId);
//    public Coding translate(Coding code)
//    ValueSet validation operation
//    Translation operation
//    Lookup another tree of data
//    Create an instance tree
//    Return the correct string format to refer to a tree (input or output)

  }
  private Map<String, StructureMap> library;
  private ITransformerServices services;
  private IWorkerContext context;
  private FHIRPathEngine fpe;
  
  private void log(String cnt) {
  	System.out.println(cnt);
  }
  
  public StructureMapTransformer(IWorkerContext context, Map<String, StructureMap> library, ITransformerServices services) {
    super();
    this.context = context;
    this.library = library;
    this.services = services;
    fpe = new FHIRPathEngine(context);
  }

  /**
   * Given an item, return all the children that conform to the pattern described in name
   * 
   * Possible patterns:
   *  - a simple name (which may be the base of a name with [] e.g. value[x])
   *  - a name with a type replacement e.g. valueCodeableConcept
   *  - * which means all children
   *  - ** which means all descendents
   *  
   * @param item
   * @param name
   * @param result
   * @throws FHIRException 
   */
  protected void getChildrenByName(Base item, String name, List<Base> result) throws FHIRException {
    for (Base v : item.listChildrenByName(name, true))
      if (v != null)
        result.add(v);
  }

  public Base transform(Base source, StructureMap map) {
    return null;
  }
  
  public void transform(Object appInfo, Base source, StructureMap map, Base target) throws Exception {
    TransformContext context = new TransformContext(appInfo);
    Variables vars = new Variables();
    vars.add(VariableMode.INPUT, "src", source);
    vars.add(VariableMode.OUTPUT, "tgt", target);
    
    executeGroup("", context, vars, map.getGroup().get(0));
  }

  private void executeGroup(String indent, TransformContext context, Variables vars, StructureMapGroupComponent group) throws Exception {
  	log(indent+"Group : "+group.getName());
    // todo: extends
    // todo: check inputs
    for (StructureMapGroupRuleComponent r : group.getRule()) {
      executeRule(indent+"  ", context, vars, r);
    }
  }

  private void executeRule(String indent, TransformContext context, Variables vars, StructureMapGroupRuleComponent rule) throws Exception {
  	log(indent+"rule : "+rule.getName());
    Variables srcVars = vars.copy();
    if (rule.getSource().size() != 1)
      throw new Exception("not handled yet");
    List<Variables> source = analyseSource(context, srcVars, rule.getSource().get(0)); 
    for (Variables v : source) {
      for (StructureMapGroupRuleTargetComponent t : rule.getTarget()) {
        processTarget(context, v, t);
      }
      if (rule.hasRule()) {
      	for (StructureMapGroupRuleComponent childrule : rule.getRule()) {
      		executeRule(indent +"  ", context, v, childrule);
      	}
      }
    }
  }
  
  private List<Variables> analyseSource(TransformContext context, Variables vars, StructureMapGroupRuleSourceComponent src) throws Exception {
    List<Base> sources = new ArrayList<Base>();
    List<Base> items = new ArrayList<Base>();
    Base b = vars.get(VariableMode.INPUT, src.getContext());
    if (b == null)
    	throw new FHIRException("Unknown input variable "+src.getContext());
    if (!src.hasElement()) 
      items.add(b);
    else 
      getChildrenByName(b, src.getElement(), items);
    if (src.hasCondition()) {
      ExpressionNode expr = (ExpressionNode) src.getUserData(StructureMapCompiler.MAP_WHERE_EXPRESSION);
      if (expr == null) {
        expr = fpe.parse(src.getCondition());
//        fpe.check(context.appInfo, ??, ??, expr)
        src.setUserData(StructureMapCompiler.MAP_WHERE_EXPRESSION, expr);
      }
      for (Base item : items)
        if (fpe.evaluateToBoolean(null, item, expr))
          sources.add(item);
    } else
      sources.addAll(items);
    if (src.hasCheck()) {
      ExpressionNode expr = (ExpressionNode) src.getUserData(StructureMapCompiler.MAP_WHERE_CHECK);
      if (expr == null) {
        expr = fpe.parse(src.getCondition());
//        fpe.check(context.appInfo, ??, ??, expr)
        src.setUserData(StructureMapCompiler.MAP_WHERE_CHECK, expr);
      }
      for (Base item : sources)
        if (!fpe.evaluateToBoolean(null, item, expr))
          throw new Exception("Check condition failed");
    } 
    List<Variables> result = new ArrayList<Variables>();
    for (Base r : sources) {
      Variables v = vars.copy();
      if (src.hasVariable())
        v.add(VariableMode.INPUT, src.getVariable(), r);
      result.add(v); 
    }
    return result;
  }
  
  
  private void processTarget(TransformContext context, Variables vars, StructureMapGroupRuleTargetComponent tgt) throws Exception {
    Base dest = vars.get(VariableMode.OUTPUT, tgt.getContext());
    if (dest == null)
    	throw new Exception("target context not known: "+tgt.getContext());
    if (!tgt.hasElement())
      throw new Exception("Not supported yet");
    Base v = null;
    if (tgt.hasTransform()) {
      v = runTransform(tgt, vars);
      dest.setProperty(tgt.getElement().hashCode(), v);
    } else 
    	v = dest.makeProperty(tgt.getElement().hashCode());
    if (tgt.hasVariable())
      vars.add(VariableMode.OUTPUT, tgt.getVariable(), v);
  }

  private Base runTransform(StructureMapGroupRuleTargetComponent tgt, Variables vars) throws FHIRException {
    switch (tgt.getTransform()) {
    case CREATE :
      return ResourceFactory.createResource( tgt.getParameter().get(0).getValueStringType().asStringValue());
    case COPY : 
    	Type p = tgt.getParameter().get(0).getValue();
    	if (!(p instanceof IdType))
    		return p;
    	else 
    		return vars.get(VariableMode.INPUT, ((IdType) p).asStringValue());
    case EVALUATE :
			ExpressionNode expr = (ExpressionNode) tgt.getUserData(StructureMapCompiler.MAP_EXPRESSION);
			if (expr == null) {
				expr = fpe.parse(((StringType) tgt.getParameter().get(1).getValue()).asStringValue());
				tgt.setUserData(StructureMapCompiler.MAP_WHERE_EXPRESSION, expr);
			}
			List<Base> v = fpe.evaluate(null, null, vars.get(VariableMode.INPUT, ((IdType) tgt.getParameter().get(0).getValue()).asStringValue()), expr);
			if (v.size() != 1)
				throw new FHIRException("evaluation of "+expr.toString()+" returned "+Integer.toString(v.size())+" objects");
			return v.get(0);
    	
    case TRUNCATE : 
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case ESCAPE : 
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case CAST :
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case APPEND : 
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case TRANSLATE : 
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case REFERENCE :
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case DATEOP :
      throw new Error("Transform "+tgt.getTransform().toCode()+" not supported yet");
    case UUID :
      return new IdType(UUID.randomUUID().toString());
    case POINTER :
      Base b = vars.get(VariableMode.OUTPUT, ((PrimitiveType) tgt.getParameter().get(0).getValue()).asStringValue());
      if (b instanceof Resource)
      	return new UriType("urn:uuid:"+((Resource) b).getId());
      else
      	throw new FHIRException("Transform engine cannot point at an element of type "+b.fhirType());
    default:
      throw new Error("Transform Unknown: "+tgt.getTransform().toCode());
    }
  }
  
}
