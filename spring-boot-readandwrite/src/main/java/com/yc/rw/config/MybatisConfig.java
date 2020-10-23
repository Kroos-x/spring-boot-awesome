package com.yc.rw.config;

import com.yc.rw.enums.RouteDataSourceKeyEnum;
import org.mybatis.spring.annotation.MapperScan;
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
 * 功能描述: 配置mybatis数据源
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@Configuration
@MapperScan(basePackages = "com.yc.rw.mapper")
public class MybatisConfig {

    /**
     * @Description 主数据库配置
     * @date 2020/4/17 18:11
     * @author cp
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @param masterDataSource, slaveDataSource
     * @return javax.sql.DataSource
     * @Description 自定义数据源，内部持有主库和从库的数据源
     * 通过某种机制让应用程序在进行数据读写时，按业务情况走主库还是从库
     * @method myRoutingDataSource
     * @date 2020/4/17 19:21
     * @author cp
     */
    @Bean
    @Primary
    public DataSource myRoutingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(RouteDataSourceKeyEnum.MASTER, masterDataSource);
        targetDataSource.put(RouteDataSourceKeyEnum.SLAVE, slaveDataSource);
        RouteDataSourceConfig myRoutingDataSource = new RouteDataSourceConfig();
        //未在AOP织入的方法内，默认走主库
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        //通过AOP织入的方法，自动选择主/从库
        myRoutingDataSource.setTargetDataSources(targetDataSource);
        return myRoutingDataSource;
    }


}
