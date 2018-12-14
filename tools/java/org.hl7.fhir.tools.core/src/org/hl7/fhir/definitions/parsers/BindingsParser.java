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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.r4.model.Constants;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.r4.formats.IParser;
import org.hl7.fhir.r4.formats.JsonParser;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.ConceptMap;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.BindingStrength;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r4.terminologies.ValueSetUtilities;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xls.XLSXmlNormaliser;
import org.hl7.fhir.utilities.xls.XLSXmlParser;
import org.hl7.fhir.utilities.xls.XLSXmlParser.Sheet;

public class BindingsParser {

  private InputStream file;
  private String filename;
  private String version;
  private String root;
  private XLSXmlParser xls;
  private OIDRegistry registry;
  private Map<String, CodeSystem> codeSystems;
  private Map<String, ConceptMap> maps;
  private Calendar genDate;
  private boolean exceptionIfExcelNotNormalised;
  
  public BindingsParser(InputStream file, String filename, String root, OIDRegistry registry, String version, Map<String, CodeSystem> codeSystems, Map<String, ConceptMap> maps, Calendar genDate, boolean exceptionIfExcelNotNormalised) {
    this.file = file;
    this.filename = filename;
    this.root = root;
    this.registry = registry;
    this.version = version;
    this.codeSystems = codeSystems;
    this.maps = maps;
    this.genDate = genDate;
    this.exceptionIfExcelNotNormalised = exceptionIfExcelNotNormalised;
  }

  public List<BindingSpecification> parse() throws Exception {
    List<BindingSpecification> results = new ArrayList<BindingSpecification>();
    //		BindingSpecification n = new BindingSpecification();
    //		n.setName("*unbound*");
    //		n.setBinding(BindingSpecification.Binding.Unbound);
    //		results.add(n);

    xls = new XLSXmlParser(file, filename);
    new XLSXmlNormaliser(filename, exceptionIfExcelNotNormalised).go();
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
      cd.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
      cd.setBindingMethod(readBinding(sheet.getColumn(row, "Binding"), cd.getName()+" in "+filename));
      boolean utg = "y".equals(sheet.getColumn(row, "UTG"));
      String ref = sheet.getColumn(row, "Reference");
      if (!cd.getBinding().equals(BindingMethod.Unbound) && Utilities.noString(ref)) 
        throw new Exception("binding "+cd.getName()+" is missing a reference");
      if (cd.getBinding() == BindingMethod.CodeList) {
        cd.setValueSet(new ValueSet());
        cd.getValueSet().setId(ref.substring(1));
        cd.getValueSet().setUrl("http://hl7.org/fhir/ValueSet/"+ref.substring(1));
        cd.getValueSet().setVersion(Constants.VERSION);
        
        if (!Utilities.noString(sheet.getColumn(row, "Committee"))) {
          cd.getValueSet().addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType(sheet.getColumn(row, "Committee").toLowerCase()));
        }
        cd.getValueSet().setUserData("filename", "valueset-"+cd.getValueSet().getId());
        cd.getValueSet().setUserData("path", "valueset-"+cd.getValueSet().getId()+".html");
        cd.getValueSet().setName(cd.getName());
        cd.getValueSet().setTitle(cd.getName());
        cd.getValueSet().setDateElement(new DateTimeType(genDate));
        cd.getValueSet().setStatus(PublicationStatus.DRAFT);
        cd.getValueSet().setDescription(sheet.getColumn(row, "Description"));
        if (!cd.getValueSet().hasDescription())
          cd.getValueSet().setDescription(cd.getDefinition());
        if (!ref.startsWith("#"))
          throw new Exception("Error parsing binding "+cd.getName()+": code list reference '"+ref+"' must started with '#'");
        Sheet cs = xls.getSheets().get(ref.substring(1));
        if (cs == null)
          throw new Exception("Error parsing binding "+cd.getName()+": code list reference '"+ref+"' not resolved");
        new CodeListToValueSetParser(cs, ref.substring(1), cd.getValueSet(), version, codeSystems, maps).execute(sheet.getColumn(row, "v2"), sheet.getColumn(row, "v3"), utg);
      } else if (cd.getBinding() == BindingMethod.ValueSet) {
        if (ref.startsWith("http:")) {
          cd.setReference(sheet.getColumn(row, "Reference")); // will sort this out later
        } else
          cd.setValueSet(loadValueSet(ref, sheet.getColumn(row, "Committee").toLowerCase()));
        String max = sheet.getColumn(row, "Max");
        if (!Utilities.noString(max))
          if (max.startsWith("http:")) {
            cd.setMaxReference(max); // will sort this out later
          } else
            cd.setMaxValueSet(loadValueSet(max, sheet.getColumn(row, "Committee").toLowerCase()));
      } else if (cd.getBinding() == BindingMethod.Special) {
        cd.setValueSet(new ValueSet());
        cd.getValueSet().setId(ref.substring(1));
        cd.getValueSet().setUrl("http://hl7.org/fhir/ValueSet/"+ref.substring(1));
        cd.getValueSet().setVersion(Constants.VERSION);
        cd.getValueSet().setName(cd.getName());
        
        // do nothing more: this will get filled out once all the resources are loaded
      }
      cd.setReference(sheet.getColumn(row, "Reference")); // do this anyway in the short term

      
      if (cd.getValueSet() != null) {
        touchVS(cd.getValueSet());
        ValueSetUtilities.markStatus(cd.getValueSet(), Utilities.noString(sheet.getColumn(row, "Committee")) ? "vocab" : sheet.getColumn(row, "Committee").toLowerCase(), null, null, Utilities.noString(sheet.getColumn(row, "FMM")) ? null : sheet.getColumn(row, "FMM"), null, Utilities.noString(sheet.getColumn(row, "Normative-Version")) ? null : sheet.getColumn(row, "Normative-Version"));
      }
      if (cd.getMaxValueSet() != null) {
        touchVS(cd.getMaxValueSet());
        ValueSetUtilities.markStatus(cd.getMaxValueSet(), Utilities.noString(sheet.getColumn(row, "Committee")) ? "vocab" : sheet.getColumn(row, "Committee").toLowerCase(), null, null, Utilities.noString(sheet.getColumn(row, "FMM")) ? null : sheet.getColumn(row, "FMM"), null, Utilities.noString(sheet.getColumn(row, "Max-Normative-Version")) ? null : sheet.getColumn(row, "Max-Normative-Version"));
      }
      
