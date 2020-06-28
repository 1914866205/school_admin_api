package com.soft1851.smart.campus.utils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ExportDataAdapter
 * @Description 定义数据缓冲对象
 * @date 2020-06-18 15:17
 **/
public class ExportDataAdapter<T> {
    /**
     * 默认队列大小
     */
    private static Integer DEFAULT_SIZE = 1000;

    private BlockingDeque<T> resourceQueue = null;

    public ExportDataAdapter() {
        this.resourceQueue = new LinkedBlockingDeque<T>(DEFAULT_SIZE);
    }

    public void addData(T data) {
        try {
            resourceQueue.put(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取剩余数据数量
     *
     * @return
     */
    public Integer getDataSize() {
        return resourceQueue.size();
    }

    /**
     * 从队列中获取数据
     *
     * @return
     */
    public T getData() {
        try {
            return resourceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

