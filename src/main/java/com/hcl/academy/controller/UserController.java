package com.hcl.academy.controller;

import com.hcl.academy.dto.GenericResponse;
import com.hcl.academy.dto.request.RegisterDto;
import com.hcl.academy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
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
    public GenericResponse<String> login() {
        return GenericResponse.success("Login Successfully");
    }
}
