package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.factory.config.BeanDefinition;
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
}