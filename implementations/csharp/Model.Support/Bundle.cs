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
using Hl7.Fhir.Validation;
using System.ComponentModel.DataAnnotations;

namespace Hl7.Fhir.Model
{
    public class Bundle : Hl7.Fhir.Validation.IValidatableObject
    {
        public string Title { get; set; }
        public DateTimeOffset? LastUpdated { get; set; }
        public Uri Id { get; set; }
        public UriLinkList Links { get; set; }
        public IEnumerable<Tag> Tags { get; set; }

        public string AuthorName { get; set; }
        public string AuthorUri { get; set; }

        public int? TotalResults { get; set; }

        public ManagedEntryList Entries { get; set; }

        public Bundle()
        {
            Entries = new ManagedEntryList(this);
            Links = new UriLinkList();
        }

        public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            if (validationContext.ObjectType != typeof(Bundle))
                throw new ArgumentException("Can only validate instances of type Bundle");

            var value = (Binary)validationContext.ObjectInstance;
            var result = new List<ValidationResult>();

            if (String.IsNullOrWhiteSpace(Title))
                result.Add(new ValidationResult("Feed must contain a title"));

            if (!Util.UriHasValue(Id))
                result.Add(new ValidationResult("Feed must have an id"));
            else
                if (!Id.IsAbsoluteUri)
                    result.Add(new ValidationResult("Feed id must be an absolute URI"));

            if (LastUpdated == null)
                result.Add(new ValidationResult("Feed must have a updated date"));

            if (Links.SearchLink != null)
                result.Add(new ValidationResult("Links with rel='search' can only be used on feed entries"));

            if (Entries != null)
            {
                foreach (var entry in Entries.Where(e => e != null))
                {
                    //TODO: Validate nested entries
              //      Validator.TryValidateObject(entry, new ValidationContext(entry,null,null),result);
                }
            }

            if (!result.Any()) result.Add(ValidationResult.Success);

            return result;
        }

        public ErrorList Validate()
        {
            ErrorList errors = new ErrorList();
            string context = String.Format("Feed '{0}'", Id);


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
        public static IEnumerable<ResourceEntry<T>> ByResourceType<T>(this IEnumerable<BundleEntry> bes) where T: Resource, new()
        {   
            return bes.Where(be => be is ResourceEntry<T>).Cast<ResourceEntry<T>>();
        }

        /// <summary>
        /// Collect all ResourceEntries in the IEnumerable. No DeletedEntries are returned.
        /// </summary>
        /// <param name="bes"></param>
        public static IEnumerable<ResourceEntry> FilterResourceEntries(this IEnumerable<BundleEntry> bes) 
        {
            return bes.Where(be => be is ResourceEntry).Cast<ResourceEntry>();
        }

        /// <summary>
        /// Collect all DeletedEntries in the IEnumerable. No ResourceEntries are returned.
        /// </summary>
        /// <param name="bes"></param>
        public static IEnumerable<DeletedEntry> FilterDeletedEntries(this IEnumerable<BundleEntry> bes)
        {
            return bes.Where(be => be is DeletedEntry).Cast<DeletedEntry>();
        }


        /// <summary>
        /// Filter all BundleEntries with the given id.
        /// </summary>
        /// <param name="id">Id of the Resource, as given in the BundleEntry's id</param>
        /// <returns>A list of BundleEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<BundleEntry> ById(this IEnumerable<BundleEntry> bes, Uri id)
        {
            return bes.Where(be => Uri.Equals(be.Id, id));
        }


        /// <summary>
        /// Filter all ResourceEntries with the given id.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="id">Id of the Resource, as given in the ResourceEntry's id</param>
        /// <returns>A list of typed ResourceEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<ResourceEntry<T>> ById<T>(this IEnumerable<ResourceEntry<T>> res, Uri id) where T : Resource, new()
        {
            return res.Where(re => Uri.Equals(re.Id, id));
        }


        /// <summary>
        /// Find the BundleEntry with a given self-link id.
        /// </summary>
        /// <param name="self">Sel-link id of the Resource, as given in the BundleEntry's link with rel='self'.</param>
        /// <returns>A list of BundleEntries with the given self-link id, or an empty list if none were found.</returns>
        public static BundleEntry BySelfLink(this IEnumerable<BundleEntry> bes, Uri self)
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
        public static ResourceEntry<T> BySelfLink<T>(this IEnumerable<ResourceEntry<T>> res, Uri self) where T: Resource, new()
        {
            return res.FirstOrDefault(re => Uri.Equals(re.SelfLink, self));
        }


        /// <summary>
        /// Filter all BundleEntries that have a given tag.
        /// </summary>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of BundleEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<BundleEntry> ByTag(this IEnumerable<BundleEntry> bes, string term)
        {
            return bes.Where(be => be.Tags.FilterFhirTags().HasTag(term));
        }


        /// <summary>
        /// Filter all ResourceEntries that have a given tag.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of typed ResourceEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<ResourceEntry<T>> ByTag<T>(this IEnumerable<ResourceEntry<T>> res, string term) where T : Resource, new()
        {
            return res.Where(re => re.Tags.FilterFhirTags().HasTag(term));
        }
    }
}
