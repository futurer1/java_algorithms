## Binary Tree

!()[inorder_1.jpg]

```java
BinaryTree binaryTree = new BinaryTree();
TreeNode treeNode = new TreeNode(
        1,
        null,
        new TreeNode(2,
                new TreeNode(3, null, null),
                null)
        );
List<Integer> res = binaryTree.inorderTraversal(treeNode);
System.out.println(res);
```

### Filled Object:

!()[BinaryTree.png]
