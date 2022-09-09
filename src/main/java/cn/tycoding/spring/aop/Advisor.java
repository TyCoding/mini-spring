package cn.tycoding.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author tycoding
 * @since 2022/9/9
 */
public interface Advisor {

    Advice getAdvice();
}
