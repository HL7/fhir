Skip to content
Search or jump to…

Pull requests
Issues
Marketplace
Explore
 
@grahamegrieve 
HL7
/
fhir
49
148
80
 Code Pull requests 12 Actions Projects 0 Wiki Security Insights Settings
fhir/source/library/library-cms146-example.xml
@brynrhodes brynrhodes Bump build
d6bdadc 5 hours ago
@brynrhodes@melvapeters
607 lines (598 sloc)  68.9 KB
  
<?xml version="1.0" encoding="UTF-8"?>
<Library xmlns="http://hl7.org/fhir" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://hl7.org/fhir ../../schema/library.xsd">
  <id value="library-cms146-example"/>
  <text>
    <status value="generated" />
    <div xmlns="http://www.w3.org/1999/xhtml">
         <table class="grid dict">
            <tr>
               <td>
                  <b>Id: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">Library/library-cms146-example</td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Identifier: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <b>value: </b>
                  <span>CMS146</span>
               </td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Version: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">2.0.0</td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Title: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">Appropriate Testing for Children with Pharyngitis</td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Type: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-right: 25px;">
                  <span>
                     <span>
                        <span style="padding-left: 25px;">
                           <b>code: </b>
                           <span>logic-library</span>
                        </span>
                     </span>
                  </span>
               </td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Status: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">draft</td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Description: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">Logic for CMS 146: Appropriate Testing for Children with Pharyngitis</td>
            </tr>
         </table>
         <p></p>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Related: </b>
               </td>
            </tr>
            <tr style="vertical-align: top;">
               <td style="padding-left: 25px; padding-right: 25px;">
                  <p style="margin-bottom: 5px;">
                     <b>type: </b>
                     <span>depends-on</span>
                  </p>
                  <p style="margin-bottom: 5px;">
                     <b>Resource: </b>
                     <br/>
                     <span>
                        <span style="padding-left: 25px;">
                           <b>reference: </b>
                           <span>Library/library-quick-model-definition</span>
                        </span>
                     </span>
                  </p>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>Patient</span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>Condition</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>category</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>diagnosis</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>clinicalStatus</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>confirmed</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>code</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.102.12.1011</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>Condition</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>category</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>diagnosis</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>clinicalStatus</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>confirmed</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>code</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.102.12.1012</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>Encounter</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>status</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>finished</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>class</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>ambulatory</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>type</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.101.12.1061</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>DiagnosticReport</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>diagnosis</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.198.12.1012</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>Medication</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>code</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.196.12.1001</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>MedicationRequest</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>status</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>active</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>medication.code</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.196.12.1001</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Data Requirements: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <div>
                     <p style="margin-bottom: 5px;">
                        <b>type: </b>
                        <span>MedicationStatement</span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>status</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>code:</b>
                           <span>completed</span>
                        </span>
                     </p>
                     <p style="margin-bottom: 5px;">
                        <b>code filter:</b>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>path: </b>
                           <span>medication.code</span>
                        </span>
                        <br/>
                        <span style="padding-left: 25px;">
                           <b>valueset: </b>
                           <span>2.16.840.1.113883.3.464.1003.196.12.1001</span>
                        </span>
                     </p>
                  </div>
               </td>
            </tr>
         </table>
         <table class="grid dict">
            <tr>
               <td>
                  <b>Content: </b>
               </td>
            </tr>
            <tr>
               <td style="padding-left: 25px; padding-right: 25px;">
                  <p style="margin-bottom: 5px;">
                     <b>type: </b>
                     <span>text/cql</span>
                  </p>
                  <p style="margin-bottom: 5px;">
                     <b>url: </b>
                     <span>library-cms146-example-content.cql</span>
                  </p>
               </td>
            </tr>
         </table>
      </div>
  </text>
  <identifier>
    <use value="official"/>
    <value value="CMS146"/>
  </identifier>
  <version value="2.0.0"/>
  <title value="Appropriate Testing for Children with Pharyngitis"/>
  <status value="draft"/>
  <type>
    <coding>
      <code value="logic-library"/>
    </coding>
  </type>
  <date value="2015-07-22"/>
  <description value="Logic for CMS 146: Appropriate Testing for Children with Pharyngitis"/>
  <relatedArtifact>
    <type value="depends-on"/>
    <resource value="http://hl7.org/fhir/Library/library-quick-model-definition"/>
  </relatedArtifact>

  <dataRequirement>
    <type value="Patient"/>
  </dataRequirement>

  <dataRequirement>
    <type value="Condition"/>
    <codeFilter>
      <path value="category"/>
      <code>
        <code value="diagnosis"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="clinicalStatus"/>
      <code>
        <code value="confirmed"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="code"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.102.12.1011"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="Condition"/>
    <codeFilter>
      <path value="category"/>
      <code>
        <code value="diagnosis"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="clinicalStatus"/>
      <code>
        <code value="confirmed"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="code"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.102.12.1012"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="Encounter"/>
    <codeFilter>
      <path value="status"/>
      <code>
        <code value="finished"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="class"/>
      <code>
        <code value="ambulatory"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="type"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.101.12.1061"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="DiagnosticReport"/>
    <codeFilter>
      <path value="diagnosis"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.198.12.1012"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="Medication"/>
    <codeFilter>
      <path value="code"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.196.12.1001"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="MedicationRequest"/>
    <codeFilter>
      <path value="status"/>
      <code>
        <code value="active"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="medication.code"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.196.12.1001"/>
    </codeFilter>
  </dataRequirement>

  <dataRequirement>
    <type value="MedicationStatement"/>
    <codeFilter>
      <path value="status"/>
      <code>
        <code value="completed"/>
      </code>
    </codeFilter>
    <codeFilter>
      <path value="medication.code"/>
      <valueSet value="urn:oid:2.16.840.1.113883.3.464.1003.196.12.1001"/>
    </codeFilter>
  </dataRequirement>
  <content>
    <contentType value="text/cql"/>
    <data value="bGlicmFyeSBDTVMxNDYgdmVyc2lvbiAnMicKCi8qIENNUyAxNDZ2MgogKgogKiA9PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09CiAqIFFETSBMb2dpYwogKiA9PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09CiAqIEluaXRpYWwgUGF0aWVudCBQb3B1bGF0aW9uID0KICogICAgIEFORDogIlBhdGllbnQgQ2hhcmFjdGVyaXN0aWMgQmlydGhkYXRlOiBiaXJ0aCBkYXRlIiA+PSAyIHllYXIocykgc3RhcnRzIGJlZm9yZSBzdGFydCBvZiAiTWVhc3VyZW1lbnQgUGVyaW9kIgogKiAgICAgQU5EOiAiUGF0aWVudCBDaGFyYWN0ZXJpc3RpYyBCaXJ0aGRhdGU6IGJpcnRoIGRhdGUiIDwgMTggeWVhcihzKSBzdGFydHMgYmVmb3JlIHN0YXJ0IG9mICJNZWFzdXJlbWVudCBQZXJpb2QiCiAqICAgICBBTkQ6CiAqICAgICAgICAgQU5EOiAiT2NjdXJyZW5jZSBBIG9mIEVuY291bnRlciwgUGVyZm9ybWVkOiBBbWJ1bGF0b3J5L0VEIFZpc2l0IiBkdXJpbmcgIk1lYXN1cmVtZW50IFBlcmlvZCIKICogICAgICAgICBBTkQ6ICJNZWRpY2F0aW9uLCBPcmRlcjogQW50aWJpb3RpYyBNZWRpY2F0aW9ucyIgPD0gMyBkYXkocykgc3RhcnRzIGFmdGVyIHN0YXJ0IG9mICJPY2N1cnJlbmNlIEEgb2YgRW5jb3VudGVyLCBQZXJmb3JtZWQ6IEFtYnVsYXRvcnkvRUQgVmlzaXQiCiAqICAgICAgICAgQU5EOgogKiAgICAgICAgICAgICBPUjogIk9jY3VycmVuY2UgQSBvZiBFbmNvdW50ZXIsIFBlcmZvcm1lZDogQW1idWxhdG9yeS9FRCBWaXNpdCIgZHVyaW5nCiAqICAgICAgICAgICAgICAgICBPUjogIk9jY3VycmVuY2UgQSBvZiBEaWFnbm9zaXMsIEFjdGl2ZTogQWN1dGUgUGhhcnluZ2l0aXMiCiAqICAgICAgICAgICAgICAgICBPUjogIk9jY3VycmVuY2UgQSBvZiBEaWFnbm9zaXMsIEFjdGl2ZTogQWN1dGUgVG9uc2lsbGl0aXMiCiAqICAgICAgICAgICAgIE9SOgogKiAgICAgICAgICAgICAgICAgT1I6ICJPY2N1cnJlbmNlIEEgb2YgRGlhZ25vc2lzLCBBY3RpdmU6IEFjdXRlIFBoYXJ5bmdpdGlzIgogKiAgICAgICAgICAgICAgICAgT1I6ICJPY2N1cnJlbmNlIEEgb2YgRGlhZ25vc2lzLCBBY3RpdmU6IEFjdXRlIFRvbnNpbGxpdGlzIgogKiAgICAgICAgICAgICAgICAgc3RhcnRzIGR1cmluZyAiT2NjdXJyZW5jZSBBIG9mIEVuY291bnRlciwgUGVyZm9ybWVkOiBBbWJ1bGF0b3J5L0VEIFZpc2l0IgogKgogKiBEZW5vbWluYXRvciA9CiAqICAgICBBTkQ6ICJJbml0aWFsIFBhdGllbnQgUG9wdWxhdGlvbiIKICoKICogRGVub21pbmF0b3IgRXhjbHVzaW9ucyA9CiAqICAgICBBTkQ6ICJNZWRpY2F0aW9uLCBBY3RpdmU6IEFudGliaW90aWMgTWVkaWNhdGlvbnMiIDw9IDMwIGRheShzKSBzdGFydHMgYmVmb3JlIHN0YXJ0IG9mCiAqICAgICAgICAgT1I6ICJPY2N1cnJlbmNlIEEgb2YgRGlhZ25vc2lzLCBBY3RpdmU6IEFjdXRlIFBoYXJ5bmdpdGlzIgogKiAgICAgICAgIE9SOiAiT2NjdXJyZW5jZSBBIG9mIERpYWdub3NpcywgQWN0aXZlOiBBY3V0ZSBUb25zaWxsaXRpcyIKICoKICogTnVtZXJhdG9yID0KICogICAgIEFORDoKICogICAgICAgICBPUjogIkxhYm9yYXRvcnkgVGVzdCwgUmVzdWx0OiBHcm91cCBBIFN0cmVwdG9jb2NjdXMgVGVzdCAocmVzdWx0KSIgPD0gMyBkYXkocykgc3RhcnRzIGJlZm9yZSBvciBkdXJpbmcgIk9jY3VycmVuY2UgQSBvZiBFbmNvdW50ZXIsIFBlcmZvcm1lZDogQW1idWxhdG9yeS9FRCBWaXNpdCIKICogICAgICAgICBPUjogIkxhYm9yYXRvcnkgVGVzdCwgUmVzdWx0OiBHcm91cCBBIFN0cmVwdG9jb2NjdXMgVGVzdCAocmVzdWx0KSIgPD0gMyBkYXkocykgc3RhcnRzIGFmdGVyIGVuZCBvZiAiT2NjdXJyZW5jZSBBIG9mIEVuY291bnRlciwgUGVyZm9ybWVkOiBBbWJ1bGF0b3J5L0VEIFZpc2l0IgogKgogKiBEZW5vbWluYXRvciBFeGNlcHRpb25zID0KICogICAgIE5vbmUKICogPT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PQogKi8KCnVzaW5nIEZISVIKCnZhbHVlc2V0ICJBY3V0ZSBQaGFyeW5naXRpcyI6ICcyLjE2Ljg0MC4xLjExMzg4My4zLjQ2NC4xMDAzLjEwMi4xMi4xMDExJwp2YWx1ZXNldCAiQWN1dGUgVG9uc2lsbGl0aXMiOiAnMi4xNi44NDAuMS4xMTM4ODMuMy40NjQuMTAwMy4xMDIuMTIuMTAxMicKdmFsdWVzZXQgIkFtYnVsYXRvcnkvRUQgVmlzaXQiOiAnMi4xNi44NDAuMS4xMTM4ODMuMy40NjQuMTAwMy4xMDEuMTIuMTA2MScKdmFsdWVzZXQgIkFudGliaW90aWMgTWVkaWNhdGlvbnMiOiAnMi4xNi44NDAuMS4xMTM4ODMuMy40NjQuMTAwMy4xOTYuMTIuMTAwMScKdmFsdWVzZXQgIkdyb3VwIEEgU3RyZXB0b2NvY2N1cyBUZXN0IjogJzIuMTYuODQwLjEuMTEzODgzLjMuNDY0LjEwMDMuMTk4LjEyLjEwMTInCgpwYXJhbWV0ZXIgTWVhc3VyZW1lbnRQZXJpb2QgSW50ZXJ2YWw8RGF0ZVRpbWU+Cgpjb250ZXh0IFBhdGllbnQKCmRlZmluZSBJbkRlbW9ncmFwaGljOgogICAgQWdlSW5ZZWFyc0F0KHN0YXJ0IG9mIE1lYXN1cmVtZW50UGVyaW9kKSA+PSAyIGFuZCBBZ2VJblllYXJzQXQoc3RhcnQgb2YgTWVhc3VyZW1lbnRQZXJpb2QpIDwgMTgKCmRlZmluZSBQaGFyeW5naXRpczoKICAgIFtDb25kaXRpb246ICJBY3V0ZSBQaGFyeW5naXRpcyJdIHVuaW9uIFtDb25kaXRpb246ICJBY3V0ZSBUb25zaWxsaXRpcyJdCgpkZWZpbmUgQW50aWJpb3RpY3M6CiAgICBbTWVkaWNhdGlvbk9yZGVyOiAiQW50aWJpb3RpYyBNZWRpY2F0aW9ucyJdCgpkZWZpbmUgTWVhc3VyZW1lbnRQZXJpb2RFbmNvdW50ZXJzOgogICAgW0VuY291bnRlcjogIkFtYnVsYXRvcnkvRUQgVmlzaXQiXSBFCiAgICAgICAgd2hlcmUgSW5EZW1vZ3JhcGhpYyBhbmQgSW50ZXJ2YWxbRS5wZXJpb2QuInN0YXJ0Ii52YWx1ZSwgRS5wZXJpb2QuImVuZCIudmFsdWVdIGR1cmluZyBNZWFzdXJlbWVudFBlcmlvZAoKZGVmaW5lIFBoYXJ5bmdpdGlzRW5jb3VudGVyczoKICAgIE1lYXN1cmVtZW50UGVyaW9kRW5jb3VudGVycyBFCiAgICAgICAgd2l0aCBQaGFyeW5naXRpcyBQCiAgICAgICAgICAgIHN1Y2ggdGhhdCBJbnRlcnZhbFtQLm9uc2V0RGF0ZVRpbWUudmFsdWUsIFAuYWJhdGVtZW50RGF0ZVRpbWUudmFsdWVdIGluY2x1ZGVzIEludGVydmFsW0UucGVyaW9kLiJzdGFydCIudmFsdWUsIEUucGVyaW9kLiJlbmQiLnZhbHVlXQogICAgICAgICAgICAgICAgb3IgUC5vbnNldERhdGVUaW1lLnZhbHVlIGluIEUucGVyaW9kCiAgICAgICAgd2l0aCBBbnRpYmlvdGljcyBBIHN1Y2ggdGhhdCBBLmRhdGVXcml0dGVuIDMgZGF5cyBvciBsZXNzIGFmdGVyIHN0YXJ0IG9mIEUucGVyaW9kCgpkZWZpbmUgUGhhcnluZ2l0aXNXaXRoUHJpb3JBbnRpYmlvdGljczoKICAgIFBoYXJ5bmdpdGlzIFAKICAgICAgICB3aXRoIEFudGliaW90aWNzIEEgc3VjaCB0aGF0IEEuZGF0ZVdyaXR0ZW4gMzAgZGF5cyBvciBsZXNzIGJlZm9yZSBQLm9uc2V0RGF0ZVRpbWUKCmRlZmluZSBFeGNsdWRlZEVuY291bnRlcnM6CiAgICBQaGFyeW5naXRpc0VuY291bnRlcnMgRQogICAgICAgIHdpdGggUGhhcnluZ2l0aXNXaXRoUHJpb3JBbnRpYmlvdGljcyBQCiAgICAgICAgICAgIHN1Y2ggdGhhdCBJbnRlcnZhbFtQLm9uc2V0RGF0ZVRpbWUsIFAuYWJhdGVtZW50RGF0ZV0gaW5jbHVkZXMgRS5wZXJpb2QKICAgICAgICAgICAgICAgIG9yIFAub25zZXREYXRlVGltZSBpbiBFLnBlcmlvZAoKZGVmaW5lIFN0cmVwVGVzdEVuY291bnRlcnM6CiAgICBQaGFyeW5naXRpc0VuY291bnRlcnMgRQogICAgICAgIHdpdGggW0RpYWdub3N0aWNSZXBvcnQ6ICJHcm91cCBBIFN0cmVwdG9jb2NjdXMgVGVzdCJdIFQKICAgICAgICAgICAgc3VjaCB0aGF0IFQucmVzdWx0IGlzIG5vdCBudWxsCiAgICAgICAgICAgICAgICBhbmQgVC5pc3N1ZWQgaW4gSW50ZXJ2YWxbc3RhcnQgb2YgRS5wZXJpb2QgLSAzIGRheXMsIGVuZCBvZiBFLnBlcmlvZCArIDMgZGF5c10KCg=="/>
  </content>
  <content>
    <contentType value="application/elm+xml"/>
    <data value="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPGxpYnJhcnkgeG1sbnM9InVybjpobDctb3JnOmVsbTpyMSIgeG1sbnM6dD0idXJuOmhsNy1vcmc6ZWxtLXR5cGVzOnIxIiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4bWxuczp4c2Q9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hIiB4bWxuczpmaGlyPSJodHRwOi8vaGw3Lm9yZy9maGlyIiB4bWxuczpxZG00Mz0idXJuOmhlYWx0aGl0LWdvdjpxZG06djRfMyIgeG1sbnM6cWRtNTM9InVybjpoZWFsdGhpdC1nb3Y6cWRtOnY1XzMiIHhtbG5zOmE9InVybjpobDctb3JnOmNxbC1hbm5vdGF0aW9uczpyMSI+CiAgIDxhbm5vdGF0aW9uIHRyYW5zbGF0b3JPcHRpb25zPSJFbmFibGVBbm5vdGF0aW9ucyxFbmFibGVMb2NhdG9ycyxEaXNhYmxlTGlzdERlbW90aW9uLERpc2FibGVMaXN0UHJvbW90aW9uLERpc2FibGVNZXRob2RJbnZvY2F0aW9uIiB4c2k6dHlwZT0iYTpDcWxUb0VsbUluZm8iLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9IjU3IiBzdGFydENoYXI9IjUiIGVuZExpbmU9IjU3IiBlbmRDaGFyPSIzNiIgbWVzc2FnZT0iQ291bGQgbm90IHJlc29sdmUgbWVtYmVyc2hpcCBvcGVyYXRvciBmb3IgdGVybWlub2xvZ3kgdGFyZ2V0IG9mIHRoZSByZXRyaWV2ZS4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9Indhcm5pbmciIHhzaTp0eXBlPSJhOkNxbFRvRWxtRXJyb3IiLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9IjU3IiBzdGFydENoYXI9IjQ0IiBlbmRMaW5lPSI1NyIgZW5kQ2hhcj0iNzUiIG1lc3NhZ2U9IkNvdWxkIG5vdCByZXNvbHZlIG1lbWJlcnNoaXAgb3BlcmF0b3IgZm9yIHRlcm1pbm9sb2d5IHRhcmdldCBvZiB0aGUgcmV0cmlldmUuIiBlcnJvclR5cGU9InNlbWFudGljIiBlcnJvclNldmVyaXR5PSJ3YXJuaW5nIiB4c2k6dHlwZT0iYTpDcWxUb0VsbUVycm9yIi8+CiAgIDxhbm5vdGF0aW9uIGxpYnJhcnlJZD0iQ01TMTQ2IiBsaWJyYXJ5VmVyc2lvbj0iMiIgc3RhcnRMaW5lPSI2MCIgc3RhcnRDaGFyPSI1IiBlbmRMaW5lPSI2MCIgZW5kQ2hhcj0iNDciIG1lc3NhZ2U9IkNvdWxkIG5vdCByZXNvbHZlIHR5cGUgbmFtZSBNZWRpY2F0aW9uT3JkZXIuIiBlcnJvclR5cGU9InNlbWFudGljIiBlcnJvclNldmVyaXR5PSJlcnJvciIgeHNpOnR5cGU9ImE6Q3FsVG9FbG1FcnJvciIvPgogICA8YW5ub3RhdGlvbiBsaWJyYXJ5SWQ9IkNNUzE0NiIgbGlicmFyeVZlcnNpb249IjIiIHN0YXJ0TGluZT0iNjMiIHN0YXJ0Q2hhcj0iNSIgZW5kTGluZT0iNjMiIGVuZENoYXI9IjM4IiBtZXNzYWdlPSJSZXRyaWV2ZSBoYXMgYSB0ZXJtaW5vbG9neSB0YXJnZXQgYnV0IGRvZXMgbm90IHNwZWNpZnkgYSBjb2RlIHBhdGggYW5kIHRoZSB0eXBlIG9mIHRoZSByZXRyaWV2ZSBkb2VzIG5vdCBoYXZlIGEgcHJpbWFyeSBjb2RlIHBhdGggZGVmaW5lZC4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9Indhcm5pbmciIHhzaTp0eXBlPSJhOkNxbFRvRWxtRXJyb3IiLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9IjYzIiBzdGFydENoYXI9IjUiIGVuZExpbmU9IjYzIiBlbmRDaGFyPSIzOCIgbWVzc2FnZT0iQ291bGQgbm90IHJlc29sdmUgbWVtYmVyc2hpcCBvcGVyYXRvciBmb3IgdGVybWlub2xvZ3kgdGFyZ2V0IG9mIHRoZSByZXRyaWV2ZS4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9Indhcm5pbmciIHhzaTp0eXBlPSJhOkNxbFRvRWxtRXJyb3IiLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9IjY5IiBzdGFydENoYXI9IjM0IiBlbmRMaW5lPSI2OSIgZW5kQ2hhcj0iNDYiIG1lc3NhZ2U9Ik1lbWJlciBvbnNldERhdGVUaW1lIG5vdCBmb3VuZCBmb3IgdHlwZSBGSElSLkNvbmRpdGlvbi4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9ImVycm9yIiB4c2k6dHlwZT0iYTpDcWxUb0VsbUVycm9yIi8+CiAgIDxhbm5vdGF0aW9uIGxpYnJhcnlJZD0iQ01TMTQ2IiBsaWJyYXJ5VmVyc2lvbj0iMiIgc3RhcnRMaW5lPSI3NSIgc3RhcnRDaGFyPSIxNCIgZW5kTGluZT0iNzUiIGVuZENoYXI9IjI0IiBtZXNzYWdlPSJDb3VsZCBub3QgdmFsaWRhdGUgcmVmZXJlbmNlIHRvIGV4cHJlc3Npb24gQW50aWJpb3RpY3MgYmVjYXVzZSBpdHMgZGVmaW5pdGlvbiBjb250YWlucyBlcnJvcnMuIiBlcnJvclR5cGU9InNlbWFudGljIiBlcnJvclNldmVyaXR5PSJlcnJvciIgeHNpOnR5cGU9ImE6Q3FsVG9FbG1FcnJvciIvPgogICA8YW5ub3RhdGlvbiBsaWJyYXJ5SWQ9IkNNUzE0NiIgbGlicmFyeVZlcnNpb249IjIiIHN0YXJ0TGluZT0iNzgiIHN0YXJ0Q2hhcj0iNSIgZW5kTGluZT0iNzgiIGVuZENoYXI9IjI1IiBtZXNzYWdlPSJDb3VsZCBub3QgdmFsaWRhdGUgcmVmZXJlbmNlIHRvIGV4cHJlc3Npb24gUGhhcnluZ2l0aXNFbmNvdW50ZXJzIGJlY2F1c2UgaXRzIGRlZmluaXRpb24gY29udGFpbnMgZXJyb3JzLiIgZXJyb3JUeXBlPSJzZW1hbnRpYyIgZXJyb3JTZXZlcml0eT0iZXJyb3IiIHhzaTp0eXBlPSJhOkNxbFRvRWxtRXJyb3IiLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9Ijg0IiBzdGFydENoYXI9IjUiIGVuZExpbmU9Ijg0IiBlbmRDaGFyPSIyNSIgbWVzc2FnZT0iQ291bGQgbm90IHZhbGlkYXRlIHJlZmVyZW5jZSB0byBleHByZXNzaW9uIFBoYXJ5bmdpdGlzRW5jb3VudGVycyBiZWNhdXNlIGl0cyBkZWZpbml0aW9uIGNvbnRhaW5zIGVycm9ycy4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9ImVycm9yIiB4c2k6dHlwZT0iYTpDcWxUb0VsbUVycm9yIi8+CiAgIDxhbm5vdGF0aW9uIGxpYnJhcnlJZD0iQ01TMTQ2IiBsaWJyYXJ5VmVyc2lvbj0iMiIgc3RhcnRMaW5lPSI4NSIgc3RhcnRDaGFyPSIxNCIgZW5kTGluZT0iODUiIGVuZENoYXI9IjYxIiBtZXNzYWdlPSJSZXRyaWV2ZSBoYXMgYSB0ZXJtaW5vbG9neSB0YXJnZXQgYnV0IGRvZXMgbm90IHNwZWNpZnkgYSBjb2RlIHBhdGggYW5kIHRoZSB0eXBlIG9mIHRoZSByZXRyaWV2ZSBkb2VzIG5vdCBoYXZlIGEgcHJpbWFyeSBjb2RlIHBhdGggZGVmaW5lZC4iIGVycm9yVHlwZT0ic2VtYW50aWMiIGVycm9yU2V2ZXJpdHk9Indhcm5pbmciIHhzaTp0eXBlPSJhOkNxbFRvRWxtRXJyb3IiLz4KICAgPGFubm90YXRpb24gbGlicmFyeUlkPSJDTVMxNDYiIGxpYnJhcnlWZXJzaW9uPSIyIiBzdGFydExpbmU9Ijg1IiBzdGFydENoYXI9IjE0IiBlbmRMaW5lPSI4NSIgZW5kQ2hhcj0iNjEiIG1lc3NhZ2U9IkNvdWxkIG5vdCByZXNvbHZlIG1lbWJlcnNoaXAgb3BlcmF0b3IgZm9yIHRlcm1pbm9sb2d5IHRhcmdldCBvZiB0aGUgcmV0cmlldmUuIiBlcnJvclR5cGU9InNlbWFudGljIiBlcnJvclNldmVyaXR5PSJ3YXJuaW5nIiB4c2k6dHlwZT0iYTpDcWxUb0VsbUVycm9yIi8+CiAgIDxpZGVudGlmaWVyIGlkPSJDTVMxNDYiIHZlcnNpb249IjIiLz4KICAgPHNjaGVtYUlkZW50aWZpZXIgaWQ9InVybjpobDctb3JnOmVsbSIgdmVyc2lvbj0icjEiLz4KICAgPHVzaW5ncz4KICAgICAgPGRlZiBsb2NhbElkZW50aWZpZXI9IlN5c3RlbSIgdXJpPSJ1cm46aGw3LW9yZzplbG0tdHlwZXM6cjEiLz4KICAgICAgPGRlZiBsb2NhbElkPSIxIiBsb2NhdG9yPSI0MToxLTQxOjEwIiBsb2NhbElkZW50aWZpZXI9IkZISVIiIHVyaT0iaHR0cDovL2hsNy5vcmcvZmhpciIvPgogICA8L3VzaW5ncz4KICAgPHBhcmFtZXRlcnM+CiAgICAgIDxkZWYgbG9jYWxJZD0iOSIgbG9jYXRvcj0iNDk6MS00OTo0NiIgbmFtZT0iTWVhc3VyZW1lbnRQZXJpb2QiIGFjY2Vzc0xldmVsPSJQdWJsaWMiPgogICAgICAgICA8cGFyYW1ldGVyVHlwZVNwZWNpZmllciBsb2NhbElkPSI4IiBsb2NhdG9yPSI0OToyOS00OTo0NiIgeHNpOnR5cGU9IkludGVydmFsVHlwZVNwZWNpZmllciI+CiAgICAgICAgICAgIDxwb2ludFR5cGUgbG9jYWxJZD0iNyIgbG9jYXRvcj0iNDk6MzgtNDk6NDUiIG5hbWU9InQ6RGF0ZVRpbWUiIHhzaTp0eXBlPSJOYW1lZFR5cGVTcGVjaWZpZXIiLz4KICAgICAgICAgPC9wYXJhbWV0ZXJUeXBlU3BlY2lmaWVyPgogICAgICA8L2RlZj4KICAgPC9wYXJhbWV0ZXJzPgogICA8dmFsdWVTZXRzPgogICAgICA8ZGVmIGxvY2FsSWQ9IjIiIGxvY2F0b3I9IjQzOjEtNDM6NzIiIG5hbWU9IkFjdXRlIFBoYXJ5bmdpdGlzIiBpZD0iMi4xNi44NDAuMS4xMTM4ODMuMy40NjQuMTAwMy4xMDIuMTIuMTAxMSIgYWNjZXNzTGV2ZWw9IlB1YmxpYyIvPgogICAgICA8ZGVmIGxvY2FsSWQ9IjMiIGxvY2F0b3I9IjQ0OjEtNDQ6NzIiIG5hbWU9IkFjdXRlIFRvbnNpbGxpdGlzIiBpZD0iMi4xNi44NDAuMS4xMTM4ODMuMy40NjQuMTAwMy4xMDIuMTIuMTAxMiIgYWNjZXNzTGV2ZWw9IlB1YmxpYyIvPgogICAgICA8ZGVmIGxvY2FsSWQ9IjQiIGxvY2F0b3I9IjQ1OjEtNDU6NzQiIG5hbWU9IkFtYnVsYXRvcnkvRUQgVmlzaXQiIGlkPSIyLjE2Ljg0MC4xLjExMzg4My4zLjQ2NC4xMDAzLjEwMS4xMi4xMDYxIiBhY2Nlc3NMZXZlbD0iUHVibGljIi8+CiAgICAgIDxkZWYgbG9jYWxJZD0iNSIgbG9jYXRvcj0iNDY6MS00Njo3NyIgbmFtZT0iQW50aWJpb3RpYyBNZWRpY2F0aW9ucyIgaWQ9IjIuMTYuODQwLjEuMTEzODgzLjMuNDY0LjEwMDMuMTk2LjEyLjEwMDEiIGFjY2Vzc0xldmVsPSJQdWJsaWMiLz4KICAgICAgPGRlZiBsb2NhbElkPSI2IiBsb2NhdG9yPSI0NzoxLTQ3OjgxIiBuYW1lPSJHcm91cCBBIFN0cmVwdG9jb2NjdXMgVGVzdCIgaWQ9IjIuMTYuODQwLjEuMTEzODgzLjMuNDY0LjEwMDMuMTk4LjEyLjEwMTIiIGFjY2Vzc0xldmVsPSJQdWJsaWMiLz4KICAgPC92YWx1ZVNldHM+CiAgIDxzdGF0ZW1lbnRzPgogICAgICA8ZGVmIGxvY2F0b3I9IjUxOjEtNTE6MTUiIG5hbWU9IlBhdGllbnQiIGNvbnRleHQ9IlBhdGllbnQiPgogICAgICAgICA8ZXhwcmVzc2lvbiB4c2k6dHlwZT0iU2luZ2xldG9uRnJvbSI+CiAgICAgICAgICAgIDxvcGVyYW5kIGxvY2F0b3I9IjUxOjEtNTE6MTUiIGRhdGFUeXBlPSJmaGlyOlBhdGllbnQiIHhzaTp0eXBlPSJSZXRyaWV2ZSIvPgogICAgICAgICA8L2V4cHJlc3Npb24+CiAgICAgIDwvZGVmPgogICAgICA8ZGVmIGxvY2FsSWQ9IjIxIiBsb2NhdG9yPSI1MzoxLTU0Ojk5IiBuYW1lPSJJbkRlbW9ncmFwaGljIiBjb250ZXh0PSJQYXRpZW50IiBhY2Nlc3NMZXZlbD0iUHVibGljIj4KICAgICAgICAgPGFubm90YXRpb24geHNpOnR5cGU9ImE6QW5ub3RhdGlvbiI+CiAgICAgICAgICAgIDxhOnMgcj0iMjEiPgogICAgICAgICAgICAgICA8YTpzPmRlZmluZSBJbkRlbW9ncmFwaGljOiYjeGQ7CiAgICA8L2E6cz4KICAgICAgICAgICAgICAgPGE6cyByPSIyMCI+CiAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTQiPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEyIj4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5BZ2VJblllYXJzQXQoPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTEiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnN0YXJ0IG9mIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEwIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5NZWFzdXJlbWVudFBlcmlvZDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+KTwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMyI+ID49IDI8L2E6cz4KICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDxhOnM+IGFuZCA8L2E6cz4KICAgICAgICAgICAgICAgICAgPGE6cyByPSIxOSI+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTciPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkFnZUluWWVhcnNBdCg8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxNiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+c3RhcnQgb2YgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTUiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPk1lYXN1cmVtZW50UGVyaW9kPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4pPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjE4Ij4gJmx0OyAxODwvYTpzPgogICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgIDwvYTpzPgogICAgICAgICA8L2Fubm90YXRpb24+CiAgICAgICAgIDxleHByZXNzaW9uIGxvY2FsSWQ9IjIwIiBsb2NhdG9yPSI1NDo1LTU0Ojk5IiB4c2k6dHlwZT0iQW5kIj4KICAgICAgICAgICAgPG9wZXJhbmQgbG9jYWxJZD0iMTQiIGxvY2F0b3I9IjU0OjUtNTQ6NDkiIHhzaTp0eXBlPSJHcmVhdGVyT3JFcXVhbCI+CiAgICAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjEyIiBsb2NhdG9yPSI1NDo1LTU0OjQ0IiBwcmVjaXNpb249IlllYXIiIHhzaTp0eXBlPSJDYWxjdWxhdGVBZ2VBdCI+CiAgICAgICAgICAgICAgICAgIDxvcGVyYW5kIHhzaTp0eXBlPSJUb0RhdGVUaW1lIj4KICAgICAgICAgICAgICAgICAgICAgPG9wZXJhbmQgcGF0aD0iYmlydGhEYXRlLnZhbHVlIiB4c2k6dHlwZT0iUHJvcGVydHkiPgogICAgICAgICAgICAgICAgICAgICAgICA8c291cmNlIG5hbWU9IlBhdGllbnQiIHhzaTp0eXBlPSJFeHByZXNzaW9uUmVmIi8+CiAgICAgICAgICAgICAgICAgICAgIDwvb3BlcmFuZD4KICAgICAgICAgICAgICAgICAgPC9vcGVyYW5kPgogICAgICAgICAgICAgICAgICA8b3BlcmFuZCBsb2NhbElkPSIxMSIgbG9jYXRvcj0iNTQ6MTgtNTQ6NDMiIHhzaTp0eXBlPSJTdGFydCI+CiAgICAgICAgICAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjEwIiBsb2NhdG9yPSI1NDoyNy01NDo0MyIgbmFtZT0iTWVhc3VyZW1lbnRQZXJpb2QiIHhzaTp0eXBlPSJQYXJhbWV0ZXJSZWYiLz4KICAgICAgICAgICAgICAgICAgPC9vcGVyYW5kPgogICAgICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjEzIiBsb2NhdG9yPSI1NDo0OSIgdmFsdWVUeXBlPSJ0OkludGVnZXIiIHZhbHVlPSIyIiB4c2k6dHlwZT0iTGl0ZXJhbCIvPgogICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjE5IiBsb2NhdG9yPSI1NDo1NS01NDo5OSIgeHNpOnR5cGU9Ikxlc3MiPgogICAgICAgICAgICAgICA8b3BlcmFuZCBsb2NhbElkPSIxNyIgbG9jYXRvcj0iNTQ6NTUtNTQ6OTQiIHByZWNpc2lvbj0iWWVhciIgeHNpOnR5cGU9IkNhbGN1bGF0ZUFnZUF0Ij4KICAgICAgICAgICAgICAgICAgPG9wZXJhbmQgeHNpOnR5cGU9IlRvRGF0ZVRpbWUiPgogICAgICAgICAgICAgICAgICAgICA8b3BlcmFuZCBwYXRoPSJiaXJ0aERhdGUudmFsdWUiIHhzaTp0eXBlPSJQcm9wZXJ0eSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxzb3VyY2UgbmFtZT0iUGF0aWVudCIgeHNpOnR5cGU9IkV4cHJlc3Npb25SZWYiLz4KICAgICAgICAgICAgICAgICAgICAgPC9vcGVyYW5kPgogICAgICAgICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjE2IiBsb2NhdG9yPSI1NDo2OC01NDo5MyIgeHNpOnR5cGU9IlN0YXJ0Ij4KICAgICAgICAgICAgICAgICAgICAgPG9wZXJhbmQgbG9jYWxJZD0iMTUiIGxvY2F0b3I9IjU0Ojc3LTU0OjkzIiBuYW1lPSJNZWFzdXJlbWVudFBlcmlvZCIgeHNpOnR5cGU9IlBhcmFtZXRlclJlZiIvPgogICAgICAgICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgICAgIDwvb3BlcmFuZD4KICAgICAgICAgICAgICAgPG9wZXJhbmQgbG9jYWxJZD0iMTgiIGxvY2F0b3I9IjU0Ojk4LTU0Ojk5IiB2YWx1ZVR5cGU9InQ6SW50ZWdlciIgdmFsdWU9IjE4IiB4c2k6dHlwZT0iTGl0ZXJhbCIvPgogICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgIDwvZXhwcmVzc2lvbj4KICAgICAgPC9kZWY+CiAgICAgIDxkZWYgbG9jYWxJZD0iMjUiIGxvY2F0b3I9IjU2OjEtNTc6NzUiIG5hbWU9IlBoYXJ5bmdpdGlzIiBjb250ZXh0PSJQYXRpZW50IiBhY2Nlc3NMZXZlbD0iUHVibGljIj4KICAgICAgICAgPGFubm90YXRpb24geHNpOnR5cGU9ImE6QW5ub3RhdGlvbiI+CiAgICAgICAgICAgIDxhOnMgcj0iMjUiPgogICAgICAgICAgICAgICA8YTpzPmRlZmluZSBQaGFyeW5naXRpczomI3hkOwogICAgPC9hOnM+CiAgICAgICAgICAgICAgIDxhOnMgcj0iMjQiPgogICAgICAgICAgICAgICAgICA8YTpzIHI9IjIyIj4KICAgICAgICAgICAgICAgICAgICAgPGE6cz5bQ29uZGl0aW9uOiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4mcXVvdDtBY3V0ZSBQaGFyeW5naXRpcyZxdW90OzwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cz5dPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8YTpzPiB1bmlvbiA8L2E6cz4KICAgICAgICAgICAgICAgICAgPGE6cyByPSIyMyI+CiAgICAgICAgICAgICAgICAgICAgIDxhOnM+W0NvbmRpdGlvbjogPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JnF1b3Q7QWN1dGUgVG9uc2lsbGl0aXMmcXVvdDs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnM+XTwvYTpzPgogICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgIDwvYTpzPgogICAgICAgICA8L2Fubm90YXRpb24+CiAgICAgICAgIDxleHByZXNzaW9uIGxvY2FsSWQ9IjI0IiBsb2NhdG9yPSI1Nzo1LTU3Ojc1IiB4c2k6dHlwZT0iVW5pb24iPgogICAgICAgICAgICA8b3BlcmFuZCBsb2NhbElkPSIyMiIgbG9jYXRvcj0iNTc6NS01NzozNiIgZGF0YVR5cGU9ImZoaXI6Q29uZGl0aW9uIiBjb2RlUHJvcGVydHk9ImNvZGUiIHhzaTp0eXBlPSJSZXRyaWV2ZSI+CiAgICAgICAgICAgICAgIDxjb2RlcyBsb2NhdG9yPSI1NzoxNy01NzozNSIgbmFtZT0iQWN1dGUgUGhhcnluZ2l0aXMiIHhzaTp0eXBlPSJWYWx1ZVNldFJlZiIvPgogICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjIzIiBsb2NhdG9yPSI1Nzo0NC01Nzo3NSIgZGF0YVR5cGU9ImZoaXI6Q29uZGl0aW9uIiBjb2RlUHJvcGVydHk9ImNvZGUiIHhzaTp0eXBlPSJSZXRyaWV2ZSI+CiAgICAgICAgICAgICAgIDxjb2RlcyBsb2NhdG9yPSI1Nzo1Ni01Nzo3NCIgbmFtZT0iQWN1dGUgVG9uc2lsbGl0aXMiIHhzaTp0eXBlPSJWYWx1ZVNldFJlZiIvPgogICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgIDwvZXhwcmVzc2lvbj4KICAgICAgPC9kZWY+CiAgICAgIDxkZWYgbG9jYWxJZD0iMjciIGxvY2F0b3I9IjU5OjEtNjA6NDciIG5hbWU9IkFudGliaW90aWNzIiBjb250ZXh0PSJQYXRpZW50IiBhY2Nlc3NMZXZlbD0iUHVibGljIj4KICAgICAgICAgPGFubm90YXRpb24geHNpOnR5cGU9ImE6QW5ub3RhdGlvbiI+CiAgICAgICAgICAgIDxhOnMgcj0iMjciPgogICAgICAgICAgICAgICA8YTpzPmRlZmluZSBBbnRpYmlvdGljczomI3hkOwogICAgPC9hOnM+CiAgICAgICAgICAgICAgIDxhOnMgcj0iMjYiPgogICAgICAgICAgICAgICAgICA8YTpzPltNZWRpY2F0aW9uT3JkZXI6ICZxdW90O0FudGliaW90aWMgTWVkaWNhdGlvbnMmcXVvdDtdPC9hOnM+CiAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICA8L2E6cz4KICAgICAgICAgPC9hbm5vdGF0aW9uPgogICAgICAgICA8ZXhwcmVzc2lvbiBsb2NhbElkPSIyNiIgbG9jYXRvcj0iNjA6NS02MDo0NyIgeHNpOnR5cGU9Ik51bGwiLz4KICAgICAgPC9kZWY+CiAgICAgIDxkZWYgbG9jYWxJZD0iNDQiIGxvY2F0b3I9IjYyOjEtNjQ6MTExIiBuYW1lPSJNZWFzdXJlbWVudFBlcmlvZEVuY291bnRlcnMiIGNvbnRleHQ9IlBhdGllbnQiIGFjY2Vzc0xldmVsPSJQdWJsaWMiPgogICAgICAgICA8YW5ub3RhdGlvbiB4c2k6dHlwZT0iYTpBbm5vdGF0aW9uIj4KICAgICAgICAgICAgPGE6cyByPSI0NCI+CiAgICAgICAgICAgICAgIDxhOnM+ZGVmaW5lIE1lYXN1cmVtZW50UGVyaW9kRW5jb3VudGVyczomI3hkOwogICAgPC9hOnM+CiAgICAgICAgICAgICAgIDxhOnMgcj0iNDMiPgogICAgICAgICAgICAgICAgICA8YTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjI5Ij4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIyOCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMjgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPltFbmNvdW50ZXI6IDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiZxdW90O0FtYnVsYXRvcnkvRUQgVmlzaXQmcXVvdDs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+XTwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IEU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8YTpzPiYjeGQ7CiAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNDIiPgogICAgICAgICAgICAgICAgICAgICA8YTpzPndoZXJlIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQyIj4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzMCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+SW5EZW1vZ3JhcGhpYzwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gYW5kIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQxIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzOSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+SW50ZXJ2YWxbPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMzQiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjMzIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzMiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMzEiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjMyIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5wZXJpb2Q8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzMyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JnF1b3Q7c3RhcnQmcXVvdDs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzNCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+dmFsdWU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiwgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMzgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjM3Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzNiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMzUiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjM2Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5wZXJpb2Q8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIzNyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JnF1b3Q7ZW5kJnF1b3Q7PC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMzgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnZhbHVlPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5dPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQxIj4gZHVyaW5nIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQwIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5NZWFzdXJlbWVudFBlcmlvZDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgIDwvYTpzPgogICAgICAgICA8L2Fubm90YXRpb24+CiAgICAgICAgIDxleHByZXNzaW9uIGxvY2FsSWQ9IjQzIiBsb2NhdG9yPSI2Mzo1LTY0OjExMSIgeHNpOnR5cGU9IlF1ZXJ5Ij4KICAgICAgICAgICAgPHNvdXJjZSBsb2NhbElkPSIyOSIgbG9jYXRvcj0iNjM6NS02Mzo0MCIgYWxpYXM9IkUiPgogICAgICAgICAgICAgICA8ZXhwcmVzc2lvbiBsb2NhbElkPSIyOCIgbG9jYXRvcj0iNjM6NS02MzozOCIgZGF0YVR5cGU9ImZoaXI6RW5jb3VudGVyIiB4c2k6dHlwZT0iUmV0cmlldmUiPgogICAgICAgICAgICAgICAgICA8Y29kZXMgbG9jYXRvcj0iNjM6MTctNjM6MzciIG5hbWU9IkFtYnVsYXRvcnkvRUQgVmlzaXQiIHhzaTp0eXBlPSJWYWx1ZVNldFJlZiIvPgogICAgICAgICAgICAgICA8L2V4cHJlc3Npb24+CiAgICAgICAgICAgIDwvc291cmNlPgogICAgICAgICAgICA8d2hlcmUgbG9jYWxJZD0iNDIiIGxvY2F0b3I9IjY0OjktNjQ6MTExIiB4c2k6dHlwZT0iQW5kIj4KICAgICAgICAgICAgICAgPG9wZXJhbmQgbG9jYWxJZD0iMzAiIGxvY2F0b3I9IjY0OjE1LTY0OjI3IiBuYW1lPSJJbkRlbW9ncmFwaGljIiB4c2k6dHlwZT0iRXhwcmVzc2lvblJlZiIvPgogICAgICAgICAgICAgICA8b3BlcmFuZCBsb2NhbElkPSI0MSIgbG9jYXRvcj0iNjQ6MzMtNjQ6MTExIiB4c2k6dHlwZT0iSW5jbHVkZWRJbiI+CiAgICAgICAgICAgICAgICAgIDxvcGVyYW5kIGxvY2FsSWQ9IjM5IiBsb2NhdG9yPSI2NDozMy02NDo4NiIgbG93Q2xvc2VkPSJ0cnVlIiBoaWdoQ2xvc2VkPSJ0cnVlIiB4c2k6dHlwZT0iSW50ZXJ2YWwiPgogICAgICAgICAgICAgICAgICAgICA8bG93IGxvY2FsSWQ9IjM0IiBsb2NhdG9yPSI2NDo0Mi02NDo2MyIgcGF0aD0idmFsdWUiIHhzaTp0eXBlPSJQcm9wZXJ0eSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxzb3VyY2UgbG9jYWxJZD0iMzMiIGxvY2F0b3I9IjY0OjQyLTY0OjU3IiBwYXRoPSJzdGFydCIgeHNpOnR5cGU9IlByb3BlcnR5Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNvdXJjZSBsb2NhbElkPSIzMiIgbG9jYXRvcj0iNjQ6NDItNjQ6NDkiIHBhdGg9InBlcmlvZCIgc2NvcGU9IkUiIHhzaTp0eXBlPSJQcm9wZXJ0eSIvPgogICAgICAgICAgICAgICAgICAgICAgICA8L3NvdXJjZT4KICAgICAgICAgICAgICAgICAgICAgPC9sb3c+CiAgICAgICAgICAgICAgICAgICAgIDxoaWdoIGxvY2FsSWQ9IjM4IiBsb2NhdG9yPSI2NDo2Ni02NDo4NSIgcGF0aD0idmFsdWUiIHhzaTp0eXBlPSJQcm9wZXJ0eSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxzb3VyY2UgbG9jYWxJZD0iMzciIGxvY2F0b3I9IjY0OjY2LTY0Ojc5IiBwYXRoPSJlbmQiIHhzaTp0eXBlPSJQcm9wZXJ0eSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzb3VyY2UgbG9jYWxJZD0iMzYiIGxvY2F0b3I9IjY0OjY2LTY0OjczIiBwYXRoPSJwZXJpb2QiIHNjb3BlPSJFIiB4c2k6dHlwZT0iUHJvcGVydHkiLz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9zb3VyY2U+CiAgICAgICAgICAgICAgICAgICAgIDwvaGlnaD4KICAgICAgICAgICAgICAgICAgPC9vcGVyYW5kPgogICAgICAgICAgICAgICAgICA8b3BlcmFuZCBsb2NhbElkPSI0MCIgbG9jYXRvcj0iNjQ6OTUtNjQ6MTExIiBuYW1lPSJNZWFzdXJlbWVudFBlcmlvZCIgeHNpOnR5cGU9IlBhcmFtZXRlclJlZiIvPgogICAgICAgICAgICAgICA8L29wZXJhbmQ+CiAgICAgICAgICAgIDwvd2hlcmU+CiAgICAgICAgIDwvZXhwcmVzc2lvbj4KICAgICAgPC9kZWY+CiAgICAgIDxkZWYgbG9jYWxJZD0iNzUiIGxvY2F0b3I9IjY2OjEtNzE6ODkiIG5hbWU9IlBoYXJ5bmdpdGlzRW5jb3VudGVycyIgY29udGV4dD0iUGF0aWVudCIgYWNjZXNzTGV2ZWw9IlB1YmxpYyI+CiAgICAgICAgIDxhbm5vdGF0aW9uIHhzaTp0eXBlPSJhOkFubm90YXRpb24iPgogICAgICAgICAgICA8YTpzIHI9Ijc1Ij4KICAgICAgICAgICAgICAgPGE6cz5kZWZpbmUgUGhhcnluZ2l0aXNFbmNvdW50ZXJzOiYjeGQ7CiAgICA8L2E6cz4KICAgICAgICAgICAgICAgPGE6cyByPSI3NCI+CiAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNDYiPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQ1Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5NZWFzdXJlbWVudFBlcmlvZEVuY291bnRlcnM8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiBFPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgPGE6cz4mI3hkOwogICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8YTpzIHI9IjczIj4KICAgICAgICAgICAgICAgICAgICAgPGE6cz53aXRoIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQ4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI0NyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+UGhhcnluZ2l0aXM8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiBQPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzPiYjeGQ7CiAgICAgICAgICAgIHN1Y2ggdGhhdCA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI3MiI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjUiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjU1Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5JbnRlcnZhbFs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1MSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNTAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjQ5Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5QPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1MCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+b25zZXREYXRlVGltZTwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjUxIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz52YWx1ZTwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LCA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1NCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNTMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjUyIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5QPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1MyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+YWJhdGVtZW50RGF0ZVRpbWU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1NCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+dmFsdWU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPl08L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjUiPiBpbmNsdWRlcyA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI2NCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+SW50ZXJ2YWxbPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNTkiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjU4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1NyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNTYiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjU3Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5wZXJpb2Q8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1OCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JnF1b3Q7c3RhcnQmcXVvdDs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI1OSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+dmFsdWU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiwgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjYyIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI2MSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjYxIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5wZXJpb2Q8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI2MiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JnF1b3Q7ZW5kJnF1b3Q7PC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnZhbHVlPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5dPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4mI3hkOwogICAgICAgICAgICAgICAgb3IgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNzEiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjY4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI2NyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjYiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPlA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjY3Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5vbnNldERhdGVUaW1lPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNjgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnZhbHVlPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gaW4gPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNzAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjY5Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5FPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI3MCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+cGVyaW9kPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgPGE6cz4mI3hkOwogICAgICAgIHdpdGggQW50aWJpb3RpY3MgQSBzdWNoIHRoYXQgQS5kYXRlV3JpdHRlbiAzIGRheXMgb3IgbGVzcyBhZnRlciBzdGFydCBvZiBFLnBlcmlvZDwvYTpzPgogICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgIDwvYW5ub3RhdGlvbj4KICAgICAgICAgPGV4cHJlc3Npb24gbG9jYWxJZD0iNzQiIGxvY2F0b3I9IjY3OjUtNzE6ODkiIHhzaTp0eXBlPSJOdWxsIi8+CiAgICAgIDwvZGVmPgogICAgICA8ZGVmIGxvY2FsSWQ9Ijg4IiBsb2NhdG9yPSI3MzoxLTc1Ojg5IiBuYW1lPSJQaGFyeW5naXRpc1dpdGhQcmlvckFudGliaW90aWNzIiBjb250ZXh0PSJQYXRpZW50IiBhY2Nlc3NMZXZlbD0iUHVibGljIj4KICAgICAgICAgPGFubm90YXRpb24geHNpOnR5cGU9ImE6QW5ub3RhdGlvbiI+CiAgICAgICAgICAgIDxhOnMgcj0iODgiPgogICAgICAgICAgICAgICA8YTpzPmRlZmluZSBQaGFyeW5naXRpc1dpdGhQcmlvckFudGliaW90aWNzOiYjeGQ7CiAgICA8L2E6cz4KICAgICAgICAgICAgICAgPGE6cyByPSI4NyI+CiAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNzciPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijc2Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5QaGFyeW5naXRpczwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IFA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8YTpzPiYjeGQ7CiAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDxhOnMgcj0iODYiPgogICAgICAgICAgICAgICAgICAgICA8YTpzPndpdGggPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iNzkiPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijc4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5BbnRpYmlvdGljczwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IEE8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnM+IHN1Y2ggdGhhdCA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI4NSI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iODEiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjgwIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5BPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI4MSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+ZGF0ZVdyaXR0ZW48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI4NSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iODQiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPjMwIGRheXM8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IG9yIGxlc3MgYmVmb3JlPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI4MyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iODIiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPlA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjgzIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5vbnNldERhdGVUaW1lPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgIDwvYW5ub3RhdGlvbj4KICAgICAgICAgPGV4cHJlc3Npb24gbG9jYWxJZD0iODciIGxvY2F0b3I9Ijc0OjUtNzU6ODkiIHhzaTp0eXBlPSJOdWxsIi8+CiAgICAgIDwvZGVmPgogICAgICA8ZGVmIGxvY2FsSWQ9IjEwOSIgbG9jYXRvcj0iNzc6MS04MTo0NiIgbmFtZT0iRXhjbHVkZWRFbmNvdW50ZXJzIiBjb250ZXh0PSJQYXRpZW50IiBhY2Nlc3NMZXZlbD0iUHVibGljIj4KICAgICAgICAgPGFubm90YXRpb24geHNpOnR5cGU9ImE6QW5ub3RhdGlvbiI+CiAgICAgICAgICAgIDxhOnMgcj0iMTA5Ij4KICAgICAgICAgICAgICAgPGE6cz5kZWZpbmUgRXhjbHVkZWRFbmNvdW50ZXJzOiYjeGQ7CiAgICA8L2E6cz4KICAgICAgICAgICAgICAgPGE6cyByPSIxMDgiPgogICAgICAgICAgICAgICAgICA8YTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjkwIj4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI4OSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+UGhhcnluZ2l0aXNFbmNvdW50ZXJzPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gRTwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDxhOnM+JiN4ZDsKICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMDciPgogICAgICAgICAgICAgICAgICAgICA8YTpzPndpdGggPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iOTIiPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjkxIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5QaGFyeW5naXRpc1dpdGhQcmlvckFudGliaW90aWNzPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gUDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cz4mI3hkOwogICAgICAgICAgICBzdWNoIHRoYXQgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTA2Ij4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMDAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijk3Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5JbnRlcnZhbFs8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI5NCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iOTMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPlA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijk0Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5vbnNldERhdGVUaW1lPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4sIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijk2Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI5NSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+UDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iOTYiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPmFiYXRlbWVudERhdGU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPl08L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTAwIj4gaW5jbHVkZXMgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iOTkiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9Ijk4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5FPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSI5OSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+cGVyaW9kPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+JiN4ZDsKICAgICAgICAgICAgICAgIG9yIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEwNSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTAyIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMDEiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPlA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LjwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEwMiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+b25zZXREYXRlVGltZTwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IGluIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEwNCI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTAzIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5FPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMDQiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnBlcmlvZDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICA8L2E6cz4KICAgICAgICAgPC9hbm5vdGF0aW9uPgogICAgICAgICA8ZXhwcmVzc2lvbiBsb2NhbElkPSIxMDgiIGxvY2F0b3I9Ijc4OjUtODE6NDYiIHhzaTp0eXBlPSJOdWxsIi8+CiAgICAgIDwvZGVmPgogICAgICA8ZGVmIGxvY2FsSWQ9IjEzNCIgbG9jYXRvcj0iODM6MS04Nzo5NCIgbmFtZT0iU3RyZXBUZXN0RW5jb3VudGVycyIgY29udGV4dD0iUGF0aWVudCIgYWNjZXNzTGV2ZWw9IlB1YmxpYyI+CiAgICAgICAgIDxhbm5vdGF0aW9uIHhzaTp0eXBlPSJhOkFubm90YXRpb24iPgogICAgICAgICAgICA8YTpzIHI9IjEzNCI+CiAgICAgICAgICAgICAgIDxhOnM+ZGVmaW5lIFN0cmVwVGVzdEVuY291bnRlcnM6JiN4ZDsKICAgIDwvYTpzPgogICAgICAgICAgICAgICA8YTpzIHI9IjEzMyI+CiAgICAgICAgICAgICAgICAgIDxhOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTExIj4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMTAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPlBoYXJ5bmdpdGlzRW5jb3VudGVyczwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IEU8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICA8YTpzPiYjeGQ7CiAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTMyIj4KICAgICAgICAgICAgICAgICAgICAgPGE6cz53aXRoIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjExMyI+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTEyIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMTIiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPltEaWFnbm9zdGljUmVwb3J0OiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4mcXVvdDtHcm91cCBBIFN0cmVwdG9jb2NjdXMgVGVzdCZxdW90OzwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5dPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gVDwvYTpzPgogICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPGE6cz4mI3hkOwogICAgICAgICAgICBzdWNoIHRoYXQgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTMxIj4KICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMTYiPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjExNSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTE0Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5UPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMTUiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnJlc3VsdDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+IGlzIG5vdCBudWxsPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiYjeGQ7CiAgICAgICAgICAgICAgICBhbmQgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTMwIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMTgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjExNyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+VDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTE4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5pc3N1ZWQ8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiBpbiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMjkiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPkludGVydmFsWzwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEyMyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTIxIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5zdGFydCBvZiA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMjAiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjExOSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+RTwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4uPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTIwIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5wZXJpb2Q8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz4gLSA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMjIiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPjMgZGF5czwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+LCA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMjgiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEyNiI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+ZW5kIG9mIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEyNSI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnMgcj0iMTI0Ij4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5FPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPi48L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cyByPSIxMjUiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPnBlcmlvZDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzPiArIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YTpzIHI9IjEyNyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhOnM+MyBkYXlzPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGE6cz5dPC9hOnM+CiAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgICAgICAgICAgIDwvYTpzPgogICAgICAgICAgICAgICA8L2E6cz4KICAgICAgICAgICAgPC9hOnM+CiAgICAgICAgIDwvYW5ub3RhdGlvbj4KICAgICAgICAgPGV4cHJlc3Npb24gbG9jYWxJZD0iMTMzIiBsb2NhdG9yPSI4NDo1LTg3Ojk0IiB4c2k6dHlwZT0iTnVsbCIvPgogICAgICA8L2RlZj4KICAgPC9zdGF0ZW1lbnRzPgo8L2xpYnJhcnk+Cg=="/>
  </content>

</Library>
