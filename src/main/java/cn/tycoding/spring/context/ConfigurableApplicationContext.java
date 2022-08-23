package cn.tycoding.spring.context;

import cn.tycoding.spring.beans.BeansException;

/**
 * 应用上下文配置父类接口
 *
 * @author tycoding
 * @since 2022/8/23
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 加载或刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
