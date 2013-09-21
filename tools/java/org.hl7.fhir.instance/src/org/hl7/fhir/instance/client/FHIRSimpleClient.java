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
package org.hl7.fhir.instance.client;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
//import org.hl7.fhir.instance.formats.AtomComposer;

/**
 * Simple RESTful client for the FHIR Resource Oriented API.
 * 
 * To use, initialize class and set base service URI as follows:
 * 
 * <pre><code>
 * FHIRSimpleClient fhirClient = new FHIRSimpleClient();
 * fhirClient.initialize("http://my.fhir.domain/myServiceRoot");
 * </code></pre>
 * 
 * Default Accept and Content-Type headers are application/fhir+xml for resources and application/atom+xml for bundles.
 * 
 * These can be changed by invoking the following setter functions:
 * 
 * <pre><code>
 * setPreferredResourceFormat()
 * setPreferredFeedFormat()
 * </code></pre>
 * 
 * TODO Review all sad paths. 
 * 
 * @author Claude Nanjo
 *
 */
public class FHIRSimpleClient implements FHIRClient {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssK";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	private ResourceAddress resourceAddress;
	private ResourceFormat preferredResourceFormat;
	private FeedFormat preferredFeedFormat;
	
	public FHIRSimpleClient() {
		preferredResourceFormat = ResourceFormat.RESOURCE_XML;
		preferredFeedFormat = FeedFormat.FEED_XML;
	}
	
	@Override
	public void initialize(String baseServiceUrl)  throws URISyntaxException {
		resourceAddress = new ResourceAddress(baseServiceUrl);
	}
	
	@Override
	public void setPreferredResourceFormat(ResourceFormat resourceFormat) {
		preferredResourceFormat = resourceFormat;
	}
	
	@Override
	public String getPreferredResourceFormat() {
		return preferredResourceFormat.getHeader();
	}
	
	@Override
	public void setPreferredFeedFormat(FeedFormat feedFormat) {
		preferredFeedFormat = feedFormat;
	}
	
	@Override
	public String getPreferredFeedFormat() {
		return preferredFeedFormat.getHeader();
	}
	
	@Override
	public Conformance getConformanceStatement() throws EFhirClientException {
		return getConformanceStatement(false);
	}
	
	@Override
	public Conformance getConformanceStatement(boolean useOptionsVerb) {
		Conformance conformance = null;
		if(useOptionsVerb) {
			conformance = (Conformance)ClientUtils.issueOptionsRequest(resourceAddress.getBaseServiceUri(), getPreferredResourceFormat()).getResource();//TODO fix this
		} else {
			conformance = (Conformance)ClientUtils.issueGetResourceRequest(resourceAddress.resolveMetadataUri(), getPreferredResourceFormat()).getResource();//TODO fix this.
		}
		return conformance;
	}

	@Override
	public <T extends Resource> AtomEntry<T> read(Class<T> resourceClass, String id) {//TODO Change this to AddressableResource
		AtomEntry<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id), getPreferredResourceFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this resource", e);
		}
		return result;
	}

	@Override
	public <T extends Resource> AtomEntry<T> vread(Class<T> resourceClass, String id, String version) {
		AtomEntry<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndIdAndVersion(resourceClass, id, version), getPreferredResourceFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result;
	}

	@Override
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id) {
		AtomEntry<T> result = null;
		try {
			result = ClientUtils.issuePutRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat());
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to update this resource", e);
		}
		return result;
	}

	@Override
	public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
		try {
			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id));
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
		}

	}

	@Override
	public <T extends Resource> AtomEntry<T> create(Class<T> resourceClass, T resource) {
		AtomEntry<T> createdEntry = null;
		try {
			createdEntry = ClientUtils.issuePostRequest(resourceAddress.resolveGetUriFromResourceClass(resourceClass),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat());
		} catch(Exception e) {
			handleException("An error has occurred while trying to create this resource", e);
		}
		return createdEntry;
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> AtomFeed history(Class<T> resourceClass, String id) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> parameters) {
		AtomFeed searchResults = null;
		try {
			searchResults = ClientUtils.issueGetFeedRequest(resourceAddress.resolveSearchUri(resourceClass, parameters), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("Error performing search with parameters " + parameters, e);
		}
		return searchResults;
	}
	
	@Override
	public AtomFeed transaction(AtomFeed batch) {
		AtomFeed transactionResult = null;
		try {
			transactionResult = ClientUtils.postBatchRequest(resourceAddress.getBaseServiceUri(), ClientUtils.getFeedAsByteArray(batch, false, isJson(getPreferredFeedFormat())), getPreferredFeedFormat());
		} catch (Exception e) {
			handleException("An error occurred trying to process this transaction request", e);
		}
		return transactionResult;
	}
	
	@Override
	public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id) {
		try {
			return ClientUtils.issuePostRequest(resourceAddress.resolveValidateUri(resourceClass, id), ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	/**
	 * Helper method to prevent nesting of previously thrown EFhirClientExceptions
	 * 
	 * @param e
	 * @throws EFhirClientException
	 */
	protected void handleException(String message, Exception e) throws EFhirClientException {
		if(e instanceof EFhirClientException) {
			throw (EFhirClientException)e;
		} else {
			throw new EFhirClientException(message, e);
		}
	}
	
	/**
	 * Helper method to determine whether desired resource representation
	 * is Json or XML.
	 * 
	 * @param format
	 * @return
	 */
	protected boolean isJson(String format) {
		boolean isJson = false;
		if(format.toLowerCase().contains("json")) {
			isJson = true;
		}
		return isJson;
	}

}
