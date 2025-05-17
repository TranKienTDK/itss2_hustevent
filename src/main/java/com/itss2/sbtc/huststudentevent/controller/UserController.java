package com.itss2.sbtc.huststudentevent.controller;

import com.itss2.sbtc.huststudentevent.domain.User;
import com.itss2.sbtc.huststudentevent.dto.response.ApiResponse;
import com.itss2.sbtc.huststudentevent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        return ApiResponse.<User>builder()
                .message("User created")
                .data(userService.createUser(user))
                .build();
    }
}
