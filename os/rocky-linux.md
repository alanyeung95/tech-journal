## Upgrade OS Minor Version

specify minor version, override the minior version

```
# dnf --releasever=9.4 upgrade --refresh
```

if the latest version is greater than `9.4` and the minor version is no longer available in the official website/package, we need to retrieve the `9.4` package data by specifying the archive link for each source file like `rocky.repo` in folder `/etc/yum.repos.d`
