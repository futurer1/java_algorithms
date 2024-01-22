
package java_algorithms.binary_tree;

public class BinaryTree {

    /**
     * Несортированный массив всех значений в дереве
     */
    public List<Integer> getInorderValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    /**
     * Рекурсивно обходит ветви
     */
    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }
}

/**
 * Узел дерева, из них составляется дерево
 */
class TreeNode {
  
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode() {}
  
    TreeNode(int val) {
        this.val = val;
    }
  
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
