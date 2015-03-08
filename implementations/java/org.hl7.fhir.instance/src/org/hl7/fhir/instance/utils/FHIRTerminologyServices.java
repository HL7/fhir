package org.hl7.fhir.instance.utils;

import java.util.List;

import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;

public class FHIRTerminologyServices implements ITerminologyServices {

	private String url;
	
	
	public FHIRTerminologyServices(String url) {
	  super();
	  this.url = url;
  }

	@Override
  public boolean supportsSystem(String system) {
		throw new Error("Not done yet");
  }

	@Override
  public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
		return new ConceptDefinitionComponent().setDefinition("test definition");
  }

	@Override
  public ValidationResult validateCode(String system, String code, String display) {
		throw new Error("Not done yet");
  }

	@Override
  public List<ValueSetExpansionContainsComponent> expandVS(ConceptSetComponent inc) throws Exception {
		throw new Error("Not done yet");
  }

	@Override
  public boolean checkVS(ConceptSetComponent vsi, String system, String code) {
		throw new Error("Not done yet");
  }

	@Override
  public boolean verifiesSystem(String system) {
		throw new Error("Not done yet");
  }

}
