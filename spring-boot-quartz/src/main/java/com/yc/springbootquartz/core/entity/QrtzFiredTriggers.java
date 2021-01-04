package com.yc.springbootquartz.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述:
 *
 * @Author xieyc
 * @Date 2021-01-03
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QrtzFiredTriggers implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_UUID)
    private String schedName;
    @TableField("ENTRY_ID")
    private String entryId;
    @TableField("TRIGGER_NAME")
    private String triggerName;
    @TableField("TRIGGER_GROUP")
    private String triggerGroup;
    @TableField("INSTANCE_NAME")
    private String instanceName;
    @TableField("FIRED_TIME")
    private Long firedTime;
    @TableField("SCHED_TIME")
    private Long schedTime;
    @TableField("PRIORITY")
    private Integer priority;
    @TableField("STATE")
    private String state;
    @TableField("JOB_NAME")
    private String jobName;
    @TableField("JOB_GROUP")
    private String jobGroup;
    @TableField("IS_NONCONCURRENT")
    private String isNonconcurrent;
    @TableField("REQUESTS_RECOVERY")
    private String requestsRecovery;



}
