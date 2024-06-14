package com.irfazbow.montrackBackend.user.dto;

import com.irfazbow.montrackBackend.user.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;

    private String email;

    private String password;

//    @NotNull
//    @Min(1)
//    private int activeCurrency;

    public User toEntity() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
