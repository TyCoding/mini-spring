package cn.tycoding.spring.beans.factory;

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

        Object hello = beanFactory.getBean("hello");
        System.out.println(hello);
    }
}
