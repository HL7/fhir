library FHIRHelpers version '1.8'

using FHIR version '1.8'

define function ToInterval(period FHIR.Period):
    Interval[period."start".value, period."end".value]

define function ToQuantity(quantity FHIR.Quantity):
    System.Quantity { value: quantity.value.value, unit: quantity.unit.value }

define function ToInterval(range FHIR.Range):
    Interval[ToQuantity(range.low), ToQuantity(range.high)]

define function ToCode(coding FHIR.Coding):
    System.Code {
      code: coding.code.value,
      system: coding.system.value,
      version: coding.version.value,
      display: coding.display.value
    }

define function ToConcept(concept FHIR.CodeableConcept):
    System.Concept {
        codes: concept.coding C return ToCode(C),
        display: concept.text.value
    }

define function ToString(value FHIR.uuid): value.value
define function ToString(value FHIR.PlanActionPrecheckBehavior): value.value
define function ToString(value FHIR.ProvenanceEntityRole): value.value
define function ToString(value FHIR.UnitsOfTime): value.value
define function ToString(value FHIR.AddressType): value.value
define function ToString(value FHIR.AllergyIntoleranceCategory): value.value
define function ToString(value FHIR.SpecimenStatus): value.value
define function ToString(value FHIR.RestfulCapabilityMode): value.value
define function ToString(value FHIR.DetectedIssueSeverity): value.value
define function ToString(value FHIR.IssueSeverity): value.value
define function ToString(value FHIR.DataElementStringency): value.value
define function ToString(value FHIR.PlanActionConditionKind): value.value
define function ToString(value FHIR.EncounterStatus): value.value
define function ToString(value FHIR.StructureDefinitionKind): value.value
define function ToString(value FHIR.PublicationStatus): value.value
define function ToString(value FHIR.ConsentDataMeaning): value.value
define function ToString(value FHIR.QuestionnaireResponseStatus): value.value
define function ToString(value FHIR.SearchComparator): value.value
define function ToString(value FHIR.AllergyIntoleranceType): value.value
define function ToString(value FHIR.DocumentRelationshipType): value.value
define function ToString(value FHIR.AllergyIntoleranceClinicalStatus): value.value
define function ToString(value FHIR.CarePlanActivityStatus): value.value
define function ToString(value FHIR.ActionList): value.value
define function ToString(value FHIR.ParticipationStatus): value.value
define function ToString(value FHIR.PlanActionSelectionBehavior): value.value
define function ToDateTime(value FHIR.instant): value.value
define function ToDateTime(value FHIR.dateTime): value.value
define function ToDateTime(value FHIR.date): value.value
define function ToString(value FHIR.DocumentMode): value.value
define function ToString(value FHIR.AssertionOperatorType): value.value
define function ToString(value FHIR.DaysOfWeek): value.value
define function ToString(value FHIR.IssueType): value.value
define function ToString(value FHIR.ContentType): value.value
define function ToString(value FHIR.StructureMapContextType): value.value
define function ToString(value FHIR.FamilyHistoryStatus): value.value
define function ToString(value FHIR.MedicationStatementCategory): value.value
define function ToInteger(value FHIR.positiveInt): value.value
define function ToString(value FHIR.CommunicationStatus): value.value
define function ToString(value FHIR.ClinicalImpressionStatus): value.value
define function ToString(value FHIR.AssertionResponseTypes): value.value
define function ToString(value FHIR.NarrativeStatus): value.value
define function ToString(value FHIR.ReferralCategory): value.value
define function ToString(value FHIR.MeasmntPrinciple): value.value
define function ToString(value FHIR.ConsentExceptType): value.value
define function ToString(value FHIR.string): value.value
define function ToString(value FHIR.EndpointStatus): value.value
define function ToString(value FHIR.GuidePageKind): value.value
define function ToString(value FHIR.GuideDependencyType): value.value
define function ToString(value FHIR.ResourceVersionPolicy): value.value
define function ToString(value FHIR.MedicationRequestStatus): value.value
define function ToString(value FHIR.MedicationAdministrationStatus): value.value
define function ToString(value FHIR.NamingSystemIdentifierType): value.value
define function ToString(value FHIR.AccountStatus): value.value
define function ToString(value FHIR.ProcedureRequestPriority): value.value
define function ToString(value FHIR.MedicationDispenseStatus): value.value
define function ToString(value FHIR.IdentifierUse): value.value
define function ToString(value FHIR.DigitalMediaType): value.value
define function ToString(value FHIR.TestReportParticipantType): value.value
define function ToString(value FHIR.BindingStrength): value.value
define function ToString(value FHIR.ConsentStatus): value.value
define function ToString(value FHIR.ParticipantRequired): value.value
define function ToString(value FHIR.XPathUsageType): value.value
define function ToString(value FHIR.StructureMapInputMode): value.value
define function ToString(value FHIR.InstanceAvailability): value.value
define function ToString(value FHIR.id): value.value
define function ToString(value FHIR.LinkageType): value.value
define function ToString(value FHIR.ReferenceHandlingPolicy): value.value
define function ToString(value FHIR.FilterOperator): value.value
define function ToString(value FHIR.NamingSystemType): value.value
define function ToString(value FHIR.ResearchStudyStatus): value.value
define function ToString(value FHIR.ExtensionContext): value.value
define function ToString(value FHIR.AuditEventOutcome): value.value
define function ToString(value FHIR.ConstraintSeverity): value.value
define function ToString(value FHIR.EventCapabilityMode): value.value
define function ToString(value FHIR.PlanActionParticipantType): value.value
define function ToString(value FHIR.ProcedureStatus): value.value
define function ToString(value FHIR.ResearchSubjectStatus): value.value
define function ToString(value FHIR.PlanActionGroupingBehavior): value.value
define function ToString(value FHIR.CompositeMeasureScoring): value.value
define function ToString(value FHIR.DeviceMetricCategory): value.value
define function ToString(value FHIR.QuestionnaireStatus): value.value
define function ToString(value FHIR.StructureMapTransform): value.value
define function ToString(value FHIR.ResponseType): value.value
define function ToDecimal(value FHIR.decimal): value.value
define function ToString(value FHIR.AggregationMode): value.value
define function ToString(value FHIR.CapabilityStatementKind): value.value
define function ToString(value FHIR.sequenceType): value.value
define function ToString(value FHIR.AllergyIntoleranceVerificationStatus): value.value
define function ToString(value FHIR.EventTiming): value.value
define function ToString(value FHIR.GoalStatus): value.value
define function ToString(value FHIR.SearchParamType): value.value
define function ToString(value FHIR.SystemRestfulInteraction): value.value
define function ToString(value FHIR.StructureMapModelMode): value.value
define function ToString(value FHIR.TaskStatus): value.value
define function ToString(value FHIR.MeasurePopulationType): value.value
define function ToString(value FHIR.SubscriptionChannelType): value.value
define function ToString(value FHIR.ProcedureRequestStatus): value.value
define function ToString(value FHIR.ReferralStatus): value.value
define function ToString(value FHIR.AssertionDirectionType): value.value
define function ToString(value FHIR.SlicingRules): value.value
define function ToString(value FHIR.ExplanationOfBenefitStatus): value.value
define function ToString(value FHIR.LinkType): value.value
define function ToString(value FHIR.AllergyIntoleranceCriticality): value.value
define function ToString(value FHIR.ConceptMapEquivalence): value.value
define function ToString(value FHIR.PropertyRepresentation): value.value
define function ToString(value FHIR.AuditEventAction): value.value
define function ToString(value FHIR.MeasureDataUsage): value.value
define function ToString(value FHIR.TriggerType): value.value
define function ToString(value FHIR.ActivityDefinitionCategory): value.value
define function ToString(value FHIR.SearchModifierCode): value.value
define function ToString(value FHIR.CompositionStatus): value.value
define function ToString(value FHIR.AppointmentStatus): value.value
define function ToString(value FHIR.MessageSignificanceCategory): value.value
define function ToString(value FHIR.OperationParameterUse): value.value
define function ToString(value FHIR.ListMode): value.value
define function ToString(value FHIR.ObservationStatus): value.value
define function ToString(value FHIR.qualityType): value.value
define function ToString(value FHIR.AdministrativeGender): value.value
define function ToString(value FHIR.MeasureType): value.value
define function ToString(value FHIR.QuestionnaireItemType): value.value
define function ToString(value FHIR.StructureMapListMode): value.value
define function ToBoolean(value FHIR.boolean): value.value
define function ToString(value FHIR.DeviceMetricCalibrationType): value.value
define function ToString(value FHIR.code): value.value
define function ToString(value FHIR.SupplyRequestStatus): value.value
define function ToString(value FHIR.EncounterLocationStatus): value.value
define function ToString(value FHIR.SupplyDeliveryStatus): value.value
define function ToString(value FHIR.DiagnosticReportStatus): value.value
define function ToString(value FHIR.FlagStatus): value.value
define function ToString(value FHIR.AllergyIntoleranceCertainty): value.value
define function ToString(value FHIR.CarePlanStatus): value.value
define function ToString(value FHIR.ListStatus): value.value
define function Tobase64Binary(value FHIR.base64Binary): value.value
define function ToString(value FHIR.MeasureScoring): value.value
define function ToString(value FHIR.AuditEventAgentNetworkType): value.value
define function ToString(value FHIR.AddressUse): value.value
define function ToString(value FHIR.ConditionalDeleteStatus): value.value
define function ToString(value FHIR.ContactPointUse): value.value
define function ToString(value FHIR.DeviceMetricOperationalStatus): value.value
define function ToString(value FHIR.NutritionOrderStatus): value.value
define function ToString(value FHIR.uri): value.value
define function ToString(value FHIR.ContributorType): value.value
define function ToString(value FHIR.ReferenceVersionRules): value.value
define function ToString(value FHIR.Use): value.value
define function ToString(value FHIR.IdentityAssuranceLevel): value.value
define function ToString(value FHIR.MeasureReportStatus): value.value
define function ToString(value FHIR.DeviceMetricColor): value.value
define function ToString(value FHIR.SearchEntryMode): value.value
define function ToTime(value FHIR.time): value.value
define function ToString(value FHIR.ConditionalReadStatus): value.value
define function ToString(value FHIR.ConditionVerificationStatus): value.value
define function ToString(value FHIR.AllergyIntoleranceSeverity): value.value
define function ToString(value FHIR.OperationKind): value.value
define function ToString(value FHIR.ObservationRelationshipType): value.value
define function ToInteger(value FHIR.unsignedInt): value.value
define function ToString(value FHIR.NameUse): value.value
define function ToString(value FHIR.SubscriptionStatus): value.value
define function ToString(value FHIR.CommunicationRequestStatus): value.value
define function ToString(value FHIR.DocumentReferenceStatus): value.value
define function ToString(value FHIR.LocationMode): value.value
define function ToInteger(value FHIR.integer): value.value
define function ToString(value FHIR.repositoryType): value.value
define function ToString(value FHIR.CarePlanRelationship): value.value
define function ToString(value FHIR.LocationStatus): value.value
define function ToString(value FHIR.UnknownContentCode): value.value
define function ToString(value FHIR.NoteType): value.value
define function ToString(value FHIR.TestReportStatus): value.value
define function ToString(value FHIR.HTTPVerb): value.value
define function ToString(value FHIR.CodeSystemContentMode): value.value
define function ToString(value FHIR.PlanActionRelationshipType): value.value
define function ToString(value FHIR.EpisodeOfCareStatus): value.value
define function ToString(value FHIR.RemittanceOutcome): value.value
define function ToString(value FHIR.ContactPointSystem): value.value
define function ToString(value FHIR.SlotStatus): value.value
define function ToString(value FHIR.PropertyType): value.value
define function ToString(value FHIR.markdown): value.value
define function ToString(value FHIR.TypeDerivationRule): value.value
define function ToString(value FHIR.MedicationStatementStatus): value.value
define function ToString(value FHIR.GuidanceResponseStatus): value.value
define function ToString(value FHIR.QuantityComparator): value.value
define function ToString(value FHIR.RelatedArtifactType): value.value
define function ToString(value FHIR.oid): value.value
define function ToString(value FHIR.DeviceStatus): value.value
define function ToString(value FHIR.TestReportResultCodes): value.value
define function ToString(value FHIR.MeasureReportType): value.value
define function ToString(value FHIR.SampledDataDataType): value.value
define function ToString(value FHIR.CompartmentType): value.value
define function ToString(value FHIR.CompositionAttestationMode): value.value
define function ToString(value FHIR.PlanActionRequiredBehavior): value.value
define function ToString(value FHIR.DeviceMetricCalibrationState): value.value
define function ToString(value FHIR.GroupType): value.value
define function ToString(value FHIR.TypeRestfulInteraction): value.value
define function ToString(value FHIR.PlanActionCardinalityBehavior): value.value
define function ToString(value FHIR.CodeSystemHierarchyMeaning): value.value
define function ToString(value FHIR.MedicationStatementNotTaken): value.value
define function ToString(value FHIR.BundleType): value.value
define function ToString(value FHIR.SystemVersionProcessingMode): value.value