      cd.setDescription(sheet.getColumn(row, "Description"));
      cd.setSource(filename);
      cd.setUri(sheet.getColumn(row, "Uri"));
      cd.setStrength(readBindingStrength(sheet.getColumn(row, "Conformance")));
      String oid = sheet.getColumn(row, "Oid");
      if (!Utilities.noString(oid))
        cd.setVsOid(oid); // no cs oid in this case
      cd.setWebSite(sheet.getColumn(row, "Website"));
      cd.setStatus(PublicationStatus.fromCode(sheet.getColumn(row, "Status")));
      cd.setEmail(sheet.getColumn(row, "Email"));
      cd.setV2Map(sheet.getColumn(row, "v2"));
      cd.setV3Map(sheet.getColumn(row, "v3"));

      results.add(cd);
    }
  }

  private void touchVS(ValueSet vs) throws FHIRFormatError, URISyntaxException {
    ValueSetUtilities.makeShareable(vs);
    if (!ValueSetUtilities.hasOID(vs))
      ValueSetUtilities.setOID(vs, "urn:oid:"+BindingSpecification.DEFAULT_OID_VS +registry.idForUri(vs.getUrl()));

    if (vs.getUserData("cs") != null)
      if (!CodeSystemUtilities.hasOID((CodeSystem) vs.getUserData("cs")))
        CodeSystemUtilities.setOID((CodeSystem) vs.getUserData("cs"), "urn:oid:"+BindingSpecification.DEFAULT_OID_CS + registry.idForUri(((CodeSystem) vs.getUserData("cs")).getUrl()));
  }

  private ValueSet loadValueSet(String ref, String committee) throws Exception {
    String folder = new File(filename).getParent();
    String srcName;
    IParser p;
    if (new File(Utilities.path(folder, ref+".xml")).exists()) {
      p = new XmlParser();
      srcName = Utilities.path(folder, ref+".xml");
    } else if (new File(Utilities.path(folder, ref+".json")).exists()) {
      p = new JsonParser();
      srcName = Utilities.path(folder, ref+".json");
    } else
      throw new Exception("Unable to find source for "+ref+" in "+filename+" ("+Utilities.path(folder, ref+".xml/json)"));

    FileInputStream input = new FileInputStream(srcName);

    try {
      ValueSet result = ValueSetUtilities.makeShareable((ValueSet) p.parse(input));
      result.setId(ref.substring(9));
      if (!result.hasExperimental())
        result.setExperimental(false);
//    if (!result.hasUrl())
        result.setUrl("http://hl7.org/fhir/ValueSet/"+ref.substring(9));

      if (!result.hasVersion() || result.getUrl().startsWith("http://hl7.org/fhir"))
        result.setVersion(version);

        
        if (!Utilities.noString(committee)) {
          if (!result.hasExtension(ToolingExtensions.EXT_WORKGROUP)) {
            result.addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType(committee));
          } else {
            String ec = ToolingExtensions.readStringExtension(result, ToolingExtensions.EXT_WORKGROUP);
            if (!ec.equals(committee))
              System.out.println("ValueSet "+result.getUrl()+" WG mismatch 1: is "+ec+", want to set to "+committee);
          } 
        }
        result.setUserData("filename", "valueset-"+ref.substring(9));
        result.setUserData("path", "valueset-"+ref.substring(9)+".html");
        
        new CodeSystemConvertor(codeSystems).convert(p, result, srcName);
      return result;
    } finally {
      IOUtils.closeQuietly(input);
    }
  }

  public static BindingSpecification.BindingMethod readBinding(String s, String context) throws Exception {
    s = s.toLowerCase();
    if (s == null || "".equals(s) || "unbound".equals(s))
      return BindingSpecification.BindingMethod.Unbound;
    if (s.equals("code list"))
      return BindingSpecification.BindingMethod.CodeList;
    if (s.equals("special"))
      return BindingSpecification.BindingMethod.Special;
    if (s.equals("value set"))
      return BindingSpecification.BindingMethod.ValueSet;
    throw new Exception("Unknown Binding: "+s+" 2 "+context);
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
