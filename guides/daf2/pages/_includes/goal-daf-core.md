# U.S. Data Access Framework (DAF) [ID] Core Profile 

 
## Scope and Usage 


 
Mandatory Data Elements
-----------------------



**Each AllergyIntolerance must have:**

1.  a patient

**Profile specific implementation guidance:*


 

### [Formal Profile Definition](daf-core-allergyintolerance.html)

**Resource Example:**

* [Example1]()

 
## Search Parameters 

The following search required search and read operations are required to conform to this profile:

`GET /[Ressource]?patient=[id]`

Support: Mandatory to support search by patient.

Implementation Notes: Search for all [] for a patient. Fetches a bundle of all AllergyIntolerance resources for the specified patient (how to search by reference).

Response Class:

* (Status 200): successful operation

* (Status 400): invalid parameter

* (Status 401/4xx): unauthorized request

* (Status 403): insufficient scope

Example:


