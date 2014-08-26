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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

public class ResourceFactory extends Factory {

    public static Resource createResource(String name) throws Exception {
        if ("Condition".equals(name))
            return new Condition();
        if ("Supply".equals(name))
            return new Supply();
        if ("Organization".equals(name))
            return new Organization();
        if ("Group".equals(name))
            return new Group();
        if ("ValueSet".equals(name))
            return new ValueSet();
        if ("ImmunizationRecommendation".equals(name))
            return new ImmunizationRecommendation();
        if ("Appointment".equals(name))
            return new Appointment();
        if ("MedicationDispense".equals(name))
            return new MedicationDispense();
        if ("MedicationPrescription".equals(name))
            return new MedicationPrescription();
        if ("Slot".equals(name))
            return new Slot();
        if ("Contraindication".equals(name))
            return new Contraindication();
        if ("AppointmentResponse".equals(name))
            return new AppointmentResponse();
        if ("MedicationStatement".equals(name))
            return new MedicationStatement();
        if ("Questionnaire".equals(name))
            return new Questionnaire();
        if ("Composition".equals(name))
            return new Composition();
        if ("OperationOutcome".equals(name))
            return new OperationOutcome();
        if ("Conformance".equals(name))
            return new Conformance();
        if ("Media".equals(name))
            return new Media();
        if ("Other".equals(name))
            return new Other();
        if ("Profile".equals(name))
            return new Profile();
        if ("DocumentReference".equals(name))
            return new DocumentReference();
        if ("Immunization".equals(name))
            return new Immunization();
        if ("OrderResponse".equals(name))
            return new OrderResponse();
        if ("Subscription".equals(name))
            return new Subscription();
        if ("ConceptMap".equals(name))
            return new ConceptMap();
        if ("ImagingStudy".equals(name))
            return new ImagingStudy();
        if ("Practitioner".equals(name))
            return new Practitioner();
        if ("CarePlan".equals(name))
            return new CarePlan();
        if ("Provenance".equals(name))
            return new Provenance();
        if ("Device".equals(name))
            return new Device();
        if ("Query".equals(name))
            return new Query();
        if ("Order".equals(name))
            return new Order();
        if ("Procedure".equals(name))
            return new Procedure();
        if ("Substance".equals(name))
            return new Substance();
        if ("DiagnosticReport".equals(name))
            return new DiagnosticReport();
        if ("Medication".equals(name))
            return new Medication();
        if ("MessageHeader".equals(name))
            return new MessageHeader();
        if ("DocumentManifest".equals(name))
            return new DocumentManifest();
        if ("DataElement".equals(name))
            return new DataElement();
        if ("Availability".equals(name))
            return new Availability();
        if ("MedicationAdministration".equals(name))
            return new MedicationAdministration();
        if ("QuestionnaireAnswers".equals(name))
            return new QuestionnaireAnswers();
        if ("Encounter".equals(name))
            return new Encounter();
        if ("SecurityEvent".equals(name))
            return new SecurityEvent();
        if ("List".equals(name))
            return new List_();
        if ("OperationDefinition".equals(name))
            return new OperationDefinition();
        if ("DeviceObservationReport".equals(name))
            return new DeviceObservationReport();
        if ("ReferralRequest".equals(name))
            return new ReferralRequest();
        if ("RiskAssessment".equals(name))
            return new RiskAssessment();
        if ("FamilyHistory".equals(name))
            return new FamilyHistory();
        if ("Location".equals(name))
            return new Location();
        if ("Observation".equals(name))
            return new Observation();
        if ("AllergyIntolerance".equals(name))
            return new AllergyIntolerance();
        if ("RelatedPerson".equals(name))
            return new RelatedPerson();
        if ("Specimen".equals(name))
            return new Specimen();
        if ("Alert".equals(name))
            return new Alert();
        if ("Patient".equals(name))
            return new Patient();
        if ("Namespace".equals(name))
            return new Namespace();
        if ("AdverseReaction".equals(name))
            return new AdverseReaction();
        if ("DiagnosticOrder".equals(name))
            return new DiagnosticOrder();
        else
            throw new Exception("Unknown Resource Name '"+name+"'");
    }

    public static Element createType(String name) throws Exception {
        if ("Period".equals(name))
            return new Period();
        if ("Coding".equals(name))
            return new Coding();
        if ("Range".equals(name))
            return new Range();
        if ("Age".equals(name))
            return new Age();
        if ("Count".equals(name))
            return new Count();
        if ("Quantity".equals(name))
            return new Quantity();
        if ("Attachment".equals(name))
            return new Attachment();
        if ("Money".equals(name))
            return new Money();
        if ("Distance".equals(name))
            return new Distance();
        if ("Contact".equals(name))
            return new Contact();
        if ("Extension".equals(name))
            return new Extension();
        if ("Schedule".equals(name))
            return new Schedule();
        if ("HumanName".equals(name))
            return new HumanName();
        if ("Address".equals(name))
            return new Address();
        if ("Duration".equals(name))
            return new Duration();
        if ("Ratio".equals(name))
            return new Ratio();
        if ("SampledData".equals(name))
            return new SampledData();
        if ("ResourceReference".equals(name))
            return new ResourceReference();
        if ("CodeableConcept".equals(name))
            return new CodeableConcept();
        if ("Identifier".equals(name))
            return new Identifier();
        if ("Narrative".equals(name))
            return new Narrative();
        else
            throw new Exception("Unknown Type Name '"+name+"'");
    }

}

