package com.example.messaging.listner;

import com.example.messaging.dto.ForgetPasswordRequest;
import com.example.messaging.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Listener {

    private final EmailService emailService;

    @KafkaListener(topics = "forget-password",groupId = "new-group")
    public void receivedInfo(ForgetPasswordRequest forgetPasswordRequest){
        emailService.sendEmail(forgetPasswordRequest);
        log.info("Otp send to mail {} successfully.",forgetPasswordRequest.getEmail());
    }
}
