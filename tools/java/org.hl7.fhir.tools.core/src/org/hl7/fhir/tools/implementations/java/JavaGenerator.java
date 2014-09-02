package org.hl7.fhir.tools.implementations.java;
/*
Copyright (c) 2011-2014, HL7, Inc
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
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.Constants;
import org.hl7.fhir.instance.test.ToolsHelper;
import org.hl7.fhir.instance.utils.Version;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.implementations.java.JavaResourceGenerator.JavaGenClass;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.Logger.LogMessageType;

public class JavaGenerator extends BaseGenerator implements PlatformGenerator {

  private static final boolean IN_PROCESS = false;
  
  private FolderManager folders;
  private String javaDir;
  private String javaParserDir;
  private Definitions definitions;
  private Map<String, String> hashes = new HashMap<String, String>();
  
  public JavaGenerator(FolderManager folders) {
    super();
    this.folders = folders;
  }

  @Override
  public String getName() {
    return "java";
  }

  @Override
  public String getDescription() {
    return "Resource Definitions, XML & Json parsers, & various utilities. The java reference implementation depends on XmlPull ([[http://www.xmlpull.org/]]), the Java JSON library ([[http://json.org]]), the Apache Commons Codec library ([[http://commons.apache.org/codec/]]), and Saxon 9 (for validation). A Java client can be found at [[https://github.com/cnanjo/FhirJavaReferenceClient]]";
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    char sl = File.separatorChar;
    javaDir       =  implDir+"org.hl7.fhir.instance"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"model"+sl;
    javaParserDir =  implDir+"org.hl7.fhir.instance"+sl+"src"+sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"formats"+sl;
    this.definitions = definitions;

    for (String n : definitions.getDeletedResources()) {
      File f = new File(implDir+"org.hl7.fhir.instance"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"model"+sl+n+".java");
      if (f.exists())
        f.delete();
    }
    JavaFactoryGenerator jFactoryGen = new JavaFactoryGenerator(new FileOutputStream(javaDir+"ResourceFactory.java"));
    
    generateResourceTypeEnum();
    for (String n : definitions.getResources().keySet()) {
      ResourceDefn root = definitions.getResourceByName(n); 
      JavaResourceGenerator jrg = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions);
      jrg.generate(root.getRoot(), javaClassName(root.getName()), definitions.getBindings(), JavaGenClass.Resource, null, genDate, version);
      jrg.close();
      hashes.put(n, Long.toString(jrg.getHashSum()));
      jFactoryGen.registerResource(n,  root.getName());
    }

    generateResourceTypeEnum();
    for (ResourceDefn resource : definitions.getFutureResources().values()) {
      ElementDefn e = new ElementDefn();
      e.setName(resource.getName());
      JavaResourceGenerator jrg = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(e.getName())+".java"), definitions);
      jrg.generate(e, javaClassName(e.getName()), definitions.getBindings(), JavaGenClass.Resource, null, genDate, version);
      jrg.close();
      hashes.put(resource.getName(), Long.toString(jrg.getHashSum()));
      jFactoryGen.registerResource(resource.getName(),  e.getName());
    }

    for (String n : definitions.getInfrastructure().keySet()) {
      ElementDefn root = definitions.getInfrastructure().get(n); 
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions);
      jgen.generate(root, javaClassName(root.getName()), definitions.getBindings(), JavaGenClass.Structure, null, genDate, version);
      jgen.close();
      hashes.put(n, Long.toString(jgen.getHashSum()));
      jFactoryGen.registerType(n,  root.getName());
    }
    for (String n : definitions.getTypes().keySet()) {
      ElementDefn root = definitions.getTypes().get(n); 
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions);
      jgen.generate(root, javaClassName(root.getName()), definitions.getBindings(), JavaGenClass.Type, null, genDate, version);
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
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(cd.getName())+".java"), definitions);
      jgen.setInheritedHash(hashes.get(cd.getBaseType()));
      jgen.generate(root, javaClassName(cd.getName()), definitions.getBindings(), JavaGenClass.Constraint, cd, genDate, version);
      jFactoryGen.registerType(cd.getName(), cd.getName()); 
      hashes.put(cd.getName(), Long.toString(jgen.getHashSum()));
      jgen.close();
    }
    
    for (String n : definitions.getStructures().keySet()) {
      ElementDefn root = definitions.getStructures().get(n); 
      JavaResourceGenerator jgen = new JavaResourceGenerator(new FileOutputStream(javaDir+javaClassName(root.getName())+".java"), definitions);
      jgen.generate(root, javaClassName(root.getName()), definitions.getBindings(), JavaGenClass.Type, null, genDate, version);
      jFactoryGen.registerType(n,  root.getName());
      jgen.close();
    }
    
    JavaParserXmlGenerator jParserGenX = new JavaParserXmlGenerator(new FileOutputStream(javaParserDir+"XmlParser.java"));
    jParserGenX.generate(definitions, version, genDate);    
    JavaParserJsonGenerator jParserGenJ = new JavaParserJsonGenerator(new FileOutputStream(javaParserDir+"JsonParser.java"));
    jParserGenJ.generate(definitions, version, genDate);    
    JavaComposerXmlGenerator jComposerGen = new JavaComposerXmlGenerator(new FileOutputStream(javaParserDir+"XmlComposer.java"));
    jComposerGen.generate(definitions, version, genDate);    
    JavaComposerJsonGenerator jjComposerGen = new JavaComposerJsonGenerator(new FileOutputStream(javaParserDir+"JsonComposer.java"));
    jjComposerGen.generate(definitions, version, genDate);    
    jFactoryGen.generate(version, genDate);
    TextFile.stringToFileNoPrefix(makeConstantsClass(version, svnRevision, genDate), implDir+"org.hl7.fhir.instance"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"model"+sl+"Constants.java");
    
    ZipGenerator zip = new ZipGenerator(destDir+getReference(version));
    zip.addFiles(implDir+"org.hl7.fhir.instance"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"model"+sl, "org/hl7/fhir/instance/model/", ".java", null);
    zip.addFiles(implDir+"org.hl7.fhir.instance"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"formats"+sl, "org/hl7/fhir/instance/formats/", ".java", null);
    zip.addFiles(implDir+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl, "org/hl7/fhir/utilities/", ".java", null);
    zip.addFiles(implDir+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl+"xhtml"+sl, "org/hl7/fhir/utilities/xhtml/", ".java", null);
    zip.addFiles(implDir+"org.hl7.fhir.utilities"+sl+"src"+ sl+"org"+sl+"hl7"+sl+"fhir"+sl+"utilities"+sl+"xml"+sl, "org/hl7/fhir/utilities/xml/", ".java", null);

    String importsDir = folders.rootDir+sl+"tools"+sl+"java"+sl+"imports";
    zip.addFileName("imports/xpp3-1.1.3.4.O.jar", importsDir+sl+"xpp3-1.1.3.4.O.jar", false);
    zip.addFileName("imports/gson-2.2.4.jar", importsDir+sl+"gson-2.2.4.jar", false);
    zip.addFileName("imports/commons-codec-1.3.jar", importsDir+sl+"commons-codec-1.3.jar", false);
    
    zip.close();
    jjComposerGen.close();
    jComposerGen.close();
    jParserGenX.close();
    jParserGenJ.close();
    jFactoryGen.close();
  }

  private String makeConstantsClass(String version, String svnRevision, Date genDate) {
    String s = 
        "package org.hl7.fhir.instance.model;\r\n"+
            "\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n"+
            "// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n"+
            "\r\n"+
            "public class Constants {\r\n"+
            "\r\n"+
            "  public final static String VERSION = \""+version+"\";\r\n"+
            "  public final static String REVISION = \""+svnRevision+"\";\r\n"+
            "  public final static String DATE = \""+genDate+"\";\r\n"+
            "}\r\n";
    return s;
  }

  private void generateResourceTypeEnum() throws Exception {

    OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(javaDir+"ResourceType.java")); 
    output.write("package org.hl7.fhir.instance.model;\r\n");
    output.write("\r\n");
    output.write("public enum ResourceType {\r\n");

    for (String n : definitions.getResources().keySet()) {
      output.write("    "+n+",\r\n");
    }
    for (String n : definitions.getFutureResources().keySet()) {
      output.write("    "+n+",\r\n");
    }

    output.write("    Binary;\r\n");
    output.write("\r\n    public String getPath() {;\r\n");
    output.write("      switch (this) {\r\n");
      for (String n : definitions.getResources().keySet()) {
        output.write("    case "+n+":\r\n");
        output.write("      return \""+n.toLowerCase()+"\";\r\n");
      }

      for (String n : definitions.getFutureResources().keySet()) {
        output.write("    case "+n+":\r\n");
        output.write("      return \""+n.toLowerCase()+"\";\r\n");
      }    
    
      output.write("    case Binary:\r\n");
      output.write("      return \"binary\";\r\n");
    output.write("    }\r\n      return null;\r\n");
    output.write("  }\r\n\r\n");
    output.write("}\r\n");
    output.close();

  }

  private String javaClassName(String name) {
    if (name.equals("List"))
      return "List_";
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
public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {    
    throw new UnsupportedOperationException("Java generator uses ElementDefn-style definitions.");	
  }

  @Override
public boolean doesCompile() {
    return true; // ToolProvider.getSystemJavaCompiler() != null;
  }

  public boolean c(String name) {
    char sl = File.separatorChar;

	  
	  
    int r = ToolProvider.getSystemJavaCompiler().run(null, null, null, folders.rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.instance"+sl+"src"+sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"model"+sl+"Type.java");
    return r == 0;
  }
  
  @Override
public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    assert(this.folders.rootDir.equals(rootDir));
    char sl = File.separatorChar;
    List<File> classes = new ArrayList<File>();

    addSourceFiles(classes, rootDir + "implementations"+sl+"java"+sl+"org.hl7.fhir.utilities");
    addSourceFiles(classes, rootDir + "implementations"+sl+"java"+sl+"org.hl7.fhir.instance");
  
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    if (compiler == null)
      throw new Exception("Cannot continue build process as java compilation services are not available. Check that you are executing the build process using a jdk, not a jre");
    
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
    
    Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjectsFromFiles(classes);
    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
    List<String> options = new ArrayList<String>();
    StringBuilder path= new StringBuilder();
    for (String n : new File(rootDir+sl+"tools"+sl+"java"+sl+"imports").list()) {
      path.append(File.pathSeparator+rootDir+"tools"+sl+"java"+sl+"imports"+sl+n);
    }
    options.addAll(Arrays.asList("-classpath",path.toString()));
    //logger.log("Classpath: "+path.toString());
    JavaCompiler.CompilationTask task = ToolProvider.getSystemJavaCompiler().getTask(null, null, diagnostics, options, null, units);
    Boolean result = task.call();
    if (!result) {
      for (Diagnostic<? extends JavaFileObject> t : diagnostics.getDiagnostics()) {
        logger.log("c: "+t.toString(), LogMessageType.Error);
      }
    }

    // now, we pack a jar with what we need for testing:
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");
    manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "org.hl7.fhir.instance.validation.Validator");
    
    JarOutputStream jar = new JarOutputStream(new FileOutputStream(folders.dstDir+sl+"org.hl7.fhir.validator.jar"), manifest);
    List<String> names = new ArrayList<String>();
    names.add("META-INF/");
    names.add("META-INF/MANIFEST.MF");

    String importsDir = rootDir+sl+"tools"+sl+"java"+sl+"imports";
    AddJarToJar(jar, importsDir+sl+"xpp3-1.1.3.4.O.jar", names);
    AddJarToJar(jar, importsDir+sl+"gson-2.2.4.jar", names);
    AddJarToJar(jar, importsDir+sl+"commons-codec-1.3.jar", names);
//    AddJarToJar(jar, importsDir+sl+"Saxon-HE-9.5.1-5.jar", names);
    AddJarToJar(jar, importsDir+sl+"Saxon-B-9.0.jar", names);
    
    // by adding source first, we add all the newly built classes, and these are not updated when the older stuff is included
    AddToJar(jar, new File(rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.instance"+sl+"src"), (rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.instance"+sl+"src"+sl).length(), names);
    AddToJar(jar, new File(rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.utilities"+sl+"src"), (rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.utilities"+sl+"src"+sl).length(), names);
    jar.close();

    // now, we pack a jar with what we need for testing:
    manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");
    manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "org.hl7.fhir.instance.test.ToolsHelper");
    
    jar = new JarOutputStream(new FileOutputStream(folders.dstDir+sl+"org.hl7.fhir.tools.jar"), manifest);
    names = new ArrayList<String>();
    names.add("META-INF/");
    names.add("META-INF/MANIFEST.MF");

    importsDir = rootDir+sl+"tools"+sl+"java"+sl+"imports";
    AddJarToJar(jar, importsDir+sl+"xpp3-1.1.3.4.O.jar", names);
    AddJarToJar(jar, importsDir+sl+"gson-2.2.4.jar", names);
    AddJarToJar(jar, importsDir+sl+"commons-codec-1.3.jar", names);
    
    // by adding source first, we add all the newly built classes, and these are not updated when the older stuff is included
    AddToJar(jar, new File(rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.instance"+sl+"src"), (rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.instance"+sl+"src"+sl).length(), names);
    AddToJar(jar, new File(rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.utilities"+sl+"src"), (rootDir+"implementations"+sl+"java"+sl+"org.hl7.fhir.utilities"+sl+"src"+sl).length(), names);
    jar.close();

    checkVersion();

    return result;
  }

  
  private void checkVersion() throws Exception {
    // execute the jar file javatest.jar to check that it's version matches the version of the reference implemetnation bound in to the build tool
    // also serves as as check of the java
    // 
    String destFile = Utilities.path(System.getProperty("java.io.tmpdir"), "java-version.tmp"); 
    File file = new CSFile(destFile);
    if (file.exists())
      file.delete();
    
    List<String> command = new ArrayList<String>();
    command.add("java");
    command.add("-jar");
    command.add("org.hl7.fhir.tools.jar");
    command.add("version");
    command.add(destFile);

    ProcessBuilder builder = new ProcessBuilder(command);
    builder.directory(new File(folders.dstDir));
    final Process process = builder.start();
    BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    String s;
    while ((s = stdError.readLine()) != null) {
      System.err.println(s);
    }    
    builder.directory(new File(folders.dstDir));

    process.waitFor();
    if (!(new File(destFile).exists()))
        throw new Exception("Unable to check Java library version");
    String[] ver = TextFile.fileToString(destFile).split(":");
    if (!ver[1].equals(Constants.VERSION))
      throw new Exception("Version mismatch - the compiled version is using FHIR "+ver[1]+" but the bound version of FHIR is "+Constants.VERSION);
    if (!ver[0].equals(getVersion()))
      throw new Exception("Version mismatch - the compiled version of the reference implementation is "+ver[0]+" but the bound version is "+getVersion());
  }

  private void addSourceFiles(List<File> classes, String name) {
    File f = new File(name);
    if (f.isDirectory()) {
      for (String n : f.list()) {
        addSourceFiles(classes, name+File.separator+n);
      }
    } else if (name.endsWith(".java")) {
      classes.add(f);
    }
    
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
public boolean doesTest() {
    return true;
  }

  @Override
public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {
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
    command.add("round");
    command.add(sourceFile);
    command.add(destFile);

    ProcessBuilder builder = new ProcessBuilder(command);
    builder.directory(new File(rootDir));

    final Process process = builder.start();
    process.waitFor();
    if (new File(destFile+".err").exists())
      throw new Exception(TextFile.fileToString(destFile+".err"));
    if (!(new File(destFile).exists()))
        throw new Exception("Neither output nor error file created");
  }

  public String convertToJson(String rootDir, String sourceFile, String destFile) throws Exception {
    // for debugging: do it in process
    if (IN_PROCESS) {
      ToolsHelper t = new ToolsHelper();
      String[] cmds = new String[] {"json", sourceFile, destFile};    
      return t.executeJson(cmds);
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
      command.add("json");
      command.add(sourceFile);
      command.add(destFile);

      ProcessBuilder builder = new ProcessBuilder(command);
      builder.directory(new File(rootDir));

      final Process process = builder.start();
      BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String s;
      while ((s = stdError.readLine()) != null) {
        System.err.println(s);
      }    

      process.waitFor();
      if (new File(destFile+".err").exists())
        throw new Exception(TextFile.fileToString(destFile+".err"));
      if (!(new File(destFile+".tmp").exists()))
        throw new Exception("Neither output nor error file created doing json conversion");    
      if (new File(destFile+".tmp").length() == 0)
        throw new Exception("Output file '"+destFile+".tmp' empty");  
      String txt = TextFile.fileToString(destFile+".tmp");
      new File(destFile+".tmp").delete();
      return txt;
      
    } 
  }

  @Override
  // in process for debugging, but requires tool generated code to be current
  public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    File file = Utilities.createTempFile("temp", ".xml");
    if (file.exists())
      file.delete();
    TextFile.stringToFile(fragments, file.getAbsolutePath());
    
    File filed = Utilities.createTempFile("temp", ".txt");
    if (filed.exists())
      filed.delete();
    
    if (inProcess) {
      new ToolsHelper().executeFragments(new String[] {"fragments", file.getAbsolutePath(), filed.getAbsolutePath()}); 
    } else {
      List<String> command = new ArrayList<String>();
      command.add("java");
      command.add("-jar");
      command.add("org.hl7.fhir.tools.jar");
      command.add("fragments");
      command.add(file.getAbsolutePath());
      command.add(filed.getAbsolutePath());

      ProcessBuilder builder = new ProcessBuilder().inheritIO().command(command);
      builder.directory(new File(rootDir));

      final Process process = builder.start();
      process.waitFor();
    }
    if (!filed.exists())
      return "Fragment processing failed completely";
    String s = TextFile.fileToString(filed.getAbsolutePath());
    if ("ok".equals(s))
      return null;
    else
      return s;
  }

  @Override
  public String getVersion() {
    return Version.VERSION; // this has to be hard coded, but we'll fetch if later from the client and check that it's correct
  }
}
