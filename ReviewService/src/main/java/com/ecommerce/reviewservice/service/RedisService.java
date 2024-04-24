package com.ecommerce.reviewservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    // userId, itemId
    private final RedisTemplate redisTemplate;

    public void setValues(Integer key, Integer data) {
        ValueOperations<Integer, Integer> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValues(Integer key, Integer data, Duration duration) {
        ValueOperations<Integer, Integer> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    @Transactional
    public Integer getValues(Integer key) {
        ValueOperations<Integer, Integer> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    public void deleteValues(Integer key) {
        redisTemplate.delete(key);
    }
}
