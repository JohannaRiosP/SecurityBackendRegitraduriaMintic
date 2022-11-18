package com.misiontic.g10.securityBackend.controllers;

import com.misiontic.g10.securityBackend.models.User;
import com.misiontic.g10.securityBackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userServices.index();
    }

    @GetMapping("/by_id/{id}")
    public Optional<User> getUserById(@PathVariable("id") int idUser){
        return  this.userServices.show(idUser);
    }

    @GetMapping("/by_nickname/{nickname}")
    public Optional<User> getUserByNickname(@PathVariable("nickname") String nickname){
        return  this.userServices.showByNickname(nickname);
    }

    @GetMapping("/by_email/{email}")
    public Optional<User> getUserByEmail(@PathVariable("email") String email){
        return  this.userServices.showByEmail(email);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){

        return this.userServices.login(user);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody User user){

        return this.userServices.create(user);
    }

    @PutMapping("/updated/{id}")
    public User updateUser(@PathVariable("id") int idUser, @RequestBody User user){
        return this.userServices.update(idUser, user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int idUser){
        return this.userServices.delete(idUser);
    }
}

