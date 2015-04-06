package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;

public class ProfileUtilitiesTests {

	public static void main(String[] args) throws Exception {
		WorkerContext context = WorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip");
		StructureDefinition p = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\lipid-report-cholesterol.profile.xml"));
		new ProfileUtilities(context).generateSchematrons(new FileOutputStream("c:\\temp\\test.sch"), p);
		System.out.println("done");
	}
}
