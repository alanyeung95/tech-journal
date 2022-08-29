# Development

## importing references/dependencies
https://docs.microsoft.com/en-us/visualstudio/ide/managing-references-in-a-project?view=vs-2022

## creating a restful API server
https://www.youtube.com/watch?v=JeIE3jzAxHU

## config or env variables
`web.config` is used by debug mode, `web.xxxx.config` is used by xxxx mode

put these changes inside `web.config`
```
<configuration>
  <appSettings>
    <add key="SERVICE_HOST" value="http://service.url:3000" />
  </appSettings>
  <system.web>
  ...
  ...
<configuration>
```

# Deployment
## deploy apps to IIS server
https://www.youtube.com/watch?v=Q_A_t7KS5Ss
