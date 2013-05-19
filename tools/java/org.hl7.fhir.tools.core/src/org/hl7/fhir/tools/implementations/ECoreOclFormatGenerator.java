package org.hl7.fhir.tools.implementations;
/*
Copyright (c) 2011-2013, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;

public class ECoreOclFormatGenerator  extends OutputStreamWriter {

      
  private Map<String, ElementDefn> waitingElements = new HashMap<String, ElementDefn>();

  public ECoreOclFormatGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out, "UTF-8");
  }
  
  public void generate(Definitions definitions, String version, Date genDate) throws Exception {
    writeLine("module _'FHIR.ecore'");
    writeLine("import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';");
    writeLine("");
    writeLine("package DataTypes : dt = 'http://hl7.org/fhir/datatypes'");
    writeLine("{");
    for (String name : definitions.getResources().keySet()) {
      if (isDataType(name, definitions))
        generateResource(name, definitions.getResources().get(name));
    }
    writeLine("}");
    writeLine("package Resources : res = 'http://hl7.org/fhir/resources'");
    writeLine("{");
    for (String name : definitions.getResources().keySet()) {
      if (!isDataType(name, definitions))
        generateResource(name, definitions.getResources().get(name));
    }
    writeLine("}");
    flush();
    close();
  }

  private boolean isDataType(String name, Definitions definitions) {
//    for (TypeDefn t : definitions.getDatatypes())
//      if (t.getName().equals(name))
//        return true;
    return false;
  }

  private void writeLine(String string) throws IOException {
    write(string+"\r\n");    
  }

  private void generateResource(String name, ResourceDefn resourceDefn) throws Exception {
    generateType(name, resourceDefn.getRoot());
    processWaitingElements();
  }

  private void generateType(String name, ElementDefn elementDefn) throws Exception {
    if (elementDefn.getTypes().size() == 1 && elementDefn.getTypes().get(0).getName().equals("GenericType")) 
      writeLine(addDefn("  class "+name+"<T> extends Type", elementDefn));
    else if (elementDefn.getTypes().size() == 1)
      writeLine(addDefn("  class "+name+" extends "+elementDefn.getTypes().get(0).getName(), elementDefn));
    else
      writeLine(addDefn("  class "+name, elementDefn));
    writeLine("  {");
    for (ElementDefn e : elementDefn.getElements()) {
      generateElement(name, e);
    }
    writeLine("  }");
    writeLine("");
  }

  private void processWaitingElements() throws Exception {
    while (!waitingElements.isEmpty()) {
      String n = waitingElements.keySet().iterator().next();
      ElementDefn e = waitingElements.get(n);
      waitingElements.remove(n);
      generateType(n, e);
    }    
  }

  private void generateElement(String name, ElementDefn e) throws Exception {
    if (e.getElements().size() == 0) {
      if (e.getName().equals("extension"))
        writeLine(addDefn("    attribute "+e.getName()+" : Extensions;", e));
      else {
        if (e.getTypes().size() == 0)
          throw new Exception("element "+name+"::"+e.getName()+" has no children and no type");
        String n = e.getName();
        if (n.equals("[type]"))
          n = "type";
        if (n.endsWith("[x]"))
          n = n.substring(0, n.length()-3);
        
        if  (e.getTypes().size() > 1)
          writeLine(addDefn("    attribute "+n+" : ?;", e));
        else
        writeLine(addDefn("    attribute "+n+" : "+ecoreType(e.getTypes().get(0))+";", e));
      }
    } else {
      if (e.usesCompositeType()) {
        writeLine(addDefn("    attribute "+e.getName()+" : "+ecoreType(e.getElements().get(0).getTypes().get(0))+";", e));
      }
      else {
        String n = name+"_"+e.getName();
        writeLine(addDefn("    attribute "+e.getName()+" : "+n+";", e));
        waitingElements.put(n, e);
      }
    }
  }

  private String addDefn(String line, ElementDefn e) {
    if (e.getDefinition() == null || e.getDefinition().equals(""))
      return line;
    
    String res = line;
    while (res.length() < 44)
      res = res + " ";
    return res + " -- "+e.getDefinition();
  }

  private String ecoreType(TypeRef typeDefn) {
    if (typeDefn.getName().equals("string"))
      return "String";
    else if (typeDefn.isUnboundGenericParam())
      return "T";
    else if (typeDefn.isWildcardType())
      return "object";
    else if (typeDefn.isIdRef())
      return "String";  
    else if (typeDefn.getName().charAt(0) == '@')
      return typeDefn.getName().substring(1).replace('.', '_');  
    
    else
      return typeDefn.getName();
  }
  
}
