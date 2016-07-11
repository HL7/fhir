#### Complete Summary of the Mandatory Requirements 

1.  One Identification of a substance, or a class of substances, that is considered to be responsible for the adverse reaction risk in **AllergyIntolerance.substance**
    -   AllergyIntolerance.substance with an [ extensible] binding to a Value set (Code set) consisting of:
        -   NDF-RT codes for drug class allergies
        -   RxNorm codes limited to term types (TTY) , 'BN' Brand Name, 'IN' ingredient, 'MIN' multiple ingredient, and 'PIN' precise ingredient for drug ingredient allergies
        -   SNOMED CT if no other code from above code systems are available.

2.  One patient reference in **AllergyIntolerance.patient**
3.  One status in **AllergyIntolerance.status**
    -   AllergyIntolerance.status is bound to **[AllergyIntoleranceStatus]** Value set (Code set)

  [ extensible]: Implementation_Guide#Extensible_binding_for_CodeableConcept_Datatype "wikilink"
  [AllergyIntoleranceStatus]: http://hl7.org/fhir/DSTU2/valueset-allergy-intolerance-status.html