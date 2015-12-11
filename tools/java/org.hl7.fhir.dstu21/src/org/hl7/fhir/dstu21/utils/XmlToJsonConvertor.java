package org.hl7.fhir.dstu21.utils;

import org.hl7.fhir.dstu21.formats.JsonCreator;
import org.hl7.fhir.dstu21.model.StructureDefinition;
import org.w3c.dom.Element;

/** this task converts from an XML representation to a JSON representation based on metadata **/

public class XmlToJsonConvertor {

  private Element root;
  private JsonCreator json;
  private IWorkerContext context;
  
  public void convert() throws Exception {
    if (root == null)
      throw new Exception("No resourced provided");
    if (json == null)
      throw new Exception("No JsonCreator provided");
    if (context == null)
      throw new Exception("No context provided");
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+root.getNodeName());
    if (sd == null)
      throw new Exception("Unknown resource type "+root.getNodeName());
    DefinitionNavigator dn = new DefinitionNavigator(context, sd);
    convert(root, dn);
  }

  private void convert(Element root2, DefinitionNavigator dn) {
//    if (isPrimitive(dn.current().isPrimitive()) {
//      
//    } else {
//      json.beginObject();
//    }
//
  }
}
