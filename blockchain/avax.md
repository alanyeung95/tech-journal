## Overall
https://docs.avax.network/learn/platform-overview/avalanche-consensus/

## Node
3 states: natural, true, false

## Avalanche Consensus
https://www.youtube.com/watch?v=Sfb8G54AM_4

Repeat until decision is made:
1. A validator is presented with a set of transactions that have been issued and asked to decide which transactions to “Accept.”
2. The validator selects a subset of these transactions that do not conflict, marks them as preferred, and attempts to accept them over the network
3. This validator selects **K nodes** from the entire validator list 
   * consecutiveSuccesses meet the threshold
   * decide(preference)
4. The validator state will change to the color of the majority neighbours (quorum size)

Even it is 50/50 situation, it will fall to any of the state
