package org.hl7.fhir.rdf;
//
//import org.apache.jena.riot.RDFDataMgr;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.logging.Logger;
//
//import scala.Option;
//import scala.Tuple2;
//
//import com.hp.hpl.jena.rdf.model.Model;

//import es.weso.rdf.PrefixMap;
//import es.weso.rdf.RDFReader;
//import es.weso.rdf.jena.RDFAsJenaModel;
//import es.weso.shex.Schema;
//import es.weso.shex.ShExMatcher;
//import es.weso.shex.ShExResult;

public class ShExValidator {

//  Logger log = Logger.getLogger(ShExValidator.class.getName());

//  public void validate(Model dataModel, Schema schema, PrefixMap pm) throws Exception {
//    RDFReader rdf = new RDFAsJenaModel(dataModel);
//    ShExMatcher matcher = new ShExMatcher(schema,rdf);
//    ShExResult result = matcher.validate();
//    if (result.isValid()) {
//      log.info("Result is valid");
//      System.out.println("Valid. Result: " + result.show(1,pm));
//    } else {
//      System.out.println("Not valid");
//    }
//  } 

  public void validate(String dataFile, String schemaFile, String schemaFormat) throws Exception {
//    log.info("Reading data file " + dataFile);
//    Model dataModel =  RDFDataMgr.loadModel(dataFile);
//    log.info("Model read. Size = " + dataModel.size());
//
//
//    log.info("Reading shapes file " + schemaFile);
//    Tuple2<Schema,PrefixMap> pair = readSchema(schemaFile,schemaFormat);
//
//    Schema schema = pair._1();
//    PrefixMap pm = pair._2();
//
//    log.info("Schema read" + schema.show());
//
//    validate(dataModel,schema,pm);
  }

//  public Tuple2<Schema,PrefixMap> readSchema(String schemaFile, String format) throws Exception {
//    // Create a none, see: http://stackoverflow.com/questions/1997433/how-to-use-scala-none-from-java-code
//    Option<String> none = Option.apply(null); // Create a none
//
//    String contents = new String(Files.readAllBytes(Paths.get(schemaFile)));
//
//    return Schema.fromString(contents,format,none).get();
//  }
//
}
