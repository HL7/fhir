package org.hl7.fhir.dstu3.elementmodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonCreator;
import org.hl7.fhir.dstu3.formats.JsonCreatorCanonical;
import org.hl7.fhir.dstu3.formats.JsonCreatorGson;
import org.hl7.fhir.utilities.Utilities;

public class JsonLDParser extends ParserBase {

	private JsonCreator json;
  private String base;
  private String jsonLDBase = "http://build.fhir.org/";

	public JsonLDParser(IWorkerContext context) {
		super(context);
	}

	@Override
	public Element parse(InputStream stream) {
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
	public void compose(Element e, OutputStream stream, OutputStyle style, String base) throws Exception {
	  this.base = base;
		OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
		if (style == OutputStyle.CANONICAL)
			json = new JsonCreatorCanonical(osw);
		else
			json = new JsonCreatorGson(osw);
		json.setIndent(style == OutputStyle.PRETTY ? "  " : "");
		json.beginObject();
    prop("@context", jsonLDBase+e.getType()+".jsonld");
		Set<String> done = new HashSet<String>();
		for (Element child : e.getChildren()) {
			compose(e.getName(), e, done, child);
		}
		json.endObject();
		json.finish();
		osw.flush();
	}

	private void compose(String path, Element e, Set<String> done, Element child) throws IOException {
		if (!child.isList()) {
			compose(path, child);
		} else if (!done.contains(child.getName())) {
			done.add(child.getName());
			List<Element> list = e.getChildrenByName(child.getName());
			composeList(path, list);
		}
	}

	private void composeList(String path, List<Element> list) throws IOException {
		// there will be at least one element
    String en = list.get(0).getProperty().getDefinition().getBase().getPath();
    if (en == null) 
      en = list.get(0).getProperty().getDefinition().getPath();
    boolean doType = false;
    if (en.endsWith("[x]")) {
      en = en.substring(0, en.length()-3);
      doType = true;        
    }
    if (doType)
      en = en + Utilities.capitalize(list.get(0).getType());

    openArray(en);
    for (Element item : list) { 
      open(null);
      if (item.isPrimitive() || isPrimitive(item.getType())) {
        if (item.hasValue())
          primitiveValue(item);
      }
      Set<String> done = new HashSet<String>();
      for (Element child : item.getChildren()) {
        compose(path+"."+item.getName(), item, done, child);
      }
      close();
    }
    closeArray();
	}

	private void primitiveValue(Element item) throws IOException {
		json.name(item.fhirType()+".value");
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
	  Property p = element.hasElementProperty() ? element.getElementProperty() : element.getProperty();
    String en = p.getDefinition().getBase().getPath();
    if (en == null) 
      en = p.getDefinition().getPath();
    boolean doType = false;
    if (en.endsWith("[x]")) {
      en = en.substring(0, en.length()-3);
      doType = true;        
    }
    if (doType)
      en = en + Utilities.capitalize(element.getType());

    if (element.hasChildren() || element.hasComments() || element.hasValue()) {
			open(en);
      if (element.getProperty().isResource()) {
	      prop("@context", jsonLDBase+element.getType()+".jsonld");
        element = element.getChildren().get(0);
      }
	    if (element.isPrimitive() || isPrimitive(element.getType())) {
	      if (element.hasValue())
	        primitiveValue(element);
	    }
	    
			Set<String> done = new HashSet<String>();
			for (Element child : element.getChildren()) {
				compose(path+"."+element.getName(), element, done, child);
			}
	    if ("Coding".equals(element.getType()))
	      decorateCoding(element);
      if ("CodeableConcept".equals(element.getType()))
        decorateCodeableConcept(element);
      if ("Reference".equals(element.getType()))
        decorateReference(element);
			
			close();
		}
	}

  private void decorateReference(Element element) throws IOException {
    String ref = element.getChildValue("reference");
    if (ref != null && (ref.startsWith("http://") || ref.startsWith("https://"))) {
      json.name("Resource.reference");
      json.value(ref);
    } else if (base != null && ref != null && ref.contains("/")) {
      json.name("Resource.reference");
      json.value(base+"/"+ref);
    }
  }

  protected void decorateCoding(Element coding) throws IOException {
    String system = coding.getChildValue("system");
    String code = coding.getChildValue("code");
    
    if (system == null)
      return;
    if ("http://snomed.info/sct".equals(system)) {
      json.name("Resource.concept");
      json.value("http://snomed.info/sct#"+code);
    } else if ("http://loinc.org".equals(system)) {
      json.name("Resource.concept");
      json.value("http://loinc.org/owl#"+code);
    }  
  }

  private void decorateCodeableConcept(Element element) throws IOException {
    for (Element c : element.getChildren("coding")) {
      decorateCoding(c);
    }
  }

}
