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
     * @param name Bean名称
     * @param bean Bean对象
     */
    void registrySingleton(String name, Object bean);

    /**
     * 获取单例Bean
     *
     * @param name bean名称
     * @return Bean对象
     */
    Object getSingleton(String name);
}
