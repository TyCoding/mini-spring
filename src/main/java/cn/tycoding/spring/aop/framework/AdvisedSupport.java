package cn.tycoding.spring.aop.framework;

import cn.tycoding.spring.aop.MethodMatcher;
import cn.tycoding.spring.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Aop代理配置管理器的基类，子类可以直接获取Aop代理
 *
 * @author tycoding
 * @since 2022/9/6
 */
public class AdvisedSupport {

    /**
     * 是否使用cglib代理，默认为false
     */
    private boolean proxyTargetClass = false;

    /**
     * 代理的目标对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
