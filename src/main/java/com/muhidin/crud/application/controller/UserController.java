package com.muhidin.crud.application.controller;

import java.util.List;
import java.util.Optional;

import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.repository.UserRepository;
import com.muhidin.crud.application.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private UserServices userServices;

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
    Optional<User> one(@PathVariable Long id)  {
//        .orElseThrow(ChangeSetPersister.NotFoundException::new)
        return repository.findById(id);
    }

    @PutMapping("/users/{id}")
    User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}