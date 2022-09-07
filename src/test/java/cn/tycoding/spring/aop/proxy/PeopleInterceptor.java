package cn.tycoding.spring.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author tycoding
 * @since 2022/9/7
 */
public class PeopleInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before MethodInterceptor invoke ...");
        Object proceed = invocation.proceed();
        System.out.println("after MethodInterceptor invoke...");
        return proceed;
    }
}
