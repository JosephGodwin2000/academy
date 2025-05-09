package com.hcl.academy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Length(min = 10, message = "Password length should be in 10 characters")
    @NotBlank(message = "Email is mandatory")
    private String password;
}
