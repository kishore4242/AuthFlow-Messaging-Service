package com.example.messaging.service;

import com.example.messaging.dto.ForgetPasswordRequest;
import com.example.messaging.dto.LoginNotificationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {


    private final PasswordResetNotification passwordResetNotification;
    private final LoginNotificationService loginNotificationService;



    @Async("emailExecutor")
    public void sendResetEmail(ForgetPasswordRequest request) {
        passwordResetNotification.sendEmail(request);
    }

    @Async("loginExecutor")
    public void sendLoginEmail(LoginNotificationDto loginNotificationDto){
        loginNotificationService.sendEmail(loginNotificationDto);
    }

}