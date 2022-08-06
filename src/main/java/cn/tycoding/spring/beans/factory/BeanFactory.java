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
     * @param name bean名称
     * @return Bean容器中的bean对象
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;
}
