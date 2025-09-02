package com.example.messaging.dto;

import lombok.*;

@Data
@Getter
@Setter
public class LoginNotificationDto {
    private String email;
    private String dateTime;

    public LoginNotificationDto(String email, String dateTime){
        this.email = email;
        this.dateTime = dateTime;
    }

    public LoginNotificationDto(){

    }
}
