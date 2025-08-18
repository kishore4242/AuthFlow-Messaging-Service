package com.example.messaging.service;

import com.example.messaging.dto.ForgetPasswordRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class EmailService {

    private static final String SUBJECT = "Forget-Password: One time password(otp)";
    private final OtpCacheService otpCacheService;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender,OtpCacheService otpCacheService) {
        this.mailSender = mailSender;
        this.otpCacheService = otpCacheService;
    }

    public void sendEmail(ForgetPasswordRequest request) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            String otp = generateOtp();

            message.setTo(request.getEmail());
            message.setSubject(SUBJECT);
            message.setText(buildOtpHtmlMessage(request.getUsername(), otp),true);
            log.info("before sending the email");
            mailSender.send(mimeMessage);
            log.info("after sending the mail mail and before save it to redis");
            otpCacheService.sendOtpToRedis(request.getEmail(),otp);
        }
        catch (MessagingException e){
            throw new RuntimeException("Mime message helper exception");
        }
    }

    private String generateOtp(){
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for(int i=0;i<6;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public String buildOtpHtmlMessage(String username, String otp) {
        return String.format("<h2>Hello %s ,</h2>"+
                "<p>Your <b>OTP</b> is: <span style='color:blue;'> %s </span></p>"+
                "<p>This OTP is valid for <b>5 minutes</b>. Please do not share it with anyone.</p>"+
                "<br>"+
                "<p>Regards,<br>Kishore Team</p>",username,otp);
    }
}