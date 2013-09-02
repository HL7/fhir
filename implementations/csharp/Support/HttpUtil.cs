﻿/*
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


using Hl7.Fhir.Model;
using Hl7.Fhir.Parsers;
using Hl7.Fhir.Serializers;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;

namespace Hl7.Fhir.Support
{
    public static class HttpUtil
    {
        public const string CONTENTLOCATION = "Content-Location";
        public const string LOCATION = "Location";
        public const string LASTMODIFIED = "Last-Modified";
        public const string CATEGORY = "Category";


        public static byte[] ReadAllFromStream(Stream s, int contentLength)
        {
            if (contentLength == 0) return null;

            int bufferSize = contentLength < 4096 ? contentLength : 4096;

            byte[] byteBuffer = new byte[bufferSize];
            MemoryStream buffer = new MemoryStream();
            int readLen;

            do
            {
                readLen = s.Read(byteBuffer, 0, byteBuffer.Length);
                if (readLen > 0) buffer.Write(byteBuffer, 0, readLen);
            } while (buffer.Length < contentLength);

            return buffer.ToArray();
        }


        private const string TAGSCHEME = "\"" + Tag.FHIRTAGNS + "\"";

        public static IEnumerable<Tag> ParseCategoryHeader(string value)
        {
            if (String.IsNullOrEmpty(value)) return new List<Tag>();

            var categories = value.Split(new string[] { "," },StringSplitOptions.RemoveEmptyEntries)
                                .Select(c => c.Trim());

            List<Tag> result = null;

            foreach (var category in categories)
            {
                var values = category.Split(new string[] { ";" }, StringSplitOptions.RemoveEmptyEntries)
                                .Select(c => c.Trim());

                if (values.Count() == 3)
                {
                    var tagUri = values.First();
                    var pars = values.Skip(1).Select( v =>
                        { 
                            var vsplit = v.Split('=');
                            var item1 = vsplit[0].Trim();
                            var item2 = vsplit.Length > 1 ? vsplit[1].Trim() : null;
                            return new Tuple<string,string>(item1,item2);
                        });

                    if (pars.Any(t => t.Item1 == "scheme" && t.Item2 == TAGSCHEME))
                    {
                        if(result == null) result = new List<Tag>();

                        var newTag = new Tag()
                        {
                            Label = pars.Where(t => t.Item1 == "label").Select(t => t.Item2).FirstOrDefault(),
                            Uri = new Uri(tagUri, UriKind.RelativeOrAbsolute)
                        };
                        result.Add(newTag);
                    }
                }
            }

            return result;
        }

        public static string BuildCategoryHeader(IEnumerable<Tag> tags)
        {
            var result = new List<string>();
            foreach(var tag in tags)
            {                
                StringBuilder sb = new StringBuilder();

                if (Util.UriHasValue(tag.Uri))
                {
                    var uri = tag.Uri.ToString();
                    if (uri.Contains(",") || uri.Contains(";"))
                        throw new ArgumentException("Found tag containing ',' or ';' - this will produce an inparsable Category header");
                    sb.Append(tag.Uri.ToString());
                }

                if (!String.IsNullOrEmpty(tag.Label))
                {
                    if (tag.Label.Contains(",") || tag.Label.Contains(";"))
                        throw new ArgumentException("Found tag containing ',' or ';' - this will produce an inparsable Category header");

                    sb.AppendFormat("; label={0}", tag.Label);
                }

                sb.AppendFormat("; scheme=\"{0}\"", Tag.FHIRTAGNS);
                result.Add(sb.ToString());
            }

            return String.Join(", ", result);
        }



        public static ResourceEntry SingleResourceResponse(string body, byte[] data, string contentType, 
            string requestUri=null, string location=null,
            string category=null, string lastModified=null )
        {
            Resource resource = null;

            if (body != null)
                resource = parseBody<Resource>(body, contentType,
                    (b, e) => FhirParser.ParseResourceFromXml(b, e),
                    (b, e) => FhirParser.ParseResourceFromJson(b, e));
            else
                resource = Util.MakeBinary(data, contentType);

            ResourceEntry result = ResourceEntry.Create(resource);
            string versionIdInRequestUri = null;

            if (!String.IsNullOrEmpty(requestUri))
            {
                ResourceLocation reqLoc = new ResourceLocation(requestUri);
                versionIdInRequestUri = reqLoc.VersionId;
                ResourceLocation idLoc = new ResourceLocation(reqLoc.ServiceUri);
                idLoc.Collection = reqLoc.Collection;
                idLoc.Id = reqLoc.Id;
                result.Id = idLoc.ToUri();
            }

            if (!String.IsNullOrEmpty(location))
                result.SelfLink = new Uri(location, UriKind.Absolute);
            else
            {
                // Try to get the SelfLink from the requestUri (might contain specific version id)
                if (!String.IsNullOrEmpty(versionIdInRequestUri))
                {
                    var rl = new ResourceLocation(result.Id);
                    rl.VersionId = versionIdInRequestUri;
                    result.SelfLink = rl.ToUri();
                }
            }

            if(!String.IsNullOrEmpty(lastModified))
                result.LastUpdated = DateTimeOffset.Parse(lastModified);

            if (!String.IsNullOrEmpty(category))
                result.Tags = ParseCategoryHeader(category);

            result.Title = "A " + resource.GetType().Name + " resource";
            
            return result;
        }



        public static Bundle BundleResponse(string body, string contentType)
        {
            return parseBody<Bundle>(body, contentType, 
                (b,e) => FhirParser.ParseBundleFromXml(b, e), 
                (b,e) => FhirParser.ParseBundleFromJson(b, e));            
        }


        public static IList<Tag> TagListResponse(string body, string contentType)
        {
            return parseBody(body, contentType,
                (b, e) => FhirParser.ParseTagListFromXml(b, e),
                (b, e) => FhirParser.ParseTagListFromJson(b, e));
        }

        public static byte[] TagListBody(IEnumerable<Tag> tags, ContentType.ResourceFormat format)
        {
            return serializeBody<IEnumerable<Tag>>(tags, format,
                t => FhirSerializer.SerializeTagListToXmlBytes(tags),
                t => FhirSerializer.SerializeTagListToJsonBytes(tags));
        }

        private static byte[] serializeBody<T>(T data, ContentType.ResourceFormat format, Func<T, byte[]> xmlSerializer, Func<T, byte[]> jsonSerializer)
        {
            var isBundle = data is Bundle;

            if (format == ContentType.ResourceFormat.Json)
                return jsonSerializer(data); // FhirSerializer.SerializeBundleToJsonBytes(bundle);
            else if (format == ContentType.ResourceFormat.Xml)
                return xmlSerializer(data);   // FhirSerializer.SerializeBundleToXmlBytes(bundle);
            else
                throw new ArgumentException("Cannot encode a batch into format " + format.ToString());

        }

        private static T parseBody<T>(string body, string contentType, 
                    Func<string, ErrorList, T> xmlParser, Func<string, ErrorList, T> jsonParser) where T : class
        {
            ErrorList parseErrors = new ErrorList();
            T result = null;

            ContentType.ResourceFormat format = ContentType.GetResourceFormatFromContentType(contentType);

            switch (format)
            {
                case ContentType.ResourceFormat.Json:
                    result = jsonParser(body, parseErrors); 
                    break;
                case ContentType.ResourceFormat.Xml:
                    result = xmlParser(body, parseErrors);
                    break;
                default:
                    throw new FhirParseException("Cannot decode body: unrecognized content type " + contentType);
            }

            if (parseErrors.Count() > 0)
                throw new FhirParseException("Failed to parse body: " + parseErrors.ToString(), parseErrors, body);

            return result;
        }

    }
}
