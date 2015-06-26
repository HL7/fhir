package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.utils.ProfileComparer;
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
    comp.setLeftStructure(left);
    comp.setRightStructure(right);
    comp.compareProfiles();
    if (comp.getSubset() != null)
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream("C:\\temp\\intersection.xml"), comp.getSubset());
    if (comp.getSuperset() != null)
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream("C:\\temp\\union.xml"), comp.getSuperset());
    
    for (ValidationMessage vm : comp.getMessages()) 
      System.out.println(vm.summary());
    System.out.println("done. "+Integer.toString(comp.getMessages().size())+" messages");
  }
}
