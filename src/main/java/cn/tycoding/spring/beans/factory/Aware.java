package cn.tycoding.spring.beans.factory;

/**
 * 标记接口，实现该接口能被Spring容器感知，并通过回调方式通知，
 * 从而使得Bean能感知到自己在Spring容器中的定义，并可获取容器服务
 *
 * @author tycoding
 * @since 2022/8/29
 */
public interface Aware {
}
