package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ResourceTableGenerator extends TableGenerator {
  
  public ResourceTableGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics) throws Exception {
    super(dest, page, pageName == null ? null : pageName.toLowerCase(), inlineGraphics);
  }

  public XhtmlNode generate(ResourceDefn r, String prefix) throws Exception {
    HierarchicalTableGenerator gen = new HierarchicalTableGenerator(dest, inlineGraphics, true);
    ElementDefn e = r.getRoot();
    RenderMode mode = e.typeCode().equals("Logical") && hasLogicalMapping(e) ? RenderMode.LOGICAL : RenderMode.RESOURCE;
    TableModel model = gen.initNormalTable(prefix, mode == RenderMode.LOGICAL, true);

    
    model.getRows().add(genElement(e, gen, true, e.getName(), false, prefix, mode, true, r.getStatus()));
    
    return gen.generate(model, prefix, 0, null);
  }

  private boolean hasLogicalMapping(ElementDefn e) {
    if (e.getMappings().containsKey("http://hl7.org/fhir/logical"))
        return true;
    for (ElementDefn c : e.getElements())
      if (hasLogicalMapping(c))
        return true;
    return false;
  }

 
}
