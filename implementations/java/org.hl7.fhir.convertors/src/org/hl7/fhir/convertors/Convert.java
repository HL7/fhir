package org.hl7.fhir.convertors;


/*
  Copyright (c) 2011-2014, HL7, Inc.
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


import java.math.BigDecimal;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.Contact.ContactUse;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.HumanName.NameUse;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.Address.AddressUse;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ucum.UcumService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Convert {

	CDAUtilities cda;
	UcumService ucumSvc;
	
	public Convert(CDAUtilities cda, UcumService ucumSvc) {
		super();
		this.cda = cda;
		this.ucumSvc = ucumSvc;
	}
	
	public Identifier makeIdentifierFromII(Element e) throws Exception {
		Identifier id = new Identifier();
		String r = e.getAttribute("root");
		if (Utilities.noString(e.getAttribute("extension"))) {
			id.setSystemSimple("urn:ietf:rfc:3986");
			if (isGuid(r)) 
				id.setValueSimple("urn:uuid:"+r);
			else if (UriForOid(r) != null)
				id.setValueSimple(UriForOid(r));
			else 
				id.setValueSimple(UriForOid(r));
		} else {
			if (isGuid(r)) 
				id.setSystemSimple("urn:uuid:"+r);
			else if (UriForOid(r) != null)
				id.setSystemSimple(UriForOid(r));
			else 
				id.setSystemSimple("urn:oid:"+r);
			id.setValueSimple(e.getAttribute("extension"));
		}
		return id;
	}

	public String makeURIfromII(Element e) {
		String r = e.getAttribute("root");
		if (Utilities.noString(e.getAttribute("extension"))) {
			if (isGuid(r)) 
				return "urn:uuid:"+r;
			else if (UriForOid(r) != null)
				return UriForOid(r);
			else 
				return UriForOid(r);
		} else {
			if (isGuid(r)) 
				return "urn:uuid:"+r+"::"+e.getAttribute("extension");
			else if (UriForOid(r) != null)
				return UriForOid(r)+"::"+e.getAttribute("extension");
			else 
				return "urn:oid:"+r+"::"+e.getAttribute("extension");
		}
  }
	

	private String UriForOid(String r) {
		if (r.equals("2.16.840.1.113883.6.96"))
			return "http://snomed.info/sct";
		if (r.equals("2.16.840.1.113883.6.1"))
			return "http://loinc.org";
		if (r.equals("2.16.840.1.113883.6.8"))
			return "http://unitsofmeasure.org";
		if (r.equals("2.16.840.1.113883.6.3"))
			return "http://hl7.org/fhir/sid/icd-10";
		if (r.equals("2.16.840.1.113883.6.42"))
			return "http://hl7.org/fhir/sid/icd-9";
		if (r.equals("2.16.840.1.113883.6.73"))
			return "http://hl7.org/fhir/sid/atc";
		if (r.equals("2.16.840.1.113883.6.88"))
			return "http://www.nlm.nih.gov/research/umls/rxnorm"; // todo: confirm this
		if (r.startsWith("2.16.840.1.113883.12."))
			return "http://hl7.org/fhir/sid/v2-"+r.substring(21);
		else
			return "urn:oid:"+r;
	}

	public boolean isGuid(String r) {
		return r.matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
	}

	public InstantType makeInstantFromTS(Element child) throws Exception {
	  InstantType i = new InstantType();
	  i.setValue(DateAndTime.parseV3(child.getAttribute("value")));
	  return i;
  }

	public CodeableConcept makeCodeableConceptFromCD(Element cv) throws Exception {
		if (cv == null)
			return null;
		CodeableConcept cc = new CodeableConcept();
		cc.getCoding().add(makeCodingFromCV(cv));
		for (Element e : cda.getChildren(cv, "translation"))
			cc.getCoding().add(makeCodingFromCV(e));
		if (cda.getChild(cv, "originalText") != null) {
			Element ote = cda.getChild(cv, "originalText");
			if (cda.getChild(ote, "reference") != null) {
				if (cda.getChild(ote, "reference").getAttribute("value").startsWith("#")) {
					Element t = cda.getByXmlId(cda.getChild(ote, "reference").getAttribute("value").substring(1));
					String ot = t.getTextContent().trim();
					cc.setTextSimple(Utilities.noString(ot) ? null : ot);
				} else
					throw new Exception("external references not handled yet "+cda.getChild(ote, "reference").getAttribute("value"));
			} else {	  		
				String ot = ote.getTextContent().trim();
				cc.setTextSimple(Utilities.noString(ot) ? null : ot);
	  	}  
	  }
	  return cc;
  }

	public Coding makeCodingFromCV(Element cd) throws Exception {
		if (cd == null)
			return null;
	  Coding c = new Coding();
	  c.setCodeSimple(cd.getAttribute("code"));
	  c.setDisplaySimple(cd.getAttribute("displayName"));
	  String r = cd.getAttribute("codeSystem");
	  String uri = getUriForOID(r);
	  if (uri != null)
	  	c.setSystemSimple(uri);
	  else if (isGuid(r)) 
			c.setSystemSimple("urn:uuid:"+r);
		else if (UriForOid(r) != null)
			c.setSystemSimple(UriForOid(r));
		else 
			c.setSystemSimple("urn:oid:"+r);
	  return c;
  }

	private String getUriForOID(String r) {
		if (r.equals("2.16.840.1.113883.6.1"))
			return "http://loinc.org";
		if (r.equals("2.16.840.1.113883.6.96"))
			return "http://snomed.info/sct";
	  return null;
  }

	public Address makeAddressFromAD(Element e) {
		if (e == null)
			return null;
	  Address a = new Address();
  	String use = e.getAttribute("use");
	  if (use != null) {
	  	if (use.equals("H") || use.equals("HP") || use.equals("HV"))
	  		a.setUse(new Enumeration<AddressUse>(AddressUse.home));
	  	else if (use.equals("WP") || use.equals("DIR") || use.equals("PUB"))
	  		a.setUse(new Enumeration<AddressUse>(AddressUse.work));
	  	else if (use.equals("TMP"))
	  		a.setUse(new Enumeration<AddressUse>(AddressUse.temp));
	  	else if (use.equals("BAD"))
	  		a.setUse(new Enumeration<AddressUse>(AddressUse.old));
	  }
	  Node n = e.getFirstChild();
	  while (n != null) {
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		String v = n.getTextContent();
	  		if (n.getLocalName().equals("additionalLocator"))
	  			a.getLine().add(makeString(v));
//	  		else if (e.getLocalName().equals("unitID"))
//	  			else if (e.getLocalName().equals("unitType"))
	  			else if (n.getLocalName().equals("deliveryAddressLine"))
		  			a.getLine().add(makeString(v));
//	  			else if (e.getLocalName().equals("deliveryInstallationType"))
//	  			else if (e.getLocalName().equals("deliveryInstallationArea"))
//	  			else if (e.getLocalName().equals("deliveryInstallationQualifier"))
//	  			else if (e.getLocalName().equals("deliveryMode"))
//	  			else if (e.getLocalName().equals("deliveryModeIdentifier"))
	  			else if (n.getLocalName().equals("streetAddressLine"))
		  			a.getLine().add(makeString(v));
//	  			else if (e.getLocalName().equals("houseNumber"))
//	  			else if (e.getLocalName().equals("buildingNumberSuffix"))
//	  			else if (e.getLocalName().equals("postBox"))
//	  			else if (e.getLocalName().equals("houseNumberNumeric"))
//	  			else if (e.getLocalName().equals("streetName"))
//	  			else if (e.getLocalName().equals("streetNameBase"))
//	  			else if (e.getLocalName().equals("streetNameType"))
	  			else if (n.getLocalName().equals("direction"))
		  			a.getLine().add(makeString(v));
	  			else if (n.getLocalName().equals("careOf"))
		  			a.getLine().add(makeString(v));
//	  			else if (e.getLocalName().equals("censusTract"))
	  			else if (n.getLocalName().equals("country"))
		  			a.setCountrySimple(v);
	  			//else if (e.getLocalName().equals("county"))
	  			else if (n.getLocalName().equals("city"))
		  			a.setCitySimple(v);
//	  			else if (e.getLocalName().equals("delimiter"))
//	  			else if (e.getLocalName().equals("precinct"))
	  			else if (n.getLocalName().equals("state"))
		  			a.setStateSimple(v);
	  			else if (n.getLocalName().equals("postalCode"))
		  			a.setZipSimple(v);
	  	}  		
	  	n = n.getNextSibling();
	  }
	  return a;
  }

	public StringType makeString(String v) {
	  StringType s = new StringType();
	  s.setValue(v);
	  return s;
  }

	public Contact makeContactFromTEL(Element e) throws Exception {
		if (e == null)
			return null;
		if (e.hasAttribute("nullFlavor"))
			return null;
	  Contact c = new Contact();
  	String use = e.getAttribute("use");
	  if (use != null) {
	  	if (use.equals("H") || use.equals("HP") || use.equals("HV"))
	  		c.setUse(new Enumeration<ContactUse>(ContactUse.home));
	  	else if (use.equals("WP") || use.equals("DIR") || use.equals("PUB"))
	  		c.setUse(new Enumeration<ContactUse>(ContactUse.work));
	  	else if (use.equals("TMP"))
	  		c.setUse(new Enumeration<ContactUse>(ContactUse.temp));
	  	else if (use.equals("BAD"))
	  		c.setUse(new Enumeration<ContactUse>(ContactUse.old));
	  }
	  if (e.getAttribute("value") != null) {
	  	String[] url = e.getAttribute("value").split(":");
	  	if (url.length == 1)
	  		c.setValueSimple(url[0].trim());
	  	else {
	  		if (url[0].equals("tel"))
	  			c.setSystem(new Enumeration<ContactSystem>(ContactSystem.phone));
	  		else if (url[0].equals("mailto"))
	  			c.setSystem(new Enumeration<ContactSystem>(ContactSystem.email));
	  		c.setValueSimple(url[1].trim());
	  	}
	  }
	  return c;
	  
  }

	public HumanName makeNameFromEN(Element e) {
		if (e == null)
			return null;
	  HumanName hn = new HumanName();
  	String use = e.getAttribute("use");
	  if (use != null) {
	  	if (use.equals("L"))
	  		hn.setUse(new Enumeration<NameUse>(NameUse.usual));
	  	else if (use.equals("C"))
	  		hn.setUse(new Enumeration<NameUse>(NameUse.official));
	  	else if (use.equals("P") || use.equals("A"))
	  		hn.setUse(new Enumeration<NameUse>(NameUse.anonymous));
	  	else if (use.equals("TMP"))
	  		hn.setUse(new Enumeration<NameUse>(NameUse.temp));
	  	else if (use.equals("BAD"))
	  		hn.setUse(new Enumeration<NameUse>(NameUse.old));
	  }
	   
	  Node n = e.getFirstChild();
	  while (n != null) {
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		String v = n.getTextContent();
	  		if (n.getLocalName().equals("family"))
	  			hn.getFamily().add(makeString(v));
   			else if (n.getLocalName().equals("given"))
   				hn.getGiven().add(makeString(v));
  			else if (n.getLocalName().equals("prefix"))
  				hn.getPrefix().add(makeString(v));
  			else if (n.getLocalName().equals("suffix"))
  				hn.getSuffix().add(makeString(v));
	  	}  		
	  	n = n.getNextSibling();
	  }
	  return hn;
  }

	public DateTimeType makeDateTimeFromTS(Element ts) throws Exception {
		if (ts == null)
			return null;
		
    String v = ts.getAttribute("value");
    DateTimeType d = new DateTimeType();
	  d.setValue(DateAndTime.parseV3(v));
    return d;
  }

	public Period makePeriodFromIVL(Element ivl) throws Exception {
	  if (ivl == null)
	  	return null;
	  Period p = new Period();
	  Element low = cda.getChild(ivl, "low");
		if (low != null)
	  	p.setStart(makeDateTimeFromTS(low));
	  Element high = cda.getChild(ivl, "high");
		if (high != null)
	  	p.setEnd(makeDateTimeFromTS(high));
	  
		if (p.getStart() != null || p.getEnd() != null)
	    return p;
		else
			return null;
  }

	// this is a weird one - where CDA has an IVL, and FHIR has a date
	public DateTimeType makeDateTimeFromIVL(Element ivl) throws Exception {
	  if (ivl == null)
	  	return null;
	  if (ivl.hasAttribute("value")) 
	  	return makeDateTimeFromTS(ivl);
	  Element high =  cda.getChild(ivl, "high");
	  if (high != null)
	  	return makeDateTimeFromTS(high);
	  Element low =  cda.getChild(ivl, "low");
	  if (low != null)
	  	return makeDateTimeFromTS(low);
	  return null;
  }

	public Type makeStringFromED(Element e) throws Exception {
		if (e == null)
			return null;
		if (cda.getChild(e, "reference") != null) {
			if (cda.getChild(e, "reference").getAttribute("value").startsWith("#")) {
				Element t = cda.getByXmlId(cda.getChild(e, "reference").getAttribute("value").substring(1));
				String ot = t.getTextContent().trim();
				return Utilities.noString(ot) ? null : Factory.newString_(ot);
			} else
				throw new Exception("external references not handled yet "+cda.getChild(e, "reference").getAttribute("value"));
		}
		return Factory.newString_(e.getTextContent());
  }

	public Type makeTypeFromANY(Element e) throws Exception {
		if (e == null)
			return null;
	  String t = e.getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
	  if (Utilities.noString(t))
	  	throw new Exception("Missing type on RIM attribute with type any");
	  if (t.equals("CD") || t.equals("CE"))
	  	return makeCodeableConceptFromCD(e);
	  else if (t.equals("ST"))
	  	return makeStringFromED(e);
	  else
	  	throw new Exception("Not done yet (type = "+t+")");
  }

	public Type makeMatchingTypeFromIVL(Element ivl) throws Exception {
		if (ivl == null)
			return null;
	  if (ivl.getAttribute("value") != null)
	  	return makeDateTimeFromIVL(ivl);
	  if (cda.getChild(ivl, "low") != null || cda.getChild(ivl, "high") != null )
	    return makePeriodFromIVL(ivl);
	  throw new Exception("not handled yet");
  }

	public Type makeCodeableConceptFromNullFlavor(String nf) throws Exception {
	  // Some nullFlavors have explicit values in value sets. This can only be called where there aren't. 
	  if (nf == null || "".equals(nf))
	  	return null;
	  if ("NI".equals(nf))
	  	return null; // there's no code for this
	  if ("NA".equals(nf))
	  	return Factory.newCodeableConcept("unsupported", "http://hl7.org/fhir/data-absent-reason", "Unsupported"); // todo: is this reasonable? Why else would you use N/A?
	  if ("UNK".equals(nf))
	  	return Factory.newCodeableConcept("unknown", "http://hl7.org/fhir/data-absent-reason", "Unknown"); 
	  if ("ASKU".equals(nf))
	  	return Factory.newCodeableConcept("asked", "http://hl7.org/fhir/data-absent-reason", "Asked/Unknown"); 
	  if ("NAV".equals(nf))
	  	return Factory.newCodeableConcept("temp", "http://hl7.org/fhir/data-absent-reason", "Temporarily Unavailable"); 
	  if ("NASK".equals(nf))
	  	return Factory.newCodeableConcept("notasked", "http://hl7.org/fhir/data-absent-reason", "Not Asked"); 
	  if ("MSK".equals(nf))
	  	return Factory.newCodeableConcept("masked", "http://hl7.org/fhir/data-absent-reason", "Masked"); 
	  if ("OTH".equals(nf))
	  	return null; // well, what should be done? 
  	return null; // well, what should be done? 
	  	
  }

	public Type makeQuantityFromPQ(Element pq) {
		if (pq == null)
	    return null;
		Quantity qty = new Quantity();
		qty.setValueSimple(new BigDecimal(pq.getAttribute("value")));
		qty.setSystemSimple("http://unitsofmeasure.org");
		qty.setCodeSimple(pq.getAttribute("unit"));
		if (ucumSvc != null)
			qty.setUnitsSimple(ucumSvc.getCommonDisplay(qty.getCodeSimple()));
		else 
			qty.setUnitsSimple(qty.getCodeSimple());
		return qty;		
  }

}
