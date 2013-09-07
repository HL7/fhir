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

import java.io.ByteArrayOutputStream;
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
 * FHIRSimpleClient fhirClient = new FHIRSimpleClient();
 * fhirClient.initialize("http://my.fhir.domain/myServiceRoot");
 * 
 * Default Accept and Content-Type headers are application/fhir+xml for resources and application/atom+xml for bundles.
 * 
 * These can be changed by invoking the following setter functions:
 * 
 * setPreferredResourceFormat()
 * setPreferredFeedFormat()
 * 
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
	
	public void initialize(String baseServiceUrl)  throws URISyntaxException {
		resourceAddress = new ResourceAddress(baseServiceUrl);
	}
	
	public void setPreferredResourceFormat(ResourceFormat resourceFormat) {
		preferredResourceFormat = resourceFormat;
	}
	
	public String getPreferredResourceFormat() {
		return preferredResourceFormat.getHeader();
	}
	
	public void setPreferredFeedFormat(FeedFormat feedFormat) {
		preferredFeedFormat = feedFormat;
	}
	
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
		try {
			return ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id), getPreferredResourceFormat());
		} catch (Exception e) {
			throw new EFhirClientException("An error has occurred while trying to read this resource", e);
		}
	}

	@Override
	public <T extends Resource> AtomEntry<T> vread(Class<T> resourceClass, String id, String version) {
		try {
			return ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndIdAndVersion(resourceClass, id, version), getPreferredResourceFormat());
		} catch (Exception e) {
			throw new EFhirClientException("An error has occurred while trying to read this version of the resource", e);
		}
	}

	@Override
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id) {
		try {
			return ClientUtils.issuePutRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id),ClientUtils.getResourceAsByteArray(resource, false, false), getPreferredResourceFormat());
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to update this resource", e);
		}
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
		try {
			return ClientUtils.issuePostRequest(resourceAddress.resolveGetUriFromResourceClass(resourceClass),ClientUtils.getResourceAsByteArray(resource, false, false), getPreferredResourceFormat());
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to create this resource", e);
		}
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id) {
		try {
			return ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException("An error has occurred while trying to retrieve history information for this resource", e);
		}
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass)
			throws EFhirClientException {
		try {
			return ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}
	
	@Override
	public <T extends Resource> AtomFeed history(Class<T> resourceClass, String id) {
		try {
			return ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate) {
		try {
			return ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	@Override
	public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> parameters) {
		try {
			return ClientUtils.issueGetFeedRequest(resourceAddress.resolveSearchUri(resourceClass, parameters), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	@Override
	public AtomFeed batch(AtomFeed batch) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			AtomComposer composer = new AtomComposer();
//			composer.compose(baos, batch, false);
			return ClientUtils.postBatchRequest(resourceAddress.getBaseServiceUri(), baos.toByteArray(), getPreferredFeedFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}
	
	@Override
	public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id) {
		try {
			return ClientUtils.issuePostRequest(resourceAddress.resolveValidateUri(resourceClass, id), ClientUtils.getResourceAsByteArray(resource, false, false), getPreferredResourceFormat());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

}
