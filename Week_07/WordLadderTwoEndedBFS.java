import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class WordLadderTwoEndedBFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 双向 BFS
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        // beginSet 和 endSet 交替使用，等价于单向 BFS 里使用队列，每次扩散都要加到总的 visited 里
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
        // 包含起点，因此初始化的时候步数为 1
        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            // nextLevelVisited 在扩散完成以后，会成为新的 beginSet
            Set<String> nextLevelVisited = new HashSet<String>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (old == c) {
                            continue;
                        }
                        chars[i] = c;
                        String nextWord = String.valueOf(chars);
                        if (wordSet.contains(nextWord)) {
                            if (endSet.contains(nextWord)) {
                                return len + 1;
                            }
                            if (!visited.contains(nextWord) && wordList.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                        // 恢复状态
                        chars[i] = old;
                    }
                }
            }
            // 表示从 begin 这一侧向外扩散了一层
            beginSet = nextLevelVisited;
            len++;
        }
        return 0;
    }
}
