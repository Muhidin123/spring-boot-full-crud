package com.muhidin.crud.application.services;

import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements InterfaceUserServices {

//    @Autowired
    private final UserRepository userRepository;
    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User newUser(User user) {
     return userRepository.save(user);
    }

    @Override
    public List<User> all(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> oneUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateOrReplaceUser( User newUser ,Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
