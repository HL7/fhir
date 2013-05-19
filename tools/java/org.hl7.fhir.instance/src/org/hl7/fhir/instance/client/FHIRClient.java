package org.hl7.fhir.instance.client;

/*
Copyright (c) 2011-2013, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/

import java.util.Calendar;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.ResourceType;

/**
 * Have one interface for each particular server
 * 
 * @author Grahame
 *
 */
public interface FHIRClient {

	// Get a conformance statement for the system
	public Conformance getConformanceStatement() throws EFhirClientException;
	
	// Read the current state of a resource
	public AtomEntry read(ResourceType type, String id) throws EFhirClientException;
	
	// Read the state of a specific version of the resource
	public AtomEntry vread(ResourceType type, String id, String versionid) throws EFhirClientException; 
	
    // Update an existing resource by its id (or create it if it is new)
	public AtomEntry update(String id, AtomEntry resource) throws EFhirClientException;
	
	// Delete a resource
	public void delete(ResourceType type, String id) throws EFhirClientException; 

	// Create a new resource with a server assigned id. return the id the server assigned
	public String create(AtomEntry resource) throws EFhirClientException;
	
	// Retrieve the update history for a resource, for a resource type, for all resources. LastUpdate can be null for all of these
	public AtomFeed history(Calendar lastUpdate, ResourceType type, String id) throws EFhirClientException;
	public AtomFeed history(Calendar lastUpdate, ResourceType type) throws EFhirClientException;
	public AtomFeed history(Calendar lastUpdate) throws EFhirClientException;
	
	// Search the resource type based on some filter criteria
	public AtomFeed search(ResourceType type, Map<String, String> params) throws EFhirClientException;
	
	// 	Update or create a set of resources
	public AtomFeed batch(AtomFeed batch) throws EFhirClientException;
	
}
