package com.yc.rw.config;

import com.yc.rw.enums.RouteDataSourceKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 功能描述: 通过ThreadLocal将数据源设置到每个线程上下文中
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@Slf4j
public class DBContextHolder extends AbstractRoutingDataSource {

    private static final ThreadLocal<RouteDataSourceKeyEnum> contextHolder = new ThreadLocal<>();

    // private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(RouteDataSourceKeyEnum dbType) {
        contextHolder.set(dbType);
    }

    public static RouteDataSourceKeyEnum get() {
        return contextHolder.get();
    }

    /**
     * 获取当前数据源
     *
     * @return dbsource
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get() == null ? RouteDataSourceKeyEnum.MASTER.name() : contextHolder.get();
    }

    public static void master() {
        set(RouteDataSourceKeyEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave() {
        set(RouteDataSourceKeyEnum.SLAVE);
        log.info("切换到slave");

        /**
         *  //  轮询(多从库的情况)
         *         int index = counter.getAndIncrement() % 2;
         *         if (counter.get() > 9999) {
         *             counter.set(-1);
         *         }
         *         if (index == 0) {
         *             set(DBTypeEnum.SLAVE1);
         *             System.out.println("切换到slave1");
         *         }else {
         *             set(DBTypeEnum.SLAVE2);
         *             System.out.println("切换到slave2");
         *         }
         */
    }

    /**
     * 清空数据源信息
     */
    public static void clearDatabaseType() {
        contextHolder.remove();
    }

}
