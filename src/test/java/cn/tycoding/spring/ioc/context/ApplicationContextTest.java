package cn.tycoding.spring.ioc.context;

import cn.tycoding.spring.ioc.factory.Hello;
import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/23
 */
public class ApplicationContextTest {

    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean-processor.xml");
        Hello hello = applicationContext.getBean("hello", Hello.class);

        System.out.println(hello);
    }
}
