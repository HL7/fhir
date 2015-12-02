package org.hl7.fhir.dstu21.validation;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.hl7.fhir.dstu21.model.Attachment;
import org.hl7.fhir.dstu21.model.BooleanType;
import org.hl7.fhir.dstu21.model.Coding;
import org.hl7.fhir.dstu21.model.DateTimeType;
import org.hl7.fhir.dstu21.model.DateType;
import org.hl7.fhir.dstu21.model.DecimalType;
import org.hl7.fhir.dstu21.model.InstantType;
import org.hl7.fhir.dstu21.model.IntegerType;
import org.hl7.fhir.dstu21.model.Quantity;
import org.hl7.fhir.dstu21.model.Questionnaire;
import org.hl7.fhir.dstu21.model.QuestionnaireResponse;
import org.hl7.fhir.dstu21.model.Reference;
import org.hl7.fhir.dstu21.model.Resource;
import org.hl7.fhir.dstu21.model.StringType;
import org.hl7.fhir.dstu21.model.TimeType;
import org.hl7.fhir.dstu21.model.Type;
import org.hl7.fhir.dstu21.model.UriType;
import org.hl7.fhir.dstu21.model.ValueSet;
import org.hl7.fhir.dstu21.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu21.model.Questionnaire.QuestionnaireItemComponent;
import org.hl7.fhir.dstu21.model.Questionnaire.QuestionnaireItemType;
import org.hl7.fhir.dstu21.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.dstu21.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.dstu21.model.QuestionnaireResponse.QuestionnaireResponseStatus;
import org.hl7.fhir.dstu21.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.dstu21.utils.EOperationOutcome;
import org.hl7.fhir.dstu21.utils.IWorkerContext;

/**
 * Validates that an instance of {@link QuestionnaireResponse} is valid against the {@link Questionnaire} that it claims to conform to.
 * 
 * @author James Agnew
 */
public class QuestionnaireResponseValidator extends BaseValidator {

	/* *****************************************************************
	 * Note to anyone working on this class -
	 * 
	 * This class has unit tests which run within the HAPI project build. Please sync any changes here to HAPI and ensure that unit tests are run.
	 * ****************************************************************
	 */

	private IWorkerContext myWorkerCtx;

	public QuestionnaireResponseValidator(IWorkerContext theWorkerCtx) {
		this.myWorkerCtx = theWorkerCtx;
	}

	private Set<Class<? extends Type>> allowedTypes(Class<? extends Type> theClass0) {
		HashSet<Class<? extends Type>> retVal = new HashSet<Class<? extends Type>>();
		retVal.add(theClass0);
		return Collections.unmodifiableSet(retVal);
	}

	private List<QuestionnaireResponseItemComponent> findResponsesByLinkId(List<QuestionnaireResponseItemComponent> theItem,
			String theLinkId) {
		Validate.notBlank(theLinkId, "theLinkId must not be blank");

		ArrayList<QuestionnaireResponseItemComponent> retVal = new ArrayList<QuestionnaireResponse.QuestionnaireResponseItemComponent>();
		for (QuestionnaireResponseItemComponent next : theItem) {
			if (theLinkId.equals(next.getLinkId())) {
				retVal.add(next);
			}
		}
		return retVal;
	}

	public void validate(List<ValidationMessage> theErrors, QuestionnaireResponse theAnswers) throws EOperationOutcome, Exception {
		LinkedList<String> pathStack = new LinkedList<String>();
		pathStack.add("QuestionnaireResponse");
		pathStack.add(QuestionnaireResponse.SP_QUESTIONNAIRE);

		if (!fail(theErrors, IssueType.INVALID, pathStack, theAnswers.hasQuestionnaire(), "QuestionnaireResponse does not specity which questionnaire it is providing answers to")) {
			return;
		}

		Reference questionnaireRef = theAnswers.getQuestionnaire();
		Questionnaire questionnaire = getQuestionnaire(theAnswers, questionnaireRef);
		if (!fail(theErrors, IssueType.INVALID, pathStack, questionnaire != null, "Questionnaire {0} is not found in the WorkerContext", theAnswers.getQuestionnaire().getReference())) {
			return;
		}

		QuestionnaireResponseStatus status = theAnswers.getStatus();
		boolean validateRequired = false;
		if (status == QuestionnaireResponseStatus.COMPLETED || status == QuestionnaireResponseStatus.AMENDED) {
			validateRequired = true;
		}

		pathStack.removeLast();
		pathStack.add("group(0)");
		validateItems(theErrors, questionnaire.getItem(), theAnswers.getItem(), pathStack, theAnswers, validateRequired);
	}

