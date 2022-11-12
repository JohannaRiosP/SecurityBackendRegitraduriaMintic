package com.misiontic.g10.securityBackend.controllers;

import com.misiontic.g10.securityBackend.models.User;
import com.misiontic.g10.securityBackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int idUser){
        return  this.userServices.show(idUser);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUser(@RequestBody User user){
        return this.userServices.create(user);
    }

    @PutMapping("/updated/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@PathVariable("id") int idUser, @RequestBody User user){
        return this.userServices.update(idUser, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser(@PathVariable("id") int idUser){
        return this.userServices.delete(idUser);
    }
}

