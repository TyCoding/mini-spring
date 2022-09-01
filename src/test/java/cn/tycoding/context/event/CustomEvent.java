package cn.tycoding.context.event;

import cn.tycoding.spring.context.ApplicationContext;
import cn.tycoding.spring.context.event.ApplicationContextEvent;

/**
 * @author tycoding
 * @since 2022/9/1
 */
public class CustomEvent extends ApplicationContextEvent {

    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
