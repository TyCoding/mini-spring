package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.BeanFactory;

/**
 * 提供可自动装配的Bean工厂实现
 *
 * @author tycoding
 * @since 2022/8/9
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 用于执行BeanPostProcess的postProcessBeforeInitialization方法
     *
     * @param existingBean 已存在的Bean实例
     * @param beanName     bean名称
     * @return 处理后的Bean实例
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 用于执行BeanPostProcess的postProcessAfterInitialization方法
     *
     * @param existingBean 已存在的Bean实例
     * @param beanName     bean名称
     * @return 处理后的Bean实例
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
