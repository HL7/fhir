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
using System.IO;
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Model
{
    [FhirType]
    public class TagList
    {
        public TagList()
        {
            Category = new List<Tag>();
        }

        public TagList(IEnumerable<Tag> tags)
        {
            this.Category = new List<Tag>(tags);
        }

        public List<Tag> Category { get; set; }
    }

    [FhirType]
    public class Tag
    {
        public const string FHIRTAGNS = "http://hl7.org/fhir/tag";
        public static readonly Uri FHIRTAGSCHEME = new Uri(FHIRTAGNS, UriKind.Absolute);

        public string Term { get; private set; }
        public string Label { get; private set; }
        public Uri Scheme { get; private set; }

        private Tag()
        {
        }

        public Tag(string term)
        {
            this.Term = term;
        }

        public Tag(string term, Uri scheme=null, string label=null) : this(term)
        {
            this.Scheme = scheme;
            this.Label = label;
        }

        public Tag(string term, string scheme = null, string label = null)
            : this(term, scheme==null ? null : new Uri(scheme,UriKind.RelativeOrAbsolute), label)
        {
        }


        public ErrorList Validate()
        {
            ErrorList result = new ErrorList();

            if (Label == null)
                result.Add("Tag label cannot be null");

            return result;
        }

        public override bool Equals(object obj)
        {
            // Check for null values and compare run-time types.
            if (obj == null || GetType() != obj.GetType())
                return false;

            var t = (Tag)obj;
            return String.Equals(this.Term, t.Term) && Uri.Equals(this.Scheme, t.Scheme);
        }

        public override int GetHashCode()
        {
            int hash = 0;

            if (Term != null) hash ^=  Term.GetHashCode();
            if (Scheme != null) hash ^= Scheme.GetHashCode();

            return hash;
        }
    }


    public static class TagListExtensions
    {
        public static IEnumerable<Tag> FindByTerm(this IEnumerable<Tag> tags, string term)
        {
            return tags.Where(e => String.Equals(term, e.Term));
        }

        public static IEnumerable<Tag> FindByTerm(this IEnumerable<Tag> tags, string term, Uri scheme)
        {
            return tags.Where(e => String.Equals(term, e.Term) && Uri.Equals(scheme,e.Scheme));
        }

        public static IEnumerable<Tag> FindByTerm(this IEnumerable<Tag> tags, string term, string scheme)
        {
            var schemeUri = scheme != null ? new Uri(scheme, UriKind.RelativeOrAbsolute) : null;

            return FindByTerm(tags, term, schemeUri);
        }

        public static IEnumerable<Tag> FindByScheme(this IEnumerable<Tag> tags, Uri scheme)
        {
            return tags.Where(e => Uri.Equals(e.Scheme, scheme));
        }

        public static bool HasTag(this IEnumerable<Tag> tags, string term)
        {
            return FindByTerm(tags, term).Count() > 0;
        }

        public static bool HasTag(this IEnumerable<Tag> tags, string term, Uri scheme)
        {
            return FindByTerm(tags, term, scheme).Count() > 0;   
        }

        public static bool HasTag(this IEnumerable<Tag> tags, string term, string scheme)
        {
            return FindByTerm(tags, term, scheme).Count() > 0;
        }

        public static IEnumerable<Tag> FilterFhirTags(this IEnumerable<Tag> tags)
        {
            var fhirScheme = new Uri(Tag.FHIRTAGNS);
            return tags.Where(e => Uri.Equals(e.Scheme, fhirScheme));
        }

        public static IEnumerable<Tag> Merge(this IEnumerable<Tag> tags, IEnumerable<Tag> that)
        {
            var result = new List<Tag>(tags.Remove(that));

            result.AddRange(that);

            return result;
        }

        public static IEnumerable<Tag> Remove(this IEnumerable<Tag> tags, IEnumerable<Tag> that)
        {
            var result = new List<Tag>();

            result.AddRange(tags);

            if (that != null)
                result.RemoveAll(t => that.HasTag(t.Term, t.Scheme));

            return result;
        }

        public static ErrorList Validate(this IEnumerable<Tag> tags)
        {
            ErrorList result = new ErrorList();

            foreach (var tag in tags) result.AddRange(tag.Validate());

            return result;
        }
    }
}

