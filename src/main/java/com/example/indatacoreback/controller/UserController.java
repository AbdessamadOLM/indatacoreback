package com.example.indatacoreback.controller;

import com.example.indatacoreback.DTO.UserDto;
import com.example.indatacoreback.Models.User;
import com.example.indatacoreback.Services.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> CreateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto)
                , HttpStatus.CREATED);
    }

    @PostMapping("/generate-user")
    public ResponseEntity<User> generateUser(){
        return new ResponseEntity<>(userService.addGeneratedUser(), HttpStatus.CREATED);
    }
}
