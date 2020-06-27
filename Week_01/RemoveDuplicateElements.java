/**
 * 26.删除排序数组中的重复项：
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组，并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicateElements {
    public int removeDuplicates(int[] nums) {
        // 采用双指针法，时间复杂度为：O(n)
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        // i为慢指针，j 为快指针
        int i = 0;
        for(int j = 1; j < n; j ++) {
            if(nums[j] != nums[i]) {
                i ++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
