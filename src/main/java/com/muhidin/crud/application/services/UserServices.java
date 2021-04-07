package com.muhidin.crud.application.services;

import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements InterfaceUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User newUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> all(){
        return userRepository.findAll();
    }
}
