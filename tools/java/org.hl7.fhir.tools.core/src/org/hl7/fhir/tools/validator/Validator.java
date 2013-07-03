package org.hl7.fhir.tools.validator;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.validation.ProfileValidatorTests;
import org.hl7.fhir.instance.validation.ValidationEngine;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.utilities.Utilities;

/**
 * A service that will validate one or more FHIR resources against 
 * the specification
 * 
 * @author Grahame
 *
 */
public class Validator {

  public static void main(String[] args) throws Exception {
    String output = null;
    if (args.length == 0) {
      System.out.println("FHIR Validation tool. ");
      System.out.println("");
      System.out.println("The FHIR validation tool validates a FHIR resource or bundle.");
      System.out.println("Schema and schematron checking is performed, then some additional checks are performed");
      System.out.println("");
      System.out.println("JSON is not supported at this time");
      System.out.println("");
      System.out.println("Usage: FHIRValidator.jar [source] (-defn [definitions]) (-output [output]) where: ");
      System.out.println("* [source] is a file name or url of the resource or bundle feed to validate");
      System.out.println("* [definitions] is the file name or url of the validation pack (validation.zip). Default: get it from hl7.org");
      System.out.println("* [output] is a filename for the results (OperationOutcome). Default: results are sent to the std out.");
      System.out.println("");
      System.out.println("Or: FHIRValidator.jar -profile-tests [registry] (-defn [definitions])");
      System.out.println("");
      System.out.println("Master Source for the validation pack: "+MASTER_SOURCE);
    } else {
      if (args[0].equals("-profile-tests")) {
        String pack = null;
        String registry = null;
        for (int i = 0; i < args.length - 1; i++) {
          if (args[i].equals("-profile-tests"))
            registry = args[i+1];
          if (args[i].equals("-defn"))
            pack = args[i+1];
        }
        ProfileValidatorTests tests = new ProfileValidatorTests(new File(pack), new File(registry));
        tests.execute();
      } else { 
        Validator exe = new Validator();
        exe.setSource(args[0]);
        for (int i = 1; i < args.length - 1; i++) {
          if (args[i].equals("-defn"))
            exe.setDefinitions(args[i+1]);
          if (args[i].equals("-output"))
            output = args[i+1];
        }
        exe.process();
        if (output == null) {
          System.out.println("Validating "+args[0]+": "+Integer.toString(exe.outputs().size())+" messages");
          for (ValidationMessage v : exe.outputs()) {
            System.out.println(v.summary());
          }
          if (exe.outputs().size() == 0)
            System.out.println(" ...success");
          else
            System.out.println(" ...failure");
        } else {
          new XmlComposer().compose(new FileOutputStream(output), exe.engine.getOutcome(), true);
        }
      }
    }
  }


  private List<ValidationMessage> outputs() {
    return engine.getOutputs();
  }


  /**
   * The source (file name, folder name, url) of the FHIR validation pack. This can be the 
   * fhir url, an alternative url of a local copy of the fhir spec, the name of 
   * a zip file containing the fhir spec, the name of a directory containing the
   * fhir spec 
   */
  private String definitions;
  
  /**
   * The name of the resource/feed to validate. this can be the actual source as json or xml, a file name, a zip file, 
   * or a url. If the source identifies a collection of resources and/or feeds, they
   * will all be validated
   */
  private String source;

  ValidationEngine engine = new ValidationEngine();
  static final String MASTER_SOURCE = "??";

  public void process() throws Exception {
    byte[] defn = loadDefinitions();
    readDefinitions(engine, defn);
    engine.setSource(loadSource());
    engine.process();
  }

 
  private void readDefinitions(ValidationEngine engine, byte[] defn) throws Exception {
    ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(defn));
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (!ze.getName().endsWith(".zip") && !ze.getName().endsWith(".jar") ) { // skip saxon .zip
        String name = ze.getName();
        InputStream in = zip;
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int n;
        byte[] buf = new byte[1024];
        while ((n = in.read(buf, 0, 1024)) > -1) {
          b.write(buf, 0, n);
        }        
        engine.getDefinitions().put(name, b.toByteArray());
      }
      zip.closeEntry();
    }
    zip.close();    
  }

  private byte[] loadDefinitions() throws Exception {
    byte[] defn;
    if (Utilities.noString(definitions)) {
      defn = loadFromUrl(MASTER_SOURCE);
    } else if (definitions.startsWith("https:") || definitions.startsWith("http:")) {
      defn = loadFromUrl(definitions);
    } else if (new File(definitions).exists()) {
      defn = loadFromFile(definitions);      
    } else
      throw new Exception("Unable to find FHIR validation Pack (source = "+definitions+")");
    return defn;
  }

  private byte[] loadSource() throws Exception {
    byte[] src;
    if (new File(source).exists())
      src = loadFromFile(source);
    else if (source.startsWith("https:") || source.startsWith("http:"))
      src = loadFromUrl(source);
    else 
      src = source.getBytes();
    return src;
  }

  private byte[] loadFromUrl(String src) throws Exception {
    URL url = new URL(src);
    InputStream in = url.openStream();
    byte[] b = new byte[in.available()];
    in.read(b);
    return b;
  }

  private byte[] loadFromFile(String src) throws Exception {
    FileInputStream in = new FileInputStream(src);
    byte[] b = new byte[in.available()];
    in.read(b);
    in.close();
    return b;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public String getOutcome() throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    new XmlComposer().compose(b, engine.getOutcome(), true); 
    return b.toString();
  }

  public String getDefinitions() {
    return definitions;
  }

  public void setDefinitions(String definitions) {
    this.definitions = definitions;
  }
  
  
}
