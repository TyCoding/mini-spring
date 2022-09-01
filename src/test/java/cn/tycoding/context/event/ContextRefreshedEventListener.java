package cn.tycoding.context.event;

import cn.tycoding.spring.context.event.ApplicationListener;
import cn.tycoding.spring.context.event.ContextRefreshedEvent;

/**
 * @author tycoding
 * @since 2022/9/1
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("自定义Context初始化事件监听");
    }
}
