Instance: DeNovoMutationStudy
InstanceOf: GenomicStudy //http://build.fhir.org/branches/genomicStudyResource/genomicstudy.html
Usage: #example
Title: "De Novo Mutation Study"
Description: "A genomic study resource that descriped a de novo mutation analysis of a newly born child."
* identifier.system = "http://www.somesystemabc.net/identifiers/genomicstudys"
* identifier.value = "urn:uuid:1111-1111-1111-1111"
* identifier.use = #temp
* status = http://hl7.org/fhir/genomicstudy-status#unknown 
    "Unknown"
* type = http://www.somesystemabc.net/CodeSystem#denovo 
    "De novo mutation analysis"
* subject = Reference(denovoChild)
* encounter = Reference(denovoEncounter)
* startDate = "2021-01-01"
* basedOn = Reference(genomicServiceRequest)
* referrer = Reference(practitioner01)
* interpreter = Reference(practitioner02)
* reason = http://snomed.info/sct#267431006 "Disorder of lipid metabolism (disorder)"

Instance: denovoChild
InstanceOf: Patient
Usage: #example
Title: "De novo mutation child"
Description: "An instance of a patient resoruce representing a de novo mutation proband."
* identifier.use = #temp
* identifier.type = http://terminology.hl7.org/CodeSystem/v2-0203#MR "Medical record number"
* identifier.system = "http://www.somesystemabc.net/identifiers/persons"
* identifier.value = "11111"
* identifier.period.start = "2021-01-01"
* identifier.assigner.display = "Child Hospital"
* active = true
* name[0].use = #official
* name[=].family = "Doe"
* name[=].given[0] = "Child"
* name[=].given[+] = "Junior"
* gender = #unknown
* birthDate = "2021-01-01"

Instance: denovoEncounter
InstanceOf: Encounter
Usage: #example
Title: "De novo mutation encounter"
Description: "An encounter where a de novo mutation analysis was needed."
Usage: #example
* status = #in-progress
* class.coding.system = "http://terminology.hl7.org/CodeSystem/v3-ActCode"
* class.coding.code = "IMP"
* class.coding.display = "inpatient encounter"
* subject = Reference(denovoChild)

Instance: genomicServiceRequest
InstanceOf: ServiceRequest
Usage: #example
Title: "A Request for a genomic study"
Description: "A Request for a genomic study including a de novo mutation analysis."
* identifier.type = http://terminology.hl7.org/CodeSystem/v2-0203#LACSN
* identifier.type.text = "Laboratory Accession ID"
* identifier.system = "http://www.somesystemabc.net/identifiers/serviceRequests"
* identifier.value = "111111111"
* status = #active
* intent = #plan
* code = http://snomed.info/sct#3981005 "Carrier detection, molecular genetics (procedure)"
* subject = Reference(denovoChild)
* encounter = Reference(denovoEncounter)
* reason = http://snomed.info/sct#267431006 "Disorder of lipid metabolism (disorder)"

Instance: practitioner01
InstanceOf: Practitioner
Usage: #example
Title: "Referrer Practitioner Example #1"
Description: "An instance of a practitioner resource to represent a referrer of a genomic study"
* identifier.use = #temp
* identifier.type = http://terminology.hl7.org/CodeSystem/v2-0203#PRN "Provider number"
* identifier.system = "http://www.somesystemabc.net/identifiers/persons"
* identifier.value = "11115"
* identifier.period.start = "2021-01-01"
* identifier.assigner.display = "Child Hospital"
* active = true
* name.family = "Doel"
* name.given = "John"
* name.prefix = "Dr"

Instance: practitioner02
InstanceOf: Practitioner
Usage: #example
Title: "Interpreter Practitioner Example #2"
Description: "An instance of a practitioner resource to represent a interpreter of a genomic study"
* identifier.use = #temp
* identifier.type = http://terminology.hl7.org/CodeSystem/v2-0203#PRN "Provider number"
* identifier.system = "http://www.somesystemabc.net/identifiers/persons"
* identifier.value = "11116"
* identifier.period.start = "2021-01-01"
* identifier.assigner.display = "Child Hospital"
* active = true
* name.family = "Doel"
* name.given = "Jane"
* name.prefix = "Dr"




