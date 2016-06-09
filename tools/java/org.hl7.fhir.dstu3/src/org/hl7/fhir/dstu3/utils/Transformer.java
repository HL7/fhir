package org.hl7.fhir.dstu3.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class Transformer {

	private String txServer;
	private String definitions;
	private List<String> folders = new ArrayList<String>();
	private String source;
	private String mapUri;
	private String output;
	private String message;
	private StructureMapUtilities scu;

	public String getTxServer() {
		return txServer;
	}
	public void setTxServer(String txServer) {
		this.txServer = txServer;
	}
	public String getDefinitions() {
		return definitions;
	}
	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}
	public List<String> getFolders() {
		return folders;
	}

	public void addFolder(String value) {
		folders.add(value);
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}





	public String getMapUri() {
		return mapUri;
	}
	public void setMapUri(String mapUri) {
		this.mapUri = mapUri;
	}
	public boolean process() {
		try {
	    System.out.println("  .. load definitions from "+definitions);
			IWorkerContext context = SimpleWorkerContext.fromPack(definitions);
			scu = new StructureMapUtilities(context, new HashMap<String, StructureMap>(), null);

			for (String folder : folders) {
		    System.out.println("  .. load additional definitions from "+folder);
				((SimpleWorkerContext) context).loadFromFolder(folder);
				loadMaps(folder);
			}
	    System.out.println("  .. load source from "+source);
			Element e = Manager.parse(context, new FileInputStream(source), FhirFormat.XML);

			Bundle bundle = new Bundle();
			StructureMap map = scu.getLibrary().get(mapUri);
			if (map == null)
				throw new Error("Unable to find map "+mapUri);
			scu.transform(null, e, map, bundle);
			new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(output), bundle);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
			return false;
		} 
	}
	
	private void loadMaps(String folder) {
		for (String f : new File(folder).list()) {
			try {
				StructureMap map = scu.parse(TextFile.fileToString(Utilities.path(folder, f)));
				scu.getLibrary().put(map.getUrl(), map);
			} catch (Exception e) {
			}
		}

	}
	public String getMessage() {
		return message;
	}


}
