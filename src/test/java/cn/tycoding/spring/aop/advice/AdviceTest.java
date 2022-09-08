package cn.tycoding.spring.aop.advice;

import cn.tycoding.spring.aop.MethodMatcher;
import cn.tycoding.spring.aop.TargetSource;
import cn.tycoding.spring.aop.aspectj.AspectJExpressionPointcut;
import cn.tycoding.spring.aop.framework.AdvisedSupport;
import cn.tycoding.spring.aop.framework.CglibAopProxy;
import cn.tycoding.spring.aop.framework.adapter.AfterReturningAdviceInterceptor;
import cn.tycoding.spring.aop.proxy.People;
import cn.tycoding.spring.aop.proxy.Student;
import org.junit.Test;

/**
 * @author tycoding
 * @since 2022/9/8
 */
public class AdviceTest {

    @Test
    public void t1() {
        People people = new Student();

        // 被代理的目标对象
        TargetSource targetSource = new TargetSource(people);

        // 前置拦截器
//        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(new PeopleBeforeAdvice());
        // 后置拦截器
        AfterReturningAdviceInterceptor interceptor = new AfterReturningAdviceInterceptor(new PeopleAfterAdvice());

        // 生成AspectJ的切点Pointcut，获取一个方法匹配器
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution( * cn.tycoding.spring.aop.proxy.People.say(..))").getMethodMatcher();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        People proxy = (People) new CglibAopProxy(advisedSupport).getProxy();
        proxy.say();
    }
}
