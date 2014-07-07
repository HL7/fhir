package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ResourceProfileStatus;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Questionnaire.GroupComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionnaireStatus;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.TerminologyServices;



/*
  Copyright (c) 2011-2014, HL7, Inc.
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


/**
 * This class takes a profile, and builds a questionnaire from it
 * 
 * If you then convert this questionnaire to a form using the 
 * XMLTools form builder, and then take the QuestionnaireAnswers 
 * this creates, you can use QuestionnaireInstanceConvert to 
 * build an instance the conforms to the profile
 *  
 * FHIR context: 
 *   conceptLocator, codeSystems, valueSets, maps, client, profiles
 * You don't have to provide any of these, but 
 * the more you provide, the better the conversion will be
 * 
 * @author Grahame
 *
 */
public class QuestionnaireBuilder {

	/**
	 * Given a profile, build a questionnaire. 
	 * The profile must have a single structure in it
	 *  
	 * @param profile the profile to build a questionnaire from
	 * @return the questionnaire that represents the profile
	 * @throws Exception 
	 */
	public Questionnaire buildQuestionnaire(Profile profile) throws Exception {
		if (profile.getStructure().isEmpty())
			throw new Exception("buildQuestionnaire: no sturcture found");
		if (profile.getStructure().size() != 1)
			throw new Exception("buildQuestionnaire: if there is more than one structure in the profile, you must choose one");
		return buildQuestionnaire(profile, profile.getStructure().get(0)); 
	}
	
	/**
	 * Given a profile with a selected structure, build a questionnaire. 
	 *  
	 * @param profile the profile to build a questionnaire from
	 * @return the questionnaire that represents the profile
	 * @throws Exception 
	 */
	private Questionnaire buildQuestionnaire(Profile profile, ProfileStructureComponent structure) throws Exception {
	  if (!profile.getStructure().contains(structure))
			throw new Exception("buildQuestionnaire: profile/structure mismatch");
		if (structure.getSnapshot().getElement().get(0).getDefinition() == null)
			throw new Exception("Found an element with no definition generating a Questionnaire");
	  Questionnaire result = new Questionnaire();
	  processMetadata(result, profile, structure);
	  buildGroup(result.getGroup(), profile, structure, structure.getSnapshot().getElement().get(0), new ArrayList<ElementComponent>());
	  if (profiles != null) {
	  	NarrativeGenerator ngen = new NarrativeGenerator("", conceptLocator, codeSystems, valueSets, maps, profiles, client);
	  	ngen.generate(result);
	  }
	  return result;
  }

	private void processMetadata(Questionnaire result, Profile profile, ProfileStructureComponent structure) {
		// todo: can we derive a more informative identifier from the questionnaire if we have a profile> 
		Identifier id = new Identifier();
		id.setSystemSimple("urn:ietf:rfc:3986");
		if (profile.getIdentifier() != null && profile.getUrlSimple().contains("/profile/"))
				id.setValueSimple(profile.getUrlSimple().replace("/profile/", "/questionnaire"));
		else
			id.setValueSimple("urn:uuid:"+UUID.randomUUID().toString().toLowerCase());
		result.setVersionSimple(profile.getVersionSimple());
		result.setStatusSimple(convertStatus(profile.getStatusSimple()));
		result.setDateSimple(profile.getDateSimple());
		result.setPublisherSimple(profile.getPublisherSimple());
		result.setGroup(new GroupComponent());
		for (Coding t : profile.getCode())
			result.getGroup().getConcept().add(t);
  }

	private QuestionnaireStatus convertStatus(ResourceProfileStatus status) {
		switch (status) {
		case active: return QuestionnaireStatus.published;
		case draft: return QuestionnaireStatus.draft;
		case retired : return QuestionnaireStatus.retired;
		default:
		  return null;
		}
	}

