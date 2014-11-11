package org.hl7.fhir.instance.client;


/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/


import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.Coding;

public class TagListRequest {
	private List<Coding> payload;
	private int httpStatus = -1;
	private List<Integer> successfulStatuses = new ArrayList<Integer>();
	private List<Integer> errorStatuses = new ArrayList<Integer>();
	
	public TagListRequest(List<Coding> payload, int httpStatus, List<Integer> successfulStatuses, List<Integer> errorStatuses) {
		this.payload = payload;
		this.httpStatus = httpStatus;
		if(successfulStatuses != null) {
			this.successfulStatuses.addAll(successfulStatuses);
		}
		if(errorStatuses != null) {
			this.errorStatuses.addAll(errorStatuses);
		}
	}
	
	public TagListRequest(List<Coding> payload, int httpStatus) {
		this.payload = payload;
		this.httpStatus = httpStatus;
	}
	
	public TagListRequest(List<Coding> payload, int httpStatus, int successfulStatus) {
		this.payload = payload;
		this.httpStatus = httpStatus;
		this.successfulStatuses.add(successfulStatus);
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public List<Coding> getPayload() {
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
