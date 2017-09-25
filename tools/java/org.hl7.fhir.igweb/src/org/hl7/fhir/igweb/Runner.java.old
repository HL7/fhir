package org.hl7.fhir.igweb;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.*;
import java.util.*;

import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.convertors.R3ToR4Loader;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.Publisher;
import org.hl7.fhir.igtools.publisher.SpecificationPackage;
import org.hl7.fhir.igweb.builder.BuilderService;
import org.hl7.fhir.igweb.builder.Job;
import org.hl7.fhir.r4.context.IWorkerContext.ILoggingService;

import com.google.gson.*;

public class Runner {
  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(Runner.class);

  public static void main(String[] theArgs) throws Exception {
    Options options = new Options();
    options.addOption(new Option("c", "config", true, "The path to the IGWeb config"));
    options.addOption(new Option("l", "log", true, "Where to log submissions and responses to"));
    options.addOption(new Option("j", "jekyll", true, "The path to the Jekyll command (optional)"));
    
    CommandLine commandLine = new DefaultParser().parse(options , theArgs);
    
    String jekyll = "jekyll";
    if (commandLine.hasOption("j")) {
      jekyll = commandLine.getOptionValue("j");
    }
    BuilderService.INSTANCE.setJekyllCommand(jekyll);
    
    String logPath = commandLine.getOptionValue("log");
    if (isBlank(logPath)) {
      throw new ParseException("Missing required argument -log");
    }
    File lp = new File(logPath);
    if (!lp.exists() || !lp.isDirectory())
      throw new ParseException("Argument -log - folder '"+logPath+"' not found");
    BuilderService.INSTANCE.setLogPath(logPath);
    
    String configPath = commandLine.getOptionValue("config");
    if (isBlank(configPath)) {
      throw new ParseException("Missing required argument -config");
    }
    
    FileReader configReader = new FileReader(configPath);
    String configString = IOUtils.toString(configReader);
    Gson gson = new GsonBuilder().create();
    JsonObject configObject = gson.fromJson(configString, JsonObject.class);

    /*
     * Load specifications (igpack.zip files)
     */
    TreeMap<String, SpecificationPackage> specifications = new TreeMap<>();
    addVersionIfPresent(configObject, "3.0", specifications);
    addVersionIfPresent(configObject, "3.1", specifications);
    if (specifications.isEmpty()) {
      throw new ParseException("No specifications specified");
    }
    BuilderService.INSTANCE.setSpecifications(specifications);
    
    IgWebServer server = new IgWebServer();
    
    JsonPrimitive portObject = configObject.getAsJsonPrimitive("port");
    if (portObject == null) {
      throw new ParseException("Config does not contain a port");
    }
    int port = Integer.parseInt(portObject.getAsString());
    server.setPort(port);
    
    server.start();

  }

  private static void addVersionIfPresent(JsonObject configObject, String version, Map<String, SpecificationPackage> specifications) throws FileNotFoundException, IOException, FHIRException {
    JsonPrimitive specObj = configObject.getAsJsonPrimitive("spec-" + version);
    if (specObj != null) {
      String path = specObj.getAsString();
      ourLog.info("Loading IGPack: {}", path);
      SpecificationPackage pack;
      if (ourPathToSpec.containsKey(path)) {
        pack = ourPathToSpec.get(path);  
      } else {
        if ("3.0".equals(version))
          pack = SpecificationPackage.fromPath(path, new R3ToR4Loader());
        else
          pack = SpecificationPackage.fromPath(path);
        ourPathToSpec.put(path, pack);
      }
      specifications.put(version, pack);
    }
  }
  
  private static Map<String, SpecificationPackage> ourPathToSpec = new HashMap<>();
}
