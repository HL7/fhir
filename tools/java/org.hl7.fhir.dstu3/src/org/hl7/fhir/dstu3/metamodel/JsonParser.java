package org.hl7.fhir.dstu3.metamodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonCreator;
import org.hl7.fhir.dstu3.formats.JsonCreatorCanonical;
import org.hl7.fhir.dstu3.formats.JsonCreatorGson;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.utilities.Utilities;

public class JsonParser extends ParserBase {

	private JsonCreator json;

	public JsonParser(IWorkerContext context, boolean check) {
		super(context, check);
	}

	@Override
	public Element parse(InputStream stream) throws Exception {
		throw new NotImplementedException("not done yet");
	}


	protected void prop(String name, String value) throws IOException {
		if (name != null)
			json.name(name);
		json.value(value);
	}

	protected void open(String name) throws IOException {
		if (name != null) 
			json.name(name);
		json.beginObject();
	}

	protected void close() throws IOException {
		json.endObject();
	}

	protected void openArray(String name) throws IOException {
		if (name != null) 
			json.name(name);
		json.beginArray();
	}

	protected void closeArray() throws IOException {
		json.endArray();
	}


	@Override
	public void compose(Element e, OutputStream stream, OutputStyle style, String identity) throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
		if (style == OutputStyle.CANONICAL)
			json = new JsonCreatorCanonical(osw);
		else
			json = new JsonCreatorGson(osw);
		json.setIndent(style == OutputStyle.PRETTY ? "  " : "");
		json.beginObject();
		prop("resourceType", e.getType());
		Set<String> done = new HashSet<String>();
		composeComments(e);
		for (Element child : e.getChildren()) {
			compose(e.getName(), e, done, child);
		}
		json.endObject();
		json.finish();
		osw.flush();
	}

	private void compose(String path, Element e, Set<String> done, Element child) throws IOException {
		if (!child.getProperty().isList()) {
			compose(path, child);
		} else if (!done.contains(child.getName())) {
			done.add(child.getName());
			List<Element> list = e.getChildrenByName(child.getName());
			composeList(path, list);
		}
	}

	private void composeList(String path, List<Element> list) throws IOException {
		// there will be at least one element
		String name = list.get(0).getName();
		boolean complex = true;
		if (list.get(0).getProperty().isPrimitive()) {
			boolean prim = false;
			complex = false;
			for (Element item : list) { 
				if (item.hasValue())
					prim = true;
				if (item.hasChildren() || item.hasComments())
					complex = true;
			}
			if (prim) {
				openArray(name);
				for (Element item : list) { 
					if (item.hasValue())
						primitiveValue(null, item);
					else
						json.nullValue();
				}				
				closeArray();
			}
			name = "_"+name;
		}
		if (complex) {
			openArray(name);
			for (Element item : list) { 
				if (item.hasChildren() || item.hasComments()) {
					open(null);
					composeComments(item);
					if (item.getProperty().isResource()) {
						item = item.getChildren().get(0);
						prop("resourceType", item.getType());
					}
					Set<String> done = new HashSet<String>();
					for (Element child : item.getChildren()) {
						compose(path+"."+name+"[]", item, done, child);
					}
					close();
				} else
					json.nullValue();
			}				
			closeArray();
		}		
	}

	private void primitiveValue(String name, Element item) throws IOException {
		if (name != null)
			json.name(name);
	  String type = item.getType();
	  if (Utilities.existsInList(type, "boolean"))
	  	json.value(item.getValue().equals("true") ? new Boolean(true) : new Boolean(false));
	  else if (Utilities.existsInList(type, "integer", "unsignedInt", "positiveInt"))
	  	json.value(new Integer(item.getValue()));
	  else if (Utilities.existsInList(type, "decimal"))
	  	json.value(new BigDecimal(item.getValue()));
	  else
	  	json.value(item.getValue());	
	}

	private void compose(String path, Element element) throws IOException {
		String name = element.getName();
		if (element.getProperty().isPrimitive() || ParserBase.isPrimitive(element.getType())) {
			if (element.hasValue())
				primitiveValue(name, element);
			name = "_"+name;
		}
		if (element.hasChildren() || element.hasComments()) {
			open(name);
			composeComments(element);
			if (element.getProperty().isResource()) {
				element = element.getChildren().get(0);
				prop("resourceType", element.getType());
			}
			Set<String> done = new HashSet<String>();
			for (Element child : element.getChildren()) {
				compose(path+"."+element.getName(), element, done, child);
			}
			close();
		}
	}

	private void composeComments(Element element) throws IOException {
    if (element.hasComments()) {
    	openArray("fhir_comments");
    	for (String s : element.getComments())
    		json.value(s);
    	closeArray();
    }
	
	}

}
