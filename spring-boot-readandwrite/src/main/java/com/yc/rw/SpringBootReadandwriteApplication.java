package com.yc.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootReadandwriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReadandwriteApplication.class, args);
    }

    /**
     * 实现方式：
     * 读写分离实现的方式有多种，但是每种都需要配置数据库的主从复制
     *  1.数据库中间件实现，如Mycat等数据库中间件，对于项目本身来说，只有一个数据源，就是链接到Mycat，再由mycat根据规则去选择从哪个库获取数据
     *  2.基于AOP切面实现读写分离、
     *
     * 优点
     * 1.降低数据库读取压力，尤其是有些需要大量计算的实时报表类应用
     * 2.增强数据安全性，读写分离有个好处就是数据近乎实时备份，一旦某台服务器硬盘发生了损坏，从库的数据可以无限接近主库
     * 3.可以实现一定程度高可用，当然只是配置了读写分离并不能实现完全高可用，最多就是在Master（主库）宕机了还能进行查询操作，具体高可用还需要其他操作
     *
     * 缺点
     * 1.增大成本，一台数据库服务器和多台数据库的成本肯定是不一样的
     * 2.增大代码复杂度，不过这点还比较轻微吧，但是也的确会一定程度上加重
     * 3.增大写入成本，虽然降低了读取成本，但是写入成本却是一点也没有降低，毕竟还有从库一直在向主库请求数据
     *
     * 实践：
     *  1、yml配置多个数据源连接
     *  2、DataSourceConfig 多数据源配置
     *  3、MyBatis配置
     *  4、DBContextHolder 通过ThreadLocal将数据源设置到每个线程上下文中
     *  5、DataSourceAop 配置切面规则
     *  6、@interface Master 特殊情况下需要强制读主库，用该注解标注
     */


}
