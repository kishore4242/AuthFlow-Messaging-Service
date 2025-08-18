package com.example.messaging.service;


import com.example.messaging.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OtpCacheService {

    private final RedisTemplate<String, String> redisTemplate;

    public OtpCacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendOtpToRedis(String email, String otp) {
        log.debug("inside the otp set");
        redisTemplate.opsForValue().set(email, otp, EmailUtils.OTP_EXPIRY);
        log.debug("after the value set");
    }

    public String getOtpFromRedis(String email){
        return redisTemplate.opsForValue().get(email);
    }
}
