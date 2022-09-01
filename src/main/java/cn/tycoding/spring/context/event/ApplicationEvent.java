package cn.tycoding.spring.context.event;

import java.util.EventObject;

/**
 * 事件通知的基类
 *
 * @author tycoding
 * @since 2022/8/31
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
