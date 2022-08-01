package cn.tycoding.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean容器对象
 *
 * @author tycoding
 * @since 2022/8/1
 */
public class BeanFactory {

    private final Map<String, Object> beanMap = new HashMap<>();

    public void registryBean(String name, Object bean) {
        beanMap.put(name, bean);
    }

    public Object getBean(String name) {
        return beanMap.get(name);
    }
}
