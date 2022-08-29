package cn.tycoding.spring.beans.factory;

import cn.tycoding.spring.beans.BeansException;

/**
 * 实现该接口可以感知到自己所属的BeanFactory
 *
 * @author tycoding
 * @since 2022/8/29
 */
public interface BeanFactoryAware extends Aware {

    /**
     * 设置BeanFactory对象
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