	// a group has been created. We have to fill it out based on the provided element
	// profile and structure are in context so that we can resolve references
	private void buildGroup(GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, List<ElementComponent> parents) throws Exception {
		
	  group.setLinkIdSimple(element.getPathSimple()); // todo: this will be wrong when we start slicing
	  group.setTitleSimple(element.getDefinition().getShortSimple()); // todo - may need to prepend the name tail... 
	  group.setTextSimple(element.getDefinition().getFormalSimple());
	  group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
	  group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));

	  // now, we iterate the children
	  for (ElementComponent child : ProfileUtilities.getChildList(structure, element)) {
	  	// if the element as a type, we add a question. else we add a group on the basis that 
	  	// it will have children of it's own 
			if (child.getDefinition() == null)
				throw new Exception("Found an element with no definition generating a Questionnaire");
	  	
			if (!isExempt(element, child) && !parents.contains(child)) {
				List<ElementComponent> nparents = new ArrayList<ElementComponent>();
				nparents.addAll(parents);
				nparents.add(child);
				GroupComponent childGroup = group.addGroup();
				if (child.getDefinition().getType().isEmpty() ) {
					buildGroup(childGroup, profile, structure, child, nparents);
				} else {
					buildQuestion(childGroup, profile, structure, child);
				}
			}
	  }
  }

  private String tail(String path) {
    return path.substring(path.lastIndexOf(".")+1);
  }

	private boolean isExempt(ElementComponent element, ElementComponent child) {
		String name = tail(child.getPathSimple());
		String type = element.getDefinition().getType().isEmpty() ? "" : element.getDefinition().getType().get(0).getCodeSimple();
		
	  // we don't generate questions for the base stuff in every element
		if ("Resource".equals(type) && 
				(name.equals("text") || name.equals("language") || name.equals("contained")))
				return true;
		
		// we don't generate questions for extensions
		if (name.equals("extension") || name.equals("modifierExtension") )
			return true;
		
	  return false;
  }

	// most of the types are complex in regard to the Questionnaire, so they are still groups
	// there will be questions for each component
	private void buildQuestion(GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element) throws Exception {
    group.setLinkIdSimple(element.getPathSimple());
    // in this context, we don't have any concepts to mark...
    group.setTextSimple(element.getDefinition().getShortSimple()); // prefix with name?
    group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
    group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));

    if (element.getDefinition().getType().size() == 1) {
      //    	throw new Exception("Multiple types not handled yet");

      // no we have to build the question panel for each different data type
      TypeRefComponent type = element.getDefinition().getType().get(0);
      if (type.getCodeSimple().equals("*"))
        return;
      
      if (type.getCodeSimple().equals("code"))
        addCodeQuestions(group, element);
      else if (type.getCodeSimple().equals("string") || type.getCodeSimple().equals("id") || type.getCodeSimple().equals("oid"))
        addStringQuestions(group, element);
      else if (type.getCodeSimple().equals("uri"))
        addUriQuestions(group, element);
      else if (type.getCodeSimple().equals("boolean"))
        addBooleanQuestions(group, element);
      else if (type.getCodeSimple().equals("decimal"))
        addDecimalQuestions(group, element);
      else if (type.getCodeSimple().equals("dateTime") || type.getCodeSimple().equals("date"))
        addDateTimeQuestions(group, element);
      else if (type.getCodeSimple().equals("instant"))
        addInstantQuestions(group, element);
      else if (type.getCodeSimple().equals("CodeableConcept"))
        addCodeableConceptQuestions(group, element);
      else if (type.getCodeSimple().equals("Period"))
        addPeriodQuestions(group, element);
      else if (type.getCodeSimple().equals("Ratio"))
        addRatioQuestions(group, element);
      else if (type.getCodeSimple().equals("HumanName"))
        addHumanNameQuestions(group, element);
      else if (type.getCodeSimple().equals("Address"))
        addAddressQuestions(group, element);
      else if (type.getCodeSimple().equals("Contact"))
        addContactQuestions(group, element);
      else if (type.getCodeSimple().equals("Identifier"))
        addIdentifierQuestions(group, element);
      else if (type.getCodeSimple().equals("integer"))
        addIntegerQuestions(group, element);
      else if (type.getCodeSimple().equals("Coding"))
        addCodingQuestions(group, element);
      else if (type.getCodeSimple().equals("Quantity"))
        addQuantityQuestions(group, element);
      else if (type.getCodeSimple().equals("ResourceReference"))
        addReferenceQuestions(group, element);
      else if (type.getCodeSimple().equals("idref"))
        addIdRefQuestions(group, element);
      else if (type.getCodeSimple().equals("Duration"))
        addDurationQuestions(group, element);
      else if (type.getCodeSimple().equals("base64Binary"))
        addBinaryQuestions(group, element);
      else if (type.getCodeSimple().equals("Attachment"))
        addAttachmentQuestions(group, element);
      else if (type.getCodeSimple().equals("Range"))
        addRangeQuestions(group, element);
      else if (type.getCodeSimple().equals("Schedule"))
        addScheduleQuestions(group, element);
      else if (type.getCodeSimple().equals("Extension"))
        addExtensionQuestions(group, element);
      else 
        throw new Exception("Unhandled Data Type: "+type.getCodeSimple()+" on element "+element.getPathSimple());
    }
  }

	private void addExtensionQuestions(GroupComponent group, ElementComponent element) {
    // TODO Auto-generated method stub
    
  }

  private void addRangeQuestions(GroupComponent group, ElementComponent element) {
    // TODO Auto-generated method stub
    
  }

  private void addScheduleQuestions(GroupComponent group, ElementComponent element) {
    // TODO Auto-generated method stub
    
  }

  private void addDurationQuestions(GroupComponent group, ElementComponent element) {
    
  }

  private void addAttachmentQuestions(GroupComponent group, ElementComponent element) {
    
  }

  private void addBinaryQuestions(GroupComponent group, ElementComponent element) {
    // TODO Auto-generated method stub
    
  }

  private void addIdRefQuestions(GroupComponent group, ElementComponent element) {
	  
	}

	private void addCodeableConceptQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "text", "text:");		
		if (hasValueSet(element))
  		addQuestion(group, AnswerFormat.openchoice, "coding", "code:").setRepeatsSimple(true).setOptions(getValueSet(element));
		else
  		addQuestion(group, AnswerFormat.openchoice, "coding", "code:").setRepeatsSimple(true);
	}
	
	private void addCodingQuestions(GroupComponent group, ElementComponent element) {
		if (hasValueSet(element))
  		addQuestion(group, AnswerFormat.openchoice, "coding", "code:").setOptions(getValueSet(element));
		else
  		addQuestion(group, AnswerFormat.openchoice, "coding", "code:");
	}
	
	private void addPeriodQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.dateTime, "low", "start:");
		addQuestion(group, AnswerFormat.dateTime, "end", "end:");
	}
	
	private void addHumanNameQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "text", "text:");		
		addQuestion(group, AnswerFormat.string, "family", "family:").setRepeatsSimple(true);
		addQuestion(group, AnswerFormat.string, "given", "given:").setRepeatsSimple(true);
	}
	
	private void addAddressQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "text", "text:");		
		addQuestion(group, AnswerFormat.string, "line", "line:").setRepeatsSimple(true);
		addQuestion(group, AnswerFormat.string, "city", "city:");
		addQuestion(group, AnswerFormat.string, "state", "state:");
		addQuestion(group, AnswerFormat.string, "zip", "zip:");
		addQuestion(group, AnswerFormat.string, "country", "country:");
	}
	
	private void addContactQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.choice, "type", "type:").setOptions(new ResourceReference().setReferenceSimple("http://hl7.org/fhir/vs/contact-system"));		
		addQuestion(group, AnswerFormat.string, "value", "value:");
	}
	
	private void addQuantityQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.choice, "comparator", "comp:").setOptions(new ResourceReference().setReferenceSimple("http://hl7.org/fhir/vs/quantity-comparator"));		
		addQuestion(group, AnswerFormat.decimal, "value", "value:");
		addQuestion(group, AnswerFormat.string, "units", "units:");
	}
	
	private void addRatioQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.decimal, "value", "low:");
		addQuestion(group, AnswerFormat.decimal, "value", "high:");
		addQuestion(group, AnswerFormat.string, "units", "units:");
	}
	
	private void addIdentifierQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "label", "label:");
		addQuestion(group, AnswerFormat.string, "system", "system:");
		addQuestion(group, AnswerFormat.string, "value", "value:");
	}
	
	private void addReferenceQuestions(GroupComponent group, ElementComponent element) {
		// todo
		addQuestion(group, AnswerFormat.string, "reference", "url:");
	}
	
	private void addStringQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "string", "value");		
	}
	
	private void addDecimalQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.decimal, "string", "value");		
	}
	
	private void addIntegerQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.integer, "string", "value");		
	}
	
	private void addUriQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.string, "string", "url:");		
	}
	
	private void addDateTimeQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.dateTime, "dateTime", "date/time:");		
	}
	
	private void addInstantQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.instant, "instant", "value:");		
	}
	
	private void addBooleanQuestions(GroupComponent group, ElementComponent element) {
		addQuestion(group, AnswerFormat.boolean_, "boolean", "value");		
	}
	
	private void addCodeQuestions(GroupComponent group, ElementComponent element) {
		if (hasValueSet(element))
			addQuestion(group, AnswerFormat.string, "code", "code:");
		else 
			addQuestion(group, AnswerFormat.choice, "code", "code:").setOptions(getValueSet(element));
  }

	private ResourceReference getValueSet(ElementComponent element) {
	  if (element == null|| element.getDefinition() == null || element.getDefinition().getBinding() == null || element.getDefinition().getBinding().getReference() instanceof Uri)
	    return null;
	  else
	    return (ResourceReference) element.getDefinition().getBinding().getReference();
  }

	private boolean hasValueSet(ElementComponent element) {
	  return element.getDefinition().getBinding() == null || !(element.getDefinition().getBinding().getReference() instanceof ResourceReference);
  }

	private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String id, String name) {
	  QuestionComponent q = group.addQuestion();
	  q.setLinkIdSimple(id);
	  q.setTextSimple(name);
	  q.setTypeSimple(af);
	  q.setRequiredSimple(true);
	  q.setRepeatsSimple(false);
	  return q;
  }

	// FHIR context. You don't have to provide any of these, but 
	// the more you provide, the better the conversion will be
  private TerminologyServices conceptLocator;
  private Map<String, AtomEntry<ValueSet>> codeSystems;
  private Map<String, AtomEntry<ValueSet>> valueSets;
  private Map<String, AtomEntry<ConceptMap>> maps;
  private FHIRClient client;
  private Map<String, Profile> profiles;
	public TerminologyServices getConceptLocator() {
		return conceptLocator;
	}
	public void setConceptLocator(TerminologyServices conceptLocator) {
		this.conceptLocator = conceptLocator;
	}
	public Map<String, AtomEntry<ValueSet>> getCodeSystems() {
		return codeSystems;
	}
	public void setCodeSystems(Map<String, AtomEntry<ValueSet>> codeSystems) {
		this.codeSystems = codeSystems;
	}
	public Map<String, AtomEntry<ValueSet>> getValueSets() {
		return valueSets;
	}
	public void setValueSets(Map<String, AtomEntry<ValueSet>> valueSets) {
		this.valueSets = valueSets;
	}
	public Map<String, AtomEntry<ConceptMap>> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, AtomEntry<ConceptMap>> maps) {
		this.maps = maps;
	}
	public FHIRClient getClient() {
		return client;
	}
	public void setClient(FHIRClient client) {
		this.client = client;
	}
	public Map<String, Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(Map<String, Profile> profiles) {
		this.profiles = profiles;
	}

  
}
