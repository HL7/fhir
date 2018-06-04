package org.hl7.fhir.r4.validation;
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

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;

import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Constants;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.ImplementationGuide;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.r4.validation.Validator.EngineMode;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.validation.r4.tests.ValidationEngineTests;

/**
 * A executable class that will validate one or more FHIR resources against 
 * the specification
 * 
 * todo: schema validation (w3c xml, json schema, shex?)
 * 
 * if you want to host validation inside a process, skip this class, and look at 
 * ValidationEngine
 * 
 * todo: find a gome for this:

 * @author Grahame
 *
 */
public class Validator {

  public enum EngineMode {
    VALIDATION, TRANSFORM, NARRATIVE, SNAPSHOT
  }


  public static void main(String[] args) throws Exception {
     if (hasParam(args, "-tests")) {
      try {
      ValidationEngineTests.execute();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (args.length == 0 || hasParam(args, "help") || hasParam(args, "?") || hasParam(args, "-?") || hasParam(args, "/?") ) {
      System.out.println("FHIR Validation tool v"+Constants.VERSION+"-"+Constants.REVISION);
      System.out.println("");
      System.out.println("The FHIR validation tool validates a FHIR resource or bundle.");
      System.out.println("The validation tool compares a resource against the base definitions and any");
      System.out.println("profiles declared in the resource (Resource.meta.profile) or specified on the ");
      System.out.println("command line");
      System.out.println("");
      System.out.println("The FHIR validation tool validates a FHIR resource or bundle.");
      System.out.println("Schema and schematron checking is performed, then some additional checks are performed. ");
      System.out.println("* XML & Json (FHIR versions 1.0, 1.4, 3.0, 3.4)");
      System.out.println("* Turtle (FHIR versions 3.0, 3.4)");
      System.out.println("");
      System.out.println("If requested, instances will also be verified against the appropriate schema");
      System.out.println("W3C XML Schema, JSON schema or ShEx, as appropriate");
      System.out.println("");
      System.out.println("Usage: org.hl7.fhir.r4.validation.ValidationEngine (parameters)");
      System.out.println("");
      System.out.println("The following parameters are supported:");
      System.out.println("[source]: a file, url, directory or pattern for resources to validate.  At");
      System.out.println("    least one source must be declared.  If there is more than one source or if");
      System.out.println("    the source is other than a single file or url and the output parameter is");
      System.out.println("    used, results will be provided as a Bundle.");
      System.out.println("    Patterns are limited to a directory followed by a filename with an embedded");
      System.out.println("    asterisk.  E.g. foo*-examples.xml or someresource.*, etc.");
      System.out.println("-version [ver]: The FHIR version to use. This can only appear once. ");
      System.out.println("    valid values 1.0 | 1.4 | 3.0 | "+Constants.VERSION.substring(0, 3)+" or 1.0.2 | 1.4.0 | 3.0.1 | "+Constants.VERSION);
      System.out.println("    Default value is  "+Constants.VERSION.substring(0, 3));
      System.out.println("-ig [package|file|url]: an IG or profile definition to load. Can be ");
      System.out.println("     the URL of an implementation guide or a package ([id]-[ver]) for");
      System.out.println("     a built implementation guide or a local folder that contains a");
      System.out.println("     set of conformance resources.");
      System.out.println("     No default value. This parameter can appear any number of times");
      System.out.println("-tx [url]: the [base] url of a FHIR terminology service");
      System.out.println("     Default value is http://tx.fhir.org/r4. This parameter can appear once");
      System.out.println("     To run without terminology value, specific n/a as the URL");
      System.out.println("-profile [url]: the canonical URL to validate against (same as if it was ");
      System.out.println("     specified in Resource.meta.profile). If no profile is specified, the ");
      System.out.println("     resource is validated against the base specification. This parameter ");
      System.out.println("     can appear any number of times.");
      System.out.println("     Note: the profile (and it's dependencies) have to be made available ");
      System.out.println("     through one of the -ig parameters. Note that package dependencies will ");
      System.out.println("     automatically be resolved");
      System.out.println("-questionnaire [file|url}: the location of a questionnaire. If provided, then the validator will validate");
      System.out.println("     any QuestionnaireResponse that claims to match the Questionnaire against it");
      System.out.println("     no default value. This parameter can appear any number of times");
      System.out.println("-output [file]: a filename for the results (OperationOutcome)");
      System.out.println("     Default: results are sent to the std out.");
      System.out.println("-native: use schema for validation as well");
      System.out.println("     * XML: w3c schema+schematron");
      System.out.println("     * JSON: json.schema");
      System.out.println("     * RDF: SHEX");
      System.out.println("     Default: false");
      System.out.println("");
      System.out.println("Parameters can appear in any order");
      System.out.println("");
      System.out.println("Alternatively, you can use the validator to execute a transformation as described by a structure map.");
      System.out.println("To do this, you must provide some additional parameters:");
      System.out.println("");
      System.out.println(" -transform [map]");
      System.out.println("");
      System.out.println("* [map] the URI of the map that the transform starts with");
      System.out.println("");
      System.out.println("Any other dependency maps have to be loaded through an -ig reference ");
      System.out.println("");
      System.out.println("-transform uses the parameters -defn, -txserver, -ig (at least one with the map files), and -output");
      System.out.println("");
      System.out.println("Alternatively, you can use the validator to generate narrative for a resource.");
      System.out.println("To do this, you must provide a specific parameter:");
      System.out.println("");
      System.out.println(" -narrative");
      System.out.println("");
      System.out.println("-narrative requires the parameters -defn, -txserver, -source, and -output. ig and profile may be used");
      System.out.println("");
      System.out.println("Finally, you can use the validator to generate a snapshot for a profile.");
      System.out.println("To do this, you must provide a specific parameter:");
      System.out.println("");
      System.out.println(" -snapshot");
      System.out.println("");
      System.out.println("-snapshot requires the parameters -defn, -txserver, -source, and -output. ig may be used to provide necessary base profiles");
    } else { 
      String definitions = "hl7.fhir.core-"+Constants.VERSION;
      String map = null;
      List<String> igs = new ArrayList<String>();
      List<String> questionnaires = new ArrayList<String>();
      String txServer = "http://tx.fhir.org/r4";
      boolean doNative = false;
      List<String> profiles = new ArrayList<String>();
      EngineMode mode = EngineMode.VALIDATION;
      String output = null;
      List<String> sources= new ArrayList<String>();
      Map<String, String> locations = new HashMap<String, String>();

        // load the parameters - so order doesn't matter
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-defn"))
          if (i+1 == args.length)
            throw new Error("Specified -defn without indicating definition file");
          else
            definitions = args[++i];
        else if (args[i].equals("-version"))  {
          String v = args[++i];
          if ("1.0".equals(v)) v = "1.0.2";
          if ("1.4".equals(v)) v = "1.4.0";
          if ("3.0".equals(v)) v = "3.0.1";
          if (v.startsWith(Constants.VERSION)) v = Constants.VERSION;
          definitions = "hl7.fhir.core-"+v;
        } else if (args[i].equals("-output"))
          if (i+1 == args.length)
            throw new Error("Specified -output without indicating output file");
          else
            output = args[++i];
        else if (args[i].equals("-profile")) {
          String p = null;
          if (i+1 == args.length)
            throw new Error("Specified -profile without indicating profile source");
          else {
            p = args[++i];
            profiles.add(p);
          }
          if (p != null && i+1 < args.length && args[i+1].equals("@")) {
            i++;
            if (i+1 == args.length)
              throw new Error("Specified -profile with @ without indicating profile location");
            else 
              locations.put(p, args[++i]);
          }
        } else if (args[i].equals("-questionnaire"))
          if (i+1 == args.length)
            throw new Error("Specified -questionnaire without indicating questionnaire file");
          else
            questionnaires.add(args[++i]);
        else if (args[i].equals("-native"))
            doNative = true;
        else if (args[i].equals("-transform")) {
          map = args[++i];
          mode = EngineMode.TRANSFORM;
        } else if (args[i].equals("-narrative"))
          mode = EngineMode.NARRATIVE;
        else if (args[i].equals("-snapshot"))
          mode = EngineMode.SNAPSHOT;
        else if (args[i].equals("-tx"))
          if (i+1 == args.length)
            throw new Error("Specified -tx without indicating terminology server");
          else
            txServer = "n/a".equals(args[++i]) ? null : args[i];
        else if (args[i].equals("-ig"))
          if (i+1 == args.length)
            throw new Error("Specified -ig without indicating ig file");
          else {
            String s = args[++i];
            if (s.startsWith("hl7.fhir.core-"))
              definitions = s;
            else
              igs.add(s);
          }
        else if (args[i].equals("-map"))
          if (map == null)
            if (i+1 == args.length)
              throw new Error("Specified -map without indicating map file");
            else
              map = args[++i];
          else
            throw new Exception("Can only nominate a single -map parameter");
        else if (args[i].startsWith("-x"))
          i++;
        else
          sources.add(args[i]);
        }
      if  (sources.isEmpty())
        throw new Exception("Must provide at least one source file");
        
      System.out.println("  .. FHIR Version "+definitions.substring(14));
      System.out.println("  .. connect to tx server @ "+txServer);
      ValidationEngine validator = new ValidationEngine(definitions, txServer);
      System.out.println("    (v"+validator.getContext().getVersion()+")");
      for (String src : igs) {
        System.out.println("+  .. load IG from "+src);
          validator.loadIg(src);
      }
      validator.setQuestionnaires(questionnaires);
      validator.setNative(doNative);

      XmlParser x = new XmlParser();
      if (mode == EngineMode.TRANSFORM) {
        if  (sources.size() > 1)
          throw new Exception("Can only have one source when doing a transform (found "+sources+")");
        if  (txServer == null)
          throw new Exception("Must provide a terminology server when doing a transform");
        if  (map == null)
          throw new Exception("Must provide a map when doing a transform");
        try {
          Resource r = validator.transform(sources.get(0), map);
          System.out.println(" ...success");
          if (output != null) {
            FileOutputStream s = new FileOutputStream(output);
            x.setOutputStyle(OutputStyle.PRETTY);
            x.compose(s, r, true);
            s.close();
          }
        } catch (Exception e) {
          System.out.println(" ...Failure: "+e.getMessage());
        }
      } else if (mode == EngineMode.NARRATIVE) {
        DomainResource r = validator.generate(sources.get(0));
        System.out.println(" ...generated narrative successfully");
        if (output != null) {
          handleOutput(r, output);
        }
      } else if (mode == EngineMode.SNAPSHOT) {
        if  (definitions == null)
          throw new Exception("Must provide a defn when generating a snapshot");
        StructureDefinition r = validator.snapshot(sources.get(0));
        System.out.println(" ...generated snapshot successfully");
        if (output != null) {
          handleOutput(r, output);
        }
      } else {
        if  (definitions == null)
          throw new Exception("Must provide a defn when doing validation");
        for (String s : profiles) {
          if (!validator.getContext().hasResource(StructureDefinition.class, s) && !validator.getContext().hasResource(ImplementationGuide.class, s)) {
            System.out.println("Fetch Profile from "+s);
            validator.loadProfile(locations.getOrDefault(s, s));
          }
        }
        if (profiles.size() > 0)
          System.out.println("  .. validate "+sources+" against "+profiles.toString());
        else
          System.out.println("  .. validate "+sources);
        Resource r = validator.validate(sources, profiles);
        if (output == null) {
          if (r instanceof Bundle)
            for (BundleEntryComponent e : ((Bundle)r).getEntry())
              displayOO((OperationOutcome)e.getResource());
          else
            displayOO((OperationOutcome)r);
        } else {
          FileOutputStream s = new FileOutputStream(output);
          x.compose(s, r, true);
          s.close();
        }
      }
    }
  }

  private static void handleOutput(Resource r, String output) throws IOException {
    if (output.startsWith("http://") || output.startsWith("http://")) {
      ByteArrayOutputStream bs = new ByteArrayOutputStream();
      handleOutputToStream(r, output, bs);
      URL url = new URL(output);
      HttpURLConnection c = (HttpURLConnection) url.openConnection();
      c.setDoOutput(true);
      c.setDoInput(true);
      c.setRequestMethod("POST");
      c.setRequestProperty( "Content-type", "application/fhir+xml");
      c.setRequestProperty( "Accept", "application/fhir+xml" );
      c.getOutputStream().write(bs.toByteArray());
      c.getOutputStream().close();

      if (c.getResponseCode() >= 300) {
//        String line;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
//        while ((line = reader.readLine()) != null) {
//          System.out.println(line);
//        }
//        reader.close();
        throw new IOException("Unable to PUT to "+output+": "+c.getResponseMessage());
      }
    } else {
      FileOutputStream s = new FileOutputStream(output);
      handleOutputToStream(r, output, s);
    }
  }

  private static void handleOutputToStream(Resource r, String output, OutputStream s) throws IOException {
    if (output.endsWith(".html") || output.endsWith(".htm") && r instanceof DomainResource)
      new XhtmlComposer(XhtmlComposer.HTML, true).compose(s, ((DomainResource) r).getText().getDiv());
    else
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, r);
    s.close();
  }

