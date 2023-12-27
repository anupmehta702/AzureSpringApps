package com.example.studentservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean("StudentRedisTemplate")
    public RedisTemplate setUpRedisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate<Integer,Student>();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("studentcache.redis.cache.windows.net");
        configuration.setPassword("4ZAWQdB2plYE9fh4khol5RDxWf1wQol14AzCaED2Mig=");
        configuration.setPort(6379);
        RedisConnectionFactory factory = new LettuceConnectionFactory(configuration);
        ((LettuceConnectionFactory) factory).afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
