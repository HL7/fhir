package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ResourceProfileStatus;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Questionnaire.GroupComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionnaireStatus;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValuesetStatus;
import org.hl7.fhir.instance.utils.ProfileUtilities.ExtensionResult;
import org.hl7.fhir.instance.utils.ProfileUtilities.StrucResult;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ucum.DefinedUnit;



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
 * You don"t have to provide any of these, but 
 * the more you provide, the better the conversion will be
 * 
 * @author Grahame
 *
 */
public class QuestionnaireBuilder {

  private WorkerContext context;
    
	public QuestionnaireBuilder(WorkerContext context) {
    super();
    this.context = context;
  }

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
	  buildGroup(result, result.getGroup(), profile, structure, structure.getSnapshot().getElement().get(0), new ArrayList<ElementComponent>());
	  if (context != null) {
	  	NarrativeGenerator ngen = new NarrativeGenerator("", context);
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
	private void buildGroup(Questionnaire questionnaire, GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, List<ElementComponent> parents) throws Exception {
		
	  group.setLinkIdSimple(element.getPathSimple()); // todo: this will be wrong when we start slicing
	  group.setTitleSimple(element.getDefinition().getShortSimple()); // todo - may need to prepend the name tail... 
	  group.setTextSimple(element.getDefinition().getCommentsSimple());
	  ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());
	  group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
	  group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));

	  // now, we iterate the children
	  for (ElementComponent child : ProfileUtilities.getChildList(structure, element)) {
	  	// if the element as a type, we add a question. else we add a group on the basis that 
	  	// it will have children of it"s own 
			if (child.getDefinition() == null)
				throw new Exception("Found an element with no definition generating a Questionnaire");
	  	
			if (!isExempt(element, child) && !parents.contains(child)) {
				List<ElementComponent> nparents = new ArrayList<ElementComponent>();
				nparents.addAll(parents);
				nparents.add(child);
				GroupComponent childGroup = group.addGroup();
				if (child.getDefinition().getType().isEmpty() ) {
					buildGroup(questionnaire, childGroup, profile, structure, child, nparents);
				} else {
					buildQuestion(questionnaire, childGroup, profile, structure, child, child.getPathSimple());
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
		
	  // we don"t generate questions for the base stuff in every element
		if ("Resource".equals(type) && 
				(name.equals("text") || name.equals("language") || name.equals("contained")))
				return true;
		
		// we don"t generate questions for extensions
		if (name.equals("extension") || name.equals("modifierExtension")) {
		  if (!child.getDefinition().getType().isEmpty() && !Utilities.noString(child.getDefinition().getType().get(0).getProfileSimple()))
		    return false;
		  else
		    return true;
		}
		
	  return false;
  }

	private List<TypeRefComponent> expandTypeList(List<TypeRefComponent> types) {
	  List<TypeRefComponent> result = new ArrayList<Profile.TypeRefComponent>();
	  for (TypeRefComponent t : types) {
	    if (!Utilities.noString(t.getProfileSimple()))
	      result.add(t);
	    else if (t.getCodeSimple().equals("*")) {
	      result.add(new TypeRefComponent().setCodeSimple("integer"));
	      result.add(new TypeRefComponent().setCodeSimple("decimal"));
	      result.add(new TypeRefComponent().setCodeSimple("dateTime"));
	      result.add(new TypeRefComponent().setCodeSimple("date"));
	      result.add(new TypeRefComponent().setCodeSimple("instant"));
	      result.add(new TypeRefComponent().setCodeSimple("time"));
	      result.add(new TypeRefComponent().setCodeSimple("string"));
	      result.add(new TypeRefComponent().setCodeSimple("uri"));
	      result.add(new TypeRefComponent().setCodeSimple("boolean"));
	      result.add(new TypeRefComponent().setCodeSimple("Coding"));
	      result.add(new TypeRefComponent().setCodeSimple("CodeableConcept"));
	      result.add(new TypeRefComponent().setCodeSimple("Attachment"));
	      result.add(new TypeRefComponent().setCodeSimple("Identifier"));
	      result.add(new TypeRefComponent().setCodeSimple("Quantity"));
	      result.add(new TypeRefComponent().setCodeSimple("Range"));
	      result.add(new TypeRefComponent().setCodeSimple("Period"));
	      result.add(new TypeRefComponent().setCodeSimple("Ratio"));
	      result.add(new TypeRefComponent().setCodeSimple("HumanName"));
	      result.add(new TypeRefComponent().setCodeSimple("Address"));
	      result.add(new TypeRefComponent().setCodeSimple("Contact"));
	      result.add(new TypeRefComponent().setCodeSimple("Schedule"));
	      result.add(new TypeRefComponent().setCodeSimple("ResourceReference"));
	    } else
	      result.add(t);
	  }
	  return result;
	}
	
	private ResourceReference makeTypeList(Questionnaire questionnaire, Profile profile, List<TypeRefComponent> types, String path) {
    ValueSet vs = new ValueSet();
    vs.setNameSimple("Type options for "+path);
    vs.setDescriptionSimple(vs.getNameSimple());
	  vs.setStatusSimple(ValuesetStatus.active);
	  vs.setDefine(new ValueSetDefineComponent());
	  vs.getDefine().setSystemSimple(Utilities.makeUuidUrn());
	  for (TypeRefComponent t : types) {
	    ValueSetDefineConceptComponent cc = vs.getDefine().addConcept();
	    if (t.getCodeSimple().equals("ResourceReference") && (t.getProfileSimple() != null && t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/"))) { 
	      cc.setCodeSimple(t.getProfileSimple().substring(28));
	      cc.setDisplaySimple(cc.getCodeSimple());
	    } else {
	      StrucResult res = null;
	      if (!Utilities.noString(t.getProfileSimple())) 
	        res = new ProfileUtilities(context).getStructure(profile, t.getProfileSimple());
	      if (res != null) {  
	        cc.setCodeSimple(t.getProfileSimple());
	        cc.setDisplaySimple(res.getStructure().getNameSimple());
	      } else {
	        cc.setCodeSimple(t.getCodeSimple());
	        cc.setDisplaySimple(t.getCodeSimple());
	      }
	    }
	    t.setTag("code", cc.getCodeSimple());
	  }
	  vs.setXmlId(getNextId());
	  questionnaire.getContained().add(vs);

	  ResourceReference result = new ResourceReference();
	  result.setReferenceSimple("#"+vs.getXmlId());
	  return result;
	}
	
	int lastId = 0;
	private String getNextId() {
    lastId++;
    return "vs"+Integer.toString(lastId);
  }

  // most of the types are complex in regard to the Questionnaire, so they are still groups
	// there will be questions for each component
	private void buildQuestion(Questionnaire questionnaire, GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, String path) throws Exception {
    group.setLinkIdSimple(path);
    // in this context, we don"t have any concepts to mark...
    group.setTextSimple(element.getDefinition().getShortSimple()); // prefix with name?
    group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
    group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));

    if (Utilities.noString(element.getDefinition().getCommentsSimple()))
      ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple()+" "+element.getDefinition().getCommentsSimple());
    else
      ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());
    
    if (element.getDefinition().getType().size() > 1 || element.getDefinition().getType().get(0).getCodeSimple().equals("*")) {
      // todo
      QuestionComponent q = addQuestion(group, AnswerFormat.choice, element.getPathSimple(), "_type", "type");
      List<TypeRefComponent> types = expandTypeList(element.getDefinition().getType());
      q.setOptions(makeTypeList(questionnaire, profile, types, element.getPathSimple()));
      for (TypeRefComponent t : types) {
        GroupComponent sub = q.addGroup();
        sub.setLinkIdSimple(element.getPathSimple()+"._"+t.getTag("code"));
        sub.setTextSimple(t.getTag("code"));
        // always optional, never repeats
        processDataType(questionnaire, profile, sub, element, element.getPathSimple()+"._"+t.getTag("code"), t);
      }
    } else
      processDataType(questionnaire, profile, group, element, element.getPathSimple(), element.getDefinition().getType().get(0));
	}

	private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name) {
    QuestionComponent q = group.addQuestion();
    q.setLinkIdSimple(path+"."+id);
    q.setTextSimple(name);
    q.setTypeSimple(af);
    q.setRequiredSimple(false);
    q.setRepeatsSimple(false);
    return q;
  }
  
	
  private void processDataType(Questionnaire questionnaire, Profile profile, GroupComponent group, ElementComponent element, String path, TypeRefComponent type) throws Exception {
      if (type.getCodeSimple().equals("code"))
        addCodeQuestions(group, element, path);
      else if (type.getCodeSimple().equals("string") || type.getCodeSimple().equals("id") || type.getCodeSimple().equals("oid"))
        addStringQuestions(group, element, path);
      else if (type.getCodeSimple().equals("uri"))
        addUriQuestions(group, element, path);
      else if (type.getCodeSimple().equals("boolean"))
        addBooleanQuestions(group, element, path);
      else if (type.getCodeSimple().equals("decimal"))
        addDecimalQuestions(group, element, path);
      else if (type.getCodeSimple().equals("dateTime") || type.getCodeSimple().equals("date"))
        addDateTimeQuestions(group, element, path);
      else if (type.getCodeSimple().equals("instant"))
        addInstantQuestions(group, element, path);
      else if (type.getCodeSimple().equals("time"))
        addTimeQuestions(group, element, path);
      else if (type.getCodeSimple().equals("CodeableConcept"))
        addCodeableConceptQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Period"))
        addPeriodQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Ratio"))
        addRatioQuestions(group, element, path);
      else if (type.getCodeSimple().equals("HumanName"))
        addHumanNameQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Address"))
        addAddressQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Contact"))
        addContactQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Identifier"))
        addIdentifierQuestions(group, element, path);
      else if (type.getCodeSimple().equals("integer"))
        addIntegerQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Coding"))
        addCodingQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Quantity"))
        addQuantityQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Money"))
        addMoneyQuestions(group, element, path);
      else if (type.getCodeSimple().equals("ResourceReference"))
        addReferenceQuestions(group, element, path, type.getProfileSimple());
      else if (type.getCodeSimple().equals("idref"))
        addIdRefQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Duration"))
        addDurationQuestions(group, element, path);
      else if (type.getCodeSimple().equals("base64Binary"))
        addBinaryQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Attachment"))
        addAttachmentQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Age"))
        addAgeQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Range"))
        addRangeQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Schedule"))
        addScheduleQuestions(group, element, path);
      else if (type.getCodeSimple().equals("SampledData"))
        addSampledDataQuestions(group, element, path);
      else if (type.getCodeSimple().equals("Extension"))
        addExtensionQuestions(questionnaire, profile, group, element, path, type.getProfileSimple());
      else 
        throw new Exception("Unhandled Data Type: "+type.getCodeSimple()+" on element "+element.getPathSimple());
  }

	private ResourceReference getValueSet(ElementComponent element) {
	  if (element == null|| element.getDefinition() == null || element.getDefinition().getBinding() == null || element.getDefinition().getBinding().getReference() instanceof UriType)
	    return null;
	  else
	    return (ResourceReference) element.getDefinition().getBinding().getReference();
  }

	private boolean hasValueSet(ElementComponent element) {
	  return element.getDefinition().getBinding() != null && (element.getDefinition().getBinding().getReference() instanceof ResourceReference);
  }

  // Primitives ------------------------------------------------------------------
	private void addCodeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception	{
  ToolingExtensions.setQuestionType(group, "code");
	  QuestionComponent q;
	  if (hasValueSet(element)) {
	    q = addQuestion(group, AnswerFormat.choice, path, "value", unCamelCase(tail(element.getPathSimple())));
	    q.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
	  } else
	    q = addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
	  ;
	}

	private String unCamelCase(String s) {
    StringBuilder b = new StringBuilder();
    for (char ch: s.toCharArray()) {
      if (Character.isUpperCase(ch)) 
        b.append(' ');
      if (b.length() == 0)
        b.append(Character.toUpperCase(ch));
      else
        b.append(ch);
    }
    return b.toString();
  }

  private void addStringQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "string");
	  addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addTimeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "time");
	  addQuestion(group, AnswerFormat.time, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addUriQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "uri");
	  addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addBooleanQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "boolean");
	  addQuestion(group, AnswerFormat.boolean_, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addDecimalQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "decimal");
	  addQuestion(group, AnswerFormat.decimal, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addIntegerQuestions(GroupComponent group, ElementComponent element, String path)	throws Exception {
	  ToolingExtensions.setQuestionType(group, "integer");
	  addQuestion(group, AnswerFormat.integer, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addDateTimeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "datetime");
	  addQuestion(group, AnswerFormat.dateTime, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addInstantQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "instant");
	  addQuestion(group, AnswerFormat.instant, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addBinaryQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "binary");
	  // ? Lloyd: how to support binary content
	}

	// Complex Types ---------------------------------------------------------------

	private void addCodingQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Coding");

	  QuestionComponent q = addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "value", "Code:");
	  if (hasValueSet(element)) 
	    q.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
	}

  private AnswerFormat answerTypeForBinding(ElementDefinitionBindingComponent elementDefinitionBindingComponent) {
    if (elementDefinitionBindingComponent == null) 
      return AnswerFormat.openchoice;
    else if (elementDefinitionBindingComponent.getIsExtensibleSimple()) 
      return AnswerFormat.openchoice;
    else
      return AnswerFormat.choice;
  }

	private void addCodeableConceptQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "CodeableConcept");
	  if (hasValueSet(element)) {
	    QuestionComponent question = addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "coding", "code:");
	    question.setRepeatsSimple(true);
	    question.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
	  } else
	    addQuestion(group, AnswerFormat.openchoice, path, "coding", "code:").setRepeatsSimple(true);
	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
	}

	private void addPeriodQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Period");
	  addQuestion(group, AnswerFormat.dateTime, path, "low", "start:");
	  addQuestion(group, AnswerFormat.dateTime, path, "end", "end:");
	}

	private void addRatioQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Ratio");
	  addQuestion(group, AnswerFormat.decimal, path, "numerator", "numerator:");
	  addQuestion(group, AnswerFormat.decimal, path, "denominator", "denominator:");
	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
	}

	private void addHumanNameQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Name");
	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
	  addQuestion(group, AnswerFormat.string, path, "family", "family:").setRepeatsSimple(true);
	  addQuestion(group, AnswerFormat.string, path, "given", "given:").setRepeatsSimple(true);
	}

	private void addAddressQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Address");
	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
	  addQuestion(group, AnswerFormat.string, path, "line", "line:").setRepeatsSimple(true);
	  addQuestion(group, AnswerFormat.string, path, "city", "city:");
	  addQuestion(group, AnswerFormat.string, path, "state", "state:");
	  addQuestion(group, AnswerFormat.string, path, "zip", "zip:");
	  addQuestion(group, AnswerFormat.string, path, "country", "country:");
	}

	private void addContactQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Contact");
	  ResourceReference ref = new ResourceReference();
	  addQuestion(group, AnswerFormat.choice, path, "type", "type:").setOptions(ref);
	  ref.setReferenceSimple("http://hl7.org/fhir/vs/contact-system");
	  addQuestion(group, AnswerFormat.string, path, "value", "value:");
	}

	private void addIdentifierQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Identifier");
	  addQuestion(group, AnswerFormat.string, path, "label", "label:");
	  addQuestion(group, AnswerFormat.string, path, "system", "system:");
	  addQuestion(group, AnswerFormat.string, path, "value", "value:");
	}

	private void addQuantityQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Quantity");
    ResourceReference ref = new ResourceReference();
	  addQuestion(group, AnswerFormat.choice, path, "comparator", "comp:").setOptions(ref);
	  ref.setReferenceSimple("http://hl7.org/fhir/vs/quantity-comparator");
	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
	}

  private void addMoneyQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
    ToolingExtensions.setQuestionType(group, "Money");
    addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
    addQuestion(group, AnswerFormat.string, path, "currency", "currency:");
  }

	private void addAgeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Age");
    ResourceReference ref = new ResourceReference();
	  addQuestion(group, AnswerFormat.choice, path, "comparator", "comp:").setOptions(ref);
	  ref.setReferenceSimple("http://hl7.org/fhir/vs/quantity-comparator");
	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
    ref = new ResourceReference();
	  ref.setReferenceSimple("http://hl7.org/fhir/vs/duration-units");
	  addQuestion(group, AnswerFormat.choice, path, "units", "units:");
	}

	private void addDurationQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Duration");
	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
	}

	private void addAttachmentQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Attachment");
