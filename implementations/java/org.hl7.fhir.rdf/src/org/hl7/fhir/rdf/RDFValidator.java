package org.hl7.fhir.rdf;

import java.io.FileOutputStream;

import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.hl7.fhir.utilities.Utilities;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * The purpose of this class is twofold:
 * - validate the the turtle syntax is correct
 * - check the semantics
 * 
 * @author Grahame
 *
 */
public class RDFValidator {

  public void validate(String filename) throws Exception {
    Model model = RDFDataMgr.loadModel(filename) ;
    System.out.println(Integer.toString(model.getGraph().size())+" triples in RDF file "+filename);
    FileOutputStream strm = new FileOutputStream(Utilities.changeFileExt(filename, ".rdf.xml"));
    try {
      RDFDataMgr.write(strm, model, RDFFormat.RDFXML_PLAIN);
    } finally {
      strm.close();
    }
  }
}
