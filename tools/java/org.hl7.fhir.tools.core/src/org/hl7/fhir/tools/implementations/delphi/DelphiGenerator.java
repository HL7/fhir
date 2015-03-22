package org.hl7.fhir.tools.implementations.delphi;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;

/**
 * Generates the delphi reference implementation
 * 
 * todo: the delphi reference implementation depends on too much HL7Connect infrastructure.
 * 
 * @author Grahame
 *
 */
public class DelphiGenerator extends BaseGenerator implements PlatformGenerator {

  // this is to generate code with generics, but due to limitations with 
  // delphi's generics (no covariance) it it not used. retained for possible
  // future use if delphi generics are ever sorted out
  private boolean generics = false; 
  
  public enum ClassCategory {
    Type, Component, Resource, AbstractResource
  }

  private DelphiCodeGenerator defCodeType;
  private DelphiCodeGenerator defCodeComp;
  private DelphiCodeGenerator defCodeRes;
  private DelphiCodeGenerator defCodeConst;
  private DelphiCodeGenerator prsrCode;
  private Definitions definitions;

  private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
  private List<String> enumsDone = new ArrayList<String>();
  private Map<String, Integer> enumSizes = new HashMap<String, Integer>();

  private List<ElementDefn> enums = new ArrayList<ElementDefn>();
  private List<String> enumNames = new ArrayList<String>();
  private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();
  private List<String> lists = new ArrayList<String>();

  private StringBuilder workingParserX;
  private StringBuilder workingParserXA;
  private StringBuilder workingComposerX;
  private StringBuilder workingComposerXA;
  private StringBuilder workingParserJ;
  private StringBuilder workingComposerJ;
  private StringBuilder factoryIntf;
  private StringBuilder factoryImpl;
  private StringBuilder factoryByName;

  private StringBuilder prsrRegX = new StringBuilder();
  private StringBuilder srlsRegX = new StringBuilder();
  private StringBuilder prsrRegJ = new StringBuilder();
  private StringBuilder srlsRegJ = new StringBuilder();
  private StringBuilder prsrImpl = new StringBuilder();
  private StringBuilder prsrdefX = new StringBuilder();
  private StringBuilder srlsdefX = new StringBuilder();
  private StringBuilder prsrdefJ = new StringBuilder();
  private StringBuilder srlsdefJ = new StringBuilder();
  private StringBuilder prsrFragJ = new StringBuilder();
  private StringBuilder prsrFragX = new StringBuilder();
  private StringBuilder prsrDTX = new StringBuilder();
  private StringBuilder prsrDTJ = new StringBuilder();
  private Map<String, String> simpleTypes = new HashMap<String, String>();

  private List<String> types = new ArrayList<String>();
  private List<String> constants = new ArrayList<String>();
  private String dcc;
  private String exe;
  private FolderManager folders;


  public DelphiGenerator(FolderManager folders) {
    this.folders = folders;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)  throws Exception {
    simpleTypes.put("TFhirId", "String");
    
    start(implDir, version, genDate);
    initParser(version, genDate);

    this.definitions = definitions;

    generate(definitions.getInfrastructure().get("Element"), "TFHIRBase", false, ClassCategory.Type, true);
    generate(definitions.getInfrastructure().get("BackboneElement"), "TFHIRElement", false, ClassCategory.Type, true);

    parserGap();
    generateElement();
    generatePrimitive(new DefinedCode("enum", "", ""), "TFhirPrimitiveType", true, false);

    for (DefinedCode n : definitions.getPrimitives().values()) {
      if (n instanceof PrimitiveType)
        generatePrimitive(n, "TFhirPrimitiveType", false, false);
    }
    for (DefinedCode n : definitions.getPrimitives().values()) {
      if (!(n instanceof PrimitiveType))
        generatePrimitive(n, ((DefinedStringPattern) n).getBase().contains(" ") ? "TFhirType" : "TFhir"+Utilities.capitalize(((DefinedStringPattern) n).getBase()), false, true);
    }
    parserGap();

    Set<String> done = new HashSet<String>();
    boolean alldone = false;
    while (!alldone) {
      alldone = true;
      for (String s : definitions.getBaseResources().keySet()) {
        ResourceDefn n = definitions.getBaseResources().get(s);
        if (!done.contains(n.getName())) {
          if (Utilities.noString(n.getRoot().typeCode()) || done.contains(n.getRoot().typeCode())) { 
            generate(n.getRoot(), Utilities.noString(n.getRoot().typeCode()) ? "TFHIRBase" : "TFhir"+n.getRoot().typeCode(), true, n.isAbstract() ? ClassCategory.AbstractResource : ClassCategory.Resource, n.isAbstract());
            if (!n.isAbstract()) {
              prsrRegX.append("  else if element.baseName = '"+n.getName()+"' Then\r\n    result := Parse"+n.getName()+"(element, path+'/"+n.getName()+"')\r\n");
              srlsRegX.append("    frt"+n.getName()+": Compose"+n.getName()+"(xml, '"+n.getName()+"', TFhir"+n.getName()+"(resource));\r\n");
              prsrRegJ.append("  else if s = '"+n.getName()+"' Then\r\n    result := Parse"+n.getName()+"(jsn)\r\n");
              srlsRegJ.append("    frt"+n.getName()+": Compose"+n.getName()+"(json, '"+n.getName()+"', TFhir"+n.getName()+"(resource));\r\n");
            }
            done.add(n.getName());
          } else
            alldone = false;
        }
      }
    }

    parserGap();

    for (ElementDefn n : definitions.getInfrastructure().values()) {
      if (!n.getName().equals("Element") && !n.getName().equals("BackboneElement")) {
        generate(n, "TFHIRType", false, ClassCategory.Type, false);
      }
    }

    for (ElementDefn n : definitions.getTypes().values()) {
      generate(n, "TFhirType", false, ClassCategory.Type, false);
    }

    for (ElementDefn n : definitions.getStructures().values()) {
      generate(n, "TFhirType", false, ClassCategory.Type, false);
    }

    for (ProfiledType c : definitions.getConstraints().values()) {
      genConstraint(c);
    }
    parserGap();
    for (String s : definitions.sortedResourceNames()) {
      ResourceDefn n = definitions.getResources().get(s);
      generate(n.getRoot(), "TFhir"+n.getRoot().typeCode(), true, ClassCategory.Resource, false);
      generateSearchEnums(n);
      prsrRegX.append("  else if element.baseName = '"+n.getName()+"' Then\r\n    result := Parse"+n.getName()+"(element, path+'/"+n.getName()+"')\r\n");
      srlsRegX.append("    frt"+n.getName()+": Compose"+n.getName()+"(xml, '"+n.getName()+"', TFhir"+n.getName()+"(resource));\r\n");
      prsrRegJ.append("  else if s = '"+n.getName()+"' Then\r\n    result := Parse"+n.getName()+"(jsn)\r\n");
      srlsRegJ.append("    frt"+n.getName()+": Compose"+n.getName()+"(json, '"+n.getName()+"', TFhir"+n.getName()+"(resource));\r\n");
    }

    defCodeConst.enumConsts.add("  FHIR_GENERATED_VERSION = '"+version+"';\r\n");
    defCodeConst.enumConsts.add("  FHIR_GENERATED_REVISION = '"+svnRevision+"';\r\n");
    defCodeConst.enumConsts.add("  FHIR_GENERATED_DATE = '"+new SimpleDateFormat("yyyyMMddHHmmss").format(genDate)+"';\r\n");
    defCodeRes.classDefs.add("  {@Class TFhirResourceFactory : TFHIRBaseFactory\r\n");
    defCodeRes.classDefs.add("     FHIR factory: class constructors and general useful builders\r\n");
    defCodeRes.classDefs.add("  }\r\n");
    defCodeRes.classDefs.add(" TFhirResourceFactory = class (TFHIRBaseFactory)\r\n  public\r\n"+factoryIntf.toString()+"    function makeByName(const name : String) : TFHIRBase;\r\n  end;\r\n");
    types.add("TFhirResourceFactory");
    defCodeRes.classImpls.add(factoryImpl.toString());
    defCodeRes.classImpls.add("function TFHIRResourceFactory.makeByName(const name : String) : TFHIRBase;\r\nbegin\r\n  "+factoryByName.toString().substring(7)+"  else\r\n    result := nil;\r\nend;\r\n\r\n");
    defCodeComp.finish();
    defCodeType.finish();
    defCodeRes.finish();
    defCodeConst.finish();

    prsrCode.classDefs.add(buildParser());
    prsrCode.classImpls.add(prsrImpl.toString());
    prsrCode.finish();

    genDoco(implDir);
    ZipGenerator zip = new ZipGenerator(destDir+getReference(version));
    zip.addFiles(implDir, "", ".pas", null);
    zip.addFiles(implDir, "", ".res", null);
    zip.addFiles(implDir, "", ".rc", null);
    zip.addFiles(implDir, "", ".dpr", null);
    zip.addFiles(implDir, "", ".dproj", null);
    zip.addFiles(implDir, "", ".dof", null);
    zip.addFiles(implDir, "", ".txt", null);
    zip.addFiles(Utilities.path(implDir, "support", ""), "support\\", ".pas", null);
    zip.addFiles(Utilities.path(implDir, "support", ""), "support\\", ".inc", null);
    zip.close();    
  }

  private void genDoco(String implDir) {
    IniFile ini = new IniFile(implDir+"fhir.ini");
    ini.setStringProperty("doco", "constants", asCSV(constants, 0, constants.size()+1), null);

    int t = 0;
    int i = 0;
    while (i < types.size()) {
      t++;
      ini.setStringProperty("doco", "types"+Integer.toString(t), asCSV(types, i, i + 10), null);
      i = i + 10;
    }
    ini.save();
  }

