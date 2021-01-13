package com.yc.swagger.response;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 功能描述: 分页数据封装类
 *
 * @Author: xieyc
 * @Date: 2021-01-10
 */
@Data
@ApiModel
public class CommonPage<T> {

    @ApiModelProperty(value = "当前页码")
    protected long current = 1;

    @ApiModelProperty(value = "每页数量")
    protected long size = 10;

    @ApiModelProperty(value = "总页数")
    protected long totalPage;

    @ApiModelProperty(value = "总条数")
    protected long total;

    @ApiModelProperty(value = "分页数据")
    protected List<T> records = Collections.emptyList();

    @ApiModelProperty(value = "是否存在上一页")
    protected boolean hasPrevious = false;

    @ApiModelProperty(value = "是否存在下一页")
    public boolean hasNext = false;

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage((pageInfo.getTotal() / pageInfo.getSize()));
        result.setCurrent(pageInfo.getCurrent());
        result.setSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotal());
        result.setRecords(pageInfo.getRecords());
        result.setHasNext(pageInfo.hasNext());
        result.setHasPrevious(pageInfo.hasPrevious());
        return result;
    }

}
