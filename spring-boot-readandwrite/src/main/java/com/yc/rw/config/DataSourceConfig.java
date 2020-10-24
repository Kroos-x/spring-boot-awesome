package com.yc.rw.config;

import com.yc.rw.enums.RouteDataSourceKeyEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 数据源配置
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@Configuration
public class DataSourceConfig {

    /**
     * 写库
     *
     * @return db
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 读库
     *
     * @return db
     */
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(RouteDataSourceKeyEnum.MASTER, masterDataSource);
        targetDataSources.put(RouteDataSourceKeyEnum.SLAVE, slaveDataSource);
        DBContextHolder myRoutingDataSource = new DBContextHolder();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }


}
