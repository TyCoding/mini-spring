package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;

/**
 * Cglib动态代理实例化Bean策略；Spring默认使用此方式
 *
 * @author tycoding
 * @since 2022/8/3
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        return null;
    }
}
