package cn.tycoding.spring.ioc.factory;

import cn.tycoding.spring.beans.factory.FactoryBean;

/**
 * @author tycoding
 * @since 2022/8/30
 */
public class HelloFactoryBean implements FactoryBean<Hello> {

   private String des;

    @Override
    public Hello getObject() throws Exception {
        Hello hello = new Hello();
        hello.setDes("这是HelloFactoryBean中的Hello对象");
        return hello;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
