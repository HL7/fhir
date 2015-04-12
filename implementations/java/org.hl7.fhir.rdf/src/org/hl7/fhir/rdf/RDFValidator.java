package org.hl7.fhir.rdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.hl7.fhir.utilities.Utilities;

import arq.sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * The purpose of this class is twofold:
 * - validate the the turtle syntax is correct
 * - check the semantics
 * 
 * @author Grahame
 *n
 */
public class RDFValidator {
  private static final String prefixes =
      "PREFIX dc: <http://purl.org/dc/elements/1.1/> \r\n"+
      "PREFIX dcterms: <http://purl.org/dc/terms/> \r\n"+
      "PREFIX owl: <http://www.w3.org/2002/07/owl#> \r\n"+
      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"+
      "PREFIX rdfs: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"+
      "PREFIX rim: <http://hl7.org/orim/class/> \r\n"+
      "PREFIX dt: <http://hl7.org/orim/datatype/> \r\n"+
      "PREFIX vs: <http://hl7.org/orim/valueset/> \r\n"+
      "PREFIX cs: <http://hl7.org/orim/codesystem/> \r\n"+
      "PREFIX xs: <http://www.w3.org/2001/XMLSchema/> \r\n"+
      "PREFIX fhir: <http://hl7.org/fhir/> \r\n"+
      "PREFIX os: <http://open-services.net/ns/core#> \r\n";

  Model model;
  
  public void validate(String filename) throws Exception {
    Model m = RDFDataMgr.loadModel(filename);
    System.out.println(Integer.toString(m.getGraph().size())+" triples in RDF file "+filename);
    model = model == null ? m : model.union(m);
    FileOutputStream strm = new FileOutputStream(Utilities.changeFileExt(filename, ".rdf.xml"));
    try {
      RDFDataMgr.write(strm, m, RDFFormat.RDFXML_PLAIN);
    } finally {
      strm.close();
    }
  }
  
  public String assertion(String sparql) {
    Query query = QueryFactory.create(prefixes+sparql);

    // Execute the query and obtain results
    QueryExecution qe = QueryExecutionFactory.create(query, model);
    ResultSet results = qe.execSelect();
    String rows = null;
    
    if (results.hasNext()) { 
      // Output query results 
      ByteArrayOutputStream ba = new ByteArrayOutputStream();
      ResultSetFormatter.out(ba, results, query);
      rows = new String(ba.toByteArray());
    }
    
    // Important - free up resources used running the query
    qe.close();
    return rows;
  }
}
