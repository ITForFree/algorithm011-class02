import java.util.*;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例:
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class NQueens {
    private Set<Integer> col = new HashSet<>();
    private Set<Integer> pie = new HashSet<>();
    private Set<Integer> na = new HashSet<>();
    public void dfs(List<List<String>> res, List<String> list, int row, int n) {
        // terminator
        if(row == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        // current level
        for(int i = 0; i < n; i++) {
            if(col.contains(i) || pie.contains(row + i) || na.contains(row - i)) {
                continue;
            }

            // construct the output
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            // update the flage
            list.add(rowString);
            col.add(i);
            pie.add(row + i);
            na.add(row - i);

            // drill down
            dfs(res, list, row + 1, n);

            // reverse states
            list.remove(rowString);
            col.remove(i);
            pie.remove(row + i);
            na.remove(row - i);
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList(), 0, n);
        return res;
    }
}
