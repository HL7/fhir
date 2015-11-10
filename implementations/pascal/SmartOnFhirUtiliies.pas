unit SmartOnFhirUtiliies;


{
Copyright (c) 2001-2013, Health Intersections Pty Ltd (http://www.healthintersections.com.au)
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

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 'AS IS' AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
}

interface

uses
  SysUtils,
  FHIRResources, FHIRTypes, FHIRUtilities;

type
  TRegisteredServer = record
    name : String;
    SmartOnFHIR: boolean;
    fhirEndpoint : String;
    tokenEndpoint : String;
    authorizeEndpoint : String;
    clientid : String;
    clientsecret : String; // this is for testing purposes; the notepad++ plug-in is not a confidential app
    redirectport : integer;
  end;

function usesSmartOnFHIR(conf : TFhirConformance; var authorize, token: String): Boolean;

implementation

function usesSmartOnFHIR(conf : TFhirConformance; var authorize, token: String): Boolean;
var
  cc : TFhirCodeableConcept;
  ex1, ex2 : TFhirExtension;
begin
  result := false;
  authorize := '';
  token := '';
  if conf.restList.Count <> 1 then
    raise Exception.Create('Unable to find rest entry in conformance statement');
  if (conf.restList[0].security <> nil) then
  begin
    for cc in conf.restList[0].security.serviceList do
      if cc.hasCode('http://hl7.org/fhir/restful-security-service', 'SMART-on-FHIR') or cc.hasCode('http://hl7.org/fhir/restful-security-service', 'OAuth2') or
         cc.hasCode('http://hl7.org/fhir/vs/restful-security-service', 'SMART-on-FHIR') or cc.hasCode('http://hl7.org/fhir/vs/restful-security-service', 'OAuth2') then
      begin
        for ex1 in conf.restList[0].security.extensionList do
          if ex1.url = 'http://fhir-registry.smarthealthit.org/StructureDefinition/oauth-uris' then
            for ex2 in ex1.extensionList do
              if ex2.url = 'authorize' then
                authorize := TFHIRUri(ex2.value).value
              else if ex2.url = 'token' then
                token := TFHIRUri(ex2.value).value;

      end;
  end;
  result := (token <> '') and (authorize <> '');
end;


end.
