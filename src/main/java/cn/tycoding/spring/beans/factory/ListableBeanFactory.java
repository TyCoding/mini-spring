package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.BeansException;

import java.util.Map;

/**
 * BeanFactory接口的扩展，提供获取所有Bean实例信息的方法
 *
 * @author tycoding
 * @since 2022/8/9
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 返回匹配type的所有Bean实例
     *
     * @param type Bean类型
     * @param <T>  Bean实例
     * @return Bean实例集合
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回Bean工厂下所有Bean名称
     *
     * @return 名称集合
     */
    String[] getBeanDefinitionNames();
}
