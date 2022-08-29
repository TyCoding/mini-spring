package cn.tycoding.spring.context;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.Aware;

/**
 * 继承此接口能够感知到所属的ApplicationContext
 *
 * @author tycoding
 * @since 2022/8/29
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置ApplicationContext对象
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
