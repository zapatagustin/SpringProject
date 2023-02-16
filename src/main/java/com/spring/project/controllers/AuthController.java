package com.spring.project.controllers;

import com.spring.project.dao.UserDao;
import com.spring.project.models.User;
import com.spring.project.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User user) {

        User loginSuccess = userDao.getUsersByCredentials(user);

        if (loginSuccess != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(loginSuccess.getId()), loginSuccess.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
}
