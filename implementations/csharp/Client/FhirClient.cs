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
        public Uri FhirEndpoint { get; private set; }


        public FhirClient(Uri endpoint)
        {
            if (!endpoint.IsAbsoluteUri)
                throw new ArgumentException("endpoint must be an absolute path");

            FhirEndpoint = endpoint;
            PreferredFormat = ContentType.ResourceFormat.Xml;
        }


        /// <summary>
        /// Get a conformance statement for the system
        /// </summary>
        /// <param name="useOptionsVerb">If true, uses the Http OPTIONS verb to get the conformance, otherwise uses the /metadata endpoint</param>
        /// <returns>A Conformance resource, or null if the server did not return status 200</returns>
        public Conformance Conformance(bool useOptionsVerb = false)
        {
            var rl = new ResourceLocation(FhirEndpoint);

            if( !useOptionsVerb )
                rl.Operation = ResourceLocation.RESTOPER_METADATA;

            var req = createRequest(rl, false);
            req.Method = useOptionsVerb ? "OPTIONS" : "GET";

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
            {
                Resource result = parseResource();

                if (!(result is Conformance))
                    throw new FhirOperationException(
                        String.Format("Received a resource of type {0}, expected a Conformance resource", result.GetType().Name));

                return (Conformance)result;
            }
            else
            {
                var outcome = tryParseOperationOutcome();

                if (outcome != null)
                    throw new FhirOperationException("Conformance operation failed with status code " + LastResponseDetails.Result);
                else
                    throw new FhirOperationException("Conformance operation failed with status code " + LastResponseDetails.Result, outcome);
            }
        }


        /// <summary>
        /// Fetches the latest version of a resource
        /// </summary>
        /// <param name="id">The id of the Resource to fetch</param>
        /// <typeparam name="TResource">The type of resource to fetch</typeparam>
        /// <returns>The requested resource, or null if the server did not return status 200</returns>
        public TResource Read<TResource>(string id) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var req = createRequest(ResourceLocation.Build(FhirEndpoint, collection, id), false);
            req.Method = "GET";
            
            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return (TResource)parseResource();
            else
                return null;
        }

        
        /// <summary>
        /// Fetches a specific version of a resource
        /// </summary>
        /// <param name="id">The id of the resource to fetch</param>
        /// <param name="versionId">The version id of the resource to fetch</param>
        /// <typeparam name="TResource">The type of resource to fetch</typeparam>
        /// <returns></returns>
        public TResource VRead<TResource>(string id, string versionId) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var vReadLocation = ResourceLocation.Build(FhirEndpoint, collection, id, versionId);
            var req = createRequest(vReadLocation, false);
            req.Method = "GET";

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return (TResource)parseResource();
            else
                return null;
        }


        /// <summary>
        /// Update (or create) a resource
        /// </summary>
        /// <param name="resource">The updated resource content</param>
        /// <param name="id">The id of the resource to be updated</param>
        /// <param name="versionId">If not null, the version of the resource that is being updated</param>
        /// <typeparam name="TResource">The type of resource that is being updated</typeparam>
        /// <returns>The resource as updated on the server, or null if the update failed.</returns>
        /// <remarks>
        /// <para>The returned resource need not be the same as the resources passed as a parameter,
        /// since the server may have updated or changed part of the data because of business rules.</para>
        /// <para>If there was no existing resource to update with the given id, the server may allow the client
        /// to create the resource instead. If so, it returns status code 201 (Created) instead of 200. If
        /// the resource did not exist and creation using the id given by the client if forbidden, a
        /// 405 (Method Not Allowed) is returned.</para>
        /// <para>If a versionId parameter is provided, the update will only succeed if the last version
        /// on the server corresponds to that versionId. This allows the client to detect update conflicts.
        /// If a conflict arises, Update returns null and the result status code will be 409 (Conflict). If
        /// the server requires version-aware updates but the client does not provide the versionId parameter,
        /// Update also returns null, but the result status code will be 412 (Preconditions failed).</para></remarks>
        public TResource Update<TResource>(TResource resource, string id, string versionId = null)
                        where TResource : Resource
        {
            string contentType = ContentType.BuildContentType(PreferredFormat, false);
            string collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(resource) :
                FhirSerializer.SerializeResourceToJsonBytes(resource);

            var req = createRequest(ResourceLocation.Build(FhirEndpoint, collection, id), false);

            req.Method = "PUT";
            req.ContentType = contentType;
            setRequestBody(req, data);

            if (versionId != null)
            {
                var versionUrl = ResourceLocation.Build(FhirEndpoint, collection, id, versionId).ToUri();
                req.Headers[HttpRequestHeader.ContentLocation] = versionUrl.ToString();
            }

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.Created || LastResponseDetails.Result == HttpStatusCode.OK)
                return (TResource)parseResource();
            else
                return null;
        }


        /// <summary>
        /// Delete a resource
        /// </summary>
        /// <param name="id">id of the resource to delete</param>
        /// <typeparam name="TResource">The type of the resource to delete</typeparam>
        /// <returns>true if the delete succeeded, or false otherwise</returns>
        public bool Delete<TResource>(string id) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var req =
                  createRequest(ResourceLocation.Build(FhirEndpoint, collection, id), false);
            req.Method = "DELETE";

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.NoContent)
                return true;
            else
                return false;
        }


        /// <summary>
        /// Create a resource
        /// </summary>
        /// <param name="resource">The resource instance to create</param>
        /// <param name="newId">Newly assigned id by server after successful creation</param>
        /// <returns>The resource as created on the server, or null if the create failed.</returns>
        /// <typeparam name="TResource">The type of resource to create</typeparam>
        /// <remarks><para>The returned resource need not be the same as the resources passed as a parameter,
        /// since the server may have updated or changed part of the data because of business rules.</para>
        /// <para>When the resource was created, but newId is null, the server failed to return the new
        /// id in the Http Location header.</para>
        /// </remarks>
        public TResource Create<TResource>(TResource resource, out string newId) where TResource : Resource
        {
            string contentType = ContentType.BuildContentType(PreferredFormat, false);
            string collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(resource) :
                FhirSerializer.SerializeResourceToJsonBytes(resource);

            var req = createRequest(new ResourceLocation(FhirEndpoint,collection), false);
            req.Method = "POST";
            req.ContentType = contentType;
            setRequestBody(req, data);
         
            doRequest(req);

            newId = null;

            if (LastResponseDetails.Result == HttpStatusCode.Created)
            {
                var result = parseResource();
                if (LastResponseDetails.Location != null)
                    newId = new ResourceLocation(LastResponseDetails.Location).Id;
                return (TResource)result;
            }
            else
                return null;
        }


        /// <summary>
        /// Retrieve the version history for a specific resource instance
        /// </summary>
        /// <param name="id">The id of the resource to get the history for</param>
        /// <param name="lastUpdate">If provided, only get updates on or after the given point in time</param>
        /// <typeparam name="TResource">The type of resource to get the history for</typeparam>
        /// <returns>A Bundle listing all versions for the given resource id</returns>
	    public Bundle History<TResource>(string id, DateTimeOffset? lastUpdate = null ) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = ResourceLocation.Build(FhirEndpoint, collection, id);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;

            string query = "";
            if (lastUpdate.HasValue)
                query = addParam(query, Util.HISTORY_PARAM_SINCE, Util.FormatIsoDateTime(lastUpdate.Value));
            rl.Query = query;

            var req = createRequest(rl, true);
            req.Method = "GET";
            
            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
                return null;
        }

       
        /// <summary>
        /// Retrieve the version history for all resources of a certain type
        /// </summary>
        /// <param name="lastUpdate">If provided, only get updates on or after the given point in time</param>
        /// <typeparam name="TResource">The type of resource to get the history for</typeparam>
        /// <returns>A Bundle listing all versions for all resources of the given type</returns>
        public Bundle History<TResource>(DateTimeOffset? lastUpdate = null) where TResource : Resource
        {
            var collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));
            var rl = ResourceLocation.Build(FhirEndpoint, collection);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;

            string query = "";
            if (lastUpdate.HasValue)
                query = addParam(query, Util.HISTORY_PARAM_SINCE, Util.FormatIsoDateTime(lastUpdate.Value));
            rl.Query = query;

            var req = createRequest(rl, true);
            req.Method = "GET";

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
                return null;
        }


        /// <summary>
        /// Retrieve the version history of any resource on the server
        /// </summary>
        /// <param name="lastUpdate">If provided, only get updates on or after the given point in time</param>
        /// <returns>A Bundle listing all versions of all resource on the server</returns>
        public Bundle History(DateTimeOffset? lastUpdate = null)
        {
            var rl = new ResourceLocation(FhirEndpoint);
            rl.Operation = ResourceLocation.RESTOPER_HISTORY;

            string query = "";
            if (lastUpdate.HasValue)
                query = addParam(query, Util.HISTORY_PARAM_SINCE, Util.FormatIsoDateTime(lastUpdate.Value));
            rl.Query = query;

            var req = createRequest(rl, true);
            req.Method = "GET";

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
                return null;
        }


        /// <summary>
        /// Validates whether the contents of the resource would be acceptable as an update
        /// </summary>
        /// <param name="resource">The resource contents to validate</param>
        /// <param name="id">The id that would be updated</param>
        /// <returns>null is validation succeeded, otherwise returns an IssueReport detailing the validation errors</returns>
        public OperationOutcome Validate<TResource>(TResource resource, string id) where TResource : Resource
        {
            string contentType = ContentType.BuildContentType(PreferredFormat, false);
            string collection = ResourceLocation.GetCollectionNameForResource(typeof(TResource));

            byte[] data = PreferredFormat == ContentType.ResourceFormat.Xml ?
                FhirSerializer.SerializeResourceToXmlBytes(resource) :
                FhirSerializer.SerializeResourceToJsonBytes(resource);

            var req = createRequest(ResourceLocation.Build(FhirEndpoint, collection, id), false);

            req.Method = "POST";
            req.ContentType = contentType;
            setRequestBody(req, data);

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return null;
            else
                return (OperationOutcome)parseResource();
        }


     
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

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
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

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
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
            setRequestBody(req, data);

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return parseBundle();
            else
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
            setRequestBody(req, data);

            doRequest(req);

            if (LastResponseDetails.Result == HttpStatusCode.OK)
                return true;
            else
                return false;
        }


        public ContentType.ResourceFormat PreferredFormat { get; set; }

        private HttpWebRequest createRequest(ResourceLocation location, bool forBundle)
        {
            //if( PreferredFormat == ContentType.ResourceFormat.Json )
            //    endpoint = addParam(endpoint, ContentType.FORMAT_PARAM, ContentType.FORMAT_PARAM_JSON);
            //if (PreferredFormat == ContentType.ResourceFormat.Xml)
            //    endpoint = addParam(endpoint, ContentType.FORMAT_PARAM, ContentType.FORMAT_PARAM_XML);

            var req = (HttpWebRequest)HttpWebRequest.Create(location.ToUri());
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

        private static string addParam(string original, string paramName, string paramValue)
        {
            string url = original;

            if (!original.Contains("?"))
                url += "?";
            else
                url += "&";

            url += Uri.EscapeDataString(paramName) + "=" + Uri.EscapeDataString(paramValue);

            return url;
        }


        public ResponseDetails LastResponseDetails { get; private set; }

        private void doRequest(HttpWebRequest req)
        {
            HttpWebResponse response = (HttpWebResponse)req.GetResponseNoEx();

            try
            {
                LastResponseDetails = ResponseDetails.FromHttpWebResponse(response);
            }
            finally
            {
#if !NETFX_CORE
                response.Close();
#endif
            }
        }


        private OperationOutcome tryParseOperationOutcome()
        {
            Resource body = null;

            try
            {
                body = parseResource();
            }
            catch
            {
                return null;
            }

            return body as OperationOutcome;
        }


        private Resource parseResource()
        {
            string data = LastResponseDetails.BodyAsString();
            string contentType = LastResponseDetails.ContentType;

            ErrorList parseErrors = new ErrorList();
            Resource result;

            ContentType.ResourceFormat format = ContentType.GetResourceFormatFromContentType(contentType);

            switch (format)
            {
                case ContentType.ResourceFormat.Json:
                    result = FhirParser.ParseResourceFromJson(data, parseErrors);
                    break;
                case ContentType.ResourceFormat.Xml:
                    result = FhirParser.ParseResourceFromXml(data, parseErrors);
                    break;
                default:
                    throw new FhirParseException("Cannot decode resource: unrecognized content type");
            }

            if (parseErrors.Count() > 0)
                throw new FhirParseException("Failed to parse the resource data", parseErrors, data);

            return result;
        }


        private Bundle parseBundle()
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
                    throw new FhirParseException("Cannot decode bundle: unrecognized content type");
            }

            if (parseErrors.Count() > 0)
                throw new FhirParseException("Failed to parse the bundle data: ", parseErrors, data);

            return result;
        }

        private void setRequestBody(HttpWebRequest request, byte[] data)
        {
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
