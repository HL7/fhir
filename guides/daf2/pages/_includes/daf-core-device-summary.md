#### Complete Summary of the Mandatory Requirements

1.  One udicarrier string in `Device.udicarrier`
    -   The Human Readable Form (HRF) representation of the barcode string as printed on the packaging of the device *SHALL* be used. The AIDC representation cannot be conveyed in FHIR, Because of limitations on character sets in XML and the need to round-trip JSON data through XML.
1.  A code in `Device.type` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -   [Device Types] (SNOMED-CT) value set.
1.  One patient reference in `Device.patient`

  [Device Types]: http://hl7-fhir.github.io/valueset-device-kind.html
