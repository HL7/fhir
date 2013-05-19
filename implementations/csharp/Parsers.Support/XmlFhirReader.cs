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
using Hl7.Fhir.Model;

namespace Hl7.Fhir.Parsers
{
    public class XmlFhirReader : IFhirReader
    {
        public const string XHTMLELEM = "div";
        public const string IDATTR = "id";
        public const string VALUEATTR = "value";
        public const string CONTENT_TYPE = "contentType";

        private XmlReader xr;

        public XmlFhirReader(XmlReader xr)
        {
            var settings = new XmlReaderSettings();
            settings.IgnoreComments = true;
            settings.IgnoreProcessingInstructions = true;
            settings.IgnoreWhitespace = true;

            this.xr = XmlReader.Create(xr, settings);
            //this.xr.MoveToContent();
        }

        public void MoveToContent()
        {
            if( this.xr.ReadState == ReadState.Initial )
                this.xr.MoveToContent();
        }

        public string CurrentElementName
        {
            get
            {
                if (xr.NodeType == XmlNodeType.Element)
                    return xr.LocalName;
                else
                    return "#" + xr.NodeType.ToString();
            }
        }


        bool insideEmptyElement = false;


        private class ElementAttributes
        {
            public string Primitive { get; set; }
            public string LocalId { get; set; }
            public string ContentType { get; set; }
        }

        private Stack<ElementAttributes> elementStack = new Stack<ElementAttributes>();


        public void EnterElement()
        {
            string value = null;
            string id = null;
            string contentType = null;

            readAttributes(out id, out value, out contentType);
            elementStack.Push(new ElementAttributes { LocalId = id, Primitive = value, 
                ContentType = contentType });

            if (!xr.IsEmptyElement)
            {
                insideEmptyElement = false;
                xr.ReadStartElement();
            }
            else
                insideEmptyElement = true;
        }


        private ElementAttributes currentAttributes
        {
            get
            {
                if (elementStack.Count > 0)
                    return elementStack.Peek();
                else
                    return null;
            }
        }

        public bool HasMoreElements()
        {
            if (xr.EOF || xr.ReadState == ReadState.Error) return false;

            // First, if we still have "attribute" elements to process, we are at an element
            if (IsAtPrimitiveValueElement() ||
                IsAtRefIdElement()) return true;

            // IsAtElement() is normally called after you called EnterElement() on your parent
            // to see if you're at a child element. However, for empty parent elements, we cannot
            // go "into" an element, we only simulate the "attribute" elements. So, if they
            // are done (previous if()), and this was such an empty element, we are ready, there are
            // no more elements
            if (insideEmptyElement) return false;

            // Otherwise, just check whether we are at an acceptable element
            return IsAtXhtmlElement() ||
                xr.NodeType == XmlNodeType.Element;
        }


        public bool IsAtFhirElement()
        {
            return xr.NodeType == XmlNodeType.Element && xr.NamespaceURI == Support.Util.FHIRNS;
        }

        public bool IsAtXhtmlElement()
        {
            return xr.NodeType == XmlNodeType.Element && xr.NamespaceURI == Support.Util.XHTMLNS &&
                xr.LocalName == XHTMLELEM;
        }

        public string ReadXhtmlContents()
        {
            return xr.ReadOuterXml();
        }


        public bool IsAtPrimitiveValueElement()
        {
            return currentAttributes != null && currentAttributes.Primitive != null;
        }

        public string ReadPrimitiveContents()
        {
            string result = null;

            if (currentAttributes != null)
            {
                result = currentAttributes.Primitive;
                currentAttributes.Primitive = null;
            }

            return result;
        }

        public string ReadBinaryBase64TextContents()
        {
            if (xr.NodeType == XmlNodeType.Text)
                return xr.Value;
            else
                throw new InvalidOperationException("Binary content expected, but node type was " + xr.NodeType.ToString());
        }

        public string ReadBinaryContentType()
        {
            string result = null;
            if (currentAttributes != null)
            {
                result = currentAttributes.ContentType;
                currentAttributes.ContentType = null;
            }

            return result;
        }


        public bool IsAtRefIdElement()
        {
            return currentAttributes != null && currentAttributes.LocalId != null;
        }

        public string ReadRefIdContents()
        {
            string result = null;

            if (currentAttributes != null)
            {
                result = currentAttributes.LocalId;
                currentAttributes.LocalId = null;
            }

            return result;
        }

        public void LeaveElement()
        {
            if (xr.NodeType == XmlNodeType.EndElement)
                xr.ReadEndElement();
            else if (insideEmptyElement)
            {
                xr.Read();
                insideEmptyElement = false;
            }
            else
                throw new FhirFormatException("Expected end of element");

            elementStack.Pop();
        }


        public void SkipSubElementsFor(string name)
        {
            if(!insideEmptyElement)
            {
                while (!isEndElement(xr, name) && !xr.EOF && xr.ReadState != ReadState.Error)
                    xr.Skip();
            }
        }


        private static bool isEndElement(XmlReader reader, string en)
        {
            //Note: this will even find a closing element if it is the same name but
            //another namespace. Too bad. Cannot assume it is FHIRNS, since it might
            //be xhtml (Narrative.div) element too.
            return reader.NodeType == XmlNodeType.EndElement && reader.LocalName == en;
        }


        public int LineNumber
        {
            get { return ((IXmlLineInfo)xr).LineNumber; }
        }

        public int LinePosition
        {
            get { return ((IXmlLineInfo)xr).LinePosition; }
        }


        public void EnterArray()
        {
            if (xr.NodeType != XmlNodeType.Element)
                throw new FhirFormatException("Expected a (repeating) element from FHIR namespace");
        }

        public bool IsAtArrayMember()
        {
            return IsAtFhirElement();
        }

        public void LeaveArray()
        {
            // Nothing
        }

       
        private void readAttributes(out string localId, out string value, out string contentType)
        {
            string elementName = xr.LocalName;

            localId = null;
            value = null;
            contentType = null;

            if (xr.HasAttributes)
            {
                while (xr.MoveToNextAttribute())
                {
                    if (xr.LocalName == IDATTR && xr.NamespaceURI == "")
                        localId = String.IsNullOrEmpty(xr.Value) ? null : xr.Value;
                    else if (xr.LocalName == VALUEATTR && xr.NamespaceURI == "")
                        value = String.IsNullOrEmpty(xr.Value) ? null : xr.Value;
                    else if (xr.LocalName == CONTENT_TYPE && xr.NamespaceURI == "")
                        contentType = String.IsNullOrEmpty(xr.Value) ? null : xr.Value;
                    else
                    {
                        if (xr.NamespaceURI == Util.XMLNS)
#pragma warning disable 642
                            ;
#pragma warning restore 642
                        else
                            throw new FhirFormatException(String.Format("Unsupported attribute '{0}' on element {1}",
                                    xr.LocalName, elementName));
                    }
                }

                xr.MoveToElement();
            }
        }
    }
}
