package cn.tycoding.spring.context.event;

/**
 * ApplicationContext关闭触发的事件
 *
 * @author tycoding
 * @since 2022/8/31
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
