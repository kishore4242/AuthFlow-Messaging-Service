package com.example.messaging.controller;

import com.example.messaging.service.OtpCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPassword {
    private final OtpCacheService otpCacheService;

    public ResetPassword(OtpCacheService otpCacheService) {
        this.otpCacheService = otpCacheService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody String emil){
        return ResponseEntity.ok().body(otpCacheService.getOtpFromRedis(emil));
    }

}
