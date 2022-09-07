package cn.tycoding.spring.aop.framework;

/**
 * Aop代理工厂
 *
 * @author tycoding
 * @since 2022/9/7
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 创建Aop代理：Jdk动态代理或者Cglib动态代理
     * 在Spring源码中默认关闭cglib代理，而具体生成代理的策略取决于被代理类是什么
     * 具体查看Spring源码DefaultAopProxyFactory类
     * 当被代理类是接口时就会使用JDK动态代理
     */
    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
