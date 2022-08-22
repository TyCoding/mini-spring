package cn.tycoding.spring.beans.factory;

/**
 * @author tycoding
 * @since 2022/8/6
 */
public class Hello {

    private String msg;

    private String des;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "msg='" + msg + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
