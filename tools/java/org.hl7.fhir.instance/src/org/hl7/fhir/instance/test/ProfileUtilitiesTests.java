package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.utils.ProfileComparer;
import org.hl7.fhir.instance.utils.ProfileComparer.ProfileComparison;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;

public class ProfileUtilitiesTests {

  public static void main(String[] args) throws Exception {
    WorkerContext context = WorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip");
    //    StructureDefinition p = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\lipid-report-cholesterol.profile.xml"));
    //		new ProfileUtilities(context).generateSchematrons(new FileOutputStream("c:\\temp\\test.sch"), p);
    StructureDefinition left = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\patient-daf-dafpatient.profile.xml"));
    StructureDefinition right = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\patient-qicore-qicore-patient.profile.xml"));
    ProfileComparer comp = new ProfileComparer(context);
    comp.compareProfiles(left, right);
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
}
