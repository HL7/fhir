﻿using Hl7.Fhir.Model;
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Reflection;

namespace Hl7.Fhir.Introspection
{
    [AttributeUsage(AttributeTargets.Property, Inherited = false, AllowMultiple = false)]
    public class ReferencesAttribute : Attribute
    {
        [CLSCompliant(false)]
        public ReferencesAttribute(params string[] resources)
        {
            Resources = resources;
        }

        public string[] Resources { get; set; }   
    }
}