  private static void displayOO(OperationOutcome oo) {
    int error = 0;
    int warn = 0;
    int info = 0;
    String file = ToolingExtensions.readStringExtension(oo, ToolingExtensions.EXT_OO_FILE);

    for (OperationOutcomeIssueComponent issue : oo.getIssue()) {
      if (issue.getSeverity()==OperationOutcome.IssueSeverity.FATAL || issue.getSeverity()==OperationOutcome.IssueSeverity.ERROR)
        error++;
      else if (issue.getSeverity()==OperationOutcome.IssueSeverity.WARNING)
        warn++;
      else
        info++;
    }
    
    System.out.println((error==0?"Success...":"*FAILURE* ")+ "validating "+file+": "+" error:"+Integer.toString(error)+" warn:"+Integer.toString(warn)+" info:"+Integer.toString(info));
    for (OperationOutcomeIssueComponent issue : oo.getIssue()) {
      System.out.println(getIssueSummary(issue));
    }
    System.out.println();
  }

  private static String getIssueSummary(OperationOutcomeIssueComponent issue) {
    return "  " + issue.getSeverity().getDisplay() + " @ " + issue.getLocation().get(0).asStringValue() + " : " + issue.getDetails().getText();
  }
 

  private static boolean hasParam(String[] args, String param) {
    for (String a : args)
      if (a.equals(param))
        return true;
    return false;
  }


	private static boolean hasTransformParam(String[] args) {
		for (String s : args) {
			if (s.equals("-transform"))
				return true;
		}
		return false;
	}
}