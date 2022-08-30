package cn.tycoding.spring.beans.factory;

/**
 * FactoryBean是一种特殊的Bean；而BeanFactory是Bean生产工具
 * FactoryBean特殊在于，即是一种特殊的Bean也能生产Bean；
 * FactoryBean可向容器中注册两个Bean，一个是他本身，另一个是他生产的Bean
 *
 * @author tycoding
 * @since 2022/8/29
 */
public interface FactoryBean<T> {

    /**
     * FactoryBean 生产的Bean
     *
     * @return 返回生产的Bean实例，而不是FactoryBean本身
     */
    T getObject() throws Exception;

    boolean isSingleton();
}
