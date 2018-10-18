package org.hl7.fhir.definitions.generators.specification;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.rdf.FHIRResource;
import org.hl7.fhir.rdf.FHIRResourceFactory;
import org.hl7.fhir.rdf.RDFNamespace;
import org.hl7.fhir.rdf.RDFTypeMap;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;

/**
 * Generator to create fhir "Ontology" -- a model of the various subjects, predicates and types in the FHIR spec
 */
public class FhirTurtleGenerator {
    private OutputStream destination;
    private Definitions definitions;
    private BuildWorkerContext context;
    private List<ValidationMessage> issues;
    private FHIRResourceFactory fact;
    private Resource value;

    // OWL doesn't recognize xsd:gYear, xsd:gYearMonth or xsd:date.  If true, map all three to xsd:datetime
    private boolean owlTarget = true;


    public FhirTurtleGenerator(OutputStream destination, Definitions definitions, BuildWorkerContext context,
                               List<ValidationMessage> issues) {
        this.destination = destination;
        this.definitions = definitions;
        this.context = context;
        this.issues = issues;
        this.fact = new FHIRResourceFactory();
        this.value = fact.fhir_resource("value", OWL2.DatatypeProperty, "fhir:value")
                .addTitle("Terminal data value")
                .resource;
    }

    /**
     * Only produce the v3 vocabulary for appending to rim.ttl
     * Placeholder for now - has no effect in this generation
     */
    public void executeV3(Map<String, ValueSet> valuesets, Map<String, CodeSystem> codeSystems) throws Exception {
//        for (String csName : codeSystems.keySet()) {
//            CodeSystem cs = codeSystems.get(csName);
//            if (cs == null) {
//                System.out.println("-----> " + csName);
//            } else {
//                new OWLCodeSystem(cs).commit(cs, destDir);
//            }
//        }
    }

    public void executeMain() throws Exception {
        genOntologyDefinition();
        genBaseMetadata();

        for (String pn : sorted(definitions.getPrimitives().keySet())) {
            if(isPrimitive(pn))
                genPrimitiveType(definitions.getPrimitives().get(pn));
        }

        for (String infn : sorted(definitions.getInfrastructure().keySet()))
            genElementDefn(definitions.getInfrastructure().get(infn));

        for (String n : sorted(definitions.getTypes().keySet()))
            genElementDefn(definitions.getTypes().get(n));

        for (String n : sorted(definitions.getStructures().keySet()))
            genElementDefn(definitions.getStructures().get(n));

        for (String n : sorted(definitions.getConstraints().keySet())) {
            genProfiledType(definitions.getConstraints().get(n));
        }

        for (String n : sorted(definitions.getBaseResources().keySet()))
            genResourceDefn(definitions.getBaseResources().get(n));

        for (String n : sorted(definitions.getResources().keySet()))
            genResourceDefn(definitions.getResources().get(n));

//        for (StructureDefinition sd : context.getExtensionDefinitions()) {
//             System.out.println("=====> " + sd.getName());
//             genStructure(context.getExtensionDefinitions().get(n));
//        }

        commit(true);
    }

    /**
     * Emit an ontology definition for the file
     */
    private void genOntologyDefinition() {
        fact.fhir_ontology("fhir.ttl", "FHIR Model Ontology")
                .addDataProperty(RDFS.comment, "Formal model of FHIR Clinical Resources")
                .addObjectProperty(OWL2.versionIRI, ResourceFactory.createResource("http://build.fhir.org/fhir.ttl"))
                .addObjectProperty(OWL2.imports, ResourceFactory.createResource("http://hl7.org/fhir/w5.ttl"));
    }

