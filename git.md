## Common

How to fix committing to master instead of branch?

```
git reset --soft HEAD^
```

git tag version

```
git tag v2.53.0-alpha.5
git push origin v2.53.0-alpha.5
```

change commit message

```
git commit --amend
```

point local repo to new url after renaming it

```
git remote set-url origin https://github.com/xxxxx.git
```

clone specific branch

```
git clone --branch <branchname> <remote-repo-url>
```

change branch name

```
git branch -m old-branch-name new-branch-name
git push origin -u new-branch-name
```

# rebase

https://www.atlassian.com/git/tutorials/merging-vs-rebasing

# Remove file from history
Use the `git filter-branch` command, which will rewrite your commit history to remove the file.

This command will go through all your commits and tags and remove the specified .env file from them.

```
git filter-branch --force --index-filter \
"git rm --cached --ignore-unmatch .env" \
--prune-empty --tag-name-filter cat -- --all
```

Force push your changes to the remote repository:

After you've rewritten your history, you'll need to force push your changes to overwrite the history on GitHub:
```
git push origin --force --all
git push origin --force --tags
```
