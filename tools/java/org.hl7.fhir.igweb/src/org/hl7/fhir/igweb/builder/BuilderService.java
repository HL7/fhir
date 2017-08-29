package org.hl7.fhir.igweb.builder;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.lang3.time.DateUtils;
import org.hl7.fhir.igtools.publisher.Publisher;
import org.hl7.fhir.igtools.publisher.SpecificationPackage;
import org.hl7.fhir.igtools.publisher.Publisher.IGBuildMode;
import org.hl7.fhir.igweb.Slf4jLogger;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.utils.client.FHIRToolingClient;

public class BuilderService {
  /** Singleton instance */
  public static final BuilderService INSTANCE = new BuilderService();

  static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(BuilderService.class);

  private String myJekyllCommand;
  private String logPath;
  private ConcurrentHashMap<String, Job> myJobs;
  private ScheduledExecutorService myScheduler;
  private TreeMap<String, SpecificationPackage> mySpecifications;
  private ExecutorService myWorkerThreadPool;
  private int counter;
  private FHIRToolingClient txServer;

  private String sessionId;
  
  /**
   * Constructor
   */
  private BuilderService() {
    myWorkerThreadPool = Executors.newCachedThreadPool();
    myScheduler = Executors.newSingleThreadScheduledExecutor();
    myJobs = new ConcurrentHashMap<>();
    sessionId = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
    
    try {
      txServer = new FHIRToolingClient("http://tx.fhir.org/r3");
      txServer.setTimeout(30000);
    } catch (URISyntaxException e) {
    }

    myScheduler.scheduleAtFixedRate(new Cleanup(), 0L, 1, TimeUnit.MINUTES);
  }

  public Job getJob(String theJobId) {
    return myJobs.get(theJobId);
  }

  public void setJekyllCommand(String theJekyllCommand) {
    myJekyllCommand = theJekyllCommand;
  }

  public void setSpecifications(TreeMap<String, SpecificationPackage> theSpecifications) {
    mySpecifications = theSpecifications;
  }
  
  public String getLogPath() {
    return logPath;
  }

  public void setLogPath(String logPath) {
    this.logPath = logPath;
  }

  public String submit(byte[] theInput) {
    String workId = Integer.toString(counter++);// UUID.randomUUID().toString();

    Publisher pub = new Publisher();
    pub.setMode(IGBuildMode.WEBSERVER);
    pub.setSpecifications(mySpecifications);
    pub.setLogger(new Slf4jLogger());
    pub.setJekyllCommand(myJekyllCommand);
    pub.setWebTxServer(txServer);
    
    Job job = new Job(workId, theInput, pub, sessionId);
    job.setLatestVersion(mySpecifications.lastKey());
    job.setLogPath(logPath);
    myJobs.put(workId, job);

    myWorkerThreadPool.submit(job);
    
    return workId;
  }

  /**
   * Scheduled task that goes through and deleted old jobs
   */
  public class Cleanup implements Runnable {

    @Override
    public void run() {
      Date cutoff = DateUtils.addHours(new Date(), -1);
      for (String nextId : myJobs.keySet()) {
        Job nextJob = myJobs.get(nextId);
        if (nextJob == null) {
          continue;
        }
        if (nextJob.getCreated().before(cutoff)) {
          nextJob.destroy();
          myJobs.remove(nextId);
        }
      }
    }

  }

}
