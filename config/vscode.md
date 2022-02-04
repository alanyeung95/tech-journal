## extensions
*  docker
* eslint
* fix json
* git history
* javascript and typescript nightly
* npm
* prettier

### others
* go
* tslint vue
* vue

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
