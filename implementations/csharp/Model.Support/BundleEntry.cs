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
using Hl7.Fhir.Model;
using System.IO;
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Model
{
    public abstract class BundleEntry
    {
        public BundleEntry()
        {
            Links = new UriLinkList();
        }

        public Uri Id { get; set; }
        public Bundle Parent { set; get; }
        public UriLinkList Links { get; set; }
        public IEnumerable<Tag> Tags { get; set; }

        public Uri SelfLink
        {
            get { return Links.SelfLink; }
            set { Links.SelfLink = value; }
        }

        public virtual ErrorList Validate()
        {
            ErrorList errors = new ErrorList();
            errors.DefaultContext = String.Format("Entry '{0}'", Id);

            // SelfLink is no longer mandatory since a resource may not yet
            // have an established resource URL when using Atom to post batches
            // of new resources.
            //if (SelfLink == null || SelfLink.ToString() == String.Empty)
            //    errors.Add("Entry must have a link of type 'self'", context);

            if (!Util.UriHasValue(Id))
                errors.Add("Entry must have an id");
            else
                if (!Id.IsAbsoluteUri)
                    errors.Add("Entry id must be an absolute URI");

            if (Util.UriHasValue(SelfLink) && !SelfLink.IsAbsoluteUri)
                errors.Add("Entry selflink must be an absolute URI");

            if (Links.FirstLink != null || Links.LastLink != null || Links.PreviousLink != null || Links.NextLink != null)
                errors.Add("Paging links can only be used on feeds, not entries");

            if( Tags != null )
                errors.AddRange(Tags.Validate());

            return errors;
        }

        /// <summary>
        /// Read-only property getting a summary from a Resource or a descriptive text in other cases.
        /// </summary>
        public abstract string Summary { get; }
    }


    public class DeletedEntry : BundleEntry
    {
        public DateTimeOffset? When { get; set; }

        public override string Summary
        {
            get
            {
                return "<div xmlns='http://www.w3.org/1999/xhtml'>This resource has been deleted " +
                    "on " + When.ToString() + "</div>";
            }
        }

        public override ErrorList Validate()
        {
            var errors = base.Validate();

            if (When == null)
                errors.Add("A DeletedEntry must have a non-null deletion time (When)");

            return errors;
        }
    }


    public class ResourceEntry<T> : ResourceEntry where T : Resource, new()
    {
        public new T Resource
        { 
            get { return (T)((ResourceEntry)this).Resource; }
            set { ((ResourceEntry)this).Resource = value; }
        }

        public static ResourceEntry<T> Create(T resource)
        {
            var result = new ResourceEntry<T>();

            result.Resource = resource;

            return result;
        }
    }

    public abstract class ResourceEntry : BundleEntry
    {
        public Resource Resource { get; set; }

        public string Title { get; set; }

        public DateTimeOffset? LastUpdated { get; set; }
        public DateTimeOffset? Published { get; set; }
        public string AuthorName { get; set; }
        public string AuthorUri { get; set; }


        /// <summary>
        /// Creates an instance of a typed ResourceEntry&lt;T&gt;, based on the actual type of the passed resource parameter
        /// </summary>
        /// <param name="resource"></param>
        /// <returns></returns>
        public static ResourceEntry Create(Resource resource)
        {
            Type typedREType = typeof(ResourceEntry<>).MakeGenericType(resource.GetType());
            var result = (ResourceEntry)Activator.CreateInstance(typedREType);
            result.Resource = resource;

            return result;
        }


        public override ErrorList Validate()
        {
            ErrorList errors = base.Validate();

            if (String.IsNullOrWhiteSpace(Title))
                errors.Add("Entry must contain a title");

            if (String.IsNullOrWhiteSpace(AuthorName) && String.IsNullOrEmpty(Parent.AuthorName))
                errors.Add("Entry, or its parent feed, must have at least one author with a name");

            if (LastUpdated == null)
                errors.Add("Entry must have an updated date");

            if (Resource == null)
                errors.Add("Entry must contain Resource data, Content may not be null");
          //  else
             //   errors.AddRange(Resource.Validate());
            
            return errors;
        } 

        /// <summary>
        /// Read-only. Returns the summary text from a Resource.
        /// </summary>
        public override string Summary
        {
            get
            {
                if (Resource is Binary)
                    return string.Format("<div xmlns='http://www.w3.org/1999/xhtml'>" +
                        "Binary content (mediatype {0})</div>", ((Binary)Resource).ContentType);
                else if (Resource != null && Resource.Text != null)
                    return Resource.Text.Div;
                else
                    return null;
            }
        }
    }
}
