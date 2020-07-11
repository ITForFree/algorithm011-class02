import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermuteNumsII {
    public List<List<Integer>> backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums, boolean[] used) {
        // terminator
        if(tmpList.size() == nums.length) {
            list.add(new ArrayList<>(tmpList));
        }
        for(int i = 0; i < nums.length; i++) {
            // exclude the special cases
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            used[i] = true;
            tmpList.add(nums[i]);
            // recursion
            backtrack(list, tmpList, nums, used);
            // reverse
            used[i] = false;
            tmpList.remove(tmpList.size() - 1);
        }
        return list;
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }
}
