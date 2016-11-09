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

	  for (TypeRef tr : definitions.getKnownTypes()) {
	    if (!definitions.getPrimitives().containsKey(tr.getName()) && !definitions.getConstraints().containsKey(tr.getName())) {
        TypeDefn root = definitions.getElementDefn(tr.getName());
        JsonObject s = new JsonLDGenerator(definitions, workerContext, definitions.getKnownTypes()).generate(root, version, genDate);
        save(s, dstDir+root.getName().replace(".",  "_")+".jsonld");
      }
    }

    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getResources().keySet());
  	names.addAll(definitions.getBaseResources().keySet());
	
    names.add("Parameters");
    Collections.sort(names);
    for (String name : names) {
      ResourceDefn root = definitions.getResourceByName(name);
      JsonObject s = new JsonLDGenerator(definitions, workerContext, definitions.getKnownTypes()).generate(root.getRoot(), version, genDate);
      save(s, dstDir+root.getName().replace(".",  "_")+".jsonld");
	  }
  }

  private void addAllResourcesChoice(JsonObject schema, List<String> names) {
    JsonObject definitions = schema.getAsJsonObject("definitions");
    JsonObject rlist = new JsonObject();
    definitions.add("ResourceList", rlist);
    JsonArray oneOf = new JsonArray();
    rlist.add("oneOf", oneOf);
    for (String n : names) {
      JsonObject ref = new JsonObject();
      ref.addProperty("$ref", "#/definitions/"+n);
      oneOf.add(ref);
    }
  }
  
  private JsonObject generateAllResourceChoice(List<String> names) {
		JsonObject definitions;
		JsonObject schema;
		
		schema = new JsonObject();
		schema.addProperty("$schema", "http://json-schema.org/draft-04/schema#");
		schema.addProperty("id", "http://hl7.org/fhir/json-schema/ResourceList");
		schema.addProperty("$ref", "#/definitions/ResourceList");
		schema.addProperty("description", "see http://hl7.org/fhir/json.html#schema for information about the FHIR Json Schemas");
		definitions = new JsonObject();
		schema.add("definitions", definitions);
		
		JsonObject rlist = new JsonObject();
		definitions.add("ResourceList", rlist);
		JsonArray oneOf = new JsonArray();
		rlist.add("oneOf", oneOf);
		for (String n : names) {
			JsonObject ref = new JsonObject();
			ref.addProperty("$ref", n.replace(".",  "_")+".schema.json#/definitions/"+n);
			oneOf.add(ref);
		}
		return schema;
  }

  private void save(JsonObject s, String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(s);
    TextFile.stringToFile(json, filename);
  }

  private void produceCombinedSchema(Definitions definitions, String xsdDir, String dstDir, String srcDir) throws Exception {
    String src = TextFile.fileToString(srcDir + "fhir-all.schema.json");
    src = processSchemaIncludes(definitions, "fhir-all.schema.json", src, false);
    TextFile.stringToFile(src, xsdDir + "fhir-all.schema.json");
  }

  private String processSchemaIncludes(Definitions definitions, String filename, String src, boolean singleMode) throws Exception {
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getResources().keySet());
    names.add("Parameters");
    Collections.sort(names);
    while (src.contains("<!--%") || src.contains("<%"))
    {
      int i2;
      String s1;
      String s2;
      String s3;

      int i1 = src.indexOf("<!--%");
      if (i1 > -1) {
        i2 = src.indexOf("%-->");
        s1 = src.substring(0, i1);
        s2 = src.substring(i1 + 5, i2).trim();
        s3 = src.substring(i2 + 4);
      } else {
        i1 = src.indexOf("<%");
        i2 = src.indexOf("%>");
        s1 = src.substring(0, i1);
        s2 = src.substring(i1 + 2, i2).trim();
        s3 = src.substring(i2 + 2);
      } 

      String[] com = s2.split(" ");
      if (com[0].equals("genDate"))
        src = s1+ genDate+s3;
      else if (com[0].equals("version"))
        src = s1+version+s3;
      else if (com[0].equals("includes")) {
        if (singleMode)
          src = s1+"<xs:import namespace=\"http://hl7.org/fhir\" schemaLocation=\"fhir-single.schema.json\"/>"+s3;
        else
          src = s1+"<xs:import namespace=\"http://hl7.org/fhir\" schemaLocation=\"fhir-all.schema.json\"/>"+s3;
      } else if (com[0].equals("resources")) {
        StringBuilder includes = new StringBuilder();
        for (String n : names) 
          includes.append("  <xs:include schemaLocation=\""+n.toLowerCase()+".schema.json\"/>\r\n");
        src = s1+includes.toString()+s3;
      }
      else if (com[0].equals("atom.imports")) {
        StringBuilder includes = new StringBuilder();
        for (String n : names) 
          includes.append("  <xs:import namespace=\"http://hl7.org/fhir\" schemaLocation=\""+n+".schema.json\"/>\r\n");
        src = s1+includes.toString()+s3;
      }
      else if (com[0].equals("atom.elements")) {
        StringBuilder includes = new StringBuilder();
        for (String n : names) 
          includes.append("      <xs:element ref=\"fhir:"+n+"\"/>\r\n");
        src = s1+includes.toString()+s3;
      }
      else if (com[0].equals("enum")) {
        Collection<DefinedCode> values;
        if (com[1].equals("resource")) {
          values = definitions.getKnownResources().values();          
        } else {
          throw new Error("fix this");
//          values = definitions.getBindingByName(com[1]).getCodes();
        }
        StringBuilder enums = new StringBuilder();
        for (DefinedCode c : values) {
        	if (!c.getAbstract()) {
	          enums.append("        <xs:enumeration value=\""+c.getCode()+"\">\r\n");
	          enums.append("          <xs:annotation>\r\n");
	          enums.append("            <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(c.getDefinition())+"</xs:documentation>\r\n");
	          for (String l : c.getLangs().keySet())
	            enums.append("            <xs:documentation xml:lang=\""+l+"\">"+Utilities.escapeXml(c.getLangs().get(l))+"</xs:documentation>\r\n");
	          enums.append("          </xs:annotation>\r\n");
	          enums.append("        </xs:enumeration>\r\n");
	        }
        }
        src = s1+enums.toString()+s3;
      }
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+filename);
    }
    return src;
  }  
}
