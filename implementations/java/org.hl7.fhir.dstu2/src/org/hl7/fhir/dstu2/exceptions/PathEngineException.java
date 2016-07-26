package org.hl7.fhir.dstu2.exceptions;

import org.hl7.fhir.dstu2.exceptions.FHIRException;

public class PathEngineException extends FHIRException {

	public PathEngineException() {
		super();
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
