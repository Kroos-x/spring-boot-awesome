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
public class QrtzSimpleTriggers implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_UUID)
    private String schedName;
    @TableField("TRIGGER_NAME")
    private String triggerName;
    @TableField("TRIGGER_GROUP")
    private String triggerGroup;
    @TableField("REPEAT_COUNT")
    private Long repeatCount;
    @TableField("REPEAT_INTERVAL")
    private Long repeatInterval;
    @TableField("TIMES_TRIGGERED")
    private Long timesTriggered;



}
