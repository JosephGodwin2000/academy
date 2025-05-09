package com.hcl.academy.service;

import com.hcl.academy.dto.request.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(@Valid RegisterDto request);
}
