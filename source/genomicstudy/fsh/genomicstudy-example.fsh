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
* 

