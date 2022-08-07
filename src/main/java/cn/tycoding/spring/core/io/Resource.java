package cn.tycoding.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件或资源的描述接口
 * 在Spring源码中，Resource接口定义了一些基础方法如：isFile() isUrl() isOpen()...
 * Resource接口的抽象实现类是：AbstractResource
 * 而获取文件写入流的接口是 WriteableResource extends Resource
 * 而获取文件读取流的类是 InputStreamResource extends AbstractResource
 * <p>
 * 本项目仅仅做读取外部资源的功能，因此不设计那么复杂了
 *
 * @author tycoding
 * @since 2022/8/7
 */
public interface Resource {

    /**
     * 获取文件读取流
     *
     * @return InputStream 对象
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
