package twopointers.lc15;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-06-19 21:14
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：LeetCode 15 三数之和
 * 整体思路：排序 + 固定首元素 + 左右双指针夹逼，配合多层去重得到不重复三元组
 * 1. 预处理：数组排序
 *    ① 有序数组才能使用双指针通过和的大小移动左右指针；
 *    ② 相同数字相邻，方便跳过重复值，避免输出重复三元组。
 * 2. 外层循环固定三元组第一个元素 nums[i]
 *    ① 剪枝：nums[i]>0 直接break，数组升序，三数之和必然大于0，无需继续遍历；
 *    ② 外层去重：如果当前i与前一位数字相同，会生成重复组合，直接跳过。
 * 3. 内层左右双指针 left、right
 *    left = i+1（第二个数），right = 数组末尾（第三个数），两指针向中间靠拢：
 *    ① 三数和 < 0：总和偏小，左指针右移，取更大的值；
 *    ② 三数和 > 0：总和偏大，右指针左移，取更小的值；
 *    ③ 三数和 = 0：找到合法三元组存入结果，收缩指针后跳过重复值，内层去重。
 * 4. 内层去重逻辑：
 *    找到一组解后先收缩left、right，再循环跳过与刚使用过的值重复的元素，
 *    保证同一i下不会产出重复三元组。
 * 时间复杂度：O(n²)，排序 O(nlogn)，外层循环n次，内层双指针单次遍历O(n)
 * 空间复杂度：O(logn)，仅排序栈开销，不计存储答案的空间
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 数组为空或长度不足3，无法组成三元组，直接返回空
        if(nums == null || n < 3) return res;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            // 当前固定的第一个数字已经大于0，后面数字更大，三数之和一定大于0，直接结束循环剪枝
            if (nums[i] > 0) break;
            // 外层去重：和前一个固定数字相同，会产生重复三元组，跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    // 和太小，需要更大的数，左指针右移
                    left++;
                } else if (sum > 0) {
                    // 和太大，需要更小的数，右指针左移
                    right--;
                } else {
                    // 找到满足条件的三元组，加入结果集合
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 标准无bug去重逻辑：先收缩指针
                    left++;
                    right--;
                    // 跳过左侧重复数字，避免同一i下出现重复组合
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    // 跳过右侧重复数字，避免同一i下出现重复组合
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return res;
    }
}

