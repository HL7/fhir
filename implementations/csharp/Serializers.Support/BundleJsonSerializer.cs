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

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using Hl7.Fhir.Model;
using System.Xml.Linq;
using Hl7.Fhir.Parsers;
using System.IO;
using Hl7.Fhir.Serializers;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Serializers
{
    internal static class BundleJsonSerializer
    {
        public static void WriteTo(Bundle bundle, JsonWriter writer, bool summary = false)
        {
            if (bundle == null) throw new ArgumentException("Bundle cannot be null");

            JObject result = new JObject();

            if (!String.IsNullOrWhiteSpace(bundle.Title))
                result.Add(new JProperty(BundleXmlParser.XATOM_TITLE, bundle.Title));
            if (Util.UriHasValue(bundle.Id)) result.Add(new JProperty(BundleXmlParser.XATOM_ID, bundle.Id));
            if (bundle.LastUpdated != null) 
                result.Add(new JProperty(BundleXmlParser.XATOM_UPDATED, bundle.LastUpdated));

            if (!String.IsNullOrWhiteSpace(bundle.AuthorName))
                result.Add(jsonCreateAuthor(bundle.AuthorName, bundle.AuthorUri));
            if (bundle.TotalResults != null) result.Add(new JProperty(BundleXmlParser.XATOM_TOTALRESULTS, bundle.TotalResults.ToString()));
            if (bundle.Links.Count > 0)
                result.Add(new JProperty(BundleXmlParser.XATOM_LINK, jsonCreateLinkArray(bundle.Links)));

            var entryArray = new JArray();

            foreach (var entry in bundle.Entries)
                entryArray.Add(createEntry(entry,summary));

            result.Add(new JProperty(BundleXmlParser.XATOM_ENTRY, entryArray));

            result.WriteTo(writer);
        }



        public static void WriteTo(BundleEntry entry, JsonWriter writer, bool summary = false)
        {
            if (entry == null) throw new ArgumentException("Entry cannot be null");

            var result = createEntry(entry, summary);

            result.WriteTo(writer);
        }


        private static JObject createEntry(BundleEntry entry, bool summary)
        {
            JObject result = new JObject();

            if (entry is ResourceEntry)
            {
                ResourceEntry re = (ResourceEntry)entry;
                if (!String.IsNullOrEmpty(re.Title)) result.Add(new JProperty(BundleXmlParser.XATOM_TITLE, re.Title));
                if (Util.UriHasValue(entry.Id)) result.Add(new JProperty(BundleXmlParser.XATOM_ID, entry.Id.ToString()));

                if (re.LastUpdated != null) result.Add(new JProperty(BundleXmlParser.XATOM_UPDATED, re.LastUpdated));
                if (re.Published != null) result.Add(new JProperty(BundleXmlParser.XATOM_PUBLISHED, re.Published));

                if (!String.IsNullOrWhiteSpace(re.AuthorName))
                    result.Add(jsonCreateAuthor(re.AuthorName, re.AuthorUri));
            }
            else
            {
                DeletedEntry de = (DeletedEntry)entry;
                if (de.When != null) result.Add(new JProperty(BundleJsonParser.JATOM_DELETED, de.When));
                if (Util.UriHasValue(entry.Id)) result.Add(new JProperty(BundleXmlParser.XATOM_ID, entry.Id.ToString()));
            }

            if(entry.Links != null && entry.Links.Count() > 0)
                result.Add(new JProperty(BundleXmlParser.XATOM_LINK, jsonCreateLinkArray(entry.Links)));

            if (entry.Tags != null && entry.Tags.Count() > 0) 
                result.Add(TagListSerializer.CreateTagCategoryPropertyJson(entry.Tags));

            if(entry is ResourceEntry)
            {
                ResourceEntry re = (ResourceEntry)entry;
                if (re.Resource != null)
                    result.Add(new JProperty(BundleXmlParser.XATOM_CONTENT, 
                        getContentsAsJObject(re.Resource, summary)));

                // Note: this is a read-only property, so it is serialized but never parsed
                if (entry.Summary != null)
                    result.Add(new JProperty(BundleXmlParser.XATOM_SUMMARY, entry.Summary));
            }

            return result;
        }



        private static JProperty jsonCreateAuthor(string name, string uri)
        {
            JObject author = new JObject();

            if (!String.IsNullOrEmpty(name))
                author.Add(new JProperty(BundleXmlParser.XATOM_AUTH_NAME, name));
            if (!String.IsNullOrWhiteSpace(uri))
                author.Add(new JProperty(BundleXmlParser.XATOM_AUTH_URI, uri));

            return new JProperty(BundleXmlParser.XATOM_AUTHOR, new JArray(author));
        }

        private static JArray jsonCreateLinkArray(UriLinkList links)
        {
            var result = new JArray();

            foreach (var l in links)
                if (l.Uri != null)
                    result.Add(jsonCreateLink(l.Rel, l.Uri));

            return result;
        }

        private static JObject jsonCreateLink(string rel, Uri link)
        {
            return new JObject(
                new JProperty(BundleXmlParser.XATOM_LINK_REL, rel),
                new JProperty(BundleXmlParser.XATOM_LINK_HREF, link.ToString()));
        }

        private static JObject getContentsAsJObject(Resource resource, bool summary)
        {
            StringWriter w = new StringWriter();

            //TODO: This would be much more efficient if we could serialize
            //the resource to a JObject directly
            FhirSerializer.SerializeResource(resource, new JsonTextWriter(w), summary);

            JsonTextReader reader = Util.JsonReaderFromString(w.ToString());
            reader.DateParseHandling = DateParseHandling.None;
            return JObject.Load(reader);
        }
    }
}
