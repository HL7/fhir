﻿using Hl7.Fhir.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace Hl7.Fhir.Validation
{
    [AttributeUsage(AttributeTargets.Property, Inherited = false, AllowMultiple = false)]
    public class CodePatternAttribute : ValidationAttribute
    {
        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            if (value == null) return ValidationResult.Success;

            if (value.GetType() != typeof(string))
                throw new ArgumentException("CodePatternAttribute can only be applied to string properties");

            if (Regex.IsMatch(value as string, "^" + Code.PATTERN + "$", RegexOptions.Singleline))
                return ValidationResult.Success;
            else
                return FhirValidator.BuildResult(validationContext, "Not a correctly formatted Code");
        }
    }
}
