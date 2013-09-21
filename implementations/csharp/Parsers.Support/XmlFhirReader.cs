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
using Hl7.Fhir.Model;

namespace Hl7.Fhir.Parsers
{
    internal class XmlFhirReader : IFhirReader
    {
        public const string XHTMLELEM = "div";
        public const string IDATTR = "id";
        public const string VALUEATTR = "value";
        public const string CONTENTTYPEATTR = "contentType";

        public const string XHTMLDIV_VALUE_NAME = "div";
        public const string PRIMITIVE_VALUE_NAME = "value";
        public const string LOCALID_NAME = "_id";
        public const string BINARY_CONTENTTYPE_NAME = "contentType";
        public const string BINARY_CONTENT_NAME = "content";

        private XmlReader xr;

        public XmlFhirReader(XmlReader xr)
        {
            var settings = new XmlReaderSettings();
            settings.IgnoreComments = true;
            settings.IgnoreProcessingInstructions = true;
            settings.IgnoreWhitespace = true;

            this.xr = XmlReader.Create(xr, settings);
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
                // If we have batched up attributes to provide, 
                // provide the current attribute first
                if (currentElementContents != null)
                    return currentElementContents.Item1;

                // If this is the infamous xhtml div, fake that
                // the element is actually called 'div'
                else if (isAtXhtmlElement())
                    return XHTMLDIV_VALUE_NAME;

                // If we are at a text node, this must be the special 'content' element
                // used in binaries
                else if (xr.NodeType == XmlNodeType.Text)
                    return BINARY_CONTENT_NAME;

                // If we're done with the attributes, return
                // the local name of the current normal xml element
                // if this is the FHIR namespace, and a prefixed
                // element name otherwise (which will lead to the
                // desired parse errors for unknown elements)
                else if (xr.NodeType == XmlNodeType.Element)
                {
                    if (xr.NamespaceURI == Support.Util.FHIRNS)
                        return xr.LocalName;
                    else
                        return String.Format("{{0}}{1}", xr.NamespaceURI, xr.LocalName);
                }

                // Kind of an error condition, for debugging purposes
                else
                    return "#" + xr.NodeType.ToString();
            }
        }


        bool insideEmptyElement = false;


        private class ElementContents
        {
            public Stack<Tuple<string, string>> Elements = new Stack<Tuple<string, string>>();

            public bool hasContents()
            {
                return Elements.Count > 0;
            }

            public Tuple<string, string> CurrentElement()
            {
                if (hasContents())
                    return Elements.Peek();
                else
                    return null;
            }
        }
        

        private Stack<ElementContents> elementStack = new Stack<ElementContents>();

        public void EnterElement()
        {
            string value = null;
            string id = null;
            string contentType = null;

            readAttributes(out id, out value, out contentType);
            var contents = new ElementContents();
            
            if( id != null )
                contents.Elements.Push( new Tuple<string,string>(LOCALID_NAME,id) );

            if(value != null )
                contents.Elements.Push( new Tuple<string,string>(PRIMITIVE_VALUE_NAME,value) );

            if (contentType != null)
                contents.Elements.Push(new Tuple<string, string>(BINARY_CONTENTTYPE_NAME, contentType));

            if (!xr.IsEmptyElement)
            {
                insideEmptyElement = false;
                xr.ReadStartElement();
            }
            else
                insideEmptyElement = true;

            elementStack.Push(contents);
        }


        private Tuple<string,string> currentElementContents
        {
            get
            {
                if (elementStack.Count > 0)
                {
                    var element = elementStack.Peek();
                    if(element.hasContents())
                        return element.CurrentElement();
                }

                return null;
            }
        }

        private void nextElementContents()
        {
            if (elementStack.Count > 0)
            {
                if (elementStack.Peek().Elements.Count > 0)
                    elementStack.Peek().Elements.Pop();
            }
        }

        private void clearElementContents()
        {
            if (elementStack.Count > 0)
            {
                elementStack.Peek().Elements.Clear();
            }
        }

        public bool HasMoreElements()
        {
            if (xr.EOF || xr.ReadState == ReadState.Error) return false;

            // First, if we still have "attribute" elements to process, we are at an element
            if (currentElementContents != null) return true;

            // IsAtElement() is normally called after you called EnterElement() on your parent
            // to see if you're at a child element. However, for empty elements, you cannot
            // go "into" an element. So, if the we are done with the attributes (previous if()), 
            // and this was such an empty element, we are ready, there are no more elements
            if (insideEmptyElement) return false;

            // If we are at a text node, this is a special kind of element for Binary type,
            // simulated as an element with name 'content'
            if (xr.NodeType == XmlNodeType.Text) return true;

            // Otherwise, just check whether we are at an acceptable element
            // Note: we're not forcing this to be a FHIR element, elements from other
            // namespaces are allowed, though the parser will probably report an error
            if (xr.NodeType == XmlNodeType.Element) return true;

            return false;
        }


        private bool isAtXhtmlElement()
        {
            return xr.NodeType == XmlNodeType.Element && xr.NamespaceURI == Support.Util.XHTMLNS &&
                xr.LocalName == XHTMLELEM;
        }

        public string ReadPrimitiveContents(Type primitiveType)
        {
            if (currentElementContents != null)
            {
                var result = currentElementContents.Item2;
                nextElementContents();
                return result;
            }
            else if(xr.NodeType == XmlNodeType.Text)
            {                                                                
                var txt = xr.Value;
                xr.Read();
                return txt;
            }
            else if (isAtXhtmlElement())
            {
                return xr.ReadOuterXml();
            }
            else
                throw new InvalidOperationException("Reader is not at a position to read primitive values");
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
            if (!insideEmptyElement)
            {
                while (!isEndElement(xr, name) && !xr.EOF && xr.ReadState != ReadState.Error)
                    xr.Skip();
            }
            else
                // If this element is empty, there are not subelements
                // but there still might be 'simulated' subelements,
                // erase them
                clearElementContents();
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
            // Not much todo here, an array in xml is a normal element
            return xr.NodeType == XmlNodeType.Element;
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
                    else if (xr.LocalName == BINARY_CONTENTTYPE_NAME && xr.NamespaceURI == "")
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
