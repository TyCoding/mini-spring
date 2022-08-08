package cn.tycoding.spring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.PropertyValue;
import cn.tycoding.spring.beans.factory.config.BeanDefinition;
import cn.tycoding.spring.beans.factory.config.BeanReference;
import cn.tycoding.spring.beans.factory.support.AbstractBeanDefinitionReader;
import cn.tycoding.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.tycoding.spring.core.io.Resource;
import cn.tycoding.spring.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 从XML文件读取BeanDefinition
 *
 * @author tycoding
 * @since 2022/8/7
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
    public static final String COMPONENT_SCAN_ELEMENT = "component-scan";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (IOException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 在Spring源码中有单独的doLoadDocument()解析Document文档，Spring使用了自己封装的解析工具类
     * 在本项目中我们直接使用Dom4j的工具类解析XML文档
     *
     * @param inputStream 资源文件输入流
     * @throws DocumentException 解析异常
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        // 拿到根节点下所有<bean></bean>节点
        List<Element> beanElems = root.elements(BEAN_ELEMENT);
        for (Element bean : beanElems) {
            // 获取<bean>标签携带的属性
            String id = bean.attributeValue(ID_ATTRIBUTE);
            String beanName = bean.attributeValue(NAME_ATTRIBUTE);
            String className = bean.attributeValue(CLASS_ATTRIBUTE);
            String initMethod = bean.attributeValue(INIT_METHOD_ATTRIBUTE);
            String destroyMethod = bean.attributeValue(DESTROY_METHOD_ATTRIBUTE);
            String beanScope = bean.attributeValue(SCOPE_ATTRIBUTE);

            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Cannot find class [" + className + "]");
            }

            // 处理id和name，id优于name
            beanName = StrUtil.isNotEmpty(id) ? id : beanName;
            if (StrUtil.isEmpty(beanName)) {
                // 如果仍是空，取类名首字母大写作为Bean名称
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 封装BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setDestroyMethod(destroyMethod);
            beanDefinition.setScope(beanScope);

            // 获取<bean>标签下的<property>标签集合
            List<Element> propertyElems = bean.elements(PROPERTY_ELEMENT);
            for (Element property : propertyElems) {
                String propertyAttrName = property.attributeValue(NAME_ATTRIBUTE);
                String propertyAttrValue = property.attributeValue(VALUE_ATTRIBUTE);
                String propertyAttrRef = property.attributeValue(REF_ATTRIBUTE);

                if (StrUtil.isEmpty(propertyAttrName)) {
                    throw new BeansException("Property name attribute cannot be empty");
                }

                // 判断是否存在ref bean引用
                Object value = propertyAttrValue;
                if (StrUtil.isNotEmpty(propertyAttrRef)) {
                    // ref指向了其他bean名称
                    value = new BeanReference(propertyAttrRef);
                }
                PropertyValue propertyValue = new PropertyValue(propertyAttrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                // bean已存在
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册BeanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }

}
