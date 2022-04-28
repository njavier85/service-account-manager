package com.orion.account.serviceaccountmanager.controller;

import com.orion.account.serviceaccountmanager.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable("id")int userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserbyId(userId));
    }
}
