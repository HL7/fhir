package org.hl7.fhir.tools.implementations.java;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.r5.test.utils.ToolsHelper;
import org.hl7.fhir.r5.utils.NPMPackageGenerator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator.Category;
import org.hl7.fhir.r5.utils.Version;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.implementations.java.JavaResourceGenerator.JavaGenClass;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;
import org.hl7.fhir.utilities.validation.ValidationMessage;

public class JavaGenerator extends BaseGenerator implements PlatformGenerator {

  public class JavaClass {
    private File sourceFile;
    private long sourceDate;
    private long targetDate;
    private List<JavaClass> dependencies;
    public Boolean doCompile;

    public String getName() {
      return sourceFile.getName();
    }
  }

  private static final boolean IN_PROCESS = false;

  private FolderManager folders;
  private String javaDir;
  private String javaParserDir;
  private Definitions definitions;
  private Map<String, String> hashes = new HashMap<String, String>();
  private Map<String, String> adornments = new HashMap<String, String>();
  private Map<String, String> enumInfo = new HashMap<String, String>();

  private Date genDate;

  private String javaPatternsDir;

  private String javaIntfDir;
  
  public JavaGenerator(FolderManager folders) throws FileNotFoundException, IOException {
    super();
    this.folders = folders;
    loadAdornments(Utilities.path(folders.rootDir, "tools", "java", "java-adornments.txt"));
  }

  private void loadAdornments(String path) throws FileNotFoundException, IOException {
    String[] lines = TextFile.fileToString(path).split("\\r?\\n");
    String cn = null;
    StringBuilder current = null;
    for (String line : lines) {
      if (cn != null) {
        if (line.equals("----")) {
          adornments.put(cn,  current.toString());
          cn = null;
          current = null;
        } else {
          current.append(line+"\r\n");
        }
      } else if (line.startsWith("-- ")) {
        cn = line.substring(3);
        cn = cn.substring(0, cn.indexOf(" "));
        current = new StringBuilder();
      } else {
        // nothing - ignore this line
      }
    }
  }

  @Override
  public String getName() {
    return "java";
  }

  @Override
  public String getDescription(String version, String buildId) {
    return "Resource Definitions, XML & Json parsers, & various utilities. "+
        "A Java client can be found at [https://github.com/cnanjo/FhirJavaReferenceClient](https://github.com/cnanjo/FhirJavaReferenceClient). HAPI also publishes a java reference implementation at [http://jamesagnew.github.io/hapi-fhir/](http://jamesagnew.github.io/hapi-fhir/)";
  }

