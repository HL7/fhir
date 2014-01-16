package org.hl7.fhir.instance.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;

/**
 * Helper class to manage FHIR Resource URIs
 * 
 * @author Claude Nanjo
 *
 */
public class ResourceAddress {
	
	public static final String REGEX_ID_WITH_HISTORY = "(.*)(/)([a-zA-Z]*)(/)(\\d+)(/_history/)(\\d+)$";
	
	private URI baseServiceUri;
	
	public ResourceAddress(String endpointPath) throws URISyntaxException {//TODO Revisit this exception
		this.baseServiceUri = ResourceAddress.buildAbsoluteURI(endpointPath);
	}
	
	public ResourceAddress(URI baseServiceUri) {
		this.baseServiceUri = baseServiceUri;
	}
	
	public URI getBaseServiceUri() {
		return this.baseServiceUri;
	}
	
	public <T extends Resource> URI resolveSearchUri(Class<T> resourceClass, Map<String,String> parameters) {
		return appendHttpParameters(baseServiceUri.resolve(resourceClass.getSimpleName() +"/_search"), parameters);
	}
	
	public <T extends Resource> URI resolveValidateUri(Class<T> resourceClass, String id) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() +"/_validate/"+id);
	}
	
	public <T extends Resource> URI resolveGetUriFromResourceClass(Class<T> resourceClass) {
		return baseServiceUri.resolve(resourceClass.getSimpleName());
	}
	
	public <T extends Resource> URI resolveGetUriFromResourceClassAndId(Class<T> resourceClass, String id) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() +"/"+id);
	}
	
	public <T extends Resource> URI resolveGetUriFromResourceClassAndIdAndVersion(Class<T> resourceClass, String id, String version) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() +"/"+id+"/_history/"+version);
	}
	
	public <T extends Resource> URI resolveGetHistoryForResourceId(Class<T> resourceClass, String id) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() + "/" + id + "/_history");
	}
	
	public <T extends Resource> URI resolveGetHistoryForAllResources(Calendar since) {//TODO Only add _since parameters if it is non-null
		return appendHttpParameter(baseServiceUri.resolve("_history"), "_since", getCalendarDateInIsoTimeFormat(since));
	}
	
	public <T extends Resource> URI resolveGetHistoryForResourceId(Class<T> resourceClass, String id, Calendar since) {
		return appendHttpParameter(resolveGetHistoryForResourceId(resourceClass, id), "_since", getCalendarDateInIsoTimeFormat(since));
	}
	
	public <T extends Resource> URI resolveGetHistoryForResourceType(Class<T> resourceClass) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() + "/_history");
	}
	
	public <T extends Resource> URI resolveGetHistoryForResourceType(Class<T> resourceClass, Calendar since) {
		return appendHttpParameter(resolveGetHistoryForResourceType(resourceClass), "_since", getCalendarDateInIsoTimeFormat(since));
	}
	
	public <T extends Resource> URI resolveGetAllTags() {
		return baseServiceUri.resolve("_tags");
	}
	
	public <T extends Resource> URI resolveGetAllTagsForResourceType(Class<T> resourceClass) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() + "/_tags");
	}
	
	public <T extends Resource> URI resolveGetTagsForResource(Class<T> resourceClass, String id) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() + "/" + id + "/_tags");
	}
	
	public <T extends Resource> URI resolveGetTagsForResourceVersion(Class<T> resourceClass, String id, String version) {
		return baseServiceUri.resolve(resourceClass.getSimpleName() +"/"+id+"/_history/"+version + "/_tags");
	}
	
	public URI resolveMetadataUri() {
		return baseServiceUri.resolve("metadata");
	}
	
	/**
	 * For now, assume this type of location header structure.
	 * Generalize later: http://hl7connect.healthintersections.com.au/svc/fhir/318/_history/1
	 * 
	 * @param serviceBase
	 * @param locationHeader
	 */
	public static ResourceAddress.ResourceVersionedIdentifier parseCreateLocation(String locationResponseHeader) {
		Pattern pattern = Pattern.compile(REGEX_ID_WITH_HISTORY);
		Matcher matcher = pattern.matcher(locationResponseHeader);
		ResourceVersionedIdentifier parsedHeader = null;
		if(matcher.matches()){
			String serviceRoot = matcher.group(1);
			String resourceType = matcher.group(3);
			String id = matcher.group(5);
			String version = matcher.group(7);
			parsedHeader = new ResourceVersionedIdentifier(serviceRoot, resourceType, id, version);
		}
		return parsedHeader;
	}
	
	public static URI buildAbsoluteURI(String absoluteURI) {
		
		if(StringUtils.isBlank(absoluteURI)) {
			throw new EFhirClientException("Invalid URI", new URISyntaxException(absoluteURI, "URI/URL cannot be blank"));
		} 
		
		String endpoint = appendForwardSlashToPath(absoluteURI);

		return buildEndpointUriFromString(endpoint);
	}
	
	public static String appendForwardSlashToPath(String path) {
		if(path.lastIndexOf('/') != path.length() - 1) {
			path += "/";
		}
		return path;
	}
	
	public static URI buildEndpointUriFromString(String endpointPath) {
		URI uri = null; 
		try {
			URIBuilder uriBuilder = new URIBuilder(endpointPath);
			uri = uriBuilder.build();
			String scheme = uri.getScheme();
			String host = uri.getHost();
			if(!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) {
				throw new EFhirClientException("Scheme must be 'http' or 'https': " + uri);
			}
			if(StringUtils.isBlank(host)) {
				throw new EFhirClientException("host cannot be blank: " + uri);
			}
		} catch(URISyntaxException e) {
			throw new EFhirClientException("Invalid URI", e);
		}
		return uri;
	}
	
	public static URI appendQueryStringToUri(URI uri, String parameterName, String parameterValue) {
		URI modifiedUri = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(uri);
			uriBuilder.setQuery(parameterName + "=" + parameterValue);
			modifiedUri = uriBuilder.build();
		} catch(Exception e) {
			throw new EFhirClientException("Unable to append query parameter '" + parameterName + "=" + parameterValue + " to URI " + uri, e);
		}
		return modifiedUri;
	}
	
	public static String buildRelativePathFromResourceType(ResourceType resourceType) {
		//return resourceType.toString().toLowerCase()+"/";
		return resourceType.toString() + "/";
	}
	
	public static String buildRelativePathFromResourceType(ResourceType resourceType, String id) {
		return buildRelativePathFromResourceType(resourceType)+ "@" + id;
	}
	
	public static String buildRelativePathFromResource(Resource resource) {
		return buildRelativePathFromResourceType(resource.getResourceType());
	}
	
	public static String buildRelativePathFromResource(Resource resource, String id) {
		return buildRelativePathFromResourceType(resource.getResourceType(), id);
	}
	
	public static class ResourceVersionedIdentifier {
		
		private String serviceRoot;
		private String resourceType;
		private String id;
		private String version;
		private URI resourceLocation;
		
		public ResourceVersionedIdentifier(String serviceRoot, String resourceType, String id, String version, URI resourceLocation) {
			this.serviceRoot = serviceRoot;
			this.resourceType = resourceType;
			this.id = id;
			this.version = version;
			this.resourceLocation = resourceLocation;
		}
		
		public ResourceVersionedIdentifier(String resourceType, String id, String version, URI resourceLocation) {
			this(null, resourceType, id, version, resourceLocation);
		}
		
		public ResourceVersionedIdentifier(String serviceRoot, String resourceType, String id, String version) {
			this(serviceRoot, resourceType, id, version, null);
		}
		
		public ResourceVersionedIdentifier(String resourceType, String id, String version) {
			this(null, resourceType, id, version, null);
		}
		
		public ResourceVersionedIdentifier(String resourceType, String id) {
			this.id = id;
		}
		
		public String getId() {
			return this.id;
		}
		
		protected void setId(String id) {
			this.id = id;
		}
		
		public String getVersionId() {
			return this.version;
		}
		
		protected void setVersionId(String version) {
			this.version = version;
		}
		
		public String getResourceType() {
			return resourceType;
		}

		public void setResourceType(String resourceType) {
			this.resourceType = resourceType;
		}
		
		public String getServiceRoot() {
			return serviceRoot;
		}

		public void setServiceRoot(String serviceRoot) {
			this.serviceRoot = serviceRoot;
		}
		
		public String getResourcePath() {
			return this.serviceRoot + "/" + this.resourceType + "/" + this.id;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public URI getResourceLocation() {
			return this.resourceLocation;
		}
		
		public void setResourceLocation(URI resourceLocation) {
			this.resourceLocation = resourceLocation;
		}
	}
	
	public static String getCalendarDateInIsoTimeFormat(Calendar calendar) {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss");//TODO Move out
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	    return format.format(calendar.getTime());
	}
	
	public static URI appendHttpParameter(URI basePath, String httpParameterName, String httpParameterValue) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(httpParameterName, httpParameterValue);
		return appendHttpParameters(basePath, parameters);
	}
	
	public static URI appendHttpParameters(URI basePath, Map<String,String> parameters) {
        try {
        	Set<String> httpParameterNames = parameters.keySet();
        	String query = basePath.getQuery();
        	
        	for(String httpParameterName : httpParameterNames) {
		        if(query != null) {
			        query += "&";
		        } else {
		        	query = "";
		        }
		        query += httpParameterName + "=" + parameters.get(httpParameterName);
        	}
	
	        return new URI(basePath.getScheme(), basePath.getUserInfo(), basePath.getHost(),basePath.getPort(), basePath.getPath(), query, basePath.getFragment());
        } catch(Exception e) {
        	throw new EFhirClientException("Error appending http parameter", e);
        }
    }
	
}