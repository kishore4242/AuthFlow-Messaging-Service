package com.example.messaging.listner;


import com.example.messaging.dto.ForgetPasswordRequest;
import com.example.messaging.dto.LoginNotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationSerializer implements Deserializer<LoginNotificationDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public LoginNotificationDto deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(data, LoginNotificationDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize message from topic " + topic, e);
        }
    }
}
