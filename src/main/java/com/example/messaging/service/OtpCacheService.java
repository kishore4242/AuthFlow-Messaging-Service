package com.example.messaging.service;


import com.example.messaging.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class OtpCacheService {

    private final StringRedisTemplate redisTemplate;

    public OtpCacheService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendOtpToRedis(String email, String otp) {
        log.debug("inside the otp set");
        redisTemplate.opsForValue().set(email, otp, Duration.ofSeconds(EmailUtils.OTP_EXPIRY));
        log.debug("after the value set");
    }

    public String getOtpFromRedis(String email){
        String value =  redisTemplate.opsForValue().get(email);
        return value;
    }

    public String validateOtp(String email, String otp){
        try {
            String storedOtp = getOtpFromRedis(email);
            if(otp == null){
                return "Invalid otp";
            }
            else if (otp.equals(storedOtp)) {
                deleteFromRedis(email);
                return "Otp success password reset";
            } else {
                return "Invalid otp";
            }
        }
        catch (Exception e){
            throw new RuntimeException("");
        }
    }

    private void deleteFromRedis(String email) {
        try{
            if (email != null){
                redisTemplate.opsForValue().getAndDelete(email);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Invalid email exception");
        }
    }
}
