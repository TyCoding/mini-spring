package cn.tycoding.spring.aop.advice;

import cn.tycoding.spring.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author tycoding
 * @since 2022/9/8
 */
public class PeopleAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterRetuning(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("people method run after...");
    }
}
