package com.lzy.training.camp3.class01;

import cn.hutool.core.lang.Assert;
import com.lzy.utils.AlgoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

@Slf4j
class Code01_CordCoverMaxPointTest {
    private Code01_CordCoverMaxPoint code01 = new Code01_CordCoverMaxPoint();

    @Test
    void testCalcMaxCoverPoints() {
        int[] arr = AlgoUtil.generateRandomIntArray(30, 100);
        Arrays.sort(arr);
        int k = new Random().nextInt(20) + 1;
        int coverPoints = code01.calcMaxCoverPoints(arr, k);
        log.info("arr={}", arr);
        log.info("k={}", k);
        log.info("maxCoverPoints={}", coverPoints);
        int maxCoverPointsN2 = code01.calcMaxCoverPointsN2(arr, k);
        log.info("maxCoverPointsN2 = {}", maxCoverPointsN2);
        int maxCoverPointsBS = code01.calcMaxCoverPointsByBS(arr, k);
        log.info("maxCoverPointsBS = {}", maxCoverPointsBS);
        int maxPointsSlideWin = code01.calcMaxCoverPointsBySlideWindow(arr, k);
        log.info("maxPointsSlideWin = {}", maxPointsSlideWin);
        Assert.isTrue(coverPoints == maxCoverPointsN2, "计算结果不一致");
        Assert.isTrue(coverPoints == maxCoverPointsBS, "二分法计算结果有误");
        Assert.isTrue(coverPoints == maxPointsSlideWin, "滑动窗口法计算错误");
    }

    @Test
    void testBinarySearch() {
        int[] arr = AlgoUtil.generateRandomIntArray(30, 100);
        Arrays.sort(arr);
        int k = new Random().nextInt(20) + 1;
        int nearestIndexLessThanK = code01.getNearestIndexLessThanK(arr, 0, arr.length - 1, k);
        log.info("arr={}", arr);
        log.info("k={}", k);
        if(nearestIndexLessThanK == -1) {
            log.info("数组中小于等于k的位置不存在");
            return;
        }
        log.info("数组中小于等于k的最大位置={}，对应的值 = {}", nearestIndexLessThanK, arr[nearestIndexLessThanK]);
    }

}