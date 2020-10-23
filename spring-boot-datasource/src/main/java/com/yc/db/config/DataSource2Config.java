package com.yc.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-13
 * @Version: 1.0.0
 */
@Configuration
@MapperScan(basePackages = DataSourceConstant.DATA_SOURCE_2_PACKAGES, sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSource2Config {

    /**
     * 数据源配置
     * 使用的连接池是 DruidDataSource
     * <p>
     * 注解ConfigurationProperties
     * 作用就是将全局配置文件中的属性值注入到DruidDataSource 的同名参数
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = DataSourceConstant.DATA_SOURCE_2_PREFIX)
    public DataSource testDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * 创建 SqlSessionFactory 工厂
     */
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //数据源
        bean.setDataSource(dataSource);
        //mapper 地址
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConstant.DATA_SOURCE_2_MAPPER_LOCATION));
        return bean.getObject();
    }

    /**
     * 事务管理
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * MyBatis提供的持久层访问模板化的工具
     * 线程安全，可通过构造参数或依赖注入SqlSessionFactory实例。
     */
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
