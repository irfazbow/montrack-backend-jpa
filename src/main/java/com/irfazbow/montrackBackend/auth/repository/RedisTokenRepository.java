package com.irfazbow.montrackBackend.auth.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String email, String token, long expiration) {
        redisTemplate.opsForValue().set(email, token, expiration, TimeUnit.HOURS);
    }

    public String getToken(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public void deleteToken(String email) {
        redisTemplate.delete(email);
    }
}
