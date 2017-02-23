package org.hl7.fhir.rdf;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.XSD;

import java.util.HashMap;
import java.util.Map;


public class RDFTypeMap {
    /**
     * FHIR to XSD data type map.
     */
    // TODO: Figure out how to get this from the API
    static public final Map<String, Resource> ptMap = new HashMap<String, Resource>();


    // TODO: Find official OWL representation for gYear, dateTime and the like
    static {
        ptMap.put("base64Binary", XSD.base64Binary);
        ptMap.put("boolean", XSD.xboolean);
        ptMap.put("date", XSD.date);
        ptMap.put("dateTime", XSD.dateTime);
        ptMap.put("gYear", XSD.gYear);
        ptMap.put("gYearMonth", XSD.gYearMonth);
        ptMap.put("decimal", XSD.decimal);
        ptMap.put("instant", XSD.dateTime);
        ptMap.put("int", XSD.integer);
        ptMap.put("string", XSD.xstring);
        ptMap.put("time", XSD.time);
        ptMap.put("uri", XSD.anyURI);
        ptMap.put("anyURI", XSD.anyURI);
        ptMap.put("token", RDFNamespace.FHIR.resourceRef("token"));
        ptMap.put("nonNegativeInteger", XSD.nonNegativeInteger);
        ptMap.put("positiveInteger", XSD.positiveInteger);
    }

    public static Resource xsd_type_for(String type) {
        // TODO: find why namespaces are part of some of these types...
        String key = type.startsWith("xs:")? type.substring(3) : type;
        return ptMap.containsKey(key) ? ptMap.get(key) : RDFNamespace.FHIR.resourceRef(key);
    }
}
