package org.hl7.fhir.definitions.generators.specification;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ResourceTableGenerator extends TableGenerator {
  public ResourceTableGenerator(String dest, PageProcessor page, String pageName) {
    super(dest, page, pageName);
  }

  public XhtmlNode generate(ElementDefn e) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(dest);
    TableModel model = gen.new TableModel();
    
    model.getTitles().add(gen.new Title(null, null, "Name", null, null, 0));
    model.getTitles().add(gen.new Title(null, null, "Card.", null, null, 0));
    model.getTitles().add(gen.new Title(null, null, "Type", null, null, 100));
    model.getTitles().add(gen.new Title(null, null, "Description", null, null, 0));
    model.getTitles().add(gen.new Title(null, null, "Constraints", null, null, 0));
    
    model.getRows().add(genElement(e, gen, true, e.getName()));
    
    return gen.generate(model);
  }

 
}
