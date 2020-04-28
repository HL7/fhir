package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class XVerPathsGenerator {

  public class ElementInfo {
    private List<String> types = new ArrayList<>();
    private List<String> elements = new ArrayList<>();
    private String path;
  }


  private Definitions definitions;
  private String dest;
  private String r4Source;

  private Map<String, ElementInfo> r4List = new HashMap<>();
  private List<ElementInfo> r5List = new ArrayList<>();
  
  public XVerPathsGenerator(Definitions definitions, String dest, String r4Source) {
    super();
    this.definitions = definitions;
    this.dest = dest;
    this.r4Source = r4Source;
  }


  public void execute() throws IOException {
    // load the R4 content; can't be an extension if it already matches R4
    loadR4();
    // iterate all the elements, building a list
    for (String s : sorted(definitions.getTypes().keySet())) {
      ElementDefn ed = definitions.getTypes().get(s);
      process(ed);
    }
    for (String s : sorted(definitions.getBaseResources().keySet())) {
      ResourceDefn ed = definitions.getBaseResources().get(s);
      process(ed.getRoot());
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn ed = definitions.getResources().get(s);
      process(ed.getRoot());
    }
    StringBuilder r5 = new StringBuilder();
    r5.append("{\r\n");
    int i = 0;
    for (ElementInfo ei : r5List) {
      r5.append(" \""+ei.path+"\": { ");
      if (ei.types.size() > 0) {
        r5.append( "\"types\": [");
        boolean first = true;
        for (String s : ei.types) {
          if (first) first = false; else r5.append(", ");
          r5.append("\""+s+"\"");
        }
        r5.append("]");
      } else
      if (ei.elements.size() > 0) {
        r5.append( "\"elements\": [");
        boolean first = true;
        for (String s : ei.elements) {
          if (first) first = false; else r5.append(", ");
          r5.append("\""+s+"\"");
        }
        r5.append("]");
      }
      i++;
      if (i == r5List.size()) {
        r5.append(" }\r\n");
      } else {
        r5.append(" },\r\n");        
      }
    }
    r5.append("}\r\n");
    TextFile.stringToFile(r5.toString(), dest);
  }


  private void process(ElementDefn ed) {
    for (ElementDefn child : ed.getElements()) {
      process(child, ed.getName()+"."+child.getName());      
    }
  }


  private void process(ElementDefn ed, String path) {
    ElementInfo ei = new ElementInfo();
    ei.path = path;
    r5List.add(ei);
    ElementInfo r4 = r4List.get(path);
    if (ed.getTypes().size() > 0) {
      // leaf
      for (TypeRef tr : ed.getTypes()) {
        if (r4 == null || !r4.types.contains(tr.summary())) {
          ei.types.add(tr.summary());
        }
      }
    } else {
      for (ElementDefn child : ed.getElements()) {
        if (r4 == null || !r4.elements.contains(child.getName())) {
          ei.elements.add(child.getName());
        }
        process(child, path+"."+child.getName());      
      }
    }
  }


  private List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }


  private void loadR4() throws IOException {
    JsonObject r4 = JsonTrackingParser.parseJson(new File(r4Source)); 
    for (Entry<String, JsonElement> e : r4.entrySet()) {
      JsonObject eo = (JsonObject) e.getValue();
      ElementInfo ei = new ElementInfo();
      if (eo.has("types")) {
        for (JsonElement s : eo.getAsJsonArray("types")) {
          ei.types.add(s.getAsString());
        }
      }
      if (eo.has("elements")) {
        for (JsonElement s : eo.getAsJsonArray("elements")) {
          ei.elements.add(s.getAsString());
        }
      }
      if (!ei.elements.isEmpty() || !ei.types.isEmpty()) {
        r4List.put(e.getKey(), ei);
      }
    }
  }
}
