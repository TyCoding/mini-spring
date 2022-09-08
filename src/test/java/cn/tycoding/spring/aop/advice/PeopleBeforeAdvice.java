package cn.tycoding.spring.aop.advice;

import cn.tycoding.spring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author tycoding
 * @since 2022/9/8
 */
public class PeopleBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("people method run before...");
    }
}
