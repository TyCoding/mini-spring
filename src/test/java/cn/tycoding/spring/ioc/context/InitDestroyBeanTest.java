package cn.tycoding.spring.ioc.context;

import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/24
 */
public class InitDestroyBeanTest {

    @Test
    public void t1() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-destroy-bean.xml");
        applicationContext.registerShutdownHook();
    }
}
