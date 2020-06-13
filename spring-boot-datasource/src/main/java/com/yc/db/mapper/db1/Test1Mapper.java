package com.yc.db.mapper.db1;

import com.yc.db.entity.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-13
 * @Version: 1.0.0
 *
 */
@Repository
public interface Test1Mapper {

    /**
     * 所有信息
     * @return list
     */
    List<Test> listAll();
}
