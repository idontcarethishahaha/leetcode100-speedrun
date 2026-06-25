package hash.lc1;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @date 2026-06-19 20:54
 * @version 1.0
 */
class Solution {
    public int[] twoSum(int[] nums,int target){
        // key-数组中数字，value-数组下标
        Map<Integer,Integer> map=new HashMap<>();

        // i-当前元素下标
        for(int i=0;i<nums.length;i++){
            // 需要配对的另一半数字
           int need=target-nums[i];

           if(map.containsKey(need)){//这个数字在map中存在
               // get()方法返回key对应的value
               return new int[]{map.get(need),i};//返回这两个数字的下标数组
           }
           // 否则，将当前数字放入map中，供后续匹配用，全程只遍历数组一次
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
       // return new int[0];//返回空数组保护代码或者抛异常
    }
}
