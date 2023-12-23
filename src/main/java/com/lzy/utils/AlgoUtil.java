package com.lzy.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class AlgoUtil {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

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

}
