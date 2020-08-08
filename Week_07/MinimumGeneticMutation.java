import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * 433. 最小基因变化
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 * 注意:
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 示例 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 返回值: 1
 * 示例 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 返回值: 2
 * 示例 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 返回值: 3
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        // 双向 BFS
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (bankSet.size() == 0 || !bankSet.contains(end)) return -1;

        char[] basic = {'A', 'C', 'G', 'T'};
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(start);
        endSet.add(end);
        Set<String> visited = new HashSet<>();

        // 不包含起点，因此初始化的时候count为0
        int count = 0;
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (startSet.size() > endSet.size()) {
                Set<String> set = startSet;
                startSet = endSet;
                endSet = set;
            }
            // nextLevelVisited 在扩散完成以后，会成为新的 startSet
            Set<String> nextLevelVisited = new HashSet<>();
            for (String currGene : startSet) {
                char[] value = currGene.toCharArray();
                for (int i = 0; i < value.length; i++) {
                    char old = value[i];
                    for (char c : basic) {
                        if (old == c) continue;
                        value[i] = c;
                        String nextGene = new String(value);
                        if (endSet.contains(nextGene)) return count + 1;
                        if (!visited.contains(nextGene) && bankSet.contains(nextGene)) {
                            visited.add(nextGene);
                            nextLevelVisited.add(nextGene);
                        }
                    }
                    // 恢复状态
                    value[i] = old;
                }
            }
            // 表示从 start 这一侧向外扩散了一层
            startSet = nextLevelVisited;
            count++;

        }
        return -1;
    }
}
