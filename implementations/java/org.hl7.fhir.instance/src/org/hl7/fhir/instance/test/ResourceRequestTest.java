package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.client.ResourceRequest;
import org.junit.Test;

public class ResourceRequestTest {

	@Test
	public void testGetHttpStatus() {
		ResourceRequest resourceRequest = new ResourceRequest(null, 201, 201, null);
		assertTrue(resourceRequest.isSuccessfulRequest());
		assertFalse(resourceRequest.isUnsuccessfulRequest());
		
	}

	@Test
	public void testGetPayload() {
		List<Integer> errorStatuses = new ArrayList<Integer>();
		errorStatuses.add(404);
		errorStatuses.add(500);
		errorStatuses.add(403);
		List<Integer> successfulStatuses = new ArrayList<Integer>();
		successfulStatuses.add(200);
		successfulStatuses.add(201);
		ResourceRequest resourceRequest1 = new ResourceRequest(null, 201, successfulStatuses, errorStatuses, null);
		ResourceRequest resourceRequest2 = new ResourceRequest(null, 500, successfulStatuses, errorStatuses, null);
		ResourceRequest resourceRequest3 = new ResourceRequest(null, 503, successfulStatuses, errorStatuses, null);
		assertTrue(resourceRequest1.isSuccessfulRequest());
		assertFalse(resourceRequest1.isUnsuccessfulRequest());
		assertFalse(resourceRequest2.isSuccessfulRequest());
		assertTrue(resourceRequest2.isUnsuccessfulRequest());
		assertFalse(resourceRequest3.isSuccessfulRequest());
		assertTrue(resourceRequest3.isUnsuccessfulRequest());
	}

}
