package cn.tycoding.spring.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK动态代理的Aop实现
 *
 * @author tycoding
 * @since 2022/9/6
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 拿到被代理的目标对象
        Object target = advised.getTargetSource().getTarget();
        // 检查当前方法是否匹配此目标对象
        if (advised.getMethodMatcher().matches(method, target.getClass())) {
            // 获取拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            // 构建拦截器链，执行拦截器
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
        }
        return method.invoke(target, args);
    }
}
