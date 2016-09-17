package org.hl7.fhir.dstu3.elementmodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.exceptions.FHIRException;

public class Manager {

  public enum FhirFormat { XML, JSON, JSONLD, TURTLE, TEXT, VBAR;

    public String getExtension() {
      switch (this) {
      case JSON:
        return "json";
      case JSONLD:
        return "ld.json";
      case TURTLE:
        return "ttl";
      case XML:
        return "xml";
      case TEXT:
        return "txt";
      case VBAR:
        return "hl7";
      }
      return null;
    }
  }
  
  public static Element parse(IWorkerContext context, InputStream source, FhirFormat inputFormat) throws FHIRFormatError, DefinitionException, IOException, FHIRException {
    return makeParser(context, inputFormat).parse(source);
  }

  public static void compose(IWorkerContext context, Element e, OutputStream destination, FhirFormat outputFormat, OutputStyle style, String base) throws Exception {
    makeParser(context, outputFormat).compose(e, destination, style, base);
  }

  public static ParserBase makeParser(IWorkerContext context, FhirFormat format) {
    switch (format) {
    case JSON : return new JsonParser(context);
    case JSONLD : return new JsonLDParser(context);
    case XML : return new XmlParser(context);
    case TURTLE : return new TurtleParser(context);
    case VBAR : return new VerticalBarParser(context);
    case TEXT : throw new Error("Programming logic error: do not call makeParser for a text resource");
    }
    return null;
  }
  
  public static Element build(IWorkerContext context, StructureDefinition sd) {
    Property p = new Property(context, sd.getSnapshot().getElementFirstRep(), sd);
    Element e = new Element(null, p);
    return e;
  }

}