    /**
     * Emit all the basic atoms that are implicit in the actual model
     */
    private void genBaseMetadata() {
        // Declare these for now - they will get filled in more completely later on
        FHIRResource Resource = fact.fhir_class("Resource");
        FHIRResource Element = fact.fhir_class("Element");
        FHIRResource Reference = fact.fhir_class("Reference");

        // Primitive isn't in the actual model - added here
        fact.fhir_class("Primitive")
                .addTitle("Types with only a value")
                .addDefinition("Types with only a value and no additional elements as children")
                .restriction(fact.fhir_restriction(value, RDFS.Literal));

        // A resource can have an optional nodeRole
        FHIRResource treeRoot = fact.fhir_class("treeRoot")
                .addTitle("Class of FHIR base documents");
        FHIRResource nodeRole = fact.fhir_objectProperty("nodeRole")
                .addTitle("Identifies role of subject in context of a given document")
                .domain(Resource)
                .range(treeRoot.resource);
        Resource.restriction(fact.fhir_cardinality_restriction(nodeRole.resource, treeRoot.resource, 0, 1));


        // Any element can have an index to assign order in a list
        FHIRResource index = fact.fhir_dataProperty("index")
                .addTitle("Ordering value for list")
                .domain(Element)
                .range(XSD.nonNegativeInteger);
        Element.restriction(fact.fhir_cardinality_restriction(index.resource, XSD.nonNegativeInteger, 0, 1));

        // References have an optional link
        FHIRResource link = fact.fhir_objectProperty("link").addTitle("URI of a reference");
        Reference.restriction(fact.fhir_cardinality_restriction(link.resource, Resource.resource, 0, 1));

        // XHTML is an XML Literal -- but it isn't recognized by OWL so we use string
        FHIRResource NarrativeDiv = fact.fhir_dataProperty("Narrative.div");
        fact.fhir_class("xhtml", "Primitive")
            .restriction(fact.fhir_cardinality_restriction(value, fact.fhir_datatype(XSD.xstring).resource, 1, 1));
    }

  /* ==============================================
     Generators for various FHIR types
   * ============================================== */

    /**
     * PrimitiveType Generator
     *
     * @param pt FHIR Primitive Type (e.g. int, string, dateTime)
     */
    // Note: For unknown reasons, getPrimitives returns DefinedCodes, not PrimitiveTypes...
    private void genPrimitiveType(DefinedCode pt) {
        String ptName = pt.getCode();
        FHIRResource ptRes = fact.fhir_class(ptName, "Primitive")
                .addDefinition(pt.getDefinition());
        Resource rdfType = RDFTypeMap.xsd_type_for(ptName, owlTarget);
        if (rdfType != null)
            ptRes.restriction(fact.fhir_cardinality_restriction(value, fact.fhir_datatype(rdfType).resource, 1, 1));
    }


    /**
     * DefinedStringPattern Generator
     *
     * @param dsp FHIR DefinedStringPattern Type (e.g. id, oid, uuid)
     * @throws Exception
     */
    private void genDefinedStringPattern(DefinedStringPattern dsp) throws Exception {
        String dspType = dsp.getSchema();
        String dspTypeName = dspType.endsWith("+")? dspType.substring(0, dspType.length() - 1) : dspType;
        Resource dspTypeRes = RDFTypeMap.xsd_type_for(dspTypeName, owlTarget);

        FHIRResource dspRes = fact.fhir_class(dsp.getCode(), dsp.getBase())
                .addDefinition(dsp.getDefinition());

        if(dspRes != null) {
            if (dspType.endsWith("+")) {
                List<Resource> facets = new ArrayList<Resource>(1);
                facets.add(fact.fhir_pattern(dsp.getRegex()));
                dspRes.restriction(fact.fhir_restriction(value,
                        fact.fhir_datatype_restriction(dspTypeRes == XSD.xstring ? XSD.normalizedString : dspTypeRes, facets)));
            } else
                dspRes.restriction(fact.fhir_restriction(value, dspTypeRes));
        }
    }



