package cn.tycoding.spring.beans.factory.config;

/**
 * 保存Bean实例信息
 *
 * @author tycoding
 * @since 2022/8/1
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
