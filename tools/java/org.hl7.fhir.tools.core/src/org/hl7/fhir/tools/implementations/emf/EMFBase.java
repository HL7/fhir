package org.hl7.fhir.tools.implementations.emf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;


public abstract class EMFBase {
  
  protected Definitions definitions;
  protected ByteArrayOutputStream b;
  protected Writer w;
  
  protected void init(Writer w) throws UnsupportedEncodingException {
    if (w == null) {
      b = new ByteArrayOutputStream();
      this.w = new OutputStreamWriter(b, "UTF-8");
    } else
      this.w = w;
  }
  
  protected void write(String s) throws IOException {
    w.append(s.replace("\n", " ").replace("\r", "")+"\r\n");
  }

  protected void write(String s, String doco) throws IOException {
    if (doDoco()) {
      write(s+"    // "+doco);
    } else {
      write(s);
    }
  }

  
  private boolean doDoco() {
    return true;
  }

  protected void writeDirect(String s) throws IOException {
    w.append(s+"\r\n");
  }

  
 

  protected String getTypeName(ElementDefn e) throws Exception {
    if (e.getTypes().size() > 1) {
      return "Type";
    } else if (e.getTypes().size() == 0) {
      throw new Exception("not supported");
    } else {
      return getTypename(e.getTypes().get(0));
    }
  }


  protected String getTypeName(String tn) {
    if (tn.equals("string")) {
      return "String";
    } else if (tn.equals("Any")) {
      return "Resource";
    } else {
      return getTitle(tn);
    }
  }
  
  protected String getTypename(TypeRef type) throws Exception {
    if (type.getParams().size() == 1) {     
      if (type.isResourceReference())
        return "ResourceReference";
      else if (type.getName().equals("Interval"))
        return "Interval<"+getTypeName(type.getParams().get(0))+">";
      else
        throw new Exception("not supported");
    } else if (type.getParams().size() > 1) {
      if (type.isResourceReference())
        return "ResourceReference";
      else
        throw new Exception("not supported");
    } else {
      return getTypeName(type.getName());
    }
  }
  protected String getTitle(String name) {
    return Utilities.noString(name) ? "Value" : name.substring(0, 1).toUpperCase()+ name.substring(1);
  }
  
  protected String getElementName(String name, boolean alone) {
    if (name.equals("[type]"))
      return "value";
    else if ((alone && GeneratorUtils.isJavaReservedWord(name)) || (!alone && name.equals("class")))
      return name+"_";
    else if (name.equals("[x]"))
      return "value";
    else
      return name.replace("[x]", "");
  }
  
}
