#### Summary of the Mandatory Requirements 

1.  One status in `AllergyIntolerance.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -   [AllergyIntoleranceStatus](http://hl7-fhir.github.io/valueset-allergy-intolerance-status.html) value set
1.  One Identification of a substance, or a class of substances, that is considered to be responsible for the adverse reaction risk in `AllergyIntolerance.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -    [DAF Core Substance-Reactant for Intolerance and Negation Codes](valueset-daf-core-substance.html) value set
1.  One patient reference in `AllergyIntolerance.patient`
  