package com.example.messaging.service;


import com.example.messaging.dto.LoginNotificationDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class LoginNotificationService {

    private static final String SUBJECT = "New Activity Found";

    private final JavaMailSender mailSender;

    public void sendEmail(LoginNotificationDto request){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(request.getEmail());
            message.setSubject(SUBJECT);
            message.setText(buildLoginNotificationMessage(request.getEmail(), request.getDateTime()),true);
            log.info("before sending the email");
            mailSender.send(mimeMessage);
            log.info("after sending the mail mail and before save it to redis");
        }
        catch (MessagingException e){
            throw new RuntimeException("Mime message helper exception");
        }
        catch (Exception e){
            throw new RuntimeException("Some other exception occurs", e);
        }
    }

    public String buildLoginNotificationMessage(String email, String timeStamp) {
        return String.format("<h2>Hello %s ,</h2>" +
                "<p>We noticed a new <b>login</b> to your account.</p>" +
                "<p><b>Time:</b> <span style='color:blue;'>%s</span></p>" +
                "<p>If this was you, no further action is required.</p>" +
                "<p>If you did not log in, please secure your account immediately.</p>" +
                "<br>" +
                "<p>Regards,<br>Kishore Team</p>", email, timeStamp);
    }

}
