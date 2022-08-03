package cn.tycoding.extend;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试Bean实例化的几种方式
 *
 * @author tycoding
 * @since 2022/8/3
 */
public class BeanInstanceTest {

    /**
     * 无参构造函数实例化Bean
     */
    @Test
    public void t1() throws InstantiationException, IllegalAccessException {
        BeanInstanceClazz instance = BeanInstanceClazz.class.newInstance();
        System.out.println(instance);
    }

    /**
     * 有参构造函数实例化Bean
     */
    @Test
    public void t2() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        Class<BeanInstanceClazz> clazz = BeanInstanceClazz.class;
        Constructor<BeanInstanceClazz> constructor = clazz.getDeclaredConstructor(String.class);
        BeanInstanceClazz instance = constructor.newInstance("tycoding");
        System.out.println(instance.getName());
    }

    @Test
    public void t3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BeanInstanceClazz.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        Object instance = enhancer.create();
        System.out.println(instance);
    }
}

class BeanInstanceClazz {
    private String name;

    public BeanInstanceClazz() {
    }

    public BeanInstanceClazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}