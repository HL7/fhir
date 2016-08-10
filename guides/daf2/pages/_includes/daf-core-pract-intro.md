This profile sets minimum expectations for the [Practitioner] resource to record [content] associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile. 

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-Practitioner profile:

-   Query for an practitioner by name or specialty
-   Query for a practitioner within a city or state
-   Query for a practitiioner by orgnaizational relationships


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Practitioner must have:**

1.  An identifier (NPI preferred) 
1.  A name
1.  An associated role and organization
1.  A list of qualifications

**Note to Balloters:**

1. Is Practioner.role sufficient or is the new resource PractitionerRole required?
1. Should all Practitioners require an endpoint reference?

**Profile specific implementation guidance:**
 
* none

[Practitioner]: http://hl7-fhir.github.io/Practitioner.html
