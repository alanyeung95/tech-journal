## Byzantine Fault Tolerance
Byzantine Fault Tolerance is a mechanism that enables a decentralized, trustless network to **function** even in the presence of malfunctioning or malicious nodes.

Most common consensus mechanisms, like proof of work and proof of stake, are designed to be Byzantine Fault Tolerant. BFT is based on the **Byzantine Generals Problem**, an abstract problem that discusses the issue of coping with node failure.

## Type of chain
ref: https://101blockchains.com/public-vs-private-blockchain/
### Public Blockchain
1. permissionlss. Anyone can join whenever they want. Basically, there are no restrictions when it comes to participation. 
2. anyone can see the ledger and take part in the consensus process.
3. decentralized
4. low efficiency

### Private Blockchain
1. these solutions are mainly for the internal systems of an enterprise.
2. full Privacy. 
3. low Fees. Unlike public blockchain platforms, the transaction fee does not increase based on the number of requests
4. high efficiency. Only allows a handful of people in the network. In many cases, they even have certain tasks to complete. So, there is no way they can take up extra resources and slow down the platform.

example: Quorum

### Consortium Blockchain
ref: https://medium.com/flowchain-knowledgecamp/公鏈-私有鏈-聯盟鏈-3e91fe5d6403

由公司或機構為單位各自擔任節點屬於大家常常聽見的 B2B 架構（Business to Business）也就是企業與企業間的互動，可以做為同業機構間價值流通的可信平台。
聯盟鏈的去中心化程度居於公有鏈與私有鏈之間，本質跟私有鏈比較接近，優點在於可以在不同公司間訂定相同的規則與規格，以增加同質企業間更高效率、更低成本的價值流通，最常被聯想到的場景即是銀行與銀行間的聯盟鏈架構，他們可以共同協議出一套大家都認可的通用記帳標準，讓每一個不同規格的銀行之間，可以安全且有效率地透過這條聯盟鏈，來做為銀行與銀行間的價值流通管道。

example: Hyperledger Fabric、R3 Corda
