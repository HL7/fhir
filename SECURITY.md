# Security Policy

This repository holds the **source of the FHIR specification**. FHIR is a specification, not a software product or a running service, so please take a moment to report a security concern in the right place.

## An issue in a FHIR implementation or tool

The great majority of security issues people encounter with FHIR are issues in a particular **implementation** — a server, client, library, app, validator, or other tool — and not in the specification itself.

Report these to the project, vendor, or maintainer of that implementation, using *their* security process. Please do **not** report them here: the specification project cannot fix someone else's software, and a public report may put that software's users at risk before a fix is available. This includes the tools published by HL7 and from this GitHub organization — report against the relevant tool's own repository, not against the specification.

## A non-security problem in the specification

Ordinary errors, ambiguities, or improvement suggestions for the specification are not security issues. Please raise them through the normal FHIR change-management process (a [change request / Jira issue](https://jira.hl7.org/), or the usual [FHIR community channels](https://confluence.hl7.org/display/FHIR/Mailing+List+Instructions)) so they can be discussed and resolved in the open.

## A security issue in the specification itself

A genuine security flaw in the specification — for example, guidance that is unsafe to follow, or a weakness in a security mechanism that FHIR itself defines — is the thing that belongs here. As a specification we do not expect such issues, but if you believe you have found one:

- If it is **not sensitive** and is suitable for open discussion, raise it through the normal change-management process above, so it can be reviewed and corrected publicly.
- If it is **time-critical, or not suitable for public discussion**, report it privately using GitHub's private vulnerability reporting: open the **Security** tab of this repository and choose **"Report a vulnerability"**, or go directly to **<https://github.com/HL7/fhir/security/advisories/new>**. This creates a private advisory visible only to the maintainers.

## What to expect

A maintainer will acknowledge your private report and work with you to assess it. Confirmed issues in the specification are addressed through the normal FHIR change-management process; where a report needs to stay private, we will coordinate any disclosure with you.
