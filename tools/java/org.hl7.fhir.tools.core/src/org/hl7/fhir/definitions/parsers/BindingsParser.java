package org.hl7.fhir.definitions.parsers;
/*
Copyright (c) 2011+, HL7, Inc
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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Enumerations.BindingStrength;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.terminologies.ValueSetUtilities;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.XLSXmlParser;
import org.hl7.fhir.utilities.XLSXmlParser.Sheet;

public class BindingsParser {

  private InputStream file;
  private String filename;
  private String version;
  private String root;
  private XLSXmlParser xls;
  private BindingNameRegistry registry;

  public BindingsParser(InputStream file, String filename, String root, BindingNameRegistry registry, String version) {
    this.file = file;
    this.filename = filename;
    this.root = root;
    this.registry = registry;
    this.version = version;
  }

  public List<BindingSpecification> parse() throws Exception {
    List<BindingSpecification> results = new ArrayList<BindingSpecification>();
    //		BindingSpecification n = new BindingSpecification();
    //		n.setName("*unbound*");
    //		n.setBinding(BindingSpecification.Binding.Unbound);
    //		results.add(n);

    xls = new XLSXmlParser(file, filename);
    Sheet sheet = xls.getSheets().get("Bindings");

    for (int row = 0; row < sheet.rows.size(); row++) {
      processLine(results, sheet, row);
    }		
    return results;
  }

  private void processLine(List<BindingSpecification> results, Sheet sheet, int row) throws Exception {
    BindingSpecification cd = new BindingSpecification("core", sheet.getColumn(row, "Binding Name"), true);
    if (!cd.getName().startsWith("!")) {
      if (Character.isLowerCase(cd.getName().charAt(0)))
        throw new Exception("binding name "+cd.getName()+" is illegal - must start with a capital letter");
      cd.setDefinition(sheet.getColumn(row, "Definition"));
      cd.setBindingMethod(readBinding(sheet.getColumn(row, "Binding")));
      String ref = sheet.getColumn(row, "Reference");
      if (!cd.getBinding().equals(BindingMethod.Unbound) && Utilities.noString(ref)) 
        throw new Exception("binding "+cd.getName()+" is missing a reference");
      if (cd.getBinding() == BindingMethod.CodeList) {
        cd.setValueSet(new ValueSet());
        cd.getValueSet().setId(ref.substring(1));
        cd.getValueSet().setUrl("http://hl7.org/fhir/vs/"+ref.substring(1));
        if (!ref.startsWith("#"))
          throw new Exception("Error parsing binding "+cd.getName()+": code list reference '"+ref+"' must started with '#'");
        Sheet cs = xls.getSheets().get(ref.substring(1));
        if (cs == null)
          throw new Exception("Error parsing binding "+cd.getName()+": code list reference '"+ref+"' not resolved");
        new CodeListToValueSetParser(cs, ref.substring(1), cd.getValueSet(), version).execute();
      } else if (cd.getBinding() == BindingMethod.ValueSet) {
        if (ref.startsWith("http:")) {
          cd.setReference(sheet.getColumn(row, "Reference")); // will sort this out later
        } else
          cd.setValueSet(loadValueSet(ref));
      } else if (cd.getBinding() == BindingMethod.Special) {
        cd.setValueSet(new ValueSet());
        cd.getValueSet().setId(ref.substring(1));
        cd.getValueSet().setUrl("http://hl7.org/fhir/vs/"+ref.substring(1));
        cd.getValueSet().setName(cd.getName());
        
        // do nothing more: this will get filled out once all the resources are loaded
      } else if (cd.getBinding() == BindingMethod.Reference) { 
        cd.setReference(sheet.getColumn(row, "Reference"));
      }
      cd.setReference(sheet.getColumn(row, "Reference")); // do this anyway in the short term

      
      cd.setId(registry.idForName(cd.getName()));
      if (cd.getValueSet() != null) {
        ValueSet vs = cd.getValueSet();
        ValueSetUtilities.makeShareable(vs);
        vs.setUserData("filename", vs.getId());
        vs.setUserData("path", vs.getId()+".html");

        ToolingExtensions.setOID(vs, BindingSpecification.DEFAULT_OID_VS + cd.getId());
        vs.setUserData("csoid", BindingSpecification.DEFAULT_OID_CS + cd.getId());
      }
      
      cd.setDescription(sheet.getColumn(row, "Description"));
      cd.setSource(filename);
      cd.setUri(sheet.getColumn(row, "Uri"));
      cd.setStrength(readBindingStrength(sheet.getColumn(row, "Conformance")));
      String oid = sheet.getColumn(row, "Oid");
      if (!Utilities.noString(oid))
        cd.setVsOid(oid); // no cs oid in this case
      cd.setWebSite(sheet.getColumn(row, "Website"));
      cd.setStatus(ConformanceResourceStatus.fromCode(sheet.getColumn(row, "Status")));
      cd.setEmail(sheet.getColumn(row, "Email"));
      cd.setV2Map(sheet.getColumn(row, "v2"));
      cd.setV3Map(sheet.getColumn(row, "v3"));

      results.add(cd);
    }
  }

  private ValueSet loadValueSet(String ref) throws Exception {
    String folder = new File(filename).getParent();
    IParser p;
    FileInputStream input;
    if (new File(Utilities.path(folder, ref+".xml")).exists()) {
      p = new XmlParser();
      input = new FileInputStream(Utilities.path(folder, ref+".xml"));
    } else if (new File(Utilities.path(folder, ref+".json")).exists()) {
      p = new JsonParser();
      input = new FileInputStream(Utilities.path(folder, ref+".json"));
    } else
      throw new Exception("Unable to find source for "+ref+" in "+filename+" ("+Utilities.path(folder, ref+".xml/json)"));

    try {
      ValueSet result = ValueSetUtilities.makeShareable((ValueSet) p.parse(input));
      result.setId(ref);
      result.setExperimental(true);
      if (!result.hasVersion())
        result.setVersion(version);
//      if (!result.hasUrl())
        result.setUrl("http://hl7.org/fhir/vs/"+ref.substring(9));
      return result;
    } finally {
      IOUtils.closeQuietly(input);
    }
  }

  public static BindingSpecification.BindingMethod readBinding(String s) throws Exception {
    s = s.toLowerCase();
    if (s == null || "".equals(s) || "unbound".equals(s))
      return BindingSpecification.BindingMethod.Unbound;
    if (s.equals("code list"))
      return BindingSpecification.BindingMethod.CodeList;
    if (s.equals("special"))
      return BindingSpecification.BindingMethod.Special;
    if (s.equals("reference"))
      return BindingSpecification.BindingMethod.Reference;
    if (s.equals("value set"))
      return BindingSpecification.BindingMethod.ValueSet;
    throw new Exception("Unknown Binding: "+s);
  }

  public static BindingStrength readBindingStrength(String s) throws Exception {
    s = s.toLowerCase();
    if (s.equals("required") || s.equals(""))
      return BindingStrength.REQUIRED;
    if (s.equals("extensible"))
      return BindingStrength.EXTENSIBLE;
    if (s.equals("preferred"))
      return BindingStrength.PREFERRED;
    if (s.equals("example"))
      return BindingStrength.EXAMPLE;
    throw new Exception("Unknown Binding Strength: '"+s+"'");
  }
}
