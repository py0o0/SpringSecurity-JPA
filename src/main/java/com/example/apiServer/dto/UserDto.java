package com.example.apiServer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
    private String userId;
    private String userPass;
    private String role;
}
