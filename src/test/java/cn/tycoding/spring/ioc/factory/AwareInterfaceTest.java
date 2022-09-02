package cn.tycoding.spring.ioc.factory;

import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/29
 */
public class AwareInterfaceTest {

    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aware.xml");
        AwareHelloService awareHelloService = applicationContext.getBean("awareHelloService", AwareHelloService.class);
        System.out.println(awareHelloService.getApplicationContext());
        System.out.println(awareHelloService.getBeanFactory());
    }
}
