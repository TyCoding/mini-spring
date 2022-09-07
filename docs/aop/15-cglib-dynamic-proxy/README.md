# Aop - Cglib动态代理

> 当前文档对应Git分支：`15-cglib-dynamic-proxy`

基于上一章：[Aop之Jdk动态代理实现](../14-jdk-dynamic-proxy/README.md)；
Cglib实现动态代理要更简单，只需要构建`Enhancer`类即可：

CglibAopProxy

```java
public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object target = advised.getTargetSource().getTarget();
            if (advised.getMethodMatcher().matches(method, target.getClass())) {
                return advised.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(target, method, objects));
            }
            return method.invoke(target, objects);
        }
    }
}
```

测试

```java
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
```

打印

```java
before MethodInterceptor invoke ...
this is Student...
after MethodInterceptor invoke...
```
