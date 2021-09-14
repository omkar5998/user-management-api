package com.user.controller;

import com.user.entity.User;
import com.user.service.inf.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.info("Create User Request: " + user);
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

}
