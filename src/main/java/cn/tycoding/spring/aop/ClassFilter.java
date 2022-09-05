package cn.tycoding.spring.aop;

/**
 * 类层面的切入点匹配活过滤
 *
 * @author tycoding
 * @since 2022/9/5
 */
public interface ClassFilter {


    boolean matches(Class<?> clazz);
}
