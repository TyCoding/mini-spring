package cn.tycoding.spring.beans.factory;

/**
 * 由BeanFactory完成Bean实例化和初始化后，需要立即执行的接口
 *
 * @author tycoding
 * @since 2022/8/24
 */
public interface InitializingBean {

    /**
     * Bean实例初始化完成后，进行整体配置和初始化验证
     */
    void afterPropertiesSet() throws Exception;
}
