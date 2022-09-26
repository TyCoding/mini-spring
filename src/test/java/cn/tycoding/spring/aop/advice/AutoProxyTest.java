package cn.tycoding.spring.aop.advice;

import cn.tycoding.spring.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import cn.tycoding.spring.aop.proxy.People;
import cn.tycoding.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.tycoding.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/9/9
 */
public class AutoProxyTest {

    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop-auto-proxy.xml");

        People people = applicationContext.getBean("people", People.class);
        people.say();
    }

    @Test
    public void t2() {
        DefaultAdvisorAutoProxyCreator c = new DefaultAdvisorAutoProxyCreator();
        System.out.println(c instanceof InstantiationAwareBeanPostProcessor);

    }
}
