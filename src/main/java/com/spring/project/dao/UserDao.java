package com.spring.project.dao;

import com.spring.project.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);

    void signIn(User user);

    User getUsersByCredentials(User user);
}
