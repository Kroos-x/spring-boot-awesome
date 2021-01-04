package com.yc.springbootquartz.controller;

import com.yc.springbootquartz.quartz.UploadTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-03
 */
@RestController
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() throws SchedulerException {
        // cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/8 * * * * ?");
        //根据name 和group获取当前trgger 的身份
        TriggerKey triggerKey = TriggerKey.triggerKey("cj", "123");
        CronTrigger triggerOld = null;
        try {
            // 获取 触发器的信息
            triggerOld = (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if (triggerOld == null) {
            //将job加入到jobDetail中
            JobDetail jobDetail = JobBuilder.newJob(UploadTask.class).withIdentity("cj", "123").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cj", "123").withSchedule(cronScheduleBuilder).build();
            //执行任务
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            System.out.println("当前job已存在--------------------------------------------");
        }
    }


    /**
     * 删除job
     *
     * @param triggerName  触发器名称
     * @param triggerGroup 触发器分组
     * @param jobName      任务名称
     * @param jobGroup     任务分组
     * @throws SchedulerException
     */
    public void deleteJob(String triggerName, String triggerGroup, String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 修改Job
     *
     * @param oldTriggerKey 需要修改的TriggerKey 也就是唯一标识
     * @param cron          新的cron表达式
     */
    public void updateJob(TriggerKey oldTriggerKey, String cron) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(oldTriggerKey).withSchedule(scheduleBuilder).build();
        try {
            scheduler.rescheduleJob(oldTriggerKey, cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增job
     *
     * @param jobName          job名称
     * @param jobGroupName     job分组名称
     * @param triggerName      触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param jobClass         需要执行的job.class
     * @param cron             cron 表达式
     * @throws SchedulerException
     */
    public void addJob(String jobName, String jobGroupName,
                       String triggerName, String triggerGroupName, Class jobClass, String cron) throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

}
