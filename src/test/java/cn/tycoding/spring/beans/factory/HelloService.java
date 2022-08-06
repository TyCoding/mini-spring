package cn.tycoding.spring.beans.factory;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class HelloService {

    private String name;
    private String des;
    private Hello hello;

    public void sayHello() {
        System.out.println("HelloService sayHello()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Hello getHello() {
        return hello;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "HelloService{" + "name='" + name + '\'' + ", des='" + des + '\'' + ", hello=" + hello + '}';
    }
}
