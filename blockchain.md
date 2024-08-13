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


# Usage

## Smart contract

