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
    internal static class TagListSerializer
    {
        public const string TAGLIST_ROOT = "taglist";

        internal static void SerializeTagList(IEnumerable<Tag> list, JsonWriter writer)
        {
            JObject jTagList = new JObject(
                new JProperty(TAGLIST_ROOT,
                    new JObject(CreateTagCategoryPropertyJson(list))));

            jTagList.WriteTo(writer);
        }


        internal static void SerializeTagList(IEnumerable<Tag> list, XmlWriter writer)
        {
            XElement xTagList = new XElement(BundleXmlParser.XFHIRNS + TAGLIST_ROOT);

            foreach (var tag in list)
                xTagList.Add(CreateTagCategoryPropertyXml(tag, useAtomNs: false));

            xTagList.WriteTo(writer);
        }

        internal static XElement CreateTagCategoryPropertyXml(Tag tag, bool useAtomNs = true)
        {           
            XElement result = useAtomNs ?
                new XElement(BundleXmlParser.XATOMNS + BundleXmlParser.XATOM_CATEGORY) :
                new XElement(BundleXmlParser.XFHIRNS + BundleXmlParser.XATOM_CATEGORY);

            if (Util.UriHasValue(tag.Uri))
                result.Add(new XAttribute(BundleXmlParser.XATOM_CAT_TERM, tag.Uri.ToString()));
            if (!String.IsNullOrEmpty(tag.Label))
                result.Add(new XAttribute(BundleXmlParser.XATOM_CAT_LABEL, tag.Label));
            result.Add(new XAttribute(BundleXmlParser.XATOM_CAT_SCHEME, Tag.FHIRTAGNS));

            return result;
        }

        internal static JProperty CreateTagCategoryPropertyJson(IEnumerable<Tag> tagList)
        {                       
            JArray jTags = new JArray();
            JProperty result = new JProperty(BundleXmlParser.XATOM_CATEGORY, jTags);

            foreach (Tag tag in tagList)
            {
                JObject jTag = new JObject();
                if(Util.UriHasValue(tag.Uri))
                    jTag.Add(new JProperty(BundleXmlParser.XATOM_CAT_TERM, tag.Uri.ToString()) );
                if(!String.IsNullOrEmpty(tag.Label))
                    jTag.Add(new JProperty(BundleXmlParser.XATOM_CAT_LABEL, tag.Label));
                jTag.Add(new JProperty(BundleXmlParser.XATOM_CAT_SCHEME, Tag.FHIRTAGNS));
                jTags.Add(jTag);
            }

            return result;
        }
    }
}
