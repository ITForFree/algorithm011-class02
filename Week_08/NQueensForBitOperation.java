import java.util.ArrayList;
import java.util.List;

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
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class NQueensForBitOperation {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] place = new int[n];
        dfs(n, 0, 0, 0, 0, place);
        return ans;
    }

    public void dfs(int n, int row, int cPos, int nPos, int pPos, int[] place) {
        if (row == n) {
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                int temp = place[i];
                for (int j = 0; j <= temp - 1; j++) sb.append('.');
                sb.append('Q');
                for (int j = temp + 1; j < n; j++) sb.append('.');
                cur.add(sb.toString());
            }
            ans.add(new ArrayList(cur));
        }
        // 得到当前所有的空位
        int bits = (~(cPos | nPos | pPos)) & ((1 << n) - 1);
        while (bits > 0) {
            // 取到最低位的1
            int pick = bits & (-bits);
            // 在 p 位置上放入皇后
            bits = bits & (bits - 1);
            int temp = pick, placeTemp = -1;
            while (temp > 0) {
                temp = temp >> 1;
                placeTemp++;
            }
            place[row] = placeTemp;
            dfs(n, row + 1, cPos | pick, (nPos | pick) << 1, (pPos | pick) >> 1, place);
        }
    }
}
