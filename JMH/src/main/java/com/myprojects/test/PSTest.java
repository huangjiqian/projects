package com.myprojects.test;

import com.myprojects.jmh.PS;
import org.openjdk.jmh.annotations.*;

public class PSTest {
    @Benchmark
    @Measurement(iterations = 10)//总共执行多少次测试
    @Warmup(iterations = 3)//预热几次
    @Timeout(time = 100)//限时多少s
    public void testForEach() {
        PS.foreach();
    }
}
