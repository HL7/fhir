library ChlamydiaScreening_Common version '2'

using QUICK

valueset "Female Administrative Sex": '2.16.840.1.113883.3.560.100.2'
valueset "Other Female Reproductive Conditions": '2.16.840.1.113883.3.464.1003.111.12.1006'
valueset "Genital Herpes": '2.16.840.1.113883.3.464.1003.110.12.1049'
valueset "Genococcal Infections and Venereal Diseases": '2.16.840.1.113883.3.464.1003.112.12.1001'
valueset "Inflammatory Diseases of Female Reproductive Organs": '2.16.840.1.113883.3.464.1003.112.12.1004'
valueset "Chlamydia": '2.16.840.1.113883.3.464.1003.112.12.1003'
valueset "HIV": '2.16.840.1.113883.3.464.1003.120.12.1003'
valueset "Syphilis": '2.16.840.1.113883.3.464.1003.112.12.1002'
valueset "Complications of Pregnancy, Childbirth and the Puerperium": '2.16.840.1.113883.3.464.1003.111.12.1012'
valueset "Pregnancy Test": '2.16.840.1.113883.3.464.1003.111.12.1011'
valueset "Pap Test": '2.16.840.1.113883.3.464.1003.108.12.1017'
valueset "Lab Tests During Pregnancy": '2.16.840.1.113883.3.464.1003.111.12.1007'
valueset "Lab Tests for Sexually Transmitted Infections": '2.16.840.1.113883.3.464.1003.110.12.1051'
valueset "Chlamydia Screening": '2.16.840.1.113883.3.464.1003.110.12.1052'

context Patient

define "Conditions Indicating Sexual Activity":
	["Condition": "Other Female Reproductive Conditions"]
	union ["Condition": "Genital Herpes"]
    union ["Condition": "Genococcal Infections and Venereal Diseases"]
    union ["Condition": "Inflammatory Diseases of Female Reproductive Organs"]
    union ["Condition": "Chlamydia"]
    union ["Condition": "HIV"]
    union ["Condition": "Syphilis"]
    union ["Condition": "Complications of Pregnancy, Childbirth and the Puerperium"]

define "Laboratory Tests Indicating Sexual Activity":
    ["DiagnosticOrder": "Pregnancy Test"]
    union ["DiagnosticOrder": "Pap Test"]
    union ["DiagnosticOrder": "Lab Tests During Pregnancy"]
	union ["DiagnosticOrder": "Lab Tests for Sexually Transmitted Infections"]

define "Tests Performed For Chlamydia":
	["DiagnosticReport": "Chlamydia Screening"]

define "Results Present For Chlamydia Screening":
    ["DiagnosticReport": "Chlamydia Screening"] R where R."result" is not null
