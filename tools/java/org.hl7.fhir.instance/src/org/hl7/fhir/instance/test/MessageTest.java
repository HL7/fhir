package org.hl7.fhir.instance.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.hl7.fhir.instance.formats.Composer;
import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.ResourceOrFeed;
import org.hl7.fhir.instance.model.AtomFeed;
import org.junit.Test;

public class MessageTest {

	@Test
	public void test() throws Exception {
		// Create new Atom Feed
		AtomFeed feed = new AtomFeed();
		
		// Serialize Atom Feed
		Composer comp = new JsonComposer();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		comp.compose(os, feed, false);
		String json = os.toString();
		
		// Deserialize Atom Feed
		JsonParser parser = new JsonParser();
		InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
		ResourceOrFeed result = parser.parseGeneral(is);
		if (result.getFeed() == null)
			throw new Exception("Feed was null");
	}

}
