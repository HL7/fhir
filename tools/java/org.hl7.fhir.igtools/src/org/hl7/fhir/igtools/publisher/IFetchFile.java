package org.hl7.fhir.igtools.publisher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r5.model.Type;

public interface IFetchFile {
  
  public enum FetchState { NOT_FOUND, DIR, FILE }
  FetchState check(String path);
  String pathForFile(String path);
  
  FetchedFile fetch(String path) throws Exception;
  FetchedFile fetchFlexible(String path) throws Exception;
  boolean canFetchFlexible(String path) throws Exception;
  FetchedFile fetch(Type source, FetchedFile base) throws Exception;
  FetchedFile fetchResourceFile(String name) throws Exception; 
  void setPkp(IGKnowledgeProvider pkp);
  List<FetchedFile> scan(String sourceDir, IWorkerContext context, boolean autoScan) throws IOException, FHIRException;

  public ILoggingService getLogger();
  public void setLogger(ILoggingService log);
  void setResourceDirs(List<String> theResourceDirs);
  InputStream openAsStream(String filename) throws FileNotFoundException;
  String openAsString(String path) throws FileNotFoundException, IOException;
}