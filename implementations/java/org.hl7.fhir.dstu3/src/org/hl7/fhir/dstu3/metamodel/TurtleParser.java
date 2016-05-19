package org.hl7.fhir.dstu3.metamodel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.Turtle;
import org.hl7.fhir.dstu3.utils.Turtle.Complex;
import org.hl7.fhir.dstu3.utils.Turtle.Section;
import org.hl7.fhir.dstu3.utils.Turtle.Subject;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class TurtleParser extends ParserBase {

  private String base;
  
  public TurtleParser(IWorkerContext context) {
    super(context);
  }
  @Override
  public Element parse(InputStream input) throws Exception {
    Turtle src = new Turtle();
    src.parse(TextFile.streamToString(input));
    throw new NotImplementedException("not done yet");
  }
  @Override
  public void compose(Element e, OutputStream stream, OutputStyle style, String base) throws Exception {
    this.base = base;
    
		Turtle ttl = new Turtle();
		compose(e, ttl, base);
		ttl.commit(stream, false);
  }

  public void compose(Element e, Turtle ttl, String base) throws Exception {
		ttl.prefix("fhir", "http://hl7.org/fhir/");
		ttl.prefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		ttl.prefix("owl", "http://www.w3.org/2002/07/owl#");
		ttl.prefix("xs", "http://www.w3.org/2001/XMLSchema#");
		
		Section section = ttl.section("resource");
		Subject subject;
		String id = e.getChildValue("id");
		if (base != null && id != null) 
			subject = section.triple("<"+base+"/"+e.getType()+"/"+id+">", "a", "fhir:"+e.getType());
		else
		  subject = section.triple("_", "a", "fhir:"+e.getType());
		subject.linkedPredicate("fhir:nodeRole", "fhir:treeRoot", linkResolver == null ? null : linkResolver.resolvePage("rdf.html#tree-root"));

		for (Element child : e.getChildren()) {
			composeElement(subject, child);
		}
  }
  
  protected void decorateReference(Complex t, Element coding) {
    String ref = coding.getChildValue("reference");
    if (ref != null && (ref.startsWith("http://") || ref.startsWith("https://")))
      t.linkedPredicate("fhir:link", "<"+ref+">", linkResolver == null ? null : linkResolver.resolvePage("rdf.html#reference"));
    else if (base != null && ref != null && ref.contains("/")) {
      t.linkedPredicate("fhir:link", "<"+Utilities.appendForwardSlash(base)+ref+">", linkResolver == null ? null : linkResolver.resolvePage("rdf.html#reference"));
    }
  }
  
	protected void decorateCoding(Complex t, Element coding) {
		String system = coding.getChildValue("system");
		String code = coding.getChildValue("code");
		
		if (system == null)
			return;
		if ("http://snomed.info/sct".equals(system)) {
			t.prefix("sct", "http://snomed.info/id/");
			t.linkedPredicate("fhir:concept", "sct:"+code, linkResolver == null ? null : linkResolver.resolvePage("rdf.html#concept"));
		} else if ("http://loinc.org".equals(system)) {
			t.prefix("loinc", "http://loinc.org/owl#");
			t.linkedPredicate("fhir:concept", "loinc:"+code, linkResolver == null ? null : linkResolver.resolvePage("rdf.html#concept"));
		}  
	}

	private void composeElement(Complex ctxt, Element element) {
		if ("xhtml".equals(element.getType())) // need to decide what to do with this
			return;
		String en = element.getProperty().getDefinition().getBase().getPath();
    if (en == null) 
      en = element.getProperty().getDefinition().getPath();
		boolean doType = false;
			if (en.endsWith("[x]")) {
				en = en.substring(0, en.length()-3);
				doType = true;				
			}
	   if (doType || (element.getProperty().getDefinition().getType().size() > 1 && !allReference(element.getProperty().getDefinition().getType())))
	     en = en + Utilities.capitalize(element.getType());

	  Complex t = ctxt.linkedPredicate("fhir:"+en, linkResolver == null ? null : linkResolver.resolveProperty(element.getProperty()));
	  if (element.hasValue())
	  	t.linkedPredicate("fhir:value", ttlLiteral(element.getValue(), element.getType()), linkResolver == null ? null : linkResolver.resolveType(element.getType()));
	  if (element.hasIndex())
	  	t.linkedPredicate("fhir:index", Integer.toString(element.getIndex()), linkResolver == null ? null : linkResolver.resolvePage("rdf.html#index"));

	  if ("Coding".equals(element.getType()))
	  	decorateCoding(t, element);
    if ("Reference".equals(element.getType()))
      decorateReference(t, element);
	  		
		for (Element child : element.getChildren()) {
			composeElement(t, child);
		}
	}
	
	private boolean allReference(List<TypeRefComponent> types) {
	  for (TypeRefComponent t : types) {
	    if (!t.getCode().equals("Reference"))
	      return false;
	  }
    return true;
  }
	protected String ttlLiteral(String value, String type) {
	  String xst = "";
	  if (type.equals("boolean"))
	    xst = "^^xsd:boolean";
	  else if (type.equals("integer") || type.equals("unsignedInt") || type.equals("positiveInt"))
      xst = "^^xsd:int";
    else if (type.equals("decimal"))
      xst = "^^xsd:decimal";
    else if (type.equals("base64Binary"))
      xst = "^^xsd:base64Binary";
    else if (type.equals("instant"))
      xst = "^^xsd:dateTime";
    else if (type.equals("time"))
      xst = "^^xsd:time";
    else if (type.equals("date") || type.equals("dateTime") ) {
      String v = value;
      if (v.length() > 10) {
        int i = value.substring(10).indexOf("-");
        if (i == -1)
          i = value.substring(10).indexOf("+");
        v = i == -1 ? value : value.substring(0,  10+i);
      }
      if (v.length() > 10)
        xst = "^^xsd:dateTime";
      else if (v.length() == 10)
        xst = "^^xsd:date";
      else if (v.length() == 7)
        xst = "^^xsd:gYearMonth";
      else if (v.length() == 4)
        xst = "^^xsd:gYear";
    }
	  
		return "\"" +Turtle.escape(value, true) + "\""+xst;
	}


}
