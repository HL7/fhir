package org.hl7.fhir.rdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.jena.riot.RDFDataMgr;

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
    System.out.println(Integer.toString(model.getGraph().size())+" triples in RDF file");
  }
}
