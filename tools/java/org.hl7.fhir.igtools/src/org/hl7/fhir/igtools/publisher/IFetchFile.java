package org.hl7.fhir.igtools.publisher;

import java.util.List;

import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.model.Type;

public interface IFetchFile {
  
  FetchedFile fetch(String path) throws Exception;
  FetchedFile fetchFlexible(String path) throws Exception;
  boolean canFetchFlexible(String path) throws Exception;
  FetchedFile fetch(Type source, FetchedFile base) throws Exception;
  void setPkp(IGKnowledgeProvider pkp);
  List<FetchedFile> scan(String sourceDir, IWorkerContext context);
}