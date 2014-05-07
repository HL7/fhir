package org.hl7.fhir.instance.model;

import java.util.List;

import org.hl7.fhir.utilities.Utilities;

/**
 * See http://www.healthintersections.com.au/?p=1941
 * 
 * @author Grahame
 *
 */
public class Comparison {

	public class MatchProfile {

  }

	public static boolean matches(String c1, String c2, MatchProfile profile)  {
	  if (Utilities.noString(c1) || Utilities.noString(c2))
	  	return false;
	  c1 = Utilities.normalize(c1);
	  c2 = Utilities.normalize(c2);
	  return c1.equals(c2);
	}

	public static <T extends Enum<?>> boolean matches(Enumeration<T> e1, Enumeration<T> e2, MatchProfile profile)  {
	  if (e1 == null || e2 == null)
	  	return false;
	  return e1.getValue().equals(e2.getValue());
	}

	public static boolean matches(CodeableConcept c1, CodeableConcept c2, MatchProfile profile) throws Exception {
	  if (profile != null) 
	  	throw new Exception("Not Implemented Yet");
	  
	  if (c1.getCoding().isEmpty() && c2.getCoding().isEmpty()) {
	  	return matches(c1.getTextSimple(), c2.getTextSimple(), null);
	  } else {
	  	// in the absence of specific guidance, we just require that all codes match
	  	boolean ok = true;
	  	for (Coding c : c1.getCoding()) {
	  		ok = ok && inList(c2.getCoding(), c, null);
	  	}
	  	for (Coding c : c2.getCoding()) {
	  		ok = ok && inList(c1.getCoding(), c, null);
	  	}
	  	return ok;
	  }
  }

	public static void merge(CodeableConcept dst, CodeableConcept src) {
		if (dst.getText() == null && src.getText() != null)
			dst.setText(src.getText());  
  }

	
	public static boolean inList(List<Coding> list, Coding c, MatchProfile profile) throws Exception {
	  for (Coding item : list) {
	  	if (matches(item, c, profile))
	  		return true;
	  }
	  return false;
  }

	public static boolean matches(Coding c1, Coding c2, MatchProfile profile) throws Exception {
	  if (profile != null) 
	  	throw new Exception("Not Implemented Yet");
		
	  // in the absence of a profile, we ignore version
	  return matches(c1.getSystemSimple(), c2.getSystemSimple(), null) && matches(c1.getCodeSimple(), c2.getCodeSimple(), null);
  }

	public static boolean matches(Identifier i1, Identifier i2, MatchProfile profile) throws Exception {
	  if (profile != null) 
	  	throw new Exception("Not Implemented Yet");
		
	  // in the absence of a profile, we ignore version
	  return matches(i1.getSystemSimple(), i2.getSystemSimple(), null) && matches(i1.getValueSimple(), i2.getValueSimple(), null);
  }

	public static void merge(Identifier dst, Identifier src) {
		if (dst.getUse() == null && src.getUse() != null)
			dst.setUse(src.getUse());  
		if (dst.getLabel() == null && src.getLabel() != null)
			dst.setLabel(src.getLabel());  
		if (dst.getPeriod() == null && src.getPeriod() != null)
			dst.setPeriod(src.getPeriod());  
		if (dst.getAssigner() == null && src.getAssigner() != null)
			dst.setAssigner(src.getAssigner());  
  }

	public static boolean matches(Contact c1, Contact c2, Object profile) throws Exception {
	  if (profile != null) 
	  	throw new Exception("Not Implemented Yet");
		
	  // in the absence of a profile, we insist on system
	  return matches(c1.getSystem(), c2.getSystem(), null) && matches(c1.getValueSimple(), c2.getValueSimple(), null);
  }

	public static void merge(Contact dst, Contact src) {
		if (dst.getUse() == null && src.getUse() != null)
			dst.setUse(src.getUse());  
		if (dst.getPeriod() == null && src.getPeriod() != null)
			dst.setPeriod(src.getPeriod());  
  }

}
