package com.example.myseckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
* Redis配置类
*
* @author zhoubin
* @since 1.0.0
*/
@Configuration
public class RedisConfig {
@Bean
public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory
connectionFactory){
RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//key序列器
redisTemplate.setKeySerializer(new StringRedisSerializer());
//value序列器

redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//Hash类型 key序列器
redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//Hash类型 value序列器
redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
redisTemplate.setConnectionFactory(connectionFactory);
return redisTemplate;
}
}