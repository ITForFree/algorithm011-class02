import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class PermuteNums {
    public List<List<Integer>> backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums) {
        // terminator
        if(tmpList.size() == nums.length) {
            list.add(new ArrayList<>(tmpList));
        }
        for(int i = 0; i < nums.length; i++) {
            if(tmpList.contains(nums[i])) {
                continue;
            }
            tmpList.add(nums[i]);
            // recursion
            backtrack(list, tmpList, nums);
            // reverse
            tmpList.remove(tmpList.size() - 1);
        }
        return list;
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }
}
