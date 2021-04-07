package com.muhidin.crud.application.controller;

import java.util.List;
import java.util.Optional;

import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin
    User newUser(@RequestBody User newUser) {
        return userServices.newUser(newUser);
    }


    @PostMapping(value = "/newUserTest", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userServices.newUser(user), HttpStatus.OK);
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