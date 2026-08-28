#!/usr/bin/env python3
"""merge-audit.py — find content silently lost in merge commits.

For every merge commit in range, compares the *recorded* merge result against a
mechanical re-merge of its parents (git's --remerge-diff). Lines the mechanical
merge would have kept but the recorded merge dropped are "vanished" content —
either a deliberate conflict resolution, or an accident nobody chose.

For each vanished line the script then checks:
  1. is it still absent from the tip of the branch? (if it came back, ignore)
  2. did any *later regular commit* touch that content? (if someone deliberately
     removed/reworded it afterwards, it's probably intentional — the explaining
     commit is shown so a human can judge)

Findings are classified:
  SILENT   - the file was NOT listed in the merge commit's "# Conflicts:" trailer:
             git merged it cleanly, yet the recorded result differs. Nobody chose
             this. Highest suspicion.
  CONFLICT - the file was listed as conflicted; content was dropped during manual
             resolution. May be intentional, still worth review.

Requirements: git >= 2.36 (for --remerge-diff), python3. Run against a full
(non-shallow) clone for best results; on a shallow clone, history checks near the
boundary will attribute old content to the boundary commit.

Usage:
  python3 merge-audit.py /path/to/repo --since 2026-01-01 [--tip master]
  python3 merge-audit.py /path/to/repo --range v6.0..master
  python3 merge-audit.py /path/to/repo --since 2026-06-01 --path-prefix source/

Exit code is 1 if any SILENT finding with still-missing content exists (useful
as a CI guard on new merges: --range 'origin/master@{1}..origin/master').
"""
import argparse, json, re, subprocess, sys

MARKER = re.compile(r"^(<{7}|={7}|>{7}|\|{7})")

def norm(s):
    return "".join(s.split())

def git(repo, *args, check=True):
    r = subprocess.run(["git", "-C", repo, *args], capture_output=True, text=True)
    if check and r.returncode != 0:
        sys.exit(f"git {' '.join(args[:3])}... failed: {r.stderr.strip()[:300]}")
    return r

def parse_remerge_log(text):
    merges, cur, cur_file = [], None, None
    def close_file():
        nonlocal cur_file
        if cur is not None and cur_file is not None:
            slot = cur["files"].setdefault(cur_file["path"], {"removed": [], "added": []})
            slot["removed"] += cur_file["removed"]
            slot["added"] += cur_file["added"]
        cur_file = None
    def close_merge():
        nonlocal cur
        close_file()
        if cur is not None:
            merges.append(cur)
        cur = None
    for line in text.splitlines():
        m = re.match(r"^commit ([0-9a-f]{40})", line)
        if m:
            close_merge()
            cur = {"sha": m.group(1), "author": "", "date": "", "subject": None,
                   "conflicts": set(), "files": {}}
            continue
        if cur is None:
            continue
        if line.startswith("Author: "):
            cur["author"] = line[8:]; continue
        if line.startswith("Date: "):
            cur["date"] = line[6:].strip(); continue
        if line.startswith("diff --git "):
            close_file()
            m2 = re.match(r"^diff --git a/(.*) b/(.*)$", line)
            cur_file = {"path": m2.group(2) if m2 else line, "removed": [], "added": []}
            continue
        if cur_file is not None:
            if line.startswith("-") and not line.startswith("---"):
                body = line[1:]
                if not MARKER.match(body.lstrip()):
                    cur_file["removed"].append(body)
            elif line.startswith("+") and not line.startswith("+++"):
                cur_file["added"].append(line[1:])
        else:
            st = line.strip()
            if cur["subject"] is None and st and not st.startswith("Merge: "):
                cur["subject"] = st
            if st.startswith("#") and "/" in st and "Conflicts" not in st:
                cur["conflicts"].add(st.lstrip("#").strip())
    close_merge()
    return merges

