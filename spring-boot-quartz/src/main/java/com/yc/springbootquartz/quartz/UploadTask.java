package com.yc.springbootquartz.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2021-01-03
 */
@Component
@DisallowConcurrentExecution
public class UploadTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + "任务开始------------------------------------");
        System.out.println(new Date() + "任务开始------------------------------------");
    }
}
