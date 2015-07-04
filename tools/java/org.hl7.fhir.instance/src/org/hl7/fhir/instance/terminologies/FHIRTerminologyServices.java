package org.hl7.fhir.instance.terminologies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Parameters;
import org.hl7.fhir.instance.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;

public class FHIRTerminologyServices implements ITerminologyServices {

	private IFHIRClient client;
	private Conformance conformance;
	private Map<String, ValidationResult> validateCodeCache = new HashMap<String, ITerminologyServices.ValidationResult>();
	private Map<String, Boolean> supportedSystems = new HashMap<String, Boolean>();
	private Map<String, ValueSetExpansionComponent> expansionCache = new HashMap<String, ValueSetExpansionComponent>();
	
	
	public FHIRTerminologyServices(String url) throws Exception {
	  super();
	  client = new FHIRSimpleClient();
	  client.initialize(url);
	  conformance = client.getConformanceStatement();
  }

	@Override
  public boolean supportsSystem(String system) {
		if (supportedSystems.containsKey(system))
			return supportedSystems.get(system);
		
		// is it listed in the conformance statement? 
		List<Extension> extensions = ToolingExtensions.getExtensions(conformance, "http://hl7.org/fhir/StructureDefinition/supported-system");
		for (Extension t : extensions)
			if (((PrimitiveType) t.getValue()).getValueAsString().equals(system))
			  return true;
		
		// no? then see if there's a value set declaring it 
		Map<String, String> params = new HashMap<String, String>();
		params.put("system", system);
		boolean ok = client.search(ValueSet.class, params).getTotal() > 0;
		supportedSystems.put(system, ok);
		return ok;
  }

	@Override
  public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
		return new ConceptDefinitionComponent().setDefinition("test definition");
  }

	@Override
  public ValidationResult validateCode(String system, String code, String display)  {
		if (validateCodeCache.containsKey(system+"|"+code+"|"+display))
			return validateCodeCache.get(system+"|"+code+"|"+display);
		Parameters p_in = new Parameters();
		p_in.addParameter().setName("system").setValue(new UriType(system));
		p_in.addParameter().setName("code").setValue(new CodeType(code));
		if (!Utilities.noString(display))
		p_in.addParameter().setName("display").setValue(new StringType(display));
		p_in.addParameter().setName("identifier").setValue(new UriType("http://www.healthintersections.com.au/fhir/ValueSet/anything"));
		Parameters p_out = client.operateType(ValueSet.class, "validate", p_in);
		boolean ok = false;
		String msg = "No message from server";
		for (ParametersParameterComponent p : p_out.getParameter()) {
			if (p.getName().equals("result"))
				ok = ((BooleanType) p.getValue()).getValue();
			else if (p.getName().equals("message"))
				msg = ((StringType) p.getValue()).getValue();
		}
		if (ok)
			validateCodeCache .put(system+"|"+code+"|"+display, null);
		else
			validateCodeCache.put(system+"|"+code+"|"+display,  new ValidationResult(IssueSeverity.ERROR, msg));
		return validateCodeCache.get(system+"|"+code+"|"+display);
  }

  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc) throws Exception {
		String key = keyFor(inc);
		if (expansionCache.containsKey(key))
			// return expansionCache.get(key);
		  throw new Error("Hey - why are you here?");
		
		Parameters p_in = new Parameters();
		ValueSet vs = new ValueSet();
		vs.setId(UUID.randomUUID().toString());
		vs.setCompose(new ValueSetComposeComponent());
		vs.getCompose().addInclude(inc);
		p_in.addParameter().setName("valueset").setResource(vs);
		Parameters p_out = client.operateType(ValueSet.class, "expand", p_in);
		boolean ok = false;
		for (ParametersParameterComponent p : p_out.getParameter()) {
			if (p.getName().equals("return")) {
				expansionCache.put(key, ((ValueSet) p.getResource()).getExpansion());
				return ((ValueSet) p.getResource()).getExpansion();
			}
		}
		throw new Exception("Expansion failed");
  }

	private String keyFor(ConceptSetComponent inc) {
	  StringBuilder b = new StringBuilder();
	  b.append(inc.getSystem());
	  b.append("|");
	  b.append(inc.getVersion());
	  b.append("|");
	  for (ConceptReferenceComponent cc : inc.getConcept()) {
		  b.append(cc.getCode());
		  b.append("-");
		  b.append(cc.getDisplay());
		  b.append("|");
	  }
	  for (ConceptSetFilterComponent ff : inc.getFilter()) {
		  b.append(ff.getProperty());
		  b.append(ff.getOp());
		  b.append(ff.getValue());
		  b.append("|");
	  }
	  return b.toString();
  }

	@Override
  public boolean checkVS(ConceptSetComponent vsi, String system, String code) {
		throw new Error("Not done yet");
  }

	@Override
  public boolean verifiesSystem(String system) {
		return supportsSystem(system);
  }

  @Override
  public ValueSetExpansionOutcome expand(ValueSet vs) {
    try {
      Parameters p_in = new Parameters();
      p_in.addParameter().setName("valueset").setResource(vs);
      Parameters p_out = client.operateType(ValueSet.class, "expand", p_in);
      boolean ok = false;
      for (ParametersParameterComponent p : p_out.getParameter()) {
        if (p.getName().equals("return")) {
          return new ValueSetExpansionOutcome(((ValueSet) p.getResource()));
        }
      }
      return new ValueSetExpansionOutcome("No value set returned");
    } catch (Exception e) {
      return new ValueSetExpansionOutcome(e.getMessage());
    }
  }

}
