package cn.tycoding.spring.aop;

import java.lang.reflect.Method;

/**
 * 调用方法之前的调用通知
 *
 * @author tycoding
 * @since 2022/9/8
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 调用方法之前的回调
     *
     * @param method 被调用的方法
     * @param args   方法的参数
     * @param target 被调用的目标
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
