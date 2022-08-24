package cn.tycoding.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.PropertyValue;
import cn.tycoding.spring.beans.factory.DisposableBean;
import cn.tycoding.spring.beans.factory.InitializingBean;
import cn.tycoding.spring.beans.factory.config.AutowireCapableBeanFactory;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import cn.tycoding.spring.beans.factory.config.BeanPostProcessor;
import cn.tycoding.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

/**
 * BeanFactory的委托实现类，主要实现Bean的完整构建
 *
 * @author tycoding
 * @since 2022/8/1
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * 引入实例化策略类
     * 在Spring源码中，此策略的默认实现类是CglibSubclassingInstantiationStrategy
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
            // 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);

            // 初始化Bean
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册有销毁方法的Bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        registrySingleton(beanName, bean);
        return bean;
    }

    /**
     * 为Bean填充属性信息
     *
     * @param beanName       Bean名称
     * @param bean           Bean对象
     * @param beanDefinition 对象信息
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            // 遍历属性信息，通过反射写入属性信息（反射拿到属性的setter方法填充）
            for (PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()) {
                // 属性名称，此名称必须有对应的setter方法，不然无法填充
                String name = pv.getName();
                // 属性值
                Object value = pv.getValue();

                // 判断value是否是其他Bean的引用
                if (value instanceof BeanReference) {
                    // 如果存在引用就先实例化此Bean
                    BeanReference br = (BeanReference) value;
                    value = getBean(br.getBeanName());
                }

                // 这里使用Hutool的工具类，利用反射填充属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }

    /**
     * 初始化Bean
     *
     * @param beanName       Bean名称
     * @param bean           Bean实例
     * @param beanDefinition Bean定义
     * @return 初始化后的Bean实例
     */
    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行BeanPostProcessor的前置处理器
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 初始化Bean
        try {
            invokeInitMethods(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method bean[" + beanName + "] failed", e);
        }

        // 执行BeanPostProcessor的后置处理器
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 初始化Bean的详细逻辑
     *
     * @param beanName       Bean名称
     * @param bean           Bean实例
     * @param beanDefinition Bean定义
     * @return 初始化后的Bean实例
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof InitializingBean) {
            // 执行子类的init方法
            ((InitializingBean) bean).afterPropertiesSet();
        }
        String initMethod = beanDefinition.getInitMethod();
        if (StrUtil.isNotEmpty(initMethod)) {
            // 这里使用Hutool的反射工具类拿到此方法对象
            Method method = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethod);
            if (method == null) {
                throw new BeansException("Couldn't find a init method named '" + initMethod + "' on bean with name '" + beanName);
            }
            method.invoke(bean);
        }
    }

    /**
     * 注册有销毁方法的Bean
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 只有singletons类型的Bean才有销毁方法
        if (beanDefinition.isSingleton()) {
            if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethod())) {
                registerDisposableBean(beanName, new DisposableBeanAdapter(beanName, bean, beanDefinition.getDestroyMethod()));
            }
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
