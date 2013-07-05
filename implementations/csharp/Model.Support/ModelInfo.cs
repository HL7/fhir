/*
  Copyright (c) 2011-2012, HL7, Inc
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

namespace Hl7.Fhir.Model
{
    public partial class ModelInfo
    {
        public enum ParamType
        {
            Integer,        // Search parameter must be a simple whole number 
            String,        // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces and are delineated by double quotes, e.g. "van Zanten". 
            Text,           // Search parameter is on a long string. Used for text filter type search: it functions on searches within a body of text and may contain spaces to separate words. May match even if the separate words are found out of order. Text parameters are delineated by double quotes. 
            Date,           // Search parameter is on a date (and should support :before and :after modifiers). The date format is the standard XML format, though other formats may be supported 
            Token,          // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). It's value is either a string or a pair of namespace and value, separated by a "!". 
            Reference,      // A pair of resource type and resource id, separated by "/". Matches when the resource reference resolves to a resource of the given type and id. 
            Composite       // A composite search parameter that combines other search parameters together 
        }

        public class SearchParam
        {
            public string Resource { get; set; }
            public string Name { get; set; }
            public string Description { get; set; }
            public ParamType Type { get; set; }

            // If Type == ParamType.Composite, this array contains 
            // the list of search parameters this param is a combination of
            public string[] CompositeParams { get; set; }

            // The list of elements the search parameter searches on
            public string[] Elements { get; set; }
        }

        public static Type GetTypeForResourceName(string name)
        {
            if( !FhirTypeToCsType.ContainsKey(name) )
                return null;
            else
                return FhirTypeToCsType[name];
        }


        public static string GetResourceNameForType(Type resourceType)
        {
            if( !FhirCsTypeToString.ContainsKey(resourceType) )
                return null;
            else
                return FhirCsTypeToString[resourceType];
        }

        public static bool IsKnownResource(string name)
        {
            return GetTypeForResourceName(name) != null;
        }

        public static bool IsKnownResource(Type type)
        {
            return GetResourceNameForType(type) != null;
        }
    }
}
