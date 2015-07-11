package org.hl7.fhir.instance.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.utilities.CSFileInputStream;

public class WorkerContextFactory {

	private static void loadFromFile(WorkerContext theCtx, InputStream stream, String name) throws Exception {
		XmlParser xml = new XmlParser();
		Bundle f = (Bundle) xml.parse(stream);
		for (BundleEntryComponent e : f.getEntry()) {

			if (e.getFullUrl() == null) {
				System.out.println("unidentified resource in " + name+" (no fullUrl)");
			}
			if (e.getResource() instanceof StructureDefinition)
				theCtx.seeProfile(e.getFullUrl(), (StructureDefinition) e.getResource());
			else if (e.getResource() instanceof ValueSet)
				theCtx.seeValueSet(e.getFullUrl(), (ValueSet) e.getResource());
			else if (e.getResource() instanceof StructureDefinition)
				theCtx.seeExtensionDefinition(e.getFullUrl(), (StructureDefinition) e.getResource());
			else if (e.getResource() instanceof ConceptMap)
				theCtx.getMaps().put(((ConceptMap) e.getResource()).getUrl(), (ConceptMap) e.getResource());
		}
	}

	private static void loadFromPack(WorkerContext theCtx, String path) throws Exception {
		loadFromStream(theCtx, new CSFileInputStream(path));
	}

	private static void loadFromStream(WorkerContext theCtx, InputStream stream) throws Exception {
		ZipInputStream zip = new ZipInputStream(stream);
		ZipEntry ze;
		while ((ze = zip.getNextEntry()) != null) {
			if (ze.getName().endsWith(".xml")) {
				String name = ze.getName();
				loadFromFile(theCtx, zip, name);
			}
			zip.closeEntry();
		}
		zip.close();
	}

	public static WorkerContext fromDefinitions(Map<String, byte[]> source) throws Exception {
		WorkerContext res = new WorkerContext();
		for (String name : source.keySet()) {
			if (name.endsWith(".xml")) {
				loadFromFile(res, new ByteArrayInputStream(source.get(name)), name);
			}
		}
		return res;
	}

	// -- Initializations
	/**
	 * Load the working context from the validation pack
	 * 
	 * @param path
	 *           filename of the validation pack
	 * @return
	 * @throws Exception
	 */
	public static WorkerContext fromPack(String path) throws Exception {
		WorkerContext res = new WorkerContext();
		loadFromPack(res, path);
		return res;
	}

	public static WorkerContext fromClassPath() throws Exception {
		WorkerContext res = new WorkerContext();
		loadFromStream(res, WorkerContextFactory.class.getResourceAsStream("validation.zip"));
		return res;
	}

}
