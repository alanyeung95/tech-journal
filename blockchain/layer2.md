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

# ZRO-KNOWLEDGE ROLLUP
## WHAT ARE ZERO-KNOWLEDGE ROLLUPS?
ZK-rollup operators submit a summary of the changes required to represent all the transactions in a batch rather than sending each transaction individually. They also produce validity proofs to prove the correctness of their changes.

The ZK-rollup's state is maintained by a smart contract deployed on the Ethereum network. To update this state, ZK-rollup nodes must submit a validity proof for verification. As mentioned, the validity proof is a cryptographic assurance that the state-change proposed by the rollup is really the result of executing the given batch of transactions. This means that ZK-rollups only need to provide validity proofs to finalize transactions on Ethereum instead of posting all transaction data on-chain like optimistic rollups.

There are no delays when moving funds from a ZK-rollup to Ethereum because exit transactions are executed once the ZK-rollup contract verifies the validity proof. Conversely, withdrawing funds from optimistic rollups is subject to a delay to allow anyone to challenge the exit transaction with a fraud proof.

ZK-rollups write transactions to Ethereum as calldata. calldata is where data that is included in external calls to smart contract functions gets stored. Information in calldata is published on the blockchain, allowing anyone to reconstruct the rollup’s state independently. ZK-rollups use compression techniques to reduce transaction data—for example, accounts are represented by an index rather than an address, which saves 28 bytes of data. On-chain data publication is a significant cost for rollups, so data compression can reduce fees for users.

## HOW DO ZK-ROLLUPS INTERACT WITH ETHEREUM?
The ZK-rollup's core architecture is made up of the following components:

1. On-chain contracts: As mentioned, the ZK-rollup protocol is controlled by smart contracts running on Ethereum. This includes the main contract which stores rollup blocks, tracks deposits, and monitors state updates. Another on-chain contract (the verifier contract) verifies zero-knowledge proofs submitted by block producers. Thus, Ethereum serves as the base layer or "layer 1" for the ZK-rollup.

2. Off-chain virtual machine (VM): While the ZK-rollup protocol lives on Ethereum, transaction execution and state storage happen on a separate virtual machine independent of the EVM. This off-chain VM is the execution environment for transactions on the ZK-rollup and serves as the secondary layer or "layer 2" for the ZK-rollup protocol. Validity proofs verified on Ethereum Mainnet guarantee the correctness of state transitions in the off-chain VM.

ZK-rollups are "hybrid scaling solutions"—off-chain protocols that operate independently but derive security from Ethereum. Specifically, the Ethereum network enforces the validity of state updates on the ZK-rollup and guarantees the availability of data behind every update to the rollup's state. As a result, ZK-rollups are considerably safer than pure off-chain scaling solutions, such as sidechains, which are responsible for their security properties, or validiums, which also verify transactions on Ethereum with validity proofs, but store transaction data elsewhere.

### Data availability
ZK-rollups publish state data for every transaction processed off-chain to Ethereum. With this data, it is possible for individuals or businesses to reproduce the rollup’s state and validate the chain themselves. Ethereum makes this data available to all participants of the network as calldata.

ZK-rollups don’t need to publish much transaction data on-chain because validity proofs already verify the authenticity of state transitions. Nevertheless, storing data on-chain is still important because it allows permissionless, independent verification of the L2 chain's state which in turn allows anyone to submit batches of transactions, preventing malicious operators from censoring or freezing the chain.

On-chain is required for users to interact with the rollup. Without access to state data users cannot query their account balance or initiate transactions (e.g., withdrawals) that rely on state information.

### Transaction finality
Ethereum acts as a settlement layer for ZK-rollups: L2 transactions are finalized only if the L1 contract accepts the validity proof. This eliminates the risk of malicious operators corrupting the chain (e.g., stealing rollup funds) since every transaction must be approved on Mainnet. Also, Ethereum guarantees that user operations cannot be reversed once finalized on L1.

## HOW DO ZK-ROLLUPS WORK?

### Transactions
Users in the ZK-rollup sign transactions and submit to L2 operators for processing and inclusion in the next batch. In some cases, the operator is a centralized entity, called a sequencer, who executes transactions, aggregates them into batches, and submits to L1. The sequencer in this system is the only entity allowed to produce L2 blocks and add rollup transactions to the ZK-rollup contract.

