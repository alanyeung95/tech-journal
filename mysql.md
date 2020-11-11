
# Query the customer_number from the orders table for the customer who has placed the largest number of orders.
https://leetcode.com/articles/customer-placing-the-largest-number-of-orders/

# join table with where
select employee_uin.uin, employee.name 
from employee
left join employee_uin 
on employee.id=employee_uin.id
where employee.age < 25
ORDER BY employee.name asc, employee.id asc;
