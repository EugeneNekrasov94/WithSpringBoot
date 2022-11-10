package com.example.withspringboot.service;

import com.example.withspringboot.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User findUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(Long id, User user);

    List<User> findAll();
}
