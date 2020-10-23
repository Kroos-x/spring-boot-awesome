package com.yc.rw.config;

import com.yc.rw.enums.RouteDataSourceKeyEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 功能描述: 切换数据源路由
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
public class RouteDataSourceConfig extends AbstractRoutingDataSource {

    /**
     * 线程安全容器，用于高并发情况下，保证切换数据源一致
     */
    private static ThreadLocal<String> local = new ThreadLocal<>();


    /**
     * 获取当前数据源
     *
     * @return dbsource
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return local.get() == null ? RouteDataSourceKeyEnum.MASTER.name() : local.get();
    }

    /**
     * 设置数据源
     *
     * @param keyEnum 枚举
     */
    public static void setDataSource(RouteDataSourceKeyEnum keyEnum) {
        local.set(keyEnum.name());
    }

    /**
     * 清空数据源信息
     */
    public static void clearDatabaseType() {
        local.remove();
    }

}
