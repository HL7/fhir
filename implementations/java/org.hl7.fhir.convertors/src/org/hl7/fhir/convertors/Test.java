package org.hl7.fhir.convertors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.AtomFeed;

public class Test {

	public static final String DEF_PATH = "c:\\work\\org.hl7.fhir\\build\\implementations\\java\\org.hl7.fhir.convertors\\samples\\";
	public static void main(String[] args) {
		CCDAConverter c = new CCDAConverter();
		try {
			AtomFeed a = c.convert(new FileInputStream(DEF_PATH + "ccda.xml"));
			String fx = DEF_PATH + "output.xml";
			XmlComposer x = new XmlComposer();
			x.compose(new FileOutputStream(fx),  a,  true);
			String fj = DEF_PATH + "output.json";
			JsonComposer j = new JsonComposer();
			j.compose(new FileOutputStream(fj),  a, true);
			System.out.println("done. save as "+fx+" and "+fj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
