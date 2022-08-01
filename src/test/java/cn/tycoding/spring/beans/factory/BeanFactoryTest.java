package cn.tycoding.spring.beans.factory;

import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class BeanFactoryTest {

    @Test
    public void getBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registryBean("helloService", new HelloService());

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}