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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 功能描述:数据源配置
 * <p>版权所有:</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-13
 * @Version: 1.0.0
 */
@Configuration
@MapperScan(basePackages = DataSourceConstant.DATA_SOURCE_1_PACKAGES, sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSource1Config {

    /**
     * 数据源配置
     */
    @Primary
    @Bean(name = "db1DataSource")
    @Qualifier("db1DataSource")
    @ConfigurationProperties(prefix = DataSourceConstant.DATA_SOURCE_1_PREFIX)
    public DataSource testDataSource() {
        //DataSourceBuilder.create().build() 默认数据源类型是 org.apache.tomcat.jdbc.pool.DataSource
        //这里指定使用类型 -- 阿里DruidDataSource 连接池
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }


    /**
     * 创建 SqlSessionFactory 工厂
     */
    @Primary
    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConstant.DATA_SOURCE_1_MAPPER_LOCATION));
        return bean.getObject();
    }

    /**
     * 事务管理
     */
    @Primary
    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * MyBatis提供的持久层访问模板化的工具
     * 线程安全，可通过构造参数或依赖注入SqlSessionFactory实例
     */
    @Primary
    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
