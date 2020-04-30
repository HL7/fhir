package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.definitions.uml.UMLDiagram.Point;
import org.hl7.fhir.definitions.uml.UMLDiagram.UMLDiagramAssociation;
import org.hl7.fhir.definitions.uml.UMLDiagram.UMLDiagramClass;
import org.hl7.fhir.definitions.uml.UMLDiagram.UMLDiagramGeneralization;
import org.hl7.fhir.definitions.uml.UMLEnumeration.UMLEnumerationCode;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UMLWriter {

  public static String toJson(UMLModel model) {
    JsonObject json = writeEntity(model);
    JsonArray arr = new JsonArray();
    json.add("packages", arr);
    for (UMLPackage p : model.getPackages()) {
      arr.add(writePackage(p));
    }
    return new GsonBuilder().setPrettyPrinting().create().toJson(json);
  }

  private static JsonObject writePackage(UMLPackage p) {
    JsonObject json = writeEntity(p);
    JsonArray arr = new JsonArray();
    json.add("definitions", arr);
    for (String n : sorted(p.getTypes().keySet())) {
      arr.add(writeType(p.getTypes().get(n)));
    }
    arr = new JsonArray();
    json.add("diagrams", arr);
    for (String n : sorted(p.getDiagrams().keySet())) {
      arr.add(writeDiagram(p.getDiagrams().get(n)));
    }
    return json;
  }

  private static JsonObject writeType(UMLType t) {
    if (t instanceof UMLClass) {
      return writeClass((UMLClass) t);
    } else if (t instanceof UMLPrimitive) {
      return writePrimitive(t);
    } else if (t instanceof UMLEnumeration) {
      return writeEnumeration((UMLEnumeration) t);
    } else {
      throw new Error("what?");
    }
  }

  private static JsonObject writeClass(UMLClass t) {
    JsonObject json = writeEntity(t);
    json.addProperty("class", t.getType().toString());
    json.addProperty("type", "Class");
    json.addProperty("abstract", t.isAbstract());
    json.addProperty("specializes", t.hasSpecialises() ? t.getSpecialises().getName() : null);
    JsonArray arr = new JsonArray();
    json.add("attributes", arr);
    for (UMLAttribute a : t.getAttributes()) {
      arr.add(writeAttribute(a));
    }
    json.add("associations", arr);
    for (UMLAssociation a : t.getAssociations()) {
      arr.add(writeAssociation(a));
    }    
    return json;
  }


  private static JsonObject writeAssociation(UMLAssociation a) {
    JsonObject json = writeFeature(a);
    json.addProperty("type", a.getType().getName());
    return json;
  }

  private static JsonObject writeAttribute(UMLAttribute a) {
    JsonObject json = writeFeature(a);
    json.addProperty("type", a.getType().getName());
    if (a.hasTypes()) {
      JsonArray arr = new JsonArray();
      json.add("types", arr);
      for (String s : a.getTypes()) {
        arr.add(s);
      }      
    }
    if (a.hasTargets()) {
      JsonArray arr = new JsonArray();
      json.add("targets", arr);
      for (String s : a.getTargets()) {
        arr.add(s);
      }      
    }
    if (a.hasBinding()) {
      json.addProperty("binding", a.getBinding().toString());
      json.addProperty("valueSet", a.getValueSet());
    }
    return json;
  }

  private static JsonObject writeFeature(UMLFeature f) {
    JsonObject json = writeEntity(f);
    json.addProperty("min", f.getMin());
    json.addProperty("max", f.getMax());
    return json;
  }

  private static JsonObject writeEnumeration(UMLEnumeration t) {
    JsonObject json = writeEntity(t);
    json.addProperty("class", "Enumeration");
    JsonArray arr = new JsonArray();
    for (UMLEnumerationCode c : t.getCodes()) {
      arr.add(writeEntity(c));
    }    
    return json;
  }

  private static JsonObject writePrimitive(UMLType t) {
    JsonObject json = writeEntity(t);
    json.addProperty("class", "Primitive");
    return json;
  }

  private static List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }

  private static JsonObject writeEntity(UMLEntity p) {
    JsonObject json = new JsonObject();
    json.addProperty("name", p.getName());
    if (p.hasDocumentation()) {
      json.addProperty("documentation", p.getDocumentation());
    }
    return json;    
  }

  private static JsonObject writeDiagram(UMLDiagram d) {
    JsonObject json = writeEntity(d);
    json.add("size", writePoint(d.getSize()));
    JsonArray arr = new JsonArray();
    json.add("classes", arr);
    for (UMLDiagramClass dc : d.getClasses()) {
      arr.add(writeClass(dc));
    }
    json.add("assocations", arr);
    for (UMLDiagramAssociation dc : d.getAssocations()) {
      arr.add(writeAssociation(dc));
    }
    arr = new JsonArray();
    json.add("generalizations", arr);
    for (UMLDiagramGeneralization dc : d.getGeneralizations()) {
      arr.add(writeGeneralization(dc));
    }
    return json;
  }

  private static JsonObject writeClass(UMLDiagramClass d) {
    JsonObject json = new JsonObject();
    json.addProperty("attributes", d.isShowAttributes());
    json.add("topLeft", writePoint(d.getTopLeft()));
    json.add("bottemRight", writePoint(d.getTopLeft()));
    json.addProperty("class", d.getDetails().getName());
    return json;
  }

  private static JsonObject writeAssociation(UMLDiagramAssociation d) {
    JsonObject json = new JsonObject();
    json.add("start", writePoint(d.getStart()));
    json.add("end", writePoint(d.getEnd()));
    json.addProperty("class", d.getDetails().getName());
    return json;
  }

  private static JsonObject writeGeneralization(UMLDiagramGeneralization d) {
    JsonObject json = new JsonObject();
    json.add("start", writePoint(d.getStart()));
    json.add("end", writePoint(d.getEnd()));
    json.addProperty("general", d.getGeneral().getName());
    json.addProperty("special", d.getSpecial().getName());
    return json;
  }

  private static JsonObject writePoint(Point d) {
    JsonObject json = new JsonObject();
    json.addProperty("x", d.getX());
    json.addProperty("y", d.getY());
    return json;
  }


  public static String toText(UMLModel model) {
    StringBuilder b = new StringBuilder();
    if (model.hasDocumentation()) {
      writeDoco(b, model, 0);
      b.append("\r\n\r\n");
    }
    for (UMLPackage p : model.getPackages()) {
      writeEntity(b, "package", p);
      writePackage(b, p);
      b.append("end-package\r\n");
    }    
    return b.toString();
  }

  private static void writePackage(StringBuilder b, UMLPackage p) {
    b.append("\r\n");
    boolean first = true;
    for (UMLPrimitive pt : p.getPrimitives()) {
      writeEntity(b, "primitive", pt);
      first = false;
    }
    if (!first) {
      b.append("\r\n");
    }
      
    writeClasses(b, p, null, new HashSet<>());
    for (UMLEnumeration e : p.getEnums()) {
      writeEnumeration(b, e);
    }
    for (String n : sorted(p.getDiagrams().keySet())) {
      writeDiagram(b, p.getDiagrams().get(n));      
    }
  }

  private static void writeEnumeration(StringBuilder b, UMLEnumeration e) {
    writeEntity(b, "enum", e);
    for (UMLEnumerationCode c : e.getCodes()) {
      writeEntity(b, "  ", c);
    }
    b.append("end\r\n\r\n");    
  }

  private static void writeClasses(StringBuilder b, UMLPackage p, UMLType parent, Set<String> done) {
    for (UMLClass c : p.getClassesByParent(parent)) {
      if (!done.contains(c.getName())) {
        done.add(c.getName());
        writeClassGroup(b, c, done);
        writeClasses(b, p, c, done);
      }
    }    
  }

  private static void writeClassGroup(StringBuilder b, UMLClass c, Set<String> done) {
    Set<UMLClass> todo = writeClass(b, c);
    for (UMLClass s : todo) {
      if (!done.contains(s.getName())) {
        done.add(s.getName());
        writeClassGroup(b, c, done);
      }
    }
  }

  private static Set<UMLClass> writeClass(StringBuilder b, UMLClass c) {
    Set<UMLClass> res = new HashSet<>();
    switch (c.getType()) {
    case Class:
      b.append("class ");
      break;
    case Interface:
      b.append("interface ");
      break;
    case Pattern:
      b.append("pattern ");
      break;
    }
    b.append(c.getName());
    if (c.hasSpecialises()) {
      b.append(" specializes ");
      b.append(c.getSpecialises().getName());
    }
    if (c.isAbstract()) {
      b.append(" «Abstract»");
    }
    writeDoco(b, c, 0);
    b.append("\r\n");    
    if (c.hasAttributes()) {
      b.append("attributes\r\n");
      for (UMLAttribute a : c.getAttributes()) {
        writeAttribute(b, a);
      }
    }
    if (c.hasAssociations()) {
      b.append("associations\r\n");
      for (UMLAssociation a : c.getAssociations()) {
        writeAssociation(b, a);
        res.add(a.getType());
      }
    }
    b.append("end\r\n\r\n");
    return res;
  }

  private static void writeAssociation(StringBuilder b, UMLAssociation a) {
    b.append("  ");
    b.append(a.getName());
    b.append(" : ");
    b.append(a.getType().getName());
    b.append(" [");
    b.append(a.getMin());
    b.append("..");
    b.append("]");
    writeDoco(b, a, 2);
    b.append("\r\n");    
  }

  private static void writeAttribute(StringBuilder b, UMLAttribute a) {
    b.append("  ");
    b.append(a.getName());
    b.append(" : ");
    b.append(a.getType().getName());
    b.append(" [");
    b.append(a.getMin());
    b.append("..");
    b.append("]");
    if (a.hasTypes()) {
      b.append(" «"+listed(a.getTypes(), "|")+"»");
    } 
    if (a.hasTargets()) {
      b.append(" «"+listed(a.getTargets(), "|")+"»");      
    }
    if (a.hasBinding()) {
      b.append(" «"+a.getValueSet()+a.getBinding().toCode()+"»");
    }
    writeDoco(b, a, 2);
    b.append("\r\n");
  }

  private static String listed(List<String> list, String ch) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder(ch);
    for (String s : list) {
      b.append("s");
    }
    return b.toString();
  }

  private static void writeDiagram(StringBuilder b, UMLDiagram d) {
    writeEntity(b, "diagram", d);
    b.append("  size: ");
    writePoint(b, d.getSize());
    b.append("\r\n");
    for (UMLDiagramClass dc : d.getClasses()) {
      writeClass(b, dc);
    }
    for (UMLDiagramAssociation dc : d.getAssocations()) {
      writeAssociation(b, dc);
    }
    for (UMLDiagramGeneralization dc : d.getGeneralizations()) {
      writeGeneralization(b, dc);
    }
    b.append("end\r\n\r\n");    
  }

  private static void writeClass(StringBuilder b, UMLDiagramClass d) {
    b.append("  class ");
    writePoint(b,d.getTopLeft());
    b.append("..");
    writePoint(b, d.getTopLeft());
    b.append(": ");
    b.append(d.getDetails().getName());
    if (!d.isShowAttributes()) {
      b.append(" {no-attributes}");
    }
    b.append("\r\n");
  }

  private static void writeAssociation(StringBuilder b, UMLDiagramAssociation d) {
    b.append("  association ");
    writePoint(b,d.getStart());
    b.append("-->");
    writePoint(b, d.getEnd());
    b.append(": ");
    b.append(d.getDetails().getName());
    b.append("\r\n");
  }

  private static void writeGeneralization(StringBuilder b, UMLDiagramGeneralization d) {
    b.append("  generalization ");
    writePoint(b,d.getStart());
    b.append("-->");
    writePoint(b, d.getEnd());
    b.append(": ");
    b.append(d.getSpecial().getName());
    b.append(" --> ");
    b.append(d.getGeneral().getName());
    b.append("\r\n");
  }

  private static void writePoint(StringBuilder b, Point p) {
    b.append("(");
    b.append(p.getX());
    b.append(",");
    b.append(p.getY());
    b.append(")");    
  }


  private static void writeEntity(StringBuilder b, String name, UMLEntity e) {
    b.append(name);
    b.append(" ");
    b.append(e.getName());
    writeDoco(b, e, firstNonSpace(name));
    b.append("\r\n");    
  }

  private static int firstNonSpace(String name) {
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) != ' ') {
        return i;
      }
    }
    return 0;
  }

  private static void writeDoco(StringBuilder b, UMLEntity e, int indent) {
    if (e.hasDocumentation()) {
      if (e.getDocumentation().contains("\n")) {
        for (String line : e.getDocumentation().split("\\r?\\n")) {
          b.append("\r\n");    
          for (int i = 0; i < indent; i++) {
            b.append(" ");
          }
          b.append("  # ");
          b.append(line);
        }
      } else {
        b.append(" # ");
        b.append(e.getDocumentation());
      }
    }
  }
}
