package com.lzy.class03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.lzy.utils.AlgoUtil.generateRandomArray;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Code03_PartitionAndQuickSortTest {

    private Code03_PartitionAndQuickSort code03 = new Code03_PartitionAndQuickSort();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGenerateRandomArray() {
        int[] arr = generateRandomArray(20, 100);
        log.info("生成的数组为：{}", arr);
    }

    @Test
    void testPartition() {
        int[] arr = generateRandomArray(20, 100);
        code03.partition(arr, 0, arr.length - 1);
        log.info("分区后的数组为：{}", arr);
    }

    @Test
    void testNetherlands() {
        int[] arr = generateRandomArray(20, 100);
        code03.netherlandsFlag(arr, 0, arr.length - 1);
        log.info("随机数组，按照荷兰国旗分区：{}", arr);
        int[] arrSelf = {1, 3, 2, 5, 5, 6, 7, 0, 8, 5};
        code03.netherlandsFlag(arrSelf, 0, arrSelf.length - 1);
        log.info("自定义数组，按照荷兰国旗分区后：{}", arrSelf);
    }

    @Test
    void testQuickSortByPartition() {
        for (int i = 0; i < 20; i++) {
            int[] arr = generateRandomArray(30, 100);
            code03.quickSortByPartition(arr);
            log.info("快排后的结果：{}", arr);
            log.info("-----------------------------");
        }
    }

    @Test
    void testQuickSortByNetherlandsFlag() {
        for (int i = 0; i < 20; i++) {
            int[] arr = generateRandomArray(30, 100);
            int[] arrCopy = Arrays.copyOf(arr, arr.length);
            code03.quickSortByNetherlands(arr);
            log.info("快排后的结果：{}", arr);
            Arrays.sort(arrCopy);
            if(!Arrays.equals(arr, arrCopy)) {
                log.error("测试失败");
                return ;
            }
            log.info("-----------------------------");
        }
    }
}