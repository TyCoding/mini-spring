package cn.tycoding.spring.ioc.context;

import cn.tycoding.spring.beans.factory.DisposableBean;
import cn.tycoding.spring.beans.factory.InitializingBean;

/**
 * @author tycoding
 * @since 2022/8/24
 */
public class InitDestroyBean implements InitializingBean, DisposableBean {

    private String name;

    @Override
    public void destroy() throws Exception {
        System.out.println("InitDisBean destroy() 销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitDisBean afterPropertiesSet() 验证配置方法");
    }

    public void customInitMethod() {
        System.out.println("InitDisBean 自定义初始化方法");
    }

    public void customDestroyMethod() {
        System.out.println("InitDisBean 自定义销毁方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
