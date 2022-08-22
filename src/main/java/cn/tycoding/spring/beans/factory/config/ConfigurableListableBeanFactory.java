package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.ListableBeanFactory;

/**
 * @author tycoding
 * @since 2022/8/9
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据Bean名称在BeanDefinition容器中匹配
     *
     * @param beanName Bean名称
     * @return BeanDefinition对象
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