	private Questionnaire getQuestionnaire(QuestionnaireResponse theAnswers, Reference theQuestionnaireRef) throws EOperationOutcome, Exception {
		Questionnaire retVal;
		if (theQuestionnaireRef.getReferenceElement().isLocal()) {
			retVal = (Questionnaire) theQuestionnaireRef.getResource();
			if (retVal == null) {
				for (Resource next : theAnswers.getContained()) {
					if (theQuestionnaireRef.getReferenceElement().getValue().equals(next.getId())) {
						retVal = (Questionnaire) next;
					}
				}
			}
		} else {
			retVal = myWorkerCtx.fetchResource(Questionnaire.class, theQuestionnaireRef.getReferenceElement().getValue());
		}
		return retVal;
	}

	private ValueSet getValueSet(QuestionnaireResponse theResponse, Reference theQuestionnaireRef) throws EOperationOutcome, Exception {
		ValueSet retVal;
		if (theQuestionnaireRef.getReferenceElement().isLocal()) {
			retVal = (ValueSet) theQuestionnaireRef.getResource();
			if (retVal == null) {
				for (Resource next : theResponse.getContained()) {
					if (theQuestionnaireRef.getReferenceElement().getValue().equals(next.getId())) {
						retVal = (ValueSet) next;
					}
				}
			}
		} else {
			retVal = myWorkerCtx.fetchResource(ValueSet.class, theQuestionnaireRef.getReferenceElement().getValue());
		}
		return retVal;
	}

	private void validateGroup(List<ValidationMessage> theErrors, QuestionnaireItemComponent theQuestGroup, QuestionnaireResponseItemComponent theRespGroup,
			LinkedList<String> thePathStack, QuestionnaireResponse theResponse, boolean theValidateRequired) throws EOperationOutcome, Exception {
		validateItems(theErrors, theQuestGroup.getItem(), theRespGroup.getItem(), thePathStack, theResponse, theValidateRequired);
		}

	private void validateQuestion(List<ValidationMessage> theErrors, QuestionnaireItemComponent theQuestion, QuestionnaireResponseItemComponent theRespGroup,
			LinkedList<String> thePathStack, QuestionnaireResponse theResponse, boolean theValidateRequired) throws EOperationOutcome, Exception {
		String linkId = theQuestion.getLinkId();
		if (!fail(theErrors, IssueType.INVALID, thePathStack, isNotBlank(linkId), "Questionnaire is invalid, question found with no link ID")) {
			return;
		}

		QuestionnaireItemType type = theQuestion.getType();
		if (type == null) {
			rule(theErrors, IssueType.INVALID, thePathStack, false, "Questionnaire is invalid, no type specified for question with link ID[{0}]", linkId);
				return;
			}
	
		List<QuestionnaireResponseItemComponent> responses;
		if (theRespGroup == null) {
			responses = findResponsesByLinkId(theResponse.getItem(), linkId);
		} else {
			responses = findResponsesByLinkId(theRespGroup.getItem(), linkId);
		}

		if (responses.size() > 1) {
			rule(theErrors, IssueType.BUSINESSRULE, thePathStack, !theQuestion.getRequired(), "Multiple answers repetitions found with linkId[{0}]", linkId);
		}
		if (responses.size() == 0) {
			if (theValidateRequired) {
			rule(theErrors, IssueType.BUSINESSRULE, thePathStack, !theQuestion.getRequired(), "Missing answer item for required item with linkId[{0}]", linkId);
			} else {
				hint(theErrors, IssueType.BUSINESSRULE, thePathStack, !theQuestion.getRequired(), "Missing answer item for required item with linkId[{0}]", linkId);
			}
			return;
		}

		QuestionnaireResponseItemComponent responseItem = responses.get(0);
		try {
			thePathStack.add("item(" + responses.indexOf(responseItem) + ")");
			validateQuestionAnswers(theErrors, theQuestion, thePathStack, type, responseItem, theResponse, theValidateRequired);
		} finally {
			thePathStack.removeLast();
		}
	}

/*	private void validateItemItems(List<ValidationMessage> theErrors, QuestionnaireItemComponent theQuestion, QuestionnaireResponseItemAnswerComponent theAnswer,
			LinkedList<String> thePathSpec, QuestionnaireResponse theAnswers, boolean theValidateRequired) throws EOperationOutcome, Exception {
		validateGroups(theErrors, theQuestion.getGroup(), theAnswer.getGroup(), thePathSpec, theAnswers, theValidateRequired);
	}*/

