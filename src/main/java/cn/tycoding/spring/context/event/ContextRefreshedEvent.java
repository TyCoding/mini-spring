package cn.tycoding.spring.context.event;

/**
 * 初始化或刷新ApplicationContext触发的事件
 *
 * @author tycoding
 * @since 2022/8/31
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
