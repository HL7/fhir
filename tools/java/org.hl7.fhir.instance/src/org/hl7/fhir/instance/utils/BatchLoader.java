package org.hl7.fhir.instance.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Bundle.BundleType;
import org.hl7.fhir.instance.model.Bundle.HTTPVerb;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.client.FHIRToolingClient;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class BatchLoader {

	public static void main(String[] args) throws Exception {
	  if (args.length < 4) {
	  	System.out.println("Batch uploader takes 4 parameters in order: server base url, file/folder to upload, xml/json, and batch size");
	  } else {
	  	String server = args[0];
	  	String file = args[1];
	  	IParser p = args[2].equals("json") ? new JsonParser() : new XmlParser();
	  	int size = Integer.parseInt(args[3]);
	  	size = 500;
	  	if (file.endsWith(".xml")) {
	  		throw new Exception("Unimplemented file type "+file);
	  	} else if (file.endsWith(".json")) {
	  		throw new Exception("Unimplemented file type "+file);
	  	} else if (file.endsWith(".zip")) {
	  		LoadZipFile(server, file, p, size, 0, -1);
	  	} else if (new File(file).isDirectory()) {
	  	  LoadDirectory(server, file, p, size);
	  	} else 
	  		throw new Exception("Unknown file type "+file);
	  }
	}

	private static void LoadDirectory(String server, String file, IParser p, int size) throws Exception {
//    LoadZipFile(server, Utilities.path(file, "Patient.xml.zip"), p, size, 200, -1);
//    LoadZipFile(server, Utilities.path(file, "Binary.xml.zip"), p, size, 0, -1);
//	  LoadZipFile(server, Utilities.path(file, "DocumentReference.xml.zip"), p, size, 0, -1);
//	  LoadZipFile(server, Utilities.path(file, "Encounter.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "Organization.xml.zip"), p, size, 1030, -1);
	  LoadZipFile(server, Utilities.path(file, "Procedure.xml.zip"), p, size, 5788, -1);
//	  LoadZipFile(server, Utilities.path(file, "AllergyIntolerance.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "Condition.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "Immunization.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "MedicationStatement.xml.zip"), p, size, 9400, -1);
	  LoadZipFile(server, Utilities.path(file, "Observation-gen.xml.zip"), p, size, 2262, -1);
	  LoadZipFile(server, Utilities.path(file, "Observation-res.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "Observation-sh.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "Observation-vs.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "List.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "List-res.xml.zip"), p, size, 0, -1);
	  LoadZipFile(server, Utilities.path(file, "List-vs.xml.zip"), p, size, 0, -1);
    
  }

  private static void LoadZipFile(String server, String file, IParser p, int size, int start, int end) throws Exception {
		System.out.println("Load Zip file "+file);
	 	Bundle b = new Bundle();
	 	b.setType(BundleType.COLLECTION);
	 	b.setId(UUID.randomUUID().toString().toLowerCase());
	 	ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
	 	ZipEntry entry;
    while((entry = zip.getNextEntry())!=null)
    {
    	Resource r = p.parse(zip);
    	b.addEntry().setResource(r);
    }
	 	loadBundle(server, b, size, start, end);
	}

	private static int loadBundle(String server, Bundle b, int size, int start, int end) throws URISyntaxException {
		System.out.println("Post to "+server+". size = "+Integer.toString(size)+", start = "+Integer.toString(start)+", total = "+Integer.toString(b.getEntry().size()));
		FHIRToolingClient client = new FHIRToolingClient(server);
	  int c = start;
	  if (end == -1)
	    end = b.getEntry().size();
	  while (c < end) {
		 	Bundle bt = new Bundle();
		 	bt.setType(BundleType.BATCH);		 	
		 	bt.setId(UUID.randomUUID().toString().toLowerCase());
		 	for (int i = c; i < Math.min(b.getEntry().size(), c+size); i++) {
		 		BundleEntryComponent be = bt.addEntry();
		 		be.setResource(b.getEntry().get(i).getResource());
		 		be.getRequest().setMethod(HTTPVerb.PUT);
		 		be.getRequest().setUrl(be.getResource().getResourceType().toString()+"/"+be.getResource().getId());
		 	}
			System.out.print("  posting..");
			long ms = System.currentTimeMillis();
		 	Bundle resp = client.transaction(bt);
		 	
		 	for (int i = 0; i < resp.getEntry().size(); i++) {
		 	  BundleEntryComponent t = resp.getEntry().get(i);
		 	  if (!t.getResponse().getStatus().startsWith("2")) { 
		 	    System.out.println("failed status at "+Integer.toString(i)+": "+t.getResponse().getStatus());
		 	    return c+i;
		 	  }
		 	}
		 	c = c + size;
      System.out.println("  ..done: "+Integer.toString(c)+". ("+Long.toString(System.currentTimeMillis()-ms)+" ms)");
	  }
		System.out.println(" done");
		return c;
	}

}
