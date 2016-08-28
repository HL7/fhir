package org.hl7.fhir.dstu2.test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.hl7.fhir.dstu2.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu2.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu2.validation.InstanceValidator;
import org.hl7.fhir.dstu2.validation.ValidationMessage;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class InstanceValidatorTests {

  private void validateCnt(String cnt, int errorCount, boolean json) throws Exception {
    if (TestingUtilities.context == null) {
      TestingUtilities.context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir.dstu2\\build\\publish\\validation-min.xml.zip");
      ((SimpleWorkerContext) TestingUtilities.context).connectToTSServer("http://local.healthintersections.com.au:960/open");
    }

    System.out.println("Test Content");
    ByteArrayInputStream file = new ByteArrayInputStream(cnt.getBytes(Charsets.UTF_8));
    InstanceValidator val = new InstanceValidator(TestingUtilities.context);
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    JsonObject obj = new Gson().fromJson(cnt, JsonObject.class);
    val.validate(obj);
    int ec = 0;
    for (ValidationMessage m : errors) {
      if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL) {
        ec++;
        System.out.println("  "+m.summary());
      }
    }
    Assert.assertTrue(ec == errorCount);
  }
    

  @Test
  public void testJsonS4S() throws Exception {
    validateCnt("{\r\n"+
        "  \"resourceType\": \"Bundle\",\r\n"+
        "  \"type\": \"searchset\",\r\n"+
        "  \"total\": 661,\r\n"+
        "  \"link\": [\r\n"+
        "    {\r\n"+
        "      \"relation\": \"self\",\r\n"+
        "      \"url\": \"https://fhir-open-api-dstu2.smarthealthit.org/Condition?_count=1&_format=json\"\r\n"+
        "    },\r\n"+
        "    {\r\n"+
        "      \"relation\": \"next\",\r\n"+
        "      \"url\": \"https://fhir-open-api-dstu2.smarthealthit.org/Condition?_format=json&_count=1&_skip=1\"\r\n"+
        "    }\r\n"+
        "  ],\r\n"+
        "  \"entry\": [\r\n"+
        "    {\r\n"+
        "      \"fullUrl\": \"https://fhir-open-api-dstu2.smarthealthit.org/Condition/119\",\r\n"+
        "      \"resource\": {\r\n"+
        "        \"resourceType\": \"Condition\",\r\n"+
        "        \"id\": \"119\",\r\n"+
        "        \"meta\": {\r\n"+
        "          \"versionId\": \"27\",\r\n"+
        "          \"lastUpdated\": \"2016-03-09T15:29:49.651+00:00\"\r\n"+
        "        },\r\n"+
        "        \"text\": {\r\n"+
        "          \"status\": \"generated\",\r\n"+
        "          \"div\": \"<div>Single liveborn, born in hospital, delivered without mention of cesarean section</div>\"\r\n"+
        "        },\r\n"+
        "        \"patient\": {\r\n"+
        "          \"reference\": \"Patient/1032702\"\r\n"+
        "        },\r\n"+
        "        \"code\": {\r\n"+
        "          \"coding\": [\r\n"+
        "            {\r\n"+
        "              \"system\": \"http://snomed.info/sct\",\r\n"+
        "              \"code\": \"442311008\",\r\n"+
        "              \"display\": \"Single liveborn, born in hospital, delivered without mention of cesarean section\"\r\n"+
        "            }\r\n"+
        "          ],\r\n"+
        "          \"text\": \"Single liveborn, born in hospital, delivered without mention of cesarean section\"\r\n"+
        "        },\r\n"+
        "        \"clinicalStatus\": \"active\",\r\n"+
        "        \"verificationStatus\": \"confirmed\",\r\n"+
        "        \"onsetDateTime\": \"2007-12-14\"\r\n"+
        "      },\r\n"+
        "      \"search\": {\r\n"+
        "        \"mode\": \"match\"\r\n"+
        "      }\r\n"+
        "    }\r\n"+
        "  ]\r\n"+
        "}\r\n", 1, true);
  }
  
}
