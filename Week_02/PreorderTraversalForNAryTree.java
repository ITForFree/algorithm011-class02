import java.util.ArrayList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 */
public class PreorderTraversalForNAryTree {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        // 递归算法
        if(root == null) {
            return list;
        }
        list.add(root.val);
        for(Node node : root.children) {
            preorder(node);
        }
        return list;
    }

}
