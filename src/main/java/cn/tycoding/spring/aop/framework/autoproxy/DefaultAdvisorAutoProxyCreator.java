package cn.tycoding.spring.aop.framework.autoproxy;

import cn.tycoding.spring.aop.Advisor;
import cn.tycoding.spring.aop.ClassFilter;
import cn.tycoding.spring.aop.Pointcut;
import cn.tycoding.spring.aop.TargetSource;
import cn.tycoding.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.tycoding.spring.aop.framework.AdvisedSupport;
import cn.tycoding.spring.aop.framework.ProxyFactory;
import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.BeanFactory;
import cn.tycoding.spring.beans.factory.BeanFactoryAware;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import cn.tycoding.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.tycoding.spring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author tycoding
 * @since 2022/9/9
 */
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
