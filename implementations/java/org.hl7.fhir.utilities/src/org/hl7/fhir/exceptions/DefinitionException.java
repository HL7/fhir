package org.hl7.fhir.exceptions;

public class DefinitionException extends FHIRException {

	public DefinitionException() {
		super();
	}

	public DefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DefinitionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DefinitionException(String message) {
		super(message);
	}

	public DefinitionException(Throwable cause) {
		super(cause);
	}

}
