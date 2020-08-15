/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class NQueensIIForBitOperation {
    private static int size;
    private static int count;
    private static void solve(int row, int ld, int rd) {
        // ld - 撇，rd - 捺
        if(row == size) {
            count ++;
            return;
        }
        // 得到当前所有的空位
        int pos = size & (~(row | ld | rd));
        while(pos != 0) {
            int p = pos & (-pos); // 取到最低位的1
            pos -= p; // pos &= pos - 1; 在 p 位置上放入皇后
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }

    public static int totalNQueens(int n) {
        count = 0;
        // 所有可以放皇后的位置
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count;
    }
}
