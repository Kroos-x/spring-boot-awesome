package com.yc.rw.aop;

import com.yc.rw.config.RouteDataSourceConfig;
import com.yc.rw.enums.RouteDataSourceKeyEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述: 切面类 配置读写分离规则
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@Aspect
@Order(1)
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

    /**
     * 正则表达式 匹配
     */
    private static final String REGULA_REXPRESSION = "^get|^find|^query|^list|^page";

    /**
     * 切入点
     */
    @Pointcut("execution(* com.yc.rw.service.*.*(..))")
    public void aspect() {

    }

    /**
     * 配置前置处理,使用在方法aspect()上注册的切入点，绑定数据源信息
     */
    @Before("aspect()")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?> classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            //通过判断执行方法的前缀是不是get、find、query ，如果是则从从库中查询数据源
            //此方法一般根据业务需要做相应的调整
            Pattern p = Pattern.compile(REGULA_REXPRESSION);
            Matcher match = p.matcher(m.getName());
            if (match.find()) {
                RouteDataSourceConfig.setDataSource(RouteDataSourceKeyEnum.SLAVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置后置处理，清空数据源信息
     *
     * @param point 切点
     */
    @After("aspect()")
    public void after(JoinPoint point) {
        RouteDataSourceConfig.clearDatabaseType();
    }

}
