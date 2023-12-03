package com.example.indatacoreback.Services.Interfaces;

import com.example.indatacoreback.DTO.UserDto;
import com.example.indatacoreback.Models.User;

import java.util.List;

public interface IUserService {
    public List<UserDto> getAllUsers();
    public User addUser(UserDto user);
    public User addGeneratedUser();
}
