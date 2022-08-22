package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.factory.processor.CustomBeanFactoryPostProcessor;
import cn.tycoding.spring.beans.factory.processor.CustomBeanPostProcessor;
import cn.tycoding.spring.beans.factory.support.BeanDefinitionReader;
import cn.tycoding.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.tycoding.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/9
 */
public class BeanProcessorTest {

    @Test
    public void t1() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-bean-definition.xml");

        // BeanDefinition加载后，使用BeanFactoryProcessor，在Bean实例化前影响BeanDefinition结果
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        Object hello = beanFactory.getBean("hello");
        System.out.println(hello);
    }

    @Test
    public void t2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-bean-definition.xml");

        // BeanDefinition加载后，添加Bean实例化后的处理器，作用与Bean初始化前或者初始化后
        CustomBeanPostProcessor beanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcess(beanPostProcessor);

        Object hello = beanFactory.getBean("hello");
        System.out.println(hello);
    }
}
