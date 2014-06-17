package org.hl7.fhir.instance.formats;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Resource;

public abstract class ComposerBase extends FormatUtilities implements Composer {

  protected String xhtmlMessage;
  
  @Override
  public void setSuppressXhtml(String message) {
    xhtmlMessage = message;    
  }

  public String composeString(Resource resource, boolean pretty) throws Exception {
    return new String(composeBytes(resource, pretty));
  }

  public String composeString(AtomFeed feed, boolean pretty) throws Exception {
    return new String(composeBytes(feed, pretty));
  }
  
  public String composeString(List<AtomCategory> tags, boolean pretty) throws Exception {
    return new String(composeBytes(tags, pretty));
  }
  
  public byte[] composeBytes(Resource resource, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, resource, true);
    return bytes.toByteArray();
  }

  public byte[] composeBytes(AtomFeed feed, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, feed, true);
    return bytes.toByteArray();
  }
  
  public byte[] composeBytes(List<AtomCategory> tags, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, tags, true);
    return bytes.toByteArray();
  }

}
