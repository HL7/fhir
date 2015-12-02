package org.hl7.fhir.exceptions;

public class TerminologyServiceException extends FHIRException {

	public TerminologyServiceException() {
		super();
	}

	public TerminologyServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TerminologyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public TerminologyServiceException(String message) {
		super(message);
	}

	public TerminologyServiceException(Throwable cause) {
		super(cause);
	}

}
