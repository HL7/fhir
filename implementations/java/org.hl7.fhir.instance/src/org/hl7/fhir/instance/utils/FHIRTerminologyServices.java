package org.hl7.fhir.instance.utils;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Parameters;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.utilities.Utilities;

public class FHIRTerminologyServices implements ITerminologyServices {

	private IFHIRClient client;
	private Conformance conformance;
	private Map<String, ValidationResult> validateCodeCache = new HashMap<String, ITerminologyServices.ValidationResult>();
	
	
	public FHIRTerminologyServices(String url) throws Exception {
	  super();
	  client = new FHIRSimpleClient();
	  client.initialize(url);
	  conformance = client.getConformanceStatement();
  }

	@Override
  public boolean supportsSystem(String system) {
		// is it listed in the conformance statement? 
		List<Extension> extensions = ToolingExtensions.getExtensions(conformance, "http://hl7.org/fhir/StructureDefinition/supported-system");
		for (Extension t : extensions)
			if (((PrimitiveType) t.getValue()).getValueAsString().equals(system))
			  return true;
		
		// no? then see if there's a value set declaring it 
		Map<String, String> params = new HashMap<String, String>();
		params.put("system", system);
		return client.search(ValueSet.class, params).getTotal() > 0;
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
		throw new Error("Not done yet");
  }

	@Override
  public boolean checkVS(ConceptSetComponent vsi, String system, String code) {
		throw new Error("Not done yet");
  }

	@Override
  public boolean verifiesSystem(String system) {
		return supportsSystem(system);
  }

}
