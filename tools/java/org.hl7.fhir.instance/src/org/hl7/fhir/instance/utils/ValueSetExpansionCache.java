package org.hl7.fhir.instance.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.client.EFhirClientException;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationDefinition;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class ValueSetExpansionCache implements ValueSetExpanderFactory {

  public class CacheAwareExpander implements ValueSetExpander {

	  @Override
	  public ValueSetExpansionOutcome expand(ValueSet source) {
	  	if (expansions.containsKey(source.getIdentifierSimple()))
	  		return expansions.get(source.getIdentifierSimple());
	  	ValueSetExpander vse = new ValueSetExpanderSimple(context, ValueSetExpansionCache.this);
	  	ValueSetExpansionOutcome vso = vse.expand(source);
	  	if (vso.getError() != null && context.hasClient()) {
	  	  // well, we'll see if the designated server can expand it, and if it can, we'll cache it locally
	  	  try {
	  	    vso = new ValueSetExpansionOutcome(context.getClient().expandValueset(source), null);
	  	    new XmlComposer().compose(new FileOutputStream(Utilities.path(cacheFolder, makeFile(source.getIdentifierSimple()))), vso.getValueset(), true);
	  	  } catch (EFhirClientException e) {
          try {
            OperationOutcome oo = e.getServerErrors().get(0);
            oo.setStringExtension(VS_ID_EXT, source.getIdentifierSimple());
            new XmlComposer().compose(new FileOutputStream(Utilities.path(cacheFolder, makeFile(source.getIdentifierSimple()))), oo, true);
            vso = new ValueSetExpansionOutcome(vso.getService(), e.getMessage());
          } catch (Exception e1) {
          }
        } catch (Exception e) {
	  	  }
	  	}
	  	expansions.put(source.getIdentifierSimple(), vso);
	  	return vso;
	  }

    private String makeFile(String url) {
      return url.replace("$", "").replace(":", "").replace("//", "/").replace("/", "_")+".xml";
    }
  }

  private static final String VS_ID_EXT = "http://tools/cache";

	private Map<String, ValueSetExpansionOutcome> expansions = new HashMap<String, ValueSetExpansionOutcome>();
  private WorkerContext context;
  private String cacheFolder;
	
	public ValueSetExpansionCache(WorkerContext context, String cacheFolder) throws Exception {
    super();
    this.context = context;
    this.cacheFolder = cacheFolder;
    if (this.cacheFolder != null)
      loadCache();
  }
  
	private void loadCache() throws Exception {
	  String[] files = new File(cacheFolder).list();
    for (String f : files) {
      if (f.endsWith(".xml")) {
        Resource r = (ValueSet) new XmlParser().parse(new FileInputStream(Utilities.path(cacheFolder, f)));
        if (r instanceof OperationOutcome) {
          OperationOutcome oo = (OperationOutcome) r;
          expansions.put(oo.getExtension(VS_ID_EXT).getValue().toString(), new ValueSetExpansionOutcome(new XhtmlComposer().composePlainText(oo.getText().getDiv())));
        } else {
          ValueSet vs = (ValueSet) r; 
          expansions.put(vs.getIdentifierSimple(), new ValueSetExpansionOutcome(vs, null));
        }
      }
    }
  }

  @Override
	public ValueSetExpander getExpander() {
		return new CacheAwareExpander();
		// return new ValueSetExpanderSimple(valuesets, codesystems);
	}

}
