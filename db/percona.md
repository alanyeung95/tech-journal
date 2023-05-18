## Enterprise-grade security features
Leveraging access and audit controls to protect your sensitive data in transit and at rest, no matter where it is stored.

### User access control
<img src="https://www.percona.com/blog/wp-content/uploads/2020/10/limit-access-percona-monitoring-and-managment.png">

### audit log
<img src="https://1.bp.blogspot.com/-CgVpTuiIQEg/XYoEWefliDI/AAAAAAABD_Y/zqskM46dbbccNVwAz7xkzI_d_LP09AnBgCLcBGAsYHQ/s1600/Screen%2BShot%2B2019-09-24%2Bat%2B5.25.23%2BPM.png">

## Dashboard
<img src="https://images.g2crowd.com/uploads/attachment/file/133614/Monitor.png">
e.g. Percona Monitoring and Management

## Performance
### Prefix index queries optimization

Percona Server for MySQL has ported Prefix Index Queries Optimization feature from Facebook patch for MySQL.

Prior to this InnoDB would always fetch the clustered index for all prefix columns in an index, even when the value of a particular record was smaller than the prefix length. This implementation optimizes that case to use the record from the secondary index and avoid the extra lookup.

### Thread pool (Priority connection scheduling)
Even though thread pool puts a limit on the number of concurrently running queries, the number of open transactions may remain high, because connections with already started transactions are put to the end of the queue. Higher number of open transactions has a number of implications on the currently running queries. To improve the performance new thread_pool_high_prio_tickets variable has been introduced.

This variable controls the high priority queue policy. Each new connection is assigned this many tickets to enter the high priority queue. Whenever a query has to be queued to be executed later because no threads are available, the thread pool puts the connection into the high priority queue
