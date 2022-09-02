package cn.tycoding.spring.ioc.context.event;

import cn.tycoding.spring.context.event.ApplicationListener;

/**
 * @author tycoding
 * @since 2022/9/1
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("自定义事件监听器");
    }
}
