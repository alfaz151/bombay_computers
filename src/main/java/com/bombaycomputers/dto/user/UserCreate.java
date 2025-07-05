package com.bombaycomputers.dto.user;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Data
public class UserCreate {
    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    public String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    public String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    public String phone;
    
    public String address;
    public String role;
}