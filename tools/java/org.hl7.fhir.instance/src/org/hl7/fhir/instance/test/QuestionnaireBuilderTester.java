package org.hl7.fhir.instance.test;

import java.io.File;
import java.io.FileInputStream;

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
//						Questionnaire q = b.buildQuestionnaire(p);
//						new XmlComposer().compose(new FileOutputStream(TEST_DEST+f), q, true);
					  throw new Exception("test");
        } catch (Exception e) {
	        e.printStackTrace();
        }
			}
		}
	}

}
