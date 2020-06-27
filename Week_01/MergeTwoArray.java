/**
 * 88.合并两个有序数组:
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class MergeTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针，时间复杂度：O(n + m)
        // 从后往前，p1记录nums的最后一个元素位置，p2记录nums2的最后一个元素位置
        int p1 = m - 1;
        int p2 = n - 1;
        // p用来记录追加元素的位置
        int p = m + n -1;
        while(p1 >= 0 && p2 >= 0) {
            // 把较大的元素放入p位置
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        // 处理nums1为空，nums2不为空的情况
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
