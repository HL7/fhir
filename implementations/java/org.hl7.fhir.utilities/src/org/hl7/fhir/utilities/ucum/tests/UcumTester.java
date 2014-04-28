package org.hl7.fhir.utilities.ucum.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ucum.UcumEssenceService;
import org.hl7.fhir.utilities.ucum.UcumModel;
import org.hl7.fhir.utilities.ucum.UcumService;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class UcumTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("UCUM Tester - parameters:");
			System.out.println("  -definitions [filename] - filename for the UCUM definitions");
			System.out.println("  -tests [fileanme] - filename for the UCUM tests");
			System.out.println("See http://unitsofmeasure.org/trac/ for source files for both ucum definitions");
			System.out.println("(ucum-essence.xml) and tests (http://unitsofmeasure.org/trac/wiki/FunctionalTests)");
		} else {
			UcumTester worker = new UcumTester();
			int i = 0;
			while (i < args.length) {
				String a = args[i];
				i++;
				if (a.equalsIgnoreCase("-definitions"))
					worker.setDefinitions(args[i]);
				else if (a.equalsIgnoreCase("-tests"))
					worker.setTests(args[i]);
				else 
					System.out.println("Unknown parameter: '"+a+"'");
				i++;
			}
			worker.execute();
		}
	}

	private String definitions;
	private String tests;
	public String getDefinitions() {
		return definitions;
	}
	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	

	private UcumService ucumSvc;
	
	private void execute() throws Exception {
	  ucumSvc = new UcumEssenceService(definitions);
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();

		xpp.setInput(new FileInputStream(tests), null);

		int eventType = xpp.next();
		if (eventType != XmlPullParser.START_TAG)
			throw new XmlPullParserException("Unable to process XML document");
		if (!xpp.getName().equals("ucumTests")) 
			throw new XmlPullParserException("Unable to process XML document: expected 'ucumTests' but found '"+xpp.getName()+"'");

		xpp.next();
		while (xpp.getEventType() != XmlPullParser.END_TAG) {
			if (xpp.getEventType() == XmlPullParser.TEXT) {
				if (Utilities.isWhitespace(xpp.getText()))
					xpp.next();
				else
					throw new XmlPullParserException("Unexpected text "+xpp.getText());
			} else if (xpp.getName().equals("history")) 
				skipElement(xpp);
			else if (xpp.getName().equals("validation")) 
				runValidationTests(xpp);
			else if (xpp.getName().equals("displayNameGeneration")) 
				runDisplayNameGeneration(xpp);
			else 
				throw new XmlPullParserException("unknown element name "+xpp.getName());
		}
		xpp.next();
	}
	
	private void runDisplayNameGeneration(XmlPullParser xpp) throws Exception {
		xpp.next();
		while (xpp.getEventType() != XmlPullParser.END_TAG) {
			if (xpp.getEventType() == XmlPullParser.TEXT) {
				if (Utilities.isWhitespace(xpp.getText()))
					xpp.next();
				else
					throw new XmlPullParserException("Unexpected text "+xpp.getText());
			} else if (xpp.getName().equals("case")) 
				runDisplayNameGenerationCase(xpp);
			else 
				throw new XmlPullParserException("unknown element name "+xpp.getName());
		}
		xpp.next();
  }

	private void runDisplayNameGenerationCase(XmlPullParser xpp) throws Exception {
	  String id = xpp.getAttributeValue(null, "id");
	  String unit = xpp.getAttributeValue(null, "unit");
	  String display = xpp.getAttributeValue(null, "display");
	  
	  String res = ucumSvc.analyse(unit);
		System.out.println("Analyse Test "+id+": the unit '"+unit+"' ==> "+res);

	  if (!res.equals(display)) {
	  	throw new Exception("The unit '"+unit+"' was expected to be displayed as '"+display+"', but was displayed as "+res);
	  }
 		while (xpp.getEventType() != XmlPullParser.END_TAG) 
	    xpp.next();
 		xpp.next();
  }
	
	private void runValidationTests(XmlPullParser xpp) throws Exception {
		xpp.next();
		while (xpp.getEventType() != XmlPullParser.END_TAG) {
			if (xpp.getEventType() == XmlPullParser.TEXT) {
				if (Utilities.isWhitespace(xpp.getText()))
					xpp.next();
				else
					throw new XmlPullParserException("Unexpected text "+xpp.getText());
			} else if (xpp.getName().equals("case")) 
				runValidationCase(xpp);
			else 
				throw new XmlPullParserException("unknown element name "+xpp.getName());
		}
		xpp.next();
  }
	
	private void runValidationCase(XmlPullParser xpp) throws Exception {
	  String id = xpp.getAttributeValue(null, "id");
	  String unit = xpp.getAttributeValue(null, "unit");
	  boolean valid = "true".equals(xpp.getAttributeValue(null, "valid"));
	  String reason = xpp.getAttributeValue(null, "reason");
	  
	  String res = ucumSvc.validate(unit);
		boolean result = res == null;
		if (result)
		  System.out.println("Validation Test "+id+": the unit '"+unit+"' is valid");
		else
		  System.out.println("Validation Test "+id+": the unit '"+unit+"' is not valid because "+res);

	  if (valid != result) {
	  	if (valid)
	  		throw new Exception("The unit '"+unit+"' was expected to be valid, but wasn't accepted");
	  	else
	  		throw new Exception("The unit '"+unit+"' was expected to be invalid because '"+reason+"', but was accepted");
	  }
 		while (xpp.getEventType() != XmlPullParser.END_TAG) 
	    xpp.next();
 		xpp.next();
  }
	
	private void skipElement(XmlPullParser xpp) throws Exception {
		xpp.next();
		while (xpp.getEventType() != XmlPullParser.END_TAG) {
			if (xpp.getEventType() == XmlPullParser.START_TAG)
				skipElement(xpp);
			else 
				xpp.next();
		}
		xpp.next();
  }

}
