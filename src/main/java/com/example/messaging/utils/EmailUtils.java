package com.example.messaging.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
public class EmailUtils {
    private String otp;

    public static final int OTP_EXPIRY = 600;

}
