package cn.tycoding.spring.core.io;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试资源加载器
 *
 * @author tycoding
 * @since 2022/8/7
 */
public class ResourceTest {

    /**
     * 加载classpath相对路径资源
     */
    @Test
    public void t1() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        InputStream inputStream = resource.getInputStream();
        String str = IoUtil.readUtf8(inputStream);
        System.out.println(str);
    }

    /**
     * 加载磁盘相对路径资源
     */
    @Test
    public void t2() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("src/test/resources/test.txt");
        InputStream inputStream = resource.getInputStream();
        String str = IoUtil.readUtf8(inputStream);
        System.out.println(str);
    }

    /**
     * 加载URL网络路径资源
     */
    @Test
    public void t3() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://github.com/TyCoding/mini-spring/tree/main/src/test/resources/test.txt");
        InputStream inputStream = resource.getInputStream();
        String str = IoUtil.readUtf8(inputStream);
        System.out.println(str);
    }
}
