package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.BeansException;

/**
 * Spring初始化Bean的扩展点
 * 和BeanPostProcessor不同的是，
 * BeanFactoryPostProcessor主要提供Bean实例化前，修改BeanDefinition的可能，从而影响Bean实例化结果
 *
 * @author tycoding
 * @since 2022/8/9
 */
public interface BeanFactoryPostProcessor {

    void postProcessorBeanFactory() throws BeansException;
}
