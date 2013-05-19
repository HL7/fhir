package org.hl7.fhir.instance.formats;
/*
Copyright (c) 2011-2013, HL7, Inc
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

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.xmlpull.v1.XmlPullParser;

public abstract class XmlParserBase extends ParserBase {

  private boolean allowUnknownContent;
  public boolean isAllowUnknownContent() {
    return allowUnknownContent;
  }
  public void setAllowUnknownContent(boolean allowUnknownContent) {
    this.allowUnknownContent = allowUnknownContent;
  }
    
  abstract protected Resource parseResource(XmlPullParser xpp) throws Exception;

  /** -- worker routines --------------------------------------------------- */
  
  protected void parseTypeAttributes(XmlPullParser xpp, Type t) throws Exception {
    parseElementAttributes(xpp, t);
  }

  protected void parseElementAttributes(XmlPullParser xpp, Element e) throws Exception {
    if (xpp.getAttributeValue(null, "id") != null) {
      e.setXmlId(xpp.getAttributeValue(null, "id"));
      idMap.put(e.getXmlId(), e);
    }
  }

  protected void parseResourceAttributes(XmlPullParser xpp, Resource r) throws Exception {
    parseElementAttributes(xpp, r);
  }


  private String pathForLocation(XmlPullParser xpp) {
    return xpp.getPositionDescription();
  }
  

  public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
    ResourceOrFeed r = new ResourceOrFeed();
    
    if (xpp.getNamespace().equals(FHIR_NS))
      r.resource = parseResource(xpp);
    else if (xpp.getNamespace().equals(ATOM_NS)) 
      r.feed = new AtomParser().parse(xpp);
    else
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return r;    
  }

  public Resource parse(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
  
    if (!xpp.getNamespace().equals(FHIR_NS))
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseResource(xpp);
  }

  public Resource parse(XmlPullParser xpp) throws Exception {
    if (!xpp.getNamespace().equals(FHIR_NS))
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseResource(xpp);
  }

  
  protected void unknownContent(XmlPullParser xpp) throws Exception {
    if (!isAllowUnknownContent())
      throw new Exception("Unknown Content "+xpp.getName()+" @ "+pathForLocation(xpp));
  }
  
  protected XhtmlNode parseXhtml(XmlPullParser xpp) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parseHtmlNode(xpp);
  }



  protected Resource parseBinary(XmlPullParser xpp) throws Exception {
    Binary res = new Binary();
    parseElementAttributes(xpp, res);
    res.setContentType(xpp.getAttributeValue(null, "contentType"));
    int eventType = xpp.next();
    if (eventType == XmlPullParser.TEXT) {
      res.setContent(Base64.decodeBase64(xpp.getText().getBytes()));
      eventType = xpp.next();
    }
    if (eventType != XmlPullParser.END_TAG)
      throw new Exception("Bad String Structure");
    xpp.next();
    return res;
  }
  
}
