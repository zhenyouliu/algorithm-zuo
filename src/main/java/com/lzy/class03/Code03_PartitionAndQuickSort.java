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
     */
    public void partition(int[] arr, int L, int R){

        int lessEqual = L-1;
        int index = L;
        while(index <= R) {
            if(arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
    }

    /**
     * 对一个非空数组，按照末尾元素，玩荷兰国旗划分
     * @param arr 整数数组
     */
    public void netherlandsFlag(int[] arr) {

        Assert.isTrue(arr != null && arr.length > 0, "输入数组不允许为空");
        int less = -1;
        int equal = less;
        int index = 0;
        int compVal = arr[arr.length - 1];
        while(index <= arr.length - 1) {
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
    }

}
