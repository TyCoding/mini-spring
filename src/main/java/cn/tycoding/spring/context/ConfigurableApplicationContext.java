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
     */
    void refresh() throws BeansException;

    /**
     * 关闭应用上下文
     */
    void close();

    /**
     * 向虚拟机中注册一个钩子函数，在虚拟机关闭之前执行关闭容器等操作
     */
    void registerShutdownHook();
}
