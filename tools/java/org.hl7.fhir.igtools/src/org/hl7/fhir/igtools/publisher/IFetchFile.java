package org.hl7.fhir.igtools.publisher;

import java.nio.file.attribute.FileTime;

import org.hl7.fhir.dstu3.model.Type;

public interface IFetchFile {
  
  FetchedFile fetch(String path) throws Exception;
  FetchedFile fetch(Type source, FetchedFile base) throws Exception;
}