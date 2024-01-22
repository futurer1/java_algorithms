
package java_algorithms.binary_tree;

import java.util.List;

public class Main {
    public static void main(String[] args) {

      BinaryTree binaryTree = new BinaryTree();
        TreeNode treeNode = new TreeNode(
            1, // value
            null, // left
            new TreeNode(2, // value
                    new TreeNode(3, null /* left */, null /* right */),  // left
                    null //right
            ) //right
        );
        List<Integer> res = binaryTree.inorderTraversal(treeNode);
        System.out.println(res);

        // out:
        // [1, 3, 2]
    }
}
