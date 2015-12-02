package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class QuestionnaireQuestionControlEnumFactory implements EnumFactory<QuestionnaireQuestionControl> {

  public QuestionnaireQuestionControl fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("autocomplete".equals(codeString))
      return QuestionnaireQuestionControl.AUTOCOMPLETE;
    if ("drop-down".equals(codeString))
      return QuestionnaireQuestionControl.DROPDOWN;
    if ("check-box".equals(codeString))
      return QuestionnaireQuestionControl.CHECKBOX;
    if ("lookup".equals(codeString))
      return QuestionnaireQuestionControl.LOOKUP;
    if ("radio-button".equals(codeString))
      return QuestionnaireQuestionControl.RADIOBUTTON;
    if ("slider".equals(codeString))
      return QuestionnaireQuestionControl.SLIDER;
    if ("spinner".equals(codeString))
      return QuestionnaireQuestionControl.SPINNER;
    if ("text-box".equals(codeString))
      return QuestionnaireQuestionControl.TEXTBOX;
    throw new IllegalArgumentException("Unknown QuestionnaireQuestionControl code '"+codeString+"'");
  }

  public String toCode(QuestionnaireQuestionControl code) {
    if (code == QuestionnaireQuestionControl.AUTOCOMPLETE)
      return "autocomplete";
    if (code == QuestionnaireQuestionControl.DROPDOWN)
      return "drop-down";
    if (code == QuestionnaireQuestionControl.CHECKBOX)
      return "check-box";
    if (code == QuestionnaireQuestionControl.LOOKUP)
      return "lookup";
    if (code == QuestionnaireQuestionControl.RADIOBUTTON)
      return "radio-button";
    if (code == QuestionnaireQuestionControl.SLIDER)
      return "slider";
    if (code == QuestionnaireQuestionControl.SPINNER)
      return "spinner";
    if (code == QuestionnaireQuestionControl.TEXTBOX)
      return "text-box";
    return "?";
  }


}

