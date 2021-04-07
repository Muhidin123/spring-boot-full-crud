package com.muhidin.crud.application.controller;

import java.util.List;
import java.util.Optional;

import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.services.UserServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    @Autowired
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userServices.all();
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userServices.newUser(newUser);
    }


    // Single item
    @GetMapping("/users/{id}")
    Optional<User> oneUser(@PathVariable Long id)  {
        return userServices.oneUser(id);
    }

    //PUT request
    @PutMapping("/users/{id}")
    User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
        return userServices.updateOrReplaceUser(newUser, id);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
    }
}