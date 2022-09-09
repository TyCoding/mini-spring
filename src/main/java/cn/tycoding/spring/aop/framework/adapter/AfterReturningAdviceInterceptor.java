package cn.tycoding.spring.aop.framework.adapter;

import cn.tycoding.spring.aop.AfterReturningAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 后置通知拦截器
 *
 * @author tycoding
 * @since 2022/9/8
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor {

    private AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor() {
    }

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        // 等待函数执行后再执行后置拦截器
        advice.afterRetuning(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return proceed;
    }
}
