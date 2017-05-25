library SuicideRiskLogic

using FHIR

include FHIRHelpers called FHIRHelpers

codesystem "NPI Taxonomy": 'http://nucc.org/provider-taxonomy'

valueset "Suicide Risk Assessment": 'urn:oid:1.2.3.4.5' // Value set oid for suicide risk assessments

parameter Patient Patient
parameter Encounter Encounter
parameter Practitioner Practitioner

context Patient

define ProcedureRequestFulfillmentTime: Interval[Now(), null]

define RiskAssessmentScore:
	// Sample calculation only, this logic is incomplete
    case
        when not(RequiresBriefRiskAssessment)
            then 'No Risk'
        when RequiredQuestionsNull
            then null
        when IdeationRisk = 'High'
            or IntentionRisk = 'High'
            or AttemptRisk = 'High'
            or PreparationRisk = 'High'
            then 'High'
        when IdeationRisk = 'Low'
            and IntentionRisk = 'Low'
            and ImpulseControlRisk = 'Low'
            and AttemptRisk = 'Low'
            and PreparationRisk = 'Low'
            then 'Low'
        when IdeationRisk is null
            or IntentionRisk is null
            or ImpulseControlRisk is null
            or AttemptRisk is null
            or PreparationRisk is null
            then 'Undetermined'
        else 'Intermediate'
    end

define RiskAssessment:
	[RiskAssessment: "Suicide Risk Assessment"] R
		where R.context.reference.endsWith(Encounter.id)
