package com.yc.rw.aop;

import com.yc.rw.config.DBContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 配置切面规则
 *
 * @Author: xieyc
 * @Date: 2020-10-23
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * 只读：
     * 不是Master注解的对象或方法  && select开头的方法  ||  get开头的方法
     */
    @Pointcut("!@annotation(com.yc.rw.annotation.Master) " +
            "&& (execution(* com.yc.rw.service..*.select*(..)) " +
            "|| execution(* com.yc.rw.service..*.list*(..)) " +
            "|| execution(* com.yc.rw.service..*.page*(..)) " +
            "|| execution(* com.yc.rw.service..*.query*(..)) " +
            "|| execution(* com.yc.rw.service..*.find*(..)) " +
            "|| execution(* com.yc.rw.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写：
     * Master注解的对象或方法 || insert开头的方法  ||  add开头的方法 || update开头的方法
     * || edlt开头的方法 || delete开头的方法 || remove开头的方法
     */
    @Pointcut("@annotation(com.yc.rw.annotation.Master) " +
            "|| execution(* com.yc.rw.service..*.insert*(..)) " +
            "|| execution(* com.yc.rw.service..*.add*(..)) " +
            "|| execution(* com.yc.rw.service..*.save*(..)) " +
            "|| execution(* com.yc.rw.service..*.update*(..)) " +
            "|| execution(* com.yc.rw.service..*.edit*(..)) " +
            "|| execution(* com.yc.rw.service..*.delete*(..)) " +
            "|| execution(* com.yc.rw.service..*.del*(..)) " +
            "|| execution(* com.yc.rw..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

    /**
     * 配置后置处理，清空数据源信息
     *
     * @param point 切点
     */
    @After("readPointcut()")
    public void after(JoinPoint point) {
        DBContextHolder.clearDatabaseType();
    }
}
