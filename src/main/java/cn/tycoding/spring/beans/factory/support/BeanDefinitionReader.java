package cn.tycoding.spring.beans.factory.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.core.io.Resource;
import cn.tycoding.spring.core.io.ResourceLoader;

/**
 * 读取Bean信息（Bean Definition）的接口
 *
 * @author tycoding
 * @since 2022/8/7
 */
public interface BeanDefinitionReader {

    /**
     * 获取BeanDefinition对象注册的接口
     *
     * @return 返回注册对象
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载策略
     *
     * @return 返回匹配的策略实现类
     */
    ResourceLoader getResourceLoader();

    /**
     * 从指定位置加载BeanDefinition
     *
     * @param location 位置路径
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 从指定资源加载BeanDefinition
     *
     * @param resource 资源接口
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 从多个位置加载BeanDefinition
     *
     * @param locations 位置路径集合
     * @throws BeansException
     */
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
