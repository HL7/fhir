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
using Hl7.Fhir.Support;

namespace Hl7.Fhir.Serializers
{
    public partial class FhirSerializer
    {
        public static string SerializeResourceToXml(Resource resource)
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

        public static byte[] SerializeResourceToXmlBytes(Resource resource)
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

        public static string SerializeResourceToJson(Resource resource)
        {
            StringBuilder resultBuilder = new StringBuilder();
            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            FhirSerializer.SerializeResource(resource, new JsonFhirWriter(jw));

            return resultBuilder.ToString();
        }

        public static byte[] SerializeResourceToJsonBytes(Resource resource)
        {
            MemoryStream stream = new MemoryStream();

            var sw = new StreamWriter(stream, new UTF8Encoding(false));
            sw.Write(SerializeResourceToJson(resource));

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

        public static void SerializeBundle(Bundle bundle, JsonWriter writer)
        {
            BundleJson.WriteTo(bundle, writer);
        }

        public static void SerializeBundle(Bundle bundle, XmlWriter writer)
        {
            BundleXml.WriteTo(bundle, writer);
        }

        public static string SerializeBundleToJson(Bundle bundle)
        {
            if (bundle == null) throw new ArgumentException("Bundle cannot be null");

            StringBuilder resultBuilder = new StringBuilder();
            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            BundleJson.WriteTo(bundle, jw);
            jw.Flush();
            jw.Close();

            return resultBuilder.ToString();
        }

        public static string SerializeBundleToXml(Bundle bundle)
        {
            if (bundle == null) throw new ArgumentException("Bundle cannot be null");

            //Note: this will always carry UTF-16 coding in the <?xml> header
            StringBuilder sb = new StringBuilder();
            XmlWriter xw = XmlWriter.Create(sb);
            BundleXml.WriteTo(bundle, xw);
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return sb.ToString();
        }


        public static byte[] SerializeBundleToJsonBytes(Bundle bundle)
        {
            if (bundle == null) throw new ArgumentException("Bundle cannot be null");

            return Encoding.UTF8.GetBytes(SerializeBundleToJson(bundle));
        }


        public static byte[] SerializeBundleToXmlBytes(Bundle bundle)
        {
            if (bundle == null) throw new ArgumentException("Bundle cannot be null");

            MemoryStream stream = new MemoryStream();
            XmlWriterSettings settings = new XmlWriterSettings { Encoding = new UTF8Encoding(false) };
            XmlWriter xw = XmlWriter.Create(stream, settings);
            BundleXml.WriteTo(bundle, xw);
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return stream.ToArray();
        }



        public static void SerializeBundleEntry(Bundle entry , JsonWriter writer)
        {
            BundleJson.WriteTo(entry, writer);
        }


        public static void SerializeBundleEntry(Bundle entry, XmlWriter writer)
        {
            BundleXml.WriteTo(entry, writer);
        }


        public static string SerializeBundleEntryToJson(BundleEntry entry)
        {
            if (entry == null) throw new ArgumentException("Entry cannot be null");

            StringBuilder resultBuilder = new StringBuilder();
            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            BundleJson.WriteTo(entry, jw);
            jw.Flush();
            jw.Close();

            return resultBuilder.ToString();
        }


        public static string SerializeBundleEntryToXml(BundleEntry entry)
        {
            if (entry == null) throw new ArgumentException("Entry cannot be null");

            //Note: this will always carry UTF-16 coding in the <?xml> header
            StringBuilder sb = new StringBuilder();
            XmlWriter xw = XmlWriter.Create(sb);
            BundleXml.WriteTo(entry, xw);
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return sb.ToString();
        }


        public static byte[] SerializeBundleEntryToJsonBytes(BundleEntry entry)
        {
            if (entry == null) throw new ArgumentException("Entry cannot be null");

            return Encoding.UTF8.GetBytes(SerializeBundleEntryToJson(entry));
        }

        public static byte[] SerializeBundleEntryToXmlBytes(BundleEntry entry)
        {
            if (entry == null) throw new ArgumentException("Entry cannot be null");

            MemoryStream stream = new MemoryStream();
            XmlWriterSettings settings = new XmlWriterSettings { Encoding = new UTF8Encoding(false) };
            XmlWriter xw = XmlWriter.Create(stream, settings);
            BundleXml.WriteTo(entry, xw);
            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return stream.ToArray();
        }




        internal static string SerializeElementAsJson(Element elem)
        {
            StringBuilder resultBuilder = new StringBuilder();

            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);
            FhirSerializer.SerializeElement(elem, new JsonFhirWriter(jw));

            return resultBuilder.ToString();
        }

        internal static string SerializeElementAsXml(Element elem, string name = null)
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

        internal static void SerializeElement(Element elem, JsonWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new JsonFhirWriter(writer));
        }

        internal static void SerializeElement(Element elem, XmlWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new XmlFhirWriter(writer));
        }

        public static XElement SerializeResourceAsXElement(Resource resource)
        {
            return XElement.Parse(SerializeResourceToXml(resource));
        }
    }
}
