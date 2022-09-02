package cn.tycoding.spring.ioc.factory;

import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/29
 */
public class PrototypeBeanTest {

    /**
     * 测试不同bean scope类型
     */
    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-prototype-bean.xml");
        Hello singletonHello = applicationContext.getBean("singletonHello", Hello.class);
        Hello singletonHello2 = applicationContext.getBean("singletonHello", Hello.class);
        System.out.println(singletonHello == singletonHello2);

        Hello prototypeHello = applicationContext.getBean("prototypeHello", Hello.class);
        Hello prototypeHello2 = applicationContext.getBean("prototypeHello", Hello.class);
        System.out.println(prototypeHello == prototypeHello2);
    }
}
