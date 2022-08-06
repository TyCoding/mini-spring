package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.MutablePropertyValues;
import cn.tycoding.spring.beans.PropertyValue;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import cn.tycoding.spring.beans.factory.config.BeanReference;
import cn.tycoding.spring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class BeanFactoryTest {

    @Test
    public void getBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registryBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();

        Object singletonBean = beanFactory.getSingleton("helloService");
        System.out.println(singletonBean == null);
    }

    /**
     * 测试填充Bean属性
     */
    @Test
    public void setBeanPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 设置Bean属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "tycoding"));
        propertyValues.addPropertyValue(new PropertyValue("des", "hello"));
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
        beanFactory.registryBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
        System.out.println(helloService);
    }

    /**
     * 测试包含Bean引用的Bean属性填充
     */
    @Test
    public void setBeanPropertyBeanValue() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注入Hello Bean
        MutablePropertyValues beanPros = new MutablePropertyValues();
        beanPros.addPropertyValue(new PropertyValue("msg", "this hello bean"));
        BeanDefinition helloDef = new BeanDefinition(Hello.class, beanPros);
        beanFactory.registryBeanDefinition("hello", helloDef);

        // 注入HelloService Bean
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "tycoding"));
        propertyValues.addPropertyValue(new PropertyValue("des", "hello"));
        propertyValues.addPropertyValue(new PropertyValue("hello", new BeanReference("hello")));
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
        beanFactory.registryBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
        System.out.println(helloService);
    }
}