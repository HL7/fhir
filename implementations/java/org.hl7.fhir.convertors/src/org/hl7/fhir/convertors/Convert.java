package org.hl7.fhir.convertors;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.Contact.ContactUse;
import org.hl7.fhir.instance.model.DateTime;
import org.hl7.fhir.instance.model.HumanName.NameUse;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Instant;
import org.hl7.fhir.instance.model.Address.AddressUse;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Convert {

	CDAUtilities cda;
	
	public Convert(CDAUtilities cda) {
		super();
		this.cda = cda;
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

	private boolean isGuid(String r) {
		return r.matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
	}

	public Instant makeInstantFromTS(Element child) throws Exception {
	  Instant i = new Instant();
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
	  	String ot = cda.getChild(cv, "originalText").getTextContent().trim();
			cc.setTextSimple(Utilities.noString(ot) ? null : ot);
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
		if (isGuid(r)) 
			c.setSystemSimple("urn:uuid:"+r);
		else if (UriForOid(r) != null)
			c.setSystemSimple(UriForOid(r));
		else 
			c.setSystemSimple("urn:oid:"+r);
	  return c;
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

	public String_ makeString(String v) {
	  String_ s = new String_();
	  s.setValue(v);
	  return s;
  }

	public Contact makeContactFromTEL(Element e) {
		if (e == null)
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
	  	if (url[0].equals("tel"))
	  		c.setSystem(new Enumeration<ContactSystem>(ContactSystem.phone));
	  	else if (url[0].equals("mailto"))
	  		c.setSystem(new Enumeration<ContactSystem>(ContactSystem.email));
	  	c.setValueSimple(url[1].trim());
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

	public DateTime makeDateTimeFromTS(Element ts) throws Exception {
    String v = ts.getAttribute("value");
    DateTime d = new DateTime();
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

}
