package com.example.messaging.dto;

import lombok.*;

@Data
@Getter
@Setter
public class ForgetPasswordRequest {
    private String email;
    private String username;

    public ForgetPasswordRequest(String email,String username){
        this.email = email;
        this.username = username;
    }
    public ForgetPasswordRequest(){

    }
}