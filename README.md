# Mini-Spring

Mini版Spring框架实现。

版本说明：
- 参考JDK源码版本：`1.8`
- 参考Spring源码版本： `Spring5.2.x`

学习方式：Git Tree查看分支列表，分支名称对应了文档所在文件夹名称。

## Docs

### Ioc

- `01-bean-factory` [BeanFactory基础实现](docs/ioc/01-bean-factory/README.md)
- `02-bean-instance-strategy` [Bean实例化策略（JDK和Cglib动态代理）](docs/ioc/02-bean-instance-strategy/README.md)
- `03-bean-property-value` [填充Bean属性信息](docs/ioc/03-bean-property-value/README.md)
- `04-resource-loader` [资源加载器](docs/ioc/04-resource-loader/README.md)
- `05-xml-bean-definition` [XML文件装配Bean](docs/ioc/05-xml-bean-definition/README.md)
- `06-bean-processor` [Bean容器扩展机制](docs/ioc/06-bean-processor/README.md)
- `07-application-context` [Spring应用上下文（refresh）](docs/ioc/07-application-context/README.md)
- `08-bean-init-destroy` [Bean的init和destroy（close）](docs/ioc/08-bean-init-destroy/README.md)
- `09-spring-aware` [Spring的Aware接口实现](docs/ioc/09-spring-aware/README.md)
- `10-bean-scope` [Bean作用域（Bean生命周期）](docs/ioc/10-bean-scope/README.md)
- `11-factory-bean` [FactoryBean接口实现](docs/ioc/11-factory-bean/README.md)
- `12-spring-event` [Spring的事件机制](docs/ioc/12-spring-event/README.md)

**总结**

[IOC阶段技术点整理](docs/ioc/README.md)

### Aop

- `13-aspectj-expression` [AspectJ切面表达式](docs/aop/13-aspectj-expression/README.md)
- `14-jdk-dynamic-proxy` [Aop之JDK动态代理实现（拦截器实现）](docs/aop/14-jdk-dynamic-proxy/README.md)
- `16-proxy-factory` [Aop之代理工厂实现](docs/aop/16-proxy-factory/README.md)


## Tips

插件说明：
1. `.uml` 文件对应IDEA内置的 `Diagram` 插件；
2. `.drawio` 文件对应 `Draw.io` 软件或者IDEA插件市场的 `Diagrams.net Integration` 插件；

## Thanks

[https://github.com/DerekYRC/mini-spring](https://github.com/DerekYRC/mini-spring)