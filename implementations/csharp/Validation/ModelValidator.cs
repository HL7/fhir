using Hl7.Fhir.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;

namespace Hl7.Fhir.Validation
{
    public class ModelValidator
    {
        public static bool TryValidate(Resource resource, ICollection<ValidationResult> validationResults)
        {
            return Validator.TryValidateObject(resource, ValidationContextFactory.Create(resource, null), validationResults, true);
        }

        public static bool TryValidate(Element element, ICollection<ValidationResult> validationResults)
        {
            return Validator.TryValidateObject(element, ValidationContextFactory.Create(element, null), validationResults, true);
        }

        public static bool TryValidate(ResourceEntry entry, ICollection<ValidationResult> validationResults)
        {
            return Validator.TryValidateObject(entry, ValidationContextFactory.Create(entry, null), validationResults, true);
        }

        public static void Validate(ResourceEntry entry)
        {
            Validator.ValidateObject(entry, ValidationContextFactory.Create(entry, null), true);
        }

        public static void Validate(Resource resource)
        {
            Validator.ValidateObject(resource, ValidationContextFactory.Create(resource, null), true);
        }

        public static void Validate(Element element)
        {
            Validator.ValidateObject(element, ValidationContextFactory.Create(element, null), true);
        }
    }
}
