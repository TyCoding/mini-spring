package cn.tycoding.spring.context.support;

import cn.tycoding.spring.beans.BeansException;

/**
 * 加载配置文件地址
 *
 * @author tycoding
 * @since 2022/8/23
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从配置文件加载BeanDefinition并刷新应用上下文
     *
     * @param configLocations 配置文件地址
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }

}
