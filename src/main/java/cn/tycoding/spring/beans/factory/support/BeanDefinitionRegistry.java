package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册接口
 *
 * @author tycoding
 * @since 2022/8/1
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     *
     * @param name           bean名称
     * @param beanDefinition definition对象
     */
    void registryBeanDefinition(String name, BeanDefinition beanDefinition);
}
