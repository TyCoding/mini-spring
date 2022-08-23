package cn.tycoding.spring.context;

import cn.tycoding.spring.beans.factory.HierarchicalBeanFactory;
import cn.tycoding.spring.beans.factory.ListableBeanFactory;
import cn.tycoding.spring.core.io.ResourceLoader;

/**
 * @author tycoding
 * @since 2022/8/23
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
