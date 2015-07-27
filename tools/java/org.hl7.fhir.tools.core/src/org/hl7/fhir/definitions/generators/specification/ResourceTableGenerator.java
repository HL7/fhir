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
    boolean isLogical = e.typeCode().equals("Logical");
    TableModel model = gen.initNormalTable(prefix, isLogical);

    
    model.getRows().add(genElement(e, gen, true, e.getName(), false, prefix, isLogical));
    
    return gen.generate(model, prefix);
  }

 
}