    /**
     * TypeDefinition generator (e.g. code, id, markdown, uuid)
     *
     * @param td definition to generate
     * @throws Exception
     */
    private void genElementDefn(TypeDefn td) throws Exception {
        String typeName = td.getName();
        StructureDefinition typeSd = td.getProfile();
        String parentURL = typeSd.getBaseDefinitionElement().getValue();
        String parentName = null;
        // TODO: Figure out how to do this properly
        if (parentURL != null)
            parentName = parentURL.substring(parentURL.lastIndexOf("/")+1);
        FHIRResource typeRes =
                (td.getTypes().isEmpty() ? fact.fhir_class(typeName) : fact.fhir_class(typeName, parentName))
                        .addTitle(td.getShortDefn())
                        .addDefinition(td.getDefinition());
        processTypes(typeName, typeRes, td, typeName, false);
    }

    /**
     * ProfiledType generator
     */
    private void genProfiledType(ProfiledType pt) throws Exception {
        fact.fhir_class(pt.getName(), pt.getBaseType()).addTitle(pt.getDefinition()).addDefinition(pt.getDescription());
        if (!Utilities.noString(pt.getInvariant().getTurtle())) {
            Model model = ModelFactory.createDefaultModel();
            model.read(pt.getInvariant().getTurtle());
            fact.merge_rdf(model);
        }
    }


    /**
     * Resource Definition generator
     *
     * @param rd Resource Definition to emit
     * @throws Exception
     */
    private void genResourceDefn(ResourceDefn rd) throws Exception {
        String resourceName = rd.getName();
        ElementDefn resourceType = rd.getRoot();
        FHIRResource rdRes =
                fact.fhir_class(resourceName, resourceType.getTypes().isEmpty()? OWL2.Thing : RDFNamespace.FHIR.resourceRef(resourceType.typeCode()))
                        .addDefinition(rd.getDefinition());
        processTypes(resourceName, rdRes, resourceType, resourceName, true);
        if(!Utilities.noString(resourceType.getW5()))
            rdRes.addObjectProperty(RDFS.subClassOf, RDFNamespace.W5.resourceRef(resourceType.getW5()));
    }

