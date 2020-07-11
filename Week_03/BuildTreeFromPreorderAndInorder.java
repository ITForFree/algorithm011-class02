import java.util.HashMap;
import java.util.Map;

/**
 *105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTreeFromPreorderAndInorder {
    private Map<Integer, Integer> idxMap;
    public TreeNode helper(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft,
                           int inorderRight) {
        if(preorderLeft > preorderRight) {
            return null;
        }
        int preorderRoot = preorderLeft;
        int inorderRoot = idxMap.get(preorder[preorderRoot]);
        TreeNode root = new TreeNode(preorder[preorderRoot]);
        int leftSubtreeSize = inorderRoot - inorderLeft;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = helper(preorder, inorder, preorderLeft+1, preorderLeft+leftSubtreeSize, inorderLeft, inorderRoot-1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = helper(preorder,inorder, preorderLeft+leftSubtreeSize+1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        // 构造哈希映射，快速定位根节点
        idxMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return helper(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
