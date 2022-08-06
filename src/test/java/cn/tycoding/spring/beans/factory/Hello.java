package cn.tycoding.spring.beans.factory;

/**
 * @author tycoding
 * @since 2022/8/6
 */
public class Hello {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
