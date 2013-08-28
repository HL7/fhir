﻿/*
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


using Hl7.Fhir.Model;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;

namespace Hl7.Fhir.Support
{
    public class Util
    {
        public const string FHIRNS = "http://hl7.org/fhir";
        public const string XHTMLNS = "http://www.w3.org/1999/xhtml";
        public const string XMLNS = "http://www.w3.org/2000/xmlns/";

        public const string RESTPARAM_FORMAT = "_format";
       
        public const string SEARCH_PARAM_ID = "_id";
        public const string SEARCH_PARAM_COUNT = "_count";
        public const string SEARCH_PARAM_INCLUDE = "_include";
        public const string HISTORY_PARAM_SINCE = "_since";
        public const string SEARCH_PARAM_SORT = "_sort";

        public const string HISTORY_PARAM_COUNT = SEARCH_PARAM_COUNT;

        public static bool UriHasValue(Uri u)
        {
            return u != null && !String.IsNullOrEmpty(u.ToString());
        }


        private static string xValue(XObject elem)
        {
            if (elem == null) return null;

            if (elem is XElement)
                return (elem as XElement).Value;
            if (elem is XAttribute)
                return (elem as XAttribute).Value;

            return null;
        }

        public static string StringValueOrNull(XObject elem)
        {
            string value = xValue(elem);

            return String.IsNullOrEmpty(value) ? null : value;
        }

        public static int? IntValueOrNull(XObject elem)
        {
            string value = xValue(elem);

            return String.IsNullOrEmpty(value) ? (int?)null : Int32.Parse(value);
        }

        public static Uri UriValueOrNull(XObject elem)
        {
            string value = StringValueOrNull(elem);

            return String.IsNullOrEmpty(value) ? null : new Uri(value, UriKind.RelativeOrAbsolute);
        }

        public static Uri UriValueOrNull(JToken attr)
        {
            if (attr == null) return null;

            var value = attr.Value<string>();

            return String.IsNullOrEmpty(value) ? null : new Uri(value, UriKind.RelativeOrAbsolute);
        }

        public static DateTimeOffset? InstantOrNull(XObject elem)
        {
            string value = StringValueOrNull(elem);

            return String.IsNullOrEmpty(value) ? (DateTimeOffset?)null : Util.ParseIsoDateTime(value);
        }

        
        public static XmlReader XmlReaderFromString(string xml)
        {
            var settings = new XmlReaderSettings();
            settings.IgnoreComments = true;
            settings.IgnoreProcessingInstructions = true;
            settings.IgnoreWhitespace = true;

            XmlReader r = XmlReader.Create(new StringReader(xml), settings);

            return r;
        }

        public static JsonTextReader JsonReaderFromString(string json)
        {
            return new JsonTextReader(new StringReader(json));
        }

        public const string DT_PARAM_PATTERN_FULL = @"yyyy-MM-dd'T'HH:mm:ss.FFFFFFFK";
        public const string DT_PARAM_PATTERN_DATE = @"yyyy-MM-dd";


        public static DateTimeOffset ParseIsoDateTime(string value)
        {
            DateTimeOffset result;

            bool canParse = TryParseIsoDateTime(value, out result);

            if (!canParse)
                throw new ArgumentException("Cannot parse DateTimeOffset from given string");

            return result;
        }

        public static bool TryParseIsoDateTime(string value, out DateTimeOffset result)
        {
            result = DateTimeOffset.MinValue;

            if (value == null)
                return false;
            else if (DateTimeOffset.TryParseExact(value, DT_PARAM_PATTERN_FULL,
                null, System.Globalization.DateTimeStyles.AssumeUniversal, out result))
                return true;
            else if (DateTimeOffset.TryParseExact(value, DT_PARAM_PATTERN_DATE,
               null, System.Globalization.DateTimeStyles.AssumeUniversal, out result))
                return true;
            else
                return false;
        }

        public static string FormatIsoDateTime(DateTimeOffset value)
        {
            return value.ToString(DT_PARAM_PATTERN_FULL);
        }

        public static DateTimeOffset RemoveMiliseconds(DateTimeOffset dt)
        {
            return new DateTimeOffset(dt.Year, dt.Month, dt.Day, dt.Hour, dt.Minute, dt.Second, dt.Offset);
        }


        public static Binary MakeBinary(byte[] data, string contentType)
        {
            var binary = new Binary();

            binary.Content = data;
            binary.ContentType = contentType;
            //Note: binaries don't have Text narrative
            //binary.Text = new Narrative()
            //{
            //    Status = Narrative.NarrativeStatus.Generated,
            //    Div = new XElement(XNamespace.Get(XHTMLNS) + "div",
            //                "Binary content of type " + contentType).ToString()
            //};

            return binary;
        }


    }

#if NETFX_CORE
    [AttributeUsage(AttributeTargets.All, Inherited = false, AllowMultiple = true)]
    sealed class Serializable : Attribute
    {
        // Just there to avoid compilation errors under NETFX
        // (instead of putting this #if at every single [Serializable] attribute in the model
    }


    public static class ForEachExtension
    {
        public static void ForEach<T>(this List<T> list, Action<T> action)
        {
            foreach (T elem in list)
                action(elem);
        }
    }
#endif

}
