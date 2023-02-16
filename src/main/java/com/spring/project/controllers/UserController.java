package com.spring.project.controllers;

import com.spring.project.dao.UserDao;
import com.spring.project.models.User;
import com.spring.project.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users/{id}")
    public User getUser (@PathVariable Long id) {
        User user = new User();

        user.setId(1L);
        user.setName("tuma");
        user.setLastname("ella");

        return user;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {

        if (validateToken(token)) {
            return null;
        }

        return userDao.getUsers();
    }

    private boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void signIn(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.signIn(user);
    }

    @RequestMapping(value = "api/user1")
    public User edit() {
        User user = new User();
        user.setName("mono");
        user.setLastname("nomo");
        user.setEmail("tuma@gmail.com");
        user.setPhone("12341234234");
        return user;
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value = "Authorization") String token,
                       @PathVariable Long id) {
        if (!validateToken(token)) { return; }
        userDao.delete(id);
    }

}
