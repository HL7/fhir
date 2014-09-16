package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ResourceProfileStatus;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Questionnaire.GroupComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionnaireStatus;
import org.hl7.fhir.instance.model.QuestionnaireAnswers;
import org.hl7.fhir.instance.model.QuestionnaireAnswers.QuestionAnswerComponent;
import org.hl7.fhir.instance.model.QuestionnaireAnswers.QuestionnaireAnswersStatus;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.TimeType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.model.ValueSet.ValuesetStatus;
import org.hl7.fhir.instance.utils.ProfileUtilities.ExtensionResult;
import org.hl7.fhir.instance.utils.ProfileUtilities.StrucResult;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ucum.DefinedUnit;



/*
  Copyright (c) 2011+, HL7, Inc.
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
  private int lastid = 0;
  private Resource resource;
  private ProfileStructureComponent structure;
  private Profile profile;
  private Questionnaire questionnaire;
  private QuestionnaireAnswers answers;
  private String questionnaireId;
  private Factory factory = new Factory();
  private Map<String, String> vsCache = new HashMap<String, String>();

  // sometimes, when this is used, the questionnaire is already build and cached, and we are
  // processing the answers. for technical reasons, we still go through the process, but
  // we don't do the intensive parts of the work (save time)
  private Questionnaire prebuiltQuestionnaire;

  public QuestionnaireBuilder(WorkerContext context) {
    super();
    this.context = context;
  }

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public ProfileStructureComponent getStructure() {
    return structure;
  }

  public void setStructure(ProfileStructureComponent structure) {
    this.structure = structure;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Questionnaire getQuestionnaire() {
    return questionnaire;
  }

  public void setQuestionnaire(Questionnaire questionnaire) {
    this.questionnaire = questionnaire;
  }

  public QuestionnaireAnswers getAnswers() {
    return answers;
  }

  public void setAnswers(QuestionnaireAnswers answers) {
    this.answers = answers;
  }

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }

  public Questionnaire getPrebuiltQuestionnaire() {
    return prebuiltQuestionnaire;
  }

  public void setPrebuiltQuestionnaire(Questionnaire prebuiltQuestionnaire) {
    this.prebuiltQuestionnaire = prebuiltQuestionnaire;
  }


  //  /**
  //	 * Given a profile, build a questionnaire. 
  //	 * The profile must have a single structure in it
  //	 *  
  //	 * @param profile the profile to build a questionnaire from
  //	 * @return the questionnaire that represents the profile
  //	 * @throws Exception 
  //	 */
  //	public Questionnaire buildQuestionnaire(Profile profile) throws Exception {
  //		if (profile.getStructure().isEmpty())
  //			throw new Exception("buildQuestionnaire: no sturcture found");
  //		if (profile.getStructure().size() != 1)
  //			throw new Exception("buildQuestionnaire: if there is more than one structure in the profile, you must choose one");
  //		return buildQuestionnaire(profile, profile.getStructure().get(0)); 
  //	}
  //	
  //	/**
  //	 * Given a profile with a selected structure, build a questionnaire. 
  //	 *  
  //	 * @param profile the profile to build a questionnaire from
  //	 * @return the questionnaire that represents the profile
  //	 * @throws Exception 
  //	 */
  //	private Questionnaire buildQuestionnaire(Profile profile, ProfileStructureComponent structure) throws Exception {
  //	  if (!profile.getStructure().contains(structure))
  //			throw new Exception("buildQuestionnaire: profile/structure mismatch");
  //		if (structure.getSnapshot().getElement().get(0).getDefinition() == null)
  //			throw new Exception("Found an element with no definition generating a Questionnaire");
  //	  Questionnaire result = new Questionnaire();
  //	  processMetadata(result, profile, structure);
  //	  buildGroup(result, result.getGroup(), profile, structure, structure.getSnapshot().getElement().get(0), new ArrayList<ElementComponent>());
  //	  if (context != null) {
  //	  	NarrativeGenerator ngen = new NarrativeGenerator("", context);
  //	  	ngen.generate(result);
  //	  }
  //	  return result;
  //  }
  //
  //	private void processMetadata(Questionnaire result, Profile profile, ProfileStructureComponent structure) {
  //		// todo: can we derive a more informative identifier from the questionnaire if we have a profile> 
  //		Identifier id = new Identifier();
  //		id.setSystemSimple("urn:ietf:rfc:3986");
  //		if (profile.getIdentifier() != null && profile.getUrlSimple().contains("/profile/"))
  //				id.setValueSimple(profile.getUrlSimple().replace("/profile/", "/questionnaire"));
  //		else
  //			id.setValueSimple("urn:uuid:"+UUID.randomUUID().toString().toLowerCase());
  //		result.setVersionSimple(profile.getVersionSimple());
  //		result.setStatusSimple(convertStatus(profile.getStatusSimple()));
  //		result.setDateSimple(profile.getDateSimple());
  //		result.setPublisherSimple(profile.getPublisherSimple());
  //		result.setGroup(new GroupComponent());
  //		for (Coding t : profile.getCode())
  //			result.getGroup().getConcept().add(t);
  //  }
  //
  //	private QuestionnaireStatus convertStatus(ResourceProfileStatus status) {
  //		switch (status) {
  //		case active: return QuestionnaireStatus.published;
  //		case draft: return QuestionnaireStatus.draft;
  //		case retired : return QuestionnaireStatus.retired;
  //		default:
  //		  return null;
  //		}
  //	}
  //
  //	// a group has been created. We have to fill it out based on the provided element
  //	// profile and structure are in context so that we can resolve references
  //	private void buildGroup(Questionnaire questionnaire, GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, List<ElementComponent> parents) throws Exception {
  //		
  //	  group.setLinkIdSimple(element.getPathSimple()); // todo: this will be wrong when we start slicing
  //	  group.setTitleSimple(element.getDefinition().getShortSimple()); // todo - may need to prepend the name tail... 
  //	  group.setTextSimple(element.getDefinition().getCommentsSimple());
  //	  ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());
  //	  group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
  //	  group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));
  //
  //	  // now, we iterate the children
  //	  for (ElementComponent child : ProfileUtilities.getChildList(structure, element)) {
  //	  	// if the element as a type, we add a question. else we add a group on the basis that 
  //	  	// it will have children of it"s own 
  //			if (child.getDefinition() == null)
  //				throw new Exception("Found an element with no definition generating a Questionnaire");
  //	  	
  //			if (!isExempt(element, child) && !parents.contains(child)) {
  //				List<ElementComponent> nparents = new ArrayList<ElementComponent>();
  //				nparents.addAll(parents);
  //				nparents.add(child);
  //				GroupComponent childGroup = group.addGroup();
  //				if (child.getDefinition().getType().isEmpty() ) {
  //					buildGroup(questionnaire, childGroup, profile, structure, child, nparents);
  //				} else {
  //					buildQuestion(questionnaire, childGroup, profile, structure, child, child.getPathSimple());
  //				}
  //			}
  //	  }
  //  }
  //
  //  private String tail(String path) {
  //    return path.substring(path.lastIndexOf(".")+1);
  //  }
  //
  //	private boolean isExempt(ElementComponent element, ElementComponent child) {
  //		String name = tail(child.getPathSimple());
  //		String type = element.getDefinition().getType().isEmpty() ? "" : element.getDefinition().getType().get(0).getCodeSimple();
  //		
  //	  // we don"t generate questions for the base stuff in every element
  //		if ("Resource".equals(type) && 
  //				(name.equals("text") || name.equals("language") || name.equals("contained")))
  //				return true;
  //		
  //		// we don"t generate questions for extensions
  //		if (name.equals("extension") || name.equals("modifierExtension")) {
  //		  if (!child.getDefinition().getType().isEmpty() && !Utilities.noString(child.getDefinition().getType().get(0).getProfileSimple()))
  //		    return false;
  //		  else
  //		    return true;
  //		}
  //		
  //	  return false;
  //  }
  //
  //	private List<TypeRefComponent> expandTypeList(List<TypeRefComponent> types) {
  //	  List<TypeRefComponent> result = new ArrayList<Profile.TypeRefComponent>();
  //	  for (TypeRefComponent t : types) {
  //	    if (!Utilities.noString(t.getProfileSimple()))
  //	      result.add(t);
  //	    else if (t.getCodeSimple().equals("*")) {
  //	      result.add(new TypeRefComponent().setCodeSimple("integer"));
  //	      result.add(new TypeRefComponent().setCodeSimple("decimal"));
  //	      result.add(new TypeRefComponent().setCodeSimple("dateTime"));
  //	      result.add(new TypeRefComponent().setCodeSimple("date"));
  //	      result.add(new TypeRefComponent().setCodeSimple("instant"));
  //	      result.add(new TypeRefComponent().setCodeSimple("time"));
  //	      result.add(new TypeRefComponent().setCodeSimple("string"));
  //	      result.add(new TypeRefComponent().setCodeSimple("uri"));
  //	      result.add(new TypeRefComponent().setCodeSimple("boolean"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Coding"));
  //	      result.add(new TypeRefComponent().setCodeSimple("CodeableConcept"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Attachment"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Identifier"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Quantity"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Range"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Period"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Ratio"));
  //	      result.add(new TypeRefComponent().setCodeSimple("HumanName"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Address"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Contact"));
  //	      result.add(new TypeRefComponent().setCodeSimple("Schedule"));
  //	      result.add(new TypeRefComponent().setCodeSimple("ResourceReference"));
  //	    } else
  //	      result.add(t);
  //	  }
  //	  return result;
  //	}
  //	
  //	private ResourceReference makeTypeList(Questionnaire questionnaire, Profile profile, List<TypeRefComponent> types, String path) {
  //    ValueSet vs = new ValueSet();
  //    vs.setNameSimple("Type options for "+path);
  //    vs.setDescriptionSimple(vs.getNameSimple());
  //	  vs.setStatusSimple(ValuesetStatus.active);
  //	  vs.setDefine(new ValueSetDefineComponent());
  //	  vs.getDefine().setSystemSimple(Utilities.makeUuidUrn());
  //	  for (TypeRefComponent t : types) {
  //	    ValueSetDefineConceptComponent cc = vs.getDefine().addConcept();
  //	    if (t.getCodeSimple().equals("ResourceReference") && (t.getProfileSimple() != null && t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/"))) { 
  //	      cc.setCodeSimple(t.getProfileSimple().substring(28));
  //	      cc.setDisplaySimple(cc.getCodeSimple());
  //	    } else {
  //	      StrucResult res = null;
  //	      if (!Utilities.noString(t.getProfileSimple())) 
  //	        res = new ProfileUtilities(context).getStructure(profile, t.getProfileSimple());
  //	      if (res != null) {  
  //	        cc.setCodeSimple(t.getProfileSimple());
  //	        cc.setDisplaySimple(res.getStructure().getNameSimple());
  //	      } else {
  //	        cc.setCodeSimple(t.getCodeSimple());
  //	        cc.setDisplaySimple(t.getCodeSimple());
  //	      }
  //	    }
  //	    t.setTag("code", cc.getCodeSimple());
  //	  }
  //	  vs.setXmlId(getNextId());
  //	  questionnaire.getContained().add(vs);
  //
  //	  ResourceReference result = new ResourceReference();
  //	  result.setReferenceSimple("#"+vs.getXmlId());
  //	  return result;
  //	}
  //	
  //	int lastId = 0;
  //	private String getNextId() {
  //    lastId++;
  //    return "vs"+Integer.toString(lastId);
  //  }
  //
  //  // most of the types are complex in regard to the Questionnaire, so they are still groups
  //	// there will be questions for each component
  //	private void buildQuestion(Questionnaire questionnaire, GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, String path) throws Exception {
  //    group.setLinkIdSimple(path);
  //    // in this context, we don"t have any concepts to mark...
  //    group.setTextSimple(element.getDefinition().getShortSimple()); // prefix with name?
  //    group.setRequiredSimple(element.getDefinition().getMin().getValue() > 0);
  //    group.setRepeatsSimple(!element.getDefinition().getMax().getValue().equals("1"));
  //
  //    if (Utilities.noString(element.getDefinition().getCommentsSimple()))
  //      ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple()+" "+element.getDefinition().getCommentsSimple());
  //    else
  //      ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());
  //    
  //    if (element.getDefinition().getType().size() > 1 || element.getDefinition().getType().get(0).getCodeSimple().equals("*")) {
  //      // todo
  //      QuestionComponent q = addQuestion(group, AnswerFormat.choice, element.getPathSimple(), "_type", "type");
  //      List<TypeRefComponent> types = expandTypeList(element.getDefinition().getType());
  //      q.setOptions(makeTypeList(questionnaire, profile, types, element.getPathSimple()));
  //      for (TypeRefComponent t : types) {
  //        GroupComponent sub = q.addGroup();
  //        sub.setLinkIdSimple(element.getPathSimple()+"._"+t.getTag("code"));
  //        sub.setTextSimple(t.getTag("code"));
  //        // always optional, never repeats
  //        processDataType(questionnaire, profile, sub, element, element.getPathSimple()+"._"+t.getTag("code"), t);
  //      }
  //    } else
  //      processDataType(questionnaire, profile, group, element, element.getPathSimple(), element.getDefinition().getType().get(0));
  //	}
  //
  //	private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name) {
  //    QuestionComponent q = group.addQuestion();
  //    q.setLinkIdSimple(path+"."+id);
  //    q.setTextSimple(name);
  //    q.setTypeSimple(af);
  //    q.setRequiredSimple(false);
  //    q.setRepeatsSimple(false);
  //    return q;
  //  }
  //  
  //	
  //  private void processDataType(Questionnaire questionnaire, Profile profile, GroupComponent group, ElementComponent element, String path, TypeRefComponent type) throws Exception {
  //      if (type.getCodeSimple().equals("code"))
  //        addCodeQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("string") || type.getCodeSimple().equals("id") || type.getCodeSimple().equals("oid"))
  //        addStringQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("uri"))
  //        addUriQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("boolean"))
  //        addBooleanQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("decimal"))
  //        addDecimalQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("dateTime") || type.getCodeSimple().equals("date"))
  //        addDateTimeQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("instant"))
  //        addInstantQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("time"))
  //        addTimeQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("CodeableConcept"))
  //        addCodeableConceptQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Period"))
  //        addPeriodQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Ratio"))
  //        addRatioQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("HumanName"))
  //        addHumanNameQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Address"))
  //        addAddressQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Contact"))
  //        addContactQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Identifier"))
  //        addIdentifierQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("integer"))
  //        addIntegerQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Coding"))
  //        addCodingQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Quantity"))
  //        addQuantityQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Money"))
  //        addMoneyQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("ResourceReference"))
  //        addReferenceQuestions(group, element, path, type.getProfileSimple());
  //      else if (type.getCodeSimple().equals("idref"))
  //        addIdRefQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Duration"))
  //        addDurationQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("base64Binary"))
  //        addBinaryQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Attachment"))
  //        addAttachmentQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Age"))
  //        addAgeQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Range"))
  //        addRangeQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Schedule"))
  //        addScheduleQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("SampledData"))
  //        addSampledDataQuestions(group, element, path);
  //      else if (type.getCodeSimple().equals("Extension"))
  //        addExtensionQuestions(questionnaire, profile, group, element, path, type.getProfileSimple());
  //      else 
  //        throw new Exception("Unhandled Data Type: "+type.getCodeSimple()+" on element "+element.getPathSimple());
  //  }
  //
  //	private ResourceReference getValueSet(ElementComponent element) {
  //	  if (element == null|| element.getDefinition() == null || element.getDefinition().getBinding() == null || element.getDefinition().getBinding().getReference() instanceof UriType)
  //	    return null;
  //	  else
  //	    return (ResourceReference) element.getDefinition().getBinding().getReference();
  //  }
  //
  //	private boolean hasValueSet(ElementComponent element) {
  //	  return element.getDefinition().getBinding() != null && (element.getDefinition().getBinding().getReference() instanceof ResourceReference);
  //  }
  //
  //  // Primitives ------------------------------------------------------------------
  //	private void addCodeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception	{
  //  ToolingExtensions.setQuestionType(group, "code");
  //	  QuestionComponent q;
  //	  if (hasValueSet(element)) {
  //	    q = addQuestion(group, AnswerFormat.choice, path, "value", unCamelCase(tail(element.getPathSimple())));
  //	    q.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
  //	  } else
  //	    q = addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
  //	  ;
  //	}
  //
  //	private String unCamelCase(String s) {
  //    StringBuilder b = new StringBuilder();
  //    for (char ch: s.toCharArray()) {
  //      if (Character.isUpperCase(ch)) 
  //        b.append(' ');
  //      if (b.length() == 0)
  //        b.append(Character.toUpperCase(ch));
  //      else
  //        b.append(ch);
  //    }
  //    return b.toString();
  //  }
  //
  //  private void addStringQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "string");
  //	  addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addTimeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "time");
  //	  addQuestion(group, AnswerFormat.time, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addUriQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "uri");
  //	  addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addBooleanQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "boolean");
  //	  addQuestion(group, AnswerFormat.boolean_, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addDecimalQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "decimal");
  //	  addQuestion(group, AnswerFormat.decimal, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addIntegerQuestions(GroupComponent group, ElementComponent element, String path)	throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "integer");
  //	  addQuestion(group, AnswerFormat.integer, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addDateTimeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "datetime");
  //	  addQuestion(group, AnswerFormat.dateTime, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addInstantQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "instant");
  //	  addQuestion(group, AnswerFormat.instant, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addBinaryQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "binary");
  //	  // ? Lloyd: how to support binary content
  //	}
  //
  //	// Complex Types ---------------------------------------------------------------
  //
  //	private void addCodingQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Coding");
  //
  //	  QuestionComponent q = addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "value", "Code:");
  //	  if (hasValueSet(element)) 
  //	    q.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
  //	}
  //
  //  private AnswerFormat answerTypeForBinding(ElementDefinitionBindingComponent elementDefinitionBindingComponent) {
  //    if (elementDefinitionBindingComponent == null) 
  //      return AnswerFormat.openchoice;
  //    else if (elementDefinitionBindingComponent.getIsExtensibleSimple()) 
  //      return AnswerFormat.openchoice;
  //    else
  //      return AnswerFormat.choice;
  //  }
  //
  //	private void addCodeableConceptQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "CodeableConcept");
  //	  if (hasValueSet(element)) {
  //	    QuestionComponent question = addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "coding", "code:");
  //	    question.setRepeatsSimple(true);
  //	    question.setOptions((ResourceReference) element.getDefinition().getBinding().getReference());
  //	  } else
  //	    addQuestion(group, AnswerFormat.openchoice, path, "coding", "code:").setRepeatsSimple(true);
  //	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
  //	}
  //
  //	private void addPeriodQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Period");
  //	  addQuestion(group, AnswerFormat.dateTime, path, "low", "start:");
  //	  addQuestion(group, AnswerFormat.dateTime, path, "end", "end:");
  //	}
  //
  //	private void addRatioQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Ratio");
  //	  addQuestion(group, AnswerFormat.decimal, path, "numerator", "numerator:");
  //	  addQuestion(group, AnswerFormat.decimal, path, "denominator", "denominator:");
  //	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
  //	}
  //
  //	private void addHumanNameQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Name");
  //	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
  //	  addQuestion(group, AnswerFormat.string, path, "family", "family:").setRepeatsSimple(true);
  //	  addQuestion(group, AnswerFormat.string, path, "given", "given:").setRepeatsSimple(true);
  //	}
  //
  //	private void addAddressQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Address");
  //	  addQuestion(group, AnswerFormat.string, path, "text", "text:");
  //	  addQuestion(group, AnswerFormat.string, path, "line", "line:").setRepeatsSimple(true);
  //	  addQuestion(group, AnswerFormat.string, path, "city", "city:");
  //	  addQuestion(group, AnswerFormat.string, path, "state", "state:");
  //	  addQuestion(group, AnswerFormat.string, path, "zip", "zip:");
  //	  addQuestion(group, AnswerFormat.string, path, "country", "country:");
  //	}
  //
  //	private void addContactQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Contact");
  //	  ResourceReference ref = new ResourceReference();
  //	  addQuestion(group, AnswerFormat.choice, path, "type", "type:").setOptions(ref);
  //	  ref.setReferenceSimple("http://hl7.org/fhir/vs/contact-system");
  //	  addQuestion(group, AnswerFormat.string, path, "value", "value:");
  //	}
  //
  //	private void addIdentifierQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Identifier");
  //	  addQuestion(group, AnswerFormat.string, path, "label", "label:");
  //	  addQuestion(group, AnswerFormat.string, path, "system", "system:");
  //	  addQuestion(group, AnswerFormat.string, path, "value", "value:");
  //	}
  //
  //	private void addQuantityQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Quantity");
  //    ResourceReference ref = new ResourceReference();
  //	  addQuestion(group, AnswerFormat.choice, path, "comparator", "comp:").setOptions(ref);
  //	  ref.setReferenceSimple("http://hl7.org/fhir/vs/quantity-comparator");
  //	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
  //	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
  //	}
  //
  //  private void addMoneyQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //    ToolingExtensions.setQuestionType(group, "Money");
  //    addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
  //    addQuestion(group, AnswerFormat.string, path, "currency", "currency:");
  //  }
  //
  //	private void addAgeQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Age");
  //    ResourceReference ref = new ResourceReference();
  //	  addQuestion(group, AnswerFormat.choice, path, "comparator", "comp:").setOptions(ref);
  //	  ref.setReferenceSimple("http://hl7.org/fhir/vs/quantity-comparator");
  //	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
  //    ref = new ResourceReference();
  //	  ref.setReferenceSimple("http://hl7.org/fhir/vs/duration-units");
  //	  addQuestion(group, AnswerFormat.choice, path, "units", "units:");
  //	}
  //
  //	private void addDurationQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Duration");
  //	  addQuestion(group, AnswerFormat.decimal, path, "value", "value:");
  //	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
  //	}
  //
  //	private void addAttachmentQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Attachment");
  ////	  raise Exception.Create("addAttachmentQuestions not Done Yet");
  //	}
  //
  //	private void addRangeQuestions(GroupComponent group, ElementComponent element, String path)throws Exception  {
  //	  ToolingExtensions.setQuestionType(group, "Range");
  //	  addQuestion(group, AnswerFormat.decimal, path, "low", "low:");
  //	  addQuestion(group, AnswerFormat.decimal, path, "high", "high:");
  //	  addQuestion(group, AnswerFormat.string, path, "units", "units:");
  //	}
  //
  //	private void addSampledDataQuestions(GroupComponent group, ElementComponent element, String path)throws Exception  {
  //	  ToolingExtensions.setQuestionType(group, "SampledData");
  //	}
  //
  //	private void addScheduleQuestions(GroupComponent group, ElementComponent element, String path) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "Schedule");
  ////	  raise Exception.Create("addScheduleQuestions not Done Yet");
  //	}
  //
  //	// Special Types ---------------------------------------------------------------
  //
  //	private void addReferenceQuestions(GroupComponent group, ElementComponent element, String path, String profileURL) throws Exception {
  //	  ToolingExtensions.setQuestionType(group, "ResourceReference");
  //	  String rn;
  //	  if (profileURL != null && profileURL.startsWith("http://hl7.org/fhir/Profile/"))
  //	    rn = profileURL.substring(28);
  //	  else
  //	    rn = "Any";
  //	  if (rn.equals("Any"))
  //     ToolingExtensions.setQuestionReference(group, "/_search?subject=$subj&patient=$subj&encounter=$encounter");
  //	  else
  //	    ToolingExtensions.setQuestionReference(group, "/"+rn+"?subject=$subj&patient=$subj&encounter=$encounter");
  //	  addQuestion(group, AnswerFormat.reference, path, "value", group.getTextSimple());
  //	  group.setText(null);
  //	}
  //
  //	private void addIdRefQuestions(GroupComponent group, ElementComponent element, String path) {
  ////	  raise Exception.Create("not Done Yet");
  //	}
  //
  //	private void addExtensionQuestions(Questionnaire questionnaire, Profile profile, GroupComponent group, ElementComponent element, String path, String profileURL) throws Exception {
  //	  // is this a  profiled extension, then we add it
  //	  ExtensionResult er = null;
  //	  if (!Utilities.noString(profileURL))
  //	    er = new ProfileUtilities(context).getExtensionDefn(profile, profileURL);
  //	    
  //	  if (er != null) {
  //	    if (er.getExtension().getElement().size() == 1)
  //	      buildQuestion(questionnaire, group, profile, null, er.getExtension().getElement().get(0), path+".extension["+profileURL+"]");
  //	    else
  //	      throw new Exception("Not done yet");
  //	  }
  //	}
  //

  public void build() throws Exception {

    if (profile.getStructure().isEmpty()) 
      throw new Exception("QuestionnaireBuilder.build: no structure found");

    if (structure == null) {
      for (ProfileStructureComponent ps : profile.getStructure()) {
        if (ps.getPublishSimple())
          if (structure == null) 
            structure = ps;
          else
            throw new Exception("buildQuestionnaire: if there is more than one published structure in the profile, you must choose one");
      }
      if (structure == null) 
        throw new Exception("buildQuestionnaire: no published structure found");
    }

    if (!profile.getStructure().contains(structure)) 
      throw new Exception("buildQuestionnaire: profile/structure mismatch");

    if (resource != null)
      if (!structure.getTypeSimple().equals(resource.getResourceType().toString()))
        throw new Exception("Wrong Type");

    if (prebuiltQuestionnaire != null)
      questionnaire = prebuiltQuestionnaire;
    else
      questionnaire = new Questionnaire();
    if (resource != null) 
      answers = new QuestionnaireAnswers();
    processMetadata();


    List<ElementComponent> list = new ArrayList<ElementComponent>();
    List<QuestionnaireAnswers.GroupComponent> answerGroups = new ArrayList<QuestionnaireAnswers.GroupComponent>();

    if (resource != null)
      answerGroups.add(answers.getGroup());
    if (prebuiltQuestionnaire != null) {
      // give it a fake group to build
      Questionnaire.GroupComponent group = new Questionnaire.GroupComponent();
      buildGroup(group, profile, structure, structure.getSnapshot().getElement().get(0), list, answerGroups);
    } else
      buildGroup(questionnaire.getGroup(), profile, structure, structure.getSnapshot().getElement().get(0), list, answerGroups);
    //
    //     NarrativeGenerator ngen = new NarrativeGenerator(context);
    //     ngen.generate(result);
    //
    //    if FAnswers <> nil then
    //      FAnswers.collapseAllContained;
  }

  private void processMetadata() throws Exception {
    // todo: can we derive a more informative identifier from the questionnaire if we have a profile
    if (prebuiltQuestionnaire == null) {
      questionnaire.addIdentifier().setSystemSimple("urn:ietf:rfc:3986").setValueSimple(questionnaireId);
      questionnaire.setVersionSimple(profile.getVersionSimple());
      questionnaire.setStatusSimple(convertStatus(profile.getStatusSimple()));
      questionnaire.setDateSimple(profile.getDateSimple());
      questionnaire.setPublisherSimple(profile.getPublisherSimple());
      questionnaire.setGroup(new Questionnaire.GroupComponent());
      questionnaire.getGroup().getConcept().addAll(profile.getCode());
      questionnaire.setXmlId(nextId("qs"));
    }

    if (answers != null) {
      // no identifier - this is transient
      answers.setQuestionnaire(factory.makeResourceReference("#"+questionnaire.getXmlId()));
      answers.getContained().add(questionnaire);
      answers.setStatusSimple(QuestionnaireAnswersStatus.inProgress);
      answers.setGroup(new QuestionnaireAnswers.GroupComponent());
      answers.getGroup().setTag("object", resource);
    }

  }

  private QuestionnaireStatus convertStatus(ResourceProfileStatus status) {
    switch (status) {
    case active: return QuestionnaireStatus.published;
    case draft: return QuestionnaireStatus.draft;
    case retired : return QuestionnaireStatus.retired;
    default: 
      return QuestionnaireStatus.Null;
    }
  }

  private String nextId(String prefix) {
    lastid++;
    return prefix+Integer.toString(lastid);
  }

  private void buildGroup(GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element,
      List<ElementComponent> parents, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    group.setLinkIdSimple(element.getPathSimple()); // todo: this will be wrong when we start slicing
    group.setTitleSimple(element.getDefinition().getShortSimple()); // todo - may need to prepend the name tail...
    group.setTextSimple(element.getDefinition().getCommentsSimple());
    ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());
    group.setRequiredSimple(element.getDefinition().getMinSimple() > 0);
    group.setRepeatsSimple(!element.getDefinition().getMaxSimple().equals("1"));

    for (org.hl7.fhir.instance.model.QuestionnaireAnswers.GroupComponent ag : answerGroups) {
      ag.setLinkIdSimple(group.getLinkIdSimple());
      ag.setTitleSimple(group.getTitleSimple());
      ag.setTextSimple(group.getTextSimple());
    }

    // now, we iterate the children
    List<ElementComponent> list = ProfileUtilities.getChildList(structure, element);
    for (ElementComponent child : list) {
      if (child.getDefinition() == null) 
        throw new Exception("Found an element with no definition generating a Questionnaire");

      if (!isExempt(element, child) && !parents.contains(child)) {
        List<ElementComponent> nparents = new ArrayList<ElementComponent>();
        nparents.addAll(parents);
        nparents.add(child);
        GroupComponent childGroup = group.addGroup();

        List<QuestionnaireAnswers.GroupComponent> nAnswers = new ArrayList<QuestionnaireAnswers.GroupComponent>();
        processExisting(child.getPathSimple(), answerGroups, nAnswers);
        // if the element has a type, we add a question. else we add a group on the basis that
        // it will have children of it's own
        if (child.getDefinition().getType().isEmpty()) 
          buildGroup(childGroup, profile, structure, child, nparents, nAnswers);
        else
          buildQuestion(childGroup, profile, structure, child, child.getPathSimple(), nAnswers);
      }
    }
  }

  private boolean isExempt(ElementComponent element, ElementComponent child) {
    String n = tail(child.getPathSimple());
    String t = "";
    if (!element.getDefinition().getType().isEmpty())
      t =  element.getDefinition().getType().get(0).getCodeSimple();

    // we don't generate questions for the base stuff in every element
    if (t.equals("Resource")  && (n.equals("text") || n.equals("language") || n.equals("contained")))
      return true;
      // we don't generate questions for extensions
    else if (n.equals("extension") || n.equals("modifierExtension")) {
      if (child.getDefinition().getType().size() > 0 && !Utilities.noString(child.getDefinition().getType().get(0).getProfileSimple())) 
      return false;
      else
        return true;
    } else
      return false;
  }

  private String tail(String path) {
    return path.substring(path.lastIndexOf('.')+1);
  }

  private void processExisting(String path, List<QuestionnaireAnswers.GroupComponent> answerGroups, List<QuestionnaireAnswers.GroupComponent> nAnswers) {
    // processing existing data
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups) {
      List<Element> children = ((Element) ag.getTag("object")).listChildrenByName(tail(path));
      for (Element child : children) {
        if (child != null) {
          QuestionnaireAnswers.GroupComponent ans = ag.addGroup();
          ans.setTag("object", child);
          nAnswers.add(ans);
        }
      }
    }
  }

  private void buildQuestion(GroupComponent group, Profile profile, ProfileStructureComponent structure, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      group.setLinkIdSimple(path);

      // in this context, we don't have any concepts to mark...
      group.setTextSimple(element.getDefinition().getShortSimple()); // prefix with name?
      group.setRequiredSimple(element.getDefinition().getMinSimple() > 0);
      group.setRepeatsSimple(!element.getDefinition().getMaxSimple().equals('1'));

      for (QuestionnaireAnswers.GroupComponent ag : answerGroups) {
        ag.setLinkIdSimple(group.getLinkIdSimple());
        ag.setTitleSimple(group.getTitleSimple());
        ag.setTextSimple(group.getTextSimple());
      }

      if (!Utilities.noString(element.getDefinition().getCommentsSimple())) 
        ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple()+" "+element.getDefinition().getCommentsSimple());
      else
        ToolingExtensions.addFlyOver(group, element.getDefinition().getFormalSimple());

      if (element.getDefinition().getType().size() > 1 || element.getDefinition().getType().get(0).getCodeSimple().equals("*")) {
        List<TypeRefComponent> types = expandTypeList(element.getDefinition().getType());
        Questionnaire.QuestionComponent q = addQuestion(group, AnswerFormat.choice, element.getPathSimple(), "_type", "type", null, makeTypeList(profile, types, element.getPathSimple()));
          for (TypeRefComponent t : types) {
            Questionnaire.GroupComponent sub = q.addGroup();
            sub.setLinkIdSimple(element.getPathSimple()+"._"+t.getTag("text"));
            sub.setTextSimple((String) t.getTag("text"));
            // always optional, never repeats

            List<QuestionnaireAnswers.GroupComponent> selected = new ArrayList<QuestionnaireAnswers.GroupComponent>();
            selectTypes(profile, sub, t, answerGroups, selected);
            processDataType(profile, sub, element, element.getPathSimple()+"._"+t.getTag("text"), t, selected);
          }
      } else
        // now we have to build the question panel for each different data type
        processDataType(profile, group, element, element.getPathSimple(), element.getDefinition().getType().get(0), answerGroups);

  }

  private List<TypeRefComponent> expandTypeList(List<TypeRefComponent> types) {
    List<TypeRefComponent> result = new ArrayList<Profile.TypeRefComponent>();
    for (TypeRefComponent t : types) {
      if (!Utilities.noString(t.getProfileSimple()))
        result.add(t);
      else if (t.getCodeSimple().equals('*')) {
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

  private ValueSet makeTypeList(Profile profile, List<TypeRefComponent> types, String path) {
    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple(Utilities.makeUuidUrn());
    vs.setNameSimple("Type options for "+path);
    vs.setDescriptionSimple(vs.getNameSimple());
    vs.setStatusSimple(ValuesetStatus.active);
    vs.setExpansion(new ValueSetExpansionComponent());
    vs.getExpansion().setTimestampSimple(DateAndTime.now());
    for (TypeRefComponent t : types) {
      ValueSetExpansionContainsComponent cc = vs.getExpansion().addContains();
      if (t.getCodeSimple().equals("ResourceReference") && t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/")) {
        cc.setCodeSimple(t.getProfileSimple().substring(28));
        cc.setSystemSimple("http://hl7.org/fhir/resource-types");
        cc.setDisplaySimple(cc.getCodeSimple());
      } else {
        ProfileUtilities pu = new ProfileUtilities(context);
        StrucResult ps = null;
        if (!Utilities.noString(t.getProfileSimple()))
          ps = pu.getStructure(profile, t.getProfileSimple());
        
        if (ps != null && ps.getStructure() != null) {
          cc.setCodeSimple(t.getProfileSimple());
          cc.setDisplaySimple(structure.getNameSimple());
          cc.setSystemSimple("http://hl7.org/fhir/resource-types");
        } else {
          cc.setCodeSimple(t.getCodeSimple());
          cc.setDisplaySimple(t.getCodeSimple());
          cc.setSystemSimple("http://hl7.org/fhir/data-types");
        }
      }
      t.setTag("text", cc.getCodeSimple());
    }

    return vs;
  }

  private void selectTypes(Profile profile, GroupComponent sub, TypeRefComponent t, List<QuestionnaireAnswers.GroupComponent> source, List<QuestionnaireAnswers.GroupComponent> dest) throws Exception {
    List<QuestionnaireAnswers.GroupComponent> temp = new ArrayList<QuestionnaireAnswers.GroupComponent>();

    for (QuestionnaireAnswers.GroupComponent g : source)
      if (instanceOf(t, (Element) g.getTag("object"))) 
        temp.add(g);
    for (QuestionnaireAnswers.GroupComponent g : temp)
      source.remove(g);
    for (QuestionnaireAnswers.GroupComponent g : temp) {
      // 1st the answer:
      assert(g.getQuestion().size() == 0); // it should be empty
      QuestionnaireAnswers.QuestionComponent q = g.addQuestion();
      q.setLinkIdSimple(g.getLinkIdSimple()+"._type");
      q.setTextSimple("type");

      Coding cc = new Coding();
      q.addAnswer().setValue(cc);
      if (t.getCodeSimple().equals("ResourceReference") && t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/")) {
        cc.setCodeSimple(t.getProfileSimple().substring(28));
        cc.setSystemSimple("http://hl7.org/fhir/resource-types");
      } else {
        ProfileUtilities pu = new ProfileUtilities(context);
        StrucResult ps = null;
        if (!Utilities.noString(t.getProfileSimple()))
          ps = pu.getStructure(profile, t.getProfileSimple());

        if (ps != null && ps.getStructure() != null) {
          cc.setCodeSimple(t.getProfileSimple());
          cc.setSystemSimple("http://hl7.org/fhir/resource-types");
        } else {
          cc.setCodeSimple(t.getCodeSimple());
          cc.setSystemSimple("http://hl7.org/fhir/data-types");
        }
      }

      // 1st: create the subgroup
      QuestionnaireAnswers.GroupComponent subg = q.addGroup();
      dest.add(subg);
      subg.setLinkIdSimple(sub.getLinkIdSimple());
      subg.setTextSimple(sub.getTextSimple());
      subg.setTag("object", g.getTag("object"));
    }
  }

  private boolean instanceOf(TypeRefComponent t, Element obj) throws Exception {
    if (t.getCodeSimple().equals("ResourceReference")) {
      if (!(obj instanceof ResourceReference)) {
        return false;
      } else {
        String url = ((ResourceReference) obj).getReferenceSimple();
        // there are several problems here around profile matching. This process is degenerative, and there's probably nothing we can do to solve it
        if (url.startsWith("http:") || url.startsWith("https:"))
            return true;
        else if (t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/")) 
          return url.startsWith(t.getProfileSimple().substring(28)+'/');
        else
          return true;
      }
    } else if (t.getCodeSimple().equals("Quantity"))
      return obj instanceof Quantity;
    else
      throw new Exception("Not Done Yet");
  }

  private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    return addQuestion(group, af, path, id, name, answerGroups, null);
  }
  
  private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name, List<QuestionnaireAnswers.GroupComponent> answerGroups, ValueSet vs) throws Exception {
//      i, j : integer;
//      aq : TFhirQuestionnaireAnswersGroupQuestion;
//      children : TFHIRObjectList;
    QuestionComponent result = group.addQuestion();
    if (vs != null) {
      result.setOptions(new ResourceReference());
      if (vs.getExpansion() == null) {
        result.getOptions().setReferenceSimple(vs.getIdentifierSimple());
        ToolingExtensions.addFilterOnly(result.getOptions(), true); 
      } else {
        if (Utilities.noString(vs.getXmlId())) {
          vs.setXmlId(nextId("vs"));
          questionnaire.getContained().add(vs);
          vsCache.put(vs.getIdentifierSimple(), vs.getXmlId());
          vs.setText(null);
          vs.setDefine(null);
          vs.setCompose(null);
          vs.getTelecom().clear();
          vs.setPurpose(null);
          vs.setPublisher(null);
          vs.setCopyright(null);
        }
        result.getOptions().setReferenceSimple("#"+vs.getXmlId());
      }
    }
  
    result.setLinkIdSimple(path+'.'+id);
    result.setTextSimple(name);
    result.setTypeSimple(af);
    result.setRequiredSimple(false);
    result.setRepeatsSimple(false);
    if (id.endsWith("/1")) 
      id = id.substring(0, id.length()-2);

    if (answerGroups != null) {

      for (QuestionnaireAnswers.GroupComponent ag : answerGroups) {
        List<Element> children = new ArrayList<Element>(); 

        QuestionnaireAnswers.QuestionComponent aq = null;
        Element obj = (Element) ag.getTag("object");
        if (isPrimitive((TypeRefComponent) obj))
          children.add(obj);
        else if (obj instanceof Enumeration) {
          String value = ((Enumeration) obj).toString();
          children.add(new StringType(value));
        } else
          children = obj.listChildrenByName(id);

        for (Element child: children) {
          if (child != null) {
            if (aq == null) {
              aq = ag.addQuestion();
              aq.setLinkIdSimple(result.getLinkIdSimple());
              aq.setTextSimple(result.getTextSimple());
            }
            aq.addAnswer().setValue(convertType(child, af, vs, result.getLinkIdSimple()));
          }
        }
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  private Type convertType(Element value, AnswerFormat af, ValueSet vs, String path) throws Exception {
    switch (af) {
      // simple cases
    case boolean_: if (value instanceof BooleanType) return (Type) value;
    case decimal: if (value instanceof DecimalType) return (Type) value;
    case integer: if (value instanceof IntegerType) return (Type) value;
    case date: if (value instanceof DateType) return (Type) value;
    case dateTime: if (value instanceof DateTimeType) return (Type) value;
    case instant: if (value instanceof InstantType) return (Type) value;
    case time: if (value instanceof TimeType) return (Type) value;
    case string:
      if (value instanceof StringType) 
        return (Type) value;
      else if (value instanceof UriType) 
        return new StringType(((UriType) value).asStringValue());

    case text: if (value instanceof StringType) return (Type) value;
    case quantity: if (value instanceof  Quantity) return (Type) value;

    // complex cases:
    // ? AnswerFormatAttachment: ...?
    case choice:
    case openchoice :
      if (value instanceof Coding)
        return (Type) value;
      else if (value instanceof Enumeration) { 
        Coding cc = new Coding();
        cc.setCodeSimple(((Enumeration<Enum<?>>) value).asStringValue());
        cc.setSystemSimple(getSystemForCode(vs, cc.getCodeSimple(), path));
        return cc;
      }  else if (value instanceof StringType) {
        Coding cc = new Coding();
        cc.setCodeSimple(((StringType) value).asStringValue());
        cc.setSystemSimple(getSystemForCode(vs, cc.getCodeSimple(), path));
        return cc;
      }

    case reference:
      if (value instanceof ResourceReference)
        return (Type) value;
      else if (value instanceof StringType) {
        ResourceReference r = new ResourceReference();
        r.setReferenceSimple(((StringType) value).asStringValue());
      }
    }

    throw new Exception("Unable to convert from '"+value.getClass().toString()+"' for Answer Format "+af.toCode()+", path = "+path);
  }

  private String getSystemForCode(ValueSet vs, String code, String path) throws Exception {
//    var
//    i, q : integer;
//  begin
    String result = null;
    if (vs == null) {
      if (prebuiltQuestionnaire == null) 
        throw new Exception("Logic error at path = "+path);
      for (Resource r : prebuiltQuestionnaire.getContained()) {
        if (r instanceof ValueSet) {
          vs = (ValueSet) r;
          if (vs.getExpansion() != null) {
            for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
              if (c.getCodeSimple().equals(code)) {
                  if (result == null)
                    result = c.getSystemSimple();
                  else
                    throw new Exception("Multiple matches in "+vs.getIdentifierSimple()+" for code "+code+" at path = "+path);
              }
            }
          }
        }
      }
    }
    
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      if (c.getCodeSimple().equals(code)) {
        if (result == null)
          result = c.getSystemSimple();
        else
          throw new Exception("Multiple matches in "+vs.getIdentifierSimple()+" for code "+code+" at path = "+path);
      }
    }
    if (result != null)
      return result;
    throw new Exception("Unable to resolve code "+code+" at path = "+path);
  }

  private boolean isPrimitive(TypeRefComponent t) {
    return (t != null) && 
          (t.getCodeSimple().equals("string") || t.getCodeSimple().equals("code") || t.getCodeSimple().equals("boolean") || t.getCodeSimple().equals("integer") || 
              t.getCodeSimple().equals("decimal") || t.getCodeSimple().equals("date") || t.getCodeSimple().equals("dateTime") || 
              t.getCodeSimple().equals("instant") || t.getCodeSimple().equals("time") || t.getCodeSimple().equals("ResourceReference"));
  }

  private void processDataType(Profile profile, GroupComponent group, ElementComponent element, String path, TypeRefComponent t, List<QuestionnaireAnswers.GroupComponent> answerGroups) {
//    if (t.getCodeSimple().equals("code"))
//      addCodeQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("string") || t.getCodeSimple().equals("id") || t.getCodeSimple().equals("oid"))
//      addStringQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("uri"))
//      addUriQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("boolean"))
//      addBooleanQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("decimal"))
//      addDecimalQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("dateTime") || t.getCodeSimple().equals("date"))
//        addDateTimeQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("instant"))
//      addInstantQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("time"))
//      addTimeQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("CodeableConcept"))
//      addCodeableConceptQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Period"))
//      addPeriodQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Ratio"))
//      addRatioQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("HumanName"))
//      addHumanNameQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Address"))
//      addAddressQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Contact"))
//      addContactQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Identifier"))
//      addIdentifierQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("integer"))
//      addIntegerQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Coding"))
//      addCodingQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Quantity"))
//      addQuantityQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("ResourceReference"))
//      addReferenceQuestions(group, element, path, t.getProfileSimple(), answerGroups);
//    else if (t.getCodeSimple().equals("idref"))
//      addIdRefQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Duration"))
//      addDurationQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("base64Binary"))
//      addBinaryQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Attachment"))
//      addAttachmentQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Age"))
//      addAgeQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Range"))
//      addRangeQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Schedule"))
//      addScheduleQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("SampledData"))
//      addSampledDataQuestions(group, element, path, answerGroups);
//    else if (t.getCodeSimple().equals("Extension"))
//      addExtensionQuestions(profile, group, element, path, t.getProfileSimple(), answerGroups);
//    else
//      throw new Exception("Unhandled Data Type: "+t.getCodeSimple()+" on element "+element.getPathSimple());
  }

//  private void addStringQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "string");
//    addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addTimeQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "time");
//    addQuestion(group, AnswerFormat.time, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addUriQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "uri");
//    addQuestion(group, AnswerFormat.string, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addBooleanQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "boolean");
//    addQuestion(group, AnswerFormat.boolean_, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addDecimalQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "decimal");
//    addQuestion(group, AnswerFormat.decimal, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addIntegerQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "integer");
//    addQuestion(group, AnswerFormat.integer, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addDateTimeQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "datetime");
//    addQuestion(group, AnswerFormat.dateTime, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addInstantQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "instant");
//    addQuestion(group, AnswerFormat.instant, path, "value", group.getTextSimple(), answerGroups);
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addBinaryQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "binary");
//    // ? Lloyd: how to support binary content
//  }
//  
//  // Complex Types ---------------------------------------------------------------
//
//  private AnswerFormat answerTypeForBinding(ElementDefinitionBindingComponent binding) {
//    if (binding == null) 
//      return AnswerFormat.openchoice;
//    else if (binding.getIsExtensibleSimple()) 
//      return AnswerFormat.openchoice;
//    else
//      return AnswerFormat.choice;
//  }
//
//  private void addCodingQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Coding");
//    addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "value", group.getTextSimple(), answerGroups, resolveValueSet(null, element.getDefinition().getBinding()));
//    group.setText(null);
//    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
//      ag.setText(null);
//  }
//
//  private void addCodeableConceptQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "CodeableConcept");
//    addQuestion(group, answerTypeForBinding(element.getDefinition().getBinding()), path, "coding", "code:", answerGroups, resolveValueSet(null, element.getDefinition().getBinding()));
//    addQuestion(group, AnswerFormat.openchoice, path, "coding/1", "other codes:", answerGroups, makeAnyValueSet()).setRepeatsSimple(true);
//    addQuestion(group, AnswerFormat.string, path, "text", "text:", answerGroups);
//  }
//
//  private void addPeriodQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Period");
//    addQuestion(group, AnswerFormat.dateTime, path, "low", "start:", answerGroups);
//    addQuestion(group, AnswerFormat.dateTime, path, "end", "end:", answerGroups);
//  }
//
//  private void addRatioQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Ratio");
//    addQuestion(group, AnswerFormat.decimal, path, "numerator", "numerator:", answerGroups);
//    addQuestion(group, AnswerFormat.decimal, path, "denominator", "denominator:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "units", "units:", answerGroups);
//  }
//
//  private void addHumanNameQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Name");
//    addQuestion(group, AnswerFormat.string, path, "text", "text:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "family", "family:", answerGroups).setRepeatsSimple(true);
//    addQuestion(group, AnswerFormat.string, path, "given", "given:", answerGroups).setRepeatsSimple(true);
//  }
//
//  private void addAddressQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Address");
//    addQuestion(group, AnswerFormat.string, path, "text", "text:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "line", "line:", answerGroups).setRepeatsSimple(true);
//    addQuestion(group, AnswerFormat.string, path, "city", "city:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "state", "state:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "zip", "zip:", answerGroups);
//    addQuestion(group, AnswerFormat.string, path, "country", "country:", answerGroups);
//    addQuestion(group, AnswerFormat.choice, path, "use", "use:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/address-use"));
//  }
//
//    private void addContactQuestions(GroupComponent group, ElementComponent element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
//    ToolingExtensions.addType(group, "Contact");
//    addQuestion(group, AnswerFormat.choice, path, "system", "type:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/contact-system"));
//    addQuestion(group, AnswerFormat.string, path, "value", "value:", answerGroups);
//    addQuestion(group, AnswerFormat.choice, path, "use", "use:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/contact-use"));
//    }
//    
////    private void addIdentifierQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Identifier");
////    addQuestion(group, AnswerFormatString, path, "label", "label:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "system", "system:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "value", "value:", answerGroups);
////  end;
////
////    private void addQuantityQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Quantity");
////    addQuestion(group, AnswerFormatChoice, path, "comparator", "comp:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/quantity-comparator"));
////    addQuestion(group, AnswerFormatDecimal, path, "value", "value:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "units", "units:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "code", "coded units:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "system", "units system:", answerGroups);
////  end;
////
////    private void addAgeQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Age");
////    addQuestion(group, AnswerFormatChoice, path, "comparator", "comp:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/quantity-comparator"));
////    addQuestion(group, AnswerFormatDecimal, path, "value", "value:", answerGroups);
////    addQuestion(group, AnswerFormatChoice, path, "units", "units:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/duration-units"));
////  end;
////
////    private void addDurationQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Duration");
////    addQuestion(group, AnswerFormatDecimal, path, "value", "value:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "units", "units:", answerGroups);
////  end;
////
////    private void addAttachmentQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Attachment");
//////    raise Exception.Create("addAttachmentQuestions not Done Yet");
////  end;
////
////    private void addRangeQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Range");
////    addQuestion(group, AnswerFormatDecimal, path, "low", "low:", answerGroups);
////    addQuestion(group, AnswerFormatDecimal, path, "high", "high:", answerGroups);
////    addQuestion(group, AnswerFormatString, path, "units", "units:", answerGroups);
////  end;
////
////    private void addSampledDataQuestions(group: TFHIRQuestionnaireGroup; element: TFhirProfileStructureSnapshotElement; path: String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "SampledData");
////  end;
////
////    private void addScheduleQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
////    ToolingExtensions.addType(group, "Schedule");
//////    raise Exception.Create("addScheduleQuestions not Done Yet");
////  end;
////
////  // Special Types ---------------------------------------------------------------
////
////    private void addReferenceQuestions(GroupComponent group ElementComponent element, path : String; profileURL : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  var
////    rn : String;
////    i : integer;
////    q : TFhirQuestionnaireGroupQuestion;
////  begin
////    ToolingExtensions.addType(group, "ResourceReference");
////
////    q := addQuestion(group, AnswerFormatReference, path, "value", group.getTextSimple(), answerGroups);
////    group.setText(null);
////    if profileURL.startsWith("http://hl7.org/fhir/Profile/") then
////      rn := profileURL.Substring(28)
////    else
////      rn := "Any";
////    if (rn = "Any") then
////      q.setExtensionString(TYPE_REFERENCE, "/_search?subject=$subj&patient=$subj&encounter=$encounter")
////    else
////      q.setExtensionString(TYPE_REFERENCE, "/"+rn+"?subject=$subj&patient=$subj&encounter=$encounter");
////    for i := 0 to answerGroups.count - 1 do
////      answerGroups[i].setText(null);
////  end;
////
////    private void addIdRefQuestions(GroupComponent group ElementComponent element, path : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  begin
//////    raise Exception.Create("not Done Yet");
////  end;
////
////    private void addExtensionQuestions(profile : TFHIRProfile; GroupComponent group ElementComponent element, path : String; profileURL : String; answerGroups : TFhirQuestionnaireAnswersGroupList);
////  var
////    extension : TFhirProfileExtensionDefn;
////  begin
////    // is this a  profiled extension, then we add it
////    if (profileURL <> "") and profiles.getExtensionDefn(profile, profileURL, profile, extension) then
////    begin
////      if answerGroups.count > 0 then
////        raise Exception.Create("Debug this");
////      if extension.elementList.Count = 1 then
////        buildQuestion(group, profile, nil, extension.elementList[0], path+".extension["+profileURL+"]", answerGroups)
////      else
////        raise Exception.Create("Not done yet");
////    end;
////  end;
////

}
