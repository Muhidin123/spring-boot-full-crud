package com.muhidin.crud.application.services;


import com.muhidin.crud.application.entity.User;

import java.util.List;
import java.util.Optional;

public interface InterfaceUserServices {
    User newUser(User user);
    List<User> all();
    Optional<User> oneUser(Long id);
    User updateOrReplaceUser( User newUser ,Long id);
    void deleteUser(Long id);

}
