package cn.tycoding.spring.context.event;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.BeanFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ApplicationEventMulticaster接口的简单实现，将事件多播到所有注册的监听器中，让监听器自己过滤不感兴趣的事件
 *
 * @author tycoding
 * @since 2022/8/31
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        // 调用getGenericInterfaces()目的是拿到范型接口，正常通过class.getGenericInterfaces()即可获取到
        // 但是项目使用了Cglib代理生成Bean实例，因此先调用getSuperclass()方法拿到上层被代理类的Class对象（因为代理类是继承被代理类实现的）
        Type type = applicationListener.getClass().getSuperclass().getGenericInterfaces()[0];
        if (type instanceof ParameterizedType) {
            Type argument = ((ParameterizedType) type).getActualTypeArguments()[0];
            String className = argument.getTypeName();
            Class<?> eventClassName;
            try {
                eventClassName = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Wrong event class name: " + className);
            }
            return eventClassName.isAssignableFrom(event.getClass());
        }
        return false;
    }
}