//	  raise Exception.Create("addAttachmentQuestions not Done Yet");
	}

	private void addRangeQuestions(GroupComponent group, ElementComponent element, String path)throws Exception  {
	  ToolingExtensions.setQuestionType(group, "Range");
	  addQuestion(group, AnswerFormat.decimal, path, "low", "low:");
	  addQuestion(group, AnswerFormat.decimal, path, "high", "high:");
	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
	}

	private void addSampledDataQuestions(GroupComponent group, ElementComponent element, String path)throws Exception  {
	  ToolingExtensions.setQuestionType(group, "SampledData");
	}

	private void addScheduleQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
	  ToolingExtensions.setQuestionType(group, "Schedule");
//	  raise Exception.Create("addScheduleQuestions not Done Yet");
	}

	// Special Types ---------------------------------------------------------------

	private void addReferenceQuestions(GroupComponent group, ElementComponent element, String path, String profileURL) throws Exception {
	  ToolingExtensions.setQuestionType(group, "ResourceReference");
	  String rn;
	  if (profileURL != null && profileURL.startsWith("http://hl7.org/fhir/Profile/"))
	    rn = profileURL.substring(28);
	  else
	    rn = "Any";
	  if (rn.equals("Any"))
     ToolingExtensions.setQuestionReference(group, "/_search?subject=$subj&patient=$subj&encounter=$encounter");
	  else
	    ToolingExtensions.setQuestionReference(group, "/"+rn+"?subject=$subj&patient=$subj&encounter=$encounter");
	  addQuestion(group, AnswerFormat.reference, path, "value", group.getTextSimple());
	  group.setText(null);
	}

	private void addIdRefQuestions(GroupComponent group, ElementComponent element, String path) {
//	  raise Exception.Create("not Done Yet");
	}

	private void addExtensionQuestions(Questionnaire questionnaire, Profile profile, GroupComponent group, ElementComponent element, String path, String profileURL) throws Exception {
	  // is this a  profiled extension, then we add it
	  ExtensionResult er = null;
	  if (!Utilities.noString(profileURL))
	    er = new ProfileUtilities(context).getExtensionDefn(profile, profileURL);
	    
	  if (er != null) {
	    if (er.getExtension().getElement().size() == 1)
	      buildQuestion(questionnaire, group, profile, null, er.getExtension().getElement().get(0), path+".extension["+profileURL+"]");
	    else
	      throw new Exception("Not done yet");
	  }
	}

	
}
