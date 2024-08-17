# Intro

reference: https://www.youtube.com/watch?v=SSo_EIwHSd4

## Definition

Chain of block that include information

## Block struction

1. Data
2. Hash
3. Hash of previous block

## Data integrity

if data of intermediary blocks got change, the hash change and will fail the verification of following block

## Proof of work

it take 10 minutes to calculate proof of work and add the new block to the chain

## P2P network

When someone joint the network, he get the full copy of blockchain
When someone created a new block, everyone on the network verify the new block
After that, everyone on the network create a concensus

# Crypto wallet

step to generate a wallet address

1. generate a private key
2. generate a public key from private key
3. hash the public key plus other information like mainnet address as a prefix, checksum as suffix

## how crypto wallets like metamask helps user deposit or withdraw money?

MetaMask facilitates the interaction with smart contracts and token transfers by managing the signing process using the user's private key, but it does so without ever having access to the private key in an unencrypted form. Here’s how it handles transactions and smart contract interactions securely:

### Transaction Signing Process

1. **Local Key Storage**:

   - When a user creates a MetaMask wallet, the private keys are generated and stored locally on the user's device, encrypted under a password that only the user knows.
   - The private keys remain encrypted and are only accessible when the user enters their password.

2. **User Interaction**:

   - When a user decides to interact with a smart contract, for example, to deposit tokens, they initiate a transaction in the MetaMask interface.
   - MetaMask constructs the transaction based on the user's instructions. This includes defining the recipient address (the smart contract address), the amount of tokens to send, and any additional data required by the smart contract.

3. **Transaction Signing**:

   - Once the transaction is ready, MetaMask prompts the user to confirm the details and approve the transaction.
   - When the user approves the transaction, MetaMask uses the user's password to decrypt the private key locally (this operation also happens strictly on the user's device).
   - The transaction is then signed locally using the decrypted private key. This cryptographic signature proves that the transaction was authorized by the owner of the private keys without revealing the keys themselves.

4. **Sending to the Network**:
   - The signed transaction (which includes the cryptographic signature but not the private key) is sent over the network to the Ethereum blockchain.
   - The blockchain nodes verify the transaction against the user's public key, confirming it was indeed signed by the owner of the private keys.

### MetaMask’s Role

- **MetaMask does not execute transactions itself**; it merely acts as an interface to prepare and sign transactions before broadcasting them to the blockchain network.
- **No Exposure of Private Keys**: At no point does MetaMask send the user's private keys over the network. The keys are decrypted and used locally on the user’s device and only for the duration of signing a transaction.

# Type of chain

ref: https://101blockchains.com/public-vs-private-blockchain/

## Public Blockchain

1. permissionlss. Anyone can join whenever they want. Basically, there are no restrictions when it comes to participation.
2. anyone can see the ledger and take part in the consensus process.
3. decentralized
4. low efficiency

## Private Blockchain

1. these solutions are mainly for the internal systems of an enterprise.
2. full Privacy.
3. low Fees. Unlike public blockchain platforms, the transaction fee does not increase based on the number of requests
4. high efficiency. Only allows a handful of people in the network. In many cases, they even have certain tasks to complete. So, there is no way they can take up extra resources and slow down the platform.

example: Quorum

## Consortium Blockchain

ref: https://medium.com/flowchain-knowledgecamp/公鏈-私有鏈-聯盟鏈-3e91fe5d6403

由公司或機構為單位各自擔任節點屬於大家常常聽見的 B2B 架構（Business to Business）也就是企業與企業間的互動，可以做為同業機構間價值流通的可信平台。
聯盟鏈的去中心化程度居於公有鏈與私有鏈之間，本質跟私有鏈比較接近，優點在於可以在不同公司間訂定相同的規則與規格，以增加同質企業間更高效率、更低成本的價值流通，最常被聯想到的場景即是銀行與銀行間的聯盟鏈架構，他們可以共同協議出一套大家都認可的通用記帳標準，讓每一個不同規格的銀行之間，可以安全且有效率地透過這條聯盟鏈，來做為銀行與銀行間的價值流通管道。

example: Hyperledger Fabric、R3 Corda

# Others

## Byzantine Fault Tolerance

Byzantine Fault Tolerance is a mechanism that enables a decentralized, trustless network to **function** even in the presence of malfunctioning or malicious nodes.

Most common consensus mechanisms, like proof of work and proof of stake, are designed to be Byzantine Fault Tolerant. BFT is based on the **Byzantine Generals Problem**, an abstract problem that discusses the issue of coping with node failure.
