package org.hl7.fhir.definitions.generators.specification;

import java.util.Set;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class DataTypeTableGenerator extends TableGenerator {
  public DataTypeTableGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics) throws Exception {    
    super(dest, page, page.getDefinitions().getSrcFile(pageName)+"-definitions.html", inlineGraphics);
  }

  public XhtmlNode generate(ElementDefn e, Set<String> outputTracker) throws Exception {
    HierarchicalTableGenerator gen = new HierarchicalTableGenerator(dest, inlineGraphics, false);
    TableModel model = gen.initNormalTable("", false);
    
    model.getRows().add(genElement(e, gen, false, e.getName(), false, "", RenderMode.DATATYPE, true, e.getStandardsStatus()));
    
    return gen.generate(model, "", 0, outputTracker);
  }

 
}
