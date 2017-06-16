package org.hl7.fhir.igweb.builder;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.*;
import java.util.Date;

import javax.servlet.ServletOutputStream;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.igtools.publisher.*;
import org.hl7.fhir.igweb.Slf4jLogger;

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

  public Job(String theJobId, byte[] theInput, Publisher thePublisher) {
    myJobId = theJobId;
    myInput = theInput;
    myCreated = new Date();
    myPublisher = thePublisher;
  }

  public void destroy() {
    BuilderService.ourLog.info("Expiring job: {}", myJobId);
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

  @Override
  public void run() {
    ourLog.info("Beginning conversion job: {}", myJobId);

    try {
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
      myPublisher.setConfigFileRootPath(def.getPath().substring(0, def.getPath().lastIndexOf('/')+1));

      File tempFile = File.createTempFile("igweb", "igweb");
      tempFile.delete();
      tempFile.mkdirs();
      myPublisher.setTempDir(tempFile.getAbsolutePath());

      myPublisher.initialize();

      // myPublisher.setIgName(igFile.get("source").getAsString());

      myPublisher.createIg();
      
      File createdFile = new File(tempFile, "full-ig.zip");
      if (!createdFile.exists()) {
        throw new Exception("Failed to create IG Zip");
      }
      
      createdFile.deleteOnExit();
      
      myCreatedFile = createdFile;
      myFinished = true;
    } catch (Exception e) {
      ourLog.error("Failure during conversion", e);
      myFailureMessage = e.toString();
    }
  }

  public void setLatestVersion(String theLatestVersion) {
    myLatestVersion = theLatestVersion;
  }

  public void streamOutputTo(ServletOutputStream theOutputStream) throws IOException {
    IOUtils.copy(new FileInputStream(myCreatedFile), theOutputStream);
  }

}