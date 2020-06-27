/**
 * 旋转数组：
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 */

public class RotateArray {
    public void rotate(int[] nums, int k) {
        // 利用反转，时间复杂度O(n)，空间复杂度：O(1)
        // 当我们旋转数组k次， k%n个尾部元素会被移动到头部，剩下的元素会被向后移动
        k %= nums.length;
        // 将所有元素反转
        reverse(nums, 0, nums.length - 1);
        // 反转前k个元素
        reverse(nums, 0, k - 1);
        // 反转后n - k个元素
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end --;
        }

    }
}
