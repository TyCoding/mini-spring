package cn.tycoding.spring.beans.factory.config;

/**
 * 单例Bean注册接口
 *
 * @author tycoding
 * @since 2022/8/1
 */
public interface SingletonBeanRegistry {

    /**
     * 注册单例Bean
     *
     * @param beanName Bean名称
     * @param bean     Bean对象
     */
    void registrySingleton(String beanName, Object bean);

    /**
     * 获取单例Bean
     *
     * @param beanName bean名称
     * @return Bean对象
     */
    Object getSingleton(String beanName);
}
