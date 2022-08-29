## creating a restful API server
https://www.youtube.com/watch?v=JeIE3jzAxHU

## deploy apps to iis server
https://www.youtube.com/watch?v=Q_A_t7KS5Ss

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
