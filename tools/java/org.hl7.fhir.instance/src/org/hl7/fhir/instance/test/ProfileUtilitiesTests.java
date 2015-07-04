package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.terminologies.FHIRTerminologyServices;
import org.hl7.fhir.instance.utils.ProfileComparer;
import org.hl7.fhir.instance.utils.ProfileComparer.ProfileComparison;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;

public class ProfileUtilitiesTests {

  private WorkerContext context;
  private ProfileComparer comp;
  
  public static void main(String[] args) throws Exception {
    new ProfileUtilitiesTests().execute(args);
    //    StructureDefinition p = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\lipid-report-cholesterol.profile.xml"));
    //    new ProfileUtilities(context).generateSchematrons(new FileOutputStream("c:\\temp\\test.sch"), p);
  }
  
  public void execute(String[] args) throws Exception {
    System.out.println("loading context");
    context = WorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip").setTerminologyServices(new FHIRTerminologyServices("http://localhost:980/open"));
    comp = new ProfileComparer(context);
    
    compare("patient-daf-dafpatient.profile.xml", "patient-qicore-qicore-patient.profile.xml");
    compare("encounter-daf-dafencounter.profile.xml", "encounter-qicore-qicore-encounter.profile.xml");
    compare("substance-daf-dafsubstance.profile.xml", "substance-qicore-qicore-substance.profile.xml");
    compare("medication-daf-dafmedication.profile.xml", "medication-qicore-qicore-medication.profile.xml");
    compare("procedure-daf-dafprocedure.profile.xml", "procedure-qicore-qicore-procedure.profile.xml");
    compare("familymemberhistory-daf-daffamilymemberhistory.profile.xml", "familymemberhistory-qicore-qicore-familymemberhistory.profile.xml");
    compare("immunization-daf-dafimmunization.profile.xml", "immunization-qicore-qicore-immunization.profile.xml");
    compare("condition-daf-dafcondition.profile.xml", "condition-qicore-qicore-condition.profile.xml");
    compare("allergyintolerance-daf-dafallergyintolerance.profile.xml", "allergyintolerance-qicore-qicore-allergyintolerance.profile.xml");
    compare("medicationadministration-daf-dafmedicationadministration.profile.xml", "medicationadministration-qicore-qicore-medicationadministration.profile.xml");
    compare("medicationdispense-daf-dafmedicationdispense.profile.xml", "medicationdispense-qicore-qicore-medicationdispense.profile.xml");
    compare("medicationprescription-daf-dafmedicationprescription.profile.xml", "medicationprescription-qicore-qicore-medicationprescription.profile.xml");
    compare("medicationstatement-daf-dafmedicationstatement.profile.xml", "medicationstatement-qicore-qicore-medicationstatement.profile.xml");
    compare("observation-daf-smokingstatus-dafsmokingstatus.profile.xml", "observation-qicore-qicore-observation.profile.xml");
    compare("observation-daf-vitalsigns-dafvitalsigns.profile.xml", "observation-qicore-qicore-observation.profile.xml");
//    compare("observation-daf-results-dafresultobs.profile.xml", "observation-qicore-qicore-observation.profile.xml");
//    compare("diagnosticorder-daf-dafdiagnosticorder.profile.xml", "diagnosticorder-qicore-qicore-diagnosticorder.profile.xml");
//    compare("diagnosticreport-daf-dafdiagnosticreport.profile.xml", "diagnosticreport-qicore-qicore-diagnosticreport.profile.xml");
    
    System.out.println("processing output");
    for (ProfileComparison outcome : comp.getComparisons()) { 
      if (outcome.getSubset() != null)
        new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream("C:\\temp\\intersection-"+outcome.getId()+".xml"), outcome.getSubset());
      if (outcome.getSuperset() != null)
        new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream("C:\\temp\\union-"+outcome.getId()+".xml"), outcome.getSuperset());

      System.out.println("\r\n"+outcome.getId()+": Comparison of "+outcome.getLeft().getUrl()+" and "+outcome.getRight().getUrl());
      for (ValidationMessage vm : outcome.getMessages())
        if (vm.getLevel() == IssueSeverity.INFORMATION)
          System.out.println(vm.summary());
      for (ValidationMessage vm : outcome.getMessages())
        if (vm.getLevel() == IssueSeverity.WARNING)
          System.out.println(vm.summary());
      for (ValidationMessage vm : outcome.getMessages())
        if (vm.getLevel() == IssueSeverity.ERROR)
          System.out.println(vm.summary());
      for (ValidationMessage vm : outcome.getMessages())
        if (vm.getLevel() == IssueSeverity.FATAL)
          System.out.println(vm.summary());
      System.out.println("done. "+Integer.toString(outcome.getMessages().size())+" messages");
      System.out.println("=================================================================");
    }
  }

  private void compare(String fn1, String fn2) throws Exception {
    System.out.println("Compare "+fn1+" to "+fn2);
    System.out.println("  .. load");
    StructureDefinition left = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\"+fn1));
    StructureDefinition right = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\"+fn2));
    System.out.println(" .. compare");
    comp.compareProfiles(left, right);
    
  }
}
