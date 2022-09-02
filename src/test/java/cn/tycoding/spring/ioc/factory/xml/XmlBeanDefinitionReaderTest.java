package cn.tycoding.spring.ioc.factory.xml;

import cn.tycoding.spring.beans.factory.support.BeanDefinitionReader;
import cn.tycoding.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.tycoding.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * 测试从XML文件装配BeanDefinition
 *
 * @author tycoding
 * @since 2022/8/8
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void t1() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-bean-definition.xml");

        Object hello = beanFactory.getBean("hello");
        System.out.println(hello);

        Object helloService = beanFactory.getBean("helloService");
        System.out.println(helloService);
    }
}