  @Override
  public void generate(Definitions definitions, String destDir, String actualImpl, String implDir, String version, Date genDate, Logger logger, String buildId) throws Exception {
    char sl = File.separatorChar;
    this.genDate = genDate;
    javaDir       =  implDir+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"model"+sl;
    javaPatternsDir       =  implDir+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"patterns"+sl;
    javaIntfDir       =  implDir+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"interfaces"+sl;
    javaParserDir =  implDir+"org.hl7.fhir.r5"+sl+"src"+sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"formats"+sl;
    Utilities.createDirectory(javaDir);
    Utilities.createDirectory(Utilities.path(javaDir, "codesystems"));
    Utilities.createDirectory(javaParserDir);
    Utilities.createDirectory(javaPatternsDir);
    Utilities.createDirectory(javaIntfDir);
    Utilities.createDirectory(implDir+"org.hl7.fhir.convertors"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"convertors");
    this.definitions = definitions;

    JavaFactoryGenerator jFactoryGen = new JavaFactoryGenerator(new FileOutputStream(javaDir+"ResourceFactory.java"));

    generateResourceTypeEnum(version, buildId, genDate);
    JavaEnumerationsGenerator jEnums = new JavaEnumerationsGenerator(new FileOutputStream(javaDir+"Enumerations.java"), definitions, enumInfo);
    jEnums.generate(genDate, version);

    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        String name = Utilities.capitalize(lm.getResource().getName());
        JavaPatternIntfGenerator jrg = new JavaPatternIntfGenerator(new FileOutputStream(javaPatternsDir+javaClassName(name)+".java"), definitions, adornments, enumInfo);
        jrg.generate(lm.getResource().getRoot(), javaClassName(name), JavaGenClass.Resource, null, genDate, version, false, null, null);
        jrg.close();        
      }
    }
    for (String n : definitions.getBaseResources().keySet()) {
      ResourceDefn root = definitions.getBaseResources().get(n);
      JavaResourceGenerator jrg = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jrg.generate(root.getRoot(), javaClassName(root.getName()), JavaGenClass.Resource, null, genDate, version, root.isAbstract(), null, null);
      jrg.close();
      hashes.put(n, Long.toString(jrg.getHashSum()));
      if (!root.isAbstract())
        jFactoryGen.registerReference(n,  root.getName());
    }
    for (String n : definitions.getPrimitives().keySet())
      jFactoryGen.registerType(n,  Utilities.capitalize(n)+"Type");

    for (String n : definitions.getResources().keySet()) {
      ResourceDefn root = definitions.getResourceByName(n);
      JavaResourceGenerator jrg = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jrg.generate(root.getRoot(), javaClassName(root.getName()), JavaGenClass.Resource, null, genDate, version, false, root.getSearchParams(), root.getTemplate());
      jrg.close();
      hashes.put(n, Long.toString(jrg.getHashSum()));
      jFactoryGen.registerReference(n,  root.getName());
    }

    for (String n : definitions.getInfrastructure().keySet()) {
      ElementDefn root = definitions.getInfrastructure().get(n);
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jgen.generate(root, javaClassName(root.getName()), JavaGenClass.Structure, null, genDate, version, false, null, null);
      jgen.close();
      hashes.put(n, Long.toString(jgen.getHashSum()));
      if (!root.getName().equals("Element") && !root.getName().equals("BackboneElement") )
        jFactoryGen.registerType(n,  root.getName());
    }
    for (String n : definitions.getTypes().keySet()) {
      ElementDefn root = definitions.getTypes().get(n);
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jgen.generate(root, javaClassName(root.getName()), JavaGenClass.Type, null, genDate, version, false, null, null);
      jgen.close();
      hashes.put(n, Long.toString(jgen.getHashSum()));
      if (root.typeCode().equals("GenericType")) {
        for (TypeRef td : definitions.getKnownTypes()) {
          if (td.getName().equals(root.getName()) && td.hasParams()) {
            for (String pt : td.getParams()) {
              jFactoryGen.registerType(n+"<"+getTitle(pt)+">", root.getName()+"<"+getTitle(pt)+">");
            }
          }
        }
      } else
        jFactoryGen.registerType(n,  root.getName());
    }
    for (ProfiledType cd : definitions.getConstraints().values()) {
      ElementDefn root = definitions.getTypes().get(cd.getBaseType());
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(cd.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jgen.setInheritedHash(hashes.get(cd.getBaseType()));
      jgen.generate(root, javaClassName(cd.getName()), JavaGenClass.Constraint, cd, genDate, version, false, null, null);
      jFactoryGen.registerType(cd.getName(), cd.getName());
      hashes.put(cd.getName(), Long.toString(jgen.getHashSum()));
      jgen.close();
    }

    for (String n : definitions.getStructures().keySet()) {
      ElementDefn root = definitions.getStructures().get(n);
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jgen.generate(root, javaClassName(root.getName()), JavaGenClass.Type, null, genDate, version, false, null, null);
      jFactoryGen.registerType(n,  root.getName());
      jgen.close();
    }

    for (String r : definitions.getResourceTemplates().keySet()) {
      ResourceDefn root = definitions.getResourceTemplates().get(r);
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions, adornments, enumInfo, javaPatternsDir);
      jgen.generate(root.getRoot(), root.getName(), JavaGenClass.Resource, null, genDate, version, true, null, null);
      jgen.close();
    }
    
    for (CodeSystem cs : definitions.getCodeSystems().values()) {
      if (cs != null) {
        if (!cs.hasId())
          throw new Exception("No id on "+cs.getUrl());
        if (cs.getUserData("java-generated") == null && !cs.getId().startsWith("v2-")) {
          String tns = tokenize(cs.getId());
          JavaCodeSystemGenerator vsgen = new JavaCodeSystemGenerator(new FileOutputStream(Utilities.path(javaDir, "codesystems", tns+".java")));
          vsgen.generate(genDate, version, cs, tns);
          vsgen.close();
          JavaCodeSystemFactoryGenerator vsfgen = new JavaCodeSystemFactoryGenerator(new FileOutputStream(Utilities.path(javaDir, "codesystems", tns+"EnumFactory.java")));
          vsfgen.generate(genDate, version, cs, tns);
          vsfgen.close();
        }
      }
    }
    // delete old files to save people finding and deleting them
    deleteOldFile("XmlComposer");
    deleteOldFile("XmlBaseComposer");
    deleteOldFile("JsonComposer");
    deleteOldFile("JsonBaseComposer");

    JavaParserXmlGenerator jParserGenX = new JavaParserXmlGenerator(new FileOutputStream(javaParserDir+"XmlParser.java"));
    jParserGenX.generate(definitions, version, genDate);
    jParserGenX.generateComposer();
    jParserGenX.finish();
    JavaParserJsonGenerator jParserGenJ = new JavaParserJsonGenerator(new FileOutputStream(javaParserDir+"JsonParser.java"));
    jParserGenJ.generateParser(definitions, version, genDate);
    jParserGenJ.generateComposer();
    jParserGenJ.finish();
    JavaParserRdfGenerator jParserGenR = new JavaParserRdfGenerator(new FileOutputStream(javaParserDir+"RdfParser.java"));
    jParserGenR.generate(definitions, version, genDate);
    jParserGenR.generateComposer();
    jParserGenR.finish();
    jFactoryGen.generate(version, genDate);
    JavaConverterGenerator jConv = new JavaConverterGenerator(new FileOutputStream(implDir+"org.hl7.fhir.convertors"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"convertors"+sl+"VersionConvertor.javat"));
    jConv.generate(definitions, version, genDate);
    jConv.flush();
    jConv.close();
    TextFile.stringToFileNoPrefix(makeConstantsClass(version, buildId, genDate), implDir+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"model"+sl+"Constants.java");

    // FIXME: JA - Commented out, maybe even more can be
