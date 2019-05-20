> developing... 

# Mind-Map 

![pampas](https://github.com/darren-fu/scrip-paper/blob/master/imgs/pampas_brain.png)

# pampas
API网关  

- 请求proxy
    基本完成
- 服务发现
    - Composite: 完成
    - Simple:完成
    - Consul: 未完成
    - Etcd: 未开始
    - Zookeeper: 未开始
- 服务路由
    进行中
- Filter
    未开始
- 限流
    从RestyPass中迁移改造
- 降级
    未开始
- 负载均衡
    未开始 从RestyPass中迁移改造
- 流量切换
    结合负载均衡设计
- 协议转换(http,grpc,dubbo)
    - http 
        - asyncHttpClient实现 
            基本完成
        - OkHttp实现
            未开始
    - grpc
        进行中
    - dubbo
        未开始
- 动态调整(pampas-ui)
    - 配置刷新方式
        未设计
    - 配置存储方式
        mongodb，未实现
- 调用链路跟踪(open-tracing)
    未开始

# pampas-ui
网关管理平台

[pampas-ui](https://github.com/darren-fu/pampas-ui)


