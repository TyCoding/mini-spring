package cn.tycoding.spring.context.support;

import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.tycoding.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author tycoding
 * @since 2022/8/23
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] locations = getConfigLocations();
        if (locations != null) {
            reader.loadBeanDefinitions(locations);
        }
    }

    /**
     * 获取XML配置文件位置
     *
     * @return 集合
     */
    protected abstract String[] getConfigLocations();
}
