# Git-Notes

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
