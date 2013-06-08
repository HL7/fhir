/*
Copyright (c) 2011-2013, HL7, Inc
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
package org.hl7.fhir.instance.client;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Map;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;


/**
 * no security. no proxy
 * @author Grahame
 *
 */
public class FHIRSimpleClient implements FHIRClient {

	private String baseUrl;
	
	public FHIRSimpleClient(String baseUrl) throws MalformedURLException {
		super();
		this.baseUrl = baseUrl;
	}

	
	private HttpURLConnection makeClient(String tail) throws Exception {
		HttpURLConnection client = (HttpURLConnection) new URL(baseUrl+tail).openConnection();
		client.addRequestProperty("accept", "text/xml");
		return client;
	}
	
	
	@Override
	public Conformance getConformanceStatement() throws EFhirClientException {
		try {
			URLConnection client = makeClient("/metadata");
			return (Conformance) new XmlParser().parse(client.getInputStream());
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	@Override
	public AtomEntry read(ResourceType type, String id) throws EFhirClientException {
		try {
			URLConnection client = makeClient("/"+type.toString().toLowerCase()+"/@"+id);
			Resource r = new XmlParser().parse(client.getInputStream());
			AtomEntry e = new AtomEntry();
			e.setUpdated(javax.xml.bind.DatatypeConverter.parseDateTime(client.getHeaderField("Last-Updated")));
			e.setId(id);
			e.getLinks().put("self", client.getHeaderField("Content-Location"));
			e.setResource(r);
			return e;
		} catch (Exception e) {
			throw new EFhirClientException(e);
		}
	}

	@Override
	public AtomEntry vread(ResourceType type, String id, String versionid) throws EFhirClientException {
//		try {
//			URLConnection client = makeClient("/"+type.toString().toLowerCase()+"/@"+id+"/history/@"+versionid);
//			return new XmlParser().parse(client.getInputStream());
//		} catch (Exception e) {
//			throw new EFhirClientException(e);
//		}
		throw new EFhirClientException("not implemented yet");
	}

	@Override
	public AtomEntry update(String id, AtomEntry resource) throws EFhirClientException {
		throw new EFhirClientException("not implemented yet");
//		try {
//			HttpURLConnection client = makeClient("/"+resource.getResourceType().toString().toLowerCase()+"/@"+id);
//			client.setRequestMethod("PUT");
//			client.setDoOutput(true); // Triggers POST.
//			client.setRequestProperty("Content-Type", "text/xml+fhir;charset=UTF-8");
//			OutputStream output = null;
//			try {
//				output = client.getOutputStream();
//				new XmlComposer().compose(output, resource, false);
//			} finally {
//				if (output != null) 
//					output.close();  
//			}
//			return new XmlParser().parse(client.getInputStream());		
//		} catch (Exception e) {
//			throw new EFhirClientException(e);
//		}
	}

	@Override
	public void delete(ResourceType type, String id)
			throws EFhirClientException {
		// TODO Auto-generated method stub

	}

	@Override
	public String create(AtomEntry resource) throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate, ResourceType type, String id)
			throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate, ResourceType type)
			throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate) throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed search(ResourceType type, Map<String, String> params)
			throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed batch(AtomFeed batch) throws EFhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

}
