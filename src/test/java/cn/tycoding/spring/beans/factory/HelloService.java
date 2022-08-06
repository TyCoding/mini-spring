package cn.tycoding.spring.beans.factory;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class HelloService {

    private String name;
    private String des;

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

    @Override
    public String toString() {
        return "HelloService{" +
                "name='" + name + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
