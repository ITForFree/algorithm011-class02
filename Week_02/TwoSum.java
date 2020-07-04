import java.util.HashMap;

/**
 * 1.两数之和：
 * 定一个整数数组 nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 使用HashMap做缓存，时间复杂度：O(n)
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            int value = target - nums[i];
            if(hashMap.containsKey(value)) {
                return new int[]{hashMap.get(value), i};
            }
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("Can't find two nums");
    }
}
