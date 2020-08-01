/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 * 输入的字符串长度不会超过1000。
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        // 动态规划
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for(int i = n-1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i+1 < 3 || dp[i+1][j-1]);
                if(dp[i][j]) {
                    res ++;
                }
            }
        }
        return res;
    }
}
