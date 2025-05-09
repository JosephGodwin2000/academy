package com.hcl.academy.controller;

import com.hcl.academy.dto.GenericResponse;
import com.hcl.academy.dto.request.LoginDto;
import com.hcl.academy.dto.request.RegisterDto;
import com.hcl.academy.dto.response.LoginResponse;
import com.hcl.academy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public GenericResponse<String> register(@Valid @RequestBody RegisterDto request) {
        System.out.println(request);
        userService.register(request);
        return GenericResponse.success("Registered Successfully");
    }

    @PostMapping("/login")
    public GenericResponse<LoginResponse> login(@Valid @RequestBody LoginDto request) {
        System.out.println(request);
        return GenericResponse.success("Login Successfully", userService.login(request));
    }
}
