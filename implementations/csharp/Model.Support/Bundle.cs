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
using System.Xml;
using Hl7.Fhir.Model;
using System.Xml.Linq;
using Hl7.Fhir.Parsers;
using System.IO;
using Newtonsoft.Json;
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Model
{
    public class Bundle
    {
        public string Title { get; set; }
        public DateTimeOffset? LastUpdated { get; set; }
        public Uri Id { get; set; }
        public UriLinkList Links { get; set; }

        public string AuthorName { get; set; }
        public string AuthorUri { get; set; }

        public int? TotalResults { get; set; }

        public ManagedEntryList Entries { get; internal set; }

        public Bundle()
        {
            Entries = new ManagedEntryList(this);
            Links = new UriLinkList();
        }

        public ErrorList Validate()
        {
            ErrorList errors = new ErrorList();
            string context = String.Format("Feed '{0}'", Id);

            if (String.IsNullOrWhiteSpace(Title))
                errors.Add("Feed must contain a title", context);

            if (!Util.UriHasValue(Id))
                errors.Add("Feed must have an id", context);
            else
                if (!Id.IsAbsoluteUri)
                    errors.Add("Feed id must be an absolute URI", context);

            if (LastUpdated == null)
                errors.Add("Feed must have a updated date", context);

            if (Links.SearchLink != null)
                errors.Add("Links with rel='search' can only be used on feed entries", context);

            if(Entries != null)
            {
                foreach(var entry in Entries)
                    errors.AddRange(entry.Validate());
            }

            return errors;
        }
    }


    public static class BundleIEnumerableExtensions
    {
        /// <summary>
        /// Filter ResourceEntries containing a specific Resource type. No DeletedEntries are returned.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <returns>All ResourceEntries containing the given type of resource, or an empty list if none were found.</returns>
        // Note: works for IEnumerable<ResourceEntry> too
        public static IEnumerable<ResourceEntry<T>> FilterByType<T>(this IEnumerable<BundleEntry> bes) where T: Resource
        {   
            return bes.Where(be => be is ResourceEntry<T>).Cast<ResourceEntry<T>>();
        }


        /// <summary>
        /// Filter all BundleEntries with the given id.
        /// </summary>
        /// <param name="id">Id of the Resource, as given in the BundleEntry's id</param>
        /// <returns>A list of BundleEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<BundleEntry> FilterById(this IEnumerable<BundleEntry> bes, Uri id)
        {
            return bes.Where(be => Uri.Equals(be.Id, id));
        }


        /// <summary>
        /// Filter all ResourceEntries with the given id.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="id">Id of the Resource, as given in the ResourceEntry's id</param>
        /// <returns>A list of typed ResourceEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<ResourceEntry<T>> FilterById<T>(this IEnumerable<ResourceEntry<T>> res, Uri id) where T : Resource
        {
            return res.Where(re => Uri.Equals(re.Id, id));
        }


        /// <summary>
        /// Find the BundleEntry with a given self-link id.
        /// </summary>
        /// <param name="self">Sel-link id of the Resource, as given in the BundleEntry's link with rel='self'.</param>
        /// <returns>A list of BundleEntries with the given self-link id, or an empty list if none were found.</returns>
        public static BundleEntry FindBySelfLink(this IEnumerable<BundleEntry> bes, Uri self)
        {
            return bes.FirstOrDefault(be => Uri.Equals(be.SelfLink, self));
        }


        /// <summary>
        /// Find the ResourceEntry with a given self-link id.
        /// </summary>
        /// <typeparam name="T">Type of Resource to find</typeparam>
        /// <param name="self">Sel-link id of the Resource, as given in the BundleEntry's link with rel='self'.</param>
        /// <returns>A list of ResourceEntries with the given self-link id. Returns
        /// the empty list if none were found.</returns>
        public static ResourceEntry<T> FindBySelfLink<T>(this IEnumerable<ResourceEntry<T>> res, Uri self) where T: Resource
        {
            return res.FirstOrDefault(re => Uri.Equals(re.SelfLink, self));
        }


        /// <summary>
        /// Filter all BundleEntries that have a given tag.
        /// </summary>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of BundleEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<BundleEntry> FilterByTag(this IEnumerable<BundleEntry> bes, Uri tag, string value=null )
        {
            return bes.Where(be => be.Tags.HasTag(tag,value));
        }


        /// <summary>
        /// Filter all ResourceEntries that have a given tag.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of typed ResourceEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<ResourceEntry<T>> FilterByTag<T>(this IEnumerable<ResourceEntry<T>> res, Uri tag, string value=null) where T : Resource
        {
            return res.Where(re => re.Tags.HasTag(tag,value));
        }
    }


    public abstract class BundleEntry
    {
        public BundleEntry()
        {
            Links = new UriLinkList();
        }

        public Uri Id { get; set; }
        public Bundle Parent { set; get; }
        public UriLinkList Links { get; set; }
        public IList<Tag> Tags { get; set; }

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


    public class ResourceEntry<T> : ResourceEntry where T : Resource
    {
        public new T Content
        { 
            get { return (T)((ResourceEntry)this).Content; }
            set { ((ResourceEntry)this).Content = value; }
        }
    }

    public abstract class ResourceEntry : BundleEntry
    {
        public Resource Content { get; set; }

        public string Title { get; set; }

        public DateTimeOffset? LastUpdated { get; set; }
        public DateTimeOffset? Published { get; set; }
        public string EntryAuthorName { get; set; }
        public string EntryAuthorUri { get; set; }

        public string AuthorName
        {
            get
            {
                if (!String.IsNullOrEmpty(EntryAuthorName))
                    return EntryAuthorName;
                else
                    return Parent.AuthorName;
            }
        }

        public string AuthorUri
        {
            get
            {
                if (!String.IsNullOrEmpty(EntryAuthorUri))
                    return EntryAuthorUri;
                else
                    return Parent.AuthorUri;
            }
        }

        public override ErrorList Validate()
        {
            ErrorList errors = base.Validate();

            if (String.IsNullOrWhiteSpace(Title))
                errors.Add("Entry must contain a title");

            if (String.IsNullOrWhiteSpace(AuthorName))
                errors.Add("Entry, or its parent feed, must have at least one author with a name");

            if (LastUpdated == null)
                errors.Add("Entry must have an updated date");

            if (Content == null)
                errors.Add("Entry must contain Resource data, Content may not be null");
            else
                errors.AddRange(Content.Validate());
            
            return errors;
        } 

        public override string Summary
        {
            get
            {
                if (Content is Binary)
                    return string.Format("<div xmlns='http://www.w3.org/1999/xhtml'>" +
                        "Binary content (mediatype {0})</div>", ((Binary)Content).ContentType);
                else if (Content != null && Content.Text != null)
                    return Content.Text.Div;
                else
                    return null;
            }
        }
    }
}
