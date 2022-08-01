package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例Bean注册接口实现
 *
 * @author tycoding
 * @since 2022/8/1
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public void registrySingleton(String name, Object bean) {
        singletonObjects.put(name, bean);
    }

    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }


}
