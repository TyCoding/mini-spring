# Aop之切面Advisor

> 当前文档对应Git分支：`19-auto-proxy`

## 引入

在前面我们介绍了Advice通知和Pointcut切点以及Advisor切面（Pointcut + Advice）

看之前的测试代码：

```java
    @Test
    public void t2() {
        People people = new Student();

        // 被代理的目标对象
        TargetSource targetSource = new TargetSource(people);

        // 后置拦截器
        AfterReturningAdviceInterceptor interceptor = new AfterReturningAdviceInterceptor(new PeopleAfterAdvice());

        // 定义一个切面Advisor=切点+通知
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setAdvice(interceptor);
        advisor.setExpression("execution( * cn.tycoding.spring.aop.proxy.People.say(..))");

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

        People proxy = (People) new CglibAopProxy(advisedSupport).getProxy();
        proxy.say();
    }
```

可以看到几个问题：

1. 代理信息要Java配置
2. 要手动创建拦截器实例
3. 要手动配置切点、切面信息

其实用Java代码配置也是一种实现方式，但是我们都知道用配置文件（甚至是注解配置）才是最方便的；

如何将上面的代码配置化呢？我们先看一下最终的配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd"> <!-- bean definitions here -->
    
    <bean id="people" class="cn.tycoding.spring.aop.proxy.Student"/>

    <bean class="cn.tycoding.spring.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>

    <bean id="pointcutAdvisor" class="cn.tycoding.spring.aop.aspectj.AspectJExpressionPointcutAdvisor">
        <property name="expression" value="execution( * cn.tycoding.spring.aop.proxy.People.say(..))"/>
        <property name="advice" ref="methodInterceptor"/>
    </bean>

    <bean id="methodInterceptor" class="cn.tycoding.spring.aop.framework.adapter.MethodBeforeAdviceInterceptor">
        <property name="advice" ref="beforeAdvice"/>
    </bean>

    <bean id="beforeAdvice" class="cn.tycoding.spring.aop.advice.PeopleBeforeAdvice"/>

</beans>
```

可以看到这个XML配置基本把上述三个问题解决了：

1. XML配置advisor，定义切面表达式expression和通知拦截器advice
2. XML配置advice，通知就是MethodInterceptor拦截器

下面的问题就是需要等上述Bean注入IOC容器后，在Bean实例化前将上述这些Bean进行特殊处理，即将XML配置写入到Java配置中

## InstantiationAwareBeanPostProcessor

上面提到了我们需要在Bean实例化前，对上述的切面相关类进行特殊处理；
因此，在这里定义了一个公共层接口，他是一种特殊的`BeanPostProcessor`，和其他BeanPostProcessor不一样的是:
`InstantiationAwareBeanPostProcessor`是一种特殊的Bean实例化方式， 它将代替默认的Bean实例化方式，只针对实现该接口的类调用具体的实现逻辑：`postProcessBeforeInstantiation()`

```java
/**
 * Bean实例化前的BeanPostProcessor子接口
 * 用于替代目标Bean的默认实例化，单独创建具有特殊TargetSource的代理
 *
 * @author tycoding
 * @since 2022/9/9
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 目标Bean实例化之前应用
     *
     * @param beanClass 目标bean
     * @param beanName  bean名称
     * @return 代理后的Bean实例（不是默认bean实例）
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
```

## AbstractAutowireCapableBeanFactory

我们知道`AbstractAutowireCapableBeanFactory`是BeanFactory接口`getBean()`的具体实现，在这里包含了Bean的实例化、初始化等操作；

针对`InstantiationAwareBeanPostProcessor`接口的处理就放在`createBean()`逻辑上；
先判断是否实现了`InstantiationAwareBeanPostProcessor`接口，如果符合就调用其具体的实现类方法`postProcessBeforeInstantiation`然后继续初始化，
否则就用默认实例化方式。

```java
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * 引入实例化策略类
     * 在Spring源码中，此策略的默认实现类是CglibSubclassingInstantiationStrategy
     */
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        // 检查此bean是否需要特殊代理进行实例化
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (bean != null) {
            // 如果需要，就返回代理实例化后的bean
            return bean;
        }
        return doCreateBean(beanName, beanDefinition);
    }

    /**
     * 检查是否需要提前实例化（代理默认实例化）
     *
     * @return 代理后的bean
     */
    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        // 使用特殊的代理实例化Bean
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null) {
            // 继续执行bean实例化后的逻辑
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            // 只执行这种用于实例化的BeanPostProcessor
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                // 注意：这里使用了JDK代理(Bean实例创建的代理方式)，否则用Cglib代理时无法直接进行强转（要先获取代理目标对象实例才行）
                Object result = ((InstantiationAwareBeanPostProcessor) processor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    
    ...   
}
```

## DefaultAdvisorAutoProxyCreator

回到`InstantiationAwareBeanPostProcessor`接口的具体实现类：`DefaultAdvisorAutoProxyCreator`，特殊的一点在于它还实现了`BeanFactoryAware`接口；

我们知道Aware接口的意义就在于让Spring感知到这些Bean，并通过通知的方式让这些Bean可以拿到容器中的信息。

对于具体的处理逻辑：

1. 从IOC容器中拿到所有的advisor切面定义（当前是在XML配置的AspectJExpressionPointcutAdvisor类），循环所有切面
2. 对于没一个切面，单独从IOC容器拿到对应BeanDefinition，根据实例化策略主动生成一个Bean实例
3. 从当前的切面中拿到：被代理类（上面创建的Bean实例）、通知拦截器advice
4. 使用Aop代理生成当前切面生成单独的代理实例（注意当前代码局限于一个Bean只能有一种匹配的切面定义）

```java
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        // 拿到所有的切面
        Collection<AspectJExpressionPointcutAdvisor> advisors =
                beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        try {
            for (AspectJExpressionPointcutAdvisor advisor : advisors) {
                ClassFilter classFilter = advisor.getPointcut().getClassFilter();
                // 检查切点是否符合
                if (classFilter.matches(beanClass)) {

                    // 主动生成一个Bean实例
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition);

                    // 构建切面信息
                    AdvisedSupport advisedSupport = new AdvisedSupport();
                    // 当前切点对应的类就是要被Aop代理的目标类
                    advisedSupport.setTargetSource(new TargetSource(bean));
                    advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                    // 通知就是一种拦截器
                    advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());

                    // 使用Aop代理重新生成一个代理类
                    return new ProxyFactory(advisedSupport).getProxy();
                }
            }
        } catch (Exception e) {
            throw new BeansException("Error create proxy bean for [" + beanName + "]", e);
        }

        return beanClass;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
```

## 测试

```java
    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop-auto-proxy.xml");

        People people = applicationContext.getBean("people", People.class);
        people.say();
    }
```

