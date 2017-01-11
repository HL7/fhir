package org.hl7.fhir.definitions.generators.specification.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonLDDefinitionsGenerator {

  private String genDate;
  private String version;
  private BuildWorkerContext workerContext;

  public void generate(Definitions definitions, IniFile ini, String tmpResDir, String dstDir, String srcDir, String version, String genDate, BuildWorkerContext workerContext) throws Exception {
	  this.genDate = genDate;
	  this.version = version;
	  this.workerContext = workerContext;

	  JsonObject defn = new JsonObject();
	  JsonObject context = new JsonObject();
	  defn.add("@context", context);
	  defn.addProperty("@id", "http://hl7.org/fhir/"); 
	  context.addProperty("fhir", "http://hl7.org/fhir/");
	  context.addProperty("xsd", "http://www.w3.org/2001/XMLSchema#");

	  // properties for primitives, helpers, format features
	  addProperty(context, "value", "fhir:value", "xsd:string");
    addProperty(context, "decimal", "fhir:value", "xsd:decimal");
    addProperty(context, "integer", "fhir:value", "xsd:integer");
    addProperty(context, "boolean", "fhir:value", "xsd:boolean");
    addProperty(context, "binary", "fhir:value", "xsd:base64Binary");
    addProperty(context, "date", "fhir:value", "xsd:date");
    addProperty(context, "dateTime", "fhir:value", "xsd:dateTime");
    addProperty(context, "gYearMonth", "fhir:value", "xsd:gYearMonth");
    addProperty(context, "gYear", "fhir:value", "xsd:gYear");
    addProperty(context, "link", "fhir:link", "@id");
    addProperty(context, "concept", "fhir:concept", "@id");
    addProperty(context, "index", "fhir:index", "xsd:integer");
    addProperty(context, "role", "fhir:nodeRole", "@id");

    // elements defined in FHIR:
	  for (TypeRef tr : definitions.getKnownTypes()) {
	    if (!definitions.hasPrimitiveType(tr.getName()) && !tr.getName().equals("SimpleQuantity")) {
	      TypeDefn root = definitions.getElementDefn(tr.getName());
	      new JsonLDGenerator(definitions, workerContext, definitions.getKnownTypes()).generate(context, root, version, genDate);
	    }
    }

    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getResources().keySet());
  	names.addAll(definitions.getBaseResources().keySet());	
    names.add("Parameters");
    Collections.sort(names);
    for (String name : names) {
      ResourceDefn root = definitions.getResourceByName(name);
      new JsonLDGenerator(definitions, workerContext, definitions.getKnownTypes()).generate(context, root.getRoot(), version, genDate);
	  }
    save(defn, dstDir+"fhir.jsonld");
  }

  private void addProperty(JsonObject context, String name, String id, String type) {
    JsonObject v = new JsonObject();
    v.addProperty("@id", id);
    if (type != null)
      v.addProperty("@type", type);
    context.add(name, v);  
  }

  private void save(JsonObject s, String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(s);
    TextFile.stringToFile(json, filename);
  }


}
