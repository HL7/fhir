package org.hl7.fhir.instance.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.utils.QuestionnaireBuilder;

public class QuestionnaireBuilderTester {

	private static final String TEST_PROFILE_DIR = "C:\\work\\org.hl7.fhir\\build\\publish";
	private static final String TEST_DEST = "c:\\temp\\questionnaires\\";

	public static void main(String[] args) throws Exception {
		QuestionnaireBuilder b = new QuestionnaireBuilder(null);
		for (String f : new File(TEST_PROFILE_DIR).list()) {
			if (f.endsWith(".profile.xml") && !f.contains("type-")) {
				System.out.println("process "+f);
				try {
					Profile p = (Profile) new XmlParser().parse(new FileInputStream(TEST_PROFILE_DIR+"\\"+f));
					if (p.getStructure().size() == 1) {
						Questionnaire q = b.buildQuestionnaire(p);
						new XmlComposer().compose(new FileOutputStream(TEST_DEST+f), q, true);
					}
        } catch (Exception e) {
	        e.printStackTrace();
        }
			}
		}
	}

}
