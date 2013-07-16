/*
  IList<Tag>
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
using Newtonsoft.Json;
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Model
{
    public class Tag
    {
        public const string FHIRTAGNS = "http://hl7.org/fhir/tag";

        public Uri Uri { get; set; }

        public string Label { get; set; }

        public Tag()
        {
        }

        public Tag(Uri uri, string label)
        {
            this.Uri = uri;
            this.Label = label;
        }

        public Tag(string uri, string label)
            : this(new Uri(uri,UriKind.Absolute), label)
        {
        }


        public ErrorList Validate()
        {
            ErrorList result = new ErrorList();

            if (Uri == null)
                result.Add("Tag Uri cannot be null");

            if (Label == null)
                result.Add("Tag Label cannot be null");

            return result;
        }

        public override bool Equals(object obj)
        {
            // Check for null values and compare run-time types.
            if (obj == null || GetType() != obj.GetType())
                return false;

            var t = (Tag)obj;
            return (Uri.Equals(this.Uri, t.Uri) && this.Label == t.Label);
        }

        public override int GetHashCode()
        {
            int hash = 0;

            if (Uri != null) hash ^= Uri.GetHashCode();
            if (Label != null) hash ^= Label.GetHashCode();

            return hash;
        }
    }


    public static class TagListExtensions
    {
        public static IEnumerable<Tag> FindByUri(this IEnumerable<Tag> tags, Uri uri, string value = null)
        {
            if (value == null)
                return tags.Where(e => Uri.Equals(e.Uri, uri));
            else
                return tags.Where(tag => Uri.Equals(tag.Uri, uri) && tag.Label == value);
        }

        public static IEnumerable<Tag> FindByUri(this IEnumerable<Tag> tags, string uri, string value = null)
        {
            return FindByUri(tags, new Uri(uri, UriKind.Absolute), value);
        }

        public static bool HasTag(this IEnumerable<Tag> tags, string uri, string value = null)
        {
            return HasTag(tags, new Uri(uri, UriKind.Absolute), value);
        }

        public static bool HasTag(this IEnumerable<Tag> tags, Uri uri, string value = null)
        {
            if(value == null)
                return tags.Any(tag => Uri.Equals(tag.Uri, uri));
            else
                return tags.Any(tag => Uri.Equals(tag.Uri, uri) && tag.Label == value);
        }

        public static ErrorList Validate(this IEnumerable<Tag> tags)
        {
            ErrorList result = new ErrorList();

            foreach (var tag in tags) result.AddRange(tag.Validate());

            return result;
        }
    }
}

