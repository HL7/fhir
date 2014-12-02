package org.hl7.fhir.instance.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Resource;
import org.junit.Test;

public class MessageTest {

	@Test
	public void test() throws Exception {
		// Create new Atom Feed
		Bundle feed = new Bundle();
		
		// Serialize Atom Feed
		IParser comp = new JsonParser();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		comp.compose(os, feed);
		String json = os.toString();
		
		// Deserialize Atom Feed
		JsonParser parser = new JsonParser();
		InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
		Resource result = parser.parse(is);
		if (result == null)
			throw new Exception("Bundle was null");
	}

}
