package org.hl7.fhir.igweb.builder;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.*;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.igtools.publisher.*;
import org.hl7.fhir.igweb.Slf4jLogger;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.*;

public class Job implements Runnable {

  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(Job.class);
  private Date myCreated;
  private String myFailureMessage;
  private boolean myFinished;
  private byte[] myInput;
  private String myJobId;
  private Publisher myPublisher;
  private String myLatestVersion;
  private File myCreatedFile;
  private String logPath;
  private String mySessionId;

  public Job(String theJobId, byte[] theInput, Publisher thePublisher, String sessionId) {
    myJobId = theJobId;
    myInput = theInput;
    myCreated = new Date();
    myPublisher = thePublisher;
    mySessionId = sessionId;
  }

  public void destroy() {
    ourLog.info("Expiring job: {}", myJobId);
  }

  public Date getCreated() {
    return myCreated;
  }

  public String getFailureMessage() {
    return myFailureMessage;
  }

  public boolean isFinished() {
    return myFinished;
  }

  public String getLogPath() {
    return logPath;
  }

  public void setLogPath(String logPath) {
    this.logPath = logPath;
  }

  @Override
  public void run() {
    ourLog.info("Beginning conversion job {} (input file has {} bytes)", myJobId, myInput.length);

    try {
      saveToFile("in", myInput);
      
      ZipFetcher fetcher = new ZipFetcher(myInput);

      FetchedFile def = fetcher.findDefinitionFile();
      JsonObject igFile;

      for (int redirectCount = 0;; redirectCount++) {
        igFile = new GsonBuilder().create().fromJson(new InputStreamReader(new ByteArrayInputStream(def.getSource()), Charsets.UTF_8), JsonObject.class);
        if (igFile.has("redirect")) { // redirect to support auto-build for complex projects with IG folder in subdirectory
          String redirectFile = igFile.get("redirect").getAsString();
          ourLog.info("Redirecting to Configuration from " + redirectFile);
          def = fetcher.fetch(redirectFile);
        } else {
          break;
        }
        if (redirectCount > 100) {
          // Just in case
          throw new Exception("Too many redirections");
        }
      }

      JsonElement versionJsonElement = igFile.get("version");
      String originalFhirVersion = null;
      if (versionJsonElement != null) {
        originalFhirVersion = versionJsonElement.getAsString();
      }
      
      if (isBlank(originalFhirVersion)) {
        originalFhirVersion = myLatestVersion + ".0";
      }
      String fhirVersion = originalFhirVersion;
      fhirVersion = fhirVersion.substring(0, fhirVersion.lastIndexOf('.'));
      SpecificationPackage spec = myPublisher.getSpecifications().get(fhirVersion);
      if (spec == null) {
        throw new Exception("Don't know how to handle version: " + originalFhirVersion);
      }

      myPublisher.setLogger(new Slf4jLogger());
      myPublisher.setContext(spec.makeContext());
      myPublisher.setFetcher(fetcher);
      myPublisher.setConfiguration(igFile);
      myPublisher.setSpecPath("/");
      String configFileRootPath = def.getPath().substring(0, def.getPath().lastIndexOf('/')+1);
		ourLog.info("Config file root path: {}", configFileRootPath);
      myPublisher.setConfigFileRootPath(configFileRootPath);

      File tempFile = File.createTempFile("igweb", "temp");
      tempFile.delete();
      tempFile.mkdirs();
      myPublisher.setTempDir(tempFile.getAbsolutePath());
      File outFile = File.createTempFile("igweb", "out");
      outFile.delete();
      outFile.mkdirs();
      myPublisher.setOutputDir(outFile.getAbsolutePath());

      myPublisher.initialize();

      // myPublisher.setIgName(igFile.get("source").getAsString());

      myPublisher.createIg();
      
      File createdFile = new File(tempFile, "full-ig.zip");
      if (!createdFile.exists()) {
        throw new Exception("Failed to create IG Zip");
      }
      saveToFile("out", createdFile);
      
      createdFile.deleteOnExit();
      
      myCreatedFile = createdFile;
      myFinished = true;
//      Utilities.clearDirectory(tempFile.getAbsolutePath());
//      Utilities.clearDirectory(outFile.getAbsolutePath());
    } catch (Throwable e) {
      ourLog.error("Failure during conversion", e);
      myFailureMessage = e.toString();
    }
  }

  private void saveToFile(String op, File file) throws FileNotFoundException, IOException {
    byte[] cnt = TextFile.fileToBytes(file.getAbsolutePath());
    saveToFile(op, cnt);
  }

  private void saveToFile(String op, byte[] cnt) throws IOException {
    String filename = Utilities.path(logPath, "cnt"+mySessionId+"-"+myJobId+"-"+op+".zip");
	 ourLog.info("Saving file: {}", filename);
    TextFile.bytesToFile(cnt, filename);    
  }

  public void setLatestVersion(String theLatestVersion) {
    myLatestVersion = theLatestVersion;
  }

  public void streamOutputTo(ServletOutputStream theOutputStream) throws IOException {
    IOUtils.copy(new FileInputStream(myCreatedFile), theOutputStream);
  }

}
