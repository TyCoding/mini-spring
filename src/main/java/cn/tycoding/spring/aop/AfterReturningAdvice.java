package cn.tycoding.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法正常返回后的通知
 *
 * @author tycoding
 * @since 2022/9/8
 */
public interface AfterReturningAdvice extends AfterAdvice {

    /**
     * 给定方法返回后的回调
     *
     * @param method 被调用的方法
     * @param args   方法的参数
     * @param target 被调用的目标
     */
    void afterRetuning(Method method, Object[] args, Object target) throws Throwable;
}
