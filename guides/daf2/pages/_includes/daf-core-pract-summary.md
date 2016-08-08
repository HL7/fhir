#### Complete Summary of the Mandatory Requirements

1.  At least one (non-local) identifier in `Practitioner.identifier`
    -   NPI preferred
    -   Tax id is allowed
    -   Local id is allowed in addition to 'authoritative' identifier

1.  One name in `Practitioner.name`
1.  At least one practitioner role in `Practitioner.practitionerRole`
    which must include a
    -   `Practitioner.practitionerRole.organization`
    -   `Practitioner.practitionerRole.role` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
        - [NUCC - Classification]
    -   `Practitioner.practitionerRole.speciality` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
        - [NUCC - Specialization]
    -   `Practitioner.practitionerRole.telecom`
    -   `Practitioner.practitionerRole.endpoint`
    -   `Practitioner.practitionerRole.location`


[NUCC - Classification]: http://www.nucc.org/index.php/code-sets-mainmenu-41/provider-taxonomy-mainmenu-40/csv-mainmenu-57
[NUCC - Specialization]: http://www.nucc.org/index.php/code-sets-mainmenu-41/provider-taxonomy-mainmenu-40/csv-mainmenu-57