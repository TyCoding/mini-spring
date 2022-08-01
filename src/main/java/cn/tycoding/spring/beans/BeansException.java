package cn.tycoding.spring.beans;

/**
 * @author tycoding
 * @since 2022/8/1
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