    /**
     * Iterate over the Element Definitions in baseResource generating restrictions and properties
     * @param baseResourceName Name of base resource
     * @param baseResource FHIRResource for base resource
     * @param td Inner type definitions
     * @param predicateBase Root name for predicate
     * @param innerIsBackbone True if we're processing a backbone element
     */
    HashSet<String> processing = new HashSet<String>();
    private void processTypes(String baseResourceName, FHIRResource baseResource, ElementDefn td, String predicateBase, boolean innerIsBackbone) throws Exception {

        for (ElementDefn ed : td.getElements()) {
            String predicateName = predicateBase + "." + (ed.getName().endsWith("[x]")?
                            ed.getName().substring(0, ed.getName().length() - 3) : ed.getName());
            FHIRResource predicateResource;

            if (ed.getName().endsWith("[x]")) {
                predicateResource = fact.fhir_objectProperty(predicateName);

                // Choice entry
                if (ed.typeCode().equals("*")) {
                    // Wild card -- any element works (probably should be more restrictive but...)
                    Resource targetResource = RDFNamespace.FHIR.resourceRef("Element");
                    baseResource.restriction(
                            fact.fhir_cardinality_restriction(
                                    predicateResource.resource,
                                    targetResource,
                                    ed.getMinCardinality(),
                                    ed.getMaxCardinality()));
                    predicateResource.domain(baseResource);
                    predicateResource.range(targetResource);
                } else {
                    // Create a restriction on the union of possible types
                    List<Resource> typeOpts = new ArrayList<Resource>();
                    for (TypeRef tr : ed.getTypes()) {
                        // TODO: Figure out how to get the type reference code
                        String trName = tr.getName();
                        if(trName.equals("SimpleQuantity"))
                            trName = "Quantity";
                        String qualifiedPredicateName = predicateName + Utilities.capitalize(trName);
                        Resource targetRes = fact.fhir_class(tr.getName()).resource;
                        FHIRResource qualifiedPredicate = fact.fhir_objectProperty(qualifiedPredicateName, predicateResource.resource)
                                .domain(baseResource)
                                .range(targetRes);
                        typeOpts.add(
                                fact.fhir_cardinality_restriction(qualifiedPredicate.resource,
                                                                  targetRes,
                                                                  ed.getMinCardinality(),
                                                                  ed.getMaxCardinality()));
                    }
                    baseResource.restriction(fact.fhir_union(typeOpts));
                }
            } else {
                FHIRResource baseDef;
                if(ed.getTypes().isEmpty()) {
                    predicateResource = fact.fhir_objectProperty(predicateName);
                    String targetClassName = mapComponentName(baseResourceName, ed.getDeclaredTypeName());
                    baseDef = fact.fhir_class(targetClassName, innerIsBackbone? "BackboneElement": "Element")
                            .addDefinition(ed.getDefinition());
                    processTypes(targetClassName, baseDef, ed, predicateName, innerIsBackbone);
                } else {
                    TypeRef targetType = ed.getTypes().get(0);
                    String targetName = targetType.getName();
                    if(targetName.startsWith("@")) {        // Link to earlier definition
                        ElementDefn targetRef = getElementForPath(targetName.substring(1));
                        String targetRefName = targetRef.getName();
                        String targetClassName = baseResourceName +
                                Character.toUpperCase(targetRefName.charAt(0)) + targetRefName.substring(1);
                        baseDef = fact.fhir_class(targetClassName, innerIsBackbone? "BackboneElement": "Element")
                                .addDefinition(ed.getDefinition())
                                .addTitle(ed.getShortDefn());
                        if(!processing.contains(targetRefName)) {
                            processing.add(targetRefName);
                            processTypes(targetClassName, baseDef, targetRef, predicateName, innerIsBackbone);
                            processing.remove(targetRefName);
                        }
                    } else {
                        // A placeholder entry.  The rest of the information will be supplied elsewhere
                        baseDef = fact.fhir_class(targetName);
                    }
                    // XHTML the exception, in that the html doesn't derive from Primitive
                    if (targetName.equals("xhtml"))
                        predicateResource = fact.fhir_dataProperty(predicateName);
                    else
                        predicateResource = fact.fhir_objectProperty(predicateName);
                }
                predicateResource.addTitle(ed.getShortDefn())
                        .addDefinition(ed.getDefinition())
                        .domain(baseResource);
                baseResource.restriction(
                        fact.fhir_cardinality_restriction(predicateResource.resource, baseDef.resource, ed.getMinCardinality(), ed.getMaxCardinality()));
                predicateResource.range(baseDef.resource);
                if(!Utilities.noString(ed.getW5()))
                    predicateResource.addObjectProperty(RDFS.subPropertyOf, RDFNamespace.W5.resourceRef(ed.getW5()));
            }
        }
    }

    private String mapComponentName(String baseResourceName, String componentName) {
        return componentName.startsWith(baseResourceName)? componentName : baseResourceName + "." + componentName;
    }

    private ElementDefn getElementForPath(String pathname) throws Exception {
        String[] path = pathname.split("\\.");
        ElementDefn res = definitions.getElementDefn(path[0]);
        for (int i = 1; i < path.length; i++)
        {
            String en = path[i];
            if (en.length() == 0)
                throw new Exception("Improper path "+pathname);
            ElementDefn t = res.getElementByName(definitions, en, true, false, null);
            if (t == null) {
                throw new Exception("unable to resolve "+pathname);
            }
            res = t;
        }
        return res;
    }


    public void commit(boolean header) throws Exception {

        fact.serialize(destination);
        destination.flush();
        destination.close();
   }

    protected List<String> sorted(Set<String> keys) {
        List<String> names = new ArrayList<String>();
        names.addAll(keys);
        Collections.sort(names);
        return names;
    }

    protected boolean isPrimitive(String name) {
        return definitions.hasPrimitiveType(name)
                || (name.endsWith("Type")
                && definitions.getPrimitives().containsKey(name.substring(0, name.length()-4)));
    }
}
