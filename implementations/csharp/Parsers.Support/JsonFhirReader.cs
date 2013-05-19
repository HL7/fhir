/*
  Copyright (c) 2011-2012, HL7, Inc.
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

namespace Hl7.Fhir.Parsers
{
    public class JsonFhirReader : IFhirReader
    {
        public const string XHTMLELEM = "div";
        public const string IDATTR = "_id";
        public const string VALUEATTR = "value";
        public const string BINARY_CONTENT_TYPE = "contentType";
        public const string BINARY_CONTENT = "content";

        private JsonTextReader jr;

        public JsonFhirReader(JsonTextReader jr)
        {
            jr.DateParseHandling = DateParseHandling.None;
            this.jr = jr;
          //  moveToContent();
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


        private bool isAtToken()
        {
            return jr.TokenType == JsonToken.PropertyName;
        }

        public bool HasMoreElements()
        {
            return isAtToken();
        }

        public bool IsAtFhirElement()
        {
            // Cannot check namespaces, so any property is fine
            return isAtToken();
        }

        public bool IsAtXhtmlElement()
        {
            return jr.TokenType == JsonToken.PropertyName && CurrentElementName == XHTMLELEM;
        }

        public string ReadXhtmlContents()
        {
            return processStringProperty();
        }

        private string readStringProperty()
        {
            // Read away property name
            jr.Read();

            if (jr.TokenType == JsonToken.String)
            {
                string value = (string)jr.Value;
                jr.Read();
                return value;
            }
            else
                throw new FhirFormatException("Expected property with a simple string value");
        }

        public bool IsAtPrimitiveValueElement()
        {
            return isAtToken() && CurrentElementName == VALUEATTR;
        }

        public string ReadPrimitiveContents()
        {
            return processStringProperty();
        }

        private string processStringProperty()
        {
            string value = readStringProperty();
            if (!String.IsNullOrEmpty(value))
                return value;
            else
                return null;
        }

        public bool IsAtRefIdElement()
        {
            return isAtToken() && CurrentElementName == IDATTR;
        }

        public string ReadRefIdContents()
        {
            return processStringProperty();
        }


        public string ReadBinaryBase64TextContents()
        {
            if (isAtToken() && CurrentElementName == BINARY_CONTENT)
                return processStringProperty();
            else
                return null;
        }

        public string ReadBinaryContentType()
        {
            if (isAtToken() && CurrentElementName == BINARY_CONTENT_TYPE)
                return processStringProperty();
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
            get { return jr.LineNumber; }
        }

        public int LinePosition
        {
            get { return jr.LinePosition; }
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
