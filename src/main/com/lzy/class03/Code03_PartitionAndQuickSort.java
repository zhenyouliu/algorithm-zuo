package main.com.lzy.class03;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;


@Slf4j
public class Code03_PartitionAndQuickSort {

//    生成随机数组。最大长度、最大值均为随机值
    public static int[] generateRandomArray(int maxSize, int maxVal) {
        int actualSize = new Random().nextInt(maxSize) + 1;
        int[] arr = new int[actualSize];
        log.info("生成的数组的实际长度:{}", actualSize);
        for (int i = 0; i < actualSize; i++) {
            int value = new Random().nextInt(maxVal) + 1;
            arr[i] = value;
        }
        log.info("生成的随机数组为：{}", arr);
        return arr;
    }

    public static void main(String[] args) {
        generateRandomArray(20, 100);
    }

    /**
     * partition算法：将一个数组按末尾元素作为分区标准，小于末尾元素的分在左半区，大小的分在右半区
     *
     * @param arr
     * @param L 要执行分区的下标左边界
     * @param R 要执行分区的下标右边界
     * @return 返回分区后的原数组
     */
    public static int[] partition(int[] arr, int L, int R){

        return arr;
    }

}
