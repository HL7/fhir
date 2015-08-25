package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.ex.NotFoundException;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.api.IBaseResource;

/**
 * Implementations of this interface are able to retrieve resource data from a local or remote source for the purposes of running FHIR tooling. For instance a
 * validator might use this interface to load {@link StructureDefinition structure definitions} or {@link ValueSet ValueSets}.
 * <p>
 * An implementation of this interface might fetch resources from a remote server, from a local file on disk, or from a pre-loaded cache.
 * </p>
 * 
 * @author James Agnew
 */
public interface IResourceLoader {

  /**
   * Performs a ValueSet expansion and returns the expanded ValueSet
   * 
   * @param theInput
   *          The ValueSet to expand
   * @return Returns the expanded ValueSet
   */
  public ValueSet expandValueSet(ValueSet theInput);

  /**
   * Read the current state of a resource. If the ID contains no version, this method will perform a "read" operation. If the ID contains a version, the method
   * will perform a "vread" operation.
   * 
   * @param theResourceType
   *          The type of resource to load
   * @param theId
   *          The ID of the resource to load
   * @return A loaded resource
   * @throws NotFoundException
   *           If the resource is not found in the location(s) being searched by this loader
   */
  public <T extends IBaseResource> T fetch(Class<T> theResourceType, IdType theId) throws NotFoundException;

  /**
   * Validates that a given code is valid, possibly within a given ValueSet or for a given context
   * 
   * @param theValueSetId
   *          The ValueSet to validate the given code against, or <code>null</code>
   * @param theCodeSystem
   *          The code system of the code to validate (must not be null or empty)
   * @param theCode
   *          The code to validate (must not be null or empty)
   * @return Returns true if the code is valid
   */
  public boolean validateCode(UriType theValueSetId, String theCodeSystem, String theCode);

}