  private String asCSV(List<String> list, int min, int max) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (int i = min; i < Math.min(max, list.size()); i++) {
      String s = list.get(i);
      if (first)
        first = false;
      else
        b.append(",");
      b.append(s);
    }
    return b.toString();
  }

  private void parserGap() {
    prsrdefX.append("\r\n");
    prsrdefJ.append("\r\n");
    srlsdefX.append("\r\n");
    srlsdefJ.append("\r\n");
  }

  private void start(String implDir, String version, Date genDate)
      throws UnsupportedEncodingException, FileNotFoundException, Exception {
    defCodeRes = new DelphiCodeGenerator(new FileOutputStream(implDir+"FHIRResources.pas"));
    defCodeRes.start();
    defCodeRes.name = "FHIRResources";
    defCodeRes.comments.add("FHIR v"+version+" generated "+Config.DATE_FORMAT().format(genDate));
    defCodeRes.precomments.add("!Wrapper uses FHIRBase, FHIRBase_Wrapper, FHIRTypes, FHIRTypes_Wrapper, FHIRComponents, FHIRComponents_Wrapper, DateAndTime, DateAndTime_Wrapper");
    defCodeRes.uses.add("SysUtils");
    defCodeRes.uses.add("Classes");
    defCodeRes.uses.add("StringSupport");
    defCodeRes.uses.add("DecimalSupport");
    defCodeRes.uses.add("AdvBuffers");
    if (generics)
      defCodeRes.uses.add("AdvGenerics");
    defCodeRes.uses.add("DateAndTime");
    defCodeRes.uses.add("FHIRBase");
    defCodeRes.uses.add("FHIRTypes");
    defCodeRes.uses.add("FHIRComponents");

    defCodeConst = new DelphiCodeGenerator(new FileOutputStream(implDir+"FHIRConstants.pas"));
    defCodeConst.start();
    defCodeConst.name = "FHIRConstants";
    defCodeConst.comments.add("FHIR v"+version+" generated "+Config.DATE_FORMAT().format(genDate));
    defCodeConst.precomments.add("!Wrapper uses FHIRBase, FHIRBase_Wrapper, FHIRTypes, FHIRTypes_Wrapper, FHIRComponents, FHIRComponents_Wrapper, FHIRResources, FHIRResources_Wrapper");
    defCodeConst.precomments.add("!ignore ALL_RESOURCE_TYPES");
    defCodeConst.uses.add("SysUtils");
    defCodeConst.uses.add("Classes");
    defCodeConst.uses.add("StringSupport");
    defCodeConst.uses.add("DecimalSupport");
    defCodeConst.uses.add("AdvBuffers");
    if (generics)
      defCodeConst.uses.add("AdvGenerics");
    defCodeConst.uses.add("DateAndTime");
    defCodeConst.uses.add("FHIRBase");
    defCodeConst.uses.add("FHIRTypes");
    defCodeConst.uses.add("FHIRComponents");
    defCodeConst.uses.add("FHIRResources");

    defCodeType = new DelphiCodeGenerator(new FileOutputStream(implDir+"FHIRTypes.pas"));
    defCodeType.start();
    defCodeType.name = "FHIRTypes";
    defCodeType.comments.add("FHIR v"+version+" generated "+Config.DATE_FORMAT().format(genDate));
    defCodeType.precomments.add("!Wrapper uses FHIRBase, FHIRBase_Wrapper");
    defCodeType.uses.add("Classes");
    defCodeType.uses.add("SysUtils");
    defCodeType.uses.add("DecimalSupport");
    defCodeType.uses.add("StringSupport");
    //    defCodeType.uses.add("AdvWideStringLists");
    defCodeType.uses.add("AdvBuffers");
    if (generics)
      defCodeType.uses.add("AdvGenerics");
    defCodeType.uses.add("EncdDecd");
    defCodeType.uses.add("DateAndTime");
    defCodeType.uses.add("FHIRBase");

    defCodeComp = new DelphiCodeGenerator(new FileOutputStream(implDir+"FHIRComponents.pas"));
    defCodeComp.start();
    defCodeComp.name = "FHIRComponents";
    defCodeComp.comments.add("FHIR v"+version+" generated "+Config.DATE_FORMAT().format(genDate));
    defCodeComp.precomments.add("!Wrapper uses FHIRBase, FHIRBase_Wrapper, FHIRTypes, FHIRTypes_Wrapper");
    defCodeComp.uses.add("SysUtils");
    defCodeComp.uses.add("Classes");
    defCodeComp.uses.add("StringSupport");
    defCodeComp.uses.add("DecimalSupport");
    defCodeComp.uses.add("AdvBuffers");
    if (generics)
      defCodeComp.uses.add("AdvGenerics");
    defCodeComp.uses.add("DateAndTime");
    defCodeComp.uses.add("FHIRBase");
    defCodeComp.uses.add("FHIRTypes");

    factoryIntf = new StringBuilder();
    factoryImpl = new StringBuilder();
    factoryByName = new StringBuilder();


    prsrCode = new DelphiCodeGenerator(new FileOutputStream(implDir+"FHIRParser.pas"));
    prsrCode.start();
    prsrCode.name = "FHIRParser";
  }

  private void generate(ElementDefn root, String superClass, boolean resource, ClassCategory category, boolean isAbstract) throws Exception {
    typeNames.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    Map<ElementDefn, List<String>> paths = new HashMap<ElementDefn, List<String>>();

    for (ElementDefn e : root.getElements()) {
      scanNestedTypes(root, root.getName(), e, root.getName(), paths);
    }

    for (ElementDefn e : enums) {
      generateEnum(e);
    }
    for (ElementDefn e : strucs) {
      generateType(e, category == ClassCategory.Resource ? ClassCategory.Component : category, true, paths.get(e));
    }

    if (category == ClassCategory.AbstractResource) 
      genTypeAbstract(root, "TFhir"+root.getName(), superClass, category);
    else
      genType(root, "TFhir"+root.getName(), superClass, category, isAbstract);
    
  }

  private DelphiCodeGenerator getCode(ClassCategory category) {
    switch (category) {
    case Type:
      return defCodeType;
    case Component:
      return defCodeRes;
    case Resource:
      return defCodeRes;
    case AbstractResource:
      return defCodeRes;
    }
    return null;
  }

  private void genType(ElementDefn root, String tn, String superClass, ClassCategory category, boolean isAbstract) throws Exception {
    prsrdefX.append("    function Parse"+root.getName()+"(element : IXmlDomElement; path : string) : TFhir"+root.getName()+";\r\n");
    srlsdefX.append("    procedure Compose"+root.getName()+"(xml : TXmlBuilder; name : string; elem : TFhir"+root.getName()+");\r\n");
    prsrdefJ.append("    function Parse"+root.getName()+"(jsn : TJsonObject) : TFhir"+root.getName()+"; overload;\r\n");
    srlsdefJ.append("    procedure Compose"+root.getName()+"(json : TJSONWriter; name : string; elem : TFhir"+root.getName()+"; noObj : boolean = false);"+"\r\n");
    prsrFragJ.append("  else if (type_ = '"+tn+"') then\r\n    result := parse"+root.getName()+"(jsn)\r\n");
    prsrFragX.append("  else if SameText(element.NodeName, '"+tn+"') then\r\n    result := parse"+root.getName()+"(element, element.nodeName)\r\n");
    if (category == ClassCategory.Type && !isAbstract) {
      prsrDTJ.append("  else if (type_ = "+tn+") then\r\n    result := parse"+root.getName()+"(jsn)\r\n");
      prsrDTX.append("  else if (type_ = "+tn+") then\r\n    result := parse"+root.getName()+"(element, name)\r\n");
    }
    workingParserX = new StringBuilder();
    workingParserXA = new StringBuilder();
    workingComposerX = new StringBuilder();
    workingComposerXA = new StringBuilder();
    workingParserJ = new StringBuilder();
    workingComposerJ = new StringBuilder();


    StringBuilder def = new StringBuilder();
    StringBuilder defPriv1 = new StringBuilder();
    StringBuilder defPriv2 = new StringBuilder();
    StringBuilder defPub = new StringBuilder();
    StringBuilder impl = new StringBuilder();
    StringBuilder create = new StringBuilder();
    StringBuilder destroy = new StringBuilder();
    StringBuilder assign = new StringBuilder();
    StringBuilder getkids = new StringBuilder();
    StringBuilder getkidsvars = new StringBuilder();
    StringBuilder getprops = new StringBuilder();
    StringBuilder getpropsvars = new StringBuilder();
    StringBuilder setprops = new StringBuilder();
    impl.append("{ "+tn+" }\r\n\r\n");


    for (ElementDefn e : root.getElements()) {
      generateField(e, defPriv1, defPriv2, defPub, impl, create, destroy, assign, getkids, getkidsvars, getprops, getpropsvars, setprops, tn, "", category, true, tn.equals("TFhirExtension"));
    }

    def.append("  {@Class "+tn+" : "+superClass+"\r\n");
    def.append("    "+makeDocoSafe(root.getDefinition())+"\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir."+tn.substring(5)+"}\r\n");
    def.append("  "+tn+" = class ("+superClass+")\r\n");
    types.add(tn);
    factoryIntf.append("    {@member new"+tn.substring(5)+"\r\n      create a new "+root.getName()+"\r\n    }\r\n    {!script nolink}\r\n    function new"+tn.substring(5)+" : "+tn+";\r\n");    
    factoryImpl.append("function TFhirResourceFactory.new"+tn.substring(5)+" : "+tn+";\r\nbegin\r\n  result := "+tn+".create;\r\nend;\r\n\r\n");
    factoryByName.append("  else if name = '"+tn.substring(5)+"' then\r\n    result := new"+tn.substring(5)+"()\r\n");
    def.append("  private\r\n");
    def.append(defPriv1.toString());
    def.append(defPriv2.toString());
    def.append("  protected\r\n");
    if (category == ClassCategory.Resource) {
      def.append("    function GetResourceType : TFhirResourceType; override;\r\n");      
      def.append("    Function GetHasASummary : Boolean; Override;\r\n");
    }
    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
    def.append("  public\r\n");
    def.append("    constructor Create; Override;\r\n");
    def.append("    destructor Destroy; override;\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
    def.append("    function Link : "+tn+"; overload;\r\n");
    def.append("    function Clone : "+tn+"; overload;\r\n");
    def.append("    procedure setProperty(propName : string; propValue : TFHIRObject); override;\r\n");
    def.append("    function FhirType : string; override;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  published\r\n");
    def.append(defPub.toString());
    def.append("  end;\r\n");
    def.append("\r\n");
    StringBuilder impl2 = new StringBuilder();
    impl2.append("{ "+tn+" }\r\n\r\n");
    impl2.append("constructor "+tn+".Create;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(create.toString());
    impl2.append("end;\r\n\r\n");

    impl2.append("destructor "+tn+".Destroy;\r\n");
    impl2.append("begin\r\n");
    impl2.append(destroy.toString());
    impl2.append("  inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    if (category == ClassCategory.Resource) {
      impl2.append("function "+tn+".GetResourceType : TFhirResourceType;\r\nbegin\r\n  result := frt"+root.getName()+";\r\nend;\r\n\r\n");       
      impl2.append("function "+tn+".GetHasASummary : Boolean;\r\nbegin\r\n  result := false;\r\nend;\r\n\r\n");       
    }

    impl2.append("procedure "+tn+".Assign(oSource : TAdvObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(assign.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
    if (getkidsvars.length() > 0) {
      impl2.append("var\r\n");
      impl2.append(getkidsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getkids.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
    if (getpropsvars.length() > 0) {
      impl2.append("var\r\n  prop : TFHIRProperty;\r\n");      
      impl2.append(getpropsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getprops.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".setProperty(propName: string; propValue: TFHIRObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  "+setprops.toString().substring(7));
    impl2.append("  else inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".FhirType : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := '"+root.getName()+"';\r\n");
    impl2.append("end;\r\n\r\n");


    impl2.append("function "+tn+".Link : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".Clone : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");
    getCode(category).classDefs.add(def.toString());
    getCode(category).classImpls.add(impl2.toString() + impl.toString());
    getCode(category).classFwds.add("  "+tn+" = class;\r\n");
    generateParser(tn, category, !superClass.equals("TFHIRObject"), root.typeCode());
    defineList(tn, tn+"List", null, category, false);
  }

  private void genTypeAbstract(ElementDefn root, String tn, String superClass, ClassCategory category) throws Exception {
    boolean isBase = Utilities.noString(root.typeCode());
    if (root.getName().equals("Resource"))
      generateResource();

    StringBuilder def = new StringBuilder();
    StringBuilder defPriv1 = new StringBuilder();
    StringBuilder defPriv2 = new StringBuilder();
    StringBuilder defPub = new StringBuilder();
    StringBuilder impl = new StringBuilder();
    StringBuilder create = new StringBuilder();
    StringBuilder destroy = new StringBuilder();
    StringBuilder assign = new StringBuilder();
    StringBuilder getkids = new StringBuilder();
    StringBuilder getkidsvars = new StringBuilder();
    StringBuilder getprops = new StringBuilder();
    StringBuilder getpropsvars = new StringBuilder();
    StringBuilder setprops = new StringBuilder();
    impl.append("{ "+tn+" }\r\n\r\n");

    workingParserX = new StringBuilder();
    workingParserXA = new StringBuilder();
    workingComposerX = new StringBuilder();
    workingComposerXA = new StringBuilder();
    workingParserJ = new StringBuilder();
    workingComposerJ = new StringBuilder();

    for (ElementDefn e : root.getElements()) {
      generateField(e, defPriv1, defPriv2, defPub, impl, create, destroy, assign, getkids, getkidsvars, getprops, getpropsvars, setprops, tn, "", category, true, e.getName().equals("Extension"));
    }

    def.append("  {@Class "+tn+" : "+superClass+"\r\n");
    def.append("    "+makeDocoSafe(root.getDefinition())+"\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir."+tn.substring(5)+"}\r\n");
    def.append("  "+tn+" = {abstract} class ("+superClass+")\r\n");
    types.add(tn);
    def.append("  private\r\n");
    def.append(defPriv1.toString());
    def.append(defPriv2.toString());
    def.append("  protected\r\n");
    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
    if (isBase) {
      def.append("    function GetResourceType : TFhirResourceType; virtual; abstract;\r\n");
      def.append("    function GetHasASummary : Boolean; virtual; abstract;\r\n");
    }
    def.append("  public\r\n");
    def.append("    constructor Create; Override;\r\n");
    def.append("    destructor Destroy; override;\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
    def.append("    function Link : "+tn+"; overload;\r\n");
    def.append("    function Clone : "+tn+"; overload;\r\n");
    def.append("    procedure setProperty(propName : string; propValue : TFHIRObject); override;\r\n");
    def.append("    function FhirType : string; override;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  published\r\n");
    if (isBase) {
      def.append("    Property ResourceType : TFhirResourceType read GetResourceType;\r\n\r\n");
      def.append("    Property HasASummary : Boolean read GetHasASummary;\r\n\r\n");
    }
    def.append(defPub.toString());
    def.append("  end;\r\n");
    def.append("\r\n");
    if (root.getName().equals("Resource"))
      def.append("  TFhirResourceClass = class of TFhirResource;\r\n");

    StringBuilder impl2 = new StringBuilder();
    impl2.append("{ "+tn+" }\r\n\r\n");
    impl2.append("constructor "+tn+".Create;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(create.toString());
    impl2.append("end;\r\n\r\n");

    impl2.append("destructor "+tn+".Destroy;\r\n");
    impl2.append("begin\r\n");
    impl2.append(destroy.toString());
    impl2.append("  inherited;\r\n");
    impl2.append("end;\r\n\r\n");

    impl2.append("procedure "+tn+".Assign(oSource : TAdvObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(assign.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
    if (getkidsvars.length() > 0) {
      impl2.append("var\r\n");
      impl2.append(getkidsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getkids.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
    if (getpropsvars.length() > 0) {
      impl2.append("var\r\n  prop : TFHIRProperty;\r\n");      
      impl2.append(getpropsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getprops.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".setProperty(propName: string; propValue: TFHIRObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  "+setprops.toString().substring(7));
    impl2.append("  else inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".FhirType : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := '"+root.getName()+"';\r\n");
    impl2.append("end;\r\n\r\n");


    impl2.append("function "+tn+".Link : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".Clone : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");
    getCode(category).classDefs.add(def.toString());
    getCode(category).classImpls.add(impl2.toString() + impl.toString());
    getCode(category).classFwds.add("  "+tn+" = class;\r\n");
    defineList(tn, tn+"List", null, category, false);

    prsrdefX.append("    Procedure Parse"+root.getName()+"Attributes(resource : "+tn+"; path : string; element : IXmlDomElement);\r\n");
    prsrImpl.append("Procedure TFHIRXmlParser.Parse"+root.getName()+"Attributes(resource : "+tn+"; path : string; element : IXmlDomElement);\r\n");
    prsrImpl.append("begin\r\n");
    if (!isBase)
      prsrImpl.append("  Parse"+root.typeCode()+"Attributes(resource, path, element);\r\n");
    prsrImpl.append(workingParserXA.toString());
    prsrImpl.append("end;\r\n\r\n");
    prsrdefX.append("    Function Parse"+root.getName()+"Child(resource : "+tn+"; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("Function TFHIRXmlParser.Parse"+root.getName()+"Child(resource : "+tn+"; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  result := true;\r\n");
    prsrImpl.append("  "+workingParserX.toString().substring(11).replace("      ", "  ").replace("result.", "resource."));
    if (isBase)
      prsrImpl.append("  else\r\n");
    else
      prsrImpl.append("  else if not parse"+root.typeCode()+"Child(resource, path, child) then\r\n");
    prsrImpl.append("    result := false;\r\n");
    prsrImpl.append("end;\r\n\r\n");

    prsrdefJ.append("    procedure Parse"+root.getName()+"Properties(jsn : TJsonObject; resource : "+tn+");\r\n");
    prsrImpl.append("procedure TFHIRJsonParser.Parse"+root.getName()+"Properties(jsn : TJsonObject; resource : "+tn+");\r\n");
    prsrImpl.append("begin\r\n");
    if (!isBase)
      prsrImpl.append("  Parse"+root.typeCode()+"Properties(jsn, resource);\r\n");
    prsrImpl.append(workingParserJ.toString().replace("    ", "  ").replace("result.", "resource."));
    prsrImpl.append("end;\r\n\r\n");


    srlsdefX.append("    Procedure Compose"+root.getName()+"Attributes(xml : TXmlBuilder; resource : "+tn+");\r\n");
    prsrImpl.append("Procedure TFHIRXmlComposer.Compose"+root.getName()+"Attributes(xml : TXmlBuilder; resource : "+tn+");\r\n");
    prsrImpl.append("begin\r\n");
    if (!isBase)
      prsrImpl.append("  Compose"+root.typeCode()+"Attributes(xml, resource);\r\n");
    prsrImpl.append(workingComposerXA.toString());        
    prsrImpl.append("end;\r\n\r\n");
    srlsdefX.append("    Procedure Compose"+root.getName()+"Children(xml : TXmlBuilder; elem : "+tn+");\r\n");
    prsrImpl.append("Procedure TFHIRXmlComposer.Compose"+root.getName()+"Children(xml : TXmlBuilder; elem : "+tn+");\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    if (!isBase)
      prsrImpl.append("  compose"+root.typeCode()+"Children(xml, elem);\r\n");
    prsrImpl.append(workingComposerX.toString());        
    prsrImpl.append("end;\r\n\r\n");

    srlsdefJ.append("    Procedure Compose"+root.getName()+"Properties(json : TJSONWriter; elem : "+tn+");\r\n");
    prsrImpl.append("Procedure TFHIRJsonComposer.Compose"+root.getName()+"Properties(json : TJSONWriter; elem : "+tn+");\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    if (!isBase)
      prsrImpl.append("  Compose"+root.typeCode()+"Properties(json, elem);\r\n");
    prsrImpl.append(workingComposerJ.toString());        
    prsrImpl.append("end;\r\n\r\n");

  }

  private void genResource(ResourceDefn root, String tn, String superClass, ClassCategory category) throws Exception {
    prsrdefX.append("    function Parse"+root.getName()+"(element : IXmlDomElement; path : string) : TFhir"+root.getName()+";\r\n");
    srlsdefX.append("    procedure Compose"+root.getName()+"(xml : TXmlBuilder; name : string; elem : TFhir"+root.getName()+");\r\n");
    prsrdefJ.append("    function Parse"+root.getName()+"(jsn : TJsonObject) : TFhir"+root.getName()+"; overload; {b|}\r\n");
    srlsdefJ.append("    procedure Compose"+root.getName()+"(json : TJSONWriter; name : string; elem : TFhir"+root.getName()+"; noObj : boolean = false);\r\n");
    workingParserX = new StringBuilder();
    workingParserXA = new StringBuilder();
    workingComposerX = new StringBuilder();
    workingComposerXA = new StringBuilder();
    workingParserJ = new StringBuilder();
    workingComposerJ = new StringBuilder();

    generateSearchEnums(root);

    StringBuilder def = new StringBuilder();
    StringBuilder defPriv1 = new StringBuilder();
    StringBuilder defPriv2 = new StringBuilder();
    StringBuilder defPub = new StringBuilder();
    StringBuilder impl = new StringBuilder();
    StringBuilder create = new StringBuilder();
    StringBuilder destroy = new StringBuilder();
    StringBuilder assign = new StringBuilder();
    StringBuilder getkids = new StringBuilder();
    StringBuilder getkidsvars = new StringBuilder();
    StringBuilder getprops = new StringBuilder();
    StringBuilder getpropsvars = new StringBuilder();
    StringBuilder setprops = new StringBuilder();
    impl.append("{ "+tn+" }\r\n\r\n");


    boolean hasASummary = hasASummary(root);
    for (ElementDefn e : root.getRoot().getElements()) {
      generateField(e, defPriv1, defPriv2, defPub, impl, create, destroy, assign, getkids, getkidsvars, getprops, getpropsvars, setprops, tn, "", ClassCategory.Component, !hasASummary, e.getName().equals("Extension"));
    }

    def.append("  {@Class "+tn+" : "+superClass+"\r\n");
    def.append("    "+makeDocoSafe(root.getDefinition())+"\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir."+tn.substring(5)+"}\r\n");
    def.append("  "+tn+" = class ("+superClass+")\r\n");
    types.add(tn);
    factoryIntf.append("    {@member new"+tn.substring(5)+"\r\n      create a new "+root.getName()+"\r\n    }\r\n    {!script nolink}\r\n    function new"+tn.substring(5)+" : "+tn+";\r\n");    
    factoryImpl.append("function TFhirResourceFactory.new"+tn.substring(5)+" : "+tn+";\r\nbegin\r\n  result := "+tn+".create;\r\nend;\r\n\r\n");
    factoryByName.append("  else if name = '"+tn.substring(5)+"' then\r\n    result := new"+tn.substring(5)+"()\r\n");
    def.append("  private\r\n");
    def.append(defPriv1.toString());
    def.append(defPriv2.toString());
    def.append("  protected\r\n");
    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
    if (category == ClassCategory.Resource) {
      def.append("    Function GetHasASummary : Boolean; Override;\r\n");
      def.append("    function GetResourceType : TFhirResourceType; override;\r\n");      
    }
    def.append("  public\r\n");
    def.append("    constructor Create; Override;\r\n");
    def.append("    destructor Destroy; override;\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
    def.append("    function Link : "+tn+"; overload;\r\n");
    def.append("    function Clone : "+tn+"; overload;\r\n");
    def.append("    procedure setProperty(propName : string; propValue : TFHIRObject); override;\r\n");
    def.append("    function FhirType : string; override;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  published\r\n");
    def.append(defPub.toString());
    def.append("  end;\r\n");
    def.append("\r\n");
    StringBuilder impl2 = new StringBuilder();
    impl2.append("{ "+tn+" }\r\n\r\n");
    impl2.append("constructor "+tn+".Create;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(create.toString());
    impl2.append("end;\r\n\r\n");

    impl2.append("destructor "+tn+".Destroy;\r\n");
    impl2.append("begin\r\n");
    impl2.append(destroy.toString());
    impl2.append("  inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    if (category == ClassCategory.Resource) {
      impl2.append("function "+tn+".GetResourceType : TFhirResourceType;\r\nbegin\r\n  result := frt"+root.getName()+";\r\nend;\r\n\r\n");       
      impl2.append("function "+tn+".GetHasASummary : Boolean;\r\nbegin\r\n  result := "+(hasASummary ? "true" : "false")+";\r\nend;\r\n\r\n");       
    }

    impl2.append("procedure "+tn+".Assign(oSource : TAdvObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(assign.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
    if (getkidsvars.length() > 0) {
      impl2.append("var\r\n");
      impl2.append(getkidsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getkids.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
    if (getpropsvars.length() > 0) {
      impl2.append("var\r\n  prop : TFHIRProperty;\r\n");      
      impl2.append(getpropsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getprops.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".setProperty(propName : string; propValue: TFHIRObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  "+setprops.toString().substring(7));
    impl2.append("  else inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".FhirType : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := '"+root.getName()+"';\r\n");
    impl2.append("end;\r\n\r\n");

    impl2.append("function "+tn+".Link : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".Clone : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");
    getCode(category).classDefs.add(def.toString());
    getCode(category).classImpls.add(impl2.toString() + impl.toString());
    getCode(category).classFwds.add("  "+tn+" = class;\r\n");
    generateParser(tn, ClassCategory.Resource, !superClass.equals("TFHIRObject"), root.getRoot().typeCode());
  }

  private boolean hasASummary(ResourceDefn root) {
    for (ElementDefn e : root.getRoot().getElements())
      if (e.isSummary())
        return true;
    return false;
  }

  private void generateSearchEnums(ResourceDefn r) throws Exception {
    StringBuilder def = new StringBuilder();
    StringBuilder con1 = new StringBuilder();
    StringBuilder con2 = new StringBuilder();
    StringBuilder con3 = new StringBuilder();
    StringBuilder con4 = new StringBuilder();
    StringBuilder con6 = new StringBuilder();
    StringBuilder con7 = new StringBuilder();

    String tn = "TSearchParams"+r.getName();
    String prefix = "sp"+r.getName()+"_";

    if (!enumsDone.contains(prefix)) {
      enumsDone.add(prefix);
      def.append("  {@Enum "+tn+"\r\n");
      def.append("    Search Parameters for "+r.getName()+"\r\n");
      def.append("  }\r\n");
      def.append("  "+tn+" = (\r\n");
      constants.add(tn);

      con3.append("  CODES_"+tn+" : Array["+tn+"] of String = (");
      con1.append("  DESC_"+tn+" : Array["+tn+"] of String = (");
      con4.append("  TYPES_"+tn+" : Array["+tn+"] of TFhirSearchParamType = (");
      con2.append("//  CHECK_"+tn+" : Array["+tn+"] of "+tn+" = (");
      con6.append("  PATHS_"+tn+" : Array["+tn+"] of String = (");
      con7.append("  TARGETS_"+tn+" : Array["+tn+"] of TFhirResourceTypeSet = (");
      int l4 = 0;
      int l2 = 0;

      List<String> names = new ArrayList<String>();
      Map<String, SearchParameterDefn> params = new HashMap<String, SearchParameterDefn>();
      names.addAll(r.getSearchParams().keySet());
      params.putAll(r.getSearchParams());
      String pn = r.getRoot().typeCode();
      while (!Utilities.noString(pn)) {
        ResourceDefn rd = definitions.getBaseResources().get(pn);
        names.addAll(rd.getSearchParams().keySet());
        params.putAll(rd.getSearchParams());
        pn = rd.getRoot().typeCode();
      }

      Collections.sort(names);
      int l = names.size();
      int i = 0;
      for (String name : names) {
        SearchParameterDefn p = params.get(name);
        i++;
        String n = p.getCode().replace("$", "_");
        String d = makeDocoSafe(p.getDescription());
        String nf = n.replace("-", "_").replace("[x]", "x");
        String t = getTarget(p.getWorkingTargets());
        if (i == l) {
          def.append("    "+prefix+getTitle(nf)+"); {@enum.value \""+p.getCode()+"\" "+prefix+getTitle(nf)+" "+d+" }\r\n");
          con2.append(" "+prefix+getTitle(nf)+");");
          con4.append(" SearchParamType"+getTitle(p.getType().toString())+");");
          con1.append("'"+defCodeType.escape(d)+"');");
          con3.append("'"+defCodeType.escape(n)+"');");
          con6.append("'"+defCodeType.escape(n+": "+t)+"');");
          con7.append(""+t+");");
        }
        else {
          def.append("    "+prefix+getTitle(nf)+", {@enum.value \""+p.getCode()+"\" "+prefix+getTitle(nf)+" "+d+" }\r\n");
          con2.append(" "+prefix+getTitle(nf)+", ");
          con4.append(" SearchParamType"+getTitle(p.getType().toString())+", ");
          con1.append("'"+defCodeType.escape(d)+"',\r\n     ");
          con3.append("'"+defCodeType.escape(n)+"', ");
          con6.append("'"+defCodeType.escape(n+": "+t)+"',\r\n     ");
          con7.append(""+t+", ");
          if (con4.length() - l4 > 250) {
            con4.append("\r\n      ");
            l4 = con4.length();
          }
          if (con2.length() - l2 > 250) {
            con2.append("\r\n      // ");
            l2 = con2.length();
          }
        }
      }

      defCodeRes.enumDefs.add(def.toString());
      defCodeConst.enumConsts.add(con3.toString());
      defCodeConst.enumConsts.add(con1.toString());
      defCodeConst.enumConsts.add(con4.toString());
      defCodeConst.enumConsts.add(con2.toString());
      defCodeConst.enumConsts.add(con6.toString());
      defCodeConst.enumConsts.add(con7.toString());
    }
  }

  private String getTarget(Set<String> targets) throws Exception {
    if (targets.size() == 1 && targets.contains("Any"))
      return "ALL_RESOURCE_TYPES";

    StringBuilder s = new StringBuilder();
    s.append("[");
    boolean first = true;
    for (String p : targets) {
      if (definitions.hasResource(p)) {
        if (!first)
          s.append(", ");
        s.append("frt"+p);
        first = false;
      }
    }
    s.append("]");
    return s.toString();
  }

  private void generateEnum(ElementDefn e) throws Exception {
    String tn = typeNames.get(e);
    BindingSpecification cd = getConceptDomain(e.getBindingName());
    enumSizes.put(tn, cd.getCodes().size());


    String prefix = tn.substring(5);
    if (!enumsDone.contains(prefix)) {
      enumsDone.add(prefix);
      StringBuilder def = new StringBuilder();
      StringBuilder con = new StringBuilder();
      def.append("  {@Enum "+tn+"\r\n");
      def.append("    "+makeDocoSafe(cd.getDefinition())+"\r\n");
      def.append("  }\r\n");
      def.append("  "+tn+" = (\r\n");
      con.append("  CODES_"+tn+" : Array["+tn+"] of String = (");
      constants.add(tn);

      int l = cd.getCodes().size();
      int i = 0;
      def.append("    "+prefix+"Null,  {@enum.value "+prefix+"Null Value is missing from Instance }\r\n");
      con.append("'', ");
      for (DefinedCode c : cd.getCodes()) {
        i++;
        String cc = c.getCode();
        if (cc.equals("-"))
          cc = "Minus";
        else if (cc.equals("+"))
          cc = "Plus";
        else {
          cc = cc.replace("-", " ").replace("+", " ");
          cc = Utilities.camelCase(cc);
          cc = cc.replace(">=", "greaterOrEquals").replace("<=", "lessOrEquals").replace("<", "lessThan").replace(">", "greaterThan").replace("=", "equal");
        }

        cc = prefix + getTitle(cc);
        if (GeneratorUtils.isDelphiReservedWord(cc))
          cc = cc + "_";
        if (i == l) {
          def.append("    "+cc+"); {@enum.value "+cc+" "+makeDocoSafe(c.getDefinition())+" }\r\n");
          con.append("'"+c.getCode()+"');");
        }
        else {
          def.append("    "+cc+", {@enum.value "+cc+" "+makeDocoSafe(c.getDefinition())+" }\r\n");
          con.append("'"+c.getCode()+"', ");
        }
      }
      def.append("  "+tn+"List = set of "+tn+";\r\n");
      defCodeType.enumDefs.add(def.toString());
      defCodeType.enumConsts.add(con.toString());
      defCodeType.enumProcs.add("Function "+tn+"ListAsInteger(aSet : "+tn+"List) : Integer; overload;");
      defCodeType.enumProcs.add("Function IntegerAs"+tn+"List(i : integer) : "+tn+"List; overload;");


      StringBuilder impl = new StringBuilder();

      impl.append("function "+tn+"ListAsInteger(aSet : "+tn+"List) : Integer;\r\n");
      impl.append("var\r\n");
      impl.append("  a : "+tn+";\r\n");
      impl.append("begin\r\n");
      impl.append("  result := 0;\r\n");
      impl.append("  for a := low("+tn+") to high("+tn+") do\r\n");
      impl.append("  begin\r\n");
      impl.append("    assert(ord(a) < 32);\r\n");
      impl.append("    if a in aSet then\r\n");
      impl.append("      result := result + 1 shl (ord(a));\r\n");
      impl.append("  end;\r\n");
      impl.append("end;\r\n\r\n");

      impl.append("function IntegerAs"+tn+"List(i : Integer) : "+tn+"List;\r\n");
      impl.append("var\r\n");
      impl.append("  aLoop : "+tn+";\r\n");
      impl.append("begin\r\n");
      impl.append("  result := [];\r\n");
      impl.append("  for aLoop := low("+tn+") to high("+tn+") Do\r\n");
      impl.append("  begin\r\n");
      impl.append("    assert(ord(aLoop) < 32);\r\n");
      impl.append("    if i and (1 shl (ord(aLoop))) > 0 Then\r\n");
      impl.append("      result := result + [aLoop];\r\n");
      impl.append("  end;\r\n");
      impl.append(" end;\r\n\r\n");

      defCodeType.classImpls.add(impl.toString());
    }
  }

  private String enumName(String substring) {
    if (substring.equalsIgnoreCase("type"))
      return "Type_";
    else
      return substring;
  }

  private void generateType(ElementDefn e, ClassCategory category, boolean noSummaries, List<String> paths) throws Exception {
    String tn = typeNames.get(e);

    prsrdefX.append("    function Parse"+tn.substring(5)+"(element : IXmlDomElement; path : string) : "+tn+";\r\n");
    srlsdefX.append("    procedure Compose"+tn.substring(5)+"(xml : TXmlBuilder; name : string; elem : "+tn+");\r\n");
    prsrdefJ.append("    function Parse"+tn.substring(5)+"(jsn : TJsonObject) : "+tn+"; overload; {b\\}\r\n");
    srlsdefJ.append("    procedure Compose"+tn.substring(5)+"(json : TJSONWriter; name : string; elem : "+tn+"; noObj : boolean = false)"+(tn.equals("TFhirExtension") ? " override;" : "")+";\r\n");
    workingParserX = new StringBuilder();
    workingParserXA = new StringBuilder();
    workingComposerX = new StringBuilder();
    workingComposerXA = new StringBuilder();
    workingParserJ = new StringBuilder();
    workingComposerJ = new StringBuilder();

    StringBuilder def = new StringBuilder();
    StringBuilder defPriv1 = new StringBuilder();
    StringBuilder defPriv2 = new StringBuilder();
    StringBuilder defPub = new StringBuilder();
    StringBuilder impl = new StringBuilder();
    StringBuilder create = new StringBuilder();
    StringBuilder destroy = new StringBuilder();
    StringBuilder assign = new StringBuilder();
    StringBuilder getkids = new StringBuilder();
    StringBuilder getkidsvars = new StringBuilder();
    StringBuilder getprops = new StringBuilder();
    StringBuilder getpropsvars = new StringBuilder();
    StringBuilder setprops = new StringBuilder();

    def.append("  {@Class "+tn+" : TFhirElement\r\n");
    def.append("    "+makeDocoSafe(e.getDefinition())+"\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir."+tn.substring(5)+"}\r\n");
    if (category == ClassCategory.Component)
      def.append("  "+tn+" = class (TFhirBackboneElement)\r\n");
    else
      def.append("  "+tn+" = class (TFhirElement)\r\n");
    types.add(tn);
    factoryIntf.append("    {@member new"+tn.substring(5)+"\r\n      create a new "+e.getName()+"\r\n    }\r\n    {!script nolink}\r\n    function new"+tn.substring(5)+" : "+tn+";\r\n");    
    factoryImpl.append("function TFhirResourceFactory.new"+tn.substring(5)+" : "+tn+";\r\nbegin\r\n  result := "+tn+".create;\r\nend;\r\n\r\n");
    for (String p : paths)
      factoryByName.append("  else if name = '"+p+"' then\r\n    result := new"+tn.substring(5)+"()\r\n");
    impl.append("{ "+tn+" }\r\n\r\n");

    for (ElementDefn c : e.getElements()) {
      generateField(c, defPriv1, defPriv2, defPub, impl, create, destroy, assign, getkids, getkidsvars, getprops, getpropsvars, setprops, tn, "", category, noSummaries, e.getName().equals("Extension"));
    }

    def.append("  private\r\n");
    def.append(defPriv1.toString());
    def.append(defPriv2.toString());
    def.append("  protected\r\n");
    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
    def.append("  public\r\n");
    def.append("    constructor Create; Override;\r\n");
    def.append("    destructor Destroy; override;\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
    def.append("    function Link : "+tn+"; overload;\r\n");
    def.append("    function Clone : "+tn+"; overload;\r\n");
    def.append("    procedure setProperty(propName : string; propValue : TFHIRObject); override;\r\n");
    def.append("    function FhirType : string; override;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  published\r\n");
    def.append(defPub.toString());
    def.append("  end;\r\n");
    def.append("\r\n");
    StringBuilder impl2 = new StringBuilder();
    impl2.append("{ "+tn+" }\r\n\r\n");
    impl2.append("constructor "+tn+".Create;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(create.toString());
    impl2.append("end;\r\n\r\n");

    impl2.append("destructor "+tn+".Destroy;\r\n");
    impl2.append("begin\r\n");
    impl2.append(destroy.toString());
    impl2.append("  inherited;\r\n");
    impl2.append("end;\r\n\r\n");

    impl2.append("procedure "+tn+".Assign(oSource : TAdvObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(assign.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
    if (getkidsvars.length() > 0) {
      impl2.append("var\r\n");
      impl2.append(getkidsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getkids.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
    if (getpropsvars.length() > 0) {
      impl2.append("var\r\n  prop : TFHIRProperty;\r\n");      
      impl2.append(getpropsvars.toString());
    }
    impl2.append("begin\r\n");
    impl2.append("  inherited;\r\n");
    impl2.append(getprops.toString());
    impl2.append("end;\r\n\r\n");
    impl2.append("procedure "+tn+".setProperty(propName : string; propValue: TFHIRObject);\r\n");
    impl2.append("begin\r\n");
    impl2.append("  "+setprops.toString().substring(7));
    impl2.append("  else inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".FhirType : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := '"+e.getName()+"';\r\n");
    impl2.append("end;\r\n\r\n");


    impl2.append("function "+tn+".Link : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function "+tn+".Clone : "+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := "+tn+"(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");

    getCode(category).classDefs.add(def.toString());
    getCode(category).classImpls.add(impl2.toString() + impl.toString());
    getCode(category).classFwds.add("  "+tn+" = class;\r\n");
    generateParser(tn, category, true, e.typeCode());
    defineList(tn, tn+"List", null, category, false);
  }

  private void generateParser(String tn, ClassCategory category, boolean isElement, String parent) throws Exception {
    String s = workingParserX.toString();
    prsrImpl.append(
        "function TFHIRXmlParser.Parse"+tn.substring(5)+"(element : IXmlDomElement; path : string) : "+tn+";\r\n"+
            "var\r\n"+
        "  child : IXMLDOMElement;\r\n");

    prsrImpl.append(
        "begin\r\n"+
            "  result := "+tn+".create;\r\n"+
        "  try\r\n");
    if (isElement)
      if (category == ClassCategory.Resource)
        prsrImpl.append("    parse"+parent+"Attributes(result, path, element);\r\n");
      else
        prsrImpl.append("    parseElementAttributes(result, path, element);\r\n");

    prsrImpl.append(workingParserXA.toString());

    prsrImpl.append(
        "    child := FirstChild(element);\r\n"+
            "    while (child <> nil) do\r\n"+
        "    begin\r\n");
    if (s.length() >= 11)
      prsrImpl.append("      "+s.substring(11));
    if (!isElement)
      prsrImpl.append(
          "      else\r\n");
    else if (category == ClassCategory.Resource)
      prsrImpl.append(
          "      else if Not Parse"+parent+"Child(result, path, child) then\r\n");
    else if (category == ClassCategory.Component)
      prsrImpl.append(
          "      else if Not ParseBackboneElementChild(result, path, child) then\r\n");
    else 
      prsrImpl.append(
          "      else if Not ParseElementChild(result, path, child) then\r\n");
    prsrImpl.append(
        "         UnknownContent(child, path);\r\n"+
            "      child := NextSibling(child);\r\n"+
        "    end;\r\n");
    if (isElement)
      prsrImpl.append(
          "    closeOutElement(result, element);\r\n");
    prsrImpl.append(
        "\r\n"+
            "    result.link;\r\n"+
            "  finally\r\n"+
            "    result.free;\r\n"+
            "  end;\r\n"+
            "end;\r\n\r\n"
        );

    s = workingComposerX.toString();
    prsrImpl.append(
        "procedure TFHIRXmlComposer.Compose"+tn.substring(5)+"(xml : TXmlBuilder; name : string; elem : "+tn+");\r\n");
    boolean var = false;
    if (s.contains("for i := ")) {
      prsrImpl.append("var\r\n  i : integer;\r\n");
      var = true;
    }
    if (s.contains("ext := ")) {
      if (!var) 
        prsrImpl.append("var\r\n");
      prsrImpl.append("  ext : boolean;\r\n");
    }
    prsrImpl.append(
        "begin\r\n"+
        "  if (elem = nil) then\r\n    exit;\r\n");
    if (isElement)
      if (category == ClassCategory.Resource)
        prsrImpl.append("  compose"+parent+"Attributes(xml, elem);\r\n");
      else
        prsrImpl.append("  composeElementAttributes(xml, elem);\r\n");
    prsrImpl.append(workingComposerXA.toString());        
    prsrImpl.append(
        "  xml.open(name);\r\n");
    if (isElement)
      if (category == ClassCategory.Resource)
        prsrImpl.append("  compose"+parent+"Children(xml, elem);\r\n");
      else if (category == ClassCategory.Component)
        prsrImpl.append("  composeBackboneElementChildren(xml, elem);\r\n");
      else
        prsrImpl.append("  composeElementChildren(xml, elem);\r\n");

    prsrImpl.append(s);
    if (isElement)
      prsrImpl.append("  closeOutElement(xml, elem);\r\n");
    prsrImpl.append(
        "  xml.close(name);\r\n"+
            "end;\r\n\r\n"
        );

    if (tn.substring(5).equals("Extension")) {
      prsrdefJ.append("    procedure Parse"+tn.substring(5)+"(jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+"); overload; {b.}\r\n");
      prsrImpl.append("procedure TFHIRJsonParser.Parse"+tn.substring(5)+"(jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+");\r\n");
      prsrImpl.append("var\r\n");
      prsrImpl.append("  ex : "+tn+";\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  ex := Parse"+tn.substring(5)+"(jsn);\r\n");
      prsrImpl.append("  ex.url := ctxt.tags['url']; {2}\r\n");
      prsrImpl.append("  ctxt.add(ex);\r\n");
      prsrImpl.append("end;\r\n\r\n");
    } else {
      prsrdefJ.append("    procedure Parse"+tn.substring(5)+"(jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+"); overload; {b.}\r\n");
      prsrImpl.append("procedure TFHIRJsonParser.Parse"+tn.substring(5)+"(jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+");\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  ctxt.add(Parse"+tn.substring(5)+"(jsn)); {2}\r\n");
      prsrImpl.append("end;\r\n\r\n");
    }

    s = workingParserJ.toString();
    prsrImpl.append(
        "function TFHIRJsonParser.Parse"+tn.substring(5)+"(jsn : TJsonObject) : "+tn+";\r\n"+
            "begin\r\n"+
            "  result := "+tn+".create;\r\n"+
        "  try\r\n");
    if (isElement) {
      if (category == ClassCategory.Resource)
        prsrImpl.append("    Parse"+parent+"Properties(jsn, result);\r\n");
      else if (category == ClassCategory.Component)
        prsrImpl.append("    ParseBackboneElementProperties(jsn, result);\r\n");
      else
        prsrImpl.append("    ParseElementProperties(jsn, result);\r\n");
    }
    prsrImpl.append(s);
    prsrImpl.append(
        "    result.link;\r\n"+
            "  finally\r\n"+
            "    result.free;\r\n"+
            "  end;\r\n"+
            "end;\r\n\r\n"
        );

    s = workingComposerJ.toString();
    prsrImpl.append(
        "procedure TFHIRJsonComposer.Compose"+tn.substring(5)+"(json : TJSONWriter; name : string; elem : "+tn+"; noObj : boolean = false);\r\n");
    var = false;
    if (s.contains("for i := ")) { // || category == ClassCategory.Resource) {
      prsrImpl.append("var\r\n  i : integer;\r\n");
      var = true;
    }
    if (s.contains("ext := ")) {
      if (!var) 
        prsrImpl.append("var\r\n");
      prsrImpl.append("  ext : boolean;\r\n");
    }
    
    if (category == ClassCategory.Resource)
      prsrImpl.append(
          "begin\r\n"+
          "  if (elem = nil) then\r\n    exit;\r\n");
    else 
      prsrImpl.append(
          "begin\r\n"+
              "  if (elem = nil) then\r\n    exit;\r\n"+
          "  if not noObj then json.valueObject(name);\r\n");
    if (isElement)
      if (category == ClassCategory.Resource)
        prsrImpl.append("  Compose"+parent+"Properties(json, elem);\r\n");
      else if (category == ClassCategory.Component)
        prsrImpl.append("  ComposeBackboneElementProperties(json, elem);\r\n");
      else
        prsrImpl.append("  ComposeElementProperties(json, elem);\r\n");
    prsrImpl.append(s);
    if (category == ClassCategory.Resource)
      prsrImpl.append(
          "end;\r\n\r\n");
    else
      prsrImpl.append(
          "  if not noObj then json.finishObject;\r\n"+
          "end;\r\n\r\n");

  }

  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e, String literalPath, Map<ElementDefn, List<String>> paths) throws Exception {
    literalPath = literalPath +"."+e.getName();
    
    String tn = null;
    if (e.typeCode().equals("code") && e.hasBinding()) {
      BindingSpecification cd = getConceptDomain(e.getBindingName());
      if (cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
        tn = "TFhir"+enumName(getTitle(getCodeList(cd.getReference()).substring(1)));
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        typeNames.put(e,  tn);
      }
    }
    if (!paths.containsKey(e))
      paths.put(e, new ArrayList<String>());
    paths.get(e).add(literalPath);
    if (tn == null) {
      if (e.usesCompositeType()) {
        tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
        typeNames.put(e,  tn);
      } else if (e.getTypes().size() > 0) {
        tn = getTypeName(e);
        typeNames.put(e,  tn);
      } else 
      {
        tn = "TFhir"+path+getTitle(e.getName());
        strucs.add(e);
        typeNames.put(e,  tn);
        for (ElementDefn c : e.getElements()) {
          scanNestedTypes(root, path+getTitle(e.getName()), c, literalPath, paths);
        }
      }
    }
  }

  private Object getElementForPath(ElementDefn root, String pathname) throws Exception {
    String[] path = pathname.split("\\.");
    if (!path[0].equals(root.getName()))
      throw new Exception("Element Path '"+pathname+"' is not legal in this context");
    ElementDefn res = root;
    for (int i = 1; i < path.length; i++)
    {
      String en = path[i];
      if (en.length() == 0)
        throw new Exception("Improper path "+pathname);
      ElementDefn t = res.getElementByName(en);
      if (t == null) {
        throw new Exception("unable to resolve "+pathname);
      }
      res = t; 
    }
    return res;

  }

  private String getCodeList(String binding) {
    StringBuilder b = new StringBuilder();
    boolean up = true;
    for (char ch: binding.toCharArray()) {
      if (ch == '-')
        up = true;
      else if (up) {
        b.append(Character.toUpperCase(ch));
        up = false;
      }
      else        
        b.append(ch);
    }
    return b.toString();
  }

  private BindingSpecification getConceptDomain(String conceptDomain) {
    for (BindingSpecification cd : definitions.getBindings().values())
      if (cd.getName().equals(conceptDomain))
        return cd;
    return null;
  }

  private void generateField(ElementDefn e, StringBuilder defPriv1, StringBuilder defPriv2, StringBuilder defPub, StringBuilder impl, StringBuilder create, StringBuilder destroy, StringBuilder assign, StringBuilder getkids, StringBuilder getkidsvars, StringBuilder getprops, StringBuilder getpropsvars, StringBuilder setprops, String cn, String pt, ClassCategory category, boolean noSummaries, boolean isExtension) throws Exception {
    String tn;
    if (e.getTypes().size() > 0 && e.getTypes().get(0).isUnboundGenericParam())
      tn = pt;
    else
      tn = typeNames.get(e);
    if (tn == null) {
      if (e.getName().equals("extension"))
        tn = "TFhirExtension";
      else
        tn = getTypeName(e);
    }


    String parse = null;
    String ttb = "";
    String tta = "";
    String propV = "F"+getTitle(getElementName(e.getName()));
    if (typeIsSimple(tn)) {
      if (enumNames.contains(tn)) {        
        parse = "ParseEnum(CODES_"+tn+", path+'/"+e.getName()+"', child)";
        ttb = "CODES_"+tn+"[";
        tta = "]";
      } else if (tn.equals("Integer")) {
        parse = "StringToInteger32(child.text)";
        ttb = "inttostr(";
        tta = ")";
        propV = "inttostr("+propV+ ")";
      } else if (tn.equals("Boolean")) {
        parse = "StringToBoolean(child.text)";
        propV = "LCBooleanToString("+propV+ ")";
      } else if (tn.equals("TDateAndTime")) {
        parse = "TDateAndTime.createXml(child.text)";
        propV = propV+".AsXML";
      } else if (tn.equals("TFhirXHtmlNode"))
        parse = "ParseXhtml(child)";
      else
        parse = "child.text";
    } else if (tn.equals("TSmartDecimal")) 
      propV = propV+".asString";
    String parseJ1 = null;
    if (enumNames.contains(tn)) {
      parseJ1 = "ParseEnumValue(CODES_"+tn+"')";
    } else if (tn.equals("Integer")) {
      parseJ1 = "ParseIntegerValue(path+'."+e.getName()+"')";
    } else if (tn.equals("Boolean") || tn.equals("TFhirBoolean")) {
      parseJ1 = "ParseBooleanValue(path+'."+e.getName()+"')";
    } else if (tn.equals("TDateAndTime") || tn.equals("TFhirDateTime") || tn.equals("TFhirDate") || tn.equals("TFhirInstant")) {
      parseJ1 = "ParseDateAndTimeValue(path+'."+e.getName()+"')";
    } else if (tn.equals("TFhirXHtmlNode")) {
      parseJ1 = "ParseXhtml(path+'."+e.getName()+"')";
    } else {
      parseJ1 = "ParseStringValue(path+'."+e.getName()+"')";
    }
    String srlsd = "Text";
    String srlsdJ = "Prop";
    String srls = "#";
    if (typeIsSimple(tn)) {
      if (enumNames.contains(tn)) {
        srls = "CODES_"+tn+"[#]";
      } else if (tn.equals("Integer")) {
        srls = "IntegerToString(#)";
      } else if (tn.equals("Boolean")) {
        srls = "LCBooleanToString(#)";
      } else if (tn.equals("TDateAndTime")) {
        srls = "#.AsXml";
      };
    }

    String s = getElementName(e.getName()); 
    boolean summary = e.isSummary() || noSummaries;
    String sumAnd = summary ? "" : "Not SummaryOnly and ";
    String sum2 = summary ? "" : "if not SummaryOnly then\r\n    ";
    if (e.unbounded()) {
      if (enumNames.contains(tn)) {         
        defPriv1.append("    F"+getTitle(s)+" : "+listForm("TFhirEnum")+";\r\n");
        defPriv2.append("    function Get"+Utilities.capitalize(s)+" : "+listForm("TFhirEnum")+";\r\n");
        defPriv2.append("    function GetHas"+Utilities.capitalize(s)+" : Boolean;\r\n");
        if (enumSizes.get(tn) < 32) {
          defPriv2.append("    Function Get"+getTitle(s)+"ST : "+listForm(tn)+";\r\n");
          defPriv2.append("    Procedure Set"+getTitle(s)+"ST(value : "+listForm(tn)+");\r\n");
        }
        assign.append("  if ("+cn+"(oSource).F"+getTitle(s)+" = nil) then\r\n");
        assign.append("  begin\r\n");
        assign.append("    F"+getTitle(s)+".free;\r\n");
        assign.append("    F"+getTitle(s)+" := nil;\r\n");
        assign.append("  end\r\n");
        assign.append("  else\r\n");
        assign.append("  begin\r\n");
        assign.append("    F"+getTitle(s)+" := "+listForm("TFHIREnum")+".Create;\r\n");
        if (enumSizes.get(tn) < 32) {
          defPub.append("    {@member "+s+"\r\n");
          defPub.append("      "+makeDocoSafe(e.getDefinition())+"\r\n");
          defPub.append("    }\r\n");
          defPub.append("    property "+s+" : "+listForm(tn)+" read Get"+getTitle(s)+"ST write Set"+getTitle(s)+"ST;\r\n");
          defPub.append("    property "+s+"Element : "+listForm("TFhirEnum")+" read Get"+getTitle(s)+";\r\n");
          assign.append("    F"+getTitle(s)+".Assign("+cn+"(oSource).F"+getTitle(s)+");\r\n");
        } else {
          defPub.append("    property "+s+" : "+listForm("TFhirEnum")+" read Get"+getTitle(s)+";\r\n");
          assign.append("    F"+getTitle(s)+".Assign("+cn+"(oSource).F"+getTitle(s)+");\r\n");
        }
        assign.append("  end;\r\n");
        defPub.append("    property has"+Utilities.capitalize(s)+" : boolean read GetHas"+getTitle(s)+";\r\n");
        // no, do it lazy create.append("  F"+getTitle(s)+" := "+listForm("TFHIREnum")+".Create;\r\n");
        impl.append("Function "+cn+".Get"+getTitle(s)+" : "+listForm("TFhirEnum")+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    F"+getTitle(s)+" := "+listForm("TFHIREnum")+".Create;\r\n  result := F"+getTitle(s)+";\r\nend;\r\n\r\n");
        impl.append("Function "+cn+".GetHas"+getTitle(s)+" : boolean;\r\nbegin\r\n  result := (F"+getTitle(s)+" <> nil) and (F"+getTitle(s)+".count > 0);\r\nend;\r\n\r\n");
        destroy.append("  F"+getTitle(s)+".Free;\r\n");
        if (generics)
          getkidsvars.append("  o : TFHIREnum;\r\n");
        if (e.getName().endsWith("[x]") || e.getName().equals("[type]")) 
          if (generics)
            getkids.append("  if StringStartsWith(child_name, '"+getPropertyName(e.getName())+"') Then\r\n    for o in F"+getTitle(s)+" do\r\n       list.add(o.Link);\r\n");
          else
            getkids.append("  if StringStartsWith(child_name, '"+getPropertyName(e.getName())+"') Then\r\n    list.addAll(F"+getTitle(s)+");\r\n");
        else
          if (generics)
            getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n     for o in F"+getTitle(s)+" do\r\n       list.add(o.Link);\r\n");
          else
            getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n     list.addAll(F"+getTitle(s)+");\r\n");
        if (generics) {
          getpropsvars.append("  o : TFHIREnum;\r\n");
          getprops.append("  prop := oList[oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"'))];\r\n  for o in F"+getTitle(s)+" do\r\n      prop,list.add(o.Link){3};\r\n");
        } else
          getprops.append("  oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"', F"+getTitle(s)+".Link)){3};\r\n");
        if (e.getName().endsWith("[x]"))
          throw new Exception("Not done yet");
        setprops.append("  else if (propName = '"+e.getName()+"') then F"+getTitle(s)+".add(propValue as TFHIREnum) {1}\r\n");
        String obj = "";
        if (enumSizes.get(tn) < 32) {
          impl.append("Function "+cn+".Get"+getTitle(s)+"ST : "+listForm(tn)+";\r\n  var i : integer;\r\nbegin\r\n  result := [];\r\n  for i := 0 to F"+s+".count - 1 do\r\n    result := result + ["+tn+"(StringArrayIndexOfSensitive(CODES_"+tn+", F"+s+"[i].value))];\r\nend;\r\n\r\n");
          impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+listForm(tn)+");\r\nvar a : "+tn+";\r\nbegin\r\n  F"+s+".clear;\r\n  for a := low("+tn+") to high("+tn+") do\r\n    if a in value then\r\n      F"+s+".add(TFhirEnum.create(CODES_"+tn+"[a]));\r\nend;\r\n\r\n");
          obj = "Element";
        }

        workingParserX.append("      else if (child.baseName = '"+e.getName()+"') then\r\n"+
            "        result."+s+obj+".Add("+parse+"){y.1}\r\n");
        if (summary)
          workingComposerX.append("  for i := 0 to elem."+s+obj+".Count - 1 do\r\n"+
              "    ComposeEnum(xml, '"+e.getName()+"', elem."+s+obj+"[i], CODES_"+tn+");\r\n");
        else 
          workingComposerX.append("  if not SummaryOnly then\r\n    for i := 0 to elem."+s+obj+".Count - 1 do\r\n"+
              "      ComposeEnum(xml, '"+e.getName()+"', elem."+s+obj+"[i], CODES_"+tn+");\r\n");

        workingParserJ.append(
            "    if jsn.has('"+e.getName()+"') or jsn.has('_"+e.getName()+"') then\r\n"+
                "      iterateEnumArray(jsn.vArr['"+e.getName()+"'], jsn.vArr['_"+e.getName()+"'], result."+s+obj+", parseEnum, CODES_"+tn+");\r\n");

        if (summary)
          workingComposerJ.append("  if elem."+s+obj+".Count > 0 then\r\n");
        else
          workingComposerJ.append("  if not SummaryOnly and (elem."+s+obj+".Count > 0) then\r\n");
        workingComposerJ.append(
            "  begin\r\n"+
                "    json.valueArray('"+e.getName()+"');\r\n"+
                "    ext := false;\r\n"+    
                "    for i := 0 to elem."+s+obj+".Count - 1 do\r\n"+
                "    begin\r\n"+
                "      ext := ext or ((elem."+s+obj+"[i].id <> '') or (elem."+s+obj+"[i].hasExtensionList));\r\n"+
                "      ComposeEnumValue(json, '', elem."+s+obj+"[i], CODES_"+tn+", true);\r\n"+
                "    end;\r\n"+
                "    json.FinishArray;\r\n"+
                "    if ext then\r\n"+
                "    begin\r\n"+
                "      json.valueArray('_"+e.getName()+"');\r\n"+
                "      for i := 0 to elem."+s+obj+".Count - 1 do\r\n"+
                "        ComposeEnumProps(json, '', elem."+s+obj+"[i], CODES_"+tn+", true);\r\n"+
                "      json.FinishArray;\r\n"+
                "    end;\r\n"+
            "  end;\r\n");

      } else {
        String tnl;
        if (tn.contains("{"))
          tnl = listForm(tn.substring(0, tn.indexOf('{')))+tn.substring(tn.indexOf('{'));
        else
          tnl = listForm(tn);
        s = s+"List";
        defPriv1.append("    F"+s+" : "+tnl+";\r\n");
        defPub.append("    {@member "+s+"\r\n");
        defPub.append("      "+makeDocoSafe(e.getDefinition())+"\r\n");
        defPub.append("    }\r\n");
        defPriv2.append("    function Get"+Utilities.capitalize(s)+" : "+tnl+";\r\n");
        defPriv2.append("    function GetHas"+Utilities.capitalize(s)+" : Boolean;\r\n");
        defPub.append("    property "+s+" : "+tnl+" read Get"+getTitle(s)+";\r\n");
        defPub.append("    property has"+Utilities.capitalize(s)+" : boolean read GetHas"+getTitle(s)+";\r\n");
        defPub.append("\r\n");
        impl.append("Function "+cn+".Get"+getTitle(s)+" : "+tnl+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    F"+getTitle(s)+" := "+tnl+".Create;\r\n  result := F"+getTitle(s)+";\r\nend;\r\n\r\n");
        impl.append("Function "+cn+".GetHas"+getTitle(s)+" : boolean;\r\nbegin\r\n  result := (F"+getTitle(s)+" <> nil) and (F"+getTitle(s)+".count > 0);\r\nend;\r\n\r\n");
        // create.append("  F"+getTitle(s)+z" := "+tnl+".Create;\r\n");
        destroy.append("  F"+getTitle(s)+".Free;\r\n");
        assign.append("  if ("+cn+"(oSource).F"+getTitle(s)+" = nil) then\r\n");
        assign.append("  begin\r\n");
        assign.append("    F"+getTitle(s)+".free;\r\n");
        assign.append("    F"+getTitle(s)+" := nil;\r\n");
        assign.append("  end\r\n");
        assign.append("  else\r\n");
        assign.append("  begin\r\n");
        assign.append("    F"+getTitle(s)+" := "+tnl+".Create;\r\n");
        assign.append("    F"+getTitle(s)+".Assign("+cn+"(oSource).F"+getTitle(s)+");\r\n");
        assign.append("  end;\r\n");
        if (generics) {
          getkidsvars.append("  o"+getTitle(s)+" : "+tn+";\r\n");
          getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n    for o"+getTitle(s)+" in F"+getTitle(s)+" do\r\n      list.add(o"+getTitle(s)+");\r\n");
        } else
          getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n    list.addAll(F"+getTitle(s)+");\r\n");
        if (generics) {
          getpropsvars.append("  o"+getTitle(s)+" : "+tn+";\r\n");
          getprops.append("  prop := oList[oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"'))];\r\n  for o"+getTitle(s)+" in F"+getTitle(s)+" do\r\n    prop.List.add(o"+getTitle(s)+".Link){3a};\r\n");
        } else
          getprops.append("  oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"', F"+getTitle(s)+".Link)){3};\r\n");
        if (e.getName().endsWith("[x]"))
          throw new Exception("Not done yet");
        setprops.append("  else if (propName = '"+e.getName()+"') then "+getTitle(s)+".add(propValue as "+tn+"){2}\r\n");

        if (!typeIsSimple(tn)) {
          if (!e.getName().equals("[type]") && !e.getName().contains("[x]")) {
            parse = "Parse"+parseName(tn)+"(child, path+'/"+e.getName()+"')";
            if (!typeIsPrimitive(e.typeCode()))
              parseJ1 = "Parse"+parseName(tn)+"(path+'."+e.getName()+"')";
            srlsd = "Compose"+parseName(tn);
            srlsdJ = "Compose"+parseName(tn);
          } else {
            throw new Exception("not supported at "+e.getName()+" - complex type "+tn);
          }
        };
        workingParserX.append("      else if (child.baseName = '"+e.getName()+"') then\r\n"+
            "        result."+s+".Add("+parse+"){y.2}\r\n");
        if (summary) 
          workingComposerX.append("  for i := 0 to elem."+s+".Count - 1 do\r\n"+
              "    "+srlsd+"(xml, '"+e.getName()+"', "+srls.replace("#", "elem."+s+"[i]")+");\r\n");
        else
          workingComposerX.append("  if not SummaryOnly then\r\n    for i := 0 to elem."+s+".Count - 1 do\r\n"+
              "      "+srlsd+"(xml, '"+e.getName()+"', "+srls.replace("#", "elem."+s+"[i]")+");\r\n");
        if (typeIsPrimitive(e.typeCode())) 
          workingParserJ.append(
              "      if jsn.has('"+e.getName()+"') or jsn.has('_"+e.getName()+"') then\r\n"+
                  "      iteratePrimitiveArray(jsn.vArr['"+e.getName()+"'], jsn.vArr['_"+e.getName()+"'], result."+s+", parse"+parseName(tn)+");\r\n");
        else 
          workingParserJ.append("    if jsn.has('"+e.getName()+"') then\r\n"+
              "      iterateArray(jsn.vArr['"+e.getName()+"'], result."+s+", parse"+parseName(tn)+");\r\n");

        if (summary)
          workingComposerJ.append("  if elem."+s+".Count > 0 then\r\n");
        else
          workingComposerJ.append("  if not SummaryOnly and (elem."+s+".Count > 0) then\r\n");
        if (typeIsPrimitive(e.typeCode())) 
          workingComposerJ.append(
              "  begin\r\n"+
                  "    json.valueArray('"+e.getName()+"');\r\n"+
                  "    ext := false;\r\n"+    
                  "    for i := 0 to elem."+s+".Count - 1 do\r\n"+
                  "    begin\r\n"+
                  "      ext := ext or ((elem."+s+"[i].id <> '') or (elem."+s+"[i].hasExtensionList));\r\n"+
                  "      "+srlsdJ+"Value(json, '',"+srls.replace("#", "elem."+s+"[i]")+", true);\r\n"+
                  "    end;\r\n"+
                  "    json.FinishArray;\r\n"+
                  "    if ext then\r\n"+
                  "    begin\r\n"+
                  "      json.valueArray('_"+e.getName()+"');\r\n"+
                  "      for i := 0 to elem."+s+".Count - 1 do\r\n"+
                  "        "+srlsdJ+"Props(json, '',"+srls.replace("#", "elem."+s+"[i]")+", true);\r\n"+
                  "      json.FinishArray;\r\n"+
                  "    end;\r\n"+
              "  end;\r\n");
       else
          workingComposerJ.append(
              "  begin\r\n"+
                  "    json.valueArray('"+e.getName()+"');\r\n"+
                  "    for i := 0 to elem."+s+".Count - 1 do\r\n"+
                  "      "+srlsdJ+"(json, '',"+srls.replace("#", "elem."+s+"[i]")+"); {z - "+e.typeCode()+"}\r\n"+
                  "    json.FinishArray;\r\n"+
              "  end;\r\n");
      }
    } else {
      if (enumNames.contains(tn)) {         
        defPriv1.append("    F"+getTitle(s)+" : TFhirEnum;\r\n"); 
        defPriv2.append("    Procedure Set"+getTitle(s)+"(value : TFhirEnum);\r\n");
        defPriv2.append("    Function Get"+getTitle(s)+"ST : "+tn+";\r\n");
        defPriv2.append("    Procedure Set"+getTitle(s)+"ST(value : "+tn+");\r\n");
        defPub.append("    {@member "+s+"\r\n");
        defPub.append("      "+makeDocoSafe(e.getDefinition())+"\r\n");
        defPub.append("    }\r\n");
        defPub.append("    property "+s+" : "+tn+" read Get"+getTitle(s)+"ST write Set"+getTitle(s)+"ST;\r\n");
        defPub.append("    property "+s+"Element : TFhirEnum read F"+getTitle(s)+" write Set"+getTitle(s)+";\r\n");
      } else {
        defPriv1.append("    F"+getTitle(s)+" : "+tn+";\r\n");
        defPriv2.append("    Procedure Set"+getTitle(s)+"(value : "+tn+");\r\n");
        if (simpleTypes.containsKey(tn)) {
          String sn = simpleTypes.get(tn);
          defPriv2.append("    Function Get"+getTitle(s)+"ST : "+sn+";\r\n");
          defPriv2.append("    Procedure Set"+getTitle(s)+"ST(value : "+sn+");\r\n");
          defPub.append("    {@member "+s+"\r\n");
          defPub.append("      Typed access to "+makeDocoSafe(e.getDefinition())+"\r\n");
          defPub.append("    }\r\n");
          defPub.append("    property "+s+" : "+sn+" read Get"+getTitle(s)+"ST write Set"+getTitle(s)+"ST;\r\n");
          defPub.append("    {@member "+s+"Element\r\n");
          defPub.append("      "+makeDocoSafe(e.getDefinition())+"\r\n");
          defPub.append("    }\r\n");
          defPub.append("    property "+s+"Element : "+tn+" read F"+getTitle(s)+" write Set"+getTitle(s)+";\r\n");
        } else {
          defPub.append("    {@member "+s+"\r\n");
          defPub.append("      Typed access to "+makeDocoSafe(e.getDefinition())+" (defined for API consistency)\r\n");
          defPub.append("    }\r\n");
          defPub.append("    property "+s+" : "+tn+" read F"+getTitle(s)+" write Set"+getTitle(s)+";\r\n");
          defPub.append("    {@member "+s+"Element\r\n");
          defPub.append("      "+makeDocoSafe(e.getDefinition())+"\r\n");
          defPub.append("    }\r\n");
          defPub.append("    property "+s+"Element : "+tn+" read F"+getTitle(s)+" write Set"+getTitle(s)+";\r\n");
        }
      }
      defPub.append("\r\n");
      if (typeIsSimple(tn) && !tn.equals("TFhirXHtmlNode")) {
        if (enumNames.contains(tn)) {         
          impl.append("Procedure "+cn+".Set"+getTitle(s)+"(value : TFhirEnum);\r\nbegin\r\n  F"+getTitle(s)+".free;\r\n  F"+getTitle(s)+" := value;\r\nend;\r\n\r\n");
          impl.append("Function "+cn+".Get"+getTitle(s)+"ST : "+tn+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    result := "+tn+"(0)\r\n  else\r\n    result := "+tn+"(StringArrayIndexOfSensitive(CODES_"+tn+", F"+getTitle(s)+".value));\r\nend;\r\n\r\n");
          impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+tn+");\r\nbegin\r\n  if ord(value) = 0 then\r\n    "+getTitle(s)+"Element := nil\r\n  else\r\n    "+getTitle(s)+"Element := TFhirEnum.create(CODES_"+tn+"[value]);\r\nend;\r\n\r\n");
          setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+"Element := propValue as TFHIREnum\r\n");
        } else {
          impl.append("Procedure "+cn+".Set"+getTitle(s)+"(value : TFhirEnum);\r\nbegin\r\n  F"+getTitle(s)+".free;\r\n  F"+getTitle(s)+" := value;\r\nend;\r\n\r\n");
          impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+tn+");\r\nbegin\r\n  if ord(value) = 0 then\r\n    "+getTitle(s)+" := nil\r\n  else\r\n    "+getTitle(s)+" := TFhirEnum.create(CODES_"+tn+"[value]);\r\nend;\r\n\r\n");
          setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+" := propValue as TFHIREnum\r\n");
        }
        assign.append("  F"+getTitle(s)+" := "+cn+"(oSource).F"+getTitle(s)+".Link;\r\n");
        getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n     list.add(F"+getTitle(s)+".Link);\r\n");
        getprops.append("  oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"', "+propV+".Link));{1}\r\n");
        if (e.getName().endsWith("[x]"))
          throw new Exception("Not done yet");
        if (e.isXmlAttribute())
          workingParserXA.append("    result."+s+"ST := fix me (and compose)! GetAttribute(element, '"+e.getName()+"');\r\n");
        else  
          workingParserX.append("      else if (child.baseName = '"+e.getName()+"') then\r\n        result."+s+"Element := "+parse+"{1a}\r\n");
        workingParserJ.append("    if jsn.has('"+e.getName()+"') or jsn.has('_"+e.getName()+"')  then\r\n"+
            "      result."+s+"Element := parseEnum(jsn['"+e.getName()+"'], jsn.vObj['_"+e.getName()+"'], CODES_"+tn+");\r\n");
        destroy.append("  F"+getTitle(s)+".free;\r\n");
        if (enumNames.contains(tn)) {         
          workingComposerX.append("  "+sum2+"ComposeEnum(xml, '"+e.getName()+"', elem."+getTitle(s)+"Element, CODES_"+tn+");\r\n");
          workingComposerJ.append("  "+sum2+"ComposeEnumValue(json, '"+e.getName()+"', elem."+getTitle(s)+"Element, CODES_"+tn+", false);\r\n");
          workingComposerJ.append("  "+sum2+"ComposeEnumProps(json, '"+e.getName()+"', elem."+getTitle(s)+"Element, CODES_"+tn+", false);\r\n");
        } else {
          workingComposerX.append("  "+sum2+"Compose"+tn+"(xml, '"+e.getName()+"', elem."+getTitle(s)+");{x.1}\r\n");
          workingComposerJ.append("  "+sum2+"Compose"+tn+"Value(json, '"+e.getName()+"', elem."+getTitle(s)+", false); {1}\r\n");        
          workingComposerJ.append("  "+sum2+"Compose"+tn+"Props(json, '"+e.getName()+"', elem."+getTitle(s)+", false); {y}\r\n");        
        }
      }
      else {
        impl.append("Procedure "+cn+".Set"+getTitle(s)+"(value : "+tn+");\r\nbegin\r\n  F"+getTitle(s)+".free;\r\n  F"+getTitle(s)+" := value;\r\nend;\r\n\r\n");
        if (simpleTypes.containsKey(tn)) {
          String sn = simpleTypes.get(tn);
          if (sn.equals("String")) {
            impl.append("Function "+cn+".Get"+getTitle(s)+"ST : "+sn+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    result := ''\r\n  else\r\n    result := F"+getTitle(s)+".value;\r\nend;\r\n\r\n");
            impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+sn+");\r\nbegin\r\n  if value <> '' then\r\n  begin\r\n    if F"+getTitle(s)+" = nil then\r\n      F"+getTitle(s)+" := "+tn+".create;\r\n    F"+getTitle(s)+".value := value\r\n  end\r\n  else if F"+getTitle(s)+" <> nil then\r\n    F"+getTitle(s)+".value := '';\r\nend;\r\n\r\n");
          } else if (sn.equals("Boolean")) {
            impl.append("Function "+cn+".Get"+getTitle(s)+"ST : "+sn+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    result := false\r\n  else\r\n    result := F"+getTitle(s)+".value;\r\nend;\r\n\r\n");
            impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+sn+");\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    F"+getTitle(s)+" := "+tn+".create;\r\n  F"+getTitle(s)+".value := value\r\nend;\r\n\r\n");
          } else {
            impl.append("Function "+cn+".Get"+getTitle(s)+"ST : "+sn+";\r\nbegin\r\n  if F"+getTitle(s)+" = nil then\r\n    result := nil\r\n  else\r\n    result := F"+getTitle(s)+".value;\r\nend;\r\n\r\n");
            impl.append("Procedure "+cn+".Set"+getTitle(s)+"ST(value : "+sn+");\r\nbegin\r\n  if value <> nil then\r\n  begin\r\n    if F"+getTitle(s)+" = nil then\r\n      F"+getTitle(s)+" := "+tn+".create;\r\n    F"+getTitle(s)+".value := value\r\n  end\r\n  else if F"+getTitle(s)+" <> nil then\r\n    F"+getTitle(s)+".value := nil;\r\nend;\r\n\r\n");
          }
          assign.append("  "+s+"Element := "+cn+"(oSource)."+s+"Element.Clone;\r\n");
        } else
          assign.append("  "+s+" := "+cn+"(oSource)."+s+".Clone;\r\n");
        destroy.append("  F"+getTitle(s)+".free;\r\n");
        getkids.append("  if (child_name = '"+e.getName()+"') Then\r\n     list.add(F"+getTitle(s)+".Link);\r\n");
        getprops.append("  oList.add(TFHIRProperty.create(self, '"+e.getName()+"', '"+breakConstant(e.typeCode())+"', "+propV+".Link));{2}\r\n");
        if (e.getName().endsWith("[x]")) {
          if (!typeIsPrimitive(e.typeCode()))
            setprops.append("  else if (propName.startsWith('"+e.getName().substring(0, e.getName().length()-3)+"')) then "+propV.substring(1)+" := propValue as "+tn+"{4}\r\n");
          else 
            setprops.append("  else if (propName.startsWith('"+e.getName().substring(0, e.getName().length()-3)+"')) then "+propV.substring(1)+" := propValue as "+tn+"{5}\r\n");
        } else {
          if (!typeIsPrimitive(e.typeCode()))
            if (simpleTypes.containsKey(tn))
              setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+"Element := propValue as "+tn+"{4a}\r\n");
            else
              setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+" := propValue as "+tn+"{4b}\r\n");
          else
            if (!simpleTypes.containsKey(tn))
              setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+" := propValue as "+tn+"{5b}\r\n");          
            else if (tn.equals("TFhirCode"))
              setprops.append("  else if (propName = '"+e.getName()+"') then\r\n    if propValue is TFHIRCode then\r\n      "+propV.substring(1)+"Element := propValue as "+
                 "TFhirCode{5}\r\n    else if propValue is TFHIREnum then\r\n      "+propV.substring(1)+"Element := TFHIRCode.create(TFHIREnum(propValue).value)\r\n    else\r\n      raise Exception.Create('Type mismatch: cannot convert from \"'+propValue.className+'\" to \"TFHIRCode\"'){5a}\r\n");          
            else
              setprops.append("  else if (propName = '"+e.getName()+"') then "+propV.substring(1)+"Element := propValue as "+tn+"{5a}\r\n");          
            
        }
        if (e.getName().contains("[x]") && e.getTypes().size() > 1) {
          String pfx = e.getName().replace("[x]", "");
          int t = e.getTypes().size();
          int i = 0;
          for (TypeRef td : e.getTypes()) {
            if (td.isResourceReference()) {
              workingParserX.append("      else if (child.baseName = '"+pfx+"Reference') then\r\n        result."+s+" := ParseReference(child, path+'/"+pfx+"Reference') {a}\r\n");
              workingComposerX.append("  "+(i==0 ? "if" : "else if")+" "+sumAnd+"(elem."+s+" is TFhirReference) {2} then\r\n    ComposeReference(xml, '"+pfx+"Reference', TFhirReference(elem."+s+"))"+(i == t-1?";" : "")+"\r\n");
              workingParserJ.append("    if jsn.has('"+pfx+"Reference') {a3} then\r\n      result."+s+" := ParseReference(jsn.vObj['"+pfx+"Reference']);\r\n");
              workingComposerJ.append("  "+(i==0 ? "if" : "else if")+" "+sumAnd+"(elem."+s+" is TFhirReference) then\r\n    ComposeReference(json, '"+pfx+"Reference', TFhirReference(elem."+s+"))"+(i == t-1?";" : "")+"\r\n");
            }
            else {
              if (td.hasParams())
                throw new Exception("Type "+td.summary()+" has parameters");                
              workingParserX.append("      else if (child.baseName = '"+pfx+getTitle(td.getName())+"') then\r\n        result."+s+" := Parse"+getTitle(td.getName())+"(child, path+'/"+pfx+getTitle(td.getName())+"'){x.3}\r\n");
              workingComposerX.append("  "+(i==0 ? "if" : "else if")+" "+sumAnd+"(elem."+s+" is "+getTypeName(td.getName())+") {6} then\r\n    Compose"+getTitle(td.getName())+"(xml, '"+pfx+getTitle(td.getName())+"', "+getTypeName(td.getName())+"(elem."+s+"))"+(i == t-1?";" : "")+"\r\n");
              if (typeIsPrimitive(td.getName())) {
                workingComposerJ.append("  "+(i==0 ? "if" : "else if")+" "+sumAnd+"(elem."+s+" is "+getTypeName(td.getName())+") then \r\n"+
                    "  begin\r\n"+
                    "    Compose"+getTitle(td.getName())+"Value(json, '"+pfx+getTitle(td.getName())+"', "+getTypeName(td.getName())+"(elem."+s+"), false);\r\n"+
                    "    Compose"+getTitle(td.getName())+"Props(json, '"+pfx+getTitle(td.getName())+"', "+getTypeName(td.getName())+"(elem."+s+"), false);\r\n  end"+(i == t-1?";" : "")+"\r\n");
                workingParserJ.append("    if jsn.has('"+pfx+getTitle(td.getName())+"') or jsn.has('_"+pfx+getTitle(td.getName())+"') then\r\n      result."+s+" := parse"+Utilities.capitalize(td.getName())+"(jsn['"+pfx+getTitle(td.getName())+"'], jsn.vObj['_"+pfx+getTitle(td.getName())+"']);\r\n");
              } else {
                workingComposerJ.append("  "+(i==0 ? "if" : "else if")+" "+sumAnd+"(elem."+s+" is "+getTypeName(td.getName())+") then \r\n"+
                    "    Compose"+getTitle(td.getName())+"(json, '"+pfx+getTitle(td.getName())+"', "+getTypeName(td.getName())+"(elem."+s+")) "+(i == t-1?";" : "")+"\r\n");
                workingParserJ.append("    if jsn.has('"+pfx+getTitle(td.getName())+"') {a4} then\r\n      result."+s+" := Parse"+getTitle(td.getName())+"(jsn.vObj['"+pfx+getTitle(td.getName())+"']);\r\n");
              }
            }
            i++;
          }

        } else if (!e.getName().equals("[type]") && !e.getName().contains("[x]")) {
          if (e.isXmlAttribute()) {
            workingParserXA.append("    result."+s+" := GetAttribute(element, '"+e.getName()+"');{x.4}\r\n");
            workingComposerXA.append("  Attribute(xml, '"+e.getName()+"', elem."+s+"  );\r\n");
          } else {  
            if (typeIsPrimitive(e.typeCode())) { 
              workingParserX.append("      else if (child.baseName = '"+e.getName()+"') then\r\n        result."+s+"Element := Parse"+parseName(tn)+"(child, path+'/"+e.getName()+"') {b}\r\n");
              workingComposerX.append("  "+sum2+"Compose"+parseName(tn)+"(xml, '"+e.getName()+"', elem."+s+"Element);{x.2}\r\n");
            } else {
              workingParserX.append("      else if (child.baseName = '"+e.getName()+"') then\r\n        result."+s+" := Parse"+parseName(tn)+"(child, path+'/"+e.getName()+"') {b}\r\n");
              workingComposerX.append("  "+sum2+"Compose"+parseName(tn)+"(xml, '"+e.getName()+"', elem."+s+");{x.2}\r\n");
            }
          }
            if (typeIsPrimitive(e.typeCode())) {
              workingParserJ.append("    if jsn.has('"+e.getName()+"') or jsn.has('_"+e.getName()+"') then\r\n        result."+s+"Element := Parse"+parseName(tn)+"(jsn['"+e.getName()+"'], jsn.vObj['_"+e.getName()+"']);{q}\r\n");
            } else if (e.typeCode().equals("xhtml"))
              workingParserJ.append("    if jsn.has('"+e.getName()+"') then\r\n        result."+s+" := Parse"+parseName(tn)+"(jsn.path+'.div', jsn['"+e.getName()+"']);{q}\r\n");
            else
              workingParserJ.append("    if jsn.has('"+e.getName()+"') then\r\n        result."+s+" := Parse"+parseName(tn)+"(jsn.vObj['"+e.getName()+"']);{q}\r\n");
            if (typeIsPrimitive(e.typeCode())) {
              workingComposerJ.append("  "+sum2+"Compose"+parseName(tn)+"Value(json, '"+e.getName()+"', elem."+s+"Element, false);\r\n");
              workingComposerJ.append("  "+sum2+"Compose"+parseName(tn)+"Props(json, '"+e.getName()+"', elem."+s+"Element, false);\r\n");
            } else
              workingComposerJ.append("  "+sum2+"Compose"+parseName(tn)+"(json, '"+e.getName()+"', elem."+s+"); {a}\r\n");
        } else {
          String pfx = e.getName().contains("[x]") ? e.getName().replace("[x]", "") : "";
          int i = 0;
          for (DefinedCode cd : definitions.getPrimitives().values()) {
            workingParserX.append("      else if (child.baseName = '"+pfx+getTitle(cd.getCode())+"') then\r\n        result."+s+" := Parse"+getTitle(cd.getCode())+"(child, path+'."+pfx+getTitle(cd.getCode())+"') {c}\r\n");
            String ptn = "TFhir"+getTitle(cd.getCode());
            workingComposerX.append("  "+(i > 0 ? "else " : "")+"if "+sumAnd+"(elem."+s+" is "+ptn+") {1} then\r\n    Compose"+ptn.substring(5)+"(xml, '"+pfx+getTitle(cd.getCode())+"', "+ptn+"(elem."+s+"))\r\n");
            workingParserJ.append("    if jsn.has('"+pfx+getTitle(cd.getCode())+"') or jsn.has('_"+pfx+getTitle(cd.getCode())+"') then\r\n        result."+s+" := Parse"+getTitle(cd.getCode())+"(jsn['"+pfx+getTitle(cd.getCode())+"'], jsn.vObj['_"+pfx+getTitle(cd.getCode())+"']);\r\n");
            workingComposerJ.append("  "+(i > 0 ? "else " : "")+"if "+sumAnd+"(elem."+s+" is "+ptn+") then\r\n"+
                "  begin\r\n"+
                "    Compose"+ptn.substring(5)+"Value(json, '"+pfx+getTitle(cd.getCode())+"', "+ptn+"(elem."+s+"), false);\r\n"+
                "    Compose"+ptn.substring(5)+"Props(json, '"+pfx+getTitle(cd.getCode())+"', "+ptn+"(elem."+s+"), false)\r\n"+
                "  end\r\n");
            i++;
          }
          for (ElementDefn ed : definitions.getTypes().values()) {
            workingParserX.append("      else if (child.baseName = '"+pfx+getTitle(ed.getName())+"') then\r\n        result."+s+" := Parse"+getTitle(ed.getName())+"(child, path+'."+pfx+getTitle(ed.getName())+"') {e"+ed.getName()+"}\r\n");
            workingComposerX.append("  else if "+sumAnd+"(elem."+s+" is TFhir"+getTitle(ed.getName())+") {8} then\r\n    Compose"+getTitle(ed.getName())+"(xml, '"+pfx+getTitle(ed.getName())+"', TFhir"+getTitle(ed.getName())+"(elem."+s+"))\r\n");
            workingParserJ.append("    if jsn.has('"+pfx+getTitle(ed.getName())+"') {a7} then\r\n        result."+s+" := Parse"+getTitle(ed.getName())+"(jsn.vObj['"+pfx+getTitle(ed.getName())+"']);\r\n");
            workingComposerJ.append("  else if "+sumAnd+"(elem."+s+" is TFhir"+getTitle(ed.getName())+") then\r\n    Compose"+getTitle(ed.getName())+"(json, '"+pfx+getTitle(ed.getName())+"', TFhir"+getTitle(ed.getName())+"(elem."+s+"))\r\n");
          }
          int t = definitions.getStructures().size();
          i = 0;
          for (ElementDefn ed : definitions.getStructures().values()) {
            workingParserX.append("      else if (child.baseName = '"+pfx+getTitle(ed.getName())+"') then\r\n        result."+s+" := Parse"+getTitle(ed.getName())+"(child, path+'/"+pfx+getTitle(ed.getName())+"') {f}\r\n");
            workingComposerX.append("  else if "+sumAnd+"(elem."+s+" is TFhir"+getTitle(ed.getName())+") {9} then\r\n    Compose"+getTitle(ed.getName())+"(xml, '"+pfx+getTitle(ed.getName())+"', TFhir"+getTitle(ed.getName())+"(elem."+s+"))"+(i < t-1 ? "" : ";")+"\r\n");
            workingParserJ.append("    if jsn.has('"+pfx+getTitle(ed.getName())+"') {a9} then\r\n        result."+s+" := Parse"+getTitle(ed.getName())+"(jsn.vObj['"+pfx+getTitle(ed.getName())+"']);\r\n");
            workingComposerJ.append("  else if "+sumAnd+"(elem."+s+" is TFhir"+getTitle(ed.getName())+") then\r\n    Compose"+getTitle(ed.getName())+"(json, '"+pfx+getTitle(ed.getName())+"', TFhir"+getTitle(ed.getName())+"(elem."+s+"))"+(i < t-1 ? "" : ";")+"\r\n");
            i++;
          }
        }
      }
    }
  }

  private String primitiveParse(String name, String prefix) {
    if (name.equals("integer")) 
      return "ParseIntegerValue(path+'."+prefix+'.'+Utilities.capitalize(name)+"')";
    if (name.equals("boolean")) 
      return "ParseBooleanValue(path+'."+prefix+'.'+Utilities.capitalize(name)+"')";
    if (name.equals("instant") || name.equals("dateTime") || name.equals("date")) 
      return "ParseDateAndTimeValue(path+'."+prefix+'.'+Utilities.capitalize(name)+"')";
    return "ParseStringValue(path+'."+prefix+'.'+Utilities.capitalize(name)+"')";
  }

  private boolean typeIsPrimitive(String tn) {
    return tn.equalsIgnoreCase("uri") || tn.equalsIgnoreCase("datetime") || tn.equalsIgnoreCase("code") || tn.equalsIgnoreCase("boolean")
        || tn.equalsIgnoreCase("integer") || tn.equalsIgnoreCase("instant") || tn.equalsIgnoreCase("time")
        || tn.equalsIgnoreCase("datetime") || tn.equalsIgnoreCase("date") || tn.equalsIgnoreCase("id") || tn.equalsIgnoreCase("oid")
        || tn.equalsIgnoreCase("decimal") || tn.equalsIgnoreCase("string") || tn.equalsIgnoreCase("base64Binary");
  }

  private String breakConstant(String typeCode) {
    if (typeCode.length() < 255)
      return typeCode;
    else
      return typeCode.substring(0, 250)+"'+'"+typeCode.substring(250);
  }

  private String parseName(String tn) {
    if (tn.equals("TFhirResource"))
      return "InnerResource";
    else
      return tn.startsWith("TFhir") ? tn.substring(5) : tn.substring(1);
  }

  private void defineList(String tn, String tnl, String sn, ClassCategory category, boolean isAbstract) {
    if (generics)
      return; 
    if (tnl.contains("{"))
      tnl = tnl.substring(0, tnl.indexOf("{"));
    if (tn.contains("{"))
      tn = tn.substring(0, tn.indexOf("{"));
    if (!lists.contains(tnl)) {
      lists.add(tn+"List");
      String tt = tn.substring(1);
      getCode(category).classFwds.add("  "+tn+"List = class;\r\n");
      types.add(tn+"List");
      getCode(category).classDefs.add(
              "  "+tnl+"Enumerator = class (TAdvObject)\r\n"+
              "  private\r\n"+
              "    FIndex : integer;\r\n"+
              "    FList : "+tnl+";\r\n"+
              "    function GetCurrent : "+tn+";\r\n"+
              "  public\r\n"+
              "    Constructor Create(list : "+tnl+");\r\n"+
              "    Destructor Destroy; override;\r\n"+
              "    function MoveNext : boolean;\r\n"+
              "    property Current : "+tn+" read GetCurrent;\r\n"+
              "  end;\r\n\r\n");


      getCode(category).classDefs.add(
          "  {@Class "+tn+"List\r\n"+
              "    A list of "+tt+"\r\n"+
              "  }\r\n"+
              "  {!.Net HL7Connect.Fhir."+tn.substring(5)+"List}\r\n"+
              "  "+tn+"List = class ("+listForm("TFHIRObject")+")\r\n"+
              "  private\r\n"+
              "    function GetItemN(index : Integer) : "+tn+";\r\n"+
              "    procedure SetItemN(index : Integer; value : "+tn+");\r\n"+
              "  public\r\n"+
              "    {!script hide}\r\n"+
              "    function Link : "+tn+"List; Overload;\r\n"+
              "    function Clone : "+tn+"List; Overload;\r\n"+
              "    function GetEnumerator : "+tnl+"Enumerator;\r\n"+
              "    {!script show}\r\n"+
          "    \r\n");
      if (!isAbstract)
        getCode(category).classDefs.add(
            "    {@member Append\r\n"+
                "      Add a "+tt+" to the end of the list.\r\n"+
                "    }\r\n"+
                "    function Append : "+tn+";\r\n");
      getCode(category).classDefs.add(
          "    \r\n"+
              "    {@member AddItem\r\n"+
              "      Add an already existing "+tt+" to the end of the list.\r\n"+
              "    }\r\n"+
              "    procedure AddItem(value : "+tn+"); overload;\r\n");
      if (sn != null)    
        getCode(category).classDefs.add(
            "    \r\n"+
                "    {@member AddItem\r\n"+
                "      Add an already existing "+tt+" to the end of the list.\r\n"+
                "    }\r\n"+
                "    procedure AddItem(value : "+sn+"); overload;\r\n");
          getCode(category).classDefs.add(
              "    \r\n"+
              "    {@member IndexOf\r\n"+
              "      See if an item is already in the list. returns -1 if not in the list\r\n"+
              "    }\r\n"+
              "    \r\n"+
              "    {@member IndexOf\r\n"+
              "      See if an item is already in the list. returns -1 if not in the list\r\n"+
              "    }\r\n"+
              "    function IndexOf(value : "+tn+") : Integer;\r\n"+
          "    \r\n");
      if (!isAbstract)
        getCode(category).classDefs.add(
            "    {@member Insert\r\n"+
                "      Insert "+tt+" before the designated index (0 = first item)\r\n"+
                "    }\r\n"+
                "    function Insert(index : Integer) : "+tn+";\r\n"+
            "    \r\n");
      getCode(category).classDefs.add(
          "    {@member InsertItem\r\n"+
              "       Insert an existing "+tt+" before the designated index (0 = first item)\r\n"+
              "    }\r\n"+
              "    procedure InsertItem(index : Integer; value : "+tn+");\r\n"+
              "    \r\n"+
              "    {@member Item\r\n"+
              "       Get the iIndexth "+tt+". (0 = first item)\r\n"+
              "    }\r\n"+
              "    \r\n"+
              "    {@member Item\r\n"+
              "       Get the iIndexth "+tt+". (0 = first item)\r\n"+
              "    }\r\n"+
              "    procedure SetItemByIndex(index : Integer; value : "+tn+");\r\n"+
              "    \r\n"+
              "    {@member Count\r\n"+
              "      The number of items in the collection\r\n"+
              "    }\r\n"+
              "    function Item(index : Integer) : "+tn+";\r\n"+
              "    \r\n"+
              "    {@member Count\r\n"+
              "      The number of items in the collection\r\n"+
              "    }\r\n"+
              "    function Count : Integer; Overload;\r\n"+
              "    \r\n"+
              "    {@member remove\r\n"+
              "      Remove the indexth item. The first item is index 0.\r\n"+
              "    }\r\n"+
              "    procedure Remove(index : Integer);\r\n"+
              "    {@member ClearItems\r\n"+
              "      Remove All Items from the list\r\n"+
              "    }\r\n"+
              "    procedure ClearItems;\r\n"+
              "    \r\n"+
              "    Property "+Utilities.pluralizeMe(tt)+"[index : Integer] : "+tn+" read GetItemN write SetItemN; default;\r\n"+
              "  End;\r\n"+
              "\r\n"  
          );
      getCode(category).classImpls.add(
          "{ "+tnl+"Enumerator }\r\n"+
              "\r\n"+
              "Constructor "+tnl+"Enumerator.Create(list : "+tnl+");\r\n"+
              "begin\r\n"+
              "  inherited Create;\r\n"+
              "  FIndex := -1;\r\n"+
              "  FList := list;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "Destructor "+tnl+"Enumerator.Destroy;\r\n"+
              "begin\r\n"+
              "  FList.Free;\r\n"+
              "  inherited;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tnl+"Enumerator.MoveNext : boolean;\r\n"+
              "begin\r\n"+
              "  inc(FIndex);\r\n"+
              "  Result := FIndex < FList.count;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tnl+"Enumerator.GetCurrent : "+tn+";\r\n"+
              "begin\r\n"+
              "  Result := FList[FIndex];\r\n"+
              "end;\r\n"+
          "\r\n");
      getCode(category).classImpls.add(
          "{ "+tn+"List }\r\n"+
              "procedure "+tn+"List.AddItem(value: "+tn+");\r\n"+
              "begin\r\n"+
              "  assert(value.ClassName = '"+tn+"', 'Attempt to add an item of type '+value.ClassName+' to a List of "+tn+"');\r\n"+
              "  add(value);\r\n"+
              "end;\r\n"+
          "\r\n");
      if (sn != null)    
        getCode(category).classImpls.add(
                "procedure "+tn+"List.AddItem(value: "+sn+");\r\n"+
                "begin\r\n"+
                "  add("+tn+".create(value));\r\n"+
                "end;\r\n"+
            "\r\n");
      if (!isAbstract)
        getCode(category).classImpls.add(
            "function "+tn+"List.Append: "+tn+";\r\n"+
                "begin\r\n"+
                "  result := "+tn+".create;\r\n"+
                "  try\r\n"+
                "    add(result.Link);\r\n"+
                "  finally\r\n"+
                "    result.free;\r\n"+
                "  end;\r\n"+
                "end;\r\n"+
            "\r\n");
      getCode(category).classImpls.add(
          "procedure "+tn+"List.ClearItems;\r\n"+
              "begin\r\n"+
              "  Clear;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tnl+".GetEnumerator : "+tnl+"Enumerator;\r\n"+
              "begin\r\n"+
              "  result := "+tnl+"Enumerator.Create(self.link);\r\n"+
              "end;\r\n\r\n"+
              "function "+tn+"List.Clone: "+tn+"List;\r\n"+
              "begin\r\n"+
              "  result := "+tn+"List(inherited Clone);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tn+"List.Count: Integer;\r\n"+
              "begin\r\n"+
              "  result := Inherited Count;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tn+"List.GetItemN(index: Integer): "+tn+";\r\n"+
              "begin\r\n"+
              "  result := "+tn+"(ObjectByIndex[index]);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tn+"List.IndexOf(value: "+tn+"): Integer;\r\n"+
              "begin\r\n"+
              "  result := IndexByReference(value);\r\n"+
              "end;\r\n"+
          "\r\n");
      if (!isAbstract)
        getCode(category).classImpls.add(
            "function "+tn+"List.Insert(index: Integer): "+tn+";\r\n"+
                "begin\r\n"+
                "  result := "+tn+".create;\r\n"+
                "  try\r\n"+
                "    inherited insert(index, result.Link);\r\n"+
                "  finally\r\n"+
                "    result.free;\r\n"+
                "  end;\r\n"+
                "end;\r\n"+
            "\r\n");
      getCode(category).classImpls.add(
          "procedure "+tn+"List.InsertItem(index: Integer; value: "+tn+");\r\n"+
              "begin\r\n"+
              "  assert(value is "+tn+");\r\n"+
              "  Inherited Insert(index, value);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tn+"List.Item(index: Integer): "+tn+";\r\n"+
              "begin\r\n"+
              "  result := "+tn+"(ObjectByIndex[index]);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "function "+tn+"List.Link: "+tn+"List;\r\n"+
              "begin\r\n"+
              "  result := "+tn+"List(inherited Link);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "procedure "+tn+"List.Remove(index: Integer);\r\n"+
              "begin\r\n"+
              "  DeleteByIndex(index);\r\n"+
              "end;\r\n"+
              "\r\n"+
              "procedure "+tn+"List.SetItemByIndex(index: Integer; value: "+tn+");\r\n"+
              "begin\r\n"+
              "  assert(value is "+tn+");\r\n"+
              "  "+Utilities.pluralizeMe(tt)+"[index] := value;\r\n"+
              "end;\r\n"+
              "\r\n"+
              "procedure "+tn+"List.SetItemN(index: Integer; value: "+tn+");\r\n"+
              "begin\r\n"+
              "  assert(value is "+tn+");\r\n"+
              "  ObjectByIndex[index] := value;\r\n"+
              "end;\r\n"        
          ); 
    }
  }

  private boolean typeIsSimple(String tn) {
    if (tn == null)
      return false;
    return tn.equals("String") || tn.equals("Integer") || tn.equals("Boolean") || tn.equals("TDateAndTime") || tn.equals("TFhirXHtmlNode")  || enumNames.contains(tn);
  }

  private String getTitle(String name) {
    if (name.length() < 2)
      return name.toUpperCase();
    else
      return name.substring(0, 1).toUpperCase()+ name.substring(1);
  }

  private String getElementName(String name) {
    if (GeneratorUtils.isDelphiReservedWord(name))
      return name+"_";
    return name.replace("[x]", "").replace("[type]", "value");
  }

  private String getPropertyName(String name) {
    return name.replace("[x]", "").replace("[type]", "value");
  }

  private String getTypeName(ElementDefn e) throws Exception {
    if (e.getTypes().size() > 1) {
      return "TFhirType";
    } else if (e.getTypes().size() == 0) {
      throw new Exception("not supported");
    } else {
      return getTypename(e.getTypes().get(0));
    }
  }

  private String getTypename(TypeRef type) throws Exception {
    if (type.getParams().size() == 1) {     
      if (type.isResourceReference())
        return "TFhirReference{"+getTypeName(type.getParams().get(0))+"}";
      else if (type.getName().equals("Interval"))
        return "TInterval_"+type.getParams().get(0);
      else
        throw new Exception("not supported: "+type.summary());
    } else if (type.getParams().size() > 1) {
      if (type.isResourceReference())
        return "TFhirReference{Resource}";
      else
        throw new Exception("not supported");
    } else {
      return getTypeName(type.getName());
    }
  }

  private String getTypeName(String tn) {
    if (tn == null) {
      return "";
    } else if (tn.equals("xml:lang")) {
      return "TFhirString";
    } else if (tn.equals("xhtml")) {
      return "TFhirXHtmlNode"; 
    } else if (tn.equals("*")) {
      return "TFhirType";
    } else if (tn.equals("Any")) {
      return "TFhirReference";
    } else if (definitions.getConstraints().containsKey(tn)) {
      return getTypeName(definitions.getConstraints().get(tn).getBaseType());
    } else {
      return "TFhir"+getTitle(tn);
    }
  }

  @Override
  public String getName() {
    return "pascal";
  }

  public void genConstraint(ProfiledType c) {
    prsrdefX.append("    function Parse"+c.getName()+"(element : IXmlDomElement; path : string) : TFhir"+c.getName()+";\r\n");
    srlsdefX.append("    procedure Compose"+c.getName()+"(xml : TXmlBuilder; name : string; elem : TFhir"+c.getName()+");\r\n");
    prsrdefJ.append("    function Parse"+c.getName()+"(jsn : TJsonObject) : TFhir"+c.getName()+"; overload;\r\n");
    srlsdefJ.append("    procedure Compose"+c.getName()+"(json : TJSONWriter; name : string; elem : TFhir"+c.getName()+"; noObj : boolean = false);\r\n");
    defCodeType.classDefs.add("  TFhir"+c.getName()+" = TFhir"+c.getBaseType()+";\r\n");
    prsrImpl.append("function TFHIRXmlParser.Parse"+c.getName()+"(element : IXmlDomElement; path : string) : TFhir"+c.getName()+";\r\nbegin\r\n  result := Parse"+c.getBaseType()+"(element, path);\r\nend;\r\n\r\n");
    prsrImpl.append("procedure TFHIRXmlComposer.Compose"+c.getName()+"(xml : TXmlBuilder; name : string; elem : TFhir"+c.getName()+");\r\nbegin\r\n  Compose"+c.getBaseType()+"(xml, name, elem);\r\nend;\r\n\r\n");
    prsrImpl.append("function TFHIRJsonParser.Parse"+c.getName()+"(jsn : TJsonObject) : TFhir"+c.getName()+";\r\nbegin\r\n  result := Parse"+c.getBaseType()+"(jsn);\r\nend;\r\n\r\n");
    prsrImpl.append("procedure TFHIRJsonComposer.Compose"+c.getName()+"(json : TJSONWriter; name : string; elem : TFhir"+c.getName()+"; noObj : boolean = false);\r\nbegin\r\n  Compose"+c.getBaseType()+"(json, name, elem, noObj);\r\nend;\r\n\r\n");
  }


  private void generatePrimitive(DefinedCode t, String parent, boolean isEnum, boolean derived) {
    StringBuilder def = new StringBuilder();
    String tn = Utilities.capitalize(t.getCode());
    String pn = "String";
    if (tn.equals("Date") || tn.equals("DateTime") || tn.equals("Instant"))
      pn = "TDateAndTime";
    if (tn.equals("Boolean"))
      pn = "Boolean";
    if (tn.equals("Base64Binary"))
      pn = "TBytes";

    factoryIntf.append("    {@member new"+tn+"\r\n      create a new "+t.getCode()+"\r\n    }\r\n    {!script nolink}\r\n    function new"+tn+" : TFhir"+tn+";\r\n");
    factoryImpl.append("function TFhirResourceFactory.new"+tn+" : TFhir"+tn+";\r\nbegin\r\n  result := TFhir"+tn+".create;\r\nend;\r\n\r\n");
    factoryByName.append("  else if name = '"+tn+"' then\r\n    result := new"+tn+"()\r\n");

    factoryIntf.append("    {@member make"+tn+"\r\n      create a new "+t.getCode()+" with the given value\r\n    }\r\n    {!script nolink}\r\n    function make"+tn+"(value : "+pn+") : TFhir"+tn+";\r\n");
    factoryImpl.append("function TFhirResourceFactory.make"+tn+"(value : "+pn+") : TFhir"+tn+";\r\nbegin\r\n  result := TFhir"+tn+".create;\r\n  result.value := value;\r\nend;\r\n\r\n");

    simpleTypes.put("TFhir"+tn, pn);
    def.append("  {@Class TFhir"+tn+" : "+parent+"\r\n");
    def.append("    a complex string - has an Id attribute, and a dataAbsentReason.\r\n");
    def.append("    \r\n");
    def.append("    Used where a FHIR element is a string, and may have a dataAbsentReason\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir."+tn+"}\r\n");
    def.append("  TFhir"+tn+" = class ("+parent+")\r\n");
    types.add("TFhir"+tn);
    def.append("  Private\r\n");
    if (!derived) {
      def.append("    FValue: "+pn+";\r\n");
      def.append("    procedure setValue(value: "+pn+");\r\n");
      def.append("  protected\r\n");
      def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
      def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
      def.append("    function AsStringValue : String; Override;\r\n");
    }
    def.append("  Public\r\n");
    def.append("    Constructor Create(value : "+pn+"); overload;\r\n");
    def.append("    Destructor Destroy; override;\r\n");
    def.append("    \r\n");
    def.append("    {!script hide}\r\n");
    def.append("    Function Link : TFhir"+tn+"; Overload;\r\n");
    def.append("    Function Clone : TFhir"+tn+"; Overload;\r\n");
    if (!derived) {
      def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
    }
    def.append("    function FhirType : string; override;\r\n");
    def.append("    {!script show}\r\n");
    if (!derived) {
      def.append("  Published\r\n");
      def.append("    {@member value\r\n");
      def.append("      The actual value of the "+t.getCode()+"\r\n");
      def.append("    }\r\n");
      def.append("    property value : "+pn+" read FValue write SetValue;\r\n");
    }
    def.append("  End;    \r\n");
    def.append("\r\n");

    StringBuilder impl2 = new StringBuilder();
    impl2.append("{ TFhir"+tn+" }\r\n\r\n");

    impl2.append("Constructor TFhir"+tn+".Create(value : "+pn+");\r\n");
    impl2.append("begin\r\n");
    impl2.append("  Create;\r\n");
    impl2.append("  FValue := value;\r\n");
    impl2.append("end;\r\n\r\n");

    impl2.append("Destructor TFhir"+tn+".Destroy;\r\n");
    impl2.append("begin\r\n");
    if (!derived) {
      if (!pn.equals("String") && !pn.equals("Boolean") && !pn.equals("TBytes"))
        impl2.append("  FValue.free;\r\n");
    }
    impl2.append("  inherited;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFhir"+tn+".FhirType : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := '"+t.getCode()+"';\r\n");
    impl2.append("end;\r\n\r\n");

    if (!derived) {

      impl2.append("procedure TFhir"+tn+".GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
      impl2.append("begin\r\n");
      impl2.append("  inherited;\r\n");
      impl2.append("  if child_name = 'value' then\r\n    list.add(TFHIRObjectText.create(value));\r\n");
      impl2.append("end;\r\n\r\n");
      impl2.append("procedure TFhir"+tn+".ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
      impl2.append("begin\r\n");
      impl2.append("  inherited;\r\n");
      if (pn.equals("Boolean"))
        impl2.append("  oList.add(TFHIRProperty.create(self, 'value', '"+breakConstant(t.getCode())+"', LCBooleanToString(FValue)));\r\n");
      else if (!pn.equals("String") && !pn.equals("TBytes"))
        impl2.append("  oList.add(TFHIRProperty.create(self, 'value', '"+breakConstant(t.getCode())+"', FValue.toString));\r\n");
      else 
        impl2.append("  oList.add(TFHIRProperty.create(self, 'value', '"+breakConstant(t.getCode())+"', FValue));\r\n");
      impl2.append("end;\r\n\r\n");


      impl2.append("procedure TFhir"+tn+".Assign(oSource : TAdvObject);\r\n");
      impl2.append("begin\r\n");
      impl2.append("  inherited;\r\n");
      if (!pn.equals("String") && !pn.equals("Boolean") && !pn.equals("TBytes")) 
        impl2.append("  FValue := TFhir"+tn+"(oSource).Value.Link;\r\n");
      else 
        impl2.append("  FValue := TFhir"+tn+"(oSource).Value;\r\n");
      impl2.append("end;\r\n\r\n");

      impl2.append("function TFhir"+tn+".AsStringValue : string;\r\n");
      impl2.append("begin\r\n");
      if (pn.equals("Boolean"))
        impl2.append("  result := LCBooleanToString(FValue);\r\n");
      else if (pn.equals("TBytes"))
        impl2.append("  if (length(FValue) = 0) then result := '' else result := EncodeBase64(@FValue[0], length(FValue));\r\n");
      else if (!pn.equals("String"))
        impl2.append("  result := FValue.toString;\r\n");
      else 
        impl2.append("  result := FValue;\r\n");
      impl2.append("end;\r\n\r\n");
    }

    impl2.append("function TFhir"+tn+".Link : TFhir"+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFhir"+tn+"(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFhir"+tn+".Clone : TFhir"+tn+";\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFhir"+tn+"(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");
    if (!derived) {
      impl2.append("procedure TFhir"+tn+".setValue(value : "+pn+");\r\n");
      impl2.append("begin\r\n");
      if (!pn.equals("String") && !pn.equals("Boolean") && !pn.equals("TBytes")) 
        impl2.append("  FValue.free;\r\n");
      impl2.append("  FValue := value;\r\n");
      impl2.append("end;\r\n\r\n");
    }    

    if (isEnum) {
      prsrdefX.append("    function Parse"+tn+"(Const aNames : Array Of String; path : String; element : IXmlDomElement) : TFhir"+tn+";\r\n");
      prsrImpl.append("function TFHIRXmlParser.Parse"+tn+"(Const aNames : Array Of String; path : String; element : IXmlDomElement) : TFhir"+tn+";\r\n");
      prsrImpl.append("var\r\n");
      prsrImpl.append("  child : IXMLDOMElement;\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  result := TFhir"+tn+".create;\r\n");
      prsrImpl.append("  try\r\n");
      prsrImpl.append("    ParseElementAttributes(result, path, element);\r\n");
      prsrImpl.append("    result.value := GetAttribute(element, 'value');\r\n");
      prsrImpl.append("    if StringArrayIndexOfSensitive(aNames, result.value) < 0 then\r\n");
      prsrImpl.append("      raise Exception.create('unknown code: '+result.value+' from a set of choices of '+StringArrayToCommaString(aNames)+' for \"'+path+'\"');\r\n");
      prsrImpl.append("    child := FirstChild(element);\r\n");
      prsrImpl.append("    while (child <> nil) do\r\n");
      prsrImpl.append("    begin\r\n");
      prsrImpl.append("      if Not ParseElementChild(result, path, child) then\r\n");
      prsrImpl.append("         UnknownContent(child, path);\r\n");
      prsrImpl.append("      child := NextSibling(child);\r\n");
      prsrImpl.append("    end;\r\n");
      prsrImpl.append("    closeOutElement(result, element);\r\n\r\n");
      prsrImpl.append("    result.link;\r\n");
      prsrImpl.append("  finally\r\n");
      prsrImpl.append("    result.free;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");

      prsrdefJ.append("    procedure ParseEnum(value : string; jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+"; Const aNames : Array Of String); overload;\r\n");
      prsrImpl.append("procedure TFHIRJsonParser.ParseEnum(value : string; jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+"; Const aNames : Array Of String);\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  ctxt.add(ParseEnum(value, jsn, aNames));\r\n");
      prsrImpl.append("end;\r\n\r\n");
      prsrdefJ.append("    function ParseEnum(value : string; jsn : TJsonObject; Const aNames : Array Of String) : TFHIREnum; overload;\r\n");
      prsrImpl.append("function TFHIRJsonParser.ParseEnum(value : string; jsn : TJsonObject; Const aNames : Array Of String) : TFHIREnum;\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  if StringArrayIndexOfSensitive(aNames, value) < 0 then\r\n");
      prsrImpl.append("    raise Exception.create('unknown code: '+value+' from a set of choices of '+StringArrayToCommaString(aNames)+' for \"'+jsn.path+'\"');\r\n");
      prsrImpl.append("  result := TFHIREnum.create;\r\n");
      prsrImpl.append("  try\r\n");
      prsrImpl.append("    result.value := value;\r\n");
      prsrImpl.append("    if (jsn <> nil) then\r\n");
      prsrImpl.append("      parseElementProperties(jsn, result);\r\n");
      prsrImpl.append("    result.link;\r\n");
      prsrImpl.append("  finally\r\n");
      prsrImpl.append("    result.free;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");


      srlsdefX.append("    Procedure Compose"+tn+"(xml : TXmlBuilder; name : String; value : TFhir"+tn+"; Const aNames : Array Of String);\r\n");
      prsrImpl.append("Procedure TFHIRXmlComposer.Compose"+tn+"(xml : TXmlBuilder; name : String; value : TFhir"+tn+"; Const aNames : Array Of String);\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  if (value = nil) then\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  composeElementAttributes(xml, value);\r\n");
      prsrImpl.append("  attribute(xml, 'value', value.value);\r\n");
      prsrImpl.append("  xml.open(name);\r\n");
      prsrImpl.append("  composeElementChildren(xml, value);\r\n");
      prsrImpl.append("  closeOutElement(xml, value);\r\n");
      prsrImpl.append("  xml.close(name);\r\n");
      prsrImpl.append("end;\r\n\r\n");
      srlsdefJ.append("    Procedure Compose"+tn+"Value(json : TJSONWriter; name : String; value : TFhir"+tn+"; Const aNames : Array Of String; inArray : boolean);\r\n");
      srlsdefJ.append("    Procedure Compose"+tn+"Props(json : TJSONWriter; name : String; value : TFhir"+tn+"; Const aNames : Array Of String; inArray : boolean);\r\n");
      prsrImpl.append("Procedure TFHIRJsonComposer.Compose"+tn+"Value(json : TJSONWriter; name : String; value : TFhir"+tn+"; Const aNames : Array Of String; inArray : boolean);\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  if (value = nil) or (value.Value = '') then\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if inArray then\r\n");
      prsrImpl.append("      propNull(json, name);\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  end\r\n");
      prsrImpl.append("  else\r\n");
      prsrImpl.append("    prop(json, name, value.value);\r\n");
      prsrImpl.append("end;\r\n\r\n");
      prsrImpl.append("Procedure TFHIRJsonComposer.Compose"+tn+"Props(json : TJSONWriter; name : String; value : TFhir"+tn+"; Const aNames : Array Of String; inArray : boolean);\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  if (value = nil) or ((value.Id = '') and (not value.hasExtensionList) and (not value.hasComments)) then\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if inArray then\r\n");
      prsrImpl.append("      propNull(json, name);\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  end\r\n");
      prsrImpl.append("  else\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if (inArray) then\r\n");
      prsrImpl.append("      json.valueObject('')\r\n");
      prsrImpl.append("    else\r\n");
      prsrImpl.append("      json.valueObject('_'+name);\r\n");
      prsrImpl.append("    ComposeElementProperties(json, value);\r\n");
      prsrImpl.append("    json.finishObject;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");
    } else {
      prsrdefX.append("    function Parse"+tn+"(element : IXmlDomElement; path : string) : TFhir"+tn+";\r\n");
      prsrImpl.append("function TFHIRXmlParser.Parse"+tn+"(element : IXmlDomElement; path : string) : TFhir"+tn+";\r\n");
      prsrImpl.append("var\r\n");
      prsrImpl.append("  child : IXMLDOMElement;\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  result := TFhir"+tn+".create;\r\n");
      prsrImpl.append("  try\r\n");
      prsrImpl.append("    ParseElementAttributes(result, path, element);\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("    result.value := StringToBoolean(GetAttribute(element, 'value'));\r\n");
      else  if (!pn.equals("String"))
        prsrImpl.append("    result.value := to"+pn+"(GetAttribute(element, 'value'));\r\n");
      else
        prsrImpl.append("    result.value := GetAttribute(element, 'value');\r\n");
      prsrImpl.append("    child := FirstChild(element);\r\n");
      prsrImpl.append("    while (child <> nil) do\r\n");
      prsrImpl.append("    begin\r\n");
      prsrImpl.append("      if Not ParseElementChild(result, path, child) then\r\n");
      prsrImpl.append("         UnknownContent(child, path);\r\n");
      prsrImpl.append("      child := NextSibling(child);\r\n");
      prsrImpl.append("    end;\r\n");
      prsrImpl.append("    closeOutElement(result, element);\r\n\r\n");
      prsrImpl.append("    result.link;\r\n");
      prsrImpl.append("  finally\r\n");
      prsrImpl.append("    result.free;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");


      prsrdefJ.append("    procedure Parse"+tn+"(value : string; jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+"); overload;\r\n");
      prsrImpl.append("procedure TFHIRJsonParser.Parse"+tn+"(value : string; jsn : TJsonObject; ctxt : "+listForm("TFHIRObject")+");\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  ctxt.add(Parse"+tn+"(value, jsn)) {1};\r\n");
      prsrImpl.append("end;\r\n\r\n");
      prsrdefJ.append("    function Parse"+tn+"(value : string; jsn : TJsonObject) : TFHIR"+tn+"; overload;\r\n");
      prsrImpl.append("function TFHIRJsonParser.Parse"+tn+"(value : string; jsn : TJsonObject) : TFHIR"+tn+";\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  result := TFhir"+tn+".Create;\r\n");
      prsrImpl.append("  try\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("    result.value := StringToBoolean(value);\r\n");
      else if (!pn.equals("String"))
        prsrImpl.append("     result.value := to"+pn+"(value);\r\n");
      else
        prsrImpl.append("    result.value := value;\r\n");
      prsrImpl.append("    if (jsn <> nil) then\r\n");
      prsrImpl.append("      parseElementProperties(jsn, result);\r\n");
      prsrImpl.append("    result.Link;\r\n");
      prsrImpl.append("  finally\r\n");
      prsrImpl.append("    result.Free;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");








      srlsdefX.append("    Procedure Compose"+tn+"(xml : TXmlBuilder; name : String; value : TFhir"+tn+");\r\n");
      prsrImpl.append("Procedure TFHIRXmlComposer.Compose"+tn+"(xml : TXmlBuilder; name : String; value : TFhir"+tn+");\r\n");
      prsrImpl.append("begin\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("  if (value = nil) then\r\n");
      else if (!pn.equals("String"))
        prsrImpl.append("  if (value = nil) or (value.value = nil) then\r\n");
      else 
        prsrImpl.append("  if (value = nil) or (value.value = '') then\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  composeElementAttributes(xml, value);\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("  attribute(xml, 'value', LCBooleanToString(value.value));\r\n");
      else if (!pn.equals("String"))
        prsrImpl.append("  attribute(xml, 'value', asString(value.value));\r\n");
      else
        prsrImpl.append("  attribute(xml, 'value', value.value);\r\n");
      prsrImpl.append("  xml.open(name);\r\n");
      prsrImpl.append("  composeElementChildren(xml, value);\r\n");
      prsrImpl.append("  closeOutElement(xml, value);\r\n");
      prsrImpl.append("  xml.close(name);\r\n");
      prsrImpl.append("end;\r\n\r\n");
      srlsdefJ.append("    Procedure Compose"+tn+"Value(json : TJSONWriter; name : String; value : TFhir"+tn+"; inArray : boolean);\r\n");
      srlsdefJ.append("    Procedure Compose"+tn+"Props(json : TJSONWriter; name : String; value : TFhir"+tn+"; inArray : boolean);\r\n");
      prsrImpl.append("Procedure TFHIRJsonComposer.Compose"+tn+"Value(json : TJSONWriter; name : String; value : TFhir"+tn+"; inArray : boolean);\r\n");
      prsrImpl.append("begin\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("  if (value = nil) then\r\n");
      else if (!pn.equals("String"))
        prsrImpl.append("  if (value = nil) or (value.value = nil) then\r\n");
      else 
        prsrImpl.append("  if (value = nil) or (value.value = '') then\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if inArray then\r\n");
      prsrImpl.append("      propNull(json, name);\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  end\r\n");
      prsrImpl.append("  else\r\n");
      if (pn.equals("Boolean"))
        prsrImpl.append("    prop(json, name, value.value);\r\n");
      else if (!pn.equals("String"))
        prsrImpl.append("    prop(json, name, asString(value.value));\r\n");
      else if (tn.equals("Decimal") || tn.equals("Integer"))
        prsrImpl.append("    propNumber(json, name, value.value);\r\n");
      else
        prsrImpl.append("    prop(json, name, value.value);\r\n");
      prsrImpl.append("end;\r\n\r\n");
      prsrImpl.append("Procedure TFHIRJsonComposer.Compose"+tn+"Props(json : TJSONWriter; name : String; value : TFhir"+tn+"; inArray : boolean);\r\n");
      prsrImpl.append("begin\r\n");
      prsrImpl.append("  if (value = nil) or ((value.Id = '') and (not value.hasExtensionList) and (not value.hasComments)) then\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if inArray then\r\n");
      prsrImpl.append("      propNull(json, name);\r\n");
      prsrImpl.append("    exit;\r\n");
      prsrImpl.append("  end\r\n");
      prsrImpl.append("  else\r\n");
      prsrImpl.append("  begin\r\n");
      prsrImpl.append("    if (inArray) then\r\n");
      prsrImpl.append("      json.valueObject('')\r\n");
      prsrImpl.append("    else\r\n");
      prsrImpl.append("      json.valueObject('_'+name);\r\n");
      prsrImpl.append("    ComposeElementProperties(json, value);\r\n");
      prsrImpl.append("    json.finishObject;\r\n");
      prsrImpl.append("  end;\r\n");
      prsrImpl.append("end;\r\n\r\n");
    }

    defCodeType.classDefs.add(def.toString());
    defCodeType.classImpls.add(impl2.toString());
    defCodeType.classFwds.add("  TFhir"+tn+" = class;\r\n");
    defineList("TFhir"+tn, "TFhir"+tn+"List", pn, ClassCategory.Type, false);
  }

  private String listForm(String name) {
    return generics ? "TAdvList<"+name+">" : name+"List";
  }

  private void generateElement() {
    StringBuilder def = new StringBuilder();

//    def.append("  {@Class TFhirElement : TFHIRBase\r\n");
//    def.append("    Base Element Definition - extensions, ids\r\n");
//    def.append("  }\r\n");
//    def.append("  {!.Net HL7Connect.Fhir.Element}\r\n");
//    def.append("  TFhirElement = {abstract} class (TFHIRBase)\r\n");
//    types.add("TFhirElement");
//    def.append("  private\r\n");
//    def.append("    FXmlId: String;\r\n");
//    def.append("    FExtensionList : "+listForm("TFhirExtension")+";\r\n");
//    def.append("    function GetExtensionList: "+listForm("TFhirExtension")+";\r\n");
//    def.append("  protected\r\n");
//    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
//    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
//    def.append("  public\r\n");
//    def.append("    destructor Destroy; override;\r\n");
//    def.append("    {!script hide}\r\n");
//    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
//    def.append("    function Link : TFhirElement; overload;\r\n");
//    def.append("    function Clone : TFhirElement; overload;\r\n");
//    def.append("    function HasExtensions : Boolean;\r\n");
//    def.append("    {!script show}\r\n");
//    def.append("  published\r\n");
//    def.append("    {@member xmlId\r\n");
//    def.append("      the value of the xml id attribute, if present.\r\n");
//    def.append("    }\r\n");
//    def.append("    property xmlId : String read FXmlId write FXmlId;\r\n");
//    def.append("    {@member ExtensionList\r\n");
//    def.append("      Extensions on this value\r\n");
//    def.append("    }\r\n");
//    def.append("    property ExtensionList : "+listForm("TFhirExtension")+" read GetExtensionList;\r\n");
//    def.append("  end;\r\n");
//    def.append("  \r\n");
//    def.append("\r\n");
    def.append("  {@Class TFhirType : TFhirElement\r\n");
    def.append("    A base FHIR type - (polymorphism support)\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir.Type}\r\n");
    def.append("  TFhirType = class (TFhirElement)\r\n");
    types.add("TFhirType");
    def.append("  Public\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    Function Link : TFhirType; Overload;\r\n");
    def.append("    Function Clone : TFhirType; Overload;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  End;\r\n");   
    def.append("  TFHIRTypeClass = class of TFhirType;\r\n");
    def.append("  \r\n");
    def.append("  {@Class TFHIRPrimitiveType : TFhirType\r\n");
    def.append("    A base FHIR type - (polymorphism support)\r\n");
    def.append("  }\r\n");
    def.append("  {!.Net HL7Connect.Fhir.Type}\r\n");
    def.append("  TFHIRPrimitiveType = class (TFhirType)\r\n");
    types.add("TFHIRPrimitiveType");
    def.append("  Private\r\n");
    def.append("    Function GetStringValue : String;\r\n");
    def.append("    Function AsStringValue : String; Virtual;\r\n");
    def.append("  Public\r\n");
    def.append("    {!script hide}\r\n");
    def.append("    Function Link : TFHIRPrimitiveType; Overload;\r\n");
    def.append("    Function Clone : TFHIRPrimitiveType; Overload;\r\n");
    def.append("    Property StringValue : String read GetStringValue;\r\n");
    def.append("    {!script show}\r\n");
    def.append("  End;\r\n");   
    def.append("  TFHIRPrimitiveTypeClass = class of TFHIRPrimitiveType;\r\n");
    def.append("  \r\n");

    StringBuilder impl2 = new StringBuilder();
//    impl2.append("{ TFhirElement }\r\n\r\n");
//
//    impl2.append("destructor TFhirElement.Destroy;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  FExtensionList.Free;\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("end;\r\n\r\n");
//
//    impl2.append("procedure TFhirElement.GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
//    if (generics)
//      impl2.append("var\r\n  o : TFHIRExtension;\r\n");      
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("  if child_name = '@id' then\r\n    list.add(TFHIRObjectText.create(FXmlId));\r\n");
//    if (generics)
//      impl2.append("  if (child_name = 'extension') Then\r\n    for o in FExtensionList do\r\n      list.add(o.Link);\r\n");
//    else
//      impl2.append("  if (child_name = 'extension') Then\r\n    list.addAll(FExtensionList);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("procedure TFhirElement.ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
//    if (generics) {
//      impl2.append("var\r\n");
//      impl2.append("  prop : TFHIRProperty;\r\n");
//      impl2.append("  o : TFHIRExtension;\r\n");
//    }
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("  oList.add(TFHIRProperty.create(self, 'xml:id', 'string', FXmlId));\r\n");
//    if (generics) {
//      impl2.append("  prop := oList[oList.add(TFHIRProperty.create(self, 'extension', 'Extension'))];\r\n");
//      impl2.append("  if FExtensionList <> nil then;\r\n");
//      impl2.append("    for o in FExtensionList do\r\n");
//      impl2.append("      prop.List.add(o.Link);\r\n");
//    } else 
//      impl2.append("  oList.add(TFHIRProperty.create(self, 'extension', 'Extension', FExtensionList));\r\n");
//    impl2.append("end;\r\n\r\n");
//
//
//    impl2.append("procedure TFhirElement.Assign(oSource : TAdvObject);\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("  FXmlId := TFhirElement(oSource).FXmlId;\r\n");
//    impl2.append("  if TFhirElement(oSource).HasExtensions then\r\n    extensionList.assign(TFhirElement(oSource).extensionList)\r\n"+
//        "  else if FExtensionList <> nil then\r\n  begin\r\n    FExtensionList.free;\r\n    FExtensionList := nil;\r\n  end;\r\n");
//    impl2.append("end;\r\n\r\n");
//
//    impl2.append("function TFhirElement.Link : TFhirElement;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := TFhirElement(inherited Link);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFhirElement.Clone : TFhirElement;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := TFhirElement(inherited Clone);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFhirElement.GetExtensionList : "+listForm("TFhirExtension")+";\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  if FExtensionList = nil then\r\n    FExtensionList := "+listForm("TFhirExtension")+".Create;\r\n  result := FExtensionList;\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFhirElement.HasExtensions : boolean;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := (FExtensionList <> nil) and (FExtensionList.count > 0);\r\n");
//    impl2.append("end;\r\n\r\n");

//    def.append("  {@Class TFhirBackboneElement : TFHIRBase\r\n");
//    def.append("    Base Element Definition - extensions, ids\r\n");
//    def.append("  }\r\n");
//    def.append("  {!.Net HL7Connect.Fhir.BackboneElement}\r\n");
//    def.append("  TFHIRBackboneElement = {abstract} class (TFhirElement)\r\n");
//    types.add("TFHIRBackboneElement");
//    def.append("  private\r\n");
//    def.append("    FModifierExtensionList : "+listForm("TFhirExtension")+";\r\n");
//    def.append("    function GetModifierExtensionList: "+listForm("TFhirExtension")+";\r\n");
//    def.append("  protected\r\n");
//    def.append("    Procedure GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+"); override;\r\n");
//    def.append("    Procedure ListProperties(oList : "+listForm("TFHIRProperty")+"; bInheritedProperties : Boolean); Override;\r\n");
//    def.append("  public\r\n");
//    def.append("    destructor Destroy; override;\r\n");
//    def.append("    {!script hide}\r\n");
//    def.append("    procedure Assign(oSource : TAdvObject); override;\r\n");
//    def.append("    function Link : TFHIRBackboneElement; overload;\r\n");
//    def.append("    function Clone : TFHIRBackboneElement; overload;\r\n");
//    def.append("    function HasModifierExtensions : Boolean;\r\n");
//    def.append("    {!script show}\r\n");
//    def.append("  published\r\n");
//    def.append("    {@member ModifierExtensionList\r\n");
//    def.append("      Modifier Extensions on this value\r\n");
//    def.append("    }\r\n");
//    def.append("    property ModifierExtensionList : "+listForm("TFhirExtension")+" read GetModifierExtensionList;\r\n");
//    def.append("  end;\r\n");
//    def.append("  \r\n");
//    def.append("\r\n");
//
//    impl2.append("{ TFHIRBackboneElement }\r\n\r\n");
//
//    impl2.append("destructor TFHIRBackboneElement.Destroy;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  FModifierExtensionList.Free;\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("end;\r\n\r\n");
//
//    impl2.append("procedure TFHIRBackboneElement.GetChildrenByName(child_name : string; list : "+listForm("TFHIRObject")+");\r\n");
//    if (generics)
//      impl2.append("var\r\n  o : TFHIRExtension;\r\n");      
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    if (generics)
//      impl2.append("  if (child_name = 'modifierExtension') Then\r\n    for o in FModifierExtensionList do\r\n      list.add(o.Link);\r\n");
//    else
//      impl2.append("  if (child_name = 'modifierExtension') Then\r\n    list.addAll(FModifierExtensionList);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("procedure TFHIRBackboneElement.ListProperties(oList: "+listForm("TFHIRProperty")+"; bInheritedProperties: Boolean);\r\n");
//    if (generics) {
//      impl2.append("var\r\n");
//      impl2.append("  prop : TFHIRProperty;\r\n");
//      impl2.append("  o : TFHIRExtension;\r\n");
//    }
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    if (generics) {
//      impl2.append("  prop := oList[oList.add(TFHIRProperty.create(self, 'modifierExtension', 'Extension'))];\r\n");
//      impl2.append("  if FModifierExtensionList <> nil then;\r\n");
//      impl2.append("    for o in FModifierExtensionList do\r\n");
//      impl2.append("      prop.List.add(o.Link);\r\n");
//    } else
//      impl2.append("  oList.add(TFHIRProperty.create(self, 'modifierExtension', 'Extension', FModifierExtensionList));\r\n");
//    impl2.append("end;\r\n\r\n");
//
//    impl2.append("procedure TFHIRBackboneElement.Assign(oSource : TAdvObject);\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  inherited;\r\n");
//    impl2.append("  if TFHIRBackboneElement(oSource).HasModifierExtensions then\r\n    ModifierExtensionList.assign(TFHIRBackboneElement(oSource).ModifierextensionList)\r\n"+
//        "  else if FModifierExtensionList <> nil then\r\n  begin\r\n    FModifierExtensionList.free;\r\n    FModifierExtensionList := nil;\r\n  end;\r\n");
//    impl2.append("end;\r\n\r\n");
//
//    impl2.append("function TFHIRBackboneElement.Link : TFHIRBackboneElement;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := TFHIRBackboneElement(inherited Link);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFHIRBackboneElement.Clone : TFHIRBackboneElement;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := TFHIRBackboneElement(inherited Clone);\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFHIRBackboneElement.GetModifierExtensionList : "+listForm("TFhirExtension")+";\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  if FModifierExtensionList = nil then\r\n    FModifierExtensionList := "+listForm("TFhirExtension")+".Create;\r\n  result := FModifierExtensionList;\r\n");
//    impl2.append("end;\r\n\r\n");
//    impl2.append("function TFHIRBackboneElement.HasModifierExtensions : boolean;\r\n");
//    impl2.append("begin\r\n");
//    impl2.append("  result := (FModifierExtensionList <> nil) and (FModifierExtensionList.count > 0);\r\n");
//    impl2.append("end;\r\n\r\n");

    impl2.append("{ TFhirType }\r\n\r\n");
    impl2.append("function TFhirType.Link : TFhirType;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFhirType(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFhirType.Clone : TFhirType;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFhirType(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");

    impl2.append("{ TFHIRPrimitiveType }\r\n\r\n");
    impl2.append("function TFHIRPrimitiveType.Link : TFHIRPrimitiveType;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFHIRPrimitiveType(inherited Link);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFHIRPrimitiveType.Clone : TFHIRPrimitiveType;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  result := TFHIRPrimitiveType(inherited Clone);\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFHIRPrimitiveType.GetStringValue : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  if self = nil then\r\n");
    impl2.append("    result := ''\r\n");
    impl2.append("  else\r\n");
    impl2.append("    result := AsStringValue;\r\n");
    impl2.append("end;\r\n\r\n");
    impl2.append("function TFHIRPrimitiveType.AsStringValue : string;\r\n");
    impl2.append("begin\r\n");
    impl2.append("  raise Exception.create('need to override '+ClassName+'.AsStringValue');\r\n");
    impl2.append("end;\r\n\r\n");

    prsrdefX.append("    Procedure ParseElementAttributes(value : TFhirElement; path : string; element : IXmlDomElement);\r\n");
    prsrImpl.append("Procedure TFHIRXmlParser.ParseElementAttributes(value : TFhirElement; path : string; element : IXmlDomElement);\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  TakeCommentsStart(value);\r\n");
    prsrImpl.append("  value.Id := GetAttribute(element, 'id');\r\n");
    prsrImpl.append("end;\r\n\r\n");

    prsrdefX.append("    Function ParseBackboneElementChild(element : TFhirBackboneElement; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("Function TFHIRXmlParser.ParseBackboneElementChild(element : TFhirBackboneElement; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  result := true;\r\n");
    prsrImpl.append("  if (child.baseName = 'modifierExtension') then\r\n");
    prsrImpl.append("    element.ModifierExtensionList.add(ParseExtension(child, path+'/modifierExtension'))\r\n");
    prsrImpl.append("  else\r\n");
    prsrImpl.append("    result := ParseElementChild(element, path, child);\r\n");
    prsrImpl.append("end;\r\n\r\n");

    prsrdefX.append("    Function ParseElementChild(element : TFhirElement; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("Function TFHIRXmlParser.ParseElementChild(element : TFhirElement; path : string; child : IXmlDomElement) : boolean;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  result := true;\r\n");
    prsrImpl.append("  if (child.baseName = 'extension') then\r\n");
    prsrImpl.append("    element.ExtensionList.add(ParseExtension(child, path+'/extension'))\r\n");
    prsrImpl.append("  else\r\n");
    prsrImpl.append("    result := false;\r\n");
    prsrImpl.append("end;\r\n\r\n");

    prsrdefJ.append("    procedure ParseElementProperties(jsn : TJsonObject; element : TFhirElement);\r\n");
    prsrImpl.append("procedure TFHIRJsonParser.ParseElementProperties(jsn : TJsonObject; element : TFhirElement);\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  parseComments(element, jsn);\r\n\r\n");
    prsrImpl.append("  if jsn.has('id') then\r\n");
    prsrImpl.append("    element.Id := jsn['id']\r\n");
    prsrImpl.append("  else if jsn.has('_id') then\r\n");
    prsrImpl.append("    element.Id := jsn['_id'];\r\n");
    prsrImpl.append("  if jsn.has('extension') then\r\n");
    prsrImpl.append("    iterateArray(jsn.vArr['extension'], element.extensionList, parseExtension);\r\n");
    prsrImpl.append("end;\r\n\r\n");

    prsrdefJ.append("    procedure ParseBackboneElementProperties(jsn : TJsonObject; element : TFhirBackboneElement);\r\n");
    prsrImpl.append("procedure TFHIRJsonParser.ParseBackboneElementProperties(jsn : TJsonObject; element : TFhirBackboneElement);\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  parseElementProperties(jsn, element);\r\n\r\n");
    prsrImpl.append("  if jsn.has('modifierExtension') then\r\n");
    prsrImpl.append("    iterateArray(jsn.vArr['modifierExtension'], element.modifierExtensionList, parseExtension);\r\n");
    prsrImpl.append("end;\r\n\r\n");

    srlsdefX.append("    Procedure ComposeElementAttributes(xml : TXmlBuilder; element : TFhirElement);\r\n");
    prsrImpl.append("Procedure TFHIRXmlComposer.ComposeElementAttributes(xml : TXmlBuilder; element : TFhirElement);\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  CommentsStart(xml, element);\r\n");
    prsrImpl.append("  Attribute(xml, 'id', element.Id);\r\n");
    prsrImpl.append("end;\r\n\r\n");
    srlsdefX.append("    Procedure ComposeElementChildren(xml : TXmlBuilder; element : TFhirElement);\r\n");
    prsrImpl.append("Procedure TFHIRXmlComposer.ComposeElementChildren(xml : TXmlBuilder; element : TFhirElement);\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  if element.hasExtensionList then\r\n");
    prsrImpl.append("    for i := 0 to element.extensionList.count - 1 do\r\n");
    prsrImpl.append("      ComposeExtension(xml, 'extension', element.extensionList[i]);\r\n");
    prsrImpl.append("end;\r\n\r\n");
    srlsdefX.append("    Procedure ComposeBackboneElementChildren(xml : TXmlBuilder; element : TFhirBackboneElement);\r\n");
    prsrImpl.append("Procedure TFHIRXmlComposer.ComposeBackboneElementChildren(xml : TXmlBuilder; element : TFhirBackboneElement);\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  ComposeElementChildren(xml, element);\r\n");
    prsrImpl.append("  if element.hasModifierExtensionList then\r\n");
    prsrImpl.append("    for i := 0 to element.modifierExtensionList.count - 1 do\r\n");
    prsrImpl.append("      ComposeExtension(xml, 'modifierExtension', element.modifierExtensionList[i]);\r\n");
    prsrImpl.append("end;\r\n\r\n");
    srlsdefJ.append("    Procedure ComposeElementProperties(json : TJSONWriter; elem : TFhirElement);\r\n");
    prsrImpl.append("Procedure TFHIRJsonComposer.ComposeElementProperties(json : TJSONWriter; elem : TFhirElement);\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  composeComments(json, elem);\r\n");
    prsrImpl.append("  Prop(json, 'id', elem.Id);\r\n");
    prsrImpl.append("  if elem.hasExtensionList then\r\n");
    prsrImpl.append("  begin\r\n");
    prsrImpl.append("    json.valueArray('extension');\r\n");
    prsrImpl.append("    for i := 0 to elem.extensionList.Count - 1 do\r\n");
    prsrImpl.append("      ComposeExtension(json, '',elem.extensionList[i]);\r\n");
    prsrImpl.append("    json.FinishArray;\r\n");
    prsrImpl.append("  end;\r\n");
    prsrImpl.append("end;\r\n\r\n");
    srlsdefJ.append("    Procedure ComposeBackboneElementProperties(json : TJSONWriter; elem : TFhirBackboneElement);\r\n");
    prsrImpl.append("Procedure TFHIRJsonComposer.ComposeBackboneElementProperties(json : TJSONWriter; elem : TFhirBackboneElement);\r\n");
    prsrImpl.append("var\r\n");
    prsrImpl.append("  i : integer;\r\n");
    prsrImpl.append("begin\r\n");
    prsrImpl.append("  ComposeElementProperties(json, elem);\r\n");
    prsrImpl.append("  if elem.hasModifierExtensionList then\r\n");
    prsrImpl.append("  begin\r\n");
    prsrImpl.append("    json.valueArray('modifierExtension');\r\n");
    prsrImpl.append("    for i := 0 to elem.modifierExtensionList.Count - 1 do\r\n");
    prsrImpl.append("      ComposeExtension(json, '', elem.modifierExtensionList[i]);\r\n");
    prsrImpl.append("    json.FinishArray;\r\n");
    prsrImpl.append("  end;\r\n");
    prsrImpl.append("end;\r\n\r\n");

    defCodeType.classDefs.add(def.toString());
    defCodeType.classImpls.add(impl2.toString());
//    defCodeType.classFwds.add("  TFhirElement = class;\r\n");
//    defineList("TFhirElement", "TFhirElementList", null, ClassCategory.Type, true);


  }

  private void generateResource() throws Exception {
    String prefix = "frt";
    StringBuilder def = new StringBuilder();
    StringBuilder con = new StringBuilder();
    StringBuilder cmp = new StringBuilder();

    def.append("  {@Enum TFhirResourceType\r\n");
    def.append("    Enumeration of known resource types\r\n");
    def.append("  }\r\n");
    def.append("  TFhirResourceType = (\r\n");
    con.append("  CODES_TFhirResourceType : Array[TFhirResourceType] of String = (");
    def.append("    frtNull, {@enum.value Resource type not known / not Specified }\r\n");
    con.append("'', ");
    constants.add("TFhirResourceType");

    List<String> types = new ArrayList<String>();
    for (String s : definitions.getResources().keySet()) 
      types.add(s);
    for (String s : definitions.getBaseResources().keySet())
      if (!definitions.getBaseResources().get(s).isAbstract())
        types.add(s);
    Collections.sort(types);

    
    for (int i = 0; i < types.size(); i++)  {
      String s = types.get(i);
      String s2 = prefix + getTitle(s);
      if (GeneratorUtils.isDelphiReservedWord(s2))
        s2 = s2 + "_";
      con.append("'"+s);
      if (i < types.size() - 1) {
        def.append("    "+s2+", {@enum.value "+makeDocoSafe(definitions.getResourceByName(s).getDefinition())+" }\r\n");
        con.append("', ");
      } else {
        def.append("    "+s2+"); {@enum.value "+makeDocoSafe(definitions.getResourceByName(s).getDefinition())+" }\r\n");
        con.append("');");
      }
    }

    def.append("\r\n  TFhirResourceTypeSet = set of TFhirResourceType;");

    int last = 0;
    cmp.append("\r\n  COMPARTMENT_PARAM_NAMES : Array[TFhirResourceType, TFhirResourceType] of String = (");
    cmp.append("(''");
    for (String s : types) {
      cmp.append(", ''");
    }    
    cmp.append("),\r\n     ");

    con.append("\r\n  PLURAL_CODES_TFhirResourceType : Array[TFhirResourceType] of String = (");
    con.append("'', ");
    for (int i = 0; i < types.size(); i++) {
      String s = types.get(i);
      cmp.append("(''");
      Compartment c = definitions.getCompartmentByName(s.toLowerCase());
      if (c == null) {
        for (String s1 : types) {
          cmp.append(", ''");
          if (cmp.length() - last > 256) {
            cmp.append("\r\n      ");
            last = cmp.length();
          }
        }            
      } else {
        for (String s1 : types) {
          String p = c.getPathForName(s1);
          cmp.append(", '"+p+"'");
          if (cmp.length() - last > 256) {
            cmp.append("\r\n      ");
            last = cmp.length();
          }
        }    
      }
      if (i == types.size() - 1) {
        cmp.append("));\r\n");
        con.append("'"+Utilities.pluralizeMe(s.toLowerCase())+"');\r\n     ");
      } else {
        cmp.append("),\r\n");
        con.append("'"+Utilities.pluralizeMe(s.toLowerCase())+"',\r\n     ");
      }
    }
  

    con.append("\r\n  LOWERCASE_CODES_TFhirResourceType : Array[TFhirResourceType] of String = (");
    con.append("'', ");
    for (int i = 0; i < types.size(); i++) {
      String s = types.get(i);
      if (i == types.size() - 1) 
        con.append("'"+s.toLowerCase()+"');\r\n     ");
      else
        con.append("'"+s.toLowerCase()+"',\r\n     ");
    }

    con.append("\r\n  CLASSES_TFhirResourceType : Array[TFhirResourceType] of TFhirResourceClass = (");
    con.append("nil, ");
    for (int i = 0; i < types.size(); i++) {
      String s = types.get(i);
      if (i == types.size() - 1)
        con.append("TFhir"+getTitle(s)+");\r\n     ");
      else
        con.append("TFhir"+getTitle(s)+",\r\n     ");
    }

    con.append("\r\n  ALL_RESOURCE_TYPES = [");
    for (int i = 0; i < types.size(); i++) {
      String s = types.get(i);
      String s2 = prefix + getTitle(s);
      if (GeneratorUtils.isDelphiReservedWord(s2))
        s2 = s2 + "_";
      if (i == types.size() - 1)
        con.append(s2+"];\r\n     ");
      else
        con.append(s2+",\r\n     ");
    }


    defCodeRes.enumDefs.add(def.toString());
    defCodeConst.enumConsts.add(con.toString());
    defCodeConst.enumConsts.add(cmp.toString());


  }


  private String makeDocoSafe(String string) {
    string = Utilities.normaliseEolns(string);
    while (string.contains("]{") && string.contains("}") && string.indexOf("]{") < string.indexOf("}")) {
      string = string.substring(0, string.indexOf("]{")+1)+string.substring(string.indexOf("}")+1);
    }
    string = string.replace("}", "))");
    return string;
  }

  private void initParser(String version, Date genDate) {
    prsrCode.uses.add("SysUtils");
    prsrCode.uses.add("Classes");
    prsrCode.uses.add("ActiveX");
    prsrCode.uses.add("StringSupport");
    prsrCode.uses.add("DateSupport");
    prsrCode.uses.add("IdSoapMsXml");
    prsrCode.uses.add("FHIRParserBase");
    prsrCode.uses.add("DateAndTime");
    prsrCode.uses.add("FHIRBase");
    prsrCode.uses.add("FHIRResources");
    prsrCode.uses.add("FHIRConstants");
    prsrCode.uses.add("FHIRComponents");
    prsrCode.uses.add("FHIRTypes");
    prsrCode.uses.add("MsXmlParser");
    prsrCode.uses.add("XmlBuilder");
    prsrCode.uses.add("JSON");
    prsrCode.uses.add("AdvStringMatches");
    prsrCode.comments.add("FHIR v"+version+" generated "+Config.DATE_FORMAT().format(genDate));

    prsrImpl.append(
        "{ TFHIRXmlParser }\r\n"+
            "\r\n"
        );

  }

  private String buildParser() {

    prsrImpl.append(
        "function TFHIRXmlParser.ParseResource(element : IXmlDomElement; path : String) : TFhirResource;\r\n"+
            "begin\r\n"+
            "  if (element = nil) Then\r\n"+
            "    Raise Exception.Create('error - element is nil')\r\n"+
            prsrRegX.toString()+
            "  else\r\n"+
            "    raise Exception.create('Error: the element '+element.baseName+' is not recognised as a valid resource name');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "procedure TFHIRXmlComposer.ComposeResource(xml : TXmlBuilder; resource: TFhirResource; links : TFhirBundleLinkList);\r\n"+
            "begin\r\n"+
            "  if (resource = nil) Then\r\n"+
            "    Raise Exception.Create('error - resource is nil');\r\n"+
            "  Case resource.ResourceType of\r\n"+
            srlsRegX.toString()+
            "  else\r\n"+
            "    raise Exception.create('Internal error: the resource type '+CODES_TFhirResourceType[resource.ResourceType]+' is not a valid resource type');\r\n" +
            "  end;\r\n"+
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "function TFHIRJsonParser.ParseResource(jsn : TJsonObject) : TFhirResource;\r\n"+
            "var\r\n" +
            "  s : String;\r\n" +
            "begin\r\n"+
            "  s := jsn['resourceType'];\r\n "+
            prsrRegJ.toString().substring(6)+
            "  else\r\n"+
            "    raise Exception.create('error: the element '+s+' is not a valid resource name');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "function TFHIRJsonParser.ParseFragment(jsn : TJsonObject; type_ : String) : TFhirBase;\r\n"+
            "begin\r\n  "+
            prsrFragJ.toString().substring(6)+
            "  else\r\n"+
            "    raise Exception.create('error: the element '+type_+' is not a valid fragment name');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "function TFHIRXmlParser.ParseFragment(element : IXMLDOMElement) : TFhirBase;\r\n"+
            "begin\r\n  "+
            prsrFragX.toString().substring(6)+
            "  else\r\n"+
            "    raise Exception.create('error: the element '+element.nodeName+' is not a valid fragment name');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "function TFHIRJsonParser.ParseDataType(jsn : TJsonObject; name : String; type_ : TFHIRTypeClass) : TFHIRType;\r\n"+
            "begin\r\n  "+
            prsrDTJ.toString().substring(6)+
            "  else\r\n"+
            "    raise Exception.create('Unknown Type');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "function TFHIRXmlParser.ParseDataType(element : IXMLDOMElement; name : String; type_ : TFHIRTypeClass) : TFhirType;\r\n"+
            "begin\r\n  "+
            "  if (name <> '') and (name <> element.baseName) then\r\n"+
            "    raise Exception.Create('Expected Name mismatch : expected \"'+name+'\"+, but found \"'+element.baseName+'\"');\r\n"+
            prsrDTX.toString().substring(6)+
            "  else\r\n"+
            "    raise Exception.create('Unknown Type');\r\n" +
            "end;\r\n\r\n"
        );

    prsrImpl.append(
        "procedure TFHIRJsonComposer.ComposeResource(json : TJSONWriter; resource: TFhirResource; links : TFhirBundleLinkList);\r\n"+
            "begin\r\n"+
            "  if (resource = nil) Then\r\n"+
            "    Raise Exception.Create('error - resource is nil');\r\n"+
            "  json.value('resourceType', CODES_TFhirResourceType[resource.ResourceType]);\r\n"+
            "  Case resource.ResourceType of\r\n"+
            srlsRegJ.toString()+
            "  else\r\n"+
            "    raise Exception.create('Internal error: the resource type '+CODES_TFhirResourceType[resource.ResourceType]+' is not a valid resource type');\r\n" +
            "  end;\r\n"+
            "end;\r\n\r\n"
        );

    return
        "  TFHIRXmlParser = class (TFHIRXmlParserBase)\r\n"+
        "  protected\r\n"+
        prsrdefX.toString()+
        "    function ParseResource(element : IxmlDomElement; path : String) : TFhirResource; override;\r\n"+
        "    function ParseDataType(element : IXmlDomElement; name : String; type_ : TFHIRTypeClass) : TFHIRType; override;\r\n"+
        "  public\r\n"+
        "    function ParseFragment(element : IxmlDomElement) : TFhirBase; overload;\r\n"+
        "  end;\r\n\r\n"+
        "  TFHIRXmlComposer = class (TFHIRXmlComposerBase)\r\n"+
        "  protected\r\n"+
        srlsdefX.toString()+
        "    procedure ComposeResource(xml : TXmlBuilder; resource : TFhirResource; links : TFhirBundleLinkList); override;\r\n"+
        "  end;\r\n\r\n"+
        "  TFHIRJsonParser = class (TFHIRJsonParserBase)\r\n"+
        "  protected\r\n"+
        prsrdefJ.toString()+
        "    function ParseResource(jsn : TJsonObject) : TFhirResource; override;\r\n"+
        "    function ParseDataType(jsn : TJsonObject; name : String; type_ : TFHIRTypeClass) : TFHIRType; override;\r\n"+
        "  public\r\n"+
        "    function ParseFragment(jsn : TJsonObject; type_ : String) : TFhirBase;  overload;\r\n"+
        "  end;\r\n\r\n"+
        "  TFHIRJsonComposer = class (TFHIRJsonComposerBase)\r\n"+
        "  protected\r\n"+
        srlsdefJ.toString()+
        "    procedure ComposeResource(json : TJSONWriter; resource : TFhirResource; links : TFhirBundleLinkList); override;\r\n"+
        "  end;\r\n\r\n";
  }

  @Override
  public String getDescription(String version, String svnRevision) {
    return "Resource Definitions and XML & JSON parsers. Delphi 5+. Depends on IndySoap ([http://sourceforge.net/projects/indysoap/](http://sourceforge.net/projects/indysoap/)). For a full server see [http://github.com/grahamegrieve/fhirserver](http://github.com/grahamegrieve/fhirserver)";
  }

  @Override
  public String getTitle() {
    return "Pascal";
  }

  @Override
  public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    throw new UnsupportedOperationException("Pascal generator uses ElementDefn-style definitions.");
  }

  @Override
  public boolean doesCompile() {
    String dcc = System.getenv("ProgramFiles(X86)")+"\\Embarcadero\\RAD Studio\\12.0\\bin\\dcc64.exe";
    return new File(dcc).exists();
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    dcc = System.getenv("ProgramFiles(X86)")+"\\Embarcadero\\RAD Studio\\12.0\\bin\\dcc64.exe";
    exe = rootDir+"implementations\\pascal\\fhirtest.exe";
    logger.log("Compiling Pascal implementation using "+dcc, LogMessageType.Process);
    new File(exe).delete();

    List<String> command = new ArrayList<String>();
    command.add(dcc);
    command.add("-Q");
    command.add("-b");
    command.add("-W-");
    command.add("-UC:\\HL7Connect\\indysoap\\source");
    command.add("-NSSystem;System.Win;WinAPI;Vcl;Vcl.Imaging;Data;Soap");
    command.add("fhirtest.dpr");
    // command.add(">compile.log");

    ProcessBuilder builder = new ProcessBuilder().inheritIO().command(command);
    builder.directory(new File(rootDir+"implementations\\pascal"));

    Process process;
    try {
      process = builder.start();
      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return (new File(exe).exists());
  }

  @Override
  public boolean doesTest() {
    return doesCompile();
  }

  @Override
  public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {

    if (exe == null)
      exe = Utilities.path(Utilities.getDirectoryForFile(Utilities.getDirectoryForFile(rootDir)), "implementations", "pascal", "fhirtest.exe");
    if (!(new File(exe).exists()))
      throw new Exception("Delphi tool helper executeable not found "+exe);
    List<String> command = new ArrayList<String>();
    command.add(exe);
    command.add(sourceFile);
    command.add(destFile);

    ProcessBuilder builder = new ProcessBuilder().inheritIO().command(command);
    builder.directory(new File(Utilities.getDirectoryForFile(exe)));

    Process process;
    process = builder.start();
    process.waitFor();
    if (new File(destFile+".err").exists())
      throw new Exception(TextFile.fileToString(destFile+".err"));
    if (!(new File(destFile).exists()))
      throw new Exception("Neither output nor error file created");

  }

  @Override
  public String checkFragments(String rootDir, String fragments) throws Exception {
    return "Not supported by pascal implementation";
  }

  @Override
  public String getVersion() {
    return "0.80";
  }

  @Override
  public boolean canSign() {
    return doesCompile();
  }

  @Override
  public void sign(String filename, boolean atom, String type) throws Exception {
    List<String> command = new ArrayList<String>();
    command.add(exe);
    command.add(atom ? "-signatom" : "signprov");
    command.add(filename);
    command.add("-certpath");
    command.add(Utilities.path(folders.rootDir, "implementations", "certificates"));
    command.add("-certtype");
    command.add(type);

    ProcessBuilder builder = new ProcessBuilder().inheritIO().command(command);
    builder.directory(new File(Utilities.getDirectoryForFile(exe)));

    Process process;
    process = builder.start();
    process.waitFor();
    if (new File(filename+".err").exists())
      throw new Exception(TextFile.fileToString(filename+".err"));
    if (!(new File(filename).exists()))
      throw new Exception("Neither output nor error file created");
  }

  @Override
  public void verify(String filename) throws Exception {
    List<String> command = new ArrayList<String>();
    command.add(exe);
    command.add("-verify");
    command.add(filename);

    ProcessBuilder builder = new ProcessBuilder().inheritIO().command(command);
    builder.directory(new File(Utilities.getDirectoryForFile(exe)));

    Process process;
    process = builder.start();
    process.waitFor();
    if (new File(filename+".err").exists())
      throw new Exception(TextFile.fileToString(filename+".err"));
    if (!(new File(filename).exists()))
      throw new Exception("Neither output nor error file created");
  }

}
