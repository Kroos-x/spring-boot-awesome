package com.yc.springbootquartz.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
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
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_UUID)
    private String schedName;
    @TableField("JOB_NAME")
    private String jobName;
    @TableField("JOB_GROUP")
    private String jobGroup;
    @TableField("DESCRIPTION")
    private String description;
    @TableField("JOB_CLASS_NAME")
    private String jobClassName;
    @TableField("IS_DURABLE")
    private String isDurable;
    @TableField("IS_NONCONCURRENT")
    private String isNonconcurrent;
    @TableField("IS_UPDATE_DATA")
    private String isUpdateData;
    @TableField("REQUESTS_RECOVERY")
    private String requestsRecovery;
    @TableField("JOB_DATA")
    private Blob jobData;



}
