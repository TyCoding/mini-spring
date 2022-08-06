package cn.tycoding.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 对PropertyValue对象的具体处理
 *
 * @author tycoding
 * @since 2022/8/6
 */
public class MutablePropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属，如果已存在就覆盖（合并）
     *
     * @param pv 属性信息
     */
    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue propertyValue = propertyValueList.get(i);
            if (propertyValue.getName().equals(pv.getName())) {
                // 如果属性名称相同就覆盖
                propertyValueList.set(i, pv);
                return;
            }
        }
        propertyValueList.add(pv);
    }

    /**
     * 获取属性对象数组
     *
     * @return 数组对象
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 遍历属性集合，拿到匹配的属性对象
     *
     * @param propertyName 属性名称
     * @return 属性对象
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue propertyValue = propertyValueList.get(i);
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

}
