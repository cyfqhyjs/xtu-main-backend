package com.xtuonline.xtu_backend.controller;

import com.xtuonline.xtu_backend.model.dto.LoginRequest;
import com.xtuonline.xtu_backend.model.dto.RegisterRequest;
import com.xtuonline.xtu_backend.model.entity.User;
import com.xtuonline.xtu_backend.service.AuthService;
import javax.inject.Inject;
import jakarta.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController // 标记这是一个 RESTful 控制器，结合了 @Controller 和 @ResponseBody
@RequestMapping("/api/auth") // 所有在此控制器中的请求路径都以 /api/auth 开头
public class AuthController {

    @Autowired // 自动注入 AuthService
    private AuthService authService;

    @PostMapping("/register") // 处理 POST 请求到 /api/auth/register
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User registeredUser = authService.register(registerRequest);
            // 为了安全，通常不直接返回完整的 User 对象（特别是密码哈希）
            // 这里可以返回一个简单的成功消息或部分用户信息 DTO
            // return ResponseEntity.ok("User registered successfully!");
            // 或者返回不含敏感信息的用户数据 (需要创建 UserDto)
             return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully with ID: " + registeredUser.getId());
        } catch (RuntimeException e) {
            // 如果注册失败（例如用户名已存在），返回错误信息
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login") // 处理 POST 请求到 /api/auth/login
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User loggedInUser = authService.login(loginRequest);
            // 登录成功，通常会生成 JWT Token 并返回
            // 目前我们先简单返回成功信息或用户信息
            // return ResponseEntity.ok("User logged in successfully!");
            // 或者返回不含敏感信息的用户数据 (需要创建 UserDto)
            return ResponseEntity.ok("Login successful for user: " + loggedInUser.getUsername());
        } catch (RuntimeException e) {
            // 如果登录失败（用户不存在或密码错误），返回错误信息
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}