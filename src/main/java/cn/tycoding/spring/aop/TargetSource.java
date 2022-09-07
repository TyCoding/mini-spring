package cn.tycoding.spring.aop;

/**
 * 包装了当前Aop调用的目标对象
 *
 * @author tycoding
 * @since 2022/9/6
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
