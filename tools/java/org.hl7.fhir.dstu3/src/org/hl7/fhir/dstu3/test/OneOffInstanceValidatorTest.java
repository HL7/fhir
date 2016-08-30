package org.hl7.fhir.dstu3.test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext;
import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.XmlParser;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.utils.formats.JsonTrackingParser;
import org.hl7.fhir.dstu3.utils.formats.JsonTrackingParser.LocationData;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OneOffInstanceValidatorTest {

	private void parse(String path) throws Exception {
		Map<JsonElement, LocationData> map = new HashMap<JsonElement, JsonTrackingParser.LocationData>();
		
		String source = TextFile.fileToString(Utilities.path("C:\\work\\org.hl7.fhir", path));
		JsonObject obj = JsonTrackingParser.parse(source, map);
		Assert.assertTrue(obj != null);
	}
		
	private void validate(String path, int errorCount, boolean json) throws Exception {
	  if (TestingUtilities.path == null)
	    TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (TestingUtilities.context == null) {
    	TestingUtilities.context = SimpleWorkerContext.fromPack(Utilities.path(TestingUtilities.path, "publish", "igpack.zip"));
      ((SimpleWorkerContext) TestingUtilities.context).connectToTSServer("http://fhir3.healthintersections.com.au/open");
    }

    if (!TestingUtilities.silent)
      System.out.println("Test "+path);
    FileInputStream file = new FileInputStream(Utilities.path(TestingUtilities.path, path));
		InstanceValidator val = new InstanceValidator(TestingUtilities.context);
		List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
		val.validate(null, errors, file, json ? FhirFormat.JSON : FhirFormat.XML);
		int ec = 0;
		for (ValidationMessage m : errors) {
			if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL) {
				ec++;
				if (!TestingUtilities.silent)
	        System.out.println("  "+m.summary());
			}
		}
		Assert.assertTrue(ec == errorCount);
		if (!TestingUtilities.silent)
		  System.out.println(val.reportTimes());
	}
		
  private void validateCnt(String cnt, int errorCount, boolean json) throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (TestingUtilities.context == null) {
      TestingUtilities.context = SimpleWorkerContext.fromPack(Utilities.path(TestingUtilities.path, "publish", "igpack.zip"));
      ((SimpleWorkerContext) TestingUtilities.context).connectToTSServer("http://fhir3.healthintersections.com.au/open");
    }

    if (!TestingUtilities.silent)
      System.out.println("Test Content");
    ByteArrayInputStream file = new ByteArrayInputStream(cnt.getBytes(Charsets.UTF_8));
    InstanceValidator val = new InstanceValidator(TestingUtilities.context);
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    val.validate(null, errors, file, json ? FhirFormat.JSON : FhirFormat.XML);
    int ec = 0;
    for (ValidationMessage m : errors) {
      if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL) {
        ec++;
        if (!TestingUtilities.silent)
          System.out.println("  "+m.summary());
      }
    }
    Assert.assertTrue(ec == errorCount);
    if (!TestingUtilities.silent)
      System.out.println(val.reportTimes());
  }
    
//	@Test
//	public void testCustom() throws Exception {
//		validate("publish\\sdc\\questionnaire-sdc-profile-example.xml", 0, false);
//	}
	
	
	@Test
	public void test() throws Exception {
		validate("publish\\cqif\\questionnaire-cqif-example.xml", 0, false);
	}
  

}
