## fhir.ini Version mapping sections
There are several sections that have the same format describing the mappings between the 2 versions marked in the section header:
* r4-r6-changes
* r5-r6-changes

These make it nice and simple and easier to write/review the configuration on a per version basis.
And while processing, can load separately, and configure the internal rules to handle the versioning, without the configuration rules explicitly stating it.
> **Note:** that the r4b changes are also included in the r4-r6 section for simplicity since they are so similar.

### Format of the entries
Each entry maps a single property. Throughout, **Source** is the older version and **Target** is the newer version. The following forms are supported:

* **Rename or move** — `Source.oldPath=@Target.newPath` (optionally followed by `, comment`)<br/>The key (left of `=`) is always the **old/source** property path; the value after `@` is the **new/target** path (i.e. `old-name=@new-name`). The engine keys its rename map by the value-after-`@` so it can map a revision path back to the original; getting this direction backwards means no match is found and the property is reported as `new`/`deleted` instead of moved.<br/>e.g. `Source.derivedFrom=@Target.baseDefinition`
* **New property note** — `Target.newPath=+comment`<br/>Records a comment for a property that is new in the target version, typically describing where its value comes from.<br/>e.g. `Target.newProp=+This property conditionally comes from Source.propA and/or Source.propB depending on ...`
* **Changed property note** — `Target.path=*comment`<br/>Adds an extra comment to a property that changed in some other way (i.e. not added or deleted).
* **Deletion comment** — `Source.deletedPath=comment`<br/>The property was removed; the comment explains why it should not be used or migrated.<br/>e.g. `Source.deleted=This property was invalid and should not be used or migrated`
* **Deletion with mapping note** — `Source.deletedPath=->comment`<br/>The property is still reported as deleted, but the comment names the property(ies) in the new version that its content maps to. This form is used when the change is more than a simple rename.<br/>e.g. `Source.reason=->reason.concept`

Merges (several old properties into one) and conditional splits (one old property into several) do not have a dedicated syntax — they are described using the comment forms above (`+`, `*` or `->`) on the affected properties.

The automated checks in the core build perform their matching by property-name comparison, then consult the rename map for any mapped entries.

Note that the diff engine doesn't support multiple maps to the same property (as it considers them moves rather than maps). As such instead of "mapping" the property, add a new property note on the target property and a deletion mapping note for the source fields.

> For example:
> ```
> CareTeam.reason=+ Merged both reasonCode and reasonReference into reason
> CareTeam.reasonCode=-> CareTeam.reason
> CareTeam.reasonReference=-> CareTeam.reason
> ```

## Implementation reference (for maintainers)
The version diff engine lives in `org.hl7.fhir.convertors.SpecDifferenceEvaluator` (in the `org.hl7.fhir.core` repository, `org.hl7.fhir.convertors` module). The key functions are:

* `loadFromIni(IniFile)` — parses a change section into two maps:
  * `renames` — the rename/move map. **It is keyed by the new/target path and stores the old/source path** (`renames.put(valueAfterAt, iniKey)`), i.e. for `old-name=@new-name` it stores `renames[new-name] = old-name`. This is why the ini key must be the old name (see the format section above).
  * `deletionComments` — keyed by the old/source path, holds the free-text deletion/`->` comment.
* `checkRename(String)` — resource/type-level lookup used by the `getDiffAsJson`/`getDiffAsXml` entry points to find the original type that a (possibly renamed) revision type corresponds to.
* `mapPath(String tn, String path)` — rewrites a revision element path to its original path using `renames`. It first tries an exact key match, then falls back to a prefix match (which only covers descendants whose leaf name is unchanged). Per the convention in `fhir.ini` ("if a backbone element is renamed, all the properties all need to be marked as renamed too"), every affected descendant should still be given its own explicit entry — mandatory for any child whose own name also changes — rather than relying on the prefix fallback (see `MedicinalProductDefinition` for an example).
* `getMatchingElement(String tn, List<ElementDefinition>, ElementDefinition target)` — finds the original element that a revision element matches, by comparing `mapPath(target.path)` against each original path (ignoring `[x]` suffixes).
* `compare(...)` / `compareJson(...)` / `compareXml(...)` — the per-resource diff drivers (HTML, JSON and XML renderings of the same algorithm).
* `compareElement(...)` / `compareElementJson(...)` / `compareElementXml(...)` — emit the per-element differences (rename/move, cardinality, type, binding, default value, modifier) for a matched pair. The rename/move message is only produced here, so it only appears for elements that were successfully matched.
* `noteMove(...)` / `moveAlreadyNoted(...)` — de-duplicate "Moved from X to Y" messages (a moved backbone would otherwise report a move for every descendant).
* `markNew` / `markDeleted` / `markChanged` / `markNoChanges` — emit the individual HTML table rows.

### Basic algorithm (per resource/type)
For each resource, the original (source version) differential is compared against the revision (target version) differential in two phases:

1. **Match** — every revision element is run through `getMatchingElement` → `mapPath`, which uses `renames` to translate the revision path back to the original path and looks up the original element. Matched pairs are cross-linked via `UserData("match")`.
2. **Report**
   * **New** — revision elements with no match are reported as added (`markNew`).
   * **Changed / renamed / moved** — matched pairs go to `compareElement*`. If the tail name changed but the parent is the same it is a *rename*; if the parent changed it is a *move* (`Moved from <old path> to <new name>`).
   * **Deleted** — original elements with no match are reported as deleted (`markDeleted`), **with child-suppression**: once a path is recorded as deleted, any descendant path is skipped, so a deleted backbone element swallows its remaining unmapped children.

### Consequence for deleted backbones whose children move up
Because matching and deletion are independent, a backbone can be reported as deleted while its children are simultaneously reported as moved — provided each child has its own `old-name=@new-name` entry. For example, when R4 `Subscription.channel` is removed and its children move to the root:

```
Subscription.channel.type=@Subscription.channelType
Subscription.channel.payload=@Subscription.contentType
```

`Subscription.channel` is still reported as deleted, while `channel.type` and `channel.payload` match their new root paths and are reported as moved (rather than being suppressed as deleted children).
