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
     * Class信息
     */
    private Class beanClass;

    /**
     * 属性信息
     */
    private MutablePropertyValues propertyValues;

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
}
