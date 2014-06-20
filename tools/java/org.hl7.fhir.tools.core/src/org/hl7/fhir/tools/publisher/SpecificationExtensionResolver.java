package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.validation.ExtensionLocatorService;
import org.hl7.fhir.utilities.Utilities;

public class SpecificationExtensionResolver implements ExtensionLocatorService {

	private String specPath;
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public SpecificationExtensionResolver(String specPath) {
	  super();
	  this.specPath = specPath;
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
    
		if (!uri.startsWith("http://hl7.org/fhir/Profile/"))
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Extensions must either reference example.org, or acme.com, or be an internal valid reference to an extension defined in FHIR");
		String[] path = uri.substring(28).split("\\#");
		if (path.length != 2)
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Internal extension references must start with http://hl7.org/fhir/Porfile/ and then have the profile name and a fragment reference");
		String filename = Utilities.path(specPath, path[0]+".profile.xml");
		if (!new File(filename).exists()) 
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Profile "+path[0]+".profile.xml not found");
		
		try {
	    Profile profile = profiles.get(filename); 
	    if (profile == null) {
	       profile = (Profile) new XmlParser().parse(new FileInputStream(filename));
	       profiles.put(filename, profile);
	    }
	    for (ProfileExtensionDefnComponent ext : profile.getExtensionDefn()) {
	    	if (ext.getCodeSimple().equals(path[1]))
	  			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.Located, ext, null);
	    }
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Extension code '"+path[1]+"' not found");
	    
    } catch (Exception e) {
			return new ExtensionLocationResponse(uri, ExtensionLocatorService.Status.NotAllowed, null, "Error access extension definition: " +e.getMessage());
    }
		
  }

}
