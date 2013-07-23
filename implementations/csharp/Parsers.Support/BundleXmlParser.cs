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
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Parsers
{
    internal static class BundleXmlParser
    {
        public const string XATOM_FEED = "feed";
        public const string XATOM_DELETED_ENTRY = "deleted-entry";
        public const string XATOM_DELETED_WHEN = "when";
        public const string XATOM_DELETED_REF = "ref";
        public const string XATOM_LINK = "link";
        public const string XATOM_LINK_REL = "rel";
        public const string XATOM_LINK_HREF = "href";
        public const string XATOM_CONTENT_BINARY = "Binary";
        public const string XATOM_CONTENT_TYPE = "type";
        public const string XATOM_CONTENT_BINARY_TYPE = "contentType";
        public const string XATOM_TITLE = "title";
        public const string XATOM_UPDATED = "updated";
        public const string XATOM_ID = "id";
        public const string XATOM_ENTRY = "entry";
        public const string XATOM_PUBLISHED = "published";
        public const string XATOM_AUTHOR = "author";
        public const string XATOM_AUTH_NAME = "name";
        public const string XATOM_AUTH_URI = "uri";
        public const string XATOM_CATEGORY = "category";
        public const string XATOM_CAT_TERM = "term";
        public const string XATOM_CAT_SCHEME = "scheme";
        public const string XATOM_CAT_LABEL = "label";
        public const string XATOM_CONTENT = "content";
        public const string XATOM_SUMMARY = "summary";
        public const string XATOM_TOTALRESULTS = "totalResults";

        public static string ATOM_CATEGORY_RESOURCETYPE_NS = "http://hl7.org/fhir/resource-types";
        public static string ATOMPUB_TOMBSTONES_NS = "http://purl.org/atompub/tombstones/1.0";
        public static string ATOMPUB_NS = "http://www.w3.org/2005/Atom";
        public static string OPENSEARCH_NS = "http://a9.com/-/spec/opensearch/1.1";

        public static readonly XNamespace XATOMNS = ATOMPUB_NS;
        public static readonly XNamespace XTOMBSTONE = ATOMPUB_TOMBSTONES_NS;
        public static readonly XNamespace XFHIRNS = Util.FHIRNS;
        public static readonly XNamespace XOPENSEARCHNS = OPENSEARCH_NS;



        internal static Bundle Load(XmlReader reader, ErrorList errors)
        {
            XElement feed;

            try
            {
                feed = XDocument.Load(reader).Root;
            }
            catch (Exception exc)
            {
                errors.Add("Exception while loading feed: " + exc.Message);
                return null;
            }

            Bundle result;

            try
            {
                result = new Bundle()
                {
                    Title = Util.StringValueOrNull(feed.Element(XATOMNS + XATOM_TITLE)),
                    LastUpdated = Util.InstantOrNull(feed.Element(XATOMNS + XATOM_UPDATED)),
                    Id = Util.UriValueOrNull(feed.Element(XATOMNS + XATOM_ID)),
                    Links = getLinks(feed.Elements(XATOMNS + XATOM_LINK)),
                    AuthorName = feed.Elements(XATOMNS + XATOM_AUTHOR).Count() == 0 ? null :
                            Util.StringValueOrNull(feed.Element(XATOMNS + XATOM_AUTHOR)
                                .Element(XATOMNS + XATOM_AUTH_NAME)),
                    AuthorUri = feed.Elements(XATOMNS + XATOM_AUTHOR).Count() == 0 ? null :
                            Util.StringValueOrNull(feed.Element(XATOMNS + XATOM_AUTHOR)
                                .Element(XATOMNS + XATOM_AUTH_URI)),
                    TotalResults = Util.IntValueOrNull(feed.Element(XOPENSEARCHNS + XATOM_TOTALRESULTS))
                };
            }
            catch (Exception exc)
            {
                errors.Add("Exception while parsing xml feed attributes: " + exc.Message,
                    String.Format("Feed '{0}'", feed.Element(XATOMNS + XATOM_ID).Value));
                return null;
            }

            result.Entries = loadEntries(feed.Elements().Where(elem =>
                        (elem.Name == XATOMNS + XATOM_ENTRY ||
                         elem.Name == XTOMBSTONE + XATOM_DELETED_ENTRY)), result, errors);

            errors.AddRange(result.Validate());

            return result;
        }

        internal static Bundle Load(string xml, ErrorList errors)
        {
            return Load(Util.XmlReaderFromString(xml), errors);
        }

        private static ManagedEntryList loadEntries(IEnumerable<XElement> entries, Bundle parent, ErrorList errors)
        {
            var result = new ManagedEntryList(parent);

            foreach (var entry in entries)
            {
                var loaded = loadEntry(entry, errors);
                if (entry != null) result.Add(loaded);
            }

            return result;
        }

        internal static BundleEntry LoadEntry(string xml, ErrorList errors)
        {
            return LoadEntry(Util.XmlReaderFromString(xml), errors);
        }

        internal static BundleEntry LoadEntry(XmlReader reader, ErrorList errors)
        {
            XElement entry;

            try
            {
                entry = XDocument.Load(reader).Root;
            }
            catch (Exception exc)
            {
                errors.Add("Exception while loading entry: " + exc.Message);
                return null;
            }

            return loadEntry(entry, errors);
        }

        private static BundleEntry loadEntry(XElement entry, ErrorList errors)
        {
            BundleEntry result;

            errors.DefaultContext = "An atom entry";

            try
            {
                if (entry.Name == XTOMBSTONE + XATOM_DELETED_ENTRY)
                {
                    result = new DeletedEntry();
                    result.Id = Util.UriValueOrNull(entry.Attribute(XATOM_DELETED_REF));
                    if (result.Id != null) errors.DefaultContext = String.Format("Entry '{0}'", result.Id.ToString());
                }
                else
                {
                    XElement content = entry.Element(XATOMNS + XATOM_CONTENT);
                    var id = Util.UriValueOrNull(entry.Element(XATOMNS + XATOM_ID));

                    if (id != null) errors.DefaultContext = String.Format("Entry '{0}'", id.ToString());

                    if (content != null)
                    {
                        var parsed = getContents(content, errors);
                        if (parsed != null)
                            result = ResourceEntry.Create(parsed);
                        else
                            return null;
                    }
                    else
                        throw new InvalidOperationException("BundleEntry has empty content: cannot determine Resource type in parser.");

                    result.Id = id;
                }

                result.Links = getLinks(entry.Elements(XATOMNS + XATOM_LINK));
                result.Tags = TagListParser.ParseTags(entry.Elements(XATOMNS + XATOM_CATEGORY));

                if (result is DeletedEntry)
                {
                    ((DeletedEntry)result).When = Util.InstantOrNull(entry.Attribute(XATOM_DELETED_WHEN));
                }
                else
                {
                    ResourceEntry re = (ResourceEntry)result;
                    re.Title = Util.StringValueOrNull(entry.Element(XATOMNS + XATOM_TITLE));
                    re.LastUpdated = Util.InstantOrNull(entry.Element(XATOMNS + XATOM_UPDATED));
                    re.Published = Util.InstantOrNull(entry.Element(XATOMNS + XATOM_PUBLISHED));
                    re.AuthorName = entry.Elements(XATOMNS + XATOM_AUTHOR).Count() == 0 ? null :
                                Util.StringValueOrNull(entry.Element(XATOMNS + XATOM_AUTHOR)
                                    .Element(XATOMNS + XATOM_AUTH_NAME));
                    re.AuthorUri = entry.Elements(XATOMNS + XATOM_AUTHOR).Count() == 0 ? null :
                                Util.StringValueOrNull(entry.Element(XATOMNS + XATOM_AUTHOR)
                                    .Element(XATOMNS + XATOM_AUTH_URI));

                    
                }
            }
            catch (Exception exc)
            {
                errors.Add("Exception while reading entry: " + exc.Message);
                return null;
            }
            finally
            {
                errors.DefaultContext = null;
            }

            return result;
        }


        private static UriLinkList getLinks(IEnumerable<XElement> links)
        {
            return new UriLinkList(
                links.Select(el => new UriLinkEntry
                {
                    Rel = Util.StringValueOrNull(el.Attribute(XATOM_LINK_REL)),
                    Uri = Util.UriValueOrNull(el.Attribute(XATOM_LINK_HREF))
                }));
        }


        private static Resource getContents(XElement content, ErrorList errors)
        {
            string contentType = Util.StringValueOrNull(content.Attribute(XATOM_CONTENT_TYPE));

            if (contentType != "text/xml")
            {
                errors.Add("Entry should have contents of type 'text/xml'");
                return null;
            }

            //TODO: This is quite inefficient. The Xml parser has just parsed this
            //entry's Resource from text, now we are going to serialize it to as string
            //just to read from it again using a Xml parser. But that is what my
            //parser takes as input, so no choice for now...
            return FhirParser.ParseResourceFromXml(content.FirstNode.ToString(), errors);
        }    
    }
}
