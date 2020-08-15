/**
 * 552. 学生出勤记录 II
 * 给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。
 * 学生出勤记录是只包含以下三个字符的字符串：
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。
 * 示例 1:
 * 输入: n = 2
 * 输出: 8
 * 解释：
 * 有8个长度为2的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数超过一次。
 */
public class StudentAttendanceRecordII {
    long M=1000000007;
    public int checkRecord(int n) {
        // 动态规划
        // dp[i] 表示长度为 n 的可奖励字符串的数目(只包含字母 L 和 P)
        // 递推公式为：dp[i] = 2dp[i−1] + dp[i−4]
        long[] dp = new long[n <= 5 ? 6 : n + 1];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        for(int i = 4; i <= n; i++) {
            // M - dp[i-4] 为取余的特殊处理，防止 n 比较大时出现负数
            // 也可用 dp[i] = (2 * dp[i-1] - dp[i-4] + M) % M，要加一个 M 不然 2*dp[i-1] - dp[i-4] 可能是负数，导致最后sum的结果不对
            dp[i] = (2 * (dp[i-1])) % M  + (M - dp[i-4]) % M;
        }
        // 没有 A 时，那么可奖励字符串的数目就是 dp[n]
        long sum = dp[n];
        // 有 A 时，且 A 出现在字符串 i 时
        for(int i = 1; i <= n; i++) {
            sum += (dp[i-1] * dp[n-i]) % M;
        }
        return (int)(sum % M);
    }
}
