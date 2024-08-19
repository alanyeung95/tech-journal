# Locking

Table locking is a concurrency control mechanism used to manage access to a database table by multiple transactions. It can be implemented at different levels, such as table-level, page-level, or row-level.

## Type of locking

table level locking

```
LOCK TABLES accounts WRITE, transactions WRITE;
...
UNLOCK TABLES;
```

row level locking

```
select xxx from xxxx
update xxx set xxx
```

page level locking

```
UPDATE orders SET status = 'Shipped' WHERE order_date < '2023-01-01';
```

## Scenario

Suppose you want to transfer money between two accounts. This operation involves:

1. Checking account balances to ensure sufficient funds.
2. Deducting an amount from one account.
3. Adding the same amount to another account.
4. Recording the transaction details.

To maintain data integrity and prevent other operations from interfering during this process, you will need to use both read and write locks.

## Tables Setup

Here are simple SQL commands to create the necessary tables:

```sql
-- Lock the tables for write from the beginning
LOCK TABLES accounts WRITE, transactions WRITE;

-- Check if sufficient funds are available
SELECT balance INTO @balance FROM accounts WHERE account_id = 1;
IF @balance > 100 THEN

    -- Deduct the amount from the sender's account
    UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;

    -- Add the amount to the recipient's account
    UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;

    -- Record the transaction
    INSERT INTO transactions (from_account_id, to_account_id, amount)
    VALUES (1, 2, 100);

    -- Commit the transaction if everything is okay
    COMMIT;
ELSE
    -- If not enough balance, rollback the transaction
    ROLLBACK;
END IF;

-- Unlock tables
UNLOCK TABLES;
```

# Transaction

Transactions represent a sequence of operations performed as a single logical unit of work, which must either all succeed or all fail (atomicity). Transactions provide a framework for error recovery and ensure database consistency.

1. Purpose: The primary purpose of transactions is to ensure data integrity and consistency across multiple operations by adhering to the ACID properties (Atomicity, Consistency, Isolation, Durability).

2. Isolation Levels: Transactions can be configured with different isolation levels to manage how data modified during a transaction can be accessed by other transactions. These include Read Uncommitted, Read Committed, Repeatable Read, and Serializable.

3. Scope: Typically affects only the rows or data items that are explicitly accessed or modified during the transaction, although this can depend on the isolation level and database implementation.

4. Usage: Used for almost all database operations that need to ensure data correctness across multiple operations, such as updating account balances, processing orders, or any operation where the state before and after the transaction must be consistent.

## Example

```sql
START TRANSACTION;

-- This might place a shared lock on the row(s) read
SELECT balance FROM accounts WHERE account_id = 1;

-- This will place an exclusive lock on the row(s) affected
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;

COMMIT;
```

# Comparison and Considerations

- Granularity:

  - Table Locking: Affects the entire table, less granular, can lead to bottlenecks.
  - Transactions: Affects only the data involved in the operations, more granular, better for concurrency.

- Performance Impact:
  - Table Locking: Can significantly impact database performance due to its coarse granularity, especially if long-held.
  - Transactions: Depending on the isolation level, transactions can be optimized to reduce locking overhead, thus minimizing impact on performance.

For some case like account transfer, transaction is more preferred due to

1. Atomicity
2. Explicit locking is more prone to deadlocks because it requires manual control over lock management
