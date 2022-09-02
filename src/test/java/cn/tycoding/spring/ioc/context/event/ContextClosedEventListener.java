package cn.tycoding.spring.ioc.context.event;

import cn.tycoding.spring.context.event.ApplicationListener;
import cn.tycoding.spring.context.event.ContextClosedEvent;

/**
 * @author tycoding
 * @since 2022/9/1
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("自定义Context关闭后事件监听");
    }
}
