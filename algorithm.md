# Linked List
```
public ListNode reverseList(ListNode head) {
    ListNode prevHead = null;
    while(head != null){
        ListNode recordNext = head.next;
        head.next = prevHead;
        prevHead = head;
        head = recordNext;
    }
    return prevHead;
}
```

# Tree
Merge binary tree
```
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
```
Invert binary tree
```
public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
}
```
```
https://leetcode.com/problems/range-sum-of-bst/
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

 

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        if root == None:
            return 0
        elif low<=root.val and high>=root.val:
            return root.val + self.rangeSumBST(root.left, low, high) +  self.rangeSumBST(root.right, low, high)
            
        elif low>root.val:
            return  self.rangeSumBST(root.right, low, high)     
        elif high<root.val:
            return self.rangeSumBST(root.left, low, high) 
        else:
            return 0
```
# DP
```
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
          dp[i] = dp[i-1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

# windos
```
#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'fewestCoins' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING coins as parameter.
#

def fewestCoins(coins):
    charSet = set()
    for char in coins:
        charSet.add(char) 
    print(charSet)  

    windowSize = len(charSet)
    while(True):   
        for i in range(len(coins) - windowSize +1):
            windowString = coins[i:i+windowSize]
            find=True
            for char in charSet:
                if (char not in windowString):
                    find=False
            if (find==True):
                return len(windowString)
        windowSize+=1
     

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    coins = input()

    result = fewestCoins(coins)

    fptr.write(str(result) + '\n')

    fptr.close()
```
