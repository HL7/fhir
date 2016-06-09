package org.hl7.fhir.dstu3.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.XmlParser;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.JsonTrackingParser;
import org.hl7.fhir.dstu3.utils.JsonTrackingParser.LocationData;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class InstanceValidatorTests {

	private void parse(String path) throws Exception {
		Map<JsonElement, LocationData> map = new HashMap<JsonElement, JsonTrackingParser.LocationData>();
		
		String source = TextFile.fileToString(Utilities.path("C:\\work\\org.hl7.fhir", path));
		JsonObject obj = JsonTrackingParser.parse(source, map);
		Assert.assertTrue(obj != null);
	}
		
	private void validate(String path, int errorCount, boolean json) throws Exception {
    if (TestingUtilities.context == null) {
    	TestingUtilities.context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation-min.xml.zip");
      ((SimpleWorkerContext) TestingUtilities.context).connectToTSServer("http://local.healthintersections.com.au:960/open");
    }

    System.out.println("Test "+path);
    FileInputStream file = new FileInputStream(Utilities.path("C:\\work\\org.hl7.fhir", path));
		InstanceValidator val = new InstanceValidator(TestingUtilities.context);
		List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
		val.validate(errors, file, json ? FhirFormat.JSON : FhirFormat.XML);
		int ec = 0;
		for (ValidationMessage m : errors) {
			if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL) {
				ec++;
	      System.out.println("  "+m.summary());
			}
		}
		Assert.assertTrue(ec == errorCount);
		System.out.println(val.reportTimes());
	}
		
