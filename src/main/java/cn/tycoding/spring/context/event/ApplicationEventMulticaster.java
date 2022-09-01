package cn.tycoding.spring.context.event;

/**
 * 管理多个ApplicationListener对象并向他们发布事件的对象实现的接口
 *
 * @author tycoding
 * @since 2022/8/31
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加一个监听器以接收所有事件通知
     *
     * @param listener 事件监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 删除一个监听器
     *
     * @param listener 事件监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 将给定的事件多播到适当的监听器
     *
     * @param event 事件
     */
    void multicastEvent(ApplicationEvent event);
}
