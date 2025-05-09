package com.hcl.academy.service;

import com.hcl.academy.dto.GenericResponse;
import com.hcl.academy.dto.request.LoginDto;
import com.hcl.academy.dto.request.RegisterDto;
import com.hcl.academy.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(@Valid RegisterDto request);

    LoginResponse login(@Valid LoginDto request);
}
