# P vs NP-Hard

https://stackoverflow.com/questions/1857244/what-are-the-differences-between-np-np-complete-and-np-hard

# Red-Black Tree

Red-black trees are a type of self-balancing binary search tree, an advanced form of binary trees that ensure operations such as insertions, deletions, and lookups can be performed in logarithmic time complexity, 𝑂(log 𝑛). This self-balancing property is crucial for maintaining performance efficiencies in dynamic datasets where frequent modifications are required.

Key Properties of Red-Black Trees:

1. Every node is either red or black.
2. The root is always black.
3. All leaves (NIL nodes) are black.
4. If a node is red, then both its children are black. This is sometimes referred to as the "no two reds" rule, which means that no red node can have a red child.
5. Every path from a given node to any of its descendant NIL nodes goes through the same number of black nodes. This is the black-height property of the tree.

## Why Not Just Use Normal Binary Trees?

Worst-Case Scenarios: In a normal BST, if you insert data that is already sorted, the tree degenerates into a linked list, leading to O(n) time complexity for search, insert, and delete operations.

Lack of Rebalancing: Normal BSTs lack any mechanism to rebalance themselves, making them unsuitable for applications where data is dynamically inserted and removed, as the cost of maintaining the tree can become prohibitively expensive.

## Purpose of Red and Black Colors

Enforcing Balance Rules: The red and black colors help in maintaining essential balance properties of the tree. Specifically, the rule that each path from a given node to any of its descendant NIL nodes must contain the same number of black nodes (black-height) helps ensure that the tree remains balanced.

Preventing Deep Tree Structures: By requiring that red nodes can only have black children (i.e., no two red nodes can be consecutive), red-black trees prevent the formation of long chains of like-colored nodes. This rule helps avoid the creation of a significantly unbalanced tree, which could degrade performance to linear time in the worst case.

## Usage

MySQL uses the Red-Black tree for indexes on tables.

ref: https://www.geeksforgeeks.org/introduction-to-red-black-tree, https://bmsce.ac.in/Content/CS/Unit-2-ADA-NM.pdf

# B-Tree

1. Every node has at most m children.
2. Every node, except for the root and the leaves, has at least ⌈m/2⌉ children.
3. The root node has at least two children unless it is a leaf.
4. All leaves appear on the same level.
5. A non-leaf node with k children contains k−1 keys.

## Why Use B-Trees?

Efficient Disk Accesses
Minimized Disk I/O Operations: Because nodes can have many children, B-Trees become relatively shallow compared to binary trees, even for a large number of entries. Fewer levels in the tree mean fewer disk reads/writes during operations, which is crucial because disk access time is typically the bottleneck in storage operations.

How B-Trees Minimize Disk I/O?

1. Each level in a B-Tree may correspond to a disk read or write if the node data is not already in memory. When a query or transaction involves searching for, inserting, or deleting a key in a B-Tree, the operation starts at the root node and traverses down to the leaf nodes.
2. If a B-Tree has fewer levels (i.e., it is "shallower"), the path from the root to any leaf node—where most keys are found and manipulated—is shorter. This means that fewer nodes need to be accessed, and consequently, fewer disk operations are required.
