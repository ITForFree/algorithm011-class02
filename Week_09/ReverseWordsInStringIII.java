import java.util.ArrayList;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例 1:
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseWordsInStringIII {
    public String reverseWords(String s) {
        String[] words = split(s);
        StringBuilder res = new StringBuilder();
        for(String word : words) {
            res.append(reverse(word) + " ");
        }
        return res.toString().trim();
    }
    public String[] split(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                words.add(word.toString());
                word = new StringBuilder();
            } else {
                word.append(s.charAt(i));
            }
        }
        words.add(word.toString());
        return words.toArray(new String[words.size()]);
    }
    public String reverse(String s) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            res.insert(0, s.charAt(i));
        }
        return res.toString();
    }
}
