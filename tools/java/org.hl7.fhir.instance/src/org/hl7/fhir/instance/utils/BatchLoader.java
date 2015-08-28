package org.hl7.fhir.instance.utils;

import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleType;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.client.FHIRToolingClient;

public class BatchLoader {

	public static void main(String[] args) throws Exception {
	  if (args.length < 4) {
	  	System.out.println("Batch uploader takes 4 parameters in order: server base url, file to upload, xml/json, and batch size");
	  } else {
	  	String server = args[0];
	  	String file = args[1];
	  	IParser p = args[2].equals("json") ? new JsonParser() : new XmlParser();
	  	int size = Integer.parseInt(args[3]);
	  	if (file.endsWith(".xml")) {
	  		throw new Exception("Unimplemented file type "+file);
	  	} else if (file.endsWith(".json")) {
	  		throw new Exception("Unimplemented file type "+file);
	  	} else if (file.endsWith(".zip")) {
	  		LoadZipFile(server, file, p, size);
	  	} else 
	  		throw new Exception("Unknown file type "+file);
	  }
	}

	private static void LoadZipFile(String server, String file, IParser p, int size) throws Exception {
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
	 	loadBundle(server, b, size);
	}

	private static void loadBundle(String server, Bundle b, int size) throws URISyntaxException {
		System.out.println("Post to "+server+". size = "+Integer.toString(size));
		FHIRToolingClient client = new FHIRToolingClient(server);
	  int c = 0;
	  while (c < b.getEntry().size()) {
		 	Bundle bt = new Bundle();
		 	bt.setType(BundleType.TRANSACTION);
		 	bt.setId(UUID.randomUUID().toString().toLowerCase());
		 	for (int i = c; i < Math.min(b.getEntry().size(), c+size); i++) {
		 		bt.addEntry(b.getEntry().get(i));
		 	}
			System.out.print(".");
		 	client.transaction(bt);
		 	c = c + size;
	  	
	  }
		System.out.println(" done");
	
	}

}
