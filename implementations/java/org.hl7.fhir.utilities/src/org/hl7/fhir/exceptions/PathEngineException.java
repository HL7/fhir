package org.hl7.fhir.exceptions;

public class PathEngineException extends FHIRException {

	public PathEngineException() {
		super();
	}

	public PathEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PathEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	public PathEngineException(String message) {
		super(message);
	}

	public PathEngineException(Throwable cause) {
		super(cause);
	}

}
