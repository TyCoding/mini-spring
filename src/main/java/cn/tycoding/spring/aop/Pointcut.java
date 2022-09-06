package cn.tycoding.spring.aop;

/**
 * @author tycoding
 * @since 2022/9/5
 */
public interface Pointcut {

    /**
     * 获取Class层切入点匹配器
     */
    ClassFilter getClassFilter();

    /**
     * 获取函数层切入点匹配器
     */
    MethodMatcher getMethodMatcher();
}