	private void validateItems(List<ValidationMessage> theErrors, List<QuestionnaireItemComponent> theQuestionnaireItems, List<QuestionnaireResponseItemComponent> theResponseItems,
			LinkedList<String> thePathStack, QuestionnaireResponse theResponse, boolean theValidateRequired) throws EOperationOutcome, Exception {
		Set<String> allowedItems = new HashSet<String>();
		for (QuestionnaireItemComponent nextQuestionnaireItem : theQuestionnaireItems) {
			if (!nextQuestionnaireItem.getType().equals(QuestionnaireItemType.DISPLAY)) {
				String itemType = nextQuestionnaireItem.getType().equals(QuestionnaireItemType.GROUP) ? "group" : "question";
				String linkId = nextQuestionnaireItem.getLinkId();
				allowedItems.add(linkId);

				List<QuestionnaireResponseItemComponent> responseItems = findResponsesByLinkId(theResponseItems, linkId);
				if (responseItems.isEmpty()) {
					if (nextQuestionnaireItem.getRequired()) {
					if (theValidateRequired) {
							rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Missing required " + itemType + " with linkId[{0}]", linkId);
					} else {
							hint(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Missing required " + itemType + " with linkId[{0}]", linkId);
					}
				}
				continue;
			}
				if (responseItems.size() > 1) {
					if (nextQuestionnaireItem.getRepeats() == false) {
						int index = theResponseItems.indexOf(responseItems.get(1));
						thePathStack.add("item(" + index + ")");
						rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Multiple repetitions of " + itemType + " with linkId[{0}] found at this position, but this item cannot repeat", linkId);
					thePathStack.removeLast();
				}
			}
				for (QuestionnaireResponseItemComponent nextResponseItem : responseItems) {
					int index = theResponseItems.indexOf(responseItems.get(1));
					thePathStack.add(itemType +"(" + index + ")");
					if (nextQuestionnaireItem.getType() == QuestionnaireItemType.GROUP) {
						validateGroup(theErrors, nextQuestionnaireItem, nextResponseItem, thePathStack, theResponse, theValidateRequired);
					} else {
						validateQuestion(theErrors, nextQuestionnaireItem, nextResponseItem, thePathStack, theResponse, theValidateRequired);
					}
				thePathStack.removeLast();
			}
		}
		}

		// Make sure there are no items in response that aren't in the questionnaire
		int idx = -1;
		for (QuestionnaireResponseItemComponent next : theResponseItems) {
			idx++;
			if (!allowedItems.contains(next.getLinkId())) {
				thePathStack.add("item(" + idx + ")");
				rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Item with linkId[{0}] found at this position, but this item does not exist at this position in Questionnaire",
						next.getLinkId());
				thePathStack.removeLast();
			}
		}
	}

