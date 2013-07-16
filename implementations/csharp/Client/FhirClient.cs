/*
  Copyright (c) 2011-2013, HL7, Inc.
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

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Hl7.Fhir;
using Hl7.Fhir.Model;
using Hl7.Fhir.Support;
using System.Net;
using Hl7.Fhir.Parsers;
using Hl7.Fhir.Serializers;
using System.IO;
using Newtonsoft.Json;



namespace Hl7.Fhir.Client
{
    public class FhirClient
    {
        /// <summary>
        /// Creates a new client without setting a default endpoint
        /// </summary>
        public FhirClient()
        {
            PreferredFormat = ContentType.ResourceFormat.Xml;
        }

        /// <summary>
        /// Creates a new client using a default endpoint
        /// </summary>
        public FhirClient(Uri endpoint)
        {
            if (endpoint == null) throw new ArgumentNullException("endpoint");

            PreferredFormat = ContentType.ResourceFormat.Xml;
            Endpoint = endpoint;
        }

        /// <summary>
        /// The default endpoint for use with operations that use discrete id/version parameters
        /// instead of explicit uri endpoints.
        /// </summary>
        public Uri Endpoint { get; set; }

        /// <summary>
        /// Get a conformance statement for the system
        /// </summary>
        /// <param name="useOptionsVerb">If true, uses the Http OPTIONS verb to get the conformance, otherwise uses the /metadata endpoint</param>
        /// <returns>A Conformance resource. Throws an exception if the operation failed.</returns>
        public ResourceEntry<Conformance> Conformance(Uri endpoint, bool useOptionsVerb = false)
        {
            if (endpoint == null) throw new ArgumentNullException("endpoint");

            var rl = new ResourceLocation(endpoint);
            if( !useOptionsVerb ) rl.Operation = ResourceLocation.RESTOPER_METADATA;

            var req = createRequest(rl.ToUri(), false);
            req.Method = useOptionsVerb ? "OPTIONS" : "GET";

            return doRequest(req, HttpStatusCode.OK, () => resourceEntryFromResponse<Conformance>() );
        }


        /// <summary>
        /// Get a conformance statement for the system using the default endpoint
        /// </summary>
        /// <param name="useOptionsVerb">If true, uses the Http OPTIONS verb to get the conformance, otherwise uses the /metadata endpoint</param>
        /// <returns>A Conformance resource. Throws an exception if the operation failed.</returns>
        public ResourceEntry<Conformance> Conformance(bool useOptionsVerb = false)
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of Conformance");

            return Conformance(Endpoint);
        }


        /// <summary>
        /// Fetches a resource from a FHIR resource endpoint. This can be a Resource id url or a version-specific
        /// Resource url.
        /// </summary>
        /// <param name="endpoint">The url of the Resource to fetch. This can be a Resource id url or a version-specific
        /// Resource url.</param>
        /// <typeparam name="TResource">The type of resource to fetch</typeparam>
        /// <returns>The requested resource as a ResourceEntry. This operation will throw an exception
        /// if the resource has been deleted or does not exist</returns>
        public ResourceEntry<TResource> Fetch<TResource>(Uri endpoint) where TResource : Resource, new()
        {
            if (endpoint == null) throw new ArgumentNullException("id");

            var req = createRequest(endpoint, false);
            req.Method = "GET";
            
            return doRequest(req, HttpStatusCode.OK, () => resourceEntryFromResponse<TResource>() );
        }


        public ResourceEntry<TResource> Fetch<TResource>(string id, string versionId=null) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of Fetch");

            var rl = buildResourceEndpoint<TResource>(id);
            if (!String.IsNullOrEmpty(versionId)) rl.VersionId = versionId;

            return Fetch<TResource>(rl.ToUri());
        }


        public ResourceEntry<TResource> Read<TResource>(string id) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of Read");
            if (String.IsNullOrEmpty(id)) throw new ArgumentNullException("id");

            return Fetch<TResource>(id);
        }

        public ResourceEntry<TResource> VRead<TResource>(string id, string versionId) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of VRead");
            if (String.IsNullOrEmpty(id)) throw new ArgumentNullException("id");
            if (String.IsNullOrEmpty(versionId)) throw new ArgumentNullException("versionId");
            
            return Fetch<TResource>(id,versionId);
        }


        /// <summary>
        /// Update (or create) a resource at a given endpoint
        /// </summary>
        /// <param name="entry">A ResourceEntry containing the resource to update</param>
        /// <param name="versionAware">Whether or not version aware update is used.</param>
        /// <typeparam name="TResource">The type of resource that is being updated</typeparam>
        /// <returns>The resource as updated on the server. Throws an exception when the update failed,
        /// in particular may throw an exception when the server returns a 409 when a conflict is detected
        /// while using version-aware updates or 412 if the server requires version-aware updates.</returns>
        public ResourceEntry<TResource> Update<TResource>(ResourceEntry<TResource> entry, bool versionAware = false)
                        where TResource : Resource, new()
        {
            if (entry == null) throw new ArgumentNullException("entry");
            if (entry.Content == null) throw new ArgumentException("Entry does not contain a Resource to update", "entry");
            if (entry.Id == null) throw new ArgumentException("Entry needs a non-null entry.id to send the update to", "entry");
            if (versionAware && entry.SelfLink == null) throw new ArgumentException("When requesting version-aware updates, Entry.SelfLink may not be null.", "entry");

            string contentType = ContentType.BuildContentType(PreferredFormat, false);

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(entry.Content) :
                FhirSerializer.SerializeResourceToJsonBytes(entry.Content);

            var req = createRequest(entry.Id, false);

            req.Method = "PUT";
            req.ContentType = contentType;
            initializeResourceSubmission(req, data, entry.Tags);

            // If a version id is given, post the data to a version-specific url
            if (versionAware)
                req.Headers[HttpRequestHeader.ContentLocation] = entry.SelfLink.ToString();

            return doRequest(req, new HttpStatusCode[] { HttpStatusCode.Created, HttpStatusCode.OK }, 
                    () => resourceEntryFromResponse<TResource>() );
        }

 
        /// <summary>
        /// Delete a resource at the given endpoint
        /// </summary>
        /// <param name="id">endpoint of the resource to delete</param>
        /// <typeparam name="TResource">The type of the resource to delete</typeparam>
        /// <returns>Returns normally if delete succeeded, throws an exception otherwise, though this might
        /// just mean the server returned 404 (the resource didn't exist before) or 410 (the resource was
        /// already deleted).</returns>
        public void Delete(Uri endpoint)
        {
            if (endpoint == null) throw new ArgumentNullException("endpoint");

            var req = createRequest(endpoint, false);
            req.Method = "DELETE";

            doRequest(req, HttpStatusCode.NoContent, () => true );
        }


        public void Delete<TResource>(string id) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of Delete");
            if (String.IsNullOrEmpty(id)) throw new ArgumentNullException("id");

            var rl = buildResourceEndpoint<TResource>(id);

            Delete(rl.ToUri());
        }


        /// <summary>
        /// Create a resource
        /// </summary>
        /// <param name="collectionEndpoint">Endpoint where the resource is sent to be created</param>
        /// <param name="resource">The resource instance to create</param>
        /// <param name="tags">Optional. List of Tags to add to the created instance.</param>
        /// <returns>The resource as created on the server, or an exception if the create failed.</returns>
        /// <typeparam name="TResource">The type of resource to create</typeparam>
        /// <remarks><para>The returned resource need not be the same as the resources passed as a parameter,
        /// since the server may have updated or changed part of the data because of business rules.</para>
        /// </remarks>
        public ResourceEntry<TResource> Create<TResource>(Uri collectionEndpoint, TResource resource, IEnumerable<Tag> tags=null) where TResource : Resource, new()
        {
            if (collectionEndpoint == null) throw new ArgumentNullException("collectionEndpoint");
            if (resource == null) throw new ArgumentNullException("resource");

            string contentType = ContentType.BuildContentType(PreferredFormat, false);

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(resource) :
                FhirSerializer.SerializeResourceToJsonBytes(resource);

            var req = createRequest(collectionEndpoint, false);
            req.Method = "POST";
            req.ContentType = contentType;
            initializeResourceSubmission(req, data, tags);

            return doRequest(req, HttpStatusCode.Created, () => resourceEntryFromResponse<TResource>() );
        }

       
        public ResourceEntry<TResource> Create<TResource>(TResource resource, IEnumerable<Tag> tags=null) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of Create");
            if (resource == null) throw new ArgumentNullException("resource");

            string collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = new ResourceLocation(Endpoint,collection);

            return Create<TResource>(rl.ToUri(), resource, tags);
        }


        /// <summary>
        /// Retrieve the version history from a history endpoint
        /// </summary>
        /// <param name="endpoint">The endpoint where the history request is sent.</param>
        /// <param name="since">Optional. Returns only changes after the given date</param>
        /// <param name="count">Optional. Asks server to limit the number of entries returned</param>
        /// <returns>A bundle with the requested history, may contain both ResourceEntries and DeletedEntries.</returns>
        /// <remarks>The endpoint may be a FHIR server for server-wide history, a collection endpoint (
        /// i.e. /patient) for history of a certain type of resources or a resource id, for the
        /// history of that specific resource instance.</remarks>
        public Bundle History(Uri endpoint, DateTimeOffset? since = null, int? count = null)
        {
            if (endpoint == null) throw new ArgumentNullException("endpoint");

            var rl = new ResourceLocation(endpoint);

            if (since != null) rl.SetParam(Util.HISTORY_PARAM_SINCE, Util.FormatIsoDateTime(since.Value));
            if(count != null) rl.SetParam(Util.HISTORY_PARAM_COUNT, count.ToString());

            var req = createRequest(rl.ToUri(), true);
            req.Method = "GET";

            return doRequest(req, HttpStatusCode.OK, () => bundleFromResponse());
        }



        /// <summary>
        /// Retrieve the version history for a specific resource instance
        /// </summary>
        /// <param name="id">The id of the resource to get the history for</param>
        /// <param name="since">Optional. Returns only changes after the given date</param>
        /// <param name="count">Optional. Asks server to limit the number of entries returned</param>
        /// <returns>A bundle with the history for the indicated instance, may contain both 
        /// ResourceEntries and DeletedEntries.</returns>
	    public Bundle History<TResource>(string id, DateTimeOffset? since = null, int? count = null ) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of History");
            if (String.IsNullOrEmpty(id)) throw new ArgumentNullException("id");

            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = ResourceLocation.Build(Endpoint, collection, id);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;
            
            return History(rl.ToUri(), since, count);
        }


        /// <summary>
        /// Retrieve the version history for all resources of a certain type
        /// </summary>
        /// <param name="since">Optional. Returns only changes after the given date</param>
        /// <param name="count">Optional. Asks server to limit the number of entries returned</param>
        /// <returns>A bundle with the history for the indicated instance, may contain both 
        /// ResourceEntries and DeletedEntries.</returns>
        public Bundle History<TResource>(DateTimeOffset? since = null, int? count = null ) where TResource : Resource, new()
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of History");

            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = ResourceLocation.Build(Endpoint, collection);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;

            return History(rl.ToUri(), since, count);
        }


        /// <summary>
        /// Retrieve the version history of any resource on the server
        /// </summary>
        /// <param name="since">Optional. Returns only changes after the given date</param>
        /// <param name="count">Optional. Asks server to limit the number of entries returned</param>
        /// <returns>A bundle with the history for the indicated instance, may contain both 
        /// ResourceEntries and DeletedEntries.</returns>
        public Bundle History(DateTimeOffset? since = null, int? count = null )
        {
            if (Endpoint == null) throw new InvalidOperationException("You must set a default endpoint when using this overload of History");

            var rl = new ResourceLocation(Endpoint);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;

            return History(rl.ToUri(), since, count);
        }


        /// <summary>
        /// Validates whether the contents of the resource would be acceptable as an update
        /// </summary>
        /// <param name="entry">The entry containing the updated Resource to validate</param>
        /// <returns>null if validation succeeded, otherwise returns OperationOutcome detailing the validation errors.
        /// If the server returned an error, but did not return an OperationOutcome resource, an exception will be
        /// thrown.</returns>
        public OperationOutcome Validate<TResource>(ResourceEntry<TResource> entry) where TResource : Resource, new()
        {
            if (entry == null) throw new ArgumentNullException("entry");
            if (entry.Content == null) throw new ArgumentException("Entry does not contain a Resource to validate", "entry");
            if (entry.Id == null) throw new ArgumentException("Entry needs a non-null entry.id to use for validation", "entry");

            string contentType = ContentType.BuildContentType(PreferredFormat, false);

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(entry.Content) :
                FhirSerializer.SerializeResourceToJsonBytes(entry.Content);

            var req = createRequest(entry.Id, false);

            req.Method = "POST";
            req.ContentType = contentType;
            initializeResourceSubmission(req, data, entry.Tags);

            try
            {
                doRequest(req, HttpStatusCode.OK, () => true);
                return null;
            }
            catch(FhirOperationException foe)
            {
                if(foe.Outcome != null)
                    return foe.Outcome;
                else
                    throw foe;
            }
        }

  /*   
        /// <summary>
        /// Return all resources of a certain type
        /// </summary>
        /// <param name="count">The maximum number of resources to return</param>
        /// <param name="includes">Zero or more include paths</param>
        /// <typeparam name="TResource">The type of resource to list</typeparam>
        /// <returns>A Bundle with all resources of the given type, or an empty Bundle if none were found.</returns>
        /// <remarks>This operation supports include parameters to include resources in the bundle that the
        /// returned resources refer to.</remarks>
        public Bundle SearchAll<TResource>(int? count = null, params string[] includes) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            string query = "";

            if( count.HasValue )
                query = addParam("", Util.SEARCH_PARAM_COUNT, count.Value.ToString());
            
            foreach (string includeParam in includes)
                query = addParam(query, Util.SEARCH_PARAM_INCLUDE, includeParam);

            var rl = ResourceLocation.Build(FhirEndpoint, collection);
            rl.Query = query;

            var req = createRequest(rl, true);
            req.Method = "GET";

            //doRequest(req);

            //if (LastResponseDetails.Result == HttpStatusCode.OK)
            //    return parseBundle();
            //else
                return null;
        }


        /// <summary>
        /// Search for resources based on search criteria
        /// </summary>
        /// <param name="parameters">A list of key,value pairs that contain the search params and their search criteria</param>
        /// <param name="count">The maximum number of resources to return</param>
        /// <param name="includes">Zero or more include paths</param>
        /// <typeparam name="TResource">The type of resource to search for</typeparam>
        /// <returns>A Bundle with the BundleEntries matching the search criteria, or an empty
        /// Bundle if no resources were found.</returns>
        /// <remarks>This operation supports include parameters to include resources in the bundle that the
        /// returned resources refer to.</remarks> 
        public Bundle Search<TResource>(string[] parameters, int? count = null, 
                        params string[] includes) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = ResourceLocation.Build(FhirEndpoint, collection);
            rl.Operation = ResourceLocation.RESTOPER_SEARCH;

            if (parameters.Length % 2 != 0)
                throw new ArgumentException("Parameters should contain pairs of keys and values");

            string query = "";

            for (var pi = 0; pi < parameters.Length; pi+=2)
                query = addParam(query, parameters[pi], parameters[pi+1]);

            foreach (var include in includes)
                query = addParam(query, Util.SEARCH_PARAM_INCLUDE, include);

            rl.Query = query;

            var req = createRequest(rl, true);
            req.Method = "GET";

            //doRequest(req);

            //if (LastResponseDetails.Result == HttpStatusCode.OK)
            //    return parseBundle();
            //else
                return null;

        }
  

        /// <summary>
        /// Search for resources based on a resource's id.
        /// </summary>
        /// <param name="id">The id of the resource to search for</param>
        /// <param name="includes">Zero or more include paths</param>
        /// <typeparam name="TResource">The type of resource to search for</typeparam>
        /// <returns>A Bundle with the BundleEntry as identified by the id parameter or an empty
        /// Bundle if the resource wasn't found.</returns>
        /// <remarks>This operation is similar to Read, but additionally,
        /// it is possible to specify include parameters to include resources in the bundle that the
        /// returned resource refers to.</remarks>
        public Bundle SearchById<TResource>(string id, params string[] includes) where TResource : Resource
        {
            var idCriteria = new Dictionary<string, string>();
            idCriteria.Add(Util.SEARCH_PARAM_ID, id);
            return Search<TResource>( new string[] { Util.SEARCH_PARAM_ID, id }, 1, includes);
        }



        /// <summary>
        /// Send a batched update to the server
        /// </summary>
        /// <param name="batch">The contents of the batch to be sent</param>
        /// <returns>A bundle as returned by the server after it has processed the updates in the batch, or null
        /// if an error occurred.</returns>
        public Bundle Batch(Bundle batch)
        {
            byte[] data;
            string contentType = ContentType.BuildContentType(PreferredFormat, false);

            if (PreferredFormat == ContentType.ResourceFormat.Json)
                data = FhirSerializer.SerializeBundleToJsonBytes(batch);
            else if (PreferredFormat == ContentType.ResourceFormat.Xml)
                data = FhirSerializer.SerializeBundleToXmlBytes(batch);
            else
                throw new ArgumentException("Cannot encode a batch into format " + PreferredFormat.ToString());

            var req = createRequest(new ResourceLocation(FhirEndpoint), true);
            req.Method = "POST";
            req.ContentType = contentType;
            initializeResourceSubmission(req, data);

            //doRequest(req);

            //if (LastResponseDetails.Result == HttpStatusCode.OK)
            //    return parseBundle();
            //else
                return null;
        }


        /// <summary>
        /// Send a Bundle to a path on the server
        /// </summary>
        /// <param name="bundle">The contents of the Bundle to be sent</param>
        /// <param name="path">A path on the server to send the Bundle to</param>
        /// <returns>True if the bundle was successfully delivered, false otherwise</returns>
        /// <remarks>This method differs from Batch, in that it can be used to deliver a Bundle
        /// at the endpoint for messages, documents or binaries, instead of the batched update
        /// REST endpoint.</remarks>
        public bool DeliverBundle(Bundle bundle, string path)
        {
            byte[] data;
            string contentType = ContentType.BuildContentType(PreferredFormat, false);

            if (PreferredFormat == ContentType.ResourceFormat.Json)
                data = FhirSerializer.SerializeBundleToJsonBytes(bundle);
            else if (PreferredFormat == ContentType.ResourceFormat.Xml)
                data = FhirSerializer.SerializeBundleToXmlBytes(bundle);
            else
                throw new ArgumentException("Cannot encode a batch into format " + PreferredFormat.ToString());

            var req = createRequest(new ResourceLocation(FhirEndpoint, path), true);
            req.Method = "POST";
            req.ContentType = contentType;
            initializeResourceSubmission(req, data);

            //doRequest(req);

            //if (LastResponseDetails.Result == HttpStatusCode.OK)
            //    return true;
            //else
                return false;
        }

*/
        public ContentType.ResourceFormat PreferredFormat { get; set; }

        private HttpWebRequest createRequest(Uri location, bool forBundle)
        {
            //if( PreferredFormat == ContentType.ResourceFormat.Json )
            //    endpoint = addParam(endpoint, ContentType.FORMAT_PARAM, ContentType.FORMAT_PARAM_JSON);
            //if (PreferredFormat == ContentType.ResourceFormat.Xml)
            //    endpoint = addParam(endpoint, ContentType.FORMAT_PARAM, ContentType.FORMAT_PARAM_XML);

            var req = (HttpWebRequest)HttpWebRequest.Create(location);
            var agent =  "FhirClient for FHIR " + Model.ModelInfo.Version;

#if NETFX_CORE
            req.Headers[HttpRequestHeader.UserAgent] = agent;
#else
            req.UserAgent = agent;
#endif
                       
            if (PreferredFormat == ContentType.ResourceFormat.Xml)
                req.Accept = ContentType.BuildContentType(ContentType.ResourceFormat.Xml, forBundle);
            else if (PreferredFormat == ContentType.ResourceFormat.Json)
                req.Accept = ContentType.BuildContentType(ContentType.ResourceFormat.Json, forBundle);
            else
                throw new ArgumentException("PreferrredFormat was set to an unsupported seralization format");

            return req;
        }


        public ResponseDetails LastResponseDetails { get; private set; }

        private ResourceLocation buildResourceEndpoint<TResource>(string id) where TResource : Resource, new()
        {
            var rl = new ResourceLocation(Endpoint);
            rl.Id = id;
            rl.Collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            return rl;
        }


        private ResourceEntry<T> resourceEntryFromResponse<T>() where T : Resource, new()
        {
            // Initialize a resource entry from the received data. Note: Location overrides ContentLocation
            ResourceEntry result = HttpUtil.SingleResourceResponse(LastResponseDetails.BodyAsString(),
                    LastResponseDetails.ContentType, LastResponseDetails.ResponseUri.ToString(), 
                    LastResponseDetails.Location ?? LastResponseDetails.ContentLocation, 
                    LastResponseDetails.Category, LastResponseDetails.LastModified);

            if (result.Content is T)
                return (ResourceEntry<T>)result;
            else
                throw new FhirOperationException(
                    String.Format("Received a resource of type {0}, expected a {1} resource",
                                    result.Content.GetType().Name, typeof(T).Name));
        }


        private Bundle bundleFromResponse()
        {
            string data = LastResponseDetails.BodyAsString();
            string contentType = LastResponseDetails.ContentType;

            ErrorList parseErrors = new ErrorList();
            Bundle result;

            ContentType.ResourceFormat format = ContentType.GetResourceFormatFromContentType(contentType);

            switch (format)
            {
                case ContentType.ResourceFormat.Json:
                    result = FhirParser.ParseBundleFromJson(data, parseErrors);
                    break;
                case ContentType.ResourceFormat.Xml:
                    result = FhirParser.ParseBundleFromXml(data, parseErrors);
                    break;
                default:
                    throw new FhirParseException("Cannot decode bundle: unrecognized content type " + format);
            }

            if (parseErrors.Count() > 0)
                throw new FhirParseException("Failed to parse bundle data: " + parseErrors.ToString(), parseErrors, data);

            return result;
        }


        private T doRequest<T>(HttpWebRequest req, HttpStatusCode success, Func<T> onSuccess)
        {
            return doRequest(req, new HttpStatusCode[] { success }, onSuccess);
        }

        private T doRequest<T>(HttpWebRequest req, HttpStatusCode[] success, Func<T> onSuccess)
        {
            HttpWebResponse response = (HttpWebResponse)req.GetResponseNoEx();

            try
            {
                LastResponseDetails = ResponseDetails.FromHttpWebResponse(response);

                if ( success.Contains(LastResponseDetails.Result) ) 
                    return onSuccess();
                else
                {
                    // Try to parse the body as an OperationOutcome resource, but it is no
                    // problem if it's something else, or there is no parseable body at all

                    ResourceEntry<OperationOutcome> outcome = null;

                    try
                    {
                        outcome = resourceEntryFromResponse<OperationOutcome>();
                    }
                    catch
                    {
                        // failed, too bad.
                    }

                    if (outcome != null)
                        throw new FhirOperationException("Operation failed with status code " + LastResponseDetails.Result, outcome.Content);                        
                    else
                        throw new FhirOperationException("Operation failed with status code " + LastResponseDetails.Result);
                }
            }
            finally
            {
#if !NETFX_CORE
                response.Close();
#endif
            }
        }


        private void initializeResourceSubmission(HttpWebRequest request, byte[] data, IEnumerable<Tag> tags=null)
        {
            if (tags != null)
                request.Headers[HttpUtil.CATEGORY] = HttpUtil.BuildCategoryHeader(tags);

#if NETFX_CORE
            var getStreamTask = request.GetRequestStreamAsync();
            getStreamTask.RunSynchronously();
            Stream outs = getStreamTask.Result;
#else
            Stream outs = request.GetRequestStream();
#endif

            outs.Write(data, 0, (int)data.Length);
            outs.Flush();

#if !NETFX_CORE
            outs.Close();
#endif
        }

    }
}
