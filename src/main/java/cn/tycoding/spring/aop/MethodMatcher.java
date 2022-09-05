package cn.tycoding.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法层面检查切入点方法函数是否符合
 *
 * @author tycoding
 * @since 2022/9/5
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
