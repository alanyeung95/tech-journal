# Relational Database

A relational database (RDB) is a way of structuring information in tables, rows, and columns. Table are related to one another (using foreign keys)

# Denormalization 
Denormalization is a database optimization technique in which we add redundant data to one or more tables. This can help us avoid costly joins in a relational database.

https://www.youtube.com/watch?v=vRqzFBTgGJA&ab_channel=ExamPro

## Pros of Denormalization:-
1. Retrieving data is faster since we do fewer joins

## Cons of Denormalization:-
1. Updates and inserts are more expensive.

# Which Database Is Right For Your Business?

reference: https://blog.panoply.io/mongodb-and-mysql

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
Operations on a single document are always atomic with MongoDB databases; however, operations that involve multiple documents, which are often referred to as “transactions,” are not atomic.

## Schema stability
When adding new columns to a relational database like MySQL, it can lock up the entire database and cause performance issues. With MongoDB, since it is schema-less, you can add new fields and it won't affect existing rows. The high availability of MongoDB may be helpful.

## security
sql injection exist in Mysql
