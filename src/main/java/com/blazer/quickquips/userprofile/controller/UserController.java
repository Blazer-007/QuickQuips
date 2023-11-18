package com.blazer.quickquips.userprofile.controller;

import com.blazer.quickquips.userprofile.dto.AuthRequest;
import com.blazer.quickquips.userprofile.model.UserInfo;
import com.blazer.quickquips.userprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
        String resp = userService.addUser(userInfo);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return userService.authenticateAndGetToken(authRequest);
    }
}
