package com.lzy.training.camp3.class01;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

/**
 * 给定一个有序数组arr,从左到右依次表示X轴上从左往右点的位置。
 * 给定一个正整数K,返回如果有一根长度为K的绳子，最多能盖住几个点？
 * 注：绳子的边缘点碰到X轴上的点，也算盖住
 */
@Slf4j
public class Code01_CordCoverMaxPoint {

    public int calcMaxCoverPoints(final int[] arr, int k) {
        Assert.isTrue(arr != null && arr.length > 0);
        log.info("k={}，输入数组为：{}", k, arr);
        int coverPoints = 0;
        int startIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            int tempCover = 0;
            for (int j = i; j < arr.length && arr[j] <= arr[i] + k; j++) {
                tempCover++;
            }
            if (coverPoints < tempCover) {
                coverPoints = tempCover;
                startIndex = i;
            }
        }
        log.info("最大覆盖长度={}，覆盖的起点下标={}", coverPoints, startIndex);
        return coverPoints;
    }

    /**
     * 暴力解法：双层循环，时间复杂度=O(N2)
     *
     * @param arr 有序数组
     * @param k   绳子最大长度
     * @return 绳子最大覆盖点数
     */
    public int calcMaxCoverPointsN2(final int[] arr, int k) {
        int maxPoints = -1;
        for (int i = 0; i < arr.length; i++) {
            int tempLength = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] <= k) {
                    tempLength++;
                } else {
                    maxPoints = Math.max(maxPoints, tempLength);
                    break;
                }
            }
        }
        return maxPoints;
    }

    /**
     * 使用二分法计算：双层循环，时间复杂度O(N*logN)
     *
     * @param arr
     * @param k
     * @return
     */
    public int calcMaxCoverPointsByBS(final int[] arr, int k) {
        int maxPoints = -1;
        for (int i = 0; i < arr.length; i++) {
            int nearIndex = getNearestIndexLessThanK(arr, i, arr.length - 1, arr[i] + k);
            maxPoints = Math.max(maxPoints, nearIndex - i + 1);
        }
        return maxPoints;
    }

    /**
     * 二分找出小于等于compValue的最大index
     *
     * @param arr
     * @param l
     * @param r
     * @param compValue
     * @return
     */
    public int getNearestIndexLessThanK(int[] arr, int l, int r, int compValue) {
        int left = l;
        int right = r;
        int mid = left + ((right - left) >> 1);
        while (left <= right) {
            if (arr[mid] <= compValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + ((right - left) >> 1);
        }
        return mid;
    }

    /**
     * 使用滑动窗口法（或者叫左右指针法）：
     * 窗口长度与左右边界元素的差值之间存在单调性，即窗口长度越大，左右边界差值就越大，所以存在单调性，所以可以使用滑动窗口法，
     * 在整个遍历的过程中，左右边界都不用回退，都只需要一直往前走，所有总的操作次数不会超过2*N次，因此该算法的复杂度是O(N)的。
     * 为最优解算法。
     * @param arr
     * @param k
     * @return
     */
    public int calcMaxCoverPointsBySlideWindow(int[] arr, int k) {
        int L = 0;
        int R = 0;
        int N = arr.length;
        int maxPoints = -1;
        while(L < N) {
            // L不动时，找到窗口的最右边界
            while(R < N && arr[R] - arr[L] <= k) {
                R++;
            }
            // 计算此时的窗口长度，并比较和赋值
            maxPoints = Math.max(maxPoints, R - L);
            L++;
        } // 由于整个遍历过程，L和R一直都是只增加的，所以每个元素最多遍历了2次。
        return maxPoints;
    }

}
