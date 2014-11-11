/* 
 * Copyright (c) 2014, Furore (info@furore.com) and contributors
 * See the file CONTRIBUTORS for details.
 * 
 * This file is licensed under the BSD 3-Clause license
 * available at https://raw.githubusercontent.com/ewoutkramer/fhir-net-api/master/LICENSE
 */

using Hl7.Fhir.Model;
using Hl7.Fhir.Support;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Hl7.Fhir.Model
{
    //TODO: Add functionality to resolve references between resources in the Bundle (see references.html)
    //TODO: Add functionality to make relative references absolute and vice versa using fhir-base url
    public static class BundleExtensions
    {
        public static IEnumerable<Resource> FindItemByReference(this Bundle bundle, Uri reference)
        {
            var id = reference;

            if (!id.IsAbsoluteUri)
            {
                if (bundle.Base == null)
                    throw Error.Argument("reference", "Reference is a relative uri, so it needs a fhir-base link to be able to find entries by id or selflink");

                id = new Uri(new Uri(bundle.Base), id);
            }

            if (bundle.Item == null) return Enumerable.Empty<Resource>();

            var byId = bundle.Item.ById(id);
            var bySelf = bundle.Item.BySelfLink(id);

            if(bySelf != null)
                return byId.Concat(new List<Resource> { bySelf });
            else
                return byId;
        }

        /// <summary>
        /// Filter all BundleEntries with the given id.
        /// </summary>
        /// <param name="id">Id of the Resource, as given in the BundleEntry's id</param>
        /// <param name="entries">List of bundle entries to filter on</param>
        /// <returns>A list of BundleEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<Resource> ById(this IEnumerable<Resource> entries, Uri id)
        {
            if (!id.IsAbsoluteUri) throw Error.Argument("id", "Id must be an absolute uri");

            return entries.Where(be => Uri.Equals(be.Id, id));
        }


        /// <summary>
        /// Filter all ResourceEntries with the given id.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="res">List of resources to filter on</param>
        /// <param name="id">Id of the Resource, as given in the ResourceEntry's id</param>
        /// <returns>A list of typed ResourceEntries with the given id, or an empty list if none were found.</returns>
        public static IEnumerable<T> ById<T>(this IEnumerable<Resource> res, Uri id) where T : Resource, new()
        {
            if (!id.IsAbsoluteUri) throw Error.Argument("id", "Id must be an absolute uri");

            return res.Where(re => Uri.Equals(re.Id, id) && re is T).Cast<T>();
        }


        /// <summary>
        /// Find the BundleEntry with a given self-link id.
        /// </summary>
        /// <param name="entries">List of bundle entries to filter on</param>
        /// <param name="self">Sel-link id of the Resource, as given in the BundleEntry's link with rel='self'.</param>
        /// <returns>A list of BundleEntries with the given self-link id, or an empty list if none were found.</returns>
        public static Resource BySelfLink(this IEnumerable<Resource> entries, Uri self)
        {
            if (!self.IsAbsoluteUri) throw Error.Argument("self", "Must be an absolute uri");

            return entries.FirstOrDefault(be => be.Meta != null && Uri.Equals(be.Meta.VersionId, self));
        }


        /// <summary>
        /// Find the ResourceEntry with a given self-link id.
        /// </summary>
        /// <typeparam name="T">Type of Resource to find</typeparam>
        /// <param name="res">List of resources to filter on</param>
        /// <param name="self">Sel-link id of the Resource, as given in the BundleEntry's link with rel='self'.</param>
        /// <returns>A list of ResourceEntries with the given self-link id. Returns
        /// the empty list if none were found.</returns>
        public static T BySelfLink<T>(this IEnumerable<Resource> res, Uri self) where T : Resource, new()
        {
            if (!self.IsAbsoluteUri) throw Error.Argument("id", "Must be an absolute uri");

            return res.FirstOrDefault(re => re.Meta != null && Uri.Equals(re.Meta.VersionId, self)) as T ;
        }


        /// <summary>
        /// Filter all BundleEntries that have a given tag.
        /// </summary>
        /// <param name="entries">List of bundle entries to filter on</param>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of BundleEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<Resource> ByTag(this IEnumerable<Resource> entries, Coding tag)
        {
            return entries.Where(be => be.Meta != null && be.Meta.Tag != null && be.Meta.Tag.Contains(tag));
        }


        /// <summary>
        /// Filter all ResourceEntries that have a given tag.
        /// </summary>
        /// <typeparam name="T">Type of Resource to filter</typeparam>
        /// <param name="res">List of resources to filter on</param>
        /// <param name="tag">Tag to filter Resources on</param>
        /// <returns>A list of typed ResourceEntries having the given tag, or an empty list if none were found.</returns>
        public static IEnumerable<T> ByTag<T>(this IEnumerable<Resource> entries, Coding tag) where T : Resource, new()
        {
            return entries.Where(be => be.Meta != null && be.Meta.Tag != null && be.Meta.Tag.Contains(tag) && be is T).Cast<T>();
        }
    }

}
