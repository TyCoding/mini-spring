package cn.tycoding.spring.aop;

/**
 * @author tycoding
 * @since 2022/9/9
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
