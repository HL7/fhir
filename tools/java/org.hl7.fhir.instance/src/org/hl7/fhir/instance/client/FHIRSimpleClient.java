package org.hl7.fhir.instance.client;


/*
  Copyright (c) 2011+, HL7, Inc.
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Constants;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.Version;
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
public class FHIRSimpleClient implements IFHIRClient {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssK";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	private ResourceAddress resourceAddress;
	private ResourceFormat preferredResourceFormat;
	private FeedFormat preferredFeedFormat;
	private HttpHost proxy;
	private int maxResultSetSize = -1;//_count
	
	//Pass enpoint for client - URI
	public FHIRSimpleClient() {
		preferredResourceFormat = ResourceFormat.RESOURCE_XML;
		preferredFeedFormat = FeedFormat.FEED_XML;
	}
	
	public void configureProxy(String proxyHost, int proxyPort) {
		proxy = new HttpHost(proxyHost, proxyPort);
	}
	
	//TODO Add getter for endpoint
	
	//public void configure() throws new not implemented exception - constrain using conformance.
	
	@Override
	public IFHIRClient initialize(String baseServiceUrl)  throws URISyntaxException {
		initialize(baseServiceUrl, -1);
		return this;
	}
	
	@Override
	public void initialize(String baseServiceUrl, int maxResultSetSize)  throws URISyntaxException {
		resourceAddress = new ResourceAddress(baseServiceUrl);
		this.maxResultSetSize = maxResultSetSize;
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
	public int getMaximumRecordCount() {
		return maxResultSetSize;
	}
	
	@Override
	public void setMaximumRecordCount(int maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}
	
	@Override
	public Conformance getConformanceStatement() throws EFhirClientException {
		return getConformanceStatement(false);
	}
	
	@Override
	public Conformance getConformanceStatement(boolean useOptionsVerb) {
		Conformance conformance = null;
		try {
			if(useOptionsVerb) {
				conformance = (Conformance)ClientUtils.issueOptionsRequest(resourceAddress.getBaseServiceUri(), getPreferredResourceFormat(), proxy).getReference();//TODO fix this
			} else {
				conformance = (Conformance)ClientUtils.issueGetResourceRequest(resourceAddress.resolveMetadataUri(), getPreferredResourceFormat(), proxy).getReference();
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to fetch the server's conformance statement", e);
		}
		return conformance;
	}
	
	//TODO Add call to get resource from URI - absolute or relative (both read and vread)
	
	@Override
	public <T extends Resource> T read(Class<T> resourceClass, String id) {//TODO Change this to AddressableResource
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addSuccessStatus(200);//Only one for now
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload());
			}
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this resource", e);
		}
		return result.getPayload();
	}

	@Override
	public <T extends Resource> T vread(Class<T> resourceClass, String id, String version) {
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issueGetResourceRequest(resourceAddress.resolveGetUriFromResourceClassAndIdAndVersion(resourceClass, id, version), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addErrorStatus(405);//unknown
			result.addSuccessStatus(200);//Only one for now
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload());
			}
		} catch (Exception e) {
			handleException("An error has occurred while trying to read this version of the resource", e);
		}
		return result.getPayload();
	}
	
	@Override
	public <T extends Resource> T update(Class<T> resourceClass, T resource, String id) {
		ResourceRequest<T> result = null;
		try {
			List<Header> headers = null;
			result = ClientUtils.issuePutRequest(resourceAddress.resolveGetUriFromResourceClassAndId(resourceClass, id),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), headers, proxy);
			result.addErrorStatus(410);//gone
			result.addErrorStatus(404);//unknown
			result.addErrorStatus(405);
			result.addErrorStatus(422);//Unprocessable Entity
			result.addSuccessStatus(200);
			result.addSuccessStatus(201);
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload());
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
	public <T extends Resource> OperationOutcome create(Class<T> resourceClass, T resource) {
		ResourceRequest<T> resourceRequest = null;
		try {
			List<Header> headers = null;
			resourceRequest = ClientUtils.issuePostRequest(resourceAddress.resolveGetUriFromResourceClass(resourceClass),ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), headers, proxy);
			resourceRequest.addSuccessStatus(201);
			if(resourceRequest.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + resourceRequest.getHttpStatus(), (OperationOutcome)resourceRequest.getPayload());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to create this resource", e);
		}
		return (OperationOutcome)resourceRequest.getPayload();
	}

	@Override
	public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass, String id) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle history(Date lastUpdate, Class<T> resourceClass, String id) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> Bundle history(Date lastUpdate, Class<T> resourceClass) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> Bundle history(Class<T> resourceClass) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceType(resourceClass, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource type", e);
		}
		return history;
	}
	
	@Override
	public <T extends Resource> Bundle history(Class<T> resourceClass, String id) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForResourceId(resourceClass, id, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history information for this resource", e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle history(Date lastUpdate) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle history(Calendar lastUpdate) {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(lastUpdate, maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle history() {
		Bundle history = null;
		try {
			history = ClientUtils.issueGetFeedRequest(resourceAddress.resolveGetHistoryForAllResources(maxResultSetSize), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve history since last update",e);
		}
		return history;
	}

	@Override
	public <T extends Resource> Bundle search(Class<T> resourceClass, Map<String, String> parameters) {
		Bundle searchResults = null;
		try {
			searchResults = ClientUtils.issueGetFeedRequest(resourceAddress.resolveSearchUri(resourceClass, parameters), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("Error performing search with parameters " + parameters, e);
		}
		return searchResults;
	}
	
  @Override
  public <T extends Resource> Bundle searchPost(Class<T> resourceClass, T resource, Map<String, String> parameters) {
    Bundle searchResults = null;
    try {
      searchResults = ClientUtils.issuePostFeedRequest(resourceAddress.resolveSearchUri(resourceClass, new HashMap<String, String>()), parameters, "src", resource, getPreferredFeedFormat());
    } catch (Exception e) {
      handleException("Error performing search with parameters " + parameters, e);
    }
    return searchResults;
  }
	
	@Override
	public Bundle transaction(Bundle batch) {
		Bundle transactionResult = null;
		try {
			transactionResult = ClientUtils.postBatchRequest(resourceAddress.getBaseServiceUri(), ClientUtils.getFeedAsByteArray(batch, false, isJson(getPreferredFeedFormat())), getPreferredFeedFormat(), proxy);
		} catch (Exception e) {
			handleException("An error occurred trying to process this transaction request", e);
		}
		return transactionResult;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Resource> OperationOutcome validate(Class<T> resourceClass, T resource, String id) {
		ResourceRequest<T> result = null;
		try {
			result = ClientUtils.issuePostRequest(resourceAddress.resolveValidateUri(resourceClass, id), ClientUtils.getResourceAsByteArray(resource, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), proxy);
			result.addErrorStatus(400);//gone
			result.addErrorStatus(422);//Unprocessable Entity
			result.addSuccessStatus(200);//OK
			if(result.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server returned error code " + result.getHttpStatus(), (OperationOutcome)result.getPayload());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to validate this resource", e);
		}
		return (OperationOutcome)result.getPayload();
	}
	
	/* change to meta operations
	@Override
	public List<Coding> getAllTags() {
		TagListRequest result = null;
		try {
			result = ClientUtils.issueGetRequestForTagList(resourceAddress.resolveGetAllTags(), getPreferredResourceFormat(), null, proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve all tags", e);
		}
		return result.getPayload();
	}
	
	@Override
	public <T extends Resource> List<Coding> getAllTagsForResourceType(Class<T> resourceClass) {
		TagListRequest result = null;
		try {
			result = ClientUtils.issueGetRequestForTagList(resourceAddress.resolveGetAllTagsForResourceType(resourceClass), getPreferredResourceFormat(), null, proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve tags for this resource type", e);
		}
		return result.getPayload();
	}
	
	@Override
	public <T extends Resource> List<Coding> getTagsForReference(Class<T> resource, String id) {
		TagListRequest result = null;
		try {
			result = ClientUtils.issueGetRequestForTagList(resourceAddress.resolveGetTagsForReference(resource, id), getPreferredResourceFormat(), null, proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve tags for this resource", e);
		}
		return result.getPayload();
	}
	
	@Override
	public <T extends Resource> List<Coding> getTagsForResourceVersion(Class<T> resource, String id, String versionId) {
		TagListRequest result = null;
		try {
			result = ClientUtils.issueGetRequestForTagList(resourceAddress.resolveGetTagsForResourceVersion(resource, id, versionId), getPreferredResourceFormat(), null, proxy);
		} catch (Exception e) {
			handleException("An error has occurred while trying to retrieve tags for this resource version", e);
		}
		return result.getPayload();
	}
	
//	@Override
//	public <T extends Resource> boolean deleteTagsForReference(Class<T> resourceClass, String id) {
//		try {
//			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetTagsForReference(resourceClass, id), proxy);
//		} catch(Exception e) {
//			handleException("An error has occurred while trying to retrieve tags for this resource version", e);
//			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
//		}
//
//	}
//	
//	@Override
//	public <T extends Resource> boolean deleteTagsForResourceVersion(Class<T> resourceClass, String id, List<Coding> tags, String version) {
//		try {
//			return ClientUtils.issueDeleteRequest(resourceAddress.resolveGetTagsForResourceVersion(resourceClass, id, version), proxy);
//		} catch(Exception e) {
//			handleException("An error has occurred while trying to retrieve tags for this resource version", e);
//			throw new EFhirClientException("An error has occurred while trying to delete this resource", e);
//		}
//	}
	
	@Override
	public <T extends Resource> List<Coding> createTags(List<Coding> tags, Class<T> resourceClass, String id) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveGetTagsForReference(resourceClass, id),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to set tags for this resource", e);
		}
		return request.getPayload();
	}
	
	@Override
	public <T extends Resource> List<Coding> createTags(List<Coding> tags, Class<T> resourceClass, String id, String version) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveGetTagsForResourceVersion(resourceClass, id, version),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to set the tags for this resource version", e);
		}
		return request.getPayload();
	}

	@Override
	public <T extends Resource> List<Coding> deleteTags(List<Coding> tags, Class<T> resourceClass, String id, String version) {
		TagListRequest request = null;
		try {
			request = ClientUtils.issuePostRequestForTagList(resourceAddress.resolveDeleteTagsForResourceVersion(resourceClass, id, version),ClientUtils.getTagListAsByteArray(tags, false, isJson(getPreferredResourceFormat())), getPreferredResourceFormat(), null, proxy);
			request.addSuccessStatus(201);
			request.addSuccessStatus(200);
			if(request.isUnsuccessfulRequest()) {
				throw new EFhirClientException("Server responded with HTTP error code " + request.getHttpStatus());
			}
		} catch(Exception e) {
			handleException("An error has occurred while trying to delete the tags for this resource version", e);
		}
		return request.getPayload();
	}
	*/

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
	
	protected List<Header> buildCategoryHeader(List<Coding> tags) {
		List<Header> headers = new ArrayList<Header>();
		String categoryValue = "";
		for(int index = 0; index < tags.size(); index++) {
			Coding tag = tags.get(index);
			// todo-bundle categoryValue += tag.getTerm() + ";scheme=\"" + tag.getScheme() + "\";label=\"" + tag.getLabel() + "\"";
			if(index < tags.size() - 1) {
				categoryValue += ",";
			}
		}
		headers.add(new BasicHeader("Category",categoryValue));
		return headers;
	}

	@Override
  public Bundle fetchFeed(String url) {
		Bundle feed = null;
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
		vinfo.fhirServerVersion = conf.getFhirVersion();
		if (conf.getSoftware() != null)
		  vinfo.fhirServerSoftware = conf.getSoftware().getVersion();
		return vinfo;
  }

  @Override
  public ValueSet expandValueset(ValueSet source) throws Exception {
    Bundle searchResults = null;
    searchResults = ClientUtils.issuePostFeedRequest(resourceAddress.resolveOperationUri(ValueSet.class, "expand"), new HashMap<String, String>(), "valueSet", source, getPreferredFeedFormat());
    return (ValueSet) searchResults.getEntry().get(0).getResource();
  }

}
