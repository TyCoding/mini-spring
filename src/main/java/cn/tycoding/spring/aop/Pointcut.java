package cn.tycoding.spring.aop;

/**
 * @author tycoding
 * @since 2022/9/5
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
