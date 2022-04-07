FHIR Validation tool 

The FHIR validation tool validates a FHIR resource or bundle.
The validation tool compares a resource against the base definitions and any
profiles declared in the resource (Resource.meta.profile) or specified on the 
command line

The FHIR validation tool validates a FHIR resource or bundle.
Schema and schematron checking is performed, then some additional checks are performed. 
* XML & Json (FHIR versions 1.0, 1.4, 3.0, 3.4)
* Turtle (FHIR versions 3.0, 3.4)

If requested, instances will also be verified against the appropriate schema
W3C XML Schema, JSON schema or ShEx, as appropriate

Usage: org.hl7.fhir.r4.validation.ValidationEngine (parameters)

The following parameters are supported:
[source]: a file, url, directory or pattern for resources to validate.  At
    least one source must be declared.  If there is more than one source or if
    the source is other than a single file or url and the output parameter is
    used, results will be provided as a Bundle.
    Patterns are limited to a directory followed by a filename with an embedded
    asterisk.  E.g. foo*-examples.xml or someresource.*, etc.
-defn [package|file|url]: where to find the FHIR specification igpack.zip
      default value is hl7.fhir.core-4.3.0-cibuild. This parameter can only appear once
-ig [package|file|url]: an IG or profile definition to load. Can be 
     the URL of an implementation guide or a package ([id]-[ver]) for
     a built implementation guide or a local folder that contains a
     set of conformance resources.
     No default value. This parameter can appear any number of times
-tx [url]: the [base] url of a FHIR terminology service
     Default value is http://tx.fhir.org/r4. This parameter can appear once
     To run without terminology value, specific n/a as the URL
-profile [url]: the canonical URL to validate against (same as if it was 
     specified in Resource.meta.profile). If no profile is specified, the 
     resource is validated against the base specification. This parameter 
     can appear any number of times.
     Note: the profile (and it's dependencies) have to be made available 
     through one of the -ig parameters. Note that package dependencies will 
     automatically be resolved
-questionnaire [file|url}: the location of a questionnaire. If provided, then the validator will validate
     any QuestionnaireResponse that claims to match the Questionnaire against it
     no default value. This parameter can appear any number of times
-output [file]: a filename for the results (OperationOutcome)
     Default: results are sent to the std out.
-native: use schema for validation as well
     * XML: w3c schema+schematron
     * JSON: json.schema
     * RDF: SHEX
     Default: false

Parameters can appear in any order

Alternatively, you can use the validator to execute a transformation as described by a structure map.
To do this, you must provide some additional parameters:

 -transform [map]

* [map] the URI of the map that the transform starts with

Any other dependency maps have to be loaded through an -ig reference 

-transform uses the parameters -defn, -txserver, -ig (at least one with the map files), and -output

Alternatively, you can use the validator to generate narrative for a resource.
To do this, you must provide a specific parameter:

 -narrative

-narrative requires the parameters -defn, -txserver, -source, and -output. ig and profile may be used

Finally, you can use the validator to generate a snapshot for a profile.
To do this, you must provide a specific parameter:

 -snapshot

-snapshot requires the parameters -defn, -txserver, -source, and -output. ig may be used to provide necessary base profiles
