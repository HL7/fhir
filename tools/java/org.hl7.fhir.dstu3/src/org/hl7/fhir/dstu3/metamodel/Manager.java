package org.hl7.fhir.dstu3.metamodel;

import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.utils.IWorkerContext;

public class Manager {

  public enum FhirFormat { XML, JSON, JSONLD, TURTLE }
  
  public static Element parse(IWorkerContext context, InputStream source, FhirFormat inputFormat, boolean check) throws Exception {
    return makeParser(context, inputFormat, check).parse(source);
  }

  public static void compose(IWorkerContext context, Element e, OutputStream destination, FhirFormat outputFormat, OutputStyle style, String base) throws Exception {
    makeParser(context, outputFormat, false).compose(e, destination, style, base);
  }

  private static ParserBase makeParser(IWorkerContext context, FhirFormat format, boolean check) {
    switch (format) {
    case JSON : return new JsonParser(context, check);
    case JSONLD : return new JsonLDParser(context, check);
    case XML : return new XmlParser(context, check);
    case TURTLE : return new TurtleParser(context, check);
    }
    return null;
  }
  
}
