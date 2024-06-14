package com.irfazbow.montrackBackend.user.service.impl;

import com.irfazbow.montrackBackend.exception.ApplicationException;
import com.irfazbow.montrackBackend.user.dto.RegisterRequestDto;
import com.irfazbow.montrackBackend.user.entity.User;
import com.irfazbow.montrackBackend.user.repository.UserRepository;
import com.irfazbow.montrackBackend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequestDto user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ApplicationException("Email already in use");
        }

        User newUser = user.toEntity();
        var password = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(password);

        return userRepository.save(newUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User profile() {
        return null;
    }
}
