package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Cglib动态代理实例化Bean策略；Spring默认使用此方式
 *
 * @author tycoding
 * @since 2022/8/3
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * Cglib实例化
     *
     * @param beanDefinition Bean信息
     * @return Cglib实例化后的Bean对象
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        return enhancer.create();
    }
}
