package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.BeansException;

/**
 * Bean容器对象
 *
 * @author tycoding
 * @since 2022/8/1
 */
public interface BeanFactory {

    /**
     * 获取Bean对象
     *
     * @param beanName bean名称
     * @return Bean容器中的bean对象
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 获取指定类型的Bean对象
     * 在Spring源码中也是进行类型强制转换，不过会判断是否是此类型并做细化处理，在这里直接强制转换了
     *
     * @param beanName     bean名称
     * @param requiredType Bean类型
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;

}
