## Group by
```
SELECT NAME, COUNT(*) as c FROM table 
GROUP BY name 
ORDER BY c 
DESC 
LIMIT 1
```

## Join
```
SELECT 
    productCode, 
    productName, 
    textDescription
FROM
    products t1
INNER JOIN productlines t2 
    ON t1.productline = t2.productline;
```

Similarly, the following query uses the INNER JOIN with the USING syntax:
```
SELECT 
    orderNumber,
    status,
    SUM(quantityOrdered * priceEach) total
FROM
    orders
INNER JOIN orderdetails USING (orderNumber)
GROUP BY orderNumber;
```

### right join

`employees` table
| employee_id | employee_name | department_id |
|-------------|---------------|---------------|
| 1           | Alice         | 101           |
| 2           | Bob           | 102           |
| 3           | Charlie       | 103           |

`departments` table
| department_id | department_name |
|---------------|-----------------|
| 101           | HR              |
| 102           | IT              |
| 104           | Finance         |

```
SELECT employees.employee_name, departments.department_name
FROM employees
RIGHT JOIN departments ON employees.department_id = departments.department_id;
```

result 
| employee_name | department_name |
|---------------|-----------------|
| Alice         | HR              |
| Bob           | IT              |
| NULL          | Finance         |


## Query the customer_number from the orders table for the customer who has placed the largest number of orders.
https://leetcode.com/articles/customer-placing-the-largest-number-of-orders/

# join table with where
select employee_uin.uin, employee.name 
from employee
left join employee_uin 
on employee.id=employee_uin.id
where employee.age < 25
ORDER BY employee.name asc, employee.id asc;

## Good Practise

### Transaction & Rollback
Before you execute your script:
```
BEGIN TRANSACTION;
```
After you execute your script and have done your checking:
```
ROLLBACK TRANSACTION;
```
Every change in your script will then be undone.

example:
```
BEGIN TRAN

UPDATE  C
SET column1 = 'XXX'
FROM table1 C

SELECT *
FROM table1
WHERE column1 = 'XXX'

ROLLBACK TRAN
```

https://stackoverflow.com/questions/19837655/sql-server-query-dry-run

## Deadlock
![Illustration](https://yuanchieh.page/post/2022/img/0425/01_2.png)

solution:
If every transaction locks records in the same sequence (e.g., always locking the record with the lower ID first), deadlocks can be avoided

```
-- Transaction 1
BEGIN TRANSACTION;
SELECT * FROM records WHERE id IN (1, 5) ORDER BY id FOR UPDATE;
UPDATE records SET data = 'new data' WHERE id = 1;
UPDATE records SET data = 'new data' WHERE id = 5;
COMMIT;

-- Transaction 2
BEGIN TRANSACTION;
SELECT * FROM records WHERE id IN (1, 5) ORDER BY id FOR UPDATE;
UPDATE records SET data = 'new data' WHERE id = 1;
UPDATE records SET data = 'new data' WHERE id = 5;
COMMIT;
```
