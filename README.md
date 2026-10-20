## The FHIR Specification

This repository holds the source for the FHIR specification: resource and datatype definitions,
value sets, examples and the narrative pages. The specification is built from this source by
[kindling](https://github.com/HL7/kindling), the FHIR specification publisher.

| CI Status ([master][Link-BuildFhirOrgMaster]) |
| :---: |
| [![Build Status][Badge-AzureMasterPipeline]][Link-AzureMasterPipeline] |

### Important Links

Only the editors of the specification need to build it. If that's not you, one of these
links will probably be more useful:

* [Published FHIR Specification](https://hl7.org/fhir) or the [current build](https://build.fhir.org)
* [Jira - propose a change](https://jira.hl7.org/projects/FHIR/issues). Use this rather than making a PR directly, since all changes must be approved through the Jira workflow
* [FHIR chat](https://chat.fhir.org)
* [FHIR test cases](https://github.com/FHIR/fhir-test-cases)

### Building Locally

You need:

* Java 17 or later
* at least 16 GB of RAM (the build runs with a 12.5 GB heap; see `gradle.properties`)

Then run:

```
./gradlew publish
```

or use `publish.sh` (bash) or `publish.bat` (Windows), which do the same thing. The output
goes in `publish/`. A full build takes around 10 minutes, but this depends a lot on
the hardware. See also the [FHIR Build Process][Link-Confluence] on Confluence.

#### Partial builds

The build keeps track of what changed since the last build (in `temp/build.cache`), and only
rebuilds the affected resources and pages. It does a full build when nothing has changed, when
there is no previous output, or when you ask for one:

```
./gradlew publishFull
```

#### Command line parameters

Pass parameters through gradle with `--args`, for example:

```
./gradlew publish --args="-resource patient -nosound"
```

| Parameter | Meaning |
| --- | --- |
| `-nopartial` | Always do a full build (same as `publishFull`) |
| `-resource [name]` | Only rebuild the named resource |
| `-page [file]` | Only rebuild the named page (e.g. `fhirpatch.html`) |
| `-nogen` | Don't generate the specification; just run the validation |
| `-validation-mode [mode]` | `normal` (default), `none` or `extended` |
| `-validate [id]` | Only validate the named example |
| `-nosound` | Don't play the tones at the start and end of the build |
| `-output [folder]` | Put the output somewhere other than `publish/` |
| `-folder [folder]` | Build from a folder other than the current directory |
| `-fhir-settings [file]` | Location of the fhir-settings.json file (API keys etc.) |
| `-diff [program]` | Program used to show differences when a reference implementation doesn't round-trip an example |
| `-name [text]` | Name of the build, shown on each page (default: "Local Build (machine name)") |
| `-web` | Produce the final form for publication on hl7.org (core editors only) |

Verbose or custom logging can be set up with a logback configuration file:

```
./gradlew publish -Plogback.configurationFile=~/my-logback-config.xml
```

### CI Builds

Each pull request is built by the [pull request pipeline][Link-AzurePRPipeline]. If the build
succeeds, it is uploaded to [build.fhir.org/branches][Link-BuildFhirOrgBranches] under the name
of the branch, where the changes can be reviewed.

Once a PR is merged to master, the [master branch pipeline][Link-AzureMasterPipeline] builds it
and uploads it to [build.fhir.org][Link-BuildFhirOrgMaster].

Build results are posted to the committers/notification stream on chat.fhir.org.

### Maintenance

This project is maintained by [Grahame Grieve][Link-grahameGithub] and [David Otasek][Link-dotasekGithub] on behalf of the FHIR community.
Changes to normative content require review by the FHIR normative review team (see `CODEOWNERS`).

---

[Link-AzureMasterPipeline]: https://dev.azure.com/fhir-pipelines/fhir-publisher/_build/latest?definitionId=44&branchName=master
[Link-AzurePRPipeline]: https://dev.azure.com/fhir-pipelines/fhir-publisher/_build?definitionId=42
[Link-BuildFhirOrgMaster]: https://build.fhir.org
[Link-BuildFhirOrgBranches]: https://build.fhir.org/branches/
[Link-Confluence]: https://confluence.hl7.org/display/FHIR/FHIR+Build+Process
[Link-grahameGithub]: https://github.com/grahamegrieve
[Link-dotasekGithub]: https://github.com/dotasek
[Badge-AzureMasterPipeline]: https://dev.azure.com/fhir-pipelines/fhir-publisher/_apis/build/status/Master%20Branch%20Pipeline?branchName=master