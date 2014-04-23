package org.hl7.fhir.instance.test;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.model.Conformance;

public class CLientTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		testConformance();

	}

	private static void testConformance() throws Exception {
//		FHIRClient client = new FHIRSimpleClient("http://hl7connect.healthintersections.com.au/svc/fhir");
//		Conformance stmt = client.getConformanceStatement();
//		System.out.println("Retrieved conformance statement from "+stmt.getSoftware().getName().getValue());
	}

}
