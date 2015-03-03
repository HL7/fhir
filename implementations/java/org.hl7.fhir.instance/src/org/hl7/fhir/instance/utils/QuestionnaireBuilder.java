package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Enumerations.*;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Questionnaire.AnswerFormat;
import org.hl7.fhir.instance.model.Questionnaire.GroupComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionComponent;
import org.hl7.fhir.instance.model.Questionnaire.QuestionnaireStatus;
import org.hl7.fhir.instance.model.QuestionnaireAnswers;
import org.hl7.fhir.instance.model.QuestionnaireAnswers.QuestionnaireAnswersStatus;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.TimeType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.utilities.Utilities;



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

  private static final int MaxListboxCodings = 20;
  private WorkerContext context;
  private int lastid = 0;
  private Resource resource;
  private StructureDefinition profile;
  private Questionnaire questionnaire;
  private QuestionnaireAnswers answers;
  private String questionnaireId;
  private Factory factory = new Factory();
  private Map<String, String> vsCache = new HashMap<String, String>();
  private ValueSetExpander expander;

  // sometimes, when this is used, the questionnaire is already build and cached, and we are
  // processing the answers. for technical reasons, we still go through the process, but
  // we don't do the intensive parts of the work (save time)
  private Questionnaire prebuiltQuestionnaire;

  public QuestionnaireBuilder(WorkerContext context) {
    super();
    this.context = context;
  }

  public Resource getReference() {
    return resource;
  }

  public void setReference(Resource resource) {
    this.resource = resource;
  }

  public StructureDefinition getProfile() {
    return profile;
  }

  public void setProfile(StructureDefinition profile) {
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

  public ValueSetExpander getExpander() {
    return expander;
  }

  public void setExpander(ValueSetExpander expander) {
    this.expander = expander;
  }

  public void build() throws Exception {
		if (profile == null)
      throw new Exception("QuestionnaireBuilder.build: no profile found");

    if (resource != null)
      if (!profile.getType().equals(resource.getResourceType().toString()))
        throw new Exception("Wrong Type");

    if (prebuiltQuestionnaire != null)
      questionnaire = prebuiltQuestionnaire;
    else
      questionnaire = new Questionnaire();
    if (resource != null) 
      answers = new QuestionnaireAnswers();
    processMetadata();


    List<ElementDefinition> list = new ArrayList<ElementDefinition>();
    List<QuestionnaireAnswers.GroupComponent> answerGroups = new ArrayList<QuestionnaireAnswers.GroupComponent>();

    if (resource != null)
      answerGroups.add(answers.getGroup());
    if (prebuiltQuestionnaire != null) {
      // give it a fake group to build
      Questionnaire.GroupComponent group = new Questionnaire.GroupComponent();
      buildGroup(group, profile, profile.getSnapshot().getElement().get(0), list, answerGroups);
    } else
      buildGroup(questionnaire.getGroup(), profile, profile.getSnapshot().getElement().get(0), list, answerGroups);
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
      questionnaire.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue(questionnaireId);
      questionnaire.setVersion(profile.getVersion());
      questionnaire.setStatus(convertStatus(profile.getStatus()));
      questionnaire.setDate(profile.getDate());
      questionnaire.setPublisher(profile.getPublisher());
      questionnaire.setGroup(new Questionnaire.GroupComponent());
      questionnaire.getGroup().getConcept().addAll(profile.getCode());
      questionnaire.setId(nextId("qs"));
    }

    if (answers != null) {
      // no identifier - this is transient
      answers.setQuestionnaire(factory.makeReference("#"+questionnaire.getId()));
      answers.getContained().add(questionnaire);
      answers.setStatus(QuestionnaireAnswersStatus.INPROGRESS);
      answers.setGroup(new QuestionnaireAnswers.GroupComponent());
      answers.getGroup().setUserData("object", resource);
    }

  }

  private QuestionnaireStatus convertStatus(ConformanceResourceStatus status) {
    switch (status) {
		case ACTIVE: return QuestionnaireStatus.PUBLISHED;
		case DRAFT: return QuestionnaireStatus.DRAFT;
		case RETIRED : return QuestionnaireStatus.RETIRED;
    default: 
      return QuestionnaireStatus.NULL;
    }
  }

  private String nextId(String prefix) {
    lastid++;
    return prefix+Integer.toString(lastid);
  }

  private void buildGroup(GroupComponent group, StructureDefinition profile, ElementDefinition element,
      List<ElementDefinition> parents, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
	  group.setLinkId(element.getPath()); // todo: this will be wrong when we start slicing
	  group.setTitle(element.getShort()); // todo - may need to prepend the name tail... 
	  group.setText(element.getComments());
	  ToolingExtensions.addFlyOver(group, element.getDefinition());
    group.setRequired(element.getMin() > 0);
    group.setRepeats(!element.getMax().equals("1"));

    for (org.hl7.fhir.instance.model.QuestionnaireAnswers.GroupComponent ag : answerGroups) {
      ag.setLinkId(group.getLinkId());
      ag.setTitle(group.getTitle());
      ag.setText(group.getText());
    }

    // now, we iterate the children
    List<ElementDefinition> list = ProfileUtilities.getChildList(profile, element);
    for (ElementDefinition child : list) {

      if (!isExempt(element, child) && !parents.contains(child)) {
				List<ElementDefinition> nparents = new ArrayList<ElementDefinition>();
        nparents.addAll(parents);
        nparents.add(child);
        GroupComponent childGroup = group.addGroup();

        List<QuestionnaireAnswers.GroupComponent> nAnswers = new ArrayList<QuestionnaireAnswers.GroupComponent>();
        processExisting(child.getPath(), answerGroups, nAnswers);
        // if the element has a type, we add a question. else we add a group on the basis that
        // it will have children of it's own
        if (child.getType().isEmpty()) 
          buildGroup(childGroup, profile, child, nparents, nAnswers);
        else
          buildQuestion(childGroup, profile, child, child.getPath(), nAnswers);
      }
    }
  }

  private boolean isExempt(ElementDefinition element, ElementDefinition child) {
    String n = tail(child.getPath());
    String t = "";
    if (!element.getType().isEmpty())
      t =  element.getType().get(0).getCode();

    // we don't generate questions for the base stuff in every element
    if (t.equals("Resource")  && (n.equals("text") || n.equals("language") || n.equals("contained")))
      return true;
      // we don't generate questions for extensions
    else if (n.equals("extension") || n.equals("modifierExtension")) {
      if (child.getType().size() > 0 && !Utilities.noString(child.getType().get(0).getProfile())) 
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
      List<Base> children = ((Element) ag.getUserData("object")).listChildrenByName(tail(path));
      for (Base child : children) {
        if (child != null) {
          QuestionnaireAnswers.GroupComponent ans = ag.addGroup();
          ans.setUserData("object", child);
          nAnswers.add(ans);
        }
      }
    }
  }

  private void buildQuestion(GroupComponent group, StructureDefinition profile, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      group.setLinkId(path);

      // in this context, we don't have any concepts to mark...
      group.setText(element.getShort()); // prefix with name?
      group.setRequired(element.getMin() > 0);
      group.setRepeats(!element.getMax().equals('1'));

      for (QuestionnaireAnswers.GroupComponent ag : answerGroups) {
        ag.setLinkId(group.getLinkId());
        ag.setTitle(group.getTitle());
        ag.setText(group.getText());
      }

      if (!Utilities.noString(element.getComments())) 
        ToolingExtensions.addFlyOver(group, element.getDefinition()+" "+element.getComments());
      else
        ToolingExtensions.addFlyOver(group, element.getDefinition());

      if (element.getType().size() > 1 || element.getType().get(0).getCode().equals("*")) {
        List<TypeRefComponent> types = expandTypeList(element.getType());
        Questionnaire.QuestionComponent q = addQuestion(group, AnswerFormat.CHOICE, element.getPath(), "_type", "type", null, makeTypeList(profile, types, element.getPath()));
          for (TypeRefComponent t : types) {
            Questionnaire.GroupComponent sub = q.addGroup();
            sub.setLinkId(element.getPath()+"._"+t.getUserData("text"));
            sub.setText((String) t.getUserData("text"));
            // always optional, never repeats

            List<QuestionnaireAnswers.GroupComponent> selected = new ArrayList<QuestionnaireAnswers.GroupComponent>();
            selectTypes(profile, sub, t, answerGroups, selected);
            processDataType(profile, sub, element, element.getPath()+"._"+t.getUserData("text"), t, selected);
          }
      } else
        // now we have to build the question panel for each different data type
        processDataType(profile, group, element, element.getPath(), element.getType().get(0), answerGroups);

  }

  private List<TypeRefComponent> expandTypeList(List<TypeRefComponent> types) {
	  List<TypeRefComponent> result = new ArrayList<TypeRefComponent>();
    for (TypeRefComponent t : types) {
	    if (!Utilities.noString(t.getProfile()))
        result.add(t);
	    else if (t.getCode().equals("*")) {
	      result.add(new TypeRefComponent().setCode("integer"));
	      result.add(new TypeRefComponent().setCode("decimal"));
	      result.add(new TypeRefComponent().setCode("dateTime"));
	      result.add(new TypeRefComponent().setCode("date"));
	      result.add(new TypeRefComponent().setCode("instant"));
	      result.add(new TypeRefComponent().setCode("time"));
	      result.add(new TypeRefComponent().setCode("string"));
	      result.add(new TypeRefComponent().setCode("uri"));
	      result.add(new TypeRefComponent().setCode("boolean"));
	      result.add(new TypeRefComponent().setCode("Coding"));
	      result.add(new TypeRefComponent().setCode("CodeableConcept"));
	      result.add(new TypeRefComponent().setCode("Attachment"));
	      result.add(new TypeRefComponent().setCode("Identifier"));
	      result.add(new TypeRefComponent().setCode("Quantity"));
	      result.add(new TypeRefComponent().setCode("Range"));
	      result.add(new TypeRefComponent().setCode("Period"));
	      result.add(new TypeRefComponent().setCode("Ratio"));
	      result.add(new TypeRefComponent().setCode("HumanName"));
	      result.add(new TypeRefComponent().setCode("Address"));
        result.add(new TypeRefComponent().setCode("ContactPoint"));
        result.add(new TypeRefComponent().setCode("Timing"));
	      result.add(new TypeRefComponent().setCode("Reference"));
      } else
        result.add(t);
    }
    return result;
  }

  private ValueSet makeTypeList(StructureDefinition profile, List<TypeRefComponent> types, String path) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setUrl(Utilities.makeUuidUrn());
    vs.setName("Type options for "+path);
    vs.setDescription(vs.getName());
	  vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setExpansion(new ValueSetExpansionComponent());
    vs.getExpansion().setTimestampElement(DateTimeType.now());
    for (TypeRefComponent t : types) {
      ValueSetExpansionContainsComponent cc = vs.getExpansion().addContains();
	    if (t.getCode().equals("Reference") && (t.hasProfile() && t.getProfile().startsWith("http://hl7.org/fhir/StructureDefinition/"))) { 
	      cc.setCode(t.getProfile().substring(40));
        cc.setSystem("http://hl7.org/fhir/resource-types");
	      cc.setDisplay(cc.getCode());
      } else {
        ProfileUtilities pu = new ProfileUtilities(context);
        StructureDefinition ps = null;
	      if (!Utilities.noString(t.getProfile())) 
          ps = pu.getProfile(profile, t.getProfile());
        
        if (ps != null) {
	        cc.setCode(t.getProfile());
          cc.setDisplay(ps.getSnapshot().getElement().get(0).getType().get(0).getCode());
          cc.setSystem("http://hl7.org/fhir/resource-types");
        } else {
	        cc.setCode(t.getCode());
	        cc.setDisplay(t.getCode());
          cc.setSystem("http://hl7.org/fhir/data-types");
        }
      }
      t.setUserData("text", cc.getCode());
    }

    return vs;
  }

  private void selectTypes(StructureDefinition profile, GroupComponent sub, TypeRefComponent t, List<QuestionnaireAnswers.GroupComponent> source, List<QuestionnaireAnswers.GroupComponent> dest) throws Exception {
    List<QuestionnaireAnswers.GroupComponent> temp = new ArrayList<QuestionnaireAnswers.GroupComponent>();

    for (QuestionnaireAnswers.GroupComponent g : source)
      if (instanceOf(t, (Element) g.getUserData("object"))) 
        temp.add(g);
    for (QuestionnaireAnswers.GroupComponent g : temp)
      source.remove(g);
    for (QuestionnaireAnswers.GroupComponent g : temp) {
      // 1st the answer:
      assert(g.getQuestion().size() == 0); // it should be empty
      QuestionnaireAnswers.QuestionComponent q = g.addQuestion();
      q.setLinkId(g.getLinkId()+"._type");
      q.setText("type");

      Coding cc = new Coding();
      q.addAnswer().setValue(cc);
      if (t.getCode().equals("Reference") && t.getProfile().startsWith("http://hl7.org/fhir/StructureDefinition/")) {
        cc.setCode(t.getProfile().substring(40));
        cc.setSystem("http://hl7.org/fhir/resource-types");
      } else {
        ProfileUtilities pu = new ProfileUtilities(context);
        StructureDefinition ps = null;
        if (!Utilities.noString(t.getProfile()))
          ps = pu.getProfile(profile, t.getProfile());

        if (ps != null) {
          cc.setCode(t.getProfile());
          cc.setSystem("http://hl7.org/fhir/resource-types");
        } else {
          cc.setCode(t.getCode());
          cc.setSystem("http://hl7.org/fhir/data-types");
        }
      }

      // 1st: create the subgroup
      QuestionnaireAnswers.GroupComponent subg = q.addGroup();
      dest.add(subg);
      subg.setLinkId(sub.getLinkId());
      subg.setText(sub.getText());
      subg.setUserData("object", g.getUserData("object"));
    }
  }

  private boolean instanceOf(TypeRefComponent t, Element obj) throws Exception {
    if (t.getCode().equals("Reference")) {
      if (!(obj instanceof Reference)) {
        return false;
      } else {
        String url = ((Reference) obj).getReference();
        // there are several problems here around profile matching. This process is degenerative, and there's probably nothing we can do to solve it
        if (url.startsWith("http:") || url.startsWith("https:"))
            return true;
        else if (t.getProfile().startsWith("http://hl7.org/fhir/StructureDefinition/")) 
          return url.startsWith(t.getProfile().substring(40)+'/');
        else
          return true;
      }
    } else if (t.getCode().equals("Quantity"))
      return obj instanceof Quantity;
    else
      throw new Exception("Not Done Yet");
  }

  private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    return addQuestion(group, af, path, id, name, answerGroups, null);
  }
  
  private QuestionComponent addQuestion(GroupComponent group, AnswerFormat af, String path, String id, String name, List<QuestionnaireAnswers.GroupComponent> answerGroups, ValueSet vs) throws Exception {
    QuestionComponent result = group.addQuestion();
    if (vs != null) {
      result.setOptions(new Reference());
      if (vs.getExpansion() == null) {
        result.getOptions().setReference(vs.getUrl());
        ToolingExtensions.addFilterOnly(result.getOptions(), true); 
      } else {
        if (Utilities.noString(vs.getId())) {
          vs.setId(nextId("vs"));
          questionnaire.getContained().add(vs);
          vsCache.put(vs.getUrl(), vs.getId());
          vs.setText(null);
          vs.setDefine(null);
          vs.setCompose(null);
          vs.getContact().clear();
          vs.setPublisherElement(null);
          vs.setCopyrightElement(null);
        }
        result.getOptions().setReference("#"+vs.getId());
      }
    }
  
    result.setLinkId(path+'.'+id);
    result.setText(name);
    result.setType(af);
    result.setRequired(false);
    result.setRepeats(false);
    if (id.endsWith("/1")) 
      id = id.substring(0, id.length()-2);

    if (answerGroups != null) {

      for (QuestionnaireAnswers.GroupComponent ag : answerGroups) {
        List<Base> children = new ArrayList<Base>(); 

        QuestionnaireAnswers.QuestionComponent aq = null;
        Element obj = (Element) ag.getUserData("object");
        if (isPrimitive((TypeRefComponent) obj))
          children.add(obj);
        else if (obj instanceof Enumeration) {
          String value = ((Enumeration) obj).toString();
          children.add(new StringType(value));
        } else
          children = obj.listChildrenByName(id);

        for (Base child: children) {
          if (child != null) {
            if (aq == null) {
              aq = ag.addQuestion();
              aq.setLinkId(result.getLinkId());
              aq.setText(result.getText());
            }
            aq.addAnswer().setValue(convertType(child, af, vs, result.getLinkId()));
          }
        }
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  private Type convertType(Base value, AnswerFormat af, ValueSet vs, String path) throws Exception {
    switch (af) {
      // simple cases
    case BOOLEAN: if (value instanceof BooleanType) return (Type) value;
    case DECIMAL: if (value instanceof DecimalType) return (Type) value;
    case INTEGER: if (value instanceof IntegerType) return (Type) value;
    case DATE: if (value instanceof DateType) return (Type) value;
    case DATETIME: if (value instanceof DateTimeType) return (Type) value;
    case INSTANT: if (value instanceof InstantType) return (Type) value;
    case TIME: if (value instanceof TimeType) return (Type) value;
    case STRING:
      if (value instanceof StringType) 
        return (Type) value;
      else if (value instanceof UriType) 
        return new StringType(((UriType) value).asStringValue());

    case TEXT: if (value instanceof StringType) return (Type) value;
    case QUANTITY: if (value instanceof  Quantity) return (Type) value;

    // complex cases:
    // ? AnswerFormatAttachment: ...?
    case CHOICE:
    case OPENCHOICE :
      if (value instanceof Coding)
        return (Type) value;
      else if (value instanceof Enumeration) { 
        Coding cc = new Coding();
        cc.setCode(((Enumeration<Enum<?>>) value).asStringValue());
        cc.setSystem(getSystemForCode(vs, cc.getCode(), path));
        return cc;
      }  else if (value instanceof StringType) {
        Coding cc = new Coding();
        cc.setCode(((StringType) value).asStringValue());
        cc.setSystem(getSystemForCode(vs, cc.getCode(), path));
        return cc;
      }

    case REFERENCE:
      if (value instanceof Reference)
        return (Type) value;
      else if (value instanceof StringType) {
        Reference r = new Reference();
        r.setReference(((StringType) value).asStringValue());
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
          if (vs.hasExpansion()) {
            for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
              if (c.getCode().equals(code)) {
                  if (result == null)
                    result = c.getSystem();
                  else
                    throw new Exception("Multiple matches in "+vs.getUrl()+" for code "+code+" at path = "+path);
              }
            }
          }
        }
      }
    }
    
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      if (c.getCode().equals(code)) {
        if (result == null)
          result = c.getSystem();
        else
          throw new Exception("Multiple matches in "+vs.getUrl()+" for code "+code+" at path = "+path);
      }
    }
    if (result != null)
      return result;
    throw new Exception("Unable to resolve code "+code+" at path = "+path);
  }

  private boolean isPrimitive(TypeRefComponent t) {
    return (t != null) && 
          (t.getCode().equals("string") || t.getCode().equals("code") || t.getCode().equals("boolean") || t.getCode().equals("integer") || 
              t.getCode().equals("decimal") || t.getCode().equals("date") || t.getCode().equals("dateTime") || 
              t.getCode().equals("instant") || t.getCode().equals("time") || t.getCode().equals("Reference"));
  }

  private void processDataType(StructureDefinition profile, GroupComponent group, ElementDefinition element, String path, TypeRefComponent t, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    if (t.getCode().equals("code"))
      addCodeQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("string") || t.getCode().equals("id") || t.getCode().equals("oid"))
      addStringQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("uri"))
      addUriQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("boolean"))
      addBooleanQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("decimal"))
      addDecimalQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("dateTime") || t.getCode().equals("date"))
        addDateTimeQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("instant"))
      addInstantQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("time"))
      addTimeQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("CodeableConcept"))
      addCodeableConceptQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Period"))
      addPeriodQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Ratio"))
      addRatioQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("HumanName"))
      addHumanNameQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Address"))
      addAddressQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("ContactPoint"))
      addContactPointQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Identifier"))
      addIdentifierQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("integer"))
      addIntegerQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Coding"))
      addCodingQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Quantity"))
      addQuantityQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Money"))
      addMoneyQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Reference"))
      addReferenceQuestions(group, element, path, t.getProfile(), answerGroups);
    else if (t.getCode().equals("Duration"))
      addDurationQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("base64Binary"))
      addBinaryQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Attachment"))
      addAttachmentQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Age"))
      addAgeQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Range"))
      addRangeQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Timing"))
      addTimingQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("SampledData"))
      addSampledDataQuestions(group, element, path, answerGroups);
    else if (t.getCode().equals("Extension"))
      addExtensionQuestions(profile, group, element, path, t.getProfile(), answerGroups);
    else if (!t.getCode().equals("Narrative") && !t.getCode().equals("Resource") && !t.getCode().equals("ElementDefinition")&& !t.getCode().equals("Meta"))
      throw new Exception("Unhandled Data Type: "+t.getCode()+" on element "+element.getPath());
  }

  private void addCodeQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "code");
    ValueSet vs = resolveValueSet(null, element.hasBinding() ? element.getBinding() : null);
    addQuestion(group, AnswerFormat.CHOICE, path, "value", unCamelCase(tail(element.getPath())), answerGroups, vs);
    group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private String unCamelCase(String s) {
    StringBuilder result = new StringBuilder();
    
      for (int i = 0; i < s.length(); i++) {
        if (Character.isUpperCase(s.charAt(i))) 
          result.append(' ');
        result.append(s.charAt(i));
      }
      return result.toString().toLowerCase();
  }

  private void addStringQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "string");
    addQuestion(group, AnswerFormat.STRING, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addTimeQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "time");
    addQuestion(group, AnswerFormat.TIME, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addUriQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "uri");
    addQuestion(group, AnswerFormat.STRING, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addBooleanQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "boolean");
    addQuestion(group, AnswerFormat.BOOLEAN, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addDecimalQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "decimal");
    addQuestion(group, AnswerFormat.DECIMAL, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addIntegerQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "integer");
    addQuestion(group, AnswerFormat.INTEGER, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addDateTimeQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "datetime");
    addQuestion(group, AnswerFormat.DATETIME, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addInstantQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "instant");
    addQuestion(group, AnswerFormat.INSTANT, path, "value", group.getText(), answerGroups);
	  group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addBinaryQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "binary");
    // ? Lloyd: how to support binary content
  }
  
  // Complex Types ---------------------------------------------------------------

  private AnswerFormat answerTypeForBinding(ElementDefinitionBindingComponent binding) {
    if (binding == null) 
      return AnswerFormat.OPENCHOICE;
    else if (binding.getIsExtensible()) 
      return AnswerFormat.OPENCHOICE;
    else
      return AnswerFormat.CHOICE;
  }

  private void addCodingQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "Coding");
    addQuestion(group, answerTypeForBinding(element.hasBinding() ? element.getBinding() : null), path, "value", group.getText(), answerGroups, resolveValueSet(null, element.hasBinding() ? element.getBinding() : null));
    group.setText(null);
    for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
      ag.setText(null);
  }

  private void addCodeableConceptQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "CodeableConcept");
    addQuestion(group, answerTypeForBinding(element.hasBinding() ? element.getBinding() : null), path, "coding", "code:", answerGroups, resolveValueSet(null, element.hasBinding() ? element.getBinding() : null));
    addQuestion(group, AnswerFormat.STRING, path, "text", "text:", answerGroups);
  }

  private ValueSet makeAnyValueSet() {
    // TODO Auto-generated method stub
    return null;
  }

  private void addPeriodQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "Period");
    addQuestion(group, AnswerFormat.DATETIME, path, "low", "start:", answerGroups);
    addQuestion(group, AnswerFormat.DATETIME, path, "end", "end:", answerGroups);
  }

  private void addRatioQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "Ratio");
    addQuestion(group, AnswerFormat.DECIMAL, path, "numerator", "numerator:", answerGroups);
    addQuestion(group, AnswerFormat.DECIMAL, path, "denominator", "denominator:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "units", "units:", answerGroups);
  }

  private void addHumanNameQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "Name");
    addQuestion(group, AnswerFormat.STRING, path, "text", "text:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "family", "family:", answerGroups).setRepeats(true);
    addQuestion(group, AnswerFormat.STRING, path, "given", "given:", answerGroups).setRepeats(true);
  }

  private void addAddressQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "Address");
    addQuestion(group, AnswerFormat.STRING, path, "text", "text:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "line", "line:", answerGroups).setRepeats(true);
    addQuestion(group, AnswerFormat.STRING, path, "city", "city:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "state", "state:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "postalCode", "post code:", answerGroups);
    addQuestion(group, AnswerFormat.STRING, path, "country", "country:", answerGroups);
    addQuestion(group, AnswerFormat.CHOICE, path, "use", "use:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/address-use"));
  }

    private void addContactPointQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
    ToolingExtensions.addType(group, "ContactPoint");
    addQuestion(group, AnswerFormat.CHOICE, path, "system", "type:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/contact-point-system"));
    addQuestion(group, AnswerFormat.STRING, path, "value", "value:", answerGroups);
    addQuestion(group, AnswerFormat.CHOICE, path, "use", "use:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/contact-point-use"));
    }
    
    private void addIdentifierQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Identifier");
      addQuestion(group, AnswerFormat.STRING, path, "label", "label:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "system", "system:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "value", "value:", answerGroups);
    }

    private void addQuantityQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Quantity");
      addQuestion(group, AnswerFormat.CHOICE, path, "comparator", "comp:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/quantity-comparator"));
      addQuestion(group, AnswerFormat.DECIMAL, path, "value", "value:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "units", "units:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "code", "coded units:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "system", "units system:", answerGroups);
    }

    private void addMoneyQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Money");
      addQuestion(group, AnswerFormat.DECIMAL, path, "value", "value:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "currency", "currency:", answerGroups);
  }

    private void addAgeQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Age");
      addQuestion(group, AnswerFormat.CHOICE, path, "comparator", "comp:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/quantity-comparator"));
      addQuestion(group, AnswerFormat.DECIMAL, path, "value", "value:", answerGroups);
      addQuestion(group, AnswerFormat.CHOICE, path, "units", "units:", answerGroups, resolveValueSet("http://hl7.org/fhir/vs/duration-units"));
    }

    private void addDurationQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Duration");
      addQuestion(group, AnswerFormat.DECIMAL, path, "value", "value:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "units", "units:", answerGroups);
    }

    private void addAttachmentQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Attachment");
      //    raise Exception.Create("addAttachmentQuestions not Done Yet");
    }

    private void addRangeQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Range");
      addQuestion(group, AnswerFormat.DECIMAL, path, "low", "low:", answerGroups);
      addQuestion(group, AnswerFormat.DECIMAL, path, "high", "high:", answerGroups);
      addQuestion(group, AnswerFormat.STRING, path, "units", "units:", answerGroups);
    }
    
    private void addSampledDataQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "SampledData");
    }
    
    private void addTimingQuestions(GroupComponent group, ElementDefinition element, String path, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      ToolingExtensions.addType(group, "Schedule");
    }
    
  // Special Types ---------------------------------------------------------------

    private void addReferenceQuestions(GroupComponent group, ElementDefinition element, String path, String profileURL, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception {
      //  var
      //    rn : String;
      //    i : integer;
      //    q : TFhirQuestionnaireGroupQuestion;
      ToolingExtensions.addType(group, "Reference");

      QuestionComponent q = addQuestion(group, AnswerFormat.REFERENCE, path, "value", group.getText(), answerGroups);
      group.setText(null);
      String rn = null;
      if (profileURL != null && profileURL.startsWith("http://hl7.org/fhir/StructureDefinition/"))
        rn = profileURL.substring(40);
      else
        rn = "Any";
      if (rn.equals("Any"))
        ToolingExtensions.addReference(q, "/_search?subject=$subj&patient=$subj&encounter=$encounter");
      else
        ToolingExtensions.addReference(q, "/"+rn+"?subject=$subj&patient=$subj&encounter=$encounter");
      for (QuestionnaireAnswers.GroupComponent ag : answerGroups)
        ag.setText(null);
    }


    private void addExtensionQuestions(StructureDefinition profile, GroupComponent group, ElementDefinition element, String path, String url, List<QuestionnaireAnswers.GroupComponent> answerGroups) throws Exception { 
      // if this a  profiled extension, then we add it
    	if (!Utilities.noString(url)) {
    		StructureDefinition ed =  context.getExtensionStructure(null, url);
    		if (ed != null) {
          if (answerGroups.size() > 0)
            throw new Exception("Debug this");
    			buildQuestion(group, profile, ed.getSnapshot().getElement().get(0), path+".extension["+url+"]", answerGroups);
        }
      }
    }

    private ValueSet resolveValueSet(String url) {
//      if (prebuiltQuestionnaire != null)
        return null; // we don't do anything with value sets in this case

//      if (vsCache.containsKey(url))
//        return (ValueSet) questionnaire.getContained(vsCache.get(url));
//      else {
//        ValueSet vs = context.findValueSet(url);
//        if (vs != null)
//          return expander.expand(vs, MaxListboxCodings, false);
//      }
//       
//       /*     on e: ETooCostly do
//            begin
//              result := TFhirValueSet.Create;
//              try
//                result.identifierST := ref.referenceST;
//                result.link;
//              finally
//                result.Free;
//              end;
//            end;
//            on e : Exception do
//              raise;
//          end;*/
//      }
    }

    private ValueSet resolveValueSet(Object object, ElementDefinitionBindingComponent binding) {
      return null;
    }

}
