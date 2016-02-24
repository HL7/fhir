package org.hl7.fhir.dstu3.formats;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Complex;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Section;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Subject;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.XMLWriter;

public abstract class RdfParserBase extends ParserBase implements IParser  {

  protected abstract void composeResource(Complex complex, Resource resource) throws IOException;

  @Override
  public ParserType getType() {
    return ParserType.RDF_TURTLE;
  }

  @Override
  public Resource parse(InputStream input) throws IOException, FHIRFormatError {
    throw new Error("Parsing not implemented yet");
  }

  @Override
  public Type parseType(InputStream input, String knownType) throws IOException, FHIRFormatError {
    throw new Error("Parsing not implemented yet");
  }

  private String url;
  
  @Override
  public void compose(OutputStream stream, Resource resource) throws IOException {
      RdfGenerator ttl = new RdfGenerator(stream);
//      ttl.setFormat(FFormat);
      ttl.prefix("fhir", "http://hl7.org/fhir/");
      ttl.prefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
      Section section = ttl.section("resource");
      Subject subject;
      if (url != null) 
        subject = section.triple("<"+url+">", "a", "fhir:"+resource.getResourceType().toString());
      else
        subject = section.triple("_", "a", "fhir:"+resource.getResourceType().toString());

      composeResource(subject, resource);
      try {
        ttl.commit(false);
      } catch (Exception e) {
        throw new IOException(e); 
      }
  }

  @Override
  public void compose(OutputStream stream, Type type, String rootName) throws IOException {
    throw new Error("Not supported in RDF");  
  }

  protected String ttlLiteral(String value) {
  	return "\"" +RdfGenerator.escape(value, true) + "\"";
  }

  protected void composeXhtml(Complex t, String string, String string2, XhtmlNode div, int i) {
	}


}
