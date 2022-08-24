package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.factory.HierarchicalBeanFactory;

/**
 * 提供对BeanFactory工厂配置修改的工具
 *
 * @author tycoding
 * @since 2022/8/9
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 添加Bean初始化处理器
     *
     * @param beanPostProcessor Bean初始化处理器
     */
    void addBeanPostProcess(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例Bean
     */
    void destroySingletons();
}
