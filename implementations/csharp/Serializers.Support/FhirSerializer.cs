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
            return xmlWriterToString( xw => FhirSerializer.SerializeResource(resource, new XmlFhirWriter(xw)) );
        }

        public static string SerializeTagListToXml(TagList list)
        {
            return xmlWriterToString(xw => TagListSerializer.SerializeTagList(list, xw));
        }

        public static byte[] SerializeResourceToXmlBytes(Resource resource)
        {
            return xmlWriterToBytes(xw => FhirSerializer.SerializeResource(resource, new XmlFhirWriter(xw)));
        }

        public static byte[] SerializeTagListToXmlBytes(TagList list)
        {
            return xmlWriterToBytes(xw => TagListSerializer.SerializeTagList(list, xw));
        }

        public static string SerializeResourceToJson(Resource resource)
        {
            return jsonWriterToString(jw => FhirSerializer.SerializeResource(resource, new JsonFhirWriter(jw)));
        }

        public static string SerializeTagListToJson(TagList list)
        {
            return jsonWriterToString(jw => TagListSerializer.SerializeTagList(list, jw));
        }

        public static byte[] SerializeResourceToJsonBytes(Resource resource)
        {
            return jsonWriterToBytes(jw => FhirSerializer.SerializeResource(resource, new JsonFhirWriter(jw)));
        }

        public static byte[] SerializeTagListToJsonBytes(TagList list)
        {
            return jsonWriterToBytes(jw => TagListSerializer.SerializeTagList(list, jw));
        }


        public static void SerializeResource(Resource resource, JsonWriter writer)
        {
            FhirSerializer.SerializeResource(resource, new JsonFhirWriter(writer));
        }

        public static void SerializeTagList(TagList list, JsonWriter jw)
        {
            TagListSerializer.SerializeTagList(list, jw);
        }

        public static void SerializeTagList(TagList list, XmlWriter xw)
        {
            TagListSerializer.SerializeTagList(list, xw);
        }

        public static void SerializeResource(Resource resource, XmlWriter writer)
        {
            FhirSerializer.SerializeResource(resource, new XmlFhirWriter(writer));
        }

        public static void SerializeBundle(Bundle bundle, JsonWriter writer)
        {
            BundleJsonSerializer.WriteTo(bundle, writer);
        }

        public static void SerializeBundle(Bundle bundle, XmlWriter writer)
        {
            BundleXmlSerializer.WriteTo(bundle, writer);
        }

        public static string SerializeBundleToJson(Bundle bundle)
        {
            return jsonWriterToString(jw => BundleJsonSerializer.WriteTo(bundle, jw));
        }

        public static string SerializeBundleToXml(Bundle bundle)
        {
            return xmlWriterToString(xw => BundleXmlSerializer.WriteTo(bundle, xw));
        }

        public static byte[] SerializeBundleToJsonBytes(Bundle bundle)
        {
            return jsonWriterToBytes(jw => BundleJsonSerializer.WriteTo(bundle, jw));
        }

        public static byte[] SerializeBundleToXmlBytes(Bundle bundle)
        {
            return xmlWriterToBytes(xw => BundleXmlSerializer.WriteTo(bundle, xw));
        }

        public static void SerializeBundleEntry(Bundle entry , JsonWriter writer)
        {
            BundleJsonSerializer.WriteTo(entry, writer);
        }

        public static void SerializeBundleEntry(Bundle entry, XmlWriter writer)
        {
            BundleXmlSerializer.WriteTo(entry, writer);
        }

        public static string SerializeBundleEntryToJson(BundleEntry entry)
        {
            return jsonWriterToString(jw=>BundleJsonSerializer.WriteTo(entry, jw));
        }

        public static string SerializeBundleEntryToXml(BundleEntry entry)
        {
            return xmlWriterToString(xw => BundleXmlSerializer.WriteTo(entry, xw));
        }

        public static byte[] SerializeBundleEntryToJsonBytes(BundleEntry entry)
        {
            return jsonWriterToBytes(jw => BundleJsonSerializer.WriteTo(entry, jw));
        }

        public static byte[] SerializeBundleEntryToXmlBytes(BundleEntry entry)
        {
            return xmlWriterToBytes(xw => BundleXmlSerializer.WriteTo(entry, xw));
        }

        public static XElement SerializeResourceAsXElement(Resource resource)
        {
            return XElement.Parse(SerializeResourceToXml(resource));
        }

        internal static string SerializeElementAsJson(Element elem)
        {
            return jsonWriterToString(jw => FhirSerializer.SerializeElement(elem, new JsonFhirWriter(jw)));
        }

        internal static string SerializeElementAsXml(Element elem, string name = null)
        {
            return xmlWriterToString(xw =>
                {
                    xw.WriteStartElement(name == null ? "element" : name, Support.Util.FHIRNS);
                    FhirSerializer.SerializeElement(elem, new XmlFhirWriter(xw));
                    xw.WriteEndElement();
                });
        }

        internal static void SerializeElement(Element elem, JsonWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new JsonFhirWriter(writer));
        }

        internal static void SerializeElement(Element elem, XmlWriter writer)
        {
            FhirSerializer.SerializeElement(elem, new XmlFhirWriter(writer));
        }

        private static byte[] xmlWriterToBytes(Action<XmlWriter> serializer)
        {
            MemoryStream stream = new MemoryStream();
            XmlWriterSettings settings = new XmlWriterSettings { Encoding = new UTF8Encoding(false) };
            XmlWriter xw = XmlWriter.Create(stream, settings);

            serializer(xw);

            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif
            return stream.ToArray();

        }


        private static byte[] jsonWriterToBytes(Action<JsonWriter> serializer)
        {
            MemoryStream stream = new MemoryStream();

            var sw = new StreamWriter(stream, new UTF8Encoding(false));
            JsonWriter jw = new JsonTextWriter(sw);

            serializer(jw);

            jw.Flush();

#if !NETFX_CORE
            sw.Close();
#endif

            return stream.ToArray();

        }

        private static string jsonWriterToString(Action<JsonWriter> serializer)
        {
            StringBuilder resultBuilder = new StringBuilder();
            StringWriter sw = new StringWriter(resultBuilder);
            JsonWriter jw = new JsonTextWriter(sw);

            serializer(jw);

            jw.Flush();

#if !NETFX_CORE
            jw.Close();
#endif

            return resultBuilder.ToString();
        }


        private static string xmlWriterToString(Action<XmlWriter> serializer)
        {
            StringBuilder sb = new StringBuilder();
            XmlWriter xw = XmlWriter.Create(sb);

            serializer(xw);

            xw.Flush();

#if !NETFX_CORE
            xw.Close();
#endif

            return sb.ToString();
        }
    }
}
