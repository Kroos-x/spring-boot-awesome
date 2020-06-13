package com.yc.db.config.datasource;

/**
 * 功能描述：数据源常量
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-13
 * @Version: 1.0.0
 */
public class DataSourceConstant {

    // ****************** DataSource 1 *********************
    /**
     * 数据源配置前缀
     */
    public static final String DATA_SOURCE_1_PREFIX = "spring.datasource.db1";

    /**
     * Mapper 接口包地址
     */
    public static final String DATA_SOURCE_1_PACKAGES = "com.yc.db.mapper.db1";

    /**
     * mapper xml文件地址
     */
    public static final String DATA_SOURCE_1_MAPPER_LOCATION = "classpath:mybatis/mapper/db1/*.xml";


    //************** 数据源 2 配置 **************
    /**
     * 数据源配置 前缀
     */
    public static final String DATA_SOURCE_2_PREFIX = "spring.datasource.db2";
    /**
     * mapper 接口包地址
     */
    public static final String DATA_SOURCE_2_PACKAGES = "com.yc.db.mapper.db2";
    /**
     * mapper xml文件地址
     */
    public static final String DATA_SOURCE_2_MAPPER_LOCATION = "classpath:mybatis/mapper/db2/*.xml";
}
