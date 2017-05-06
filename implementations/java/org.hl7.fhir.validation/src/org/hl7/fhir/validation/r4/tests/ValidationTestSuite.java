package org.hl7.fhir.validation.r4.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.exceptions.PathEngineException;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.elementmodel.Element;
import org.hl7.fhir.r4.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.TypeDetails;
import org.hl7.fhir.r4.test.support.TestingUtilities;
import org.hl7.fhir.r4.utils.FHIRPathEngine.IEvaluationContext;
import org.hl7.fhir.r4.utils.IResourceValidator.IValidatorResourceFetcher;
import org.hl7.fhir.r4.utils.IResourceValidator.ReferenceValidationPolicy;
import org.hl7.fhir.r4.validation.InstanceValidator;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RunWith(Parameterized.class)
public class ValidationTestSuite implements IEvaluationContext, IValidatorResourceFetcher {

  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    
    Map<String, JsonObject> examples = new HashMap<String, JsonObject>();
    JsonObject json =  (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(TestingUtilities.home(), "tests", "validation-examples", "manifest.json")));
    json = json.getAsJsonObject("validator-tests");
    for (Entry<String, JsonElement> e : json.getAsJsonObject("Json").entrySet()) {
      examples.put("Json."+e.getKey(), e.getValue().getAsJsonObject());
    }
    for (Entry<String, JsonElement> e : json.getAsJsonObject("Xml").entrySet()) {
      examples.put("Xml."+e.getKey(), e.getValue().getAsJsonObject());
    }

    List<String> names = new ArrayList<String>(examples.size());
    names.addAll(examples.keySet());
    Collections.sort(names);

    List<Object[]> objects = new ArrayList<Object[]>(examples.size());
    for (String id : names) {
      objects.add(new Object[] { id, examples.get(id)});
    }
    return objects;
  }

  private String name;
  private JsonObject content;
  
  public ValidationTestSuite(String name, JsonObject content) {
    this.name = name;
    this.content = content;
  }

  @SuppressWarnings("deprecation")
  @Test
  public void test() throws Exception {
    if (TestingUtilities.context == null)
      TestingUtilities.context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\definitions.xml.zip");

    String path = Utilities.path(TestingUtilities.home(), "tests", "validation-examples", name.substring(name.indexOf(".")+1));
    InstanceValidator val = new InstanceValidator(TestingUtilities.context, this);
    val.setFetcher(this);
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    if (name.startsWith("Json."))
      val.validate(null, errors, new FileInputStream(path), FhirFormat.JSON);
    else
      val.validate(null, errors, new FileInputStream(path), FhirFormat.XML);
    int ec = 0;
    for (ValidationMessage vm : errors)
      if (vm.getLevel() == IssueSeverity.FATAL || vm.getLevel() == IssueSeverity.ERROR) {
        ec++;
        System.out.println(vm.getDisplay());
      }
    Assert.assertEquals("Expected "+Integer.toString(content.get("errorCount").getAsInt())+" errors, but found "+Integer.toString(ec), content.get("errorCount").getAsInt(), ec);
  }

  @Override
  public Base resolveConstant(Object appContext, String name) throws PathEngineException {
    return null;
  }

  @Override
  public TypeDetails resolveConstantType(Object appContext, String name) throws PathEngineException {
    return null;
  }

  @Override
  public boolean log(String argument, List<Base> focus) {
    return false;
  }

  @Override
  public FunctionDetails resolveFunction(String functionName) {
    return null;
  }

  @Override
  public TypeDetails checkFunction(Object appContext, String functionName, List<TypeDetails> parameters) throws PathEngineException {
    return null;
  }

  @Override
  public List<Base> executeFunction(Object appContext, String functionName, List<List<Base>> parameters) {
    return null;
  }

  @Override
  public Base resolveReference(Object appContext, String url) {
    return null;
  }

  @Override
  public Element fetch(Object appContext, String url) throws FHIRFormatError, DefinitionException, IOException, FHIRException {
    return null;
  }

  @Override
  public ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url) {
    return ReferenceValidationPolicy.IGNORE;
  }

  @Override
  public boolean resolveURL(Object appContext, String path, String url) throws IOException, FHIRException {
    return true;
  }

}
