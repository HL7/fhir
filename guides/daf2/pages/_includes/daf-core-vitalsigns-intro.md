This profile sets minimum expectations for the Observation resource to record, search and fetch vital signs associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Observation must have:**

-   a patient
-   a time indicating when the measurement was taken
-   a LOINC code which tells you what is being measured and is taken from the “LOINC Code” column in the table below.
    -   note: If a more specific code is recorded, the generic code and the translated coded must be sent - e.g. method specific LOINC Codes, SNOMED CT concepts, system specific codes
-   a numeric result value and standard UCUM unit which is taken from the “LOINC Code” column in the table below.
    -   note: if there is no numeric result then you have to supply a reason
-   a status
-   a category code of 'vital-signs'. 

**Profile specific implementation guidance:**

* This table represents a minimum set of vital sign concepts, the required LOINC codes ([Argonaut Vital Signs]), and UCUM units of measure codes ([Argonaut Vital Signs Units]) used for representing vitals signs observations. These are [extensible bindings] defined for this IG and require that when a system support of any of these vital signs concepts, they must represent them using these codes. In addition, if you have a blood pressure observation, you must have both a systolic and a diastolic component, though one or both may have dataAbsentReason instead of a value.

---

Vital Sign Name | LOINC&nbsp;Code | UCUM Unit Code  | Notes  | Examples
--- | --- | --- | --- | ---
Vital Signs | 8716-3 | — | This is the top-level grouping structure for a set of vital signs.  It has no value in Observation.valueQuantity ; instead, it just includes related links (with type=has-member) to the Observations in this set (e.g. respiratory rate, heart rate, BP).  Note that querying for the panel may miss individual results that are not part of an actual panel. |[Vital Signs Panel Example](todo.html)
Respiratory Rate | 9279-1 |/min | |[Vital Signs Respiratory Rate Example](todo.html)
Heart rate | 8867-4 | /min | |[Vital Signs Heart Rate Example](todo.html)
Oxygen saturation | 59408-5  | % | http://s.details.loinc.org/LOINC/2710-2.html LOINC has deprecated 2710-2 and replaced it with 59408-5 (Oxygen saturation in Arterial blood by Pulse oximetry).  The previous term (2710-2) listed in C-CDA. See [O2 Saturation by Pulse Ox LOINCS](todo.html)  for a list of LOINCs that can be translations to this LOINC|[Vital Signs Oxygen Saturation Example](todo.html)
Body temperature | 8310-5 | Cel, [degF] | |[Vital Signs Body Temperature Example](todo.html)
Body height | 8302-2 | cm, [in_i] | |[Vital Signs Body height Example](todo.html)
Body length | 8306-3 | cm, [in_i] | Like height, but lying down, typically this is used for infants |[Vital Signs Body Length Example](todo.html)
Head circumference | 8287-5 | cm, [in_i]||[Vital Signs Head Cirmcumference Example](todo.html)
Body weight | 29463-7 | g, kg,[lb_av]||[Vital Signs Body Weight Example](todo.html)
Body mass index | 39156-5 | kg/m2 ||[Vital  Body Mass Example](todo.html)

---

* Alternate codes may be provided in addition to the standard LOINC and UCUM codes defined here. An example of [using multiple codes] is provided. Other profiles may make rules about which vital sign must be present or must be present as part of a panel.

* Additional elements from [DAF Observation Profile](daf-Observation.html) may be present.
