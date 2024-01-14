package com.lzy.training.camp3.class01;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

import java.time.chrono.MinguoDate;
import java.util.Arrays;
import java.util.Stack;

/**
 * 有效字符串定义：
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 *
 * 问题1： 如何判断一个由左右括号组成的字符串是否为有效字符串？
 * 问题2：至少需要添加多少个括号，才能使无效括号字符串变为有效？
 * 问题3：求一个括号字符串的最长有效子串长度
 * */
@Slf4j
public class Code02_NeedParentheses {

//    问题1解法：

    /**
     * 判断括号字符串的有效性：利用栈的特点。
     * @param str 括号字符串
     * @return true-有效，false-无效
     */
    public boolean isValidParenthesesByStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
//            碰到左括号则压栈，右括号则出栈。需要出栈时，若栈中为空，则说明没有左括号与之匹配，字符串无效。
            if('(' == str.charAt(i)) {
                stack.push('(');
            } else {
                if(stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 判断括号字符串的有效性：利用count变量
     * @param str 括号字符串
     * @return true-有效，false-无效
     */
    public boolean isValidParenthesesByCount(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '('){
                count++;
            } else {
                count--;
                if (count == -1) {
//                    左括号不够用，字符串无效
                    return false;
                }
            }
        }
        return count == 0;
    }

    // 问题2解法：
    public int needParenthesisToBeValid(String str) {
        // 没当碰到无人配对的右括号时，都补偿一个左括号与之配对。
        // 如此，所有的右括号都保证了有效性，最后再加上无人配对的左括号数量，就是需要的括号总数
        char[] chars = str.toCharArray();
        int count = 0;
        int needLeft = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '(') {
                count++;
            } else {
                count--;
                if(count == -1) {
                    needLeft++;
                    count = 0;
                }
            }
        }
        return count + needLeft;
    }

    /**
     * 最大有效子串长度问题，考虑使用动态规划方案解决。
     *
     * 建模的关键：把问题转换为 -》如何计算出“以当前i位置为结尾（或开始）”的最大有效子串长度问题。
     * 如果转换后的这个问题我们能够解决，那么比较所有位置上的最大子串长度的最大值，即为问题的答案。
     *
     * 那么如何计算出当前i位置为结尾的最大有效子串长度呢？ -》 动态规划方法。即建立递推公式，找到递推公式，就解决了动态规划问题。
     * 高中学过的递推问题解决步骤：
     * 1.假设已知i-1位置上的最大有效子串长度，推导出i位置上的最大有效子串长度。（最难的一步）
     * 2.计算递推起点初始值：即0位置上的最大有效子串长度
     * 3.开始递推解决。
     *
     * 推导公式：假设出所有可能的场景
     * 已知条件：i-1位置为结尾的最大有效长度等于dp[i-1]（已知值）
     * 1. i位置上的值为左括号时，那么dp[i] = 0;
     * 2. i位置为右括号时，
     * 1）忽略i-1 到 i-1 - dp[i-1] + 1这段区间上的元素，因为这段区间是已经最长的有效子串。
     * 2）如果最长有效子串区间的左边一位（关键位置keyIndex，坐标值：i-1 - dp[i-1]）为右括号，那么该位置和i位置无法匹配，
     *   此时i位置上的最长有效子串长度=0
     * 3）如果keyIndex为左括号，那么该位置和i位置能够匹配，dp[i]的最小值=dp[i-1] + 2。（i-1位置上的最长子串 + 关键位置和自身位置）
     *   因为此时，i位置的最大有效长度至少能够扩展到keyIndex位置，能不能继续向左扩展，取决于keyIndex左边还有没有能“自匹配”的有效子串。
     *   若有，则再加上自匹配的有效子串。因此，此种情况下的公式等于：
     *   ，dp[i] = dp[i-1] + 2 + dp[keyIndex-1];
     *
     * 推导结果：得出公式（分段函数）
     *          0, when str[i] = '('
     * dp[i] =  0, when str[i] = ')' && str[keyIndex] = ')'
     *          dp[i-1] + 2 + dp[keyIndex-1], when str[i] = ')' && str[keyIndex] = '('
     *  其中：keyIndex = i-1 - dp[i-1]
     *
     * @param str 输入的括号字符串
     * @return 最大有效子串长度
     */
    public int maxValidLength(String str) {
//        实现以上公式
        int maxValid = 0;
        char[] toCharArray = str.toCharArray();
        int[] dp = new int[toCharArray.length];
        dp[0] = 0;
        for (int i = 1; i < toCharArray.length; i++) {
            dp[i] = maxValidLengthForIndex(toCharArray, dp, i);
            maxValid = Math.max(maxValid, dp[i]);
        }
        log.info("每个位置上的最大有效长度dp[i]={}", Arrays.toString(dp));
        return maxValid;
    }

    private static char LEFT_BRACKET = '(';
    private static char RIGHT_BRACKET = ')';


    private int maxValidLengthForIndex(char[] toCharArray, int[] dp, int i) {
        // 情况一
        if (toCharArray[i] == LEFT_BRACKET) {
            return 0;
        }
        // 情况二
        int keyIndex = i - 1 - dp[i-1];

        if (keyIndex < 0 || toCharArray[keyIndex] == RIGHT_BRACKET) {
            // 不存在关键位置，或者关键位置等于右括号，都说明无法匹配当前i位置
            return 0;
        }
        // 情况三
        if (keyIndex == 0) {
            // 关键位置为左括号，且关键位置等于0，说明左边没有更多元素供匹配了。
            return dp[i-1] + 2;
        } else {
            // 关键位置为左括号，且关键位置左边还有元素
            return dp[i-1] + 2 + dp[keyIndex-1];
        }
    }

}
