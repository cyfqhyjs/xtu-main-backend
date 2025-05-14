package com.xtuonline.xtu_backend.service;

import com.xtuonline.xtu_backend.model.dto.LoginRequest;
import com.xtuonline.xtu_backend.model.dto.RegisterRequest;
import com.xtuonline.xtu_backend.model.entity.User;
import com.xtuonline.xtu_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 标记这是一个服务层组件
public class AuthServiceImpl implements AuthService {

    @Autowired // 自动注入 UserRepository
    private UserRepository userRepository;

    @Autowired // 自动注入 PasswordEncoder (我们在 SecurityConfig 中定义了 Bean)
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest registerRequest) {
        // 1. 检查用户名是否已存在
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        // 2. 创建新用户
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        // 3. 对密码进行加密
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // 4. 保存用户到数据库
        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        // 1. 根据用户名查找用户
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: User not found with username: " + loginRequest.getUsername()));

        // 2. 验证密码是否匹配
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Error: Invalid password!");
        }

        // 3. 密码匹配，登录成功
        return user;
    }
}