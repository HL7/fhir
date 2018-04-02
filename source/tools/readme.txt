FHIR Validation tool

The FHIR validation tool validates a FHIR resource or bundle.
Schema and schematron checking is performed, then some additional checks are performed

JSON is not supported at this time

Usage: java -jar org.hl7.fhir.validator.jar [source] (-defn [definitions]) (-output [output]) where: 
* [source] is a file name or url of the resource or bundle feed to validate
* [definitions] is the file name or url of the validation pack (validation.zip). 
   Default: get it from hl7.org/fhir (or variant - make sure you download the same version as the validator)
* [output] is a filename for the results (OperationOutcome). Default: results are sent to the std out.
* [profile] is an optional filename or URL for a specific profile to validate a resource
    against. In the absence of this parameter, the resource will be checked against the 
    base specification using the definitions.

Or, you can use the java class directly in the jar. Quick Doco:

Class org.hl7.fhir.r4.validation.ValidationEngine

The following resource formats are supported: XML, JSON, Turtle
The following versions are supported: 1.4.0, 1.6.0, and current

Note: the validation engine is intended to be threadsafe
To Use:
 
 / Initialize
   ValidationEngine validator = new ValidationEngine(src);
     - this must refer to the igpack.zip for the version of the spec against which you want to validate
      it can be a url or a file reference. It can nominate the igpack.zip directly, 
      or it can name the container alone (e.g. just the spec URL).
      The validation engine does not cache igpack.zip. the user must manage that if desired 

    validator.connectToTSServer(txServer);
      - this is optional; in the absence of a terminology service, snomed, loinc etc. will not be validated
      
    validator.loadIg(src);
      - call this any number of times for the Implementation Guide(s) of interest. This is a reference
        to the igpack.zip for the implementation guide - same rules as above
        the version of the IGPack must match that of the spec 
        Alternatively it can point to a local folder that contains conformance resources.
         
    validator.loadQuestionnaire(src)
      - url or filename of a questionnaire to load. Any loaded questionnaires will be used while validating
      
    validator.setNative(doNative);
      - whether to do xml/json/rdf schema validation as well

   You only need to do this initialization once. You can validate as many times as you like
   
 2. validate
    validator.validate(src, profiles);
      - source (as stream, byte[]), or url or filename of a resource to validate. 
        Also validate against any profiles (as canonical URLS, equivalent to listing them in Resource.meta.profile)
        
        if the source is provided as byte[] or stream, you need to provide a format too, though you can 
        leave that as null, and the validator will guess
   
License:
The validator itself is covered the license below. The java includes files with many other 
open source licenses. TODO: chase them down and put them in here....


 Copyright (c) 2011+, HL7, Inc
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
 list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 this list of conditions and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
 endorse or promote products derived from this software without specific 
 prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.
