## common

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

## rebase

https://www.atlassian.com/git/tutorials/merging-vs-rebasing


