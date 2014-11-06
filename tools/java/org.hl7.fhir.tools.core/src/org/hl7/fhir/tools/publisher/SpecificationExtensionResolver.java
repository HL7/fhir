package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.utils.WorkerContext.ExtensionDefinitionResult;
import org.hl7.fhir.instance.validation.ExtensionLocatorService;
import org.hl7.fhir.utilities.Utilities;

public class SpecificationExtensionResolver implements ExtensionLocatorService {

	private String specPath;
	private WorkerContext context;
	
	public SpecificationExtensionResolver(String specPath, WorkerContext context) {
	  super();
	  this.specPath = specPath;
	  this.context = context;
  }

	@Override
  public ExtensionLocationResponse locateExtension(String uri) {
		if (uri.startsWith("http://example.org") || uri.startsWith("http://acme.com"))
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.Unknown, null, null);
		
		// and we allow this one, though we don't advertise it in the error message below
    if (uri.startsWith("http://nema.org/"))
      return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.Unknown, null, null);
		
    // there's also this special case: 
    // the challenge with validating these is that what is valid depends on the type of resource
    // so there's a need for a special query validator to handle that
    if (uri.startsWith("http://hl7.org/fhir/query#"))
      return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.Unknown, null, null);
    
		if (!uri.startsWith("http://hl7.org/fhir/ExtensionDefinition/"))
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Extensions must either reference example.org, or acme.com, or be an internal valid reference to an extension defined in FHIR");
		String path = uri.substring(40);
		String filename = Utilities.path(specPath, path+".xml");
		if (!new File(filename).exists()) 
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Profile "+path+".xml not found");
		
		try {
		  ExtensionDefinitionResult ed = context.getExtensionDefinition(null, uri); 
		  if (ed == null) {
		    ExtensionDefinition ex = (ExtensionDefinition) new XmlParser().parse(new FileInputStream(filename));
		    ed = new ExtensionDefinitionResult(ex, ex.getElement().get(0));
		    // context.seeExtensionDefinition(ed);
		  } 
	  	return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.Located, ed.getExtensionDefinition(), null);
	    
    } catch (Exception e) {
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Error access extension definition: " +e.getMessage());
    }
		
  }

}
