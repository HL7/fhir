package org.hl7.fhir.r4.utils;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.utils.GraphQL.Argument;

public class GraphQLEngine {
  
  public interface GraphQLStorageServices {
    public class ReferenceResolution {
      Resource targetContext;
      Resource target;
    }
    // given a reference inside a context, return what it references (including resolving internal references (e.g. start with #)
    public ReferenceResolution lookup(Object appInfo, Resource context, Reference reference);
    
    // just get the identified resource
    public Resource lookup(Object appInfo, String type, String id);

    // list the matching resources. searchParams are the standard search params. 
    // this is different to search because the server returns all matching resources, or an error. There is no paging on this search   
    public void listResources(Object appInfo, String type, List<Argument> searchParams, List<Resource> matches);

    // just perform a standard search, and return the bundle as you return to the client  
    public Bundle search(Object appInfo, String type, List<Argument> searchParams);
  }
}
