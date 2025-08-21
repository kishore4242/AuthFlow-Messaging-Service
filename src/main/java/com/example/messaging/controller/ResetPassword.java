package com.example.messaging.controller;

import com.example.messaging.dto.RequestEmail;
import com.example.messaging.service.OtpCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ResetPassword {
    private final OtpCacheService otpCacheService;

    public ResetPassword(OtpCacheService otpCacheService) {
        this.otpCacheService = otpCacheService;
    }

    @PostMapping("/reset-password/{value}")
    public ResponseEntity<?> resetPassword(@RequestBody RequestEmail email, @PathVariable String value){
        try{
        otpCacheService.validateOtp(email.getEmail(),value);
        return ResponseEntity.ok().body(Map.of(
                "message", "Password reset success",
                "redirect","/login"
        ));

        }
        catch (Exception e){
            return ResponseEntity.ok().body(Map.of(
                    "message", "Some error occur during the password change"
            ));
        }
    }

}
