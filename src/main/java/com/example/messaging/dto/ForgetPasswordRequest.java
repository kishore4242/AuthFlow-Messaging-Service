package com.example.messaging.dto;

import lombok.*;

@Data
@Getter
@Setter
public class ForgetPasswordRequest {
    private String email;
    private String username;
    private String otp;

    public ForgetPasswordRequest(String email,String username, String otp){
        this.email = email;
        this.username = username;
        this.otp = otp;
    }
    public ForgetPasswordRequest(){

    }
}