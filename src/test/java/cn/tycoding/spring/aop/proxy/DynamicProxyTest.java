package cn.tycoding.spring.aop.proxy;

import cn.tycoding.spring.aop.MethodMatcher;
import cn.tycoding.spring.aop.TargetSource;
import cn.tycoding.spring.aop.aspectj.AspectJExpressionPointcut;
import cn.tycoding.spring.aop.framework.AdvisedSupport;
import cn.tycoding.spring.aop.framework.CglibAopProxy;
import cn.tycoding.spring.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author tycoding
 * @since 2022/9/7
 */
public class DynamicProxyTest {

    @Test
    public void t1() throws Exception {
        byte[] bytes = ProxyGenerator.generateProxyClass("$ProxyPeople", new Class[]{People.class});
        String filePath = System.getProperty("user.dir") + "/target/$ProxyPeople.class";
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
    }

    @Test
    public void t2() {
        // 设置要增强的InvocationHandler
        InvocationHandler invocationHandler = new PeopleHandler(new Student());
        // 生成代理类
        People people = (People) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{People.class}, invocationHandler);
        people.say();
    }

    @Test
    public void t3() {
        People people = new Student();

        // 被代理的目标对象
        TargetSource targetSource = new TargetSource(people);
        // 自定义的拦截器
        PeopleInterceptor interceptor = new PeopleInterceptor();
        // 生成AspectJ的切点Pointcut，获取一个方法匹配器
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution( * cn.tycoding.spring.aop.proxy.People.say(..))").getMethodMatcher();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        People proxy = (People) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.say();
    }

    @Test
    public void t4() {
        People people = new Student();

        // 被代理的目标对象
        TargetSource targetSource = new TargetSource(people);
        // 自定义的拦截器
        PeopleInterceptor interceptor = new PeopleInterceptor();
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
