# seckill
并发秒杀项目

本项目使用了redis+rabbitmq+canal+resilice4j

1、使用了rabbitmq进行异步下单操作

2、使用了分布式锁解决了并发安全问题

3、使用resilience4j进行了接口限流

4、使用了canal,通过rabbitmq实现了将mysql的秒杀产品数据同步到redis缓存中

