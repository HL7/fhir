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
using Hl7.Fhir.Model;
using System.IO;
using Newtonsoft.Json;
using System.Xml.Linq;
using System.Xml;

namespace Hl7.Fhir.Serializers
{
    public partial class FhirSerializer
    {
        public static string SerializeResourceAsXml(Resource resource)
        {
            //Note: this will always carry UTF-16 coding in the <?xml> header
            StringBuilder sb = new StringBuilder();
            XmlWriter xw = XmlWriter.Create(sb);
            FhirSerializer.SerializeResource(resource, new XmlFhirWriter(xw));
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return sb.ToString();
        }

        public static byte[] SerializeResourceAsXmlBytes(Resource resource)
        {
            MemoryStream stream = new MemoryStream();
            XmlWriterSettings settings = new XmlWriterSettings { Encoding = new UTF8Encoding(false) };
            XmlWriter xw = XmlWriter.Create(stream, settings);
            FhirSerializer.SerializeResource(resource, new XmlFhirWriter(xw));
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif
            return stream.ToArray();
        }

        public static string SerializeResourceAsJson(Resource resource)
        {
            StringBuilder resultBuilder = new StringBuilder();
            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            FhirSerializer.SerializeResource(resource, new JsonFhirWriter(jw));

            return resultBuilder.ToString();
        }

        public static byte[] SerializeResourceAsJsonBytes(Resource resource)
        {
            MemoryStream stream = new MemoryStream();

            var sw = new StreamWriter(stream, new UTF8Encoding(false));
            sw.Write(SerializeResourceAsJson(resource));

#if !NETFX_CORE
            sw.Close();
#endif

            return stream.ToArray();
        }   

        public static void SerializeResource(Resource resource, JsonWriter writer)
        {
            FhirSerializer.SerializeResource(resource, new JsonFhirWriter(writer));
        }

        public static void SerializeResource(Resource resource, XmlWriter writer)
        {
            FhirSerializer.SerializeResource(resource, new XmlFhirWriter(writer));
        }


        public static string SerializeElementAsJson(Element elem)
        {
            StringBuilder resultBuilder = new StringBuilder();

            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            FhirSerializer.SerializeElement(elem, new JsonFhirWriter(jw));

            return resultBuilder.ToString();
        }

        public static string SerializeElementAsXml(Element elem, string name = null)
        {
            //Note: this will always carry UTF-16 coding in the <?xml> header
            StringBuilder sb = new StringBuilder();
            XmlWriterSettings xws = new XmlWriterSettings { }; // { ConformanceLevel = System.Xml.ConformanceLevel.Fragment };
            XmlWriter xw = XmlWriter.Create(sb,xws);
            
            xw.WriteStartElement(name == null ? "element" : name, Support.Util.FHIRNS);
            FhirSerializer.SerializeElement(elem, new XmlFhirWriter(xw));
            xw.WriteEndElement();
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return sb.ToString();
        }

        public static void SerializeElement(Element elem, JsonWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new JsonFhirWriter(writer));
        }

        public static void SerializeElement(Element elem, XmlWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new XmlFhirWriter(writer));
        }


        public static XElement SerializeResourceAsXElement(Resource resource)
        {
            return XElement.Parse(SerializeResourceAsXml(resource));
        }
    }
}
