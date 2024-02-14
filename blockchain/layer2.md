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

## HOW DO OPTIMISTIC ROLLUPS WORK?

### State commitments
At any point in time, the optimistic rollup’s state (accounts, balances, contract code, etc.) is organized as a Merkle tree called a “state tree”. The root of this Merkle tree (state root), which references the rollup’s latest state, is hashed and stored in the rollup contract. Every state transition on the chain produces a new rollup state, which an operator commits to by computing a new state root.

The operator is required to submit both old state roots and new state roots when posting batches. If the old state root matches the existing state root in the on-chain contract, the latter is discarded and replaced with the new state root.

### Fraud proving
optimistic rollups allow anyone to publish blocks without providing proofs of validity. However, to ensure the chain remains safe, optimistic rollups specify a time window during which anyone can dispute a state transition. Hence, rollup blocks are called “assertions” since anyone can dispute their validity

If someone disputes an assertion, then the rollup protocol will initiate the fraud proof computation. Every type of fraud proof is interactive—someone must post an assertion before another person can challenge it. The difference lies in how many rounds of interaction are required to compute the fraud proof.

Single-round interactive proving schemes replay disputed transactions on L1 to detect invalid assertions. The rollup protocol emulates the re-execution of the disputed transaction on L1 (Ethereum) using a verifier contract, with the computed state root determining who wins the challenge. If the challenger's claim about the rollup’s correct state is correct, the operator is penalized by having their bond slashed.

### Multi-round interactive proving
Multi-round interactive proving involves a back-and-forth protocol between the asserter and challenger overseen by an L1 verifier contract, which ultimately decides the lying party. After an L2 node challenges an assertion, the asserter is required to divide the disputed assertion into two equal halves. Each individual assertion in this case will contain as many steps of computation as the other.

The challenger will then choose what assertion it wants to challenge. The dividing process (called a “bisection protocol”) continues until both parties are disputing an assertion about a single step of execution. At this point, the L1 contract will resolve the dispute by evaluating the instruction (and its result) to catch the fraudulent party.

The asserter is required to provide a “one-step proof” verifying the validity of the disputed single-step computation. If the asserter fails to provide the one-step proof, or the L1 verifier deems the proof invalid, they lose the challenge.

### Why fraud proofs matter for optimistic rollups
Fraud proofs are important because they facilitate trustless finality in optimistic rollups. Trustless finality is a quality of optimistic rollups that guarantees that a transaction—so long as it’s valid—will eventually be confirmed.

Malicious nodes can try to delay the confirmation of a valid rollup block by starting false challenges. However, fraud proofs will eventually prove the rollup block’s validity and cause it to be confirmed.

This also relates to another security property of optimistic rollups: the validity of the chain relies on the existence of one honest node. The honest node can advance the chain correctly by either posting valid assertions or disputing invalid assertions. Whatever the case, malicious nodes who enter into disputes with the honest node will lose their stakes during the fraud proving process.

### L1/L2 interoperability
#### Asset movement
https://ethereum.org/developers/docs/scaling/optimistic-rollups#asset-movement

#### EVM compatibility
For developers, the advantage of optimistic rollups is their compatibility—or, better still, equivalence—with the Ethereum Virtual Machine (EVM). EVM-compatible rollups comply with specifications in the Ethereum Yellow Paper(opens in a new tab) and support the EVM at the bytecode level.

EVM-compatibility in optimistic rollups has the following benefits:

i. Developers can migrate existing smart contracts on Ethereum to optimistic rollup chains without having to modify codebases extensively. This can save development teams time when deploying Ethereum smart contracts on L2.

## HOW DO OPTIMISTIC ROLLUP FEES WORK?
State write: Optimistic rollups publish transaction data and block headers (consisting of the previous block header hash, state root, batch root) to Ethereum as calldata. The minimum cost of an Ethereum transaction is 21,000 gas. Optimistic rollups can reduce the cost of writing the transaction to L1 by batching multiple transactions in a single block (which amortizes the 21k gas over multiple user transactions).

calldata: Beyond the base transaction fee, the cost of each state write depends on the size of calldata posted to L1. calldata costs are currently governed by EIP-1559(opens in a new tab), which stipulates a cost of 16 gas for non-zero bytes and 4 gas for zero bytes of calldata, respectively. To reduce user fees, rollup operators compress transactions to reduce the number of calldata bytes published on Ethereum.

L2 operator fees: This is the amount paid to the rollup nodes as compensation for computational costs incurred in processing transactions, much like gas fees on Ethereum. Rollup nodes charge lower transaction fees since L2s have higher processing capacities and aren't faced with the network congestions that force validators on Ethereum to prioritize transactions with higher fees.

## Pros and cons of optimistic rollups
### Pros
1. Offers massive improvements in scalability without sacrificing security or trustlessness.
2. Transaction data is stored on the layer 1 chain, improving transparency, security, censorship-resistance, and decentralization.
3. Computing fraud proofs is open to regular L2 node, unlike validity proofs (used in ZK-rollups) that require special hardware.
4. Compatibility with EVM and Solidity allows developers to port Ethereum-native smart contracts to rollups or use existing tooling to create new dapps.

### Cons
1. Centralized rollup operators (sequencers) can influence transaction ordering.
2. Security model relies on at least one honest node executing rollup transactions and submitting fraud proofs to challenge invalid state transitions.
3. Users must wait for the one-week challenge period to expire before withdrawing funds back to Ethereum.
4. Rollups must post all transaction data on-chain, which can increase costs.

## Arbitrum vs Optimism
The main technical difference between the two chains is that Optimism uses single-round fraud proofs while Arbitrum uses multi-round fraud proofs. In normal English, this means Optimism's method is faster but potentially more expensive due to higher gas fees because it's executed on the L1. Arbitrum's way takes more time but is more cost-effective.

Moreover, Optimism uses the Ethereum Virtual Machine (EVM), while Arbitrum has its own Arbitrum Virtual Machine (AVM). That means Optimism's programming language is limited to Solidity. Arbitrum supports all EVM programming languages.

## Optimism
https://docs.optimism.io/

## Arbitrum
https://docs.arbitrum.io/intro
