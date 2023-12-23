package com.lzy.class03;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.lzy.utils.AlgoUtil.*;


@Slf4j
public class Code03_PartitionAndQuickSort {

    /**
     * partition算法：将一个数组按指定的区间范围的末尾元素作为分区值，小于末尾元素的分在左半区，大小的分在右半区
     *
     * @param arr 输入数组
     * @param L 要执行分区的下标左边界
     * @param R 要执行分区的下标右边界
     * @return 返回末尾那个元素分区后的下标位置
     */
    public int partition(int[] arr, int L, int R){

        int lessEqual = L-1;
        int index = L;
        while(index <= R) {
            if(arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        return lessEqual;
    }

    /**
     * 对一个非空数组，按照末尾元素，玩荷兰国旗划分
     * @param arr 整数数组
     * @return index[]：返回等于区的左右边界下标
     */
    public int[] netherlandsFlag(int[] arr, int L, int R) {

        if(L > R) {
            return new int[]{-1, -1};
        }
        if(L == R) {
            return new int[]{L, R};
        }
        int less = L-1;
        int equal = less;
        int index = L;
        int compVal = arr[R];
        while(index <= R) {
            if(arr[index] < compVal) {
//                小于比较值，先把等于区右扩，再把小于区右扩
                swap(arr, less + 1, ++equal);
                swap(arr, index, ++less);
            } else if(arr[index] == compVal){
//                等于比较值，将当前元素交换到等于区
                swap(arr, index, ++equal);
            }
            index++;
        }
        return new int[]{less+1, equal};
    }

    /**
     * 快排算法1：递归地按照末尾元素进行分区。每次递归完成末尾元素的排序。（一次排好一个元素）
     */
    public void quickSortByPartition(int[] arr) {
        if(arr == null || arr.length < 2) {
            return ;
        }
        /**
         * 执行递归任务：
         * 1）先通过partition进行一次排序，把末尾元素排好位置。
         * 2）把子任务分发下去。结束。（子任务回收上来的时候，所有元素就全部排序完成了。）
         */
        process1(arr, 0, arr.length - 1);
    }

    private void process1(int[] arr, int L, int R) {
        if(L >= R) { // 区间长度小于2，没有必要再排序。
            return ;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid+1, R);
    }

    /**
     * 快排算法2：递归地按照末尾元素玩荷兰国旗游戏。每次排好一组与末尾元素相等的元素。
     */
    public void quickSortByNetherlands(int[] arr) {
        if(arr == null || arr.length < 2) {
            return ;
        }
        /**
         * 执行递归任务：
         * 1）玩荷兰国旗游戏，把白旗元素确定好
         * 2）分发子任务。
         */
        process2(arr, 0, arr.length - 1);

    }

    void process2(int[] arr, int L, int R) {
        if(L >= R) {
            return ;
        }
        int[] boundArr = netherlandsFlag(arr, 0, arr.length - 1);
        process1(arr, L, boundArr[0] - 1);
        process1(arr, boundArr[1] + 1, R);
    }

}
