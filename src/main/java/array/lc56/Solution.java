package array.lc56;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-06-19 21:10
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0) return new int[0][];
        int n=intervals.length;

        bubblesort(intervals,n);

        List<int[]> res=new ArrayList<>();
        //把排序后的第一个区间直接放进结果容器，作为初始对比对象
        res.add(intervals[0]);

        for(int i=1;i<n;i++){
            // 结果集的最后一个区间
            int[] last=res.get(res.size()-1);

            // 当前遍历到的区间的起点、终点
            int curstart=intervals[i][0];//区间左端点
            int curend=intervals[i][1];//区间右端点

            //排好序后，当前区间的起点一定 ≥ 所有前面区间的起点，只需要和最后一个区间对比
            if(curstart<=last[1]){
                // 重叠/相接，合并区间
                last[1] = Math.max(last[1], curend);//最后一个区间的右端点
            }else{
                //不重叠，直接加入
                res.add(intervals[i]);//加入第i个区间
            }

        }

        int[][] ans=new int[res.size()][2];
        for(int i=0;i<ans.length;i++){
            ans[i]=res.get(i);
        }
        return ans;

    }
    private void bubblesort(int[][] arr,int len){
        // 一共最多len-1轮
        for(int i=0;i<len-1;i++){
            //如果一整轮没有交换，代表全部有序，不用继续循环，节省时间
            boolean flag=false;

            //内层循环，每轮把最大的元素冒泡到末尾
            //len-1-i：后面 i 个元素已经排好序，不用再对比
            for(int j=0;j<len-1-i;j++){
                // arr[j][0]  第j个元素的左端点
                if(arr[j][0]>arr[j+1][0]){
                    // 交换2个区间数组
                    int[] t=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=t;
                    flag=true;//标记发生交换
                }
            }
            if(!flag) break;
        }
    }
}

/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0)
            return new int[0][];
        int n=intervals.length;
        // 官方快排：按区间左端点升序 O(nlogn)，远快于冒泡O(n²)
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);

        List<int[]> res=new ArrayList<>();
        res.add(intervals[0]);
        for(int i=1;i<n;i++){
            int[] last=res.get(res.size()-1);
            int curStart=intervals[i][0];
            int curEnd=intervals[i][1];
            if(curStart<=last[1]){
                last[1]=Math.max(last[1], curEnd);
            }else{
                res.add(intervals[i]);
            }
        }
        int[][] ans=new int[res.size()][2];
        for(int i=0;i<ans.length;i++){
            ans[i]=res.get(i);
        }
        return ans;
    }
}
 */