import java.util.ArrayList;
import java.util.List;
/**
 * 212. 单词搜索 II
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 示例:
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * 提示:
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 */
public class WordSearchII {
    // 时间复杂度：O(M(4⋅3^(L−1))，其中M 是二维网格中的单元格数，L 是单词的最大长度
    // 假设单词的最大长度是 L，从一个单元格开始，最初我们最多可以探索 4 个方向。假设每个方向都是有效的（即最坏情况），
    // 在接下来的探索中，我们最多有 3 个相邻的单元（不包括我们来的单元）要探索。因此，在回溯探索期间，我们最多遍历 4⋅3^(L−1) 个单元格
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();
    public List<String> findWords(char[][] board, String[] words) {
        // 1.construct the Trie
        TrieNodeForWordSearch root = new TrieNodeForWordSearch();
        for(String word : words) {
            TrieNodeForWordSearch node = root;
            for(Character letter : word.toCharArray()) {
                if(node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNodeForWordSearch newNode = new TrieNodeForWordSearch();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word; // store words in Trie
        }
        this._board = board;

        // 2. Backtracking starting for each cell in the board
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }
        return this._result;
    }

    private void backtracking(int row, int col, TrieNodeForWordSearch parent) {
        Character letter = this._board[row][col];
        TrieNodeForWordSearch curNode = parent.children.get(letter);

        // check if there is any match
        if(curNode.word != null) {
            this._result.add(curNode.word);
            curNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for(int i = 0; i < 4; i++) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (curNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, curNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (curNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
