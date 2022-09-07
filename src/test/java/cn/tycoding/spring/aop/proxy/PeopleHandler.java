package cn.tycoding.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tycoding
 * @since 2022/9/7
 */
public class PeopleHandler implements InvocationHandler {
    private Object target;
    public PeopleHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("this is PeopleHandler...");
        return method.invoke(target, args);
    }
}
