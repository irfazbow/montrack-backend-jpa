package com.irfazbow.montrackBackend.user.service;

import com.irfazbow.montrackBackend.user.dto.RegisterRequestDto;
import com.irfazbow.montrackBackend.user.entity.User;

import java.util.List;

public interface UserService {
    User register(RegisterRequestDto user);

    User findByEmail(String email);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    User profile();
}
