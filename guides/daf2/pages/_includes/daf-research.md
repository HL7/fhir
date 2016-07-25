# Introduction

DAF-Research IG will focus on enabling researchers to access data from multiple organizations in the context of Learning Health System (LHS). While DAF-Research IG can be applied to multiple use cases, the current requirements have been drawn heavily from PCORnet use cases and implementations. The capabilities described as part of the IG are intended to be leveraged to build our nation's data infrastructure for a Learning Health System. DAF-Research will leverage the DAF-Core IG which has been created with substantial feedback from the Argonaut project. 

# Guidance to the Reader
The following table will provide a roadmap for the reader.

|Topic of Interest|What it contains|Where can I find it|
|--------------------|--------------------------|---------------------|
|DAF-Research IG Background|The artifact provides background on LHS, PCORI and PCORnet activities.|[Background](daf-research-intro.html)|
|Capabilities and Actors|The artifact defines the various capabilities and actors that make up the DAF-Research IG.|[Capabilities and Actors](daf-research-intro.html)|
|Developer Guidance|The artifact contains data mapping spreadsheets, deployment options, examples that will help implementers of DAF-Research IG.|[Implementation Guidance](daf-research-developer.html)|

# Capabilities, Actors and Conformance Requirements
The following table outlines the various DAF-Research Capabilities specified as part of the IG, the actors associated with the capability and a link to the conformance requirements for the actor.

|Capability|Actors|Conformance Requirements|
|------------------|----------------|---------------------|
|C1: Standardize data extraction mechanism from clinical data sources to populate data marts which can then be accessed by Researchers.|Data Source|[Data Source Conformance](conformance-daf-datasource.html)|
||Data Mart|[Data Mart Conformance](conformance-daf-datamart.html)|
|C2: Publish Metadata about data sources useful for Researchers to access data.|Data Mart|[Data Mart Conformance](conformance-daf-datamart.html)|
|C3: Standardize Query Distribution mechanism from Researchers to Data Marts facilitating workflows.|Research Query Requester|[Research Query Requester Conformance](conformance-daf-research-queryrequester.html)|
|C4: Standardize Aggregate Query Results return from Data Marts back to Researchers in response to a C3 query.|Research Query Responder|[Research Query Responder Conformance](conformance-daf-research-queryresponder.html)|