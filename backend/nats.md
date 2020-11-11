run nats-streaming server
``` 
go run nats-streaming-server.go --store=SQL --sql_driver=mysql --sql_no_caching=true --sql_source='nss:password@(127.0.0.1:3306)/nss_db' 
```
