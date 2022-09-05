package cn.tycoding.spring.aop;

import cn.tycoding.spring.aop.aspectj.AspectJExpressionPointcut;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author tycoding
 * @since 2022/9/5
 */
public class PointcutExpressionTest {

    @Test
    public void t1() throws Exception {
        AspectJExpressionPointcut pointcut =
                new AspectJExpressionPointcut("execution(* cn.tycoding.spring.aop.AopHelloService.*(..))");
        Class<AopHelloService> clazz = AopHelloService.class;
        Method method = clazz.getDeclaredMethod("say");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

        System.out.println("----");
    }
}