Other ZK-rollups may rotate the operator role by using a proof-of-stake validator set. Prospective operators deposit funds in the rollup contract, with the size of each stake influencing the staker’s chances of getting selected to produce the next rollup batch. The operator’s stake can be slashed if they act maliciously, which incentivizes them to post valid blocks.

### How ZK-rollups publish transaction data on Ethereum
As explained, transaction data is published on Ethereum as calldata. calldata is a data area in a smart contract used to pass arguments to a function and behaves similarly to memory. While calldata isn’t stored as part of Ethereum’s state, it persists on-chain as part of the Ethereum chain's history logs(opens in a new tab). calldata does not affect Ethereum's state, making it a cheap way to store data on-chain.

The calldata keyword often identifies the smart contract method being called by a transaction and holds inputs to the method in the form of an arbitrary sequence of bytes. ZK-rollups use calldata to publish compressed transaction data on-chain; the rollup operator simply adds a new batch by calling the required function in the rollup contract and passes the compressed data as function arguments. This helps reduce costs for users since a large part of rollup fees go toward storing transaction data on-chain.

### Validity proofs
The new state root that the ZK-rollup operator submits to the L1 contract is the result of updates to the rollup’s state. Say Alice sends 10 tokens to Bob, the operator simply decreases Alice’s balance by 10 and increments Bob’s balance by 10. The operator then hashes the updated account data, rebuilds the rollup's Merkle tree, and submits the new Merkle root to the on-chain contract.

But the rollup contract won’t automatically accept the proposed state commitment until the operator proves the new Merkle root resulted from correct updates to the rollup’s state. The ZK-rollup operator does this by producing a validity proof, a succinct cryptographic commitment verifying the correctness of batched transactions.

Validity proofs allow parties to prove the correctness of a statement without revealing the statement itself—hence, they are also called zero-knowledge proofs. ZK-rollups use validity proofs to confirm the correctness of off-chain state transitions without having to re-execute transactions on Ethereum. These proofs can come in the form of a ZK-SNARK (Zero-Knowledge Succinct Non-Interactive Argument of Knowledge) or ZK-STARK (Zero-Knowledge Scalable Transparent Argument of Knowledge).

#### ZK-SNARKs

For the ZK-SNARK protocol to work, creating a Common Reference String (CRS) is necessary: the CRS provides public parameters for proving and verifying validity proofs. The security of the proving system depends on the CRS setup; if information used to create public parameters fall into the possession of malicious actors they may be able to generate false validity proofs.

Trusted setups are used because they increase the security of the CRS setup. As long as one honest participant destroys their input, the security of the ZK-SNARK system is guaranteed. Still, this approach requires trusting those involved to delete their sampled randomness and not undermine the system's security guarantees.

Trust assumptions aside, ZK-SNARKs are popular for their small proof sizes and constant-time verification. As proof verification on L1 constitutes the larger cost of operating a ZK-rollup, L2s use ZK-SNARKs to generate proofs that can be verified quickly and cheaply on Mainnet.

#### ZK-STARKs

Like ZK-SNARKs, ZK-STARKs prove the validity of off-chain computation without revealing the inputs. However, ZK-STARKs are considered an improvement on ZK-SNARKs because of their scalability and transparency.

ZK-STARKs are 'transparent', as they can work without the trusted setup of a Common Reference String (CRS). Instead, ZK-STARKs rely on publicly verifiable randomness to set up parameters for generating and verifying proofs.

ZK-STARKs also provide more scalability because the time needed to prove and verify validity proofs increases quasilinearly in relation to the complexity of the underlying computation. With ZK-SNARKs, proving and verification times scale linearly in relation to the size of the underlying computation. This means ZK-STARKs require less time than ZK-SNARKs for proving and verifying when large datasets are involved, making them useful for high-volume applications.

ZK-STARKs are also secure against quantum computers, while the Elliptic Curve Cryptography (ECC) used in ZK-SNARKs is widely believed to be susceptible to quantum computing attacks. The downside to ZK-STARKs is that they produce larger proof sizes, which are more expensive to verify on Ethereum.

### How do validity proofs work in ZK-rollups?
https://ethereum.org/developers/docs/scaling/zk-rollups#validity-proofs-in-zk-rollups
