---

## Hard Blocks (will CHANGES_REQUEST every time)

### 1. PR with no tracker reference
Submitting a change without a Jira tracker item that documents WG approval will be rejected.

**Rule:** PRs need a Jira link in the PR body showing WG approval.

---

### 2. PR changes don't align with what Jira tickets requested
Submitting a change without a Jira tracker item that documents WG approval will be rejected.

**Rule:** PRs need make all the changes authorized by the Jira, but no more (with some flexibility for adjusting associated wording or improving grammar/flow)

---
### 2. Code systems / value sets that belong in THO
Inline code systems need to have experimental=true or be bound to a 'code' element.  Otherwise, the code system needs to be in THO, not in the extension IG.

---

### 3. targetProfile defined on Reference more than once
Multiple `targetProfile` values on a `Reference` type must be expressed as multiple `targetProfile` entries on a **single** `type.Reference` element, not as duplicate `type` entries.

---

### 4. Markdown with invalid entities
Markdown is only allowed to contain XHTML entities (e.g. `&amp;`, `&lt;`), not HTML entities (e.g. `&mdash;`)

---

## Consistent Non-Blocking Flags (COMMENTED state — approved but flagged)

---

## Checklist for PR Authors

Before opening a PR, verify:

- [ ] All new extensions have a Jira tracker link in the PR body showing WG approval
- [ ] Inline code systems are marked `experimental`, are bound to a 'code' element, or have a THO proposal reference
- [ ] `Reference` types use multiple `targetProfile` entries on a single element (not duplicate elements)
- [ ] No HTML entities (e.g., `&mdash;`) in markdown narrative files
- [ ] Description text is original — not copied from another extension unchanged
