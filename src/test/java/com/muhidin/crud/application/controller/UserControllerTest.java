package com.muhidin.crud.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhidin.crud.application.entity.User;
import com.muhidin.crud.application.repository.UserRepository;
import com.muhidin.crud.application.services.UserServices;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserServices userServices;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserController userController;

    private final User request = new User();

    @Test
    public void it_should_return_created_user() throws Exception {
        request.setName("TEST NAME");
        request.setEmail("TEST EMAIL");

        userServices.newUser(request);


        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        Mockito.when(userServices.newUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()));


    }

    @Mock
    UserRepository userRepository;


    @Test
    public void it_should_return_a_saved_user() {
        request.setName("Muhidin");
        request.setEmail("Muhidin@gmail.com");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
        User created = userServices.newUser(request);
        System.out.println(created);

        assertThat(request.getName()).isSameAs(created.getName());
    }




}