package org.hl7.fhir.definitions.generators.specification;

import net.sf.saxon.regex.RegexIterator;
import org.apache.jena.datatypes.xsd.impl.XMLLiteralType;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.*;
import org.hl7.fhir.definitions.model.*;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.rdf.FHIRResource;
import org.hl7.fhir.rdf.FHIRResourceFactory;
import org.hl7.fhir.rdf.RDFNamespace;
import org.hl7.fhir.rdf.RDFTypeMap;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;


import java.io.OutputStream;
import java.util.*;

/**
 * Generator to create fhir "Ontology" -- a model of the various subjects, predicates and types in the FHIR spec
 */
public class W5TurtleGenerator {
    private OutputStream destination;
    private Definitions definitions;
    private BuildWorkerContext context;
    private List<ValidationMessage> issues;


    public W5TurtleGenerator(OutputStream destination, Definitions definitions, BuildWorkerContext context,
                             List<ValidationMessage> issues) {
        this.destination = destination;
        this.definitions = definitions;
        this.context = context;
        this.issues = issues;
    }

    /**
     * Only produce the v3 vocabulary for appending to rim.ttl
     * Placeholder - has no effect in this generation
     */
    public void executeV3(Map<String, ValueSet> valuesets, Map<String, CodeSystem> codeSystems) throws Exception {

    }

    public void executeMain() throws Exception {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.setNsPrefix(RDFNamespace.W5.getPrefix(), RDFNamespace.W5.getURI());
        model.setNsPrefix(RDFNamespace.FHIR.getPrefix(), RDFNamespace.FHIR.getURI());

        Ontology w5 = model.createOntology(RDFNamespace.W5.getURI());
        w5.addProperty(RDFS.label, "W5 Categorization");
        w5.addProperty(RDFS.comment, "FHIR W5 categorization is a preliminary classification of the fhir property");
        w5.addVersionInfo("FHIR W5 categorization (Preliminary)");
        w5.addProperty(OWL2.versionIRI, "http://build.fhir.org/w5.ttl");

        // The only way to differentiate predicates from classes is the existence of subclasses -- if something
        // has subclasses or is a subclass then it is a class.  Otherwise it is a predicate...
        Set<String> w5classes = new HashSet<String>();
        for (W5Entry e : definitions.getW5list()) {
            Set<String> escs = e.getSubClasses();
            if (!escs.isEmpty()) {
                w5classes.add(e.getCode());
                for (String s : e.getSubClasses())
                    w5classes.add(s);
            }
        }

        for (W5Entry e : definitions.getW5list()) {
            String es = e.getCode();
            if(w5classes.contains(es)) {
                OntClass ec = model.createClass(RDFNamespace.W5.uriFor(es));
                ec.addLabel(es, null);
                ec.addComment(e.getDescription(), null);
                for (String s : e.getSubClasses()) {
                    model.createClass(RDFNamespace.W5.uriFor(s))
                            .addSuperClass(ec);
                }
            } else {
                OntProperty ep = model.createObjectProperty(RDFNamespace.W5.uriFor(es));
                ep.addLabel(es, null);
                ep.addComment(e.getDescription(), null);
                String esroot = es;
                while(esroot.contains(".")) {
                    esroot = esroot.substring(0, esroot.lastIndexOf('.'));
                    ep.addSuperProperty(model.createProperty(RDFNamespace.W5.uriFor(esroot)));
                }
            }
        }

        commit(model, true);
    }

    public void commit(OntModel model, boolean header) throws Exception {
        RDFDataMgr.write(destination, model, RDFFormat.TURTLE_PRETTY);
        destination.flush();
        destination.close();
    }
}


