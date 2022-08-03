package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Bean的简单实例化策略
 *
 * @author tycoding
 * @since 2022/8/3
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 使用构造方法实例化Bean
     *
     * @param beanDefinition Bean信息
     * @return Bean实例化后的对象
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 这里直接使用无参构造实例化
            Constructor constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
