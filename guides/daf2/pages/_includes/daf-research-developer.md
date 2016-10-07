# Implementation Guidance for DAF-Research IG

[DAF-Research] IG describes four capabilities [C1, C2, C3, C4], each one of which is intended to help improve the data infrastructure for PCORnet and in the larger context a Learning Health System.
This part of the IG provides additional guidance for the implementation of each of the four capabilities. This section is not normative and is only intended to provide guidance to implementers.


## Capability C1: Extracting data from a Data Source and population of a Data Mart

Implementing C1 capability involves four steps. 

1. Instantiation of a Task for Extraction at the Data Source
2. Execution of the Task to extract data from the Data Source
3. Instantiation of a Task for Loading of data at the Data Mart
4. Execution of the Task to load data into the Data Mart

Note: Data Source actor can be implemented by system like an EMR natively or alternately it can be implemented as a layer (additional software module) on top of an EMR system.
In either case the implementation has to meet the [Data Source Conformance] requirements.
The next few paragraphs will provide details for each step above.


### C1:Step 1: Instantiation of a Task for Extraction at the Data Source

The Data Source actor has to support the creation of a [DAF-Task] resource instance. This can be achieved using a FHIR API using the POST operation or using a graphical user interface which allows 
an end user to create the Task instance. This task instance has to have the following data

* Task.status - Should be set to "Ready" state since this would only be created after appropriate approvals are in place.
* Task.code - Should be populated with the daf-extract-operation
* Task.fulfillment - Should be setup according to the frequency at which this is expected to run
* Task.input.type - dateTime (This corresponds to the timereference parameter in the daf-extract-operation)
* Task.input.valuedateTime - Value from when the extract has to be performed. (For e.g 20160812050000)
* Task.input.type - Reference (This corresponds to the patient parameter in the daf-extract-operation)
* Task.input.valueReference - Patient for whom data has to be extracted. (For e.g Patient/134234, This is an identifier/reference that is known to the DataSource implementing the FHIR API).

This Task instance would then be persisted for execution. The actual execution of the task can be controlled using a scheduled timer or a manual kick off.
Note: If this is a task that is set up to repeat at a regular frequency, this step can be skipped after the first time.


### C1:Step 2: Execution of the Task to extract data from the Data Source

The Task created earlier is Step 1 is executed at some point of time automatically or manually and the following actions are expected to happen.

* Create a new [DAF-Task] instance for each execution of the task. For example, if the task is run nightly, a new task instance is created every night when the task runs.
* Populate the new Task with the same details as the Task created in Step 1. 
* Set the parent for the task instance created in this step to be Task from Step 1. 
* Set the Task.status to In-progress.
* Start extraction of data which can be accomplished in multiple ways, some of which are elaborated below based on likelihood of support from vendors.

1. Extract data for each patient using the 2015 Edition CCDS APIs that are supported by vendors which are outlined in the [DAF-Core] IG. For each patient, the extraction program may have to invoke multiple APIs to construct the full patient record.
2. Extract data for each patient using the Patient/$everything operation if the vendor system supports it.
3. Extract data for each patient using a native query to the database if allowed. 

* Add the data for each patient to a Bundle which contains all the data along with the linkages.
* Set Task.output.type - Reference. (This is the bundle that contains the data for a desired number of patients)
* Populate Task.output.valueReference (This should point to the Bundle reference created).
* Set Task.status as completed if everything completes normally.
* In case of exception, Set Task.status as Failed and Set Task.output.type as Reference but Task.output.valueReference will point to the OperationOutcome resource instance.

#### Guidance on the profiles to be used to map FHIR Resources to PCORnet Common Data Model

The PCORnet CDM is a consensus artifact that has been adopted by PCORnet as a model for Data Marts which can then be queried by Researchers. Since this is a different data model than FHIR the following guidance can be used to extract data so that PCORnet CDM can be appropriately populated. 
However data extraction programs have to be aware that vendors may be supporting just DAF-Core or a subset of DAF-Core for their initial implementation and hence may not have all the PCORnet CDM data elements available.

As one can see there are a few new resources that would be proposed and created for an effective mapping of PCORnet CDM to FHIR and vice versa. These New Resources will be proposed to the appropriate HL7 WGs based on pilot implementations and feedback. Similarly extensions required will be proposed and added to the profiles after pilot implementations are completed. Profiles which are not DAF-Core are annotated accordingly in the table below.