//    ZipGenerator zip = new ZipGenerator(destDir+getReference(version));
//    zip.addFiles(actualImpl+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"formats"+sl, "org/hl7/fhir/r5/formats/", ".java", null);
//    zip.addFiles(actualImpl+"org.hl7.fhir.r5"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"model"+sl, "org/hl7/fhir/r5/model/", ".java", null);
//    zip.addFiles(actualImpl+"org.hl7.fhir.rdf"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"rdf"+sl, "org/hl7/fhir/rdf/", ".java", null);
//    zip.addFiles(actualImpl+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl, "org/hl7/fhir/utilities/", ".java", null);
//    zip.addFiles(actualImpl+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl+"xhtml"+sl, "org/hl7/fhir/utilities/xhtml/", ".java", null);
//    zip.addFiles(actualImpl+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl+"xml"+sl, "org/hl7/fhir/utilities/xml/", ".java", null);
//
//    String importsDir = folders.rootDir+sl+"tools"+sl+"java"+sl+"imports";
//    zip.addFileName("imports/xpp3-1.1.4c.jar", importsDir+sl+"xpp3-1.1.4c.jar", false);
//    zip.addFileName("imports/gson-2.3.jar", importsDir+sl+"gson-2.3.jar", false);
//    zip.addFileName("imports/commons-codec-1.9.jar", importsDir+sl+"commons-codec-1.9.jar", false);
//    zip.addFileName("imports/commons-lang3-3.3.2.jar", importsDir+sl+"commons-lang3-3.3.2.jar", false);
//    zip.addFileName("imports/commons-logging-1.1.1.jar", importsDir+sl+"commons-logging-1.1.1.jar", false);
//    zip.addFileName("imports/commons-logging-api-1.1.jar", importsDir+sl+"commons-logging-api-1.1.jar", false);
//    zip.addFileName("imports/httpclient-4.2.3.jar", importsDir+sl+"httpclient-4.2.3.jar", false);
//    zip.addFileName("imports/httpcore-4.2.2.jar", importsDir+sl+"httpcore-4.2.2.jar", false);
//    zip.addFileName("imports/hapi-fhir-base-3.4.0.jar", importsDir+sl+"hapi-fhir-base-3.4.0.jar", false);
//
//    zip.close();

    jParserGenX.close();
    jParserGenJ.close();
    jParserGenR.close();
    jFactoryGen.close();
    saveEnumInfo();
  }

  private static final String CACHEFILE = "c:\\temp\\java.enums.r5.cache";
  
  private void saveEnumInfo() throws IOException {
    if (new File("C:\\temp").exists()) {
      StringBuilder b = new StringBuilder();
      for (Entry<String, String> e : enumInfo.entrySet()) {
        b.append(e.getKey()+"="+e.getValue()+"\r\n");
      }
      TextFile.stringToFile(b.toString(), new File(CACHEFILE), false);
    }
  }

  private String tokenize(String id) {
    StringBuilder b = new StringBuilder();
    boolean capitalize = true;
    boolean first = true;
    for (char c : id.toCharArray()) {
      if (Character.isLetter(c) || (!first && Character.isDigit(c))) {
        if (capitalize)
          b.append(Character.toUpperCase(c));
        else
          b.append(c);
        first = false;
        capitalize = false;
      } else
        capitalize = true;
    }
    String s = b.toString();
    if (s.startsWith("Valueset") || s.startsWith("ValueSet"))
      s = s.substring(8);
    if (GeneratorUtils.isJavaReservedWord(s))
      s = s + "_";
    return s;
  }

  private void deleteOldFile(String name) {
    File f = new File(javaParserDir+name+".java");
    if (f.exists())
      f.delete();
  }

  private String makeConstantsClass(String version, String buildId, Date genDate) {
    StringBuilder rt = new StringBuilder();
    boolean first = true;
    for (String n : definitions.sortedResourceNames()) {
      if (first) first = false; else rt.append("|");
      rt.append(n);
    }
      
    String s =
        "package org.hl7.fhir.r5.model;\r\n"+
            "\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n"+
            "// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n"+
            "\r\n"+
            "public class Constants {\r\n"+
            "\r\n"+
            "  public final static String VERSION = \""+version+"\";\r\n"+
            "  public final static String BUILD_ID = \""+buildId+"\";\r\n"+
            "  public final static String DATE = \""+genDate+"\";\r\n"+
            "  public final static String URI_REGEX = \"((http|https)://([A-Za-z0-9\\\\\\\\\\\\.\\\\:\\\\%\\\\$]*\\\\/)*)?("+rt.toString()+")\\\\/[A-Za-z0-9\\\\-\\\\.]{1,64}(\\\\/_history\\\\/[A-Za-z0-9\\\\-\\\\.]{1,64})?\";\r\n"+
            "}\r\n";
    return s;
  }

  private void generateResourceTypeEnum(String version, String buildId, Date genDate) throws Exception {

    OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(javaDir+"ResourceType.java"), "UTF-8");
    output.write("package org.hl7.fhir.r5.model;\r\n");
    output.write("\r\n");
    output.write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    output.write("\r\n");
    output.write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    output.write("public enum ResourceType {");

    List<String> names = new ArrayList<String>();
    for (String n : definitions.getResources().keySet())
      names.add(n);
    for (String n : definitions.getBaseResources().keySet())
      if (!definitions.getBaseResources().get(n).isAbstract())
        names.add(n);
    Collections.sort(names);

    boolean first = true;
    for (String n : names) {
      if (first)
        first = false;
      else
        output.write(",");
      output.write("\r\n    "+n);
    }
    output.write(";\r\n\r\n");

    output.write("\r\n    public String getPath() {;\r\n");
    output.write("      switch (this) {\r\n");
    for (String n : names) {
      output.write("    case "+n+":\r\n");
      output.write("      return \""+n.toLowerCase()+"\";\r\n");
    }
    output.write("    }\r\n      return null;\r\n");
    output.write("  }\r\n\r\n");

    output.write("\r\n    public static ResourceType fromCode(String code) throws FHIRException {;\r\n");
    for (String n : names) {
      output.write("    if (\""+n+"\".equals(code))\r\n");
      output.write("      return "+n+";\r\n");
    }
    output.write("\r\n    throw new FHIRException(\"Unknown resource type\"+code);\r\n");
    output.write("  }\r\n\r\n");

    output.write("}\r\n");
    output.close();

  }

  private String javaClassName(String name) {
    if (name.equals("List"))
      return "ListResource";
    else
      return name;
  }

  private String getTitle(String n) {
    return n.substring(0,1).toUpperCase()+n.substring(1);
  }

  @Override
  public String getTitle() {
    return "Java";
  }


  @Override
  public boolean doesCompile() {
    return true; // ToolProvider.getSystemJavaCompiler() != null;
  }

