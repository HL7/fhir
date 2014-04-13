package org.hl7.fhir.instance.formats;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.utilities.Utilities;

public abstract class ParserBase extends FormatUtilities {

  public class ResourceOrFeed {
    protected Resource resource;
    protected AtomFeed feed;
    protected List<AtomCategory> taglist;
    
    public Resource getResource() {
      return resource;
    }
    public AtomFeed getFeed() {
      return feed;
    }
		public List<AtomCategory> getTaglist() {
			return taglist;
		}
    
  }
  

  protected Map<String, Object> idMap = new HashMap<String, Object>();

//protected Element resolveElement(String id) {
//  return idMap.get(id);
//}


  protected int parseIntegerPrimitive(String value) {
    if (value.startsWith("+") && Utilities.IsInteger(value.substring(1)))
      value = value.substring(1);
	return java.lang.Integer.parseInt(value);
  }
  protected int parseIntegerPrimitive(java.lang.Long value) {
    if (value < java.lang.Integer.MIN_VALUE || value > java.lang.Integer.MAX_VALUE) {
        throw new IllegalArgumentException
            (value + " cannot be cast to int without changing its value.");
    }
    return value.intValue();
  }


  protected DateAndTime parseDateTimePrimitive(String value) throws ParseException {
    return new DateAndTime(value);
  }


  protected String parseCodePrimitive(String value) {
    return value;
  }

  protected DateAndTime parseDatePrimitive(String value) throws ParseException {
    return new DateAndTime(value);
  }

  protected BigDecimal parseDecimalPrimitive(BigDecimal value) {
    return value;
  }

  protected BigDecimal parseDecimalPrimitive(String value) {
    return new BigDecimal(value);
  }

  protected String parseUriPrimitive(String value) throws Exception {
  	 return value;
  }

  protected byte[] parseBase64BinaryPrimitive(String value) {
    return Base64.decodeBase64(value.getBytes());
  }
  
  protected String parseOidPrimitive(String value) {
    return value;
  }

  protected Boolean parseBooleanPrimitive(String value) {
    return java.lang.Boolean.valueOf(value);
  }
  
  protected Boolean parseBooleanPrimitive(Boolean value) {
    return java.lang.Boolean.valueOf(value);
  }
  
  protected DateAndTime parseInstantPrimitive(String value) throws Exception {
    return new DateAndTime(value);
  }

  protected String parseIdPrimitive(String value) {
    return value;
  }

  protected String parseStringPrimitive(String value) {
    return value;
  }

  protected String parseUuidPrimitive(String value) {
    return value;
  }

}
