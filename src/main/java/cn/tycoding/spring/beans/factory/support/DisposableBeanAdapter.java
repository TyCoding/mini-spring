package cn.tycoding.spring.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.spring.beans.BeansException;
import cn.tycoding.spring.beans.factory.DisposableBean;

import java.lang.reflect.Method;

/**
 * Bean销毁接口DisposableBean的适配器，执行Bean销毁逻辑
 *
 * @author tycoding
 * @since 2022/8/24
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final String beanName;
    private final Object bean;

    private final String destroyMethodName;

    public DisposableBeanAdapter(String beanName, Object bean, String destroyMethodName) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            // 执行子类的destroy方法
            ((DisposableBean) bean).destroy();
        }

        // 避免同时继承DisposableBean且方法名也是destroy，destroy方法执行两次的情况
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method method = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (method == null) {
                throw new BeansException("Couldn't find a destroy method name '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }
    }
}
