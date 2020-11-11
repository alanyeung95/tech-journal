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


## Query the customer_number from the orders table for the customer who has placed the largest number of orders.
https://leetcode.com/articles/customer-placing-the-largest-number-of-orders/

# join table with where
select employee_uin.uin, employee.name 
from employee
left join employee_uin 
on employee.id=employee_uin.id
where employee.age < 25
ORDER BY employee.name asc, employee.id asc;