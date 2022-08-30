package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * FactoryBean的管理，集成DefaultSingletonBeanRegistry
 *
 * @author tycoding
 * @since 2022/8/30
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    /**
     * 如果Bean是FactoryBean，则返回getObject创建的Bean
     */
    protected Object getObjectFromFactoryBean(Object beanInstance, String beanName) {
        Object bean = beanInstance;
        if (bean instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) bean;
            try {
                if (factoryBean.isSingleton()) {
                    // singleton模式，从缓存获取
                    Object obj = this.factoryBeanObjectCache.get(beanName);
                    if (obj == null) {
                        bean = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, bean);
                    }
                } else {
                    // prototype模式，创建新的实例
                    bean = factoryBean.getObject();
                }
            } catch (Exception e) {
                throw new BeansException("FactoryBean throw exception on object [" + beanName + "] creation", e);
            }
        }
        return bean;
    }
}
