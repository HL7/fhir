#### Complete Summary of the Mandatory Requirements

1.  One status in `CareTeam.status`
-   CareTeam.status is bound to [CareTeamStatus] value set
1.  One reference to a patient in `CareTeam.subject`
1.  One participant role for each careteam member in
    `CareTeam.participant.role`
    -  CarePlan.participant.role is bound to the [CareTeam Provider Role
Value Set] value set.
1.  Careteam members in `CareTeam.participant.member`

 [CareTeamStatus]: valueset-daf-careteam-status.html
 [CareTeam Provider Role Value Set]: valueset-daf-core-careteam-provider-roles.html