package org.hl7.fhir.instance.formats;

import java.io.InputStream;

import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
import org.hl7.fhir.instance.model.Resource;
import org.xmlpull.v1.XmlPullParser;

public interface Parser {

  public ResourceOrFeed parseGeneral(InputStream input) throws Exception;
  public Resource parse(InputStream input) throws Exception;

}
