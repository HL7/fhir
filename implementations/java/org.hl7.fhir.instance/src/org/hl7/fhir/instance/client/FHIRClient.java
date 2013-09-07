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

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;

/**
 * Have one interface for each particular server
 * 
 * @author Grahame
 *
 */
public interface FHIRClient {

	public void initialize(String baseServiceUrl)  throws URISyntaxException;
	
	public void setPreferredResourceFormat(ResourceFormat resourceFormat);
	
	public String getPreferredResourceFormat();
	
	public void setPreferredFeedFormat(FeedFormat feedFormat);
	
	public String getPreferredFeedFormat();
	
	// Get a conformance statement for the system
	public Conformance getConformanceStatement();
	
	// Get a conformance statement for the system
	public Conformance getConformanceStatement(boolean useOptionsVerb);
	
	// Read the current state of a resource
	//public <T extends Resource> T read(Class<T> resource, String id);
	
	// Read the current state of a resource
	public <T extends Resource> AtomEntry<T> read(Class<T> resource, String id);
	
	// Read the state of a specific version of the resource
	//public <T extends Resource> T  vread(Class<T> resource, String id, String versionid);
	
	// Read the state of a specific version of the resource
	public <T extends Resource> AtomEntry<T> vread(Class<T> resource, String id, String versionid);
	
    // Update an existing resource by its id (or create it if it is new)
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id);
	
	public <T extends Resource> boolean delete(Class<T> resourceClass, String id); 

	// Create a new resource with a server assigned id. return the id the server assigned
	public <T extends Resource> AtomEntry<T> create(Class<T> resourceClass, T resource);
	
	// Retrieve the update history for a resource, for a resource type, for all resources. LastUpdate can be null for all of these
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id);
	public <T extends Resource> AtomFeed history(Class<T> resource, String id);
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass);
	public <T extends Resource> AtomFeed history(Calendar lastUpdate);
	
	// Validates resource payload
	public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id);
	
	public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> params);
	
	// 	Update or create a set of resources
	public AtomFeed batch(AtomFeed batch);
	
}
