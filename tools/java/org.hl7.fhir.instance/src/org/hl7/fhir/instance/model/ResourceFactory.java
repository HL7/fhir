package org.hl7.fhir.instance.model;

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

// Generated on Sun, Aug 3, 2014 22:02-0600 for FHIR v0.2.1

public class ResourceFactory extends Factory {

    public static Resource createResource(String name) throws Exception {
        if ("Appointment".equals(name))
            return new Appointment();
        if ("ReferralRequest".equals(name))
            return new ReferralRequest();
        if ("Provenance".equals(name))
            return new Provenance();
        if ("Questionnaire".equals(name))
            return new Questionnaire();
        if ("Query".equals(name))
            return new Query();
        if ("DocumentManifest".equals(name))
            return new DocumentManifest();
        if ("Specimen".equals(name))
            return new Specimen();
        if ("AllergyIntolerance".equals(name))
            return new AllergyIntolerance();
        if ("CarePlan".equals(name))
            return new CarePlan();
        if ("MedicationPrescription".equals(name))
            return new MedicationPrescription();
        if ("OperationOutcome".equals(name))
            return new OperationOutcome();
        if ("FamilyHistory".equals(name))
            return new FamilyHistory();
        if ("Medication".equals(name))
            return new Medication();
        if ("Procedure".equals(name))
            return new Procedure();
        if ("List".equals(name))
            return new List_();
        if ("ConceptMap".equals(name))
            return new ConceptMap();
        if ("Subscription".equals(name))
            return new Subscription();
        if ("ValueSet".equals(name))
            return new ValueSet();
        if ("OperationDefinition".equals(name))
            return new OperationDefinition();
        if ("DocumentReference".equals(name))
            return new DocumentReference();
        if ("Order".equals(name))
            return new Order();
        if ("Availability".equals(name))
            return new Availability();
        if ("Immunization".equals(name))
            return new Immunization();
        if ("SecurityEvent".equals(name))
            return new SecurityEvent();
        if ("Device".equals(name))
            return new Device();
        if ("Media".equals(name))
            return new Media();
        if ("Conformance".equals(name))
            return new Conformance();
        if ("RelatedPerson".equals(name))
            return new RelatedPerson();
        if ("Namespace".equals(name))
            return new Namespace();
        if ("Practitioner".equals(name))
            return new Practitioner();
        if ("AppointmentResponse".equals(name))
            return new AppointmentResponse();
        if ("Observation".equals(name))
            return new Observation();
        if ("MedicationAdministration".equals(name))
            return new MedicationAdministration();
        if ("DeviceObservationReport".equals(name))
            return new DeviceObservationReport();
        if ("Slot".equals(name))
            return new Slot();
        if ("Contraindication".equals(name))
            return new Contraindication();
        if ("MedicationStatement".equals(name))
            return new MedicationStatement();
        if ("RiskAssessment".equals(name))
            return new RiskAssessment();
        if ("Group".equals(name))
            return new Group();
        if ("Organization".equals(name))
            return new Organization();
        if ("MedicationDispense".equals(name))
            return new MedicationDispense();
        if ("Supply".equals(name))
            return new Supply();
        if ("DiagnosticReport".equals(name))
            return new DiagnosticReport();
        if ("ImagingStudy".equals(name))
            return new ImagingStudy();
        if ("Profile".equals(name))
            return new Profile();
        if ("DataElement".equals(name))
            return new DataElement();
        if ("QuestionnaireAnswers".equals(name))
            return new QuestionnaireAnswers();
        if ("AdverseReaction".equals(name))
            return new AdverseReaction();
        if ("Encounter".equals(name))
            return new Encounter();
        if ("Substance".equals(name))
            return new Substance();
        if ("Condition".equals(name))
            return new Condition();
        if ("Composition".equals(name))
            return new Composition();
        if ("DiagnosticOrder".equals(name))
            return new DiagnosticOrder();
        if ("Patient".equals(name))
            return new Patient();
        if ("OrderResponse".equals(name))
            return new OrderResponse();
        if ("Alert".equals(name))
            return new Alert();
        if ("MessageHeader".equals(name))
            return new MessageHeader();
        if ("ImmunizationRecommendation".equals(name))
            return new ImmunizationRecommendation();
        if ("Location".equals(name))
            return new Location();
        if ("Other".equals(name))
            return new Other();
        else
            throw new Exception("Unknown Resource Name '"+name+"'");
    }

    public static Element createType(String name) throws Exception {
        if ("Address".equals(name))
            return new Address();
        if ("Quantity".equals(name))
            return new Quantity();
        if ("Period".equals(name))
            return new Period();
        if ("Attachment".equals(name))
            return new Attachment();
        if ("Duration".equals(name))
            return new Duration();
        if ("Count".equals(name))
            return new Count();
        if ("Range".equals(name))
            return new Range();
        if ("Contact".equals(name))
            return new Contact();
        if ("Extension".equals(name))
            return new Extension();
        if ("Money".equals(name))
            return new Money();
        if ("HumanName".equals(name))
            return new HumanName();
        if ("Identifier".equals(name))
            return new Identifier();
        if ("Narrative".equals(name))
            return new Narrative();
        if ("Coding".equals(name))
            return new Coding();
        if ("Schedule".equals(name))
            return new Schedule();
        if ("SampledData".equals(name))
            return new SampledData();
        if ("Ratio".equals(name))
            return new Ratio();
        if ("ResourceReference".equals(name))
            return new ResourceReference();
        if ("Distance".equals(name))
            return new Distance();
        if ("Age".equals(name))
            return new Age();
        if ("CodeableConcept".equals(name))
            return new CodeableConcept();
        else
            throw new Exception("Unknown Type Name '"+name+"'");
    }

}

