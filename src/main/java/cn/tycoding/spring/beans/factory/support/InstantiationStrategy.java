package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;

/**
 * Bean 实例化策略接口；可以通过CGLIB等不同形式实例化Bean
 *
 * @author tycoding
 * @since 2022/8/3
 */
public interface InstantiationStrategy {

    /**
     * 实例化Bean
     *
     * @param beanDefinition Bean信息
     * @return 实例化后的Bean对象
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
