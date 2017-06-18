package org.hl7.fhir.igweb;

import org.hl7.fhir.r4.context.IWorkerContext.ILoggingService;

public class Slf4jLogger implements ILoggingService {
  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(Slf4jLogger.class);

  @Override
  public void logMessage(String theMessage) {
    ourLog.info(theMessage);
  }

  @Override
  public void logDebugMessage(LogCategory theCategory, String theMessage) {
    ourLog.debug(theMessage);
  }

}
