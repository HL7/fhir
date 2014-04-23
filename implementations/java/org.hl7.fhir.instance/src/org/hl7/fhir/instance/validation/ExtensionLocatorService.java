package org.hl7.fhir.instance.validation;

import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;



/**
 * This interface is used to provide extension location services for the validator
 * 
 * when it encounters an extension, it asks this server to locate it, or tell it 
 * whether to ignore the extension, or mark it as invalid
 * 
 * @author Grahame
 *
 */
public interface ExtensionLocatorService {

  public enum Status {
    Located, NotAllowed, Unknown
  }

  public class ExtensionLocationResponse {
    private Status status;
    private ProfileExtensionDefnComponent definition;
		private String message;

    public ExtensionLocationResponse(Status status, ProfileExtensionDefnComponent definition, String message) {
      super();
      this.status = status;
      this.definition = definition;
      this.message = message;
    }

    public Status getStatus() {
      return status;
    }

    public ProfileExtensionDefnComponent getDefinition() {
      return definition;
    }

		public String getMessage() {
	    return message;
    }
    
  }

  public ExtensionLocationResponse locateExtension(String uri);
}
