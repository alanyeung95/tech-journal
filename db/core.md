# Indexing
## B+ Tree vs Hash Index
ref:

https://www.sqlpipe.com/blog/b-tree-vs-hash-index-and-when-to-use-them

https://www.linkedin.com/pulse/what-best-timesten-index-my-oltp-application-douglas-hood

# Relational Database

A relational database (RDB) is a way of structuring information in tables, rows, and columns. Table are related to one another (using foreign keys)

# Denormalization 
Denormalization is a database optimization technique in which we add redundant data to one or more tables. This can help us avoid costly joins in a relational database.

https://www.youtube.com/watch?v=vRqzFBTgGJA&ab_channel=ExamPro

## Pros of Denormalization:-
1. Retrieving data is faster since we do fewer joins

## Cons of Denormalization:-
1. Updates and inserts are more expensive.

# Join
Nested Loop Join, Hash Join, Sort Merge Join

# Which Database Is Right For Your Business?

schema update frequency, data structure (e.g. nested json), scaling, failover, performance (date insertion vs join table)

reference: 
1. https://aws.amazon.com/compare/the-difference-between-mongodb-vs-mysql
2. https://blog.panoply.io/mongodb-and-mysql

## Transaction (atom)
### MySQL
Any type of application that requires multi-row transactions such as an accounting system, would be better suited for a relational database. MongoDB is not an easy replacement for legacy systems that were built for relational databases.

```
start transaction;
delete from orderitems where order_num = 20010;
delete from orders where order_num = 20010;
commit;
```

### Mongo
1. Operations on a single document are always atomic with MongoDB databases; 
2. Starting from version 4.0, MongoDB introduced support for multi-document transactions. These transactions allow you to perform multiple operations across different documents and collections in an atomic way, similar to transactions in relational databases.
3. **Replica Sets and Transactions**: Multi-document transactions in MongoDB are supported on replica sets. Since version 4.2, MongoDB also supports distributed transactions across multiple shards in a sharded cluster.

## Scalability
MongoDB, like MySQL, supports a configuration where only one node (the Primary) handles writes in a replica set setup, ensuring consistent write operations. 

### MySQL
1. Vertical scalability by adding more resources to the current database server

### Mongo
Sharding is a method for distributing a single dataset across multiple databases, which can then be stored on multiple machines. This allows for larger datasets to be split into smaller chunks and stored in multiple data nodes, increasing the total storage capacity of the system.

ref: https://www.mongodb.com/resources/products/capabilities/sharding

## Schema stability
When adding new columns to a relational database like MySQL, it can lock up the entire database and cause performance issues. With MongoDB, since it is schema-less, you can add new fields and it won't affect existing rows. The high availability of MongoDB may be helpful.

## Failover

Mongo: Election for Primary: If the primary node becomes unavailable due to a failure or network partition, the remaining nodes hold an election to choose a new primary from among the secondaries. This ensures that the replica set can continue to accept write operations even if the original primary fails.

MySQL: MySQL with Master-Slave Replication: In traditional MySQL setups using master-slave replication, the master handles all write operations, similar to the Primary in MongoDB. However, slaves in MySQL do not participate in elections to become the new master if the master fails; the promotion of a slave to a master is typically a manual process or requires additional software tools.

## Performance
MySQL is designed to enact high-performance joins across multiple tables that are appropriately indexed. However, it requires data to be inserted row by row, so write performance is slower.

MongoDB documents follow a hierarchical data model and keep most of the data in a single document, reducing the need for joins across multiple documents. Joins are supported via the $lookup operation, but they are not optimized for performance. However, MongoDB offers an insertMany() API for rapidly inserting data, prioritizing write performance. 

## security
sql injection exist in Mysql
