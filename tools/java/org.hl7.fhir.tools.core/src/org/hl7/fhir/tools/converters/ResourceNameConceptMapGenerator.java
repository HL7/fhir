package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.r5.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.r5.model.Enumerations.ConceptMapRelationship;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xls.XLSXmlParser;
import org.hl7.fhir.utilities.xls.XLSXmlParser.Sheet;

public class ResourceNameConceptMapGenerator {

  public static void main(String[] args) throws IOException, FHIRException {
    String srcFolder = args[0]; 
    String filename = Utilities.path(srcFolder, "resource-name-tracker.xml");
    Date date = new Date(new File(filename).lastModified());
    XLSXmlParser xls = new XLSXmlParser(new FileInputStream(filename), "resource-name-tracker.xml");
    String dstFolder = args[1]; 
    genMap("r2", "1.0", "r3", "3.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r2-r3", date);
    genMap("r2", "1.0", "r4", "4.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r2-r4", date);
    genMap("r3", "3.0", "r2", "1.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r3-r2", date);
    genMap("r3", "3.0", "r4", "4.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r3-r4", date);
    genMap("r4", "4.0", "r2", "1.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r4-r2", date);
    genMap("r4", "4.0", "r3", "3.0", xls.getSheets().get("Map"), dstFolder, "resource-names-r4-r3", date);
  }

  private static void genMap(String src, String srcV, String dst, String dstV, Sheet xls, String dstFolder, String name, Date date) throws FileNotFoundException, IOException {
    ConceptMap cm = new ConceptMap();
    cm.setId(name);
    cm.setUrl("http://hl7.org/fhir/"+name);
    cm.setName("ResourceNames"+src.toUpperCase()+"to"+dst.toUpperCase());
    cm.setTitle("Resource Names "+src.toUpperCase()+" to "+dst.toUpperCase());
    cm.setDescription("This map contains a mapping between resources from "+src+" to "+dst);
    cm.setStatus(PublicationStatus.ACTIVE);
    cm.setDate(date);
    ConceptMapGroupComponent grp = cm.addGroup();
    grp.setSource("http://hl7.org/fhir/"+srcV+"/resource-types");
    grp.setTarget("http://hl7.org/fhir/"+dstV+"/resource-types");
    for (int row = 0; row < xls.rows.size(); row++) {
      String s = xls.getColumn(row, src);
      String t = xls.getColumn(row, dst);
      if (!Utilities.noString(s) && (Character.isAlphabetic(s.charAt(0)) || s.startsWith("->") || s.startsWith("(") )) {
        String c = s.startsWith("->") ? s.substring(2).trim() : s.startsWith("(") ? s.substring(1, s.length()-1) : s;
        SourceElementComponent map = elementForCode(grp, c);
        if (Utilities.noString(t))
          map.addTarget().setRelationship(ConceptMapRelationship.NOTRELATEDTO);
        else if (t.startsWith("("))
          map.addTarget().setCode(t.substring(1, t.length()-1)).setRelationship(ConceptMapRelationship.RELATEDTO);
        else if (t.startsWith("->"))
          map.addTarget().setCode(t.substring(2).trim()).setRelationship(ConceptMapRelationship.BROADER);
        else if (t.startsWith(":"))
          map.addTarget().setComment(t.substring(1).trim()).setRelationship(ConceptMapRelationship.NOTRELATEDTO);
        else if (s.startsWith("->"))
          map.addTarget().setCode(t).setRelationship(ConceptMapRelationship.NARROWER);
        else if (s.startsWith("("))
          map.addTarget().setCode(t).setRelationship(ConceptMapRelationship.RELATEDTO);
        else
          map.addTarget().setCode(t).setRelationship(ConceptMapRelationship.EQUIVALENT);
      }
    }
    //
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dstFolder, "ConceptMap-"+name+".json")), cm);
    
  }

  private static SourceElementComponent elementForCode(ConceptMapGroupComponent grp, String s) {
    for (SourceElementComponent t : grp.getElement())
      if (t.getCode().equals(s))
        return t;
    SourceElementComponent t = grp.addElement();
    t.setCode(s);
    return t;
  }

}
