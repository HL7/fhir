package org.hl7.fhir.igtools.publisher;

import org.hl7.fhir.dstu3.metamodel.ParserBase;
import org.hl7.fhir.dstu3.metamodel.Property;

public class IGLinkResolver implements ParserBase.ILinkResolver {

  @Override
  public String resolveType(String type) {
    return "test-"+type+".html";
  }

  @Override
  public String resolveProperty(Property property) {
    return "testprop-"+property.getName()+".html";
  }

  @Override
  public String resolvePage(String name) {
    return "testpage-"+name;
  }

}
