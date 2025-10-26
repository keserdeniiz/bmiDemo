package com.example.bmidemo.service;

import com.example.bmidemo.model.User;
import com.example.bmidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
}
