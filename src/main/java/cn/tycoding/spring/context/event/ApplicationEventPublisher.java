package cn.tycoding.spring.context.event;

/**
 * 发布事件
 *
 * @author tycoding
 * @since 2022/8/31
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(ApplicationEvent event);
}
