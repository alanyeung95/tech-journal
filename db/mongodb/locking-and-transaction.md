# Locking

## Types of Locks in MongoDB

### Collection-Level Lock:

Description: A lock that applies to a specific collection within a database. This allows different collections within the same database to be accessed concurrently.

Usage: This was the primary level of locking starting in MongoDB 3.0.

### Document-Level Lock (WiredTiger Engine):

Description: The most granular lock, applying to individual documents within a collection. This allows for the highest level of concurrency, enabling multiple operations on different documents within the same collection to proceed without blocking each other.

Usage: This is the standard locking mechanism in modern versions of MongoDB with the WiredTiger storage engine.

## What type of locking does MongoDB use?

Read Operations:

1. Shared Lock (S Lock): MongoDB uses shared locks for read operations. This allows multiple read operations to access the same document concurrently, but prevents any write operations from modifying the document while it is being read.
2. Intent Locks: MongoDB uses intent locks to indicate that a lock is going to be requested at a more granular level. For instance, an intent shared lock (IS) on a collection means that individual documents within the collection will be read, allowing the database to prepare for potential document-level locks.

\*\*\* An IS lock is applied at a higher level in the database hierarchy (like a database or collection) to indicate that a transaction intends to acquire a Shared (S) lock at a lower level (such as on a specific document within a collection). It serves as a signal or placeholder to prevent conflicting locks (such as Intent Exclusive (IX) or Exclusive (X) locks) from being granted on the higher-level resource while the lower-level resources are locked.

Write Operations:

1. Exclusive Lock (X Lock): Write operations require an exclusive lock on the document being modified. This prevents other operations, both read and write, from accessing the document until the lock is released.
2. Lock Escalation: If a write operation affects a large number of documents, MongoDB might escalate the lock from document-level to collection-level to improve efficiency. However, this is managed internally and is typically avoided to maintain high concurrency.

Transactions (Introduced in MongoDB 4.0):

1. Multi-Document Transactions: With the introduction of multi-document transactions, MongoDB uses a combination of document-level and higher-level locks to ensure atomicity across multiple documents and collections. During a transaction, locks are held until the transaction is committed or aborted to ensure data consistency.

## How They Work Together

Hierarchical Locking Example:

- Collection Level: Suppose a transaction wants to read specific documents in the accounts collection. The transaction first acquires an IS lock on the accounts collection. This IS lock indicates that the transaction intends to read some documents and may need to acquire S locks on those documents.
- Document Level: For each document that the transaction reads, it acquires an S lock on that specific document. The S lock ensures that the document cannot be modified by other transactions while it is being read.

\*\*\* Coexistence, Not Conversion: An IS lock does not "convert" into an S lock. Instead, the IS lock and S lock coexist, with the IS lock on the higher-level resource (like a collection) and the S lock on the lower-level resource (like a document).

# Transaction

```js
// Start a client session
const session = client.startSession();

try {
  // Start a transaction
  session.startTransaction();

  // Step 1: Check if sufficient funds are available
  const accountsCollection = db.collection("accounts");
  const sourceAccount = await accountsCollection.findOne(
    { account_id: 1 },
    { session }
  );

  if (!sourceAccount || sourceAccount.balance < 100) {
    throw new Error("Insufficient funds in source account");
  }

  // Step 2: Deduct the amount from the source account
  const updateSource = await accountsCollection.updateOne(
    { account_id: 1, balance: { $gte: 100 } }, // Ensure balance is still sufficient
    { $inc: { balance: -100 } },
    { session }
  );

  if (updateSource.matchedCount === 0) {
    throw new Error(
      "Failed to deduct from the source account. Possible insufficient funds or conflict."
    );
  }

  // Step 3: Add the amount to the destination account
  const updateDestination = await accountsCollection.updateOne(
    { account_id: 2 },
    { $inc: { balance: +100 } },
    { session }
  );

  if (updateDestination.matchedCount === 0) {
    throw new Error("Failed to credit the destination account.");
  }

  // Step 4: Record the transaction
  const transactionsCollection = db.collection("transactions");
  await transactionsCollection.insertOne(
    {
      from_account_id: 1,
      to_account_id: 2,
      amount: 100,
      transaction_date: new Date(),
    },
    { session }
  );

  // Step 5: Commit the transaction
  await session.commitTransaction();
  console.log("Transaction committed successfully");
} catch (error) {
  // If an error occurred, abort the transaction
  await session.abortTransaction();
  console.error("Transaction aborted due to an error:", error);
} finally {
  // End the session
  session.endSession();
}
```

- MongoDB’s WiredTiger storage engine uses document-level locking. When a document is accessed within a transaction, MongoDB automatically locks the document to ensure that no other transaction can modify it until the current transaction is completed (either committed or aborted).

- When you perform the initial balance check (findOne() operation) within the transaction, MongoDB locks the document. This lock is held until the transaction is either committed or aborted, preventing other transactions from modifying the document during this time.

- This means that after you check the balance and decide to proceed with the update, the document is still locked, ensuring that no other operations have modified the balance in the meantime.
