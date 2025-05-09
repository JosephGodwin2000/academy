package com.hcl.academy.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterDto {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Contact number is invalid")
    private String contactNumber;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String emailId;
}
