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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.utilities.Utilities;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public abstract class XmlBase {
  protected static final String FHIR_NS = "http://hl7.org/fhir";
  protected static final String ATOM_NS = "http://www.w3.org/2005/Atom";
  protected static final String GDATA_NS = "http://schemas.google.com/g/2005";
 
  protected XmlPullParser loadXml(InputStream stream) throws Exception {
    BufferedInputStream input = new BufferedInputStream(stream);
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();
    xpp.setInput(input, "UTF-8");
    xpp.next();
    
    return xpp;
  }
 
  protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
    int eventType = xpp.getEventType();
    while ((eventType == XmlPullParser.TEXT && xpp.isWhitespace()) || (eventType == XmlPullParser.COMMENT))
      eventType = xpp.next();
    return eventType;
  }

  

  public static String dateToXml(java.util.Calendar date) {
    // there's a better way to do this in java 1.7, but for now going java 1.7 is too hard for implementers
    // String res = new SimpleDateFormat(XML_DATE_PATTERN).format(date);
    // return res.substring(0, 22)+":"+res.substring(22);
 
  	// javax.xml.bind. isn't available on android..
    return javax.xml.bind.DatatypeConverter.printDateTime(date);
  	
  }
  
  public static java.util.Calendar xmlToDate(String date) throws ParseException  {
    // java 1.7 can parse xml date/times, but going java 1.7 is too hard for implementers
    // javax.xml.bind.DatatypeConverte can parse xml date/times, but is not available on android. (and it's error message sucks)
    // so we parse the date directly
    String s;
    String t;
    if (date.endsWith("Z")) {
      s = date.substring(0, date.length()-1);
      t = null;
    } else if (date.lastIndexOf("-") > 8) {
      s = date.substring(0, date.lastIndexOf("-"));
      t = date.substring(s.lastIndexOf("-"));
    } else if (date.lastIndexOf("+") > 8) {
      s = date.substring(0, date.lastIndexOf("-"));
      t = date.substring(s.lastIndexOf("+"));
    } else { // no timezone
      s = date;
      t = null;      
    }
    Calendar res = new GregorianCalendar();
    try {
      if (t != null)
        res.setTimeZone(TimeZone.getTimeZone("GMT"+t));
      res.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(s));
    } catch (ParseException e) {
      throw new ParseException("The date '"+date+"' is not a valid Date Time Format ("+e.getMessage()+")", e.getErrorOffset());
    }
    return res;
  }

  protected void skipElementWithContent(XmlPullParser xpp)  throws Exception {
  	// when this is called, we are pointing an element that may have content
    while (xpp.getEventType() != XmlPullParser.END_TAG) {
  		xpp.next();
    	if (xpp.getEventType() == XmlPullParser.START_TAG) 
    		skipElementWithContent(xpp);
    }
    xpp.next();
  }
  
  protected void skipEmptyElement(XmlPullParser xpp) throws Exception {
    while (xpp.getEventType() != XmlPullParser.END_TAG) 
      xpp.next();
    xpp.next();
  }

  protected String toString(String value) {
    return value;
  }
  
  protected String toString(int value) {
    return java.lang.Integer.toString(value);
  }
  
  protected String toString(boolean value) {
    return java.lang.Boolean.toString(value);
  }
  
  protected String toString(BigDecimal value) {
    return value.toString();
  }
  
  protected String toString(URI value) {
    return value.toString();
  }

  public static String toString(byte[] value) {
    byte[] encodeBase64 = Base64.encodeBase64(value);
    return new String(encodeBase64);
  }
  
  protected String toString(Calendar value) {
    return dateToXml(value);
  }
  

}