	private void validateQuestionAnswers(List<ValidationMessage> theErrors, QuestionnaireItemComponent theQuestion, LinkedList<String> thePathStack, QuestionnaireItemType type,
			QuestionnaireResponseItemComponent responseQuestion, QuestionnaireResponse theResponse, boolean theValidateRequired) throws EOperationOutcome, Exception {

		String linkId = theQuestion.getLinkId();
		Set<Class<? extends Type>> allowedAnswerTypes = determineAllowedAnswerTypes(type);
		if (allowedAnswerTypes.isEmpty()) {
			rule(theErrors, IssueType.BUSINESSRULE, thePathStack, responseQuestion.isEmpty(), "Question with linkId[{0}] has no answer type but an answer was provided", linkId);
		} else {
			rule(theErrors, IssueType.BUSINESSRULE, thePathStack, !(responseQuestion.getAnswer().size() > 1 && !theQuestion.getRepeats()), "Multiple answers to non repeating question with linkId[{0}]",
					linkId);
			if (theValidateRequired) {
				rule(theErrors, IssueType.BUSINESSRULE, thePathStack, !(theQuestion.getRequired() && responseQuestion.getAnswer().isEmpty()), "Missing answer to required question with linkId[{0}]", linkId);
			} else {
				hint(theErrors, IssueType.BUSINESSRULE, thePathStack, !(theQuestion.getRequired() && responseQuestion.getAnswer().isEmpty()), "Missing answer to required question with linkId[{0}]", linkId);
			}
		}

		int answerIdx = -1;
		for (QuestionnaireResponseItemAnswerComponent nextAnswer : responseQuestion.getAnswer()) {
			answerIdx++;
			try {
				thePathStack.add("answer(" + answerIdx + ")");
				Type nextValue = nextAnswer.getValue();
				if (!allowedAnswerTypes.contains(nextValue.getClass())) {
					rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Answer to question with linkId[{0}] found of type [{1}] but this is invalid for question of type [{2}]", linkId, nextValue
							.getClass().getSimpleName(), type.toCode());
					continue;
				}

				// Validate choice answers
				if (type == QuestionnaireItemType.CHOICE || type == QuestionnaireItemType.OPENCHOICE) {
					Coding coding = (Coding) nextAnswer.getValue();
					if (isBlank(coding.getCode()) && isBlank(coding.getDisplay()) && isBlank(coding.getSystem())) {
						rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Answer to question with linkId[{0}] is of type coding, but none of code, system, and display are populated", linkId);
						continue;
					} else if (isBlank(coding.getCode()) && isBlank(coding.getSystem())) {
						if (type != QuestionnaireItemType.OPENCHOICE) {
							rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false,
									"Answer to question with linkId[{0}] is of type only has a display populated (no code or system) but question does not allow {1}", linkId, QuestionnaireItemType.OPENCHOICE.name());
							continue;
						}
					} else if (isBlank(coding.getCode()) || isBlank(coding.getSystem())) {
						rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false,
								"Answer to question with linkId[{0}] has a coding, but this coding does not contain a code and system (both must be present, or neither as the question allows {1})", linkId,
								QuestionnaireItemType.OPENCHOICE.name());
						continue;
					}

					String optionsRef = theQuestion.getOptions().getReference();
					if (isNotBlank(optionsRef)) {
						ValueSet valueSet = getValueSet(theResponse, theQuestion.getOptions());
						if (valueSet == null) {
							rule(theErrors, IssueType.BUSINESSRULE, thePathStack, false, "Question with linkId[{0}] has options ValueSet[{1}] but this ValueSet can not be found", linkId, optionsRef);
							continue;
						}

						boolean found = false;
						if (coding.getSystem().equals(valueSet.getCodeSystem().getSystem())) {
							for (ConceptDefinitionComponent next : valueSet.getCodeSystem().getConcept()) {
								if (coding.getCode().equals(next.getCode())) {
									found = true;
									break;
								}
							}
						}

						rule(theErrors, IssueType.BUSINESSRULE, thePathStack, found, "Question with linkId[{0}] has answer with system[{1}] and code[{2}] but this is not a valid answer for ValueSet[{3}]",
								linkId, coding.getSystem(), coding.getCode(), optionsRef);
					}
				}

				validateItems(theErrors, theQuestion.getItem(), nextAnswer.getItem(), thePathStack, theResponse, theValidateRequired);

			} finally {
				thePathStack.removeLast();
			}

		} // for answers
	}

	private Set<Class<? extends Type>> determineAllowedAnswerTypes(QuestionnaireItemType type) {
		Set<Class<? extends Type>> allowedAnswerTypes;
		switch (type) {
		case ATTACHMENT:
			allowedAnswerTypes = allowedTypes(Attachment.class);
			break;
		case BOOLEAN:
			allowedAnswerTypes = allowedTypes(BooleanType.class);
			break;
		case CHOICE:
			allowedAnswerTypes = allowedTypes(Coding.class);
			break;
		case DATE:
			allowedAnswerTypes = allowedTypes(DateType.class);
			break;
		case DATETIME:
			allowedAnswerTypes = allowedTypes(DateTimeType.class);
			break;
		case DECIMAL:
			allowedAnswerTypes = allowedTypes(DecimalType.class);
			break;
		case INSTANT:
			allowedAnswerTypes = allowedTypes(InstantType.class);
			break;
		case INTEGER:
			allowedAnswerTypes = allowedTypes(IntegerType.class);
			break;
		case OPENCHOICE:
			allowedAnswerTypes = allowedTypes(Coding.class);
			break;
		case QUANTITY:
			allowedAnswerTypes = allowedTypes(Quantity.class);
			break;
		case REFERENCE:
			allowedAnswerTypes = allowedTypes(Reference.class);
			break;
		case STRING:
			allowedAnswerTypes = allowedTypes(StringType.class);
			break;
		case TEXT:
			allowedAnswerTypes = allowedTypes(StringType.class);
			break;
		case TIME:
			allowedAnswerTypes = allowedTypes(TimeType.class);
			break;
		case URL:
			allowedAnswerTypes = allowedTypes(UriType.class);
			break;
		case NULL:
		default:
			allowedAnswerTypes = Collections.emptySet();
		}
		return allowedAnswerTypes;
	}
}
