# Layer 2
Layer 2 (L2) is a collective term to describe a specific set of Ethereum scaling solutions. A layer 2 is a separate blockchain that extends Ethereum and inherits the security guarantees of Ethereum

Examples of layer 2 projects include "rollups" on Ethereum and the Lightning Network on Bitcoin. All user transaction activity on these layer 2 projects can ultimately settle back to the layer 1 blockchain.

The main goal of layer 2 is to increase transaction throughput (higher transactions per second) without sacrificing decentralization or security.

## Rollups
Rollups bundle (or ’roll up’) hundreds of transactions into a single transaction on layer 1. This distributes the L1 transaction fees across everyone in the rollup, making it cheaper for each user.

The transaction data in the rollup is submitted to layer 1, but the execution is done separately by the rollup. By submitting transaction data onto layer 1, rollups inherit the security of Ethereum. This is because once the data is uploaded to layer 1, reverting a rollup transaction requires reverting Ethereum. There are two different approaches to rollups: optimistic and zero-knowledge - they differ primarily on how this transaction data is submitted to L1.

### Optimistic rollups
Optimistic rollups are 'optimistic' in the sense that transactions are assumed to be valid, but can be challenged if necessary. If an invalid transaction is suspected, a fault proof is run to see if this has taken place.

### Zero-knowledge rollups
Zero-knowledge rollups use validity proofs where transactions are computed off-chain, and then compressed data is supplied to Ethereum Mainnet as a proof of their validity.

# OPTIMISTIC ROLLUP

## WHAT IS AN OPTIMISTIC ROLLUP?
An optimistic rollup is an approach to scaling Ethereum that involves moving computation and state storage off-chain. Optimistic rollups execute transactions outside of Ethereum, but post transaction data to Mainnet as calldata.

Optimistic rollups are considered “optimistic” because they assume off-chain transactions are valid and don't publish proofs of validity for transaction batches posted on-chain. This separates optimistic rollups from zero-knowledge rollups that publish cryptographic proofs of validity for off-chain transactions.

Optimistic rollups instead rely on a fraud-proving scheme to detect cases where transactions are not calculated correctly. After a rollup batch is submitted on Ethereum, there's a time window (called a challenge period) during which anyone can challenge the results of a rollup transaction by computing a fraud proof.

If the fraud proof succeeds, the rollup protocol re-executes the transaction(s) and updates the rollup's state accordingly. The other effect of a successful fraud proof is that the sequencer responsible for including the incorrectly executed transaction in a block receives a penalty.

## HOW DO OPTIMISTIC ROLLUPS INTERACT WITH ETHEREUM?
Each optimistic rollup is managed by a set of smart contracts deployed on the Ethereum network. Optimistic rollups process transactions off the main Ethereum chain, but post off-chain transactions (in batches) to an on-chain rollup contract. Like the Ethereum blockchain, this transaction record is immutable and forms the "optimistic rollup chain."

### On-chain contracts
The optimistic rollups's operation is controlled by smart contracts running on Ethereum. This includes contracts that store rollup blocks, monitor state updates on the rollup, and track user deposits. In this sense, Ethereum serves as the base layer or "layer 1" for optimistic rollups.

### Off-chain virtual machine (VM)
Although contracts managing the optimistic rollup protocol run on Ethereum, the rollup protocol performs computation and state storage on another virtual machine separate from the Ethereum Virtual Machine. The off-chain VM is where applications live and state changes are executed; it serves as the upper layer or "layer 2" for an optimistic rollup.



