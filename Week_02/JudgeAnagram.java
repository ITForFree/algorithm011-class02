/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 */
public class JudgeAnagram {
    public boolean isAnagram(String s, String t) {
        // 借助哈希表实现，时间复杂度：O(n)
        if(s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for(int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a'] ++;
            counter[t.charAt(i) - 'a'] --;
        }
        for(int count : counter) {
            if(count != 0) return false;
        }
        return true;
    }
}
