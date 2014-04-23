package org.hl7.fhir.instance.client;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Resource;

public class TagListRequest {
	private List<AtomCategory> payload;
	private int httpStatus = -1;
	private List<Integer> successfulStatuses = new ArrayList<Integer>();
	private List<Integer> errorStatuses = new ArrayList<Integer>();
	
	public TagListRequest(List<AtomCategory> payload, int httpStatus, List<Integer> successfulStatuses, List<Integer> errorStatuses) {
		this.payload = payload;
		this.httpStatus = httpStatus;
		if(successfulStatuses != null) {
			this.successfulStatuses.addAll(successfulStatuses);
		}
		if(errorStatuses != null) {
			this.errorStatuses.addAll(errorStatuses);
		}
	}
	
	public TagListRequest(List<AtomCategory> payload, int httpStatus) {
		this.payload = payload;
		this.httpStatus = httpStatus;
	}
	
	public TagListRequest(List<AtomCategory> payload, int httpStatus, int successfulStatus) {
		this.payload = payload;
		this.httpStatus = httpStatus;
		this.successfulStatuses.add(successfulStatus);
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public List<AtomCategory> getPayload() {
		return payload;
	}
		
	public boolean isSuccessfulRequest() {
		return successfulStatuses.contains(httpStatus) && !errorStatuses.contains(httpStatus) && httpStatus > 0;
	}
	
	public boolean isUnsuccessfulRequest() {
		return !isSuccessfulRequest();
	}
	
	public void addSuccessStatus(int status) {
		this.successfulStatuses.add(status);
	}
	
	public void addErrorStatus(int status) {
		this.errorStatuses.add(status);
	}
}
