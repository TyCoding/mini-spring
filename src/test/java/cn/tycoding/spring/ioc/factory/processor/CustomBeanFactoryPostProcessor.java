package cn.tycoding.spring.ioc.factory.processor;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.MutablePropertyValues;
import cn.tycoding.spring.beans.PropertyValue;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import cn.tycoding.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.tycoding.spring.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 自定义Bean实例化前的处理器，影响Bean实例化结果
 *
 * @author tycoding
 * @since 2022/8/22
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("这是【BeanFactoryPostProcessor】自定义后置处理器，作用与Bean实例化前");

        // Bean实例化前修改BeanDefinition内容
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("hello");
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        // 修改/覆盖属性值
        propertyValues.addPropertyValue(new PropertyValue("des", "这是前置处理器增加的"));

    }
}
