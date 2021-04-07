package com.muhidin.crud.application.services;


import com.muhidin.crud.application.entity.User;

import java.util.List;

public interface InterfaceUserServices {
    User newUser(User user);
    List<User> all();
}
