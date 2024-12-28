package com.example.apiServer.service;



import com.example.apiServer.entity.User;
import com.example.apiServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String username){
        return userRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException(username));

    }

}
