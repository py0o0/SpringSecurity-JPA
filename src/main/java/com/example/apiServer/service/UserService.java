package com.example.apiServer.service;

import com.example.apiServer.dto.UserDto;
import com.example.apiServer.entity.User;
import com.example.apiServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void save(UserDto user) {
        userRepository.save(User.builder()
                        .userId(user.getUserId())
                        .userPass( bCryptPasswordEncoder.encode(user.getUserPass()))
                        .role(user.getRole())
                .build());
    }
}
