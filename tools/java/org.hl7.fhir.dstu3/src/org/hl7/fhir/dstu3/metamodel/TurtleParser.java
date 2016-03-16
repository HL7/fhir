package org.hl7.fhir.dstu3.metamodel;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.RdfGenerator;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Complex;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Section;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Subject;
import org.hl7.fhir.dstu3.utils.IWorkerContext;

public class TurtleParser extends ParserBase {

  public TurtleParser(IWorkerContext context, boolean check) {
    super(context, check);
  }
  @Override
  public Element parse(InputStream stream) throws Exception {
    throw new NotImplementedException("not done yet");
  }
  @Override
  public void compose(Element e, OutputStream stream, OutputStyle style, String identity) throws Exception {
		RdfGenerator ttl = new RdfGenerator(stream);
		//      ttl.setFormat(FFormat);
		ttl.prefix("fhir", "http://hl7.org/fhir/");
		ttl.prefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		ttl.prefix("owl", "http://www.w3.org/2002/07/owl#");
		
		Section section = ttl.section("resource");
		Subject subject;
		if (identity != null) 
			subject = section.triple("<"+identity+">", "a", "fhir:"+e.getType());
		else
		  subject = section.triple("_", "a", "fhir:"+e.getType());
		subject.predicate("a", "owl:Ontology");
		
		for (Element child : e.getChildren()) {
			composeElement(subject, child);
		}
		ttl.commit(false);
  }
  
	protected void decorateCoding(Complex t, Element coding) {
		String system = coding.getChildValue("system");
		String code = coding.getChildValue("code");
		
		if (system == null)
			return;
		if ("http://snomed.info/sct".equals(system)) {
			t.prefix("sct", "http://snomed.info/sct/");
			t.predicate("a", "sct:"+code);
		} else if ("http://snomed.info/sct".equals(system)) {
			t.prefix("loinc", "http://loinc.org/owl#");
			t.predicate("a", "loinc:"+code);
		}  
	}

	private void decorateCodeableConcept(Complex t, Element element) {
	  for (Element c : element.getChildren("coding")) {
	  	decorateCoding(t, c);
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

	  Complex t = ctxt.predicate("fhir:"+en);
	  if (doType || element.getProperty().getDefinition().getType().size() > 1)
	  	t.predicate("a", "fhir:"+element.getType());
	  if (element.hasValue())
	  	t.predicate("fhir:value", ttlLiteral(element.getValue()));
	  if (element.hasIndex())
	  	t.predicate("fhir:index", Integer.toString(element.getIndex()));

	  if ("Coding".equals(element.getType()))
	  	decorateCoding(t, element);
	  if ("CodeableConcept".equals(element.getType()))
	  	decorateCodeableConcept(t, element);
	  		
		for (Element child : element.getChildren()) {
			composeElement(t, child);
		}
	}
	
	protected String ttlLiteral(String value) {
		return "\"" +RdfGenerator.escape(value, true) + "\"";
	}

}
