package cn.tycoding.spring.context.event;

import cn.tycoding.spring.context.ApplicationContext;

/**
 * ApplicationContext事件的基类
 *
 * @author tycoding
 * @since 2022/8/31
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取事件产生的ApplicationContext对象
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
