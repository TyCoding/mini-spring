package cn.tycoding.spring.aop.framework;

/**
 * 已配置Aop代理的委托接口
 *
 * @author tycoding
 * @since 2022/9/6
 */
public interface AopProxy {

    /**
     * 创建一个新的代理对象
     */
    Object getProxy();
}
