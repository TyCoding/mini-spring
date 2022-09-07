package cn.tycoding.spring.aop.proxy;

/**
 * @author tycoding
 * @since 2022/9/7
 */
public class Student implements People {
    @Override
    public void say() {
        System.out.println("this is Student...");
    }
}
