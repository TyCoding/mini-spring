package cn.tycoding.spring.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 资源加载策略接口的默认实现
 *
 * @author tycoding
 * @since 2022/8/7
 */
public class DefaultResourceLoader implements ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 从本地相对路径获取
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 作为URL处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 从磁盘地址获取
                return new FileSystemResource(location);
            }
        }
    }
}
