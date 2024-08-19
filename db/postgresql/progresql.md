
# Intro
Postgres is an object-relational database, while MySQL is a purely relational database. This means that Postgres includes features like table inheritance and function overloading, which can be important to certain applications.

# Difference
ref: https://aws.amazon.com/compare/the-difference-between-mysql-vs-postgresql/

## Performance
MySQL has improved performance for high-frequency read operations.

PostgreSQL has improved performance for high-frequency write operations.

## Data type
MySQL 的各种 text 字段有不同的限制, 要手动区分 small text, middle text, large text... Pg 没有这个限制, text 能支持各种大小.

MySQL supports numeric, character, date and time, spatial, and JSON data types.

PostgreSQL supports all MySQL data types along with geometric, enumerated, network address, arrays, ranges, XML, hstore, and composite.

## OOP feature
https://www.postgresql.org/docs/10/tutorial-inheritance.html

table inheritance
```
CREATE TYPE product AS (
  product_id int,
  name text,
  price numeric
);

CREATE TABLE electronics OF product (
  warranty_period int
);

SELECT name, price FROM electronics WHERE warranty_period > 2;
```
