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
using Hl7.Fhir.Support;
using Newtonsoft.Json;
using Hl7.Fhir.Model;
using System.Globalization;

namespace Hl7.Fhir.Parsers
{
    internal class JsonFhirReader : IFhirReader
    {
        private JsonReader jr;

        public JsonFhirReader(JsonReader jr)
        {
            jr.DateParseHandling = DateParseHandling.None;
            jr.FloatParseHandling = FloatParseHandling.Decimal;
            this.jr = jr;
        }


        public void MoveToContent()
        {
            if (jr.TokenType == JsonToken.None)
            {
                jr.Read();

                if (jr.TokenType == JsonToken.StartObject)
                {
                    jr.Read();
                }
                else
                    throw new FhirFormatException("Resources should have a Json object as root");
            }
        }

        public string CurrentElementName
        {
            get
            {
                // Path can be strings like a.b[2].c[4]
                // The current element is the last part, sans the array markers
                string pathPart = jr.Path.Split('.').Last();

                if (pathPart[pathPart.Length - 1] == ']')
                    pathPart = pathPart.Substring(0, pathPart.IndexOf('['));

                return pathPart;
            }
        }

        public void EnterElement()
        {
            // Read away the complex property's name, if it is there
            skipPropertyName();

            if (jr.TokenType != JsonToken.StartObject)
                throw new FhirFormatException("Expected a StartObject JSon token");

            jr.Read();
        }

        public bool HasMoreElements()
        {
            return jr.TokenType == JsonToken.PropertyName;
        }

        private string readPropertyAsString(JsonToken expectedTokenType)
        {
            // Read away property name
            jr.Read();

            if (jr.TokenType == expectedTokenType)
            {
                string value;

                if (jr.Value is string)
                    value = (string)jr.Value;
                else if (jr.Value is bool)
                    value = jr.Value.ToString().ToLower();
                else if (jr.Value is decimal)
                    value = ((decimal)jr.Value).ToString(CultureInfo.InvariantCulture);
                else
                    value = jr.Value.ToString();

                jr.Read();
                return value;
            }
            else
            {
                jr.Read();
                throw new FhirFormatException("Expected property of type " + expectedTokenType.ToString());
            }
        }

     
        public string ReadPrimitiveContents(Type primitiveType)
        {
            // For some specific types, we use the Json native serialization,
            // not a string
            if (primitiveType == typeof(FhirBoolean))
                return readPropertyAsString(JsonToken.Boolean);
            else if (primitiveType == typeof(Integer))
                return readPropertyAsString(JsonToken.Integer);
            else if (primitiveType == typeof(FhirDecimal))
                return readPropertyAsString(JsonToken.Float);
            else
                return processStringProperty();
        }

        private string processStringProperty()
        {
            string value = readPropertyAsString(JsonToken.String);
            if (!String.IsNullOrEmpty(value))
                return value;
            else
                return null;
        }

 
        public void LeaveElement()
        {
            if (jr.TokenType == JsonToken.EndObject)
                jr.Read();
            else
                throw new FhirFormatException("Expected to find end of complex content");
        }

        public void SkipSubElementsFor(string name)
        {
            while (CurrentElementName != name && jr.Read())
                // read tokens until we're back in the parent element or EOF
                ;
        }

        public int LineNumber
        {
            get 
            {
                if (jr is JsonTextReader)
                    return ((JsonTextReader)jr).LineNumber;
                else
                    return -1;
            }
        }

        public int LinePosition
        {
            get 
            {
                if (jr is JsonTextReader)
                    return ((JsonTextReader)jr).LinePosition;
                else
                    return -1;
            }
        }

        public void EnterArray()
        {
            // Read away name of array property
            jr.Read();

            // Read away array start
            if (jr.TokenType == JsonToken.StartArray)
                jr.Read();
            else
                throw new FhirFormatException("Expected start of array");
        }

        public bool IsAtArrayMember()
        {
            return jr.TokenType != JsonToken.EndArray;
        }

        public void LeaveArray()
        {
            if (jr.TokenType == JsonToken.EndArray)
                jr.Read();
            else
                throw new FhirFormatException("Expected end of array");
        }


        private void skipPropertyName()
        {
            if (jr.TokenType == JsonToken.PropertyName) jr.Read();
        }
    }
}
