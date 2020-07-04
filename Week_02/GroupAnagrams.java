import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 按计数分类，时间复杂度：O(nk)，n 为 strs 的长度，k 为 strs 中字符串的最大长度
        // 字符数 count 的散列化表示用 **＃** 字符分隔的字符串。 例如，abbccc 表示为 ＃1＃2＃3＃0＃0＃0 ...＃0，其中总共有26个条目
        if(strs.length == 0) return new ArrayList();
        int[] count = new int[26];
        HashMap<String, List> ans = new HashMap<>();
        for(String s : strs) {
            Arrays.fill(count, 0);
            char[] charArr = s.toCharArray();
            for(char c : charArr) {
                count[c - 'a'] ++;
            }
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < 26; i++) {
                sb.append("#");
                sb.append(count[i]);
            }
            String key = sb.toString();
            if(!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
