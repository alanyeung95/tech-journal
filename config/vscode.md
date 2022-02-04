## extensions
```
code --list-extensions 
```
### common

```
dbaeumer.vscode-eslint
donjayamanne.githistory
eg2.vscode-npm-script
esbenp.prettier-vscode
ms-azuretools.vscode-docker
ms-vscode-remote.remote-containers
ms-vscode.vscode-typescript-next
oliversturm.fix-json
```
### others
```
golang.go
prograhammer.tslint-vue
jcbuisson.vue
```

## setting.json
```
{
  "editor.formatOnSave": true,
  "vetur.format.defaultFormatter.html": "prettier",
  "vetur.format.defaultFormatter.stylus": "none",
  "files.trimTrailingWhitespace": true,
  "typescript.tsdk": "node_modules/typescript/lib",
  "[typescript]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "eslint.autoFixOnSave": true,
  "eslint.validate": [
    {
      "language": "vue",
      "autoFix": true
    },
    {
      "language": "html",
      "autoFix": true
    },
    {
      "language": "javascript",
      "autoFix": true
    },
    {
      "language": "typescript",
      "autoFix": true
    }
  ],
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  }
}
```
