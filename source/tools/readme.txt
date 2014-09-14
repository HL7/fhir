FHIR Validation tool

The FHIR validation tool validates a FHIR resource or bundle.
Schema and schematron checking is performed, then some additional checks are performed

JSON is not supported at this time

Usage: FHIRValidator.jar [source] (-defn [definitions]) (-output [output]) where: 
* [source] is a file name or url of the resource or bundle feed to validate
* [definitions] is the file name or url of the validation pack (validation.zip). Default: get it from hl7.org
* [output] is a filename for the results (OperationOutcome). Default: results are sent to the std out.
* [profile] is an optional filename or URL for a specific profile to validate a resource
    against. In the absence of this parameter, the resource will be checked against the 
    base specification using the definitions.

Or, you can use the java class directly in the jar. Quick Doco:

Class org.hl7.fhir.tools.validator.Validator
methods:
  void setSource(string) - see above
  void setDefinitions(string) - see above
  void process(); - actually perform the validation (may throw Exception)
  String getOutcome(); - the outcome as an OperationOutcome resource represented as a string
  
  
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
