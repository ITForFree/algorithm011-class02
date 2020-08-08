import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        // 动态规划
        if(n == 0) {
            return new ArrayList<>();
        }
        // dp[i] = "(" + dp[j] + ")" + dp[i- j - 1] , j = 0, 1, ..., i - 1
        // 这里把dp 数组变成列表，方便调用而已
        List<List<String>> dp = new LinkedList<>();
        dp.add(Arrays.asList(""));

        for (int i = 1; i <= n; i++) {
            List<String> nList = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                List<String> inside = dp.get(j);
                // “剩下的括号对数” + “可能的括号对数”(j) = i - 1，故 “剩下的括号对数” = i - j - 1
                List<String> tail = dp.get(i - j - 1);
                for (int k = 0; k < inside.size(); k++) {
                    for (int l = 0; l < tail.size(); l++) {
                        nList.add("(" + inside.get(k) + ")" + tail.get(l));
                    }
                }
            }
            dp.add(nList);
        }
        return dp.get(n);
    }
}
