package com.yc.db.service;

import com.yc.db.entity.Test;

import java.util.List;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-13
 * @Version: 1.0.0
 */
public interface TestService {

    List<Test> listAll_one();

    List<Test> listAll_two();
}
