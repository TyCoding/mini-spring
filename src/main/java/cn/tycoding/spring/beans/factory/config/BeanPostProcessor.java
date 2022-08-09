package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.BeansException;

/**
 * Spring初始化Bean的扩展点
 * 提供两个方法分别在Bean初始化前和初始化后执行（实例化后）
 * 主要针对实例化后Bean的初始化过程，提供修改Bean定义的可能
 *
 * @author tycoding
 * @since 2022/8/9
 */
public interface BeanPostProcessor {

    /**
     * Bean初始化之前执行
     *
     * @param bean     Bean实例
     * @param beanName Bean名称
     * @return 处理后的Bean实例
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * Bean初始化之后执行
     *
     * @param bean     Bean实例
     * @param beanName Bean名称
     * @return 处理后的Bean实例
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
