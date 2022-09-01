package cn.tycoding.spring.context.event;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.BeanFactory;
import cn.tycoding.spring.beans.factory.BeanFactoryAware;

import java.util.HashSet;
import java.util.Set;

/**
 * ApplicationEventMulticaster接口的抽象实现，提供基本的监听器注册工具
 * 其中multicastEvent()交由子类实现，在SimpleApplicationEventMulticaster中此方法仅是将事件发送到所有的监听器中
 *
 * @author tycoding
 * @since 2022/8/31
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
