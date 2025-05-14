package com.xtuonline.xtu_backend.service;

import com.xtuonline.xtu_backend.model.dto.LoginRequest;
import com.xtuonline.xtu_backend.model.dto.RegisterRequest;
import com.xtuonline.xtu_backend.model.entity.User; 

public interface AuthService {
    /**
     * @param registerRequest
     * @return
     * @throws RuntimeException 
     */
    User register(RegisterRequest registerRequest);

    /**
     * @param loginRequest 
     * @return 
     * @throws RuntimeException 
     */
    User login(LoginRequest loginRequest);
}