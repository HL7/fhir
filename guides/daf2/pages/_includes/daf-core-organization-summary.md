#### Complete Summary of the Mandatory Requirements

1.  At least one identifier in `Organization.identifier`
    -   NPI preferred
    -   Tax id is allowed
    -   Local id is allowed in addition to 'authoritative' identifier

1.  One status in `Organization.active`
1.  At least one name in `Organization.name` 
1.  At least one  contact in `Organization.telecom`
1.  At least one address in `Organization.address`

(Future recommended elements: At least one type in `Organization.type` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:  [???](todo.html)