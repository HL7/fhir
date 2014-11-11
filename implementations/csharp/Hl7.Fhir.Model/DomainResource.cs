using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using Hl7.Fhir.Validation;

namespace Hl7.Fhir.Model
{
    public partial class DomainResource
    {
        public DomainResource()
        {
            Meta = new ResourceMetaComponent();
        }

        public DomainResource(Uri id, DateTimeOffset lastUpdated)
        {
            Id = id.ToString();
            Meta.LastUpdated = lastUpdated;        
        }


        public override IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            // TODO: Contained resources share the same internal id resolution space as the parent
            // resource -> verify id uniqueness
            var result = new List<ValidationResult>();

            // Validate specific invariants for contained items. The content of the contained
            // items is validated by the "normal" validation triggered by the FhirElement attribute
            if (Contained != null)
            {
                foreach (var contained in Contained.OfType<DomainResource>())
                {
                    if (contained.Contained != null && contained.Contained.Any())
                        result.Add(DotNetAttributeValidation.BuildResult(validationContext, "Contained resources cannot contain nested contained resources"));

                    if (contained.Text != null)
                        result.Add(DotNetAttributeValidation.BuildResult(validationContext, "Contained resources should not contain narrative"));
                }
            }

           return result;
        }

        /// <summary>
        /// Finds a Resource amongst this Resource's contained resources
        /// </summary>
        /// <param name="containedReference">A ResourceReference containing an anchored resource id.</param>
        /// <returns>The found resource, or null if no matching contained resource was found. Will throw an exception if there's more than
        /// one matching contained resource</returns>
        public Resource FindContainedResource(ResourceReference containedReference)
        {
            return FindContainedResource(containedReference.Reference);
        }

        /// <summary>
        /// Finds a Resource amongst this Resource's contained resources
        /// </summary>
        /// <param name="containedReference">A Uri containing an anchored resource id.</param>
        /// <returns>The found resource, or null if no matching contained resource was found. Will throw an exception if there's more than
        /// one matching contained resource</returns>
        public Resource FindContainedResource(Uri containedReference)
        {
            return FindContainedResource(containedReference.ToString());
        }

        /// <summary>
        /// Finds a Resource amongst this Resource's contained resources
        /// </summary>
        /// <param name="containedReference">A string containing an anchored resource id.</param>
        /// <returns>The found resource, or null if no matching contained resource was found. Will throw an exception if there's more than
        /// one matching contained resource</returns>
        public Resource FindContainedResource(string containedReference)
        {
            if (containedReference == null) throw new ArgumentNullException("containedReference");
            if (!containedReference.StartsWith("#")) throw new ArgumentException("Reference is not a local anchored reference", "containedReference");

            var rref = containedReference.Substring(1);

            if (Contained == null) return null;

            return Contained.SingleOrDefault(r => r.Id != null && r.Id == rref);
        }

    }
}
