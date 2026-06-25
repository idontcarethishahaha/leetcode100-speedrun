package twopointers.lc15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-06-20 20:26
 */
/*
Krahets大佬的题解，觉得这个很不错
核心：排序 + 固定一层 k + 左右对撞双指针
先把数组升序排序，利用有序特性缩小搜索范围、快速去重；
外层循环固定第一个数 nums[k]；
内层用 i=k+1（左指针）、j=末尾（右指针）相向移动，计算三数和；
根据和与 0 的大小关系移动指针，全程跳过重复元素，保证答案无重复三元组。
 */
class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 1. 数组排序，双指针前提
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        // 外层循环：固定第一个元素k，最多到倒数第三个，留i、j两个位置
        for(int k = 0; k < nums.length - 2; k++){
            // 优化：nums[k]已经大于0，后面全是更大正数，三数和必然>0，直接退出循环
            if(nums[k] > 0) break;

            // k层去重：和上一个k值相同则跳过，避免生成重复三元组
            if(k > 0 && nums[k] == nums[k - 1]) continue;

            // 左右双指针初始化：i在k下一位，j在数组最后
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    // 和偏小，需要更大的数：左指针右移，同时跳过所有重复i
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    // 和偏大，需要更小的数：右指针左移，同时跳过所有重复j
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    // 刚好等于0，记录一组答案
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    // 存入后，左右指针同时跳过重复值，防止重复解
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }
}
/*
如果看不懂一行去重，可以拆分开，逻辑完全一致：
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    i++;
                    // 跳过i重复
                    while (i < j && nums[i] == nums[i - 1]) i++;
                } else if (sum > 0) {
                    j--;
                    // 跳过j重复
                    while (i < j && nums[j] == nums[j + 1]) j--;
                } else {
                    res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    i++;
                    j--;
                    // 存入答案后跳过两边重复
                    while (i < j && nums[i] == nums[i - 1]) i++;
                    while (i < j && nums[j] == nums[j + 1]) j--;
                }
            }
        }
        return res;
    }
}
 */
