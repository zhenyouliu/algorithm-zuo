package com.lzy.自测;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CompletableFutureDemoTest {

    private ExecutorService executor;

    @BeforeEach
    void setUp() {
        executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setDaemon(true);
                    thread.setName("custom-fixed-pool");
                    return thread;
                });
    }

    private String getName(String name) {
        log.info("打印名字：" + name);
        return name;
    }

    /**
     * 验证结论：future对象中的任务，提交给线程池之后就会立刻进行异步执行了，不是调用get或join方法时才会触发任务执行
     */
    @Test
    void testNoGet() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> getName("刘振友"));
//        completableFuture.join();
        log.info("测试结束");
    }

    @AfterEach
    void tearDown() {
        executor.shutdown();
    }
}