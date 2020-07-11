import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class CombineNums {
    private  List<List<Integer>> res = new LinkedList();
    private  int n;
    private  int k;
    public  void backtrack(int first, LinkedList<Integer> curr) {
        // terminator
        if(curr.size() == k) {
            res.add(new LinkedList(curr));
        }
        for(int i = first; i <= n; i ++) {
            curr.add(i);
            // recursion
            backtrack(i + 1, curr);
            // reverse
            curr.removeLast();
        }
    }
    public  List<List<Integer>> combine(int n, int k) {
        n = n;
        k = k;
        backtrack(1, new LinkedList<Integer>());
        return res;
    }
}
