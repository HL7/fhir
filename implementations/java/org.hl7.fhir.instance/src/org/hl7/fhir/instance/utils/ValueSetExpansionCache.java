package org.hl7.fhir.instance.utils;

/*
Copyright (c) 2011+, HL7, Inc
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.client.EFhirClientException;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class ValueSetExpansionCache implements ValueSetExpanderFactory {

  public class CacheAwareExpander implements ValueSetExpander {

	  @Override
	  public ValueSetExpansionOutcome expand(ValueSet source) throws ETooCostly {
	  	if (expansions.containsKey(source.getUrl()))
	  		return expansions.get(source.getUrl());
	  	ValueSetExpander vse = new ValueSetExpanderSimple(context, ValueSetExpansionCache.this);
	  	ValueSetExpansionOutcome vso = vse.expand(source);
	  	if (vso.getError() != null && context.hasClient()) {
	  	  // well, we'll see if the designated server can expand it, and if it can, we'll cache it locally
	  	  try {
	  	    vso = new ValueSetExpansionOutcome(context.getClient().expandValueset(source), null);
	  	    new XmlParser().compose(new FileOutputStream(Utilities.path(cacheFolder, makeFile(source.getUrl()))), vso.getValueset(), true);
	  	  } catch (EFhirClientException e) {
          try {
            if (!e.getServerErrors().isEmpty()) {
            OperationOutcome oo = e.getServerErrors().get(0);
            ToolingExtensions.setStringExtension(oo, VS_ID_EXT, source.getUrl());
            new XmlParser().compose(new FileOutputStream(Utilities.path(cacheFolder, makeFile(source.getUrl()))), oo, true);
            }
            vso = new ValueSetExpansionOutcome(vso.getService(), e.getMessage());
          } catch (Exception e1) {
          }
        } catch (Exception e) {
	  	  }
	  	}
	  	expansions.put(source.getUrl(), vso);
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
        Resource r = new XmlParser().parse(new FileInputStream(Utilities.path(cacheFolder, f)));
        if (r instanceof OperationOutcome) {
          OperationOutcome oo = (OperationOutcome) r;
          expansions.put(ToolingExtensions.getExtension(oo,VS_ID_EXT).getValue().toString(), new ValueSetExpansionOutcome(new XhtmlComposer().setXmlOnly(true).composePlainText(oo.getText().getDiv())));
        } else {
          ValueSet vs = (ValueSet) r; 
          expansions.put(vs.getUrl(), new ValueSetExpansionOutcome(vs, null));
        }
      }
    }
  }
  
	@Override
	public ValueSetExpander getExpander() {
		return new CacheAwareExpander();
		// return new ValueSetExpander(valuesets, codesystems);
	}

}
