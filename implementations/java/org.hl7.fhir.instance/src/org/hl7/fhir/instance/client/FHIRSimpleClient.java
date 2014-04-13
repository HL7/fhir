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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Constants;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
//import org.hl7.fhir.instance.formats.AtomComposer;
import org.hl7.fhir.instance.utils.Version;

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
	private HttpHost proxy;
	
	public FHIRSimpleClient() {
		preferredResourceFormat = ResourceFormat.RESOURCE_XML;
		preferredFeedFormat = FeedFormat.FEED_XML;
	}
	
	public void configureProxy(String proxyHost, int proxyPort) {
		proxy = new HttpHost(proxyHost, proxyPort);
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
			conformance = (Conformance)ClientUtils.issueOptionsRequest(resourceAddress.getBaseServiceUri(), getPreferredResourceFormat(), proxy).getResource();//TODO fix this
		} else {
			conformance = (Conformance)ClientUtils.issueGetResourceRequest(resourceAddress.resolveMetadataUri(), getPreferredResourceFormat(), proxy).getResource();
		}
		return conformance;
	}

	@Override
	public <T extends Resource> AtomEntry<T> read(Class<T> resourceClass, String id) {//TODO Change this to AddressableResource
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addSuccessStatus(200);//Only one for now
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload().getResource());
			}
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this resource", e);
		}
		return result.getPayload();
	}

	@Override
	public <T extends Resource> AtomEntry<T> vread(Class<T> resourceClass, String id, String version) {
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndIdAndVersion(resourceClass, id, version), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addErrorStatus(405);//unknown
			result.addSuccessStatus(200);//Only one for now
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload().getResource());
			}
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getPayload();
	}
	
	@Override
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id) {
		return update(resourceClass, resource, id, null);
	}

	@Override
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id, List<AtomCategory> tags) {
		ResourceRequest<T> result = null;
		try {
			List<Header> headers = null;
			if(tags != null && tags.size() > 0) {
				headers = buildCategoryHeaders(tags);
			}
			result = ClientUtils.issuePutRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), headers, proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addErrorStatus(405);
			result.addErrorStatus(422);//Unprocessable Entity
			result.addSuccessStatus(200);
			result.addSuccessStatus(201);
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload().getResource());
			}
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to update this resource", e);
		}
		return result.getPayload();
	}

	@Override
	public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
		try {
			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id), proxy);
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
		}

	}

	@Override
	public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource) {
		return create(resourceClass, resource, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource, List<AtomCategory> tags) {
		ResourceRequest<T> resourceRequest = null;
		try {
			List<Header> headers = null;
			if(tags != null && tags.size() > 0) {
				headers = buildCategoryHeaders(tags);
			}
			resourceRequest = ClientUtils.issuePostRequest(resourceAddress.resolveGetUriFromResourceClass(resourceClass),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), headers, proxy);
			resourceRequest.addSuccessStatus(201);
			if(resourceRequest.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + resourceRequest.getHttpStatus(), (OperationOutcome)resourceRequest.getPayload().getResource());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to create this resource", e);
		}
		return (AtomEntry<OperationOutcome>)resourceRequest.getPayload();
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass, String id) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> AtomFeed history(Class<T> resourceClass, String id) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(DateAndTime lastUpdate) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history(Calendar lastUpdate) {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed history() {
		AtomFeed history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> parameters) {
		AtomFeed searchResults = null;
		try {
			searchResults = ClientUtils.issueGetFeedRequest(resourceAddress.resolveSearchUri(resourceClass, parameters), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("Error performing search with parameters " + parameters, e);
		}
		return searchResults;
	}
	
  @Override
  public <T extends Resource> AtomFeed searchPost(Class<T> resourceClass, T resource, Map<String, String> parameters) {
    AtomFeed searchResults = null;
    try {
      searchResults = ClientUtils.issuePostFeedRequest(resourceAddress.resolveSearchUri(resourceClass, new HashMap<String, String>()), parameters, "src", resource, getPreferredFeedFormat());
    } catch (Exception e) {
      handleException("Error performing search with parameters " + parameters, e);
    }
    return searchResults;
  }
	
	@Override
	public AtomFeed transaction(AtomFeed batch) {
		AtomFeed transactionResult = null;
		try {
			transactionResult = ClientUtils.postBatchRequest(resourceAddress.getBaseServiceUri(), ClientUtils.getFeedAsByteArray(batch, false, isJson(getPreferredFeedFormat())), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error occurred trying to process this transaction request", e);
		}
		return transactionResult;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id) {
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issuePostRequest(resourceAddress.resolveValidateUri(resourceClass, id), ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(400);//gone
			result.addErrorStatus(422);//Unprocessable Entity
			result.addSuccessStatus(200);//OK
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload().getResource());
			}
		} catch(Exception e) {
			throw new EFhirClientException("An error has occurred while trying to validate this resource", e);
		}
		return (AtomEntry<OperationOutcome>)result.getPayload();
	}
	
	@Override
	public List<AtomCategory> getAllTags() {
		AtomFeed result = null;
		try {
			result = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetAllTags(), getPreferredResourceFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getTags();
	}
	
	@Override
	public <T extends Resource> List<AtomCategory> getAllTagsForResourceType(Class<T> resourceClass) {
		AtomFeed result = null;
		try {
			result = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetAllTagsForResourceType(resourceClass), getPreferredResourceFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getTags();
	}
	
	@Override
	public <T extends Resource> List<AtomCategory> getTagsForResource(Class<T> resource, String id) {
		AtomFeed result = null;
		try {
			result = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetTagsForResource(resource, id), getPreferredResourceFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getTags();
	}
	
	@Override
	public <T extends Resource> List<AtomCategory> getTagsForResourceVersion(Class<T> resource, String id, String versionId) {
		AtomFeed result = null;
		try {
			result = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetTagsForResourceVersion(resource, id, versionId), getPreferredResourceFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getTags();
	}
	
//	@Override
//	public <T extends Resource> boolean deleteTagsForResource(Class<T> resourceClass, String id) {
//		try {
//			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetTagsForResource(resourceClass, id), proxy);
//		} catch(Exception e) {
//			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
//		}
//
//	}
//	
//	@Override
//	public <T extends Resource> boolean deleteTagsForResourceVersion(Class<T> resourceClass, String id, List<AtomCategory> tags, String version) {
//		try {
//			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetTagsForResourceVersion(resourceClass, id, version), proxy);
//		} catch(Exception e) {
//			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
//		}
//	}
	
	@Override
	public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveGetTagsForResource(resourceClass, id),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to create this resource", e);
		}
		return request.getPayload();
	}
	
	@Override
	public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveGetTagsForResourceVersion(resourceClass, id, version),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to set the tags for this resource", e);
		}
		return request.getPayload();
	}

	@Override
	public <T extends Resource> List<AtomCategory> deleteTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveDeleteTagsForResourceVersion(resourceClass, id, version),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to set the tags for this resource", e);
		}
		return request.getPayload();
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
	
	protected List<Header> buildCategoryHeaders(List<AtomCategory> tags) {
		List<Header> headers = new ArrayList<Header>();
		for(AtomCategory tag : tags) {
			headers.add(new BasicHeader("Category",tag.getTerm() + ";scheme=\"" + tag.getScheme() + "\";label=\"" + tag.getLabel() + "\""));
		}
		return headers;
	}

	@Override
  public AtomFeed fetchFeed(String url) {
		AtomFeed feed = null;
		try {
			feed = ClientUtils.issueGetFeedRequest(new URI(url), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return feed;
  }

	private class SimpleVersionInfo implements VersionInfo {

		private String clientJavaLibVersion;
		private String fhirJavaLibVersion;
		private String fhirJavaLibRevision;
		private String fhirServerVersion;
		private String fhirServerSoftware;

		private SimpleVersionInfo(String clientJavaLibVersion,
        String fhirJavaLibVersion, String fhirJavaLibRevision) {
	    super();
	    this.clientJavaLibVersion = clientJavaLibVersion;
	    this.fhirJavaLibVersion = fhirJavaLibVersion;
	    this.fhirJavaLibRevision = fhirJavaLibRevision;
    }

		@Override
    public String getClientJavaLibVersion() {
	    return clientJavaLibVersion;
    }

		@Override
    public String getFhirJavaLibVersion() {
	    return fhirJavaLibVersion;
    }

		@Override
    public String getFhirJavaLibRevision() {
	    return fhirJavaLibRevision;
    }

		@Override
    public String getFhirServerVersion() {
	    return fhirServerVersion;
    }

		@Override
    public String getFhirServerSoftware() {
	    return fhirServerSoftware;
    }
		
	}
	@Override
  public VersionInfo getVersions() {
		SimpleVersionInfo vinfo = new SimpleVersionInfo(Version.VERSION, Constants.VERSION, Constants.REVISION);
		Conformance conf = getConformanceStatement();
		vinfo.fhirServerVersion = conf.getFhirVersionSimple();
		if (conf.getSoftware() != null)
		  vinfo.fhirServerSoftware = conf.getSoftware().getVersionSimple();
		return vinfo;
  }

}