//	@Test
//	public void testCustom() throws Exception {
//		validate("build\\publish\\sdc\\questionnaire-sdc-profile-example.xml", 0, false);
//	}
	
	
	@Test
	public void testXmlListMinimal() throws Exception {
		validate("build\\tests\\validation-examples\\list-minimal.xml", 0, false);
	}

	@Test
	public void testXmlListWrongOrder() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-order.xml", 1, false);
	}

	@Test
	public void testXmlListWrongCode() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-code.xml", 1, false);
	}

	@Test
	public void testXmlListWrongNS() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-ns.xml", 1, false);
	}
	
	@Test
	public void testXmlListWrongNS1() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-ns1.xml", 1, false);
	}

	@Test
	public void testXmlListWrongNS2() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-ns2.xml", 1, false);
	}

	@Test
	public void testXmlListEmpty1() throws Exception {
		validate("build\\tests\\validation-examples\\list-empty1.xml", 2, false);
	}

	@Test
	public void testXmlListEmpty2() throws Exception {
		validate("build\\tests\\validation-examples\\list-empty2.xml", 2, false);
	}

	@Test
	public void testXmlListUnknownAttr() throws Exception {
		validate("build\\tests\\validation-examples\\list-unknown-attr.xml", 1, false);
	}

	@Test
	public void testXmlListUnknownElement() throws Exception {
		validate("build\\tests\\validation-examples\\list-unknown-element.xml", 1, false);
	}

	@Test
	public void testXmlListText() throws Exception {
		validate("build\\tests\\validation-examples\\list-text.xml", 1, false);
	}

	@Test
	public void testXmlListExtension() throws Exception {
		validate("build\\tests\\validation-examples\\list-extension.xml", 0, false);
	}

	@Test
	public void testXmlListXhtml1() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-correct1.xml", 0, false);
	}

	@Test
	public void testXmlListXhtml2() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-correct2.xml", 0, false);
	}

	@Test
	public void testXmlListXXE() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-xxe1.xml", 1, false);
	}

	@Test
	public void testXmlListXXE2() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-xxe2.xml", 1, false);
	}

	@Test
	public void testXmlListXhtmlWrongNs1() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-wrongns1.xml", 1, false);
	}

	@Test
	public void testXmlListXhtmlWrongNs2() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-wrongns2.xml", 1, false);
	}

	@Test
	public void testXmlListXhtmlWrongNs3() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-wrongns3.xml", 1, false);
	}

	@Test
	public void testXmlListXhtmlBadElement() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-element.xml", 2, false);
	}

	@Test
	public void testXmlListXhtmlBadAttribute() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-attribute.xml", 1, false);
	}

	@Test
	public void testXmlbadSyntax() throws Exception {
		validate("build\\tests\\validation-examples\\list-bad-syntax.xml", 1, false);
	}
	
	@Test
	public void testXmlContained() throws Exception {
		validate("build\\tests\\validation-examples\\list-contained.xml", 0, false);
	}

	@Test
	public void testXmlContainedBad() throws Exception {
		validate("build\\tests\\validation-examples\\list-contained-bad.xml", 1, false);
	}

	@Test
	public void testXmlBundle() throws Exception {
		validate("build\\tests\\validation-examples\\bundle-good.xml", 0, false);
	}
	
	@Test
	public void testXmlGroupOk() throws Exception {
		validate("build\\tests\\validation-examples\\group-minimal.xml", 0, false);
	}

	@Test
	public void testXmlGroupGood() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-good.xml", 0, false);
	}

	@Test
	public void testXmlGroupBad1() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad1.xml", 2, false);
	}

	@Test
	public void testXmlGroupBad2() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad2.xml", 1, false);
	}

	@Test
	public void testXmlGroupBad3() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad3.xml", 1, false);
	}

	@Test
	public void testXmlGroupEmpty() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-empty.xml", 1, false);
	}


	@Test
	public void testJsonListMinimal() throws Exception {
		validate("build\\tests\\validation-examples\\list-minimal.json", 0, true);
	}

	@Test
	public void testJsonListWrongOrder() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-order.json", 0, true);
	}

	@Test
	public void testJsonListWrongCode() throws Exception {
		validate("build\\tests\\validation-examples\\list-wrong-code.json", 1, true);
	}

	@Test
	public void testJsonListEmpty1() throws Exception {
		validate("build\\tests\\validation-examples\\list-empty1.json", 2, true);
	}

	@Test
	public void testJsonListEmpty2() throws Exception {
		validate("build\\tests\\validation-examples\\list-empty2.json", 0, true);
	}

	@Test
	public void testJsonListUnknownProp() throws Exception {
		validate("build\\tests\\validation-examples\\list-unknown-prop.json", 1, true);
	}

	@Test
	public void testJsonListExtension1() throws Exception {
		validate("build\\tests\\validation-examples\\list-extension1.json", 0, true);
	}

	@Test
	public void testJsonListExtension2() throws Exception {
		validate("build\\tests\\validation-examples\\list-extension2.json", 1, true);
	}

	@Test
	public void testJsonListXhtmlCorrect1() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-correct1.json", 0, true);
	}

	@Test
	public void testJsonListXhtmlCorrect2() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-correct2.json", 0, true);
	}

	@Test
	public void testJsonListXhtmlXXE() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-xxe.json", 1, true);
	}

	@Test
	public void testJsonListXhtmlBadSyntax() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-syntax.json", 1, true);
	}

	@Test
	public void testJsonListXhtmlWrongNS1() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-wrongns1.json", 1, true);
	}

	@Test
	public void testJsonListXhtmlWrongNS2() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-wrongns2.json", 1, true);
	}

	@Test
	public void testJsonListXhtmlBadElement() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-element.json", 2, true);
	}

	@Test
	public void testJsonListXhtmlBadAttribute() throws Exception {
		validate("build\\tests\\validation-examples\\list-xhtml-attribute.json", 1, true);
	}

	@Test
	public void testJsonbadSyntax() throws Exception {
		validate("build\\tests\\validation-examples\\list-bad-syntax.json", 1, true);
	}
	
	@Test
	public void testJsonContained() throws Exception {
		validate("build\\tests\\validation-examples\\list-contained.json", 0, true);
	}

	@Test
	public void testJsonContainedBad() throws Exception {
		validate("build\\tests\\validation-examples\\list-contained-bad.json", 1, true);
	}

	@Test
	public void testJsonBundle() throws Exception {
		validate("build\\tests\\validation-examples\\bundle-good.json", 0, true);
	}
	
	@Test
	public void testJsonGroupOk() throws Exception {
		validate("build\\tests\\validation-examples\\group-minimal.json", 0, true);
	}

	@Test
	public void testJsonGroupTiny() throws Exception {
		validate("build\\tests\\validation-examples\\group-minimal-tiny.json", 0, true);
	}

	@Test
	public void testJsonGroupGood() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-good.json", 0, true);
	}

	@Test
	public void testJsonGroupBad1() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad1.json", 2, true);
	}

	@Test
	public void testJsonGroupBad2() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad2.json", 2, true);
	}

	@Test
	public void testJsonGroupBad3() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-bad3.json", 2, true);
	}

	@Test
	public void testJsonGroupEmpty() throws Exception {
		validate("build\\tests\\validation-examples\\group-choice-empty.json", 1, true);
	}


}
