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


Instance: denovoChild
InstanceOf: Patient
Usage: #example
Title: "De novo mutation child"
Description: "An instance of a patient resoruce representing a de novo mutation proband."
* identifier.use = #temp
* identifier.type = http://terminology.hl7.org/CodeSystem/v2-0203#MR
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


