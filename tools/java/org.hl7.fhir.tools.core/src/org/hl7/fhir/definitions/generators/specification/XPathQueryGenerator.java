package org.hl7.fhir.definitions.generators.specification;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.publisher.QaTracker;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;


public class XPathQueryGenerator {

  private Definitions definitions;
  private Logger log;
  private QaTracker qa;

  public XPathQueryGenerator(Definitions definitions, Logger log, QaTracker qa) {
    super();
    this.definitions = definitions;
    this.log = log;
    this.qa = qa;
  }

  public Definitions getDefinitions() {
    return definitions;
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;
  }

  public String generateXpath(List<String> list) throws Exception {
    StringBuilder b = new StringBuilder();
    for (String ppath : list) {
      String path[] = ppath.split("\\.");
      if (path[path.length -1].endsWith("[x]")) {
        ElementDefn defn = definitions.getElementDefn(path[0]);
        ElementDefn ed = defn.getElementForPath(ppath, definitions, "Search parameter xpath generation", true);
        for (TypeRef tr : ed.getTypes()) {
          buildPath(b, path, ed.getName().substring(0, ed.getName().length()-3)+Utilities.capitalize(tr.getName()));          
        }
      } else 
        buildPath(b, path, path[path.length-1]);
      return b.toString();
    }
    return null;
  }

  private void buildPath(StringBuilder b, String[] path, String last) {
    if (b.length() > 0)
      b.append(" | ");
    for (int i = 0; i < path.length-1; i++) {
      if (i > 0)
        b.append("/");
      b.append("f:");
      b.append(processCondition(path[i]));
    }
    b.append("/");
    b.append("f:");
    b.append(processCondition(last));
  }

  private Object processCondition(String s) {
    if (!s.contains("("))
      return s;
    String cond = s.substring(s.indexOf("(")+1);
    cond = cond.substring(0, cond.indexOf(")"));
    s = s.substring(0, s.indexOf("("));
    if (Utilities.isInteger(cond))
      return s+"["+cond+"]";
    else {
      String[] parts = cond.split("=");
      return s+"["+parts[0]+"/@value='"+parts[1]+"']";
      
    }
  }

}
