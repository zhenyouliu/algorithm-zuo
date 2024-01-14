package com.lzy.training.camp3.class01;

import lombok.extern.slf4j.Slf4j;

/**
 * 正方形染色问题：
 * 假设有一行从左到右排列的正方形，每个正方形的颜色为红色（R）或绿色（G）,如果你可以对任意一个正方形的颜色就行修改（修改为R或G），
 * 请问：你最少需要修改多少个正方形，才能够使每个红色正方形的位置都位于每个绿色正方形左边
 * 注意：可以全部为红色，或绿色。
 * 如：GRRRR  -> 最少修改1个，即把0号位置的G染色为R就可以了。
 *
 */
@Slf4j
public class Code03_RGdyeing {
    /**
     * 问题的关键：先读懂题目，找到暴力解法，如果连暴力解法都找不到，那就完蛋了。
     * 暴力解法：遍历枚举。计算出以i位置作为RG的分界线时，需要染多少个正方形（计为n）。比较出所有位置为分界线时的n的最小值。
     * 1）计算以i位置作为分界线时，需要染色的数量：[0,i]上G的数量（都要染成R） + [i+1, N-1]上R的数量（都要染成G）
     * 2)记录遍历过程中算出来的最小值。即为答案。
     * 3）需要处理两个边界：当全为R时，当全为G时
     */


    public static char R_COLOR = 'R';
    public static char G_COLOR = 'G';

    public int minDyeingNumOnViolentSolution(String inputStr) {
        int minNum = Integer.MAX_VALUE;
        char[] toCharArray = inputStr.toCharArray();

//        处理边界问题：
//        1.全为红色，需要染色数量 -> 此处无需特殊处理，在后面的for循环中已经包含该种可能。
        int tempNum = 0;
//        for (int i = 0; i < toCharArray.length; i++) {
//            tempNum += toCharArray[i] == G_COLOR ? 1 : 0;
//        }
//        minNum = Math.min(minNum, tempNum);

//        2.全为绿色，需要染色的数量
        tempNum = 0;
        for (int i = 0; i < toCharArray.length; i++) {
            tempNum += toCharArray[i] == R_COLOR ? 1 : 0;
        }
        minNum = Math.min(minNum, tempNum);

        for (int i = 0; i < toCharArray.length; i++) {
//            [0, i]上红色区域，[i+1, N-1]为绿色区域
            int leftG = 0;
            for(int j = 0; j <= i; j++) {
                leftG += toCharArray[j] == G_COLOR ? 1 : 0;
            }
            int rightR = 0;
            for(int j = i+1; j < toCharArray.length; j++) {
                rightR += toCharArray[j] == R_COLOR ? 1 : 0;
            }
            minNum = Math.min(minNum, leftG + rightR);
        }
        return minNum;
    }


    /**
     * 方案二：思路和暴力解法一致。采用预分配数组，对大查询中的小查询进行优化。
     * 即：用一个leftG数组，记录从左到右遍历过程中，每个位置上，当前位置左边的G的总个数。
     * 用一个rightR数组，记录从右到左遍历过程中，每个位置上，当前位置右边出现的R的共个数。
     * 从而达到O(1)获取分界线左右两边对R和G数量统一的需求。
     *
     * @param inputStr 输入的RG组成的数组。
     * @return 最小染色数量
     */
    public int minDyeingNumOnPreAllocatedArray(String inputStr) {
        int minNum = Integer.MAX_VALUE;
        char[] toCharArray = inputStr.toCharArray();

//        处理边界问题：
//        1.全为红色，需要染色数量
        int tempNum = 0;
        for (int i = 0; i < toCharArray.length; i++) {
            tempNum += toCharArray[i] == G_COLOR ? 1 : 0;
        }
        minNum = Math.min(minNum, tempNum);

//        2.全为绿色，需要染色的数量
        tempNum = 0;
        for (int i = 0; i < toCharArray.length; i++) {
            tempNum += toCharArray[i] == R_COLOR ? 1 : 0;
        }
        minNum = Math.min(minNum, tempNum);

        int[] leftG = new int[toCharArray.length];
        int[] rightR = new int[toCharArray.length];
        
        int count = 0;
        for(int i = 0; i < toCharArray.length; i++) {
            count += toCharArray[i] == G_COLOR ? 1 : 0;
            leftG[i] = count;
        }
        count = 0;
        for(int i = toCharArray.length - 1; i >= 0; i--) {
            count += toCharArray[i] == R_COLOR ? 1 : 0;
            rightR[i] = count;
        }
        log.info("当前数组统计结果，leftG={}", leftG);
        log.info("当前数组统计结果，rightR={}", rightR);

        for (int i = 0; i < toCharArray.length - 1; i++) {
            minNum = Math.min(minNum, leftG[i] + rightR[i + 1]);
        }
        
        return minNum;
    }
}
