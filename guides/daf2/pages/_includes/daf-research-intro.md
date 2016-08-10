# Background

The nation is reaching a critical mass of HealthIT systems (EHRs, Data Warehouses etc) that comply with data and vocabulary standards. The wide deployment of HealthIT systems has created unique opportunities for providers, provider support teams, healthcare professionals and organizations etc. to access and use the patient data that is already collected during clinical workflows. [DAF] initiative has developed multiple implementation guides to enable and access the structured patient data collected as part of existing clinical workflows. 

[DAF-Core] IG focuses on specifying standard Application Programming Interfaces (APIs) for accessing data about individual patients for treatment and payment purposes. The [Argonaut] project has been invaluable in providing feedback on implementing FHIR in the real-world and the use of DAF profiles that were balloted as part of DSTU2. This feedback is now incorporated into DAF-Core and the profiles that were previously balloted are now updated based on the Argonaut feedback. 

[DAF-Research] IG on the other hand will focus on enabling researchers to access data from multiple organizations in the context of Learning Health System (LHS). The capabilities created as part of [DAF-Research] are intended to be leveraged to build our nation's data infrastructure for a Learning Health System. [DAF-Research] IG will leverage work that has been completed and tested as part of the [DAF-Core] IG.

# DAF applicability to a Learning Health System (LHS)

The Institute of Medicine (IOM) defines a Learning Health System as “one that is designed to generate and apply the best evidence for the collaborative healthcare choices of each patient and provider; to drive the process of discovery as a natural outgrowth of patient care; and to ensure innovation, quality, safety, and value in health care.” IOM has conducted a series of workshops on Learning Health System and published a number of resources which are listed below and will provide additional context for readers.

* [http://iom.nationalacademies.org/~/media/Files/Activity%20Files/Quality/VSRT/Core%20Documents/LearningHealthSystem.pdf](http://iom.nationalacademies.org/~/media/Files/Activity%20Files/Quality/VSRT/Core%20Documents/LearningHealthSystem.pdf)
* [http://iom.nationalacademies.org/reports/2011/digital-infrastructure-for-a-learning-health-system.aspx](http://iom.nationalacademies.org/reports/2011/digital-infrastructure-for-a-learning-health-system.aspx)
* [http://iom.nationalacademies.org/reports/2011/engineering-a-learning-healthcare-system.aspx](http://iom.nationalacademies.org/reports/2011/engineering-a-learning-healthcare-system.aspx)
* [http://iom.edu/Reports/2011/Clinical-Data-as-the-Basic-Staple-for-Health-Learning.aspx](http://iom.edu/Reports/2011/Clinical-Data-as-the-Basic-Staple-for-Health-Learning.aspx)
* [http://nam.edu/perspectives-2013-making-the-case-for-continuous-learning-from-routinely-collected-data/](http://nam.edu/perspectives-2013-making-the-case-for-continuous-learning-from-routinely-collected-data/)

Fundamental to the LHS vision is a data infrastructure that allows definition, collection, access and use of health data from one or more data sources with the appropriate privacy and security safeguards. DAF aims to provide some of the building blocks necessary to build the LHS data infrastructure.

# Relationship between Patient Centered Outcome Research Institute (PCORI), PCORNet and DAF

[PCORI] is an independent non profit, non governmental organization authorized by Congress in 2010 to improve the quality and relevance of evidence available to help patients, caregivers, clinicians, employers, insurers and policy makers make informed health decisions. Specifically, PCORI funds comparative clinical effectiveness research or CER as well as support work that will improve the methods used to conduct such studies. PCORnet, the National Patient-Centered Clinical Research Network is an innovative initiative of PCORI. [PCORnet] will transform clinical research by engaging patients, care providers and health systems in collaborative partnerships that leverage health data to advance medical knowledge and improve healthcare. [PCORnet] will bring together health research and healthcare delivery, which have been largely separate endeavors. By doing so, this national health data network will allows us to explore the questions about conditions, care and outcomes that matter most to patients and their families. PCORnet integrates health data for studies and catalyzes research partnerships among two types of networks: Clinical Data Research Networks (CDRNs), which are based in healthcare systems such as hospitals and health centers, and Patient-Powered Research Networks (PPRNs), which are run by groups of patients and their partners who are focused on one or more specific conditions or communities, and who are interested in sharing health information and participating in research. Their efforts are supported by a Coordinating Center.

Collaboratively with PCORI and PCORnet the role of DAF is to identify capabilities (standards, tools, and policies) that can enable PCORI and PCORnet to implement their vision at a national scale. DAF is piloting the identified capabilities in the [DAF-Research] IG within the PCORnet environment before finalizing the capabilities as building blocks for a national data infrastructure.

# DAF-Research IG Capabilities and Actors

The DAF-Research IG has identified the following actors based on the PCORnet abstract reference model described further below.

Data Source: Data Source is a Health IT system that collects data as part of healthcare workflows. The data collected in these workflows can then accessed or extracted for treatment, operations or research purposes.
Examples of Data Sources include EMRs, Claims Data Repositories,  etc.

Data Mart: A Health IT system that gathers data from one or more Data Sources and enables researchers to access data. A Data Source can also be a Data Mart. 
Examples of Data Marts include Data Warehouses, Registries etc.

Research Query Requester (also known as Research Query Composer): A Health IT system that enables Researchers to compose queries and submit queries to one or more Data Marts. 
Examples of Research Query Composers include web portals.

Research Query Responder: A Health IT system that provides responses to queries submitted by the Research Query Requester.  
Examples of Research Query Responders include software that translates queries to local dialects if required, runs the queries on databases and returns the results back to the requester.

The interaction between these actors is shown in the PCORnet abstract model below
![Alt text](assets/images/PCORnet Abstract Model.png "PCORnet Abstract Model")

The following are some real-world example implementations of the actors in the PCORnet environment currently.

* Data Sources: EMRs from vendors such as Cerner, Epic, e-ClinicalWorks etc.
* Data Marts: CDRN's hosting databases using PCORnet Common Data Model (CDM)
* Research Query Composer: PopMedNet Portal.
Research Query Responder: PopMedNet Data Mart Client. 

Although these actors exist in the PCORnet environment, the DAF-Research IG defines the following capabilities which can add value and improve the data infrastructure for the network and beyond.
Each of these capabilities have been identified based on discussions with PCORnet pilot sites and with participants from PCORnet community.

![Alt text](assets/images/DAF-Research Capabilities.png "DAF-Research Capabilities, Benefits and Actors")

In addition to the above C1 through C4 capabilities, C5 and C6 have been identified as part of the future work, but have been delayed waiting to learn from the pilot implementations of C1 through C4.

* C5 capability deals with standardizing the query result structure and semantics for patient level data to be returned to researchers. This will reuse in large part the capabilities already developed by DAF-Core.
* C6 capability deals with standardizing the query semantics (query language) for research based queries. This is an area which is evolving and is not mature enough for us to standardize at this point.

 




[DAF-Core]: daf-core.html
[DAF-Research]: daf-research.html
[Office of the National Coordinator (ONC)]: http://www.healthit.gov/newsroom/about-onc 
[ONC]: http://www.healthit.gov/newsroom/about-onc
[Data Access Framework]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[DAF]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[PCORI]:  http://www.pcori.org
[PCORnet]: http://www.pcornet.org/
[Argonaut]: http://argonautwiki.hl7.org/index.php?title=Main_Page* 