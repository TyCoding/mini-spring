package cn.tycoding.spring.beans.factory.config;

import cn.tycoding.spring.beans.MutablePropertyValues;

/**
 * 保存Bean实例信息（包括各种属性信息）
 *
 * @author tycoding
 * @since 2022/8/1
 */
public class BeanDefinition {

    /**
     * Bean单例还是多例
     */
    private static String SCOPE_SINGLETON = "singleton";
    private static String SCOPE_PROTOTYPE = "prototype";

    /**
     * Class信息
     */
    private Class beanClass;

    /**
     * 属性信息
     */
    private MutablePropertyValues propertyValues;

    /**
     * 默认初始化方法
     */
    private String initMethod;

    /**
     * 默认销毁方法
     */
    private String destroyMethod;

    /**
     * 默认单例模式
     */
    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;
    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, MutablePropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new MutablePropertyValues() : propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestroyMethod() {
        return destroyMethod;
    }

    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? SCOPE_SINGLETON : scope;
        this.singleton = SCOPE_SINGLETON.equalsIgnoreCase(scope);
        this.prototype = SCOPE_PROTOTYPE.equalsIgnoreCase(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }
}
