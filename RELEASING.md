# Releasing PackApps

1. On a branch from trunk, update the version in gradle.properties and add a matching version heading to CHANGELOG.md. Open a PR. CI builds and exercises the packages on Windows, Intel and Apple Silicon macOS, and Ubuntu. The database job also checks the MySQL schema.
2. After checks pass, merge the PR and tag its exact merged commit with an annotated tag, for example `git tag -a v5.0.0 COMMIT -m "PackApps 5.0.0"`. Push the tag.
3. The Draft desktop release workflow checks the tag, builds from that commit on every native runner and exercises the installed launcher, OCR, persistence and exports. After all jobs pass it uploads the five downloads and SHA256SUMS to a draft. Re-run failed jobs to resume. A retry can replace assets only while the release is a draft.
4. Review the notes and download hashes in GitHub before manually publishing. Keep published tags and assets intact. Corrections ship under a new version.

For a local packaging check use JDK 25, JAVA_HOME, Python 3.12+ and `python scripts/package.py` on the target desktop. Windows needs WiX and installs a per-user copy. Linux needs fakeroot, dpkg tooling, sudo and a graphical display or xvfb-run. Use a disposable machine for installer tests. macOS uses hdiutil and codesign. Downloads are written to build/release.
