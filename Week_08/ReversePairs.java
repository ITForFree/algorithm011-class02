/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        // 归并排序
        if(nums == null || nums.length < 2) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] nums, int low, int high) {
        if(low >= high) return 0;
        int mid = low + (high - low) / 2;
        int count = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high);
        for(int i = low, j = mid + 1; i <= mid && j <= high;) {
            if(nums[i] > (long)nums[j] * 2) {
                count += mid - i + 1;
                j ++;
            } else {
                i ++;
            }
        }
        merge(nums, low, high);
        return count;
    }
    private static void merge(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        while(i <= mid && j <= high) {
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while(i <= mid) temp[k++] = nums[i++];
        while(j <= high) temp[k++] = nums[j++];
        for(int p = 0; p < temp.length; p++) {
            nums[low+p] = temp[p];
        }
    }
}

