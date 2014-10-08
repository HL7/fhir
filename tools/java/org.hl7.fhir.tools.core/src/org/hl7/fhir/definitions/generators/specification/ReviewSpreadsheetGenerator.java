package org.hl7.fhir.definitions.generators.specification;

import java.util.Calendar;
import java.util.List;

import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.utilities.XLSXmlGenerator;
import org.hl7.fhir.utilities.XLSXmlGenerator.SimpleSheet;

public class ReviewSpreadsheetGenerator {

  public void generate(String filename, String author, Calendar genDate, Profile profile) throws Exception {
    XLSXmlGenerator xls = new XLSXmlGenerator(filename, author, new DateAndTime(genDate).toString());
    xls.addSimpleSheet("Review Details", generateReviewHeader(xls));
    for (ProfileStructureComponent sc : profile.getStructure())
      xls.addSimpleSheet(sc.getName(), generateReviewSheet(xls, sc));
    xls.finish();
  }

  private List<List<String>> generateReviewSheet(XLSXmlGenerator xls, ProfileStructureComponent sc) {
    SimpleSheet res = xls.new SimpleSheet();
    res.addRow("Path", "Name", "Cardinality", "Type", "Profile", "Value Set", "Definition", "Your Comments");
    ElementComponent ed = sc.getSnapshot().getElement().get(0);
    String path = ed.getPath();
    res.addRow(path, sc.getName(), "", sc.getType(), "", "", ed.getDefinition().getFormal(), "");
    processRows(path, sc.getSnapshot().getElement(), 1, res);
    return res;
  }

  private int processRows(String path, List<ElementComponent> list, int i, SimpleSheet res) {
    ElementComponent ed = list.get(i);
    while (i < list.size() && ed.getPath().startsWith(path+".")) {
      if (ed.getDefinition().getType().size() == 0) {
        res.addRow(ed.getPath(), ed.getName(), describeCardinality(ed.getDefinition()), "", "", "", ed.getDefinition().getFormal(), "");
        i = processRows(ed.getPath(), list, i, res);
      } else if (ed.getDefinition().getType().size() == 1) {
        res.addRow(ed.getPath(), ed.getName(), describeCardinality(ed.getDefinition()), ed.getDefinition().getType().get(0).getCode(), ed.getDefinition().getType().get(0).getProfile(), describeBinding(ed.getDefinition()), ed.getDefinition().getFormal(), "");
      } else {
        res.addRow(ed.getPath(), ed.getName(), describeCardinality(ed.getDefinition()), "", "", describeBinding(ed.getDefinition()), ed.getDefinition().getFormal(), "");
      }
      i++;
      if (i < list.size())
        ed = list.get(i);
    }
    return i;
    
  }

  private String describeBinding(ElementDefinitionComponent def) {
    if (def.getBinding() == null)
      return "";
    return def.getBinding().getName();
  }

  private String describeCardinality(ElementDefinitionComponent def) {
    return def.getMin()+".."+def.getMax();
  }

  private List<List<String>> generateReviewHeader(XLSXmlGenerator xls) {
    SimpleSheet res = xls.new SimpleSheet();
    res.addRow("Name:", "", "The name of the person filling out this sheet");
    res.addRow("Email address", "", "The email address to contact about these comments");
    res.addRow("Vendor", "", "The name of the vendor these comments represent");
    res.addRow("Date", "", "The date these comments were made");
    res.addRow("Version", "", "The FHIR Version for these comments");
    return res;
  }
}
