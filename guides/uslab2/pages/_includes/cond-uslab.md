---
title: USLab Condition Profile
---

USLab Condition Profile
-----------------------

Scope and Usage
===


This Condition Profile is part of the The USLabOrder and USLabReport Implementation Guides. Its scope is US Realm ambulatory care and is based upon existing regulatory requirements for Laboratories and Electronic Health Record Systems (EHR-S) for ordering clinical laboratory results. The content has been modeled after the joint HL7 and The Standards and Interoperability (S and I) Framework Laboratory Orders and Results Interface Initiatives and the HL7 Lab Order Conceptual Specification and V3 Lab Normative Standard. However, much of the content is likely to be usable outside the ambulatory space and in other jurisdictions.

***Two Condition Profiles are defined:***

 1. [USlabCond](uslab-cond.html) - Used to supply additional clinical information in the USLabOrder DiagnosticOrder profile
 1. [USLabReasonForStudy](uslab-cond.html) - Used to supply ICD-9CM/ICD-10CM diagnosis codes to support medical necessity of ordering.

Although not specified in this implementation, they may be used in other resources as well.

***Must Support***

For the purposes of this profile, all elements listed in the differential profile view are *Supported* which means that the Laboratory Order/Results Sender SHALL be capable of supplying these elements and or extensions to the Laboratory Order/Results Receiver if the data is available. For the Laboratory Order/Results Receiver *Supported* means they SHALL save/print/archive/etc. the supplied the elements and or extensions sent by the Laboratory Order/Results Sender. Both the Laboratory Receiver and Laboratory Sender MAY use the information to control their display of the information.


****Examples****


 1. [USLab Condition Profile: Lead Exposure](cond-uslab-example1.html)
 1. [USLab Reason for Study Profile: Lead Exposure](cond-uslab-example2.html)

