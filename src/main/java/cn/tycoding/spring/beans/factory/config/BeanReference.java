package cn.tycoding.spring.beans.factory.config;

/**
 * Bean 引用
 *
 * @author tycoding
 * @since 2022/8/6
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
