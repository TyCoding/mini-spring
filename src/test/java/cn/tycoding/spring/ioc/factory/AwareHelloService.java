package cn.tycoding.spring.ioc.factory;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.BeanFactory;
import cn.tycoding.spring.beans.factory.BeanFactoryAware;
import cn.tycoding.spring.context.ApplicationContext;
import cn.tycoding.spring.context.ApplicationContextAware;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class AwareHelloService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public void sayHello() {
        System.out.println("HelloService sayHello()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
