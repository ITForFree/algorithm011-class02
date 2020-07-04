import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的前序遍历。
 */
public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 迭代，时间复杂度：O(n)
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if(root == null) {
            return res;
        }
        stack.add(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            res.add(node.val);
            if(node.right != null) {
                stack.add(node.right);
            }
            if(node.left != null) {
                stack.add(node.left);
            }
        }
        return res;
    }
}
