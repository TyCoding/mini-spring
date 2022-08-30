package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/8/30
 */
public class FactoryBeanTest {

    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        HelloFactoryBean helloFactoryBean = applicationContext.getBean("helloFactoryBean", HelloFactoryBean.class);
        System.out.println(helloFactoryBean);
    }
}
