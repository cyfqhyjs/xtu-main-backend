package com.xtuonline.xtu_backend.model.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}