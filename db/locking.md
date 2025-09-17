In the context of database operations and concurrency control, there are two main locking strategies. Pessimistic and Optimistic Locking are not tied to a specific technology, but rather to a design philosoph.

## Pessimistic Locking

**Pessimistic locking** assumes that conflicts are likely to happen and therefore takes a conservative approach by locking data before it's read or modified, preventing other transactions from accessing the locked data until the lock is released. This approach is similar to traditional locking mechanisms, such as read and write locks, that directly prevent concurrent access to data.

- **Usage**: Pessimistic locking is typically used when the likelihood of data conflicts is high, or when the cost of a transaction failure is significant. It is common in systems where data integrity is critical and conflicts are common.
- **Implementation**: This can be done through explicit table locks, row locks, or other database-specific locking mechanisms.
- **Characteristics**:
  - Locks data early to prevent concurrent modifications.
  - Can lead to deadlocks if not managed correctly.
  - May impact system performance due to longer wait times for locked resources.

## Optimistic Locking

**Optimistic locking**, in contrast, assumes that conflicts are rare and does not lock data when it's read. Instead, it checks if the data was modified by another transaction before it commits changes. If a conflict is detected (usually through version numbering or timestamps), the transaction is rolled back, and the application can retry the operation.

- **Usage**: Optimistic locking is used when the likelihood of conflicts is low, which makes it suitable for high-concurrency environments or systems where locking-induced delays are unacceptable.
- **Implementation**: This often involves adding a version number or timestamp to each record. When updating a record, the transaction checks if the record’s version number has changed since it was last read. If it has, the update is aborted because another transaction has modified the record.
- **Characteristics**:
  - Does not prevent other transactions from accessing data simultaneously.
  - Reduces the potential for deadlocks.
  - Suitable for applications with low conflict rates but high levels of concurrency.

### Examples

**Pessimistic Locking Example**:
In SQL Server, you might use a `SELECT` statement with a locking hint to ensure pessimistic locking:

```sql
SELECT * FROM accounts WITH (UPDLOCK, HOLDLOCK) WHERE account_id = 1;
```

**Optimistic Locking Example**:
In a system with a `version` column in a table, an update might look like this:

```sql
UPDATE accounts
SET balance = balance + 100, version = version + 1
WHERE account_id = 1 AND version = @version;
```

Here, `@version` is the version number read by the application. If the version has changed since it was read, the `UPDATE` statement affects 0 rows, indicating a conflict.

## Row-level locks (InnoDB)
Multiple transactions can read/write different rows simultaneously.

## Table-level locks (MyISAM)
Entire table is locked during writes — slower for high concurrency.

## Document-level (MongoDB)
Each write locks only the affected document, allowing high concurrency.

## Exclusive lock & Sharable lock
these are locks used by DB, when we use query like `select * from xxx` or `update products set xxxx`, the db will aplly the appropriate lock.

but developers could also manually request locks:
```
LOCK TABLE employees READ;     -- Shared lock
LOCK TABLE employees WRITE;    -- Exclusive lock
```

they are absolutely using a pessimistic locking strategy.

## IS (Intent Shared) and IX (Intent Exclusive) lock

For example, if a document is being updated:

1. MongoDB places an IX lock on the database and collection.
2. Then applies an X lock on the document itself

Benefit:
Without intent locks, the database would need to scan every row or page to check for conflicting locks. 

With intent locks:
1. The engine can quickly check at the table level whether deeper locks exist.
2. This speeds up decisions about whether a new lock can be granted.

Think of it like putting a sign on a door that says “someone’s inside” — you don’t need to open every room to check.

