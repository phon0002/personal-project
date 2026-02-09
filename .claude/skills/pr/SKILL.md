---
name: pr
description: Create, review, or summarize GitHub pull requests
disable-model-invocation: false
argument-hint: "[create|review|summary] [pr-number]"
---

# Pull Request Assistant

Help with GitHub pull requests using the GitHub CLI (`gh`).

## Operations

### Create a PR
When the user runs `/pr create` or `/pr` with no arguments:

1. Run `git status` to check for uncommitted changes
2. Run `git log` and `git diff main...HEAD` (or `master...HEAD`) to understand all changes on the current branch
3. Check if the branch has been pushed to remote
4. Draft a concise PR title (under 70 characters) and description
5. Push the branch if needed with `-u` flag
6. Create the PR using `gh pr create` with this format:

```
gh pr create --title "title" --body "$(cat <<'EOF'
## Summary
<1-3 bullet points>

## Test plan
- [ ] Testing steps...

Generated with Claude Code
EOF
)"
```

6. Return the PR URL

### Review a PR
When the user runs `/pr review <number>`:

1. Fetch PR details: `gh pr view <number>`
2. Fetch PR diff: `gh pr diff <number>`
3. Analyze the changes and provide a thorough review covering:
   - Overview of what the PR does
   - Code quality and style analysis
   - Specific suggestions for improvements
   - Potential issues or risks
   - Test coverage assessment

### Summarize a PR
When the user runs `/pr summary <number>`:

1. Fetch PR details: `gh pr view <number>`
2. Fetch PR diff: `gh pr diff <number>`
3. Provide a brief summary of the changes and their purpose

### List open PRs
When the user runs `/pr list`:

1. Run `gh pr list` to show all open PRs

## Requirements

- GitHub CLI (`gh`) must be installed and authenticated
- Must be in a git repository with a remote origin