## v5.0
1.添加Hystrix，实现容错，通过断路器实现在服务器不能用（cpu占用过高，请求总是超时）的情况下能够快速得到失败的响应，
防止不必要的等待和雪崩效应。

2.添加 hysrix.dashboard 监控数据
使用http://localhost:9200/hystrix.stream 查看