## Dirty Read and Dirty Write

### Dirty Read

A **dirty read** occurs when a transaction reads data that has been modified by another transaction but not yet committed. These reads are "dirty" because the data could potentially be rolled back if the modifying transaction does not complete successfully, meaning the reading transaction could end up with data that was never officially saved into the database.

**Example of Dirty Read:**

1. Transaction A modifies a record (e.g., changes the balance of an account).
2. Before Transaction A commits the changes, Transaction B reads the modified balance.
3. If Transaction A fails and rolls back after Transaction B reads the data, Transaction B now has an incorrect view of the data that never actually existed in the database.

### Dirty Write

A **dirty write** occurs when a transaction modifies data that another transaction has also modified before either transaction commits. This can lead to data integrity issues because one transaction may overwrite changes made by the other without realizing it.

**Example of Dirty Write:**

1. Transaction A modifies a record (e.g., updates a customer address).
2. Before Transaction A commits its changes, Transaction B also updates the same customer address to a different value and commits.
3. If Transaction A later commits, it will overwrite the changes made by Transaction B without knowing that the record was altered, potentially leading to lost updates and inconsistencies.

### Handling Dirty Reads and Writes

To prevent these issues, most relational database management systems (RDBMS) provide different levels of transaction isolation:

- **Read Uncommitted**: This level allows dirty reads, meaning transactions can see changes made by other transactions before they are committed. However, it does not prevent dirty reads but does allow them.
- **Read Committed**: This is a commonly used isolation level that prevents dirty reads. Transactions can only read data that has been committed.
- **Repeatable Read**: This isolation level prevents dirty reads and ensures that if a transaction reads a record twice in its scope, it will get the same values each time, even if other transactions are trying to modify it.
- **Serializable**: This is the strictest isolation level, where transactions are effectively isolated from each other as if they are serialized. It prevents dirty reads, dirty writes, and other concurrency issues like phantom reads.
