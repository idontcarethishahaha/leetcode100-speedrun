package hash.lc128;

import java.util.HashSet;
import java.util.Set;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-06-22 14:22
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int maxl=0;
        for(int num:set){
            if(!set.contains(num-1)){
                int curnum=num;
                int curl=1;

                while(set.contains(curnum+1)){
                    curnum++;
                    curl++;
                }
                maxl=Math.max(curl,maxl);
            }
        }
        return maxl;
    }
}
