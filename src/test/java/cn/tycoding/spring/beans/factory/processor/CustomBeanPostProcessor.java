package cn.tycoding.spring.beans.factory.processor;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.config.BeanPostProcessor;

/**
 * 自定义Bean实例化后 【初始化】前后处理器，影响Bean初始化结果
 *
 * @author tycoding
 * @since 2022/8/22
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("这是【BeanPostProcessor】自定义前置处理器，作用与Bean初始化前");

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("这是【BeanPostProcessor】自定义后置处理器，作用与Bean初始化后");

        return null;
    }
}
