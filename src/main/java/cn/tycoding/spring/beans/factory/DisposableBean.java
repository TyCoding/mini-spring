package cn.tycoding.spring.beans.factory;

/**
 * 销毁Bean释放资源时实现的接口
 *
 * @author tycoding
 * @since 2022/8/24
 */
public interface DisposableBean {

    /**
     * 销毁接口
     */
    void destroy() throws Exception;
}
