package org.hl7.fhir.definitions.generators.specification;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class DataTypeTableGenerator extends TableGenerator {
  public DataTypeTableGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics) throws Exception {    
    super(dest, page, "datatype-definitions.html", inlineGraphics);
  }

  public XhtmlNode generate(ElementDefn e) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(dest, inlineGraphics);
    TableModel model = gen.initNormalTable();
    
    model.getRows().add(genElement(e, gen, false, e.getName(), false));
    
    return gen.generate(model);
  }

 
}