//  public boolean c(String name) {
//    char sl = File.separatorChar;
//
//
//
//    int r = ToolProvider.getSystemJavaCompiler().run(null, null, null, folders.rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.r5"+sl+"src"+sl+"org"+sl+"hl7"+sl+"fhir"+sl+"r5"+sl+"model"+sl+"Type.java");
//    return r == 0;
//  }


  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger, List<ValidationMessage> issues, boolean forWeb) throws Exception {

    // FIXME: JA this method is NOP - Can it be removed?
    if (true) {
      return true;
    }

    logger.log(" .... build validator", LogMessageType.Process);
    // now, we pack a jar with what we need for testing:
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");
    manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "org.hl7.fhir.r5.validation.Validator");

    JarOutputStream jar = new JarOutputStream(new FileOutputStream(Utilities.path(folders.dstDir, "org.hl7.fhir.validator.jar")), manifest);
    List<String> names = new ArrayList<String>();
    names.add("META-INF/");
    names.add("META-INF/MANIFEST.MF");

    String importsDir = Utilities.path(rootDir, "tools", "java", "imports");
    AddJarToJar(jar, Utilities.path(importsDir, "xpp3-1.1.4c.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "gson-2.3.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-codec-1.9.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-io-1.2.jar"), names);
    //    AddJarToJar(jar, Utilities.path(importsDir, "Saxon-B-9.0.jar"), names);
    //    AddJarToJar(jar, Utilities.path(importsDir, "saxon-dom-8.7.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "Saxon-HE-9.5.1-5.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-lang3-3.3.2.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-logging-1.1.1.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-logging-api-1.1.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "commons-compress-1.16.1.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "httpclient-4.2.3.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "httpcore-4.2.2.jar"), names);
    // FIXME: JA need to pull this version from the ivy file
    AddJarToJar(jar, Utilities.path(importsDir, "hapi-fhir-base-3.7.0-SNAPSHOT.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "ucum-1.0-SNAPSHOT.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "junit-4.11.jar"), names);
    AddJarToJar(jar, Utilities.path(importsDir, "hamcrest-core-1.3.jar"), names);

    // by adding source first, we add all the newly built classes, and these are not updated when the older stuff is included
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.r5", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.r5", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.dstu3", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.dstu3", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.dstu2", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.dstu2", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.dstu2016may", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.dstu2016may", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.convertors", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.convertors", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.validation", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.validation", "bin", "").length(), names);
    AddToJar(jar, new File(Utilities.path(rootDir+"implementations", "java", "org.hl7.fhir.utilities", "bin")), Utilities.path(rootDir,"implementations", "java", "org.hl7.fhir.utilities", "bin", "").length(), names);
    jar.close();

    // now, we pack a jar with what we need for testing:
    manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");
    manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "org.hl7.fhir.r5.test.ToolsHelper");
    
    logger.log(" .... build validator package", LogMessageType.Process);
    ImplementationGuide ig = new ImplementationGuide();
    ig.setUrl("http://hl7.org/fhir/validator");
    ig.setVersion(buildId); // gh-todo
    ig.setName("FHIRValidator");
    ig.setTitle("FHIR Validator");
    ig.setStatus(PublicationStatus.ACTIVE);
    ig.setLicense(SPDXLicense.CC01_0);
    ig.setPackageId("hl7.fhir.tools.validator");
    ig.setPublisher("http://hl7.org/fhir");
    ig.addFhirVersion(FHIRVersion.fromCode(Constants.VERSION));
        
    NPMPackageGenerator npm = new NPMPackageGenerator(Utilities.path(folders.dstDir, "validator.tgz"), ig.getUrl(), forWeb ? "http://hl7.org/fhir" : "http://build.fhir.org", PackageType.TOOL, ig, Config.DATE_FORMAT().format(genDate) );
    npm.addFile(Category.TOOL, "org.hl7.fhir.validator.jar", TextFile.fileToBytes(Utilities.path(folders.dstDir, "org.hl7.fhir.validator.jar")));
    npm.finish();
    return true;
  }

  private void addSourceFiles(int prefix, Map<String, JavaClass> classes, String name, List<String> paths) {
    if (prefix == 0)
      prefix = name.length()+1;
    File f = new File(name);
    if (f.isDirectory()) {
      for (String n : f.list()) {
        addSourceFiles(prefix, classes, name+File.separator+n, paths);
      }
    } else if (name.endsWith(".java")) {
      String path = f.getParent();
      if (!paths.contains(path))
        paths.add(path);

      JavaClass jc = new JavaClass();
      jc.sourceFile = f;
      jc.sourceDate = f.lastModified();
      File cf = new File(Utilities.changeFileExt(f.getAbsolutePath(), ".class"));
      if (cf.exists())
        jc.targetDate = cf.lastModified();
      classes.put(Utilities.changeFileExt(f.getAbsolutePath(), "").substring(prefix).replace(File.separatorChar, '.'), jc);
    }
  }

  private List<File> listFilesToCompile(Map<String, JavaClass> classes) throws IOException {
    // first pass: determine dependencies
    for (JavaClass jc : classes.values()) {
      if (jc.dependencies == null)
        jc.dependencies = determineDependencies(jc, classes);
      if (jc.sourceDate > jc.targetDate)
        jc.doCompile = true;
    }
    // second pass: mark everything that needs compiling (dependents)
    for (JavaClass jc : classes.values()) {
      if (jc.doCompile == null)
        jc.doCompile = checkNeedsCompile(jc.dependencies);
    }
    List<File> list = new ArrayList<File>();
    for (JavaClass jc : classes.values()) {
      // if (jc.doCompile) // - enable this to set up minimal compiling
      list.add(jc.sourceFile);
    }
    return list;
  }

  private Boolean checkNeedsCompile(List<JavaClass> dependencies) {
    //    for (JavaClass jc : dependencies) {
    //      if (jc.doCompile == null)
    //        jc.doCompile = checkNeedsCompile(jc.dependencies);
    //      if (jc.doCompile)
    //        return true;
    //    }
    return true;
  }

  private List<JavaClass> determineDependencies(JavaClass jc, Map<String, JavaClass> classes) throws IOException {
    List<String> imports = new ArrayList<String>();
    BufferedReader src = new BufferedReader(new InputStreamReader(new FileInputStream(jc.sourceFile)));
    String line = src.readLine();
    while (!line.contains("class") && !line.contains("enum") && !line.contains("interface")) {
      line = line.trim();
      if (line.endsWith(";"))
        line = line.substring(0, line.length()-1);
      if (line.startsWith("import")) {
        imports.add(line.substring(7));
      }
      line = src.readLine();
    }
    src.close();
    List<JavaClass> list = new ArrayList<JavaGenerator.JavaClass>();
    for (String imp : imports) {
      if (classes.containsKey(imp)) {
        list.add(classes.get(imp));
      } else if (imp.startsWith("org.hl7.fhir.instance.model.api")) {
        // This comes from the HAPI JAR because it's common across versions
        continue;
      } else if (imp.startsWith("org.hl7.fhir")) {
        boolean found = false;
        if (imp.endsWith(".*")) {
          String mask = imp.substring(0, imp.length()-1);
          for (String s : classes.keySet()) {
            if (s.startsWith(mask)) {
              list.add(classes.get(s));
              found = true;
            }
          }
        }
        if (!found) {
          String s = imp.substring(0, imp.lastIndexOf("."));
          while (s.contains(".") && !found) {
            if (classes.containsKey(s)) {
              found = true;
              list.add(classes.get(s));
            }
            s = s.substring(0, s.lastIndexOf("."));
          }
        }
        if (!found)
          throw new Error("unable to find import for class " + jc.getName() + ": " +imp);
      }

    }
    return list;
  }

  private void AddJarToJar(JarOutputStream jar, String name, List<String> names) throws Exception {
    ZipInputStream zip = new ZipInputStream(new FileInputStream(name));
    ZipEntry ze = null;
    while ((ze = zip.getNextEntry()) != null) {
      String n = ze.getName();
      if (!names.contains(n)) {
        names.add(n);
        JarEntry jarAdd = new JarEntry(n);
        jarAdd.setTime(ze.getTime());
        jar.putNextEntry(jarAdd);
        for (int c = zip.read(); c != -1; c = zip.read()) {
          jar.write(c);
        }
      }
      zip.closeEntry();
    }
    zip.close();
  }


  private static int BUFFER_SIZE = 10240;
  private void AddToJar(JarOutputStream jar, File file, int rootLen, List<String> names) throws Exception {
    if (!file.exists())
      return;

    if (file.isDirectory()) {
      String name = file.getPath().replace("\\", "/");
      if (!name.isEmpty())
      {
        if (!name.endsWith("/"))
          name += "/";
        String n = name.substring(rootLen);
        if (n.length() > 0 && !names.contains(n)) {
          names.add(n);
          JarEntry entry = new JarEntry(n);
          entry.setTime(file.lastModified());
          jar.putNextEntry(entry);
          jar.closeEntry();
        }
      }
      for (File f: file.listFiles())
        if (f.getName().endsWith(".class") || f.getName().endsWith(".jar") || f.isDirectory())
          AddToJar(jar, f, rootLen, names);
    } else {
      String n = file.getPath().substring(rootLen).replace("\\", "/");
      if (!names.contains(n)) {
        names.add(n);
        JarEntry jarAdd = new JarEntry(n);
        jarAdd.setTime(file.lastModified());
        jar.putNextEntry(jarAdd);

        // Write file to archive
        byte buffer[] = new byte[BUFFER_SIZE];
        FileInputStream in = new FileInputStream(file);
        while (true) {
          int nRead = in.read(buffer, 0, buffer.length);
          if (nRead <= 0)
            break;
          jar.write(buffer, 0, nRead);
        }
        in.close();
        jar.closeEntry();
      }
    }
  }



  @Override
  public String getVersion() {
    return Version.VERSION; // this has to be hard coded, but we'll fetch if later from the client and check that it's correct
  }

  public void canonicaliseXml(FolderManager folders, String sourceFile, String destFile) throws Exception {
    // for debugging: do it in process
    if (IN_PROCESS) {
      ToolsHelper t = new ToolsHelper();
      String[] cmds = new String[] {"json", sourceFile, destFile};
      t.executeCanonicalXml(cmds);
    } else {

      // execute the jar file javatest.jar
      // it will produce either the specified output file, or [output file].err with an exception
      //
      File file = new CSFile(destFile);
      if (file.exists())
        file.delete();
      file = new CSFile(destFile+".err");
      if (file.exists())
        file.delete();

      List<String> command = new ArrayList<String>();
      command.add("java");
      command.add("-jar");
      command.add("org.hl7.fhir.tools.jar");
      command.add("cxml");
      command.add(sourceFile);
      command.add(destFile);

      ProcessBuilder builder = new ProcessBuilder(command);
      builder.directory(new File(folders.dstDir));

      final Process process = builder.start();
      BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String s;
      while ((s = stdError.readLine()) != null) {
        System.err.println(s);
      }

      process.waitFor();
      if (new File(destFile+".err").exists())
        throw new Exception(TextFile.fileToString(destFile+".err"));
    }
  }

  private boolean hasBinIGTools(String path) {
    File file = new File(path);
    return containsClassFiles(file);
  }
  private boolean containsClassFiles(File file) {
    if (!file.exists())
      return false;

    if (file.isDirectory()) {
      for (File f: file.listFiles()) {
        if (containsClassFiles(f))
          return true;
      }
      return false;
    } else 
      return file.getName().endsWith(".class");
  }


}
