package com.example.carx_testing.services;

import com.example.carx_testing.models.UserDTO;
import com.example.carx_testing.repositories.UserRepositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public String getJsonUser(String uuid) {

        UUID uuidUser = UUID.fromString(uuid);

        ObjectMapper objectMapper = new ObjectMapper();
        return userRepositories.findUserById(uuidUser).map(user -> {
            try {
                return objectMapper.writeValueAsString(user.getUser_data());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "JsonProcessingException";
            }
        }).orElse(null);
    }

    public void setJsonUser(String data, String uuid) {

        UUID uuidUser = UUID.fromString(uuid);
        userRepositories.findUserById(uuidUser).ifPresent(user -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                UserDTO userDTO = objectMapper.readValue(data, UserDTO.class);
                user.setUser_data(userDTO);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
