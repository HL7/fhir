This profile sets minimum expectations for the [Organization] resource to searching for and fetching a Organization associated with a patient or provider. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-Organization profile:

-   Query for an address or telephone number by organization name
-   Query for an organization by name or address


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Organization must have:**

1.  A name
1.  An identifier
1.  A status of the organization
1.  A list of contact information
1.  Endpoint information
 

**Profile specific implementation guidance:**

* none

 **Note to Balloters:  the following elements are being considered for inclusion into this profile:**

1.  An Organization type



[Organization]: http://hl7-fhir.github.io/organization.html