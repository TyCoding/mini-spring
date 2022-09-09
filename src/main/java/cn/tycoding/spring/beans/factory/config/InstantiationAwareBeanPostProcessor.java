package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.BeansException;

/**
 * Bean实例化前的BeanPostProcessor子接口
 * 用于替代目标Bean的默认实例化，单独创建具有特殊TargetSource的代理
 *
 * @author tycoding
 * @since 2022/9/9
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 目标Bean实例化之前应用
     *
     * @param beanClass 目标bean
     * @param beanName  bean名称
     * @return 代理后的Bean实例（不是默认bean实例）
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