|PCORnet CDM Table Name            |Recommended Profile for Data Extraction|
|----------------------------------|----------------------------------------|
|DIAGNOSIS, CONDITION|[Condition](daf-condition.html)*|
|LAB_RESULT_CM|[DiagnosticReport-Results](daf-core-diagnosticreport.html)|
|ENCOUNTER|[Encounter](daf-encounter.html)*|
|Prescribing|[MedicationOrder](daf-core-medicationorder.html)|
|DISPENSING|[MedicationDispense](daf-medicationdispense.html)*|
|LAB_RESULT_CM|[Observation](daf-core-resultobs.html)|
|VITALS|[Observation-Vitalsigns](daf-core-vitalsigns.html)|
|DEMOGRAPHIC|[Patient](daf-patient.html)*|
|PROCEDURES|[Procedure](daf-core-procedure.html)|
|PRO CM|Questionaire Profile - TBD|
|ENROLLMENT|Potential New Resource - TBD|
|PCORNET_TRIAL|Potential New Resource - TBD|
|DEATH|Potential New Resource/Profile - TBD|
|DEATH_CAUSE|Potential New Resource/Profile - TBD|
|HARVEST|New Resource - TBD|

* Indicate DAF-Research specific profiles which are created from DAF-Core profiles.

#### Guidance on the profiles to be used to map FHIR Resources to OMOP

Some PCORnet sites are using OMOP model as a source or destination and hence a mapping from FHIR to OMOP would be useful for these sites. The following is a mapping that was developed by the DAF pilot sites and can be a starting point for the implementation of C1 capability. Profiles which are not DAF-Core are annotated accordingly in the table below.

|OMOP Table Name            |Recommended Profile for Data Extraction|
|----------------------------------|----------------------------------------|
|Concept,Vocabulary,Domain,Concept_Synonym,Concept_Ancestor|ValueSet **|
|Concept_Class|Concept **|
|Concept_Relationship, Relationship|ConceptMap **|
|Cohort_Definition, Attribute_Definition|Group **|
|Specimen|Specimen **|
|Drug_Strength|[Medication](daf-core-medication.html)|
|Procedure_Occurence|[Procedure](daf-core-procedure.html)|
|Drug_Exposure|[MedicationOrder](daf-core-medicationorder.html),[MedicationStatement](daf-core-medicationstatement.html),[Immunization](daf-core-immunization.html)|
|Device_Exposure|[Procedure](daf-core-procedure.html),[Device](daf-core-device.html)|
|Measurement,Note,Observation|[Observation](daf-core-resultobs.html)|
|Person|[Patient](daf-patient.html)*|
|Observation_Period, Visit_Occurence|[Encounter](daf-encounter.html)*|
|Condition_Occurence|[Condition](daf-condition.html)*|

** Base FHIR Resources without any specific profiles, * DAF Research specific profiles

### C1: Step 3: Instantiation of a Task for Loading of data at the Data Mart

The Data Mart actor has to support the creation of a [DAF-Task] resource instance. This can be achieved using a FHIR API using the POST operation or using a graphical user interface which allows 
an end user to create the Task instance. This task instance has to have the following data

* Task.status - Should be set to "Ready" state since this would only be created after appropriate approvals are in place.
* Task.code - Should be populated with the daf-load-operation
* Task.fulfillment - Should be setup according to the frequency at which this is expected to run
* Task.input.type - Reference 
* Task.input.valueReference - Points to the Bundle that was extracted in Step 2.

This Task instance would then be persisted for execution. The actual execution of the task can be controlled using a scheduled timer or a manual kick off.
Note: If this is a task that is set up to repeat at a regular frequency, this step can be skipped after the first time.


### C1:Step 4: Population of the Data Mart with the extracted data 

#### Pre-Processing the Bundle returned from the extract operation 

A Bundle returned from Step 2 will conform to FHIR and DAF-Core or other specific IG requirements. This Bundle may have to go through additional transformations, mappings and other processing before 
it is loaded into a destination Data Mart. One of these processing steps is "De-Identifying the data".

##### De-Identification of data 

It is expected that most vendors supporting the ONC 2015 Edition CCDS API's or the Patient/$everything operation would be returning identifiable patient information as part of the API.
Since PCORnet required de-identified data the de-identification has to be performed subsequently. Implementations can choose internally approved mechanisms for de-identifying the data and populating the PCORnet CDM.

