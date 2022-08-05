package cn.tycoding.extend;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java代理设计模式测试。
 * Java的代理技术主要分为三类：1.JDK静态代理 2.JDK动态代理 3.Cglib动态代理
 * <p>
 * 代理模式的逻辑：接口 -> 接口实现类（被代理类） -> 代理类（无论是静态还是动态都是要生成被代理类的引用）
 *
 * @author tycoding
 * @since 2022/8/4
 */
public class ProxyTest {

    /**
     * 测试JDK静态代理
     * JDK静态代理技术。逻辑：
     * 1.定义接口
     * 2.定义接口实现类（被代理类）
     * 3.定义代理类实现接口（包含被代理类的引用），调用被代理类的处理逻辑
     */
    @Test
    public void t1() {
        JdkStaticProxy proxy = new JdkStaticProxy();
        proxy.say();
    }

    /**
     * 测试JDK动态代理
     */
    @Test
    public void t2() {
        ProxyTestInfImpl impl = new ProxyTestInfImpl();
        JdkDynamicProxy handler = new JdkDynamicProxy(impl);
        ProxyTestInf instance = (ProxyTestInf) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                impl.getClass().getInterfaces(), handler);
        System.out.println(instance);
        instance.say();
    }

    /**
     * 查看JDK动态代理生成的字节码文件
     */
    @Test
    public void t3() throws Exception {
        byte[] bytes = ProxyGenerator.generateProxyClass("$ProxyTestInfImpl", new Class[]{ProxyTestInf.class});
        // 如果Windows系统此路径不在项目target目录下，将 "/target/" 修改为 "//target//"
        String filePath = System.getProperty("user.dir") + "/target/$ProxyTestInfImpl.class";
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * Cglib动态代理，测试实现了接口的类
     */
    @Test
    public void t4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyTestInfImpl.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        ProxyTestInfImpl impl = (ProxyTestInfImpl) enhancer.create();
        impl.say();
    }

    /**
     * Cglib动态代理，测试未实现任何接口的类
     */
    @Test
    public void t5() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProxy.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        CglibProxy impl = (CglibProxy) enhancer.create();
        impl.say1();
        impl.say2();
    }
}

class CglibProxy {
    public void say1() {
        System.out.println("this CglibProxy say1()");
    }
    public void say2() {
        System.out.println("this CglibProxy say2()");
    }
}

/**
 * JDK动态代理类。核心：通过反射技术生成代理类
 */
class JdkDynamicProxy implements InvocationHandler {

    // 被代理类对象
    private Object obj;

    public JdkDynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}


/**
 * JDK静态代理类。核心：实现代理接口，包含一个被代理类的对象引用，调用被代理类的处理逻辑
 */
class JdkStaticProxy implements ProxyTestInf {
    private ProxyTestInf inf = new ProxyTestInfImpl();

    @Override
    public void say() {
        inf.say();
    }
}

/**
 * 接口
 */
interface ProxyTestInf {
    void say();
}

/**
 * 被代理的类
 */
class ProxyTestInfImpl implements ProxyTestInf {

    @Override
    public void say() {
        System.out.println("this is ProxyTestInfImpl");
    }
}
