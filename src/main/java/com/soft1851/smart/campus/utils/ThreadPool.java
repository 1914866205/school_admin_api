package com.soft1851.smart.campus.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ThreadPool
 * @Description TODO
 * @date 2020-06-18 15:22
 **/
public class ThreadPool {
    /**
     * 异步线程
     */
    private final static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(40, 150, 10, TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(2000),
                    r -> new Thread(r, "excelExportThread"),
                    new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
