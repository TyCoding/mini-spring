package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.factory.BeanFactory;
import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;

/**
 * BeanFactory接口实现类
 *
 * @author tycoding
 * @since 2022/8/1
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 创建Bean对象
     *
     * @param name           bean名称
     * @param beanDefinition bean实例
     * @return bean对象
     * @throws BeansException
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 获取Bean实例
     *
     * @param name bean名称
     * @return Bean实例
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;
}
