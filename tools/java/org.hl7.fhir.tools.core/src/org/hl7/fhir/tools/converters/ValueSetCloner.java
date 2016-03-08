package org.hl7.fhir.tools.converters;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValueSetCloner {
  
  public static void main(String[] args) throws Exception {  
    Document src = XMLUtil.parseFileToDom("C:\\work\\org.hl7.fhir\\build\\guides\\sdc\\questionnaire-sdc-profile-example.xml");
    List<Element> entries = new ArrayList<Element>();
    XMLUtil.getNamedChildren(src.getDocumentElement(), "entry", entries); 
    for (Element entry : entries) {
      Element res = XMLUtil.getFirstChild(XMLUtil.getNamedChild(entry, "resource"));
      List<Element> contained = new ArrayList<Element>();
      XMLUtil.getNamedChildren(res, "contained", contained);
      for (Element c : contained) {
        Element r = XMLUtil.getFirstChild(c);
        Element cs = XMLUtil.getNamedChild(r, "codeSystem");
        if (r.getNodeName().equals("ValueSet") && cs != null) {
          Element nc = src.createElement("contained");
          res.insertBefore(nc, c);
          Element nr = src.createElement("CodeSystem");
          nc.appendChild(nr);
          Element id = src.createElement("id");
          id.setAttribute("value", XMLUtil.getNamedChildValue(r, "id").replace("-vs", "-cs"));
          nr.appendChild(id);
          Element url = src.createElement("url");
          String uri = XMLUtil.getNamedChildValue(cs, "system");
          if (uri == null)
            throw new Error("unided cs");
          url.setAttribute("value", uri);
          nr.appendChild(url);
          Element status = src.createElement("status");
          status.setAttribute("value", XMLUtil.getNamedChildValue(r, "status"));
          nr.appendChild(status);
          Element content = src.createElement("content");
          content.setAttribute("value", "complete");
          nr.appendChild(content);

          List<Element> concepts = new ArrayList<Element>();
          XMLUtil.getNamedChildren(cs, "concept", concepts);
          for (Element concept : concepts) {
            cs.removeChild(concept);
            nr.appendChild(concept);
          }
          r.removeChild(cs);
          Element comp = src.createElement("compose");
          r.appendChild(comp);
          Element inc = src.createElement("include");
          comp.appendChild(inc);
          Element system = src.createElement("system");
          inc.appendChild(system);
          system.setAttribute("value", uri);
        }
      }
    }
    XMLUtil.writeDomToFile(src, "C:\\work\\org.hl7.fhir\\build\\guides\\sdc\\questionnaire-sdc-profile-example-out.xml");
  }

}
