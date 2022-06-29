## binary-tree-inorder-traversal
visit left node, then parent node, finally right node
### ans
```
# recursively
def inorderTraversal1(self, root):
    res = []
    self.helper(root, res)
    return res
    
def helper(self, root, res):
    if root:
        self.helper(root.left, res)
        res.append(root.val)
        self.helper(root.right, res)
 
# iteratively       
def inorderTraversal(self, root):
    res, stack = [], []
    while True:
        while root:
            stack.append(root)
            root = root.left
        if not stack:
            return res
        node = stack.pop()
        res.append(node.val)
        root = node.right
```

## invert-binary-tree
### iterative ans

def invertTree(self, root):
    stack = [root]
    while stack:
        node = stack.pop()
        if node:
            node.left, node.right = node.right, node.left
            stack += node.left, node.right
    return root
```

## maximum-depth-of-binary-tree

```
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

### iterative ans
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int max = 1;
        
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        
        nodes.push(root);
        depths.push(1);
        
        while (!nodes.empty()) {
            TreeNode curr = nodes.pop();
            int depth = depths.pop();
            
            if (curr.left == null && curr.right == null) {
                max = Math.max(max, depth);
            } 
            
            if (curr.right != null) {
                nodes.push(curr.right);
                depths.push(depth + 1);
            } 
            if (curr.left != null) {
                nodes.push(curr.left);
                depths.push(depth + 1);
            }
        }
        
        return max;
        
    }
}
```

## symmetric-tree
```
Input: root = [1,2,2,3,4,4,3]
Output: true
```

```
Input: root = [1,2,2,null,3,null,3]
Output: false
```

### ans (iterative)
```
 class Solution2:
  def isSymmetric(self, root):
    if root is None:
      return True

    stack = [[root.left, root.right]]

    while len(stack) > 0:
      pair = stack.pop(0)
      left = pair[0]
      right = pair[1]

      if left is None and right is None:
        continue
      if left is None or right is None:
        return False
      if left.val == right.val:
        stack.insert(0, [left.left, right.right])

        stack.insert(0, [left.right, right.left])
      else:
        return False
    return True
```