##### Mapping to be used

One of the value propositions of the data extract standardization is the need to eliminate mappings from each Data Source. As long as a Data Source has performed the right mapping to its FHIR Resources and profiles, 
the extracted data can be directly mapped to a destination model of choice such as the PCORnet CDM. The following is a mapping of FHIR to PCORnet CDM developed by DAF working with PCORnet community and data experts.
This mapping can be followed to load the appropriate tables within the PCORnet CDM.

[PCORnet CDM to FHIR mapping](https://docs.google.com/spreadsheets/d/1Gw-j7GSlDA0rxJqpSRI6g9ZPRk7LHPnE5-AJuWd1ry0/edit#gid=1928349566)

For systems loading to from OMOP to FHIR the following mapping developed by DAF pilots can be used.

[OMOP to FHIR mapping](https://docs.google.com/spreadsheets/d/11ZmwGxnXViLkTVdX5Vi0FP-Gh4AD2HZEfYOhzZptZfw/edit#gid=0)

Using the above mapping the task to load the data would be executed as follows. 

* Create a new [DAF-Task] instance for each execution of the task. For example, if the task is run nightly, a new task instance is created every night when the task runs.
* Populate the new Task with the same details as the Task created in Step 3. 
* Set the parent for the task instance created in this step to be Task from Step 3. 
* Set the Task.status to In-progress. Set the parent Task.status to In-progress.
* Start loading of data for each patient into the database using the PCORnet CDM to FHIR mapping provided.
* Create [DAF-Provenance] instance for each run of the task pointing back to all the Resources that were updated in this execution.
* Update [DAF-Conformance] as needed to reflect the latest data changes.
* Set Task.status as completed if everything completes normally. The parent Task will remain In-progress for ever until the parent Task is Completed or Rejected. 
* In case of exception, Set Task.status as Failed and Set Task.output.type as Reference but Task.output.valueReference will point to the OperationOutcome resource instance.



## C2: Publishing Data Mart Meta data

Implementing C2 capability involves three steps. 

1. Instantiation of the Conformance profile
2. Population and Updation of the Conformance profile
3. Making the Conformance profile available to Researchers

The next few paragraphs will provide details for each step above.


### C2:Step 1: Instantiation of the Conformance profile

The Data Mart has to instantiate a Conformance Resource instance to declare its characteristics that would help a Researcher to compose queries. 
In addition the Conformance Resource should also help a Data Mart administrator to manage the data within the Data Mart. 
The Conformance Resource declares the various profiles, operations and other specifics about the implementation. 
For the DAF Data Mart actor the following data is expected to be present within the DAF-Conformance resource instance.

* Conformance.url provides the unique identifier for the Resource. The URL is a web-accessible address to determine the server capabilities.
* Conformance.version - Populate with a timestamp to indicate when it was created.
* Conformance.status - This will always be "active" for implementations.
* Conformance.date - Update this whenever the resource instance changes.
* Conformance.publisher - Organization publishing this conformance instance.
* Conformance.kind - This will always be "instance" for implementations.
* Conformance.implementation.url - Populate with the same value as the Conformance.url
* Conformance.implementation.description - Populate with the same description as Conformance.description
* Conformance.fhirVersion - Populate with "STU3".
* Conformance.format - Populate two entries (one with "xml" and other with "json")
* Conformance.profile - Declare the list of profiles supported by the instantiation. For DAF Data Mart this would be DAF-Conformance, DAF-Task, DAF-Provenance, DAF-OperationDefinition at a minimum.
* Conformance.rest.mode - Populate with "Server" 

* For each Resource supported by the Server populate the Conformance.rest segment with 

1. Resource Type (e.g Task)
2. Resource Profile (DAF specific profiles, e.g DAF-Task)
3. Search Param Name for each search parameter supported
4. Interaction.code (Multiple interactions would be supported typically, for e.g READ, SEARCH, vREAD, POST )
5. Operation.name for each operation supported by the Data Mart (These include daf-load-operation, daf-execute-query etc)
6. Operation.definition points back to the instance of the Operation Definitions that the Server supports. 

For each Operation that is supported by the Server, a DAF-OperationDefinition instance should be created with the appropriate data 
and then the Conformance.Operation.defintion should point to the instance that has been created. 

The following extensions should be populated for the Conformance resource instance
*  PCORnet Data Mart Active Flag - This indicates if the Data Mart is still active and is accepting queries.

#### Guidance for instantiating a DAF-OperationDefintion

The DAF-OperationDefinition profile has been created to help servers declare conformance to the various DAF-Research operations.
In order to declare support for various operations, an implementation would create an instance of DAF-OperationDefinition and then point to it by the Conformance.rest.operation part of the Conformance resource.

The following data elements are expected to be populated for each DAF-OperationDefinition that is instantiated.

* URL - Point to the Operation Definition.
* kind - This would be "operation" for DAF-Research
* code - This would be one of "daf-extract-operation", "daf-load-operation", "daf-execute-query".
* system - This is always "true" since all the DAF operations are executed on the Root URL of the system.
* parameters - Would be populated with the parameters for the operation as defined in the URL

The following Extensions have to be populated as part of the OperationDefinition

* Extension (daf-data-models) - Identify the list of data models supported for the operation. This is critical for the daf-execute-query operation.
* Extension (daf-query-formats) - Identify the list of query formats supported for the operation. This is critical for the daf-execute-query operation.
* Extension (daf-query-format-version) - Identify the version of the query-format that is supported by this instance. This should be part of the extension within the daf-query-formats.


### C2: Step 2. Population and Updation of the Conformance profile

The Conformance profile once published gets updated less frequently as compared to other clinical resources. 
However updates to the Conformance profile will be performed due to changes in the following data elements

* URL of the overall conformance resource
* List of Operations that are supported change
* List of Resources that are supported change
* List of research data models and query formats supported change
* List of search parameters change
* Changes to Security protocols.


### C2: Step 3. Making the Conformance profile available to Researchers

The Conformance resource just like other FHIR resources can be queried by researchers.
Conformance resources should be available for querying without requiring additional authorization. 
The Conformance resource will be published at the well known FHIR URL <base URL>/Conformance.
There can only be one instance of the Conformance resource per server implementation.
Conformance resource should support the historical version (vREAD) retrieval to identify the changes over a period of time.



## C3: Composing a Query and submitting it to multiple Data Marts

Capability C3 implementation involves two steps

1. Instantiation of a Task for executing a query
2. Submitting the query to multiple Data Marts


### C3: Step 1: Instantiation of a Task for executing a query

In PCORnet and most research environments, queries submitted to access data are asynchronous in nature, repeated frequently and may involve humans in the work flow performing approvals, rejections etc.
In order to support these requirements an instantiation of a Task is performed. In order to track the Tasks across multiple Data Marts and states the following Task hierarchy is implemented. 

A Task (this is known as the Root Task) would be created based on the query composed by the Researcher.
For each Data Mart that the query will be sent to, a new Task instance (Data Mart specific task) would be created using the data from the Root Task. The parent of the Data Mart specific Task would be Root Task.
Each Data Mart when it executes it's Task would create an instance of the Task for the execution (Execution specific Task) from the Data Mart specific Task and then populate it accordingly with the results of the execution.
This hierarchical nature would facilitate the Researcher to retrieve data specific to an execution within the Data Mart, across all Data Mart executions to date or across all the Data Marts.

This Root Task instances created will have the following data

* Task.status - Should be set to "Requested" state 
* Task.code - Should be populated with the daf-execute-query
* Task.requester - Should be populated with the requesting organization and their name. This is the Researcher's organization.
* Task.description - Description of the query
* Task.priority - Priority of the task
* Task.reason - Populate with the purposeOfUse values.

The following extensions need to be populated on the Task

* dueDate - Date by which this Task has to be executed
* queryApprovals - Have to be populated using profile of Basic Resource. This needs to be experimented before being used.

The following are the list of inputs to the daf-execute-query operation which would be populated on the Task.input data element.

* Task.input.name - queryFormat
* Task.input.type - CodeableConcept 
* Task.input.valueCodeableConcept - Value which would indicate the queryFormat such as SAS/SQL.
* Task.input.name - dataModel
* Task.input.type - CodeableConcept 
* Task.input.valueCodeableConcept - Value which would indicate the dataModel such as PCORnet CDM / i2b2 / OMOP
* Task.input.name - instructions
* Task.input.type - string 
* Task.input.valueString - Instructions for execution of the query
* Task.input.name - queryPackage
* Task.input.type - string 
* Task.input.valueString - Value which contains the actual query composed using SAS/SQL/Json etc.

Optionally the query can indicate the type of data expected as part of the results as part of the queryResultsPhiDisclosureLevel.


### C3: Step 2: Submitting the query to multiple Data Marts

In order for the Researcher to execute the query against multiple Data Marts, the Research Query Composer system has to create an instance of the Root Task created in Step 1 for 
each Data Mart. In order to make Tasks specific to a Data Mart, the following Task data elements would be set.

* Task.owner - Should be populated with the organization that owns the query. This is normally the CDRN.
* Task.status - Should be set to "Requested" state for each instance.

All the other data elements would be replicated from the root task. 
Once the Data Mart specific Tasks are created, Research Query Responders can access these tasks via the search mechanism on the Task.owner data element.



## C4: Execution of the Query, Creation of query results and returning the resulting data 

Implementing C4 capability involves the following three steps

1. Retrieving the query specific to the Data Mart
2. Executing the query and returning the query results
3. Retrieving query results from multiple Data Marts 


### C4: Step 1: Retrieving the query specific to the Data Mart

Each Research Query Responder can access the queries that it needs to execute by performing a GET on the Task where Task.owner would be itself.
This GET operation on the Task resource may cross firewall boundaries and might require appropriate authorization before the resources can be accessed. 

The Research Query Responder would then duplicate the task with all the data for the specific execution. This new Task instance would have the Data Mart specific Task as its parent.
The Research Query Responder would set the Task.status to "Received", "Accepted" and "Ready" as appropriate.


### C4: Step 2: Executing the query and returning the query results

The Research Query Responder would start the execution specific Task instance by updating the Task.status to "In-Progress".
The Research Query Responder would then translate the incoming query to native execution language based on the following parameters

* queryFormat
* queryFormatVersion
* dataModel 
* queryPackage
* instructions.

The query would then be executed and the results would be created using the DAF-QueryResults Observation profile. The data would be represented as follows

* Observation.category - Set this to the types of things being observed. It can be Patient, Encounter, Observation etc.
* Observation.code - Set this to the types of things being aggregated. It can be Patient, Encounter, Observation etc.

* For each measurement create an Observation.component entry with the following

* Observation.component.code - Set this to the actual property being measured. (For e.g Height, Weight etc)
* Observation.component.value - Set this to the aggregate value 
* Observation.component.interpretation - Set this to Count, Average etc.
* Observation.component.referenceRange - Set this to the Low and High values that are the boundaries used for value calculation.

One Observation.component should be created for each stratified data element.  
Once these results are created the Research Query Responder should create the Bundle and set the execution specific Task.output to the Bundle instance.
The Task.status should be set to "Completed".
In case of failures the data is returned as part of the OperationOutcome element.
These execution specific Task instances are now available for retrieval by the Researcher. 


### C4: Step 3: Retrieving query results from multiple Data Marts

In order for a Researcher to get a complete picture of the population based on their query submitted, the query results from multiple Data Marts have to be retrieved.
For this purpose the Research Query Composer, has to query each of the Data Marts for execution specific Task instances with the parent set to the Data Mart specific Task that was created during the initiation of the query.
Once these task instances are retrieved then the Task.output would contain the result of each query execution for each Data Mart.
These results would then be made available for the Researcher for further analysis.


[DAF-Core]: daf-core.html
[DAF-Research]: daf-research.html
[DAF-Research-intro]: daf-research-intro.html
[C1, C2, C3, C4]: daf-research-intro.html
[Data Source Conformance]: capabilitystatement-daf-datasource.html
[Data Mart Conformance]: capabilitystatement-daf-datamart.html
[Research Query Composer Conformance]: capabilitystatement-daf-datasource.html
[Research Query Responder Conformance]: capabilitystatement-daf-datasource.html
[DAF-Task]: daf-task.html
[DAF-Provenance]: daf-provenance.html
[DAF-OperationDefinition]: daf-operationdefinition.html
[DAF-Conformance]: daf-conformance.html
[DAF-QueryResults]: daf-queryresults.html
[Office of the National Coordinator (ONC)]: http://www.healthit.gov/newsroom/about-onc 
[ONC]: http://www.healthit.gov/newsroom/about-onc
[Data Access Framework]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[DAF]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[PCORI]:  http://www.pcori.org
[PCORnet]: http://www.pcornet.org/
[Argonaut]: http://argonautwiki.hl7.org/index.php?title=Main_Page* 

