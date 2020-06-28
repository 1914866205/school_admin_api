package com.soft1851.smart.campus.task;

import org.quartz.SchedulerException;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/18
 * @Version 1.0
 */
public class DynamicScheduleTask {
    public static void main(String[] args) throws SchedulerException {
        //添加任务，注意各个参数的作用
        QuartzManager.addJob("testJob", "jobGroup1",
                "testTrigger", "triggerGroup1",
                TestJob.class, "0/5 * * * * ?");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //修改任务
        /*QuartzManager.modifyJobTime("testTrigger", "triggerGroup1",5);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //删除任务
        QuartzManager.removeJob("testJob", "jobGroup1",
                "testTrigger", "triggerGroup1");
        QuartzManager.shutdownJobs();*/
    }
}
