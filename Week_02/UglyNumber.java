import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 49. 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 */
public class UglyNumber {
    public int nthUglyNumber(int n) {
        // 借助优先队列实现，时间复杂度：O(NlogN)
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> hashSet = new HashSet<>();
        long[] primes = new long[]{2,3,5};
        for(long prime : primes) {
            pq.offer(prime);
            hashSet.add(prime);
        }
        long num = 1;
        for(int i = 1; i < n; i ++) {
            num = pq.poll();
            for(int j = 0; j < 3; j ++) {
                if(!hashSet.contains(num * primes[j])) {
                    pq.offer(num * primes[j]);
                    hashSet.add(num * primes[j]);
                }
            }
        }
        return (int)num;
    }
}
