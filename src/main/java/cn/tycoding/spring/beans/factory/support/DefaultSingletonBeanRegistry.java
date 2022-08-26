package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.DisposableBean;
import cn.tycoding.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单例Bean注册接口实现
 *
 * @author tycoding
 * @since 2022/8/1
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public void registrySingleton(String beanName, Object bean) {
        singletonObjects.put(beanName, bean);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void removeSingleton(String beanName) {
        singletonObjects.remove(beanName);
    }

    public void destroySingletons() {
        Set<String> beanNames = disposableBeans.keySet();
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();

                // 销毁单例Bean
                removeSingleton(beanName);
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw a exception", e);
            }
        }
    }
}
