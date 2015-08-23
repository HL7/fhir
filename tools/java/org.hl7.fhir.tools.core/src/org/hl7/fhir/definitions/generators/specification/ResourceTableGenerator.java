package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ResourceTableGenerator extends TableGenerator {
  
  public ResourceTableGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics) throws Exception {
    super(dest, page, pageName == null ? null : pageName.toLowerCase(), inlineGraphics);
  }

  public XhtmlNode generate(ElementDefn e, String prefix) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(dest, inlineGraphics);
    RenderMode mode = e.typeCode().equals("Logical") ? RenderMode.LOGICAL : RenderMode.RESOURCE;
    TableModel model = gen.initNormalTable(prefix, mode == RenderMode.LOGICAL);

    
    model.getRows().add(genElement(e, gen, true, e.getName(), false, prefix, mode));
    
    return gen.generate(model, prefix);
  }

 
}
