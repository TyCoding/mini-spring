package cn.tycoding.spring.context.event;

import java.util.EventListener;

/**
 * 事件监听
 *
 * @author tycoding
 * @since 2022/8/31
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 要处理的事件
     */
    void onApplicationEvent(E event);
}
