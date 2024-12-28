package com.example.apiServer.controller;


import com.example.apiServer.dto.UserDto;
import com.example.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(UserDto user){
        return "redirect:/";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(String username, String userpass){
        UserDto user = new UserDto();
        user.setUserId(username);
        user.setUserPass(userpass);
        user.setRole("USER");
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
