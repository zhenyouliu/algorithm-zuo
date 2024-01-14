package com.lzy.training.camp3.class01;

import com.lzy.utils.AlgoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Code02_NeedParenthesesTest {

    private final Code02_NeedParentheses code02 = new Code02_NeedParentheses();

    @Test
    void isValidParenthesesByStackTest() {
        String s = AlgoUtil.generateRandomParenthesesStr(20);
        log.info("生成的随机括号数组={}", s);
        if(code02.isValidParenthesesByStack(s)){
            log.info("数组有效");
        } else {
            log.info("数组无效");
        }
    }

    @Test
    void isValidParenthesesByCountTest() {
        String s = AlgoUtil.generateRandomParenthesesStr(20);
        log.info("生成的随机括号数组={}", s);
        if(code02.isValidParenthesesByCount(s)){
            log.info("数组有效");
        } else {
            log.info("数组无效");
        }
    }

    @Test
    void needParenthesisToBeValidTest() {
        String s = AlgoUtil.generateRandomParenthesesStr(20);
        log.info("生成的随机括号数组={}", s);
        if(code02.isValidParenthesesByCount(s)){
            log.info("数组有效");
        } else {
            log.info("数组无效");
            int i = code02.needParenthesisToBeValid(s);
            log.info("至少需要添加{}个括号，使该字符串有效", i);
        }
    }

    @Test
    void maxValidLengthTest() {
//        String s = AlgoUtil.generateRandomParenthesesStr(20);
        String inputStr = "(()()))))";
        log.info("生成的随机括号数组={}", inputStr);
        int maxValidLength = code02.maxValidLength(inputStr);
        log.info("最大有效长度={}", maxValidLength);
    }

}