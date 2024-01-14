package com.lzy.training.camp3.class01;

import com.lzy.utils.AlgoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class Code03_RGdyeingTest {

    private Code03_RGdyeing code03 = new Code03_RGdyeing();

    @Test
    void testMInDyeingNumOnViolentSolution() {
        String s = AlgoUtil.generateRGStr(5);
        log.info("生成的随机RG字符串：{}", s);
        int i = code03.minDyeingNumOnViolentSolution(s);
        log.info("最少需要的染色数量={}", i);
    }

    @Test
    void testMInDyeingNumOnPreAllocatedArray() {
        String s = AlgoUtil.generateRGStr(5);
        log.info("生成的随机RG字符串：{}", s);
        int i = code03.minDyeingNumOnPreAllocatedArray(s);
        log.info("最少需要的染色数量={}", i);
    }



}