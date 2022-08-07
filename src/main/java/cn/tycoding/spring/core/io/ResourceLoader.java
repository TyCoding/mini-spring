package cn.tycoding.spring.core.io;

/**
 * 加载资源的策略接口
 * 在Spring源码中此接口应该支持：
 * 1.从磁盘路径：file:C:/test.txt 获取资源
 * 2.从项目相对路径获取资源：classpath:test.txt
 * 3.从URL获取：http://xxx.com/test.txt
 *
 * @author tycoding
 * @since 2022/8/7
 */
public interface ResourceLoader {

    /**
     * 根据位置获取资源
     *
     * @param location 资源位置
     * @return
     */
    Resource getResource(String location);
}