def main():
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("repo")
    ap.add_argument("--since", help="only merges after this date, e.g. 2026-01-01")
    ap.add_argument("--range", dest="range_", help="rev range, e.g. v6.0..master")
    ap.add_argument("--tip", default="master", help="branch whose tip defines 'still missing' (default master)")
    ap.add_argument("--path-prefix", default="", help="only report findings under this path")
    ap.add_argument("--min-line", type=int, default=4, help="ignore vanished lines shorter than this (normalized)")
    ap.add_argument("--json", dest="json_out", help="also write findings as JSON to this file")
    args = ap.parse_args()

    ver = git(args.repo, "version").stdout.strip()
    m = re.search(r"(\d+)\.(\d+)", ver)
    if m and (int(m.group(1)), int(m.group(2))) < (2, 36):
        sys.exit(f"{ver}: --remerge-diff needs git >= 2.36")

    log_args = ["log", "--merges", "--remerge-diff", "--no-color"]
    if args.since:
        log_args.append(f"--since={args.since}")
    log_args.append(args.range_ if args.range_ else args.tip)
    print(f"re-merging all merge commits in range (this can take a few minutes)...", file=sys.stderr)
    merges = parse_remerge_log(git(args.repo, *log_args).stdout)

    tip_cache = {}
    def tip_content(path):
        if path not in tip_cache:
            r = git(args.repo, "show", f"{args.tip}:{path}", check=False)
            tip_cache[path] = r.stdout if r.returncode == 0 else None
        return tip_cache[path]

    findings = []
    for mg in merges:
        for path, fd in mg["files"].items():
            if args.path_prefix and not path.startswith(args.path_prefix):
                continue
            added_norms = {norm(a) for a in fd["added"]}
            vanished = [r for r in fd["removed"]
                        if norm(r) not in added_norms and len(norm(r)) > args.min_line]
            if not vanished:
                continue
            tip = tip_content(path)
            if tip is None:
                still, note = vanished, "file absent at tip"
            else:
                tip_norms = {norm(l) for l in tip.splitlines()}
                still = [v for v in vanished if norm(v) not in tip_norms]
                note = ""
            if not still:
                continue
            probe = max(still, key=lambda s: len(norm(s))).strip()
            hist = git(args.repo, "log", "--format=%h %an %ad %s", "--date=short",
                       "-S", probe, args.tip, "--", path, check=False).stdout.splitlines()
            hist = [h for h in hist if not h.startswith(mg["sha"][:8])]
            findings.append({
                "sha": mg["sha"][:10], "date": mg["date"], "author": mg["author"],
                "subject": mg["subject"] or "", "path": path,
                "kind": "CONFLICT" if path in mg["conflicts"] else "SILENT",
                "vanished": len(vanished), "still_missing": len(still), "note": note,
                "history": hist[:4], "lines": [s.strip() for s in still[:12]],
            })

    findings.sort(key=lambda x: (x["kind"] != "SILENT", -x["still_missing"]))
    print(f"\n# Merge-loss audit: {len(merges)} merges scanned, "
          f"{len(findings)} file-level findings with content still missing at {args.tip}\n")
    for f in findings:
        print("=" * 100)
        print(f"{f['kind']:8s} merge {f['sha']}  {f['date']}  {f['author']}")
        print(f"         {f['subject'][:100]}")
        print(f"         {f['path']}  (still missing {f['still_missing']} of {f['vanished']} dropped lines) {f['note']}")
        if f["history"]:
            print("         later commits touching this content (may explain an intentional removal):")
            for h in f["history"]:
                print(f"           {h[:140]}")
        for l in f["lines"]:
            print(f"    - {l[:160]}")
        print(f"    inspect: git show --remerge-diff {f['sha']} -- '{f['path']}'")
    if args.json_out:
        with open(args.json_out, "w") as fh:
            json.dump(findings, fh, indent=1)
    sys.exit(1 if any(f["kind"] == "SILENT" for f in findings) else 0)

if __name__ == "__main__":
    main()
