package com.profiles.controller;

import com.profiles.dto.UserDto;
import com.profiles.user.userEntity.UserEntity;
import com.profiles.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBController {
    @Autowired
    private UserRepo userRepo;



    @PostMapping("/user")
    public UserEntity user(@RequestBody UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        return userRepo.save(entity);
    }
}