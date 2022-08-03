package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;

/**
 * BeanFactory的委托实现类，主要实现Bean的完整构建
 *
 * @author tycoding
 * @since 2022/8/1
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 引入实例化策略类
     * 在Spring源码中，此策略的默认实现类是CglibSubclassingInstantiationStrategy
     * 本版本中先使用Simple实现类
     */
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(name, beanDefinition);
    }

    protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        registrySingleton(name, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
