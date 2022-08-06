package cn.tycoding.spring.beans;

/**
 * Bean 属性信息
 *
 * @author tycoding
 * @since 2022/8/6
 */
public class PropertyValue {

    /**
     * 属性名，必须提供setter方法
     */
    private final String name;

    /**
     * 属性值
     */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
