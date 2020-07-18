import java.util.*;

/**
 * 126. 单词接龙 II
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: []
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class WordLadderII {
    List<List<String>> res;
    List<String> list;
    Map<String, List<String>> map;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList();
        if (wordList.size() == 0) return res;
        list = new LinkedList();
        map = new HashMap();
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> unvisited = new HashSet<>(wordList);
        q.add(beginWord);
        unvisited.remove(beginWord);
        boolean found = false;

        // bfs
        while(!q.isEmpty()) {
            int size = q.size();
            for (int k = size - 1; k >= 0; k--) { // for each string in the queue
                String word = q.poll();
                for (int i = 0; i < word.length(); i++) {
                    char chs[] = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String newStr = new String(chs);
                        if (unvisited.contains(newStr)) {
                            if (!visited.contains(newStr)) {
                                visited.add(newStr);
                                q.add(newStr);
                            }
                            // build adjacent graph
                            if (map.containsKey(newStr)) {
                                map.get(newStr).add(word);
                            }else {
                                List<String> l = new ArrayList<>();
                                l.add(word);
                                map.put(newStr, l);
                            }
                            if (newStr.equals(endWord)) found = true;
                        }
                    }
                }
            }
            if (found) break;
            unvisited.removeAll(visited);
            visited.clear();
        }
        // back trace based on the adjacent graph that we have built
        backTrace(endWord, beginWord);
        return res;
    }

    private void backTrace(String cur, String start) {
        // find start by end
        if (cur.equals(start)) {
            list.add(0, start);
            res.add(new ArrayList<String>(list));
            list.remove(0); // backtrace by one step
            return;
        }
        list.add(0, cur);
        if (map.get(cur) != null) {
            for (String s : map.get(cur)) { // for each neighbors
                backTrace(s, start);
            }
        }
        list.remove(0);
    }
}
