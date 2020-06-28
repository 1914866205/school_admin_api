package com.soft1851.smart.campus.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description 执行的任务
 * @Author wf
 * @Date 2020/5/18
 * @Version 1.0
 */
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Console.log("定时任务，当前时间：", DateUtil.now());
    }
}
