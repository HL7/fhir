package org.hl7.fhir.dstu2.exceptions;

import org.hl7.fhir.dstu2.exceptions.FHIRException;

public class FHIRFormatError extends FHIRException {

	private static final long serialVersionUID = 1L;

	public FHIRFormatError() {
		super();
	}

	public FHIRFormatError(String message, Throwable cause) {
		super(message, cause);
	}

	public FHIRFormatError(String message) {
		super(message);
	}

	public FHIRFormatError(Throwable cause) {
		super(cause);
	}

}
