#### Complete Summary of the Mandatory Requirements

1. A `Location.status' which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
   -   [LocationStatus]  value set.
1. A `Location.name`
1. A `Location.telecom`
1. A `Location.address`
1. A `Location.managingOrganization`

Future recommended elements:

1. A `Location.type` 
    -   The Location.type which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -   [???] value set

1. At least one identifier in `Location.identifier` which *SHOULD* come from:
    -   NPI preferred
    -   Tax id is allowed
    -   Local id is allowed in addition to 'authoritative' identifier

[???]: (todo.html)
[LocationStatus]: http://hl7-fhir.github.io/valueset-location-status.html

